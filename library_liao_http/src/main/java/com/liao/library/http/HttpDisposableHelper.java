package com.liao.library.http;

import com.liao.common.BaseDisposableHelper;
import com.liao.common.scheduler.RxScheduler;
import com.liao.library.http.subscriber.HttpResponseSubscriber;
import com.liao.library.http.subscriber.HttpSubscriber;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.disposables.Disposable;
import retrofit2.Response;

/**
 * @ProjectName: LiaoLibraryDemo
 * @Package: com.liao.library.http
 * @ClassName: HttpDisposableManager
 * @Description: 管理Http Disposable
 * @Author: CHN_Liao
 * @CreateDate: 2021/10/26 15:33
 */
public class HttpDisposableHelper extends BaseDisposableHelper {

    public <T> void addDisposable(Flowable<T> flowable, HttpSubscriber<T> subscriber) {
        Disposable d = flowable.compose(RxScheduler.Flo_io_main())
                .subscribeWith(subscriber);
        addDisposable(d);
    }

    public <T> void addDisposable(Flowable<Response<T>> flowable, HttpResponseSubscriber<T> subscriber) {

        Disposable d = flowable.compose(RxScheduler.Flo_io_main())
                .subscribeWith(subscriber);
        addDisposable(d);
    }
}
