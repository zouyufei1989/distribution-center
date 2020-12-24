var TREE;

$(document).ready(function () {
    initTree();

    bindModalShow('packageGoodsModal', function () {
        reloadGoodsTree();
    }, 1);

    $('#btn_package_goods').click(function () {
        if (_ROWS_CHOOSED.length != 1) {
            Alert('', '请选择一条编辑数据', 'error');
            return;
        }
        if (_ROWS_CHOOSED[0].goodsItemTypeCnt != 0) {
            Alert('', '套餐已设置商品', 'error');
            return;
        }
        $('#packageGoodsModal').modal('show');
    });
});

function initTree() {
    layui.use('tree', function () {
        TREE = layui.tree;
        //渲染
        TREE.render({
            elem: '#goodsTree',
            id: 'id',
            accordion: true,
            showCheckbox: true,
            onlyIconControl: true,
            data: [],
        });
    });
}

function reloadGoodsTree() {
    $.ajax({
        url: '../goods/list/search',
        type: 'post',
        data: {
            rows: 0,
            'goods.type': 1
        },
        async: true,
        cache: false,
        success: function (response) {
            if (response.success == true) {
                var goods = response.rows.map(i => {
                    return {
                        name: i.name + "(" + i.price4SingleShow + "元/" + i.unit4SingleShow + ")",
                        price: i.items[0].price,
                        id: i.items[0].id,
                        type: 'goods',
                        typeName: i.goodsTagName4SingleShow
                    };
                });
                var goodsMap = groupBy(goods, 'typeName');
                var treeData = [];

                $.each(goodsMap, function (key, val) {
                    treeData.push({
                        id: new GUID().newGUID(),
                        name: key,
                        type: 'type',
                        children: val
                    });
                });
                TREE.reload('id', {
                    data: parseTreeNode(treeData),
                });
            } else {
                Alter('', response.message, 'error');
            }
        }
    });
}

function parseTreeNode(data) {
    return data.map(i => {
        var node = {
            id: i.id,
            title: initNodeTitle(i),
            spread: true,
            disabled: i.type === 'type',
            'data-src': i
        };
        if (i.children) {
            node.children = parseTreeNode(i.children);
        }
        return node;
    });

    function initNodeTitle(goodsItem) {
        var labelTitle = $('<label class="pull-left" style="float: left;"></label>');
        labelTitle.attr('id', 'lbl_node_' + goodsItem.id);
        labelTitle.html(goodsItem.name);
        return labelTitle.prop("outerHTML");
    }
}

function cancelEditPackage() {
    Confirm('确定取编辑套餐么', function () {
        $('#packageGoodsModal').modal('hide');
    });
}

function savePackage(e) {
    if (TREE.getChecked('id').length == 0) {
        Alert('', '未选中任何商品', 'error');
        return;
    }
    var ele = $(e);
    Confirm("确定分配选中商品?", function () {
        loadingStart(ele, function () {
            $.ajax({
                url: 'assignGoods4Package',
                type: 'post',
                data: JSON.stringify({
                    goodsId: _ROWS_CHOOSED[0].id,
                    goodsItemIdList: TREE.getChecked('id')
                        .map(i => i.children)
                        .reduce((i, j) => {
                            return i.concat(j)
                        }).map(i => i.id)
                }),
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json;charset=UTF-8'
                },
                async: true,
                cache: false,
                success: function (result) {
                    if (result.success == false) {
                        loadingEnd(function () {
                            Alert("", result.message || "失败！", "error");
                        });
                        return;
                    }

                    loadingEnd(function () {
                        Alert("", "成功！", "success", function () {
                            reloadList();
                            $('#packageGoodsModal').modal('hide');
                        });
                    });
                }
            });
        });
    })
}

