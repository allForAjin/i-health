package com.health.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName MessageUtil.java
 * @Description TODO
 * @createTime 2021-11-30 17:37:35
 */
public class MessageUtil {

    private static final String API_ID = "C41499189";
    private static final String PASSWORD = "535d551934cab731c1f743afa10e0abb";
    private static final String FORMAT = "json";
    private static final String URL = "https://106.ihuyi.com/webservice/sms.php?method=Submit";


    /**
     * 调用接口发送验证码
     *
     * @param phone 手机号
     * @param code  验证码
     * @return java.lang.String 接口返回的结果
     * @author lmk
     * @Date 2021/12/1 16:28
     */
    public static String sendCode(String phone, String code) {
        String content = "您的验证码是：" + code + "。请不要把验证码泄露给其他人。";
        Map<String, String> header = new HashMap<>();
        header.put("ContentType", "application/x-www-form-urlencoded;charset=GBK");

        Map<String, String> params = new HashMap<>();
        params.put("account", API_ID);
        params.put("password", PASSWORD);
        params.put("mobile", phone);
        params.put("content", content);
        params.put("format", FORMAT);
        return HttpClientUtil.doPost(URL, header, params);
    }

    /**
     * 生成随机数验证码
     *
     * @return java.lang.String
     * @author lmk
     * @Date 2021/12/1 16:27
     */
    public static String createCode() {
        int code = (int) ((Math.random() * 9 + 1) * 100000);
        return String.valueOf(code);
    }


}
