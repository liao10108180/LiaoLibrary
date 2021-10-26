package com.liao.common;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * @ProjectName: LiaoLibraryDemo
 * @Package: com.liao.common
 * @ClassName: DisposableManager
 * @Description: 管理Disposable
 * @Author: CHN_Liao
 * @CreateDate: 2021/10/26 15:17
 */
public class BaseDisposableManager {
    protected CompositeDisposable mCompositeDisposable;

    /**
     * 结束所有订阅
     */
    public void unSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }

    /**
     * 移除disposable
     *
     * @param disposable
     */
    public void removeDisposable(Disposable disposable) {
        if (disposable == null) {
            return;
        }
        if (mCompositeDisposable != null) {
            mCompositeDisposable.remove(disposable);
        }
    }

    /**
     * 添加disposable
     *
     * @param disposable
     */
    public void addDisposable(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }
}
