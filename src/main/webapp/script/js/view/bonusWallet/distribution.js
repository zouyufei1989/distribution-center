var distributionVue;
$(document).ready(function () {

    $('#btn_distribution').click(function () {
        if (_ROWS_CHOOSED.length == 0) {
            Alert('', '请选择下发积分客户', 'error');
            return;
        }
        distributionVue.shareHolders = _ROWS_CHOOSED;
        $('#distributionModal').modal('show');
    });

    distributionVue = new Vue({
        el: '#div_distribution',
        data: {
            shareHolders: []
        },
        mounted() {
            $("#amount").TouchSpin({
                verticalbuttons: true,
                initval: 1,
                min: 0,
                max: 9999999,
                step: 1,
                decimals: 0,
                buttondown_class: 'btn btn-white',
                buttonup_class: 'btn btn-white'
            });
        },
        computed: {
            multiple() {
                return this.shareHolders.length > 1;
            },
            shareHolderNames() {
                return this.shareHolders.map(o => o['customer.name']).join(",")
            },
            available4Single() {
                if (this.shareHolders.length == 0) {
                    return 0;
                }
                return this.shareHolders[0].pendingBonus;
            },
            multipleTip() {
                return "您正在为" + this.shareHolders.length + "名股东: " + this.shareHolderNames + " 发放积分";
            }
        },
        methods: {
            cancel() {
                $('#distributionModal').modal('hide');
            },
            distribute(e) {
                var _this = this;
                var customerGroupIds = this.shareHolders.map(i => i['customer.customerGroup.id']);

                Confirm('确定下发积分?', function () {
                    loadingStart($(e.target), function () {
                        $.ajax({
                            url: "distributeBonus",
                            type: 'post',
                            data: JSON.stringify({
                                amount: $('#amount').val() * 100,
                                customerGroupIds: customerGroupIds
                            }),
                            headers: {
                                'Accept': 'application/json',
                                'Content-Type': 'application/json;charset=UTF-8'
                            },
                            async: true,
                            cache: false,
                            success: function (result) {
                                if (result.success == false) {
                                    loadingEnd(function () {
                                        Alert("", result.message || "失败！", "error");
                                    });
                                    return;
                                }

                                loadingEnd(function () {
                                    Alert("", "成功！", "success", function () {
                                        $('#distributionModal').modal('hide');
                                        reloadList();
                                    });
                                });
                            }
                        });
                    })
                });
            }
        }
    });

});