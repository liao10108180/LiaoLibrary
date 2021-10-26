package com.liao.library.http.subscriber;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.subscribers.ResourceSubscriber;

/**
 * @ProjectName: LiaoLibraryDemo
 * @Package: com.liao.library.http.subscriber
 * @ClassName: AbstractSubscriber
 * @Description: description class
 * @Author: CHN_Liao
 * @CreateDate: 2021/10/25 17:47
 */
public abstract class AbstractHttpSubscriber<T> extends ResourceSubscriber<T> {

    protected abstract void onSuccess(@NonNull T t);

    protected abstract void onFail(String msg);

    protected abstract void onFinish();
}
