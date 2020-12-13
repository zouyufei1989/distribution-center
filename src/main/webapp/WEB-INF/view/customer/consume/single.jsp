<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="row" name="single">
    <div class="col-lg-offset-2 col-lg-8">
        <form id="mainForm" class="form-horizontal ">
            <div id="goodsTree"></div>
        </form>
    </div>
</div>
<div class="row" name="single">
    <div class="col-md-offset-1 col-md-10">
        <div class="alert alert-info" style="padding-top: 10px;padding-bottom: 10px;margin-top: 10px;">
            <i class="fa fa-info-circle"></i> <span id="consumeTip">当前已选择0项消费内容，应收0元</span>
        </div>
    </div>
</div>
<form id="form_singlePay" class="form-horizontal">
    <div class="form-group" >
        <label class="col-sm-3 control-label"> 实收金额：</label>
        <div class="col-sm-7">
            <input id="paidMoney" class="form-control" min="0" required>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label"> 使用余额：</label>
        <div class="col-sm-7">
            <label> <input type="radio" name="pay_money" id="single">否 </label>
            <label> <input type="radio" name="pay_money">是 </label>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label"> 使用积分：</label>
        <div class="col-sm-7">
            <label> <input type="radio" name="pay_bonus" >否 </label>
            <label> <input type="radio" name="pay_bonus">是 </label>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label"> 非余额（线下支付）：</label>
        <div class="col-sm-7">
            <label> <input type="radio" name="pay_offline" >否 </label>
            <label> <input type="radio" name="pay_offline">是 </label>
        </div>
    </div>
</form>