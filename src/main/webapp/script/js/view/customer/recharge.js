var chargeVue;
$(document).ready(function () {
    chargeVue = new Vue({
        el: '#div_recharge',
        data: {
            customer: {}
        },
        methods: {
            cancel() {
                $('#rechargeModal').modal('hide')
            },
            recharge() {
                var _this = this;
                Confirm('您正在为客户"' + _this.customer.name + '"进行充值，充值金额为<span class="text-danger">' + $('#recharge').val() + '元</span>，确定要充值吗？', function () {
                    $.ajax({
                        url: 'recharge',
                        type: 'post',
                        headers: {
                            'Accept': 'application/json',
                            'Content-Type': 'application/json;charset=UTF-8'
                        },
                        data: JSON.stringify({
                            customerGroupId: _this.customer.id,
                            amount: $('#recharge').val() * 100
                        }),
                        async: true,
                        cache: false,
                        success: function (result) {
                            if (result.success === false) {
                                Alert("", result.message || "失败！", "error");
                                return;
                            }
                            Alert("", "成功！", "success", function () {
                                reloadList();
                                $('#rechargeModal').modal('hide');
                            });

                        }
                    });
                });
            }
        }
    });

    $("#recharge").TouchSpin({
        verticalbuttons: true,
        initval: 0,
        min: 0,
        max: 9999999,
        step: 1,
        decimals: 0,
        postfix: '¥',
        buttondown_class: 'btn btn-white',
        buttonup_class: 'btn btn-white'
    });
});

function goRecharge(rowId) {
    var customer = $("#table_list").jqGrid("getRowData", rowId);
    console.log(customer)
    chargeVue.customer = {
        id: customer['customerGroup.id'],
        name: customer.name,
        phone: customer.phone,
        serialNumber: customer['customerGroup.serialNumber'],
        recharge: 10
    };
    $('#rechargeModal').modal('show')
}