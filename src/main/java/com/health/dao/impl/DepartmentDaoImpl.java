package com.health.dao.impl;

import com.health.dao.DepartmentDao;
import com.health.entity.Department;
import com.health.utils.SqlUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName DepartmentDaoImpl.java
 * @Description TODO
 * @createTime 2021-11-24 16:59:12
 */
public class DepartmentDaoImpl implements DepartmentDao {

    @Override
    public List<Department> getDepartmentByHospital(String hospitalId) {
        String sql="select id,dep_name from department where hospital_id=?";
        List<Object> objectList= SqlUtil.executeQuery(sql,hospitalId);
        List<Department> departmentList=new ArrayList<>();
        for (Object object:objectList){
            Object[] result=(Object[]) object;
            departmentList.add(new Department((Integer) result[0],(String) result[1],null));
        }
        return departmentList;
    }

    @Override
    public int getDepartmentCount() {
        String sql="select count(*) from department";
        return SqlUtil.executeQueryCount(sql);
    }
}
