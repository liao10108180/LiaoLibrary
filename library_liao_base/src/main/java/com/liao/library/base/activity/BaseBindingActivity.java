package com.liao.library.base.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * @ProjectName: LiaoLibraryDemo
 * @Package: com.liao.library.base
 * @ClassName: BaseDataBindingActivity
 * @Description: 支持DataBinding的BaseActivity
 * @Author: CHN_Liao
 * @CreateDate: 2021/10/26 14:46
 */
public abstract class BaseBindingActivity<VDB extends ViewDataBinding> extends SuperBaseActivity {

    protected VDB mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, getContentViewId());

        init(savedInstanceState);
    }
}
