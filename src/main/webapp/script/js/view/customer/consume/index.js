var consumeVue;
$(document).ready(function () {

    consumeVue = new Vue({
        el: '#div_actions',
        data: {
            action: 'buySingle',
            goodsChoosed: [],
            purchaseInfo: {actuallyMoney: 0},
            customerInfo: {
                availableMoney: 0,
                availableBonus: 0,
                customerGroupId: null
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
            }
        },
        methods: {
            chooseAction(action) {
                this.action = action;
                this.goodsChoosed = [];
                this.purchaseInfo = {actuallyMoney: 0};
                reloadGoodsTree();
                this.$nextTick(function () {
                    $('.select2_demo_3').select2().trigger('change');
                })
            },
        },
    });
});
