<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="jqGrid_wrapper" style="margin-top: 10px;" id="div_list">
    <table id="table_list"></table>
    <div id="pager_list"></div>
</div>

<div class="modal inmodal fade" id="gridImgPreview" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="wrapper animated fadeInRight" style="height:300px">
                    <div id="preview" style="height: 300px;background-size: 100%; background: no-repeat center;z-index: 5000"></div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">

    var _DEFAULT_LINK_COLOR = '#23c6c8';
    var _ROWS_CHOOSED = [];

    var gridLoadComplete = null;

    function defaultColWidth(item) {
        if (item.width) {
            return item.width;
        }

        return 100;
    }


    function initGridLocalData(data, columns, gridId, pagerId, cannotExpand, rowNum, multiselect) {
        gridId = "#" + (gridId || "table_list");
        pagerId = "#" + (pagerId || "pager_list");

        $(gridId).jqGrid({
            data: data,
            datatype: "local",
            height: "100%",
            autowidth: true,
            shrinkToFit: true,
            rowNum: rowNum || 20,
            rownumbers: true,
            multiselect: (typeof (multiselect) == "undefined" || multiselect == null) ? true : multiselect,
            rownumWidth: 50,
            cellLayout:50,
            rowList: [20, 50, 100],
            colNames: columns.map(function (item) {
                return item.header;
            }),
            colModel: columns.map(function (item) {
                return {
                    name: item.name,
                    formatter: item.formatter || function (val) {
                        return val || '';
                    },
                    width: defaultColWidth(item),
                    hidden: item.hidden || false
                }
            }),
            onSelectAll: function (aRowids, status) {
                var gridObj = $("#" + this.id);
                _ROWS_CHOOSED = [];
                if (status) {
                    $.each(aRowids, function (index, id) {
                        _ROWS_CHOOSED.push(gridObj.jqGrid("getRowData", id));
                    });
                }
            },
            onSelectRow: function (row_id, status) {
                var rowSelect = $("#" + this.id).jqGrid("getRowData", row_id);
                if (status) {
                    //选中
                    _ROWS_CHOOSED.push(rowSelect);
                } else {
                    //取消选中
                    for (var i = 0; i < _ROWS_CHOOSED.length; i++) {
                        if (_ROWS_CHOOSED[i].id === rowSelect.id) {
                            _ROWS_CHOOSED.splice(i, 1);
                            break;
                        }
                    }
                }

                if (typeof onSelectRow === "function") {
                    onSelectRow(row_id, status);
                }
            },
            subGrid: !cannotExpand && typeof subGridRowExpanded === 'function',
            subGridOptions: {
                "plusicon": "ui-icon-carat-1-s",
                "minusicon": "ui-icon-carat-1-n"
            },
            subGridRowExpanded: function (subgrid_id, row_id) {
                // 展开后自动选中
                $("#" + this.id).jqGrid('setSelection', row_id);
                var rowExpanded = $("#" + this.id).jqGrid("getRowData", row_id);
                subGridRowExpanded(subgrid_id, row_id, rowExpanded);
            },
            gridComplete: function (e) {
                _ROWS_CHOOSED = [];
            },
            pager: pagerId,
            viewrecords: true,
            hidegrid: false,
        });

        $(gridId).jqGrid('navGrid', pagerId, {
            edit: false, add: false, del: false, search: false
        }, {
            height: 200, reloadAfterSubmit: true
        });

        $("body").resize(function () {
            var width = $(gridId).parents(".jqGrid_wrapper").width();
            if (width) {
                $(gridId).setGridWidth(width);
            }
        });
    }

    function appendHistoryColumn(columns) {
        // if (LOGIN_USER === 'root') {
        //     columns = columns.concat(
        //         {
        //             name: 'historyType', header: '历史', formatter: function (val, opt, obj) {
        //                 return historyFormatter(obj.id, val);
        //             }
        //         }
        //     );
        // }
        return columns;
    }

    function initGridData(url, params, columns, gridId, pagerId, rows, forceCloseExpand) {
        setURLParamToDom();
        gridId = "#" + (gridId || "table_list");
        pagerId = "#" + (pagerId || "pager_list");
        columns = appendHistoryColumn(columns);

        $(gridId).jqGrid({
            url: url,
            postData: params || getSearchParams(),
            datatype: "json",
            mtype: "GET",
            height: "100%",
            autowidth: true,
            shrinkToFit: true,
            rowNum: rows || 20,
            rownumbers: true,
            multiselect: true,
            onSelectAll: function (aRowids, status) {
                var gridObj = $("#" + this.id);
                _ROWS_CHOOSED = [];
                if (status) {
                    $.each(aRowids, function (index, id) {
                        _ROWS_CHOOSED.push(gridObj.jqGrid("getRowData", id));
                    });
                }
            },
            onSelectRow: function (row_id, status) {
                var rowSelect = $("#" + this.id).jqGrid("getRowData", row_id);
                if (status) {
                    //选中
                    _ROWS_CHOOSED.push(rowSelect);
                } else {
                    //取消选中
                    for (var i = 0; i < _ROWS_CHOOSED.length; i++) {
                        if (_ROWS_CHOOSED[i].id === rowSelect.id) {
                            _ROWS_CHOOSED.splice(i, 1);
                            break;
                        }
                    }
                }

                if (typeof onSelectRow === "function") {
                    onSelectRow(row_id, status);
                }
            },
            rownumWidth: 50,
            cellLayout:50,
            rowList: rows ? [rows] : [20, 50, 100],
            colNames: columns.map(function (item) {
                return item.header;
            }),
            colModel: columns.map(function (item) {
                return {
                    name: item.name,
                    formatter: item.formatter || function (val) {
                        if (val == null || val === undefined) {
                            return '';
                        }
                        return val;
                    },
                    width: defaultColWidth(item),
                    hidden: item.hidden || false,
                    key: item.key || false
                }
            }),
            pager: pagerId,
            viewrecords: true,
            hidegrid: false,
            subGrid: forceCloseExpand === true ? false : (typeof subGridRowExpanded === 'function'),
            subGridOptions: {
                "plusicon": "ui-icon-carat-1-s",
                "minusicon": "ui-icon-carat-1-n"
            },
            subGridRowExpanded: function (subgrid_id, row_id) {
                // 展开后自动选中
                //$("#" + this.id).jqGrid('setSelection', row_id);
                var rowExpanded = $("#" + this.id).jqGrid("getRowData", row_id);
                subGridRowExpanded(subgrid_id, row_id, rowExpanded);
            },
            loadComplete: function (XMLHttpRequest) {
                if (gridLoadComplete && typeof gridLoadComplete === 'function') {
                    gridLoadComplete(XMLHttpRequest);
                }
                $(gridId).find('img').click(function (e) {
                    $('#preview').css('background-image', "url(" + $(e.target).attr('src') + ")");
                    $('#gridImgPreview').modal('show');
                });
            },
            gridComplete: function (e) {
                _ROWS_CHOOSED = [];
            }
        });

        $(gridId).jqGrid('navGrid', pagerId, {
            edit: false, add: false, del: false, search: false
        }, {
            height: 200, reloadAfterSubmit: true
        });

        $("body").resize(function () {
            var width = $(gridId).parents(".jqGrid_wrapper").width();
            if (width) {
                $(gridId).setGridWidth(width);
            }
        });
    }

    function hyperlinkeButtonFormatter(title, funcExpression, color) {
        var link = $('<a></a>');
        link.html(title);
        if (funcExpression) {
            link.attr('href', 'javascript:' + funcExpression);
        }
        link.attr('style', "color: " + (color || _DEFAULT_LINK_COLOR) + " !important; text-decoration:underline; margin:5px;");
        return link.prop("outerHTML");
    }

    function hyperlinkFormatter(title, href, color) {
        var link = $('<a></a>');
        link.html(title);
        link.attr("href", href);
        link.attr("target", "_blank");
        link.attr('style', "color: " + (color || _DEFAULT_LINK_COLOR) + " !important; text-decoration:underline; ");
        return link.prop("outerHTML");
    }


    function imgPreViewFormatter(val) {
        var img = $('<img></img>');
        img.attr("src", val);
        img.css('width', "100px");
        img.css('cursor', "pointer");
        return img.prop("outerHTML");
    }

    function circleInFrontTextFormatter(title, cls) {
        var sp = $('<span></span>');
        var i = $('<i class="fa fa-circle" style="font-size: xx-small;margin-right:5px"></i>');

        i.addClass(cls);
        sp.append(i);
        sp.append(title);

        return sp.prop("outerHTML");
    }

    function colorfulTextFormatter(title, cls) {
        var sp = $('<span>' + title + '</span>');
        sp.addClass(cls);

        return sp.prop("outerHTML");
    }

    function uriDecode(val) {
        if (!val) {
            return "";
        }
        return decodeURI(val);
    }

    function setURLParamToDom() {
        for (var key in JS_PAGE_PARAMS) {
            if ($(getEleId4JQ(key)).length > 0) {
                $(getEleId4JQ(key)).val(JS_PAGE_PARAMS[key]);
                if ($(getEleId4JQ(key))[0].tagName === 'SELECT') {
                    $(getEleId4JQ(key)).trigger('change');
                }
            }
        }
    }

    function getSearchParams() {
        var param = {};
        $.each($('[search-param]'), function () {
            param[$(this).attr('id')] = $(this).attr('value') || $(this).val();
        });
        return param;
    }

    function reloadList() {
        if (typeof _reloadList == "function") {
            _reloadList();
            return;
        }

        var param = getSearchParams();

        $("#table_list").jqGrid('setGridParam', {
            datatype: 'json',
            page: 1,
            postData: param
        }).trigger("reloadGrid");
        _ROWS_CHOOSED = [];

        saveParamInUrl(param);
    }

    function saveParamInUrl(param) {
        var baseUrl = window.location.pathname;

        var paramsArray = [];

        //add search params: 保存当前搜索条件，返回后使用
        $.each(param, function (key, value) {
            paramsArray.push(key + "=" + value);
        });

        history.pushState({}, "", baseUrl + "?" + paramsArray.join("&"));
    }

    function historyFormatter(key, type) {
        if (type) {
            return hyperlinkeButtonFormatter("历史", "openHistory(\'" + key + "\',\'" + type + "\')");
        }
        return '';
    }

    function openHistory(key, type) {
        window.open(CONTENT_PATH + "history/index?key=" + key + "&type=" + type);
    }

    $(document).ready(function () {
        $('input[search-param]').attr('placeholder', '请输入');
        $('.select2_demo_3[search-param]').select2();
    });


</script>