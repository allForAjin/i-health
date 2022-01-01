var adminObj;

$(function (){

    //防止页面后退
    history.pushState(null, null, document.URL);
    window.addEventListener('popstate', function () {
        history.pushState(null, null, document.URL);
    });
});

$(function (){
    var adminPhone = $("#user-phone").text();

    $.ajax({
        url: 'admin/adminServlet',
        method: 'post',
        async: false,
        dataType: 'json',
        data: {
            action: 'getAdminInfo',
            adminPhone: adminPhone
        },
        success: function (result) {
            if (result.code==10000){
                var data=result.data;
                adminObj={
                    id:data.id,
                    phone:data.phone,
                    name:data.name,
                    sex:data.sex,
                    birth:data.birth
                };
            }

        }
    });
    if (adminObj!=null){
        $("#user-name").text(adminObj.name);
    }

});
