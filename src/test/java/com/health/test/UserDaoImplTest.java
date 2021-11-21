package com.health.test;

import com.health.dao.UserDao;
import com.health.dao.impl.UserDaoImpl;
import com.health.entity.User;
import org.junit.jupiter.api.Test;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName UserDaoImplTest.java
 * @Description TODO
 * @createTime 2021-11-18 16:53:33
 */
class UserDaoImplTest {
    private UserDao userDao=new UserDaoImpl();

    @Test
    void insertUser() {
        Map<String,Object> inputParam=new HashMap<>();
        inputParam.put("username","doctor1");
        inputParam.put("password","12345678");
        inputParam.put("phone","19152366987");
        inputParam.put("type", User.DOCTOR);

        Map<String,Integer> outputParam=new HashMap<>();
        outputParam.put("count", Types.INTEGER);
        System.out.println(userDao.insertUser(inputParam,outputParam));
    }
}