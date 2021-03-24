<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<script type="text/javascript">
    Vue.component('reservation-period-combo', {
        props: ['id', 'must_choose_one', 'timestamp', 'date', 'orderid', 'value'],
        data: function () {
            return {
                items: []
            }
        },
        methods: {
            reload() {
                if (!(this.orderid && this.date)) {
                    console.log('orderId or date is undefined');
                    return;
                }
                var _this = this;
                var items = this.must_choose_one == "false" ? [{
                    name: '请选择',
                    id: ''
                }] : [];
                $.ajax({
                    url: '${ctx}/reservation/queryReservationCalender',
                    data: {
                        startDate: _this.date,
                        endDate: _this.date,
                        orderId: _this.orderid,
                        rows: 0,
                    },
                    type: 'post',
                    async: false,
                    success: function (result) {
                        if (result.success == false) {
                            Alert('', result.message, 'error');
                        } else {
                            var periods = result.data;
                            if (periods && periods.length > 0) {
                                items = items.concat(periods.map(o => {
                                    return {
                                        id: o.start + "-" + o.end,
                                        name: o.start + " - " + o.end + (o.available > 0 ? '' : '  (已约满)'),
                                        available: o.available
                                    }
                                }));
                            }
                        }

                    }
                });
                return items;
            }
        },
        mounted: function () {
            $('#' + this.id).select2();
        },
        watch: {
            timestamp: function (newValue, oldValue) {
                this.items = this.reload();
                var _this = this;
                _this.$nextTick(function () {
                    $('#' + _this.id).select2();
                    if (_this.value) {
                        $('#' + _this.id).val(_this.value).trigger('change');
                    }
                })
            }
        },
        template: '#reservation-period-combo-template'
    })
</script>


<template id="reservation-period-combo-template">
    <select :id="id" class="select2_demo_3 form-control m-b">
        <option v-show="!must_choose_one">请选择</option>
        <option :disabled="item.available<=0" v-for="(item,index) in items" :key="item.id" :data-name="item.name" :value="item.id">{{item.name}}</option>
    </select>
</template>