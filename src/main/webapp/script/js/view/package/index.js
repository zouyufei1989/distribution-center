var searchVue;
$(document).ready(function () {

    searchVue = new Vue({el: '.param_row', data: {groupId: null}});

    initGridData("list/search", null,
        [
            {name: 'name', header: '套餐名称'},
            {name: 'groupName', header: '所属门店'},
            {name: 'desc', header: '套餐描述'},
            {name: 'createDate', header: '创建时间', formatter: dateFormatter},
            {name: 'statusName', header: '状态'},
            {name: 'id', header: "id", hidden: true},
        ]);

});