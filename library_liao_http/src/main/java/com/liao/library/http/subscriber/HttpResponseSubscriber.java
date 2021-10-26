package com.liao.library.http.subscriber;

import com.liao.library.http.HttpExceptionMsg;

import retrofit2.Response;

/**
 * @ProjectName: LiaoLibraryDemo
 * @Package: com.liao.library.http.subscriber
 * @ClassName: CommonResponseSubscriber
 * @Description: description class
 * @Author: CHN_Liao
 * @CreateDate: 2021/10/25 17:40
 */
public abstract class HttpResponseSubscriber<T> extends AbstractHttpSubscriber<Response<T>> {
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onNext(Response<T> t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable t) {
        onFail(HttpExceptionMsg.exceptionHandler(t));
        onFinish();
    }

    @Override
    public void onComplete() {
        onFinish();
    }

}
