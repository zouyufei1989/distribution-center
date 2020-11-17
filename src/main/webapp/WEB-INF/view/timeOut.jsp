<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/> <!DOC<!DOCTYPE html>
<html>

<head>

    <%@ include file="template_meta.jsp" %>
    <%@ include file="template_css.jsp" %>
    <%@ include file="template_js.jsp" %>

</head>

<body class="gray-bg">

<div class="middle-box text-center animated fadeInDown">
    <div class="middle-box text-center animated fadeInDown">
        <h2>登录超时</h2>
        <h3 class="font-bold">即将跳转到登录页面(<font id="timeCount" color="orange"></font>)...</h3>

        <div class="error-desc">
            若没有自动跳转,请<a href="${pageContext.request.contextPath}/login">点我立即重新登录</a>
        </div>
    </div>

    <script type="text/javascript">
        var cnt = 5;
        $(document).ready(function () {
            timeCount()
        });

        function timeCount() {
            $("#timeCount").html(cnt);
            if (cnt == 0) {
                window.location.href = '${pageContext.request.contextPath}/login';
            } else {
                cnt--;
                setTimeout("timeCount()", 1000);
            }
        }
    </script>
</div>


</body>

</html>
