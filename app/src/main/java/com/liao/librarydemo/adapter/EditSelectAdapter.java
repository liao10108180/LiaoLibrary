package com.liao.librarydemo.adapter;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.liao.librarydemo.R;

/**
 * @Author: LiaoZhenHui
 * @CreateDate: 2022-08-12
 * @Description: description class
 */
public class EditSelectAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {

    private ItemContent<T> mItemContent;

    public EditSelectAdapter(ItemContent<T> itemContent) {
        super(R.layout.item_popup_edit_select);
        mItemContent = itemContent;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder viewHolder, T t) {
        viewHolder.setText(R.id.tvContent, mItemContent.showItemText(t));
    }

    /**
     * Item内容
     *
     * @param <T>
     */
    public interface ItemContent<T> {
        String showItemText(T data);
    }
}
