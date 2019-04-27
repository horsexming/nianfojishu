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
	
	 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/freezeheader-style.css" />
		<%@include file="/util/sonHead.jsp"%>
	 <script language="javascript"  src="<%=basePath %>/js/jquery.freezeheader.js"></script>    
		<STYLE type="text/css">
.window {
	width: 100%;
	height: 50px;
	background: #181818;
<%--	margin-left: -250px;--%>
<%--	left: 50%;--%>
<%--	position: absolute;--%>
}

.noselect {
	width: 100%;
	height: 50px;
	cursor: pointer;
}
.dhlabel{
border-top:1px solid #000;
border-bottom: 1px solid #000;
border-left: 1px solid #000;
border-right: 1px solid #000;
margin-left: 5px;
margin-right: 5px;
padding: 3px 5px;
white-space: nowrap;
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
/****结束**/

</STYLE>
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
					<iframe id="xiugaiIframe" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 500px; margin: 0px; padding: 0px;"></iframe>

				</div>
			</div>
		</div>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				采购订单模式
				<a
					href="WaigouwaiweiPlanAction!findWwPlanList.action?pageStatus=${pageStatus}&tag=${tag}&noOperation=${noOperation}">采购明细模式</a>
				<p align="right">
					<a href="javascript:;" onclick="hidecx()" id="a_hide">隐藏</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="javascript:;" onclick="showcx()" id="a_show"
						style="display: none;">查询</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</p>
					<div align="right">
			<s:if test="pageStatus!='gysall'&&pageStatus!='gysnew'&&pageStatus!='sbdqr'&&pageStatus!='gyssbdqr'">
			<s:if test="pageStatus=='dsq'">
			<label style="background-color: gray;" class="dhlabel" >待申请</label>
			</s:if>
			<s:else>
			<label style="background-color: #5cb85c;" onclick="toShowWW('dsq');" class="dhlabel" ><font color="white">待申请</font></label>
			</s:else>
			<s:if test="pageStatus=='dtz'">
			<label style="background-color: gray;" class="dhlabel"  >待通知</label>
			</s:if>
			<s:else>
			<label style="background-color: #5cb85c;" onclick="toShowWW('dtz');" class="dhlabel" ><font color="white">待通知</font></label>
			</s:else>
			<s:if test="pageStatus=='dqr'">
			<label style="background-color: gray;" class="dhlabel"  >待确认</label>
			</s:if>
			<s:else>
			<label style="background-color: #5cb85c;" onclick="toShowWW('dqr');" class="dhlabel" ><font color="white">待确认</font></label>
			</s:else>
			<s:if test="pageStatus=='findAllself'">
			<label style="background-color: gray;" class="dhlabel"  >所有(自己)</label>
			</s:if>
			<s:else>
			<label style="background-color: #5cb85c;" onclick="toShowWW('findAllself');" class="dhlabel" ><font color="white">所有(自己)</font></label>
			</s:else>
			<s:if test="pageStatus=='findAll'">
			<label style="background-color: gray;" class="dhlabel"  >所有</label>
			</s:if>
			<s:else>
			<label style="background-color: #5cb85c;" onclick="toShowWW('findAll');" class="dhlabel" ><font color="white">所有</font></label>
			</s:else>
			</s:if>
			</div>
				<form
					action="WaigouwaiweiPlanAction!findWwOrderList.action?pageStatus=${pageStatus}&noOperation=${noOperation}"
					method="post">
					<table class="table" id="cxtable">
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
								邮箱:
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
								<SELECT name="waigouOrder.status" id="status">
									<s:if test='pageStatus=="dsq"'>
										<option value="待核对">
											待核对
										</option>
										<option value="待通知">
											待通知
										</option>
									</s:if>
									<s:elseif test='pageStatus=="dtz"'>
										<option value="待通知">
											待通知
										</option>
									</s:elseif>
									<s:elseif test='pageStatus=="dqr" || pageStatus == "gysnew"'>
										<option value="待确认">
											待确认
										</option>
										<option value="协商待确认">
											协商待确认
										</option>
									</s:elseif>
									<s:elseif test='pageStatus=="findAll" || pageStatus== "gysall"'>
									<option value="待核对">
										待核对
									</option>
									<option value="待通知">
										待通知
									</option>
									<option value="待确认">
										待确认
									</option>
									<option value="协商待确认">
										协商待确认
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
									<option value="入库">
										入库
									</option>
								</s:elseif>
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
								采购订单号:
							</th>
							<td>
								<input name="waigouOrder.planNumber"
									value="${waigouOrder.planNumber}" />
							</td>
							<th>
								打印状态
							</th>
							<td>
								<SELECT name="waigouOrder.isprint">
									<option></option>
									<option value="${waigouOrder.isprint}">${waigouOrder.isprint}</option>
									<option value="是">是</option>
									<option value="否">否</option>
								</SELECT>
							</td>
							<th>
								总成件号:
							</th>
							<td>
								<input name="waigouOrder.ywMarkId"
									value="${waigouOrder.ywMarkId}" />
							</td>
						</tr>
						<tr>
							<th>
								内部订单号:
							</th>
							<td>
								<input name="waigouOrder.neiorderNum"
									value="${waigouOrder.neiorderNum}" />
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
							<td colspan="6" align="center">
								<input type="submit" value="查询" class="input">
							</td>
						</tr>
					</table>
				</form>
				<div align="right"><font color="red">注:双击行显示明细</font></div>
				<form id="cgOrderForm" method="post">
					<table style="border-collapse: collapse;" class="gridView" align="left" id="table1"  border="1px solid #999 "   >
						<thead >
							<tr bgcolor="#c0dcf2" height="30px" id="topic" class="window noselect"
								style="border-collapse: separate;" ondblclick="showDetail()">
								<th align="center">
								<s:if test="noOperation!='noOperation'">
									<input type="checkbox" onclick="chageAllCheck(this)"
										id="checkAll">
									全选
								</s:if>
								<s:else>
									序号
								</s:else>
								</th>
								<th align="center">
									供应商
								</th>
								<th align="center">
									采购月份
								</th>
								<th align="center">
									采购订单号:
								</th>
								<th align="center">
									内部订单号
								</th>
								<th align="center">
									总成件号
								</th>
								<th align="center">
									总成数量
								</th>
								<th align="center">
									总成批次
								</th>
								<th align="center">
									状态
								</th>
								<th align="center">
									打印状态
								</th>
<%--								<th align="center" ondblclick="showDetail()">--%>
<%--									<table class="table">--%>
<%--									<tr><th align="center" colspan="4">--%>
<%--									明细(双击)</th>--%>
<%--									</tr>--%>
<%--										<tr>--%>
<%--										<td align="center" width="25%">件号</td>--%>
<%--										<td align="center" width="45%">工序号</td>--%>
<%--										<td align="center" width="15%">数量</td>--%>
<%--										<td align="center" width="15%">到货数</td>--%>
<%--										</tr>--%>
<%--								</table>--%>
<%--								</th>--%>
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
									操作
								</th>
							</tr>
						</thead>
						<s:if test="pageStatus=='dsq'">
							<tr>
								<th colspan="15"
									style="height: 35px; color: #ffffff; background-color: #FFB5B5;">
									待申请采购订单
								</th>
							</tr>
						</s:if>
						<s:elseif test="pageStatus=='dtz'">
							<tr>
								<th colspan="15"
									style="height: 35px; color: #ffffff; background-color: #FFB5B5;">
									待通知采购订单
								</th>
							</tr>
						</s:elseif>
						<s:elseif test="pageStatus=='dqr'">
							<tr>
								<th colspan="15"
									style="height: 35px; color: #ffffff; background-color: #FFB5B5;">
									待确认采购订单
								</th>
							</tr>
						</s:elseif>
						<s:elseif test="pageStatus=='gysnew'">
							<tr>
								<th colspan="15"
									style="height: 35px; color: #ffffff; background-color: #FFB5B5;">
									待确认采购订单
								</th>
							</tr>
						</s:elseif>
						<s:else>
							<tr>
								<th colspan="15"
									style="height: 35px; color: #ffffff; background-color: #CECEFF;">
									历史采购计划
								</th>
							</tr>
						</s:else>
						<s:iterator value="list" id="pageWgww" status="pageStatus">
							<s:if test="#pageStatus.index%2==1">
								<tr align="center" bgcolor="#e6f3fb" style="height: 50px;"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')"
									ondblclick="mxshow('${pageWgww.id}')" >
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									style="height: 50px;" onmouseout="outBgcolor(this,'')"
									ondblclick="mxshow('${pageWgww.id}')" >
							</s:else>
							<input type="hidden" id="detailshow_${pageWgww.id}" value="h"/>
							<td>
								<s:property value="#pageStatus.index+1" />
								<s:if test="noOperation!='noOperation'">
									<input type="checkbox" name="processIds" value="${pageWgww.id}"
										onclick="chageNum(this)">
								</s:if>
							</td>
							<td>
								${pageWgww.gysName}
								<br />
								(${pageWgww.userCode})
							</td>
							<td>

								${pageWgww.caigouMonth}
							</td>
							<td >
								${pageWgww.planNumber}
							</td>
							<td style="width: 150px;" align="left">
								${pageWgww.neiorderNum}
							</td>
							<td>
								${pageWgww.rootMarkId}
								<br />
								<font color="red">(${pageWgww.ywMarkId})</font>
							</td>
							<td>
								${pageWgww.filnalCount}
							</td>
							<td style="width: 100px;">
								${pageWgww.rootSlfCard}
							</td>
							<td>
								${pageWgww.status}
							</td>
							<td>
<%--								${pageWgww.isprint}--%>
								<s:if test='#pageWgww.isprint=="是"'>
									是(${pageWgww.printTime})
								</s:if>
								<s:else>
									否
								</s:else>
							</td>
<%--							<td>--%>
<%--							<label class="hwwDetail" id="hwwDetail_${pageWgww.id}"  ondblclick="showDetailSingle(${pageWgww.id})">双击显示</label>--%>
<%--								<table class="table wwDetail" id="wwDetail_${pageWgww.id}" style="display: none;" ondblclick="hideDetailSingle(${pageWgww.id})">--%>
<%--									<s:iterator value="#pageWgww.wwpList" id="pagewwp">--%>
<%--										<tr>--%>
<%--										<td align="center" width="25%">${pagewwp.markId}</td>--%>
<%--										<td align="center" width="45%">${pagewwp.processNOs}</td>--%>
<%--										<td align="right" width="15%">${pagewwp.number}</td>--%>
<%--										<td align="right" width="15%">${pagewwp.qsNum}</td>--%>
<%--										</tr>--%>
<%--									</s:iterator>--%>
<%--								</table>--%>
<%--							</td>--%>
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
							<td style="width: 200px;">
							<a href="WaigouwaiweiPlanAction!exportExcelWaigouPlan.action?waigouPlan.waigouOrder.id=${pageWgww.id}&tag=ww">导出明细</a>/
							<s:if test="noOperation!='noOperation'">
								<s:if
									test='#session.Users.code==#pageWgww.addUserCode&&(#pageWgww.status=="待通知"||#pageWgww.status=="待确认"|| #pageWgww.status=="生产中")'>
									<a href="javascript:;" onclick="backApply(${pageWgww.id})">反审</a>/
							</s:if>
								<s:if test="pageStatus=='dtz'">
									<a
										href="WaigouwaiweiPlanAction!wworderToTzGys.action?processIds=${pageWgww.id}">发送提醒</a>
								</s:if>
								<s:elseif test="pageStatus=='dsq'">
									<a onclick="addRemark(${pageWgww.id},this)"
										id="remark${pageWgww.id}" href="#">添加备注</a>
<!-- 									<input value="备注" type="button" style="font-size: 10px"  -->
<!-- 										 > -->
									<a
										href="WaigouwaiweiPlanAction!applyWWorder.action?id=${pageWgww.id}">申请审批</a>/
									<s:if test="#pageWgww.epId!=null">
										/<a
											href="CircuitRunAction_findAduitPage.action?id=${pageWgww.epId}">历史审批</a>
									</s:if>
								</s:elseif>
								<s:elseif test="pageStatus=='dqr'">
									<a
										href="WaigouwaiweiPlanAction!wworderToTzGys.action?processIds=${pageWgww.id}">再次提醒</a>/
								<a
										href="WaigouwaiweiPlanAction!wworderQueren.action?processIds=${pageWgww.id}">采购确认</a>/
								</s:elseif>
								<s:elseif test="pageStatus=='gys'">

								</s:elseif>
								<s:else>

								</s:else>
								<a     
									href="WaigouwaiweiPlanAction!gotoprint.action?processIds=${pageWgww.id}&pageStatus=waiwei&tag=${pageStatus}">打印订单</a>/
								<s:if test="tag=='muju'">
									<a href="">打印开模明细</a>
								</s:if>	
								<s:else>
<%--									<s:if test="#pageWgww.status!='待核对'&&#pageWgww.status!='待通知'&&#pageWgww.status!='待确认'&&#pageWgww.status!='协商待确认'">--%>
										<a
											href="WaigouwaiweiPlanAction!finwwOrderwl.action?id=${pageWgww.id}">打印物料单</a>/
<%--									</s:if>--%>
								</s:else>
								<a
									href="WaigouwaiweiPlanAction!findWwPlanList.action?id=${pageWgww.id}&pageStatus=${pageStatus}">采购明细</a>
								<s:if test="pageStatus!='gysall'&&pageStatus!='gysnew'&&pageStatus!='gyssbdqr'&&pageStatus!='gys'&&(#pageWgww.epId!=null||#pageWgww.applystatus=='打回')">
									<a
										href="CircuitRunAction_findAduitPage.action?id=${pageWgww.epId}">审批动态</a>
								</s:if>
								<a href="WaigouwaiweiPlanAction!getwgOrderTz.action?id=${pageWgww.id}">下载图纸</a>
<%--								<s:elseif test="pageStatus=='dsq'">--%>
<%--									/<a--%>
<%--										href="WaigouwaiweiPlanAction!applyWWorder.action?id=${pageWgww.id}">申请审批</a>--%>
<%--								</s:elseif>--%>
								</s:if>
								<s:else>
									<a
									href="WaigouwaiweiPlanAction!findWwPlanList.action?id=${pageWgww.id}&pageStatus=${pageStatus}&noOperation=${noOperation}">采购明细</a>
								</s:else>
							</td>
							</tr>
							<tr id="tr_${pageWgww.id}" style="display: none;" class="wwDetail">
								<td colspan="30">
									<table class="table">
										<s:if test="#pageWgww.wwpList!=null ">
											<tr id="">
												<th>
													件号
												</th>
												<th>
													名称
												</th>
												<th>
													供料属性
												</th>
												<th>
													批次
												</th>
												<th>
													规格
												</th>
												<th>
													图号
												</th>
												<th>工序号</th>
												<th>工序名称</th>
												<th>
													数量
												</th>
												<th>签收数</th>
												<th>入库数</th>
												<th>
													单位
												</th>
											</tr>
										</s:if>
										<s:iterator value="#pageWgww.wwpList" id="waigouPlan"
											status="pagestatus1">
											<tr>
												<td align="left">
													${waigouPlan.markId}
												</td>
												<td align="left">
													${waigouPlan.proName}
												</td>
												<td align="left">
													${waigouPlan.kgliao}
												</td>
												<td align="left">
													${waigouPlan.pcDetail}
												</td>
												<td align="left">
													${waigouPlan.specification}
												</td>
												<td align="center">
													${waigouPlan.tuhao}
												</td>
												<td align="left">
													${waigouPlan.processNOs}
												</td>
												<td align="left">
													${waigouPlan.processNames}
												</td>
												<td align="right">
													${waigouPlan.number}
												</td>
												<td align="right">
													${waigouPlan.qsNum}
												</td>
												<td align="right">
													${waigouPlan.hasruku}
												</td>
												<td align="left">
													${waigouPlan.unit}
												</td>
											</tr>
										</s:iterator>
									</table>
								</td>
							</tr>
						</s:iterator>
						<tr>
						<s:if test="noOperation!='noOperation'">
							<th>
								<input type="checkbox" onclick="chageAllCheck(this)"
									id="checkAll2">
								全选
							</th>
							<td colspan="21" align="left">
								<s:if test="pageStatus=='dtz'">
									<input id="yjtz" type="button"
										style="width: 100px; height: 50px;"
										onclick="toSubmit(this.form,'tz')" value="一键通知" />
								</s:if>
								<s:elseif test="pageStatus=='dqr'">
									<input id="yjqr" type="button"
										style="width: 100px; height: 50px;" value="一键确认"
										onclick="toSubmit(this.form,'qr')" />
								</s:elseif>
								<s:elseif test="pageStatus=='dsq'">
									<input id="yjqr" type="button"
										style="width: 100px; height: 50px;" value="一键申请"
										onclick="toSubmit(this.form,'sq')" />
								</s:elseif>
								<input id="yjtz" type="button"
										style="width: 100px; height: 50px;"
										onclick="toSubmit(this.form,'print')" value="前往打印" />
							</td>
							</s:if>
						</tr>
						<tr>
							<td colspan="21" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
var parentdocument =  window.parent.document;
$(function() {
	duoxuaSelect("status");//下拉多选框
<%--	$("#table1").freezeHeader();--%>
})
<%--parentdocument.onscroll=function(){ --%>
<%--	$("#table1").freezeHeader();--%>
<%--} --%>
function toSubmit(form, type) {
	if (type == 'tz') {
		form.action = "WaigouwaiweiPlanAction!wworderToTzGys.action";
		form.submit();
	} else if (type == 'qr') {
		form.action = "WaigouwaiweiPlanAction!wworderQueren.action?pageStatus=ww";
		form.submit();
	} else if (type == 'sq') {
		form.action = "WaigouwaiweiPlanAction!applyordersq.action?pageStatus=ww";
		form.submit();
	}else if (type == 'print') {
		form.action = "WaigouwaiweiPlanAction!gotoprint.action?pageStatus=waiwei&tag=${pageStatus}";
		form.submit();
	}else {
		alert("非法操作!");
	}
	return true
}
function tanchu(id, gysId) {
	document.getElementById("xiugaiIframe").src = "WaigouwaiweiPlanAction!toxiugaigys.action?id="
			+ id + "&id2=" + gysId;
	chageDiv('block');

}
function backApply(id) {
	if (confirm("确认反审此订单?")) {
		window.location.href = "WaigouwaiweiPlanAction!backApply.action?tag=ww&id="
				+ id;
	}
}
function mxshow(id) {
	if (id != '') {
		if($("#detailshow_"+id).val()=="h"){
			$("#tr_" + id).show();
			$("#detailshow_"+id).val("s");
		}else{
			$("#tr_" + id).hide();
			$("#detailshow_"+id).val("h");
		}
	}
}
function showcx() {
	$("#cxtable").show();
	$("#a_hide").show();
	$("#a_show").hide();
}
function hidecx() {
	$("#cxtable").hide();
	$("#a_hide").hide();
	$("#a_show").show();
}

//可拖动div的js

   var clicked = "Nope.";
        var mausx = "0";
        var mausy = "0";
        var winx = "0";
        var winy = "0";
        var difx = mausx - winx;
        var dify = mausy - winy;

        $("html").mousemove(function (event) {
            mausx = event.pageX;
            mausy = event.pageY;
            winx = $(".window").offset().left;
            winy = $(".window").offset().top;
            if (clicked == "Nope.") {
                difx = mausx - winx;
                dify = mausy - winy;
            }

            var newx = event.pageX - difx - $(".window").css("marginLeft").replace('px', '');
            var newy = event.pageY - dify - $(".window").css("marginTop").replace('px', '');
            $(".window").css({ top: newy, left: newx });

           // $(".container").html("Mouse Cords: " + mausx + " , " + mausy + "<br />" + "Window Cords:" + winx + " , " + winy + "<br />Draggin'?: " + clicked + "<br />Difference: " + difx + " , " + dify + "");
        });

        $(".noselect").mousedown(function (event) {
            clicked = "Yeah.";
        });

        $("html").mouseup(function (event) {

            clicked = "Nope.";
        });
function toShowWW(pStatus){
	window.location.href="WaigouwaiweiPlanAction!findWwOrderList.action?pageStatus="+pStatus+"&tag=${tag}"+"&noOperation=${noOperation}";
}
var detailShow="h";
function showDetail(){
	if(detailShow=="h"){
		$(".wwDetail").show();
		//$(".hwwDetail").hide();
		detailShow="s";
	}else{
		$(".wwDetail").hide();
		//$(".hwwDetail").show();
		detailShow="h";
	}
}
function showDetailSingle(index){
	$("#wwDetail_"+index).show();
	$("#hwwDetail_"+index).hide();
}
function hideDetailSingle(index){
	$("#hwwDetail_"+index).show();
	$("#wwDetail_"+index).hide();
}
//添加备注
function addRemark(id,obj){
	$("#title").text("添加备注");
	document.getElementById("xiugaiIframe").src = "WaigouwaiweiPlanAction!findWaigouOrderById.action?id="+ id;
	chageDiv('block');
}
</script>
	</body>
</html>
