package com.health.service.impl;

import com.health.dao.PatientDao;
import com.health.dao.impl.PatientDaoImpl;
import com.health.entity.Patient;
import com.health.service.PatientService;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName PatientServiceImpl.java
 * @Description TODO
 * @createTime 2021-11-15 14:11:02
 */
public class PatientServiceImpl implements PatientService {
    private final PatientDao patientDao = new PatientDaoImpl();

    @Override
    public boolean patientLogin(String username, String password) {
        Patient patient = patientDao.queryPatientByUsername(username);
        return username.equals(patient.getUsername()) && password.equals(patient.getPassword());
    }

    @Override
    public boolean patientIsExisted(String username) {
        Patient patient = patientDao.queryPatientByUsername(username);
        if (patient == null) {
            return false;
        }
        return true;
    }

    @Override
    public Patient getPatientByUsername(String username) {
        return patientDao.queryPatientByUsername(username);
    }
}
