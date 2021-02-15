var videoListModalVue;
$(document).ready(function () {

    videoListModalVue = new Vue({
        el: '#videoListModal',
        data: {
            videoList: [],
            groupId: null
        },
        methods: {
            show() {
                $('#videoListModal').modal('show');
            },
            hide() {
                $('#videoListModal').modal('hide');
            },
            hideUploadModal() {
                $('#uploadVideoModal').modal('hide');
            },
            uploadVideo(e) {
                var _this = this;
                var file = $('#video').val();
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
                                    $('#video').val('');
                                    _this.hideUploadModal();
                                    _this.videoList.push(result.fileUrl);
                                    return;
                                }
                                Alert("", "上传失败!", "error");
                            });
                        }
                    }
                    $("#uploadVideoForm").ajaxSubmit(option);
                });
            },
            remove(i) {
                this.videoList.splice(i, 1);
            },
            save(e) {
                var _this = this;

                loadingStart($(e.target), function () {
                    $.ajax({
                        url: 'editVideoList',
                        type: 'post',
                        headers: {
                            "Cache-Control": "no-cache",
                            'Accept': 'application/json',
                            'Content-Type': 'application/json;charset=UTF-8'
                        },
                        data: JSON.stringify({
                            groupId:_this.groupId,
                            videoList:_this.videoList.join(";")
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
