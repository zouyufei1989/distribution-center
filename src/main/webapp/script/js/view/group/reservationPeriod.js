var reservationPeriodModalVue;
$(document).ready(function () {

    reservationPeriodModalVue = new Vue({
        el: '#reservationPeriodModal',
        data: {
            periods: [],
            groupId: null
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
                $('#reservationPeriodModal').modal('show');
            },
            hide(){
                $('#reservationPeriodModal').modal('hide');
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
            cancel() {
                $('#reservationPeriodModal').modal('hide');
            },
            setStartTime(i,e){
                this.periods[i].startTime = $(e.target).val();
            },
            setEndTime(i,e){
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
                                groupId:_this.groupId
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
                                    Alert("", "成功！", "success",function(){
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

    $('#btn_reservation_period').click(function () {
        if (_ROWS_CHOOSED.length != 1) {
            Alert('', '请选择一条待编辑数据', 'warning');
            return;
        }
        reservationPeriodModalVue.groupId = _ROWS_CHOOSED[0].id;
        reservationPeriodModalVue.show();
    });
});
