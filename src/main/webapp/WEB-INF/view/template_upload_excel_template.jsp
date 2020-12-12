<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<script type="text/javascript">
    Vue.component('upload-excel-modal', {
        props: ['id', 'title', 'template_url', 'upload_url'],
        data: function () {
            return {
                modalId: this.id,
                inputId: "input_" + this.id,
                formId: "form_" + this.id,
                btnId: "btn_" + this.id,
            }
        },
        mounted: function () {
            var _this = this;
            $("#" + _this.btnId).click(function () {
                var file = $('#' + _this.inputId).val();
                if (file == '') {
                    Alert("", "请选择文件!");
                    return;
                }

                loadingStart(function () {
                    uploadFile(_this.upload_url, _this.formId, function (result) {
                        $('#' + _this.inputId).val('');
                        $("#" + _this.id).modal("hide");
                        reloadList();
                    }, function (result) {
                        Alert("", result.message, "error");
                    });
                }, 1);
            });
        },
        template: '#upload-excel-template'
    })

    $(document).ready(function () {
        $(function () {
            new Vue({el: '#uploadModal'});
        });
    });
</script>


<template id="upload-excel-template">
    <div class="modal inmodal fade" :id="modalId" role="dialog" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <h5>{{title}}</h5>
                    <form class="form-horizontal" :id="formId" enctype="multipart/form-data">
                        <div class="form-group">
                            导入模版:<a :href="template_url">点此下载</a>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">上传文件:</label>
                            <div class="col-sm-8">
                                <div class="input-group">
                                    <input type="file" class="form-control" :id="inputId" name="uploadFile">
                                    <span class="input-group-btn">
                                        <button type="button" data-style="zoom-in" class="ladda-button btn btn-w-m btn-primary btn-update-footer" :id="btnId">上&nbsp;&nbsp;&nbsp;&nbsp;传</button>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</template>
<%@ include file="template_loading_modal.jsp" %>