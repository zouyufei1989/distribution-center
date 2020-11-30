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
                        <%@ include file="../vue_template/city_combo.jsp" %>
                        <%@ include file="../vue_template/status_combo.jsp" %>
                        <form id="mainForm" class="form-horizontal form-update">
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><span style="color: red;">*</span>公司名称:</label>
                                <div class="col-sm-7">
                                    <input type="text" class="form-control" id="name" name="name" required/>
                                </div>
                            </div>

                            <div class="form-group">

                                <label class="col-sm-3 control-label"> <span style="color: red;">*</span>城市编码：</label>
                                <div class="col-sm-7">
                                    <city-combo id="cityCode" must-choose-one="true" :city_code="cityCode"></city-combo>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><span style="color: red;">*</span>公司地址:</label>
                                <div class="col-sm-7">
                                    <input type="text" class="form-control" id="address" name="address" required/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><span style="color: red;">*</span>集团负责人:</label>
                                <div class="col-sm-7">
                                    <input type="text" class="form-control" id="ownerName" name="ownerName" required/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><span style="color: red;">*</span>负责人电话:</label>
                                <div class="col-sm-7">
                                    <input type="text" class="form-control" id="ownerPhone" name="ownerPhone" required/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><span style="color: red;">*</span>状态:</label>
                                <div class="col-sm-7">
                                    <status-combo id="status" must-choose-one="true"></status-combo>
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
    loadJS("../script/js/view/group/update.js", 1)
</script>
</html>