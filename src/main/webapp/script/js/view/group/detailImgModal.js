var coverImgModalVue;
$(document).ready(function () {

    coverImgModalVue = new Vue({
        el: '#coverImgModal',
        data: {
            detailImgList: [],
            groupId: null
        },
        methods: {
            show() {
                $('#coverImgModal').modal('show');
            },
            hide() {
                $('#coverImgModal').modal('hide');
            },
            hideUploadModal() {
                $('#uploadDetailImgModal').modal('hide');
            },
            uploadImg(e) {
                var _this = this;
                var file = $('#detailImg').val();
                if (file == '') {
                    Alert("", "请选择文件!");
                    return;
                }
                loadingStart($(e.target), function () {
                    var option = {
                        url: "../utils/uploadFileToUpyun",
                        type: "POST",
                        async: true,
                        success: function (result) {
                            loadingEnd(function () {
                                if (result.success) {
                                    $('#detailImg').val('');
                                    _this.hideUploadModal();
                                    _this.detailImgList.push(result.fileUrl);
                                    return;
                                }
                                Alert("", "上传失败!", "error");
                            });
                        }
                    }
                    $("#uploadDetailImgForm").ajaxSubmit(option);
                });
            },
            remove(i) {
                this.detailImgList.splice(i, 1);
            },
            save(e) {
                var _this = this;

                loadingStart($(e.target), function () {
                    $.ajax({
                        url: 'editDetailImg',
                        type: 'post',
                        headers: {
                            "Cache-Control": "no-cache",
                            'Accept': 'application/json',
                            'Content-Type': 'application/json;charset=UTF-8'
                        },
                        data: JSON.stringify({
                            groupId:_this.groupId,
                            detailCoverImg:_this.detailImgList.join(";")
                        }),
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
                                Alert("", "成功！", "success", function () {
                                    _this.hide();
                                    reloadList();
                                });
                            });
                        }
                    });
                });
            }
        }
    });
});
