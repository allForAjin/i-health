package com.health.dao;

import com.health.entity.Admin;
import com.health.entity.OperateRecord;
import com.health.entity.Patient;

import java.util.List;
import java.util.Map;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName AdminDao.java
 * @Description TODO
 * @createTime 2021-11-20 16:56:11
 */
public interface AdminDao {
    /**
     * 获取当前页的记录
     *
     * @param username 用户名
     * @param operate  操作类型
     * @param type     用户类型
     * @param begin    sql语句起始索引
     * @param limit    每页显示的数量
     * @return java.util.List<com.health.entity.OperateRecord>
     * @author lmk
     * @Date 2021/11/20 16:47
     */
    public List<OperateRecord> getPage(String username, String operate, String type, int begin, int limit);

    /**
     * 获取访客记录表中记录数量
     *
     * @param username 用户名
     * @param operate  用户操作
     * @param type     用户类型
     * @return int 记录数
     * @author lmk
     * @Date 2021/11/21 0:27
     */
    public int getRecordCount(String username, String operate, String type);

    /**
     * 更新门诊日期
     *
     * @param date 当日日期
     * @return int
     * @author lmk
     * @Date 2021/11/26 16:59
     */
    public int updateNormalRegistDate(String date);

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
     * 获取患者数量
     *
     * @param name  患者名字
     * @param phone 患者电话
     * @return int
     * @author lmk
     * @Date 2021/12/8 22:05
     */
    public int getPatientCount(String name, String phone);

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
    public List<Patient> getPatientInfo(String name, String phone, int begin, int limit);

    /**
     * 更新患者信息
     *
     * @param patient 待更新的患者信息
     * @return int 大于等于1为成功，反之失败
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
     * 插入患者
     * @author lmk
     * @Date 2021/12/16 19:53
     * @param patient 待插入的患者
     * @return int 大于等于1为成功，反之失败
     */
    public int insertPatient(Patient patient);

    /**
     * 通过患者的账号（手机号）获取患者数量
     * @author lmk
     * @Date 2021/12/16 20:14
     * @param phone 患者账号（手机号）
     * @return int
     */
    public int queryUserCountByPhone(String phone);
}
