$(document).ready(function () {
    new Vue({el: '#startDateByRes'});
    new Vue({el: '#endDateByRes'});
    new Vue({el: '#userIdByRes'});
    new Vue({el: '#startDateByUser'});
    new Vue({el: '#endDateByUser'});
    new Vue({el: '#userIdByUser'});

    visitLogStsByResource();
    visitLogStsByUser();
});

function visitLogStsByResource() {

    $("#div_visitLogStsByResource").html('<table id="tbl_chart_by_resource"></table>').append('<div id="pager_chart_by_resource"></div>');

    $.ajax({
        type: "POST",
        url: "visitLogStsByResource",
        data: {
            startDate: $('#startDateByRes').val(),
            endDate: $('#endDateByRes').val(),
            userId: $('#userIdByRes').val()
        },
        async: true,
        success: function (data) {
            if (data.success === false) {
                return;
            }

            initGridLocalData(data.rows, [
                {name: 'moduleName', header: '模块名称'},
                {name: 'resourceName', header: '功能名称'},
                {name: 'maxDuration', header: '最长耗时'},
                {name: 'minDuration', header: '最短耗时'},
                {name: 'avgDuration', header: '平均耗时'},
                {name: 'count4Read', header: '查看次数'},
                {name: 'count4Edit', header: '编辑次数'},
                {name: 'count4Import', header: '导入次数'},
                {name: 'count4Export', header: '导出次数'},
                {
                    name: 'count4Export', header: '总次数', formatter: function (val, opt, obj) {
                        return obj.count4Read + obj.count4Edit + obj.count4Import + obj.count4Export;
                    }
                },
            ], 'tbl_chart_by_resource', 'pager_chart_by_resource');

        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            Alert("", textStatus);
        }
    });
}

function visitLogStsByUser() {

    $("#div_visitLogStsByUser").html('<table id="tbl_chart_by_user"></table>').append('<div id="pager_chart_by_user"></div>');

    $.ajax({
        type: "POST",
        url: "visitLogStsByUser",
        data: {
            startDate: $('#startDateByRes').val(),
            endDate: $('#endDateByRes').val(),
            userId: $('#userIdByRes').val()
        },
        async: true,
        success: function (data) {
            if (data.success === false) {
                return;
            }

            initGridLocalData(data.rows, [
                {name: 'userName', header: '访问者'},
                {name: 'count4Read', header: '查看次数'},
                {name: 'count4Edit', header: '编辑次数'},
                {name: 'count4Import', header: '导入次数'},
                {name: 'count4Export', header: '导出次数'},
                {
                    name: 'count4Export', header: '总次数', formatter: function (val, opt, obj) {
                        return obj.count4Read + obj.count4Edit + obj.count4Import + obj.count4Export;
                    }
                },
            ], 'tbl_chart_by_user', 'pager_chart_by_user');

        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            Alert("", textStatus);
        }
    });
}
