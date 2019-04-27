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
				送货单模式 &nbsp;&nbsp;
				<a
					href="goodsAction!findDeliveryNoteDetail_1.action?pageStatus=${pageStatus}">送货单明细模式</a>
				<form action="goodsAction!findDeliveryNote.action"
					method="post">
					<table class="table">
						<tr>
							<th align="right">
								送货单号:
							</th>
							<td>
								<input type="text" name="waigouDeliveryGoods.planNumber"
									value="${waigouDeliveryGoods.planNumber}" />
							</td>
							<th align="right">
								客户名称:
							</th>
							<td>
								<input type="text" name="waigouDeliveryGoods.customer"
									value="${waigouDeliveryGoods.customer}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								联系人:
							</th>
							<td>
								<input type="text" name="waigouDeliveryGoods.contacts"
									value="${waigouDeliveryGoods.contacts}" />
							</td>
							<th align="right">
								状态:
							</th>
							<td>
								<select name="waigouDeliveryGoods.status">
									<s:if
										test="waigouDeliveryGoods.status == null ||waigouDeliveryGoods.status == '' ">
										<option value="${waigouDeliveryGoods.status}">
											${waigouDeliveryGoods.status}
										</option>
									</s:if>
									<s:else>
										<option value="${waigouDeliveryGoods.status}">
											${waigouDeliveryGoods.status}
										</option>
										<option value=""></option>
									</s:else>
									<option value="待确认">
										待确认
									</option>
									<option value="待打印">
										待打印
									</option>
									<option value="待送货">
										待送货
									</option>
									<option value="已签收">
										已签收
									</option>
									<option value="已验收">
										已验收
									</option>
									<option value="退货带核对">
										退货带核对
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<th align="right">
								添加日期从:
							</th>
							<td>
								<input type="text" name="firsttime" class="Wdate"
									value="${firsttime}"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<th align="right">
								止:
							</th>
							<td>
								<input type="text" name="endtime" class="Wdate"
									value="${endtime}"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
						</tr>
						<tr>
							<th>
								是否打印
							</th>
							<td colspan="6">
								<input type="radio" value="YES" name="waigouDeliveryGoods.isprint">
								是
								<input type="radio" value="NO" name="waigouDeliveryGoods.isprint">
								否
							</td>
						</tr>
					</table>
					<input type="hidden" value="${pageStatus}" name="pageStatus" />
					<input type="submit" value="查询" class="input" />
					<input type="button" value="导出" class="input"
						onclick="exportExcel(this.form);todisabledone(this)" data="downData" />
				</form>

				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th align="center">
							序号
						</th>
						<th align="center">
							送货单号
						</th>
						<th align="center">
							客户名称
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
						<th colspan="10"
							style="height: 35px; color: #ffffff; background-color: red;">
							<s:if test="pageStatus == 'dqr'">
								待确认送货单
							</s:if>
							<s:elseif test="pageStatus == 'gys'">
								待打印送货单
							</s:elseif>
						</th>
					</tr>
					<s:iterator value="wdgList" id="printWd" status="pageStatus">
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
							${printWd.customer}
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
							<s:if test='printWd.isprint == "YES"'>
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
								href="goodsAction!findDeliveryNoteDetail.action?id=${printWd.id}&pageStatus=${pageStatus}">送货明细</a>
							<s:if test=" pageStatus == 'dqr'">
								<%--								/<a--%>
								<%--									href="WaigouwaiweiPlanAction!querenDelivery.action?selected=${printWd.id}">确认</a>--%>
							</s:if>
							<s:else>
								<a
									href="goodsAction!findDNDetailForPrint.action?id=${printWd.id}">打印</a>
							</s:else>

						</td>
					</s:iterator>
					<tr>
						<th colspan="10"
							style="height: 35px; color: #ffffff; background-color: green;">
							历史采购计划
						</th>
					</tr>
					<s:iterator value="lists" id="pageWgww" status="pageStatus">
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
						<td>
							${pageWgww.customer}
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
							<s:if test='pageWgww.isprint == "YES"'>
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
								href="goodsAction!findDeliveryNoteDetail.action?id=${pageWgww.id}">送货明细</a>
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
		<SCRIPT type="text/javascript">
	
	function exportExcel(obj){//zhaobiaoAction!listAll.action
		obj.action = "WaigouwaiweiPlanAction!exportExcelWaigouDelivery.action";
	 	obj.submit();
	  	obj.action = "WaigouwaiweiPlanAction!findDeliveryNote.action";
	 	}
	</SCRIPT>
	</body>
</html>
