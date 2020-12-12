var assignVue;
$(document).ready(function () {
    $('#btn_send_activity').click(function () {
        if (_ROWS_CHOOSED.length != 1) {
            Alert('', '请选择一个活动', 'error');
            return;
        }
        assignVue.activity = _ROWS_CHOOSED[0];
        $('#assignModal').modal('show');
    });

    assignVue = new Vue({
        el: '#div_assignActivity',
        data: {
            nameOrPhone: '',
            activity: {},
            activityPrice: 0,
            shareHolders: [],
        },
        computed: {
            assignTip() {
                var checkedItems = this.shareHolders.filter(i => i.checked);
                if (checkedItems.length === 0) {
                    return "您正在对活动“" + this.activity.name + "”发起赠送，当前已选择赠送目标 0 人，数量总计 0元。"
                }
                var sumCnt = this.shareHolders.filter(i => i.checked).map(i => i.cnt).reduce((i, j) => {
                    return Number.parseInt(i) + Number.parseInt(j)
                });
                var sumPrice = (this.shareHolders.filter(i => i.checked).map(i => i.cnt).reduce((i, j) => {
                    return Number.parseInt(i) + Number.parseInt(j)
                }) * Number.parseInt(this.activity.sumPrice) / 100).toFixed(2);
                return "您正在对活动“" + this.activity.name + "”发起赠送，当前已选择赠送目标 " + checkedItems.length + " 人，数量总计 " + sumCnt + "。"
            }
        },
        methods: {
            queryCustomer() {
                var _this = this;
                $.ajax({
                    url: '../customer/list/search',
                    type: 'post',
                    data: {
                        nameOrPhone: _this.nameOrPhone,
                        'customer.customerGroup.type': 2
                    },
                    async: true,
                    cache: false,
                    success: function (result) {
                        if (result.success === false) {
                            Alert("", result.message || "失败！", "error");
                            return;
                        }
                        $.each(result.rows, function (index, item) {
                            item.checkd = false;
                            item.cnt = 0;
                        });
                        _this.shareHolders = result.rows;
                    }
                });
            },
            cntChange(step, id) {
                var item = this.shareHolders.filter(i => i.customerGroup.id === id);
                if (item) {
                    item[0].cnt += step;
                }
            },
            cancel() {
                $('#assignModal').modal('hide');
            },
            assign() {
                var _this = this;
                var checkedItems = this.shareHolders.filter(i => i.checked);
                if (!checkedItems || checkedItems.length == 0) {
                    Alert("", "请选择要赠送目标", "error");
                    return;
                }


                Confirm('确定赠送?', function () {
                    loadingStart(function(){
                        var assignItems = _this.shareHolders.filter(i => i.checked).map(i =>  {
                            return {cnt: i.cnt, customerGroupId: i.customerGroup.id}
                        })
                        $.ajax({
                            url: "../activityAssign/add",
                            type: 'post',
                            data: JSON.stringify({
                                activityId: _this.activity.id,
                                items: assignItems
                            }),
                            headers: {
                                'Accept': 'application/json',
                                'Content-Type': 'application/json;charset=UTF-8'
                            },
                            async: true,
                            cache: false,
                            success: function (result) {
                                if (result.success == false) {
                                    loadingEnd(function(){
                                        Alert("", result.message || "失败！", "error");
                                    });
                                    return;
                                }

                                loadingEnd(function(){
                                    Alert("", "成功！", "success", function () {
                                        $('#assignModal').modal('hide');
                                        reloadList();
                                    });
                                });
                            }
                        });
                    })
                });
            }
        },
        mounted() {
            this.queryCustomer();
        },
    })

});
