$(function () {
    patientInfoTableInit();
    deleteBtnSetting();
});

/**
 * 查询框及其按钮设置
 */
$(function () {
    $("#query-btn").click(function () {
        $("#patientManage-table").bootstrapTable('refresh');
    });

    $("#all-btn").click(function () {
        $("#username-query").val('');
        $("#patientManage-table").bootstrapTable('refresh');
    });
});

/**
 * 添加、更新与批量删除按钮
 */
$(function () {
    /**
     * 确认更新
     */
    $("#confirm-update").click(function () {
        if (infoIsIegal()) {
            updatePatient();
        } else {
            alert("失败");
        }
    });

    /**
     * 批量删除
     */
    $("#delete-patient").click(function (){
        const checkedBox = $("#patientManage-table").bootstrapTable('getSelections');
        if (checkedBox.length<=0){
            return;
        }
        batchDeletePatient();
    });

    $("#confirm-add").click(function (){
        if (addInfoIsIegal()){
            addPatient();
        }
    });
});

/**
 * 出生日期选择器及模态框初始化
 */
$(function () {
    $("#patient-birth").datetimepicker({
        forceParse: 0,//设置为0，时间不会跳转1899，会显示当前时间。
        language: 'zh-CN',//显示中文
        format: 'yyyy-mm-dd',//显示格式
        minView: "month",//设置只显示到月份
        initialDate: new Date(),//初始化当前日期
        autoclose: true,//选中自动关闭
        todayBtn: true//显示今日按钮
    });

    $("#patient_birth_add").datetimepicker({
        forceParse: 0,//设置为0，时间不会跳转1899，会显示当前时间。
        language: 'zh-CN',//显示中文
        format: 'yyyy-mm-dd',//显示格式
        minView: "month",//设置只显示到月份
        initialDate: new Date(),//初始化当前日期
        autoclose: true,//选中自动关闭
        todayBtn: true//显示今日按钮
    });



    $('#update-modal').modal({
        backdrop: 'static',
        show: false,
        keyboard: false
    });

    $('#add-modal').modal({
        backdrop: 'static',
        show: false,
        keyboard: false
    });
});


function patientInfoTableInit() {
    $("#patientManage-table").bootstrapTable({
        pagination: true, //显示分页条
        pageNumber: 1, //初始加载第一页
        pageSize: 5,   //一页显示的行数
        paginationLoop: false, //不开启分页条无限循环
        pageList: [5],  //选择每页显示多少行
        url: 'admin/adminServlet',
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
                action: 'getPatientInfo',
                patientName: $("#username-query").val(),
                patientPhone: ''
            };
        },
        onCheckAll: function () {
            deleteBtnSetting();
        },
        onCheck: function () {
            deleteBtnSetting();
        },
        onUncheck: function () {
            deleteBtnSetting();
        },
        onUncheckAll: function () {
            deleteBtnSetting();
        },
        columns: [{
            checkbox: true,
        }, {
            title: '患者id',
            field: 'id',
        }, {
            title: '姓名',
            field: 'name',
        }, {
            title: '联系方式',
            field: 'phone',
        }, {
            title: '性别',
            field: 'sex',
        }, {
            title: '年龄',
            field: 'age',
        }, {
            title: '出生日期',
            field: 'birth'
        }, {
            title: '操作',
            formatter: patientInfoTableOperation,

        }],
        onLoadSuccess: function () {
        }

    })
}

//批量删除按钮
function deleteBtnSetting() {
    $("#delete-patient").unbind('mouseenter').unbind('mouseleave');
    const checkedBox = $("#patientManage-table").bootstrapTable('getSelections');
    if (checkedBox.length > 0) {
        $("#delete-patient").removeClass("btn_no_click");
        $("#delete-patient").hover(function () {
            $(this).addClass("btn_normal");
        }, function () {
            $(this).removeClass("btn_normal");
        });
    } else if (checkedBox.length <= 0){
        $("#delete-patient").addClass("btn_no_click");

    }
}

/**
 * 表格操作设置
 * @param value 值
 * @param row 行
 * @param index 序号
 * @returns {string} 操作html
 */
function patientInfoTableOperation(value, row, index) {
    return '<span>\n' +
        '<a href="javascript:void(0);" data-toggle="modal" data-target="#update-modal"' +
        'onclick="updatePatientInfoSetting(' + row.id + ')">修改</a>&nbsp;<span>丨</span>\n' +
        '<a href="javascript:void(0);" onclick="deletePatient(' + row.id + ')">删除</a></span>';
}

/**
 * 更新患者模态框数据设置
 * @param id 患者id
 */
function updatePatientInfoSetting(id) {
    var row = $("#patientManage-table").bootstrapTable('getRowByUniqueId', id);
    $("#id-update").val(id);
    $("#name-update").val(row.name);
    $("#phone-update").val(row.phone);
    $("#patient-birth").val(row.birth);
    if (row.sex == '男') {
        $("#male-update").prop('checked', true);
    } else {
        $("#female-update").prop('checked', true);
    }
}

/**
 * 更新患者请求
 */
function updatePatient() {
    $.ajax({
        url: 'admin/adminServlet',
        method: 'post',
        dataType: 'json',
        data: {
            action: 'updatePatientInfo',
            id: $("#id-update").val(),
            name: $("#name-update").val(),
            phone: $("#phone-update").val(),
            birth: $("#patient-birth").val(),
            sex: $("input[name=sex-update]:checked").val(),
        },
        success: function (result) {
            $("#update-modal").modal('hide');
            if (result.code == 10000) {
                layer.msg('  更新成功！', {
                    icon: 1,
                    time: 2000,
                    shade: [0.5, '#393D49'],
                    area: ['150px', '66px']
                });
                $("#patientManage-table").bootstrapTable('refresh');
            } else {
                layer.msg('  更新失败！', {
                    icon: 2,
                    time: 2000,
                    shade: [0.5, '#393D49'],
                    area: ['150px', '66px']
                });
            }
        }
    });
}

/**
 * 删除患者
 * @param id
 */
function deletePatient(id) {
    layer.confirm('是否确认删除？', {
        icon: 3,
        title: '提示',
        shade: [0.5, '#393D49'],
        move: false,
    }, function () {
        $.ajax({
            url: 'admin/adminServlet',
            method: 'post',
            dataType: 'json',
            data: {
                action: 'deletePatient',
                id: id
            },
            success: function (result) {
                $("#patientManage-table").bootstrapTable('refresh');
                if (result.code == 10000) {
                    layer.msg('  删除成功！', {
                        icon: 1,
                        time: 2000,
                        shade: [0.5, '#393D49'],
                        area: ['150px', '66px']
                    });
                } else {
                    layer.msg('  删除失败！', {
                        icon: 1,
                        time: 2000,
                        shade: [0.5, '#393D49'],
                        area: ['150px', '66px']
                    });
                }
            }
        });
    });
}

/**
 * 修改信息时判断用户信息是否正确
 * @returns {boolean}
 */
function infoIsIegal() {
    var namePatten = /^[\u4e00-\u9fa5]{1,10}$/;
    var nameText = $("#name-update").val();
    if (!namePatten.test(nameText) || nameText == null) {
        layer.msg('  请输入正确的姓名！', {
            icon: 2,
            time: 2000,
            shade: [0.5, '#393D49'],
            area: ['230px', '66px']
        });
        return false;
    }

    var phonePatten = /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/;
    var phoneText = $("#phone-update").val();
    if (!phonePatten.test(phoneText) || phoneText == null) {
        layer.msg('  请输入正确的手机号！', {
            icon: 2,
            time: 2000,
            shade: [0.5, '#393D49'],
            area: ['230px', '66px']
        });
        return false;
    }
    var sex = $("input[name=sex-update]:checked").val();
    if ((sex != 'male' && sex != 'female') || sex == null) {
        layer.msg('  请选择性别！', {
            icon: 2,
            time: 2000,
            shade: [0.5, '#393D49'],
            area: ['200px', '66px']
        });
        return false;
    }

    if ($("#patient-birth").val() == null) {
        layer.msg('  请选择日期！', {
            icon: 2,
            time: 2000,
            shade: [0.5, '#393D49'],
            area: ['200px', '66px']
        });
        return false;
    }
    return true;
}


function addInfoIsIegal() {
    var namePatten = /^[\u4e00-\u9fa5]{1,10}$/;
    var nameText = $("#name-add").val();
    if (!namePatten.test(nameText) || nameText == null) {
        layer.msg('  请输入正确的姓名！', {
            icon: 2,
            time: 2000,
            shade: [0.5, '#393D49'],
            area: ['230px', '66px']
        });
        return false;
    }

    var phonePatten = /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/;
    var phoneText = $("#phone-add").val();
    if (!phonePatten.test(phoneText) || phoneText == null) {
        layer.msg('  请输入正确的手机号！', {
            icon: 2,
            time: 2000,
            shade: [0.5, '#393D49'],
            area: ['230px', '66px']
        });
        return false;
    }
    var sex = $("input[name=sex-add]:checked").val();
    if ((sex != 'male' && sex != 'female') || sex == null) {
        layer.msg('  请选择性别！', {
            icon: 2,
            time: 2000,
            shade: [0.5, '#393D49'],
            area: ['200px', '66px']
        });
        return false;
    }

    if ($("#patient_birth_add").val() == null) {
        layer.msg('  请选择日期！', {
            icon: 2,
            time: 2000,
            shade: [0.5, '#393D49'],
            area: ['200px', '66px']
        });
        return false;
    }
    return true;
}

function batchDeletePatient() {
    const checkBox = $("#patientManage-table").bootstrapTable('getSelections');
    layer.confirm('是否确认删除这 ' + checkBox.length + ' 项？', {
        icon: 3,
        title: '提示',
        shade: [0.5, '#393D49'],
        move: false,
    }, function () {
        layer.close();
        $.ajax({
            url: 'admin/adminServlet',
            method: 'post',
            dataType: 'json',
            data: {
                action: 'batchDeletePatient',
                values: JSON.stringify(checkBox)
            },
            success: function (result) {
                if (result.code == 10000) {
                    layer.msg('  删除成功！', {
                        icon: 1,
                        time: 2000,
                        shade: [0.5, '#393D49'],
                        area: ['210px', '66px']
                    });
                } else {
                    layer.msg('  失败！', {
                        icon: 2,
                        time: 2000,
                        shade: [0.5, '#393D49'],
                        area: ['210px', '66px']
                    });
                }
                $("#patientManage-table").bootstrapTable('refresh');
                deleteBtnSetting();
            }

        });

    });
}

function addPatient(){
    $("#add-modal").modal('hide');
    $.ajax({
        url: 'admin/adminServlet',
        method: 'post',
        dataType: 'json',
        data: {
            action: 'addPatient',
            name: $("#name-add").val(),
            phone: $("#phone-add").val(),
            birth: $("#patient_birth_add").val(),
            sex: $("input[name=sex-add]:checked").val(),
        },
        success: function (result) {
            if (result.code == 10000) {
                layer.msg('  添加成功！', {
                    icon: 1,
                    time: 2000,
                    shade: [0.5, '#393D49'],
                    area: ['210px', '66px']
                });
            } else if(result.code==20000){
                layer.msg('  失败！', {
                    icon: 2,
                    time: 2000,
                    shade: [0.5, '#393D49'],
                    area: ['210px', '66px']
                });
            }else if (result.code==30000){
                layer.msg('  用户已存在！', {
                    icon: 3,
                    time: 2000,
                    shade: [0.5, '#393D49'],
                    area: ['210px', '66px']
                });
            }
            $("#patientManage-table").bootstrapTable('refresh');
        }

    });
}

function getPatientView(){
    $.ajax({
        url: 'admin/adminServlet',
        method: 'post',
        dataType: 'json',
        data:{
            action:'userView'
        },
        success:function (result){
            if (result.code==10000){
                $("#total").text(result.data.patientCount);
                $("#online").text(result.data.size);
                let recordCount=result.data.recordCount;
                if (recordCount>=10000){
                    recordCount=String(recordCount/10000);
                    recordCount+='W';
                }
                $("#totalVisitor").text(recordCount);
            }
        }
    });
}

$(function (){
    getPatientView();
    setInterval(function (){
        getPatientView();
    },1000*60);
});
