<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
					<a href="javascript:location.reload();" style="color: #ffffff">刷新</a>
					<a href="EquipmentAction!findAlll.action?maintenance.name="
						style="color: #ffffff">查询报修单</a>
				</div>
			</div>
			
			<div align="center">
				<font color="red"> ${successMessage}</font>
				<form action="EquipmentAction!updatemaintenance.action"
					method="post">
					<input type="hidden" name="maintenance.id"
						value="${maintenance.id}" />
					<input type="hidden" name="maintenance.status"
						value="${maintenance.status}" />
					<input type="hidden" name="maintenance.userid"
						value="${maintenance.userid}" />
					<input type="hidden" name="maintenance.alermMan"
						value="${maintenance.alermMan}" />
					<input type="hidden" name="maintenance.barcode"
						value="${maintenance.barcode}" />
					<input type="hidden" name="maintenance.alermDept"
						value="${maintenance.alermDept}" />
					<input type="hidden" name="maintenance.more"
						value="${maintenance.more}" />
					<input type="hidden" name="maintenance.alarmTime"
						value="${maintenance.alarmTime}" />

					<table border="1" width="100%" class="table">
						<tr>

							<td colspan="20" align="center"
								style="font-family: 微软雅黑; font-weight: bold;">
								修改报修单
							</td>
						</tr>
						<tr>
							<th>
								工区
							</th>
							<td>

								<input type="text" name="maintenance.workArea"
									value="${maintenance.workArea}" readonly="readonly" />
							</td>

							<th>
								工位
							</th>
							<td>

								<input type="text" name="maintenance.workPosition"
									value="${maintenance.workPosition}" readonly="readonly" />
							</td>
							<th>
								设备编号
							</th>
							<td>
								<input type="text" name="maintenance.no"
									value="${maintenance.no}" readonly="readonly" />
							</td>
						</tr>
						<tr>
							<th>
								设备类型
							</th>
							<td>
								<input type="text" name="maintenance.type"
									value="${maintenance.type}" readonly="readonly" />
							</td>

							<th>
								设备名称
							</th>

							<td>
								<input type="text" name="maintenance.name"
									value="${maintenance.name}" readonly="readonly" />


							</td>

							<th>
								所在班组
							</th>
							<td>
								<input type="text" name="maintenance.classGroup"
									value="${maintenance.classGroup}" readonly="readonly" />
							</td>
						</tr>
						<tr>


							<s:if test='maintenance.status=="故障指派"'>

							</s:if>
							<s:else>
								<th>
									修理人
								</th>
								<td>
									<select name="maintenance.repairMan" >
									<option value="${maintenance.repairMan}">
											${maintenance.repairMan}
										</option>
										<s:iterator value="list" id="pagePeople">
											<option value="${pagePeople.repairname}">
												${pagePeople.repairname}
											</option>
										</s:iterator>
									</select>
									</td>
							</s:else>
							

						</tr>

						<tr>

							<td>
								故障状况
							</td>
							<td>

								<input type="text" style="width: 250px; height: 80px;"
									name="maintenance.faultDetail"
									value="${maintenance.faultDetail}" />
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
