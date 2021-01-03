var searchVue;
$(document).ready(function () {

    searchVue = new Vue({el: '.param_row', data: {groupId: null}});

    initGridData("list/search", null,
        [
            {name: 'name', header: '套餐名称', width: 100},
            {name: 'groupName', header: '所属门店', width: 100},
            {name: 'desc', header: '套餐描述', width: 200},
            {name: 'sumPrice', header: '价格(元)', formatter:moneyFormatter},
            {name: 'cnt', header: '包含次数'},
            {name: 'goodsItemTypeCnt', header: '商品种类'},
            {name: 'createDate', header: '创建时间', formatter: yyyyMMddhhmmFormatter},
            {name: 'statusName', header: '状态'},
            {name: 'id', header: "id", hidden: true},
        ]);
});

function subGridRowExpanded(subgrid_id, row_id, rowExpanded) {
    $.ajax({
        url: 'findById',
        type: 'post',
        data: {
            id: row_id
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

            initGridLocalData(result.data.items,
                [
                    {name: 'name', header: '商品名称'},
                    {name: 'desc', header: '商品描述'},
                    {name: 'profitRate4Show', header: '毛利率'},
                    {name: 'price4Show', header: '单价(元)'},
                    {name: 'unit', header: '单位'},
                    {name: 'goodsTagName', header: '标签'},
                    {name: 'createDate', header: '创建时间', formatter: yyyyMMddhhmmFormatter},
                    {name: 'id', header: "id", hidden: true},
                ], subgrid_table_id, subgrid_pager_id);
            console.log(result.data.items.length)
        }
    });
}