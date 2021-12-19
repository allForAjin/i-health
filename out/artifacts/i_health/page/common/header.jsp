<%--
  Created by IntelliJ IDEA.
  User: 15485
  Date: 2021/11/15
  Time: 11:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":"
            + request.getServerPort()
            + request.getContextPath() + "/";
    application.setAttribute("basePath",basePath);
%>
<%--<meta http-equiv="Expires" CONTENT="0">--%>
<%--<meta http-equiv="Cache-Control" CONTENT="no-cache">--%>
<%--<meta http-equiv="Pragma" CONTENT="no-cache">--%>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<base href="${applicationScope.basePath}">
<link rel="stylesheet" href="static/css/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="static/css/bootstrap/bootstrap-table.min.css">
<link rel="stylesheet" href="static/css/bootstrap/bootstrap-select.min.css">
<link rel="stylesheet" href="static/css/icon/iconfont.css">



