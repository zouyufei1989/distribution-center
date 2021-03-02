<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="../template_meta.jsp" %>
    <%@ include file="../template_css.jsp" %>
    <%@ include file="../template_js.jsp" %>
    <style type="text/css">
        .panel-heading {
            border-bottom: 0 !important;
            background-color: #ffffff !important;
        }

        .ui-pg-selbox {
            display: none !important;
        }

        .ui-paging-info {
            display: none;
        }

        .ui-pg-table {
            margin-top: 2px !important;
        }

        .ui-jqgrid tr.ui-row-ltr td {
            border-right: none !important;
        }

        .ui-th-ltr {
            border-right: none !important;
        }
    </style>
</head>
<body>
<%@ include file="../template_menu.jsp" %>

<div id="page-wrapper" class="gray-bg">
    <%@ include file="../template_header.jsp" %>
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-lg-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content" style="padding-bottom: 2px !important;padding-top: 0px !important;">
                        <form role="form" class="form-inline" style="padding: 0px;margin-bottom: 15px">
                            <%@ include file="../vue_template/group_combo.jsp" %>
                            <%@ include file="../vue_template/month_picker_template.jsp" %>
                            <%@ include file="../template_search_export_btn.jsp" %>
                            <div class="param_row">
                                <div class="form-group">
                                    <label>门店:</label>
                                    <group-combo id="groupId" search-param must_choose_one="false"></group-combo>
                                </div>
                                <div class="form-group">
                                    <label>月份:</label>
                                    <v-month-picker id="month"></v-month-picker>
                                </div>
                                <reload-export-btn-group id="btnGroup" reload="true"></reload-export-btn-group>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div style="display: flex;justify-content: space-between;" id="divSummary" v-cloak>
                    <div class="panel panel-default" style="width: 23%">
                        <div class="panel-heading" style="color: darkgrey !important;">
                            当前顾客总数
                        </div>
                        <div class="panel-body text-center">
                            <h1>{{allCnt}}</h1>
                        </div>
                    </div>
                    <div class="panel panel-default" style="width: 23%">
                        <div class="panel-heading" style="color: darkgrey !important;">
                            本月新增顾客数
                        </div>
                        <div class="panel-body text-center">
                            <h1>{{monthCnt}}</h1>
                        </div>
                    </div>
                    <div class="panel panel-default" style="width: 23%">
                        <div class="panel-heading" style="color: darkgrey !important;">
                            已消费人数
                        </div>
                        <div class="panel-body text-center">
                            <h1>{{consumedCnt}}</h1>
                        </div>
                    </div>
                    <div class="panel panel-default" style="width: 23%">
                        <div class="panel-heading" style="color: darkgrey !important;">
                            消费顾客占比
                        </div>
                        <div class="panel-body text-center">
                            <h1>{{consumeRate}}</h1>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="ibox float-e-margins ">
                    <div class="ibox-title ibox-title-radius">
                        <h5><strong>门店业绩走势</strong></h5>
                        <div data-toggle="buttons-checkbox" class="btn-group pull-right">
                            <button type="button" data-toggle="buttons-checkbox" class="btn btn-link" onclick="groupPerformance(this,'month')">今月</button>
                            <button type="button" data-toggle="buttons-checkbox" class="btn btn-link" onclick="groupPerformance(this,'year')">全年</button>
                        </div>
                    </div>
                    <div class="ibox-content ibox-content-radius ibox-content-noTopPadding" style="padding-top:0px;padding-bottom:5px">
                        <div class="jqGrid_wrapper" id="chart-container" style="margin-top: 20px; width: 100%; height: 400px"></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-6">
                <div class="ibox float-e-margins ">
                    <div class="ibox-title ibox-title-radius">
                        <h5><strong>产品销量排名</strong></h5>
                    </div>
                    <div class="ibox-content ibox-content-radius ibox-content-noTopPadding" style="padding-top:0px;padding-bottom:5px">
                        <div class="jqGrid_wrapper" style="margin-top: 10px" id="div_list_goods">
                            <table id="table_list_goods"></table>
                            <div id="pager_list_goods"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="ibox float-e-margins ">
                    <div class="ibox-title ibox-title-radius">
                        <h5><strong>套餐销售排名</strong></h5>
                    </div>
                    <div class="ibox-content ibox-content-radius ibox-content-noTopPadding" style="padding-top:0px;padding-bottom:5px">
                        <div class="jqGrid_wrapper" style="margin-top: 10px;" id="div_list_package">
                            <table id="table_list_package"></table>
                            <div id="pager_list_package"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-6">
                <div class="ibox float-e-margins ">
                    <div class="ibox-title ibox-title-radius">
                        <h5><strong>门店业绩统计</strong></h5>
                    </div>
                    <div class="ibox-content ibox-content-radius ibox-content-noTopPadding" style="padding-top:0px;padding-bottom:5px">
                        <div class="jqGrid_wrapper" id="groupPerformancePieChart" style="width:100%;height:485px"></div>
                    </div>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="ibox float-e-margins ">
                    <div class="ibox-title ibox-title-radius">
                        <h5><strong>股东月度积分</strong></h5>
                    </div>
                    <div class="ibox-content ibox-content-radius ibox-content-noTopPadding" style="padding-top:0px;padding-bottom:5px">
                        <div class="jqGrid_wrapper" id="bonusStatistics" style="width:100%;height:485px"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="../template_grid.jsp" %>

</body>
<script type="text/javascript">
    loadJS("../script/js/view/statistics/operationStatistics.js", 1)
</script>

</html>