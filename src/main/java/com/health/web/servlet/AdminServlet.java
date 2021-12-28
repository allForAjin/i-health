package com.health.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.health.entity.*;
import com.health.entity.response.Code;
import com.health.entity.response.ResponseResult;
import com.health.service.AdminService;
import com.health.service.impl.AdminServiceImpl;
import com.health.utils.JsonUtil;
import com.health.utils.PageHelper;
import com.health.utils.WebUtil;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
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
    private static final Logger LOGGER = Logger.getLogger(AdminServlet.class);

    /**
     * 获取访客信息
     *
     * @param request  请求
     * @param response 响应
     * @author lmk
     * @Date 2021/12/8 22:02
     */
    protected void getVisitorRecord(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operate = "all".equals(request.getParameter("operate")) ? "" : request.getParameter("operate");
        String type = "all".equals(request.getParameter("type")) ? "" : request.getParameter("type");
        String username = request.getParameter("username");

        int offset = WebUtil.parseInt(request.getParameter("offset"), 0);
        int limit = WebUtil.parseInt(request.getParameter("limit"), 10);

        PageHelper<OperateRecord> pageHelper = adminService.getRecordPage(username, operate, type, offset, limit);

        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(JSON.toJSONString(pageHelper));
        response.getWriter().flush();
        response.getWriter().close();
    }

    /**
     * 获取admin信息
     *
     * @param request  请求
     * @param response 响应
     * @author lmk
     * @Date 2021/12/8 21:59
     */
    protected void getAdminInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String phone = request.getParameter("adminPhone");
        Admin admin = adminService.getAdminInfoByPhone(phone);

        ResponseResult<Admin> result = null;
        if (admin != null) {
            result = new ResponseResult<>(Code.SUCCESS, admin);
        }
        response(response, JsonUtil.toJson(result));
    }

    protected void getPatientInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("patientName");
        String phone = request.getParameter("patientPhone");

        int begin = WebUtil.parseInt(request.getParameter("begin"), 0);
        int limit = WebUtil.parseInt(request.getParameter("limit"), 5);
        PageHelper<Patient> pageHelper = adminService.getPatientInfo(name, phone, begin, limit);

        response(response, JsonUtil.toJson(pageHelper));
    }

    protected void updatePatientInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Patient patient = WebUtil.createBeanByMap(request.getParameterMap(), new Patient());
        int num = adminService.updatePatientInfo(patient);
        ResponseResult<String> result = null;
        if (num < 1) {
            result = new ResponseResult<>(Code.FAIL, "更新失败！");
        } else {
            result = new ResponseResult<>(Code.SUCCESS, "更新成功");
        }
        response(response, JsonUtil.toJson(result));
    }

    protected void deletePatient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        int num = adminService.deletePatientById(WebUtil.parseInt(id, 0));
        ResponseResult<String> result = null;
        if (num < 1) {
            result = new ResponseResult<>(Code.FAIL, "删除失败！");
        } else {
            result = new ResponseResult<>(Code.SUCCESS, "删除成功!");
        }
        response(response, JsonUtil.toJson(result));
    }

    protected void batchDeletePatient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String values = request.getParameter("values");
        JSONArray jsonArray = JSONArray.parseArray(values);
        int num = 0;
        for (int i = 0; i < jsonArray.size(); i++) {
            num += adminService.deletePatientById((Integer) jsonArray.getJSONObject(i).get("id"));
        }
        ResponseResult<String> result = null;
        if (num == jsonArray.size()) {
            result = new ResponseResult<>(Code.SUCCESS, "删除" + num + "项成功");
        } else {
            result = new ResponseResult<>(Code.FAIL, "删除失败，num=" + num);
        }
        response(response, JsonUtil.toJson(result));
    }


    /**
     * 添加患者
     *
     * @param request  请求
     * @param response 响应
     * @return void
     * @author lmk
     * @Date 2021/12/16 20:26
     */
    protected void addPatient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Patient patient = WebUtil.createBeanByMap(request.getParameterMap(), new Patient());
        ResponseResult<String> result = null;
        if (adminService.userIsExisted(patient.getPhone())) {
            result = new ResponseResult<>(Code.EXISTED, "用户已存在！");
        } else {
            int num = adminService.addPatient(patient);
            if (num < 1) {
                result = new ResponseResult<>(Code.FAIL, "插入失败！");
            } else {
                result = new ResponseResult<>(Code.SUCCESS, "插入成功!");
            }
        }

        response(response, JsonUtil.toJson(result));
    }

    protected void getNormalInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String hospitalName=request.getParameter("hospitalName");
        String level=request.getParameter("level");
        String date=request.getParameter("date");

        int begin=WebUtil.parseInt(request.getParameter("offset"),0);
        int limit=WebUtil.parseInt(request.getParameter("limit"),20);

        PageHelper<NormalRegistInfo> pageHelper= adminService.getNormalInfo(hospitalName,level,date,begin,limit);
        super.response(response,JsonUtil.toJson(pageHelper));
    }
}
