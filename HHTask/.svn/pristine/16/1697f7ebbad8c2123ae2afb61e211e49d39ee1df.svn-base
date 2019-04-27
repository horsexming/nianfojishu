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

		<title>补打</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<%@include file="/util/sonHead.jsp"%>
	</head>

	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div align="center" id="printDiv">
			<img alt="" src="barcode.action?msg=${param.context}" style="width: 200px; height: 60px;">
		</div>
		<div align="center">
			<input type="button" value="打印" onclick="pagePrint('printDiv')">
		</div>
	</body>
</html>
