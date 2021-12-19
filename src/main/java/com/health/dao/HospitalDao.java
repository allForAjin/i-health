package com.health.dao;

import com.health.entity.Hospital;

import java.util.List;
import java.util.Map;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName HospitalDao.java
 * @Description TODO
 * @createTime 2021-11-24 14:46:55
 */
public interface HospitalDao {
    /**
     * 获取所有医院的名称
     *
     * @return java.util.List<Hospital> 返回null则表示无医院或获取失败
     * @author lmk
     * @Date 2021/11/24 14:47
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
     * 通过id获取医院信息
     * @author lmk
     * @Date 2021/12/5 0:01
     * @param hospitalId 医院id
     * @return com.health.entity.Hospital
     */
    public Hospital getHospitalById(Integer hospitalId);

    /**
     * 医院信息分页
     * @author lmk
     * @Date 2021/12/17 18:28
     * @param begin 当前页起始索引
     * @param pageSize 当前页码
     * @return java.util.List<com.health.entity.Hospital> null表示失败，反之成功
     */
    public List<Hospital> queryHospitalForPage(int begin,int pageSize);

    /**
     * 查询医院数量
     * @author lmk
     * @Date 2021/12/17 18:42
     * @return int -1表示失败
     */
    public int queryForHospitalCount();
}
