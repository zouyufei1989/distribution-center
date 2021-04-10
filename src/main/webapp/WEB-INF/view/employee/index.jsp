<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="../template_meta.jsp" %>
    <%@ include file="../template_css.jsp" %>
    <%@ include file="../template_js.jsp" %>
    <style type="text/css">
        #bindModal td {
            border: none !important;
        }

        .shareholder_list {
            border: #888888 dashed 1px;
            border-radius: 7px;
            margin-top: 10px;
            padding: 10px;
            height: 400px;
            overflow:auto;
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
                        <form role="form" class="form-inline" style="padding: 0px">
                            <%@ include file="../template_btnGroup.jsp" %>
                            <%@ include file="../vue_template/employee_status_combo.jsp" %>
                            <%@ include file="../vue_template/group_combo.jsp" %>
                            <%@ include file="../vue_template/gender_combo.jsp" %>
                            <%@ include file="../vue_template/employee_level_combo.jsp" %>
                            <%@ include file="../vue_template/date_picker_template.jsp" %>
                            <%@ include file="../vue_template/bonus_plan_combo.jsp" %>
                            <%@ include file="../template_search_export_btn.jsp" %>
                            <div class="param_row">
                                <div class="form-group">
                                    <label>门店:</label>
                                    <group-combo id="employee.groupId" search-param></group-combo>
                                </div>
                                <div class="form-group">
                                    <label>姓名:</label> <input type="text" id="employee.name" search-param class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>手机号:</label> <input type="text" id="employee.phone" search-param class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>状态:</label>
                                    <employee-status-combo id="employee.status" search-param must_choose_one="false"></employee-status-combo>
                                </div>
                                <reload-export-btn-group id="btnGroup" reload="true"></reload-export-btn-group>
                            </div>
                        </form>
                        <%@ include file="../template_grid.jsp" %>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="update.jsp" %>
<%@ include file="bindModal.jsp" %>

</body>
<script type="text/javascript">
    loadJS("../script/js/view/employee/index.js", 1)
    loadJS("../script/js/view/employee/update.js", 1)
    loadJS("../script/js/view/employee/bindModal.js", 1)
</script>

</html>