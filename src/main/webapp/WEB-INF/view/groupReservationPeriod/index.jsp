<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="../template_meta.jsp" %>
    <%@ include file="../template_css.jsp" %>
    <%@ include file="../template_js.jsp" %>
    <style type="text/css">
        .addPeriod {
            border: 1px dashed slategray;
            padding: 5px 20px 5px 20px;
            cursor: pointer;
            border-radius: 3px;
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
                            <%@ include file="../vue_template/group_combo.jsp" %>
                            <%@ include file="../vue_template/goods_type_combo.jsp" %>
                            <%@ include file="../template_search_export_btn.jsp" %>
                            <%@ include file="../template_loading_modal.jsp" %>
                            <div class="param_row">
                                <input type="hidden" id="onlyReservable" value="true" search-param class="form-control">
                                <div class="form-group">
                                    <div class="form-group">
                                        <label>门店:</label>
                                        <group-combo id="groupId" search-param></group-combo>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="form-group">
                                        <label>名称:</label>
                                        <input type="text" id="goods.name" search-param class="form-control">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="form-group">
                                        <label>类型:</label>
                                        <goods-type-combo id="goods.type" search-param must_choose_one="false" data_exclude="1"></goods-type-combo>
                                    </div>
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

<%@ include file="periodListModal.jsp" %>
<%@ include file="updatePeriodListModal.jsp" %>

</body>
<script type="text/javascript">
    loadJS("../script/js/view/groupReservationPeriod/index.js", 1)
    loadJS("../script/js/view/groupReservationPeriod/periodListModal.js", 1)
    loadJS("../script/js/view/groupReservationPeriod/updatePeriodListModal.js", 1)
</script>

</html>