<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="modal inmodal fade" id="distributionModal" role="dialog" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <h3>积分发放</h3>
                <div class="wrapper animated fadeInRight" id="div_distribution">
                    <div class="row">
                        <div class="col-lg-12" v-if="!multiple">
                            <form id="" class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">客户姓名:</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" :value="shareHolderNames" disabled/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span class="text-danger">*</span> 发放积分:</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" id="amount"/><label class="text-muted"> 可发放积分: {{available4Single}}</label>
                                    </div>

                                </div>
                            </form>
                        </div>
                        <div class="col-lg-12" v-else>
                            <div class="alert alert-info" style="">
                                 {{multipleTip}}
                            </div>
                            <div class="" style="display: flex;">
                                <i class="fa fa-info-circle text-warning fa-2x"></i> <div>批量执行积分发放操作，无法指定发放的积分数量，发放数量为用户所有可发放的积分总额。</div>
                            </div>
                        </div>
                        <div class="col-sm-12 text-right" style="margin-top: 50px">
                            <button type="button" class="btn btn-w-m btn-default btn-update-footer" @click="cancel()">取消</button>
                            <button type="button" data-style="zoom-in" class="ladda-button btn btn-w-m btn-primary btn-update-footer" @click="distribute($event)">确认发放</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

