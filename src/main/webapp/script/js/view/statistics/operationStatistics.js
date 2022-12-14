var summaryVue;
$(document).ready(function () {
    summaryVue = new Vue({
        el: '#divSummary',
        data: {
            allCnt: 0,
            monthCnt: 0,
            consumedCnt: 0,
            consumeRate: 0
        }
    });

    new Vue({el: '#groupId'});
    new Vue({el: '#month'});

    reloadList();
});

function reloadList() {
    summary();

    $('#div_list_package').html('<table id="table_list_package"></table><div id="pager_list_package"></div>');
    goodsRank("goodsSellingRank", 2, "table_list_package", "pager_list_package");

    $('#div_list_goods').html('<table id="table_list_goods"></table><div id="pager_list_goods"></div>');
    goodsRank("goodsSellingRank", 1, "table_list_goods", "pager_list_goods");

    groupPerformancePie();
    bonusStatistics();

    $('button[data-toggle="buttons-checkbox"]:first').trigger("click")
}

function summary() {
    $.ajax({
        type: "POST",
        url: "operationSummaryInfo",
        headers: {
            "Cache-Control": "no-cache",
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        },
        data: JSON.stringify({
            month: $('#month').val(),
            groupId: $('#groupId').val(),
        }),
        async: false,
        success: function (response) {
            if (response.success === false) {
                return;
            }
            summaryVue.allCnt = response.data.allCnt;
            summaryVue.monthCnt = response.data.monthCnt;
            summaryVue.consumedCnt = response.data.consumedCnt;
            summaryVue.consumeRate = response.data.consumeRate;
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            Alert("", textStatus);
        }
    });
}

function goodsRank(url, goodsTypeId, listId, pagerId) {
    $.ajax({
        type: "POST",
        url: url,
        headers: {
            "Cache-Control": "no-cache",
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        },
        data: JSON.stringify({
            month: $('#month').val(),
            groupId: $('#groupId').val(),
            goodsTypeId: goodsTypeId,
        }),
        dataType: "json",
        async: false,
        cache: true,
        success: function (response) {
            if (response.success == false) {
                Alert("", "????????????!", "error");
                return;
            }
            initGridLocalData(response.data, [
                {name: 'goodsName', header: '????????????'},
                {name: 'groupName', header: '????????????'},
                {name: 'cnt', header: '????????????'},
                {name: 'sumMoney', header: '????????????', formatter: moneyFormatter},
                {name: 'goodsId', header: 'id', hidden: true},
            ], listId, pagerId, null, 10, false);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            Alert("", textStatus);
        }
    });
}

function groupPerformancePie() {
    $.ajax({
        url: 'groupPerformancePie',
        type: 'post',
        headers: {
            "Cache-Control": "no-cache",
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        },
        data: JSON.stringify({
            month: $('#month').val(),
            groupId: $('#groupId').val(),
        }),
        async: false,
        cache: false,
        success: function (response) {
            var chart = echarts.init(document.getElementById('groupPerformancePieChart'));
            var chartData = [
                {value: moneyFormatter(response.data.singleSumMoney), name: '????????????', src: response.data.singleSumMoney},
                {value: moneyFormatter(response.data.rechargeSumMoney), name: '??????', src: response.data.rechargeSumMoney},
                {value: moneyFormatter(response.data.packageSumMoney), name: '????????????', src: response.data.packageSumMoney},
                {value: moneyFormatter(response.data.packageConsumedMoney), name: '????????????', src: response.data.packageConsumedMoney}];
            var sum = response.data.singleSumMoney + response.data.rechargeSumMoney + response.data.packageSumMoney + response.data.packageConsumedMoney;

            option = {
                title: {
                    text: "?????? " + moneyFormatter(sum),
                    left: 'center',
                    bottom: 230
                },
                tooltip: {
                    trigger: 'item',
                    formatter: "{b} : {c} ({d}%)"
                },
                legend: {
                    bottom: '1%',
                    left: 'center',
                    formatter: function (name) {
                        var data = chartData.filter(i => i.name == name)[0];
                        return name + "|\t\t" + data.value + "\t\t" + rateFormatter(data.src * 10000 / sum);
                    }
                },
                series: [
                    {
                        type: 'pie',
                        radius: ['40%', '70%'],
                        avoidLabelOverlap: false,
                        itemStyle: {
                            borderRadius: 10,
                            borderColor: '#fff',
                            borderWidth: 2
                        },
                        label: {
                            show: false,
                        },
                        emphasis: {
                            label: {
                                show: true,
                            }
                        },
                        labelLine: {
                            show: false
                        },
                        data: chartData
                    }
                ]
            };
            formatOptions(option);
            chart.setOption(option);
        }
    });
}

function bonusStatistics() {
    $.ajax({
        url: 'bonusStatistics',
        type: 'post',
        headers: {
            "Cache-Control": "no-cache",
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        },
        data: JSON.stringify({
            month: $('#month').val(),
            groupId: $('#groupId').val(),
        }),
        async: false,
        cache: false,
        success: function (response) {
            var chart = echarts.init(document.getElementById('bonusStatistics'));
            var chartData = [
                {value: moneyFormatter(response.data.notDistributed), name: '?????????', src: response.data.notDistributed},
                {value: moneyFormatter(response.data.distributed), name: '?????????', src: response.data.distributed}];
            var sum = response.data.notDistributed + response.data.distributed;

            option = {
                title: {
                    text: "??????????????? " + moneyFormatter(sum),
                    left: 'center',
                    bottom: 230
                },
                tooltip: {
                    trigger: 'item',
                    formatter: "{b} : {c} ({d}%)"
                },
                legend: {
                    bottom: '1%',
                    left: 'center',
                    formatter: function (name) {
                        var data = chartData.filter(i => i.name == name)[0];
                        return name + "|\t\t" + data.value + "\t\t" + rateFormatter(data.src * 10000 / sum);
                    }
                },
                series: [
                    {
                        type: 'pie',
                        radius: ['40%', '70%'],
                        avoidLabelOverlap: false,
                        itemStyle: {
                            borderRadius: 10,
                            borderColor: '#fff',
                            borderWidth: 2
                        },
                        label: {
                            show: false,
                        },
                        emphasis: {
                            label: {
                                show: true,
                            }
                        },
                        labelLine: {
                            show: false
                        },
                        data: chartData
                    }
                ]
            };
            formatOptions(option);
            chart.setOption(option);
        }
    });
}

function groupPerformance(e, statisticType) {
    $('button[data-toggle="buttons-checkbox"]').removeClass("active");
    $(e).addClass("active");

    var request = {
        groupId: $('#groupId').val(),
        type: $('#groupId').val() ? (statisticType === 'month' ? 2 : 3) : 1,
        month: statisticType === 'month' ? new Date().Format("yyyy-MM") : '',
        year: statisticType === 'year' ? new Date().Format("yyyy") : '',
    };

    $.ajax({
        url: 'groupPerformance',
        type: 'post',
        headers: {
            "Cache-Control": "no-cache",
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        },
        data: JSON.stringify(request),
        async: false,
        cache: false,
        success: function (response) {
            var chart = echarts.init(document.getElementById('chart-container'));

            option = {
                toolbox: {
                    feature: {
                        saveAsImage: {show: true}
                    }
                },
                xAxis: [{
                    type: 'category',
                    data: request.groupId ? response.data.xaxisData : response.data.xaxisData.map(i => i.split("@")[1])
                }],
                yAxis: {
                    type: 'value'
                },
                tooltip: {
                    trigger: 'item',
                    formatter: "{b} : {c} ???"
                },
                series: [
                    {
                        data: response.data.yaxisData.map(i => moneyFormatter(i)),
                        type: 'bar'
                    }
                ]
            };
            formatOptions(option);
            chart.setOption(option);
        }
    });
}