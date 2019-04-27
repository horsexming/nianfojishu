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
				<table border="1" class="table">
					<tr>
						<td colspan="20" align="center"
							style="font-family: 微软雅黑; font-weight: bold;">
							设备变动明细单
						</td>
					</tr>
					<tr>
						<th>
							设备名称:
						</th>
						<td>
							<input type="hidden" value="${equipmentChanges.id}"
								name="equipmentChanges.id" />
							${equipmentChanges.equipmentName}
						</td>
						<th>
							设备编号:
						</th>
						<td>

							${equipmentChanges.devicenumber}
						</td>
					</tr>

					<tr>
						<th>
							新工区:
						</th>
						<td>

							${equipmentChanges.newworkarea}

						</td>
						<th>
							新工位:
						</th>
						<td>
							${equipmentChanges.newstation}
						</td>
					</tr>
					<tr>
						<th>
							旧工区:
						</th>
						<td>


							${equipmentChanges.oldworkarea}
						</td>
						<th>
							旧工位:
						</th>
						<td>

							${equipmentChanges.oldstation}
						</td>
					</tr>
					<tr>
						<th>
							申请人:
						</th>
						<td>
							${equipmentChanges.name}
						</td>
						<th>
							申请时间:
						</th>
						<td>

							${equipmentChanges.changesdate}
						</td>
					</tr>
					<tr>
						<th>
							设备移动人员:
						</th>
						<td>
							${equipmentChanges.changesname}
						</td>
						<th>
							移动完成时间:
						</th>
						<td>

							${equipmentChanges.completiontime}
						</td>
					</tr>
					<tr>
						<th>
							变动原因说明:
						</th>
						<td>
							${equipmentChanges.reason}
						</td>
					</tr>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
