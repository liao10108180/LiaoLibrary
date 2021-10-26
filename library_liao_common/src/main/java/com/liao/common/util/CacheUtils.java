package com.liao.common.util;

import android.content.SharedPreferences;
import android.os.Parcelable;

import androidx.annotation.Nullable;

import com.tencent.mmkv.MMKV;

/**
 * @ProjectName: LiaoLibraryDemo
 * @Package: com.liao.common.util
 * @ClassName: CacheUtils
 * @Description: 轻量级缓存，如同SharedPreferences的MMKV，MMKV源于腾讯开源
 * @Author: CHN_Liao
 * @CreateDate: 2021/10/26 8:59
 */
public class CacheUtils {
    private static MMKV mmkv;

    static {
        initMMKV();

    }

    private static void initMMKV() {
        synchronized (CacheUtils.class) {
            if (mmkv == null) {
                mmkv = MMKV.defaultMMKV();
            }
        }
    }

    /**
     * 将SharedPreferences迁移到MMKV
     *
     * @param preferences
     */
    public static void importFromSharedPreferences(SharedPreferences preferences) {
        mmkv.importFromSharedPreferences(preferences);
    }

    /**
     * 缓存字符串
     *
     * @param key   key
     * @param value 字符串
     * @return
     */
    public static boolean cache(String key, @Nullable String value) {
        return mmkv.encode(key, value);
    }

    /**
     * 缓存float值
     *
     * @param key
     * @param value float值
     * @return
     */
    public static boolean cache(String key, float value) {
        return mmkv.encode(key, value);
    }

    /**
     * 缓存布尔值
     *
     * @param key
     * @param value
     * @return
     */
    public static boolean cache(String key, boolean value) {
        return mmkv.encode(key, value);
    }

    /**
     * 缓存整型
     *
     * @param key
     * @param value
     * @return
     */
    public static boolean cache(String key, int value) {
        return mmkv.encode(key, value);
    }

    /**
     * 缓存长整型
     *
     * @param key
     * @param value
     * @return
     */
    public static boolean cache(String key, long value) {
        return mmkv.encode(key, value);
    }

    /**
     * 缓存double
     *
     * @param key
     * @param value double
     * @return
     */
    public static boolean cache(String key, double value) {
        return mmkv.encode(key, value);
    }

    /**
     * 缓存字节
     *
     * @param key
     * @param value 字节
     * @return
     */
    public static boolean cache(String key, @Nullable byte[] value) {
        return mmkv.encode(key, value);
    }

    /**
     * 缓存实例
     *
     * @param key
     * @param t   实例
     * @param <T>
     * @return
     */
    public static <T extends Parcelable> boolean cache(String key, T t) {
        if (t == null) {
            return false;
        }
        return mmkv.encode(key, t);
    }

    public static String getString(String key) {
        return mmkv.decodeString(key);
    }

    public static String getString(String key, String defaultValue) {
        return mmkv.decodeString(key, defaultValue);
    }

    public static float getFloat(String key) {
        return mmkv.decodeFloat(key);
    }

    public static float getFloat(String key, float defaultValue) {
        return mmkv.decodeFloat(key, defaultValue);
    }

    public static boolean getBoolean(String key) {
        return mmkv.decodeBool(key);
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return mmkv.decodeBool(key, defaultValue);
    }

    public static int getInt(String key) {
        return mmkv.decodeInt(key);
    }

    public static int getInt(String key, int defaultValue) {
        return mmkv.decodeInt(key, defaultValue);
    }

    public static long getLong(String key) {
        return mmkv.decodeLong(key);
    }

    public static long getLong(String key, long defaultValue) {
        return mmkv.decodeLong(key, defaultValue);
    }

    public static double getDouble(String key) {
        return mmkv.decodeDouble(key);
    }

    public static double getDouble(String key, double defaultValue) {
        return mmkv.decodeDouble(key, defaultValue);
    }

    public static byte[] getByteArray(String key) {
        return mmkv.decodeBytes(key);
    }

    public static byte[] getByteArray(String key, byte[] defaultValue) {
        return mmkv.decodeBytes(key, defaultValue);
    }

    public static <T extends Parcelable> T getParcelable(String key, Class<T> tClass) {
        return mmkv.decodeParcelable(key, tClass);
    }

    /**
     * 清除指定key的缓存
     *
     * @param key
     */
    public static void removeKey(String key) {
        mmkv.removeValueForKey(key);
    }

    /**
     * 清除所有缓存
     */
    public static void clearAll() {
        mmkv.clearAll();
    }

}
