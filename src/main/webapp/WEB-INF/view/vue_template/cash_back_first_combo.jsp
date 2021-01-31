<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<script type="text/javascript">
    Vue.component('cash-back-first-combo', {
        props: ['id', 'must_choose_one'],
        data: function () {
            var data = this.must_choose_one == "false" ? [{
                name: '请选择',
                id: ''
            }] : [];
            $.ajax({
                url: '${ctx}/utils/selectFirstCashback',
                data: {rows: 0, page: 1},
                type: 'post',
                async: false,
                cached: false,
                success: function (result) {
                    for(var key in result.rows){
                        data.push({
                            value : key,
                            name: result.rows[key]
                        });
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
        , template: '#cash-back-first-combo-template'
    })
</script>


<template id="cash-back-first-combo-template">
    <select :id="id" class="select2_demo_3 form-control m-b">
        <option v-for="(item,index) in items" :value="item.value">{{item.name}}</option>
    </select>
</template>