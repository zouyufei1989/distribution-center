$(document).ready(function () {

    new Vue({el:'.param_row'});

    initGridData("list/search", null,
        [
            {name: 'name', header: '门店名称'},
            {name: 'cityName', header: '所在城市'},
            {name: 'address', header: '详细地址'},
            {name: 'ownerName', header: '负责人'},
            {name: 'ownerPhone', header: '店铺电话'},
            {name: 'desc', header: '店铺简介'},
            {name: 'thumbnail', header: '缩略图', formatter: imgPreViewFormatter},
            {name: 'detailCoverImg', header: '详情封面图', formatter: imgPreViewFormatter},
            {name: 'detailImg', header: '详情图片', formatter: imgPreViewFormatter},
            {
                name: 'openRules', header: '营业时间', formatter: function (val) {
                    if (!val) {
                        return "";
                    }
                    var weekday = val.split('@')[0].split("-");
                    var timespan = val.split('@')[1].split("-");
                    return weekday[0] + " 至 " + weekday[1] + "<br>" + timespan[0] + " 至 " + timespan[1];
                }
            },
            {name: 'index', header: '门店排序'},
            {name: 'statusName', header: '状态'},
            {name: 'createDate', header: '创建日期', formatter: dateFormatter},
            {name: 'id', header: "id", hidden: true},
        ]);

});