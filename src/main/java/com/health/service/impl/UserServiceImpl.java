package com.health.service.impl;

import com.health.dao.UserDao;
import com.health.dao.impl.UserDaoImpl;
import com.health.entity.OperateRecord;
import com.health.entity.User;
import com.health.service.UserService;
import com.health.utils.SqlUtil;
import com.health.utils.WebUtil;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.Watchable;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName UserServiceImpl.java
 * @Description TODO
 * @createTime 2021-11-15 20:35:34
 */
public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoImpl();

    @Override
    public boolean userLogin(String username, String password) {
        User user = userDao.queryUserByUsername(username);
        return username.equals(user.getUsername()) && password.equals(user.getPassword());
    }

    @Override
    public boolean userIsExisted(String username) {
        User user = userDao.queryUserByUsername(username);
        return user != null;
    }

    @Override
    public User getUserByUsername(String username) {
        return userDao.queryUserByUsername(username);
    }

    @Override
    public String getPathByUserType(String type) {
        switch (type) {
            case User.PATIENT:
                return "/page/patient/patient_site.jsp";
            case User.DOCTOR:
                return "/page/doctor/doctor_site.jsp";
            case User.ADMIN:
                return "/page/admin/admin_site.jsp";
            default:
                return "/page/user/login.jsp";
        }
    }

    @Override
    public boolean register(Map<String, String[]> userMap) {
        Map<String, Object> inputMap = new HashMap<>();
        for (String key : userMap.keySet()) {
            String[] value = userMap.get(key);
            inputMap.put(key, value[0]);
        }
        inputMap.remove("action");
        Map<String, Integer> outPutParam = new HashMap<>();
        outPutParam.put("count", Types.INTEGER);
        int insertNum = userDao.insertUser(inputMap, outPutParam);
        return insertNum != -1 && insertNum != 0;
    }

    @Override
    public boolean addUserOperation(OperateRecord record) {
        int insertNum = userDao.insertUserOperate(record);
        return insertNum != -1 && insertNum != 0;
    }


}
