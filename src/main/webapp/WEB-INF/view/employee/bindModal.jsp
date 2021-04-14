<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="modal inmodal fade" id="bindModal" role="dialog" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-body">
                <h3>
                    <span v-if="type=='bind'">分配股东</span>
                    <span v-else>迁移股东</span>
                </h3>
                <div class="wrapper animated fadeInRight">
                    <div class="row">
                        <div class="col-lg-12" id="div_customerInfo">
                            <form id="" class="form-horizontal" style="margin-bottom: 40px">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"> 客户姓名：</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" v-model="employee.name" disabled>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">所属门店：</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" v-model="employee.groupName" disabled>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"> 手机号：</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" v-model="employee.phone" disabled>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"> 身份证：</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" v-model="employee.identifyNo" disabled>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"> 当前状态 ：</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" v-model="employee.statusName" disabled>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6" v-show="type=='bind'">
                            <h4>选择要绑定的股东</h4>
                            <div class="shareholder_list">
                                <table class="table table-striped table-hover">
                                    <tr>
                                        <td colspan="3">
                                            <form role="form" class="form-inline" style="display: flex;justify-content: space-between">
                                                <div class="form-group">
                                                    <button class="btn btn-link" style="padding-top: 7px" type="button" @click="clearSearchParam">
                                                        全部
                                                    </button>
                                                </div>
                                                <div class="form-group">
                                                    <input type="text" class="form-control" v-model="searchParam.phone" placeholder="请输入姓名或手机号">
                                                </div>
                                                <div class="form-group">
                                                    <bonus-plan-combo must_choose_one="false" :group_id="employee.groupId" id="bonusPlanId" style="width:100px"></bonus-plan-combo>
                                                </div>
                                            </form>
                                        </td>
                                    </tr>
                                    <tr v-for="(item,i) in availableShareHolders" @click="choose(i)" >
                                        <td> {{item.name}}</td>
                                        <td> {{item.phone}}</td>
                                        <td> {{item.customerGroup.bonusPlanName}}</td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                        <div :class="{'col-md-6': type=='bind', 'col-md-7': type=='transfer','col-md-offset-3':type=='transfer'}">
                            <h4 class="text-info">已绑定的股东</h4>
                            <div :class="{'shareholder_list': type=='bind'}">
                                <table class="table table-striped table-hover">
                                    <tr>
                                        <td colspan="3">
                                            <form role="form" class="form-inline" style="display: flex;justify-content: space-between">
                                                <div class="form-group">
                                                    <i class="fa fa-info-circle text-info" style="margin-right: 10px"></i><label>当前累计绑定个{{myShareHolders.length}}股东</label>
                                                </div>
                                            </form>
                                        </td>
                                    </tr>
                                    <tr v-for="(item,i) in myShareHolders" v-show="!item.choosed"  @click="revert(i)">
                                        <td> {{item.name}}</td>
                                        <td> {{item.phone}}</td>
                                        <td> {{item.customerGroup.bonusPlanName}}</td>
                                    </tr>
                                    <tr v-for="(item,i) in myShareHolders" v-show="item.choosed" class="text-choosed" @click="revert(i)">
                                        <td> {{item.name}}</td>
                                        <td> {{item.phone}}</td>
                                        <td> {{item.customerGroup.bonusPlanName}}</td>
                                    </tr>
                                </table>
                            </div>
                        </div>

                        <div class="col-lg-12" v-if="type=='transfer'">
                            <form class="form-horizontal" style="margin-bottom: 40px">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"> 迁移至 ：</label>
                                    <div class="col-sm-7">
                                        <employee-combo id="transferId" must_choose_one="true" :group_id="employee.groupId" :exclude="employee.id"></employee-combo>
                                    </div>
                                </div>
                            </form>
                        </div>

                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-tool btn-default" type="button" data-dismiss="modal">取&nbsp;&nbsp;消</button>
                <button class="btn btn-tool btn-primary" type="button" @click="bindNew($event)" v-if="type=='bind'">确&nbsp;&nbsp;定</button>
                <button class="btn btn-tool btn-primary" type="button" @click="transfer($event)" v-else>确&nbsp;&nbsp;定</button>
            </div>
        </div>
    </div>
</div>
