var reservationPeriodModalVue;
var switchery;
$(document).ready(function () {
    new Vue({el: '.param_row'});

    initGridData("goodsList", null,
        [
            {name: 'name', header: '名称'},
            {name: 'typeName', header: '类型'},
            {name: 'groupName', header: '所属门店'},
            {
                name: 'periodCnt', header: '时段数量', formatter: function (val, opt, obj) {
                    return hyperlinkeButtonFormatter(val, "showPeriodList(" + obj.id + ",'" + obj.name + "')");
                }, width: 120
            },
            {name: 'id', header: "id", hidden: true},
        ]);

    $('#btn_reservation_period').click(function () {
        if(_ROWS_CHOOSED.length!=1){
            Alert('','请选择一条编辑数据','error');
            return;
        }
        updatePeriodListModalVue.goodsId = _ROWS_CHOOSED[0].id;
        updatePeriodListModalVue.typeName = _ROWS_CHOOSED[0].typeName;
        updatePeriodListModalVue.groupName = _ROWS_CHOOSED[0].groupName;
        updatePeriodListModalVue.goodsName = _ROWS_CHOOSED[0].name;
    });

});
