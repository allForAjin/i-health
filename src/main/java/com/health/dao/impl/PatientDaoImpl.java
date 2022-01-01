package com.health.dao.impl;

import com.health.dao.PatientDao;
import com.health.entity.NormalRegistInfo;
import com.health.entity.NormalRegistRecord;
import com.health.entity.Patient;
import com.health.utils.SqlUtil;
import com.health.utils.WebUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
    public Patient getPatientByPhone(String phone) {
        String sql = "select id,pPhone,pName,pSex,age,pBirth from patient_info where pPhone=?";
        List<Object> objectList = SqlUtil.executeQuery(sql, phone);
        Object[] result = (Object[]) objectList.get(0);
        return new Patient((Integer) result[0], (String) result[1], (String) result[2],
                (String) result[3], WebUtil.parseLongToInt((Long) result[4], 0),
                WebUtil.dateToStrong((Date) result[5], WebUtil.DATE));
    }

    @Override
    public int updatePatientInfo(Integer id, Patient patient) {
        String sql="update patients set pPhone=?,pName=?,pSex=?,pBirth=? where id=?";
        return SqlUtil.executeUpdate(sql,patient.getPhone(),patient.getName(),patient.getSex(),patient.getBirth(),id);
    }


    private String createSql(String hospitalId, String date) {
        String partSql = "";
        if (hospitalId != "" && hospitalId != null) {
            partSql += " and hospital_id='" + hospitalId + "'";
        }
        if (date != "" && date != null) {
            partSql += " and regist_date='" + date + "'";
        }

        return partSql;
    }


}
