$(document).ready(function () {
    new Vue({el: '#startDate'});
    new Vue({el: '#endDate'});

    reloadList();
});

function reloadList() {
    $("#div_list").html('<table id="table_list"></table>').append('<div id="pager_list"></div>');

    $.ajax({
        type: "POST",
        url: "selectSearchListGroupByMethod",
        data: {
            startDate: $('#startDate').val(),
            endDate: $('#endDate').val(),
            method: $('#method').val()
        },
        async: true,
        success: function (data) {
            if (data.success === false) {
                return;
            }

            initGridLocalData(data.rows, [
                {name: 'method', header: '方法名'},
                {name: 'cnt', header: '异常次数'}
            ]);

        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            Alert("", textStatus);
        }
    });
}

function subGridRowExpanded(subgrid_id, row_id, rowExpanded) {
    var subgrid_table_id, subgrid_pager_id;

    subgrid_table_id = subgrid_id + "_t";
    subgrid_pager_id = subgrid_id + "_p";
    $("#" + subgrid_id).html("<table id='" + subgrid_table_id + "' class='scroll'></table><div id='" + subgrid_pager_id + "'></div>");

    initGridData("list/search", {
            startDate: $('#startDate').val(),
            endDate: $('#endDate').val(),
            method: rowExpanded.method
        },
        [
            {name: 'errorMessage', header: '错误信息'},
            {name: 'stackTrace', header: '堆栈信息',width:300},
            {name: 'id', header: "id", hidden: true},
        ], subgrid_table_id, subgrid_pager_id);
}