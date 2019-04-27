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

<title>短信管理页面</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<jsp:include page="inc.jsp"></jsp:include>
</head>

<body class="easyui-layout">
	<div data-options="region:'north'" style="height:60px;"></div>
	<div data-options="region:'south'" style="height:20px;text-align: center;background-color:#E0ECFF;">
		版权所有,仿冒必究!
	</div>
	<div data-options="region:'west',title:'导航',split:true" style="width:200px;">
		<jsp:include page="layout/west.jsp"></jsp:include>
	</div>
	<div data-options="region:'east'" style="width:200px;">
		<jsp:include page="layout/east.jsp"></jsp:include>
	</div>
	<div data-options="region:'center',title:'内容'">
		<jsp:include page="layout/center.jsp"></jsp:include>
	</div>
</body>
</html>
