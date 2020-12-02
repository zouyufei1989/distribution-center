var detailEditor;
$(document).ready(function () {
    initWangEditor();
    $('#btn_edit_detail').click(function () {
        if (_ROWS_CHOOSED.length != 1) {
            Alert("", "请选择一个商品", "");
            return;
        }
        detailEditor.txt.html(_ROWS_CHOOSED[0].detail);
        $('#detailEditModal').modal('show');
    });
});

function initWangEditor() {
    var E = window.wangEditor;
    detailEditor = new E('#detailEditor');
    detailEditor.config.menus = [
        'head',
        'bold',
        'fontSize',
        'fontName',
        'italic',
        'underline',
        'strikeThrough',
        'indent',
        'lineHeight',
        'foreColor',
        'backColor',
        'link',
        'quote',
        'emoticon',
        'image',
        'splitLine',
        'undo',
        'redo',
    ];
    detailEditor.config.uploadImgServer = '../utils/uploadFileToUpyun';
    detailEditor.config.uploadFileName = 'file';
    detailEditor.config.withCredentials = true
    detailEditor.config.uploadImgHeaders = {
        "X-XSRF-TOKEN": getCookie('XSRF-TOKEN')
    }
    detailEditor.config.height = 700;
    detailEditor.config.pasteFilterStyle = false;
    detailEditor.create()
}

function saveDetail(){
    loadingStart(function(){
        $.ajax({
            url: 'edit',
            type: 'post',
            headers: {
                "Cache-Control": "no-cache",
                'Accept': 'application/json',
                'Content-Type': 'application/json;charset=UTF-8'
            },
            data: JSON.stringify({
                id: _ROWS_CHOOSED[0].id,
                detail: detailEditor.txt.html()
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
                    $('#updateModal').modal("hide");
                    Alert("", "成功！", "success", function () {
                        $('#detailEditModal').modal('hide');
                        reloadList();
                    });
                });
            }
        });
    });
}

function cancelEdit(){
    Confirm("确定要取消编辑？", function () {
        $('#detailEditModal').modal('hide');
    });
}