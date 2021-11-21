package com.health.test;

import com.health.dao.PatientDao;
import com.health.dao.UserDao;
import com.health.dao.impl.PatientDaoImpl;
import com.health.dao.impl.UserDaoImpl;
import org.junit.jupiter.api.Test;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName PatientDaoTest.java
 * @Description TODO
 * @createTime 2021-11-15 13:35:07
 */
class PatientDaoTest {
    private UserDao userDao=new UserDaoImpl();

    @Test
    void queryPatientByUsername() {
        System.out.println(userDao.queryUserByUsername("admin"));
    }

    @Test
    void insertPatient() {
    }
}