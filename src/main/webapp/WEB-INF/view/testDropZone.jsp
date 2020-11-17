<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>

    <link rel="stylesheet" href="http://cn.inspinia.cn/html/inspiniaen/css/plugins/dropzone/dropzone.css">
    <script src="../script/plugins/dropzone/dropzone.js"></script>
</head>

<body>
<div id="dropzoneForm" class="dropzone" action="uploadFileUrl"></div>

<%--禁用后，上传区域不可点击--%>
<button onclick="DROPZONE.removeEventListeners();">禁用</button>
<%--启用后，上传区域恢复点击上传文件--%>
<button onclick="DROPZONE.setupEventListeners();">启用</button>
<%--手动初始化一张已存在的服务器图片到dropzone--%>
<button onclick="setImg()">加载网络图片</button>
</body>


<script type="text/javascript">
    Dropzone.options.dropzoneForm = {
        paramName: "file", // The name that will be used to transfer the file
        maxFilesize: 2, // MB
        maxFiles: 3,
        dictDefaultMessage: "<strong>点击上传站点图片。</strong>",
        acceptedFiles: 'image/*',
        addRemoveLinks: true,
        dictRemoveFile: "删除",
        init: function () {
            DROPZONE = this;
            DROPZONE.on("success", function (file, response) {
                //response 是上传图片 服务器返回值
                console.log(JSON.stringify(response));
            });

            DROPZONE.on("removedfile", function (file) {
                //删除文件触发的事件
                console.log(file.name)
            });
        },
    };

function setImg(){
    //初始化服务器图片到dropzone （不可跨域请求）
    var url = "http://xxx/xxx.jpg";
    var mockFile = {name: "stop.jpg", accepted: true, status: "success", processing: "true", url: url};
    DROPZONE.emit("addedfile", mockFile);
    DROPZONE.emit("thumbnail", mockFile, url);
    DROPZONE.emit("complete", mockFile);
    DROPZONE.createThumbnailFromUrl(mockFile, url);
}

</script>

</html>