package com.balidao.transreport.dao.pager;

/**
 * Created by double on 16-11-29.
 */

import java.util.List;

/**
 * 分页对象
 * 
 * @author Administrator
 *
 * @param <T>
 */
public class Pager<T> {
    /**
     * 分页的大小
     */
    private int pageSize;
    /**
     * 分页的起始页
     */
    private int startPage;
    /**
     * 总记录数
     */
    private long totalSize;
    /**
     * 分页的数据
     */
    private List<T> datas;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }
}
