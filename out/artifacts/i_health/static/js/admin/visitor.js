$(function () {
    $("#visitor-table").bootstrapTable({
        pagination: true,   //是否显示分页条
        pageNumber: 1, //初始化加载第一页
        pageSize: 10,   //一页显示的行数
        paginationLoop: false,   //是否开启分页条无限循环，最后一页时点击下一页是否转到第一页
        pageList: [10, 15],  //选择每页显示多少行，数据过少时可能会没有效果
        url: 'admin/adminServlet?action=getVisitorRecord',
        method: 'get',
        sidePagination: 'server',//server:服务器端分页|client：前端分页
        queryParams: function (params) {//上传服务器的参数
            var temp = {//如果是在服务器端实现分页，limit、offset这两个参数是必须的
                limit: params.limit, // 每页显示数量
                offset: params.offset, // SQL语句起始索引
                // page : (params.offset / params.limit) + 1, //当前页码
                type:$("#user-type option:selected").val(),
                operate:$("#user-operate option:selected").val(),
                username:$("#username-query").val(),
            };
            return temp;
        },
        columns: [{
            title: '序号',
            field: 'id',
            //sortable: true
        }, {
            title: '用户名',
            field: 'username',
            //sortable: true
        }, {
            title: 'ip地址',
            field: 'ip',
        }, {
            title: '操作时间',
            field: 'time',
        }, {
            title: '操作类型',
            field: 'type',
            formatter: operation,
        }],
        onLoadSuccess: function () {
            // $(".querybtn").click(function(){
            //     alert($(this).parents("tr").find("td:eq(1)").text());
            // });
        }

    });
});

$(function () {
    $("#query-btn").click(function (){
        $("#visitor-table").bootstrapTable('refresh');
    });

    $("#all-btn").click(function () {
        $("#user-type option[value='all']").attr('selected',true);
        $("#user-operate option[value='all']").attr('selected',true);
        $("#user-type").selectpicker('refresh');
        $("#user-operate").selectpicker('refresh');
        $("#username-query").val("");
        $("#visitor-table").bootstrapTable('refresh');
    });
});

function operation(value, row, index) {
    if (value == "login") {
        return '<span>登录</span>'
    } else if (value == "logout") {
        return '<span>注销</span>'
    } else if (value == "regist") {
        return '<span>注册</span>'
    }
}

