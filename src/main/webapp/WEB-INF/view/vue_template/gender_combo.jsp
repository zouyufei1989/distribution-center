<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<script type="text/javascript">
    Vue.component('gender-combo', {
        props: ['id', 'must_choose_one'],
        data: function () {
            var data = [];
            $.ajax({
                url: '${ctx}/utils/selectGender',
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
            if (this.must_choose_one == 'false' || this.must_choose_one == false) {
                data = [{value: null, name: '请选择'}].concat(data);
            }
            return {
                items: data
            }
        }, mounted: function () {
            $('.select2_demo_3').select2();
        }, template: '#gender-combo-template'
    })
</script>


<template id="gender-combo-template">
    <select :id="id" class="select2_demo_3 form-control m-b">
        <option v-for="(item,index) in items" :value="item.value">{{item.name}}</option>
    </select>
</template>