<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="modal inmodal fade" id="refundMoneyModal" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <h3>退款操作</h3>
                <div class="wrapper animated fadeInRight">
                    <div class="row">
                        <div class="col-lg-12">
                            <form id="mainForm" class="form-horizontal ">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">退款金额:</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" v-model="refundAmount">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">退款路径:</label>
                                    <div class="col-sm-7" style="padding-top: 7px">
                                        <label>
                                            <input type="radio" value="1" v-model="type"> 账户余额
                                        </label>
                                        <label style="margin-left: 15px">
                                            <input type="radio" value="2" v-model="type"> 线下退款
                                        </label>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-outline btn-tool btn-default" type="button" data-dismiss="modal">取&nbsp;&nbsp;消</button>
                <button class="btn btn-outline btn-tool btn-primary" type="button" @click="refund($event)">确&nbsp;&nbsp;定</button>
            </div>
        </div>
    </div>
</div>

<div class="modal inmodal fade" id="refundBonusModal" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <h3>扣除积分</h3>
                <div class="wrapper animated fadeInRight">
                    <div class="row">
                        <div class="col-lg-12">
                            <form id="" class="form-horizontal ">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">所属股东:</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" v-model="parentCustomerName" disabled>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">本次积分:</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" v-model="bonusGenerated4Show" disabled>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">获取时间:</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" v-model="createDate4Show" disabled>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">扣除数量:</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" v-model="bonusGenerated">
                                    </div>
                                    <label class="col-sm-3 control-label text-muted">当前总积分{{availableBonus4Show}}</label>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-outline btn-tool btn-default" type="button" data-dismiss="modal">取&nbsp;&nbsp;消</button>
                <button class="btn btn-outline btn-tool btn-primary" type="button" @click="refund($event)">确&nbsp;&nbsp;定</button>
            </div>
        </div>
    </div>
</div>
