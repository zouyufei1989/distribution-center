<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<script type="text/javascript">
    Vue.component('visit-log-type-combo', {
        props: ['id','must_choose_one'],
        data: function () {
            var items = this.must_choose_one == "false" ? [{
                name: '请选择',
                value: ''
            }] : [];
            $.ajax({
                    url: '${ctx}/utils/selectVisitLogTypeEnum',
                    data: {},
                    type: 'post',
                    async: false,
                    cached: false,
                    success: function (result) {
                        for(var key in result.rows){
                            items.push({
                                value : key,
                                name: result.rows[key]
                            });
                        }
                    }
                }
            );

            return {
                items: items
            }
        },
        mounted: function () {
            $('#' + this.id).select2();
        },
        template: '#visit-log-type-combo-template'
    })
</script>

<template id="visit-log-type-combo-template">
    <select :id="id" class="select2_demo_3 form-control m-b" >
        <option v-for="(item,index) in items" :value="item.value">{{item.name}}
        </option>
    </select>
</template>