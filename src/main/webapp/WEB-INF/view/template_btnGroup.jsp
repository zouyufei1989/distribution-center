<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="form-group" id="div_btnGroup" v-cloak style="margin-bottom:20px">
    <template>
        <button v-for="btn in buttons" type="button" :class="['btn','btn-outline','btn-tool',btn.btn_class]" :id="btn.btn_id" data-toggle="modal" :data-target="btn.modal_name" :data-func-id="btn.fun_id">{{btn.btn_name}}</button>
    </template>
</div>

<script type="text/javascript">
    $(document).ready(function () {

        if (editOnModal()) {
            bindModalShow('updateModal', function () {
                $('.select2_demo_3').select2();
                $('.select2_demo_3').trigger('change');
            }, 1);
        }

        new Vue({
            el: "#div_btnGroup",
            computed: {
                buttons: function () {
                    var result = [];
                    // get the funcs of the user login
                    $.each(JS_FUNC_LIST, function (index, func) {
                        if ((func.fun_id & JS_ROLE_FUNC_IDS) == func.fun_id) {
                            result.push(func);
                        }
                    });
                    return result;
                }
            },
            methods: {
                getModalName: function () {
                    return $('#header_level_nav a:eq(1)').text().replace("管理", "");
                }
            },
            mounted() {
                // bind buttons' events
                var _this = this;
                $("#btn_new").click(function () {
                    if (editOnModal()) {
                        $('#updateModal h3').text("新建" + _this.getModalName());
                        $('#updateModal form').validate().resetForm();
                        $('#updateModal img').attr('src','');
                        $('#updateModal').modal('show');
                        if(typeof additionFunc4Add === 'function'){
                            additionFunc4Add();
                        }
                        return;
                    }
                    var params = [];
                    params.push("from=" + JS_PAGE_NAME);
                    params.push("funcId=" + $(this).attr('data-func-id'));
                    window.location.href = "update?" + params.join('&');
                });

                $("#btn_update").click(function () {
                    if (_ROWS_CHOOSED.length != 1) {
                        Alert("", "请选择一条需要编辑的数据！");
                        return;
                    }

                    if (_ROWS_CHOOSED[0].editable === "false") {
                        Alert("", "选中数据不可编辑！");
                        return;
                    }
                    if (editOnModal()) {
                        if (typeof findByIdOverride === 'function') {
                            // 如果更新页面，查询详情需要自定义方法，写在findByIdOverride 方法中
                            findByIdOverride();
                        } else {
                            findById();
                        }
                        $('#updateModal h3').text("编辑" + _this.getModalName());
                        $('#updateModal form').validate().resetForm();
                        $('#updateModal img').attr('src','');
                        $('#updateModal').modal('show');
                        return;
                    }
                    var params = [];
                    params.push("from=" + JS_PAGE_NAME);
                    params.push("id=" + _ROWS_CHOOSED[0].id);
                    params.push("funcId=" + $(this).attr('data-func-id'));
                    window.location.href = "update?" + params.join('&');
                });

                $("#btn_disable").click(function () {
                    Confirm("确定要禁用选中行？", function () {
                        changeStatus(0);
                    });

                });

                $("#btn_enable").click(function () {
                    Confirm("确定要启用选中行？", function () {
                        changeStatus(1);
                    });
                });

                //逻辑删除
                $("#btn_delete").click(function () {
                    Confirm("确定要删除选中行？", function () {
                        if (typeof deleteRow === 'function') {
                            deleteRow();
                        } else {
                            changeStatus(-2);
                        }
                    });
                });

                //物理删除+备份
                $('#btn_deleteByIds').click(function () {
                    if (_ROWS_CHOOSED.length === 0) {
                        Alert('', '请选择要删除的数据', 'error');
                        return;
                    }

                    Confirm('确定删除选中数据?', function () {
                        $.ajax({
                            url: 'deleteByIds',
                            type: 'post',
                            headers: {
                                'Accept': 'application/json',
                                'Content-Type': 'application/json;charset=UTF-8'
                            },
                            data: JSON.stringify({ids: _ROWS_CHOOSED.map(i => i.id)}),
                            async: true,
                            cache: false,
                            success: function (result) {
                                if (result.success === false) {
                                    Alert("", result.message || "失败！", "error");
                                    return;
                                }
                                Alert("", "成功！", "success", function () {
                                    reloadList();
                                });

                            }
                        });
                    });
                });

                $("#btn_exportExcel").click(function () {
                    window.open('exportExcel?' + $.param(getSearchParams()));
                });
            }
        });
    });

    function changeStatus(status, url, callback, params) {
        var request = params;

        if (!request) {
            if (!_ROWS_CHOOSED || _ROWS_CHOOSED.length == 0) {
                Alert("", "请选择需要编辑的数据！", '');
                return;
            }

            for (var i = 0; i < _ROWS_CHOOSED.length; i++) {
                if (_ROWS_CHOOSED[i].editable === "false") {
                    Alert("", "选中数据存在不可编辑！", '');
                    return;
                }
            }

            request = {
                ids: _ROWS_CHOOSED.map(function (item) {
                    return item.id
                }),
                status: status
            };
        }

        $.ajax({
            url: url || 'changeStatus',
            type: 'post',
            data: JSON.stringify(request),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json;charset=UTF-8'
            },
            async: true,
            cache: false,
            success: function (result) {
                if (result.success == false) {
                    Alert("", result.message || "失败！", "error");
                    return;
                }

                Alert("", "成功！", "success", function () {
                    if (callback && typeof callback === 'function') {
                        callback();
                        return;
                    }
                    reloadList();
                });

            }
        });
    }

</script>