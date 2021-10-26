package com.liao.common;

import android.annotation.SuppressLint;
import android.app.Application;

/**
 * @ProjectName: LiaoLibraryDemo
 * @Package: com.liao.lib_utils
 * @ClassName: Utils
 * @Description: description class
 * @Author: CHN_Liao
 * @CreateDate: 2021/10/15 11:00
 */
public class LiaoUtils {
    @SuppressLint("StaticFieldLeak")
    private static Application sApp;

    private LiaoUtils() {
        throw new UnsupportedOperationException("Utils不可实例化！");
    }

    /**
     * 初始化Utils
     * @param app application
     */
    public static void init(final Application app) {
        if (app == null) {
            return;
        }
        sApp = app;

        LiaoBridge.init(sApp);
    }

    public static Application getApp(){
        if (sApp == null){
            throw new NullPointerException("Utils sApp 为空！");
        }
        return sApp;
    }


}
