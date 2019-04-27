<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'number.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>

	<body onkeydown="enter()">
		<script>
var cardNumber = "";
function enter() {
	var keyCode = window.event.keyCode;
	if (keyCode < 48 || keyCode > 57) {
		if (keyCode == 13 && cardNumber.length == 10) {
			document.getElementById("cardNumber1").value = cardNumber;
			document.getElementById("cardNumber2").value = cardNumber;
			cardNumber = "";
			return;
		} else {
			cardNumber = "";
			event.returnvalue = false;
			return;
		}
	}
	if (keyCode == 48) {
		keyCode = 0;
	} else if (keyCode == 49) {
		keyCode = 1;
	} else if (keyCode == 50) {
		keyCode = 2;
	} else if (keyCode == 51) {
		keyCode = 3;
	} else if (keyCode == 52) {
		keyCode = 4;
	} else if (keyCode == 53) {
		keyCode = 5;
	} else if (keyCode == 54) {
		keyCode = 6;
	} else if (keyCode == 55) {
		keyCode = 7;
	} else if (keyCode == 56) {
		keyCode = 8;
	} else if (keyCode == 57) {
		keyCode = 9;
	}
	cardNumber = cardNumber + keyCode;

}
</script>
		<input id="cardNumber1" disabled="disabled" />
		<input id="cardNumber2" type="hidden" />
	</body>
</html>
