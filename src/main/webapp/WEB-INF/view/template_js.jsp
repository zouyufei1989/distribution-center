<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script src="${pageContext.request.contextPath}/script/js/js_version.js?v=<%=Math.random()%>"></script>
<script type="text/javascript">

    function loadJS(url, refresh) {
        if (refresh) {
            document.write('<script src="' + url + '?v=' + JS_VERSION + '"><\/script>')
        } else {
            document.write('<script src="' + url + '"><\/script>')
        }
    }

    loadJS('${pageContext.request.contextPath}/script/plugins/inspinia/js/jquery-2.1.1.js');
    loadJS("${pageContext.request.contextPath}/script/plugins/inspinia/js/bootstrap.js");
    <!-- Custom and plugin javascript -->
    loadJS("${pageContext.request.contextPath}/script/plugins/inspinia/js/inspinia.js");
    loadJS("${pageContext.request.contextPath}/script/plugins/inspinia/js/plugins/metisMenu/jquery.metisMenu.js");
    loadJS("${pageContext.request.contextPath}/script/plugins/inspinia/js/plugins/slimscroll/jquery.slimscroll.min.js");
    <!--data mask-->
    loadJS("${pageContext.request.contextPath}/script/plugins/inspinia/js/plugins/jasny/jasny-bootstrap.min.js");
    <!-- Data picker -->
    loadJS("${pageContext.request.contextPath}/script/plugins/inspinia/js/plugins/datapicker/bootstrap-datepicker.js");
    <!-- Switchery -->
    loadJS("${pageContext.request.contextPath}/script/plugins/inspinia/js/plugins/switchery/switchery.js");
    <!-- Select2 -->
    loadJS("${pageContext.request.contextPath}/script/plugins/inspinia/js/plugins/select2/select2.full.min.js");

    <!-- jqGrid -->
    loadJS("${pageContext.request.contextPath}/script/plugins/inspinia/js/plugins/jqGrid/i18n/grid.locale-cn.js");
    loadJS("${pageContext.request.contextPath}/script/plugins/inspinia/js/plugins/jqGrid/jquery.jqGrid.min111.js");
    <!-- Sweet alert -->
    loadJS("${pageContext.request.contextPath}/script/plugins/inspinia/js/plugins/sweetalert/sweetalert.min.js");

    <!-- Jquery Validate -->
    loadJS("${pageContext.request.contextPath}/script/plugins/inspinia/js/plugins/validate/jquery.validate.min.js");
    loadJS("${pageContext.request.contextPath}/script/plugins/inspinia/js/plugins/validate/messages_cn.js");

    <!-- iCheck -->
    loadJS("${pageContext.request.contextPath}/script/plugins/inspinia/js/plugins/iCheck/icheck.min.js");

    loadJS("${pageContext.request.contextPath}/script/plugins/echarts-4.3.0/echarts.min.js");
    loadJS("${pageContext.request.contextPath}/script/js/common_gps_converter.js", 1);
    loadJS("${pageContext.request.contextPath}/script/js/common_echarts.js", 1);

    <!-- vue -->
    loadJS("${pageContext.request.contextPath}/script/plugins/vue/vue.js");
    <!-- vue resource -->
    loadJS("${pageContext.request.contextPath}/script/plugins/vue/vue-source.js");

    loadJS("${pageContext.request.contextPath}/script/plugins/datetimepicker/js/bootstrap-datetimepicker.js");
    loadJS("${pageContext.request.contextPath}/script/plugins/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js");

    <!--ajax ????????????-->
    loadJS("${pageContext.request.contextPath}/script/js/jquery.form.js");

    <!--??????????????????-->
    loadJS("${pageContext.request.contextPath}/script/plugins/vue/vue-treeselect.min.js");
    <!--toastr-->
    loadJS("${pageContext.request.contextPath}/script/plugins/inspinia/js/plugins/toastr/toastr.min.js");
    <!--????????????-->
    loadJS("${pageContext.request.contextPath}/script/plugins/dropzone/dropzone.js");
    <!--??????countup-->
    loadJS("${pageContext.request.contextPath}/script/plugins/countUp/countUp.js");
    <!--touchspin ???????????????-->
    loadJS("${pageContext.request.contextPath}/script/plugins/inspinia/js/plugins/touchspin/jquery.bootstrap-touchspin.min.js");
    <!--???????????????-->
    loadJS("${pageContext.request.contextPath}/script/plugins/duallistbox/jquery.bootstrap-duallistbox.js");

    loadJS("${pageContext.request.contextPath}/script/plugins/layui/layui.js");


    loadJS("${pageContext.request.contextPath}/script/plugins/inspinia/js/plugins/ladda/spin.min.js");
    loadJS("${pageContext.request.contextPath}/script/plugins/inspinia/js/plugins/ladda/ladda.min.js");
    loadJS("${pageContext.request.contextPath}/script/plugins/inspinia/js/plugins/ladda/ladda.jquery.min.js");


    loadJS("${pageContext.request.contextPath}/script/js/common_cookies.js");
    loadJS("${pageContext.request.contextPath}/script/js/main.js", 1);
</script>
<script src="https://webapi.amap.com/maps?v=1.3&key=fadee97bdcbf2dde564994f5deef0b58&plugin=AMap.Autocomplete,AMap.Walking,AMap.PlaceSearch,AMap.PolyEditor,AMap.CircleEditor,AMap.Transfer,AMap.Driving,AMap.CitySearch,AMap.Heatmap,AMap.Size"></script>
<script src="https://webapi.amap.com/ui/1.0/main.js?v=1.0.11"></script>


<%@ include file="template_pwd.jsp" %>

<script type="text/javascript">
    var CONTENT_PATH = '${pageContext.request.contextPath}/';
    var CITY_CODE = '${sessionScope.user.cityCode}';
    var LOGIN_USER = '${sessionScope.user.username}';
    var CITY_NAME = '${sessionScope.user.cityName}';
    var CITY_CENTER = [121.473658, 31.230378];
    var JS_FUNC_LIST = ${sessionScope.funcList}; //????????????????????????
    var JS_ROLE_FUNC_IDS = ${sessionScope.RoleFuncIds}; // ??????????????????????????????????????????
    var JS_PAGE_NAME; // ????????????URL - ?????????????????????href
    var JS_FROM_URL; //  ??????from
    var JS_PAGE_PARAMS; // ????????????

    //????????????session ????????????
    var TIMEOUT_INTERVAL;

    JS_PAGE_NAME = window.location.pathname;
    JS_PAGE_NAME = JS_PAGE_NAME.substring(JS_PAGE_NAME.indexOf("/") + 1);
    JS_PAGE_NAME = JS_PAGE_NAME.substring(JS_PAGE_NAME.indexOf("/"));
    if (JS_PAGE_NAME == '/getLogin') {
        JS_PAGE_NAME = '/main';
    }
    JS_PAGE_NAME = JS_PAGE_NAME.replace("#", "");

    JS_PAGE_PARAMS = $.urlGet();
    JS_FROM_URL = JS_PAGE_PARAMS['from'];
    JS_FROM_URL = JS_FROM_URL && JS_FROM_URL.substring(JS_FROM_URL.indexOf("/"));
    JS_FROM_URL = JS_FROM_URL && JS_FROM_URL.replace("#", "");

    function sortArr(array, comparableFunc) {
        for (var i = 0; i < array.length - 1; i++) {
            for (var j = 0; j < array.length - i - 1; j++) {
                if (comparableFunc(array[j], array[j + 1])) {
                    var tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
        return array;
    }

    //???Date??????????????? Date ????????????????????????String
    //???(M)??????(d)?????????(h)??????(m)??????(s)?????????(q) ????????? 1-2 ???????????????
    //???(y)????????? 1-4 ?????????????????????(S)????????? 1 ????????????(??? 1-3 ????????????)
    //?????????
    //(new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
    //(new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
    Date.prototype.Format = function (fmt) { //author: meizz
        var o = {
            "M+": this.getMonth() + 1,                 //??????
            "d+": this.getDate(),                    //???
            "h+": this.getHours(),                   //??????
            "m+": this.getMinutes(),                 //???
            "s+": this.getSeconds(),                 //???
            "q+": Math.floor((this.getMonth() + 3) / 3), //??????
            "S": this.getMilliseconds()             //??????
        };
        if (/(y+)/.test(fmt))
            fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt))
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    }

    function priceFormatter(val) {
        return (val || 0) / 100;
    }

    function getDateRange(type) {
        if (type === "today") {
            var today = new Date().Format('yyyy-MM-dd');
            return {startDate: today, endDate: today};
        }

        var range = [];
        var url = "convertWeekByDate";
        if (type === "month") {
            url = "convertMonthByDate";
        }
        if (type === "year") {
            url = "convertYearByDate";
        }

        $.ajax({
            url: '${ctx}/utils/' + url + '',
            data: {},
            type: 'post',
            async: false,
            success: function (result) {
                range = result;
            }
        });

        return range;
    }


    function bindModalShow(modalId, funcShown, keep) {
        if (funcShown) {
            $('#' + modalId).on('shown.bs.modal', function (e) {
                funcShown();
                if (!keep) {
                    $('#' + modalId).off('shown.bs.modal');//?????????XXX??????????????????????????????????????????????????????XXX
                }
            })
        }
    }

    function bindModalHide(modalId, funcHidden, keep) {
        if (funcHidden) {
            $('#' + modalId).on('hidden.bs.modal', function (e) {
                funcHidden();
                if (!keep) {
                    $('#' + modalId).off('hidden.bs.modal');//?????????XXX??????????????????????????????????????????????????????XXX
                }
            })
        }
    }

    function GUID() {
        this.date = new Date();
        /* ????????????????????????????????????????????????????????????????????????????????????????????????????????????????????? */
        if (typeof this.newGUID != 'function') { /* ??????GUID??? */
            GUID.prototype.newGUID = function () {
                this.date = new Date();
                var guidStr = '';
                sexadecimalDate = this.hexadecimal(this.getGUIDDate(), 16);
                sexadecimalTime = this.hexadecimal(this.getGUIDTime(), 16);
                for (var i = 0; i < 9; i++) {
                    guidStr += Math.floor(Math.random() * 16).toString(16);
                }
                guidStr += sexadecimalDate;
                guidStr += sexadecimalTime;
                while (guidStr.length < 32) {
                    guidStr += Math.floor(Math.random() * 16).toString(16);
                }
                return this.formatGUID(guidStr);
            }
            /* * ??????????????????????????????GUID????????????8??????????????????19700101 * ??????????????????GUID???????????????????????? */
            GUID.prototype.getGUIDDate = function () {
                return this.date.getFullYear() + this.addZero(this.date.getMonth() + 1) + this.addZero(this.date.getDay());
            }
            /* * ??????????????????????????????GUID????????????8??????????????????????????????????????????2?????????12300933 * ??????????????????GUID???????????????????????? */
            GUID.prototype.getGUIDTime = function () {
                return this.addZero(this.date.getHours()) + this.addZero(this.date.getMinutes()) + this.addZero(this.date.getSeconds()) + this.addZero(parseInt(this.date.getMilliseconds() / 10));
            }
            /* * ??????: ????????????????????????????????????0???????????????????????????NaN????????????????????????????????? * ??????: ?????????????????????????????????0????????????????????????????????????????????? * ?????????: ?????????????????????????????????0?????????????????????????????????????????????????????? */
            GUID.prototype.addZero = function (num) {
                if (Number(num).toString() != 'NaN' && num >= 0 && num < 10) {
                    return '0' + Math.floor(num);
                } else {
                    return num.toString();
                }
            }
            /*  * ????????????y???????????????????????????x??????????????? * ????????????1???????????????????????????????????????2???????????????????????????????????????3????????????????????????????????????????????????????????????10 * ??????????????????????????????????????? */
            GUID.prototype.hexadecimal = function (num, x, y) {
                if (y != undefined) {
                    return parseInt(num.toString(), y).toString(x);
                } else {
                    return parseInt(num.toString()).toString(x);
                }
            }
            /* * ??????????????????32??????????????????GUID?????????????????? * ????????????1???????????????32??????????????? * ??????????????????GUID?????????????????? */
            GUID.prototype.formatGUID = function (guidStr) {
                var str1 = guidStr.slice(0, 8) + '-', str2 = guidStr.slice(8, 12) + '-', str3 = guidStr.slice(12, 16) + '-', str4 = guidStr.slice(16, 20) + '-', str5 = guidStr.slice(20);
                return str1 + str2 + str3 + str4 + str5;
            }
        }
    }

    function goBack() {
        //??????Safari??????????????????????????????
        if (navigator.userAgent && /(iPhone|iPad|iPod|Safari)/i.test(navigator.userAgent)) {
            window.location.href = window.document.referrer;
        } else {
            window.history.back(-1); //?????????????????????history.go(-1);
        }
    }

    function getStartAndEndDate(now, dateType) {
        var startDate, endDate;
        var nowDayOfWeek = now.getDay() == 0 ? 7 : now.getDay();
        var nowDay = now.getDate();
        var nowMonth = now.getMonth();
        var nowYear = now.getYear();
        nowYear += (nowYear < 2000) ? 1900 : 0;

        if (dateType == '???') {
            startDate = new Date(nowYear, nowMonth, nowDay - nowDayOfWeek + 1).Format("yyyy-MM-dd");
            endDate = new Date(nowYear, nowMonth, nowDay + (7 - nowDayOfWeek)).Format("yyyy-MM-dd");
        } else if (dateType == '???') {
            startDate = new Date(nowYear, nowMonth, 1).Format("yyyy-MM-dd");
            endDate = new Date(nowYear, nowMonth, getMonthDays(nowYear, nowMonth)).Format("yyyy-MM-dd");
        } else {
            startDate = new Date(nowYear, 0, 1).Format("yyyy-MM-dd");
            endDate = new Date(nowYear, 11, 31).Format("yyyy-MM-dd");
        }
        return [startDate, endDate];

        function getMonthDays(nowYear, myMonth) {
            var monthStartDate = new Date(nowYear, myMonth, 1);
            var monthEndDate = new Date(nowYear, myMonth + 1, 1);
            var days = (monthEndDate - monthStartDate) / (1000 * 60 * 60 * 24);
            return days;
        }
    }

    //??????switchery ????????????
    function setSwitchery(switchElement, checkedBool) {
        if ((checkedBool && !switchElement.isChecked()) || (!checkedBool && switchElement.isChecked())) {
            switchElement.setPosition(true);
            switchElement.handleOnchange(true);
        }
    }

    function groupBy(list, name) {
        return list.reduce((obj, item) => {
            var key = item[name];
            obj[key] = obj[key] || [];
            obj[key].push(item);
            return obj
        }, {});
    }

    function isTimeOut() {
        $.ajax({
            url: CONTENT_PATH + 'isTimeout',
            type: 'get',
            async: true,
            success: function (result) {
                if (result === true) {
                    clearInterval(TIMEOUT_INTERVAL);
                    Alert('', '?????????????????????????????????', 'error', function () {
                        window.location.href = CONTENT_PATH + "logout";
                    });
                }
            }
        });
    }

    function initXSSFilter() {
        var inputs = $('input.form-control[type!="password"][type!="file"][ignorexss!="1"]');
        for (var i = 0; i < inputs.length; i++) {
            var item = inputs[i];
            item.onblur = function () {
                $(this).val(xssFilter($(this).val()));
            };
        }

        var textareas = $('textarea.form-control');
        for (var i = 0; i < textareas.length; i++) {
            var area = textareas[i];
            area.onblur = function () {
                $(this).val(xssFilter($(this).val()));
            };
        }

        var xssFilters = [];
        xssFilters.push(new RegExp("\\("));
        xssFilters.push(new RegExp("\\)"));
        xssFilters.push(new RegExp(","));
        xssFilters.push(new RegExp("\\\\"));
        xssFilters.push(new RegExp("script"));
        xssFilters.push(new RegExp("svg"));
        xssFilters.push(new RegExp("alert"));
        xssFilters.push(new RegExp("confirm"));
        xssFilters.push(new RegExp("prompt"));
        xssFilters.push(new RegExp("onload"));
        xssFilters.push(new RegExp("onmouseover"));
        xssFilters.push(new RegExp("onfocus"));
        xssFilters.push(new RegExp("onerror"));
        xssFilters.push(new RegExp("xss"));

        function xssFilter(val) {
            xssFilters.forEach(function (xssReg) {
                if (xssReg.test(val)) {
                    Alert('', '????????????????????????????????????', 'warning');
                    val = val.replace(new RegExp(xssReg.source, 'g'), '*');
                }
            })
            return val;
        }
    }

    $.validator.addMethod("strictPwd", function (value, element, params) {
        if (!value || value.length < 8) {
            return false;
        }

        var pwdRegex = new RegExp('(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[^a-zA-Z0-9]).{8,30}');
        return pwdRegex.test(value);
    }, "?????????????????????????????????????????????????????????????????????8???");

    function timeFormatter(val, format) {
        if (!val) {
            return '';
        }
        return new Date(val).Format(format);
    }

    function yyyyMMddhhmmssFormatter(val) {
        return timeFormatter(val, 'yyyy-MM-dd') + "<br>" + timeFormatter(val, 'hh:mm:ss');
    }

    function yyyyMMddhhmmFormatter(val) {
        return timeFormatter(val, 'yyyy-MM-dd') + "<br>" + timeFormatter(val, 'hh:mm');
    }

    function yyyyMMddFormatter(val) {
        return timeFormatter(val, 'yyyy-MM-dd');
    }

    function hhmmssFormatter(val) {
        return timeFormatter(val, 'hh:mm:ss');
    }

    function moneyFormatter(val) {
        if (typeof val === "undefined") {
            return "";
        }
        if (val === 0) {
            return 0;
        }
        if (!val) {
            return ''
        }
        if (val < 0) {
            return 0;
        }
        return (Number.parseFloat(val) / 100).toFixed(2);
    }

    function rateFormatter(val) {
        if (typeof val === "undefined") {
            return "";
        }
        if (val === 0) {
            return 0;
        }
        if (!val) {
            return ''
        }
        return (Number.parseFloat(val) / 100).toFixed(2) + "%";
    }

    function uploadFile(uploadUrl, formId, successFunc, failFunc) {
        if (!$('#' + formId).find('input:file').val()) {
            Alert("", "???????????????????????????", "error", function () {
                if (loadingEnd && typeof loadingEnd === 'function') {
                    loadingEnd();
                }
            });
            return;
        }
        var option = {
            url: uploadUrl,
            type: "POST",
            async: true,
            success: function (result) {
                if (loadingEnd && typeof loadingEnd === 'function') {
                    loadingEnd();
                }

                if (result.success === false) {
                    if (failFunc && typeof failFunc === 'function') {
                        failFunc(result);
                    }
                    return;
                }
                Alert('', '????????????', 'success', function () {
                    if (successFunc && typeof successFunc === "function") {
                        successFunc(result);
                    }
                });
            }
        }
        $("#" + formId).ajaxSubmit(option);
    }

    function editOnModal() {
        return $("#updateModal").length > 0;
    }

    function getEleId4JQ(docId) {
        return '#' + docId.replace(new RegExp('\\.', 'g'), '\\.');
    }

    function getJqObj(id) {
        return $(('#' + id).replace(new RegExp('\\.', 'g'), '\\.'));
    }

    $(document).ready(function () {

        // trigger reloadlist when press enter
        var inputs = $('[search-param]');
        for (var i = 0; i < inputs.length; i++) {
            var item = inputs[i];
            item.onkeydown = function () {
                if (event.keyCode == 13 && typeof reloadList === "function") {
                    reloadList();
                    return true;
                }
            };
        }

        // prevent refreshing page when press entry
        $('form').on('submit', function () {
            return false;
        });

        TIMEOUT_INTERVAL = setInterval(isTimeOut, 1000 * 60);
        initXSSFilter();
        $('input').attr('autocomplete', 'off');

        // $.ajaxSetup({
        //     headers: {"X-XSRF-TOKEN": getCookie('XSRF-TOKEN')}
        // });

        $('.modal-body h3').after('<hr/>');
        $('.modal-body h5').after('<hr/>');

        toastr.options = {
            "closeButton": true,
            "debug": false,
            "progressBar": false,
            "preventDuplicates": false,
            "positionClass": "toast-top-right",
            "onclick": null,
            "showDuration": "400",
            "hideDuration": "500",
            "timeOut": "2000",
            "extendedTimeOut": "1000",
            "showEasing": "swing",
            "hideEasing": "linear",
            "showMethod": "fadeIn",
            "hideMethod": "fadeOut"
        }
    });

    loadJS("${pageContext.request.contextPath}/script/js/common_map.js", 1)
</script>
