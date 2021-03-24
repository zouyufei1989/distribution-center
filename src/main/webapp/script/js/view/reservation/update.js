var updateVue;

$(document).ready(function () {

    bindModalHide('updateModal', function () {
        updateVue.id = null;
    }, 1);

    updateVue = new Vue({
        el: '#updateModal',
        data: {
            id: null,
            current: {},
            first: {},
            timestamp: new Date().getTime(),
        },
        mounted: function () {
            var _this = this;
            $('#reservationDate').change(function () {
                _this.current.date = $('#reservationDate').val();
                _this.refreshReservationPeriods();
            });
            $('#period').change(function () {
                var period = $('#period').val().split('-');
                _this.current.startTime = period[0];
                _this.current.endTime = period[1];
            });
            $('#orderToUse').change(function () {
                _this.current.orderId = $('#orderToUse').val();
                _this.refreshReservationPeriods();
            });
        },
        watch: {
            id: function (newVal, oldVal) {
                if (!newVal) {
                    return;
                }
                this.queryReservationHistories(newVal);
            },
        },
        computed: {
            period() {
                return this.current.startTime + "-" + this.current.endTime;
            }
        },
        methods: {
            queryReservationHistories() {
                var _this = this;
                $.ajax({
                    url: '../history/list/search',
                    data: {
                        type: 'RESERVATION',
                        key: _this.id,
                        rows: 0
                    },
                    type: 'post',
                    async: true,
                    success: function (result) {
                        _this.current = JSON.parse(result.rows[result.rows.length - 1].detail);
                        if (result.rows.length > 1) {
                            _this.first = JSON.parse(result.rows[0].detail);
                        } else {
                            _this.first = null;
                        }
                    }
                });
            },
            editReservation(e) {
                var _this = this;
                Confirm('确定保存?', function () {
                    loadingStart($(e.target), function () {
                        $.ajax({
                            url: 'edit',
                            type: 'post',
                            headers: {
                                "Cache-Control": "no-cache",
                                'Accept': 'application/json',
                                'Content-Type': 'application/json;charset=UTF-8'
                            },
                            data: JSON.stringify({
                                id: _this.current.id,
                                orderId: _this.current.orderId,
                                date: _this.current.date,
                                startTime: _this.current.startTime,
                                endTime: _this.current.endTime,
                                remark: _this.current.remark
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
                                    $('#updateModal').modal("hide");
                                    Alert("", "成功！", "success", function () {
                                        reloadList();
                                    });
                                });
                            }
                        });
                    })
                });
            },
            refreshReservationPeriods() {
                this.timestamp = new Date().getTime();
            }
        }
    });
});

function showUpdateModal(reservationJson) {
    var reservation = JSON.parse(reservationJson);
    $('#updateModal').modal('show');
    updateVue.id = reservation.id;
}