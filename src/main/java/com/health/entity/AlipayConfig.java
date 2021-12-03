package com.health.entity;

import com.health.utils.WebUtil;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName AlipayConfig.java
 * @Description TODO
 * @createTime 2021-12-01 12:20:20
 */
public class AlipayConfig {
    /**
     * 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
     */
    public static final String APP_ID = "2021000118658191";

    /**
     * 商户私钥，您的PKCS8格式RSA2私钥
     */
    public static final String MERCHANT_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCge+wF2KlsigBsR/slGp7o6W9/JrgjB4X3fAVnMuPzvRRMu6BlDfZojtQNhGYcbNc5RJyAGsJLgMBWtBQCVr2eszji6T38UUqviZ2Ft+1hKofVbvUfDJCOe9oPzaNzONedAQX+eAfEWzNFosImnVlkOp+AtxudJs+jsnlnODqczdpRNkItO+Xjq3sy+gRVep5my3Ow6eaBkQghAQcxgWFh8fwKwTT9W8UxT24aHUzd9SH227NB3PEt2ziiLfnrVqGhnuas55BClR1tGi75ZoKez/Tir/fwkXefk87NwQ9+Fs3RfJOcGTuQSbDLyWpfmfMCXdIMKHcHrbKJycyZCTDDAgMBAAECggEAceG+SR/IincbebtSoq/TWGBeeJJXVLRAArEFfIgGbq4cO59hYJSZ6PSB0d3EQCOUTtU7YUSb38xLsLs0/7vh7z4q/gqZq/Vb1FkJaRxt3Oe7YF/OnevioNmOkmxQsrEBgC57vGocyOAW8DFhKNm7134qNcK6W7hw3C8IK5EhNqdVuk59t7llkmcCC73v/7tcU2bopg2b08+r1lyeUVmBdbmbK96bw/CjF8PdBMR/UuA6GI9wLaqeLB95UXtGCygygcDw6YheyLtP5LJDXXX5soBeCnWlYx5rkeqpIlFR2QTL3FpWSiZLHW8AZQwFs2d6sDkLLtKXb++hSL7N9QjycQKBgQDL1VzSF09qKjb8floC5W2yY1SSNUMV5hbsd3iGKNtIYOnckWR8JLapACM91Oy9wpVzL38NoRMzfAJjoC2NyXYjfqI9+7/l00izQCY8GTEwlhJfKwlI4oICtxLO8mfy7mC8sqxjAsjNncLbBBMnEDUQUXTNNYvGoFrHgTJz0NjPZQKBgQDJjmvAcQq4AzyFYlSSM4OAzOrXgNXp2ArFyg1iCBw+0Ha07btngZ0tfx0JEARsDoGegKxtc89/8HWCShk30EkSfNkgz+4S/s366k0zeBXtLM6JRbTMchTK2zE991H/piUJPguuRIoDKImv/B01Ax1sZb1vrHKTswpLOFPBs6mhBwKBgASI4KHD5v5jpgmD/himZgRb9VuzV6S7E7N7DPFfI1G+5jUD/wpL/sxPtnj/rayjEZQYcGkrNjQlBCCXRawr2Tb88DsUyLYiNzHVsYQQw2ljTVFUscYvU9kfnYmBFxLufrRz/SXOwAzi/5zDwHFUCPot9b1C/bMTijF6DFdLRCeJAoGABbVC1/BbnVp6HEzsmDddQgiLng7IABdeNrvCJUSViDHfKsCQzh2dlexDpj4N4gGHKjX6rB7bgYQNz+LJVAtSBnQCsisxUthkfPaluQ9tj4n1T23p8OBKNnR+sIKQj+zkn55iNk06m1xKifFZ4mZ0Lt2yv7bih8xKPlcaCCJOa4ECgYBmAzSGMPPgyTHLeTUJgaWvTchhTfDfcTYXP2PTARon2BporOdAYAhePBJT0EYkhStPISHVR25JozImkFjhHkOqiWZnPiDK8nQBsRcIdxUhL/rQPzvw8cHZzi8OfXQzqdnbsHBLbw1zWwRNloch1+JkvMgsx0/RGpjFyG8wtfk/ng==";

    /**
     * 支付宝公钥
     */
    public static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtAaMqWyzBMrQIbIRwKLRRD+AY3JqYjzS8rhOrYUr9/NKri7WPIQf9yeSA/eZXDmpkciRDNjAkpCfsLrjE5/XBEn1ih+C3nSh15v9oW41Tzq8QPXwLA1bFhNlp9h9nkdG58sxy1tmva+oi0AduoFSSvnHvChREFwpBKftT8nGcmU50vHX/Ycam4dYy7CIiZImR6IM9HtGH5A6yO6HZB7h9VH1536UZ+hWdam9w4kuxlvAbTvdWzAW+8rds/3m5rQ02IEXOzoqiPuQi0JB/cFKOEGztEzROw8JZiLgymkHikCtfz4Zn/Rc8nm9Nt6nKYvBN1sEjikrkujSK7pxCriUMwIDAQAB";

    /**
     * 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
     */
    public static final String NOTIFY_URL = "http://127.0.0.1:8001/i_health/pay";

    /**
     * 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
     */
    public static final String RETURN_URL = "http://127.0.0.1:8001/i_health/pay/Alipay";

    /**
     * 签名方式
     */
    public static final String SIGN_TYPE = "RSA2";

    /**
     * 字符编码格式
     */
    public static final String CHARSET = "utf-8";

    /**
     * 支付宝网关
     */
    public static final String GATEWAY_URL = "https://openapi.alipaydev.com/gateway.do";

    public static final String LOG_PATH = "E:\\";



    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(LOG_PATH + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String createOrderId(){
        String date= new SimpleDateFormat("yyyyMMdd").format(new Date());
        String part2="00001000";
        String random1=createRandom();
        String part3="00";
        String seconds=new SimpleDateFormat("HHmmssSSSS").format(new Date());
        String random2=createRandom();
        return date+part2+random1+part3+seconds+random2;
    }

    private static String createRandom(){
        Random random=new Random();
        String result=random.nextInt(100)+"";
        if (result.length()==1){
            result="0"+result;
        }
        return result;
    }


}
