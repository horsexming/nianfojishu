<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.task.action.UsersAction"%>
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

		<title>上传证书</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>

	<body>
		<div align="center">
			<form id="uploadform" action="companyInfoAction_uploadlic.action"
				method="post" enctype="multipart/form-data">
				<table class="table">
					<tr align="center">
						<td align="center" width="50%">
							上传证书:
							<input type="file" name="license" id="licensefile">
						</td>
					</tr>
					<tr align="center">
						<td colspan="3" align="center">
							<br>
							<input type="submit" value="上传证书"
								style="width: 100px; height: 50px">
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div align="center">
			<iframe id="showLicensemsg">
			</iframe>
		</div>
	</body>

	<script type="text/javascript" src="javascript/jquery-1.8.3.js">
</script>
	<script type="text/javascript">
$(document)
		.ready(
				function() {
					$("#showLicensemsg")
							.attr(
									"src",
									"<%=UsersAction.getMainurl()%>/companyInfoAction_getLicensemsg.action?companyUrl=${selfUrl}");
				});
</script>
</html>
