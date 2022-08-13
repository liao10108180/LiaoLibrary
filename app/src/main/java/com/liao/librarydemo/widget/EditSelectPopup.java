package com.liao.librarydemo.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.liao.librarydemo.R;
import com.liao.librarydemo.adapter.EditSelectAdapter;

import java.util.List;

/**
 * @Author: LiaoZhenHui
 * @CreateDate: 2022-08-12
 * @Description: description class
 */
public class EditSelectPopup<T> extends PopupWindow implements OnItemChildClickListener {
    private List<T> data;

    private View mView;

    private EditText mEditText;

    private RecyclerView rv;

    /**
     * EditText是否为当前焦点
     */
    private boolean isEditHasFocus = false;

    private ClickListener mClickListener;

    private EditSelectAdapter.ItemContent mItemContent;

    private EditSelectAdapter<T> mAdapter;

    public EditSelectPopup(Context context, EditText editText) {
        super(context);
        this.mEditText = editText;
        mView = LayoutInflater.from(context).inflate(R.layout.popup_edit_select, null);
    }

    private void initPopup() {
        setWidth(mEditText.getWidth());

        initEditListener();

        rv = mView.findViewById(R.id.rv);
        mAdapter = new EditSelectAdapter<>(mItemContent);
        rv.setAdapter(mAdapter);

    }

    private void initEditListener() {
        mEditText.setOnClickListener(v -> {
            if (mClickListener != null) {
                mClickListener.onEditClick(v);
            }
        });

        mEditText.setOnFocusChangeListener((v, hasFocus) -> {
            isEditHasFocus = hasFocus;

            if (isEditHasFocus && !isShowing() && data != null && data.size() > 0) {
                showAsDropDown(mEditText);

            }
        });
    }

    @Override
    public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
        if (mClickListener != null) {
            mClickListener.onItemClick(mAdapter.getItem(position));

            dismiss();

        }
    }

    public void setList(List<T> data) {
        if (mAdapter != null) {
            mAdapter.setList(data);
        }
    }

    /**
     * 点击事件监听
     *
     * @param <T>
     */
    public interface ClickListener<T> {
        void onEditClick(View v);

        void onItemClick(T data);
    }

    public static class Builder {
        private EditSelectPopup editSelectPopup;

        public <T> Builder(@NonNull Context context, @NonNull EditText editText) {
            editSelectPopup = new EditSelectPopup<T>(context, editText);

        }

        public EditSelectPopup build() {
            editSelectPopup.initPopup();

            return editSelectPopup;
        }
    }
}
