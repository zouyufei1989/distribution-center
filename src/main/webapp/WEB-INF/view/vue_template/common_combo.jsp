<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<script type="text/javascript">
    Vue.component('common-combo', {
        props: ['id', 'must_choose_one', 'propitems', 'maxint', 'minint', 'val', 'tag', 'search_param'],
        data: function () {
            if (this.propitems) {
                return {
                    items: this.propitems
                }
            }

            var result = [];
            for (var i = this.minint || 0; i <= this.maxint; i++) {
                result.push(
                    {
                        value: i,
                        text: i
                    }
                );
            }
            return {
                items: result
            }
        },
        methods: {
            change: function () {
            }
        },
        mounted: function () {
            $('#' + this.id).select2();
            if (this.val) {
                $('#' + this.id).val(this.val).trigger('change');
            }

        }, template: '#common-combo-template'
    })

</script>


<template id="common-combo-template">
    <select :id="id" class="select2_demo_3 form-control m-b" :tag="tag" onchange="commonComboChange(this)" search-param v-if="search_param">
        <option v-for="(item,index) in items" :value="item.value">{{item.text}}</option>
    </select>
    <select :id="id" class="select2_demo_3 form-control m-b" :tag="tag" onchange="commonComboChange(this)" v-else>
        <option v-for="(item,index) in items" :value="item.value">{{item.text}}</option>
    </select>
</template>