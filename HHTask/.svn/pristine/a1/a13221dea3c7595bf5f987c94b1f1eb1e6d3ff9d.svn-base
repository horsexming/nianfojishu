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
				<h3>修改报废记录</h3>
				<form action="scrap_update.action" method="post">
				<table border="1" style="border-collapse: collapse;">
					<tr>
						<td>编号：<input type="text" name="scr.number" value="${scr.number }" style="margin-left: 16px;"/></td>
						<td>名称：<input type="text" name="scr.matetag" value="${scr.matetag }" style="margin-left: 30px;"/></td>
						<td>规格：<input type="text" name="scr.format" value="${scr.format }" style="margin-left: 30px;"/></td>
					</tr>
					<tr>
						<td>责任人：<input type="text" name="scr.username" value="${scr.username }"/></td>
						<td>部门：<input type="text" name="scr.dept" value="${scr.dept }" style="margin-left: 30px;"/></td>
						<td>日期：<input type="text" style="width: 155px;margin-left: 30px;" class="Wdate"
									type="text" name="scr.badDate" value="${scr.badDate}"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" /></td>
					</tr>
					<tr>
						<td>数量：<input type="text" name="scr.amount" value="${scr.amount }" style="margin-left: 16px;"/></td>
						<td>损失意见：<input type="text" name="scr.badView" value="${scr.badView }"/></td>
						<td>损失原因：<input type="text" name="scr.more1" value="${scr.more1 }"/></td>
					</tr>
					<tr>
						<td colspan="3" align="left">备注:<textArea rows="4" cols="20" name="scr.more2">${scr.more2 }</textArea></td>
					</tr>
					<TR>
						<td colspan="3" align="center"><input type="hidden" name="scr.id" value="${scr.id }"/>
						<input type="submit" value="更改"/></td>
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
