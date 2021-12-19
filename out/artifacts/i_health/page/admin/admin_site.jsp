<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@include file="/page/common/header.jsp"%>
    <title>首页</title>
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
                        <li class="active"><a href="page/admin/admin_site.jsp">首页</a></li>
                        <li><a href="page/admin/visitor.jsp">访客记录</a></li>
                        <li><a href="page/admin/patient_manager.jsp">患者信息管理</a></li>
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
                        </ol>
                        <%@include file="/page/common/welcome.jsp"%>
                    </div>

                    <div class="admin-view">
                        <div class="admin_view_title">
                            概览
                        </div>
                        <div class="all-info">
                            <ul class="view-text">
                                <li class="li-box col-xs-4 col-sm-4 col-md-4 col-lg-3">
                                    <p class="box-name">
                                        用户总数
                                    </p>
                                    <div class="li-val">
                                        <a href="" class="li-link" id="total">10</a>
                                    </div>
                                </li>
                                <li class="li-box col-xs-4 col-sm-4 col-md-4 col-lg-3">
                                    <p class="box-name">
                                        在线用户
                                    </p>
                                    <div class="li-val">
                                        <a href="" class="li-link" id="online">10</a>
                                    </div>
                                </li>
                                <li class="li-box col-xs-4 col-sm-4 col-md-4 col-lg-3"></li>
                                <li class="li-box col-xs-4 col-sm-4 col-md-4 col-lg-3"></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>


        </div>
    </div>


</body>
    <%@include file="/page/common/foot.jsp"%>
    <%@include file="/page/common/logout.jsp"%>
    <%@include file="/page/common/admin_common.jsp" %>
</html>