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
                                <label class="col-sm-3 control-label"><span style="color: red;">*</span>key:</label>
                                <div class="col-sm-7">
                                    <input type="text" class="form-control" id="key" name="key" required/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><span style="color: red;">*</span>value:</label>
                                <div class="col-sm-7">
                                    <input type="text" class="form-control" id="value" name="value" required/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><span style="color: red;">*</span>说明:</label>
                                <div class="col-sm-7">
                                    <input type="text" class="form-control" id="comment" name="comment" required/>
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
    loadJS("../script/js/view/keyValue/update.js", 1)
</script>
</html>