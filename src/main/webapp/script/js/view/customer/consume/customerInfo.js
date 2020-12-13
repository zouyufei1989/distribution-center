var customerVue;

$(document).ready(function () {
    customerVue = new Vue({
        el: '#div_customerInfo',
        data: {
            id: null,
            name: '',
            serialNumber: '',
            phone: '',
            parentName: '',
            createDate: '',
            availableMoney: '',
        },
        watch: {
            id: function (newValue, oldValue) {
                this.queryCustomerInfo(newValue);
            }
        },
        methods: {
            refreshCustomerInfo() {
                this.queryCustomerInfo(this.id);
            },
            queryCustomerInfo(id) {
                var _this = this;
                $.ajax({
                    url: 'findById',
                    type: 'post',
                    data: {
                        id: id || _this.id
                    },
                    async: true,
                    cache: false,
                    success: function (result) {
                        _this.name = result.data.name;
                        _this.serialNumber = result.data.customerGroup.serialNumber;
                        _this.phone = result.data.phone;
                        _this.parentName = result.data.parent ? result.data.parent.name : '';
                        _this.createDate = yyyyMMddFormatter(result.data.customerGroup.createDate);
                        _this.availableMoney = result.data.wallet.availableMoney4Show;

                    }
                });
            }
        }
    });
});
