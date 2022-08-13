package com.liao.librarydemo;

import android.view.Gravity;

import com.hjq.toast.ToastUtils;
import com.liao.common.util.LogUtils;
import com.liao.library.base.BaseApplication;

import java.util.HashMap;

/**
 * @ProjectName: LiaoLibraryDemo
 * @Package: com.liao.librarydemo
 * @ClassName: App
 * @Description: description class
 * @Author: CHN_Liao
 * @CreateDate: 2021/10/25 17:20
 */
public class App extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        //配置日志
        LogUtils.LogConfig.getInstance()
                .setFilePrefix("Liao") //日志文件名前缀
                .setLog2FileSwitch(true) //是否开启日志文件保存,默认为false
                .setLogSwitch(true) //是否开启日志，默认为true
                .setSaveDays(6) //日志文件保存天数,单位：天
                .apply();

        //Toast
        ToastUtils.init(this);
        ToastUtils.setGravity(Gravity.CENTER);

    }
}
