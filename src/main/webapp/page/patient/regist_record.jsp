<%--
  Created by IntelliJ IDEA.
  User: 15485
  Date: 2021/11/29
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <%@include file="/page/common/header.jsp" %>
        <title>预约/挂号记录</title>
        <link rel="stylesheet" href="static/css/style.css">
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
                            <li><a href="page/patient/day_registration.jsp">当日挂号</a></li>
                            <li class="active"><a href="page/patient/regist_record.jsp">预约-挂号记录</a></li>
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
                                <li class="active">预约-挂号记录</li>
                            </ol>
                            <%@include file="/page/common/welcome.jsp" %>
                        </div>
                        
                        <div class="show-box ">
                            <div class="box-header">
                                <div class="regist-title">
                                    <h3>挂号记录</h3>
                                </div>
                                <div class="regist-query">
                                    <div class="btn-div">
                                        <button type="button" class="btn btn-danger" id="delete-btn">批量删除</button>
                                    </div>
                                    <%--<div class="query-selector">--%>
                                    <%--    <select class="selectpicker show-tick form-control"--%>
                                    <%--            data-live-search="true" data-live-search-placeholder="搜索"--%>
                                    <%--            id="hospital">--%>
                                    <%--    </select>--%>
                                    <%--</div>--%>
                                    
                                    
                                    <%--<button type="button" class="btn btn-primary" id="query-btn">查询</button>--%>
                                    <%--<button type="button" class="btn btn-success" id="all-btn">显示全部</button>--%>
                                </div>
                            </div>
                            
                            
                            <div class="table-div">
                                <table class="table table-hover table-no-bordered" id="registrecord-table">
                                </table>
                            
                            </div>
                        </div>
                        <div class="bottom">
                        </div>
                    </div>
                </div>
            
            
            </div>
        </div>
        
        <div class="modal fade" id="pay-modal" tabindex="-1" role="dialog" aria-labelledby="payModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                        <h4 class="modal-title" id="myModalLabel">订单信息确认</h4>
                    </div>
                    <form id="pay-form" method="post" action="pay/goAlipay">
                        <input type="hidden" name="action" value="normalRegistPay">
                        <div class="scroll">
                            <div class="modal-body">
                                <input type="hidden" name="remain" id="remain">
                                
                                <label for="id" class="control-label">订单号:</label>
                                <input type="text" class="form-control" id="id" name="id" readonly>
                                <label for="hospital-name" class="control-label">医院名称:</label>
                                
                                <input type="text" class="form-control" id="hospital-name" name="hospital" readonly>
    
                                <label for="department-name" class="control-label">挂号科室:</label>
                                <input type="text" class="form-control" id="department-name" name="department" readonly>
    
                                <label for="regist-date" class="control-label">挂号日期:</label>
                                <input type="text" class="form-control" id="regist-date" name="registDate" readonly>
    
                                <label for="time" class="control-label">时间段:</label>
                                <input type="text" class="form-control" id="time" name="time" readonly>
                                
                                <label for="patientName" class="control-label">姓名:</label>
                                <input type="text" class="form-control" id="patientName" name="patientName" readonly>
                                
                                <label for="phone" class="control-label">手机号:</label>
                                <input type="text" class="form-control" id="phone" name="phone" readonly>
                                
                                <label for="cost" class="control-label">支付金额:</label>
                                <input type="text" class="form-control" id="cost" name="cost" readonly>
                            
                            </div>
                            
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                <button type="submit" class="btn btn-primary" id="conform-pay">提交订单</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    
        <div class="modal fade" id="refund-modal" tabindex="-1" role="dialog" aria-labelledby="refundModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                        <h4 class="modal-title" id="refundModalLabel">退款信息</h4>
                    </div>
                    <form id="refund-form" method="post" action="pay/goAlipay">
                        <input type="hidden" name="action" value="normalRegistPayRefund">
                        <div class="scroll">
                            <div class="modal-body">
                                <label for="trade-id" class="control-label">订单号:</label>
                                <input type="text" class="form-control" id="trade-id" name="id" readonly>
    
                                <label for="hospital-name-refund" class="control-label">医院名称:</label>
                                <input type="text" class="form-control" id="hospital-name-refund" name="hospital" readonly>
    
                                <label for="department-name-refund" class="control-label">挂号科室:</label>
                                <input type="text" class="form-control" id="department-name-refund" name="department" readonly>
    
                                <label for="regist-date-refund" class="control-label">挂号日期:</label>
                                <input type="text" class="form-control" id="regist-date-refund" name="registDate" readonly>
    
                                <label for="time-refund" class="control-label">时间段:</label>
                                <input type="text" class="form-control" id="time-refund" name="time" readonly>
    
                                <label for="patientName-refund" class="control-label">姓名:</label>
                                <input type="text" class="form-control" id="patientName-refund" name="patientName" readonly>
                            
                                <label for="phone-refund" class="control-label">手机号:</label>
                                <input type="text" class="form-control" id="phone-refund" name="phone" readonly>
                            
                                <label for="cost-refund" class="control-label">退款金额:</label>
                                <input type="text" class="form-control" id="cost-refund" name="cost" readonly>
                        
                            </div>
                        
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                <button type="button" class="btn btn-primary" id="conform-refund">退款</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
    <%@include file="/page/common/foot.jsp" %>
    <%@include file="/page/common/logout.jsp" %>
    <%@include file="/page/common/patient_common.jsp" %>
    <script src="static/js/patient/record.js"></script>
</html>
