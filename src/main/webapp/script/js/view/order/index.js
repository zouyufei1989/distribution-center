$(document).ready(function () {

    new Vue({el: '.form-inline'});

    initGridData("list/search", null,
        [
            {name: 'customerName', header: '客户姓名'},
            {name: 'serialNumber', header: '客户编号'},
            {name: 'customerTypeName', header: '客户类型'},
            {name: 'goodsTypeName', header: '消费类型'},
            {name: 'sumMoney', header: '应收金额',formatter:moneyFormatter},
            {name: 'payTypeName', header: '支付方式'},
            {name: 'groupName', header: '消费门店'},
            {name: 'createDate', header: '消费时间', formatter: yyyyMMddhhmmssFormatter},
            {name: 'creator', header: '操作人'},
            {
                name: 'id', header: '操作', formatter: function (val) {
                    return hyperlinkeButtonFormatter('查看详情', 'showOrderDetail(' + val + ')')
                }
            },
            {name: 'id', header: "id", hidden: true},
            {name: 'actuallyMoney', header: "actuallyMoney", hidden: false,formatter:moneyFormatter},
            {name: 'parentName', header: "parentName", hidden: false},
            {name: 'phone', header: "phone", hidden: false},
            {name: 'items', header: "items",formatter:function(val){
                return JSON.stringify(val)
            }, hidden: false},
        ]);
});

function showOrderDetail(orderId){
    var rowSelect = $("#table_list").jqGrid("getRowData", orderId);
    detailModal.order = rowSelect;
    detailModal.orderItems = JSON.parse(rowSelect.items);
    $('#detailModal').modal('show')
}