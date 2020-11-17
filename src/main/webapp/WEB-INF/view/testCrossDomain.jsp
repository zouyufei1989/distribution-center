<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>

<head>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script type="text/javascript">
	function test(){
		$.ajax({
        type: "GET",
        //url: "http://localhost:8083/lenovo/h5/test",
        // url: "http://www.b.com:8083/lenovo/h5/bindTmpVehicleRouteAuto",
        url: "http://t.pandabus.cc:8080/guohe/h5/queryTmpPassengerStatus?id=0117d67b-c17d-470b-b554-da04cd3a3a8d  ",
        //url: "http://t.pandabus.cc:8080/lenovo/h5/bindTmpVehicleRouteAuto",
        dataType: "json",
        //contentType: "application/json;charset=UTF-8",
        //data: JSON.stringify({sign:1111,timestamp:222}),
        success: function (result) {
            console.log(result);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            Alert("", textStatus, "error");
        }
    });

	}

</script>
</head>

<body>
<button onclick='test()'>send</button>
</body>



</html>