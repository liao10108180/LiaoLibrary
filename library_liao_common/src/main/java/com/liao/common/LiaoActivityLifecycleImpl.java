package com.liao.common;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.LinkedList;
import java.util.List;

/**
 * @ProjectName: LiaoLibraryDemo
 * @Package: com.liao.common
 * @ClassName: UtilsActivityLifecycleImpl
 * @Description: description class
 * @Author: CHN_Liao
 * @CreateDate: 2021/10/25 17:15
 */
final class LiaoActivityLifecycleImpl implements Application.ActivityLifecycleCallbacks {
    static final LiaoActivityLifecycleImpl INSTANCE = new LiaoActivityLifecycleImpl();
    private final LinkedList<Activity> mActivityList = new LinkedList<>();

    /**
     * 初始化Activity生命周期监听
     *
     * @param app
     */
    void init(Application app) {
        app.registerActivityLifecycleCallbacks(this);
    }

    private void setTopActivity(final Activity activity) {
        if (mActivityList.contains(activity)) {
            if (!mActivityList.getFirst().equals(activity)) {
                mActivityList.remove(activity);
                mActivityList.addFirst(activity);
            }
        } else {
            mActivityList.addFirst(activity);
        }
    }

    List<Activity> getActivityList() {
        return mActivityList;
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
        setTopActivity(activity);
    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {

    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
        setTopActivity(activity);
    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
        mActivityList.remove(activity);
    }
}