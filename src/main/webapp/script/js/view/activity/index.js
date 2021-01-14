var searchVue;
$(document).ready(function () {

    searchVue = new Vue({el: '.param_row', data: {groupId: null}});

    initGridData("list/search", null,
        [
            {name: 'serialNumber', header: '活动编号',width:130},
            {name: 'name', header: '活动名称',width:100},
            {name: 'sumPrice4Show', header: '价值'},
            {name: 'goodsItemTypeCnt', header: '内涵商品数'},
            {name: 'assignCnt', header: '已赠送数量'},
            {name: 'desc', header: '备注'},
            {name: 'createDate', header: '创建时间', formatter: yyyyMMddhhmmFormatter},
            {name: 'expireDate', header: '活动截止时间'},
            {name: 'statusName', header: '状态'},
            {name: 'sumPrice', header: "sumPrice", hidden: true},
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
                    {name: 'cnt', header: '次数'},
                    {name: 'goodsTagName', header: '标签'},
                    {name: 'createDate', header: '创建时间', formatter: yyyyMMddhhmmFormatter},
                    {name: 'id', header: "id", hidden: true},
                ], subgrid_table_id, subgrid_pager_id,true);
            console.log(result.data.items.length)
        }
    });
}