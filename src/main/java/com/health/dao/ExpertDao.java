package com.health.dao;

import com.health.entity.Expert;
import com.health.entity.ExpertRegistInfo;
import com.health.entity.ExpertRegistRecord;

import java.util.List;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName ExpertDao.java
 * @Description TODO
 * @createTime 2021-12-23 21:06:54
 */
public interface ExpertDao {
    /**
     * 通过科室id获取该科室的专家信息
     *
     * @param departmentId 科室id
     * @return java.util.List<com.health.entity.Expert>
     * @author lmk
     * @Date 2021/12/23 21:08
     */
    public List<Expert> getExpertInfoByDepartment(String departmentId);

    /**
     * 获取专家挂号信息
     *
     * @param doctorId   医生序号
     * @param registDate 挂号日期
     * @param time       挂号时间段
     * @return java.util.List<com.health.entity.ExpertRegistInfo> null表示无，反之则为成功
     * @author lmk
     * @Date 2021/12/25 14:29
     */
    public List<ExpertRegistInfo> queryExpertRegistInfo(String doctorId, String registDate, String time);

    /**
     * 判断专家门诊挂号记录是否存在
     *
     * @param patientId 患者id
     * @param expertId  专家id
     * @return int 大于等于1表示存在
     * @author lmk
     * @Date 2021/12/27 17:00
     */
    public int expertRegistRecordIsExisted(String patientId, String expertId);

    /**
     * 添加专家门诊挂号记录
     *
     * @param record 专家门诊挂号记录
     * @return int
     * @author lmk
     * @Date 2021/12/27 16:51
     */
    public int insertExpertRegistRecord(ExpertRegistRecord record);

    /**
     * 减少专家门诊剩余量
     *
     * @param expertId 专家门诊id
     * @return int 更新条数
     * @author lmk
     * @Date 2021/12/27 17:40
     */
    public int decreaseExpertRegistRemain(String expertId);

    /**
     * 查询用户专家挂号数量
     *
     * @param phone 用户账号
     * @return int 记录数量
     * @author lmk
     * @Date 2021/12/28 15:07
     */
    public int getExpertRecordCount(String phone);

    /**
     * 获取专家门诊挂号信息
     *
     * @param phone 用户账号
     * @param begin 分页起始索引
     * @param limit 每页数量
     * @return java.util.List<com.health.entity.ExpertRegistRecord> null表示无
     * @author lmk
     * @Date 2021/12/28 15:10
     */
    public List<ExpertRegistRecord> getExpertRecord(String phone,int begin,int limit);

    /**
     * 通过订单号获取专家门诊挂号数量
     *
     * @param orderId 订单号
     * @return int 数量
     * @author lmk
     * @Date 2021/12/29 19:19
     */
    public int getExpertCountByOrderId(String orderId);

    /**
     * 更新专家门诊支付状态
     * @author lmk
     * @Date 2021/12/29 20:46
     * @param orderId 订单id
     * @param payStatus 更新后的状态
     * @return int
     */
    public int updatePayStatus(String orderId,Integer payStatus);
}
