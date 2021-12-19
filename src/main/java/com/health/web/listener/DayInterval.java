package com.health.web.listener;
/**
 * @ClassName ${NAME}.java
 * @author lmk
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021-11-26 16:43:46
 */

import com.health.dao.AdminDao;
import com.health.dao.impl.AdminDaoImpl;
import com.health.service.AdminService;
import com.health.service.impl.AdminServiceImpl;
import com.health.utils.WebUtil;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@WebListener
public class DayInterval implements ServletContextListener {
    private final AdminService adminService = new AdminServiceImpl();

    public DayInterval() {

    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /* This method is called when the servlet context is initialized(when the Web application is deployed). */
        updateDayTime();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
    }

    public void updateDayTime() {
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
//                System.out.println(
//                        "每日日期更新：" +
//                                adminService.updateNormalRegistDate(new Date()) + "条，"
//                                + WebUtil.dateToStrong(new Date(), WebUtil.DATE));
                System.out.println("每日支付状态更新：" + adminService.updatePayStatusEveryday(-1) + "条");
                //System.out.println("每日剩余号量更新：" + adminService.updateNormalRemainEveryday() + "条");

                //}
            }
            //设置24小时执行一次
        }, sendDate, 1000 * 60 * 60 * 24);
    }


}
