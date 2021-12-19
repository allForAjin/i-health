package com.health.service.impl;

import com.health.dao.AdminDao;
import com.health.dao.NormalDao;
import com.health.dao.impl.AdminDaoImpl;
import com.health.dao.impl.NormalDaoImpl;
import com.health.entity.Admin;
import com.health.entity.OperateRecord;
import com.health.entity.Patient;
import com.health.entity.User;
import com.health.service.AdminService;
import com.health.utils.PageHelper;
import com.health.utils.WebUtil;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName AdminServiceImpl.java
 * @Description TODO
 * @createTime 2021-11-20 16:58:03
 */
public class AdminServiceImpl implements AdminService {
    private final AdminDao adminDao = new AdminDaoImpl();
    private final NormalDao normalDao = new NormalDaoImpl();
    private static final Logger LOGGER = Logger.getLogger(AdminService.class);


    @Override
    public PageHelper<OperateRecord> getRecordPage(String username, String operate, String type, int begin, int limit) {
        int total = adminDao.getRecordCount(username, operate, type);
        List<OperateRecord> recordList = adminDao.getPage(username, operate, type, begin, limit);
        PageHelper<OperateRecord> pageHelper = new PageHelper<>(recordList, total);
        return pageHelper;
    }

    @Override
    public int updateNormalRegistDate(Date date) {
        return adminDao.updateNormalRegistDate(WebUtil.dateToStrong(date, WebUtil.DATE));
    }

    @Override
    public int updatePayStatusEveryday(int payStatus) {
        return normalDao.updatePayStatusEveryday(payStatus);
    }

    @Override
    public int updateNormalRemainEveryday() {
        return normalDao.updateNormalRemainEveryday();
    }

    @Override
    public Admin getAdminInfoByPhone(String phone) {
        return adminDao.getAdminInfoByPhone(phone);
    }

    @Override
    public PageHelper<Patient> getPatientInfo(String name, String phone, int begin, int limit) {
        int total = adminDao.getPatientCount(name, phone);
        List<Patient> patientList = adminDao.getPatientInfo(name, phone, begin, limit);
        PageHelper<Patient> pageHelper = new PageHelper<>(patientList, total);
        return pageHelper;
    }

    @Override
    public int updatePatientInfo(Patient patient) {
        if ("male".equals(patient.getSex())) {
            patient.setSex("男");
        } else if ("female".equals(patient.getSex())) {
            patient.setSex("女");
        }
        return adminDao.updatePatientInfo(patient);
    }

    @Override
    public int deletePatientById(Integer id) {
        return adminDao.deletePatientById(id);
    }

    @Override
    public int addPatient(Patient patient) {
        if ("male".equals(patient.getSex())) {
            patient.setSex("男");
        }
        if ("female".equals(patient.getSex())) {
            patient.setSex("女");
        }
        int num = adminDao.insertPatient(patient);
        if (num < 1) {
            LOGGER.error("addPatient:num=" + num);

        }
        return num;
    }

    @Override
    public boolean userIsExisted(String phone) {
        int num=adminDao.queryUserCountByPhone(phone);
        if (num>=1){
            LOGGER.warn("用户已存在！");
            return true;
        }
        return false;
    }
}
