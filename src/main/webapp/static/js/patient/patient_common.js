var patientObj;

$(function (){

    //防止页面后退
    history.pushState(null, null, document.URL);
    window.addEventListener('popstate', function () {
        history.pushState(null, null, document.URL);
    });
});

$(function (){
    var patientPhone = $("#user-phone").text();

    $.ajax({
        url: 'patient/patientServlet',
        method: 'post',
        async: false,
        dataType: 'json',
        data: {
            action: 'getPersonInfo',
            patientPhone: patientPhone
        },
        success: function (result) {
            patientObj={
                id:result.id,
                phone:result.phone,
                name:result.name,
                sex:result.sex,
                age:result.age,
            };
        }
    });
    $("#user-name").text(patientObj.name);
});



$(function (){
    $('#info-modal').modal({
        backdrop: 'static',
        show: false,
        keyboard: false
    });
    for (let key in patientObj){
        if (patientObj[key]==''){
            $('#info-modal').modal();
        }
    }
});