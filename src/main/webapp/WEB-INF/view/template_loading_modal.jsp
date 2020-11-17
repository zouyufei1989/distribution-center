<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<script type="text/javascript">
    function loadingStart(callback) {
        if (typeof callback === 'function') {
            bindModalShow('loadingModal', callback, 0);
        }
        $('#loadingModal').modal('show');
    }

    function loadingEnd(callback) {
        if (typeof callback === 'function') {
            bindModalHide('loadingModal', callback, 0);
        }
        $('#loadingModal').modal('hide');
    }
</script>

<div class="modal inmodal fade" role="dialog" aria-hidden="true" data-backdrop="static" id="loadingModal" style="z-index: 9998 !important;background: gray;opacity: 0.5">
    <div class="modal-dialog" style="margin-top: 20% !important; opacity: 1 !important;">
        <div class="sk-spinner sk-spinner-three-bounce" style="z-index:9999 !important;opacity: 1 !important;">
            <div class="sk-bounce1" style="opacity: 1 !important;"></div>
            <div class="sk-bounce2" style="opacity: 1 !important;"></div>
            <div class="sk-bounce3" style="opacity: 1 !important;"></div>
        </div>
    </div>
</div>