package com.liao.library.http;

import android.net.ParseException;
import android.os.NetworkOnMainThreadException;
import android.util.Log;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;
import java.util.Objects;

import retrofit2.HttpException;

/**
 * @ProjectName: LiaoLibraryDemo
 * @Package: com.liao.library.http
 * @ClassName: HttpExceptionMsg
 * @Description: 请求网络异常提示
 * @Author: CHN_Liao
 * @CreateDate: 2021/10/25 17:30
 */
public class HttpExceptionMsg {
    public static final int HTTP_EXCEPTION_500 = 500;
    public static final int HTTP_EXCEPTION_400 = 400;
    public static final int HTTP_EXCEPTION_403 = 403;
    public static final int HTTP_EXCEPTION_307 = 307;

    public static String exceptionHandler(Throwable t) {
        String msg = "未知错误";
        if (t instanceof ConnectException) {
            msg = "网络连接异常";
        } else if (t instanceof UnknownHostException) {
            msg = "网络不可用";
        } else if (t instanceof SocketTimeoutException) {
            msg = "请求网络超时";
        } else if (t instanceof retrofit2.HttpException) {
            retrofit2.HttpException httpException = (retrofit2.HttpException) t;
            msg = convertStatusCode(httpException);
        } else if (t instanceof JsonParseException || t instanceof ParseException || t instanceof JSONException) {
            msg = "数据解析错误";
        } else if (t instanceof IllegalArgumentException) {
            IllegalArgumentException exception = (IllegalArgumentException) t;
            Log.e("错误", Objects.requireNonNull(exception.getMessage()));
            msg = exception.getMessage();
        } else if (t instanceof UnknownServiceException) {
            UnknownServiceException exception = (UnknownServiceException) t;
            msg = exception.getMessage();
        } else if (t instanceof NetworkOnMainThreadException) {
            NetworkOnMainThreadException exception = (NetworkOnMainThreadException) t;
            msg = "网络请求在主线程";
        }
        return msg;
    }

    public static String convertStatusCode(HttpException httpException) {
        String msg;
        if (httpException.code() == HTTP_EXCEPTION_500) {
            msg = "服务器发生错误";
        } else if (httpException.code() == HTTP_EXCEPTION_400) {
            msg = "请求地址不存在";
        } else if (httpException.code() == HTTP_EXCEPTION_403) {
            msg = "请求被服务器拒绝";
        } else if (httpException.code() == HTTP_EXCEPTION_307) {
            msg = "请求被重定向到其他页面";
        } else {
            msg = httpException.message();
        }
        return msg;
    }
}
