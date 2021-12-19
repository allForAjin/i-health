package com.health.entity;

import java.util.List;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName MyPage.java
 * @Description TODO
 * @createTime 2021-12-17 18:21:23
 */
public class MyPage<T> {
    /**
     * 当前页码
     */
    private Integer pageNo;

    /**
     * 总页码
     */
    private Integer totalPage;

    /**
     * 总记录数
     */
    private Integer recordTotalCount;

    /**
     * 当前页显示数量
     */
    private Integer pageSize;


    /**
     * 当前页数据
     */
    private List<T> items;

    public MyPage() {
    }

    public MyPage(Integer pageNo, Integer totalPage, Integer recordTotalCount, Integer pageSize, List<T> items) {
        this.pageNo = pageNo;
        this.totalPage = totalPage;
        this.recordTotalCount = recordTotalCount;
        this.pageSize = pageSize;
        this.items = items;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getRecordTotalCount() {
        return recordTotalCount;
    }

    public void setRecordTotalCount(Integer recordTotalCount) {
        this.recordTotalCount = recordTotalCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "MyPage{" +
                "pageNo=" + pageNo +
                ", totalPage=" + totalPage +
                ", recordTotalCount=" + recordTotalCount +
                ", pageSize=" + pageSize +
                ", items=" + items +
                '}';
    }
}
