<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="modal inmodal fade" id="updateModal" role="dialog" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-body">
                <h3></h3>
                <div class="wrapper animated fadeInRight">
                    <div class="row">
                        <div class="col-lg-12">
                            <%@ include file="../vue_template/city_combo.jsp" %>
                            <%@ include file="../vue_template/status_combo.jsp" %>
                            <%@ include file="../vue_template/cash_back_first_combo.jsp" %>
                            <form id="mainForm" class="form-horizontal ">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span style="color: red;">*</span>所属门店:</label>
                                    <div class="col-sm-7">
                                        <group-combo id="groupId"></group-combo>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span style="color: red;">*</span>客户姓名:</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" id="name" name="name" required maxlength="20"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"> <span style="color: red;">*</span>客户编号：</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control"  id="serialNumber" required readonly disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span style="color: red;">*</span>联系电话:</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" id="phone" name="phone" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span style="color: red;">*</span>客户类型:</label>
                                    <div class="col-sm-7">
                                        <label label4Radio>
                                            <input type="radio" name="customerType" id="normal" value="1" > 普客
                                        </label>
                                        <label label4Radio>
                                            <input type="radio" name="customerType" id="shareholder" value="2" checked> 股东
                                        </label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span style="color: red;"></span>股东到期时间:</label>
                                    <div class="col-sm-7">
                                        <v-month-picker id="expireDate"></v-month-picker>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span style="color: red;"></span>银行卡号:</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" id="bankCardNumber" name="bankCardNumber"  maxlength="20">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span style="color: red;"></span>开户行:</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" id="bankName" name="bankName"  maxlength="50"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span style="color: red;">*</span>状态:</label>
                                    <div class="col-sm-7">
                                        <status-combo id="status" must-choose-one="true"></status-combo>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"> <span style="color: red;">*</span>首次是否返现：</label>
                                    <div class="col-sm-7">
                                        <cash-back-first-combo id="cashbackFirst"></cash-back-first-combo>
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

<upload-img-modal id="urlModal" title="banner" img="img_url"></upload-img-modal>