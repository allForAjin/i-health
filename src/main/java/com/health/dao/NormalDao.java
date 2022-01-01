package com.health.dao;

import com.health.entity.Normal;
import com.health.entity.NormalRegistInfo;
import com.health.entity.NormalRegistRecord;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName NormalDao.java
 * @Description TODO
 * @createTime 2021-11-29 11:01:16
 */
public interface NormalDao {
    /**
     * 获取门诊信息内容
     *
     * @param hospitalId   医院id
     * @param departmentId 科室id
     * @param date         日期
     * @param time         时间段
     * @return java.util.List<com.health.entity.NormalRegistInfo> null表示未找到或失败
     * @author lmk
     * @Date 2021/11/25 18:41
     */
    public List<NormalRegistInfo> getNormalRegistInfo(String hospitalId, String departmentId, String time, String date);

    /**
     * 获取门诊信息总量
     *
     * @param hospitalId 医院名
     * @param date       日期
     * @return int -1表示失败
     * @author lmk
     * @Date 2021/11/25 18:56
     */
    public int getTotalCount(String hospitalId, String time, String date);

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
     * 添加用户门诊挂号记录
     *
     * @param record 待添加的记录
     * @return int
     * @author lmk
     * @Date 2021/11/28 22:58
     */
    public int addNormalRegistRecord(NormalRegistRecord record);

    /**
     * 挂号时减少剩余号量
     *
     * @param id     序号
     * @param remain 待更新剩余号量
     * @return int 更新语句条数
     * @author lmk
     * @Date 2021/11/29 12:45
     */
    public int decreaseNormalRemain(Integer id, Integer remain);

    /**
     * 获取挂号记录
     *
     * @param phone 用户联系方式
     * @param begin
     * @param limit
     * @return java.util.List<com.health.entity.NormalRegistRecord>
     * @author lmk
     * @Date 2021/11/29 18:02
     */
    public List<NormalRegistRecord> getNormalRegistRecordByPatient(String phone, int begin, int limit);

    /**
     * 获取挂号记录数量
     *
     * @param phone 用户联系方式
     * @return int 更新语句条数
     * @author lmk
     * @Date 2021/11/29 18:06
     */
    public int getNormalRegistRecordCount(String phone);

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
     * 用户支付
     *
     * @param orderId   订单号
     * @param payStatus 订单状态
     * @return int 更新语句条数
     * @author lmk
     * @Date 2021/12/1 17:02
     */
    public int updatePayStatus(String orderId, int payStatus);

    /**
     * 删除用户挂号记录
     *
     * @param id 记录id
     * @return int 删除条数
     * @author lmk
     * @Date 2021/12/2 22:51
     */
    public int deleteNormalRegistRecord(Integer id);

    /**
     * 剩余号量添加
     *
     * @param normalId 挂号id
     * @return int 更新条数
     * @author lmk
     * @Date 2021/12/6 10:42
     */
    public int addNormalRemain(Integer normalId);

    /**
     * 每日更新门诊剩余号量
     *
     * @return int 更新条数
     * @author lmk
     * @Date 2021/12/6 11:00
     */
    public int updateNormalRemainEveryday();


    /**
     * 添加门诊信息
     *
     * @param normal 待添加的信息
     * @return int 添加成功的条数
     * @author lmk
     * @Date 2021/12/19 10:22
     */
    public int addNormalInfo(Normal normal);

    /**
     * 通过订单号获取普通门诊挂号数量
     *
     * @param orderId 订单号
     * @return int
     * @author lmk
     * @Date 2021/12/29 19:19
     */
    public int getNormalCountByOrderId(String orderId);
}

