var attrs = ['groupId', 'name', 'profitRate', 'price', 'unit', 'thumbnail', 'desc', 'status', 'goodsTagId', 'showPrice'];

$(document).ready(function () {
    new Vue({el: '#status'});
    new Vue({el: '#groupId'});
    var vue_goodstag = new Vue({el: '#goodsTagId', data: {groupId: null}});
    new Vue({el: '#showPrice'});
    new Vue({el: '#thumbnailModal'});

    $('#groupId').change(function () {
        vue_goodstag.groupId = $('#groupId').val();
    });

    $("#price").TouchSpin({
        verticalbuttons: true,
        initval: 0,
        min: 0,
        max: 9999999,
        step: 0.01,
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
    $('#thumbnailUrl').attr('src', result.data.items[0].thumbnail);
    $('#profitRate').val(result.data.items[0].profitRate / 100);
    $('#price').val(result.data.items[0].price / 100);
    $('#unit').val(result.data.items[0].unit);
    $('#desc').val(result.data.items[0].desc);
    $('#goodsTagId').val(result.data.items[0].goodsTagId).trigger('change');
    $('#showPrice').val(result.data.items[0].showPrice).trigger('change');
}

function additionParam() {
    return {
        thumbnail: $('#thumbnailUrl').attr('src'),
        profitRate: Number.parseFloat($('#profitRate').val()) * 100,
        price: Number.parseFloat($('#price').val()) * 100
    };
}