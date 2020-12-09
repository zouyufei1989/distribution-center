<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<script type="text/javascript">
    Vue.component('bonus-plan-combo', {
        props: ['id', 'must_choose_one'],
        data: function () {
            var data = this.must_choose_one == "false" ? [{
                name: '请选择',
                id: ''
            }] : [];
            $.ajax({
                url: '${ctx}/bonusPlan/list/search',
                data: {rows: 0, page: 1},
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
            return {
                items: data
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