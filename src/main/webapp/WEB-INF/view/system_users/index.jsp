<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="../template_meta.jsp" %>
    <%@ include file="../template_css.jsp" %>
    <%@ include file="../template_js.jsp" %>
</head>
<body>
<%@ include file="../template_menu.jsp" %>

<div id="page-wrapper" class="gray-bg">
    <%@ include file="../template_header.jsp" %>

    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-lg-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <form role="form" class="form-inline" style="padding: 0px">
                            <%@ include file="../template_btnGroup.jsp" %>
                        </form>
                        <%@ include file="../template_grid.jsp" %>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="changePwdModal" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12">
                        <h3 class="m-t-none m-b">修改密码</h3>
                        <hr/>
                        <form role="form" id="pwdForm">
                            <div class="form-group">
                                <label class="control-label"><span style="color: red;">*</span>新密码：</label> <input type="password" class="form-control" name="pass_newPassword" id="pass_newPassword" required strictPwd="true">
                            </div>
                            <div class="form-group">
                                <label class="control-label"><span style="color: red;">*</span>确认密码：</label> <input type="password" class="form-control" name="pass_confirmPassword" id="pass_confirmPassword" equalTo="#pass_newPassword" required>
                            </div>
                            <input type="hidden" id="userId">
                        </form>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-outline btn-tool btn-primary" type="button" onclick="changePwd($(this))">提&nbsp;&nbsp;交</button>
                <button class="btn btn-outline btn-tool btn-default" type="button" data-dismiss="modal">取&nbsp;&nbsp;消</button>
            </div>
        </div>
    </div>
</div>

</body>

<script type="text/javascript">
    loadJS("${pageContext.request.contextPath}/script/js/view/system/users/index.js", 1)
    loadJS("${pageContext.request.contextPath}/script/js/md5.js")
</script>
</html>