$(document).ready(function () {
    $("#errorInfo").html(errorInfo);
    $('#password').text('');
    getVerifyCode();

    $('*').keydown(function () {
        if (event.keyCode == 13) {
            login();
            return false;
        }
    });
});

function login() {
    if (!$('#loginForm').valid()) {
        return;
    }
    $.ajax({
        url: 'getLogin',
        type: 'post',
        data: {
            username: $('#userName').val(),
            password: encryptRSAPwd($('#password').val()),
            verifyCode: $('#verifyCode').val(),
            _csrf: getCookie('XSRF-TOKEN')
        },
        async: true,
        cache: false,
        success: function (response) {
            if (response.code === 0) {
                window.location.href = "group/index";
                return;
            }
            $('#errorInfo').html(response.message);
        }
    });

}

function getVerifyCode() {
    $('#img_verify_code').attr('src', 'verifyCode?date=' + new Date().getTime());
}

function getVerifyImg() {
    $('#div_verifyImg').children().remove();
    $.ajax({
            url: 'getVerifyImg',
            data: {},
            type: 'post',
            async: true,
            cached: false,
            success: function (result) {

            }
        }
    );
}
