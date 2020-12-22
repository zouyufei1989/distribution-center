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
    <h1>403</h1>
    <h3 class="font-bold">禁止访问</h3>

    <div class="error-desc">
        客户端没有权利访问所请求内容
    </div>
</div>


</body>

</html>
