<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOC<!DOCTYPE html>
<html>

<head>

    <%@ include file="template_meta.jsp" %>
    <%@ include file="template_css.jsp" %>
    <%@ include file="template_js.jsp" %>

</head>

<body class="gray-bg">


<div class="middle-box text-center animated fadeInDown">
    <div class="middle-box text-center animated fadeInDown">
        <div class="error-desc">
            ${message}
        </div>
    </div>
</div>


</body>

</html>
