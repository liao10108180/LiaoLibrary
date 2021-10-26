package com.liao.library.http.observer;

import com.liao.library.http.HttpExceptionMsg;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import retrofit2.Response;

/**
 * @ProjectName: LiaoLibraryDemo
 * @Package: com.liao.library.http.observer
 * @ClassName: CommonResponseObserver
 * @Description: description class
 * @Author: CHN_Liao
 * @CreateDate: 2021/10/25 17:55
 */
public abstract class CommonResponseObserver<T> implements Observer<Response<T>> {

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        onStart(d);
    }

    @Override
    public void onNext(@NonNull Response<T> response) {
        //获取响应头部数据
        onSuccess(response);
        onFinish();
    }

    @Override
    public void onError(@NonNull Throwable e) {
        onFail(HttpExceptionMsg.exceptionHandler(e));
        onFinish();
    }

    @Override
    public void onComplete() {
        onFinish();
    }

    protected abstract void onStart(Disposable d);

    protected abstract void onSuccess(@NonNull Response<T> response);

    protected abstract void onFail(String msg);

    protected abstract void onFinish();

}
