package com.health.service;

import com.health.entity.Hospital;
import com.health.entity.NormalRegistInfo;
import com.health.entity.NormalRegistRecord;
import com.health.entity.Patient;
import com.health.utils.PageHelper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName PatientService.java
 * @Description TODO
 * @createTime 2021-11-15 13:49:22
 */
public interface PatientService {

    /**
     * 获取所有医院名称
     * @author lmk
     * @Date 2021/11/24 14:56
     * @return List<Hospital> 返回null则表示无医院或获取失败
     */
    public List<Hospital> getAllHospitalName();

    /**
     * 功能描述
     * @author lmk
     * @Date 2021/11/25 19:13
     * @param hospitalId 医院编号
     * @param date 当日日期
     * @param begin sql开始索引
     * @param limit 每页数据量
     * @return com.health.utils.PageHelper<com.health.entity.NormalRegistInfo>
     */
    public PageHelper<NormalRegistInfo> getNormalRegistInfoPage(String hospitalId,String date, int begin, int limit);

    /**
     * 通过用户名获取患者信息
     * @author lmk
     * @Date 2021/11/28 10:15
     * @param phone 患者账号（手机号）
     * @return com.health.entity.Patient null表示没有该用户
     */
    public Patient getPatientByPhone(String phone);


    /**
     * 添加用户门诊挂号记录
     * @author lmk
     * @Date 2021/11/28 22:58
     * @param record 待添加的记录
     * @return int
     */
    public int addNormalRegistRecord(NormalRegistRecord record);

    /**
     * 查询用户是否已挂过当前号
     * @author lmk
     * @Date 2021/11/29 12:57
     * @param patientId 患者序号
     * @param normalId 门诊序号
     * @return boolean
     */
    public boolean normalRecordIsExisted(Integer patientId,Integer normalId);

    /**
     * 挂号时减少剩余号量
     * @author lmk
     * @Date 2021/11/29 12:45
     * @param id 序号
     * @param remain 待更新剩余号量
     * @return int
     */
    public int decreaseNormalRemain(Integer id,Integer remain);

}
