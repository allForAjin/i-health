<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
    
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <%@include file="/page/common/header.jsp" %>
        <title>系统登录</title>
        <link rel="stylesheet" href="static/css/index.css">
    </head>
    
    <body>
        <div class="container">
            <div class="login">
                <h3>用户登录</h3>
                <form action="userServlet" method="post" class="login-form">
                    <input type="hidden" name="action" value="login">
                    <div class="line">
                        <i class="iconfont formal-icon">&#xe648;</i><span class="split">|</span>
                        <input type="text" class="text" placeholder="账号"
                               id="username-login" name="username" value="${cookie.username.value}"/>
                        <div class="error-div">
                            <i class="iconfont error-icon">&#xe8b3;</i><span class="text-error">请输入账号！</span>
                        </div>
                    </div>
                    
                    <div class="line">
                        <i class="iconfont formal-icon">&#xe8b2;</i><span class="split">|</span>
                        <input type="password" class="password" placeholder="密码" id="password-login" name="password" value="${cookie.password.value}"/>
                        <div class="error-div">
                            <i class="iconfont error-icon">&#xe8b3;</i><span class="text-error">请输入密码！</span>
                        </div>
                    </div>
                    <input type="submit" value="登录" id="login-btn">
                    <div class="link clearfix">
                        <a id="forget" href="page/user/login.jsp">忘记密码？</a>
                        <a id="regist" href="page/user/regist.jsp">注册账号</a>
                    </div>
                </form>
            </div>
        </div>
    
    </body>
    <%@include file="/page/common/foot.jsp" %>
    <script src="static/js/index.js"></script>
    <script>
        $(function () {
            var msg =${empty sessionScope.msg};
            if (!msg) {
                layer.msg('  用户名不存在！', {
                    icon: 2,
                    time: 2000,
                    shade: [0.5, '#393D49'],
                    area: ['210px', '66px']
                });
            }
        });
        $(function () {
            var error =${empty sessionScope.error};
            if (!error) {
                layer.msg('  用户名或密码错误！', {
                    icon: 2,
                    time: 2000,
                    shade: [0.5, '#393D49'],
                    area: ['210px', '66px']
                });
            }
        });
    
    </script>
</html>