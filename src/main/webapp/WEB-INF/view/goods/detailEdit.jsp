<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="modal inmodal fade" id="detailEditModal" role="dialog" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-body">
                <h3>商品详情</h3>
                <div class="wrapper animated fadeInRight">
                    <div class="row">
                        <div class="col-lg-12">
                            <form id="" class="form-horizontal ">
                                <div class="form-group">
                                    <div class="col-sm-12">
                                        <div id="detailEditor"></div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-12 text-center">
                                        <button type="button" id="btn_saveDetail" data-style="zoom-in" class="ladda-button btn btn-w-m btn-primary btn-update-footer" onclick="saveDetail()">保存</button>
                                        <button type="button" class="btn btn-w-m btn-default btn-update-footer" onclick="cancelEdit()">取消</button>
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