package com.health.service;

import com.health.entity.Patient;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName PatientService.java
 * @Description TODO
 * @createTime 2021-11-15 13:49:22
 */
public interface PatientService {

    /**
     * 患者登录
     * @author lmk
     * @Date 2021/11/15 15:36
     * @param username 用户名
     * @param password 密码
     * @return boolean true表示成功，false表示失败
     */

    public boolean patientLogin(String username,String password);

    /**
     * 通过用户名判断患者是否存在
     * @author lmk
     * @Date 2021/11/15 13:52
     * @param username 患者用户名
     * @return boolean true表示存在，false表示是不存在
     */
    public boolean patientIsExisted(String username);

    /**
     * 通过用户名获取患者信息
     * @author lmk
     * @Date 2021/11/15 16:01
     * @param username 用户名
     * @return com.health.entity.Patient 信息
     */
    public Patient getPatientByUsername(String username);
}
