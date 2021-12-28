package com.health.test;

import com.health.entity.Patient;
import com.health.entity.User;
import com.health.utils.WebUtil;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName WebUtilTest.java
 * @Description TODO
 * @createTime 2021-11-15 20:18:43
 */
class WebUtilTest {
    @Test
    void convertBeanToMapTest() {
        User user = new User(1, "doctor", "12345678", "doctor");
        Map map = WebUtil.convertBeanToMap(user);
        for (Object key : map.keySet()) {
            System.out.println("key:" + key + ",value:" + map.get(key));
        }
    }

    @Test
    void date() {
        //System.out.println(WebUtil.dateToStrong(new Date()));
//        String str = "2021-11-29";
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            Date date = simpleDateFormat.parse(str);
//            System.out.println(date.getTime() > new Date().getTime());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println();
        System.out.println(WebUtil.getWeekOfDate("2021-12-22"));
    }

}