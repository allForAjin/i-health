<%--
  Created by IntelliJ IDEA.
  User: 15485
  Date: 2021/11/15
  Time: 21:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="welcome">
    <%--<span class="user">欢迎您！用户&nbsp;&nbsp;</span>--%>
        <span class="user" id="user-phone">${sessionScope.user.username}</span>
        <%--<a href="javascript:void(0)" id="logout">注销</a>--%>
    
    
    <div class="user-menu">
        <i class="iconfont">&#xe638;</i>
        <a href="#" class="user-drop" id="drop">
            <span class="user" id="user-name"></span>
            <span id="icon-span"><i class="iconfont arrow-icon" id="arrow">&#xe600;</i></span>
        </a>
        <ul id="account-operate">
            <li><a href="javascript:void(0);">修改信息</a></li>
            <li><a href="javascript:void(0);">修改密码</a></li>
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
                <h4 class="modal-title" id="myModalLabel">个人信息</h4>
            </div>
            
            <div class="scroll">
                <div class="modal-body">
                
                </div>
                
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" id="conform-regist">确认</button>
                </div>
            </div>
        </div>
    </div>
</div>
