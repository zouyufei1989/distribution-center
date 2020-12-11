var attrs = ['groupId', 'serialNumber', 'name', 'scope', 'maxCntPerCus', 'desc', 'status', 'coverImg'];

$(document).ready(function () {
    new Vue({el: '#status'});
    new Vue({el: '#groupId'});
    new Vue({el: '#coverImgModal'});
    new Vue({el: '#scope'});

    $('#expireDate').change(function () {
        var month = $('#expireDate').val();
        if (isNaN(month)) {
            return;
        }
        month = Number.parseInt(month);
        $('#lbl_expireDate').html(new Date(new Date().setMonth(new Date().getMonth() + month)).Format("yyyy-MM-dd"));
    });

    $("#expireDate").TouchSpin({
        verticalbuttons: true,
        initval: 0,
        min: 1,
        max: 240,
        step: 1,
        decimals: 0,
        postfix: '个月',
        buttondown_class: 'btn btn-white',
        buttonup_class: 'btn btn-white'
    });

    $("#maxCntPerCus").TouchSpin({
        verticalbuttons: true,
        initval: 0,
        min: 1,
        max: 99,
        step: 1,
        decimals: 0,
        buttondown_class: 'btn btn-white',
        buttonup_class: 'btn btn-white'
    });

});

function additionFunc4Add() {
    $.ajax({
        url: '../utils/generateActivitySerialNumber',
        type: 'post',
        async: true,
        cache: false,
        success: function (result) {
            $('#serialNumber').val(result.data);
        }
    });

    fillTreeWithGoods();
}

function fillAdditionAttrs(result) {
    $('#coverImgUrl').attr('src', result.data.coverImgUrl);
}

function additionParam() {
    return {
        coverImg: $('#coverImgUrl').attr('src'),
        expireDate: $('#lbl_expireDate').text(),
        items: TREE.getChecked('id').map(i => i.children).reduce((i, j) => {
            return i.concat(j)
        }).map(i => {
            return {goodsItemId: i.id, cnt: $('#txt_cnt_' + i.id).val()}
        })
    };
}