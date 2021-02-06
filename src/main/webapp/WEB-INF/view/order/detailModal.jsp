<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="modal inmodal fade" id="detailModal" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-body">
                <h3>消费详情</h3>
                <div class="wrapper animated fadeInRight">
                    <div class="row">
                        <div class="col-lg-12">
                            <form id="mainForm" class="form-horizontal ">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">客户姓名:</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" v-model="order.customerName" disabled>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">客户编号:</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" v-model="order.serialNumber" disabled>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">联系电话:</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" v-model="order.phone" disabled>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">所属股东:</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" v-model="order.parentName" disabled>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">消费时间:</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" v-model="order.createDate" disabled>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">消费类型:</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" v-model="order.goodsTypeName" disabled>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">应收金额:</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" v-model="order.sumMoney" disabled>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">实收金额:</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" v-model="order.actuallyMoney" disabled>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">支付金额:</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" v-model="order.payTypeName" disabled>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <hr>
                <h4>消费明细</h4>
                <div class="wrapper animated fadeInRight">
                    <div class="row">
                        <div class="col-lg-12">
                            <form id="" class="form-horizontal ">
                                <div class="form-group" v-for="(item,i) in orderItems">
                                    <label class="col-sm-3 control-label">{{item.goodsTagName}}</label>
                                    <label class="col-sm-3 control-label">{{item.goodsName}}({{moneyFormatter(item.goodsPrice)}}/{{item.goodsUnit}}) * {{item.cnt}}</label>
                                    <label class="col-sm-3 control-label">¥ {{moneyFormatter(item.goodsPrice*item.cnt)}}</label>
                                    <button v-show="refund && order.status!=5 && i==orderItems.length-1" style="margin-top: 5px;margin-left: 85px" type="button" data-style="zoom-in" class="btn btn-primary btn-xs" @click="refundMoney()">退款</button>
                                    <button v-show="refund && order.status==5 && i==orderItems.length-1" style="margin-top: 5px;margin-left: 85px" type="button" data-style="zoom-in" class="btn btn-primary btn-xs" disabled>已退款</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <hr v-show="refund">
                <h4 v-show="refund">积分记录</h4>
                <div class="wrapper animated fadeInRight" v-show="refund">
                    <div class="row">
                        <div class="col-lg-12">
                            <form id="" class="form-horizontal ">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">所属股东:</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" v-model="refundParams.parentCustomerName" disabled>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">积分数量:</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" v-model="parentAvailableBonus" disabled>
                                    </div>
                                    <div class="col-sm-2">
                                        <button type="button" style="margin-top: 5px" data-style="zoom-in" class="btn btn-primary btn-xs" @click="refundBonus()">扣除</button>
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
