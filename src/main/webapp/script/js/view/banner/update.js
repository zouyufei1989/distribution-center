var attrs = ['title', 'href', 'url', 'desc', 'index', 'status'];

$(document).ready(function () {
    $("#index").TouchSpin({
        verticalbuttons: true,
        buttondown_class: 'btn btn-white',
        buttonup_class: 'btn btn-white'
    });

    new Vue({el: '#status'});
    new Vue({el: '#urlModal'});
});

function fillAdditionAttrs(result) {
    $('#img_url').attr('src', result.data.url);
}

function additionParam() {
    return {
        url: $('#img_url').attr('src')
    };
}
