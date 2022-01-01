$(function () {
    $("#logout").click(function () {
        layer.confirm('是否确认注销？', {
            icon: 3,
            title: '提示',
            shade: [0.5, '#393D49'],
            move: false,
        }, function () {
            $.ajax({
                url: 'userServlet?action=logout',
                type: 'get',
                async: false,
                success: function () {
                    layer.close();
                    location.reload();
                }
            });
        });

    });


    $('#update-datetimepicker').datetimepicker({
        language: 'zh',
        weekStart: 1,
        todayBtn: 1,
        autoclose: true,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: true,
        format: 'yyyy-mm-dd',
    });

    $("#confirmUpdateInfo").click(function () {
        layer.confirm('是否确认提交？', {
            icon: 3,
            title: '提示',
            shade: [0.5, '#393D49'],
            move: false,
        },function (){

            const namePatten = /^[\u4e00-\u9fa5]{0,10}$/;
            const name=$("#name").val();

            const phonePatten = /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/;
            const phone=$("#phone").val();

            if (!namePatten.test(name)||!phonePatten.test(phone)){
                layer.msg('  请正确填写信息！', {
                    icon: 2,
                    time: 2000,
                    shade: [0.5, '#393D49'],
                    area: ['210px', '66px']
                });
                return;
            }

            const type = $("#type").val();
            let url = '';
            let userPhone;
            if (type === "admin") {
                url = 'admin/adminServlet';
                userPhone=adminObj.phone;
            } else if (type === "patient") {
                url = 'patient/patientServlet';
                userPhone=patientObj.phone;
            }
            let sex=$("input[name='sex']:checked").val();
            if (sex=='male'){
                sex='男';
            }else {
                sex='女';
            }
            $.ajax({
                url: url,
                method: 'post',
                dataType: 'json',
                data: {
                    action: 'updatePersonInfo',
                    id: $("#id").val(),
                    name: name,
                    phone: phone,
                    sex: sex,
                    birth: $("#birth").val(),
                    userPhone:userPhone
                },
                success: function (result) {
                    if (result.code==10000){
                        layer.msg('  修改成功！', {
                            icon: 1,
                            time: 2000,
                            shade: [0.5, '#393D49'],
                            area: ['150px', '66px']
                        });
                        $("#info-modal").modal('hide');
                    }else {
                        layer.msg('  修改失败！', {
                            icon: 2,
                            time: 2000,
                            shade: [0.5, '#393D49'],
                            area: ['150px', '66px']
                        });
                    }
                }
            });
        });
        });




    $("#info-modal").on('show.bs.modal', function () {
        setPersonInfo();
    });


});

function setPersonInfo() {

    const type = $("#type").val();
    if (type === "admin") {
        $("#name").val(adminObj.name);
        $("#phone").val(adminObj.phone);
        $("#id").val(adminObj.id);
        if (adminObj.sex == '男') {
            $("#male-update").attr('checked', true);
        } else {
            $("#female-update").attr('checked', true);
        }
        $("#birth").val(adminObj.birth);
    } else if (type === "patient") {
        $("#name").val(patientObj.name);
        $("#phone").val(patientObj.phone);
        $("#id").val(patientObj.id);
        if (patientObj.sex == '男') {
            $("#male-update").attr('checked', true);
        } else {
            $("#female-update").attr('checked', true);
        }
        $("#birth").val(patientObj.birth);
    }

}

$(function () {
    $(".user-menu").hover(function () {
        $("#account-operate").css('display', 'block');
        $("#icon-span").html('');
        $("#icon-span").html('<i class="iconfont arrow-icon" id="arrow">&#xe601;</i>');

    }, function () {
        $("#account-operate").css('display', 'none');
        $("#icon-span").html('');
        $("#icon-span").html('<i class="iconfont arrow-icon" id="arrow">&#xe600;</i>');
    });
});


$("#account-operate").hover(function () {
    $(this).css('display', 'block');
}, function () {
    $(this).css('display', 'none');
});

var time = 60;
function getCode(obj) {
    if (time === 0) {
        time = 60;
        obj.prop('disabled', false);
        obj.text("获取验证码");
        return;
    } else {
        time--;
        obj.text(time + "秒后重新发送");
    }
    setTimeout(function () {
        getCode(obj);
    }, 1000);
}

function getMessage(obj) {
    obj.prop('disabled', true);
    getCode(obj);
    let url,phone;
    const type = $("#type").val();
    if (type=="admin"){
        url='admin/adminServlet';
        phone=adminObj.phone;
    }else{
        url='patient/patientServlet';
        phone=patientObj.phone;
    }
    $.ajax({
        url: 'message',
        async: false,
        method: 'post',
        data: {
            action: 'getMessage',
            phone: phone,
        },
        success: function (result) {
            var data = JSON.parse(result);
            if (data.resultCode == 2) {
                code = data.messageCode;
                layer.msg('验证码发送成功，请注意查收！', {
                    icon: 1,
                    time: 2000,
                    shade: [0.5, '#393D49'],
                    area: ['300px', '66px']
                });
            } else if (data.resultCode == 408) {
                layer.msg('发送超限！', {
                    icon: 2,
                    time: 2000,
                    shade: [0.5, '#393D49'],
                    area: ['200px', '66px']
                });
            }
        }
    });
}

function updatePassword(type){
    const passwordPatten = /^\w{8,16}$/;
    if (!passwordPatten.test($("#update-pwd").val())||!passwordPatten.test($("#confirm-pwd").val())){
        layer.msg('密码格式错误！', {
            icon: 3,
            time: 2000,
            shade: [0.5, '#393D49'],
            area: ['200px', '66px']
        });
        return;
    }
    if ($("#update-pwd").val()!=$("#confirm-pwd").val()){
        layer.msg('两次密码输入不一致！', {
            icon: 3,
            time: 2000,
            shade: [0.5, '#393D49'],
            area: ['220px', '66px']
        });
        return;
    }

    let url;
    let id;
    if (type=='admin'){
        $("#phone-pwd").val(adminObj.phone);
        url='admin/adminServlet';
        id=adminObj.id;
    }else {
        $("#phone-pwd").val(patientObj.phone);
        url='patient/patientServlet';
        id=patientObj.id;
    }
    $.ajax({
        url: url,
        async: false,
        method: 'post',
        data: {
            action: 'getMessage',
            id: id,
            originPwd:$("#origin-pwd").val()
        },
    });

}

$(function (){
    $("#getCode").click(function (){
        getMessage($(this));
    });

    $("#password-modal").on('show.bs.modal',function (){
        const type=$("#type").val();
        if (type=='admin'){
            $("#phone-pwd").val(adminObj.phone);
        }else {
            $("#phone-pwd").val(patientObj.phone);
        }
        $("#getCode").prop('disabled', false);
        $("#getCode").text("获取验证码");

        $("#confirmUpdatePwd").click(function (){
            updatePassword(type);
        });
    });

    if ($("#code").val() == "") {
        $("#confirmUpdatePwd").prop('disabled', true);
    }
    $("#code").bind("input propertychange", function () {
        if ($(this).val() == "") {
            $("#confirmUpdatePwd").prop('disabled', true);
        } else {
            $("#confirmUpdatePwd").prop('disabled', false);
        }
    });
});