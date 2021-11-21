package com.health.service;

import com.health.entity.OperateRecord;
import com.health.entity.Patient;
import com.health.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName UserService.java
 * @Description TODO
 * @createTime 2021-11-15 20:32:38
 */
public interface UserService {
    /**
     * 用户登录
     * @author lmk
     * @Date 2021/11/15 15:36
     * @param username 用户名
     * @param password 密码
     * @return boolean true表示成功，false表示失败
     */

    public boolean userLogin(String username, String password);

    /**
     * 通过用户名判断用户是否存在
     * @author lmk
     * @Date 2021/11/15 13:52
     * @param username 用户名
     * @return boolean true表示存在，false表示是不存在
     */
    public boolean userIsExisted(String username);

    /**
     * 通过用户名获取用户信息
     * @author lmk
     * @Date 2021/11/15 16:01
     * @param username 用户名
     * @return com.health.entity.User 信息
     */
    public User getUserByUsername(String username);

    /**
     * 通过用户类型返回相应的访问路径
     * @author lmk
     * @Date 2021/11/16 18:11
     * @param type 用户类型
     * @return java.lang.String
     */
    public String getPathByUserType(String type);

    /**
     * 用户注册
     * @author lmk
     * @Date 2021/11/18 17:13
     * @param userMap 用户信息map
     * @return boolean 若true表示成功，反之失败
     */
    public boolean register(Map<String,String[]> userMap);

    /**
     * 添加用户访问记录
     * @author lmk
     * @Date 2021/11/20 11:21
     * @param record 访问记录
     * @return boolean 若true表示成功，false表示失败
     */
    public boolean addUserOperation(OperateRecord record);



}
