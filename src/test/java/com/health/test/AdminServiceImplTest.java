package com.health.test;

import com.health.dao.impl.NormalDaoImpl;
import com.health.entity.Normal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName AdminServiceImplTest.java
 * @Description TODO
 * @createTime 2021-12-27 15:44:53
 */
class AdminServiceImplTest {

    @Test
    void addNormalRegistEveryday() {
        for(int i=1;i<=27;i++){
            Normal normal = new Normal(null, i, 52, 52, "2022-01-01", "上午");
            Normal normal1 = new Normal(null, i, -1, -1, "2022-01-01", "下午");
            new NormalDaoImpl().addNormalInfo(normal);
            int num=new NormalDaoImpl().addNormalInfo(normal1);
        }

    }
}