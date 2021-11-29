package com.health.dao;

import com.health.entity.NormalRegistInfo;
import com.health.entity.NormalRegistRecord;
import com.health.entity.Patient;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName PatientService.java
 * @Description TODO
 * @createTime 2021-11-15 13:21:07
 */
public interface PatientDao {


    /**
     * 通过用户名获取患者信息
     * @author lmk
     * @Date 2021/11/28 10:15
     * @param phone 患者账号（手机号）
     * @return com.health.entity.Patient null表示没有该用户
     */
    public Patient getPatientByPhone(String phone);



}
