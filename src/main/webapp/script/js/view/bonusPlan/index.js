$(document).ready(function () {

    new Vue({el:'.param_row'});

    initGridData("list/search", null,
        [
            {name: 'name', header: '积分方案名称',width:120},
            {name: 'serialNumber', header: '积分方案编号'},
            {name: 'groupName', header: '所属门店'},
            {name: 'bonusRate4Show', header: '积分比例'},
            {name: 'usedCount', header: '已分配股东数'},
            {name: 'desc', header: '备注',width:120},
            {name: 'createDate', header: '创建时间', formatter: yyyyMMddhhmmFormatter},
            {name: 'statusName', header: '状态'},
            {name: 'id', header: "id", hidden: true},
        ]);

});