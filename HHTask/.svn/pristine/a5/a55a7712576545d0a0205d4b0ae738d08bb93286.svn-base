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
				<form action="EquipmentChangesAction!updatemaintenance.action"
					method="post" onsubmit="return check()">
					<input type="hidden" name="equipmentChanges.id"
						value="${equipmentChanges.id}" />
					<input type="hidden" name="equipmentChanges.name"
						value="${equipmentChanges.name}" />
					<input type="hidden" name="equipmentChanges.dapt"
						value="${equipmentChanges.dapt}" />
					<input type="hidden" name="equipmentChanges.userid"
						value="${equipmentChanges.userid}" />
						<input type="hidden" name="equipmentChanges.machine.id"
						value="${equipmentChanges.machine.id}" />
						<input type="hidden" name="equipmentChanges.equipmentName"
						value="${equipmentChanges.equipmentName}" />
						<input type="hidden" name="equipmentChanges.devicenumber"
						value="${equipmentChanges.devicenumber}" />
							<input type="hidden" name="equipmentChanges.devicetype"
						value="${equipmentChanges.devicetype}" />
							<input type="hidden" name="equipmentChanges.oldworkarea"
						value="${equipmentChanges.oldworkarea}" />
							<input type="hidden" name="equipmentChanges.oldstation"
						value="${equipmentChanges.oldstation}" />
						
					<table border="1" width="100%" class="table">
						<tr>
							<td colspan="20" align="center"
								style="font-family: 微软雅黑; font-weight: bold;">
								修改设备变动申请单
							</td>
						</tr>
						<tr>
							<td>
								设备名称:
							</td>
							<td>
								${equipmentChanges.equipmentName}
							</td>
							<td>
								设备编号:
							</td>
							<td>
								${equipmentChanges.devicenumber}
							</td>
							<td>
								设备类型:
							</td>
							<td>
								${equipmentChanges.devicetype}
							</td>
						</tr>
						<tr>
							<td>
								旧工区:
							</td>
							<td>


								${equipmentChanges.oldworkarea}
							</td>
							<td>
								旧工位:
							</td>
							<td>
								${equipmentChanges.oldstation}
							</td>
							<td>
							</td>
							<td>
							</td>
						</tr>
						<tr>
							<td>
								新工区:
							</td>
							<td>
								<input name="equipmentChanges.newworkarea"
									value="${equipmentChanges.newworkarea}" />
							</td>
							<td>
								新工位:
							</td>
							<td>

								<input name="equipmentChanges.newstation"
									value="${equipmentChanges.newstation}" />
							</td>
							<td>
							</td>
							<td>
							</td>
						</tr>
						<tr>
							<td>
								变动原因说明:
							</td>
							<td colspan="5">
								<input type="text" style="width: 250px; height: 80px;"
									name="equipmentChanges.reason"
									value="${equipmentChanges.reason}" />
							</td>
						</tr>
						<tr>
							<td colspan="6" align="center">
								<input type="submit" value="修改"
									style="width: 100px; height: 50px;">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="reset" value="重置"
									style="width: 100px; height: 50px;">

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
