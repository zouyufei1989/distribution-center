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
                                    <label class="col-sm-3 control-label"> <span style="color: red;">*</span>套餐名称：</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" id="name" required maxlength="20">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"> <span style="color: red;">*</span>套餐价格：</label>
                                    <div class="col-sm-7">
                                        <input type="text" value="" id="sumPrice" min="0">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"> <span style="color: red;">*</span>使用次数：</label>
                                    <div class="col-sm-7">
                                        <input type="text" value="" id="cnt" min="0">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"> <span style="color: red;"></span>简介：</label>
                                    <div class="col-sm-7">
                                        <textarea name="desc" class="form-control" id="desc" rows="5" maxlength="500"></textarea>
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
                                        <span style="">套餐封面:</span>
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

<upload-img-modal id="coverImgModal" title="套餐封面" img="coverImgUrl" width="686" height="176"></upload-img-modal>