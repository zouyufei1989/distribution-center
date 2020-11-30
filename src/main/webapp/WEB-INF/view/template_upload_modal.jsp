<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<script type="text/javascript">
    Vue.component('upload-img-modal', {
        props: ['id', 'title', 'target', 'img'],
        data: function () {
            return {
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
                var option = {
                    url: "${ctx}/utils/uploadFileToUpyun",
                    type: "POST",
                    async: true,
                    success: function (result) {
                        if (result.success) {
                            $('#' + _this.inputId).val('');
                            $("#" + _this.id).modal("hide");

                            if (_this.target) {
                                $('#' + _this.target).val(result.fileUrl);
                            }
                            if (_this.img) {
                                $('#' + _this.img).attr("src", result.fileUrl);
                            }
                            if (typeof uploadSuccess === 'function') {
                                uploadSuccess(result);
                            }
                            return;
                        }
                        Alert("", "上传失败!", "error");
                    }
                }
                $("#" + _this.formId).ajaxSubmit(option);
            });
        }
        ,
        template: '#upload-template'
    })

</script>


<template id="upload-template">
    <div class="modal inmodal fade" :id="id" role="dialog" aria-hidden="true" data-backdrop="static">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                    </button>
                    <h5>{{title}}</h5>
                    <hr/>
                    <form class="form-horizontal" :id="formId" enctype="multipart/form-data">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">{{title}}:</label>
                            <div class="col-sm-8">
                                <div class="input-group">
                                    <input type="file" class="form-control" name="file" :id="inputId"> <span class="input-group-btn">
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