<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<script type="text/javascript">
    Vue.component('employee-combo', {
        props: ['id', 'must_choose_one', 'group_id', 'timestamp', 'value', 'async', 'status', 'exclude'],
        data: function () {
            return {
                items: []
            }
        },
        methods: {
            reload() {
                var _this = this;
                $.ajax({
                    url: '${ctx}/employee/list/search',
                    data: {
                        'employee.status': _this.status ? _this.status : 1,
                        'employee.groupId': _this.group_id
                    },
                    type: 'post',
                    async: _this.async ? true : false,
                    success: function (result) {
                        _this.items = result.rows.map(o => {
                            return {
                                id: o.id,
                                name: o.name,
                            }
                        });

                        _this.$nextTick(function () {
                            $('#' + _this.id).select2();
                            if (_this.value) {
                                $('#' + _this.id).val(_this.value).trigger('change');
                            }
                        })

                    }
                });
            }
        },
        computed: {
            availables: function () {
                if (this.exclude) {
                    var _this = this;
                    return _this.items.filter(i => i.id != _this.exclude)
                }
                return this.items;
            }
        },
        mounted: function () {
            this.reload();
        },
        watch: {
            timestamp: function (newValue, oldValue) {
                this.reload();
            },
            group_id: function (newValue, oldValue) {
                console.log('gr' + newValue)
                this.reload();
            },
        },
        template: '#employee-combo-template'
    })
</script>


<template id="employee-combo-template">
    <select :id="id" class="select2_demo_3 form-control m-b">
        <option v-show="!must_choose_one">请选择</option>
        <option v-for="(item,index) in availables" :key="item.id" :value="item.id">{{item.name}}</option>
    </select>
</template>