var patientId = undefined;
var totalPage;
$(function () {
    hospitalSelectorInit();
    normalRegistModal();

    /**
     * 日期框设置
     */
    var date=new Date();
    var endDate=date.setDate(date.getDate()+7);
    $('#regist-datetimepicker').datetimepicker({
        language: 'zh',
        weekStart: 1,
        todayBtn: 1,
        autoclose: true,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        startDate: date,
        endDate: endDate,
        forceParse: true,
        format: 'yyyy-mm-dd',
    });

    /**
     * 选择科室挂号后弹出确认信息模态框
     */
    $("#choose-regist").click(getRegistInfo);

    /**
     * 查询号量按钮点击
     */
    $("#registTime-query").click(function () {
        var departmentId = $("#departmentId").val();
        getDepartmentInfo(departmentId);
    });
});




$(function () {

});

/**
 * 分页条设置
 */
$(function () {
    getHospitalForPage(1);
    var link_length = $(".hospital-page-link").length;
    const last_all = $(".hospital-page-link:last").children().text();
    console.log(totalPage)
    if (totalPage < link_length) {
        console.log(111)
        for (var i = link_length - 1; i >= link_length - (last_all - totalPage); i--) {
            $(".hospital-page-link:eq(" + i + ")").css("display", "none");
        }
    }

    $("#next_page").click(function () {
        var current = $(".page-inner").find(".picked");

        getHospitalForPage(parseInt(current.text()) + 1);
        if (parseInt(current.text()) == totalPage) {
            return;
        }
        if (current.parent().next()[0] == $(this)[0]) {

            $(".hospital-page-link").each(function () {
                var no = $(this).children().text();
                $(this).children().text(parseInt(no) + 3);
            });
            current.removeClass("picked");
            $(".page-inner .hospital-page-link :eq(0)").addClass("picked");
            const last = $(".hospital-page-link:last").children().text();
            if (last > totalPage) {
                for (var i = link_length - 1; i > last - totalPage; i--) {
                    $(".hospital-page-link:eq(" + i + ")").css("display", "none");
                }
            }
        } else {
            current.removeClass("picked");
            current.parent().next().children().addClass("picked");
        }


    });

    $("#pre_page").click(function () {
        var current = $(".page-inner").find(".picked");
        getHospitalForPage(parseInt(current.text()) - 1);
        if (parseInt(current.text()) - 1 == 0) {
            return;
        }
        if (current.parent().prev()[0] == $(this)[0]) {
            $(".hospital-page-link").css("display", "inline-block");
            $(".hospital-page-link").each(function () {
                var no = $(this).children().text();
                $(this).children().text(parseInt(no) - 3);
            });
            current.removeClass("picked");
            $(".page-inner .hospital-page-link :eq(2)").addClass("picked");
        } else {
            current.removeClass("picked");
            current.parent().prev().children().addClass("picked");
        }

    });

    $(".hospital-page-link").click(function () {
        $(".page-inner").find(".picked").removeClass("picked");
        $(this).children().addClass("picked");
        var pageNo = $(this).children().text();
        getHospitalForPage(pageNo);
    });
});

function getHospitalForPage(pageNo) {
    if (pageNo > totalPage || pageNo < 1) {
        return;
    }
    $.ajax({
        url: 'patient/patientServlet',
        method: 'post',
        dataType: 'json',
        async: false,
        data: {
            action: 'getHospitalForPage',
            pageNo: pageNo,
            pageSize: 5,
        },
        success: function (result) {
            if (result.code == 10000) {
                $(".list-container").empty();
                createHospitalList(result.data.items);
                totalPage = result.data.totalPage;

                // var link_length = $(".hospital-page-link").length;
                // const last_all = $(".hospital-page-link:last").children().text();
                // console.log(totalPage)
                // if (totalPage<link_length){
                //     console.log(111)
                //     for (var i = link_length - 1; i >= link_length-(last_all - totalPage); i--) {
                //         $(".hospital-page-link:eq(" + i + ")").css("display", "none");
                //     }
                // }
            }
        }
    });
}


function isOverTime() {
    var date = new Date();
    var hour = date.getHours();
    if (hour > 15 || hour < 7) {
        layer.alert("当前不在挂号时间范围内！\n（挂号时间：7:00-15:00）", {
            move: false,
            title: '提示'
        });
        $("#registTime-query").prop("disabled", true);
        $("#choose-regist").prop("disabled", true);
    } else {
        $("#registTime-query").prop("disabled", false);
        $("#choose-regist").prop("disabled", false);
    }
}


/**
 * 条件搜索
 */
$(function () {
    $("#query-btn").click(getHospitalById);
    $("#all-btn").click(function () {
        $("#hospital").selectpicker('val', 'all');
        $("#hospital").selectpicker('refresh');
        $(".list-container").empty();
        getHospitalList();
    });
});

/**
 * ajax请求，获取所有医院信息
 */
function getHospitalList() {
    $.ajax({
        url: 'patient/patientServlet',
        async: false,
        method: 'post',
        dataType: 'json',
        data: {
            action: 'getHospitalAllNormalInfo',
        },
        success: function (data) {
            if (data.code == 10000) {
                createHospitalList(data);
            }

        }
    });
}

/**
 * 执行ajax请求，通过医院id获取医院信息
 */
function getHospitalById() {
    $.ajax({
        url: 'patient/patientServlet',
        async: false,
        method: 'post',
        dataType: 'json',
        data: {
            action: 'getHospitalById',
            hospitalId: $("#hospital").selectpicker('val'),
        },
        success: function (data) {
            $(".list-container").empty();
            if (data.code == 10000) {
                createHospitalList(data);
            }

        }
    });
}

/**
 * 创建医院信息列表
 * @param result 请求成功后的数据
 */
function createHospitalList(result) {
    var hospital = result;
    var htm;
    for (var i = 0; i < hospital.length; i++) {
        htm = '<div class="list-item">\n' +
            '<div class="hospital-item">\n' +
            '<div class="hospital-img">\n' +
            '<img src="' + hospital[i].imgPath + '" alt="">\n' +
            '</div>\n' +
            '\n' +
            '<div class="info">\n' +
            '<div class="item-title">\n' +
            '<h3><a href="javascript:void(0);" class="hospital-name" onclick="openRegistInfoModal(' + hospital[i].id + ')" ' +
            'id=hospital_' + hospital[i].id + '>' + hospital[i].name + '</a></h3>\n' +
            '</div>\n' +
            '<div class="item-info"><span>医院等级：</span><span>' + hospital[i].level + '</span></div>\n' +
            '<div class="item-info"><span>联系方式：</span><span>' + hospital[i].phone + '</span></div>\n' +
            '</div>\n' +
            '</div>\n' +
            '\n' +
            '</div>\n' +
            '</div>';

        $(".list-container").append(htm);
    }
}


/**
 * 医院选择下拉框内容初始化
 */
function hospitalSelectorInit() {
    $.ajax({
        url: 'patient/patientServlet',
        async: false,
        data: {
            action: 'getHospitalName'
        },
        method: 'post',
        dataType: 'json',
        success: function (result) {
            if (result.code == 10000) {
                var data = result.data;
                $("#hospital").find("option").remove();
                $("#hospital").append("<option value='all'>全部医院</option>");
                for (var i = 0; i < data.length; i++) {
                    $("#hospital").append("<option value='" + data[i].id + "'>" + data[i].name + "</option>");
                }
                $("#hospital").selectpicker('refresh');
            }


        }
    });
}

/**
 * 打开医院科室信息模态框
 * 并通过医院id获取科室名，创建科室列表
 * @param hospitalId 医院id
 */
function openRegistInfoModal(hospitalId) {
    $("body").css("overflow-y", "hidden");
    var id = '#hospital_' + hospitalId;
    var hospitalName = $(id).text();
    $("#hospitalId").val(hospitalId);
    $("#registInfoModalLabel").text(hospitalName);

    $("#registInfo-modal").modal();
    isOverTime();
    $.ajax({
        url: 'patient/patientServlet',
        method: 'post',
        dataType: 'json',
        data: {
            action: 'getNormalDepartment',
            hospitalId: hospitalId,
        },
        success: function (data) {
            if (data.code == 10000) {
                createDepartmentList(data);
            }

        }
    });

}

/**
 * 创建科室列表，插入科室列表html
 * @param result 请求成功后获取的科室名数据
 */
function createDepartmentList(result) {
    var department = result.data;
    var htm;
    for (var i = 0; i < department.length; i++) {
        htm = '<span class="department-item" id=department_' + department[i].id + '' +
            ' onclick="changeDepartment(' + department[i].id + ')">' + department[i].name + '</span>';
        $(".department-nameList").append(htm);
    }
    $(".department-nameList span:eq(0)").addClass("checked");
    var departmentId = department[0].id;
    $("#departmentId").val(departmentId);
    getDepartmentInfo(departmentId);

}

/**
 * 获取科室信息
 * @param departmentId 科室id
 */
function getDepartmentInfo(departmentId) {
    $.ajax({
        url: 'patient/patientServlet',
        method: 'post',
        dataType: 'json',
        data: {
            action: 'getNormalDepartmentInfo',
            hospitalId: $("#hospitalId").val(),
            departmentId: departmentId,
            date: $("#registDate").val(),
            time: $("#registTime").val()
        },
        success: function (data) {
            var result = data.data;
            if (result.length == 0) {
                $("#remain-num").val(0);
                return;
            }
            $("#normalId").val(result[0].id);
            $("#departmentName").val(result[0].departmentName);
            $("#registDate").val(result[0].registDate);
            $("#normalCost").val(result[0].cost + '元');
            $("#remain-num").val(result[0].remain);
            $("#departmentId").val(departmentId);
        }
    });
}

/**
 * 改变科室
 * @param departmentId 科室id
 */
function changeDepartment(departmentId) {
    var id = "#department_" + departmentId;
    $(".department-nameList span").removeClass('checked');

    $(id).addClass('checked');
    $("#registDate").val();
    getDepartmentInfo(departmentId);

}


/**
 * 设置确认挂号模态框数据
 */
function getRegistInfo() {
    if ($("#remain-num").val() <= 0) {
        layer.msg('  无号源，请选择其他科室！', {
            icon: 2,
            time: 2000,
            shade: [0.5, '#393D49'],
            area: ['230px', '66px']
        });
        return;
    }
    $("#registInfo-modal").modal('hide');
    $("body").css("overflow-y", "hidden");
    $("#regist-modal").modal();
    $("#hospital-name").val($("#registInfoModalLabel").text());
    $("#department-name").val($("#departmentName").val());
    $("#regist-date").val($("#registDate").val());
    $("#time").val($("#registTime option:selected").text());
    $("#normal-cost").val($("#normalCost").val());
    $("#remain").val($("#remain-num").val());
    setInfoInModal();
}

/**
 * 模态框初始化
 */
function normalRegistModal() {
    $('#regist-modal').modal({
        backdrop: 'static',
        show: false,
        keyboard: false
    });

    $('#registInfo-modal').modal({
        backdrop: 'static',
        show: false,
        keyboard: false
    });

    $('#registInfo-modal').on('hide.bs.modal', function () {
        // $("body").css("overflow-y", "auto");
        $(".department-list").empty();
    });
    $('#regist-modal').on('hide.bs.modal', function () {
        $("body").css("overflow-y", "auto");
        $("#identify").val("");
        $("#get-identify").prop('disabled', false);
        $("#get-identify").val("获取验证码");
    });

    //按下确认挂号后
    $("#confirm-regist").click(confirmRegistNormal);
    $("#back-department").click(function (){
        $('#regist-modal').modal('hide');
        $('#registInfo-modal').modal();
        getDepartmentInfo($("#departmentId").val());
    });
}


/**
 * 设置模态框中患者信息
 */
function setInfoInModal() {
    $("#patient-phone").val(patientObj.phone);
    $("#patient-name").val(patientObj.name);
    $("#patient-age").val(patientObj.age);
    $("#patient-sex").val(patientObj.sex);
}

/**
 * 普通门诊确认挂号
 * 按下模态框确认预约按钮后调用
 */
function confirmRegistNormal() {
    if ($("#identify").val() != code) {
        layer.msg('验证码错误！', {
            icon: 2,
            time: 2000,
            shade: [0.5, '#393D49'],
            area: ['120px', '66px']
        });
        return;
    }
    var cost = $("#normal-cost").val();
    var registData = {
        patientId: patientObj.id,
        normalId: $("#normalId").val(),
        cost: cost.slice(0, cost.length - 1),
        registDate: $("#regist-date").val(),
        time: $("#time").val(),
        department: $("#department-name").val(),
        hospital: $("#hospital-name").val(),
    };
    layer.confirm('是否确认挂号？', {
        icon: 3,
        title: '确认',
        shade: [0.5, '#393D49'],
        move: false,
    }, function () {
        layer.close();
        //确认挂号请求
        $.ajax({
            url: 'patient/patientServlet',
            method: 'post',
            dataType: 'json',
            data: {
                action: 'confirmNormalRegist',
                confirmData: JSON.stringify(registData),
                remain: $("#remain").val(),
            },
            success: function (data) {
                normalRegistResponse(data)
            },
        });
    });
}

/**
 * 普通门诊挂号请求成功后调用
 * @param result 后端返回的数据
 */
function normalRegistResponse(result) {
    $("#regist-modal").modal('hide');
    if (result.code == 10000) {
        layer.msg('挂号成功！', {
            icon: 1,
            time: 2000,
            shade: [0.5, '#393D49'],
            area: ['120px', '66px']
        });

    } else if (result.code == 20000) {
        layer.msg('请勿重复挂号！', {
            icon: 2,
            time: 2000,
            shade: [0.5, '#393D49'],
            area: ['120px', '66px']
        });
    } else {
        layer.msg('挂号失败！', {
            icon: 2,
            time: 2000,
            shade: [0.5, '#393D49'],
            area: ['120px', '66px']
        });
    }

    $("#registration-table").bootstrapTable('refresh');
}


$(function () {
    if ($("#identify").val() == "") {
        $("#confirm-regist").prop('disabled', true);
        //$("#confirm-regist").addClass('disabled');
    }
    $("#identify").bind("input propertychange", function () {
        if ($(this).val() == "") {
            $("#confirm-regist").prop('disabled', true);
        } else {
            $("#confirm-regist").prop('disabled', false);
        }
    });

    $("#get-identify").click(getMessage);
});


var time = 60;

function getCode() {
    if (time === 0) {
        time = 60;
        $("#get-identify").prop('disabled', false);
        $("#get-identify").text("获取验证码");
        return;
    } else {
        time--;
        $("#get-identify").text(time + "秒后重新发送");
    }
    setTimeout(function () {
        getCode();
    }, 1000);
}

var code = "";

function getMessage() {
    $("#get-identify").prop('disabled', true);
    getCode();
    $.ajax({
        url: 'patient/patientServlet',
        async: false,
        method: 'post',
        data: {
            action: 'getMessage',
            phone: $("#patient-phone").val(),
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
