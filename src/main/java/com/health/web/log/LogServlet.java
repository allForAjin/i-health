package com.health.web.log; /**
 * @ClassName ${NAME}.java
 * @author lmk
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021-12-14 09:47:13
 */

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LogServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {

        System.out.println("正在初始化 log4j日志设置信息");
        setConfig(config);
    }


    private void setConfig(ServletConfig config) throws ServletException {
        String log4jLocation = config.getInitParameter("log4j-properties-location");

        ServletContext sc = config.getServletContext();

        if (log4jLocation == null) {
            System.err.println("*** 没有 log4j-properties-location 初始化的文件, 所以使用 BasicConfigurator初始化");
            BasicConfigurator.configure();
        } else {
            String webAppPath = sc.getRealPath("/");
            String log4jProp = webAppPath + log4jLocation;
            File yoMamaYesThisSaysYoMama = new File(log4jProp);
            if (yoMamaYesThisSaysYoMama.exists()) {
                System.out.println("使用: " + log4jProp+"初始化日志设置信息");
                PropertyConfigurator.configure(log4jProp);
            } else {
                System.err.println("*** " + log4jProp + " 文件没有找到， 所以使用 BasicConfigurator初始化");
                BasicConfigurator.configure();
            }
        }
        super.init(config);
    }
}
