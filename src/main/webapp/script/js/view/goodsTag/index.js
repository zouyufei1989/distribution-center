$(document).ready(function () {

    new Vue({el:'.param_row'});

    initGridData("list/search", null,
        [
            {name: 'name', header: '商品标签名称'},
            {name: 'groupName', header: '所属门店'},
            {name: 'remark', header: '备注'},
            {name: 'createDate', header: '创建时间', formatter: dateFormatter},
            {name: 'statusName', header: '状态'},
            {name: 'id', header: "id", hidden: true},
        ]);

});