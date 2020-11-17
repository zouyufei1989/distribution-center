<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>智享班车管理系统</title>

    <link href="${pageContext.request.contextPath}/script/plugins/inspinia/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/script/plugins/inspinia/font-awesome/css/font-awesome.css" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/script/plugins/inspinia/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/script/plugins/inspinia/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/login/index.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/script/plugins/inspinia/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">

    <script src="${pageContext.request.contextPath}/script/plugins/inspinia/js/jquery-2.1.1.js"></script>
    <%@ include file="../template_pwd.jsp" %>

</head>

<body class="gray-bg">

<div class="row">
    <div class="loginColumns animated fadeInDown">
        <div class="row">
            <div class="col-md-6"></div>
            <div class="col-md-6">
                <div class="ibox-content">
                    <p id="errorInfo" style="color: red;display: none;"></p>
                    <form class="m-t" role="form" action="getLogin" method="post" onsubmit="return submitForm();">
                        <div class="form-group">
                            <input type="text" name="userName" id="userName" class="form-control" placeholder="用户名" required="true">
                        </div>
                        <div class="form-group">
                            <input type="password" name="password" id="password" class="form-control" placeholder="密码" required="true">
                        </div>
                        <div class="form-group">
                            <div class="container">
                            <div id="captcha" style="position: relative"></div>
                            <div id="msg"></div>
                            </div>
                        </div>
                        <button type="submit" disabled class="btn btn-primary block full-width m-b" style="height: 32px;">登录</button>
                        <button type="reset" class="btn block full-width m-b" style="height: 32px;">重置</button>
                    </form>
                    <p class="m-t">
                        <small>Copyright © 2016 EasyInnovation. All Rights Reserved.</small>
                    </p>
                </div>
            </div>
        </div>
        <hr/>
    </div>
</div>


<script src="${pageContext.request.contextPath}/script/plugins/inspinia/js/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/script/js/view/login/index.js"></script>
<script src="${pageContext.request.contextPath}/script/js/view/login/verifyImg.js"></script>
<script type="text/javascript">
    var errorInfo = "${msg}";
</script>

</body>
</html>
