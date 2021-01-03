var attrs = ['groupId', 'name','desc', 'status','cnt','sumPrice'];

$(document).ready(function () {
    new Vue({el: '#status'});
    new Vue({el: '#groupId'});

    $("#cnt").TouchSpin({
        verticalbuttons: true,
        initval: 0,
        min: 0,
        max: 9999999,
        step: 1,
        decimals: 0,
        postfix: '次',
        buttondown_class: 'btn btn-white',
        buttonup_class: 'btn btn-white'
    });

    $("#sumPrice").TouchSpin({
        verticalbuttons: true,
        initval: 1,
        min: 0,
        max: 9999999,
        step: 1,
        decimals: 0,
        postfix: '元',
        buttondown_class: 'btn btn-white',
        buttonup_class: 'btn btn-white'
    });
});


function fillAdditionAttrs(result) {
    $('#sumPrice').val(result.data.sumPrice / 100);
}

function additionParam() {
    return {
        sumPrice: Number.parseFloat($('#sumPrice').val()) * 100
    };
}