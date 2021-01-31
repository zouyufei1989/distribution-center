var attrs = ['groupId', 'name', 'serialNumber', 'phone', 'type', 'expireDate','bankCardNumber','bankName','status','cashbackFirst'];

$(document).ready(function () {
    new Vue({el: '#status'});
    new Vue({el: '#groupId'});
    new Vue({el: '#expireDate'});
    new Vue({el: '#cashbackFirst'});

    $('#normal').click(function () {
        $('#expireDate').parent().parent().parent().hide();
        $('#expireDate').val('');
        $('#cashbackFirst').val(0).trigger('change');
        $('#cashbackFirst').attr('disabled','disabled');
    });

    $('#shareholder').click(function () {
        $('#expireDate').parent().parent().parent().show();
        $('#cashbackFirst').removeAttr('disabled');
    });
});

function fillAdditionAttrs(result) {
    if(result.data.customerGroup.type ==1){
        $('#normal').trigger('click');
    }else{
        $('#shareholder').trigger('click');
    }
    $('#groupId').val(result.data.customerGroup.groupId).trigger('change');
    $('#serialNumber').val(result.data.customerGroup.serialNumber);
    $('#expireDate').val(result.data.customerGroup.expireDate);
    $('#bankCardNumber').val(result.data.customerGroup.bankCardNumber);
    $('#bankName').val(result.data.customerGroup.bankName);
    $('#status').val(result.data.customerGroup.status).trigger('change');
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
            $('#serialNumber').val(result.data);
        }
    });
}