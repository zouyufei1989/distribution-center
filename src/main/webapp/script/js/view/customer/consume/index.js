var consumeVue;
$(document).ready(function () {
    consumeVue = new Vue({
        el:'#div_actions',
        data:{
            action : 'buySingle',
        },
        methods:{
            chooseAction(action){
                this.action = action;
            }
        }
    });
});
