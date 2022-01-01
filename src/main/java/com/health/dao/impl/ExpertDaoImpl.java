package com.health.dao.impl;

import com.health.dao.ExpertDao;
import com.health.entity.Expert;
import com.health.entity.ExpertRegistInfo;
import com.health.entity.ExpertRegistRecord;
import com.health.utils.SqlUtil;
import com.health.utils.WebUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName ExpertDaoImpl.java
 * @Description TODO
 * @createTime 2021-12-23 21:09:33
 */
public class ExpertDaoImpl implements ExpertDao {
    @Override
    public List<Expert> getExpertInfoByDepartment(String departmentId) {
        String sql = "select id ,dPhone,dName,dSex,dBirth,dDepartment,dCareer,dDescription,regist_count,img_path from doctors where dDepartment=?";
        List<Object> objectList = SqlUtil.executeQuery(sql, departmentId);
        List<Expert> experts = new ArrayList<>();
        for (Object object : objectList) {
            Object[] result = (Object[]) object;
            String date = WebUtil.dateToStrong((Date) result[4], WebUtil.DATE);
            experts.add(new Expert((Integer) result[0], (String) result[1], (String) result[2],
                    (String) result[3], date, (Integer) result[5], (String) result[6],
                    (String) result[7], (Integer) result[8], (String) result[9]));
        }
        return experts;
    }

    @Override
    public List<ExpertRegistInfo> queryExpertRegistInfo(String doctorId, String registDate, String time) {
        String sql = "select id,doctor_id,department_name,doctor,remain,regist_date,time,cost from expert_regist_info where 1=1";
        sql += createExpertInfoSql(doctorId, registDate, time);
        List<Object> objectList = SqlUtil.executeQuery(sql);
        List<ExpertRegistInfo> expertRegistInfoList = new ArrayList<>();
        for (Object object : objectList) {
            Object[] result = (Object[]) object;
            String date = WebUtil.dateToStrong((Date) result[5], WebUtil.DATE);
            expertRegistInfoList.add(new ExpertRegistInfo((Integer) result[0], (Integer) result[1], (String) result[2],
                    (String) result[3], (Integer) result[4], date,
                    (String) result[6], (BigDecimal) result[7]));
        }
        return expertRegistInfoList;
    }

    @Override
    public int expertRegistRecordIsExisted(String patientId, String expertId) {
        String sql = "select count(*) from expert_regist_record where pid=? and expert_id=?";
        return SqlUtil.executeQueryCount(sql, patientId, expertId);
    }

    @Override
    public int insertExpertRegistRecord(ExpertRegistRecord record) {
        String sql = "insert into expert_regist_record (order_id,pid,expert_id,operate_time,cost,register_date,time,department,hospital,pay_status,expert_name) values(?,?,?,?,?,?,?,?,?,?,?)";
        return SqlUtil.executeUpdate(sql, record.getOrderId(),
                record.getPatientId(), record.getExpertId(), record.getOperateTime(),
                record.getCost(), record.getRegistDate(), record.getTime(), record.getDepartment(),
                record.getHospital(), record.getPayStatus(), record.getExpertName());
    }

    @Override
    public int decreaseExpertRegistRemain(String expertId) {
        String sql = "update expert set remain=remain-1 where id=?";
        return SqlUtil.executeUpdate(sql, expertId);
    }

    @Override
    public int getExpertRecordCount(String phone) {
        String sql = "select count(*) from expert_record_info where pPhone=?";
        return SqlUtil.executeQueryCount(sql, phone);
    }

    @Override
    public List<ExpertRegistRecord> getExpertRecord(String phone, int begin, int limit) {
        String sql = "select id,pPhone,order_id,pName,pSex,age,cost,register_date,time,department,hospital,pay_status,operate_time,pid,expert_id,expert_name from expert_record_info where pPhone=? limit ?,?";
        List<Object> objectList = SqlUtil.executeQuery(sql, phone, begin, limit);
        List<ExpertRegistRecord> list = new ArrayList<>();
        for (Object object : objectList) {
            Object[] result = (Object[]) object;
            String operateTime = WebUtil.dateToStrong((Date) result[12], WebUtil.DATETIME);
            String date = WebUtil.dateToStrong((Date) result[7], WebUtil.DATE);
            int age = WebUtil.parseLongToInt((Long) result[5], 0);
            list.add(new ExpertRegistRecord((Integer) result[0], (String) result[1], (String) result[2],
                    (String) result[3], (String) result[4], age, (BigDecimal) result[6],
                    date, (String) result[8], (String) result[9], (String) result[10],
                    (Integer) result[11], operateTime, (Integer) result[13], (Integer) result[14],
                    (String) result[15]));
        }
        return list;
    }

    @Override
    public int getExpertCountByOrderId(String orderId) {
        String sql = "select count(*) from expert_regist_record where order_id=?";
        return SqlUtil.executeQueryCount(sql, orderId);
    }

    @Override
    public int updatePayStatus(String orderId, Integer payStatus) {
        String sql="update expert_regist_record set pay_status=? where order_id=?";
        return SqlUtil.executeUpdate(sql,payStatus,orderId);
    }

    private String createExpertInfoSql(String doctorId, String registDate, String time) {
        String partSql = "";
        if (doctorId != null && doctorId != "") {
            partSql += " and doctor_id='" + doctorId + "'";
        }
        if (registDate != null && registDate != "") {
            partSql += " and regist_date='" + registDate + "'";
        }
        if (time != null && time != "") {
            partSql += " and time='" + time + "'";
        }
        return partSql;
    }
}
