<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<script type="text/javascript">
    Vue.component('status-combo', {
        props: ['id', 'must_choose_one'],
        data: function () {
            var items = [{value: 1, text: '启用'}, {value: 0, text: '禁用'}];
            if (this.must_choose_one === 'false') {
                items = [{value: '', text: '请选择'}].concat(items);
            }
            return {
                items: items
            }
        }, mounted: function () {
            $('.select2_demo_3').select2();

        }, template: '#status-combo-template'
    })
</script>


<template id="status-combo-template">
    <select :id="id" class="select2_demo_3 form-control m-b">
        <option v-for="(item,index) in items" :value="item.value">{{item.text}}</option>
    </select>
</template>