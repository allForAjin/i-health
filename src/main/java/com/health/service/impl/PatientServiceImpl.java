package com.health.service.impl;

import com.health.dao.HospitalDao;
import com.health.dao.NormalDao;
import com.health.dao.PatientDao;
import com.health.dao.impl.HospitalDaoImpl;
import com.health.dao.impl.NormalDaoImpl;
import com.health.dao.impl.PatientDaoImpl;
import com.health.entity.Hospital;
import com.health.entity.NormalRegistInfo;
import com.health.entity.NormalRegistRecord;
import com.health.entity.Patient;
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
    private final NormalDao normalDao=new NormalDaoImpl();

    @Override
    public List<Hospital> getAllHospitalName() {
        return hospitalDao.getAllHospitalName();
    }

    @Override
    public PageHelper<NormalRegistInfo> getNormalRegistInfoPage(String hospitalId, String date, int begin, int limit) {
        if ("all".equals(hospitalId)) {
            hospitalId = null;
        }
        int count = normalDao.getTotalCount(hospitalId, date);
        List<NormalRegistInfo> list = normalDao.getNormalRegistInfo(hospitalId, date, begin, limit);
        return new PageHelper<NormalRegistInfo>(list, count);
    }

    @Override
    public Patient getPatientByPhone(String phone) {
        return patientDao.getPatientByPhone(phone);
    }



    @Override
    public int addNormalRegistRecord(NormalRegistRecord record) {
        record.setOperateTime(WebUtil.dateToStrong(new Date(),WebUtil.DATETIME));
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
}
