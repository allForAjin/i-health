package com.health.web.listener; /**
 * @ClassName ${NAME}.java
 * @author lmk
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021-11-16 21:56:11
 */

import com.health.entity.User;
import javafx.application.Application;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.HashMap;
import java.util.Map;

public class LoginListener implements HttpSessionListener {

    public LoginListener() {

    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
        User user = (User) se.getSession().getAttribute("user");
        if (user != null) {
            Map<User, String> loginMap = (Map<User, String>) se.getSession().getServletContext().getAttribute("loginMap");
            loginMap.remove(user);
            se.getSession().getServletContext().setAttribute("loginMap", loginMap);
            System.out.println("已销毁：" + user);
        }

    }


}
