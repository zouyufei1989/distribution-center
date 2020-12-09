<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<script type="text/javascript">
    Vue.component('bonus-plan-combo', {
        props: ['id', 'must_choose_one','group_id','bonus_plan_id'],
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
                    if(_this.bonus_plan_id){
                        $('#' + _this.id).val(_this.bonus_plan_id).trigger('change');
                    }
                })
            },
            bonus_plan_id: function (newValue, oldValue) {
                $('#' + this.id).val(this.bonus_plan_id).trigger('change');
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
                    url: '${ctx}/bonusPlan/list/search',
                    data: {rows: 0, page: 1, 'bonusPlan.groupId': _this.group_id},
                    type: 'post',
                    async: false,
                    cached: false,
                    success: function (result) {
                        for (var i = 0; i < result.rows.length; i++) {
                            var item = result.rows[i];
                            if (item.status == 1) {
                                data.push({
                                    value: item.id,
                                    name: item.name
                                });
                            }
                        }
                    }
                });
                return data;
            }
        },
        mounted: function () {
            $('#' + this.id).select2();
        }
        , template: '#bonus-plan-combo-template'
    })
</script>


<template id="bonus-plan-combo-template">
    <select :id="id" class="select2_demo_3 form-control m-b">
        <option v-for="(item,index) in items" :value="item.value">{{item.name}}</option>
    </select>
</template>