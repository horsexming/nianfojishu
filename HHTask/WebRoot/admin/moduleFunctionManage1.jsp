<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="checkAdmin.jsp"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
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
		<center>
			<h3>
				自拟定顺序
			</h3>
			<div align="center">
				<font color="red">${successMessage}</font>
			</div>
			<form id="updateMf1" action="ModuleFunctionAction!updateMf1.action"
				method="post" style="margin: 0px; padding: 0px;"
				enctype="multipart/form-data">
				<input type="hidden" name="id" value="${moduleFunction.id}">
				<input type="hidden" name="moduleFunction.id"
					value="${moduleFunction.id}">
				<input type="hidden" name="moduleFunction.fatherId"
					value="${moduleFunction.fatherId}">
				<input type="hidden" name="moduleFunction.belongLayer"
					value="${moduleFunction.belongLayer}">
				<table class="table">
					<tr>
						<td align="right">
							功能名称:
						</td>
						<td>
							<input type="text" readonly="readonly"
								name="moduleFunction.functionName"
								value="${moduleFunction.functionName}" />
						</td>
					</tr>

					<tr>
						<td align="right">
							序列ID:
						</td>
						<td>
							<input type="text" name="moduleFunction.sequence_id"
								value="${moduleFunction.sequence_id}" />
						</td>
					</tr>

					<tr>
						<td colspan="2" align="center">
							<br>
							<input type="submit" style="width: 100px; height: 50" value="修改">
							<input type="reset" style="width: 100px; height: 50px;"
								value="重置">
						</td>
					</tr>
				</table>
			</form>
			<br />
		<s:if test="moduleFunctionList!=null">
			<form action="ModuleFunctionAction!updateMfById1.action?id=${id}"
				method="post">
				<table class="table" border="1">
					<tr>
						<td width="250px">
							功能名称
						</td>
						<td>
							序列ID
						</td>
						<td align="center">
							操作
						</td>
					</tr>
					<s:iterator id="pageCompanyinfo" value="moduleFunctionList"
						status="pageIndex">
						<tr>
							<td>
								${pageCompanyinfo.functionName}
							</td>
							<td>
								<input type="hidden" name="detailSelect"
									value="${pageCompanyinfo.id}">
								<input type="text" name="detailSelect1"
									value="${pageCompanyinfo.sequence_id}" style="width: 50px">
							</td>
							<td>
								<s:if test="#pageIndex.first==true">
									<input type="button" value="上移" disabled="disabled">
									<input type="button" value="下移"
										onclick="downMove(${pageCompanyinfo.sequence_id})">
								</s:if>
								<s:else>
									<s:if test="#pageIndex.last==true">
										<input type="button" value="上移"
											onclick="upMove(${pageCompanyinfo.sequence_id})">
										<input type="button" value="下移" disabled="disabled">
									</s:if>
									<s:else>
										<input type="button" value="上移"
											onclick="upMove(${pageCompanyinfo.sequence_id})">
										<input type="button" value="下移"
											onclick="downMove(${pageCompanyinfo.sequence_id})">
									</s:else>
								</s:else>
							</td>
						</tr>
					</s:iterator>
				</table>
				<input type="submit" value="提交" style="width: 100px; height: 50">
				<input type="reset" value="重置" style="width: 100px; height: 50">
			</form>
	</s:if>
			<div align="center">
				<%
					request.getSession().removeAttribute("successMessage");
					request.getSession().removeAttribute("errorMessage");
				%>
				<br />
				<br />
				<br />
			</div>
			<center>
	</body>
	<script type="text/javascript">
function upMove(obj) {
	var id1 = ${id};
	window.location.href = "ModuleFunctionAction!updateMfById2.action?id="
			+ id1 + "&se_id=" + obj + "&a=1";
}
function downMove(obj) {
	var id1 =${id};
	window.location.href = "ModuleFunctionAction!updateMfById2.action?id="
			+ id1 + "&se_id=" + obj + "&a=2";
}
</script>


</html>
