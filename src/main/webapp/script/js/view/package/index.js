var searchVue;
$(document).ready(function () {

    searchVue = new Vue({el: '.param_row', data: {groupId: null}});

    initGridData("list/search", null,
        [
            {name: 'name', header: '项目名称', width: 100},
            {name: 'serialNumber', header: '项目编号', width: 100},
            {name: 'desc', header: '项目简介', width: 200},
            {name: 'cnt', header: '内含次数'},
            {name: 'goodsItemTypeCnt', header: '包含商品'},
            {name: 'sumPrice', header: '价格(元)', formatter: moneyFormatter},
            {name: 'broughtCnt', header: '已够数量'},
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
                ], subgrid_table_id, subgrid_pager_id,true);
            console.log(result.data.items.length)
        }
    });
}