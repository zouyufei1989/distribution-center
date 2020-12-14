<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="modal inmodal fade" id="assignModal" role="dialog" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-body">
                <h3>发起活动赠送</h3>
                <div class="wrapper animated fadeInRight" id="div_assignActivity">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="alert alert-info" style="padding-top: 10px;padding-bottom: 10px;margin-top: 10px;">
                                <i class="fa fa-info-circle"></i> <span id="priceTip">{{assignTip}}</span>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            选择赠送目标
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-offset-2 col-md-8">
                            <div class="input-group">
                                <input class="form-control" v-model="nameOrPhone" placeholder="请输入姓名/手机号">
                                <span class="input-group-btn">
                                    <button style="padding: 7px" type="button" class="btn btn-primary" @click="queryCustomer">搜索</button>
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-offset-2 col-md-8">
                            <table class="table table-condensed table-hover">
                                <tbody>
                                <tr class="i-checks" v-for="item in shareHolders">
                                    <td><input type="checkbox" style="margin-top: 8px !important;" v-model="item.checked"></td>
                                    <td>{{item.name}}</td>
                                    <td>{{item.customerGroup.serialNumber}}</td>
                                    <td>{{item.phone}}</td>
                                    <td>
                                        <div class="input-group input-group-sm" style="float: right;width: 100px" v-show="item.checked">
                                            <span class="input-group-btn">
                                                <button class="btn btn-default" style="margin-right: 5px" type="button" @click="cntChange(-1,item.customerGroup.id)">-</button>
                                            </span>
                                            <input type="text" style="width:50px" class="form-control" v-model="item.cnt" digits="" min="0" >
                                            <span class="input-group-btn">
                                                <button class="btn btn-default" style="margin-left: 5px" type="button" @click="cntChange(1,item.customerGroup.id)">+</button>
                                            </span>
                                        </div>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-offset-2 col-md-8 text-right">
                            <button type="button" data-style="zoom-in" class="ladda-button btn btn-w-m btn-primary btn-update-footer" @click="assign($event)">赠送</button>
                            <button type="button" class="btn btn-w-m btn-default btn-update-footer" @click="cancel()">取消</button>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
