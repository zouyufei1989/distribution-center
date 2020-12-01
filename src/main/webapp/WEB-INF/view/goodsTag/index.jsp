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
                            <%@ include file="../template_loading_modal.jsp" %>
                            <%@ include file="../template_search_export_btn.jsp" %>
                            <%@ include file="../vue_template/group_combo.jsp" %>
                            <div class="param_row">
                                <div class="form-group">
                                    <label>门店:</label>
                                    <group-combo id="goodsTag.groupId" must_choose_one="false" search-param></group-combo>
                                </div>
                                <div class="form-group">
                                    <label>标签名称:</label> <input type="text" id="goodsTag.name" search-param class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>状态:</label>
                                    <status-combo id="goodsTag.status" search-param must_choose_one="false"></status-combo>
                                </div>
                                <reload-export-btn-group id="btnGroup" reload="true"></reload-export-btn-group>
                            </div>
                        </form>
                        <%@ include file="../template_grid.jsp" %>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="update.jsp" %>

</body>
<script type="text/javascript">
    loadJS("../script/js/view/goodsTag/index.js", 1)
    loadJS("../script/js/view/goodsTag/update.js", 1)
</script>

</html>