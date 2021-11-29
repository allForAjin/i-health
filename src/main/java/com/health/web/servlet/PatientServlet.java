package com.health.web.servlet;

/**
 * @ClassName PatientServlet.java
 * @author lmk
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021-11-15 14:18:27
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.health.entity.Hospital;
import com.health.entity.NormalRegistInfo;
import com.health.entity.NormalRegistRecord;
import com.health.entity.Patient;
import com.health.service.PatientService;
import com.health.service.impl.PatientServiceImpl;
import com.health.utils.JsonUtil;
import com.health.utils.PageHelper;
import com.health.utils.WebUtil;
import org.apache.commons.io.IOUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "PatientServlet", value = "/patient/patientServlet")
public class PatientServlet extends BaseServlet {
    private final PatientService patientService = new PatientServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }

    protected void getHospitalName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Hospital> hospitalMao = patientService.getAllHospitalName();
        String data = JsonUtil.toJson(hospitalMao);
        System.out.println(data);
        response.getWriter().write(data);
        response.getWriter().flush();
        response.getWriter().close();
    }

    protected void getNormalInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int offset = WebUtil.parseInt(request.getParameter("offset"), 0);
        int limit = WebUtil.parseInt(request.getParameter("limit"), 5);
        String hospitalId = request.getParameter("hospitalId");
        PageHelper<NormalRegistInfo> pageHelper = new PageHelper<>();
        pageHelper = patientService.getNormalRegistInfoPage(hospitalId,
                WebUtil.dateToStrong(new Date(), WebUtil.DATE), offset, limit);
        String result = JsonUtil.toJson(pageHelper);
        System.out.println(result);
        response.getWriter().write(result);
        response.getWriter().flush();
        response.getWriter().close();
    }

    protected void getPersonInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String phone = request.getParameter("patientPhone");
        Patient patient = patientService.getPatientByPhone(phone);

        response.getWriter().write(JsonUtil.toJson(patient));
        response.getWriter().flush();
        response.getWriter().close();

    }

    protected void confirmNormalRegist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String data = request.getParameter("confirmData");
        Integer remain = WebUtil.parseInt(request.getParameter("remain"), 0);
        Map map = JsonUtil.convertJsonToMap(data);

        NormalRegistRecord normalRegistRecord = WebUtil.createBeanByMap(map, new NormalRegistRecord());
        System.out.println(normalRegistRecord);
        boolean flag = patientService.normalRecordIsExisted(
                normalRegistRecord.getPatientId(),
                normalRegistRecord.getNormalId());
        Map<String,String> resultMap=new HashMap<>();
        if (flag) {
            System.out.println("existed");
            resultMap.put("result","existed");
        } else {
            int num = patientService.addNormalRegistRecord(normalRegistRecord);
            int decreaseNum = patientService.decreaseNormalRemain(
                    normalRegistRecord.getNormalId(), remain - 1
            );
            System.out.println(num+","+decreaseNum);
            if (num != 1 || decreaseNum != 1) {
                System.out.println("fail");
                resultMap.put("result","fail");
            }else {
                System.out.println("success");
                resultMap.put("result","success");
            }
        }
        response.getWriter().write(JSON.toJSONString(resultMap));
        response.getWriter().flush();
        response.getWriter().close();

    }
}
