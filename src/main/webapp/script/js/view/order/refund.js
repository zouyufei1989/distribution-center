var moneyModal, bonusModal;
$(document).ready(function () {
    moneyModal = new Vue({
        el: '#refundMoneyModal',
        data: {
            type: 1,
            refundAmount: null,
            orderId: null
        },
        methods: {
            refund(e) {
                var _this = this;
                Confirm("确定退款吗？", function () {
                    loadingStart($(e.target), function () {
                        $.ajax({
                            url: '../order/refund',
                            type: 'post',
                            data: JSON.stringify({
                                orderId: _this.orderId,
                                refundAmount: _this.refundAmount * 100,
                                type: _this.type
                            }),
                            headers: {
                                'Accept': 'application/json',
                                'Content-Type': 'application/json;charset=UTF-8'
                            },
                            async: true,
                            cache: false,
                            success: function (result) {
                                loadingEnd(function () {
                                    if (result.success == false) {
                                        Alert("", result.message || "失败！", "error");
                                        return;
                                    }

                                    Alert("", "成功！", "success", function () {
                                        $('#refundMoneyModal').modal('hide');
                                        reloadList();
                                        detailModal.refundParams.orderStatus = 5;
                                    });
                                })
                            }
                        });
                    })
                });
            }
        },
    });

    bonusModal = new Vue({
        el: '#refundBonusModal',
        data: {
            parentCustomerName: '',
            bonusGenerated: 0,
            bonusGenerated4Show: 0,
            createDate: '',
            availableBonus: 0,
            orderId: null
        },
        methods: {
            refund(e) {
                var _this = this;
                Confirm("确定扣除吗？", function () {
                    loadingStart($(e.target), function () {
                        $.ajax({
                            url: '../bonusWallet/refund',
                            type: 'post',
                            data: JSON.stringify({
                                orderId: _this.orderId,
                                refundAmount: _this.bonusGenerated * 100,
                            }),
                            headers: {
                                'Accept': 'application/json',
                                'Content-Type': 'application/json;charset=UTF-8'
                            },
                            async: true,
                            cache: false,
                            success: function (result) {
                                loadingEnd(function () {
                                    if (result.success == false) {
                                        Alert("", result.message || "失败！", "error");
                                        return;
                                    }

                                    Alert("", "成功！", "success", function () {
                                        detailModal.refundParams.orderStatus = 6;
                                        $('.modal').modal('hide');
                                    });
                                })
                            }
                        });
                    })
                });
            }
        },
        computed: {
            availableBonus4Show() {
                return moneyFormatter(this.availableBonus);
            },
            createDate4Show() {
                return timeFormatter(this.createDate, 'yyyy-MM-dd hh:mm:ss')
            }
        }
    });

});
