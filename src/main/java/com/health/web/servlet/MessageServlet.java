package com.health.web.servlet;

import com.alibaba.fastjson.JSONObject;
import com.health.entity.response.Code;
import com.health.entity.response.ResponseResult;
import com.health.utils.JsonUtil;
import com.health.utils.MessageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName MessageServlet.java
 * @Description TODO
 * @createTime 2021-12-31 14:11:02
 */
@WebServlet(name = "MessageServlet", value = "/message")
public class MessageServlet extends BaseServlet{
    protected final Timer timer = new Timer();

    protected void getMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String phone = request.getParameter("phone");
        String code = MessageUtil.createCode();
        String result = MessageUtil.sendCode(phone, code);

        System.out.println(result);
        Map map = JsonUtil.convertJsonToMap(result);
        HttpSession session = request.getSession();
        session.setAttribute("messageCode", code);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                session.removeAttribute("messageCode");
                System.out.println("验证码session删除成功！");
                timer.cancel();
            }

        }, 1000 * 60 * 5);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("resultCode", map.get("code"));
        jsonObject.put("messageCode", code);

        response(response, JSONObject.toJSONString(jsonObject));
    }


}
