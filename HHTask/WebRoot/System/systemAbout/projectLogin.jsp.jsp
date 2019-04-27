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
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<form action="ProjectRecordAction!addProjectLogin.action"
					method="post">
					<input type="hidden" name="projectLogin.projectRecord.id"
						value="${projectRecord.id}">
					<input type="hidden" name="id" value="${moduleFunction.id}">

					<table border="0">
						<tr>
							<th colspan="2">
								您的帐号即将与
								<font color="red">${projectRecord.websiteName}</font> 绑定
								<br />
								(绑定后以后可直接登录 ${projectRecord.websiteName})
								<br />
								<br />
							</th>
						</tr>
						<tr>
							<th colspan="2">
								请输入
								<font color="red">${projectRecord.websiteName}</font> 的登录信息
								<br />
							</th>
						</tr>
						<tr>
							<td align="right">
								<s:iterator id="loginFieldName"
									value='projectRecord.loginFieldName.split(",")'>
									${loginFieldName}:<br />
								</s:iterator>
							</td>
							<td>
								<s:iterator id="loginField"
									value='projectRecord.loginField.split(",")'>
									<input name="projectLogin.loginFieldValue" />
									<br />
								</s:iterator>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<br />
								<input type="submit" value="绑定"
									style="width: 80px; height: 50px;" />
								<input type="reset" value="重置"
									style="width: 80px; height: 50px;" />

							</td>
						</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
	</body>
















</html>
