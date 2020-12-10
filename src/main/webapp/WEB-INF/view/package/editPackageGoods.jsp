<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="modal inmodal fade" id="packageGoodsModal" role="dialog" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <h3>设置套餐包含的商品</h3>
                <div class="wrapper animated fadeInRight wrapper-content">
                    <div class="row">
                        <div class="col-lg-12">
                            <select multiple="multiple" id="packages" class="form-control" style="height: 350px;width:100%">
                                <option v-for="item in goods" :value="item.items[0].id">{{item.name}}</option>
                            </select>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 15px">
                        <div class="col-lg-12 text-center ">
                            <button type="button" data-style="zoom-in" class="ladda-button btn btn-w-m btn-primary btn-update-footer" onclick="savePackage()">保存</button>
                            <button type="button" class="btn btn-w-m btn-default btn-update-footer" onclick="cancelEditPackage()">取消</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>