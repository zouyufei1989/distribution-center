<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="modal inmodal fade" id="packageGoodsModal" role="dialog" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <h3>设置套餐包含的商品</h3>
                <div class=" animated fadeInRight">
                    <div class="row" >
                        <div class="col-lg-offset-2 col-lg-8">
                            <form id="mainForm" class="form-horizontal ">
                                <div id="goodsTree"></div>
                            </form>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 15px">
                        <div class="col-lg-12 text-center ">
                            <button type="button" data-style="zoom-in" class="ladda-button btn btn-w-m btn-primary btn-update-footer" onclick="savePackage(this)">保存</button>
                            <button type="button" class="btn btn-w-m btn-default btn-update-footer" onclick="cancelEditPackage()">取消</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>