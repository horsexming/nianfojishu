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
		<%@include file="/util/sonHead.jsp"%>
		<title>打印预览</title>
	</head>
	<body style="background: #ffffff url('') repeat-x;">
		<center>
			<div id="printViewDiv" style="width: 100%">
				<div id="contDiv" style="width: 100%">
				</div>
			</div>
			<br />
			<br />
			<input type="button" value="打印"
				onclick="pagePrintOld('printViewDiv')"
				style="width: 80px; height: 50px;">
		</center>
		<script>
$(function() {
	var printBodyHTML = window.dialogArguments;
	document.getElementById("contDiv").innerHTML = printBodyHTML.name;
})
</script>
	</body>
</html>
