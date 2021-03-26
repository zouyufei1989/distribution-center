<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="modal inmodal fade" id="consumeModal" role="dialog" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-body">
                <h3>顾客到店消费</h3>
                <div class="wrapper animated fadeInRight">
                    <%@ include file="reservationInfo.jsp" %>
                    <div class="row" id="div_actions">
                        <hr/>
                        <div class="col-md-12 text-center" v-show="additionalConsumeFlag==false">
                            <span class="addPeriod" @click="additionalConsume()">  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+追加消费&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>
                        </div>
                        <div v-show="additionalConsumeFlag">
                            <div class="col-md-12">
                                <form id="" class="form-horizontal">
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label"> 消费类型：</label>
                                        <div class="col-sm-7">
                                            <label label4Radio> <input type="radio" name="consumeType" :checked="action=='buySingle'" @click="chooseAction('buySingle')">单品 </label>
                                            <label label4Radio> <input type="radio" name="consumeType" :checked="action=='consumePackage'" @click="chooseAction('consumePackage')">项目消费 </label>
                                            <label label4Radio> <input type="radio" name="consumeType" :checked="action=='bugPackage'" @click="chooseAction('buyPackage')">购买项目 </label>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="col-md-12">
                                <%@ include file="action.jsp" %>
                            </div>
                        </div>
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
