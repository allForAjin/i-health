package com.health.web.servlet;
/**
 * @ClassName ${NAME}.java
 * @author lmk
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021-12-01 18:02:05
 */

import com.health.service.PatientService;
import com.health.service.impl.PatientServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Alipay", value = "/pay/Alipay")
public class Alipay extends HttpServlet {
    private final PatientService patientService = new PatientServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("out_trade_no");
        patientService.payForNormalRegist(orderId, 1);
        ServletContext application = request.getServletContext();
        String basePath = (String) application.getAttribute("basePath");
        response.sendRedirect(basePath + "page/patient/regist_record.jsp");
    }

}
