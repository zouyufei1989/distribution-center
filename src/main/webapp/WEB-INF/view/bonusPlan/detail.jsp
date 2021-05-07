<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="modal inmodal fade" id="detailModal" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-body">
                <h3>积分方案详情</h3>
                <div class="wrapper animated fadeInRight">
                    <div class="row">
                        <div class="col-lg-12">
                            <form class="form-horizontal ">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span style="color: red;"></span>所属门店:</label>
                                    <div class="col-sm-7">
                                        <group-combo id="groupId4Detail"></group-combo>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"> <span style="color: red;"></span>积分编号：</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" v-model="item.serialNumber">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"> <span style="color: red;"></span>积分方案名称：</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" v-model="item.name">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"> <span style="color: red;"></span>设置积分比例：</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" v-model="item.bonusRate4Show">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"> <span style="color: red;"></span>首次是否返现：</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" v-model="item.cashback">
                                    </div>
                                </div>
                                <div class="form-group" id="div_cashback" v-show="item.cashback=='是'">
                                    <label class="col-sm-3 control-label"> <span style="color: red;"></span>返现金额：</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" value="" v-model="item.cashbackAmount" min="0">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span style="color: red;"></span>状态:</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" v-model="item.statusName">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span style="color: red;"></span>备注:</label>
                                    <div class="col-sm-7">
                                        <textarea class="form-control" id="desc" rows="5" v-model="item.desc" maxlength="200"></textarea>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <hr>
                <h3>修改记录</h3>
                <table class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th>修改时间</th>
                        <th>操作者</th>
                        <th>原编号</th>
                        <th>改后编号</th>
                        <th>原名称</th>
                        <th>改后名称</th>
                        <th>原比例</th>
                        <th>改后比例</th>
                        <th>原状态</th>
                        <th>改后状态</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr class="i-checks" v-for="item in histories">
                        <td>{{item.updateDate}}</td>
                        <td>{{item.updater}}</td>
                        <td>{{item.serialNumber1}}</td>
                        <td>{{item.serialNumber2}}</td>
                        <td>{{item.name1}}</td>
                        <td>{{item.name2}}</td>
                        <td>{{item.bonusRate1}}</td>
                        <td>{{item.bonusRate2}}</td>
                        <td>{{item.status1}}</td>
                        <td>{{item.status2}}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
