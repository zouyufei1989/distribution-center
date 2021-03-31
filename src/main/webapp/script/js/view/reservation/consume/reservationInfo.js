var reservationVue;

$(document).ready(function () {

    reservationVue = new Vue({
        el: '#div_reservationInfo',
        data: {
            item: {},
            reservationUseCnt: 1,
            timestamp: new Date().getTime()
        },
        mounted: function () {
            var _this = this;
            $("#reservationUseCnt").TouchSpin({
                verticalbuttons: true,
                initval: 0,
                min: 1,
                max: 9999999,
                step: 1,
                decimals: 0,
                buttondown_class: 'btn btn-white',
                buttonup_class: 'btn btn-white',
            }).on("change", function (e) {
                _this.reservationUseCnt = $(e.target).val();
                actionVue.reservation.cnt = _this.reservationUseCnt;
            });

            $('#reservationInfoOrderToUse').change(function () {
                _this.item.orderId = $(this).val();
                actionVue.reservation.goodsName = $(this).find('option:checked').attr('data-name');
                var cntAvailable = $(this).find("option:selected").attr("data-available");
                $("#reservationUseCnt").trigger("touchspin.updatesettings", {max: isNaN(cntAvailable) ? 0 : parseInt(cntAvailable)});
            });

            $('#reservationInfoGoodsType').change(function () {
                _this.item.goodsTypeId = $(this).val();
                _this.timestamp = new Date().getTime();
            });
        },
        watch: {
            'item.customerGroupId': function (newValue, oldValue) {
                if (newValue) {
                    this.queryCustomerInfo(newValue);
                }
            }
        },
        methods: {
            refreshCustomerInfo() {
                this.queryCustomerInfo(this.item.customerGroupId);
            },
            queryCustomerInfo(id) {
                var _this = this;
                $.ajax({
                    url: '../customer/findById',
                    type: 'post',
                    data: {
                        id: id || _this.item.customerGroupId
                    },
                    async: true,
                    cache: false,
                    success: function (result) {
                        actionVue.customerInfo = {
                            availableMoney: result.data.wallet.availableMoney4Show,
                            availableBonus: result.data.bonusWallet.availableBonus / 100.0,
                            customerGroupId: result.data.customerGroup.id,
                            serialNumber: result.data.customerGroup.serialNumber,
                            customerType: result.data.customerGroup.type,
                            name: result.data.name || result.data.nickName,
                            phone: result.data.phone
                        }
                    }
                });
            }
        }
    });
});
