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
                                    <label class="col-sm-3 control-label"><span style="color: red;">*</span>标题:</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" id="title" name="title" required/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"> <span style="color: red;">*</span>显示排序：</label>
                                    <div class="col-sm-7">
                                        <input type="text" value="" name="index" id="index" min="0" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span style="color: red;"></span>点击链接:</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" id="href" name="href"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span style="color: red;">*</span>图片:</label>
                                    <div class="col-sm-2">
                                        <div class="upload_box" id="url" data-toggle="modal" data-target="#urlModal">+</div>
                                    </div>
                                    <div class="col-sm-5">
                                        <img id="img_url" src="" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span style="color: red;"></span>描述:</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" id="desc" name="desc"/>
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