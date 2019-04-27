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
		<title>进度条演示页面</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<style type="text/css">
.processbar {
	height: 13px;
	background-color: #fff;
	border: 1px solid #999;
}

.finish {
	height: 13px;
	width: 40%;
	background-color: #999;
}
</style>
	</head>
	<body>
		<div class="processbar">
			<div class="finish"></div>
		</div>
	</body>
</html>