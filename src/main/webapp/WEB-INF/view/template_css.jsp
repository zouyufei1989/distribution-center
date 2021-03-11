<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<title>沛尚回归</title>
<link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/resource/favicon.ico" media="screen"/>
<link href="${pageContext.request.contextPath}/script/plugins/inspinia/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/script/plugins/inspinia/font-awesome/css/font-awesome.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/script/plugins/inspinia/css/plugins/iCheck/custom.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/script/plugins/inspinia/css/plugins/datapicker/datepicker3.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/script/plugins/inspinia/css/plugins/clockpicker/clockpicker.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/script/plugins/inspinia/css/plugins/select2/select2.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/script/plugins/inspinia/css/plugins/jqGrid/ui.jqgrid.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/script/plugins/inspinia/css/plugins/jQueryUI/jquery-ui-1.10.4.custom.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/script/plugins/inspinia/css/plugins/jasny/jasny-bootstrap.min.css" rel="stylesheet">

<!-- Sweet Alert -->
<link href="${pageContext.request.contextPath}/script/plugins/inspinia/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/script/plugins/inspinia/css/style.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/script/plugins/inspinia/css/animate.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/script/plugins/inspinia/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">

<!--switchery-->
<link href="${pageContext.request.contextPath}/script/plugins/inspinia/css/plugins/switchery/switchery.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/script/plugins/datetimepicker/css/bootstrap-datetimepicker.css" rel="stylesheet">
<!--多级下拉列表-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/script/plugins/treeselect/vue-treeselect.min.css">
<!--toastr-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/script/plugins/inspinia/css/plugins/toastr/toastr.min.css">
<!--拖拽上传-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/script/plugins/dropzone/dropzone.css">
<!--数字文本框-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/script/plugins/inspinia/css/plugins/touchspin/jquery.bootstrap-touchspin.min.css">
<!--双向选择框-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/script/plugins/duallistbox/bootstrap-duallistbox.css">
<!--ladda-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/script/plugins/inspinia/css/plugins/ladda/ladda.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/script/plugins/inspinia/css/plugins/ladda/ladda-themeless.min.css">

<link rel="stylesheet" href="${pageContext.request.contextPath}/script/plugins/layui/css/layui.css">


<style type="text/css">

    [v-cloak] {
        display: none !important;
    }

    .btn-tool {
        padding: 0px 15px;
        margin-right: 10px;
        height: 28px;
    }

    .btn-search {
        padding: 0px 30px;
        color: #666;
        height: 28px;
    }

    .btn-update-footer {
        height: 34px;
        margin-right: 6px;
    }

    .margin_search_condition {
        margin-left: 15px;
    }

    .form-update {
        margin: 45px 0px 40px 0px;
        color: #4d4d4d;
    }

    .img-circle {
        width: 40px;
    }

    .upload_box {
        width: 100%;
        height: 80px;
        font-size: -webkit-xxx-large;
        font-weight: lighter;
        border: 1.5px dashed #e5e6e7;
        border-radius: 3px 3px;
        text-align: center;
        cursor: pointer;
    }

    .select2-container--default .select2-selection--single {
        height: 34px;
        line-height: 33px;
    }

    .select2-container--default .select2-selection--single .select2-selection__arrow {
        height: 32px;
    }

    .select2-container--default .select2-selection--single .select2-selection__rendered {
        color: #4d4d4d;
    }

    .text-black {
        color: black;
    }

    .param_row {
        padding-top: 5px;
        padding-bottom: 5px;
        display: flex;
        flex-wrap: wrap;
    }

    .param_row > .form-group:not(:first-child) {
        margin-left: 10px;
    }

    .param_row > .form-group {
        margin-top: 10px;
    }

    .param_row select {
        min-width: 120px;
    }

    .amap-logo {
        display: none !important;
    / / 去掉高德地图logo opacity: 0 !important;
    }

    .amap-copyright {
        opacity: 0;
    / / 去掉高德的版本号
    }

    #mainForm img {
        height: 80px;
    }

    #updateModal .wrapper {
        margin-top: 50px;
    }

    .sweet-alert {
        height: 230px;
    }

    .modal-body > .wrapper {
        margin-top: 25px !important;
    }

    .ui-jqgrid tr.jqgrow td {
        white-space: normal !important;
        height: auto;
        vertical-align: middle;
        padding: 10px !important;
        text-align: center;
        word-break: break-all;
    }

    .ui-jqgrid .ui-jqgrid-htable th {
        white-space: normal !important;
        vertical-align: middle !important;
        text-align: center !important;
        word-break: break-all;
        padding: 10px !important;
    }

    .ui-jqgrid-bdiv{
        height: auto !important;
        min-height: 20px !important;
    }

    .ui-icon-carat-1-s {
        margin-left: 12px !important;
    }

</style>
