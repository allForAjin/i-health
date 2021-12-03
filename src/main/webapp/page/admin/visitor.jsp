<%--
  Created by IntelliJ IDEA.
  User: 15485
  Date: 2021/11/20
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <%@include file="/page/common/header.jsp" %>
        <title>访客记录</title>
        <link rel="stylesheet" href="static/css/style.css">
    </head>
    <body>
        <div class="wrap">
            <div class="container-fluid">
                <!-- 左侧导航栏 -->
                <div class="left-nav">
                    <nav class="navbar">
                        <div class="mytitle">
                            <h3><span>i-health问诊系统</span></h3>
                        </div>
                        <ul class="nav nav-pills nav-stacked ul-menu">
                            <li><a href="page/admin/admin_site.jsp">首页</a></li>
                            <li class="active"><a href="page/admin/visitor.jsp">访客记录</a></li>
                            <li><a href="#">导航2</a></li>
                            <li><a href="#">导航3</a></li>
                            <li><a href="#">导航4</a></li>
                        </ul>
                    </nav>
                </div>
                
                <!-- 右侧主界面 -->
                <div class="main-content">
                    <div class="container-fluid">
                        <div class="index-pos-box nav-path">
                            <ol class="breadcrumb ol-path">
                                <li><a href="page/admin/admin_site.jsp">首页</a></li>
                                <li class="active">访客记录</li>
                            </ol>
                            <%@include file="/page/common/welcome.jsp" %>
                        </div>
                        
                        <div class="show-box">
                            <div class="box-header">
                                <div class="table-title">
                                    <h3>访客记录查看</h3>
                                </div>
                                <div class="visitor-query">
                                    <span>用户类型：</span>
                                    <select class="selectpicker" id="user-type">
                                        <option value="all">全部用户</option>
                                        <option value="patient">患者</option>
                                        <option value="doctor">医生</option>
                                        <option value="admin">管理员</option>
                                    </select>
        
                                    <span>操作类型：</span>
                                    <select class="selectpicker" id="user-operate">
                                        <option value="all">全部操作</option>
                                        <option value="login">登录</option>
                                        <option value="logout">注销</option>
                                        <option value="regist">注册</option>
                                    </select>
    
                                    <input type="text" class="form-control" id="username-query" placeholder="用户名搜索">
                                    <button type="button" class="btn btn-primary" id="query-btn">查询</button>
                                    <button type="button" class="btn btn-success" id="all-btn">显示全部</button>
                                </div>
                            </div>
                            
                            
                                <table class="table table-hover table-no-bordered" id="visitor-table">
                                </table>
                            
                        </div>
                        <div class="bottom">
                        </div>
                    </div>
                </div>
            
            
            </div>
        </div>
    
    
    </body>
    <%@include file="/page/common/foot.jsp" %>
    <%@include file="/page/common/logout.jsp" %>
    <script src="static/js/admin/visitor.js"></script>
</html>
