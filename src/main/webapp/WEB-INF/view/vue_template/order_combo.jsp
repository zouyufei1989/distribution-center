<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<script type="text/javascript">
    Vue.component('order-combo', {
        props: ['id', 'must_choose_one', 'customer_group_id', 'type', 'status'],
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
                    async: false,
                    success: function (result) {
                        var combineOrders = result.rows.filter(o => o.goodsCombine && !o.expired);
                        if (combineOrders && combineOrders.length > 0) {
                            items = items.concat(combineOrders.map(o => {
                                return {
                                    id: o.items[0].id,
                                    cnt: o.items[0].cnt,
                                    cntUsed: o.items[0].cntUsed,
                                    name: o.goodsName,
                                }
                            }).filter(o => o.cnt > o.cntUsed));
                        }
                        var singleOrders = result.rows.filter(o => !o.goodsCombine && !o.expired);
                        if (singleOrders && singleOrders.length > 0) {
                            items = items.concat(singleOrders.reduce((i, j) => {
                                return i.items.concat(j.items)
                            }).filter(o => o.cnt > o.cntUsed));
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
            customer_group_id: function (newValue, oldValue) {
                this.items = this.reload();
            }
        },
        template: '#order-combo-template'
    })
</script>


<template id="order-combo-template">
    <select :id="id" class="select2_demo_3 form-control m-b">
        <option v-show="!must_choose_one">请选择</option>
        <option v-for="(item,index) in items" :key="item.id" :data-name="item.name" :value="item.id">{{item.name}}（剩余{{item.cnt-item.cntUsed}}次） &nbsp;</option>
    </select>
</template>