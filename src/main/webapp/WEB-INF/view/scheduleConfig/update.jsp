<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="../../view/template_meta.jsp" %>
    <%@ include file="../../view/template_css.jsp" %>
    <%@ include file="../../view/template_js.jsp" %>
</head>
<body>
<%@ include file="../../view/template_menu.jsp" %>

<div id="page-wrapper" class="gray-bg">
    <%@ include file="../../view/template_header.jsp" %>
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-lg-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <form id="mainForm" class="form-horizontal form-update">
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><span style="color: red;">*</span>fullClass:</label>
                                <div class="col-sm-7">
                                    <input type="text" class="form-control" id="fullClass" name="fullClass" required/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">year:</label>
                                <div class="col-sm-7">
                                    <input type="text" class="form-control" id="year" name="year"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">month:</label>
                                <div class="col-sm-7">
                                    <input type="text" class="form-control" id="month" name="month"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">day:</label>
                                <div class="col-sm-7">
                                    <input type="text" class="form-control" id="day" name="day"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">hour:</label>
                                <div class="col-sm-7">
                                    <input type="text" class="form-control" id="hour" name="hour"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">minute:</label>
                                <div class="col-sm-7">
                                    <input type="text" class="form-control" id="minute" name="minute"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">参数:</label>
                                <div class="col-sm-7">
                                    <input type="text" class="form-control" id="params" name="params"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">说明:</label>
                                <div class="col-sm-7">
                                    <input type="text" class="form-control" id="comment" name="comment"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-3"></div>
                                <div class="col-sm-6">
                                    <%@ include file="../../view/template_save_update_btn.jsp" %>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    loadJS("${pageContext.request.contextPath}/script/plugins/wangEditor/wangEditor.js")
    loadJS("../script/js/view/scheduleConfig/update.js", 1)
</script>
</html>