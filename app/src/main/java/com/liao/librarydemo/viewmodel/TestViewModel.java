package com.liao.librarydemo.viewmodel;

import com.liao.library.base.BaseViewModel;
import com.liao.library.http.subscriber.HttpSubscriber;
import com.liao.librarydemo.bean.BaseResp;
import com.liao.librarydemo.bean.PageResp;
import com.liao.librarydemo.bean.resp.ArticleResp;
import com.liao.librarydemo.http.HttpHelper;

import io.reactivex.rxjava3.annotations.NonNull;

/**
 * @ProjectName: LiaoLibraryDemo
 * @Package: com.liao.librarydemo.viewmodel
 * @ClassName: TestViewModel
 * @Description: description class
 * @Author: CHN_Liao
 * @CreateDate: 2021/11/1 15:23
 */
public class TestViewModel extends BaseViewModel {

    public void test(long pageNum){
        mDisposableHelper.addDisposable(HttpHelper.getApi().getArticleList(pageNum), new HttpSubscriber<BaseResp<PageResp<ArticleResp>>>() {
            @Override
            protected void onSuccess(@NonNull BaseResp<PageResp<ArticleResp>> pageRespBaseResp) {

            }

            @Override
            protected void onFail(String msg) {

            }

            @Override
            protected void onFinish() {

            }
        });
    }
}
