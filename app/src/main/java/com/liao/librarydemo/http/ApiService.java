package com.liao.librarydemo.http;

import com.liao.librarydemo.bean.BaseResp;
import com.liao.librarydemo.bean.PageResp;
import com.liao.librarydemo.bean.resp.ArticleResp;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @ProjectName: LiaoLibraryDemo
 * @Package: com.liao.librarydemo.http
 * @ClassName: ApiService
 * @Description: description class
 * @Author: CHN_Liao
 * @CreateDate: 2021/11/1 16:15
 */
public interface ApiService {

    @GET("article/list/{pageNum}/json")
    Flowable<BaseResp<PageResp<ArticleResp>>> getArticleList(@Path("pageNum") long pageNum);
}
