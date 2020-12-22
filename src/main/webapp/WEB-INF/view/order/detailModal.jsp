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
                                <div class="form-group" v-for="item in orderItems">
                                    <label class="col-sm-3 control-label">{{item.goodsTagName}}</label>
                                    <label class="col-sm-3 control-label">{{item.goodsName}}({{(item.goodsPrice/100).toFixed(2)}}/{{item.goodsUnit}}) * {{item.cnt}}</label>
                                    <label class="col-sm-3 control-label">¥ {{(item.goodsPrice*item.cnt/100).toFixed(2)}}</label>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
