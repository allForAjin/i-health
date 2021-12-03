package com.health.test;

import com.health.utils.AliPayUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName AliPayUtilTest.java
 * @Description TODO
 * @createTime 2021-12-01 12:54:35
 */
class AliPayUtilTest {

    @Test
    void alipay() {
        AliPayUtil.alipay("1","0.01元","eioqweq","eoiqwueioq");
    }
}