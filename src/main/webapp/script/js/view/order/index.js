$(document).ready(function () {

    new Vue({el: '.form-inline'});

    initGridData("list/search", null,
        [
            {name: 'customerName', header: '客户姓名'},
            {name: 'serialNumber', header: '客户编号'},
            {name: 'customerTypeName', header: '客户类型'},
            {name: 'goodsTypeName', header: '消费类型'},
            {name: 'sumMoney', header: '应收金额', formatter: moneyFormatter},
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
            {name: 'actuallyMoney', header: "actuallyMoney", hidden: true, formatter: moneyFormatter},
            {name: 'parentName', header: "parentName", hidden: true},
            {name: 'phone', header: "phone", hidden: true},
            {
                name: 'items', header: "items", formatter: function (val) {
                    return JSON.stringify(val)
                }, hidden: true
            },
        ]);
});

function showOrderDetail(orderId) {
    var rowSelect = $("#table_list").jqGrid("getRowData", orderId);
    detailModal.order = rowSelect;
    detailModal.orderItems = JSON.parse(rowSelect.items);
    $('#detailModal').modal('show')
}

function subGridRowExpanded(subgrid_id, row_id, rowExpanded) {
    $.ajax({
        url: 'queryOrderConsumption',
        type: 'post',
        data: {
            'orderConsumption.orderId': row_id
        },
        async: true,
        cache: false,
        success: function (result) {
            if (result.success == false) {
                Alert("", "失败！", "error");
                return;
            }
            var subgrid_table_id, subgrid_pager_id;
            subgrid_table_id = subgrid_id + "_t";
            subgrid_pager_id = subgrid_id + "_p";
            $("#" + subgrid_id).html("<table id='" + subgrid_table_id + "' class='scroll'></table><div id='" + subgrid_pager_id + "'></div>");

            initGridLocalData(result.data,
                [
                    {name: 'goodsName', header: '商品名称'},
                    {name: 'goodsPrice', header: '商品价格', formatter: moneyFormatter},
                    {name: 'cnt', header: '使用次数'},
                    {
                        name: 'aftAvailableCnt', header: '剩余次数', formatter: function (val) {
                            return val
                        }
                    },
                    {name: 'createDate', header: '使用时间', formatter: yyyyMMddhhmmFormatter},
                    {name: 'id', header: "id", hidden: true},
                ], subgrid_table_id, subgrid_pager_id);
        }
    });
}