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
import com.health.entity.*;
import com.health.entity.response.Code;
import com.health.entity.response.ResponseResult;
import com.health.service.PatientService;
import com.health.service.impl.PatientServiceImpl;
import com.health.utils.*;
import org.apache.commons.io.IOUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@WebServlet(name = "PatientServlet", value = "/patient/patientServlet")
public class PatientServlet extends BaseServlet {
    private final PatientService patientService = new PatientServiceImpl();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }

    protected void getHospitalName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Hospital> hospitalList = patientService.getAllHospitalName();
        String data = JsonUtil.toJson(hospitalList);
        ResponseResult<List<Hospital>> result = new ResponseResult<>(Code.SUCCESS, hospitalList);
        super.response(response, JsonUtil.toJson(result));
    }

    protected void getNormalDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String hospitalId = request.getParameter("hospitalId");
        List<Department> departmentList = patientService.getDepartmentByHospital(hospitalId);
        ResponseResult<List<Department>> result = new ResponseResult<>(Code.SUCCESS, departmentList);

        super.response(response, JsonUtil.toJson(result));
    }

    protected void getHospitalById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String hospitalId = request.getParameter("hospitalId");
        List<Hospital> hospitalList = new ArrayList<>();
        if ("all".equals(hospitalId)) {
            hospitalList = patientService.getAllHospitalInfo();
        } else {
            Hospital hospital = patientService.getHospitalById(hospitalId);
            hospitalList.add(hospital);
        }

        ResponseResult<List<Hospital>> result = new ResponseResult<>(Code.SUCCESS, hospitalList);

        super.response(response, JsonUtil.toJson(result));
    }


    protected void getNormalDepartmentInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String hospitalId = request.getParameter("hospitalId");
        String departmentId = request.getParameter("departmentId");
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        List<NormalRegistInfo> resultList = patientService.getNormalRegistInfo(hospitalId, departmentId,
                time, date);
        System.out.println(resultList);
        ResponseResult<List<NormalRegistInfo>> result = new ResponseResult<>(Code.SUCCESS, resultList);

        super.response(response, JsonUtil.toJson(result));
    }

    protected void getHospitalAllNormalInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Hospital> hospitalList = patientService.getAllHospitalInfo();
        ResponseResult<List<Hospital>> result = new ResponseResult<>(Code.SUCCESS, hospitalList);

        super.response(response, JsonUtil.toJson(result));
    }

    protected void getHospitalForPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNo = WebUtil.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtil.parseInt(request.getParameter("pageSize"), 1);
        MyPage page = patientService.queryHospitalForPage(pageNo, pageSize);
        System.out.println(page);
        ResponseResult<MyPage> result = null;
        if (page != null) {
            result = new ResponseResult<>(Code.SUCCESS, page);
        } else {
            result = new ResponseResult<>(Code.FAIL, null);
        }
        super.response(response, JsonUtil.toJson(result));

    }

    protected void getPersonInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String phone = request.getParameter("patientPhone");
        Patient patient = patientService.getPatientByPhone(phone);

        super.response(response, JsonUtil.toJson(patient));

    }



    /**
     * 功能描述
     *
     * @param request
     * @param response
     * @return void
     * @author lmk
     * @Date 2021/12/27 16:45
     */
    protected void confirmNormalRegist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        String correctCode = (String) request.getSession().getAttribute("messageCode");
        ResponseResult<String> result = null;
        if (!messageCodeIsCorrected(code, correctCode, request, response)) {
            return;
        }

        String data = request.getParameter("confirmData");
        Integer remain = WebUtil.parseInt(request.getParameter("remain"), 0);
        if (remain <= 0) {
            result = new ResponseResult<>(Code.FAIL, "无号");
            super.response(response, JsonUtil.toJson(result));
            return;
        }
        Map map = JsonUtil.convertJsonToMap(data);

        NormalRegistRecord normalRegistRecord = WebUtil.createBeanByMap(map, new NormalRegistRecord());

        boolean flag = patientService.normalRecordIsExisted(
                normalRegistRecord.getPatientId(),
                normalRegistRecord.getNormalId());
        System.out.println(flag);
        if (flag) {
            result = new ResponseResult<>(Code.EXISTED, "existed");
        } else {
            int num = patientService.addNormalRegistRecord(normalRegistRecord);
            int decreaseNum = patientService.decreaseNormalRemain(
                    normalRegistRecord.getNormalId(), remain - 1
            );
            System.out.println(num + "," + decreaseNum);
            if (num != 1 || decreaseNum != 1) {
                System.out.println("fail");
                result = new ResponseResult<>(Code.FAIL, "fail");
            } else {
                System.out.println("success");
                result = new ResponseResult<>(Code.SUCCESS, "success");
            }
        }
        super.response(response, JsonUtil.toJson(result));
    }

    /**
     * 功能描述
     *
     * @param request
     * @param response
     * @return void
     * @author lmk
     * @Date 2021/12/27 16:46
     */
    protected void confirmExpertRegist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        String correctCode = (String) request.getSession().getAttribute("messageCode");
        ResponseResult<String> result = null;
        if (!messageCodeIsCorrected(code, correctCode, request, response)) {
            return;
        }


        String data = request.getParameter("confirmData");
        Integer remain = WebUtil.parseInt(request.getParameter("remain"), 0);
        if (remain <= 0) {
            result = new ResponseResult<>(Code.FAIL, "缺少号量！");
            super.response(response, JsonUtil.toJson(result));
            return;
        }
        Map map = JsonUtil.convertJsonToMap(data);

        ExpertRegistRecord registRecord = WebUtil.createBeanByMap(map, new ExpertRegistRecord());

        boolean flag = patientService.expertRecordIsExisted(
                registRecord.getPatientId(),
                registRecord.getExpertId());
        System.out.println(flag);
        if (flag) {
            result = new ResponseResult<>(Code.EXISTED, "existed");
        } else {
            int num = patientService.addExpertRegistInfo(registRecord);
            int decreaseNum = patientService.decreaseExpertRegistRecord(registRecord.getExpertId());
            if (num != 1 || decreaseNum != 1) {
                System.out.println("fail");
                result = new ResponseResult<>(Code.FAIL, "fail");
            } else {
                System.out.println("success");
                result = new ResponseResult<>(Code.SUCCESS, "success");
            }
        }
        super.response(response, JsonUtil.toJson(result));
    }

    /**
     * 功能描述
     *
     * @param request
     * @param response
     * @return void
     * @author lmk
     * @Date 2021/12/27 16:46
     */
    protected void getNormalRegistRecord(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String phone = request.getParameter("phone");
        int offset = WebUtil.parseInt(request.getParameter("offset"), 0);
        int limit = WebUtil.parseInt(request.getParameter("limit"), 5);
        PageHelper<NormalRegistRecord> pageHelper = patientService.getNormalRegistRecordByPhone(phone, offset, limit);

        super.response(response, JsonUtil.toJson(pageHelper));
    }

    protected void getExpertRegistRecord(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String phone = request.getParameter("phone");
        int offset = WebUtil.parseInt(request.getParameter("offset"), 0);
        int limit = WebUtil.parseInt(request.getParameter("limit"), 5);

        PageHelper<ExpertRegistRecord> pageHelper = patientService.getExpertRegistRecord(phone, offset, limit);
        super.response(response, JsonUtil.toJson(pageHelper));

    }

    /**
     * 功能描述
     *
     * @param request
     * @param response
     * @return void
     * @author lmk
     * @Date 2021/12/27 16:46
     */
    protected void deleteNormalRecord(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        int num = patientService.deleteNormalRegistRecord(WebUtil.parseInt(id, 0));

        Map<String, String> map = new HashMap<>();
        if (num >= 1) {
            map.put("result", "success");
        } else {
            map.put("result", "fail");
        }
        super.response(response, JsonUtil.toJson(map));

    }

    /**
     * 功能描述
     *
     * @param request
     * @param response
     * @return void
     * @author lmk
     * @Date 2021/12/27 16:46
     */
    protected void cancelNormalRegist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String patientId = request.getParameter("patientId");
        String normalId = request.getParameter("normalId");
        String id = request.getParameter("id");

        int num = patientService.cancelNormalRegist(WebUtil.parseInt(patientId, 0),
                WebUtil.parseInt(normalId, 0),
                WebUtil.parseInt(id, 0));

        Map<String, String> map = new HashMap<>();
        if (num >= 1) {
            map.put("result", "success");
        } else {
            map.put("result", "fail");
        }
        super.response(response, JsonUtil.toJson(map));

    }

    /**
     * 功能描述
     *
     * @param request
     * @param response
     * @return void
     * @author lmk
     * @Date 2021/12/27 16:46
     */
    protected void batchDeleteNormalRecord(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String values = request.getParameter("values");
        JSONArray jsonArray = JSONArray.parseArray(values);
        int num = 0;
        for (int i = 0; i < jsonArray.size(); i++) {
            num += patientService.deleteNormalRegistRecord((Integer) jsonArray.getJSONObject(i).get("id"));
        }
        ResponseResult<String> result;
        System.out.println(num);
        if (num == jsonArray.size()) {
            result = new ResponseResult<>(Code.SUCCESS, "删除成功");
        } else {
            result = new ResponseResult<>(Code.FAIL, "删除失败");
        }

        super.response(response, JsonUtil.toJson(result));

    }

    /**
     * 功能描述
     *
     * @param request
     * @param response
     * @return void
     * @author lmk
     * @Date 2021/12/27 16:46
     */
    protected void getExpertByDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String departmentId = request.getParameter("departmentId");
        List<Expert> expertList = patientService.getExpertInfoByDepartment(departmentId);
        ResponseResult<List<Expert>> result = null;
        if (expertList.size() != 0) {
            result = new ResponseResult<>(Code.SUCCESS, expertList);
        } else {
            result = new ResponseResult<>(Code.FAIL, null);
        }
        super.response(response, JsonUtil.toJson(result));
    }

    /**
     * 功能描述
     *
     * @param request
     * @param response
     * @return void
     * @author lmk
     * @Date 2021/12/27 16:46
     */
    protected void getExpertRegistInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String doctorId = request.getParameter("id");
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        List<ExpertRegistInfo> expertRegistInfoList = patientService.getExpertRegistInfo(doctorId, date, time);
        ResponseResult<List<ExpertRegistInfo>> result = null;
        if (expertRegistInfoList.size() != 0) {
            result = new ResponseResult<>(Code.SUCCESS, expertRegistInfoList);
        } else {
            result = new ResponseResult<>(Code.FAIL, null);
        }
        super.response(response, JsonUtil.toJson(result));
    }

    protected void updatePersonInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Patient patient = WebUtil.createBeanByMap(request.getParameterMap(), new Patient());
        int num = patientService.updatePatientInfo(patient.getId(), patient);
        ResponseResult<String> result=null;
        if (num == 1) {
            result=new ResponseResult<>(Code.SUCCESS,"更新成功");
        }else {
            result=new ResponseResult<>(Code.FAIL,"操作失败");
        }
        super.response(response, JsonUtil.toJson(result));
    }

}
