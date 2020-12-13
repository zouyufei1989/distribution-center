<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="row">
    <div class="col-lg-12" id="div_customerInfo">
        <form id="" class="form-horizontal">
            <div class="form-group">
                <label class="col-sm-3 control-label"> 客户姓名：</label>
                <div class="col-sm-7">
                    <input type="text" class="form-control" v-model="name" disabled>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">客户编号：</label>
                <div class="col-sm-7">
                    <input type="text" class="form-control" v-model="serialNumber" disabled>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label"> 联系电话：</label>
                <div class="col-sm-7">
                    <input type="text" class="form-control" v-model="phone" disabled>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label"> 所属股东：</label>
                <div class="col-sm-7">
                    <input type="text" class="form-control" v-model="parentName" disabled>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label"> 创建时间：</label>
                <div class="col-sm-7">
                    <input type="text" class="form-control" v-model="createDate" disabled>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label"> 账户余额：</label>
                <div class="col-sm-7">
                    <input type="text" class="form-control" v-model="availableMoney" disabled>
                </div>
                <div class="col-sm-1">
                    <button class="btn btn-link" style="padding-top: 7px" type="button" @click="refreshCustomerInfo">
                        <i class="fa fa-refresh"></i>
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>