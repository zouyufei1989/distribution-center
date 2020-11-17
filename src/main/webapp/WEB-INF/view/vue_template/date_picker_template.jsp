<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!--bootstrap date picker-->
<script type="text/javascript">
    Vue.component('v-date-picker', {
        props: ['id', 'min_view_mode', 'max_view_mode', 'required', 'value', 'search_param', 'before_month', 'before_week','max_date'],
        data: function () {
            return {
                today: new Date().Format('yyyy-MM-dd')
            }
        },
        template: '#date-picker-template',
        computed: {
            div_id: function () {
                return 'div_' + this.id;
            }
        },
        mounted: function () {
            var _this = this;
            $('#' + this.div_id).datepicker({
                minViewMode: 0,
                maxViewMode: 2,
                todayBtn: "linked",
                keyboardNavigation: false,
                forceParse: false,
                autoclose: true,
                format: "yyyy-mm-dd",
                language: "zh-CN",
                endDate: _this.max_date
            });

            if (this.value) {
                $('#' + this.id).val(new Date(this.value).Format('yyyy-MM-dd'));
                return;
            }

            var today = new Date();
            if (this.before_week) {
                today.setDate(today.getDate() - 7);
            } else if (this.before_month) {
                today.setDate(today.getDate() - 30);
            }

            $('#' + this.id).val(today.Format('yyyy-MM-dd'));
        }
    })
</script>

<template id='date-picker-template'>
    <div class="input-group date" :id='div_id'>
        <input type="text" class="input form-control" :id="id" :required="required" :name="id" search-param v-if="search_param">
        <input type="text" class="input form-control" :id="id" :required="required" :name="id" v-else>
        <span class="input input-group-addon">
            <i class="fa fa-calendar"></i>
        </span>
    </div>
</template>