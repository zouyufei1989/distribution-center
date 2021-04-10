$(document).ready(function () {
    initTree();
});


function initTree() {
    layui.use('tree', function () {
        TREE = layui.tree;
        //渲染
        TREE.render({
            elem: '#relationTree',
            id: 'id',
            accordion: true,
            showCheckbox: false,
            onlyIconControl: true,
            data: [],
        });
    });
}

function reloadRelationTree(employeeId) {
    $.ajax({
        url: 'buildEmployeeRelationships',
        type: 'post',
        data: {
            employeeId: employeeId,
        },
        async: true,
        cache: false,
        success: function (response) {
            if (response.success == true) {
                response.data.spread=true;
                TREE.reload('id', {
                    data: [response.data]
                });
            } else {
                Alter('', response.message, 'error');
            }
        }
    });
}