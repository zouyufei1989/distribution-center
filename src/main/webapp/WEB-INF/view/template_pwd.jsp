<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="${pageContext.request.contextPath}/script/plugins/encrypt/jsencrypt.js"></script>
<script src="${pageContext.request.contextPath}/script/plugins/encrypt/keys.js"></script>

<script type="text/javascript">
    function encryptRSAPwd(pwd) {
        // Encrypt with the public key...
        var encrypt = new JSEncrypt();
        encrypt.setPublicKey(PUBLIC_KEY);
        var encrypted = encrypt.encrypt(pwd);

        return encrypted;
    }

</script>
