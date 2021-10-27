package com.liao.library.base.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

/**
 * @ProjectName: LiaoLibraryDemo
 * @Package: com.liao.library.base
 * @ClassName: BaseActivity
 * @Description: description class
 * @Author: CHN_Liao
 * @CreateDate: 2021/10/26 14:07
 */
public abstract class BaseActivity extends SuperBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());

        init(savedInstanceState);
    }

}
