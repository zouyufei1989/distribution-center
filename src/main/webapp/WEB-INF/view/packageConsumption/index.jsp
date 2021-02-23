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
                            <%@ include file="../template_search_export_btn.jsp" %>
                            <%@ include file="../vue_template/group_combo.jsp" %>
                            <%@ include file="../vue_template/date_picker_template.jsp" %>
                            <div class="param_row">
                                <div class="form-group">
                                    <label>门店:</label>
                                    <group-combo id="groupId" search-param></group-combo>
                                </div>
                                <div class="form-group">
                                    <label>姓名:</label> <input type="text" id="customerName" search-param class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>手机号:</label> <input type="text" id="phone" search-param class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>使用日期:</label>
                                    <v-date-picker id="startDate" search_param="true" before_month="1"></v-date-picker>
                                    -
                                    <v-date-picker id="endDate" search_param="true"></v-date-picker>
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


<div class="modal inmodal fade" id="detailModal" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-body">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                </button>
                <h3>项目消耗记录</h3>
                <div class="wrapper animated fadeInRight">
                    <div class="row">
                        <div class="col-md-12">
                            <label style="margin-bottom: 20px">{{tip}}</label>
                        </div>
                        <div class="col-md-12">
                            <div class="tabs-container">
                                <ul class="nav nav-tabs">
                                    <li class="" v-for="(goodsName,index) in goodsNames" @click="queryDetail(orderIds[index])"><a data-toggle="tab" href="tab"> {{goodsName}}</a></li>
                                </ul>
                                <div class="">
                                    <div id="tab" class="tab-pane">
                                        <div class="panel-body">
                                            <table class="table table-bordered table-striped text-center">
                                                <tr>
                                                    <td>消耗时间</td>
                                                    <td>本次扣除</td>
                                                    <td>剩余次数</td>
                                                </tr>
                                                <tr v-for="item in detailList">
                                                    <td>{{formatCreateDate(item.createDate)}}</td>
                                                    <td>{{item.cnt}}</td>
                                                    <td>{{item.aftAvailableCnt}}</td>
                                                </tr>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
<script type="text/javascript">
    loadJS("../script/js/view/packageConsumption/index.js", 1)
</script>

</html>