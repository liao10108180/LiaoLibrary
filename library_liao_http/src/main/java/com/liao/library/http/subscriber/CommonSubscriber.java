package com.liao.library.http.subscriber;

import com.liao.library.http.HttpExceptionMsg;

/**
 * @ProjectName: LiaoLibraryDemo
 * @Package: com.liao.library.http.subscriber
 * @ClassName: CommonSubscriber
 * @Description: description class
 * @Author: CHN_Liao
 * @CreateDate: 2021/10/25 17:50
 */
public abstract class CommonSubscriber<T> extends AbstractSubscriber<T> {
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onNext(T t) {
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
