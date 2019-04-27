<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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

		<title>My JSP 'testshowP.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<script type="text/javascript" src="javascript/jquery-1.8.3.js">
</script>
		<script type="text/javascript" charset="utf-8">
jQuery.fn.flash = function(color, duration) {
	var current = this.css('color');
	this.animate( {
		color : 'rgb(' + color + ')'
	}, duration / 2);
	this.animate( {
		color : current
	}, duration / 2);
}
$('#importantElement').flash('255,0,0', 100);
</script>

	</head>
	<body>
		<div id="importantElement"></div>
	</body>
</html>
