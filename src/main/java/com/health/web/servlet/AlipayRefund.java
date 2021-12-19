package com.health.web.servlet; /**
 * @ClassName ${NAME}.java
 * @author lmk
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021-12-06 15:18:18
 */

import com.health.service.PatientService;
import com.health.service.impl.PatientServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AlipayRefund", value = "/pay/refund")
public class AlipayRefund extends HttpServlet {
    private final PatientService patientService = new PatientServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("refund");
        String orderId = request.getParameter("out_trade_no");
        patientService.payForNormalRegist(orderId, -2);
        ServletContext application = request.getServletContext();
        String basePath = (String) application.getAttribute("basePath");
        response.sendRedirect(basePath + "page/patient/regist_record.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
