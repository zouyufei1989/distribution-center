<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<button type="button" data-style="zoom-in" class="ladda-button btn btn-w-m btn-primary btn-update-footer" id="btn_save">保存</button>
<button type="button" class="btn btn-w-m btn-default btn-update-footer" id="btn_cancle">取消</button>
<%@ include file="template_loading_modal.jsp" %>

<script type="text/javascript">

    $(document).ready(function () {

        if (!editOnModal()) {
            if (JS_PAGE_PARAMS['id']) {
                if (typeof findByIdOverride === 'function') {
                    // 如果更新页面，查询详情需要自定义方法，写在findByIdOverride 方法中
                    findByIdOverride();
                } else {
                    findById();
                }
            }
        }

        $("#btn_save").click(function () {
            if ($("#mainForm").valid() == false) {
                return;
            }

            if (typeof additionValid === "function" && additionValid() === false) {
                return;
            }

            Confirm("确定保存吗?", function () {
                loadingStart(function () {
                    if (editData()) {
                        edit();
                        return;
                    }
                    add();
                });
            });
        });

        $("#btn_cancle").click(function () {
            Confirm("确定要取消编辑？", function () {
                if (editOnModal()) {
                    $('#updateModal').modal('hide');
                } else {
                    goBack();
                }
            });
        });
    });

    function editData() {
        if (editOnModal()) {
            return $('#updateModal h3').text().indexOf("编辑") === 0;
        }
        return JS_PAGE_PARAMS['id'];
    }

    function add() {
        var param = initParam();

        $.ajax({
            url: 'add',
            type: 'post',
            headers: {
                "Cache-Control": "no-cache",
                'Accept': 'application/json',
                'Content-Type': 'application/json;charset=UTF-8'
            },
            data: JSON.stringify(param),
            async: true,
            cache: false,
            success: function (result) {
                if (result.success === false) {
                    loadingEnd(function () {
                        Alert("", result.message || "失败！", "error");
                    });
                    return;
                }
                loadingEnd(function () {
                    $('#updateModal').modal("hide");
                    Alert("", "成功！", "success", function () {
                        if (editOnModal()) {
                            reloadList();
                        } else {
                            goBack();
                        }
                    });
                });
            }
        });
    }

    function edit() {
        var param = initParam();

        $.ajax({
            url: 'edit',
            type: 'post',
            headers: {
                "Cache-Control": "no-cache",
                'Accept': 'application/json',
                'Content-Type': 'application/json;charset=UTF-8'
            },
            data: JSON.stringify(param),
            async: true,
            cache: false,
            success: function (result) {
                if (result.success === false) {
                    loadingEnd(function () {
                        Alert("", result.message || "失败！", "error");
                    });
                    return;
                }
                loadingEnd(function () {
                    $('#updateModal').modal("hide");
                    Alert("", "成功！", "success", function () {
                        if (editOnModal()) {
                            reloadList();
                        } else {
                            goBack();
                        }
                    });
                });
            }
        });
    }

    function getEditRowId() {
        if (editOnModal() && _ROWS_CHOOSED && _ROWS_CHOOSED[0]) {
            return _ROWS_CHOOSED[0].id;
        }
        if (!editOnModal()) {
            return JS_PAGE_PARAMS['id'];
        }
        return "";
    }

    function initParam() {
        var param = {};
        param.id = getEditRowId();

        $.each(attrs, function (index, item) {
            if ($("#" + item).is(":visible")) {
                param[item] = $('#' + item).val();
            }
        })

        if (typeof additionParam === "function") {
            var _param = additionParam(param);
            $.each(_param, function (k, v) {
                param[k] = v;
            });
        }

        return param;
    }

    function findById() {
        $.ajax({
            url: 'findById',
            type: 'post',
            data: {
                id: getEditRowId()
            },
            async: true,
            cache: false,
            success: function (result) {
                if (result.success == false) {
                    Alert("", "失败！", "error");
                    return;
                }

                $.each(attrs, function (index, item) {
                    if ($("#" + item).is(":visible")) {
                        $('#' + item).val(result.data[item]);

                        if ($('#' + item)[0].tagName === 'SELECT') {
                            $('#' + item).trigger('change');
                        }
                    }
                });

                if (typeof fillAdditionAttrs === 'function') {
                    fillAdditionAttrs(result);
                }

            }
        });
    }


</script>

