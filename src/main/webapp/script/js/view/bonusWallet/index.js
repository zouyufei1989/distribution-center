$(document).ready(function () {

    new Vue({el:'.param_row'});

    initGridData("list/search", null,
        [
            {name: 'customer.name', header: '客户姓名'},
            {name: 'customer.customerGroup.serialNumber', header: '客户编号'},
            {name: 'customer.phone', header: '手机号码'},
            {name: 'customer.customerGroup.typeName', header: '客户类型'},
            {name: 'pendingBonus', header: '可发放积分',formatter:moneyFormatter},
            {name: 'groupName', header: '所属门店'},
            {name: 'lastDistributionDate', header: '上次发放时间', formatter: dateFormatter},
            {name: 'customer.customerGroup.id', header: "customerGroupId", hidden: true},
            {name: 'id', header: "id", hidden: true},
        ]);

});