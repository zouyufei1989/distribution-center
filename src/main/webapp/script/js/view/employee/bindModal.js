var bindModalVue;
$(document).ready(function () {

    bindModalVue = new Vue({
        el: '#bindModal',
        data: {
            type: '',
            employee: {},
            shareHolders: [],
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

            bindModalShow('bindModal', function () {
                bindModalVue.refresh();
            }, 1)
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
                this.getShareholders();
            }
        },
        methods: {
            refresh: function () {
                this.searchParam = {
                    phone: '',
                    bonusPlanId: ''
                };
                this.getShareholders();
            },
            choose(i) {
                this.availableShareHolders[i].choosed = true;
                this.availableShareHolders[i].employeeId = this.employee.id;

            },
            revert(i) {
                this.myShareHolders[i].choosed = true;
                this.myShareHolders[i].employeeId = null;

            },
            clearSearchParam: function () {
                this.searchParam = {
                    phone: '',
                    bonusPlanId: ''
                };
            },
            bindNew: function (e) {
                var _this = this;
                if (_this.myShareHolders && _this.myShareHolders.length > 0) {
                    Confirm('确定分配' + _this.myShareHolders.length + '位股东吗?', function () {
                        loadingStart($(e.target), function () {
                            $.ajax({
                                url: 'bindCustomerGroup',
                                type: 'post',
                                data: JSON.stringify({
                                    customerGroupIds: _this.myShareHolders.map(i=>i.customerGroup.id),
                                    employeeId: _this.employee.id
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
                    });
                } else {
                    Alert('', '请选择股东', 'error');
                }

            },
            transfer: function (e) {
                var _this = this;
                var targetId = $('#transferId').val();
                if (targetId) {
                    Confirm('确定将员工' + _this.employee.name + '绑定股东迁移至' + $('#transferId').find('option:checked').text() + '吗?', function () {
                        loadingStart($(e.target), function () {
                            $.ajax({
                                url: 'transferCustomerGroup',
                                type: 'post',
                                data: JSON.stringify({
                                    srcEmployeeId: _this.employee.id,
                                    employeeId: targetId
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
                    });
                } else {
                    Alert('', '请选择迁移到员工', 'error');
                }

            },
            getShareholders() {
                var _this = this;
                $.ajax({
                    url: '../customer/list/search',
                    type: 'post',
                    data: {
                        rows: 0,
                        groupId: _this.employee.groupId,
                        'customer.customerGroup.type': 2
                    },
                    async: true,
                    cache: false,
                    success: function (result) {
                        _this.shareHolders = result.rows;
                    }
                });
            }
        }
    });
});
