package com.liao.library.base.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * @ProjectName: LiaoLibraryDemo
 * @Package: com.liao.library.base.fragment
 * @ClassName: BaseBindingFragment
 * @Description: 支持DataBinding的BaseFragment
 * @Author: CHN_Liao
 * @CreateDate: 2021/10/27 10:32
 */
public abstract class BaseBindingFragment<VDB extends ViewDataBinding> extends SuperBaseFragment {
    protected VDB mBinding;

    @Override
    protected View getCreateView(@NonNull LayoutInflater inflater,
                                 @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState,
                                 int layoutResID) {

        mBinding = DataBindingUtil.inflate(inflater, layoutResID, container, false);
        return mBinding.getRoot();
    }
}
