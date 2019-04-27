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
				<h3>
					项目明细
				</h3>
				<table class="table" id="zptable">
					<tr>
						<th>
							序号
						</th>
						<th>
							项目池名称
						</th>
						<th>
							项目编号
						</th>
						<th>
							项目名称
						</th>
						<th>
							负责人
						</th>
						<th>开始时间</th>
						<th>预完成时间</th>
						<th>实际完成时间</th>
						<th>
							项目分数							
						</th>
						<th>
							项目占比
						</th>
						<th>
							金额
						</th>
						<th align="center">申请延期时间</th>
						<th align="center">审批人</th>
						<th align="center">申请延期状态</th>
						<th>状态</th>
<%--						<th >已完成项目</th>--%>
<%--						<th>--%>
<%--							操作--%>
<%--						</th>--%>
					</tr>
					<tr>
						<td align="center">1</td>
						<td align='center'>${projectManageyf.projectPool.poolName}</td>
						<td align="center">${projectManageyf.proNum}</td>
						<td align="center">${projectManageyf.proName}</td>
						<td align="center">${projectManageyf.principals}</td>
						<td align="center">${projectManageyf.startTime}</td>
						<td align="center">${projectManageyf.reTime}</td>
						<td align="center">${projectManageyf.shijiTime}</td>
						<td align="center">${projectManageyf.gradeStore}</td>
						<td align="center">${projectManageyf.proportion}</td>
						<td align="center">${projectManageyf.money}(元)</td>
						<td align="center">
							<s:if test="null!=projectManageyf.outTime">
								${projectManageyf.outTime}(天)
							</s:if>
						</td>
						<td align="center">${projectManageyf.outTimeexplainPerson}</td>
						<td align="center">${projectManageyf.outTimeResult}</td>
						<td align="center">${projectManageyf.status}</td>
<%--						<td align="center">${projectManageyf.complete}/${projectManageyf.sumCount}</td>--%>
<%--						<td align="center">--%>
<%--							<input type="button" value="查看参与人" onclick="getPlayers(${projectManageyf.id})">--%>
<%--						</td>--%>
					</tr>
				</table>
				<br/>
				<br/>
				<h3>项目参与人</h3>
				<table class="table" id="showPlayers">
					<tr>
						<th>编号</th>
						<th>工号</th>
						<th>姓名</th>
						<th>开始时间</th>
						<th>权重</th>
						<th>占比</th>
						<th>所得金额</th>
					</tr>
					<s:if test="null!=pdList && pdList.size()>0">
						<s:iterator value="pdList" id="pd" status="see">
							<s:if test="#see.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
								<td>${see.index+1}</td>
								<td>${pd.userId}</td>
								<td>${pd.userName}</td>
								<td>${pd.startTime}</td>
								<td>
									<s:if test="null!=#pd.weight">
										${pd.weight}%
									</s:if>
								</td>
								<td>${pd.proportion}</td>
								<td align="right">
									<s:if test="null!=#pd.money">
										${pd.money}(元)
									</s:if>
								</td>
							</tr>
						</s:iterator>
					</s:if>
					<s:else>
						<td colspan="21" style="font-size: 15px; color: red">
							对不起，没有查到本项目研发人员信息
						</td>
					</s:else>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
	</body>
</html>
