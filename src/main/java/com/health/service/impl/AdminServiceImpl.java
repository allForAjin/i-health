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
        int num = adminDao.queryUserCountByPhone(phone);
        if (num >= 1) {
            LOGGER.warn("用户已存在！");
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
            System.out.println("已有" + addDate + "数据，无需添加！");
            return -1;
        }
        int num = 0;
        for (int i = 1; i <= departmentCount; i++) {
            int totalMorning = (int) ((Math.random() * 50) + 50);
            int totalAfternoon = (int) ((Math.random() * 50) + 50);
            if ("周日".equals(week)) {
                System.out.println("周日！");
                totalMorning=-1;
                totalAfternoon=-1;
            }
            Normal normal = new Normal(null, i, totalMorning, totalMorning, addDate, "上午");
            Normal normal1 = new Normal(null, i, totalAfternoon, totalAfternoon, addDate, "下午");
            num += normalDao.addNormalInfo(normal);
            if ("周六".equals(week)){
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
}
