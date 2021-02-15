<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="modal inmodal fade" id="updateModal" role="dialog" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-body">
                <h3></h3>
                <div class="wrapper animated fadeInRight">
                    <div class="row">
                        <div class="col-lg-5">
                            <form id="mainForm" class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span style="color: red;">*</span>门店名称:</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" id="name" name="name" required maxlength="50"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"> <span style="color: red;">*</span>所在城市：</label>
                                    <div class="col-sm-7">
                                        <city-combo id="cityCode" must-choose-one="true" :city_code="cityCode" :city_name="cityName"></city-combo>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span style="color: red;"></span>辅助地址:</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" id="searchAddress" name="searchAddress" placeholder="输入地址搜索..."/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span style="color: red;">*</span>详细地址:</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" id="address" name="address" required maxlength="255"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span style="color: red;">*</span>经度:</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" id="lng" name="lng" required onkeyup="refreshStationMarker()"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span style="color: red;">*</span>纬度:</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" id="lat" name="lat" required onkeyup="refreshStationMarker()"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span style="color: red;">*</span>负责人:</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" id="ownerName" name="ownerName" required/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span style="color: red;">*</span>店铺电话:</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" id="ownerPhone" name="ownerPhone" required/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span style="color: red;"></span>店铺简介:</label>
                                    <div class="col-sm-7">
                                        <textarea name="desc" class="form-control" id="desc" rows="5" maxlength="500"></textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">
                                        <span style="color: red;"></span>缩略图:
                                        <br>
                                        <span style="color:darkgrey;font-size: smaller">(图片尺寸:宽112 * 高112)</span>
                                    </label>
                                    <div class="col-sm-2">
                                        <div class="upload_box" id="thumbnail" data-toggle="modal" data-target="#thumbnailModal">+</div>
                                    </div>
                                    <div class="col-sm-5">
                                        <img id="thumbnail_url" src="" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">
                                        <span style="color: red;"></span>详情封面图片:
                                        <br>
                                        <span style="color:darkgrey;font-size: smaller">(图片尺寸:宽686 * 高343)</span>
                                    </label>
                                    <div class="col-sm-2">
                                        <div class="upload_box" id="detailCoverImg" data-toggle="modal" data-target="#detailCoverImgModal">+</div>
                                    </div>
                                    <div class="col-sm-5">
                                        <img id="detailCoverImg_url" src="">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">
                                        <span style="color: red;"></span>视频封面图片:
                                        <br>
                                        <span style="color:darkgrey;font-size: smaller">(图片尺寸:宽686 * 高343)</span>
                                    </label>
                                    <div class="col-sm-2">
                                        <div class="upload_box" id="videoCoverImg" data-toggle="modal" data-target="#videoCoverImgModal">+</div>
                                    </div>
                                    <div class="col-sm-5">
                                        <img id="videoCoverImg_url" src="">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span style="color: red;">*</span>营业时间:</label>
                                    <div class="col-sm-2">
                                        <select id="sel_start_weekday" class="select2_demo_3 form-control m-b" style="width: 100% !important;"> </select>
                                    </div>
                                    <div class="col-sm-1"><label class="col-sm-3 control-label">至</label></div>
                                    <div class="col-sm-2">
                                        <select id="sel_end_weekday" class="select2_demo_3 form-control m-b" style="width: 100% !important;"> </select>
                                    </div>
                                    <div class="col-sm-offset-3 col-sm-2">
                                        <select id="sel_start_time" class="select2_demo_3 form-control m-b" style="width: 100% !important;"> </select>
                                    </div>
                                    <div class="col-sm-1"><label class="col-sm-3 control-label">至</label></div>
                                    <div class="col-sm-2">
                                        <select id="sel_end_time" class="select2_demo_3 form-control m-b" style="width: 100% !important;"> </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span style="color: red;">*</span>门店排序:</label>
                                    <div class="col-sm-7">
                                        <input type="text" value="" name="index" id="index" min="0" required >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span style="color: red;">*</span>状态:</label>
                                    <div class="col-sm-7">
                                        <status-combo id="status" must-choose-one="true"></status-combo>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="col-lg-7">
                            <div class="row">
                                <div class="col-sm-12">
                                    <div id="mapContainer" style="width: 100%; height: 822px;"></div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-12 text-center" style="margin-top: 45px">
                                    <%@ include file="../template_save_update_btn.jsp" %>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<upload-img-modal id="thumbnailModal" title="缩略图" img="thumbnail_url" width="112" height="112"></upload-img-modal>
<upload-img-modal id="detailCoverImgModal" title="详情封面" img="detailCoverImg_url" width="686" height="343"></upload-img-modal>
<upload-img-modal id="videoCoverImgModal" title="视频封面" img="videoCoverImg_url" width="686" height="343"></upload-img-modal>