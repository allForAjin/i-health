package com.health.service;

import com.health.entity.*;
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
     *
     * @return List<Hospital> 返回null则表示无医院或获取失败
     * @author lmk
     * @Date 2021/11/24 14:56
     */
    public List<Hospital> getAllHospitalName();

    /**
     * 获取所有医院信息
     *
     * @return java.util.List<com.health.entity.Hospital> 返回null则表示无医院或获取失败
     * @author lmk
     * @Date 2021/12/3 14:41
     */
    public List<Hospital> getAllHospitalInfo();

    /**
     * 医院信息分页
     *
     * @param pageNo    当前页起始索引
     * @param pageSize 当前页码
     * @return com.health.entity.MyPage null表示为失败，反之成功
     * @author lmk
     * @Date 2021/12/17 18:28
     */
    public MyPage queryHospitalForPage(int pageNo, int pageSize);

    /**
     * 通过医院id获取科室名
     *
     * @param hospitalId 医院id
     * @return java.util.List<com.health.entity.Department> 获取的科室
     * @author lmk
     * @Date 2021/12/4 15:06
     */
    public List<Department> getDepartmentByHospital(String hospitalId);

    /**
     * 功能描述
     *
     * @param hospitalId   医院编号
     * @param departmentId 科室id
     * @param date         当日日期
     * @param time         时间段
     * @return com.health.utils.PageHelper<com.health.entity.NormalRegistInfo>
     * @author lmk
     * @Date 2021/11/25 19:13
     */
    public List<NormalRegistInfo> getNormalRegistInfo(String hospitalId, String departmentId, String time, String date);

    /**
     * 通过id获取医院信息
     *
     * @param hospitalId 医院id
     * @return com.health.entity.Hospital
     * @author lmk
     * @Date 2021/12/5 0:01
     */
    public Hospital getHospitalById(String hospitalId);

    /**
     * 通过用户名获取患者信息
     *
     * @param phone 患者账号（手机号）
     * @return com.health.entity.Patient null表示没有该用户
     * @author lmk
     * @Date 2021/11/28 10:15
     */
    public Patient getPatientByPhone(String phone);


    /**
     * 添加用户门诊挂号记录
     *
     * @param record 待添加的记录
     * @return int
     * @author lmk
     * @Date 2021/11/28 22:58
     */
    public int addNormalRegistRecord(NormalRegistRecord record);

    /**
     * 查询用户是否已挂过当前号
     *
     * @param patientId 患者序号
     * @param normalId  门诊序号
     * @return boolean
     * @author lmk
     * @Date 2021/11/29 12:57
     */
    public boolean normalRecordIsExisted(Integer patientId, Integer normalId);

    /**
     * 挂号时减少剩余号量
     *
     * @param id     序号
     * @param remain 待更新剩余号量
     * @return int
     * @author lmk
     * @Date 2021/11/29 12:45
     */
    public int decreaseNormalRemain(Integer id, Integer remain);

    /**
     * 获取挂号记录并分页
     *
     * @param phone 通过手机号获取
     * @param begin sql语句开始索引
     * @param limit 一页显示的行数
     * @return com.health.utils.PageHelper<com.health.entity.NormalRegistRecord>
     * @author lmk
     * @Date 2021/11/29 20:29
     */
    public PageHelper<NormalRegistRecord> getNormalRegistRecordByPhone(String phone, int begin, int limit);


    /**
     * 支付门诊挂号订单
     *
     * @param orderId 订单号
     * @param status  支付状态
     * @return int
     * @author lmk
     * @Date 2021/12/1 17:10
     */
    public int payForNormalRegist(String orderId, int status);

    /**
     * 删除患者挂号记录
     *
     * @param id 记录id
     * @return int
     * @author lmk
     * @Date 2021/12/2 22:55
     */
    public int deleteNormalRegistRecord(Integer id);

    /**
     * 取消用户挂号
     *
     * @param patientId 患者id
     * @param normalId  挂号id
     * @param id        记录id
     * @return int
     * @author lmk
     * @Date 2021/12/6 10:44
     */
    public int cancelNormalRegist(Integer patientId, Integer normalId, Integer id);
}
