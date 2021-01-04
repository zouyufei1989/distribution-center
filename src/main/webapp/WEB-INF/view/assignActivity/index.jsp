<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="../template_meta.jsp" %>
    <%@ include file="../template_css.jsp" %>
    <%@ include file="../template_js.jsp" %>

    <style type="text/css">
        #divSummary span{
            font-size: x-large;
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
                        <div class="row" style="display: inline;" id="divSummary" >
                            <div class="col-sm-4 text-center">
                                赠送总次数 <br> <span v-cloak>{{data.cnt}}  次</span>
                            </div>
                            <div class="col-sm-4 text-center border-left">
                                赠送股东总数<br> <span v-cloak> {{data.assignCustomerSum}} 人</span>
                            </div>
                            <div class="col-sm-4 text-center border-left">
                                赠送总价值<br> <span v-cloak>{{ moneyFormatter(data.totalPrice)}} 元</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-lg-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <form role="form" class="form-inline" style="padding: 0px">
                            <%@ include file="../template_btnGroup.jsp" %>
                            <%@ include file="../vue_template/date_picker_template.jsp" %>
                            <%@ include file="../template_search_export_btn.jsp" %>
                            <%@ include file="../vue_template/group_combo.jsp" %>
                            <div class="param_row">
                                <div class="form-group">
                                    <label>门店:</label>
                                    <group-combo id="groupId" must_choose_one="false" search-param></group-combo>
                                </div>
                                <div class="form-group">
                                    <label>赠送时间:</label>
                                    <v-date-picker id="startDate" search_param="true" before_month="1"></v-date-picker>
                                    -
                                    <v-date-picker id="endDate" search_param="true"></v-date-picker>
                                </div>
                                <div class="form-group">
                                    <label>活动名称:</label> <input type="text" id="goodsName" search-param class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>操作者:</label> <input type="text" id="creatorName" search-param class="form-control">
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

</body>
<script type="text/javascript">
    loadJS("../script/js/view/assignActivity/index.js", 1)
</script>

</html>