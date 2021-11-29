package com.health.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.health.dao.HospitalDao;
import com.health.dao.impl.HospitalDaoImpl;
import com.health.entity.Hospital;
import com.health.utils.JsonUtil;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName HospitalDaoImplTest.java
 * @Description TODO
 * @createTime 2021-11-24 14:54:15
 */
class HospitalDaoImplTest {
    private final HospitalDao hospitalDao=new HospitalDaoImpl();

    @Test
    void getAllHospitalName() {
        List<Hospital> map= hospitalDao.getAllHospitalName();
        System.out.println(JsonUtil.toJson(map));
    }
}