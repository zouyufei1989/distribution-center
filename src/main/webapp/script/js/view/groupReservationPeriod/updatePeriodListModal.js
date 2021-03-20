var updatePeriodListModalVue;

$(document).ready(function () {
    updatePeriodListModalVue = new Vue({
        el: '#updatePeriodListModal',
        data: {
            periods: [],
            goodsId: null,
            goodsName: '',
            groupName: '',
            typeName: ''
        },
        watch: {
            goodsId: function (newVal, oldVal) {
                if (!newVal) {
                    return;
                }
                var _this = this;
                $.ajax({
                    url: 'list/search',
                    type: 'post',
                    data: {
                        goodsId: _this.goodsId,
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
                        $.each(_this.periods, function (i, item) {
                            item.saved = 1
                        });
                        _this.periods.push({
                            startTime: '',
                            endTime: '',
                            cnt: 0,
                            goodsId: this.goodsId,
                            saved: 0
                        });
                        $('#updatePeriodListModal').modal('show');
                    }
                });

            }
        },
        methods: {
            hide() {
                $('#updatePeriodListModal').modal('hide');
                this.goodsId = null;
                this.typeName = null;
                this.groupName = null;
                this.goodsName = null;
            },
            saveTemplate(i) {
                var period = this.periods[i];
                if (isNaN(new Date("2000-01-01 " + period.startTime))) {
                    Alert('', '请检查开始时间、结束时间', 'error');
                    return;
                }
                if (isNaN(new Date("2000-01-01 " + period.endTime))) {
                    Alert('', '请检查开始时间、结束时间', 'error');
                    return;
                }
                if (period.startTime >= period.endTime) {
                    Alert('', '开始时间不可晚于结束时间', 'error');
                    return;
                }
                if (isNaN(period.cnt) || period.cnt < 0) {
                    Alert('', '可约人数最小为0', 'error');
                    return;
                }
                period.saved = 1;
                this.periods.push({
                    startTime: '',
                    endTime: '',
                    cnt: 0,
                    goodsId: this.goodsId,
                    saved: 0
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
                        _this.periods.forEach(p => p.goodsId = _this.goodsId);
                        $.ajax({
                            url: 'addReservationPeriods',
                            type: 'post',
                            headers: {
                                "Cache-Control": "no-cache",
                                'Accept': 'application/json',
                                'Content-Type': 'application/json;charset=UTF-8'
                            },
                            data: JSON.stringify({
                                periods: _this.periods.filter(p => p.saved == 1),
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
