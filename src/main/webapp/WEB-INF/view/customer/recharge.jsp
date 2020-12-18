<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="modal inmodal fade" id="rechargeModal" role="dialog" aria-hidden="true" data-backdrop="static" style="z-index: 3050 !important;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <h3>客户充值</h3>
                <div class="wrapper animated fadeInRight" id="div_recharge">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="alert alert-info" style="padding-top: 10px;padding-bottom: 10px;margin-top: 10px;">
                                <i class="fa fa-info-circle"></i> <span id="">您正在为客户{{customer.name}}充值</span>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <form id="bonusPlanForm" class="form-horizontal ">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">客户姓名:</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" v-model="customer.name" disabled/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">客户编号:</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" v-model="customer.serialNumber" disabled/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">联系电话:</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" v-model="customer.phone" disabled/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"> <span style="color: red;"></span>充值金额：</label>
                                    <div class="col-sm-7">
                                        <input id="recharge" type="text" value="" v-model="customer.recharge" min="1" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-3 col-sm-7 text-center">
                                        <button type="button" data-style="zoom-in" class="ladda-button btn btn-w-m btn-primary btn-update-footer" @click="recharge()">确定</button>
                                        <button type="button" class="btn btn-w-m btn-default btn-update-footer" @click="cancel()">取消</button>
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
