var attrs = ['name', 'cityCode', 'address', 'ownerName', 'ownerPhone', 'status', 'desc', 'thumbnail', 'detailCoverImg', 'index', 'openRules', 'lng', 'lat'];
var VUE_CITY;
var mapModel;


$(document).ready(function () {
    new Vue({el: '#status'});
    new Vue({el: '#thumbnailModal'});
    new Vue({el: '#detailCoverImgModal'});

    $("#index").TouchSpin({
        verticalbuttons: true,
        buttondown_class: 'btn btn-white',
        buttonup_class: 'btn btn-white'
    });
    initWeekday('sel_start_weekday');
    initWeekday('sel_end_weekday');
    initTime('sel_start_time','09:00');
    initTime('sel_end_time','21:00');


    VUE_CITY = new Vue({el: '#cityCode', data: {cityCode: null, cityName: null}});
    $('#cityCode').on('change', function () {
        var city = $('#cityCode').select2('data')[0];
        if (mapModel) {
            if($('#cityCode').select2('data')[0].id != VUE_CITY.cityCode){
                VUE_CITY.cityCode = city.id;
                VUE_CITY.cityName = city.text;
                mapModel.api.setCity(city.text);
            }
        } else {
            mapModel = createMapModel("mapContainer", city.text);
            mapModel.api.initAutoCompleteControl('searchAddress', function (e) {
                $('#lng').val(e.poi.location.lng);
                $('#lat').val(e.poi.location.lat);
                refreshStationMarker();
            }, city.text);
            VUE_CITY.cityCode = city.id;
            VUE_CITY.cityName = city.text;
        }
    });
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
    $('#thumbnail_url').attr('src', result.data.thumbnail);
    $('#detailCoverImg_url').attr('src', result.data.detailCoverImg);
    refreshStationMarker();
}

function additionParam() {
    return {
        cityCode: $('#cityCode').val(),
        thumbnail: $('#thumbnail_url').attr('src'),
        detailCoverImg: $('#detailCoverImg_url').attr('src'),
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

function initTime(ele,def) {
    var time = new Date('2020-01-01 00:00:00').getTime();
    for (var i = 0; i < 24 * 60; i+=5) {
        var timeStr = new Date(time + i * 1000 * 60).Format('hh:mm');
        if(timeStr === def){
            $('#' + ele).append("<option selected value='" + timeStr + "'>" + timeStr + "</option>");
            continue;
        }
        $('#' + ele).append("<option value='" + timeStr + "'>" + timeStr + "</option>");
    }
}

function refreshStationMarker() {
    mapModel.api.clearAll();
    var marker = mapModel.api.addMarker($('#lng').val(), $('#lat').val(), ICON_ARR['station'].icon, ICON_ARR['station'].offset, '', true);
    mapModel.event.onMarkerDragend(marker, function (e) {
        var eventLnglat = e.target.getPosition();
        $('#lat').val(eventLnglat.lat);
        $('#lng').val(eventLnglat.lng);
    });
    mapModel.entity.map.setFitView();
}