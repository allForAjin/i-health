package com.health.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName MessageUtilTest.java
 * @Description TODO
 * @createTime 2021-12-01 15:29:23
 */
class MessageUtilTest {

    @Test
    void sendCode() {
        //System.out.println(MessageUtil.sendCode("19121542079"));
        System.out.println(MessageUtil.createCode());
    }

    @Test
    void confirmCode() {
    }
}