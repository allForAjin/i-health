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

});

$(function (){
    $(".user-menu").hover(function (){
        $("#account-operate").css('display','block');
        $("#icon-span").html('');
        $("#icon-span").html('<i class="iconfont arrow-icon" id="arrow">&#xe601;</i>');

    },function (){
        $("#account-operate").css('display','none');
        $("#icon-span").html('');
        $("#icon-span").html('<i class="iconfont arrow-icon" id="arrow">&#xe600;</i>');
    });
});

$("#account-operate").hover(function() {
    $(this).css('display', 'block');
}, function() {
    $(this).css('display', 'none');
});