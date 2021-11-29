package com.health.dao.impl;

import com.health.dao.AdminDao;
import com.health.entity.OperateRecord;
import com.health.utils.SqlUtil;
import com.health.utils.WebUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName AdminDaoImpl.java
 * @Description TODO
 * @createTime 2021-11-20 16:56:33
 */
public class AdminDaoImpl implements AdminDao {
    @Override
    public List<OperateRecord> getPage(String username, String operate, String type, int begin, int limit) {
        String sql = "select id,username,ip,time,operate,type from record_view where 1=1";
        sql += createSql(username, operate, type);
        sql += " order by id";
        sql += " limit ?,?";
        List<OperateRecord> recordList = new ArrayList<>();
        List<Object> objectList = SqlUtil.executeQuery(sql, begin, limit);
        for (Object object : objectList) {
            Object[] record = (Object[]) object;
            recordList.add(new OperateRecord((Integer) record[0], (String) record[1], (String) record[2],
                    WebUtil.dateToStrong((Date) record[3],WebUtil.DATETIME), (String) record[4]));
        }
        return recordList;
    }

    @Override
    public int getRecordCount(String username, String operate, String type) {
        String sql = "select count(*) from record_view where 1=1";
        sql += createSql(username, operate, type);
        return SqlUtil.executeQueryCount(sql);
    }

    @Override
    public int updateNormalRegistDate(String date) {
        String sql="update normal set regist_date=?";
        return SqlUtil.executeUpdate(sql,date);
    }

    private String createSql(String username, String operate, String type) {
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
}
