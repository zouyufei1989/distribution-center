var attrs = ["fullClass", 'year', 'month', 'day', 'hour', 'minute', 'comment', 'params'];

$(document).ready(function () {
    new Vue({el: '#routes'});
    initWangEditor();
});

function initWangEditor() {
    var E = window.wangEditor;
    editor = new E('#editor');
    editor.customConfig.menus = [
        'head',  // 标题
        'bold',  // 粗体
        'fontSize',  // 字号
        'fontName',  // 字体
        'italic',  // 斜体
        'foreColor',  // 文字颜色
        'backColor',  // 背景颜色
        'link',  // 插入链接
        'list',  // 列表
        'justify',  // 对齐方式
        'table',  // 表格
        'image',  // 插入图片
    ];
    editor.customConfig.uploadImgServer = '../utils/uploadFileToUpyun';
    editor.customConfig.uploadFileName = 'file';
    editor.create()
}

function additionParam() {
    var addParam = {
        comment: editor.txt.html(),
    };

    return addParam;
}

function fillAdditionAttrs(result) {
    editor.txt.html(result.data.comment);
}

function rebuildParams(oldParam) {
    var newParams = [];
    for (var key in oldParam) {
        newParams.push(key + "=" + oldParam[key]);
    }
    return newParams.join("&");
}
