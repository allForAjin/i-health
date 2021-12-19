package com.health.dao.impl;

import com.health.dao.AdminDao;
import com.health.entity.Admin;
import com.health.entity.OperateRecord;
import com.health.entity.Patient;
import com.health.entity.User;
import com.health.utils.SqlUtil;
import com.health.utils.WebUtil;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @author lmk
 * @version 1.0.0
 * @ClassName AdminDaoImpl.java
 * @Description TODO
 * @createTime 2021-11-20 16:56:33
 */
public class AdminDaoImpl implements AdminDao {
    private static final Logger logger = Logger.getLogger(AdminDao.class);

    @Override
    public List<OperateRecord> getPage(String username, String operate, String type, int begin, int limit) {
        String sql = "select id,username,ip,time,operate,type from record_view where 1=1";
        sql += createOperateSql(username, operate, type);
        sql += " order by id";
        sql += " limit ?,?";
        List<OperateRecord> recordList = new ArrayList<>();
        List<Object> objectList = SqlUtil.executeQuery(sql, begin, limit);
        for (Object object : objectList) {
            Object[] record = (Object[]) object;
            recordList.add(new OperateRecord((Integer) record[0], (String) record[1], (String) record[2],
                    WebUtil.dateToStrong((Date) record[3], WebUtil.DATETIME), (String) record[4]));
        }
        return recordList;
    }

    @Override
    public int getRecordCount(String username, String operate, String type) {
        String sql = "select count(*) from record_view where 1=1";
        sql += createOperateSql(username, operate, type);
        return SqlUtil.executeQueryCount(sql);
    }

    @Override
    public int updateNormalRegistDate(String date) {
        String sql = "update normal set regist_date=? where TO_DAYS(NOW())>TO_DAYS(register_date)";
        return SqlUtil.executeUpdate(sql, date);
    }

    @Override
    public Admin getAdminInfoByPhone(String phone) {
        String sql = "select id,username,name from admin where username=?";
        List<Object> objectList = SqlUtil.executeQuery(sql, phone);
        Object[] object = (Object[]) objectList.get(0);
        return new Admin((Integer) object[0], (String) object[1], (String) object[2]);
    }

    @Override
    public int getPatientCount(String name, String phone) {
        String sql = "select count(*) from patients where 1=1";
        sql += createPatientSql(name, phone);
        return SqlUtil.executeQueryCount(sql);
    }

    @Override
    public List<Patient> getPatientInfo(String name, String phone, int begin, int limit) {
        String sql = "select id,pPhone,pName,pSex,age,pBirth from patient_info where 1=1";
        sql += createPatientSql(name, phone);
        sql += " order by id";
        sql += " limit ?,?";
        List<Object> objectList = SqlUtil.executeQuery(sql, begin, limit);
        List<Patient> patientList = new ArrayList<>();
        for (Object objects : objectList) {
            Object[] object = (Object[]) objects;
            String sex = object[3] == null ? "-" : (String) object[3];
            Integer age = object[4] == null ? 0 : WebUtil.parseLongToInt((Long) object[4], 0);
            String birth = object[5] == null ? "-" : WebUtil.dateToStrong((Date) object[5], WebUtil.DATE);
            patientList.add(new Patient((Integer) object[0], (String) object[1], (String) object[2],
                    sex, age, birth));
        }
        return patientList;
    }

    @Override
    public int updatePatientInfo(Patient patient) {
        String sql = "update patients set pName=?,pSex=?,pBirth=?,pPhone=? where id=?";
        return SqlUtil.executeUpdate(sql, patient.getName(), patient.getSex(), patient.getBirth(), patient.getPhone(), patient.getId());
    }

    @Override
    public int deletePatientById(Integer id) {
        String sql = "delete from patients where id=?";
        return SqlUtil.executeUpdate(sql, id);
    }

    @Override
    public int insertPatient(Patient patient) {
        String sqlUser = "insert into user (phone,password,type) values (?,?,?)";
        int userNum = SqlUtil.executeUpdate(sqlUser, patient.getPhone(), patient.getPhone(), User.PATIENT);
        if (userNum < 1) {
            logger.error("insertPatient:userNum=" + userNum);
            return userNum;
        }
        String sqlPatient = "insert into patients (pPhone,pName,pSex,pBirth) values (?,?,?,?)";
        return SqlUtil.executeUpdate(sqlPatient, patient.getPhone(), patient.getName(), patient.getSex(), patient.getBirth());
    }

    @Override
    public int queryUserCountByPhone(String phone) {
        String sql="select count(*) from user where phone=?";
        return SqlUtil.executeQueryCount(sql,phone);
    }


    private String createOperateSql(String username, String operate, String type) {
        String partSql = "";
        if (operate.length() != 0 && operate != null) {
            partSql += " and operate='" + operate + "'";
        }
        if (type.length() != 0 && type != null) {
            partSql += " and type='" + type + "'";
        }
        if (username.length() != 0 && username != null) {
            String patten = "'^.*" + username + ".*$'";
            partSql += " and username REGEXP " + patten;
        }
        return partSql;
    }

    private String createPatientSql(String name, String phone) {
        String partSql = "";
        if (name.length() != 0 && name != null) {
            String patten = "'^.*" + name + ".*$'";
            partSql += " and pName REGEXP " + patten;
        }
        if (phone.length() != 0 && phone != null) {
            partSql = " and pPhone='" + phone + "'";
        }
        return partSql;
    }


}
