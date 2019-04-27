<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.task.entity.Users"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<%@include file="/util/sonHead.jsp"%>
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/css/bootstrap.min.css">
	</head>
	<body bgcolor="#ffffff">
		<div class="container">
			<div class="row" style="height: 20px;">
            
            </div>
			<div class="row clearfix">
				<h1 align="center">
					欢迎使用 PEBS 访客系统
				</h1>
            </div>
            <div class="row" style="height: 60px;">
            	请刷身份证
            </div>
		</div>
	</body>	
</html>
