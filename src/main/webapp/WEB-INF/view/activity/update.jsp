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
                                    <label class="col-sm-3 control-label"> <span style="color: red;">*</span>活动编号：</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" id="serialNumber" disabled required maxlength="20">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"> <span style="color: red;">*</span>活动名称：</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" id="name" required maxlength="20">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"> <span style="color: red;">*</span>活动有效期：</label>
                                    <div class="col-sm-5">
                                        <input type="text" class="form-control" id="expireMonthCnt" min="1">
                                    </div>
                                    <div class="col-sm-2 text-center">
                                         <label class="control-label">至 </label>
                                         <label class="control-label" id="lbl_expireDate"></label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"> <span style="color: red;">*</span>活动范围：</label>
                                    <div class="col-sm-7">
                                        <activity-scope-combo id="scope" ></activity-scope-combo>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"> <span style="color: red;">*</span>每人限领次数：</label>
                                    <div class="col-sm-7">
                                        <input type="text" value="1" id="maxCntPerCus" min="1">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span style="color: red;">*</span>状态:</label>
                                    <div class="col-sm-7">
                                        <status-combo id="status" must-choose-one="true"></status-combo>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">
                                        <span style="color: red;">活动封面:</span>
                                        <br>
                                        <span style="color:darkgrey;font-size: smaller">(图片尺寸:宽686 * 高176)</span>
                                    </label>
                                    <div class="col-sm-2">
                                        <div class="upload_box" id="coverImg" data-toggle="modal" data-target="#coverImgModal">+</div>
                                    </div>
                                    <div class="col-sm-5">
                                        <img id="coverImgUrl" src="">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"> <span style="color: red;"></span>备注：</label>
                                    <div class="col-sm-7">
                                        <textarea name="desc" class="form-control" id="desc" rows="5" maxlength="500"></textarea>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="row" hide-4-update>
                        <hr/>
                        <%@ include file="editActivityGoods.jsp" %>
                    </div>
                    <div class="row text-right" style="margin-top: 50px">
                        <%@ include file="../template_save_update_btn.jsp" %>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>

<upload-img-modal id="coverImgModal" title="活动封面" img="coverImgUrl"></upload-img-modal>