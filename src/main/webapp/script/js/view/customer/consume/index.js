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
                customerType: null
            },

        },
        computed: {
            cnt() {
                return this.goodsChoosed.length;
            },
            sumPrice() {
                var sum = 0;
                if (this.goodsChoosed.length > 0) {
                    sum = this.goodsChoosed.map(i => (i.price * i.cnt / 100).toFixed(2)).reduce((i, j) => Number.parseFloat(i) + Number.parseFloat(j));
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
                this.purchaseInfo = {
                    actuallyMoney: 0,
                    payMoney: 1,
                    payBonus: 0,
                    payOffline: 0,
                };
                reloadGoodsTree();
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
            purchase() {
            },
            consume() {
            },
            purchaseAndConsume() {
            }
        },
    });
});
