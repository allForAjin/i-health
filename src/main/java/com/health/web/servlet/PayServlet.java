package com.health.web.servlet; /**
 * @ClassName ${NAME}.java
 * @author lmk
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021-12-01 13:39:57
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.health.entity.alipay.PayInfo;
import com.health.service.PatientService;
import com.health.service.impl.PatientServiceImpl;
import com.health.utils.AliPayUtil;
import com.health.utils.JsonUtil;
import com.health.utils.WebUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "PayServlet", value = "/pay/goAlipay")
public class PayServlet extends BaseServlet {
    private final PatientService patientService = new PatientServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }

    protected void normalRegistPay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tradeNo = request.getParameter("id");
        String totalAmount = request.getParameter("cost");
        PayInfo info = WebUtil.createBeanByMap(request.getParameterMap(), new PayInfo());

        String subject = info.toString();
        String body = "门诊挂号";

        System.out.println(tradeNo + "," + totalAmount + "," + subject + "," + body);

        String result = "";
        result = AliPayUtil.alipay(tradeNo, totalAmount, subject, body);
        Cookie cookie = new Cookie("user", request.getSession().getId());
        cookie.setMaxAge(60 * 60 * 24);
        response.addCookie(cookie);
        super.response(response, result);
    }

    protected void normalRegistPayRefund(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tradeNo = request.getParameter("id");
        String totalAmount = request.getParameter("cost");

        String result = "";
        result = AliPayUtil.alipayRefund(tradeNo, totalAmount, "不需要了", null);
        JSONObject jsonObject= JSON.parseObject(result);
        String code=(String) jsonObject.getJSONObject("alipay_trade_refund_response").get("code");

        Map<String,String> map=new HashMap<>();
        int num=-1;
        if("10000".equals(code)){
            num=patientService.payForNormalRegist(tradeNo, -2);
        }

        if (num>=1){
            map.put("result","success");
        }

        super.response(response, JsonUtil.toJson(map));
    }

}
