package com.liao.librarydemo;

import android.os.Bundle;

import com.liao.library.base.fragment.BaseVMFragment;
import com.liao.librarydemo.databinding.ActivityMainBindingImpl;

/**
 * @ProjectName: LiaoLibraryDemo
 * @Package: com.liao.librarydemo
 * @ClassName: TestFragment
 * @Description: description class
 * @Author: CHN_Liao
 * @CreateDate: 2021/10/27 10:21
 */
public class TestFragment extends BaseVMFragment<MainViewModel, ActivityMainBindingImpl> {
    @Override
    protected MainViewModel createViewModel() {
        return null;
    }

    @Override
    protected int getContentViewId() {
        return 0;
    }

    @Override
    protected void init(Bundle bundle) {

    }

}
