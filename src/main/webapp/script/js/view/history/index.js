$(document).ready(function () {
    $("body").toggleClass("mini-navbar");
    queryHistory();
});

function queryHistory() {
    var type = JS_PAGE_PARAMS['type'];
    var option = null;
    if (type == "DEVICE") {
        option = DEVICE_OPTION;
    } else if (type == "VEHICLE") {
        option = VEHICLE_OPTION;
    } else if (type == "DRIVER") {
        option = DRIVER_OPTION;
    } else if (type == "ROUTE") {
        option = ROUTE_OPTION;
    }

    $.ajax({
        url: 'list/search',
        type: 'post',
        data: {
            type: type,
            key: JS_PAGE_PARAMS['key']
        },
        async: true,
        cache: false,
        success: function (result) {
            var histories = buildHistoryRows(result.rows, option);
            initGridLocalData(histories, option, null, null, null, 100);
        }
    });
}

function buildHistoryRows(rows, option) {
    var result = [];
    rows = parseJSONHistory(rows);

    for (var i = 0; i < rows.length; i++) {
        var history = {};
        $.each(option, function (index, item) {
            var val = rows[i][item.name];
            if (i == 0 || JSON.stringify(rows[i][item.name]) == JSON.stringify(rows[i - 1][item.name])) {
                history[item.name] = {value: val};
            } else {
                history[item.name] = {value: val, changed: 1};
            }
        });
        result.push(history)
    }
    return result;

    function parseJSONHistory(rows) {
        var result = [];
        for (var i = 0; i < rows.length; i++) {
            var history = JSON.parse(rows[i].detail);
            history.updater = rows[i].updater;
            history.createDate = yyyyMMddhhmmssFormatter(rows[i].createDate);
            result.push(history)
        }
        return result;
    }
}

function formatChangedProperty(val) {
    if (val.changed) {
        return colorfulTextFormatter(val.value, 'text-danger');
    }
    return val.value;
}