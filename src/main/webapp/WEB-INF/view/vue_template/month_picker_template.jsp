<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<script type="text/javascript">
    Vue.component('v-month-picker', {
        data: function () {
            var today = new Date();
            today.setMonth(today.getMonth() + (this.month_plus || 0));
            var result = today.Format('yyyy-MM');
            return {
                value: result
            }
        },
        props: ['id', 'required', 'search_param', 'month_plus'],
        template: '#date-picker-template',
        computed: {
            div_id: function () {
                return 'div_' + this.id;
            }
        },
        mounted: function () {
            $('#' + this.div_id).datepicker({
                minViewMode: 1,
                maxViewMode: 2,
                todayBtn: "linked",
                keyboardNavigation: false,
                forceParse: false,
                autoclose: true,
                format: "yyyy-mm",
                language: "zh-CN"
            });

            if (this.search_param) {
                $('#' + this.id).attr('search-param', '');
            }

            if (typeof monthChanged === 'function') {
                $('#' + this.id).on('change', monthChanged);
            }
        }
    })
</script>

<template id='date-picker-template'>
    <div class="input-group date" :id='div_id'>
        <input type="text" class="input-sm form-control" :id="id" :required="required" :name="id" v-model="value">

        <span class="input-sm input-group-addon">
        <i class="fa fa-calendar"></i>
        </span>
    </div>
</template>