var bonusPlanModalVue;
$(document).ready(function () {
    bindModalShow('bonusPlanModal', function () {
        $('.select2_demo_3').select2();
    }, 1);

    $('#btn_bonus_plan').click(function () {
        if (_ROWS_CHOOSED.length != 1) {
            Alert("", "请选择一条数据", "error");
            return;
        }
        bonusPlanModalVue.groupId = _ROWS_CHOOSED[0]['customerGroup.groupId'];
        bonusPlanModalVue.name = _ROWS_CHOOSED[0]['name'];
        bonusPlanModalVue.phone = _ROWS_CHOOSED[0]['phone'];
        bonusPlanModalVue.serialNumber = _ROWS_CHOOSED[0]['customerGroup.serialNumber'];
        bonusPlanModalVue.bonusPlanId = _ROWS_CHOOSED[0]['customerGroup.bonusPlanId'];

        $('#bonusPlanModal').modal('show');
    });

    bonusPlanModalVue = new Vue({
        el: '#bonusPlanForm',
        data: {
            groupId: null,
            name: null,
            serialNumber: null,
            phone: null,
            bonusPlanId: null
        },
        methods: {
            cancel() {
                $('#bonusPlanModal').modal('hide');
            },
            assignBonusPlanId(e) {
                var bonusPlanId = $('#bonusPlanId').val();
                if (!bonusPlanId) {
                    Alert('', '请选择积分方案', 'error');
                    return;
                }

                Confirm('确定保存积分方案?',function(){
                    loadingStart($(e.target),function(){
                        $.ajax({
                            url: 'assignBonusPlan',
                            type: 'post',
                            headers: {
                                "Cache-Control": "no-cache",
                                'Accept': 'application/json',
                                'Content-Type': 'application/json;charset=UTF-8'
                            },
                            data: JSON.stringify({
                                id: _ROWS_CHOOSED[0]['customerGroup.id'],
                                bonusPlanId: bonusPlanId
                            }),
                            async: true,
                            cache: false,
                            success: function (result) {
                                if (result.success === false) {
                                    loadingEnd(function () {
                                        Alert("", result.message || "失败！", "error");
                                    });
                                    return;
                                }
                                loadingEnd(function () {
                                    $('#bonusPlanModal').modal("hide");
                                    Alert("", "成功！", "success", function () {
                                        reloadList();
                                    });
                                });
                            }
                        });
                    });
                });
            }
        }
    });
});
