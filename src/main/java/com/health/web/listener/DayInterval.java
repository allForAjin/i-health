package com.health.web.listener;
/**
 * @ClassName ${NAME}.java
 * @author lmk
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021-11-26 16:43:46
 */

import com.health.dao.impl.AdminDaoImpl;
import com.health.service.impl.AdminServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@WebListener
public class DayInterval implements ServletContextListener {

    public DayInterval() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /* This method is called when the servlet context is initialized(when the Web application is deployed). */
        showDayTime();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
    }

    public static void showDayTime() {
        Date sendDate = new Date();
        Timer dTimer = new Timer();
        dTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minutes = c.get(Calendar.MINUTE);
                int second = c.get(Calendar.SECOND);
                //if (hour == 0 && minutes == 0) {
                    new AdminServiceImpl().updateNormalRegistDate(new Date());
                //}
            }
            //设置24小时执行一次
        }, sendDate, 1000 * 60 * 60 * 24);
    }


}
