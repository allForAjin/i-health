package com.health.service.impl;

import com.health.dao.HospitalDao;
import com.health.dao.NormalDao;
import com.health.dao.PatientDao;
import com.health.dao.impl.HospitalDaoImpl;
import com.health.dao.impl.NormalDaoImpl;
import com.health.dao.impl.PatientDaoImpl;
import com.health.entity.*;
import com.health.service.PatientService;
import com.health.utils.PageHelper;
import com.health.utils.WebUtil;

import java.math.BigDecimal;
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

    @Override
    public List<Hospital> getAllHospitalName() {
        return hospitalDao.getAllHospitalName();
    }

    @Override
    public List<Hospital> getAllHospitalInfo() {
        return hospitalDao.getAllHospitalInfo();
    }

    @Override
    public PageHelper<NormalRegistInfo> getNormalRegistInfoPage(String hospitalId, String time, String date, int begin, int limit) {
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

        int count = normalDao.getTotalCount(hospitalId, time, date);
        List<NormalRegistInfo> list = normalDao.getNormalRegistInfo(hospitalId, time, date, begin, limit);
        return new PageHelper<NormalRegistInfo>(list, count);
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
    public int deleteNormalRegistRecord(Integer patientId, Integer normalId) {
        return normalDao.deleteNormalRegistRecord(patientId, normalId);
    }
}
