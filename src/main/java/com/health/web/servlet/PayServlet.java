package com.health.web.servlet; /**
 * @ClassName ${NAME}.java
 * @author lmk
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021-12-01 13:39:57
 */

import com.health.entity.PayInfo;
import com.health.utils.AliPayUtil;
import com.health.utils.WebUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "PayServlet", value = "/pay/goAlipay")
public class PayServlet extends BaseServlet {

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
        response.getWriter().write(result);
        response.getWriter().flush();
        response.getWriter().close();
    }
}
