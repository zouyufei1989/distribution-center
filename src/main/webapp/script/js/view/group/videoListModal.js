var videoListModalVue;
$(document).ready(function () {

    videoListModalVue = new Vue({
        el: '#videoListModal',
        data: {
            videoList: [],
            groupId: null,
        },
        methods: {
            effectUrl(url){
                console.log(url)
                return url && url.length>0;
            },
            show() {
                $('#videoListModal').modal('show');
            },
            hide() {
                $('#videoListModal').modal('hide');
            },
            hideUploadModal() {
                $('#uploadVideoModal').modal('hide');
            },
            hideUploadCoverModal() {
                $('#uploadVideoCoverModal').modal('hide');
            },
            upload(e,attr,formId) {
                var _this = this;
                var file = $('#'+formId + ' input').val();
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
                                    $('#'+formId + ' input').val('')
                                    _this.hideUploadModal();
                                    _this.hideUploadCoverModal();

                                    var i = 0;
                                    for (; i < _this.videoList.length; i++) {
                                        if (!_this.videoList[i]) {
                                            break;
                                        }else if (!_this.videoList[i][attr]){
                                            break;
                                        }
                                    }
                                    if(i==_this.videoList.length){
                                        var item = {imgUrl:'',videoUrl:''};
                                        item[attr] = result.fileUrl;
                                        _this.videoList.push(item);
                                    }else{
                                        _this.videoList[i][attr] = result.fileUrl;
                                    }

                                    return;
                                }
                                Alert("", "上传失败!", "error");
                            });
                        }
                    }
                    $("#"+formId).ajaxSubmit(option);
                });
            },
            removeImg(i) {
                this.videoList[i].imgUrl = "";
                if(!this.videoList[i].videoUrl){
                    this.videoList.splice(i,1);
                }
            },
            removeVideo(i) {
                this.videoList[i].videoUrl = "";
                if(!this.videoList[i].imgUrl){
                    this.videoList.splice(i,1);
                }
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
                            groupId: _this.groupId,
                            videoList: JSON.stringify(_this.videoList)
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
