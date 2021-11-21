package com.health.test;

import com.health.utils.SqlUtil;
import org.junit.jupiter.api.Test;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName SqlUtilTest.java
 * @Description TODO
 * @createTime 2021-11-17 14:15:29
 */
class SqlUtilTest {

    @Test
    void executeProcedureWithInput() {
        Map<String, Object> input = new HashMap<>();
        input.put("username", "testpatient1");
        input.put("password", "12345678");
        input.put("phone", "136381875632");
        input.put("type", "patient");

        Map<String, Integer> output = new HashMap<>();
        output.put("count", Types.INTEGER);

        String sql = "{call add_patient(?,?,?,?,?)}";
        Map<String, Object> map = SqlUtil.executeProcedureWithInputAndOutput(sql, input, output);
        for (String key : map.keySet()) {
            System.out.println("key:" + key + ",value:" + map.get(key));
        }


    }
}