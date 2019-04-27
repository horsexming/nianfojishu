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
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">
					
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<h3>修改报检记录</h3>
				<form action="maintain_update.action" method="post">
				<table border="1" style="border-collapse: collapse;">
					<tr>
						<td>编号：<input type="text" name="mai.number" value="${mai.number }" style="margin-left: 30px;"/></td>
						<td>名称：<input type="text" name="mai.matetag" value="${mai.matetag }" style="margin-left: 30px;"/></td>
						<td>规格：<input type="text" name="mai.format" value="${mai.format }"/></td>
					</tr>
					<tr>
						<td>单位：<input type="text" name="mai.unit" value="${mai.unit }" style="margin-left: 30px;"/></td>
						<td>数量：<input type="text" name="mai.amount" value="${mai.amount }" style="margin-left: 30px;"/></td>
						<td>状态：<input type="text" name="mai.state" value="${mai.state}"/></td>
					</tr>
					<tr>
						<td>报修时间：<input type="text" style="width: 155px;" class="Wdate"
									type="text" name="mai.fixDate" value="${mai.fixDate}"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" /></td>
						<td colspan="2">备注：<input type="text" name="mai.more" value="${mai.more}" style="width: 200px;"/></td>
					</tr>
					<TR>
						<td colspan="3" align="center"><input type="hidden" name="mai.id" value="${mai.id }"/>
						<input type="submit" value="修改"/></td>
					</TR>
				</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
