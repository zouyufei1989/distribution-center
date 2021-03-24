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
            {name: 'statusName', header: '状态'},
            {
                name: 'id', header: '操作', formatter: function (val, opt, obj) {
                    return hyperlinkeButtonFormatter('修改', 'showUpdateModal(\'' + JSON.stringify(obj) + '\')') + hyperlinkeButtonFormatter('到店', 'goConsume(' + val + ')');
                }
            },
            {name: 'customerGroupId', header: "customerGroupId", hidden: true},
            {name: 'id', header: "id", hidden: true},
        ]);
});