package com.health.test;

import com.health.entity.OperateRecord;
import com.health.entity.User;
import com.health.service.UserService;
import com.health.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName UserServiceImplTest.java
 * @Description TODO
 * @createTime 2021-11-18 17:19:22
 */
class UserServiceImplTest {
    private final UserService userService=new UserServiceImpl();

    @Test
    void register() {
        Map<String,Object> map=new HashMap<>();
        map.put("username","doctor1");
        map.put("password","12345678");
        map.put("phone","19152366987");
        map.put("type", User.DOCTOR);
        //System.out.println(userService.register(map));
    }

    @Test
    void addUserOperation(){
//        OperateRecord record=new OperateRecord(null,"doctor","192.168.0.1",new Date(),"login");
//        System.out.println(userService.addUserOperation(record));
    }
}