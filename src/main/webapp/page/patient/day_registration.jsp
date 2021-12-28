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
                            <li class="active"><a href="page/patient/day_registration.jsp">预约挂号</a></li>
                            <li><a href="page/patient/regist_record.jsp">预约记录</a></li>
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
                                <li class="active">预约挂号</li>
                            </ol>
                            <%@include file="/page/common/welcome.jsp" %>
                        </div>
                        
                        <div class="show-box hospital-box" style="display: none;">
                            <div class="box-header">
                                <div class="regist-title">
                                    <h3>预约挂号</h3>
                                </div>
                                <div class="regist-query">
                                    <span>搜索医院：</span>
                                    <div class="query-selector">
                                        <select class="selectpicker show-tick form-control"
                                                data-live-search="true" data-live-search-placeholder="搜索医院"
                                                id="hospital">
                                        </select>
                                    </div>
                                    <div class="query-selector">
                                    </div>
                                    
                                    
                                    <button type="button" class="btn btn-primary" id="query-btn">查询</button>
                                    <button type="button" class="btn btn-success" id="all-btn">显示全部</button>
                                </div>
                            </div>
                            
                            
                            <div class="list-container">
                            </div>
                            
                            <div class="page-container" style="display: none;">
                                <div class="page-inner">
                                    <a href="javascript:void(0);" class="big-item" id="pre_page">上一页</a>
                                    <a href="javascript:void(0);" class="hospital-page-link"><span
                                            class="page-no picked">1</span></a>
                                    <a href="javascript:void(0);" class="hospital-page-link"><span
                                            class="page-no">2</span></a>
                                    <a href="javascript:void(0);" class="hospital-page-link"><span
                                            class="page-no">3</span></a>
                                    <a href="javascript:void(0);" class="big-item" id="next_page">下一页</a>
                                </div>
                                <%--<div class="page-info">--%>
                                <%--    <p>第<span id="hospital-pageNo">1</span>页，共<span id="hospital-totalPage">5</span>页</p>--%>
                                <%--</div>--%>
                            </div>
                        </div>
                        
                        <div class="hospital-header">
                            <div class="hospital-img regist-img">
                            </div>
                            <div class="regist-title" id="regist-header">
                                <div class="info">
                                    <h3 id="department-title"></h3>
                                
                                </div>
                            
                            </div>
                        </div>
                        
                        
                        <div class="show-box" id="department-box">
                            <div class="choose-department">
                                <h3>选择科室：</h3>
                                <div class="department-nameList">
                                </div>
                                <h3>普通门诊</h3>
                                
                                <table id="department-table" cellpadding="0" cellspacing="0">
                                    <thead>
                                    <tr>
                                        <th class="f">&nbsp;</th>
                                        <th class="h"></th>
                                        <th class="h"></th>
                                        <th class="h"></th>
                                        <th class="h"></th>
                                        <th class="h"></th>
                                        <th class="h"></th>
                                        <th class="h"></th>
                                        <th class="h"></th>
                                    </tr>
                                    </thead>
                                    
                                    <tbody>
                                    <tr class="r">
                                        <td class="first"><span>上</span></td>
                                        <td class="d"></td>
                                        <td class="d"></td>
                                        <td class="d"></td>
                                        <td class="d"></td>
                                        <td class="d"></td>
                                        <td class="d"></td>
                                        <td class="d"></td>
                                        <td class="d"></td>
                                    
                                    </tr>
                                    
                                    <tr class="r">
                                        <td class="first"><span>下</span></td>
                                        <td class="d"></td>
                                        <td class="d"></td>
                                        <td class="d"></td>
                                        <td class="d"></td>
                                        <td class="d"></td>
                                        <td class="d"></td>
                                        <td class="d"></td>
                                        <td class="d"></td>
                                    </tr>
                                    
                                    </tbody>
                                </table>
                                <div class="expert">
                                    <div class="regist-title">
                                        <h3>专家门诊</h3>
                                    </div>
                                    <div class="list-container" id="expert_list">
                                        <div class="list-item department-list">
                                            <div class="department_list_item">
                                                <div class="department-img">
                                                    <img src="static/img/doctor/doctor-1.jpg">
                                                </div>
                                                <div class="info">
                                                    <div class="item-title"><a href="javascript:void(0);"
                                                                               class="expert_name">李四</a></div>
                                                    <div class="item-info expert_profession"><span>主任医师</span></div>
                                                    <div class="item-info expert_profession"><span class="appointment">预约量：<span
                                                            class="num">10</span></span></div>
                                                </div>
                                                <button class="btn btn-primary choose-expert">选择预约</button>
                                            </div>
                                        </div>
                                        
                                        
                                        <div class="list-item department-list">
                                            <div class="department_list_item">
                                                <div class="department-img">
                                                    <img src="static/img/doctor/doctor-1.jpg">
                                                </div>
                                                <div class="info">
                                                    <div class="item-title"><a href="javascript:void(0);"
                                                                               class="expert_name">李四</a></div>
                                                    <div class="item-info expert_profession"><span>主任医师</span></div>
                                                    <div class="item-info expert_profession"><span class="appointment">预约量：<span
                                                            class="num">10</span></span></div>
                                                </div>
                                                <button class="btn btn-primary choose-expert">选择预约</button>
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
                                <div class="top-info">
                                    <input type="hidden" name="remain" id="remain">
                                    <input type="hidden" name="normalId" id="normalId">
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
                                    
                                    <div class="bottom-title">
                                        <span>患者信息</span>
                                    </div>
                                </div>
                                
                                <div class="bottom-info">
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
        
        <div class="modal fade" id="expertInfo-modal" tabindex="-1" role="dialog"
             aria-labelledby="expertInfoModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                        <h4 class="modal-title" id="expertInfoModalLabel">专家挂号信息查询</h4>
                    </div>
                    
                    <div class="scroll">
                        <div class="modal-body">
                            <div class="department-info">
                                <input type="hidden" name="expertId" id="expertId">
                                <label for="departmentName" class="control-label">挂号科室:</label>
                                <input type="text" class="form-control modal-input" id="departmentName" disabled>
                                
                                
                                <label for="expertName" class="control-label">挂号专家:</label>
                                <input type="text" class="form-control  modal-input" id="expertName" disabled>
                                
                                <div class="form-group border">
                                    <label>选择挂号日期：</label>
                                    <div class="date-container  modal-input">
                                        <div class="date-picker">
                                            <div class='input-group date' id='regist-datetimepicker'>
                                                <input type='text' class="form-control" id="registDate" readonly/>
                                                <span class="input-group-addon bgf">
                                                <span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                            </div>
                                        </div>
                                        <div class="registTime-selector modal-input">
                                            <select class="selectpicker show-tick form-control"
                                                    id="registTime">
                                                <option value="morning">上午</option>
                                                <option value="afternoon">下午</option>
                                            </select>
                                        </div>
                                    </div>
                                    <button type="button" class="btn btn-primary" id="registTime-query">查询号量</button>
                                    <div></div>
                                </div>
                                
                                
                                
                                
                                <label for="remain-num" class="control-label block-label">剩余号量:</label>
                                <input type="text" class="form-control modal-input" id="remain-num" disabled>
                                <label for="expertCost" class="control-label">挂号费(元):</label>
                                <input type="text" class="form-control modal-input" id="expertCost" disabled>
                            
                            
                            </div>
                        </div>
                        
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            <button type="button" class="btn btn-primary" id="choose-regist">选择挂号</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        
    
        <div class="modal fade" id="expert-modal" tabindex="-1" role="dialog" aria-labelledby="expertModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                        <h4 class="modal-title" id="expertModalLabel">预约信息确认</h4>
                    </div>
                
                    <div class="scroll">
                        <div class="modal-body">
                            <form id="expert-form">
                                <div class="top-info">
                                    <input type="hidden" name="id" id="expertRegistId">
                                    <input type="hidden" name="remain" id="expert_remain">
                                    <label for="hospital_name_expert" class="control-label">医院名称:</label>
                                    <input type="text" class="form-control modal-input" name="hospital" id="hospital_name_expert" disabled>
                                
                                    <label for="department_name_expert" class="control-label">挂号科室:</label>
                                    <input type="text" class="form-control modal-input" name="department" id="department_name_expert" disabled>
    
                                    <label for="expert_name" class="control-label">挂号专家:</label>
                                    <input type="text" class="form-control modal-input" name="doctorName" id="expert_name" disabled>
                                
                                    <label for="regist_date_expert" class="control-label">挂号日期:</label>
                                    <input type="text" class="form-control modal-input" name="registDate" id="regist_date_expert" disabled>
                                
                                    <label for="time_expert" class="control-label">时间段:</label>
                                    <input type="text" class="form-control modal-input" name="time" id="time_expert" disabled>
                                
                                    <label for="expert-cost" class="control-label">挂号费(元):</label>
                                    <input type="text" class="form-control modal-input" name="cost" id="expert-cost" disabled>
                                
                                    <div class="bottom-title">
                                        <span>患者信息</span>
                                    </div>
                                </div>
                            
                                <div class="bottom-info">
                                    <label for="patient_name_expert" class="control-label">患者姓名:</label>
                                    <input type="text" class="form-control modal-input" name="patientName" id="patient_name_expert" disabled>
                                
                                    <label for="patient_age_expert" class="control-label">年龄:</label>
                                    <input type="text" class="form-control modal-input" name="age" id="patient_age_expert" disabled>
                                
                                    <label for="patient_sex_expert" class="control-label">性别:</label>
                                    <input type="text" class="form-control modal-input" name="sex" id="patient_sex_expert" disabled>
                                
                                    <label for="patient_phone_expert" class="control-label">手机号:</label>
                                    <input type="text" class="form-control modal-input" name="phone" id="patient_phone_expert" disabled>
                                    
                                    
                                    <label for="identify_expert" class="control-label">验证码：</label>
                                    <div class="identify-div">
                                        <input type="text" class="form-control modal-input" name="code" id="identify_expert">
                                        <button type="button" class="btn btn-primary" id="get_identify_expert">获取验证码</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            <button type="button" class="btn btn-primary" id="confirm_regist_expert">确认挂号</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    
    
    </body>
    <%@include file="/page/common/foot.jsp" %>
    <%@include file="/page/common/patient_common.jsp" %>
    <script src="static/js/patient/patient.js"></script>
    <script src="static/js/bootstrap/bootstrap-datetimepicker.js"></script>
    <script src="static/js/bootstrap/bootstrap-datetimepicker.zh-CN.js"></script>
    <script src="static/js/bootstrap/bootstrap-datetimepicker.fr.js"></script>

</html>
