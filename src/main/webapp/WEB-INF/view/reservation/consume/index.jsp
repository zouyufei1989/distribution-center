<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="modal inmodal fade" id="consumeModal" role="dialog" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-body">
                <h3>顾客到店消费</h3>
                <div class="wrapper animated fadeInRight">
                    <%--客户预约信息--%>
                    <%@ include file="reservationInfo.jsp" %>
                    <div class="row" id="div_actions">
                        <%--追加消费--%>
                        <div class="col-md-12 text-center" v-show="additionalConsumeFlag==false">
                            <hr/>
                            <span class="addPeriod" @click="additionalConsume()">  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+追加消费&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>
                        </div>
                        <div v-show="additionalConsumeFlag">
                            <h3 class="text-warning">其他追加消费</h3>
                            <div class="col-md-12">
                                <form id="" class="form-horizontal">
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label"> 消费类型：</label>
                                        <div class="col-sm-7">
                                            <label label4Radio> <input type="radio" name="consumeType" :checked="action=='buySingle'" @click="chooseAction('buySingle')">单品 </label> <label label4Radio> <input type="radio" name="consumeType" :checked="action=='consumePackage'" @click="chooseAction('consumePackage')">项目消费 </label> <label label4Radio> <input type="radio" name="consumeType" :checked="action=='bugPackage'" @click="chooseAction('buyPackage')">购买项目 </label>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="col-md-12">
                                <%@ include file="action.jsp" %>
                            </div>
                        </div>
                        <%--累计消费--%>
                        <div class="row" v-show="action!='buyPackage'">
                            <div class="col-md-12">
                                <h3 class="text-warning">本次累计消费</h3>
                                <table class="table table-bordered table-striped text-center">
                                    <tr>
                                        <td>{{reservation.goodsName}}</td>
                                        <td>{{reservation.cnt}} 次</td>
                                        <td></td>
                                    </tr>
                                    <tr v-for="(item,i) in consumeRequests">
                                        <td>{{item.goodsName}}</td>
                                        <td>{{item.cnt}} 次</td>
                                        <td><button class="btn btn-link text-danger" type="button" @click="removePackageChoosed(i)">删除</button></td>
                                    </tr>
                                    <tr v-for="item in goodsChoosed">
                                        <td>{{item.goodsName}}</td>
                                        <td>{{item.cnt}} 次</td>
                                        <td><button class="btn btn-link text-danger" type="button" @click="removeGoodsChoosed(item.id)">删除</button></td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                        <%--支付方式--%>
                        <h3 class="text-warning" v-show="additionalConsumeFlag && action!='buyPackage'">消费金额</h3>
                        <div class="row" v-show="additionalConsumeFlag">
                            <div class="col-md-12">
                                <form class="form-horizontal">
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label"> 实收金额：</label>
                                        <div class="col-sm-7">
                                            <input v-model="purchaseInfo.actuallyMoney" id="actuallyMoney" class="form-control" min="0" required>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label"> 使用余额：</label>
                                        <div class="col-sm-3">
                                            <label label4Radio> <input type="radio" :checked="purchaseInfo.payMoney==0" @click="changePayType('payMoney',0)" name="pay_money" id="single">否 </label> <label label4Radio> <input type="radio" :checked="purchaseInfo.payMoney==1" @click="changePayType('payMoney',1)" name="pay_money">是 </label>
                                        </div>
                                        <div class="col-sm-6" v-show="purchaseInfo.payMoney==1">
                                            <label label4Radio style="color:#d2d2d2"> 当前可用余额: {{customerInfo.availableMoney}}</label> <label label4Radio style="color:#d2d2d2" v-show="moneyEnough"> 当前余额不足
                                            <button type="button" style="padding:1px 10px" class="btn btn-primary btn-xs" @click="goRecharge()" v-show="customerInfo.customerType==2">去充值</button>
                                        </label>
                                        </div>
                                    </div>
                                    <div class="form-group" v-show="customerInfo.customerType == 2">
                                        <label class="col-sm-3 control-label"> 使用积分：</label>
                                        <div class="col-sm-3">
                                            <label label4Radio> <input type="radio" :checked="purchaseInfo.payBonus==0 " @click="changePayType('payBonus',0)" name="pay_bonus">否 </label> <label label4Radio> <input type="radio" :checked="purchaseInfo.payBonus==1 " @click="changePayType('payBonus',1)" name="pay_bonus">是 </label>
                                        </div>
                                        <div class="col-sm-6" v-show="purchaseInfo.payBonus==1">
                                            <label label4Radio style="color:#d2d2d2"> 当前可用积分: {{customerInfo.availableBonus}}</label>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label"> 非余额（线下支付）：</label>
                                        <div class="col-sm-3">
                                            <label label4Radio> <input type="radio" :checked="purchaseInfo.payOffline==0" @click="changePayType('payOffline',0)" name="pay_offline">否 </label> <label label4Radio> <input type="radio" :checked="purchaseInfo.payOffline==1" @click="changePayType('payOffline',1)" name="pay_offline">是 </label>
                                        </div>
                                        <div class="col-sm-2" v-show="purchaseInfo.payOffline==1">
                                            <input class="form-control" min="0" :value="extraMoneyOffline" disabled>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <%--支付按钮--%>
                        <div class="row">
                            <div class="col-md-12">
                                <form class="form-horizontal">
                                    <div class="form-group">
                                        <div class="col-sm-12 text-right" style="margin-top: 20px">
                                            <button type="button" class="btn btn-w-m btn-default btn-update-footer" @click="cancel()">取消</button>
                                            <button type="button" data-style="zoom-in" class="ladda-button btn btn-w-m btn-primary btn-update-footer" v-if="action=='buyPackage'" @click="purchase($event)">购买</button>
                                            <button type="button" data-style="zoom-in" class="ladda-button btn btn-w-m btn-primary btn-update-footer" v-else @click="consumeReservation($event)">提交</button>
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
</div>
