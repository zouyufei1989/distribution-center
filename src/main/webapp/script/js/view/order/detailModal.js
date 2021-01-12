var detailModal;
$(document).ready(function () {
    detailModal = new Vue({
        el: '#detailModal',
        data: {
            order: {
            },
            orderItems:[]
        },
        methods:{
            moneyFormatter(val){
                return moneyFormatter(val);
            }
        }
    });

});
