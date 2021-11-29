<%--
  Created by IntelliJ IDEA.
  User: 15485
  Date: 2021/11/15
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="static/js/jquery-3.5.1/jquery-3.5.1.min.js"></script>
<script src="static/js/bootstrap/bootstrap.min.js"></script>
<script src="static/js/bootstrap/bootstrap-table.min.js"></script>
<script src="static/js/bootstrap/bootstrap-table-zh-CN.min.js"></script>
<script src="static/js/bootstrap/bootstrap-select.min.js"></script>
<script src="static/js/bootstrap/defaults-zh_CN.min.js"></script>
<script src="static/js/bootstrap/bootstrapSelect.js"></script>
<script src="static/js/layer3.1.1/layer.js"></script>
<script>
    //防止页面后退
    history.pushState(null, null, document.URL);
    window.addEventListener('popstate', function () {
        history.pushState(null, null, document.URL);
    });
</script>
