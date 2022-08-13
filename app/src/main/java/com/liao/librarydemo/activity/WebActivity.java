package com.liao.librarydemo.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.liao.library.base.activity.BaseBindingActivity;
import com.liao.librarydemo.R;
import com.liao.librarydemo.databinding.ActivityWebBinding;

/**
 * @ProjectName: LiaoLibraryDemo
 * @Package: com.liao.librarydemo.activity
 * @ClassName: WebActivity
 * @Description: description class
 * @Author: CHN_Liao
 * @CreateDate: 2021/11/3 14:38
 */
public class WebActivity extends BaseBindingActivity<ActivityWebBinding> {
    @Override
    protected int getContentViewId() {
        return R.layout.activity_web;
    }

    @SuppressLint({"JavascriptInterface", "SetJavaScriptEnabled"})
    @Override
    protected void init(Bundle savedInstanceState) {
    }
}
