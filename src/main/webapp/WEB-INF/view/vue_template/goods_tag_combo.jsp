<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<script type="text/javascript">
    Vue.component('goods-tag-combo', {
        props: ['id', 'must_choose_one', 'group_id'],
        data: function () {
            var data = this.load();
            return {
                items: data
            }
        },
        watch: {
            group_id: function (newValue, oldValue) {
                var _this = this;
                this.items = this.load();
                _this.$nextTick(function () {
                    /*现在数据已经渲染完毕*/
                    $('#' + _this.id).select2();
                    $('#' + _this.id).val(_this.value || _this.items[0].id).trigger('change');
                })
            }
        },
        methods: {
            load: function () {
                var _this = this;
                var data = this.must_choose_one == "false" ? [{
                    name: '请选择',
                    id: ''
                }] : [];
                $.ajax({
                    url: '${ctx}/goodsTag/list/search',
                    data: {rows: 0, page: 1, 'goodsTag.groupId': _this.group_id},
                    type: 'post',
                    async: false,
                    cached: false,
                    success: function (result) {
                        for (var i = 0; i < result.rows.length; i++) {
                            if (result.rows[i].status === 1) {
                                data.push(result.rows[i]);
                            }
                        }
                    }
                });
                return data;
            }
        },
        mounted: function () {
            $('#' + this.id).select2();
        },
        template: '#goods-tag-combo-template'
    })
</script>


<template id="goods-tag-combo-template">
    <select :id="id" class="select2_demo_3 form-control m-b">
        <option v-for="(item,index) in items" :key="item.id" :value="item.id" :data-city-code="item.cityCode">{{item.name}}</option>
    </select>
</template>