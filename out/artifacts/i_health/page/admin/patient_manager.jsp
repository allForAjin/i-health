<%--
  Created by IntelliJ IDEA.
  User: 15485
  Date: 2021/12/8
  Time: 19:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <%@include file="/page/common/header.jsp" %>
        <title>患者信息管理</title>
        <link rel="stylesheet" href="static/css/style.css">
        <link rel="stylesheet" href="static/css/bootstrap/bootstrap-datetimepicker.css">
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
                            <li><a href="page/admin/visitor.jsp">访客记录</a></li>
                            <li class="active"><a href="page/admin/patient_manager.jsp">患者信息管理</a></li>
                            <li><a href="page/admin/normal_manager.jsp">普通门诊管理</a></li>
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
                                <li class="active">患者信息管理</li>
                            </ol>
                            <%@include file="/page/common/welcome.jsp" %>
                        </div>
                        
                        <div class="show-box ">
                            <div class="box-header">
                                <div class="regist-title">
                                    <h3>患者信息</h3>
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
                                <table class="table table-hover table-no-bordered" id="patientManage-table">
                                </table>
                            
                            </div>
                            <div class="btn-div">
                                <div class="btn_group btn_no_click" id="delete-patient">
                                    <i class="iconfont btn_icon">&#xe6ac;</i>
                                    <span class="btn_title">批量删除</span>
                                </div>
                                <div class="btn_group btn_hover" data-toggle="modal" data-target="#add-modal">
                                    <i class="iconfont btn_icon">&#xe624;</i>
                                    <span class="btn_title">添加用户</span>
                                </div>
                            </div>
                        </div>
                        <div class="bottom">
                        </div>
                    </div>
                </div>
            
            
            </div>
        </div>
    
        <%--更新用户modal--%>
        <div class="modal fade" id="update-modal" tabindex="-1" role="dialog" aria-labelledby="updateModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                        <h4 class="modal-title" id="updateModalLabel">患者信息</h4>
                    </div>
                        <div class="scroll">
                            <div class="modal-body">
                                <label for="id-update" class="control-label">患者id:</label>
                                <input type="text" class="form-control" id="id-update" name="id" readonly>
                                
                                <label for="name-update" class="control-label">姓名:</label>
                                <input type="text" class="form-control" id="name-update" name="name">
                                <br>
    
                                <label for="phone-update" class="control-label">手机号（账号）:</label>
                                <input type="text" class="form-control" id="phone-update" name="phone">
                                <br>
                                
                                <label class="control-label">性别:</label>
                                <br>
                                <div class="radio-inline">
                                    <input type="radio" name="sex-update" id="male-update" value="male">男
                                </div>
                                <div class="radio-inline">
                                    <input type="radio" name="sex-update" id="female-update" value="female">女
                                </div>
                                <br>
                                <br>
    
                                <label for="patient-birth" class="control-label">出生日期:</label>
                                <div class="birth-picker" id="datetimepicker">
                                    <input type="text" class="form-control" name="birth" id="patient-birth" placeholder="点击选择日期" readonly>
                                </div>
                                <br>

                        
                            </div>
                        
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                <button type="button" class="btn btn-primary" id="confirm-update">确认</button>
                            </div>
                        </div>
                </div>
            </div>
        </div>
    
        <%--添加用户modal--%>
        <div class="modal fade" id="add-modal" tabindex="-1" role="dialog" aria-labelledby="addModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                        <h4 class="modal-title" id="addModalLabel">患者信息</h4>
                    </div>
                    <div class="scroll">
                        <div class="modal-body">
                        
                            <label for="name-add" class="control-label">姓名:</label>
                            <input type="text" class="form-control" id="name-add" name="name">
                            <br>
                        
                            <label for="phone-add" class="control-label">手机号（账号）:</label>
                            <input type="text" class="form-control" id="phone-add" name="phone">
                            <br>
                        
                            <label for="male-add" class="control-label">性别:</label>
                            <br>
                            <div class="radio-inline">
                                <input type="radio" name="sex-add" id="male-add" value="male">男
                            </div>
                            <div class="radio-inline">
                                <input type="radio" name="sex-add" id="female-add" value="female">女
                            </div>
                            <br>
                            <br>
                        
                            <label for="patient_birth_add" class="control-label">出生日期:</label>
                            <div class="birth-picker" id="datetimepicker-add">
                                <input type="text" class="form-control" name="birth" id="patient_birth_add" placeholder="点击选择日期" readonly>
                                </button>
                            </div>
                            <br>
                    
                    
                        </div>
                    
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            <button type="button" class="btn btn-primary" id="confirm-add">确认</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <%@include file="/page/common/foot.jsp" %>
    <%@include file="/page/common/admin_common.jsp" %>
    <script src="static/js/admin/admin.js"></script>
    <script src="static/js/bootstrap/bootstrap-datetimepicker.js"></script>
    <script src="static/js/bootstrap/bootstrap-datetimepicker.zh-CN.js"></script>
    <script src="static/js/bootstrap/bootstrap-datetimepicker.fr.js"></script>
</html>
