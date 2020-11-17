<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<script type="text/javascript">
    Vue.component('group-combo', {
        props: ['id', 'must_choose_one'],
        data: function () {
            var data = this.must_choose_one == "false" ? [{
                name: '请选择',
                id: ''
            }] : [];
            $.ajax({
                url: '${ctx}/utils/selectGroups',
                data: {rows:0,page:1},
                type: 'post',
                async: false,
                cached:false,
                success: function (result) {
                    for (var i = 0; i < result.rows.length; i++) {
                        if (result.rows[i].status === 1) {
                            data.push(result.rows[i]);
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
        },
        template: '#group-combo-template'
    })
</script>


<template id="group-combo-template">
    <select :id="id" class="select2_demo_3 form-control m-b">
        <option v-for="(item,index) in items" :key="item.id" :value="item.id" :data-city-code="item.cityCode">{{item.name}}</option>
    </select>
</template>