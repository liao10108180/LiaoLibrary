package com.liao.library.http;

import io.reactivex.rxjava3.core.Flowable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @ProjectName: LiaoLibraryDemo
 * @Package: com.liao.library.http
 * @ClassName: http工具类
 * @Description:
 * @Author: CHN_Liao
 * @CreateDate: 2021/10/25 17:36
 */
public class HttpUtils {
    /**
     * json转RequestBody
     *
     * @param json
     * @return
     */
    public static RequestBody jsonToRequestBody(String json) {
        return jsonToRequestBody(json, "application/json; charset=utf-8");
    }

    /**
     * json转RequestBody
     *
     * @param json
     * @param mediaType
     * @return
     */
    public static RequestBody jsonToRequestBody(String json, String mediaType) {
        MediaType mt = MediaType.Companion.parse(mediaType);
        return RequestBody.Companion.create(json, mt);
    }
}
