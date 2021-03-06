package com.health.service;

import com.health.entity.Admin;
import com.health.entity.NormalRegistInfo;
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
     * 获取访客记录表中记录数量
     * @author lmk
     * @Date 2021/12/30 21:09
     * @return int
     */
    public int getRecordCount();

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
     *
     * @param phone 用户账号（手机号）
     * @return boolean
     * @author lmk
     * @Date 2021/12/16 20:22
     */
    public boolean userIsExisted(String phone);

    /**
     * 每日添加门诊号量
     *
     * @return int
     * @author lmk
     * @Date 2021/12/19 17:28
     */
    public int addNormalRegistEveryday();

    /**
     * 获取门诊号源信息
     *
     * @param hospitalName 医院名
     * @param level        医院等级
     * @param date         就诊日期
     * @param begin        sql语句起始索引
     * @param limit        就诊日期
     * @return com.health.utils.PageHelper<com.health.entity.NormalRegistInfo> 分页信息
     * @author lmk
     * @Date 2021/12/20 12:52
     */
    public PageHelper<NormalRegistInfo> getNormalInfo(String hospitalName, String level, String date, int begin, int limit);

    /**
     * 删除今日前的门诊记录
     *
     * @return int 删除的数量
     * @author lmk
     * @Date 2021/12/22 20:53
     */
    public int deleteNormalInfoByDate();

    /**
     * 更新管理员个人信息
     *
     * @param id    管理员id
     * @param admin 管理员信息
     * @return int
     * @author lmk
     * @Date 2021/12/30 19:09
     */
    public int updateAdminInfo(Integer id, Admin admin);

    /**
     * 获取患者总数
     *
     * @return int
     * @author lmk
     * @Date 2021/12/30 20:39
     */
    public int getPatientCount();

}
