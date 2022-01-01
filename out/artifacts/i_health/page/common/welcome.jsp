<%--
  Created by IntelliJ IDEA.
  User: 15485
  Date: 2021/11/15
  Time: 21:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="welcome">
    <span class="user" id="user-phone">${sessionScope.user.username}</span>
        <input type="hidden" name="type" id="type" value="${sessionScope.user.type}">
    
    
    <div class="user-menu">
        <i class="iconfont">&#xe638;</i>
        <a href="javascript:void(0);" class="user-drop" id="drop">
            <span class="user" id="user-name"></span>
            <span id="icon-span"><i class="iconfont arrow-icon" id="arrow">&#xe600;</i></span>
        </a>
        <ul id="account-operate">
            <li><a href="javascript:void(0);" data-toggle="modal" data-target="#info-modal">修改信息</a></li>
            <li><a href="javascript:void(0);"  data-toggle="modal" data-target="#password-modal">修改密码</a></li>
            <li><a href="javascript:void(0);" id="logout">退出账号</a></li>
        </ul>
    </div>
</div>


<div class="modal fade" id="info-modal" tabindex="-1" role="dialog" aria-labelledby="infoModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="infoModalLabel">个人信息</h4>
            </div>
            
            <form id="updateForm">
                
                <div class="scroll">
                    <div class="modal-body">
                        <input type="hidden" name="id" id="id">
                        
                        <label for="name" class="control-label">姓名:</label>
                        <input type="text" class="form-control" id="name" name="name">
                        
                        <label for="phone" class="control-label">手机号（账号）:</label>
                        <input type="text" class="form-control pointer" id="phone" name="phone" readonly>
                        
                        <label class="control-label" style="margin-top: 10px;">性别:</label>
                        <br>
                        <div class="radio-inline">
                            <input type="radio" name="sex" id="male-update" value="male">男
                        </div>
                        <div class="radio-inline">
                            <input type="radio" name="sex" id="female-update" value="female">女
                        </div>
                        <div></div>
                        <label style="margin-top: 10px;">选择出生日期：</label>
                        <div class="date-container  modal-input">
                            <div class="date-picker">
                                <div class='input-group date' id='update-datetimepicker'>
                                    <input type='text' class="form-control pointer" id="birth" readonly/>
                                    <span class="input-group-addon bgf">
                                                <span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                </div>
                            </div>
                        </div>
                    
                    </div>
                    
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="button" class="btn btn-primary" id="confirmUpdateInfo">确认</button>
                    </div>
                </div>
            </form>
        
        </div>
    </div>
</div>

<div class="modal fade" id="password-modal" tabindex="-1" role="dialog" aria-labelledby="infoModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="passwordModalLabel">个人信息</h4>
            </div>
            
            <form id="passwordForm">
                
                <div class="scroll">
                    <div class="modal-body">
                        <input type="hidden" name="id" id="id-pwd">
                        
                        <label for="phone" class="control-label">手机号（账号）:</label>
                        <input type="text" class="form-control modal-input" id="phone-pwd" name="phone" readonly>
    
                        <label for="origin-pwd" class="control-label">原密码:</label>
                        <input type="password" class="form-control modal-input" id="origin-pwd" name="originPwd">
    
                        <label for="update-pwd" class="control-label">新密码:</label>
                        <input type="password" class="form-control modal-input" id="update-pwd" name="updatePwd">
    
                        <label for="confirm-pwd" class="control-label">确认密码:</label>
                        <input type="password" class="form-control modal-input" id="confirm-pwd">
    
                        <label for="code" class="control-label" style="display: block;">验证码:</label>
                        <input type="text" class="form-control modal-input" id="code">
                        <button type="button" class="btn btn-primary" id="getCode">获取验证码</button>
                        
                        
                    
                    </div>
                    
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="button" class="btn btn-primary" id="confirmUpdatePwd">确认</button>
                    </div>
                </div>
            </form>
        
        </div>
    </div>
</div>

