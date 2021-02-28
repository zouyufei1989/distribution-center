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
                    <div class="ibox-content">
                        <form role="form" class="form-inline" style="padding: 0px">
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
                                <div class="form-group" style="padding-top:3px">
                                    <label>排名方式:</label>
                                    <label style="margin-left: 5px">
                                        <input type="radio" name="sortType"  value="monthCnt" checked> 按新增顾客数
                                    </label>
                                    <label style="margin-left: 5px">
                                        <input type="radio" name="sortType"  value="availableBonus" > 按积分
                                    </label>
                                    <label style="margin-left: 5px">
                                        <input type="radio" name="sortType"  value="consumeRate" > 按顾客成交率
                                    </label>
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
<script type="text/javascript">
    loadJS("../script/js/view/statistics/shareHolderStatistics.js", 1)
</script>

</html>