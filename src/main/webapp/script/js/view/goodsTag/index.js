$(document).ready(function () {

    new Vue({el:'.param_row'});

    initGridData("list/search", null,
        [
            {name: 'name', header: '商品标签名称',width:130},
            {name: 'groupName', header: '所属门店',width:120},
            {name: 'remark', header: '备注',width:200},
            {name: 'createDate', header: '创建时间', formatter: yyyyMMddhhmmFormatter},
            {name: 'statusName', header: '状态'},
            {name: 'id', header: "id", hidden: true},
        ]);

});