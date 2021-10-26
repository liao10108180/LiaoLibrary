package com.liao.common;

import android.app.Activity;
import android.app.Application;

import java.util.List;

/**
 * @ProjectName: LiaoLibraryDemo
 * @Package: com.liao.common
 * @ClassName: UtilsBridge
 * @Description: description class
 * @Author: CHN_Liao
 * @CreateDate: 2021/10/25 17:15
 */
public class LiaoBridge {
    static void init(Application app) {
        LiaoActivityLifecycleImpl.INSTANCE.init(app);
    }

    public static List<Activity> getActivityList() {
        return LiaoActivityLifecycleImpl.INSTANCE.getActivityList();
    }
}
