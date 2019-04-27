<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
		<title>错误提示页面,${companyInfo.shortName}作业网</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="shortcut icon" href="favicon.ico" />
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="css/css.css">
	</head>
	<body bgcolor="#ffffff">
		<center>
			<%@include file="sonTop.jsp"%>
			<div id="gongneng"
				style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
				<div id="xitong"
					style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
					align="left">
					<font color="#ffffff">500</font>
				</div>
				
				<div align="center">
					<h2 style="color: red">
						啊偶!服务器忙，请稍候重试!
					</h2>
					<hr width="40%">
					<h5>
						可能是服务器繁忙或者是操作失误!请稍候重试或者和管理员联系!
					</h5>
					<SCRIPT type="text/javascript">
							
						</SCRIPT>
					<a href="index.jsp">返回首页</a>
					<a href="javascript:;" onclick="javascript:history.go(-1);">返回上一页</a>
				</div>
				<br>
			</div>
			<%@include file="foot.jsp"%>
		</center>
	</body>
</html>
