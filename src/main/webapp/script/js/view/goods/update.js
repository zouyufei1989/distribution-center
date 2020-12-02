var attrs = ['groupId', 'name', 'profitRate', 'price', 'unit', 'thumbnail', 'desc', 'status', 'goodsTagId', 'showPrice'];

$(document).ready(function () {
    new Vue({el: '#status'});
    new Vue({el: '#groupId'});
    new Vue({el: '#goodsTagId'});
    new Vue({el: '#showPrice'});
    new Vue({el: '#thumbnailModal'});

    $("#price").TouchSpin({
        verticalbuttons: true,
        initval: 0,
        min: 0,
        max: 9999999,
        step: 1,
        decimals: 2,
        postfix: '¥',
        buttondown_class: 'btn btn-white',
        buttonup_class: 'btn btn-white'
    });

    $("#profitRate").TouchSpin({
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
});

function fillAdditionAttrs(result) {
    $('#thumbnailUrl').attr('src', result.data.thumbnail);
    $('#profitRate').val(result.data.profitRate4Show);
    $('#price').val(result.data.price4Show);
}

function additionParam() {
    return {
        thumbnail: $('#thumbnailUrl').attr('src'),
        profitRate: Number.parseFloat($('#profitRate').val()) * 100,
        price: Number.parseFloat($('#price').val()) * 100
    };
}