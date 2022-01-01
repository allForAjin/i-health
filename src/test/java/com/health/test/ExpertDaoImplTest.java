package com.health.test;

import com.health.dao.impl.ExpertDaoImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName ExpertDaoImplTest.java
 * @Description TODO
 * @createTime 2021-12-23 21:21:53
 */
class ExpertDaoImplTest {
    @Test
    void getExpertInfoByDepartment(){
        System.out.println(new ExpertDaoImpl().getExpertInfoByDepartment("1"));

    }

    @Test
    void queryExpertRegistInfo(){
        System.out.println(new ExpertDaoImpl().getExpertRecord("19121542079",0,1));

    }
}