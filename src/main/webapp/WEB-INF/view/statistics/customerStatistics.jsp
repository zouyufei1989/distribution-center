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
            background-color: #ddd !important;
        }

        .panel-body {
            background-color: #ddd !important;
        }

        .panel-number {
            background-color: #f3f3f4 !important;
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
                    <div class="ibox-content">
                        <form role="form" class="form-inline" style="padding: 0px;margin-bottom: 15px">
                            <%@ include file="../template_btnGroup.jsp" %>
                            <%@ include file="../vue_template/group_combo.jsp" %>
                            <%@ include file="../vue_template/month_picker_template.jsp" %>
                            <%@ include file="../template_search_export_btn.jsp" %>
                            <div class="param_row">
                                <div class="form-group">
                                    <label>门店:</label>
                                    <group-combo id="groupId" search-param></group-combo>
                                </div>
                                <div class="form-group">
                                    <label>月份:</label>
                                    <v-month-picker id="month"></v-month-picker>
                                </div>
                                <reload-export-btn-group id="btnGroup" reload="true"></reload-export-btn-group>
                            </div>
                        </form>

                        <div class="row" style="width:100% !important;" id="divSummary" v-cloak>
                            <div class="col-lg-6">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        顾客回店
                                    </div>
                                    <div class="panel-body" style="display: flex;justify-content: space-between">
                                        <div class="panel panel-default" style="width: 40%">
                                            <div class="panel-heading panel-number" style="color: darkgrey !important;">
                                                已到店顾客数
                                            </div>
                                            <div class="panel-body text-center panel-number">
                                                <h1>{{arrivalCnt}}</h1>
                                            </div>
                                        </div>

                                        <div class="panel panel-default" style="width: 40%">
                                            <div class="panel-heading panel-number" style="color: darkgrey !important;">
                                                未到店顾客数
                                            </div>
                                            <div class="panel-body text-center panel-number">
                                                <h1>{{pendingCnt}}</h1>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        新增顾客
                                    </div>
                                    <div class="panel-body" style="display: flex;justify-content: space-between">
                                        <div class="panel panel-default" style="width: 40%">
                                            <div class="panel-heading panel-number" style="color: darkgrey !important;">
                                                新顾客到店数
                                            </div>
                                            <div class="panel-body text-center panel-number">
                                                <h1>{{newArrivalCnt}}</h1>
                                            </div>
                                        </div>

                                        <div class="panel panel-default" style="width: 40%">
                                            <div class="panel-heading panel-number" style="color: darkgrey !important;">
                                                新顾客未到店数
                                            </div>
                                            <div class="panel-body text-center panel-number">
                                                <h1>{{newPendingCnt}}</h1>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <%@ include file="../template_grid.jsp" %>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
<script type="text/javascript">
    loadJS("../script/js/view/statistics/customerStatistics.js", 1)
</script>

</html>