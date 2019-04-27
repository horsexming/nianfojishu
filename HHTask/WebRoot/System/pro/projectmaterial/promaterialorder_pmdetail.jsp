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
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h3>
				        项目材料清单<br/>(add project material order)
				</h3>
				<form action="projectMaterialOrderAction_add.action"
					method="post" onsubmit="return validate();">
					<table id="addTable" width="100%" border="1" style="border-collapse: collapse;">
						<tr>
						   <td align="center" colspan="3">
								项目名称（project Name）：
							</td>
							<td align="center" colspan="5" >
								<s:property value="projectMaterialOrder.proName"/>
							</td>
						</tr>
						<tr>
						   <td align="center" colspan="3">
								清单编号（order number）：
							</td>
							<td align="center" colspan="5" >
								<s:property value="projectMaterialOrder.orderNo"/>
							</td>
						</tr>
						<tr>
						   <td align="center" colspan="3">
								备注（remark）：
							</td>
							<td align="center" colspan="5" >
								<s:property value="projectMaterialOrder.remark"/>
							</td>
						</tr>
						<tr>
						 <td align="center">
								项目名称（part Number）
							</td>
						   <td align="center">
								件号（part Number）
							</td>
							<td align="center">
								名称（part Name）
							</td>
							<td align="center">
								牌号（trademark）
							</td>
							<td align="center">
								规格（specification）
							</td>
							<td align="center">
								数量（need Number）
							</td>
							<td align="center">
								单位（part unit）
							</td>
							<td align="center">
								供应商（supplier）
							</td>
						</tr>
						<s:iterator value="pmList" id="pm">
						<tr >
						<td align="center">
								<s:property value="#pm.proName"/>
							</td>
						   <td align="center">
								<s:property value="#pm.markId"/>
							</td>
							<td align="center">
								<s:property value="#pm.materialName"/>
							</td>
							<td align="center">
								<s:property value="#pm.paihao"/>
							</td>
							<td align="center">
							<s:property value="#pm.guige"/>
							</td>
							<td align="center">
								<s:property value="#pm.needNumber"/>
							</td>
							<td align="center">
							<s:property value="#pm.unit"/>
							</td>
							<td align="center">
							<s:property value="#pm.supplier"/>
							</td>
						</tr>
							</s:iterator>
					</table>
				</form>
				<input type="hidden" value="<s:property value='successMessage'/>" id="successMessage">
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		
	</body>
</html>
