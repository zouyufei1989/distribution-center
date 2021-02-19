<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="modal inmodal fade" id="coverImgModal" role="dialog" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <h3>详情图片列表</h3>
                <div class="wrapper animated fadeInRight">
                    <div class="row">
                        <form id="mainForm" class="form-horizontal">
                            <div class="form-group" v-for="(img,i) in detailImgList">
                                <div class="col-sm-offset-3 col-sm-6">
                                    <img :src="img">
                                </div>
                                <div class="col-sm-sm-1">
                                    <i class="fa fa-times" @click="remove(i)"></i>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-6">
                                    <span class="addPeriod" data-toggle="modal" data-target="#uploadDetailImgModal"> 上传详情图片 + </span>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-12 text-center">
                                    <button type="button" data-style="zoom-in" class="ladda-button btn btn-w-m btn-primary btn-update-footer " @click="save($event)">保存</button>
                                    <button type="button" class="btn btn-w-m btn-default btn-update-footer " @click="hide()">取消</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="modal inmodal fade" id="uploadDetailImgModal" role="dialog" aria-hidden="true" data-backdrop="static" style="z-index: 2300 !important;">
        <div class="modal-dialog" >
            <div class="modal-content">
                <div class="modal-body" >
                    <button type="button" class="close" @click="hideUploadModal()">
                        <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                    </button>
                    <h5>上传详情图片</h5>
                    <form class="form-horizontal" enctype="multipart/form-data" id="uploadDetailImgForm">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">详情图片:</label>
                            <div class="col-sm-8">
                                <div class="input-group">
                                    <input type="file" class="form-control" name="file" id="detailImg">
                                    <input type="hidden" class="form-control" value="686" id="width" name="width">
                                    <input type="hidden" class="form-control" value="343" id="height" name="height">
                                    <span class="input-group-btn">
                                        <button type="button" data-style="zoom-in" class="ladda-button btn btn-w-m btn-primary btn-update-footer" @click="uploadImg($event)">上&nbsp;&nbsp;&nbsp;&nbsp;传</button>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>