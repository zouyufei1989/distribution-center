<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="modal inmodal fade" id="reservationPeriodModal" role="dialog" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <h3>预约时间段设置</h3>
                <div class="wrapper animated fadeInRight">
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
                                    <input id="t" type="text" class="form-control input-sm" v-model="period.startTime" data-mask="99:99:99" @blur="setStartTime(i,$event)">
                                    <span class="input-group-addon"><span class="fa fa-clock-o"></span></span>
                                </div>
                            </td>
                            <td>
                                <div class="input-group clockpicker" data-autoclose="true">
                                    <input type="text" class="form-control input-sm" v-model="period.endTime" data-mask="99:99:99"  @blur="setEndTime(i,$event)">
                                    <span class="input-group-addon"><span class="fa fa-clock-o"></span></span>
                                </div>
                            </td>
                            <td><input type="text" class="form-control input-sm" v-model="period.cnt"></td>
                            <td><i class="fa fa-times" @click="remove(i)"></i></td>
                        </tr>
                        <tr>
                            <td colspan="3" class="text-center">
                                <span class="addPeriod" @click="add()"> 添加时间段 + </span>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3" class="text-center">
                                <button type="button" data-style="zoom-in" class="ladda-button btn btn-w-m btn-primary btn-update-footer" @click="save($event)">保存</button>
                                <button type="button" class="btn btn-w-m btn-default btn-update-footer" @click="cancel()">取消</button>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
