package com.health.dao.impl;

import com.health.dao.UserDao;
import com.health.entity.OperateRecord;
import com.health.entity.Patient;
import com.health.entity.User;
import com.health.utils.SqlUtil;
import com.health.utils.WebUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName UserDaoImpl.java
 * @Description TODO
 * @createTime 2021-11-15 20:31:20
 */
public class UserDaoImpl implements UserDao {
    @Override
    public User queryUserByUsername(String username) {
        String sql = "select id,phone,password,type from user where phone=?";
        List<Object> objectList = SqlUtil.executeQuery(sql, username);
        if (objectList.size() == 0) {
            return null;
        }
        Object[] result = (Object[]) objectList.get(0);
        return new User((int) result[0], (String) result[1], (String) result[2], (String) result[3]);
    }

    @Override
    public int insertUser(Map<String, Object> inputParam, Map<String, Integer> outputParam) {
        String sql = "{call add_user(?,?,?,?,?)}";
        Map<String, Object> resultMap = SqlUtil.executeProcedureWithInputAndOutput(sql, inputParam, outputParam);
        if (resultMap.get("count") == null) {
            return -1;
        }
        return WebUtil.parseInt(String.valueOf(resultMap.get("count")), -1);
    }

    @Override
    public int insertUserOperate(OperateRecord record) {
        String sql="insert into operate_record (username,ip,time,type) values (?,?,?,?)";
        return SqlUtil.executeUpdate(sql,record.getUsername(),record.getIp(),record.getTime(),record.getType());
    }


}
