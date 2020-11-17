var ROUTE_OPTION = [
    {name: 'name', header: '班线名称', formatter: formatChangedProperty},
    {name: 'type', header: '循环', width: 50, formatter: formatChangedProperty},
    {name: 'isComment', header: '评价', width: 50, formatter: formatChangedProperty},
    {name: 'isEnroll', header: '申请', width: 50, formatter: formatChangedProperty},
    {name: 'isVerify', header: '审核', width: 50, formatter: formatChangedProperty},
    {name: 'weekend', header: '运营日', formatter: formatChangedProperty},
    {name: 'onlineDate', header: '生效时间', formatter: formatChangedProperty},
    {name: 'statusName', header: '状态', formatter: formatChangedProperty},
    {name: 'routeTagName', header: '标签', formatter: formatChangedProperty},
    {
        name: 'vehicleBinding4Short', header: '绑定车辆', width: 400, formatter: function (obj) {
            var val = obj.value;
            if (!val) {
                return '';
            }
            var div = $("<div></div>");
            $.each(val, function (index, item) {
                var span = $("<span></span>");
                span.html(item);
                div.append(span);
                div.append($("<br>"));
            });
            if (obj.changed == 1) {
                div.addClass("text-danger");
            }
            return div.prop("outerHTML");
        }
    },
    {
        name: 'stops', header: '站点', width: 300, formatter: function (obj) {
            var val = obj.value;
            if (!val) {
                return '';
            }
            var div = $("<div></div>");
            $.each(val, function (index, item) {
                if (item.status == 1) {
                    var span = $("<span>" + item.sequence + ") " + item.arriveTime + " " + item.name + "</span>");
                    div.append(span);
                    div.append("<br>");
                }
            });
            if (obj.changed == 1) {
                div.addClass("text-danger");
            }
            return div.prop("outerHTML");
        }
    },
    {name: 'updater', header: '修改人', formatter: formatChangedProperty},
    {name: 'createDate', header: '修改时间', formatter: formatChangedProperty}
];