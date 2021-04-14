<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<script type="text/javascript">
    Vue.component('goods-type-combo', {
        props: ['id', 'must_choose_one', 'data_exclude', 'value'],
        data: function () {
            var data = [];
            var _this = this;
            $.ajax({
                url: '${ctx}/utils/selectGoodsType',
                data: {},
                type: 'post',
                async: false,
                cached: false,
                success: function (result) {
                    for (var key in result.rows) {
                        if (_this.data_exclude == key) {
                            continue;
                        }
                        data.push({
                            value: key,
                            name: result.rows[key]
                        });
                    }
                }
            });
            data = sortArr(data, (i, j) => {
                return i.value == j.value ? 0 : i.value - j.value;
            });
            if (this.must_choose_one == 'false' || this.must_choose_one == false) {
                data = [{value: null, name: '请选择'}].concat(data);
            }
            return {
                items: data
            }
        },
        watch: {
            value: function (newVal, oldVal) {
                $('.select2_demo_3').select2();
                $('#' + this.id).val(this.value).trigger('change');
            }
        },
        mounted: function () {
            var _this = this;
            _this.$nextTick(function () {
                /*现在数据已经渲染完毕*/
                $('.select2_demo_3').select2();
                $('#' + _this.id).val(_this.value).trigger('change');
            })
        }, template: '#goods-type-combo-template'
    })
</script>


<template id="goods-type-combo-template">
    <select :id="id" class="select2_demo_3 form-control m-b">
        <option v-for="(item,index) in items" :value="item.value">{{item.name}}</option>
    </select>
</template>