<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="modal inmodal fade" id="updateModal" role="dialog" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-body">
                <h3></h3>
                <div class="wrapper animated fadeInRight">
                    <div class="row">
                        <div class="col-lg-12">
                            <form id="mainForm" class="form-horizontal form-update">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span style="color: red;">*</span>所属门店:</label>
                                    <div class="col-sm-7">
                                        <group-combo id="groupId"></group-combo>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"> <span style="color: red;">*</span>商品名称：</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" id="name" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"> <span style="color: red;">*</span>毛利率：</label>
                                    <div class="col-sm-7">
                                        <input type="text" value="" id="profitRate" min="0">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span style="color: red;">*</span>商品标签:</label>
                                    <div class="col-sm-7">
                                        <goods-tag-combo id="goodsTagId" must-choose-one="true"></goods-tag-combo>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span style="color: red;">*</span>是否展示价格:</label>
                                    <div class="col-sm-7">
                                        <goods-show-price-combo id="showPrice" must-choose-one="true"></goods-show-price-combo>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"> <span style="color: red;">*</span>单价：</label>
                                    <div class="col-sm-7">
                                        <input type="text" value="" id="price" min="0">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"> <span style="color: red;">*</span>单位：</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" id="unit" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"> <span style="color: red;">*</span>商品展示图：</label>
                                    <div class="col-sm-2">
                                        <div class="upload_box" id="thumbnail" data-toggle="modal" data-target="#thumbnailModal">+</div>
                                    </div>
                                    <div class="col-sm-5">
                                        <img id="thumbnailUrl" src="">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"> <span style="color: red;">*</span>简介：</label>
                                    <div class="col-sm-7">
                                        <textarea name="desc" class="form-control" id="desc" rows="5"></textarea>
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

<upload-img-modal id="thumbnailModal" title="商品展示图" img="thumbnailUrl"></upload-img-modal>