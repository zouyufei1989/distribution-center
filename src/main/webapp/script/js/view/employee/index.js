$(document).ready(function () {

    // new Vue({el: '.param_row'});
    searchVue = new Vue({el: '.param_row', data: {groupId: null}});

    initGridData("list/search", null,
        [
            {name: 'name', header: '姓名'},
            {name: 'phone', header: '手机号'},
            {name: 'statusName', header: '状态'},
            {name: 'createDate', header: '创建日期', formatter: yyyyMMddhhmmFormatter},
            {name: 'id', header: "id", hidden: true},
        ]);

});