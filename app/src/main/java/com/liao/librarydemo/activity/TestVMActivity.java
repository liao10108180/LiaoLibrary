package com.liao.librarydemo.activity;

import android.os.Bundle;

import com.liao.library.base.activity.BaseVMActivity;
import com.liao.librarydemo.R;
import com.liao.librarydemo.databinding.ActivityTestBinding;
import com.liao.librarydemo.viewmodel.TestViewModel;

/**
 * @ProjectName: LiaoLibraryDemo
 * @Package: com.liao.librarydemo.activity
 * @ClassName: TestVMActivity
 * @Description: description class
 * @Author: CHN_Liao
 * @CreateDate: 2021/11/1 15:23
 */
public class TestVMActivity extends BaseVMActivity<TestViewModel, ActivityTestBinding> {
    @Override
    protected TestViewModel createViewModel() {
        return new TestViewModel();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_test;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        mViewModel.test(0);
    }
}
