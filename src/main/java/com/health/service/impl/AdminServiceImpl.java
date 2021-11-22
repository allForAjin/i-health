package com.health.service.impl;

import com.health.dao.AdminDao;
import com.health.dao.impl.AdminDaoImpl;
import com.health.entity.OperateRecord;
import com.health.service.AdminService;
import com.health.utils.PageHelper;

import java.util.List;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName AdminServiceImpl.java
 * @Description TODO
 * @createTime 2021-11-20 16:58:03
 */
public class AdminServiceImpl implements AdminService {
    private final AdminDao adminDao = new AdminDaoImpl();

    @Override
    public PageHelper<OperateRecord> getRecordPage(String username, String operate, String type, int begin, int limit) {
        int total = adminDao.getRecordCount(username, operate, type);
        List<OperateRecord> recordList = adminDao.getPage(username, operate, type, begin, limit);
        PageHelper<OperateRecord> pageHelper = new PageHelper<>(recordList, total);
        return pageHelper;
    }
}
