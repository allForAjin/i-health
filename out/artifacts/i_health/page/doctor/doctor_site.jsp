<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>首页</title>
    <%@include file="/page/common/header.jsp"%>
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
                        <li class="active"><a href="#">首页</a></li>
                        <li><a href="#">导航1</a></li>
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
                            <li><a href="#">首页</a></li>
                            <li class="active">导航1</li>
                        </ol>
                        <%@include file="/page/common/welcome.jsp"%>
                    </div>

                    <div class="show-box">

                    </div>
                </div>
            </div>


        </div>
    </div>


</body>
    <%@include file="/page/common/foot.jsp"%>
    <%@include file="/page/common/logout.jsp"%>
</html>