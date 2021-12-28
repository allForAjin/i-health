var patientId = undefined;
var totalPage;
var currentDepartmentId;
$(function () {
    normalRegistModal();

    /**
     * 日期框设置
     */
    var date = new Date();

    $('#regist-datetimepicker').datetimepicker({
        language: 'zh',
        weekStart: 1,
        todayBtn: 1,
        autoclose: true,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        startDate: date,
        // endDate: endDate,
        forceParse: true,
        format: 'yyyy-mm-dd',
    });


});


/**
 * 分页条设置
 */
$(function () {
    getHospitalForPage(1);
    var link_length = $(".hospital-page-link").length;
    const last_all = $(".hospital-page-link:last").children().text();
    if (totalPage < link_length) {
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

/**
 * 分页获取医院信息
 * @param pageNo 页码
 */
function getHospitalForPage(pageNo) {
    if (pageNo > totalPage || pageNo < 1) {
        return;
    }
    if (location.hash.slice(2) != "#" && location.hash.slice(2) != '') {
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
                hospitalSelectorInit();
                $(".hospital-box").css("display", "block");
                $(".page-container").css("display", "block");
                $(".list-container").empty();
                createHospitalList(result.data.items);
                totalPage = result.data.totalPage;

            }
        }
    });
}


function isOverTime() {
    var date = new Date();
    var hour = date.getHours();
    if (hour > 20 || hour < 7) {
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
        success: function (result) {
            if (result.code == 10000) {
                createHospitalList(result.data);
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
        success: function (result) {

            if (result.code == 10000) {
                $(".list-container").empty();
                createHospitalList(result.data);
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
            '<h3><a href="javascript:void(0);" data-path="' + hospital[i].id + '" class="hospital-name"' +
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
 */
function openRegistInfoModal() {
    if ($(this).attr("remain") == 0) {
        return;
    }
    $("#hospital-name").val($("#department-title").text());
    $("#department-name").val($(this).attr("dep"));
    $("#regist-date").val($(this).attr("date"));
    $("#time").val($(this).attr("time"));
    $("#normal-cost").val($(this).attr("cost") + '元');
    $("#remain").val($(this).attr("remain"));
    $("#normalId").val($(this).attr("id"));
    setInfoInModal();
    $('#regist-modal').modal();
    $("body").css("padding-right", "0");
}


/**
 * 创建科室列表，插入科室列表html
 * @param result 请求成功后获取的科室名数据
 */
function createDepartmentList(result) {
    $(".department-nameList").empty();
    var department = result.data;
    var htm;
    for (var i = 0; i < department.length; i++) {
        htm = '<span class="department-item" id=department_' + department[i].id + '' +
            ' onclick="changeDepartment(' + department[i].id + ')">' + department[i].name + '</span>';
        $(".department-nameList").append(htm);
    }
    $(".department-nameList span:eq(0)").addClass("checked");
    var departmentId = department[0].id;
    try {
        const expert = getExpertByDepartment(departmentId);
        createExpertList(expert);
    } catch (err) {
        console.log(err);
    }

    return getAllDepartmentInfo(departmentId);
}


function getAllDepartmentInfo(departmentId) {
    var departmentInfo = [];
    $.ajax({
        url: 'patient/patientServlet',
        method: 'post',
        dataType: 'json',
        async: false,
        data: {
            action: 'getNormalDepartmentInfo',
            hospitalId: $("#department-title").attr("data-path"),
            departmentId: departmentId,
            date: 'all',
            time: 'all'
        },
        success: function (result) {
            departmentInfo = result.data;
        }
    });
    return departmentInfo;
}

/**
 * 改变科室
 * @param departmentId 科室id
 */
function changeDepartment(departmentId) {
    currentDepartmentId = departmentId;
    var id = "#department_" + departmentId;
    $(".department-nameList span").removeClass('checked');

    $(id).addClass('checked');
    $("#registDate").val();
    const departmentInfo = getAllDepartmentInfo(departmentId);
    departmentTableSetting(departmentInfo);
    const expert = getExpertByDepartment(departmentId);
    createExpertList(expert);
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

    $('#expertInfo-modal').modal({
        backdrop: 'static',
        show: false,
        keyboard: false
    });

    $('#expert-modal').modal({
        backdrop: 'static',
        show: false,
        keyboard: false
    });

    $('#regist-modal').on('show.bs.modal', function () {
        $("#get-identify").text("获取验证码");
    });

    $('#expert-modal').on('show.bs.modal', function () {
        $("#get_identify_expert").text("获取验证码");
    });

    // $('#regist-modal').on('show.bs.modal', function () {
    //     $(this).find('.modal-dialog').css('margin-top', '20%');
    // });


    $('#regist-modal').on('hide.bs.modal', function () {
        $("body").css("overflow-y", "auto");
        $("#identify").val("");
        $("#get-identify").prop('disabled', false);
        $("#get-identify").val("获取验证码");
    });

    $('#expert-modal').on('hide.bs.modal', function () {
        $("body").css("overflow-y", "auto");
        $("#identify_expert").val("");
        $("#get_identify_expert").prop('disabled', false);
        $("#get_identify_expert").val("获取验证码");
    });

    //按下确认挂号后
    $("#confirm-regist").click(confirmRegistNormal);
    $("#confirm_regist_expert").click(confirmRegistExpert);

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
    var cost = $("#normal-cost").val();
    var date = $("#regist-date").val();
    var registData = {
        patientId: patientObj.id,
        normalId: $("#normalId").val(),
        cost: cost.slice(0, cost.length - 1),
        registDate: date.substring(0, date.length - 2),
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
            async: false,
            data: {
                action: 'confirmNormalRegist',
                confirmData: JSON.stringify(registData),
                remain: $("#remain").val(),
                code: $("#identify").val()
            },
            success: function (result) {
                if (result.code == 20000 && result.data == "验证码错误！") {
                    layer.msg('验证码错误！', {
                        icon: 2,
                        time: 2000,
                        shade: [0.5, '#393D49'],
                        area: ['120px', '66px']
                    });
                } else if (result.code == 40000) {
                    layer.msg('验证码已过期！', {
                        icon: 2,
                        time: 2000,
                        shade: [0.5, '#393D49'],
                        area: ['130px', '66px']
                    });
                } else {
                    registResponse(result);
                }

            }
        });
    });
}

function confirmRegistExpert() {
    layer.confirm('是否确认挂号？', {
        icon: 3,
        title: '确认',
        shade: [0.5, '#393D49'],
        move: false,
    }, function () {
        layer.close();
        var data = {
            expertId: $("#expertRegistId").val(),
            hospital: $("#hospital_name_expert").val(),
            department: $("#department_name_expert").val(),
            expertName: $("#expert_name").val(),
            registDate: $("#regist_date_expert").val(),
            time: $("#time_expert").val(),
            cost: $("#expert-cost").val(),
            patientId: patientObj.id
        };
        //确认挂号请求
        $.ajax({
            url: 'patient/patientServlet',
            method: 'post',
            dataType: 'json',
            async: false,
            data: {
                action: 'confirmExpertRegist',
                confirmData: JSON.stringify(data),
                remain: $("#expert_remain").val(),
                code: $("#identify_expert").val()
            },
            success: function (result) {
                if (result.code == 20000 && result.data == "验证码错误！") {
                    layer.msg('验证码错误！', {
                        icon: 2,
                        time: 2000,
                        shade: [0.5, '#393D49'],
                        area: ['120px', '66px']
                    });
                } else if (result.code == 40000) {
                    layer.msg('验证码已过期！', {
                        icon: 2,
                        time: 2000,
                        shade: [0.5, '#393D49'],
                        area: ['130px', '66px']
                    });
                } else {
                    console.log("code:" + result.code)
                    registResponse(result);
                }

            }
        });
    });
}


/**
 * 普通门诊挂号请求成功后调用
 * @param result 后端返回的数据
 */
function registResponse(result) {

    if (result.code == 10000) {
        layer.msg('挂号成功！', {
            icon: 1,
            time: 2000,
            shade: [0.5, '#393D49'],
            area: ['120px', '66px']
        }, function () {
            window.location.href = $("base").attr("href") + "page/patient/regist_record.jsp";
        });
    } else if (result.code == 30000) {
        layer.msg('请勿重复挂号！', {
            icon: 2,
            time: 2000,
            shade: [0.5, '#393D49'],
            area: ['120px', '66px']
        });
    } else if (result.code == 20000 && result.data == "无号") {
        layer.msg('无号！', {
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
    $("#regist-modal").modal('hide');
    $("#expert-modal").modal('hide');
}


$(function () {
    if ($("#identify").val() == "") {
        $("#confirm-regist").prop('disabled', true);
    }
    $("#identify").bind("input propertychange", function () {
        if ($(this).val() == "") {
            $("#confirm-regist").prop('disabled', true);
        } else {
            $("#confirm-regist").prop('disabled', false);
        }
    });

    $("#get-identify").click(function () {
        getMessage($(this));
    });


    if ($("#identify_expert").val() == "") {
        $("#confirm_regist_expert").prop('disabled', true);
    }
    $("#identify_expert").bind("input propertychange", function () {
        if ($(this).val() == "") {
            $("#confirm_regist_expert").prop('disabled', true);
        } else {
            $("#confirm_regist_expert").prop('disabled', false);
        }
    });

    $("#get_identify_expert").click(function () {
        getMessage($(this));
    });


});


/**
 * 获取验证码按钮倒计时
 * @type {number}
 */
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


var code = "";

function getMessage(obj) {
    obj.prop('disabled', true);
    getCode(obj);
    $.ajax({
        url: 'patient/patientServlet',
        async: false,
        method: 'post',
        data: {
            action: 'getMessage',
            phone: patientObj.phone,
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

/**
 * 通过医院id获取医院名 并返回医院名
 * @param id 医院id
 * @returns {string}
 */
function getHospitalNameById(id) {
    var hospital = '';
    $.ajax({
        url: 'patient/patientServlet',
        async: false,
        method: 'post',
        dataType: 'json',
        data: {
            action: 'getHospitalById',
            hospitalId: id
        },
        success: function (result) {
            hospital = (result.data)[0];
        }
    });
    return hospital;
}

/**
 * 通过科室id获取专家信息
 * @param departmentId 科室id
 */
function getExpertByDepartment(departmentId) {
    var expert = new Array();
    $.ajax({
        url: 'patient/patientServlet',
        method: 'get',
        dataType: 'json',
        async: false,
        data: {
            action: 'getExpertByDepartment',
            departmentId: departmentId,
        }, success: function (result) {
            if (result.code == 10000) {
                expert = result.data;
            } else {
                layer.msg('  无专家信息！', {
                    icon: 3,
                    time: 1000,
                    shade: [0.5, '#393D49'],
                    area: ['220px', '66px']
                });
            }
        }
    });
    return expert;
}

function createExpertList(expert) {
    $("#expert_list").empty();
    if (expert.length === 0) {
        return;
    }
    for (let i = 0; i < expert.length; i++) {
        $("#expert_list").append('<div class="list-item department-list">\n' +
            '<div class="department_list_item" expert-name="' + expert[i].name + '">\n' +
            '<div class="department-img">\n' +
            '<img src="' + expert[i].imgPath + '">\n' +
            '</div>\n' +
            '<div class="info">\n' +
            '<div class="item-title">' +
            '<a href="javascript:void(0);" onclick="expertRegistModalSetting(' + expert[i].id + ')" ' +
            'class="expert_name">' + expert[i].name + '</a></div>\n' +
            '<div class="item-info expert_profession"><span>' + expert[i].career + '</span></div>\n' +
            '<div class="item-info expert_profession"><span class="appointment">预约量：<span class="num">' + expert[i].registCount + '</span></span></div>\n' +
            '</div>\n' +
            '<button class="btn btn-primary choose-expert" onclick="expertRegistModalSetting(' + expert[i].id + ')">选择预约</button>\n' +
            '</div>\n' +
            '</div>');
    }
}

function expertRegistModalSetting(id) {
    $(".department-info input").val('');
    $("#expertInfo-modal").modal();
    $("#expertId").val(id);
    try {
        const expertRegistInfo = getExpertRegistInfo(id);
        $("#departmentName").val(expertRegistInfo.departmentName);
        $("#expertName").val(expertRegistInfo.doctorName);
        $("#registDate").val(expertRegistInfo.registDate);
        $("#remain-num").val(expertRegistInfo.remain);
        $("#expertCost").val(expertRegistInfo.cost);
    } catch (err) {
        console.log(err);
    }


    $("#registTime-query").click(function () {
        const expertRegistInfo = getExpertRegistInfo(id);
        if (expertRegistInfo == null) {
            $("#remain-num").val(0);
            $("#expertCost").val(0);
            $("#choose-regist").attr('disabled', true);
        } else {
            $("#remain-num").val(expertRegistInfo.remain);
            $("#expertCost").val(expertRegistInfo.cost);
            $("#choose-regist").attr('disabled', false);
        }

    });

    $("#choose-regist").click(function () {
        $("#expertInfo-modal").modal('hide');
        $("#expert-modal").modal();
        expertRegistConfirmModal();
    });

}

function expertRegistConfirmModal() {
    $("#expertRegistId").val($("#expertId").val());
    $("#expert_remain").val($("#remain-num").val());

    $("#hospital_name_expert").val($("#department-title").text());
    $("#department_name_expert").val($("#departmentName").val());
    $("#expert_name").val($("#expertName").val());
    $("#regist_date_expert").val($("#registDate").val());
    $("#time_expert").val($("#registTime option:checked").text());
    $("#expert-cost").val($("#expertCost").val());

    $("#patient_phone_expert").val(patientObj.phone);
    $("#patient_name_expert").val(patientObj.name);
    $("#patient_age_expert").val(patientObj.age);
    $("#patient_sex_expert").val(patientObj.sex);

}

function getExpertRegistInfo(id) {
    var expertRegistInfo;
    $.ajax({
        url: 'patient/patientServlet',
        method: 'post',
        dataType: 'json',
        async: false,
        data: {
            action: 'getExpertRegistInfo',
            id: id,
            date: $("#registDate").val(),
            time: $("#registTime option:checked").val(),
        },
        success: function (result) {
            if (result.code == 10000) {
                expertRegistInfo = (result.data)[0];
            } else {
                layer.msg('  无专家信息！', {
                    icon: 3,
                    time: 1000,
                    shade: [0.5, '#393D49'],
                    area: ['220px', '66px']
                });
                $("#choose-regist").attr('disabled', false);
            }
        }
    });
    return expertRegistInfo;
}


/**
 * 以下为 hash 方法单页面应用设置
 * 此中代码由于各种函数嵌套调用导致耦合度过高（可能连我自己都看不明白），
 * 内容复杂，切勿乱改！！！
 * 呜呜呜
 */
$(function () {

    /**
     * 页面刷新，hash方法
     * @param path url修改值
     */
    function refresh(path) {
        $(".regist-img").empty();
        $("#regist-header .item-info").remove();
        if (path == '#') {
            $(".hospital-box").css("display", "block");
            $("#department-box").css("display", "none");
            return;
        }


        $(".hospital-box").css("display", "none");
        $("#department-box").css("display", "block");
        $(".hospital-header").css("display", "block");

        hospitalSetting(path);

        var departmentInfo = getDepartmentList(path);


        departmentTableSetting(departmentInfo);


    }


    /**
     * 医院信息header设置
     * @param path 医院id
     */
    function hospitalSetting(path) {
        const hospital = getHospitalNameById(path);
        $("#department-title").text(hospital.name);
        $("#department-title").attr("data-path", path);
        $(".hospital-header .hospital-img").append("<img src='" + hospital.imgPath + "'>");
        $("#regist-header .info").append(
            '<div class="item-info"><span class="title">医院等级：</span><span>' + hospital.level + '</span></div>\n' +
            '<div class="item-info"><span class="title">联系方式：</span><span>' + hospital.phone + '</span></div>'
        );
    }

    $(".hospital-name").each(function () {
        this.addEventListener('click', function (e) {
            var path = $(this).attr("data-path");
            window.location.hash = "/" + path;

        });
    });

    window.addEventListener('hashchange', function (e) {
        var oldPath = e.oldURL.split("#/")[1];
        var newPath = e.newURL.split("#/")[1];
        //hash值不一致时更新视图
        if (oldPath != newPath) {
            refresh(newPath);
        }
    });

    window.addEventListener('load', function (e) {
        var path = "#";
        if (location.hash) {
            path = location.hash.slice(2);
        }
        refresh(path);
    });

});

/**
 * 预约挂号表格设置
 * @param departmentInfo
 */
function departmentTableSetting(departmentInfo) {
    $("tr .d").empty();
    var head = $("#department-table .h");
    let j = 0;
    for (let i = 0; i < head.length; i++) {
        let date;
        try {
            date = departmentInfo[j].registDate;
        } catch (err) {
            console.log(err);
            date = "无";
        }

        $(head[i]).text(date.slice(5));
        j += 2;
    }

    j = 0;
    for (var i = 1; i <= head.length; i++) {
        $("#department-table tbody tr:eq(0) td:eq(" + i + ")").append(
            '<span class="regist-btn"><a href="javascript:void(0);"\n' +
            'data-html="true"\n' +
            'data-trigger="hover"\n' +
            'data-placement="top" data-content="余量：' + departmentInfo[j].remain + '" ' +
            'dep="' + departmentInfo[j].departmentName + '" ' +
            'id="' + departmentInfo[j].id + '" time="' + departmentInfo[j].time + '" ' +
            'date="' + departmentInfo[j].registDate + '" cost="' + departmentInfo[j].cost + '" ' +
            'remain="' + departmentInfo[j].remain + '">\n' +
            '<span>预约</span>\n' +
            '</a></span>');

        if (departmentInfo[j].remain == 0) {
            $("#department-table tbody tr:eq(0) td:eq(" + i + ")")
                .children(".regist-btn")
                .children("a")
                .addClass("gray");
            $("#department-table tbody tr:eq(0) td:eq(" + i + ")")
                .children(".regist-btn")
                .children("a").children("span").text("已满");
        } else if (departmentInfo[j].remain < 0) {
            $("#department-table tbody tr:eq(0) td:eq(" + i + ")").empty();
        }
        j++;

        if (j == departmentInfo.length) {
            break;
        }

        $("#department-table tbody tr:eq(1) td:eq(" + i + ")").append(
            '<span class="regist-btn"><a href="javascript:void(0);"\n' +
            'data-html="true"\n' +
            'data-trigger="hover"\n' +
            'data-placement="top" data-content="余量：' + departmentInfo[j].remain + '" ' +
            'dep="' + departmentInfo[j].departmentName + '" ' +
            'id="' + departmentInfo[j].id + '" time="' + departmentInfo[j].time + '" ' +
            'date="' + departmentInfo[j].registDate + '" cost="' + departmentInfo[j].cost + '" ' +
            'remain="' + departmentInfo[j].remain + '">\n' +
            '<span>预约</span>\n' +
            '</a></span>');

        if (departmentInfo[j].remain == 0) {
            $("#department-table tbody tr:eq(1) td:eq(" + i + ")")
                .children(".regist-btn")
                .children("a")
                .addClass("gray");
            $("#department-table tbody tr:eq(1) td:eq(" + i + ")")
                .children(".regist-btn")
                .children("a").children("span").text("已满");
        } else if (departmentInfo[j].remain < 0) {
            $("#department-table tbody tr:eq(1) td:eq(" + i + ")").empty();
        }
        j++;

    }
    $(".regist-btn a").popover();
    $(".regist-btn a").click(openRegistInfoModal);
}

function getDepartmentList(hospitalId) {
    var departmentInfo = [];
    $.ajax({
        url: 'patient/patientServlet',
        method: 'post',
        dataType: 'json',
        async: false,
        data: {
            action: 'getNormalDepartment',
            hospitalId: hospitalId,
        },
        success: function (data) {
            if (data.code == 10000) {
                departmentInfo = createDepartmentList(data);
            }

        }
    });
    return departmentInfo;
}


