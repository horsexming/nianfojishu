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
				<form action="ProjectQuotationList_add.action" method="post">
					<input type="hidden" name="quotationList.root.id" value="${quotationList.root.id}" >
					<table class="table" style="width: 90%" >
						<tr>
							<th colspan="6" >添加询价包</th>
						</tr>
						<tr>
							<th>零件代号</th>
							<td> <input name="quotationList.partNum"> </td>
							<th>零件名称</th>
							<td> <input name="quotationList.description"> </td>
							<th>项目名称</th>
							<td> <input name="quotationList.project"> </td>
						</tr>
						<tr>
							<th>年度预测</th>
							<td> <input name="quotationList.annualForecast"> </td>
							<th>采购</th>
							<td> <input name="quotationList.buyer"> </td>
							<th>运输距离</th>
							<td> <input name="quotationList.logisticsDistance"> </td>
						</tr>
						<tr>
							<th>发货地</th>
							<td colspan="3"> <input name="quotationList.location" style="width: 300px;"> </td>
							<th>报价日期</th>
							<td> <input name="quotationList.quotationDate" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" > </td>
						</tr>
						<tr>
							
						</tr>
						<tr>
							<td colspan="6" align="center" >
								<input type="submit" value="提交">
								<input type="reset" value="重填">
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
