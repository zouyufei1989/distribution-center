<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="../template_meta.jsp" %>
    <%@ include file="../template_css.jsp" %>
    <%@ include file="../template_js.jsp" %>
    <%@ include file="../template_pwd.jsp" %>
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
                        <%@ include file="../vue_template/role_combo.jsp" %>
                        <%@ include file="../vue_template/group_combo.jsp" %>
                        <form id="mainForm" class="form-horizontal form-update">
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><span style="color: red;">*</span>登录账号:</label>
                                <div class="col-sm-7">
                                    <input type="text" class="form-control" id="username" name="username" required/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><span style="color: red;">*</span>密码:</label>
                                <div class="col-sm-7">
                                    <input type="password" class="form-control" id="password" name="password" required strictPwd="true"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><span style="color: red;">*</span>确认密码:</label>
                                <div class="col-sm-7">
                                    <input type="password" class="form-control" id="re_password" name="re_password" equalTo="#password" required/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><span style="color: red;">*</span>昵称:</label>
                                <div class="col-sm-7">
                                    <input type="text" class="form-control" id="nickName" name="nickName" required/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><span style="color: red;">*</span>角色:</label>
                                <div class="col-sm-7">
                                    <role-combo id="roleId" must_choose_one="true"></role-combo>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><span style="color: red;">*</span>公司:</label>
                                <div class="col-sm-7">
                                    <group-combo id="groupId" must_choose_one="false"></group-combo>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-3"></div>
                                <div class="col-sm-6">
                                    <%@ include file="../template_save_update_btn.jsp" %>
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
    loadJS("${pageContext.request.contextPath}/script/js/view/system/users/update.js", 1)
    loadJS("${pageContext.request.contextPath}/script/js/md5.js")
</script>
</html>