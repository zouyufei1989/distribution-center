<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<style type="text/css">
    /**规定搜索区域控件的长度**/
    form select[search-param] {
        width: 150px !important;
    }

    form input[search-param] {
        width: 150px !important;
    }
</style>

<script type="text/javascript">
    Vue.component('reload-export-btn-group', {
        props: ['reload', 'exportexcel', "exporturl"],
        methods: {
            exportExcel: function () {
                if (typeof exportExcelOverride === 'function') {
                    exportExcelOverride();
                    return;
                }

                var params = getSearchParams();
                window.open("${pageContext.request.contextPath}/exportExcel/" + this.exporturl + "?" + $.param(params));
            }
        },
        template: '#reload-export-btn-template'
    })

    $(document).ready(function () {
        new Vue({el: '#btnGroup'});
    });

</script>


<template id="reload-export-btn-template">
    <div class="pull-right form-group">
        <button v-show="reload" type="button" class="btn btn-default btn-search" onclick="reloadList()">查询</button>
        <button v-show="exportexcel" type="button" class="btn btn-default btn-search" @click="exportExcel">导出</button>
    </div>
</template>