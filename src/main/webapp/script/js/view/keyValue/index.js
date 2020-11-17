$(document).ready(function () {
    initGridData("list/search", null,
        [
            {name: 'key', header: 'key'},
            {name: 'value', header: 'value'},
            {name: 'comment', header: '说明'},
            {name: 'createDate', header: '创建日期', formatter: yyyyMMddhhmmFormatter},
            {name: 'id', header: 'id', hidden: true},
        ]);

});
