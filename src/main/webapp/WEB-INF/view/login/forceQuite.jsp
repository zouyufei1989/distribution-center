<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Pandabus | TimeOut</title>

    <link href="${pageContext.request.contextPath}/script/plugins/inspinia/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/script/plugins/inspinia/font-awesome/css/font-awesome.css" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/script/plugins/inspinia/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/script/plugins/inspinia/css/style.css" rel="stylesheet">

</head>

<body class="gray-bg">


<div class="middle-box text-center animated fadeInDown">
    <h2>该账号已在其他设备上登录</h2>
    <h3 class="font-bold">即将跳转到登录页面(<font id="timeCount" color="orange"></font>)...</h3>

    <div class="error-desc">
        若没有自动跳转,请<a href="${pageContext.request.contextPath}/login">点我立即重新登录</a>
    </div>
</div>


<script type="text/javascript">
    <!-- Mainly scripts -->
    loadJS("${pageContext.request.contextPath}/script/plugins/inspinia/js/jquery-3.3.1.min.js")
    loadJS("${pageContext.request.contextPath}/script/plugins/inspinia/js/bootstrap.min.js")

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
</body>

</html>
