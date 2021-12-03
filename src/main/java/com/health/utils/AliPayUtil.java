package com.health.utils;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.health.entity.AlipayConfig;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName AliPayUtil.java
 * @Description TODO
 * @createTime 2021-11-30 23:00:45
 */
public class AliPayUtil {
    /**
     * 该笔订单允许的最晚付款时间，逾期将关闭交易。
     * 取值范围：1m～15d。
     * m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。
     * 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
     */
    private static final String TIMEOUT_EXPRESS = "15m";

    /**
     * 支付宝支付
     *
     * @param tradeNo     商户订单号，必填
     * @param totalAmount 付款金额，单位 元（0.01元）
     * @param subject     订单名称，必填 （lmk-付款用户[...]）
     * @param body        商品描述，可空
     * @return java.lang.String
     * @author lmk
     * @Date 2021/12/1 12:48
     */
    public static String alipay(String tradeNo, String totalAmount, String subject, String body) {
        AlipayClient alipayClient = new DefaultAlipayClient(
                AlipayConfig.GATEWAY_URL, AlipayConfig.APP_ID,
                AlipayConfig.MERCHANT_PRIVATE_KEY, "json",
                AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGN_TYPE);
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.RETURN_URL);
        alipayRequest.setNotifyUrl(AlipayConfig.NOTIFY_URL);
        try {
            alipayRequest.setBizContent("{\"out_trade_no\":\"" + tradeNo + "\","
                    + "\"total_amount\":\"" + totalAmount + "\","
                    + "\"subject\":\"" + subject + "\","
                    + "\"timeout_express\":\"" + TIMEOUT_EXPRESS + "\","
                    + "\"body\":\"" + body + "\","
                    + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

            //请求
            String result="";

            result = alipayClient.pageExecute(alipayRequest).getBody();
            System.out.println("返回结果为：" + result);
            return result;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }
}
