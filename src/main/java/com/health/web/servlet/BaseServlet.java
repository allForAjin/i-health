package com.health.web.servlet; /**
 * @ClassName ${NAME}.java
 * @author lmk
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021-11-15 14:17:25
 */

import com.alibaba.fastjson.JSONObject;
import com.health.entity.response.Code;
import com.health.entity.response.ResponseResult;
import com.health.utils.JsonUtil;
import com.health.utils.MessageUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        response.setHeader("Cache-Control", "no-store");
        response.setContentType("text/html; charset=utf-8");
        try {
            Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void response(HttpServletResponse response,String result) throws ServletException, IOException{
        response.getWriter().write(result);
        response.getWriter().flush();
        response.getWriter().close();
    }

    /**
     * 判断验证码是否正确
     *
     * @param code        前端回传验证码
     * @param correctCode 正确验证码
     * @param request     请求
     * @param response    响应
     * @return boolean
     * @author lmk
     * @Date 2021/12/27 16:19
     */
    protected boolean messageCodeIsCorrected(String code, String correctCode, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResponseResult<String> result = null;
        System.out.println("code:" + code + ",correctCode:" + correctCode);
        if (correctCode != null) {
            if (!correctCode.equals(code)) {
                result = new ResponseResult<>(Code.FAIL, "验证码错误！");
                response(response, JsonUtil.toJson(result));
                return false;
            } else {
                request.getSession().removeAttribute("messageCode");
                return true;
            }
        } else {
            result = new ResponseResult<>(Code.OVER, "验证码已过期！");
            response(response, JsonUtil.toJson(result));
            return false;
        }
    }


}
