package com.health.dao.impl;

import com.health.dao.NormalDao;
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
    public List<NormalRegistInfo> getNormalRegistInfo(String hospitalId, String date, int begin, int limit) {
        String sql = "select id,hospital,level,department,regist_date,time,cost,remain from normal_regist_info where 1=1";
        sql += createSql(hospitalId, date);
        sql += " order by hospital";
        sql += " limit ?,?";
        List<Object> objectList = SqlUtil.executeQuery(sql, begin, limit);
        List<NormalRegistInfo> normalList = new ArrayList<>();
        for (Object objects : objectList) {
            Object[] object = (Object[]) objects;
            normalList.add(
                    new NormalRegistInfo((Integer) object[0], null, (String) object[1], (String) object[2],
                            null, (String) object[3], null, (Integer) object[7],
                            WebUtil.dateToStrong((Date) object[4], WebUtil.DATE), (String) object[5], (BigDecimal) object[6]));
        }
        return normalList;
    }

    @Override
    public int getTotalCount(String hospitalId, String date) {
        String sql = "select count(*) from normal_regist_info where 1=1";
        sql += createSql(hospitalId, date);
        return SqlUtil.executeQueryCount(sql);
    }

    @Override
    public boolean normalRecordIsExisted(Integer patientId, Integer normalId) {
        String sql = "select count(*) from normal_regist_record where pid=? and normal_id=?";
        int num = SqlUtil.executeQueryCount(sql, patientId, normalId);
        return num > 0;
    }



    @Override
    public int addNormalRegistRecord(NormalRegistRecord record) {
        String sql = "insert into normal_regist_record (pid,normal_id,operate_time,cost,register_date,time,department,hospital) values(?,?,?,?,?,?,?,?)";
        return SqlUtil.executeUpdate(sql, record.getPatientId(), record.getNormalId(),
                record.getOperateTime(), record.getCost(), record.getRegistDate(),
                record.getTime(), record.getDepartment(), record.getHospital());
    }

    @Override
    public int decreaseNormalRemain(Integer id, Integer remain) {
        String sql = "update normal set remain=? where id=?";
        return SqlUtil.executeUpdate(sql, remain, id);
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
