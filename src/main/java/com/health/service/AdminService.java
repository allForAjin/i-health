package com.health.service;

import com.health.entity.Admin;
import com.health.entity.OperateRecord;
import com.health.entity.Patient;
import com.health.utils.PageHelper;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName AdminService.java
 * @Description TODO
 * @createTime 2021-11-20 16:57:52
 */
public interface AdminService {
    /**
     * 获取当前指定页的record数据
     *
     * @param username 用户名
     * @param operate  操作
     * @param type     用户类型
     * @param begin    sql语句起始索引
     * @param limit    每页显示数量
     * @return com.health.utils.PageHelper<com.health.entity.OperateRecord>
     * @author lmk
     * @Date 2021/11/20 17:02
     */
    public PageHelper<OperateRecord> getRecordPage(String username, String operate, String type, int begin, int limit);

    /**
     * 更新门诊日期
     *
     * @param date 当日日期
     * @return int
     * @author lmk
     * @Date 2021/11/26 16:59
     */
    public int updateNormalRegistDate(Date date);

    /**
     * 更新支付状态
     *
     * @param payStatus 要更新的数据状态
     * @return int 更新成功的数量
     * @author lmk
     * @Date 2021/11/30 11:21
     */
    public int updatePayStatusEveryday(int payStatus);

    /**
     * 每日更新门诊剩余号量
     *
     * @return int 更新条数
     * @author lmk
     * @Date 2021/12/6 11:00
     */
    public int updateNormalRemainEveryday();

    /**
     * 通过电话获取admin信息
     *
     * @param phone admin电话
     * @return com.health.entity.Admin
     * @author lmk
     * @Date 2021/12/8 21:50
     */
    public Admin getAdminInfoByPhone(String phone);


    /**
     * 获取患者信息
     *
     * @param name  患者姓名
     * @param phone 患者电话
     * @param begin sql语句起始索引
     * @param limit 每页显示数量
     * @return java.util.List<com.health.entity.Patient> 患者list，null表示出错或未找到
     * @author lmk
     * @Date 2021/12/8 22:46
     */
    public PageHelper<Patient> getPatientInfo(String name, String phone, int begin, int limit);

    /**
     * 更新患者信息
     *
     * @param patient 待更新的患者信息
     * @return int
     * @author lmk
     * @Date 2021/12/12 21:26
     */
    public int updatePatientInfo(Patient patient);

    /**
     * 通过id删除患者
     *
     * @param id 患者id
     * @return int 大于等于1为成功，反之失败
     * @author lmk
     * @Date 2021/12/12 22:52
     */
    public int deletePatientById(Integer id);

    /**
     * 添加患者
     *
     * @param patient 要添加的患者
     * @return boolean 若true表示成功，反之失败
     * @author lmk
     * @Date 2021/12/16 19:46
     */
    public int addPatient(Patient patient);

    /**
     * 查询用户名是否已存在
     * @author lmk
     * @Date 2021/12/16 20:22
     * @param phone 用户账号（手机号）
     * @return boolean
     */
    public boolean userIsExisted(String phone);

}
