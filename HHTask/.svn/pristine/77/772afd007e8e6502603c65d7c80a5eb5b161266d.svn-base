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
				<form action="EquipmentAction!findAllparts.action" method="post"
					style="margin: 0px;">
					<table class="table">
						<tr>
							<th colspan="8">
								更换零件管理
							</th>
						</tr>
						<tr>
							<td align="right">
								维修条码：
							</td>
							<td>
								<input type="text" style="width: 150px;" name="parts.barcode">
							</td>
							<td align="right">
								零件名称：
							</td>
							<td>
								<input type="text" style="width: 150px;" name="parts.partName">
							</td>
							<td align="right">
								零件规格:
							</td>
							<td>
								<input type="text" style="width: 150px;" name="parts.pictureNo">
							</td>
						</tr>
						<tr>
							<td align="right">
								起始日期：
							</td>
							<td>
								<input class="Wdate" name="date1"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />

							</td>
							<td align="right">
								结束日期：
							</td>
							<td>
								<input class="Wdate" name="date2"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />

							</td>
						</tr>
						<tr align="center">
							<td colspan="6">
								<input type="submit" value="查询"
									style="width: 100px; height: 60px">
							</td>
						</tr>
					</table>


					<input type="hidden" name="parts.id" value="${parts.id}" />
					
					<table width="100%" border="0" style="border-collapse: collapse;"
						class="table">

					</table>
					<table width="100%" border="0" style="border-collapse: collapse;"
						class="table">

						<tr bgcolor="#c0dcf2" height="50px">

							<th align="center">
								编号
							</th>
						<th align="center">
								工区
							</th>
							<th align="center">
								工位
							</th>
							<th align="center">
								设备名称
							</th>
							<th align="center">
								维修条码
							</th>
							<th align="center">
								零件名称
							</th>
							<th align="center">
								零件规格
							</th>
							<th align="center">
								零件数量
							</th>
							<th align="center">
								单位
							</th>
							<th align="center">
								单价
							</th>
							<th align="center">
								更换时间
							</th>
						</tr>
						<s:iterator value="partslist" id="pageparts" status="pageStatus">
							<s:if test="#pageStatus.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:if test="#pageStatus.index%2==1">
									<font>
								</s:if>
								<s:else>
									<font color="red">
								</s:else>
								<s:property value="#pageStatus.index+1" />
								</font>
							</td>
							<td>
								${pageparts.maintenance.workArea}
							</td>
							<td>
								${pageparts.maintenance.workPosition}
							</td>
							<td>
								${pageparts.maintenance.name}
							</td>
							<td>
								${pageparts.barcode}
							</td>
							<td>
								${pageparts.partName}

							</td>

							<td>
								${pageparts.pictureNo}
							</td>
							<td>
								${pageparts.num}
							</td>
							<td>
								${pageparts.unit}
							</td>
							<td>
								${pageparts.more}
							</td>
							<td>

								<fmt:formatDate value="${pageparts.alarmTime}"
									pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
						</s:iterator>
						<tr>
							<s:if test="errorMessage==null">
								<td colspan="12" align="right">
									第
									<font color="red"><s:property value="cpage" /> </font> /
									<s:property value="total" />
									页
									<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
										styleClass="page" theme="number" />
							</s:if>
							<s:else>
								<td colspan="11" align="center" style="color: red">
									${errorMessage}
								</td>
							</s:else>

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
