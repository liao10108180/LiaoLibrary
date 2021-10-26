package com.liao.librarydemo;

import android.os.Bundle;

import com.liao.library.base.BaseBindingActivity;
import com.liao.librarydemo.databinding.ActivityMainBinding;

public class MainActivity extends BaseBindingActivity<ActivityMainBinding> {

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }


}