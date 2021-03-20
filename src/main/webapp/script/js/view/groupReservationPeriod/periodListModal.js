var periodListVue;

$(document).ready(function () {
    periodListVue = new Vue({
        el: '#periodListModal',
        data: {
            goodsName: '',
            periods: [],
            goodsId: null,
        },
        watch:{
            goodsId:function(newVal,oldVal){
                var _this = this;
                $.ajax({
                    url: 'list/search',
                    type: 'post',
                    data: {
                        goodsId: newVal,
                        rows: 0
                    },
                    async: true,
                    cache: false,
                    success: function (result) {
                        if (result.success === false) {
                            Alert("", result.message || "失败！", "error");
                            return;
                        }
                        _this.periods = result.rows;
                        $('#periodListModal').modal('show');
                    }
                });
            }
        }
    });
});

function showPeriodList(goodsId, goodsName) {
    periodListVue.goodsName = goodsName;
    periodListVue.goodsId = goodsId;
}