<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="modal inmodal fade" id="updatePeriodListModal" role="dialog" aria-hidden="true" data-backdrop="static" style="z-index: 3050 !important;">
    <div class="modal-dialog ">
        <div class="modal-content ">
            <div class="modal-header">
                <span class="pull-left">编辑预约时段</span>
            </div>
            <div class="modal-body">
                <div class=" animated fadeInRight">
                    <div class="row">
                        <div class="col-lg-12">
                            <form id="mainForm" class="form-horizontal ">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">名称:</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" disabled v-model="goodsName"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">类型:</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" disabled v-model="typeName"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">所属门店:</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" disabled v-model="groupName"/>
                                    </div>
                                </div>
                                <hr/>
                                <template v-for="(period,i) in periods">
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">开始时间:</label>
                                        <div class="col-sm-7">
                                            <div class="input-group clockpicker" data-autoclose="true">
                                                <input type="text" class="form-control input-sm" v-model="period.startTime" data-mask="99:99" @blur="setStartTime(i,$event)">
                                                <span class="input-group-addon">
                                                    <span class="fa fa-clock-o"></span>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">结束时间:</label>
                                        <div class="col-sm-7">
                                            <div class="input-group clockpicker" data-autoclose="true">
                                                <input type="text" class="form-control input-sm" v-model="period.endTime" data-mask="99:99" @blur="setEndTime(i,$event)">
                                                <span class="input-group-addon">
                                                    <span class="fa fa-clock-o"></span>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">可约人数:</label>
                                        <div class="col-sm-7">
                                            <input type="text" class="form-control input-sm" v-model="period.cnt">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-10">
                                            <button v-if="period.saved==0" type="button" class="btn btn-primary btn-sm pull-right" @click="saveTemplate(i)">保存</button>
                                            <button v-if="period.saved==1" type="button" class="btn btn-danger btn-sm pull-right" @click="remove(i)">删除</button>
                                        </div>
                                    </div>
                                    <hr/>
                                </template>
                                <div class="form-group">
                                    <div class="col-sm-10">
                                        <button type="button" data-style="zoom-in" class="ladda-button pull-right btn btn-w-m btn-primary btn-update-footer " @click="save($event)">确认</button>
                                        <button type="button" class="pull-right  btn btn-w-m btn-default btn-update-footer " @click="hide()">取消</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

