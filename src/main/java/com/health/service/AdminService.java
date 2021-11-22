package com.health.service;

import com.health.entity.OperateRecord;
import com.health.utils.PageHelper;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName AdminService.java
 * @Description TODO
 * @createTime 2021-11-20 16:57:52
 */
public interface AdminService {
    /**
     * 获取当前指定页的record数据
     * @author lmk
     * @Date 2021/11/20 17:02
     * @param username 用户名
     * @param operate 操作
     * @param type 用户类型
     * @param begin sql语句起始索引
     * @param limit 每页显示数量
     * @return com.health.utils.PageHelper<com.health.entity.OperateRecord>
     */
    public PageHelper<OperateRecord> getRecordPage(String username,String operate,String type,int begin,int limit);
}
