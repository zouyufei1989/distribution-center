function formatOptions(option) {
    formatColor(option);
    formatDataZoom(option);
    formatMaxBarWidth(option);
    formatGrid(option);
    formatXnamesRotate(option);

    function formatXnamesRotate(option) {
        if (option.xAxis[0].axisLabel) {
            option.xAxis[0].axisLabel.rotate = 45;
        } else {
            option.xAxis[0].axisLabel = {rotate: 45};
        }
    }

    function formatGrid(option) {
        option.grid = {top: 80, bottom: 100};
    }

    function formatDataZoom(option) {
        var max = 4;
        var xLength = 0;
        if (option.xAxis.data) {
            xLength = option.xAxis.data.length;
        } else if (option.xAxis.length > 0) {
            xLength = option.xAxis[0].data.length;
        }
        if (xLength <= max) {
            return;
        }

        option.dataZoom = [
            {
                show: true,
                realtime: true,
                start: 0,
                end: max / xLength * 100,
                throttle: 10
            },
        ];
    }

    function formatMaxBarWidth(option) {
        if (!(option && option.series && option.series.length > 0)) {
            return;
        }

        for (var i = 0; i < option.series.length; i++) {
            if (option.series[i].type === 'bar') {
                option.series[i].barMaxWidth = 40;
            }
        }
    }

    function formatColor(option) {
        option.color = ["#61a0a8", "#2f4554", "#c4ccd3", "#bda29a", "#ca8622", "#91c7ae", "#d48265", "#749f83", "#6e7074", "#546570", "#c23531",];
    }
}

function formatGridBottom(option,bottom) {
    option.grid.bottom = bottom;
}
