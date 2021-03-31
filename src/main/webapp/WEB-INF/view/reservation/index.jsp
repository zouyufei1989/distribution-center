<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="../template_meta.jsp" %>
    <%@ include file="../template_css.jsp" %>
    <%@ include file="../template_js.jsp" %>
    <style type="text/css">
        .fc-header-toolbar button {
            padding: .4em .65em !important;
            line-height: 1 !important;
        }

        .fc-scrollgrid td, th {
            border: 1px solid rgb(221, 221, 221) !important;
        }

        .fc-event-title {
            color: white
        }

        .addPeriod {
            border: 1px dashed slategray;
            padding: 5px 20px 5px 20px;
            cursor: pointer;
            border-radius: 3px;
        }

        .layui-tree-entry {
            padding: 20px;
        }

        label[label4Radio] {
            padding-top: 7px;
            margin-right: 25px;
        }

        .form-group input {
            margin-right: 10px;
        }

        .btn-success:disabled {
            color: white !important;;
        }

    </style>
    <link href="${pageContext.request.contextPath}/script/plugins/fullcalendar/main.min.css" rel="stylesheet">
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
                        <%@ include file="../template_btnGroup.jsp" %>
                        <%@ include file="calendar.jsp" %>
                        <%@ include file="../template_search_export_btn.jsp" %>
                        <%@ include file="../vue_template/customer_type_combo.jsp" %>
                        <%@ include file="../vue_template/group_combo.jsp" %>
                        <%@ include file="../vue_template/pay_type_combo.jsp" %>
                        <%@ include file="../template_loading_modal.jsp" %>
                        <%@ include file="../vue_template/goods_type_combo.jsp" %>
                        <%@ include file="../vue_template/order_combo.jsp" %>
                        <%@ include file="../vue_template/date_picker_template.jsp" %>
                        <%@ include file="../vue_template/reservation_status_combo.jsp" %>
                        <%@ include file="../vue_template/reservation_period_combo.jsp" %>
                        <%@ include file="../vue_template/package_combo.jsp" %>
                        <div id="tmp_grid">
                            <form role="form" class="form-inline" style="padding: 0px">
                                <div class="param_row">
                                    <div class="form-group">
                                        <label>门店:</label>
                                        <group-combo id="groupId" search-param></group-combo>
                                    </div>
                                    <div class="form-group">
                                        <label>姓名:</label> <input type="text" id="customerName" search-param class="form-control"/>
                                    </div>
                                    <div class="form-group">
                                        <label>手机号:</label> <input type="text" id="phone" search-param class="form-control"/>
                                    </div>
                                </div>
                                <div class="param_row">
                                    <div class="form-group">
                                        <label>预约日期:</label>
                                        <v-date-picker id="date" search_param="true"></v-date-picker>
                                    </div>
                                    <div class="form-group">
                                        <label>状态:</label>
                                        <reservation-status-combo id="status" must_choose_one="false" search-param></reservation-status-combo>
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
</div>
<%@ include file="updateModal.jsp" %>
<%@ include file="recharge.jsp" %>
<%@ include file="consume/index.jsp" %>

</body>
<script type="text/javascript">
    loadJS("${pageContext.request.contextPath}/script/plugins/fullcalendar/main.min.js");
    loadJS("${pageContext.request.contextPath}/script/plugins/fullcalendar/locales/zh-cn.js");
    loadJS("../script/js/view/reservation/index.js", 1)
    loadJS("../script/js/view/reservation/update.js", 1)
    loadJS("../script/js/view/reservation/calendar.js", 1)
    loadJS("../script/js/view/reservation/recharge.js", 1)
    loadJS("../script/js/view/reservation/consume/index.js", 1)
    loadJS("../script/js/view/reservation/consume/reservationInfo.js", 1)
    loadJS("../script/js/view/reservation/consume/action.js", 1)

</script>

</html>