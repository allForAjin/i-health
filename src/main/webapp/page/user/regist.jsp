<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>用户注册</title>
    <%@include file="/page/common/header.jsp"%>
    <link rel="stylesheet" href="static/css/index.css">
</head>
<body>
    <div class="container">
        <div class="regist">
            <h3>用户注册</h3>
            <form action="userServlet" method="post" class="regist-form">
                <input type="hidden" name="action" value="regist">
                <input type="hidden" name="type" value="patient">
                <div class="line">
                    <i class="iconfont formal-icon">&#xe648;</i><span class="split">|</span>
                    <input type="text" class="text" placeholder="用户名（5-12位数字字母下划线）" id="username" name="username" />
                    <div class="error-div">
                        <i class="iconfont error-icon">&#xe8b3;</i>
                        <span class="text-error" id="username-error">请输入用户名！</span>
                    </div>
                </div>
                <div class="line">
                    <i class="iconfont formal-icon">&#xe8b2;</i><span class="split">|</span>
                    <input type="password" class="password" placeholder="密码(8-16位数字字母下划线)" id="password" name="password" />
                    <div class="error-div">
                        <i class="iconfont error-icon">&#xe8b3;</i>
                        <span class="text-error"  id="password-error">请输入密码！</span>
                    </div>
                </div>
                <div class="line">
                    <i class="iconfont formal-icon">&#xe620;</i><span class="split">|</span>
                    <input type="password" class="password" placeholder="确认密码" id="confirm" />
                    <div class="error-div">
                        <i class="iconfont error-icon">&#xe8b3;</i>
                        <span class="text-error" id="confirm-error">请再次输入密码！</span>
                    </div>
                </div>
                <div class="line">
                    <i class="iconfont formal-icon">&#xe61c;</i><span class="split">|</span>
                    <input type="text" class="text" placeholder="手机号" id="phone" name="phone" />
                    <div class="error-div">
                        <i class="iconfont error-icon">&#xe8b3;</i>
                        <span class="text-error" id="phone-error">请输入手机号！</span>
                    </div>
                </div>
                <input type="submit" value="注册" id="regist-btn">
            </form>

            <div class="back">
                <span>已有账号？点此</span><a href="page/user/login.jsp">返回登录</a>
            </div>
        </div>
    </div>
</body>
    <%@include file="/page/common/foot.jsp"%>
    <script src="static/js/index.js"></script>
    <script>
        $(function () {
            var msg ="${sessionScope.msg}";
            if (msg=="existed") {
                layer.msg('  用户名已存在！', {
                    icon: 2,
                    time: 2000,
                    shade: [0.5, '#393D49'],
                    area:['150px','66px']
                });
            }
        });
        $(function () {
            var error =${empty sessionScope.error};
            if (!error) {
                layer.msg('  注册失败！', {
                    icon: 2,
                    time: 2000,
                    shade: [0.5, '#393D49'],
                    area:['210px','66px']
                });
            }
        });
        $(function () {
            var regist ="${requestScope.regist}";
            if (regist=="success") {
                layer.msg('注册成功！', {
                    icon: 1,
                    time: 3000,
                    shade: [0.5, '#393D49'],
                    area:['120px','66px']
                },function () {
                    var path="${applicationScope.basePath}";
                    window.location=path+"page/user/login.jsp";
                });
            }
        });
    </script>
</html>