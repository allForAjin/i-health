<%--
  Created by IntelliJ IDEA.
  User: 15485
  Date: 2021/12/19
  Time: 20:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <%@include file="/page/common/header.jsp" %>
        <title>普通门诊管理</title>
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
                            <li><a href="page/admin/visitor.jsp">访客记录</a></li>
                            <li><a href="page/admin/patient_manager.jsp">患者信息管理</a></li>
                            <li class="active"><a href="page/admin/normal_manager.jsp">普通门诊管理</a></li>
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
                                <li>普通门诊管理</li>
                            </ol>
                            <%@include file="/page/common/welcome.jsp" %>
                        </div>
                        
                        <div class="show-box">
                            <div class="box-header">
                                <div class="regist-title">
                                    <h3>普通门诊号源信息管理</h3>
                                </div>
                                <div class="regist-query">
                                    
                                    <div class="name-search">
                                        <input type="text" class="form-control" id="username-query" placeholder="用户名搜索">
                                        <button type="button" class="btn btn-primary" id="query-btn">查询</button>
                                        <button type="button" class="btn btn-success" id="all-btn">显示全部</button>
                                    </div>
                                
                                </div>
                            </div>
                            
                            <div class="table-div">
                                <table class="table table-hover table-no-bordered" id="normalManage-table">
                                </table>
                            
                            </div>
                            <div class="btn-div">
                                <div class="btn_group btn_no_click" id="delete-normal">
                                    <i class="iconfont btn_icon">&#xe6ac;</i>
                                    <span class="btn_title">批量删除</span>
                                </div>
                                
                            </div>
                        </div>
                        <div class="bottom">
                        </div>
                    </div>
                </div>
            </div>
        
        
        </div>
        </div>
    </body>
    <%@include file="/page/common/foot.jsp" %>
    <%@include file="/page/common/admin_common.jsp" %>
    <script src="static/js/admin/normal.js"></script>
</html>
