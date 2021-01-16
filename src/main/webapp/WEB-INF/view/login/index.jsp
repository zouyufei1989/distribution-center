<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>沛尚医美</title>

    <link rel="shortcut icon" type="image/x-icon" href="resource/favicon.ico" media="screen"/>
    <link href="script/plugins/inspinia/css/bootstrap.min.css" rel="stylesheet">
    <link href="script/plugins/inspinia/font-awesome/css/font-awesome.css" rel="stylesheet">

    <link href="script/plugins/inspinia/css/animate.css" rel="stylesheet">
    <link href="script/plugins/inspinia/css/style.css" rel="stylesheet">
    <link href="script/plugins/inspinia/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <style type="text/css">
        .form-group {
            margin-left: 0px !important;
            margin-right: 0px !important;
        }

        .loginBox {
            box-shadow: 0 0 2pt #235A60;
            border-radius: 5pt;
        }

    </style>
    <%@ include file="../template_pwd.jsp" %>

</head>

<body class="gray-bg" style="background:url(resource/picture/bg.jpg) no-repeat;background-size:cover">

<div class="row">
    <div class="loginColumns animated fadeInDown">
        <div class="row">
            <div class="col-md-offset-3 col-md-6">
                <div class="ibox-content loginBox">
                    <form id="loginForm" class="form-horizontal form-update">
                        <div class="form-group text-center">
                            <img src="resource/picture/logo@3x.png" style="margin-left: -20pt;width: 20pt;height: 20pt">
                            <span style="font-family:MicrosoftYaHei;color: #404E51 ;font-size: 16pt;margin-left: 10px">沛尚回归系统</span>
                        </div>
                        <p id="errorInfo" style="color: red;"></p>
                        <div class="form-group ">
                            <input type="text" name="userName" id="userName" class="form-control" placeholder="用户名" required="true">
                        </div>
                        <div class="form-group">
                            <input type="password" name="password" id="password" class="form-control" placeholder="密码" required="true">
                        </div>
                        <div class="form-group" style="margin-bottom: 0 !important;">
                            <input type="text" name="verifyCode" id="verifyCode" class="form-control" placeholder="验证码" required="true" style="width:65%; display: inline !important;"> <img id="img_verify_code" style="float: right;"/>
                        </div>
                        <div class="form-group">
                            <button type="button" class="btn btn-outline btn-link pull-right" onclick="getVerifyCode()">换一个</button>
                        </div>
                    </form>
                    <button onclick="login()" class="btn btn-primary block full-width m-b" style="height: 32px;background-color:#00AAB9 !important;">登录</button>
                    <button type="reset" class="btn block full-width m-b btn-default" onclick="$('form')[0].reset()" style="height: 32px;">重置</button>
                </div>
            </div>

        </div>
    </div>
</div>
<footer class="text-center" style="position:fixed; bottom: 30px;width: 100%;">
    <a href="http://beian.miit.gov.cn" style="color: white !important;">${beian}</a>
</footer>

<script src="${pageContext.request.contextPath}/script/plugins/inspinia/js/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/script/plugins/inspinia/js/plugins/validate/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/script/js/common_cookies.js"></script>
<script src="${pageContext.request.contextPath}/script/js/view/login/index.js"></script>
</body>
</html>
