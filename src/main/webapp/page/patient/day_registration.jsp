<%--
  Created by IntelliJ IDEA.
  User: 15485
  Date: 2021/11/23
  Time: 18:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <%@include file="/page/common/header.jsp" %>
        <title>当日挂号</title>
        <link rel="stylesheet" href="static/css/style.css">
        <link rel="stylesheet" href="static/css/hospital-info.css">
    </head>
    <body>
        <div class="wrap bge6">
            <div class="container-fluid">
                <!-- 左侧导航栏 -->
                <div class="left-nav">
                    <nav class="navbar">
                        <div class="mytitle">
                            <h3><span>i-health问诊系统</span></h3>
                        </div>
                        <ul class="nav nav-pills nav-stacked ul-menu">
                            <li><a href="page/patient/patient_site.jsp">首页</a></li>
                            <li class="active"><a href="page/patient/day_registration.jsp">当日挂号</a></li>
                            <li><a href="page/patient/regist_record.jsp">预约-挂号记录</a></li>
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
                                <li><a href="page/patient/patient_site.jsp">首页</a></li>
                                <li class="active">当日挂号</li>
                            </ol>
                            <%@include file="/page/common/welcome.jsp" %>
                        </div>
                        
                        <div class="show-box ">
                            <div class="box-header">
                                <div class="regist-title">
                                    <h3>当日挂号</h3>
                                </div>
                                <div class="regist-query">
                                    <div class="query-selector">
                                        <select class="selectpicker show-tick form-control"
                                                data-live-search="true" data-live-search-placeholder="搜索医院"
                                                id="hospital">
                                        </select>
                                    </div>
                                    <div class="query-selector">
                                        <select class="selectpicker show-tick form-control"
                                                id="all-time">
                                            <option value="all">全部时间段</option>
                                            <option value="morning">上午</option>
                                            <option value="afternoon">下午</option>
                                        </select>
                                    </div>
                                    
                                    
                                    <button type="button" class="btn btn-primary" id="query-btn">查询</button>
                                    <button type="button" class="btn btn-success" id="all-btn">显示全部</button>
                                </div>
                            </div>
                            
                            
                            <%--<div class="table-div">--%>
                            <%--<table class="table table-hover table-no-bordered" id="registration-table">--%>
                            <%--</table>--%>
                            
                            <%--</div>--%>
                            
                            <div class="list-container">
                                <div class="list-item">
                                    <div class="hospital-item">
                                            <div class="hospital-img">
                                                <img src="static/img/hospital-1.jpg" alt="">
                                            </div>
    
                                            <div class="info">
                                                <div class="item-title">
                                                    <h3><a href="">上海市第六人民医院东院</a></h3>
                                                </div>
        
                                                <div class="hospital-description">
                                                    <span>uiruqioeuiqwoeyuqioeiqoeq</span>
                                                </div>
                                            </div>
                                        
                                    </div>
                                </div>
    
                                <div class="list-item">
                                    <div class="hospital-item">
                                        <div class="hospital-img">
                                            <img src="static/img/hospital-1.jpg" alt="">
                                        </div>
                                        <div class="info">
                                            <div class="item-title">
                                                <h3><a href="">上海市第六人民医院东院</a></h3>
                                            </div>
                                            <div class="hospital-description">
                                            
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="bottom">
                        </div>
                    </div>
                </div>
            
            
            </div>
        </div>
        
        <div class="modal fade" id="regist-modal" tabindex="-1" role="dialog" aria-labelledby="registModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                        <h4 class="modal-title" id="myModalLabel">预约信息确认</h4>
                    </div>
                    
                    <div class="scroll">
                        <div class="modal-body">
                            <form id="normal-form">
                                <div class="hospital-info">
                                    <input type="hidden" name="remain" id="remain">
                                    <label for="hospital-name" class="control-label">医院名称:</label>
                                    <input type="text" class="form-control" id="hospital-name" disabled>
                                    
                                    <label for="department-name" class="control-label">挂号科室:</label>
                                    <input type="text" class="form-control" id="department-name" disabled>
                                    
                                    <label for="regist-date" class="control-label">挂号日期:</label>
                                    <input type="text" class="form-control" id="regist-date" disabled>
                                    
                                    <label for="time" class="control-label">时间段:</label>
                                    <input type="text" class="form-control" id="time" disabled>
                                    
                                    <label for="normal-cost" class="control-label">挂号费:</label>
                                    <input type="text" class="form-control" id="normal-cost" disabled>
                                    
                                    <div class="patient-title">
                                        <span>患者信息</span>
                                    </div>
                                </div>
                                
                                <div class="patient-info">
                                    <label for="patient-name" class="control-label">患者姓名:</label>
                                    <input type="text" class="form-control" id="patient-name" disabled>
                                    
                                    <label for="patient-age" class="control-label">年龄:</label>
                                    <input type="text" class="form-control" id="patient-age" disabled>
                                    
                                    <label for="patient-sex" class="control-label">性别:</label>
                                    <input type="text" class="form-control" id="patient-sex" disabled>
                                    
                                    <label for="patient-phone" class="control-label">手机号:</label>
                                    <input type="text" class="form-control" id="patient-phone" disabled>
                                    <label for="identify" class="control-label">验证码：</label>
                                    <div class="identify-div">
                                        <input type="text" class="form-control" id="identify">
                                        <button type="button" class="btn btn-primary" id="get-identify">获取验证码</button>
                                    </div>
                                    
                                </div>
                            </form>
                        </div>
                        
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            <button type="button" class="btn btn-primary" id="confirm-regist">确认挂号</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    
    </body>
    <%@include file="/page/common/foot.jsp" %>
    <%@include file="/page/common/logout.jsp" %>
    <script src="static/js/patient/patient.js"></script>

</html>
