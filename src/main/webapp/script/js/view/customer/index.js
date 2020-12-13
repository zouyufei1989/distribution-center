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
            {name: 'sumMoney4Show', header: '股本/余额'},
            {name: 'groupName', header: '所属门店'},
            {name: 'customerGroup.expireDate', header: '股东到期时间'},
            {name: 'createDate', header: '创建时间'},
            {name: 'customerGroup.statusName', header: '状态'},
            {
                name: 'id', header: '操作', formatter: function (val, opt, obj) {
                    return hyperlinkeButtonFormatter('充值','goRecharge('+obj.customerGroup.id+')','#1ab394') + hyperlinkeButtonFormatter('消费','','#f8ac59')
                }
            },
            {name: 'customerGroup.groupId', header: "groupId", hidden: true},
            {name: 'customerGroup.bonusPlanId', header: "bonusPlanId", hidden: true},
            {name: 'customerGroup.type', header: "type", hidden: true},
            {name: 'customerGroup.id', header: "type", key: true, hidden: true},
            {
                name: 'id', header: "id", hidden: true, formatter: function (val, opt, obj) {
                    return obj.customerGroup.id;
                }
            },
        ]);
});

function onSelectRow(row_id, status) {
    if (_ROWS_CHOOSED.length === 1 && _ROWS_CHOOSED[0]['customerGroup.type'] == 2) {
        $('#btn_bonus_plan').removeAttr('disabled');
        $('#btn_bonus_plan').addClass('btn-outline');
    } else {
        $('#btn_bonus_plan').attr('disabled', 'disabled');
        $('#btn_bonus_plan').removeClass('btn-outline');
    }
}