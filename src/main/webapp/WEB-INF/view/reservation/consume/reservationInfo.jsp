<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div id="div_reservationInfo">
    <div class="well">
        <div class="row text-primary">
            <div class="col-md-4">顾客姓名：{{item.customerName}}</div>
            <div class="col-md-4">手机号：{{item.phone}}</div>
        </div>
        <div class="row text-primary" style="margin-bottom: 12px;margin-top: 12px">
            <div class="col-md-4">预约门店：{{item.groupName}}</div>
            <div class="col-md-4">预约日期：{{item.date}}</div>
            <div class="col-md-4">预约时段：{{item.startTime + ' - ' + item.endTime}}</div>
        </div>
        <div class="row text-primary">
            <div class="col-md-4">预约活动/项目：{{item.goodsName}}</div>
        </div>
    </div>

    <h3 class="text-warning">请确认顾客已到店，并核实以下消费内容</h3>
    <div class="row">
        <div class="col-lg-12">
            <form id="" class="form-horizontal">
                <div class="form-group">
                    <label class="col-sm-3 control-label"> <span class="text-danger">*</span> 消费类型：</label>
                    <div class="col-sm-5">
                        <goods-type-combo id="reservationInfoGoodsType" data_exclude="1" :value="item.goodsTypeId"></goods-type-combo>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="text-danger">*</span> 消费项目：</label>
                    <div class="col-sm-5">
                        <order-combo id="reservationInfoOrderToUse" :customer_group_id="item.customerGroupId" timestamp="" :value="item.orderId" :type="item.goodsTypeId"></order-combo>
                        <span v-show="!item.orderAvailable" class="help-block m-b-none text-warning">预约项目[{{item.goodsName}}]次数已用光</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="text-danger">*</span> 消耗次数：</label>
                    <div class="col-sm-5">
                        <input id="reservationUseCnt" name="reservationUseCnt" class="form-control" min="0" required>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>