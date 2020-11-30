var attrs = ['name', 'cityCode', 'address', 'ownerName', 'ownerPhone', 'status', 'desc', 'thumbnail', 'detailCoverImg', 'detailImg', 'index', 'openRules'];
var VUE_CITY;

$(document).ready(function () {
    new Vue({el: '#status'});
    new Vue({el: '#thumbnailModal'});
    new Vue({el: '#detailCoverImgModal'});
    new Vue({el: '#detailImgModal'});
    VUE_CITY = new Vue({el: '#cityCode', data: {cityCode: null}});
    $("#index").TouchSpin({
        verticalbuttons: true,
        buttondown_class: 'btn btn-white',
        buttonup_class: 'btn btn-white'
    });
    initWeekday('sel_start_weekday');
    initWeekday('sel_end_weekday');
    initTime('sel_start_time');
    initTime('sel_end_time');
});

function fillAdditionAttrs(result) {
    VUE_CITY.cityCode = result.data.cityCode;
    if (result.data.openRules) {
        var rules = result.data.openRules.split("@");
        $('#sel_start_weekday').val(rules[0].split("-")[0]).trigger('change');
        $('#sel_end_weekday').val(rules[0].split("-")[1]).trigger('change');
        $('#sel_start_time').val(rules[1].split("-")[0]).trigger('change');
        $('#sel_end_time').val(rules[1].split("-")[1]).trigger('change');
    }
}

function additionParam() {
    return {
        cityCode: $('#cityCode').val(),
        thumbnail: $('#thumbnail_url').attr('src'),
        detailCoverImg: $('#detailCoverImg_url').attr('src'),
        detailImg: $('#detailImg_url').attr('src'),
        openRules: $('#sel_start_weekday').val() + "-" + $('#sel_end_weekday').val() + "@" + $('#sel_start_time').val() + "-" + $('#sel_end_time').val()
    };
}

function initWeekday(ele) {
    $('#' + ele).append("<option value='周一'>周一</option>");
    $('#' + ele).append("<option value='周二'>周二</option>");
    $('#' + ele).append("<option value='周三'>周三</option>");
    $('#' + ele).append("<option value='周四'>周四</option>");
    $('#' + ele).append("<option value='周五'>周五</option>");
    $('#' + ele).append("<option value='周六'>周六</option>");
    $('#' + ele).append("<option value='周日'>周日</option>");
}

function initTime(ele) {
    var time = new Date('2020-01-01 00:00:00').getTime();
    for (var i = 0; i < 24 * 60; i++) {
        var timeStr = new Date(time + i * 1000 * 60).Format('hh:mm');
        $('#' + ele).append("<option value='" + timeStr + "'>" + timeStr + "</option>");
    }
}
