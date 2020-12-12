<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<script type="text/javascript">
    Vue.component('status-combo', {
        props: ['id', 'must_choose_one'],
        data: function () {
            var data = this.must_choose_one == "false" ? [{
                value: '',
                name: '请选择'
            }] : [];
            $.ajax({
                url: '${ctx}/utils/selectStatus',
                data: {},
                type: 'post',
                async: false,
                cached: false,
                success: function (result) {
                    var sortedItems = sort(result.rows, (i, j) => {
                        return i.value == j.value ? 0 : i.value - j.value;
                    });
                    for (var key in sortedItems) {
                        data.push({
                            value: key,
                            name: result.rows[key]
                        });
                    }
                }
            });
            return {
                items: data
            }
        }, mounted: function () {
            $('.select2_demo_3').select2();

        }, template: '#status-combo-template'
    })
</script>


<template id="status-combo-template">
    <select :id="id" class="select2_demo_3 form-control m-b">
        <option v-for="(item,index) in items" :value="item.value">{{item.name}}</option>
    </select>
</template>