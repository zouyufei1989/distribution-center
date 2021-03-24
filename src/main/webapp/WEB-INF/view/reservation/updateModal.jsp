<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="modal inmodal fade" id="updateModal" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <h3>修改预约记录</h3>
                <div class="wrapper animated fadeInRight">
                    <div class="row">
                        <div class="col-lg-12">
                            <form id="mainForm" class="form-horizontal ">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">所属门店:</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" disabled v-model="current.groupName">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">预约项目:</label>
                                    <div class="col-sm-7">
                                        <order-combo id="orderToUse" must_choose_one="false" :customer_group_id="current.customerGroupId" timestamp="" :value="current.orderId" combine=1 ></order-combo>
                                        <span v-show="first!=null" class="help-block m-b-none text-warning">原项目: {{first.goodsName}}</span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">顾客姓名</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" v-model="current.customerName" disabled>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">手机号:</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" disabled v-model="current.phone">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">预约日期:</label>
                                    <div class="col-sm-7">
                                        <v-date-picker id="reservationDate" :value="current.date" ></v-date-picker>
                                        <span v-show="first!=null" class="help-block m-b-none text-warning">原日期: {{first.date}}</span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">预约时段:</label>
                                    <div class="col-sm-7">
                                        <reservation-period-combo  id="period" :date="current.date" :orderid="current.orderId" :timestamp="timestamp" :value="period"></reservation-period-combo>
                                        <span v-show="first!=null" class="help-block m-b-none text-warning">原时段: {{first.startTime + '-' + first.endTime}}</span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">状态:</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" v-model="current.statusName" disabled>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">备注:</label>
                                    <div class="col-sm-7">
                                        <textarea class="form-control" id="desc" rows="5" maxlength="200" v-model="current.remark"></textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-3 col-sm-7">
                                        <button type="button" class="btn btn-w-m btn-default btn-update-footer" data-dismiss="modal">取消</button>
                                        <button type="button" data-style="zoom-in" class="ladda-button btn btn-w-m btn-primary btn-update-footer" @click="editReservation($event)">确定</button>
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
