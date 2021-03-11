var reservationPeriodModalVue;
var switchery;
$(document).ready(function () {
    new Vue({el: '.param_row'});
    switchery = new Switchery($('#reserveFlag')[0], {color: '#1AB394'});

    reservationPeriodModalVue = new Vue({
        el: '#reservationPeriodModal',
        data: {
            periods: [],
            groupId: null,
            reserveFlag: 0
        },
        methods: {
            show() {
                var _this = this;
                $.ajax({
                    url: 'reservationPeriodsList',
                    type: 'post',
                    data: {
                        groupId: _this.groupId,
                        rows: 0
                    },
                    async: true,
                    cache: false,
                    success: function (result) {
                        if (result.success === false) {
                            Alert("", result.message || "失败！", "error");
                            return;
                        }
                        _this.periods = result.rows;
                        if (_this.periods.length == 0) {
                            _this.add();
                        }
                    }
                });
            },
            add() {
                this.periods.push({
                    startTime: '',
                    endTime: '',
                    cnt: 0,
                    groupId: this.groupId
                });
            },
            remove(i) {
                this.periods.splice(i, 1);
            },
            setStartTime(i, e) {
                this.periods[i].startTime = $(e.target).val();
            },
            setEndTime(i, e) {
                this.periods[i].endTime = $(e.target).val();
            },
            save(e) {
                var _this = this;

                Confirm('确定保存预约时间段设置?', function () {
                    loadingStart($(e.target), function () {
                        $.ajax({
                            url: 'addReservationPeriods',
                            type: 'post',
                            headers: {
                                "Cache-Control": "no-cache",
                                'Accept': 'application/json',
                                'Content-Type': 'application/json;charset=UTF-8'
                            },
                            data: JSON.stringify({
                                periods: _this.periods,
                                groupId: _this.groupId
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
                                    Alert("", "成功！", "success", function () {
                                        _this.hide();
                                    });
                                });
                            }
                        });
                    });
                });
            }
        }
    });

    initReserveFlag();
    $('#groupId').change(function () {
        initReserveFlag();
    });

});

function initReserveFlag() {
    $('#reserveFlag').unbind('change');
    $.ajax({
            url: '../group/findById',
            data: {id: $('#groupId').val()},
            type: 'post',
            async: false,
            cached: false,
            success: function (result) {
                if (result.data.reserveFlag == 0 && $('#reserveFlag').is(":checked") == true) {
                    $('#reserveFlag').trigger('click')
                }
                if (result.data.reserveFlag == 1 && $('#reserveFlag').is(":checked") == false) {
                    $('#reserveFlag').trigger('click')
                }
                reservationPeriodModalVue.reserveFlag = result.data.reserveFlag;
            }
        }
    );

    $('#reserveFlag').change(function () {
        $.ajax({
            url: 'changeReserveFlag',
            type: 'post',
            headers: {
                "Cache-Control": "no-cache",
                'Accept': 'application/json',
                'Content-Type': 'application/json;charset=UTF-8'
            },
            data: JSON.stringify({
                groupId: $('#groupId').val(),
                reserveFlag: $('#reserveFlag').is(":checked") ? 1 : 0
            }),
            async: false,
            cache: false,
            success: function (result) {
                reservationPeriodModalVue.reserveFlag = $('#reserveFlag').is(":checked") ? 1 : 0;
            }
        });
    });
}
