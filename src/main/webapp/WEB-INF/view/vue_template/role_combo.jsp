<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<script type="text/javascript">
    Vue.component('role-combo', {
        props : [ 'id', 'must_choose_one' ],
        data : function() {
            var roles = this.must_choose_one=="false" ? [ {
                name : '请选择',
                id : ''
            } ] : [];
            $.ajax({
                url : '${ctx}/utils/selectRoles',
                data : {
                    page:0,
                    rows:0
                },
                type : 'post',
                async : false,
                success : function(result) {
                    for(var i=0;i<result.rows.length;i++){
                        if(result.rows[i].enable){
                            roles.push(result.rows[i]);
                        }
                    }
                }
            });
            return {
                items : roles
            }
        },
        mounted : function() {
            $('#' + this.id).select2();

        },
        template : '#role-combo-template'
    })
</script>


<template id="role-combo-template">
    <select :id="id" class="select2_demo_3 form-control m-b">
        <option v-for="(item,index) in items" :key="item.id" :value="item.id">{{item.name}}</option>
    </select>
</template>