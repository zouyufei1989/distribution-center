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
            <package-combo async="true" id="packageToPurchase" :timestamp="timestamp" must_choose_one="false" :customer_group_id="customerInfo.customerGroupId"></package-combo>
        </div>
        <div class="col-sm-1">
            <button class="btn btn-link" style="padding-top: 7px" type="button" @click="refreshPackageCombo">
                <i class="fa fa-refresh"></i>
            </button>
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
            <order-combo async="true" id="orderToConsume" must_choose_one="false" type="2" :customer_group_id="customerInfo.customerGroupId" :timestamp="timestamp"></order-combo>
        </div>
        <div class="col-sm-1">
            <button class="btn btn-outline btn-success" type="button" style="padding: 5px" @click="confirmConsumeRequest" v-if="consumeInfo.orderId && !isNaN(consumeInfo.orderId)">添加消费</button>
            <button class="btn btn-outline btn-success" type="button" style="padding: 5px"  v-else disabled>添加消费</button>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label"> 消费次数：</label>
        <div class="col-sm-7">
            <input id="orderToConsumeCnt" v-model="consumeInfo.cnt" class="form-control" min="0" required>
        </div>
    </div>
</form>