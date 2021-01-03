$(document).ready(function () {

    initGridData("list/search", null,
        [
            {name: 'index', header: '顺序'},
            {name: 'title', header: '标题',width:180},
            {
                name: 'href', header: '链接', formatter: function (val) {
                    return hyperlinkFormatter(val, val);
                },width:150
            },
            {name: 'desc', header: '描述',width:180},
            {name: 'statusName', header: '状态'},
            {name: 'url', header: '图片', formatter: imgPreViewFormatter,width:150},
            {name: 'createDate', header: '上传时间', formatter: yyyyMMddhhmmFormatter},
            {name: 'id', header: "id", hidden: true},
        ]);

});