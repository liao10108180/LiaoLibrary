package com.liao.librarydemo.entity;

import com.chad.library.adapter.base.entity.JSectionEntity;

/**
 * @ProjectName: LiaoLibraryDemo
 * @Package: com.liao.librarydemo.entity
 * @ClassName: MainEntity
 * @Description: description class
 * @Author: CHN_Liao
 * @CreateDate: 2021/10/27 15:02
 */
public class MainEntity extends JSectionEntity {

    private boolean isHeader;

    private String content;


    private Class<?> clazz;

    public MainEntity(boolean isHeader, String content, Class<?> clazz) {
        this.isHeader = isHeader;
        this.content = content;
        this.clazz = clazz;
    }

    public MainEntity(String content, Class<?> clazz) {
        this.isHeader = false;
        this.content = content;
        this.clazz = clazz;
    }


    public void setHeader(boolean header) {
        isHeader = header;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public boolean isHeader() {
        return isHeader;
    }
}
