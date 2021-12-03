var patientId = undefined;
var registId = undefined;

$(function () {
    hospitalSelectorInit();
    getHospitalList();
    normalTableInit();
    normalRegistModal();
});


/**
 * 条件搜索
 * 刷新表格
 */
$(function () {
    $("#query-btn").click(function () {
        $("#registration-table").bootstrapTable('refresh');
    });

    $("#all-btn").click(function () {
        $("#hospital").selectpicker('val', 'all');
        $("#hospital").selectpicker('refresh');
        $("#all-time").selectpicker('val', 'all');
        $("#all-time").selectpicker('refresh');

        $("#registration-table").bootstrapTable('refresh');
    });
});

function getHospitalList(){
    $.ajax({
        url: 'patient/patientServlet',
        async: false,
        method: 'post',
        data: {
            action: 'getHospitalNormalInfo'
        },
        success:function (data){
            createHospitalList(data)
        }
    });
}

function createHospitalList(data){
    var hospital=JSON.parse(data);
    var htm;
    for (var i=0;i<hospital.length;i++){
        htm='<div class="list-item">\n' +
            '<div class="hospital-item">\n' +
            '<div class="hospital-img">\n' +
            '<img src="'+hospital[i].imgPath+'" alt="">\n' +
            '</div>\n' +
            '\n' +
            '<div class="info">\n' +
            '<div class="item-title">\n' +
            '<h3><a href="javasctipt:void(0)" id=hospital_"'+hospital[i].id+'">'+hospital[i].name+'</a></h3>\n' +
            '</div>\n' +
            '        \n' +
            '<div class="hospital-description">\n' +
            '<span>'+hospital[i].level+'</span>\n' +
            '</div>\n' +
            '</div>\n' +
            '\n' +
            '</div>\n' +
            '</div>';

        $(".list-container").append(htm);
    }
}


function hospitalSelectorInit() {
    $.ajax({
        url: 'patient/patientServlet',
        async: false,
        data: {
            action: 'getHospitalName'
        },
        method: 'post',
        dataType: 'json',
        success: function (data) {
            $("#hospital").find("option").remove();
            $("#hospital").append("<option value='all'>全部医院</option>");
            for (var i = 0; i < data.length; i++) {
                $("#hospital").append("<option value='" + data[i].id + "'>" + data[i].name + "</option>");
            }
            $("#hospital").selectpicker('refresh');


        }
    });
}

/**
 * 当日挂号表格初始化
 */
function normalTableInit() {
    $("#registration-table").bootstrapTable({
        pagination: true, //显示分页条
        pageNumber: 1, //初始加载第一页
        pageSize: 10,   //一页显示的行数
        paginationLoop: false, //不开启分页条无限循环
        pageList: [10, 15],  //选择每页显示多少行
        url: 'patient/patientServlet',
        method: 'post',
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",//以key-value形式传参
        sidePagination: 'server',//server:服务器端分页|client：前端分页
        uniqueId: 'id',
        queryParams: function (params) {
            //上传服务器的参数
            return {
                limit: params.limit, // 每页显示数量
                offset: params.offset, // SQL语句起始索引
                // page : (params.offset / params.limit) + 1, //当前页码
                action: 'getNormalInfo',
                hospitalId: $("#hospital").val(),
                time:$("#all-time").val()
            };
        },
        columns: [{
            title: '序号',
            field: 'id',
            visible: false,
        }, {
            title: '科室id',
            field: 'departmentId',
            visible: false,
        }, {
            title: '医院',
            field: 'hospitalName',
        }, {
            title: '医院等级',
            field: 'level',
        }, {
            title: '科室',
            field: 'departmentName',
        }, {
            title: '日期',
            field: 'registDate',
        }, {
            title: '时间段',
            field: 'time',
        }, {
            title: '挂号费(元)',
            field: 'cost',
        }, {
            title: '剩余号量',
            field: 'remain',
        }, {
            title: '操作',
            field: 'operation',
            formatter: registOperation,
        }],
        onLoadSuccess: function () {
        }

    });
}


/**
 * 加入挂号按钮
 * @param value
 * @param row
 * @param index
 * @returns {string} 挂号按钮 html
 */
function registOperation(value, row, index) {
    var htm;
    if (row.remain == 0) {
        htm = '<span>无号</span>';
        return htm;
    }
    htm = '<span>\n' +
        '<a href="javascript:void(0)" class="regist-btn"' +
        'data-toggle="modal" data-target="#regist-modal"' +
        'onclick="getRegistInfo(' + row.id + ')">挂号</a></span>';
    return htm;
}

/**
 * 设置模态框数据
 * @param id 挂号id
 */
function getRegistInfo(id) {
    registId = id;
    var row = $("#registration-table").bootstrapTable('getRowByUniqueId', id);
    $("#hospital-name").val(row.hospitalName);
    $("#department-name").val(row.departmentName);
    $("#regist-date").val(row.registDate);
    $("#time").val(row.time);
    $("#normal-cost").val(row.cost + "元");
    $("#remain").val(row.remain);

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
    //按下确认挂号后
    $("#confirm-regist").click(confirmRegistNormal);
}

/**
 * 设置模态框中患者信息
 * @param result 通过ajax请求得到的数据
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
    if ($("#identify").val()!=code){
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
        normalId: registId,
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
 * @param data 后端返回的数据
 */
function normalRegistResponse(data) {
    $("#regist-modal").modal('hide');
    if (data.result == "success") {
        layer.msg('挂号成功！', {
            icon: 1,
            time: 2000,
            shade: [0.5, '#393D49'],
            area: ['120px', '66px']
        });

    } else if (data.result == "existed") {
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

var code="";
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
            var data=JSON.parse(result);
            if (data.resultCode==2){
                code=data.messageCode;
                layer.msg('验证码发送成功，请注意查收！', {
                    icon: 1,
                    time: 2000,
                    shade: [0.5, '#393D49'],
                    area: ['200px', '66px']
                });
            }else if(data.resultCode==408){
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
