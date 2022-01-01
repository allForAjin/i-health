package com.health.test;

import com.health.dao.impl.NormalDaoImpl;
import com.health.entity.Normal;
import com.health.service.impl.AdminServiceImpl;
import com.health.utils.WebUtil;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName NormalDaoImplTest.java
 * @Description TODO
 * @createTime 2021-12-19 10:27:16
 */
class NormalDaoImplTest {

    @Test
    void addNormalInfo() {

            for (int i=1;i<=27;i++){
                Normal normal=new Normal(null,i,0,0, WebUtil.getDateAfter(new Date(),4),"上午");
                Normal normal1=new Normal(null,i,0,0, WebUtil.getDateAfter(new Date(),4),"下午");
                new NormalDaoImpl().addNormalInfo(normal);
                new NormalDaoImpl().addNormalInfo(normal1);
            }



        //new AdminServiceImpl().addNormalRegistEveryday();

    }
}