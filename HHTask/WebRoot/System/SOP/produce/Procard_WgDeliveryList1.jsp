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
			<form
					action="WaigouwaiweiPlanAction!findDeliveryNoteByUser.action"
					method="post">
					<table class="table">
						<tr>
							<th>
								送货单号:
							</th>
							<td>
								<input name="waigouDelivery.planNumber" value="${waigouDelivery.planNumber}" />
							</td>
							<th>
								客户名称:
							</th>
							<td>
								<input name=waigouDelivery.customer" value="${waigouDelivery.customer}" />
							</td>
							<th>
								联系人:
							</th>
							<td>
								<input name="waigouDelivery.contacts"
									value="${waigouDelivery.contacts}" />
							</td>
						</tr>
						<tr>
							<th>
								联系方式:
							</th>
							<td>
								<input name="waigouDelivery.contactsPhone"
									value="${waigouDelivery.contactsPhone}" />
							</td>
							<th>
								状态:
							</th>
							<td>
								<input name="waigouDelivery.status"
									value="${waigouDelivery.status}"/>
							</td>
							<th>
								添加日期
							</th>
							<td>
								<input name="waigouDelivery.addTime" value="${waigouDelivery.addTime}" class="Wdate"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" />
							</td>
						</tr>
						<tr>
							<th>
								是否打印
							</th>
							<td colspan="6">
								<input type="radio" value="YES" name="waigouDelivery.isprint">是
								<input type="radio" value="NO" name="waigouDelivery.isprint">否
							</td>
						</tr>
						
						<tr>
							<th colspan="6">
									<input type="submit" value="查询" class="input">
							</th>
						</tr>
					</table>
				</form>
			<div align="center">
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th align="center">
							序号
						</th>
						<th align="center">
							送货单号
						</th>
						<th align="center">
							供应商名称
						</th>
						<th align="center">
							联系人
						</th>
						<th align="center">
							联系方式
						</th>
						<th align="center">
							状态
						</th>
						<th align="center">
								是否打印
						</th>
						<th align="center">
							添加日期
						</th>
						<th align="center">
							操作
						</th>
					</tr>
					<tr>
						<th colspan="14"
							style="height: 35px; color: #ffffff; background-color: red;">
							待打印送货单
						</th>
					</tr>
					<s:iterator value="wwPlanList" id="printWd" status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb" style="height: 50px;"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								style="height: 50px;" onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#pageStatus.index+1" />
						</td>
						<td>
							${printWd.planNumber}
						</td>
						<td>
							${printWd.gysName}
						</td>
						<td>
							${printWd.contacts}
						</td>
						<td>
							${printWd.contactsPhone}
						</td>
						<td>
							${printWd.status} 
						</td>
						<td>
							<s:if test='#printWd.isprint == "YES"'>
								是
							</s:if>
							<s:else>
								否
							</s:else>
						</td>
						<td>
							${printWd.addTime}
						</td>
						<td>
							<a
								href="WaigouwaiweiPlanAction!findDeliveryNoteDetail.action?id=${printWd.id}">送货明细</a>/
							<a
								href="WaigouwaiweiPlanAction!findDNDetailForPrint.action?id=${printWd.id}">打印</a>
						</td>
					</s:iterator>
					<tr>
						<th colspan="14"
							style="height: 35px; color: #ffffff; background-color: green;">
							历史采购计划
						</th>
					</tr>
					<s:iterator value="list" id="pageWgww" status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb" style="height: 50px;"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								style="height: 50px;" onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#pageStatus.index+1" />
						</td>
						<td>
							${pageWgww.planNumber}
						</td>
						<td align="left">
							${pageWgww.gysName}
						</td>
						<td>
							${pageWgww.contacts}
						</td>
						<td>
							${pageWgww.contactsPhone}
						</td>
						<td>
							${pageWgww.status} 
						</td>
						<td>
							<s:if test='#pageWgww.isprint == "YES"'>
								是
							</s:if>
							<s:else>
								否
							</s:else>
						</td>
						<td>
							${pageWgww.addTime}
						</td>
						<td>
							<a
								href="WaigouwaiweiPlanAction!findDeliveryNoteDetail.action?id=${pageWgww.id}">送货明细</a>
						</td>
					</s:iterator>
					<tr>
						<td colspan="14" align="right">
							第
							<font color="red"><s:property value="cpage" /> </font> /
							<s:property value="total" />
							页
							<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
								styleClass="page" theme="number" />
						</td>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
	</body>
</html>
