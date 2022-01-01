package com.health.service.impl;

import com.health.dao.impl.HospitalDaoImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName PatientServiceImplTest.java
 * @Description TODO
 * @createTime 2021-12-17 18:53:24
 */
class PatientServiceImplTest {

    @Test
    void queryHospitalForPage() {
        System.out.println(new PatientServiceImpl().payForExpertRegist("20211227000010005900182424067117",1));

    }
}