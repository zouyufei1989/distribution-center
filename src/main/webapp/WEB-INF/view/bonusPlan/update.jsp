<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="modal inmodal fade" id="updateModal" role="dialog" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-body">
                <h3></h3>
                <div class="wrapper animated fadeInRight">
                    <div class="row">
                        <div class="col-lg-12">
                            <form id="mainForm" class="form-horizontal ">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span style="color: red;">*</span>所属门店:</label>
                                    <div class="col-sm-7">
                                        <group-combo id="groupId"></group-combo>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"> <span style="color: red;">*</span>积分编号：</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" id="serialNumber" required readonly disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"> <span style="color: red;">*</span>积分方案名称：</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" id="name" required maxlength="20">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"> <span style="color: red;">*</span>设置积分比例：</label>
                                    <div class="col-sm-7">
                                        <input type="text" value="" id="bonusRate" min="0">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"> <span style="color: red;">*</span>首次是否返现：</label>
                                    <div class="col-sm-7">
                                        <label>
                                            <input type="radio" name="cashbackFirst" id="cashbackFirst1" value="1" checked> 是
                                        </label>
                                        <label style="margin-left: 15px">
                                            <input type="radio" name="cashbackFirst" id="cashbackFirst0" value="0"> 否
                                        </label>
                                    </div>
                                </div>
                                <div class="form-group" id="div_cashback">
                                    <label class="col-sm-3 control-label"> <span style="color: red;">*</span>设置返现金额：</label>
                                    <div class="col-sm-7">
                                        <input type="text" value="" id="cashbackAmount" min="0">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span style="color: red;">*</span>状态:</label>
                                    <div class="col-sm-7">
                                        <status-combo id="status" must-choose-one="true"></status-combo>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span style="color: red;"></span>备注:</label>
                                    <div class="col-sm-7">
                                        <textarea class="form-control" id="desc" rows="5" maxlength="200"></textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-3 col-sm-7 text-center">
                                        <%@ include file="../template_save_update_btn.jsp" %>
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