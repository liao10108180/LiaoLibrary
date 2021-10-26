package com.liao.library.http;

import io.reactivex.rxjava3.annotations.NonNull;

/**
 * @ProjectName: LiaoLibraryDemo
 * @Package: com.liao.library.http
 * @ClassName: SimpleHttpCallback
 * @Description: 简易请求回调
 * @Author: CHN_Liao
 * @CreateDate: 2021/10/25 17:38
 */
public interface SimpleHttpCallback<T> {

    void onStart();

    void onSuccess(@NonNull T t);

    void onFail(String msg);

    void onFinish();
}
