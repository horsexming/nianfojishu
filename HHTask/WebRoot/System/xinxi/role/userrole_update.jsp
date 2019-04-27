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
		<base href="<%=basePath%>">
		<%@include file="/util/sonHead.jsp"%>

		<title>My JSP 'role_listshow.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->


		<script language="javascript"
			src="${pageContext.request.contextPath}/javascript/jquery/jquery-1.12.4.js">
</script>
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/css/bootstrap.min.css">
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/js/bootstrap.js">
</script>
	</head>

	<body>
		<%@include file="/util/sonTop.jsp"%>
		<h1 align="center">
			修改角色
		</h1>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<form action="UserRoleAction_updateRole.action" method="post"
				style="">
				<br>
				<table border="0" width="100%" class="table">
					<tr>
						<input type="hidden" name="userRole.id" value="${userRole.id}" />
						<td align="right">
							角色名称:
						</td>
						<td>
							<input type="text" name="userRole.name" value="${userRole.name}" />
						</td>
						<td align="right">
							角色描述:
						</td>
						<td>
							<input type="text" name="userRole.description"
								value="${userRole.description}" />
						</td>
					</tr>
					<tr>
						<td align="center" colspan="4">
							<input type="submit" value="修改" onclick="reload()"
								style="width: 100px; height: 50px;" />
							<input type="reset" value="重置"
								style="width: 100px; height: 50px;" />
						</td>
					</tr>
				</table>
			</form>
		</div>

		<%@include file="/util/foot.jsp"%>
		<script type="text/javascript">
</script>
	</body>
</html>
