package com.health.dao;

import com.health.entity.Department;

import java.util.List;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName DepartmentDao.java
 * @Description TODO
 * @createTime 2021-11-24 16:56:35
 */
public interface DepartmentDao {
    /**
     * 获取所有科室名
     * @author lmk
     * @Date 2021/11/24 16:57
     * @return java.util.List<com.health.entity.Department> 返回null则表示无医院或获取失败
     */
    public List<Department> getAllDepartmentName();
}
