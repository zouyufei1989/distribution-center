<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="modal inmodal fade" id="bonusPlanModal" role="dialog" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <h3>选择股东积分方案</h3>
                <div class="wrapper animated fadeInRight">
                    <div class="row">
                        <div class="col-lg-12">
                            <%@ include file="../vue_template/bonus_plan_combo.jsp" %>
                            <form id="bonusPlanForm" class="form-horizontal ">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">股东姓名:</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" v-model="name" disabled/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">股东编号:</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" v-model="serialNumber" disabled/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">联系电话:</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" v-model="phone" disabled/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span style="color: red;">*</span>积分方案</span>:</label>
                                    <div class="col-sm-7">
                                        <bonus-plan-combo id="bonusPlanId" :group_id = "groupId" must_choose_one="false" :bonus_plan_id="bonusPlanId"></bonus-plan-combo>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-3 col-sm-7 text-center">
                                        <button type="button" data-style="zoom-in" class="ladda-button btn btn-w-m btn-primary btn-update-footer" @click="assignBonusPlanId($event)">保存</button>
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

<upload-img-modal id="urlModal" title="banner" img="img_url"></upload-img-modal>