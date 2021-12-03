package com.health.web.servlet;

import com.health.entity.OperateRecord;
import com.health.entity.User;
import com.health.service.UserService;
import com.health.service.impl.UserServiceImpl;
import com.health.utils.WebUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName UserServlet.java
 * @Description TODO
 * @createTime 2021-11-15 20:39:44
 */
@WebServlet(name = "UserServlet", value = "/userServlet")
public class UserServlet extends BaseServlet {
    private final UserService userService = new UserServiceImpl();

    /**
     * 注销
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @author lmk
     * @Date 2021/11/16 10:12
     */
    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String username = user.getUsername();
        String ip = WebUtil.getRemoteHost(request);
        OperateRecord record = new OperateRecord(null, username, ip, WebUtil.dateToStrong(new Date(), WebUtil.DATETIME), OperateRecord.LOGOUT);
        userService.addUserOperation(record);
        session.invalidate();
        //response.sendRedirect(request.getContextPath() + "/page/user/login.jsp");
    }

    /**
     * 用户登录
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @author lmk
     * @Date 2021/11/16 10:12
     */
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String ip = WebUtil.getRemoteHost(request);


        HttpSession session = request.getSession();

        //用户名不存在时重定向并回显信息
        if (!userService.userIsExisted(username)) {
            session.setAttribute("msg", "notExisted");
            session.setAttribute("username", username);
            session.setMaxInactiveInterval(5);
            response.sendRedirect(request.getContextPath() + "/page/user/login.jsp");
        } else {
            //用户名存在时
            boolean flag = userService.userLogin(username, password);
            //登录成功
            if (flag) {
                User user = userService.getUserByUsername(username);
                Cookie cookie = new Cookie("username", username);
                Cookie cookiePassword = new Cookie("password", password);
                cookie.setMaxAge(60 * 60 * 24 * 7);
                cookiePassword.setMaxAge(60 * 60 * 24 * 7);
                System.out.println("是否登录" + isLogin(user, request, session));
                OperateRecord record = new OperateRecord(null, username, ip, WebUtil.dateToStrong(new Date(), WebUtil.DATETIME), OperateRecord.LOGIN);
                userService.addUserOperation(record);
                response.addCookie(cookie);
                response.addCookie(cookiePassword);
                response.sendRedirect(request.getContextPath() + "/page/user/login.jsp");
                //用户名或密码错误
            } else {
                session.setAttribute("error", "error");
                session.setAttribute("username", username);
                session.setMaxInactiveInterval(5);
                response.sendRedirect(request.getContextPath() + "/page/user/login.jsp");
            }
        }
    }

    /**
     * 用户注册
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @author lmk
     * @Date 2021/11/20 11:58
     */
    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("phone");
        String ip = WebUtil.getRemoteHost(request);
        HttpSession session = request.getSession();
        if (userService.userIsExisted(username)) {
            System.out.println("存在");
            session.setAttribute("msg", "existed");
            session.setMaxInactiveInterval(5);
            response.sendRedirect(request.getContextPath() + "/page/user/regist.jsp");
        } else {
            boolean flag = userService.register(request.getParameterMap());
            if (flag) {
                System.out.println("成功");
                request.setAttribute("regist", "success");
                OperateRecord record = new OperateRecord(null, username, ip, WebUtil.dateToStrong(new Date(), WebUtil.DATETIME), OperateRecord.REGIST);
                userService.addUserOperation(record);
                request.getRequestDispatcher("/page/user/regist.jsp").forward(request, response);
            } else {
                System.out.println("失败");
                session.setAttribute("error", "error");
                session.setMaxInactiveInterval(5);
                response.sendRedirect(request.getContextPath() + "/page/user/regist.jsp");
            }
        }
    }


    /**
     * 判断当前用户是否登录过并将用户实体存入application域中
     *
     * @param user    要登录的用户
     * @param request HttpServletRequest
     * @param session HttpSession
     * @return boolean 登录与否
     * @author lmk
     * @Date 2021/11/20 11:58
     */
    private boolean isLogin(User user, HttpServletRequest request, HttpSession session) {
        boolean flag = false;
        ServletContext application = request.getServletContext();
        Map<User, String> loginMap = (Map<User, String>) application.getAttribute("loginMap");
        if (loginMap == null) {
            loginMap = new HashMap<>();
        }
        for (User loginUser : loginMap.keySet()) {
            System.out.println("key:" + loginUser + ",value:" + session.getId());
            if (user.getUsername().equals(loginUser.getUsername())) {
                //相同浏览器登录
                if (loginMap.get(loginUser).equals(session.getId())) {
                    System.out.println("相同");
                } else {
                    //异地登录
                    System.out.println("异地");
                    loginMap.remove(loginUser);
                    flag = true;
                }
            }
        }
        session.setAttribute("user", user);
        session.setMaxInactiveInterval(60 * 60 * 12);
        loginMap.put(user, session.getId());
        application.setAttribute("loginMap", loginMap);
        return flag;
    }


}
