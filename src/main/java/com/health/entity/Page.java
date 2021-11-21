package com.health.entity;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName Page.java
 * @Description TODO
 * @createTime 2021-11-20 16:44:38
 */
public class Page {
    /**
     * 每页显示数量
     * @Date 2021/11/20 16:44
     */
    private int limit;
    /**
     * 页码
     * @Date 2021/11/20 16:45
     */
    private int page;
    /**
     * sql语句起始索引
     * @Date 2021/11/20 16:45

     */
    private int offset;

    public static final String LIMIT="limit";
    public static final String OFFSET="offset";


    public int getLimit() {
        return limit;
    }
    public void setLimit(int limit) {
        this.limit = limit;
    }
    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public int getOffset() {
        return offset;
    }
    public void setOffset(int offset) {
        this.offset = offset;
    }


}
