$(document).ready(function () {
    new Vue({el: '#groupId'});
    new Vue({el: '#month'});

    reloadList();
});

function reloadList() {
    $("#div_list").empty();
    $("#div_list").html('<table id="table_list"></table>').append('<div id="pager_list"></div>');

    $.ajax({
        type: "POST",
        url: "queryShareHolderStatistics",
        headers: {
            "Cache-Control": "no-cache",
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        },
        data: JSON.stringify({
            month: $('#month').val(),
            groupId: $('#groupId').val(),
            sortType:$('input[name="sortType"]:checked').val()
        }),
        async: true,
        success: function (response) {
            if (response.success === false) {
                return;
            }

            initGridLocalData(response.data, [
                {name: 'shareHolderName', header: '股东姓名'},
                {name: 'phone', header: '手机号'},
                {name: 'groupName', header: '所属门店'},
                {name: 'allCnt', header: '顾客总数'},
                {name: 'monthCnt', header: '本月新增顾客数'},
                {name: 'consumeCnt', header: '已成交顾客数'},
                {name: 'consumeRate4Show', header: '成交率'},
                {name: 'availableBonus4Show', header: '积分'},
                {name: 'id', header: 'id', hidden: true},
            ]);

        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            Alert("", textStatus);
        }
    });
}
