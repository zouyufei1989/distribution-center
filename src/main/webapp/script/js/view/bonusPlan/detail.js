var detailModalVue;

$(document).ready(function () {
    detailModalVue = new Vue({
        el: '#detailModal',
        data: {item: {}, histories: {}},
        methods: {
            findById() {
                var _this = this;
                $.ajax({
                    url: 'findById',
                    type: 'post',
                    data: {
                        id: getEditRowId()
                    },
                    async: false,
                    cache: false,
                    success: function (result) {
                        if (result.success == false) {
                            Alert("", "失败！", "error");
                            return;
                        }
                        _this.item = result.data;
                        _this.item.cashback = result.data.cashbackFirst == 1 ? "是" : "否";
                    }
                });
            },
            queryHistories() {
                var _this = this;
                $.ajax({
                    url: '../history/list/search',
                    type: 'post',
                    data: {
                        key: getEditRowId(),
                        type: 'BONUS_PLAN'
                    },
                    async: false,
                    cache: false,
                    success: function (result) {
                        var histories = [];
                        if (result.success == false) {
                            Alert("", "失败！", "error");
                            return;
                        }
                        if (result.records === 1) {
                            result.rows.push(result.rows[0]);
                        }
                        for (var i = 0; i < result.rows.length - 1; i++) {
                            var basic = JSON.parse(result.rows[i].detail);
                            var contrast = JSON.parse(result.rows[i+1].detail);
                            var history = {
                                updateDate: yyyyMMddhhmmFormatter(result.rows[i+1].createDate),
                                updater: result.rows[i+1].updater,
                                serialNumber1: basic.serialNumber === contrast.serialNumber ? '未更改' : basic.serialNumber,
                                serialNumber2: basic.serialNumber === contrast.serialNumber ? '--' : contrast.serialNumber,
                                name1: basic.name === contrast.name ? '未更改' : basic.name,
                                name2: basic.name === contrast.name ? '--' : contrast.name,
                                bonusRate1: basic.bonusRate4Show === contrast.bonusRate4Show ? '未更改' : basic.bonusRate4Show,
                                bonusRate2: basic.bonusRate4Show === contrast.bonusRate4Show ? '--' : contrast.bonusRate4Show,
                                status1: basic.statusName === contrast.statusName ? '未更改' : basic.statusName,
                                status2: basic.statusName === contrast.statusName ? '--' : contrast.statusName,
                            }
                            histories.push(history);
                        }
                        _this.histories = histories;
                    }
                });
            }
        }
    });

    $('#detailForm input').attr('disabled', 'disabled');
    $('#detailForm textarea').attr('disabled', 'disabled');
    $('#detailForm select').attr('disabled', 'disabled');

    $('#btn_detail').click(function () {
        if (_ROWS_CHOOSED.length != 1) {
            Alert("", "请选择一条需要查看的数据！");
            return;
        }
        $('#detailModal').modal('show');
    });

    bindModalShow('detailModal', function () {
        detailModalVue.findById();
        detailModalVue.queryHistories();
        $('.select2_demo_3').select2();
    }, 1);
});