package com.health.test;

import com.health.dao.AdminDao;
import com.health.dao.impl.AdminDaoImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName AdminDaoTest.java
 * @Description TODO
 * @createTime 2021-12-12 23:01:40
 */
class AdminDaoTest {
    private AdminDao adminDao=new AdminDaoImpl();

    @Test
    void getPage() {
    }

    @Test
    void getRecordCount() {
    }

    @Test
    void updateNormalRegistDate() {
    }

    @Test
    void getAdminInfoByPhone() {
    }

    @Test
    void getPatientCount() {
    }

    @Test
    void getPatientInfo() {
    }

    @Test
    void updatePatientInfo() {
    }

    @Test
    void deletePatientById() {
        System.out.println(adminDao.deletePatientById(null));
    }

    @Test
    void getNormalInfo(){
        System.out.println(adminDao.getNormalInfo(null,null,null,0,20));
    }
}