<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<script type="text/javascript">
    Vue.component('status-combo', {
        props: ['id', 'must_choose_one'],
        data: function () {
            var data = [];
            $.ajax({
                url: '${ctx}/utils/selectStatus',
                data: {},
                type: 'post',
                async: false,
                cached: false,
                success: function (result) {
                    for (var key in result.rows) {
                        data.push({
                            value: key,
                            name: result.rows[key]
                        });
                    }
                }
            });
            data = sortArr(data, (i, j) => {
                return i.value == j.value ? 0 : i.value - j.value;
            });
            if (this.must_choose_one == 'false' || this.must_choose_one == false) {
                data = [{value: null, name: '请选择'}].concat(data);
            }
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