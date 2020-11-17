$(document).ready(function () {
    new Vue({el: "#type"});
    new Vue({el: "#startDate"});
    new Vue({el: "#endDate"});
    new Vue({el: "#userId"});

    initGridData("list/search", null,
        [
            {name: 'moduleName', header: '模块名称'},
            {name: 'resourceName', header: '功能名称'},
            {name: 'typeName', header: '类型'},
            {name: 'duration', header: '执行时长(毫秒)'},
            {name: 'userName', header: '访问者'},
            {name: 'createDate', header: '访问时间', formatter: yyyyMMddhhmmssFormatter},
            {name: 'id', header: "id", hidden: true},
        ]);
});
