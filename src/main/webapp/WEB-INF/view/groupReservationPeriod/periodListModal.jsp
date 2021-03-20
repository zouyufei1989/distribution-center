<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="modal inmodal fade" id="periodListModal" role="dialog" aria-hidden="true" data-backdrop="static" style="z-index: 3050 !important;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <span class="pull-left">{{goodsName}}的预约时段</span>
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
            </div>
            <div class="modal-body">
                <div class=" animated fadeInRight">
                    <table class="table table-condensed table-hover table-bordered">
                        <thead>
                        <tr>
                            <th>开始时间</th>
                            <th>结束时间</th>
                            <th>可约数量</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr v-for="item in periods">
                            <td>{{item.startTime}}</td>
                            <td>{{item.endTime}}</td>
                            <td>{{item.cnt}}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
