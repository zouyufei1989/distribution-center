var map4ShowModel;
var group4Show;

$(document).ready(function () {

    new Vue({el: '.param_row'});

    initGridData("list/search", null,
        [
            {name: 'name', header: '门店名称'},
            {name: 'cityName', header: '所在城市'},
            {
                name: 'address', header: '详细地址', formatter: function (val, opt, obj) {
                    return hyperlinkeButtonFormatter(val, "showGroupOnMap(" + obj.lng + "," + obj.lat + ")");
                }, width: 120
            },
            {name: 'ownerName', header: '负责人'},
            {name: 'ownerPhone', header: '店铺电话'},
            {name: 'thumbnail', header: '缩略图', formatter: imgPreViewFormatter},
            {
                name: 'detailImg', header: '详情图片列表', formatter: function (val, opt, obj) {
                    var cnt = 0;
                    if (val) {
                        cnt = val.split(";").length;
                    }
                    return hyperlinkeButtonFormatter(cnt, "showCoverImgModal('" + val + "'," + obj.id + ")");
                }
            },
            {
                name: 'videoList', header: '介绍视频列表', formatter: function (val, opt, obj) {
                    var cnt = 0;
                    if (val) {
                        cnt = JSON.parse(val).length;
                    }
                    return hyperlinkeButtonFormatter(cnt, "showVideoListModal('" + val + "'," + obj.id + ")");
                }
            },
            {name: 'openRules4Show', header: '营业时间', width: 120},
            {name: 'index', header: '门店排序'},
            {name: 'statusName', header: '状态'},
            {name: 'createDate', header: '创建日期', formatter: yyyyMMddhhmmFormatter},
            {name: 'id', header: "id", hidden: true},
        ]);

    bindModalShow('map4ShowModal', function () {
        if (!map4ShowModel) {
            map4ShowModel = createMapModel("map4Show");
        }
        map4ShowModel.api.clearAll();
        map4ShowModel.api.addMarker(group4Show.lng, group4Show.lat, ICON_ARR['station'].icon, ICON_ARR['station'].offset, '', false);
        map4ShowModel.entity.map.setFitView();
    }, 1);
});

function showGroupOnMap(lng, lat) {
    if (!lng || !lat) {
        Alert('', '暂无门店经纬度信息', '');
        return;
    }
    group4Show = {lng: lng, lat: lat};
    $('#map4ShowModal').modal('show');
}

function showCoverImgModal(detailImgs, groupId) {
    coverImgModalVue.groupId = groupId;
    coverImgModalVue.detailImgList = detailImgs.split(';');
    coverImgModalVue.show();
}

function showVideoListModal(videoList, groupId) {
    videoListModalVue.groupId = groupId;
    videoListModalVue.videoList = videoList ? JSON.parse(videoList) : [];
    videoListModalVue.show();
}