package com.liao.librarydemo.bean;

import java.util.List;

/**
 * @ProjectName: LiaoLibraryDemo
 * @Package: com.liao.librarydemo.bean
 * @ClassName: PageResp
 * @Description: 翻页bean
 * @Author: CHN_Liao
 * @CreateDate: 2021/11/1 16:33
 */
public class PageResp<T> {
    /**
     * 列表数据
     */
    private List<T> datas;
    private int offset;
    private boolean over;
    /**
     * 总页数
     */
    private long pageCount;
    /**
     * 每页条数
     */
    private int size;
    /**
     * 总条数
     */
    private long total;

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public long getPageCount() {
        return pageCount;
    }

    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
