$(document).ready(function () {

    new Vue({el: '.param_row'});

    initGridData("list/search", null,
        [
            {name: 'name', header: '客户姓名'},
            {name: 'customerGroup.serialNumber', header: '客户编号'},
            {name: 'customerGroup.typeName', header: '客户类型'},
            {name: 'phone', header: '联系电话'},
            {name: 'customerGroup.bonusPlanName', header: '积分方案'},
            {name: 'packageCount', header: '已购项目'},
            {name: 'wallet.sumMoney', header: '股本/余额'},
            {name: 'groupName', header: '所属门店'},
            {name: 'customerGroup.expiredDate', header: '股东到期时间'},
            {name: 'createDate', header: '创建时间'},
            {name: 'customerGroup.statusName', header: '状态'},
            {name: 'index', header: '操作'},
            {name: 'id', header: "id", hidden: true},
        ]);

});