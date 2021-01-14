var searchVue;
$(document).ready(function () {

    searchVue = new Vue({el: '.param_row', data: {groupId: null}});
    $('.param_row [data-type="groupSelect"]').change(function () {
        searchVue.groupId = $('.param_row [data-type="groupSelect"]').val();
    });

    initGridData("list/search", null,
        [
            {name: 'name', header: '商品名称'},
            {name: 'groupName', header: '所属门店'},
            {name: 'desc4SingleShow', header: '商品描述', width: 200},
            {name: 'profitRate4SingleShow', header: '毛利率', width: 100},
            {name: 'price4SingleShow', header: '单价(元)', width: 100},
            {name: 'unit4SingleShow', header: '单位'},
            {name: 'goodsTagName4SingleShow', header: '标签'},
            {
                name: 'hasDetail', header: '商品详情展示', formatter: function (val, opt, obj) {
                    if (val) {
                        return hyperlinkeButtonFormatter("查看", "showDetail(" + obj.id + ")");
                    }
                    return '';
                }
            },
            {name: 'createDate', header: '创建时间', formatter: yyyyMMddhhmmFormatter},
            {name: 'statusName', header: '状态'},
            {
                name: 'detail', header: "detail", formatter: function (val, opt, obj) {
                    return obj.items[0].detail || '';
                }, hidden: true
            },
            {name: 'id', header: "id", hidden: true},
        ]);

});

function showDetail(rowId) {
    $('#detailViewModal').modal('show');
    $('#detailView').html(jQuery("#table_list").jqGrid('getCell', rowId, 'detail') || '');

}