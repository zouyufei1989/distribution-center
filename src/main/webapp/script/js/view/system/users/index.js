$(document).ready(function () {

    initGridData("list/search", null,
        [
            {header: '登录账号', name: 'username'},
            {header: '用户名', name: 'nickName'},
            {header: '角色', name: 'roleName'},
            {header: '状态', name: 'statusName'},
            {
                header: '所属公司', name: 'groupName'
            },
            {
                header: '操作', name: 'id', formatter: function (id) {
                    return hyperlinkeButtonFormatter('修改密码', 'showChangePwdModal(' + id + ')');
                }
            },
            {header: '角色编号', name: 'id', hidden: true, id: true}
        ]);

});

function deleteRow() {
    if (_ROWS_CHOOSED.length != 1) {
        Alert('', '请选择一条待删除数据', 'error');
        return;
    }
    changeStatus(-2, 'deleteById');
}

function showChangePwdModal(id) {
    $('#userId').val(id);
    $('#changePwdModal').modal('show');
}

function changePwd() {

    if ($("#pwdForm").valid() == false) {
        return;
    }

    $.ajax({
        url: 'changePwd',
        type: 'post',
        headers: {
            "Cache-Control": "no-cache",
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        },
        data: JSON.stringify({
            id: $('#userId').val(),
            password: encryptRSAPwd($("#pass_newPassword").val())
        }),
        async: true,
        success: function (result) {
            if (result.success === false) {
                Alert("", result.message, "error");
            } else {
                Alert('', '操作成功', 'success',function(){
                    $('#changePwdModal').modal('hide');
                })
            }
        }
    });
}