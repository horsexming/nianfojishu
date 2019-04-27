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
		<script type="text/javascript" src="javascript/jquery-easyui-1.3.1/jquery-1.8.0.min.js">
</script>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
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
				<form action="ProjectStartSchedule_update.action"  method="post" >
					<input type="hidden" name="p.id" value="${p.id}" />
					<input type="hidden" name="p.root.id" value="${p.root.id}" />
					
					<table class="table" style="width: 80%" >
						<tr>
							<th colspan="2">修改项目进度</th>
						</tr>
						<tr>
							<th>进度名称</th>
							<td><input name="p.name" value="${p.name}" /></td>
						</tr>
						<tr>
							<th>开始时间</th>
							<td><input name="p.startDate" value="${p.startDate}" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" /></td>
						</tr>
						<tr>
							<th>结束时间</th>
							<td><input name="p.endDate" value="${p.endDate}" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" /></td>
						</tr>
						<tr>
							<th>备注</th>
							<td><input name="p.description" value="${p.description}" /></td>
						</tr>
						<tr>
							<td colspan="2" align="center" >
								<input type="submit" />
								<input type="reset"/>
							</td>
						</tr>
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
