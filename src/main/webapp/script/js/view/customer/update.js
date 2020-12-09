var attrs = ['groupId', 'name', 'serialNumber', 'phone', 'type', 'expireDate','bankCardNumber','bankName','status'];

$(document).ready(function () {
    new Vue({el: '#status'});
    new Vue({el: '#groupId'});
    new Vue({el: '#expireDate'});

    $('#normal').click(function () {
        $('#expireDate').parent().parent().parent().hide();
        $('#expireDate').val('');
    });

    $('#shareholder').click(function () {
        $('#expireDate').parent().parent().parent().show();
    });
});

function fillAdditionAttrs(result) {
    $('#img_url').attr('src', result.data.url);
}

function additionParam() {
    return {
        type: $('input:radio[name="customerType"]:checked').val(),
    };
}

function additionFunc4Add() {
    $.ajax({
        url: '../utils/generateCustomerSerialNumber',
        type: 'post',
        async: true,
        cache: false,
        success: function (result) {
            $('#serialNumber').val(result.serialNumber);
        }
    });
}