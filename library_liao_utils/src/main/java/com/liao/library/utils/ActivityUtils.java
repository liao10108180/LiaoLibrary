package com.liao.library.utils;

import android.app.Activity;
import android.os.Build;

import androidx.annotation.NonNull;

import com.liao.common.LiaoBridge;

import java.util.List;

/**
 * @ProjectName: LiaoLibraryDemo
 * @Package: com.liao.library.utils
 * @ClassName: ActivityUtils
 * @Description: Activity工具类
 * @Author: CHN_Liao
 * @CreateDate: 2021/10/26 10:07
 */
public class ActivityUtils {

    /**
     * 判断activity是否还存活
     *
     * @param activity
     * @return
     */
    public static boolean isActivityAlive(Activity activity) {
        return activity != null && !activity.isFinishing()
                && (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1 || !activity.isDestroyed());
    }

    /**
     * 结束Activity
     *
     * @param activity
     */
    public static void finishActivity(@NonNull Activity activity) {
        activity.finish();
    }

    /**
     * 结束指定 Activity.
     *
     * @param activity
     */
    public static void finishToActivity(@NonNull final Activity activity) {
        List<Activity> activities = LiaoBridge.getActivityList();
        for (Activity act : activities) {
            if (act.equals(activity)) {
                act.finish();
            }
        }
    }

    /**
     * 结束指定 Activity.
     *
     * @param clz
     */
    public static void finishToActivity(@NonNull final Class<? extends Activity> clz) {
        List<Activity> activities = LiaoBridge.getActivityList();
        for (Activity act : activities) {
            if (act.getClass().equals(clz)) {
                act.finish();
            }
        }
    }

    /**
     * 除了指定的activity，结束其他所有类型的 Activity.
     *
     * @param clz
     */
    public static void finishOtherActivities(@NonNull final Class<? extends Activity> clz) {
        List<Activity> activities = LiaoBridge.getActivityList();
        for (Activity act : activities) {
            if (!act.getClass().equals(clz)) {
                finishActivity(act);
            }
        }
    }

    /**
     * 结束所有activity
     */
    public static void finishAllActivities() {
        List<Activity> activityList = LiaoBridge.getActivityList();
        for (Activity act : activityList) {
            act.finish();
        }
    }

    /**
     * 结束除最新之外的所有 Activity
     */
    public static void finishAllActivitiesExceptNewest() {
        List<Activity> activities = LiaoBridge.getActivityList();
        for (int i = 1; i < activities.size(); i++) {
            finishActivity(activities.get(i));
        }
    }
}
