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
            top: 20px;
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
                            <%@ include file="../template_search_export_btn.jsp" %>
                            <%@ include file="../template_upload_modal.jsp" %>
                            <%@ include file="../vue_template/goods_show_price_combo.jsp" %>
                            <%@ include file="../vue_template/goods_tag_combo.jsp" %>
                            <%@ include file="../vue_template/group_combo.jsp" %>
                            <%@ include file="../vue_template/status_combo.jsp" %>
                            <div class="param_row">
                                <div class="form-group">
                                    <label>门店:</label>
                                    <group-combo id="goodsItem.groupId" search-param ></group-combo>
                                </div>
                                <div class="form-group">
                                    <label>商品名称:</label> <input type="text" id="goodsItem.name" search-param class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>标签:</label>
                                    <goods-tag-combo id="goodsItem.goodsTagId" must_choose_one="false" :group_id="groupId" search-param></goods-tag-combo>
                                </div>
                                <div class="form-group">
                                    <label>状态:</label>
                                    <status-combo id="goodsItem.status" search-param must_choose_one="false"></status-combo>
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
<%@ include file="detailEdit.jsp" %>
<%@ include file="detailView.jsp" %>

</body>
<script type="text/javascript">
    loadJS("${pageContext.request.contextPath}/script/plugins/wangEditor/wangEditor.js")
    loadJS("../script/js/view/goods/index.js", 1)
    loadJS("../script/js/view/goods/update.js", 1)
    loadJS("../script/js/view/goods/detail.js", 1)
</script>

</html>