$(document).ready(function () {

    new Vue({el:'.form-inline'});

    initGridData("../wallet/queryWalletRechargeRecord", null,
        [
            {name: 'customer.name', header: '客户姓名'},
            {name: 'customer.customerGroup.serialNumber', header: '客户编号'},
            {name: 'customer.phone', header: '手机号码'},
            {name: 'customer.customerGroup.typeName', header: '客户类型'},
            {name: 'befAvailableMoney', header: '充值前余额',formatter:moneyFormatter},
            {name: 'moneyChange', header: '本次充值',formatter:moneyFormatter},
            {name: 'groupName', header: '消费门店'},
            {name: 'createDate', header: '充值时间', formatter: dateFormatter},
            {name: 'creator', header: '操作人'},
            {name: 'id', header: "id", hidden: true},
        ]);

});