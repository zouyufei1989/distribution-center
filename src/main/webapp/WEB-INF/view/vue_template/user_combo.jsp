<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<script type="text/javascript">
    Vue.component('user-combo', {
        props: ['id', 'must_choose_one', 'value', 'group_id'],
        data: function () {
            var data = this.load();
            return {
                items: data
            }
        },
        mounted: function () {
            if (this.value) {
                $('#' + this.id).val(this.value);
            }
            $('#' + this.id).select2();

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
            },
            value: function (newValue, oldValue) {
                $('#' + this.id).val(newValue).trigger('change');
            }
        },
        methods: {
            load: function () {
                var _this = this;
                var data = this.must_choose_one == "false" ? [{
                    username: '请选择',
                    id: ''
                }] : [];

                $.ajax({
                    url: '${ctx}/system/users/list/search',
                    data: {
                        rows: 0,
                        page: 1,
                    },
                    type: 'post',
                    async: false,
                    cached: false,
                    success: function (result) {
                        for (var i = 0; i < result.rows.length; i++) {
                            data.push(result.rows[i]);
                        }
                    }
                });

                return data;
            }
        },
        template: '#user-combo-template'
    })
</script>


<template id="user-combo-template">
    <select :id="id" class="select2_demo_3 form-control m-b">
        <option v-for="(item,index) in items" :value="item.id">{{item.username}}</option>
    </select>
</template>