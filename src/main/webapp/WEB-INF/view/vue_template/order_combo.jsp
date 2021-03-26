<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<script type="text/javascript">
    Vue.component('order-combo', {
        props: ['id', 'must_choose_one', 'customer_group_id', 'type', 'status', 'timestamp', 'combine', 'value', 'async'],
        data: function () {
            return {
                items: []
            }
        },
        methods: {
            reload() {
                var _this = this;
                var items = [];
                $.ajax({
                    url: '${ctx}/order/list/search',
                    data: {
                        'order.customerGroupId': _this.customer_group_id,
                        'order.goodsTypeId': _this.type,
                        'order.status': _this.status
                    },
                    type: 'post',
                    async: _this.async ? true : false,
                    success: function (result) {
                        var orders = result.rows.filter(o => !o.expired);
                        if (orders && orders.length > 0) {
                            items = items.concat(orders.map(o => {
                                return {
                                    id: o.id,
                                    cnt: o.items[0] ? o.items[0].cnt : 0,
                                    cntUsed: o.items[0] ? o.items[0].cntUsed : 0,
                                    name: o.goodsName,
                                    goodsCombine: o.goodsCombine
                                }
                            }).filter(o => o.cnt > o.cntUsed));
                        }

                        if (typeof _this.combine != 'undefined' && _this.combine != null) {
                            items = items.filter(o => o.goodsCombine == _this.combine);
                        }
                        _this.items = items;
                    }
                });
            }
        },
        mounted: function () {
            $('#' + this.id).select2();
        },
        watch: {
            customer_group_id: function (newValue, oldValue) {
                this.reload();
                var _this = this;
                _this.$nextTick(function () {
                    $('#' + _this.id).select2();
                    if (_this.value) {
                        $('#' + _this.id).val(_this.value).trigger('change');
                    }
                })
            },
            type: function (newValue, oldValue) {
                this.reload();
                var _this = this;
                _this.$nextTick(function () {
                    $('#' + _this.id).select2();
                    if (_this.value) {
                        $('#' + _this.id).val(_this.value).trigger('change');
                    }
                })
            },
            timestamp: function (newValue, oldValue) {
                this.reload();
                var _this = this;
                _this.$nextTick(function () {
                    $('#' + _this.id).select2();
                    if (_this.value) {
                        $('#' + _this.id).val(_this.value).trigger('change');
                    }
                })
            }
        },
        template: '#order-combo-template'
    })
</script>


<template id="order-combo-template">
    <select :id="id" class="select2_demo_3 form-control m-b">
        <option v-show="!must_choose_one" data-available="0">请选择</option>
        <option v-for="(item,index) in items" :key="item.id" :data-name="item.name" :value="item.id" :data-available="item.cnt-item.cntUsed">{{item.name}}（剩余{{item.cnt-item.cntUsed}}次） &nbsp;</option>
    </select>
</template>