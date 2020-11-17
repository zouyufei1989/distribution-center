function setCookie(name, value, expdays) {
    var expdate = new Date();
    expdate.setDate(expdate.getDate() + expdays);
    document.cookie = name + "=" + escape(value) + ";expires=" + expdate.toUTCString();
}


function getCookie(name) {
    var start = document.cookie.indexOf(name + "=");
    if (start != -1) {
        start = start + name.length + 1;
        var end = document.cookie.indexOf(";", start);
        if (end == -1) end = document.cookie.length;
        return unescape(document.cookie.substring(start, end));
    }
    return "";
}


function delCookie(name) {
    setCookie(name, "", -1);
}