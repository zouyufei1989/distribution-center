var attrs = ['name', 'address', 'ownerName', 'ownerPhone', 'status', 'cityCode'];
var VUE_CITY, VUE_ANN;

$(document).ready(function () {
    new Vue({el: '#status'});
    VUE_CITY = new Vue({el: '#cityCode', data: {cityCode: null}});
});

function fillAdditionAttrs(result) {
    VUE_CITY.cityCode = result.data.cityCode;
}
