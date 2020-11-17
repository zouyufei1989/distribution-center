$(document).ready(function () {

    initGridData("list/search", null,
        [
            {name: 'name', header: '企业名称'},
            {name: 'cityName', header: '城市'},
            {name: 'address', header: '企业地址'},
            {name: 'phone', header: '联系电话'},
            {name: 'ownerName', header: '公司负责人'},
            {name: 'ownerPhone', header: '负责人电话'},
            {name: 'statusName', header: '状态'},
            {name: 'createDate', header: '创建日期', formatter: dateFormatter},
            {name: 'status', header: 'status', hidden: true},
            {name: 'id', header: "id", hidden: true},
        ]);

    $('#btn_announcement').click(function () {
        if (_ROWS_CHOOSED.length != 1) {
            Alert("", "请选择一个公司", "error");
            return;
        }

        $('#groupId').val(_ROWS_CHOOSED[0].id);
        $('#announcementModal').modal('show');
    });

    $('#btn_uploadAnnouncement').click(function () {
        loadingStart(function () {
            uploadFile('uploadAnnouncement', 'announcementForm', function (result) {
                $('#uploadFile').val('');
                $("#announcementModal").modal("hide");
                reloadList();
            }, function (result) {
                Alert("", result.message, "error");
            });
        }, 1);
    });
});