<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="row" v-show="action=='buySingle'">
    <div class="col-lg-offset-2 col-lg-8">
        <form id="mainForm" class="form-horizontal ">
            <div id="goodsTree"></div>
        </form>
    </div>
</div>
<div class="row" v-show="action=='buySingle'">
    <div class="col-md-offset-1 col-md-10">
        <div class="alert alert-info" style="padding-top: 10px;padding-bottom: 10px;margin-top: 10px;">
            <i class="fa fa-info-circle"></i> 当前已选择{{cnt}}项消费内容，应收{{sumPrice}}元
        </div>
    </div>
</div>
<form class="form-horizontal" v-show="action=='buyPackage'">
    <div class="form-group">
        <label class="col-sm-3 control-label"> 购买项目：</label>
        <div class="col-sm-7">
            <package-combo id="packageToPurchase" must_choose_one="false" :customer_group_id="customerInfo.customerGroupId"></package-combo>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label"> 购买次数：</label>
        <div class="col-sm-7">
            <input id="purchaseCnt" class="form-control" min="1" required>
        </div>
    </div>
</form>
<form class="form-horizontal" v-show="action=='consumePackage'">
    <div class="form-group">
        <label class="col-sm-3 control-label"> 消费项目：</label>
        <div class="col-sm-7">
            <input id="" class="form-control" min="0" required>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label"> 消费次数：</label>
        <div class="col-sm-7">
            <input id="useCnt" class="form-control" min="0" required>
        </div>
    </div>
</form>
<form class="form-horizontal" v-show="action=='buySingle' || action=='buyPackage'">
    <div class="form-group">
        <label class="col-sm-3 control-label"> 实收金额：</label>
        <div class="col-sm-7">
            <input v-model="purchaseInfo.actuallyMoney" id="actuallyMoney" class="form-control" min="0" required>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label"> 使用余额：</label>
        <div class="col-sm-3">
            <label label4Radio> <input type="radio" :checked="purchaseInfo.payMoney==0" @click="changePayType('payMoney',0)" name="pay_money" id="single">否 </label>
            <label label4Radio> <input type="radio" :checked="purchaseInfo.payMoney==1" @click="changePayType('payMoney',1)" name="pay_money">是 </label>
        </div>
        <div class="col-sm-6" v-show="purchaseInfo.payMoney==1">
            <label label4Radio style="color:#d2d2d2"> 当前可用余额: {{customerInfo.availableMoney}}</label>
            <label label4Radio style="color:#d2d2d2" v-show="moneyEnough"> 当前余额不足
                <button type="button" style="padding:1px 10px" class="btn btn-primary btn-xs">去充值</button>
            </label>
        </div>
    </div>
    <div class="form-group" v-show="customerInfo.customerType == 2">
        <label class="col-sm-3 control-label"> 使用积分：</label>
        <div class="col-sm-3">
            <label label4Radio> <input type="radio" :checked="purchaseInfo.payBonus==0" @click="changePayType('payBonus',0)" name="pay_bonus">否 </label>
            <label label4Radio> <input type="radio" :checked="purchaseInfo.payBonus==1" @click="changePayType('payBonus',1)" name="pay_bonus">是 </label>
        </div>
        <div class="col-sm-6" v-show="purchaseInfo.payBonus==1">
            <label label4Radio style="color:#d2d2d2"> 当前可用积分: {{customerInfo.availableBonus}}</label>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label"> 非余额（线下支付）：</label>
        <div class="col-sm-3">
            <label label4Radio> <input type="radio" :checked="purchaseInfo.payOffline==0" @click="changePayType('payOffline',0)" name="pay_offline">否 </label>
            <label label4Radio> <input type="radio" :checked="purchaseInfo.payOffline==1" @click="changePayType('payOffline',1)" name="pay_offline">是 </label>
        </div>
        <div class="col-sm-2" v-show="purchaseInfo.payOffline==1">
<%--            <input v-model="purchaseInfo.moneyOffline" id="moneyOffline" class="form-control" min="0" :value="extraMoneyOffline" required :disabled="extraMoneyOffline<=0">--%>
                <input class="form-control" min="0" :value="extraMoneyOffline" disabled>
        </div>
    </div>
</form>
<form class="form-horizontal">
    <div class="form-group">
        <div class="col-sm-12 text-right" style="margin-top: 20px">
            <button type="button" class="btn btn-w-m btn-default btn-update-footer" @click="cancel()">取消</button>
            <button type="button" data-style="zoom-in" class="ladda-button btn btn-w-m btn-primary btn-update-footer" v-show="action=='buyPackage'" @click="purchase()">提交</button>
            <button type="button" data-style="zoom-in" class="ladda-button btn btn-w-m btn-primary btn-update-footer" v-show="action=='buySingle'" @click="purchaseAndConsume()">提交</button>
            <button type="button" data-style="zoom-in" class="ladda-button btn btn-w-m btn-primary btn-update-footer" v-show="action=='consumePackage'" @click="consume()">提交</button>
        </div>
    </div>
</form>