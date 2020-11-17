<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<script type="text/javascript">
    Vue.component('jump-number', {
        props : [ 'value', 'is_percent', 'className' ],
        template : '#jump-number',
        computed : {
            jump_number : function() {
                if (this.is_percent == 1) {
                    numberJump(this.guid, this.value * 100, '%', '2');
                } else {
                    numberJump(this.guid, this.value);
                }
                return 0;
            },
            guid : function() {
                var guid = new GUID();
                return guid.newGUID();
            }
        }
    })

    function numberJump(elementId, target, suffix, decimal) {
        if (!$('#' + elementId).attr('data-src')) {
            $('#' + elementId).attr('data-src', 0);
        }

        var src = $('#' + elementId).attr('data-src');
        if (src == target) {
            return;
        }

        var options = {
            useEasing : true,
            useGrouping : true,
            separator : ',',
            decimal : '.',
            suffix : suffix || ''
        };
        var demo = new CountUp(elementId, src, target, decimal || 0, 4, options);
        demo.start();

        $('#' + elementId).attr('data-src', target);
    }
</script>


<template id='jump-number'> <span :class='className' :id='guid' :is_percent='is_percent'>{{jump_number}}</span> </template>