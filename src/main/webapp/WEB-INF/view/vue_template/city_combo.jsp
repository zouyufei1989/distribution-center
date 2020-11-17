<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<script type="text/javascript">
    Vue.component('city-combo', {
        props : [ 'id', 'must_choose_one' ,'city_code'],
        data : function() {
            var items = this.must_choose_one=="false" ? [ {
                name : '请选择',
                id : ''
            } ] : [];
            $.ajax({
                url : '${ctx}/utils/selectOpenCities',
                data : {},
                type : 'post',
                async : false,
                success : function(result) {
                    for(var i=0;i<result.rows.length;i++){
                        items.push({
                            name:result.rows[i].cityChName,
                            id:result.rows[i].cityCode
                        });
                    }
                }
            });
            return {
                items : items
            }
        },
        mounted : function() {
            $('#' + this.id).select2();
        },
        watch: {
            city_code: function (newValue, oldValue) {
                var _this = this;

                if (newValue) {
                    $('#' + _this.id).val(newValue).trigger('change');
                }

            }
        },
        template : '#city-combo-template'
    })
</script>


<template id="city-combo-template">
    <select :id="id" class="select2_demo_3 form-control m-b">
        <option v-for="(item,index) in items" :key="item.id" :value="item.id">{{item.name}}</option>
    </select>
</template>