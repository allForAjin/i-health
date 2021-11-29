<%--
  Created by IntelliJ IDEA.
  User: 15485
  Date: 2021/11/15
  Time: 21:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="welcome">
    <span class="user">欢迎您！用户&nbsp;&nbsp;</span>
    <span class="user" id="user-phone">${sessionScope.user.username}</span>
    <a href="javascript:void(0)" id="logout">注销</a>
</div>

