$(function () {
    registRecordTableInit();
    payModal();
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
                phone: patientObj.phone
            };
        },
        columns: [{
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
    }
}


function registRecordTableOperation(value, row, index) {
    if (row.payStatus == 0) {
        return '<span>\n' +
            '<a href="javascript:void(0);" data-toggle="modal" data-target="#pay-modal"' +
            'onclick="payForRegist(' + row.normalId + ')">支付订单</a>&nbsp;<span>丨</span>\n' +
            '<a href="javascript:void(0);">取消挂号</a>\n' +
            '</span>';
    } else if (row.payStatus == 1) {
        return '<span><a href="javascript:void(0);">退款</a></span>';
    } else if (row.payStatus == -1) {
        return '<span><a href="javascript:void(0);" onclick="deleteNormalRecord(' + row.normalId + ')">删除记录</a></span>';
    }
}

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

function payModal() {
    $('#pay-modal').modal({
        backdrop: 'static',
        show: false,
        keyboard: false
    });
}

function deleteNormalRecord(normalId) {

    const row = $("#registrecord-table").bootstrapTable('getRowByUniqueId', id);
    $.ajax({
        url: 'patient/patientServlet',
        method: 'post',
        async: false,
        data: {
            action: 'deleteNormalRecord',
            normalId: normalId,
            patientId: patientObj.id
        },
        success: function (data) {
            $("#registrecord-table").bootstrapTable('refresh');
        }
    });
}







