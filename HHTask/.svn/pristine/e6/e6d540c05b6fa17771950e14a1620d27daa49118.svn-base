<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
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
		<script src="http://pv.sohu.com/cityjson?ie=utf-8">
</script>

		<script type="text/javascript">
$(function() {
	if ('${strList1}'.indexOf('外部权限') < 0 && '${strList1}'.indexOf('gys') < 0) {
		if ('${companyInfo.outIp}'.indexOf(returnCitySN["cip"])<0) {
			$(".hideprice").hide();
		}
	}
})
</script>
		<%--	<link rel="stylesheet" type="text/css"--%>
		<%--	href="<%=basePath %>/css/button.css" />--%>
		<STYLE type="text/css">
.ztree li a {
	color: #fff;
}

/* 带复选框的下拉框 */
ul li {
	list-style: none;
	padding: 0px;
	margin: 0px;
}

.select_checkBox {
	border: 0px solid red;
	position: relative;
	display: inline-block;
}

.chartQuota {
	height: 23px;
	float: left;
	display: inline-block;
	border: 0px solid black;
	position: relative;
}

.chartOptionsFlowTrend {
	z-index: 300;
	background-color: white;
	border: 1px solid gray;
	display: none;
	position: absolute;
	left: 0px;
	top: 23px;
	width: 150px;
}

.chartOptionsFlowTrend ul {
	float: left;
	padding: 0px;
	margin: 5px;
}

.chartOptionsFlowTrend li { /* float:left; */
	display: block;
	position: relative;
	left: 0px;
	margin: 0px;
	clear: both;
}

.chartOptionsFlowTrend li * {
	float: left;
}

a:-webkit-any-link {
	color: -webkit-link;
	text-decoration: underline;
	cursor: auto;
}

.chartQuota p a {
	float: left;
	height: 21px;
	outline: 0 none;
	border: 1px solid #ccc;
	line-height: 22px;
	padding: 0 5px;
	overflow: hidden;
	background: #eaeaea;
	color: #313131;
	text-decoration: none;
}

.chartQuota p {
	margin: 0px;
	folat: left;
	overflow: hidden;
	height: 23px;
	line-height: 24px;
	display: inline-block;
}

.chartOptionsFlowTrend p {
	height: 23px;
	line-height: 23px;
	overflow: hidden;
	position: relative;
	z-index: 2;
	background: #fefbf7;
	padding-top: 0px;
	display: inline-block;
}

.chartOptionsFlowTrend p a {
	border: 1px solid #fff;
	margin-left: 15px;
	color: #2e91da;
}

#shenhe {
	position: absolute;
	top: 85px;
	right: 500px;
	z-index: 10;
	transform: rotate(12deg); /*旋转div*/
	opacity: 1.0;
}

.showtimes {
	display: none;
}
</STYLE>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;height: 1000px;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 900px;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">修改供应商</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								id="closeTcDiv" height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<div id="more" style="display: none;">
						<div align="center">
							<h3>
								修改备注
							</h3>
						</div>
						<form action="WaigouwaiweiPlanAction!updateMore.action"
							method="post">
							<input type="hidden" id="idInput" name="waigouPlan.id" />
							备注:
							<input type="text" id="moreInput" name="waigouPlan.more" />
							<input type="submit" value="修改" />
						</form>
					</div>
				</div>
				<iframe id="xiugaiIframe" src="" marginwidth="0" marginheight="0"
					hspace="0" vspace="0" frameborder="0" scrolling="yes"
					style="width: 98%; height: 500px; margin: 0px; padding: 0px;"></iframe>

			</div>
		</div>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<%--				<div id="shenhe">--%>
				<%--						<img alt="" src="<%=basePath %>/img/yishenhe.png" width="100px;" height="50px;"/>--%>
				<%--					</div>--%>
				<s:if test="waigouOrder2!=null">
					<table class="table" style="width: 50%">
						<tr>
							<th colspan="2">
								采购订单明细
							</th>
						</tr>
						<tr>
							<th align="right">
								供应商编号:
							</th>
							<td>
								${waigouOrder2.userCode}
							</td>
						</tr>
						<tr>
							<th align="right">
								供应商:
							</th>
							<td>
								${waigouOrder2.gysName}
							</td>
						</tr>
						<tr>
							<th align="right">
								采购月份:
							</th>
							<td>
								${waigouOrder2.caigouMonth}
							</td>
						</tr>
						<tr>
							<th align="right">
								订单状态:
							</th>
							<td>
								<a
									href="CircuitRunAction_findAduitPage.action?id=${waigouOrder2.epId}">
									${waigouOrder2.status}</a>
							</td>
						</tr>
						<tr>
							<th align="right">
								计划单号:
							</th>
							<td>
								${waigouOrder2.planNumber}
							</td>
						</tr>
						<tr>
							<th align="right">
								付款方式:
							</th>
							<td>
								<s:if
									test="(waigouOrder2.status=='待核对'||waigouOrder2.status=='待通知')&&(noOperation!='noOperation'||noOperation==null)">
									<form action="WaigouwaiweiPlanAction!updateOrderMsg.action"
										method="post">
										<input type="hidden" name="waigouOrder.id"
											value="${waigouOrder2.id}">
										<input type="hidden" name="tag" value="payType">
										<input name="waigouOrder.payType"
											value="${waigouOrder2.payType}">
										<input type="submit" value="修改">
									</form>
								</s:if>
								<s:else>
							${waigouOrder2.payType}
							</s:else>
							</td>
						</tr>
						<tr>
							<th align="right">
								添加日期:
							</th>
							<td>
								${waigouOrder2.addTime}
							</td>
						</tr>
						<tr>
							<th align="right">
								通知日期:
							</th>
							<td>
								${waigouOrder2.tongzhiTime}
							</td>
						</tr>
						<tr>
							<th align="right">
								确认采购日期:
							</th>
							<td>
								${waigouOrder2.querenTime}
							</td>
						</tr>
						<tr>
							<th align="right">
								联系人:
							</th>
							<td>
								${waigouOrder2.tzUserName}
							</td>
						</tr>
						<tr>
							<th align="right">
								电话:
							</th>
							<td>
								${waigouOrder2.tzUserPhone}
							</td>
						</tr>
						<tr>
							<th align="right">
								邮箱:
							</th>
							<td>
								${waigouOrder2.addUserYx}
							</td>
						</tr>
					</table>
				</s:if>
				<s:else>
					<a
						href="WaigouwaiweiPlanAction!findWgOrderList.action?pageStatus=${pageStatus}&tag=${tag}&noOperation=${noOperation}">采购订单模式</a>
						采购明细模式
					<form id="form"
						action="WaigouwaiweiPlanAction!findWgPlanList.action?pageStatus=${pageStatus}&tag=${tag}&noOperation=${noOperation}"
						method="post">
						<table class="table">
							<tr>
								<th>
									件号:
								</th>
								<td>
									<input name="waigouPlan.markId" value="${waigouPlan.markId}" />
								</td>
								<th>
									供应商:
								</th>
								<td>
									<input name="waigouPlan.gysName" value="${waigouPlan.gysName}" />
								</td>
								<th>
									物料类别:
								</th>
								<td colspan="3">
									<div class="zTreeDemoBackground left">
										<ul class="list">
											<li class="title">
												<input id="wgType" type="text" 
													value="${waigouPlan.wgType}" style="width: 120px;"
													name="waigouPlan.wgType" />
												<a id="menuBtn" href="#" onclick="showMenu(); return false;">选择</a>
											</li>
										</ul>
									</div>
									<div id="menuContent" class="menuContent"
										style="display: none; position: absolute;">
										<ul id="treeDemo" class="ztree"
											style="margin-top: 0; width: 160px;"></ul>
									</div>
								</td>
							</tr>
							<tr>
								<th>
									产品状态
								</th>
								<td>
									<select name="waigouPlan.status" id="status">
										<option></option>
										<s:if test='pageStatus == "dsq"'>
											<option value="待核对">
												待核对
											</option>
											<option value="待通知">
												待通知
											</option>
										</s:if>
										<s:elseif test='pageStatus == "dtz"'>
											<option value="待通知">
												待通知
											</option>
										</s:elseif>
										<s:elseif test='pageStatus == "dqr" || pageStatus == "gysnew"'>
											<option value="待确认">
												待确认
											</option>
											<option value="协商确认">
												协商确认
											</option>
										</s:elseif>
										<s:else>
											<option value="待核对">
												待核对
											</option>
											<option value="待通知">
												待通知
											</option>
											<option value="待确认">
												待确认
											</option>
											<option value="协商确认">
												协商确认
											</option>
											<option value="生产中">
												生产中
											</option>
											<option value="送货中">
												送货中
											</option>
											<option value="待检验">
												待检验
											</option>
											<option value="待入库">
												待入库
											</option>
											<option value="入库">
												入库
											</option>
										</s:else>
									</select>
								</td>
								<th>
									采购员姓名:
								</th>
								<td>
									<input type="text"
										value="${waigouPlan.waigouOrder.addUserName}"
										name="waigouPlan.waigouOrder.addUserName" id="" />
								</td>
								<th>
									采购员工号:
								</th>
								<td colspan="3">
									<input type="text"
										value="${waigouPlan.waigouOrder.addUserCode}"
										name="waigouPlan.waigouOrder.addUserCode" id="" />
								</td>
							</tr>
							<tr>
								<th>
									添加时间（起）:
								</th>
								<td>
									<input class="Wdate" type="text" name="waigouPlan.addTime"
										value="${fn:split(waigouPlan.addTime,',' )[0]}"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" />
								</td>
								<th>
									添加时间（止）:
								</th>
								<td>
									<input class="Wdate" type="text" name="waigouPlan.addTime"
										value="${fn:split(waigouPlan.addTime,',' )[1]}"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen',startDate:'%y-%M-%d 23:59:59'})" />
								</td>
								<th>
									物品名称
								</th>
								<td>
									<input name="waigouPlan.proName" value="${waigouPlan.proName}" />	
								</td>
								
							</tr>
							<tr>
								<th>
									到货时间（起）:
								</th>
								<td>
									<input class="Wdate" type="text"
										name="waigouPlan.acArrivalTime"
										value="${fn:split(waigouPlan.acArrivalTime,',' )[0]}"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
								</td>
								<th>
									到货时间（止）:
								</th>
								<td>
									<input class="Wdate" type="text"
										name="waigouPlan.acArrivalTime"
										value="${fn:split(waigouPlan.acArrivalTime,',' )[1]}"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen',startDate:'%y-%M-%d 23:59:59'})" />
								</td>
								<td rowspan="4" colspan="3">
									<input style="margin-left: 40px;" type="submit" value="查询"
										class="input">
									<input type="button" value="导出" class="input"
										onclick="clicks();todisabledone(this)" data="downData">
									<s:if test="pageStatus=='findAlljg'">
										<input type="button" value="导出(价格)" class="input"
											onclick="clicks2();todisabledone(this)" data="downData">
									</s:if>
								</td>
							</tr>
							<tr>
								<th>
									项目编号
								</th>
								<td>
									<input type="text" name="waigouPlan.proNumber"
										value="${waigouPlan.proNumber }">
								</td>
								<th>
									供料属性
								</th>
								<td>
									<select name="waigouPlan.kgliao" style="width: 155px;">
										<option value="${waigouPlan.kgliao}">
											${waigouPlan.kgliao}
										</option>
										<option>
										</option>
										<option value="TK">
											自购(TK)
										</option>
										<option value="TK AVL">
											指定供应商(TK AVL)
										</option>
										<option value="CS">
											客供(CS)
										</option>
										<option value="TK Price">
											完全指定(TK Price)
										</option>
									</select>
								</td>
							</tr>
							<tr>
								<th>
									采购单号
								</th>
								<td>
									<input type="text" name="planNumber" value="${planNumber}">
								</td>
								<th>
									每页条数
								</th>
								<td>
									<select name="pageSize" style="width: 80px;">
										<option>${pageSize}</option>
										<option>30</option>
										<option>50</option>
										<option>80</option>
										<option value="${pageSize*total}">全部显示</option>
									</select>
								</td>
							</tr>
							<tr>
								<th>
									需求部门
								</th>
								<td>
									<input type="text" name="waigouPlan.demanddept" value="${waigouPlan.demanddept}">
								</td>
								<th>
								</th>
								<td>
								</td>
							</tr>
						</table>
					</form>
					<div align="right">
						<input type="checkbox" onclick="showtimes(this)">
						显示时间
					</div>
				</s:else>

				<s:if test="waigouOrder2.id!=null&&noOperation!='noOperation'">
					<s:if
						test="waigouOrder2.status!='待核对'&&waigouOrder2.status!='待审批'&&waigouOrder2.status!='待通知'">
						<div id="test" style="position: absolute; right: 5px; top: 5px;">
							<img alt="" src="<%=basePath%>/img/yishenhe.png"
								style="transform: rotate(8deg);" width="80px;" height="50px;" />
						</div>
					</s:if>
					<a style="text-decoration: none;"
						href="WaigouwaiweiPlanAction!getwgOrderTz.action?id=${waigouOrder2.id}"
						class="button blue">下载图纸</a>
					<s:if test="pageStatus == 'dsq'">
						<a style="text-decoration: none;" class="button blue"
							href="WaigouwaiweiPlanAction!shuaixinAllPrice.action?id=${waigouOrder2.id}&pageStatus=${pageStatus}"
							onclick="return confirm('确定要统一刷新价格吗?')">统一刷新价格</a>
					</s:if>
				</s:if>
				<s:if
					test="pageStatus!='gysnew'&&waigouOrder2.id!=null&&noOperation!='noOperation'">
					<div align="right" style="margin-top: 0px;">
						<a class="button blue"
							style="margin-top: 0px; text-decoration: none;"
							href="WaigouwaiweiPlanAction!gotoprint.action?processIds=${waigouOrder2.id}&pageStatus=waigou">
							<font size="4">打印</font> </a>&nbsp;&nbsp;&nbsp;
					</div>
				</s:if>
				<s:if
					test="waigouOrder2.status=='待确认'||waigouOrder2.status=='协商确认'&&noOperation!='noOperation'">
					<div align="center">
						<form
							action="WaigouwaiweiPlanAction!allUpdateJiaoFuTime.action?id=${waigouOrder2.id}"
							method="post">
							统一填写交付日期:
							<input id="jiaofuTime" class="Wdate" type="text"
								name="waigouPlan.jiaofuTime" style="width: 100px;"
								onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"
								onchange="CompareDate(this,'${waigouOrder2.addTime}')" />
							<input type="hidden" name="pageStatus" value="dqr" />
							<input type="hidden" name="tag" value="wg" />
							<input type="submit">
						</form>
					</div>
				</s:if>
				<form id="form" method="post">
					<input value="${waigouOrder2.id}" name="id" id="waigouOrderId"
						type="hidden">
					<table class="table" style="font-size: 8px;">
						<tr>
							<th colspan="14" id="th_0" align="right">本页合计:</th>
							<th colspan="1" id="td_sumNum">
								<fmt:formatNumber type="number" value="${sumNum}" pattern="0.00" maxFractionDigits="2"/>
							</th>
							<th colspan="1" id="td_sumwshNum">
								<fmt:formatNumber type="number" value="${sumwshNum}" pattern="0.00" maxFractionDigits="2"/>
							</th>
							<th></th>
							<th></th>
							<s:if test="strList!=null && strList.size()>0">
								<th colspan="1" id="td_sumbhsprice">
									<fmt:formatNumber type="number" value="${sumbhsprice}" pattern="0.00" maxFractionDigits="2"/>
								</th>
								<th colspan="1"></th>
								<th colspan="1"></th>
								<th colspan="1" id="td_sumMoney">
									<fmt:formatNumber type="number" value="${sumMoney}" pattern="0.00" maxFractionDigits="2"/>
								</th>
								<th class="showtimes" colspan="7"></th>
								<th colspan="1" id="td_sumbdhNUmber">
									<fmt:formatNumber type="number" value="${sumbdhNUmber}" pattern="0.00" maxFractionDigits="2"/>
								</th>
							</s:if>
							<th colspan="20"></th>
						</tr>
						<tr bgcolor="#c0dcf2" height="50px">
							<th align="center">
								<input type="checkbox" value="" onclick="chageAllCheck(this)"/>全选
							</th>
							<th align="center">
								序号
							</th>
							<s:if test="waigouOrder2==null">
								<th align="center">
									订单编号
								</th>
								<th align="center">
									供应商
								</th>
								<th>
									采购员
								</th>
							</s:if>
							<th>
								物料类别
							</th>
							<th align="center">
								件号
							</th>
							<th align="center">
								零件名称
							</th>
							<th align="center">
								规格
							</th>
							<th align="center">
								版本
							</th>
							<th align="center">
								版次
							</th>
							<th align="center">
								供货属性
							</th>
							<th align="center">
								图号
							</th>
							<th align="center" id="th_danwei">
								单位
							</th>
							<th align="center">
								数量
							</th>
							<th align="center">
								未送货数量
							</th>
							<%--							<s:if test="pageStatus=='sbdqr'||pageStatus=='gyssbdqr'">--%>
							<th align="center">
								申请减单数
							</th>
							<th align="center">
								已减数量
							</th>
							<%--							</s:if>--%>
							<s:if test="strList!=null && strList.size()>0">
								<th align="center" class="hideprice">
									含税单价(元)
								</th>
								<th align="center" class="hideprice">
									不含税单价(元)
								</th>
								<th align="center" class="hideprice">
									税率
								</th>
								<th align="center" class="hideprice">
									总额(含税)
								</th>
							</s:if>
							<th align="center" class="showtimes">
								添加时间
							</th>
							<th align="center">
								库存量
							</th>
							<th align="center" class="showtimes">
								协商交付日期
							</th>
							<th align="center" class="showtimes">
								确认时间
							</th>
							<th align="center" class="showtimes">
								应到货时间
							</th>
							<th align="center" class="showtimes">
								实际到货时间
							</th>
							<th align="center">
								产品状态
							</th>
							<th align="center">
								到货数量
							</th>
							<th align="center">
								项目编号
							</th>
							<th align="center">
								备注
							</th>
							<s:if test="tag == 'fl'">
								<td>
									需求部门
								</td>
							</s:if>
							<th align="center">
								操作
							</th>
						</tr>
						<s:iterator value="wwPlanList" id="pageWgww2" status="pageStatus2">
							<s:if test="#pageStatus2.index%2==1">
								<tr align="center" bgcolor="#e6f3fb" style="height: 50px;"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									style="height: 50px;" onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<input type="checkbox" value="${pageWgww2.id}"  name="processIds" />
							</td>
							<td>
								<input id="wwplanId<s:property value="#pageStatus2.index" />"
									value="${pageWgww2.id}"
									name="wwPlanList[<s:property value="#pageStatus2.index" />].id"
									type="hidden">
								<input value="${pageStatus}" name="pageStatus" type="hidden">
								<s:property value="#pageStatus2.index+1" />
							</td>
							<s:if test="waigouOrder2==null">
								<td align="left"
									style="width: auto; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
									${waigouOrder.planNumber}
									<ul class="qs_ul">
										<li>
											${waigouOrder.planNumber}
										</li>
									</ul>
								</td>
								<td align="left"
									style="max-width: 60px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
									${pageWgww2.gysName}
									<br />
									<a target="_showGys"
										href="zhaobiaoAction!listByIdZhUser.action?zhUser.id=${pageWgww2.gysId}">${pageWgww2.userCode}</a>
									<ul class="qs_ul">
										<li>
											${pageWgww2.gysName}
										</li>
									</ul>
								</td>
								<td align="left">
									${pageWgww2.waigouOrder.addUserName}
									<br />
									(${pageWgww2.waigouOrder.addUserCode})
								</td>
							</s:if>
							<td>
								${pageWgww2.wgType}
							</td>
							<td align="left">
								${pageWgww2.markId}
							</td>
							<td align="left">
								${pageWgww2.proName}
							</td>
							<td align="left">
								${pageWgww2.specification}
							</td>
							<td>
								${pageWgww2.banben}
							</td>
							<td>
								${pageWgww2.banci}
							</td>
							<td>
								${pageWgww2.kgliao}
							</td>
							<td
								style="max-width: 50px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
								${pageWgww2.tuhao}
								<ul class="qs_ul">
									<li>
										${pageWgww2.tuhao}
									</li>
								</ul>
							</td>
							<td>
								${pageWgww2.unit}
							</td>
							<td align="right">
								${pageWgww2.number}
							</td>
							<td align="right">
								${pageWgww2.syNumber}
							</td>
							<%--							<s:if test="pageStatus=='sbdqr'||pageStatus=='gyssbdqr'">--%>
							<td align="right">
								<a href="WaigouwaiweiPlanAction!findWgClApplyList.action?id=${pageWgww2.id}" target="_showPrice">${pageWgww2.sbjdApplyCount}</a>		
							</td>
							<td align="right">
								${pageWgww2.sbjdCount}
							</td>
							<%--							</s:if>--%>
							<s:if test="strList!=null && strList.size()>0">
								<td class="hideprice">

									<a
										href="PriceAction!findPriceById.action?id=${pageWgww2.priceId}&statue=mingxi"
										target="_showPrice" id="a_hsprice_${pageWgww2.id}"> 
										<fmt:formatNumber value="${pageWgww2.hsPrice}" pattern="#.#####"></fmt:formatNumber>	
										</a>
								</td>
								<td id="td_price_${pageWgww2.id}" class="hideprice">
										<fmt:formatNumber value="${pageWgww2.price}" pattern="#.#####"></fmt:formatNumber>	
								</td>
								<td class="hideprice">
									${pageWgww2.taxprice}%
								</td>
								<td class="hideprice">
									<fmt:formatNumber value="${pageWgww2.money}" pattern="#.##"></fmt:formatNumber>
								</td>
							</s:if>
							<td class="showtimes">
								${pageWgww2.addTime}
							</td>
							<s:if
								test='waigouOrder.status=="待确认"||waigouOrder.status=="协商确认"'>
								<td>
									<input id="kuCunCount<s:property value="#pageStatus2.index" />"
										name="wwPlanList[<s:property value="#pageStatus2.index" />].kuCunCount"
										style="width: 50px;" value="${pageWgww2.kuCunCount}">
								</td>
								<td class="showtimes">
									<input id="jiaofuTime<s:property value="#pageStatus2.index" />"
										class="Wdate" type="text"
										name="wwPlanList[<s:property value="#pageStatus2.index" />].jiaofuTime"
										style="width: 80px;"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"
										value="${pageWgww2.jiaofuTime}"
										onchange="CompareDate(this,'${pageWgww2.jiaofuTime}')" />
								</td>
							</s:if>
							<s:else>
								<td>
									${pageWgww2.kuCunCount}
								</td>
								<td class="showtimes">
									${pageWgww2.jiaofuTime}
								</td>
							</s:else>
							<td class="showtimes">
								${pageWgww2.querenTime}
							</td>
							<td class="showtimes">
								${pageWgww2.shArrivalTime}
							</td>
							<td class="showtimes">
								${pageWgww2.acArrivalTime}
							</td>
							<td>
								${pageWgww2.status}
							</td>
							<th>
								${pageWgww2.qsNum}
							</th>
							<td>
								${pageWgww2.proNumber}
							</td>
							<th>
								${pageWgww2.more}
							</th>
							<s:if test="tag == 'fl'">
								<td>
									${pageWgww2.demanddept}
								</td>
							</s:if>
							<td>
								<s:if test="noOperation!='noOperation'||noOperation==null">
									<s:if test="pageStatus=='dsq'">
										<input type="button" value="小数补整"
											onclick="buzheng(${pageWgww2.id},${waigouOrder2.id})" />
									</s:if>
									<s:if test="pageStatus=='gyssbdqr'">
										<a
											href="WaigouwaiweiPlanAction!agreesbjd.action?id=${pageWgww2.id}">同意减单</a>
									</s:if>
									<s:if test="waigouOrder2==null">
										<a
											href="WaigouwaiweiPlanAction!findWgPlanList.action?id=${pageWgww2.waigouOrder.id}&pageStatus=${pageStatus}">查看订
											单</a>
										<br />
									</s:if>
									<s:if test="waigouOrder2!=null">
										<%--									<s:if--%>
										<%--										test="(waigouOrder2.applystatus =='未申请' ||waigouOrder2.applystatus =='未审批' || waigouOrder2.applystatus =='打回')&&#pageWgww2.status!='入库'  ">--%>
										<s:if
											test='#pageWgww2.status == "待通知"||#pageWgww2.status == "待核对"'>
											<input type="button" value="修改供应商"
												onclick="tanchu('${pageWgww2.id}','${pageWgww2.gysId}'),this" />
										</s:if>
										<s:if test='#pageWgww2.status == "待通知" ||#pageWgww2.status == "待核对" '>
											<input type="button" value="删除"
												onclick="del_1('${pageWgww2.syNumber}','${pageWgww2.number}','${pageWgww2.id}')" />
										</s:if>
											<s:if test="pageStatus != 'gysall' && pageStatus != 'gysnew'">
												<s:if test="#pageWgww2.syNumber>0">
													<input type="button" value="关闭"
													onclick="quxiao_1('${pageWgww2.syNumber}','${pageWgww2.number}','${pageWgww2.id}')" />
													<input type="button" value="重下"
														onclick="if(window.confirm('您将重下此订单明细,未送货数量将返回至物料需求，已送货数量不变。请问是否继续？')){window.location.href='WaigouwaiweiPlanAction!delWaiGouPlan.action?id=${pageWgww2.id}&deltag=chong'}" />
												</s:if>
												<input type="button" value="刷新价格"
													onclick="sxjg(${pageWgww2.id},${waigouOrder.id})" />
											</s:if>
											
											<s:if test="#pageWgww2.epId!=null">
												<a
													href="CircuitRunAction_findAduitPage.action?id=${pageWgww2.epId}">审批动态</a>
											</s:if>
										<input type="button" value="修改备注"
												onclick="xiugaiMore('${pageWgww2.id}','${pageWgww2.more}')">
										<%--									</s:if>--%>
									</s:if>
									<s:if test='waigouOrder2.status=="待确认"'>
										<input id="jiaoqibtn" type="button" value="交期确认"
											onclick="querenjiaoqi(<s:property value="#pageStatus2.index" />)">
										<br />
									</s:if>
									<input type="button" value="查看图纸" style="height: 35px;"
										onclick="javascript:location.href='WaigouwaiweiPlanAction!gysTzview2.action?id=${pageWgww2.id}';">
									<br />
									<s:if test="#pageWgww2.status == '送货中' ">
										<%--									<input type="button" value="申请入库" style="height: 35px;" onclick="javascript:location.href='WaigouwaiweiPlanAction!sqrk.action?id=${pageWgww2.id}';">--%>
									</s:if>
									<s:if test="#pageWgww2.number!=#pageWgww2.syNumber">
										<input type="button" value="送货明细" style="height: 35px;"
											onclick="javascript:location.href='WaigouwaiweiPlanAction!findWaigouPlanDNDetail.action?id=${pageWgww2.id}';">
									</s:if>
									<s:else>
										<input type="button" value="送货明细" disabled="disabled">
									</s:else>
									<s:if test="#pageWgww2.mopId!=null">
										<input type="button" value="件号反查"
											onclick="jianhaofancha(${pageWgww2.mopId})">
									</s:if>
								</s:if>
								<s:else>
									<s:if test="#pageWgww2.number!=#pageWgww2.syNumber">
										<input type="button" value="送货明细" style="height: 35px;"
											onclick="javascript:location.href='WaigouwaiweiPlanAction!findWaigouPlanDNDetail.action?id=${pageWgww2.id}';">
									</s:if>
									<s:else>
										<input type="button" value="送货明细" disabled="disabled">
									</s:else>
								</s:else>
							</td>
							</tr>
						</s:iterator>
						<tr>
							<th colspan="14" id="th_0" align="right">本页合计:</th>
							<th colspan="1" id="td_sumNum">
								<fmt:formatNumber type="number" value="${sumNum}" pattern="0.00" maxFractionDigits="2"/>
							</th>
							<th colspan="1" id="td_sumwshNum">
								<fmt:formatNumber type="number" value="${sumwshNum}" pattern="0.00" maxFractionDigits="2"/>
							</th>
							<th></th>
							<th></th>
							<s:if test="strList!=null && strList.size()>0">
								<th colspan="1" id="td_sumbhsprice">
									<fmt:formatNumber type="number" value="${sumbhsprice}" pattern="0.00" maxFractionDigits="2"/>
								</th>
								<th colspan="1"></th>
								<th colspan="1"></th>
								<th colspan="1" id="td_sumMoney">
									<fmt:formatNumber type="number" value="${sumMoney}" pattern="0.00" maxFractionDigits="2"/>
								</th>
								<th class="showtimes" colspan="7"></th>
								<th colspan="1" id="td_sumbdhNUmber">
									<fmt:formatNumber type="number" value="${sumbdhNUmber}" pattern="0.00" maxFractionDigits="2"/>
								</th>
							</s:if>
							<th colspan="20"></th>
						</tr>
						
						<tr>
							<td colspan="30" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
							</td>
						</tr>
						<tr>
							<th colspan="30">
								<s:if test="pageStatus=='dqr'">
									<a
										href="WaigouwaiweiPlanAction!orderQueren.action?processIds=${waigouOrder2.id}&pageStatus=wg">订单确认</a>
									<label id="jiaoqiAll1">
										<a href="javascript:;" onclick="querenjiaoqiAll()">交期确认</a>
									</label>
									<label id="jiaoqiAll2" style="display: none;">
										交期确认
									</label>
								</s:if>
								<s:if test="pageStatus=='gysnew'">
									<label id="jiaoqiAll1">
										<a href="javascript:;" onclick="querenjiaoqiAll()">一键确认交付日期</a>
									</label>
									<label id="jiaoqiAll2" style="display: none;">
										一键确认交付日期
									</label>
								</s:if>
								<input type="button" value="统一刷新价格" style="height: 30px;width: 150px;cursor: pointer;" onclick="shuaixinAllPrice(this.form)"/>
							</th>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
var parentdocument =  window.parent.document;
var offset=0;
$(function(){
	//var oDiv=document.getElementById("test"); 
	var  obj =window.parent.document.getElementById("showAll");
	if(obj!=null){
		offset = obj.offsetTop; 
	}
	if('待核对'=='${waigouOrder2.status}'){
		$("#test").hide();
	}
})
parentdocument.onscroll = function(){
	var oDiv=document.getElementById("test"); 
	var parenttop = parentdocument.body.scrollTop;
		parenttop = parenttop-offset;
		if(parenttop<0){
			parenttop = 10;
		}
	if(oDiv!=null){
	oDiv.style.top=parenttop;}
}
window.onscroll=function(){ 
	var oDiv=document.getElementById("test"); 
	if(oDiv!=null){
	oDiv.style.top=document.body.scrollTop + 10;  //控制上下位置
	}
}
$(function(){
	duoxuaSelect("status",'${waigouPlan.status}');
	var th_danwei = document.getElementById("th_danwei");
	$("#th_0").attr("colspan",th_danwei.cellIndex+1);
	
})
		function vali() {
	var nums = document.getElementsByName("mrkIds");
	for ( var i = 0; i < nums.length; i++) {
		if (nums[i].checked) {
			return true;
			
		}
	}
	alert("请选择外委工序！！！");
	return false;
}
function xiugaiMore(id,more){
		$("#idInput").val(id);
		$("#moreInput").val(more);
		$("#more").show();
		chageDiv('block');
}
function querenjiaoqiAll(){
	$("#jiaoqiAll1").hide();
	$("#jiaoqiAll2").show();
	if (confirm("是否确认交期!")) {
		$.ajax( {
					type : "POST",
					url : "WaigouwaiweiPlanAction!querenjiaoqiAll.action?",
					dataType : "json",
					data : $('#form').serialize(),
					success : function(data) {
						alert(data);
						$("#jiaoqiAll2").hide();
						$("#jiaoqiAll1").show();
					}
				});
	}
}

function querenjiaoqi(index){
	$("#jiaoqibtn").attr("disabled","disabled");
	var wwplanId=$("#wwplanId"+index).val();
	var kuCunCount=$("#kuCunCount"+index).val();
	var jiaofuTime=$("#jiaofuTime"+index).val();
	if (confirm("是否确认交期!")) {
		$
				.ajax( {
					type : "POST",
					url : "WaigouwaiweiPlanAction!gysXsOrder.action",
					dataType : "json",
					data : {
						'waigouPlan.id' : wwplanId,
						'waigouPlan.kuCunCount' : kuCunCount,
						'waigouPlan.jiaofuTime' : jiaofuTime,
						'id' : $("#waigouOrderId").val()
					},
					success : function(data) {
						alert(data);
						$("#jiaoqibtn").removeAttr("disabled");
					}
				});
	}

}
		
var mfzTree;
var addzTree;
var delzTree;
var updatezTree;

var id;
var pId;
var name;
var setting = { 
view: { 
dblClickExpand: false 
}, 
data: { 
simpleData: { 
enable: true 
} 
}, 
callback: { 
beforeClick: beforeClick, 
onClick: onClick 
} 
}; 
//读取树形数据
$(document).ready(function() {
	parent.mfzTree();
});
var zNodes = [];
parent.mfzTree = function() {
	$.ajax( {
		url : 'CategoryAction_findcyListByrootId.action',
		type : 'post',
		data :{status:'物料类别'},
		dataType : 'json',
		cache : true,
		success : function(doc) {
			$(doc).each(
					function() {
						zNodes.push( {
							id : $(this).attr('id'),
							pId : $(this).attr('fatherId'),
							name : $(this).attr('name'),
							target : "main",
							click : false,
						});

					});
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.expandAll(true);
		},
		error : function() {
			alert("服务器异常!");
		}
	});
};
function beforeClick(treeId, treeNode) { 
var check = (treeNode && !treeNode.isParent); 

return true; 
} 

function onClick(e, treeId, treeNode) { 
var zTree = $.fn.zTree.getZTreeObj("treeDemo"), 
nodes = zTree.getSelectedNodes(), 
v = ""; 
nodes.sort(function compare(a,b){return a.id-b.id;}); 
for (var i=0, l=nodes.length; i<l; i++) { 
v = nodes[i].name ; 
} 
//if (v.length > 0 ) v = v.substring(0, v.length-1); 
 cityObj = $("#wgType").val(v); 

} 

function showMenu() { 
var cityObj = $("#wgType"); 
var cityOffset = $("#wgType").offset(); 
$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast"); 

$("body").bind("mousedown", onBodyDown); 
} 
function hideMenu() { 
$("#menuContent").fadeOut("fast"); 
$("body").unbind("mousedown", onBodyDown); 
} 
function onBodyDown(event) { 
if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) { 
hideMenu(); 
} 
}
function tanchu(id,gysId,obj){
		document.getElementById("xiugaiIframe").src = "WaigouwaiweiPlanAction!toxiugaigys.action?id="+id+"&id2="+gysId;
		$("#more").hide();
		chageDiv('block');
		
}
function clicks() {
	document.getElementById('form').action = "WaigouwaiweiPlanAction!exportExcelWaigouPlan.action?pageStatus=${pageStatus}&tag=${tag}";
	document.getElementById('form').submit();
	document.getElementById('form').action = "WaigouwaiweiPlanAction!findWgPlanList.action?pageStatus=${pageStatus}";
}
function clicks2() {
	document.getElementById('form').action = "WaigouwaiweiPlanAction!exportExcelWaigouPlan2.action?pageStatus=${pageStatus}&tag=${tag}";
	document.getElementById('form').submit();
	document.getElementById('form').action = "WaigouwaiweiPlanAction!findWgPlanList.action?pageStatus=${pageStatus}";
}
function jianhaofancha(id){
	document.getElementById("xiugaiIframe").src = "WaigouwaiweiPlanAction!findAllCgxinxi.action?id="+id;
		$("#title").html("件号反查");
		$("#more").hide();
		chageDiv('block');
}
function buzheng(id,id2){
	if(confirm("是否确认小数补整!")){
		window.location.href = "WaigouwaiweiPlanAction!buzheng.action?pageStatus=${pageStatus}&id="+ id+"&id2="+id2;
	}
}
function quxiao_1(synum,num,id){
		if(window.confirm('您将关闭此订单明细，请与物控协商确认此物料已不用购买！！！请问是否继续？')){
			window.location.href='WaigouwaiweiPlanAction!delWaiGouPlan.action?deltag=close&id='+id
		}
}
function del_1(synum,num,id){
	if(synum==num){
		if(window.confirm('您将删除此订单明细,未送货数量将返回至物料需求请问是否继续？')){
			window.location.href='WaigouwaiweiPlanAction!delWaiGouPlan.action?deltag=del&id='+id
		}
	}else{
		alert("此订单明细已送货，无法删除！请重下。");
	}
}
function sxjg(planId,orderId){
	if(window.confirm("是否确认要刷新此价格")){
		window.location.href="WaigouwaiweiPlanAction!shuaixinPrice.action?id="+planId+"&waigouOrder.id="+orderId+"&pageStatus=${pageStatus}";
	}
}

function showtimes(obj){
	if(obj.checked){
		$(".showtimes").show();
	}else{
		$(".showtimes").hide();		
	}
}

function CompareDate(obj,addTime){
	if(obj.value!=''){
		var  adddate = new Date(addTime);
		var jiaofudate = new Date(obj.value);
		if(adddate.getTime()>jiaofudate.getTime()){
			obj.value = '';
			alert('交付日期不能早于添加日期。')
		}
	}
}

function shuaixinAllPrice(obj){
	$("input[name='pageStatus']").removeAttr('name');
	$(obj).attr('action','WaigouwaiweiPlanAction!shuaixinAllPrice.action?pageStatus=findAll');
	$(obj).submit();
}
		</SCRIPT>
	</body>
</html>
