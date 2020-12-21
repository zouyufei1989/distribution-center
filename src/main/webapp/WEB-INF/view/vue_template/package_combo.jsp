<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<script type="text/javascript">
    Vue.component('package-combo', {
        props: ['id', 'must_choose_one', 'customer_group_id','timestamp'],
        data: function () {
            return {
                items: []
            }
        },
        methods: {
            reload() {
                var _this = this;
                var items =[];
                $.ajax({
                    url: '${ctx}/package/list/search',
                    data: {
                        customerGroupId: _this.customer_group_id
                    },
                    type: 'post',
                    async: false,
                    success: function (result) {
                        items = items.concat(result.rows);
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
                var _this = this;
                _this.$nextTick(function () {
                    $('#' + _this.id).select2();
                })
            },
            timestamp: function (newValue, oldValue) {
                this.items = this.reload();
                var _this = this;
                _this.$nextTick(function () {
                    $('#' + _this.id).select2();
                })
            }
        },
        template: '#package-combo-template'
    })
</script>


<template id="package-combo-template">
    <select :id="id" class="select2_demo_3 form-control m-b">
        <option v-show="!must_choose_one">请选择</option>
        <option v-for="(item,index) in items" :key="item.id" :data-name="item.name" :data-price="item.sumPrice" :value="item.id">{{item.name}} (内含{{item.cnt}}次)  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    ¥{{item.sumPrice4Show}}</option>
    </select>
</template>