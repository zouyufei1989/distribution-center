<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="modal inmodal fade" id="packagesModal" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-body">
                <h3>{{name}}已购项目列表</h3>
                <div class="wrapper animated fadeInRight">
                    <div class="row">
                        <div class="col-lg-12">
                            <table class="table table-striped">
                                <thead>
                                <tr class="font-bold">
                                    <th>项目名称</th>
                                    <th>项目编号</th>
                                    <th>项目次数</th>
                                    <th>已使用次数</th>
                                    <th>剩余次数</th>
                                    <th>剩余时间</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr v-for="p in packages">
                                    <td class="text-info">{{p.goodsName}}</td>
                                    <td>{{p.goodsSerialNumber}}</td>
                                    <td>{{p.items[0].cnt}}</td>
                                    <td>{{p.items[0].cntUsed}}</td>
                                    <td>{{p.items[0].cnt - p.items[0].cntUsed}}</td>
                                    <td>{{formatCreateDate(p.createDate)}}</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<upload-img-modal id="urlModal" title="banner" img="img_url"></upload-img-modal>