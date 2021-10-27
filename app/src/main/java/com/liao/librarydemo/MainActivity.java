package com.liao.librarydemo;

import android.os.Bundle;

import com.liao.library.base.activity.BaseVMActivity;
import com.liao.librarydemo.databinding.ActivityMainBinding;

public class MainActivity extends BaseVMActivity<MainViewModel,ActivityMainBinding> {


    @Override
    protected MainViewModel createViewModel() {
        return new MainViewModel();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        mViewModel.testToast();
    }

}