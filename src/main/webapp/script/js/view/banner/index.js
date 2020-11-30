$(document).ready(function () {

    initGridData("list/search", null,
        [
            {name: 'index', header: '顺序'},
            {name: 'title', header: '标题'},
            {
                name: 'href', header: '链接', formatter: function (val) {
                    return hyperlinkFormatter(val, val);
                }
            },
            {name: 'desc', header: '描述'},
            {name: 'statusName', header: '状态'},
            {name: 'url', header: '图片', formatter: imgPreViewFormatter},
            {name: 'createDate', header: '上传时间', formatter: yyyyMMddhhmmFormatter},
            {name: 'id', header: "id", hidden: true},
        ]);

});