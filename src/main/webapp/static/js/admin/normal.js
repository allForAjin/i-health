var select_id = new Array();
$(function () {
    normalInfoTableInit();
});

function normalInfoTableInit() {
    $("#normalManage-table").bootstrapTable({
        pagination: true, //显示分页条
        pageNumber: 1, //初始加载第一页
        pageSize: 20,   //一页显示的行数
        paginationLoop: false, //不开启分页条无限循环
        pageList: [10, 15, 20],  //选择每页显示多少行
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
                action: 'getNormalInfo',

            };
        },
        columns: [{
            checkbox: true,
            formatter: function (i, row) {
                if ($.inArray(row.id, select_id) != -1) {//判断数组里有没有id
                    return {
                        checked: true               // 存在则选中
                    }
                }
            }
        }, {
            title: 'id',
            field: 'id',
        }, {
            title: '医院id',
            field: 'hospital_id',
            visible: false,
        }, {
            title: '医院名',
            field: 'hospitalName',
        }, {
            title: '医院等级',
            field: 'level',
        }, {
            title: '科室id',
            field: 'departmentId',
            visible: false,
        }, {
            title: '科室名',
            field: 'departmentName'
        }, {
            title: '总量',
            field: 'total'
        }, {
            title: '余量',
            field: 'remain'
        }, {
            title: '挂号日期',
            field: 'registDate'
        }, {
            title: '时间段',
            field: 'time'
        }, {
            title: '挂号费',
            field: 'cost'
        }, {
            title: '操作',
            formatter: normalInfoTableOperation,
        }],
        onLoadSuccess: function () {

        }

    });
    $("#normalManage-table").on('uncheck.bs.table check.bs.table check-all.bs.table uncheck-all.bs.table', function (e, rows, rowsAfter) {
        var data = $.isArray(rows) ? rows : [rows];
        var dataUncheck = $.isArray(rowsAfter) ? rowsAfter : [rowsAfter];
        var datas = data == '' ? dataUncheck : data;
        examine(e.type, datas);
        deleteBtnSetting();
    });


}

function examine(type, data) {
    // if (data==undefined||data==''||data==null){
    //     select_id=new Array();
    // }
    if (type.indexOf('uncheck') == -1) {
        $.each(data, function (i, v) {
            // 添加时，判断一行或多行的 id 是否已经在数组里 不存则添加　
            select_id.indexOf(v.id) == -1 ? select_id.push(v.id) : -1;
        });
    } else {
        for (var i = 0; i < data.length; i++) {
            select_id.splice(select_id.indexOf(data[i].id), 1);
        }
    }
}


function deleteBtnSetting() {
    $("#delete-normal").unbind('mouseenter').unbind('mouseleave');
    // const checkedBox = $("#normalManage-table").bootstrapTable('getSelections');
    if (select_id.length > 0) {
        $("#delete-normal").removeClass("btn_no_click");
        $("#delete-normal").hover(function () {
            $(this).addClass("btn_normal");
        }, function () {
            $(this).removeClass("btn_normal");
        });
    } else if (select_id.length <= 0) {
        $("#delete-normal").addClass("btn_no_click");

    }
}

function normalInfoTableOperation(value, row, index){
    return '<span>\n' +
        '<a href="javascript:void(0);" data-toggle="modal" data-target=""' +
        'onclick="updateNormalInfo(' + row.id + ')">修改</a>&nbsp;<span>丨</span>\n' +
        '<a href="javascript:void(0);" onclick="deleteNormal(' + row.id + ')">删除</a></span>';
}

function updateNormalInfo(id){

}

function deleteNormal(id){

}
