<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="../template_meta.jsp" %>
    <%@ include file="../template_css.jsp" %>
    <%@ include file="../template_js.jsp" %>
    <link href="${pageContext.request.contextPath}/script/plugins/fullcalendar/main.min.css" rel="stylesheet">
    <style type="text/css">
        .fc-header-toolbar button {
            padding: .4em .65em !important;
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
                        <div id='calendar'></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
<script type="text/javascript">
    loadJS("${pageContext.request.contextPath}/script/plugins/fullcalendar/locales/zh-cn.js");
    loadJS("${pageContext.request.contextPath}/script/plugins/fullcalendar/main.min.js");
    loadJS("../script/js/view/reservation/calendar.js", 1)
</script>

</html>