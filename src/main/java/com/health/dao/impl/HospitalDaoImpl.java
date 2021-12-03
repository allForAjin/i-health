package com.health.dao.impl;

import com.health.dao.HospitalDao;
import com.health.entity.Hospital;
import com.health.utils.SqlUtil;
import com.health.utils.WebUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName HospitalDaoImpl.java
 * @Description TODO
 * @createTime 2021-11-24 14:50:50
 */
public class HospitalDaoImpl implements HospitalDao {
    @Override
    public List<Hospital> getAllHospitalName() {
        String sql = "select hid,hname from hospital";
        List<Object> objectList = SqlUtil.executeQuery(sql);
        List<Hospital> result = new ArrayList<>();
        for (Object objects : objectList) {
            Object[] object = (Object[]) objects;
            result.add(new Hospital((Integer) object[0], (String) object[1], null, null));
        }
        return result;
    }

    @Override
    public List<Hospital> getAllHospitalInfo() {
        String sql = "select * from hospital";
        List<Object> objectList = SqlUtil.executeQuery(sql);
        List<Hospital> result = new ArrayList<>();
        for (Object objects : objectList) {
            Object[] object = (Object[]) objects;
            result.add(new Hospital((Integer) object[0], (String) object[1], (String) object[2], (String) object[3],(String) object[4]));
        }
        return result;
    }
}
