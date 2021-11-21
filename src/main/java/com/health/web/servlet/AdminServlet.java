package com.health.web.servlet;

import com.alibaba.fastjson.JSON;
import com.health.entity.OperateRecord;
import com.health.entity.Page;
import com.health.service.AdminService;
import com.health.service.impl.AdminServiceImpl;
import com.health.utils.JsonUtil;
import com.health.utils.PageHelper;
import com.health.utils.WebUtil;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName AdminServlet.java
 * @Description TODO
 * @createTime 2021-11-20 16:57:24
 */
@WebServlet(name = "AdminServlet", value = "/admin/adminServlet")
public class AdminServlet extends BaseServlet {
    private final AdminService adminService = new AdminServiceImpl();

    protected void getVisitorRecord(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        InputStream inputStream = null;
//        inputStream=request.getInputStream();
//        String data = "";
//        data = IOUtils.toString(inputStream, "utf-8");
//        System.out.println(data);


        //Map<String, Object> dataMap = JsonUtil.convertJsonToMap(data, Page.OFFSET, Page.LIMIT);
//        int offset = (int) dataMap.get(Page.OFFSET);
//        int limit = (int) dataMap.get(Page.LIMIT);

        String operate = "all".equals(request.getParameter("operate")) ? "" : request.getParameter("operate");
        String type = "all".equals(request.getParameter("type")) ? "" : request.getParameter("type");


        int offset = WebUtil.parseInt(request.getParameter("offset"), 0);
        int limit = WebUtil.parseInt(request.getParameter("limit"), 10);

        PageHelper<OperateRecord> pageHelper = adminService.getRecordPage(operate, type, offset, limit);

        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(JSON.toJSONString(pageHelper));
        response.getWriter().flush();
        response.getWriter().close();
    }
}
