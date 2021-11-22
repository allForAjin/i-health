package com.health.dao;

import com.health.entity.OperateRecord;

import java.util.List;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName AdminDao.java
 * @Description TODO
 * @createTime 2021-11-20 16:56:11
 */
public interface AdminDao {
    /**
     * 获取当前页的记录
     *
     * @param username 用户名
     * @param operate  操作类型
     * @param type     用户类型
     * @param begin    sql语句起始索引
     * @param limit    每页显示的数量
     * @return java.util.List<com.health.entity.OperateRecord>
     * @author lmk
     * @Date 2021/11/20 16:47
     */
    public List<OperateRecord> getPage(String username, String operate, String type, int begin, int limit);

    /**
     * 获取访客记录表中记录数量
     *
     * @param username 用户名
     * @param operate  用户操作
     * @param type     用户类型
     * @return int 记录数
     * @author lmk
     * @Date 2021/11/21 0:27
     */
    public int getRecordCount(String username, String operate, String type);
}
