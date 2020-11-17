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

    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-lg-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content" style="min-height: 550px;">
                        <form role="form" class="form-inline" style="padding: 0px">
                            <%@ include file="../template_btnGroup.jsp" %>
                            <%@ include file="../template_search_export_btn.jsp" %>
                            <%@ include file="../vue_template/visit_log_type_combo.jsp" %>
                            <%@ include file="../vue_template/date_picker_template.jsp" %>
                            <%@ include file="../vue_template/user_combo.jsp" %>
                            <div class="param_row">
                                <div class="form-group">
                                    <label>访问日期:</label>
                                    <v-date-picker id="startDate" search_param="true"></v-date-picker>
                                    -
                                    <v-date-picker id="endDate" search_param="true"></v-date-picker>
                                </div>
                            </div>
                            <div class="param_row">
                                <div class="form-group">
                                    <label>模块名称:</label> <input type="text" class="form-control" id="moduleName" search-param/>
                                </div>
                                <div class="form-group">
                                    <label>功能名称:</label> <input type="text" class="form-control" id="resourceName" search-param/>
                                </div>
                                <div class="form-group">
                                    <label>操作用户名:</label>
                                    <user-combo id="userId" must_choose_one="false" search-param></user-combo>
                                </div>
                                <div class="form-group">
                                    <label>类型:</label>
                                    <visit-log-type-combo id="type" search-param must_choose_one="false"></visit-log-type-combo>
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

</body>

<script src="../script/js/view/visitLog/index.js"></script>
</html>