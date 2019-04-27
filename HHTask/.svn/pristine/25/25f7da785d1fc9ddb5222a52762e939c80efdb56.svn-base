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
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<div id="msgDiv" style="color: red"></div>
					<table class="table">
						<tr>
							<th colspan="4">添加项目建议书</th>
						</tr>
						<tr>
							<th>客户名称</th>
							<td> ${proposal.customerName}</td>
							<th>项目建议书编号</th>
							<td>${proposal.serialNumber} </td>
						</tr>
						<tr>
							<th>客户介绍</th>
							<td colspan="3">${proposal.customerAbout}</td>
						</tr>
						<tr>
							<th>市场定向</th>
							<td colspan="3">
								${proposal.pricePosition}
							</td>
						</tr>
						<tr>
							<th>产量预测</th>
							<td> ${proposal.forecast}</td>
							<th>生产基地</th>
							<td>${proposal.productionBase} </td>
						</tr>
						<tr>
							<th>市场对标车型</th>
							<td>${proposal.marketcar} </td>
							<th>工艺对标车型</th>
							<td>${proposal.technologycar} </td>
						</tr>
						<tr>
							<th>保密协议</th>
							<td>
								${proposal.confidentiality}
							</td>
							<th>工艺协议</th>
							<td>
								${proposal.crafts}
							</td>
						</tr>
						<tr>
							<th>产品数模</th>
							<td>
								${proposal.digifax}
							</td>
							<th>产品图纸</th>
							<td>
								${proposal.drawing}
							</td>
						</tr>
						<tr>
							<th rowspan="3">项目审核</th>
							<td>
								基本部门<br/>
								
							</td>
							<td colspan="2">
								<s:iterator value="proposal.check" id="pflow">
									<s:if test="#pflow.level == 1">
										${pflow.username}&emsp;
									</s:if>
								</s:iterator>
							</td>
						</tr>
						<tr>
							<td>
								副总经理名单<br/>
							</td>							
							<td colspan="2">
								<s:iterator value="proposal.check" id="pflow">
									<s:if test="#pflow.level == 2">
										${pflow.username} &emsp;
									</s:if>
								</s:iterator>
							</td>
						</tr>
						<tr>
							<td>
								总经理<br/>
							</td>
							<td colspan="2">
								<s:iterator value="proposal.check" id="pflow">
									<s:if test="#pflow.level == 3">
										${pflow.username}&emsp;
									</s:if>
								</s:iterator>
							</td>						
						</tr>
						
						<tr>
							<th>项目建议</th>
							<td colspan="3">${proposal.descriptions}</td>
						</tr>
						<tr>
							<td align="center" colspan="4" >
								<a href="ProjectProposalFlow_check.action?proposal.id=${proposal.id}">审核</a>
								<a href="ProjectProposalFlow_list.action?proposal.id=${proposal.id}">查看</a>
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
