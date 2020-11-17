<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="../template_meta.jsp" %>
    <%@ include file="../template_css.jsp" %>
    <%@ include file="../template_js.jsp" %>
</head>
<body>
<%@ include file="../template_menu.jsp" %>

<div id="page-wrapper" class="gray-bg">
    <%@ include file="../template_header.jsp" %>
    <%@ include file="../vue_template/date_picker_template.jsp" %>
    <%@ include file="../vue_template/user_combo.jsp" %>
    <%@ include file="../template_grid.jsp" %>

    <div class="wrapper wrapper-content animated fadeInRight">

        <div class="row">
            <div class="col-lg-12">
                <div class="ibox float-e-margins ">
                    <div class="ibox-title ibox-title-radius">
                        <h5><strong>访问资源统计</strong></h5>
                    </div>
                    <div class="ibox-content ibox-content-radius ibox-content-noTopPadding" style="padding-top:5px;padding-bottom:5px">
                        <form onsubmit="return false;" role="form" class="form-inline">
                            <div class="param_row">
                                <div class="form-group">
                                    <label>日期:</label>
                                    <v-date-picker id="startDateByRes"></v-date-picker>
                                    -
                                    <v-date-picker id="endDateByRes"></v-date-picker>
                                </div>
                                <div class="form-group">
                                    <label>访问者:</label>
                                    <user-combo id="userIdByRes" must_choose_one="false"></user-combo>
                                </div>
                                <div class="pull-right">
                                    <button type="button" class="btn btn-default btn-search" onclick="visitLogStsByResource()">查询</button>
                                </div>
                            </div>
                        </form>
                        <div class="jqGrid_wrapper" id="div_visitLogStsByResource" style="margin-top: 20px; width:100%;"></div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-lg-12">
                <div class="ibox float-e-margins ">
                    <div class="ibox-title ibox-title-radius">
                        <h5><strong>访问用户统计</strong></h5>
                    </div>
                    <div class="ibox-content ibox-content-radius ibox-content-noTopPadding" style="padding-top:5px;padding-bottom:5px">
                        <form onsubmit="return false;" role="form" class="form-inline">
                            <div class="param_row">
                                <div class="form-group">
                                    <label>日期:</label>
                                    <v-date-picker id="startDateByUser"></v-date-picker>
                                    -
                                    <v-date-picker id="endDateByUser"></v-date-picker>
                                </div>
                                <div class="form-group">
                                    <label>访问者:</label>
                                    <user-combo id="userIdByUser" must_choose_one="false"></user-combo>
                                </div>
                                <div class="pull-right">
                                    <button type="button" class="btn btn-default btn-search" onclick="visitLogStsByUser()">查询</button>
                                </div>
                            </div>
                        </form>
                        <div class="jqGrid_wrapper" id="div_visitLogStsByUser" style="margin-top: 20px; width:100%;"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>

<script src="../script/js/view/statistics/visitLogStatistics.js"></script>
</html>