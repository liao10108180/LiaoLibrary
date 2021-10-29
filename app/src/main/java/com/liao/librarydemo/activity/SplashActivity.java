package com.liao.librarydemo.activity;

import android.os.Bundle;

import com.liao.library.base.activity.BaseActivity;
import com.liao.library.utils.BarUtils;
import com.liao.librarydemo.R;

/**
 * @ProjectName: LiaoLibraryDemo
 * @Package: com.liao.librarydemo.activity
 * @ClassName: SplashActivity
 * @Description: description class
 * @Author: CHN_Liao
 * @CreateDate: 2021/10/29 16:10
 */
public class SplashActivity extends BaseActivity {
    @Override
    protected int getContentViewId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        BarUtils.transparentStatusBar(this);
    }
}
