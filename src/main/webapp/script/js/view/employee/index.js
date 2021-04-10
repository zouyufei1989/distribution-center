$(document).ready(function () {

    // new Vue({el: '.param_row'});
    searchVue = new Vue({el: '.param_row', data: {groupId: null}});

    initGridData("list/search", null,
        [
            {name: 'name', header: '员工姓名'},
            {name: 'genderName', header: '性别'},
            {name: 'identifyNo', header: '身份证号'},
            {name: 'phone', header: '手机号'},
            {name: 'groupName', header: '门店'},
            {name: 'joinDate', header: '入职时间'},
            {name: 'statusName', header: '状态'},
            {name: 'groupId', hidden: true},
            {name: 'id', header: "id", hidden: true},
        ]);


    $('#btn_bind_shareholder').click(function () {
        if (_ROWS_CHOOSED.length != 1) {
            Alert('', '请选择一条编辑数据', 'error');
            return
        }
        bindModalVue.type = 'bind';
        bindModalVue.employee = _ROWS_CHOOSED[0];
        bindModalVue.choosed = [];
        bindModalVue.searchParam = {
            phone: '',
            bonusPlanId: ''
        };
        $('#bindModal').modal('show');
    });

});