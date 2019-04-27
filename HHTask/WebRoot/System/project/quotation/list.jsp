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
				<form action="ProjectQuotation_add.action" method="post">
					<table class="table" style="width: 90%" >
						<tr>
							<th colspan="6" >查看询价包</th>
						</tr>
						<tr>
							<th>零件代号</th>
							<td> ${quotation.partNum} </td>
							<th>零件名称</th>
							<td> ${quotation.description} </td>
						</tr>
						<tr>
							<th>原材料</th>
							<td colspan="5">
								<a target="_blank" href="ProjectQuotationRawMaterial_list.action?quotation.id=${quotation.id}">查看</a>
								<a target="_blank" href="ProjectQuotationRawMaterial_addInput.action?quotation.id=${quotation.id}">添加</a>
							</td>
						</tr>
						<tr>
							<th>外购外协</th>
							<td colspan="5">
								<a target="_blank" href="ProjectQuotationSubContract_list.action?quotation.id=${quotation.id}">查看</a>
								<a target="_blank" href="ProjectQuotationSubContract_addInput.action?quotation.id=${quotation.id}">添加</a>
							</td>
						</tr>
						<tr>
							<th>直接人工</th>
							<td colspan="5">
								<a target="_blank" href="ProjectQuotationDirectLaborCost_list.action?quotation.id=${quotation.id}">查看</a>
								<a target="_blank" href="ProjectQuotationDirectLaborCost_addInput.action?quotation.id=${quotation.id}">添加</a>
							</td>
						</tr>
						<tr>
							<th>设备折旧</th>
							<td colspan="5">
								<a target="_blank" href="ProjectQuotationEquipmentDepreciation_list.action?quotation.id=${quotation.id}">查看</a>
								<a target="_blank" href="ProjectQuotationEquipmentDepreciation_addInput.action?quotation.id=${quotation.id}">添加</a>
							</td>
						</tr>
						<tr>
							<th>模具成本</th>
							<td colspan="5">
								<a target="_blank" href="ProjectQuotationToolingCost_list.action?quotation.id=${quotation.id}">查看</a>
								<a target="_blank" href="ProjectQuotationToolingCost_addInput.action?quotation.id=${quotation.id}">添加</a>
							</td>
						</tr>
						<tr>
							<th>查看详细</th>
							<td colspan="5">
								<a target="_blank" href="ProjectQuotation_showAll.action?quotation.id=${quotation.id}">生成报价</a>
								<a target="_blank" href="ProjectQuotation_updateOtherInput.action?quotation.id=${quotation.id}">修改报价</a>
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
