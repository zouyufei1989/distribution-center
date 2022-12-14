$(document).ready(function () {

    searchVue = new Vue({el: '.form-inline', data: {groupId: null}});

    initGridData("list/search", null,
        [
            {name: 'customerName', header: '客户姓名'},
            {name: 'serialNumber', header: '客户编号'},
            {name: 'phone', header: '手机号码'},
            {name: 'customerTypeName', header: '客户类型'},
            {name: 'date', header: '预约日期'},
            {
                name: 'id', header: '预约时段', formatter: function (val, opt, obj) {
                    return obj.startTime + " - " + obj.endTime;
                }
            },
            {name: 'goodsName', header: '预约活动/项目'},
            {
                name: 'statusName', header: '状态', formatter: function (val, opt, obj) {
                    if(obj.status  == 1){
                        return colorfulTextFormatter(val,'text-info');
                    }
                    if(obj.status  == 2){
                        return colorfulTextFormatter(val,'text-danger');
                    }
                    if(obj.status  == 3){
                        return colorfulTextFormatter(val,'text-warning');
                    }
                    if(obj.status  == 4){
                        return colorfulTextFormatter(val,'text-muted');
                    }
                }
            },
            {
                name: 'id', header: '操作', formatter: function (val, opt, obj) {
                    if (obj.status == 1) {
                        return hyperlinkeButtonFormatter('修改', 'showUpdateModal(\'' + JSON.stringify(obj) + '\')')
                            + hyperlinkeButtonFormatter('取消', 'cancelReservation(' + obj.id + ')', '#ed5565')
                            + hyperlinkeButtonFormatter('到店', 'goConsume(\'' + JSON.stringify(obj) + '\')', '#f8ac59');
                    }
                    return '';
                }
            },
            {name: 'customerGroupId', header: "customerGroupId", hidden: true},
            {name: 'id', header: "id", hidden: true},
        ]);

    $('#btn_toggle_type').click(function () {
        $('#tmp_grid').toggleClass("hidden");
        $('#div_calendar').toggleClass("hidden");

        if ($(this).text() === '日历模式') {
            $(this).text("列表模式");
            if (!calendar.isRendered) {
                calendar.render();
            }
            fillCalendar();
            return;
        }
        $(this).text("日历模式");

    });
});

function cancelReservation(id) {
    _ROWS_CHOOSED = [];
    jQuery("#table_list").jqGrid('resetSelection');
    jQuery("#table_list").jqGrid('setSelection', id);
    Confirm("确定要取消该预约？", function () {
        changeStatus(2);
    });
}