package com.health.utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName WebUtil.java
 * @Description TODO
 * @createTime 2021-11-07 20:13:14
 */
public class WebUtil {
    public static final String DATETIME="yyyy-MM-dd HH:mm:ss";
    public static final String DATE="yyyy-MM-dd";

    /**
     * 把Map中的值注入到bean中
     *
     * @param value  要注入的属性值
     * @param object bean
     * @return object
     * @author lmk
     * @Date 2021/11/9 15:18
     */
    public static <T> T createBeanByMap(Map value, T object) {
        try {
            BeanUtils.populate(object, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

    /**
     * 将JavaBean转化为Map
     * @param object 待转换的Bean
     * @return java.util.Map
     * @author lmk
     * @Date 2021/11/20 11:26
     */
    public static Map convertBeanToMap(Object object) {
        if (object == null) {
            return null;
        }
        Map beanMap = new HashMap();
        try {
            beanMap = BeanUtils.describe(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        beanMap.remove("class");
        return beanMap;
    }


    /**
     * string转int
     *
     * @param str          待转换字符串
     * @param defaultValue 出现异常时返回值
     * @return int
     * @author lmk
     * @Date 2021/11/12 17:51
     */
    public static int parseInt(String str, int defaultValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * Date类型转换为String
     *
     * @param date 日期Date类型
     * @return java.lang.String 日期String类型
     * @author lmk
     * @Date 2021/11/20 11:40
     */
    public static String dateToStrong(Date date,String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        if (date == null) {
            date = new Date();
        }
        return format.format(date);
    }

    /**
     * long转int
     * @author lmk
     * @Date 2021/11/28 12:56
     * @param value 要转换的值
     * @param defaultValue 出现异常时的默认值
     * @return int
     */
    public static int parseLongToInt(Long value,int defaultValue){
        try {
            return parseInt(Long.toString(value),defaultValue);
        } catch (Exception e) {
            return defaultValue;
        }
    }



    /**
     * 获取客户端真实ip
     * 若客户端使用了反向代理软件时，由于在客户端和服务端中增加了中间层，所以无法获取客户端真实ip
     * 在转发请求的HTTP头信息中，增加了X－FORWARDED－FOR信息用以跟踪原有的客户端IP地址和原来客户端请求的服务器地址。
     * @author lmk
     * @Date 2021/11/20 11:44
     * @param request 客户端请求
     * @return java.lang.String 获取的真实ip
     */
    public static String getRemoteHost(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }

    public static String getDateBefore(Date date,int day){
        Calendar now=Calendar.getInstance();
        now.setTime(date);
        now.set(Calendar.DATE,now.get(Calendar.DATE)-day);
        return WebUtil.dateToStrong(now.getTime(),WebUtil.DATE);
    }

    public static String getDateAfter(Date date,int day){
        Calendar now =Calendar.getInstance();
        now.setTime(date);
        now.set(Calendar.DATE,now.get(Calendar.DATE)+day);
        return WebUtil.dateToStrong(now.getTime(),WebUtil.DATE);
    }

    public static String getWeekOfDate(String dateStr){
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat format=new SimpleDateFormat(WebUtil.DATE);
        Date date= null;
        try {
            date = format.parse(dateStr);
        } catch (ParseException e) {
            date=new Date();
            e.printStackTrace();
        }
        calendar.setTime(date);
        int week=calendar.get(Calendar.DAY_OF_WEEK)-1;
        if (week<0){
            week=0;
        }
        return weekDays[week];
    }

}
