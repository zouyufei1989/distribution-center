function Alert(title, msg, type, successFuc) {
    setTimeout(function () {
        var msgType = type || "warning";

        swal({
            title: title,
            text: msg,
            type: msgType,
            confirmButtonText: "确定"
        }, function () {
            if (successFuc) {
                setTimeout(successFuc, 200);
            }
        });
    }, 300);
}

function Confirm(msg, confirmFuc) {
    swal({
        title: "",
        text: msg,
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        closeOnConfirm: false,
        closeOnCancel: true
    }, function (isConfirm) {
        if (isConfirm) {
            if (confirmFuc) {
                confirmFuc();
            }
            swal.close();
        }
        return isConfirm;
    });
}

// jQuery url get parameters function [获取URL的GET参数值]
// <code>
//      var GET = $.urlGet(); //获取URL的Get参数
//      var id = GET['id']; //取得id的值
// </code>
//  url get parameters
//  public
//  return array()
(function ($) {
    $.extend({
        urlGet: function () {
            var aGET = new Array();
            var aQuery = decodeURI(window.location.href).split("?");  //取得Get参数
            if (aQuery && aQuery.length > 1) {
                var aBuf = aQuery[1].split("&");
                for (var i = 0, iLoop = aBuf.length; i < iLoop; i++) {
                    var aTmp = aBuf[i].split("=");  //分离key与Value
                    aGET[aTmp[0]] = aTmp[1];
                }
            }
            return aGET;
        }
    })
})(jQuery);

// div或其它标签resize事件
(function ($, h, c) {
    var a = $([]), e = $.resize = $.extend($.resize, {}), i, k = "setTimeout", j = "resize", d = j
        + "-special-event", b = "delay", f = "throttleWindow";
    e[b] = 250;
    e[f] = true;
    $.event.special[j] = {
        setup: function () {
            if (!e[f] && this[k]) {
                return false
            }
            var l = $(this);
            a = a.add(l);
            $.data(this, d, {
                w: l.width(),
                h: l.height()
            });
            if (a.length === 1) {
                g()
            }
        },
        teardown: function () {
            if (!e[f] && this[k]) {
                return false
            }
            var l = $(this);
            a = a.not(l);
            l.removeData(d);
            if (!a.length) {
                clearTimeout(i)
            }
        },
        add: function (l) {
            if (!e[f] && this[k]) {
                return false
            }
            var n;

            function m(s, o, p) {
                var q = $(this), r = $.data(this, d);
                if (r === undefined) {
                    return;
                }
                r.w = o !== c ? o : q.width();
                r.h = p !== c ? p : q.height();
                n.apply(this, arguments)
            }

            if ($.isFunction(l)) {
                n = l;
                return m
            } else {
                n = l.handler;
                l.handler = m
            }
        }
    };

    function g() {
        i = h[k](function () {
            a.each(function () {
                var n = $(this), m = n.width(), l = n.height(), o = $.data(
                    this, d);
                if (m !== o.w || l !== o.h) {
                    n.trigger(j, [o.w = m, o.h = l])
                }
            });
            g()
        }, e[b])
    }
})(jQuery, this);


function pwdValidate(pwd) {

    var count = 0;
    var specialChar = 0;
    if (pwd.match(/([a-zA-z])+/)) {
        count++;
    }

    if (pwd.match(/([0-9])+/)) {
        count++;
    }

    if (pwd.match(/[^a-zA-Z0-9]+/)) {
        specialChar = 1;
    }

    if (count == 1) {
        Alert('', '请使用混合字符作为密码', 'error');
        return false;
    }

    if (specialChar && count == 2 && pwd.length < 8) {
        Alert('', '三种字符密码长度至少8位', 'error');
        return false;
    }

    if (!specialChar && count == 2 && pwd.length < 10) {
        Alert('', '两种字符密码长度至少10位', 'error');
        return false;
    }

    if (sameStr(pwd)) {
        Alert('', '请不要包含连续相同字符', 'error');
        return false;
    }

    if (LxStr(pwd)) {
        Alert('', '请不要包含连续相邻字符', 'error');
        return false;
    }

    return true;

    function sameStr(str) {
        var arr = str.split('');
        for (var i = 1; i < arr.length - 1; i++) {
            var firstIndex = arr[i - 1].charCodeAt();
            var secondIndex = arr[i].charCodeAt();
            var thirdIndex = arr[i + 1].charCodeAt();
            if ((thirdIndex - secondIndex == 0) && (secondIndex - firstIndex == 0)) {
                return true;
            }
        }
        return false;
    }

    function LxStr(str) {
        var arr = str.split('');
        for (var i = 1; i < arr.length - 1; i++) {
            var firstIndex = arr[i - 1].charCodeAt();
            var secondIndex = arr[i].charCodeAt();
            var thirdIndex = arr[i + 1].charCodeAt();
            if ((thirdIndex - secondIndex == 1) && (secondIndex - firstIndex == 1)) {
                return true;
            }
        }
        return false;
    }
}