<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="modal inmodal fade" id="consumeModal" role="dialog" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-body">
                <h3>客户消费</h3>
                <div class="wrapper animated fadeInRight">
                    <%@ include file="customerInfo.jsp" %>
                    <div class="row">
                        <hr/>
                        <h4>选择消费项目</h4>
                        <div class="col-md-12">
                            <form id="" class="form-horizontal">
                                <div class="form-group" id="div_consumeType">
                                    <label class="col-sm-3 control-label"> 消费类型：</label>
                                    <div class="col-sm-7">
                                        <label> <input type="radio" name="consumeType" id="single">单品 </label>
                                        <label> <input type="radio" name="consumeType">项目消费 </label>
                                        <label> <input type="radio" name="consumeType">购买项目 </label>
                                    </div>
                                </div>
                            </form>
                            <%@ include file="single.jsp" %>

                        </div>
                    </div>
                    <div class="row text-right" style="margin-top: 50px">
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
