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
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 900px;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">添加预付款单</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								id="closeTcDiv" height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="xiugaiIframe" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 500px; margin: 0px; padding: 0px;"></iframe>

				</div>
			</div>
		</div>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<font size="5px"><b>预付款申请单添加</b>
				</font>
				<form
					action="WaigouwaiweiPlanAction!findWgOrderListAppli.action?pageStatus=${pageStatus}&tag=${tag}"
					method="post" id="form">
					<table class="table">
						<tr>
							<th>
								供应商编号:
							</th>
							<td>
								<input name="waigouOrder.userCode"
									value="${waigouOrder.userCode}" />
							</td>
							<th>
								供应商名称:
							</th>
							<td>
								<input name="waigouOrder.gysName" value="${waigouOrder.gysName}" />
							</td>
							<th>
								件号:
							</th>
							<td>
								<input name="waigouOrder.addUserYx"
									value="${waigouOrder.addUserYx}" />
							</td>
						</tr>
						<tr>
							<th>
								负责采购姓名:
							</th>
							<td>
								<input name="waigouOrder.addUserName"
									value="${waigouOrder.addUserName}" />
							</td>
							<th>
								负责采购工号:
							</th>
							<td>
								<input name="waigouOrder.addUserCode"
									value="${waigouOrder.addUserCode}" />
							</td>
							<th>
								状态:
							</th>
							<td>
								<SELECT name="waigouOrder.status">
									<s:if test="waigouOrder.status!=null||waigouOrder.status!=''">
										<option value="${waigouOrder.status}">
											${waigouOrder.status}
										</option>
									</s:if>
									<s:else>
										<option value="">
											
										</option>
									</s:else>
									<option value="">
										
									</option>
									<option value="待核对">
										待核对
									</option>
									<option value="待审批">
										待审批
									</option>
									<option value="待通知">
										待通知
									</option>
									<option value="待确认">
										待确认
									</option>
									<option value="待审核">
										待审核
									</option>
									<option value="生产中">
										生产中
									</option>
									<option value="送货中">
										送货中
									</option>
									<option value="待入库">
										待入库
									</option>
									<option value="入库">
										入库
									</option>
								</SELECT>
							</td>
						</tr>
						<tr>
							<th>
								采购月份:
							</th>
							<td>
								<input name="waigouOrder.caigouMonth"
									value="${waigouOrder.caigouMonth}" class="Wdate"
									onClick="WdatePicker({dateFmt:'yyyy-MM月',skin:'whyGreen'})" />
							</td>
							<th>
								开始时间:
							</th>
							<td>
								<input name="waigouOrder.addTime" value="${waigouOrder.addTime}"
									class="Wdate"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" />
							</td>
							<th>
								结束时间:
							</th>
							<td>
								<input name="waigouOrder.querenTime"
									value="${waigouOrder.querenTime}" class="Wdate"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" />
							</td>
						</tr>
						<tr>
							<th>
								订单编号:
							</th>
							<td>
								<input name="waigouOrder.planNumber"
									value="${waigouOrder.planNumber}" />
							</td>
							<th>
							</th>
							<td>
							</td>
							<th>
							</th>
							<td>
							</td>
						</tr>
						<tr>
							<th colspan="6">
								<input type="submit" value="查询" class="input">
								<input type="button" value="导出" class="input"
									onclick="clicks();todisabledone(this)" data="downData">
							</th>
						</tr>
					</table>
				</form>
				<form action="zhaobiaoAction!toAddPrepaymentsApplicationDetail.action" method="post">
					<table class="table">
						<tr>
							<td colspan="12" align="center" style="color: red">
							<input type="submit" value="添加预付款申请单" style="height:30PX;width:120px">
						</td>
						<tr bgcolor="#c0dcf2" height="50px">
							<td align="center">
								<input type="checkbox" onclick="chageAllCheck(this)"/>
							</td>
							<th align="center">
								序号
							</th>
							<th align="center">
								供应商
							</th>
							<th align="center">
								采购月份
							</th>
							<th align="center">
								订单编号
							</th>
							<th align="center">
								状态
							</th>
							<th align="center">
								添加日期
							</th>
							<th align="center">
								联系人
							</th>
							<th align="center">
								通知日期
							</th>
							<th align="center">
								确认日期
							</th>
							<th align="center">
								审批状态
							</th>
<%--							<th align="center">--%>
<%--								操作--%>
<%--							</th>--%>
						</tr>
						<s:if test="pageStatus=='dsq'">
							<tr>
								<th colspan="15"
									style="height: 35px; color: #ffffff; background-color: red;">
									待申请采购订单
								</th>
							</tr>
						</s:if>
						<s:elseif test="pageStatus=='dtz'">
							<tr>
								<th colspan="15"
									style="height: 35px; color: #ffffff; background-color: red;">
									待通知采购订单
								</th>
							</tr>
						</s:elseif>
						<s:elseif test="pageStatus=='dqr'">
							<tr>
								<th colspan="15"
									style="height: 35px; color: #ffffff; background-color: red;">
									待确认采购订单
								</th>
							</tr>
						</s:elseif>
						<s:elseif test="pageStatus=='gysnew'">
							<tr>
								<th colspan="15"
									style="height: 35px; color: #ffffff; background-color: red;">
									待确认采购订单
								</th>
							</tr>
						</s:elseif>
						<s:else>
							<tr>
								<th colspan="15"
									style="height: 35px; color: #ffffff; background-color: green;">
									历史采购计划
								</th>
							</tr>
						</s:else>
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
							<s:if test='#pageWgww.applystatus=="同意"'>
								<input name="orderId" type="checkbox" value="${pageWgww.id}" onclick="chageNum(this)"/>
							</s:if>
						</td>
							<td>
								<s:property value="#pageStatus.index+1" />
							</td>
							<td align="left">
								${pageWgww.gysName}
								<br />
								(${pageWgww.userCode})
							</td>
							<td>
								${pageWgww.caigouMonth}
							</td>
							<td>
								${pageWgww.planNumber}
							</td>
							<td>
								${pageWgww.status}
							</td>
							<td>
								${pageWgww.addTime}
							</td>
							<td>
								${pageWgww.addUserName}
								<br />
								(${pageWgww.addUserCode})
							</td>
							<td>
								${pageWgww.tongzhiTime}
							</td>
							<td>
								${pageWgww.querenTime}
							</td>
							<td>
								<s:if test="#pageWgww.epId!=null">
									<a
										href="CircuitRunAction_findAduitPage.action?id=${pageWgww.epId}">${pageWgww.applystatus}</a>
								</s:if>
								<s:else>${pageWgww.applystatus}</s:else>
							</td>
<%--							<td>--%>
<%--								<s:if test='#pageWgww.applystatus=="同意"'>--%>
<%--									<a onclick="tanchu('${pageWgww.id}')">添加预付款请单</a>--%>
<%--								</s:if>--%>
<%--							</td>--%>
						</s:iterator>
						<tr>
							<td colspan="12" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
							</td>
						</tr>
						<tr>
							<td colspan="12" align="center" style="color: red">
							<input type="submit" value="添加预付款申请单" style="height:30PX;width:120px">
						</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function tanchu(id) {
	document.getElementById("xiugaiIframe").src = "zhaobiaoAction!toAddyufu.action?id1="
			+ id;
	chageDiv('block');

}
function clicks() {
	document.getElementById('form').action = "WaigouwaiweiPlanAction!exportWgOrderList.action?pageStatus=${pageStatus}";
	document.getElementById('form').submit();
	document.getElementById('form').action = "WaigouwaiweiPlanAction!findWgPlanList.action?pageStatus=${pageStatus}";
}
</script>
	</body>
</html>