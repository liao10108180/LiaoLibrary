package com.liao.librarydemo;

import android.app.Application;

import com.liao.common.LiaoUtils;

/**
 * @ProjectName: LiaoLibraryDemo
 * @Package: com.liao.librarydemo
 * @ClassName: App
 * @Description: description class
 * @Author: CHN_Liao
 * @CreateDate: 2021/10/25 17:20
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        LiaoUtils.init(this);
    }
}
