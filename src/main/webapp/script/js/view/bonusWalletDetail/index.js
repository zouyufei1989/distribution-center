$(document).ready(function () {

    new Vue({el:'.form-inline'});

    initGridData("../bonusWallet/queryBonusWalletDetail", null,
        [
            {name: 'customer.name', header: '客户姓名'},
            {name: 'customer.customerGroup.serialNumber', header: '客户编号'},
            {name: 'customer.phone', header: '手机号码'},
            {name: 'customer.customerGroup.typeName', header: '客户类型'},
            {name: 'bonusChange', header: '本次积分',formatter:moneyFormatter},
            {name: 'bonusRate', header: '积分比例',formatter:rateFormatter},
            {name: 'srcCustomerMoneyPay', header: '顾客消费',formatter:moneyFormatter},
            {name: 'srcCustomerMoneyAvailable', header: '未消费余额',formatter:moneyFormatter},
            {name: 'aftSumBonus', header: '总积分',formatter:moneyFormatter},
            {name: 'srcCustomerName', header: '消费客户'},
            {name: 'groupName', header: '消费门店'},
            {name: 'createDate', header: '消费时间',formatter:yyyyMMddhhmmFormatter},
            {name: 'id', header: "id", hidden: true},
        ]);

});