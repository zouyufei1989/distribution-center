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
                            <form id="mainForm" class="form-horizontal form-update">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span style="color: red;">*</span>所属门店:</label>
                                    <div class="col-sm-7">
                                        <group-combo id="groupId"></group-combo>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"> <span style="color: red;">*</span>商品标签名称：</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" id="name" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span style="color: red;"></span>备注:</label>
                                    <div class="col-sm-7">
                                        <textarea name="desc" class="form-control" id="remark" rows="5"></textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span style="color: red;">*</span>状态:</label>
                                    <div class="col-sm-7">
                                        <status-combo id="status" must-choose-one="true"></status-combo>
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