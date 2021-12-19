<%--
  Created by IntelliJ IDEA.
  User: 15485
  Date: 2021/11/16
  Time: 10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
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
                        var path = document.URL;
                        window.location = path;
                    }
                });
            });

        });

    });
</script>
<script>
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
</script>
