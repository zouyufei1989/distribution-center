var searchVue;
var detailModalVue;
$(document).ready(function () {

    searchVue = new Vue({el: '.param_row', data: {groupId: null}});

    initGridData("list/search", null,
        [
            {name: 'customerName', header: '客户姓名', width: 130},
            {name: 'serialNumber', header: '客户编号', width: 100},
            {name: 'phone', header: '手机号码'},
            {name: 'customerTypeName', header: '客户类型'},
            {name: 'packageCount', header: '已购项目'},
            {name: 'usedCount', header: '已使用次数'},
            {name: 'availableCount', header: '剩余次数'},
            {
                name: 'id', header: '操作', formatter(val, opt, obj) {
                    return hyperlinkeButtonFormatter('查看记录', 'showDetail("' + obj.customerName + '","' + obj.goodsNames + '","' + obj.orderIds + '")')
                }
            },
            {name: 'goodsNames', header: 'goodsNames'},
            {name: 'orderIds', header: 'orderIds'},
            {name: 'id', header: "id", hidden: true},
        ]);

    detailModalVue = new Vue({
        el: '#detailModal',
        data: {
            goodsNames: [],
            orderIds: [],
            detailList: [],
            tip: ""
        },
        methods: {
            show() {
                $('#detailModal').modal('show');
                this.$nextTick(function () {
                    $('#detailModal li:first').tab("show");
                })
                this.queryDetail(this.orderIds[0]);
            },
            formatCreateDate(date) {
                return timeFormatter(date, "yyyy-MM-dd hh:mm:ss")
            },
            queryDetail(orderId) {
                var _this = this;
                $.ajax({
                    url: '../order/queryOrderConsumption',
                    type: 'post',
                    data: {
                        orderId: orderId,
                        distinctByConsumption: true
                    },
                    async: true,
                    cache: false,
                    success: function (response) {
                        _this.detailList = response.data;
                    }
                });
            }
        }
    });
});

function showDetail(customerName, goodsNames, orderIds) {
    detailModalVue.goodsNames = goodsNames ? goodsNames.split(",") : [];
    detailModalVue.orderIds = orderIds ? orderIds.split(",") : [];
    detailModalVue.tip = customerName + "共" + detailModalVue.orderIds.length + "个已购项目";
    detailModalVue.show();
}