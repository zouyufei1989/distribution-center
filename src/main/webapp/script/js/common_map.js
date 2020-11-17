var icon_prefix = CONTENT_PATH + "resource/picture/";
var ICON_ARR = {
    "station": {
        icon: icon_prefix + 'mark_green.png',
        offset: new AMap.Pixel(-8, -20)
    },
    "station_yellow": {
        icon: icon_prefix + 'mark_yellow.png',
        offset: new AMap.Pixel(-8, -20)
    },
    "station_blue": {
        icon: icon_prefix + 'mark_blue.png',
        offset: new AMap.Pixel(-8, -20)
    },
    "startStation": {
        icon: icon_prefix + 'startStop2x.png',
        offset: new AMap.Pixel(-10, -24)
    },
    "endStation": {
        icon: icon_prefix + 'endStop2x.png',
        offset: new AMap.Pixel(-10, -24)
    },
    "vehicleOnline": {
        icon: icon_prefix + 'vehicle_online.png',
        offset: new AMap.Pixel(-30, -68)
    },
    "vehicleOffline": {
        icon: icon_prefix + 'vehicle_offline.png',
        offset: new AMap.Pixel(-30, -68)
    },

};

function createMapModel(mapElementId, cityName) {
    var map = new AMap.Map(mapElementId, {
        view: new AMap.View2D({
            zoom: 10,
            rotation: 0,
        }),
        lang: "zh_cn",
        resizeEnable: true,
        mapStyle: 'amap://styles/light'
    });
    var heatMap, clusterMap;

    if (CITY_CENTER && CITY_CENTER[0] && CITY_CENTER[1]) {
        map.setCenter(CITY_CENTER);
    } else if (cityName) {
        map.setCity(cityName);
    } else if (CITY_NAME) {
        map.setCity(CITY_NAME);
    }

    map.plugin(["AMap.ToolBar"], function () {
        toolBar = new AMap.ToolBar();
        map.addControl(toolBar);
    });

    function initHeatMap() {
        map.plugin(["AMap.Heatmap"], function () {
            //初始化heatmap对象
            heatMap = new AMap.Heatmap(map, {
                radius: 25,
                opacity: [0, 0.8]
            });
        });
        return heatMap;
    }

    // [{lng:1,lat:2}]
    function setHeatMapData(data) {
        var heatMapData = data.map(i => {
            return {
                lng: i.lng,
                lat: i.lat,
                count: 1
            };
        });
        heatMap.setDataSet({
            data: heatMapData,
            max: 20
        });
    }

    function initClusterMap() {
        map.plugin(["AMap.MarkerClusterer"], function () {
            clusterMap = new AMap.MarkerClusterer(map, []);
            clusterMap.setMinClusterSize(2);
        });
        return clusterMap;
    }

    function setClusterMapData(data) {
        var clusterMapData = data.map(i => {
            return clusterMapModel.api.addMarker(i.lng, i.lat, ICON_ARR["station"].icon, ICON_ARR["station"].offset, "", false);
        });
        clusterMap.clearMarkers();
        clusterMap.setMarkers(clusterMapData);
    }

    function addMarker(lng, lat, icon, offset, title, draggable, extData) {
        var marker = new AMap.Marker({
            icon: icon,
            position: [lng, lat],
            autoRotation: true,
            offset: offset || new AMap.Pixel(0, 0),
            title: title || '',
            draggable: draggable || false,
            extData: extData,
            autoRotation: false
        });
        marker.setMap(map);
        return marker;
    }

    function addMarkerWithClickInfo(lng, lat, icon, offset, windowInfo, windowOffSet) {
        var marker = addMarker(lng, lat, icon, offset);
        bindMarkerClick(marker, windowInfo, windowOffSet);
        return marker;
    }

    function addMarkerLabel(marker, labelContent) {
        marker.setLabel({
            offset: new AMap.Pixel(20, 20),
            content: labelContent
        });
    }

    function addLine(lineArr, color, strokeOpacity) {
        var polyline = new AMap.Polyline({
            map: map,
            path: lineArr,
            strokeOpacity: strokeOpacity || 1,
            strokeColor: color || "#00A",
            strokeWeight: 5,
        });
        return polyline;
    }

    function addCircle(radius, color, lng, lat) {
        var circle = new AMap.Circle({
            center: new AMap.LngLat(lng, lat),
            radius: radius,
            strokeColor: color || "#F33",  //线颜色
            strokeOpacity: 0,
            strokeWeight: 3,
            fillColor: "#ee2200",
            fillOpacity: 0.35,
            bubble: true
        });

        map.add(circle);
        return circle;
    }

    function addTrafficeLayer() {
        var traffic = new AMap.TileLayer.Traffic({
            'autoRefresh': true,     //是否自动刷新，默认为false
            'interval': 180,         //刷新间隔，默认180s
        });
        map.add(traffic);
    }

    function bindMarkerClick(marker, info, offset) {
        var eventListener = AMap.event.addListener(marker, 'click', function (e) {
            var infoWindow = new AMap.InfoWindow({
                offset: new AMap.Pixel(offset[0], offset[1]),
                content: info,
                closeWhenClickMap: true
            });
            infoWindow.open(map, marker.getPosition());
        });
        return eventListener;
    }

    function bindMarkerMouseover(marker, info, func) {
        AMap.event.addListener(marker, 'mouseover', function (e) {
            var infoWindow = new AMap.InfoWindow({
                content: info,
                closeWhenClickMap: true
            });
            infoWindow.open(map, marker.getPosition());

            if (func) {
                func(marker);
            }
        });

    }

    function bindLineClick(line, info, func) {
        AMap.event.addListener(line, 'click', function (e) {
            var infoWindow = new AMap.InfoWindow({
                content: info,
                closeWhenClickMap: true
            });
            infoWindow.open(map, e.lnglat);

            if (func) {
                func(line);
            }
        });

    }

    function bindMarkerDragend(marker, func) {
        marker.on('dragend', func);
    }

    function bindMapClick(func) {
        map.on('click', function (e) {
            func(e.lnglat.getLng(), e.lnglat.getLat());
        });
    }

    function setCenterAndZoom(lng, lat, zoom) {
        map.setZoomAndCenter(zoom, [lng, lat]);
    }

    function setCity(city) {
        map.setCity(city);
    }

    function initAutoCompleteControl(inputId, callBackFunc, city) {
        var citysearch = new AMap.CitySearch();
        // 自动获取用户IP，返回当前城市
        citysearch.getLocalCity(function (status, result) {
            if (status === 'complete' && result.info === 'OK') {
                if (result && result.city && result.bounds) {
                    city = city || result.city;
                    var auto = new AMap.Autocomplete({
                        input: inputId,
                        city: city
                    });
                    AMap.event.addListener(auto, "select", callBackFunc);
                }
            }
        });
    }

    function getMarkersOnMap() {
        return map.getAllOverlays('marker');
    }

    function remove(obj) {
        if (obj) {
            map.remove(obj)
        }
    }

    return {
        entity: {
            map: map
        },
        api: {
            addMarker: addMarker,
            addMarkerWithClickInfo: addMarkerWithClickInfo,
            getMarkersOnMap: getMarkersOnMap,
            remove: remove,
            addMarkerLabel: addMarkerLabel,
            addLine: addLine,
            addCircle: addCircle,
            bindMarkerClick: bindMarkerClick,
            bindMarkerMouseover: bindMarkerMouseover,
            bindLineClick: bindLineClick,
            setCenterAndZoom: setCenterAndZoom,
            setCity: setCity,
            addTrafficeLayer: addTrafficeLayer,
            initAutoCompleteControl: initAutoCompleteControl,
            initHeatMap: initHeatMap,
            setHeatMapData: setHeatMapData,
            initClusterMap: initClusterMap,
            setClusterMapData: setClusterMapData,
            clearAll: function () {
                map.clearMap();
            }
        },
        event: {
            onMapClick: bindMapClick,
            onMarkerDragend: bindMarkerDragend,
        }
    };
};