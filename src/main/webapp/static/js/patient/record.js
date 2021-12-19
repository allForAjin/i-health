$(function () {
    registRecordTableInit();
    payModal();
});

$(function () {
    $("#conform-refund").click(normalRegistRefund);
});



function registRecordTableInit() {
    $("#registrecord-table").bootstrapTable({
        pagination: true, //显示分页条
        pageNumber: 1, //初始加载第一页
        pageSize: 5,   //一页显示的行数
        paginationLoop: false, //不开启分页条无限循环
        pageList: [5],  //选择每页显示多少行
        url: 'patient/patientServlet',
        method: 'post',
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",//以key-value形式传参
        sidePagination: 'server',//server:服务器端分页|client：前端分页
        uniqueId: 'normalId',
        queryParams: function (params) {
            //上传服务器的参数
            return {
                limit: params.limit, // 每页显示数量
                offset: params.offset, // SQL语句起始索引
                // page : (params.offset / params.limit) + 1, //当前页码
                action: 'getNormalRegistRecord',
                phone: patientObj.phone,

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
            formatter: function (value, row, index) {
                deleteBtnSetting();
                if (row.payStatus == 0 || row.payStatus == 1) {
                    return {
                        disabled: true
                    }
                }
            }
        }, {
            title: '就诊人',
            field: 'patientName',
        }, {
            title: '性别',
            field: 'sex',
        }, {
            title: '年龄',
            field: 'age',
        }, {
            title: '医院',
            field: 'hospital',
        }, {
            title: '科室',
            field: 'department',
        }, {
            title: '就诊日期',
            field: 'registDate',
        }, {
            title: '就诊时间段',
            field: 'time',
        }, {
            title: '挂号费(元)',
            field: 'cost',
        }, {
            title: '支付状态',
            field: 'payStatus',
            formatter: payStatusSetting,
        }, {
            title: '挂号时间',
            field: 'operateTime',
        }, {
            title: '操作',
            field: 'operation',
            formatter: registRecordTableOperation,
        }, {
            title: '患者id',
            field: 'patientId',
            visible: false
        }, {
            title: '挂号id',
            field: 'normalId',
            visible: false
        }, {
            title: '订单号',
            field: 'orderId',
            visible: false
        }, {
            title: '记录号',
            field: 'id',
            visible: false
        }],
        onLoadSuccess: function () {
        }

    });
}

/**
 * 通过判断status值以显示相应的信息
 * @param value
 * @param row
 * @param index
 * @returns {string}
 */
function payStatusSetting(value, row, index) {
    if (row.payStatus == 0) {
        return '<span style="color: #F94F17;">未支付</span>';
    } else if (row.payStatus == 1) {
        return '<span style="color: #20a53a;">已支付</span>';
    } else if (row.payStatus == -1) {
        return '<span>已过期</span>';
    } else if (row.payStatus == -2) {
        return '<span style="color: #F94F17;">已退款</span>';
    }
}


function registRecordTableOperation(value, row, index) {
    if (row.payStatus == 0) {
        return '<span>\n' +
            '<a href="javascript:void(0);" data-toggle="modal" data-target="#pay-modal"' +
            'onclick="payForRegist(' + row.normalId + ')">支付订单</a>&nbsp;<span>丨</span>\n' +
            '<a href="javascript:void(0);" onclick="cancelNormalRegist(' + row.normalId + ',' + row.id + ')">取消挂号</a></span>';
    } else if (row.payStatus == 1) {
        return '<span><a href="javascript:void(0);" data-toggle="modal" data-target="#refund-modal" ' +
            'onclick="openRefundModal(' + row.normalId + ')">退款</a></span>';
    } else if (row.payStatus == -1 || row.payStatus == -2) {
        return '<span><a href="javascript:void(0);" onclick="deleteNormalRecord(' + row.id + ')">删除记录</a></span>';
    }
}

/**
 * 支付订单
 * @param id 订单id号
 */
function payForRegist(id) {
    var row = $("#registrecord-table").bootstrapTable('getRowByUniqueId', id);
    $("#hospital-name").val(row.hospital);
    $("#department-name").val(row.department);
    $("#regist-date").val(row.registDate);
    $("#time").val(row.time);
    $("#id").val(row.orderId);
    $("#patientName").val(patientObj.name);
    $("#phone").val(patientObj.phone);
    $("#cost").val(row.cost);

}

function openRefundModal(id) {
    var row = $("#registrecord-table").bootstrapTable('getRowByUniqueId', id);
    $("#hospital-name-refund").val(row.hospital);
    $("#department-name-refund").val(row.department);
    $("#regist-date-refund").val(row.registDate);
    $("#time-refund").val(row.time);
    $("#trade-id").val(row.orderId);
    $("#patientName-refund").val(patientObj.name);
    $("#phone-refund").val(patientObj.phone);
    $("#cost-refund").val(row.cost);


}

function normalRegistRefund() {
    $.ajax({
        url: 'pay/goAlipay',
        method: 'post',
        data: $("#refund-form").serialize(),
        success: function (result) {
            $('#refund-modal').modal('hide');
            var data = JSON.parse(result);
            if (data.result == "success") {
                layer.msg('  退款成功！', {
                    icon: 1,
                    time: 2000,
                    shade: [0.5, '#393D49'],
                    area: ['180px', '66px']
                });
            } else {
                layer.msg('  退款失败！', {
                    icon: 2,
                    time: 2000,
                    shade: [0.5, '#393D49'],
                    area: ['210px', '66px']
                });
            }
        }

    });
}


function payModal() {
    $('#pay-modal').modal({
        backdrop: 'static',
        show: false,
        keyboard: false
    });

    $('#refund-modal').modal({
        backdrop: 'static',
        show: false,
        keyboard: false
    });
}

function deleteNormalRecord(id) {
    layer.confirm('是否确认删除？', {
        icon: 3,
        title: '提示',
        shade: [0.5, '#393D49'],
        move: false,
    }, function () {
        layer.close();
        $.ajax({
            url: 'patient/patientServlet',
            method: 'post',
            async: false,
            data: {
                action: 'deleteNormalRecord',
                id: id,
            },
            success: function (result) {
                var data = JSON.parse(result);
                if (data.result == "success") {
                    layer.msg('  删除成功！', {
                        icon: 1,
                        time: 2000,
                        shade: [0.5, '#393D49'],
                        area: ['210px', '66px']
                    });
                } else {
                    layer.msg('  删除失败！', {
                        icon: 2,
                        time: 2000,
                        shade: [0.5, '#393D49'],
                        area: ['210px', '66px']
                    });
                }
                $("#registrecord-table").bootstrapTable('refresh');
            }
        });
    });
}

/**
 * 取消挂号
 * @param normalId
 * @param id
 */
function cancelNormalRegist(normalId, id) {
    layer.confirm('是否确认取消？', {
        icon: 3,
        title: '提示',
        shade: [0.5, '#393D49'],
        move: false,
    }, function () {
        layer.close();
        $.ajax({
            url: 'patient/patientServlet',
            method: 'post',
            async: false,
            data: {
                action: 'cancelNormalRegist',
                normalId: normalId,
                patientId: patientObj.id,
                id: id
            },
            success: function (result) {
                var data = JSON.parse(result);
                if (data.result == "success") {
                    layer.msg('  取消成功！', {
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
                $("#registrecord-table").bootstrapTable('refresh');
            }
        });
    });
}

//批量删除
function deleteBtnSetting() {
    const checkedBox = $("#registrecord-table").bootstrapTable('getSelections');
    if (checkedBox.length <= 0) {
        $("#delete-btn").prop("disabled", true);
    } else {
        $("#delete-btn").prop("disabled", false);
    }
}

/**
 * 批量删除
 */
$(function () {
    $("#delete-btn").click(function () {
        const checkedBox = $("#registrecord-table").bootstrapTable('getSelections');
        layer.confirm('是否确认删除这 '+checkedBox.length+' 项？', {
            icon: 3,
            title: '提示',
            shade: [0.5, '#393D49'],
            move: false,
        }, function () {
            $.ajax({
                url: 'patient/patientServlet',
                method: 'post',
                dataType: 'json',
                data: {
                    action: 'batchDeleteNormalRecord',
                    values: JSON.stringify(checkedBox)
                },
                success:function (result){
                    if (result.code==10000){
                        layer.msg('  删除成功！', {
                            icon: 1,
                            time: 2000,
                            shade: [0.5, '#393D49'],
                            area: ['210px', '66px']
                        });
                    }else {
                        layer.msg('  失败！', {
                            icon: 2,
                            time: 2000,
                            shade: [0.5, '#393D49'],
                            area: ['210px', '66px']
                        });
                    }
                    $("#registrecord-table").bootstrapTable('refresh');
                }

            });
        });

    });

});







