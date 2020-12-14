var TREE;

$(document).ready(function () {
    initTree();

    $("#paidMoney").TouchSpin({
        verticalbuttons: true,
        initval: 0,
        min: 0,
        max: 9999999,
        step: 0.01,
        decimals: 2,
        postfix: '¥',
        buttondown_class: 'btn btn-white',
        buttonup_class: 'btn btn-white'
    });

    $("#purchaseCnt").TouchSpin({
        verticalbuttons: true,
        initval: 0,
        min: 0,
        max: 9999999,
        step: 1,
        decimals: 0,
        buttondown_class: 'btn btn-white',
        buttonup_class: 'btn btn-white'
    });

    $("#useCnt").TouchSpin({
        verticalbuttons: true,
        initval: 0,
        min: 0,
        max: 9999999,
        step: 1,
        decimals: 0,
        buttondown_class: 'btn btn-white',
        buttonup_class: 'btn btn-white'
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
            oncheck: function (obj) {
                var srcObj = obj.data['data-src'];
                if (srcObj.type === 'goods') {
                    if (obj.checked) {
                        initNodeBtnGroup(srcObj.id);
                    } else {
                        removeNodeBtnGroup(srcObj.id);
                    }
                    buildTips();
                }
            }
        });
    });
}

function buildTips() {
    var cnt = TREE.getChecked('id').length;
    if (cnt === 0) {
        $('#consumeTip').html("当前已选择0项消费内容，应收0元");
        return;
    }

    cnt = TREE.getChecked('id').map(i => i.children).reduce((i, j) => {
        return i.concat(j)
    }).length;
    var sumPrice = TREE.getChecked('id').map(i => i.children).reduce((i, j) => {
        return i.concat(j)
    }).map(i => {
        return i['data-src'].price * $('#txt_cnt_' + i.id).val();
    }).reduce((i, j) => {
        return i + j
    });
    $('#consumeTip').html("当前已选择" + cnt + "项消费内容，应收" + (sumPrice / 100).toFixed(2) + "元");
    $('#paidMoney').val((sumPrice / 100).toFixed(2));
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
                buildTips();
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

function initNodeBtnGroup(id) {
    var btnGroup = $('<div class="input-group input-group-sm" style="float: right;width: 100px"></div>');
    var btnLeft = $('<span class="input-group-btn"> <button class="btn btn-default" style="margin-right: 5px" type="button" onclick="changeCnt(' + id + ',' + -1 + ')">-</button></span>');
    var btnRight = $('<span class="input-group-btn"> <button class="btn btn-default" style="margin-left: 5px" type="button" onclick="changeCnt(' + id + ',' + 1 + ')">+</button></span>');
    var inp = $('<input type="text" onchange="buildTips()" style="width:50px" class="form-control" digits min="0" value="1">');
    inp.attr('id', 'txt_cnt_' + id);

    btnGroup.append(btnLeft);
    btnGroup.append(inp);
    btnGroup.append(btnRight);
    btnGroup.attr('id', 'btn_grp_' + id);

    $('#lbl_node_' + id).parent().parent().parent().append(btnGroup);
}

function removeNodeBtnGroup(id) {
    $('#btn_grp_' + id).remove();
}

function changeCnt(id, step) {
    var src = Number.parseInt($('#txt_cnt_' + id).val());
    $('#txt_cnt_' + id).val(src + step);
    $('#txt_cnt_' + id).trigger('change');
}

function goConsume(rowId) {
    var customer = $("#table_list").jqGrid("getRowData", rowId);
    customerVue.id = customer['customerGroup.id'];
    reloadGoodsTree();
    $('#single').trigger('click');
    $('#consumeModal').modal('show');
}