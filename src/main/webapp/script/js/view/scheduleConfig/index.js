$(document).ready(function () {
    initGridData("list/search", null,
        [
            {
                name: 'fullClass', header: 'fullClass', width: 200,
                formatter: function (val) {
                    if (val) {
                        return val.substring(val.lastIndexOf(".") + 1);
                    }
                    return "";
                }
            },
            {name: 'comment', header: 'comment', width: 200},
            {name: 'year', header: 'year'},
            {name: 'month', header: 'month'},
            {name: 'day', header: 'day'},
            {name: 'hour', header: 'hour'},
            {name: 'minute', header: 'minute'},
            {name: 'params', header: '参数'},
            {name: 'createDate', header: '创建日期', formatter: yyyyMMddhhmmFormatter},
            {name: 'id', header: 'id', hidden: true},
        ]);

});
