var consumeVue;
$(document).ready(function () {

    consumeVue = new Vue({
        el: '#div_actions',
        data: {
            action: 'buySingle',
            goodsChoosed: [],
            purchaseInfo: {
                actuallyMoney: 0,
                payMoney: 1,
                payBonus: 0,
                payOffline: 0,
            },
            customerInfo: {
                availableMoney: 0,
                availableBonus: 0,
                customerGroupId: null,
                customerType: null,
                name: ''
            },
            consumeInfo: {
                cnt: 0
            },
            timestamp:new Date().getTime()
        },
        computed: {
            cnt() {
                return this.goodsChoosed.length;
            },
            sumPrice() {
                var sum = 0;
                if (this.goodsChoosed.length > 0) {
                    sum = this.goodsChoosed.map(i => moneyFormatter(i.price * i.cnt )).reduce((i, j) => Number.parseFloat(i) + Number.parseFloat(j));
                }
                this.purchaseInfo.actuallyMoney = sum;
                return sum;
            },
            moneyEnough() {
                var enough = Number.parseFloat(this.purchaseInfo.actuallyMoney) > Number.parseFloat(this.customerInfo.availableMoney);
                return enough;
            },
            extraMoneyOffline() {
                var available = 0;
                if (this.purchaseInfo.payMoney == 1) {
                    available += Number.parseFloat(this.customerInfo.availableMoney);
                }
                if (this.purchaseInfo.payBonus == 1) {
                    available += Number.parseFloat(this.customerInfo.availableBonus);
                }
                var extra = Number.parseFloat(this.purchaseInfo.actuallyMoney) - available;
                if (extra < 0) {
                    extra = 0;
                }
                return extra;
            }
        },
        methods: {
            chooseAction(action) {
                this.action = action;
                this.goodsChoosed = [];
                this.consumeInfo = {cnt: 0};
                this.purchaseInfo = {
                    actuallyMoney: 0,
                    payMoney: 1,
                    payBonus: 0,
                    payOffline: 0,
                };
                reloadGoodsTree();
                customerVue.refreshCustomerInfo();
                this.$nextTick(function () {
                    $('.select2_demo_3').select2().trigger('change');
                })
            },
            changePayType(payType, val) {
                this.purchaseInfo[payType] = val;
            },
            cancel() {
                $('#consumeModal').modal('hide');
            },
            refreshOrderCombo(){
                this.timestamp = new Date().getTime();
            },
            refreshPackageCombo(){
                this.timestamp = new Date().getTime();
            },
            purchase(e) {
                //购买套餐
                var _this = this;
                var tip = '客户"' + this.customerInfo.name + '"购买"' + this.goodsChoosed[0].name + '"' + this.goodsChoosed[0].cnt + '个，共计' + this.sumPrice + '元，实付款<span class="text-danger">' + this.purchaseInfo.actuallyMoney + '元</span>，请确认。';
                Confirm(tip, function () {
                    loadingStart($(e.target), function () {
                        $.ajax({
                            url: "purchase",
                            type: 'post',
                            data: JSON.stringify({
                                goodsChoosed: _this.goodsChoosed,
                                sumMoney: Number.parseFloat(_this.sumPrice) * 100,
                                actuallyMoney: _this.purchaseInfo.actuallyMoney * 100,
                                extraMoneyOffline: _this.extraMoneyOffline * 100,
                                payMoney: _this.purchaseInfo.payMoney,
                                payBonus: _this.customerInfo.customerType == 1 ? 0 : _this.purchaseInfo.payBonus,
                                payOffline: _this.purchaseInfo.payOffline,
                                customerGroupId: _this.customerInfo.customerGroupId,
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
                                        reloadList();
                                        _this.timestamp = new Date().getTime();
                                        $('#consumeModal').modal('hide');
                                    });
                                });
                            }
                        });
                    })
                });
            },
            consume(e) {
                var _this = this;
                var tip = '客户"' + this.customerInfo.name + '"本次消费项目"' + this.consumeInfo.goodsName + '"' + this.consumeInfo.cnt + '次，请确认。';
                _this.consumeInfo.customerGroupId = _this.customerInfo.customerGroupId;
                Confirm(tip, function () {
                    loadingStart($(e.target), function () {
                        $.ajax({
                            url: "consume",
                            type: 'post',
                            data: JSON.stringify(_this.consumeInfo),
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
                                        $('#consumeModal').modal('hide');
                                        reloadList();
                                    });
                                });
                            }
                        });
                    })
                });
            },
            purchaseAndConsume(e) {
                var _this = this;
                //购买单品并使用
                var cnt = this.goodsChoosed.map(i => i.cnt).reduce((i, j) => Number.parseInt(i) + Number.parseInt(j));
                var tip = '客户"' + this.customerInfo.name + '"购买' + this.goodsChoosed.length + '种产品' + cnt + '个，共计' + this.sumPrice + '元，实付款<span class="text-danger">' + this.purchaseInfo.actuallyMoney + '元</span>，请确认。';
                Confirm(tip, function () {
                    loadingStart($(e.target),function(){
                        $.ajax({
                            url: "purchaseThenConsumeAll",
                            type: 'post',
                            data: JSON.stringify({
                                goodsChoosed: _this.goodsChoosed,
                                sumMoney: Number.parseFloat(_this.sumPrice) * 100,
                                actuallyMoney: _this.purchaseInfo.actuallyMoney * 100,
                                extraMoneyOffline: _this.extraMoneyOffline * 100,
                                payMoney: _this.purchaseInfo.payMoney,
                                payBonus: _this.customerInfo.customerType == 1 ? 0 : _this.purchaseInfo.payBonus,
                                payOffline: _this.purchaseInfo.payOffline,
                                customerGroupId: _this.customerInfo.customerGroupId,
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
                                        $('#consumeModal').modal('hide');
                                        reloadList();
                                    });
                                });
                            }
                        });
                    })
                });
            },
            goRecharge() {
                goRecharge(this.customerInfo.customerGroupId);
            }
        },
    });
});
