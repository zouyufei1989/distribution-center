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
                            <%@ include file="../template_search_export_btn.jsp" %>
                            <div class="param_row">
                                <div class="form-group">
                                    <div class="form-group">
                                        <label>门店:</label>
                                        <group-combo id="groupId" search-param></group-combo>
                                    </div>
                                </div>
                                <reload-export-btn-group id="btnGroup" reload="true"></reload-export-btn-group>
                            </div>
                            <div class="param_row">
                                <div style="margin-top: 20px;">
                                    <label style="margin-right: 10px;">是否可预约:</label><input type="checkbox" class="js-switch" checked id="reserveFlag"/>
                                </div>
                            </div>
                        </form>
                        <div class="jqGrid_wrapper" style="margin-top: 10px;" id="reservationPeriodModal">
                            <table class="table table-striped">
                                <thead>
                                <tr class="font-bold">
                                    <th>开始时间</th>
                                    <th>结束时间</th>
                                    <th>可预约人数</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr v-for="(period,i) in periods">
                                    <td>
                                        <div class="input-group clockpicker" data-autoclose="true">
                                            <input v-if="reserveFlag==1"  type="text" class="form-control input-sm" v-model="period.startTime" data-mask="99:99:99" @blur="setStartTime(i,$event)"> <span class="input-group-addon"><span class="fa fa-clock-o"></span></span>
                                            <input v-else disabled  type="text" class="form-control input-sm" v-model="period.startTime" data-mask="99:99:99" @blur="setStartTime(i,$event)"> <span class="input-group-addon"><span class="fa fa-clock-o"></span></span>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="input-group clockpicker" data-autoclose="true">
                                            <input v-if="reserveFlag==1"  type="text" class="form-control input-sm" v-model="period.endTime" data-mask="99:99:99" @blur="setEndTime(i,$event)"> <span class="input-group-addon"><span class="fa fa-clock-o"></span></span>
                                            <input v-else disabled type="text" class="form-control input-sm" v-model="period.endTime" data-mask="99:99:99" @blur="setEndTime(i,$event)"> <span class="input-group-addon"><span class="fa fa-clock-o"></span></span>
                                        </div>
                                    </td>
                                    <td>
                                        <input v-if="reserveFlag==1" type="text" class="form-control input-sm" v-model="period.cnt">
                                        <input v-else disabled type="text" class="form-control input-sm" v-model="period.cnt">
                                    </td>
                                    <td><i v-show="reserveFlag==1" class="fa fa-times" @click="remove(i)"></i></td>
                                </tr>
                                <tr v-show="reserveFlag==1">
                                    <td colspan="3" class="text-center">
                                        <span class="addPeriod" @click="add()"> 添加时间段 + </span>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="3" class="text-center">
                                        <button v-show="reserveFlag==1" type="button" data-style="zoom-in" class="ladda-button btn btn-w-m btn-primary btn-update-footer" @click="save($event)">保存</button>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
<script type="text/javascript">
    loadJS("../script/js/view/groupReservationPeriod/index.js", 1)
</script>

</html>