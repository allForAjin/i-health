$(function () {
    $("#username-login").blur(function () {
        if ($(this).val() == "") {
            $(".line:eq(0)").addClass("input-error");
            $(".error-div:eq(0)").css("visibility", "visible");
        }

    });

    $("#password-login").blur(function () {
        if ($(this).val() == "") {
            $(".line:eq(1)").addClass("input-error");
            $(".error-div:eq(1)").css("visibility", "visible");
        }
    });


    /**
     * 姓名校验
     */
    $("#username").blur(function () {
        var usernamePatten = /^[\u4e00-\u9fa5]{0,10}$/;
        var usernameText = $(this).val();
        if ($(this).val() == "") {
            $(".line:eq(3)").addClass("input-error");
            $(".error-div:eq(3)").css("visibility", "visible");
        } else if (!usernamePatten.test(usernameText)) {
            $("#username-error").text("请正确输入姓名！");
            $(".line:eq(3)").addClass("input-error");
            $(".error-div:eq(3)").css("visibility", "visible");
        }

    });
    /**
     * 密码校验
     * 8-16位数字字母下划线
     */
    $("#password").blur(function () {
        var passwordPatten = /^\w{8,16}$/;
        var passwordText = $(this).val();
        if ($(this).val() == "") {
            $(".line:eq(1)").addClass("input-error");
            $(".error-div:eq(1)").css("visibility", "visible");
        } else if (!passwordPatten.test(passwordText)) {
            $("#password-error").text("密码应为8-16位数字字母下划线！");
            $(".line:eq(1)").addClass("input-error");
            $(".error-div:eq(1)").css("visibility", "visible");
        }

    });
    /**
     * 确认密码校验
     * 8-16位数字字母下划线
     * 与密码相同
     */
    $("#confirm").blur(function () {
        var passwordPatten = /^\w{8,16}$/;
        var passwordText = $(this).val();
        if ($(this).val() == "") {
            $(".line:eq(2)").addClass("input-error");
            $(".error-div:eq(2)").css("visibility", "visible");
        } else if (passwordText != $("#password").val()) {
            $("#confirm-error").text("两次密码输入不一致！");
            $(".line:eq(2)").addClass("input-error");
            $(".error-div:eq(2)").css("visibility", "visible");
        }
    });

    /**
     * 手机号校验
     * /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/
     */
    $("#phone").blur(function () {
        var phoneText = $(this).val();
        var phonePatten = /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/;
        if ($(this).val() == "") {
            $(".line:eq(0)").addClass("input-error");
            $(".error-div:eq(0)").css("visibility", "visible");
        } else if (!phonePatten.test(phoneText)) {
            $("#phone-error").text("请输入正确的手机号！");
            $(".line:eq(0)").addClass("input-error");
            $(".error-div:eq(0)").css("visibility", "visible");
        }
    });

    $("#username-login").focus(function () {
        $(".line:eq(0)").removeClass("input-error");
        $(".error-div:eq(0)").css("visibility", "hidden");
    });
    $("#password-login").focus(function () {
        $(".line:eq(1)").removeClass("input-error");
        $(".error-div:eq(1)").css("visibility", "hidden");
    });

    $("#username").focus(function () {
        $(".line:eq(3)").removeClass("input-error");
        $(".error-div:eq(3)").css("visibility", "hidden");
    });
    $("#password").focus(function () {
        $(".line:eq(1)").removeClass("input-error");
        $(".error-div:eq(1)").css("visibility", "hidden");
    });
    $("#confirm").focus(function () {
        $(".line:eq(2)").removeClass("input-error");
        $(".error-div:eq(2)").css("visibility", "hidden");
    });
    $("#phone").focus(function () {
        $(".line:eq(0)").removeClass("input-error");
        $(".error-div:eq(0)").css("visibility", "hidden");
    });


    $("#login-btn").click(function () {
        var username = $("#username-login").val();
        var password = $("#password-login").val();
        if (username == "" || password == "") {
            layer.msg('请输入用户名或密码！', {
                icon: 2,
                time: 2000,
                shade: [0.5, '#393D49'],
            });
            return false;
        }
    });

    $("#regist-btn").click(function () {
        if (registInfoCheck()) {
            layer.msg('请正确填写信息！', {
                icon: 2,
                time: 2000,
                shade: [0.5, '#393D49'],
            });
            return false;
        }
    });
});


function registInfoCheck() {
    var usernamePatten = /^[\u4e00-\u9fa5]{0,10}$/;
    var usernameText = $("#username").val();
    var passwordPatten = /^\w{8,16}$/;
    var passwordText = $("#password").val();
    var confirmText = $("#confirm").val();
    var phoneText = $("#phone").val();
    var phonePatten = /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/;

    return usernameText == "" || passwordText == "" || confirmText == "" || phoneText == "" ||
        !usernamePatten.test(usernameText) || !passwordPatten.test(passwordText) ||
        !phonePatten.test(phoneText) || confirmText != passwordText;
    }
