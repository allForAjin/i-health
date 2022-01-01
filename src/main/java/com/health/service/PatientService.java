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
     * @param pageNo   当前页起始索引
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
     * @return int 删除的记录数量
     * @author lmk
     * @Date 2021/12/6 10:44
     */
    public int cancelNormalRegist(Integer patientId, Integer normalId, Integer id);

    /**
     * 通过选择的科室获取专家信息
     *
     * @param departmentId 科室id
     * @return java.util.List<com.health.entity.Expert> null表示没有或失败
     * @author lmk
     * @Date 2021/12/23 21:26
     */
    public List<Expert> getExpertInfoByDepartment(String departmentId);


    /**
     * 通过医生id，挂号日期，时间段查询专家门诊挂号信息
     *
     * @param doctorId   医生id
     * @param registDate 挂号日期
     * @param time       挂号时间段
     * @return java.util.List<com.health.entity.ExpertRegistInfo> null表示失败或没有该信息
     * @author lmk
     * @Date 2021/12/25 18:36
     */
    public List<ExpertRegistInfo> getExpertRegistInfo(String doctorId, String registDate, String time);

    /**
     * 判断专家门诊是否已挂号
     *
     * @param patientId 患者id
     * @param expertId  专家id
     * @return boolean true为存在，false为不存在
     * @author lmk
     * @Date 2021/12/27 17:34
     */
    public boolean expertRecordIsExisted(Integer patientId, Integer expertId);

    /**
     * 专家门诊挂号
     *
     * @param record 专家门诊挂号记录
     * @return int
     * @author lmk
     * @Date 2021/12/27 16:57
     */
    public int addExpertRegistInfo(ExpertRegistRecord record);

    /**
     * 挂号时减少门诊剩余号量
     *
     * @param expertId 专家门诊id
     * @return int
     * @author lmk
     * @Date 2021/12/27 17:44
     */
    public int decreaseExpertRegistRecord(Integer expertId);

    /**
     * 获取专家挂号记录
     *
     * @param phone 用户电话
     * @param begin sql语句起始索引
     * @param limit 每页条数
     * @return com.health.utils.PageHelper<com.health.entity.ExpertRegistRecord>
     * @author lmk
     * @Date 2021/12/28 20:54
     */
    public PageHelper<ExpertRegistRecord> getExpertRegistRecord(String phone, int begin, int limit);

    /**
     * 查询普通门诊订单是否存在
     *
     * @param orderId 订单号
     * @return boolean true为存在,false为不存在
     * @author lmk
     * @Date 2021/12/29 20:40
     */
    public boolean normalOrderIdIsExisted(String orderId);

    /**
     * 查询专家门诊订单是否存在
     *
     * @param orderId 订单号
     * @return boolean true为存在,false为不存在
     * @author lmk
     * @Date 2021/12/29 20:40
     */
    public boolean expertOrderIdIsExisted(String orderId);

    /**
     * 专家门诊付款
     *
     * @param orderId   订单id
     * @param payStatus 支付状态
     * @return int
     * @author lmk
     * @Date 2021/12/29 20:52
     */
    public int payForExpertRegist(String orderId, Integer payStatus);

    /**
     * 更新个人信息
     *
     * @param id      id
     * @param patient 信息
     * @return int
     * @author lmk
     * @Date 2021/12/30 18:15
     */
    public int updatePatientInfo(Integer id, Patient patient);
}
