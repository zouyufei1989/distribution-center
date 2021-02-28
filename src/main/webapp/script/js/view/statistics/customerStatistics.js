var summaryVue;
$(document).ready(function () {
    new Vue({el: '#groupId'});
    new Vue({el: '#month'});

    summaryVue = new Vue({
        el: '#divSummary',
        data: {
            arrivalCnt: 0,
            pendingCnt: 0,
            newArrivalCnt: 0,
            newPendingCnt: 0
        }
    });

    reloadList();
});

function reloadList() {
    $("#div_list").empty();
    $("#div_list").html('<table id="table_list"></table>').append('<div id="pager_list"></div>');

    $.ajax({
        type: "POST",
        url: "queryCustomerStatistics",
        headers: {
            "Cache-Control": "no-cache",
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        },
        data: JSON.stringify({
            month: $('#month').val(),
            groupId: $('#groupId').val(),
        }),
        async: true,
        success: function (response) {
            if (response.success === false) {
                return;
            }

            initGridLocalData(response.data, [
                {name: 'customerName', header: '顾客姓名'},
                {name: 'phone', header: '手机号'},
                {name: 'parentName', header: '所属股东'},
                {name: 'monthCnt', header: '本月到店次数'},
                {name: 'lastConsumeDate', header: '最近一次到店时间'},
                {name: 'id', header: 'id', hidden: true},
            ]);

        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            Alert("", textStatus);
        }
    });

    summary();
}

function summary() {

    $.ajax({
        type: "POST",
        url: "queryCustomerSummaryStatistics",
        headers: {
            "Cache-Control": "no-cache",
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        },
        data: JSON.stringify({
            month: $('#month').val(),
            groupId: $('#groupId').val(),
        }),
        async: true,
        success: function (response) {
            if (response.success === false) {
                return;
            }
            summaryVue.arrivalCnt = response.data.arrivalCnt;
            summaryVue.pendingCnt = response.data.pendingCnt;
            summaryVue.newArrivalCnt = response.data.newArrivalCnt;
            summaryVue.newPendingCnt = response.data.newPendingCnt;
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            Alert("", textStatus);
        }
    });
}
