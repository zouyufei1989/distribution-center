var bindModalVue;
$(document).ready(function () {

    bindModalVue = new Vue({
        el: '#bindModal',
        data: {
            type:'',
            employee: {},
            shareHolders: [],
            choosed: [],
            searchParam: {
                phone: '',
                bonusPlanId: ''
            }
        },
        mounted: function () {
            var _this = this;
            bindModalShow('bindModal', function () {
                $('.select2_demo_3').select2();
            }, 1);
            $('#bonusPlanId').change(function () {
                _this.searchParam.bonusPlanId = $(this).val();
            });
        },
        computed: {
            myShareHolders: function () {
                var _this = this;
                return this.shareHolders.filter(i => i.employeeId == _this.employee.id)
            },
            availableShareHolders: function () {
                var _this = this;
                var result = this.shareHolders.filter(i => !i.employeeId)
                if (this.searchParam.phone) {
                    result = result.filter(i => i.name.indexOf(_this.searchParam.phone) > -1 || i.phone.indexOf(_this.searchParam.phone) > -1);
                }
                if (this.searchParam.bonusPlanId) {
                    result = result.filter(i => i.customerGroup.bonusPlanId == _this.searchParam.bonusPlanId);
                }
                return result;
            }
        },
        watch: {
            'employee.groupId': function (newVal, oldVal) {
                var _this = this;
                if (!newVal) {
                    this.shareHolders = [];
                    return;
                }
                $.ajax({
                    url: '../customer/list/search',
                    type: 'post',
                    data: {
                        rows: 0,
                        groupId: newVal,
                        'customer.customerGroup.type': 2
                    },
                    async: true,
                    cache: false,
                    success: function (result) {
                        _this.shareHolders = result.rows;
                    }
                });
            }
        },
        methods: {
            chooseAll: function (ele) {
                var checked = ele.target.checked;
                if (this.availableShareHolders) {
                    if (checked) {
                        this.choosed = this.availableShareHolders.map(i => i.customerGroup.id);
                    } else {
                        this.choosed = [];
                    }
                }
            },
            bindNew: function (e) {
                var _this = this;
                if (_this.choosed && _this.choosed.length > 0) {
                    Confirm('确定分配' + _this.choosed.length + '位股东吗?', function () {
                        _this.bindCustomerGroup(_this.employee.id, e);
                    });
                } else {
                    Alert('', '请选择股东', 'error');
                }

            },
            bindCustomerGroup: function (employeeId, e) {
                var _this = this;
                loadingStart($(e.target), function () {
                    $.ajax({
                        url: 'bindCustomerGroup',
                        type: 'post',
                        data: JSON.stringify({
                            customerGroupIds: _this.choosed,
                            employeeId: employeeId
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
                                    $('#bindModal').modal('hide');
                                });
                            });
                        }
                    });
                })
            }
        }
    });
});
