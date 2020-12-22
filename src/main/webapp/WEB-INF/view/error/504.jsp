<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOC<!DOCTYPE html>
<html>

<head>

    <%@ include file="../template_meta.jsp" %>
    <%@ include file="../template_css.jsp" %>
    <%@ include file="../template_js.jsp" %>

</head>

<body class="gray-bg">

<div class="middle-box text-center animated fadeInDown">
    <h1>504</h1>
    <h3 class="font-bold">网关超时</h3>

    <div class="error-desc">
        服务器作为网关且不能从上游服务器及时的得到响应返回给客户端
    </div>
</div>


</body>

</html>
