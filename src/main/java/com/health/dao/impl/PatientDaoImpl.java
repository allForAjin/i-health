package com.health.dao.impl;

import com.health.dao.PatientDao;
import com.health.entity.Patient;
import com.health.utils.SqlUtil;

import java.util.List;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName PatientDaoImpl.java
 * @Description TODO
 * @createTime 2021-11-15 13:26:50
 */
public class PatientDaoImpl implements PatientDao {
    @Override
    public Patient queryPatientByUsername(String username) {
        String sql="select id,username,password,phone from patient where username=?";
        List<Object> objectList= SqlUtil.executeQuery(sql,username);
        if (objectList.size()==0){
            return null;
        }
        Object[] result=(Object[]) objectList.get(0);
        return new Patient((int)result[0],(String) result[1],(String) result[2],(String) result[3]);
    }

    @Override
    public int insertPatient(Patient patient) {
        String sql="insert into patient (username,password,phone) values (?,?,?)";
        return SqlUtil.executeUpdate(sql,patient.getUsername(),patient.getPassword(),patient.getPhone());
    }
}
