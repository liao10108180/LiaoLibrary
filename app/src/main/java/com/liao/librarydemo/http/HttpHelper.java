package com.liao.librarydemo.http;

import android.util.Log;

import com.liao.librarydemo.Constant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @ProjectName: LiaoLibraryDemo
 * @Package: com.liao.librarydemo.http
 * @ClassName: HttpHelper
 * @Description: description class
 * @Author: CHN_Liao
 * @CreateDate: 2021/11/1 16:15
 */
public class HttpHelper {

    private static OkHttpClient okHttpClient;
    //读取超时时间（秒）
    private static final int TIMEOUT_READ = 20;
    //写入超时时间（秒）
    private static final int TIMEOUT_WRITE = 20;
    //连接超时时间（秒）
    private static final int TIMEOUT_CONNECTION = 15;

    static {
        initOkHttpClient();

    }

    private static void initOkHttpClient() {

        synchronized (HttpHelper.class) {
            if (okHttpClient == null) {
                //Http请求日志拦截器
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        Log.e("HttpLog", message);
                    }
                });
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

                OkHttpClient.Builder builder = new OkHttpClient.Builder()
                        .retryOnConnectionFailure(true)
                        .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)
                        .writeTimeout(TIMEOUT_WRITE, TimeUnit.SECONDS)
                        .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
                        .addInterceptor(interceptor);

                okHttpClient = builder.build();
            }
        }
    }

    /**
     * 根据传入的url，和api创建retrofit
     *
     * @param clazz   ApiService
     * @param baseUrl
     * @param <T>
     * @return
     */
    private static <T> T createApi(Class<T> clazz, String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(clazz);
    }

    public static ApiService getApi() {
        return createApi(ApiService.class, Constant.Http.API_URL);
    }

    public static ApiService getApi(String url) {
        return createApi(ApiService.class, url);
    }

    public static <T> T getApi(Class<T> clazz, String url) {
        return createApi(clazz, url);
    }
}
