package com.health.dao;

import com.health.entity.NormalRegistInfo;
import com.health.entity.NormalRegistRecord;

import java.math.BigDecimal;
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
     * @author lmk
     * @Date 2021/11/25 18:41
     * @param hospitalId 医院名
     * @param date 日期
     * @param begin sql起始索引
     * @param limit 每页限制条数
     * @return java.util.List<com.health.entity.NormalRegistInfo> null表示未找到或失败
     */
    public List<NormalRegistInfo> getNormalRegistInfo(String hospitalId, String date, int begin, int limit);

    /**
     * 获取门诊信息总量
     * @author lmk
     * @Date 2021/11/25 18:56
     * @param hospitalId 医院名
     * @param date 日期
     * @return int -1表示失败
     */
    public int getTotalCount(String hospitalId,String date);

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
     * 添加用户门诊挂号记录
     * @author lmk
     * @Date 2021/11/28 22:58
     * @param record 待添加的记录
     * @return int
     */
    public int addNormalRegistRecord(NormalRegistRecord record);

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
