<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
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
	</head>
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<script
	src="${pageContext.request.contextPath}/javascript/jquery/jquery-3.2.1.js">
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/js/bootstrap.js">
</script>
	<body>
		<form action="reliabilityTestAction!addRTP.action" method="post" >
			<table class="table">
				<tr align="center">
					<td colspan="4" style="font-size: 20px;">
						可靠性测试项目添加
					</td>
				</tr>
				<tr>
					<td class="col-xs-6" align="right">项目名称:</td>
					<td class="col-xs-6"><input type="text" name="pro.proName" id="proName"></td>
				</tr>

			</table>
			<div align="center">
				<input class="input" type="submit" />
			</div>
		</form>
	</body>
</html>
