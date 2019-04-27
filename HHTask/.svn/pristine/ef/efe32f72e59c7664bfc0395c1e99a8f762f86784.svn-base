<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
	<head>
		<meta chatset="utf-8" />
		<title>This is a test</title>
	</head>

	<body>
		<input id="in_text" value="ABC123" />
		<input id="hellowebInput" type="button" value="js中调用APK方法">
		<script type="text/javascript">
//APK中调用了此方法
function funFromjs() {
	document.getElementById("helloweb").innerHTML = "HelloWebView,i'm from js";
}
var aTag = document.getElementById("hellowebInput");

aTag.addEventListener('click', function() {
	alert(1);
	var text = document.getElementById("in_text").value;
	alert(2);
	if (typeof (myObj) != "undefined") {
		//调用android本地方法
		alert(3);
		myObj.funFromAndroid(text);
	} else {
		alert(4);
	}
	return false;
}, false);
</script>
		<p></p>
		<div id="helloweb">

		</div>
	</body>
</html>
