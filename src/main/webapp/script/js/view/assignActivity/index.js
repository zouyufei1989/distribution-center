var searchVue;
$(document).ready(function () {

    searchVue = new Vue({el: '.param_row', data: {groupId: null}});
    new Vue({
        el: '#divSummary',
        data: {
            data: {cnt: 0, assignCustomerSum: 0, totalPrice: 0}
        },
        mounted(){
            var _this = this;
            $.ajax({
                url:'querySummary',
                type: 'post',
                data: {},
                async: true,
                cache: false,
                success: function (result) {
                    _this.data = result.data[0];
                }
            });
        }
    });

    initGridData("list/search", null,
        [
            {name: 'createDate', header: '赠送时间',formatter:yyyyMMddhhmmssFormatter},
            {name: 'creator', header: '操作者'},
            {name: 'goodsName', header: '活动名称',width:120},
            {name: 'containGoodsCnt', header: '内涵商品数',width:120},
            {name: 'sumPrice4Show', header: '价值',width:120},
            {name: 'assignCustomerCnt', header: '赠送股东数',width:120},
            {name: 'id', header: "id", hidden: true},
        ]);
});
