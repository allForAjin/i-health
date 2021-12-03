<%--
  Created by IntelliJ IDEA.
  User: 15485
  Date: 2021/11/15
  Time: 21:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="welcome">
    <span class="user">欢迎您！用户&nbsp;&nbsp;</span>
    <span class="user" id="user-name"></span>
    <span class="user" id="user-phone">${sessionScope.user.username}</span>
    <a href="javascript:void(0)" id="logout">注销</a>
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
