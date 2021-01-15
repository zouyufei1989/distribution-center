$(document).ready(function () {

    new Vue({el:'.form-inline'});

    initGridData("../bonusWallet/queryBonusDistributions", null,
        [
            {name: 'customer.name', header: '客户姓名'},
            {name: 'customer.customerGroup.serialNumber', header: '客户编号'},
            {name: 'customer.phone', header: '手机号码'},
            {name: 'customer.customerGroup.typeName', header: '客户类型'},
            {name: 'befAvailableBonus', header: '发放前积分',formatter:moneyFormatter},
            {name: 'bonusChange', header: '本次发放积分',formatter:moneyFormatter},
            {name: 'aftAvailableBonus', header: '剩余积分',formatter:moneyFormatter},
            {name: 'groupName', header: '所属门店'},
            {name: 'createDate', header: '发放时间', formatter: yyyyMMddhhmmFormatter},
            {name: 'creator', header: '操作人'},
            {name: 'id', header: "id", hidden: true},
        ]);

});