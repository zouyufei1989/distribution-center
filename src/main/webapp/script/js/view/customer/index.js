var packagesVue;
$(document).ready(function () {

    new Vue({el: '#paramForm'});
    packagesVue = new Vue({
        el: '#packagesModal',
        data: {name: '', packages: []},
        methods: {
            formatCreateDate(val) {
                return yyyyMMddhhmmFormatter(val);
            }
        }
    });

    initGridData("list/search", null,
        [
            {name: 'name', header: '客户姓名'},
            {name: 'customerGroup.serialNumber', header: '客户编号'},
            {name: 'customerGroup.typeName', header: '客户类型'},
            {name: 'phone', header: '联系电话'},
            {name: 'customerGroup.bonusPlanName', header: '积分方案'},
            {
                name: 'customerGroup.packageCount', header: '已购项目', formatter: function (val, opt, obj) {
                    if (val) {
                        return hyperlinkeButtonFormatter(val + '个', 'showPackageList(' + obj.customerGroup.id + ',"' + obj.name + '")')
                    }
                    return '';
                }
            },
            {name: 'sumMoney4Show', header: '股本/余额'},
            {name: 'groupName', header: '所属门店'},
            {name: 'customerGroup.expireDate', header: '股东到期时间'},
            {name: 'createDate', header: '创建时间', formatter: yyyyMMddhhmmFormatter},
            {name: 'customerGroup.statusName', header: '状态'},
            {
                name: 'myCustomerCnt', header: '我的客源', formatter: function (val, opt, obj) {
                    return hyperlinkeButtonFormatter(val, 'showMyCustomer(' + obj.customerGroup.id + ',"' + obj.name + '")')
                }
            },
            {
                name: 'id', header: '操作', formatter: function (val, opt, obj) {
                    if (obj.customerGroup.type == 2) {
                        return hyperlinkeButtonFormatter('充值', 'goRecharge(' + obj.customerGroup.id + ')', '#1ab394') + hyperlinkeButtonFormatter('消费', 'goConsume(' + obj.customerGroup.id + ')', '#f8ac59');
                    }
                    return hyperlinkeButtonFormatter('消费', 'goConsume(' + obj.customerGroup.id + ')', '#f8ac59')
                }, width: 100
            },
            {name: 'customerGroup.groupId', header: "groupId", hidden: true},
            {name: 'customerGroup.bonusPlanId', header: "bonusPlanId", hidden: true},
            {name: 'customerGroup.type', header: "type", hidden: true},
            {name: 'customerGroup.orderToConsumeCnt', header: "orderToConsumeCnt", hidden: true},
            {name: 'bonusWallet.availableBonus', header: "availableBonus", hidden: true},
            {name: 'wallet.sumMoney', header: "sumMoney", hidden: true},
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

function showPackageList(id, name) {
    packagesVue.name = name;
    $.ajax({
        url: '../order/list/search',
        data: {
            'customer.customerGroup.id': id,
            'order.goodsTypeId': 2
        },
        type: 'post',
        async: false,
        cached: false,
        success: function (result) {
            packagesVue.packages = result.rows.filter(i => i.items.length > 0 && (i.status == 2 || i.status == 3))
            $('#packagesModal').modal('show');
        }
    });
}

function showMyCustomer(id, parentName) {
    $('#sp_parentName').html(parentName);
    initGridData("list/search", {parentId: id},
        [
            {name: 'name', header: '客户姓名'},
            {name: 'customerGroup.serialNumber', header: '客户编号'},
            {name: 'phone', header: '联系电话'},
            {name: 'createDate', header: '创建时间', formatter: yyyyMMddhhmmFormatter},
            {name: 'customerGroup.statusName', header: '状态'}]
        , "table_list_mycustomer", "pager_list_mycustomer");
    $('#myCustomerModal').modal('show');
}

function deleteTip() {
    var tip = "该用户尚";
    var typs = [];
    if (_ROWS_CHOOSED.filter(i => i['wallet.sumMoney']>0).length > 0) {
        typs.push("余额未使用");
    }
    if (_ROWS_CHOOSED.filter(i => i['bonusWallet.availableBonus']>0).length > 0) {
        typs.push("分红（积分）未提现");
    }
    if (_ROWS_CHOOSED.filter(i => i['customerGroup.orderToConsumeCnt']>0).length > 0) {
        typs.push("订单未使用");
    }

    if (typs.length > 0) {
        tip = "该用户尚有" + typs.join("、") + "；删除后将无法恢复<br>";
    }

    tip += "确定要删除选中行?";
    return tip;
}
