<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<script type="text/javascript">
    Vue.component('v-date-time-picker', {
        props: ['id', 'min_view_mode', 'max_view_mode', 'required', 'value','search_param'],
        data: function () {
            return {
                today: new Date().Format('yyyy-MM-dd')
            }
        },
        template: '#date-time-picker-template',
        computed: {
            div_id: function () {
                return 'div_' + this.id;
            }
        },
        mounted: function () {
            $('#'+this.div_id).datetimepicker({
                language: 'zh-CN',
                weekStart: 1,
                todayBtn:  1,
                autoclose: 1,
                todayHighlight: 1,
                startView: 2,
                forceParse: 0,
                showMeridian: 1
            });
        }
    })
</script>

<template id='date-time-picker-template' >
    <div class="input-group date form_datetime" data-date-format="yyyy-mm-dd HH:ii" :data-link-field="id" :id="div_id">
        <input class="form-control" size="16" type="text" value="" readonly :id="id">
        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
        <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
    </div>

</template>