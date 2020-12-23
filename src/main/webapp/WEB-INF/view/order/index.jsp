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
                        <%@ include file="../template_btnGroup.jsp" %>
                        <%@ include file="../template_search_export_btn.jsp" %>
                        <%@ include file="../vue_template/customer_type_combo.jsp" %>
                        <%@ include file="../vue_template/group_combo.jsp" %>
                        <%@ include file="../vue_template/pay_type_combo.jsp" %>
                        <%@ include file="../vue_template/goods_type_combo.jsp" %>
                        <form role="form" class="form-inline" style="padding: 0px">
                            <div class="param_row">
                                <div class="form-group">
                                    <label>客户姓名:</label> <input type="text" id="customer.name" search-param class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>客户编号:</label>
                                    <input type="text" id="customer.customerGroup.serialNumber" search-param class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>客户类型:</label>
                                    <customer-type-combo id="customer.customerGroup.type" search-param must_choose_one="false"></customer-type-combo>
                                </div>
                            </div>
                            <div class="param_row">
                                <div class="form-group">
                                    <label>店铺:</label>
                                    <group-combo id="groupId" must_choose_one="false" search-param ></group-combo>
                                </div>
                                <div class="form-group">
                                    <label>消费类型:</label>
                                    <goods-type-combo id="order.goodsTypeId" must_choose_one="false" search-param ></goods-type-combo>
                                </div>
                                <div class="form-group">
                                    <label>支付方式:</label>
                                    <pay-type-combo id="orderPay.payType" must_choose_one="false" search-param ></pay-type-combo>
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

<%@ include file="detailModal.jsp" %>

</body>
<script type="text/javascript">
    loadJS("../script/js/view/order/index.js", 1)
    loadJS("../script/js/view/order/detailModal.js", 1)
</script>

</html>