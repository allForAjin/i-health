package com.health.dao.impl;

import com.health.dao.NormalDao;
import com.health.entity.Normal;
import com.health.entity.NormalRegistInfo;
import com.health.entity.NormalRegistRecord;
import com.health.utils.SqlUtil;
import com.health.utils.WebUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName NormalDaoImpl.java
 * @Description TODO
 * @createTime 2021-11-29 11:02:38
 */
public class NormalDaoImpl implements NormalDao {
    @Override
    public List<NormalRegistInfo> getNormalRegistInfo(String hospitalId, String departmentId, String time, String date) {
        String sql = "select id,department,remain,regist_date,time,cost from normal_regist_info where 1=1 and hospital_id=? and dep_id=?";
        sql += createSql(null, time, date);
        List<Object> objectList = SqlUtil.executeQuery(sql, hospitalId, departmentId);
        List<NormalRegistInfo> normalList = new ArrayList<>();
        for (Object objects : objectList) {
            Object[] object = (Object[]) objects;
            normalList.add(
                    new NormalRegistInfo((Integer) object[0], null, null, null,
                            null, (String) object[1], null, (Integer) object[2],
                            WebUtil.dateToStrong((Date) object[3], WebUtil.DATE), (String) object[4], (BigDecimal) object[5]));
        }
        return normalList;
    }

    @Override
    public int getTotalCount(String hospitalId, String time, String date) {
        String sql = "select count(*) from normal_regist_info where 1=1";
        sql += createSql(hospitalId, time, date);
        return SqlUtil.executeQueryCount(sql);
    }

    @Override
    public boolean normalRecordIsExisted(Integer patientId, Integer normalId, String date) {
        String sql = "select count(*) from normal_regist_record where pid=? and normal_id=? and register_date=?";
        int num = SqlUtil.executeQueryCount(sql, patientId, normalId, date);
        return num > 0;
    }


    @Override
    public int addNormalRegistRecord(NormalRegistRecord record) {
        String sql = "insert into normal_regist_record (order_id,pid,normal_id,operate_time,cost,register_date,time,department,hospital,pay_status) values(?,?,?,?,?,?,?,?,?,?)";
        return SqlUtil.executeUpdate(sql, record.getOrderId(), record.getPatientId(), record.getNormalId(),
                record.getOperateTime(), record.getCost(), record.getRegistDate(),
                record.getTime(), record.getDepartment(), record.getHospital(), record.getPayStatus());
    }

    @Override
    public int decreaseNormalRemain(Integer id, Integer remain) {
        String sql = "update normal set remain=? where id=?";
        return SqlUtil.executeUpdate(sql, remain, id);
    }

    @Override
    public List<NormalRegistRecord> getNormalRegistRecordByPatient(String phone, int begin, int limit) {
        String sql = "select pid,normal_id,cost,register_date,time,department,hospital,operate_time,pay_status,pName,pPhone,pSex,age,order_id,id from normal_record_info where pPhone=? limit ?,?";
        List<Object> objectList = SqlUtil.executeQuery(sql, phone, begin, limit);
        List<NormalRegistRecord> recordList = new ArrayList<>();
        for (Object objects : objectList) {
            Object[] result = (Object[]) objects;
            String registDate = WebUtil.dateToStrong((Date) result[3], WebUtil.DATE);
            String operateTime = WebUtil.dateToStrong((Date) result[7], WebUtil.DATETIME);
            int age = WebUtil.parseLongToInt((Long) result[12], 0);
            recordList.add(new NormalRegistRecord((Integer) result[0], (Integer) result[1], (BigDecimal) result[2],
                    registDate, (String) result[4], (String) result[5],
                    (String) result[6], operateTime, (Integer) result[8],
                    (String) result[9], (String) result[10], (String) result[11],
                    age, (String) result[13], (Integer) result[14]));
        }
        return recordList;
    }

    @Override
    public int getNormalRegistRecordCount(String phone) {
        String sql = "select count(*) from normal_record_info where pPhone=?";
        return SqlUtil.executeQueryCount(sql, phone);
    }

    @Override
    public int updatePayStatusEveryday(int payStatus) {
        String sql = "update `normal_regist_record` set pay_status=? where TO_DAYS(NOW())>TO_DAYS(register_date)";
        return SqlUtil.executeUpdate(sql, payStatus);
    }

    @Override
    public int updatePayStatus(String orderId, int payStatus) {
        String sql = "update `normal_regist_record` set pay_status=? where order_id=?";
        return SqlUtil.executeUpdate(sql, payStatus, orderId);
    }

    @Override
    public int deleteNormalRegistRecord(Integer id) {
        String sql = "delete from `normal_regist_record` where id=?";
        return SqlUtil.executeUpdate(sql, id);
    }

    @Override
    public int addNormalRemain(Integer normalId) {
        String sql = "update normal set remain=remain+1 where id=?";
        return SqlUtil.executeUpdate(sql, normalId);
    }

    @Override
    public int updateNormalRemainEveryday() {
        String sql = "update normal set remain=total";
        return SqlUtil.executeUpdate(sql);
    }

    @Override
    public int addNormalInfo(Normal normal) {
        String sql="insert into normal (dep_id,total,remain,regist_date,time) values (?,?,?,?,?)";
        return SqlUtil.executeUpdate(sql,normal.getDepartmentId(),normal.getTotal(),normal.getRemain(),normal.getRegistDate(),normal.getTime());
    }

    private String createSql(String hospitalId, String time, String date) {
        String partSql = "";
        if (hospitalId != "" && hospitalId != null) {
            partSql += " and hospital_id='" + hospitalId + "'";
        }
        if (date != "" && date != null) {
            partSql += " and regist_date='" + date + "'";
        }
        if (time != "" && time != null) {
            partSql += " and time='" + time + "'";
        }
        return partSql;
    }
}
