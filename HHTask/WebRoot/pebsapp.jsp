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

		<title>下载安装包</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	</head>
	<script type="text/javascript"
		src="javascript/jquery-easyui-1.3.1/jquery-1.8.0.min.js">
</script>
	<body> 
		<h2>
			<a id="applink2"
				href="<%=basePath%>upload/file/download/Manage_signed_12-12.apk">下载pebsAPP</a>
		</h2>
		<script type="text/javascript">
</script>
	</body>
</html>
