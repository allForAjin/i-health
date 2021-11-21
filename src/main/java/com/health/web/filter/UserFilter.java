package com.health.web.filter;

import com.health.entity.User;
import com.health.service.UserService;
import com.health.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName UserFilter.java
 * @Description TODO
 * @createTime 2021-11-16 12:51:41
 */
public class UserFilter implements Filter {
    private final UserService userService = new UserServiceImpl();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            //response.sendRedirect(request.getContextPath() + "/page/user/login.jsp");
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            response.sendRedirect(request.getContextPath() + userService.getPathByUserType(user.getType()));
        }
    }

    @Override
    public void destroy() {
    }
}
