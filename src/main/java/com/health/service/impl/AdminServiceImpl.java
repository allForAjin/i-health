package com.health.service.impl;

import com.health.dao.AdminDao;
import com.health.dao.NormalDao;
import com.health.dao.impl.AdminDaoImpl;
import com.health.dao.impl.DepartmentDaoImpl;
import com.health.dao.impl.NormalDaoImpl;
import com.health.entity.*;
import com.health.service.AdminService;
import com.health.utils.PageHelper;
import com.health.utils.WebUtil;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.List;

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
    public int getRecordCount() {
        return adminDao.getRecordCount();
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
            patient.setSex("???");
        } else if ("female".equals(patient.getSex())) {
            patient.setSex("???");
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
            patient.setSex("???");
        }
        if ("female".equals(patient.getSex())) {
            patient.setSex("???");
        }
        int num = adminDao.insertPatient(patient);
        if (num < 1) {
            LOGGER.error("addPatient:num=" + num);

        }
        return num;
    }

    @Override
    public boolean userIsExisted(String phone) {
        int num = adminDao.queryUserCountByPhone(phone);
        if (num >= 1) {
            LOGGER.warn("??????????????????");
            return true;
        }
        return false;
    }

    @Override
    public int addNormalRegistEveryday() {
        int departmentCount = new DepartmentDaoImpl().getDepartmentCount();
        String addDate = WebUtil.getDateAfter(new Date(), 7);
        String week = WebUtil.getWeekOfDate(addDate);
        int normalCount = adminDao.queryNormalCountByDate(addDate);
        if (normalCount > 0) {
            System.out.println("??????" + addDate + "????????????????????????");
            return -1;
        }
        int num = 0;
        for (int i = 1; i <= departmentCount; i++) {
            int totalMorning = (int) ((Math.random() * 50) + 50);
            int totalAfternoon = (int) ((Math.random() * 50) + 50);
            if ("??????".equals(week)) {
                System.out.println("?????????");
                totalMorning=-1;
                totalAfternoon=-1;
            }
            Normal normal = new Normal(null, i, totalMorning, totalMorning, addDate, "??????");
            Normal normal1 = new Normal(null, i, totalAfternoon, totalAfternoon, addDate, "??????");
            num += normalDao.addNormalInfo(normal);
            if ("??????".equals(week)){
                normal1.setTotal(-1);
                normal1.setRemain(-1);
            }
            num += normalDao.addNormalInfo(normal1);
        }
        return num;
    }

    @Override
    public PageHelper<NormalRegistInfo> getNormalInfo(String hospitalName, String level, String date, int begin, int limit) {
        int count = adminDao.getNormalInfoCount(hospitalName, level, date);
        List<NormalRegistInfo> data = adminDao.getNormalInfo(hospitalName, level, date, begin, limit);
        return new PageHelper<>(data, count);
    }

    @Override
    public int deleteNormalInfoByDate() {
        return adminDao.deleteNormalInfoByDate();
    }

    @Override
    public int updateAdminInfo(Integer id, Admin admin) {
        return adminDao.updateAdminInfo(id,admin);
    }

    @Override
    public int getPatientCount() {
        return adminDao.getUserCount();
    }
}
