package com.liao.librarydemo.bean;

/**
 * @ProjectName: LiaoLibraryDemo
 * @Package: com.liao.librarydemo.bean
 * @ClassName: BaseResp
 * @Description: 基础bean
 * @Author: CHN_Liao
 * @CreateDate: 2021/11/1 16:32
 */
public class BaseResp<T> {
    private T data;
    private int errorCode;
    private String errorMsg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
