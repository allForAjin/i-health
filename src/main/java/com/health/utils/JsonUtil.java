package com.health.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName JsonUtil.java
 * @Description TODO
 * @createTime 2021-11-20 17:53:27
 */
public class JsonUtil {
    public static Map convertJsonToMap(String jsonData,String...keys){
        if (jsonData==null){
            return null;
        }

        Map map= new HashMap();
        JSONObject jsonObject= JSON.parseObject(jsonData);
        for (String key:keys){
            map.put(key,jsonObject.get(key));
        }
        return map;
    }
}
