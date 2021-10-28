package com.liao.library.base;

import android.app.Application;

import com.liao.common.LiaoUtils;
import com.tencent.mmkv.MMKV;

/**
 * @ProjectName: LiaoLibraryDemo
 * @Package: com.liao.library.base
 * @ClassName: BaseApplication
 * @Description: description class
 * @Author: CHN_Liao
 * @CreateDate: 2021/10/27 14:36
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化Utils
        LiaoUtils.init(this);
        //初始化MMKV
        MMKV.initialize(this);

    }
}
