package com.health.service.impl;

import com.health.dao.*;
import com.health.dao.impl.*;
import com.health.entity.*;
import com.health.entity.alipay.AlipayConfig;
import com.health.service.PatientService;
import com.health.utils.AliPayUtil;
import com.health.utils.PageHelper;
import com.health.utils.WebUtil;

import java.util.Date;
import java.util.List;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName PatientServiceImpl.java
 * @Description TODO
 * @createTime 2021-11-15 14:11:02
 */
public class PatientServiceImpl implements PatientService {
    private final PatientDao patientDao = new PatientDaoImpl();
    private final HospitalDao hospitalDao = new HospitalDaoImpl();
    private final NormalDao normalDao = new NormalDaoImpl();
    private final ExpertDao expertDao = new ExpertDaoImpl();
    private final DepartmentDao departmentDao = new DepartmentDaoImpl();

    @Override
    public List<Hospital> getAllHospitalName() {
        return hospitalDao.getAllHospitalName();
    }

    @Override
    public List<Hospital> getAllHospitalInfo() {
        return hospitalDao.getAllHospitalInfo();
    }

    @Override
    public MyPage queryHospitalForPage(int pageNo, int pageSize) {
        if (pageSize < 1) {
            pageSize = 1;
        }
        int recordTotalCount = hospitalDao.queryForHospitalCount();
        int totalPage = recordTotalCount / pageSize;

        if (recordTotalCount % pageSize != 0) {
            totalPage++;
        }

        if (pageNo > totalPage) {
            pageNo = totalPage;
        }

        int begin = (pageNo - 1) * pageSize;
        List<Hospital> items = hospitalDao.queryHospitalForPage(begin, pageSize);
        return new MyPage(pageNo, totalPage, recordTotalCount, pageSize, items);
    }

    @Override
    public List<Department> getDepartmentByHospital(String hospitalId) {
        return departmentDao.getDepartmentByHospital(hospitalId);
    }

    @Override
    public List<NormalRegistInfo> getNormalRegistInfo(String hospitalId, String departmentId, String time, String date) {
        if ("all".equals(hospitalId)) {
            hospitalId = null;
        }
        if ("all".equals(time)) {
            time = null;
        } else if ("morning".equals(time)) {
            time = "上午";
        } else if ("afternoon".equals(time)) {
            time = "下午";
        }

        if (date == null || "".equals(date)) {
            date = WebUtil.dateToStrong(new Date(), WebUtil.DATE);
        }
        if ("all".equals(date)) {
            date = "";
        }
        List<NormalRegistInfo> list = normalDao.getNormalRegistInfo(hospitalId, departmentId, time, date);
        for (NormalRegistInfo normalRegistInfo : list) {
            String dateStr = normalRegistInfo.getRegistDate();
            dateStr += WebUtil.getWeekOfDate(dateStr);
            normalRegistInfo.setRegistDate(dateStr);
        }
        return list;
    }

    @Override
    public Hospital getHospitalById(String hospitalId) {
        return hospitalDao.getHospitalById(WebUtil.parseInt(hospitalId, 0));
    }

    @Override
    public Patient getPatientByPhone(String phone) {
        return patientDao.getPatientByPhone(phone);
    }


    @Override
    public int addNormalRegistRecord(NormalRegistRecord record) {
        record.setOperateTime(WebUtil.dateToStrong(new Date(), WebUtil.DATETIME));
        record.setOrderId(AlipayConfig.createOrderId());
        return normalDao.addNormalRegistRecord(record);
    }

    @Override
    public boolean normalRecordIsExisted(Integer patientId, Integer normalId) {
        return normalDao.normalRecordIsExisted(patientId, normalId);
    }

    @Override
    public int decreaseNormalRemain(Integer id, Integer remain) {
        return normalDao.decreaseNormalRemain(id, remain);
    }

    @Override
    public PageHelper<NormalRegistRecord> getNormalRegistRecordByPhone(String phone, int begin, int limit) {
        int total = normalDao.getNormalRegistRecordCount(phone);
        List<NormalRegistRecord> recordList = normalDao.getNormalRegistRecordByPatient(phone, begin, limit);
        return new PageHelper<>(recordList, total);
    }

    @Override
    public int payForNormalRegist(String orderId, int status) {
        return normalDao.updatePayStatus(orderId, status);
    }

    @Override
    public int deleteNormalRegistRecord(Integer id) {
        return normalDao.deleteNormalRegistRecord(id);
    }

    @Override
    public int cancelNormalRegist(Integer patientId, Integer normalId, Integer id) {
        int deleteNum = this.deleteNormalRegistRecord(id);
        if (deleteNum >= 1) {
            return normalDao.addNormalRemain(normalId);
        }
        return 0;
    }

    @Override
    public List<Expert> getExpertInfoByDepartment(String departmentId) {
        return expertDao.getExpertInfoByDepartment(departmentId);
    }

    @Override
    public List<ExpertRegistInfo> getExpertRegistInfo(String doctorId, String registDate, String time) {
        if (registDate == null || registDate == "") {
            registDate = WebUtil.dateToStrong(new Date(), WebUtil.DATE);
        }
        if ("morning".equals(time)) {
            time = "上午";
        } else if ("afternoon".equals(time)) {
            time = "下午";
        }

        return expertDao.queryExpertRegistInfo(doctorId, registDate, time);
    }

    @Override
    public boolean expertRecordIsExisted(Integer patientId, Integer expertId) {
        int num = expertDao.expertRegistRecordIsExisted(String.valueOf(patientId), String.valueOf(expertId));
        if (num > 0) {
            return true;
        }
        return false;
    }

    @Override
    public int addExpertRegistInfo(ExpertRegistRecord record) {
        record.setOrderId(AlipayConfig.createOrderId());
        record.setOperateTime(WebUtil.dateToStrong(new Date(), WebUtil.DATETIME));
        return expertDao.insertExpertRegistRecord(record);
    }

    @Override
    public int decreaseExpertRegistRecord(Integer expertId) {
        return expertDao.decreaseExpertRegistRemain(String.valueOf(expertId));
    }
}
