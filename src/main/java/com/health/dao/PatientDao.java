package com.health.dao;

import com.health.entity.Patient;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName PatientService.java
 * @Description TODO
 * @createTime 2021-11-15 13:21:07
 */
public interface PatientDao {
    /**
     * 通过用户名获取患者
     * @author lmk
     * @Date 2021/11/15 13:24
     * @param username 患者用户名
     * @return com.health.entity.Patient 患者
     */
    public Patient queryPatientByUsername(String username);

    /**
     * 新增（插入）患者
     * @author lmk
     * @Date 2021/11/15 13:25
     * @param patient 待插入的患者
     * @return int 插入的条数，-1表示失败
     */
    public int insertPatient(Patient patient);


}
