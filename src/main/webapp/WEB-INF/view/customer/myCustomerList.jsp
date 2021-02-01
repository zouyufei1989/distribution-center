<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="modal inmodal fade" id="myCustomerModal" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-body">
                <h3><span id="sp_parentName"></span>的客源</h3>
                <div class="wrapper animated fadeInRight">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="jqGrid_wrapper" style="margin-top: 10px;" id="div_list">
                                <table id="table_list_mycustomer"></table>
                                <div id="pager_list_mycustomer"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<upload-img-modal id="urlModal" title="banner" img="img_url"></upload-img-modal>