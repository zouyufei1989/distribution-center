var detailModal;
$(document).ready(function () {
    detailModal = new Vue({
        el: '#detailModal',
        data: {
            order: {},
            orderItems: [],
            refund: false,
            refundParams: {}
        },
        methods: {
            moneyFormatter(val) {
                return moneyFormatter(val);
            },
            refundMoney() {
                moneyModal.type = 1;
                moneyModal.orderId = this.order.id;
                moneyModal.refundAmount = moneyFormatter(this.refundParams.orderActualMoney);
                $('#refundMoneyModal').modal('show');
            },
            refundBonus() {
                bonusModal.parentCustomerName = this.refundParams.parentCustomerName;
                bonusModal.orderId = this.order.id;
                bonusModal.bonusGenerated = moneyFormatter(this.refundParams.bonusGenerated);
                bonusModal.bonusGenerated4Show = moneyFormatter(this.refundParams.bonusGenerated);
                bonusModal.createDate = this.refundParams.createDate;
                bonusModal.availableBonus = this.refundParams.availableBonus;
                $('#refundBonusModal').modal('show');
            }
        },
        computed: {
            parentAvailableBonus() {
                return moneyFormatter(this.refundParams.availableBonus);
            }
        }
    });

});
