<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="../template_meta.jsp" %>
    <%@ include file="../template_css.jsp" %>
    <%@ include file="../template_js.jsp" %>
    <style type="text/css">
        #updateModal .modal-dialog {
            position: absolute;
            top: 0;
            bottom: 0;
            left: 0;
            right: 0;
        }

        #updateModal .modal-content {
            position: absolute;
            top: 0;
            bottom: 0;
            width: 100%;
        }

        #updateModal .modal-body {
            overflow-y: scroll;
            position: absolute;
            top: 55px;
            bottom: 65px;
            width: 100%;
        }

        #updateModal .modal-header .close {
            margin-right: 15px;
        }

        #updateModal .modal-footer {
            position: absolute;
            width: 100%;
            bottom: 0;
        }

    </style>
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
                            <%@ include file="../vue_template/status_combo.jsp" %>
                            <%@ include file="../template_search_export_btn.jsp" %>
                            <%@ include file="../template_upload_modal.jsp" %>
                            <div class="param_row">
                                <div class="form-group">
                                    <label>门店名称:</label> <input type="text" id="name" search-param class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>状态:</label>
                                    <status-combo id="status" search-param must_choose_one="false"></status-combo>
                                </div>
                                <div class="form-group">
                                    <label>负责人:</label> <input type="text" id="ownerName" search-param class="form-control">
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
    loadJS("../script/js/view/group/index.js", 1)
    loadJS("../script/js/view/group/update.js", 1)
</script>

</html>