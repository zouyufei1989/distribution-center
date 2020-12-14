var goodsPackage;
var goodsListVue;

$(document).ready(function () {

    goodsListVue = new Vue({
        el: '#packages',
        data: {
            goods: []
        },
        mounted: function () {
            goodsPackage = $('#packages').bootstrapDualListbox({
                nonSelectedListLabel: '可用商品',
                selectedListLabel: '已选商品',
                moveOnSelect: false,
                infoText: false,
                infoTextEmpty: '',
                filterPlaceHolder: '请输入商品名称'
            });
        },
        watch: {
            goods: {
                handler(newValue, oldValue) {
                    this.$nextTick(function () {
                        if (goodsPackage) {
                            goodsPackage.bootstrapDualListbox('refresh', true);
                        }
                    })
                },
                immediate: true,
                deep: true
            }
        }
    });

    bindModalShow('packageGoodsModal', function () {
        fillGoodsList4Package();
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

function fillGoodsList4Package() {
    $.ajax({
        url: '../goods/list/search',
        type: 'post',
        data: {
            rows: 0,
            page: 1,
            'goods.type': 1
        },
        async: true,
        cache: false,
        success: function (result) {
            if (result.success === false) {
                Alert('', result.message, 'error');
                return;
            }
            goodsListVue.goods = result.rows;
        }
    });
}

function cancelEditPackage() {
    Confirm('确定取编辑套餐么', function () {
        $('#packageGoodsModal').modal('hide');
    });
}

function savePackage(e) {
    if ($('#packages').val().length == 0) {
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
                    goodsItemIdList: $('#packages').val()
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