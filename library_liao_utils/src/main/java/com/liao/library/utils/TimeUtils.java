package com.liao.library.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @ProjectName: LiaoLibraryDemo
 * @Package: com.liao.library.utils
 * @ClassName: TimeUtils
 * @Description: 时间工具类
 * @Author: CHN_Liao
 * @CreateDate: 2021/10/26 10:15
 */
public class TimeUtils {
    /**
     * 获取时间
     *
     * @param pattern 模式（如：yyyy-MM-dd HH:mm:ss）
     * @return
     */
    public static String getTime(String pattern) {
        return getTime(pattern, new Date());
    }

    /**
     * 根据时间戳获取时间
     *
     * @param pattern    模式（如：yyyy-MM-dd HH:mm:ss）
     * @param timeMillis 时间戳
     * @return
     */
    public static String getTime(String pattern, long timeMillis) {
        return getTime(pattern, new Date(timeMillis));
    }

    /**
     * 根据Date获取时间
     *
     * @param pattern 模式（如：yyyy-MM-dd HH:mm:ss）
     * @param date    Date对象
     * @return
     */
    public static String getTime(String pattern, Date date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.CHINA);
            return sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
