package com.health.dao;

import com.health.entity.OperateRecord;
import com.health.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName UserDao.java
 * @Description TODO
 * @createTime 2021-11-15 20:27:57
 */
public interface UserDao {
    /**
     * 通过用户名查询
     *
     * @param username 用户名
     * @return com.health.entity.User 若null表示不存在
     * @author lmk
     * @Date 2021/11/15 20:28
     */
    public User queryUserByUsername(String username);

    /**
     * 插入用户
     *
     * @param inputParam  输入参数，key为输入参数字段名，value为用户输入的值
     * @param outputParam 输出参数，key为输出参数字段名，value为输出参数数据类型
     * @return int 成功数量，-1表示失败
     * @author lmk
     * @Date 2021/11/15 20:30
     */
    public int insertUser(Map<String, Object> inputParam, Map<String, Integer> outputParam);

    /**
     * 插入用户访问记录
     *
     * @param record 访问记录
     * @return int 成功数量，-1表示失败
     * @author lmk
     * @Date 2021/11/20 11:17
     */
    public int insertUserOperate(OperateRecord record);


}
