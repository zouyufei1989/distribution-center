<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="modal inmodal fade" id="videoListModal" role="dialog" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <h3>介绍视频列表</h3>
                <div class="wrapper animated fadeInRight">
                    <div class="row">
                        <form id="mainForm" class="form-horizontal">
                            <div class="form-group" v-for="(url,i) in videoList">
                                <div class="col-sm-offset-3 col-sm-6">
                                    <figure>
                                        <iframe style="width:100%" :src="url+'?autoplay=false'" frameborder="0" allowfullscreen></iframe>
                                    </figure>
                                </div>
                                <div class="col-sm-sm-1">
                                    <i class="fa fa-times" @click="remove(i)"></i>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-6">
                                    <span class="addPeriod" data-toggle="modal" data-target="#uploadVideoModal"> 上传介绍视频 + </span>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-12 ">
                                    <button type="button" data-style="zoom-in" class="ladda-button btn btn-w-m btn-primary btn-update-footer pull-right" @click="save($event)">保存</button>
                                    <button type="button" class="btn btn-w-m btn-default btn-update-footer pull-right" @click="hide()">取消</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="modal inmodal fade" id="uploadVideoModal" role="dialog" aria-hidden="true" data-backdrop="static" style="z-index: 2300 !important;">
        <div class="modal-dialog" >
            <div class="modal-content">
                <div class="modal-body" >
                    <button type="button" class="close" @click="hideUploadModal()">
                        <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                    </button>
                    <h5>上传介绍视频</h5>
                    <form class="form-horizontal" enctype="multipart/form-data" id="uploadVideoForm">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">视频:</label>
                            <div class="col-sm-8">
                                <div class="input-group">
                                    <input type="file" class="form-control" name="file" id="video">
                                    <span class="input-group-btn">
                                        <button type="button" data-style="zoom-in" class="ladda-button btn btn-w-m btn-primary btn-update-footer" @click="uploadVideo($event)">上&nbsp;&nbsp;&nbsp;&nbsp;传</button>
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