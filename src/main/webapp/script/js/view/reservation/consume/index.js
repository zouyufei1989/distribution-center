var actionVue;
$(document).ready(function () {
    bindModalShow('consumeModal', function () {
        $('.select2_demo_3').select2();
    }, 1);
    bindModalHide('consumeModal', function () {
        reservationVue.item = {};

        actionVue.goodsChoosed = [];
        actionVue.purchaseInfo = {
            actuallyMoney: 0,
            payMoney: 1,
            payBonus: 0,
            payOffline: 0,
        };
        actionVue.customerInfo = {
            availableMoney: 0,
            availableBonus: 0,
            customerGroupId: null,
            customerType: null,
            name: ''
        };
        actionVue.consumeInfo = {
            cnt: 0
        }
        actionVue.additionalConsumeFlag = false;
    }, 1);

    actionVue = new Vue({
        el: '#div_actions',
        data: {
            action: '',
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
            consumeRequests: [],
            timestamp: new Date().getTime(),
            additionalConsumeFlag: false,
        },
        mounted: function () {
            $('#orderToConsume').change(function () {
                actionVue.consumeInfo.orderId = $(this).val();
                actionVue.consumeInfo.goodsName = $('#orderToConsume option:checked').attr('data-name');
                var cntAvailable = $('#orderToConsume option:checked').attr('data-available');
                $("#orderToConsumeCnt").trigger("touchspin.updatesettings", {max: isNaN(cntAvailable) ? 0 : parseInt(cntAvailable)});
            });
            $("#orderToConsumeCnt").TouchSpin({
                verticalbuttons: true,
                initval: 0,
                min: 1,
                max: 9999999,
                step: 1,
                decimals: 0,
                buttondown_class: 'btn btn-white',
                buttonup_class: 'btn btn-white',
            }).on("change", function (e) {
                actionVue.consumeInfo.cnt = $(e.target).val();
            });
        },
        computed: {
            cnt() {
                return this.goodsChoosed.length;
            },
            sumPrice() {
                var sum = 0;
                if (this.goodsChoosed.length > 0) {
                    sum = this.goodsChoosed.map(i => moneyFormatter(i.price * i.cnt)).reduce((i, j) => Number.parseFloat(i) + Number.parseFloat(j));
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
            additionalConsume() {
                this.additionalConsumeFlag = true;
                this.chooseAction('buySingle');
            },
            chooseAction(action) {
                this.action = action;
                this.goodsChoosed = [];
                this.consumeInfo = {cnt: 0};
                this.consumeRequests = [];
                this.purchaseInfo = {
                    actuallyMoney: 0,
                    payMoney: 1,
                    payBonus: 0,
                    payOffline: 0,
                };
                if (action === 'buySingle') {
                    reloadGoodsTree();
                } else {
                    this.timestamp = new Date().getTime();
                }
                this.$nextTick(function () {
                    $('.select2_demo_3').select2().trigger('change');
                })
            },
            confirmConsumeRequest() {
                this.consumeInfo.customerGroupId = this.customerInfo.customerGroupId;
                this.consumeRequests.push(JSON.parse(JSON.stringify(this.consumeInfo)));
            },
            changePayType(payType, val) {
                this.purchaseInfo[payType] = val;
            },
            cancel() {
                $('#consumeModal').modal('hide');
            },
            refreshOrderCombo() {
                this.timestamp = new Date().getTime();
            },
            refreshPackageCombo() {
                this.timestamp = new Date().getTime();
            },
            purchase(e) {
                //购买套餐
                var _this = this;
                var tip = '客户"' + this.customerInfo.name + '"购买"' + this.goodsChoosed[0].name + '"' + this.goodsChoosed[0].cnt + '个，共计' + this.sumPrice + '元，实付款<span class="text-danger">' + this.purchaseInfo.actuallyMoney + '元</span>，请确认。';
                Confirm(tip, function () {
                    loadingStart($(e.target), function () {
                        $.ajax({
                            url: "../customer/purchase",
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
                                        actionVue.timestamp = new Date().getTime();
                                    });
                                });
                            }
                        });
                    })
                });
            },
            consumeReservation(e) {
                var _this = this;
                var req = {
                    reservationId: reservationVue.item.id,
                    orderId: reservationVue.item.orderId,
                    cnt: reservationVue.reservationUseCnt,
                    customerGroupId: _this.customerInfo.customerGroupId
                };
                var tip = '将客户"' + this.customerInfo.name + '"的预约状态变为"<span class="text-info">已到店</span>"<br>';

                if (this.action === 'buySingle') {
                    req.purchaseConsumeRequest = {
                        goodsChoosed: _this.goodsChoosed,
                        sumMoney: Number.parseFloat(_this.sumPrice) * 100,
                        actuallyMoney: _this.purchaseInfo.actuallyMoney * 100,
                        extraMoneyOffline: _this.extraMoneyOffline * 100,
                        payMoney: _this.purchaseInfo.payMoney,
                        payBonus: _this.customerInfo.customerType == 1 ? 0 : _this.purchaseInfo.payBonus,
                        payOffline: _this.purchaseInfo.payOffline,
                        customerGroupId: _this.customerInfo.customerGroupId
                    };

                    var cnt = this.goodsChoosed.map(i => i.cnt).reduce((i, j) => Number.parseInt(i) + Number.parseInt(j));
                    tip += '并购买' + this.goodsChoosed.length + '种产品' + cnt + '个，共计' + this.sumPrice + '元，实付款<span class="text-danger">' + this.purchaseInfo.actuallyMoney + '元</span>，请确认。';
                } else if (this.action === 'consumePackage') {
                    req.consumeRequests = _this.consumeRequests;
                    var sumCnt = this.consumeRequests.map(i => Number.parseInt(i.cnt)).reduce((i, j) => i + j);
                    tip += '本次消费' + this.consumeRequests.length + '种项目，共' + sumCnt + '次，请确认。'
                }

                Confirm(tip, function () {
                    loadingStart($(e.target), function () {
                        $.ajax({
                            url: "consumeReservation",
                            type: 'post',
                            data: JSON.stringify(req),
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
