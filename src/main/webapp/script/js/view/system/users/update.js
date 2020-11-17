var attrs = ['username','nickName', 'groupId','roleId'];

$(document).ready(function () {
    new Vue({el: '#roleId'});
    new Vue({el: '#groupId'});
});

function additionParam(){
    if($('#password').length === 0){
        return {};
    }
    return {password:encryptRSAPwd($('#password').val())};
}

function fillAdditionAttrs(){
    $('#username').attr('disabled','disabled');
    $('#password').parent().parent().remove();
    $('#re_password').parent().parent().remove();
}