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
    private List<T> mData;

    private View mView;

    private EditText mEditText;

    private RecyclerView rv;

    /**
     * EditText是否为当前焦点
     */
    private boolean isEditHasFocus = false;

    private ClickListener<T> mClickListener;

    private EditSelectAdapter.ItemContent<T> mItemContent;

    private EditSelectAdapter<T> mAdapter;

    public EditSelectPopup(Context context, EditText editText) {
        super(context);
        this.mEditText = editText;
        mView = LayoutInflater.from(context).inflate(R.layout.popup_edit_select, null);
    }

    private void initPopup() {
        int width = mEditText.getWidth();
        setWidth(width);

        initEditListener();

        rv = mView.findViewById(R.id.rv);
        mAdapter = new EditSelectAdapter<>(mItemContent);
        rv.setAdapter(mAdapter);

    }

    private void initEditListener() {
//        mEditText.setOnClickListener(v -> {
//            if (mClickListener != null) {
//                mClickListener.onEditClick(v);
//            }
//        });

//        mEditText.setOnFocusChangeListener((v, hasFocus) -> {
//            isEditHasFocus = hasFocus;
//
//            if (isEditHasFocus && !isShowing() && data != null && data.size() > 0) {
//                showAsDropDown(mEditText);
//
//            }
//        });

//        mEditText.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_UP) {
//                    Log.e("TAG", "onTouch: " + event.getAction());
//                    showAsDropDown(mEditText);
//                }
//                return false;
//            }
//        });
    }

    @Override
    public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
        if (mClickListener != null) {
            mClickListener.onItemClick(mAdapter.getItem(position));

//            dismiss();

        }
    }

    public void setList(List<T> data) {
        mData = data;
        if (mAdapter != null) {
            mAdapter.setList(mData);
        }

        if (mData == null) {
            dismiss();
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

        public <T> Builder setItemContent(EditSelectAdapter.ItemContent<T> itemContent) {
            editSelectPopup.mItemContent = itemContent;
            return this;

        }

        public <T> Builder setOnClickListener(ClickListener<T> clickListener) {
            editSelectPopup.mClickListener = clickListener;
            return this;
        }

        public <T> EditSelectPopup<T> build() {
            editSelectPopup.initPopup();

            return editSelectPopup;
        }
    }

    public void show() {
        showAsDropDown(mEditText);
    }
}
