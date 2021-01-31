var attrs = ['groupId', 'name', 'desc', 'status', 'serialNumber'];

$(document).ready(function () {
    new Vue({el: '#status'});
    new Vue({el: '#groupId'});

    $("#bonusRate").TouchSpin({
        verticalbuttons: true,
        initval: 0,
        min: 0,
        max: 100,
        step: 0.01,
        decimals: 2,
        postfix: '%',
        buttondown_class: 'btn btn-white',
        buttonup_class: 'btn btn-white'
    });

    $("#cashbackAmount").TouchSpin({
        verticalbuttons: true,
        initval: 0,
        min: 0,
        max: 9999999,
        step: 0.01,
        decimals: 2,
        postfix: '元',
        buttondown_class: 'btn btn-white',
        buttonup_class: 'btn btn-white'
    });

    $('.i-checks').iCheck({
        checkboxClass: 'icheckbox_square-green',
        radioClass: 'iradio_square-green',
    });

});

function additionFunc4Add() {
    $.ajax({
        url: '../utils/generateBonusSerialNumber',
        type: 'post',
        async: true,
        cache: false,
        success: function (result) {
            $('#serialNumber').val(result.data);
        }
    });
}

function fillAdditionAttrs(result) {
    $('#cashbackAmount').val(result.data.cashbackAmount / 100);
    $('#bonusRate').val(result.data.bonusRate / 100);
}

function additionParam() {
    return {
        cashbackAmount: Number.parseFloat($('#cashbackAmount').val()) * 100,
        bonusRate: Number.parseFloat($('#bonusRate').val()) * 100,
    };
}