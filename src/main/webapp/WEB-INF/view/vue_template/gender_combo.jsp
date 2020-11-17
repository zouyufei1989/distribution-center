<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<script type="text/javascript">
    Vue.component('gender-combo', {
        props: ['id', 'must_choose_one'], data: function () {
            return {
                items: [{value: 0, text: '保密'},{value: 1, text: '男'},{value: 2, text: '女'}]
            }
        }, mounted: function () {
            $('#' + this.id).select2();

        }, template: '#gender-combo-template'
    })
</script>


<template id="gender-combo-template">
    <select :id="id" class="select2_demo_3 form-control m-b">
        <option v-for="(item,index) in items" :value="item.value">{{item.text}}</option>
    </select>
</template>