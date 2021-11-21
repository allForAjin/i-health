package com.health.web.servlet;

/**
 * @ClassName PatientServlet.java
 * @author lmk
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021-11-15 14:18:27
 */

import com.health.entity.Patient;
import com.health.service.PatientService;
import com.health.service.impl.PatientServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "PatientServlet", value = "/patient/patientServlet")
public class PatientServlet extends BaseServlet {
    private PatientService patientService = new PatientServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }

    protected void patientLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (!patientService.patientIsExisted(username)) {
            request.setAttribute("msg", "用户不存在！");
            request.setAttribute("username", username);
            request.getRequestDispatcher("page/user/login.jsp").forward(request, response);
        } else {
            boolean flag = patientService.patientLogin(username, password);
            System.out.println(flag);
            if (flag) {
                Patient patient=patientService.getPatientByUsername(username);
                request.getSession().setAttribute("patient", patient);
                response.sendRedirect(request.getContextPath() + "/page/admin_site.jsp");
            } else {
                request.setAttribute("error", "用户或密码错误！");
                request.setAttribute("username", username);
                request.getRequestDispatcher("page/user/login.jsp").forward(request, response);
            }
        }
    }
}
