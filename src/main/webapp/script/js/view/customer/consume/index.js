var consumeVue;
$(document).ready(function () {
    consumeVue = new Vue({
        el: '#div_actions',
        data: {
            action: 'buySingle',
            goodsChoosed: [],
            purchaseInfo: {paidMoney: 0},
            customerInfo: {
                availableMoney: 0,
                availableBonus: 0,
                customerGroupId: null
            },

        },
        methods: {
            chooseAction(action) {
                this.action = action;
            }
        }
    });
});
