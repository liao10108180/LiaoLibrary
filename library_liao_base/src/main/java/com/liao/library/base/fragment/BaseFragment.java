package com.liao.library.base.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @ProjectName: LiaoLibraryDemo
 * @Package: com.liao.library.base.fragment
 * @ClassName: BaseFragment
 * @Description: description class
 * @Author: CHN_Liao
 * @CreateDate: 2021/10/27 10:25
 */
public abstract class BaseFragment extends SuperBaseFragment {

    @Override
    protected View getCreateView(@NonNull LayoutInflater inflater,
                                 @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState,
                                 int layoutResID) {

        return LayoutInflater.from(mActivity)
                .inflate(layoutResID, container, false);
    }

}
