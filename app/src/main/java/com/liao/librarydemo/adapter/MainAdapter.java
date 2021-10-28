package com.liao.librarydemo.adapter;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.liao.librarydemo.R;
import com.liao.librarydemo.entity.MainEntity;

import java.util.List;

/**
 * @ProjectName: LiaoLibraryDemo
 * @Package: com.liao.librarydemo.adapter
 * @ClassName: MainAdapter
 * @Description: description class
 * @Author: CHN_Liao
 * @CreateDate: 2021/10/27 14:59
 */
public class MainAdapter extends BaseSectionQuickAdapter<MainEntity, BaseViewHolder> {


    public MainAdapter(List<MainEntity> data) {
        super(R.layout.item_main_section, R.layout.item_main_content, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, MainEntity mainEntity) {
        baseViewHolder.setText(R.id.tvContent, mainEntity.getContent());
    }

    @Override
    protected void convertHeader(@NonNull BaseViewHolder baseViewHolder, @NonNull MainEntity mainEntity) {
        baseViewHolder.setText(R.id.tvSection, mainEntity.getContent());
    }
}
