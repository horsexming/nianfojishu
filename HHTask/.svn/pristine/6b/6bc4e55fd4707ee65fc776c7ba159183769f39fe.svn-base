<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="java.text.SimpleDateFormat"%>
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
					个人研发项目管理
				</h3>
				<div>
					<form action="projectPoolAction_selfProjectManageyfList.action?pageStatus=applychoose" method="post">
						<table class="table" >
							<tr>
								<th>
									项目编号：
								</th>
								<td>
									<input type="text" name="projectManageyf.proNum"
										value="${projectManageyf.proNum}" />
								</td>
								<th>
									项目名称：
								</th>
								<td>
									<input type="text" name="projectManageyf.proName"
										value="${projectManageyf.proName}" />
								</td>
								<th>
									指派时间：
								</th>
								<td>
									<input type="text" 
										value="${projectManageyf.zpTime}" name="projectManageyf.zpTime"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
<%--									到--%>
<%--									<input type="text" --%>
<%--										value="${zpEndDate}" name="zpEndDate"--%>
<%--										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />--%>
								</td>
							</tr>
							<tr>
								<th>
									添加人：
								</th>
								<td>
									<input type="text" name="projectManageyf.addUserName"
										value="${projectManageyf.addUserName}" />
								</td>
								<th>
									状态：
								</th>
								<td>
									<input type="text" name="projectManageyf.status"
										value="${projectManageyf.status}" />
								</td>
								<th>
									预完成时间：
								</th>
								<td>
									<input type="text" 
										value="${projectManageyf.reTime}" name="projectManageyf.reTime"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
<%--									到--%>
<%--									<input type="text" --%>
<%--										value="${reEndDate}" name="reEndDate"--%>
<%--										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />--%>
								</td>
							</tr>
							<tr>
								<td colspan="11" align="center">
									<input type="submit" value="查询"
										style="width: 70px; height: 40px;" />
								</td>
							</tr>
						</table>
					</form>
				</div>
				<table class="table" id="zptable">
					<tr>
						<td align="center">
							序号
						</td>
						<td align="center">
							项目编号
						</td>
						<td align="center">
							项目名称
						</td>
						<td align="center">
							负责人
						</td>
						<td align="center">
							预完成时间
						</td>
						<td align="center">实际完成时间</td>
						<td align="center">
							备注
						</td>
						<td align="center">
							评分分数
						</td>
						<td align="center">
							项目状态
						</td>
						<td align="center">申请延期时间</td>
						<td align="center">审批人</td>
						<td align="center">申请延期状态</td>
						<td align="center">
							操作
						</td>
					</tr>
					<s:if
						test="projectManageyfList!=null&&projectManageyfList.size()>0">
						<s:iterator value="projectManageyfList" id="project"
							status="yfStatus">
							<s:if test="#yfStatus.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:property value="#yfStatus.index+1" />
							</td>
							<td>
								<s:property value="#project.proNum" />
							</td>
							<td>
								<s:property value="#project.proName" />
							</td>
							<td>
								<s:property value="#project.principals" />
							</td>
							<td>
								<s:property value="#project.reTime" />
							</td>
							<td><s:property value="#project.shijiTime" /></td>
							<td>
								<s:property value="#project.remark" />
							</td>
							<td>
								<s:property value="#project.gradeStore" />
							</td>
							<td><s:property value="#project.status" /></td>
							<td>
								<s:if test="null!=#project.outTime">
									<s:property value="#project.outTime" />(天)
								</s:if>
							</td>
							<td><s:property value="#project.outTimeexplainPerson" /></td>
							<td><s:property value="#project.outTimeResult" /></td>
							<td>
								<s:if test="#project.rootId==#project.id || null ==#project.chooseStatus " >
									<s:if test="'完成'==#project.status">
										项目完成时间：<b>${project.shijiTime}</b>
<%--										<input type="button" value="项目分值" --%>
<%--										onclick="location.href='projectPoolAction_getStoreResult.action?id=${project.id}'"/>--%>
<%--										<input type="button" value="文件与方案" --%>
<%--										onclick="location.href='projectPoolAction_gotoFillSchedule.action?pageStatus=${pageStatus}&id=${project.id}&status=ok'"/>--%>
									</s:if>
									<s:elseif test="#project.chooseStatus=='同意'">
										<input type="button" value="填报进度" 
										onclick="location.href='projectPoolAction_gotoFillSchedule.action?pageStatus=${pageStatus}&id=${project.id}'"/>
									</s:elseif>
									<s:elseif test="'待执行'==#project.status || '执行中'==#project.status || '待完善'==#project.status ||
									 '重新执行'==#project.status || '待确认'==#project.status">
										<input type="button" value="填报进度" 
										onclick="location.href='projectPoolAction_gotoFillSchedule.action?pageStatus=${pageStatus}&id=${project.id}'"/>
									</s:elseif>
									<s:else>
										<input type="button" value="报选项目" 
										onclick="location.href='projectPoolAction_toAddSubProject.action?pageStatus=${pageStatus}&id=${project.id}'"/>
									</s:else>
								</s:if>
								<s:else>
									<s:if test="'完成'==#project.status">
										项目完成时间：<b>${project.shijiTime}</b>
<%--										<input type="button" value="项目分值" --%>
<%--										onclick="location.href='projectPoolAction_getStoreResult.action?id=${project.id}'"/>--%>
<%--										<input type="button" value="文件与方案" --%>
<%--										onclick="location.href='projectPoolAction_gotoFillSchedule.action?pageStatus=${pageStatus}&id=${project.id}&status=ok'"/>--%>
									</s:if>
									<s:else>
										<input type="button" value="填报进度" 
										onclick="location.href='projectPoolAction_gotoFillSchedule.action?pageStatus=${pageStatus}&id=${project.id}'"/>
									</s:else>
								</s:else>
<%--								<%if(new Date.getTime()>=(new SimpleDateFormat().format(%>${project.reTime}<% )).getTime()){--%>
<%--									%>--%>
<%--									--%>
<%--									<%--%>
<%--								} %>--%>
<%--								<s:if test="new Date().getTime()>=(new SimpleDateFormat().format(#project.reTime)).getTime()">--%>
								<s:if test="'完成'!=#project.status">
									<a href="projectPoolAction_showSubGantt.action?id=${project.id}" target="_blank"><input type="button" value="项目进度图" ></a>
									<input type="button" value="申请延期" 
									onclick="location.href='${pageContext.request.contextPath}/projectPoolAction_applyForPostpone.action?pageStatus=${pageStatus}&id=${project.id}'"/>
								</s:if>
<%--								</s:if>--%>
							</td>
	
							</tr>
						</s:iterator>
						<tr>
							<td colspan="15" align="right">
								共
								<s:property value="total" />
								页 第
								<s:property value="cpage" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
							</td>
						</tr>
					</s:if>
					<s:else>
						<tr>
							<td colspan="13" style="font-size: 15px; color: red;">
								对不起，没有查到相关信息
							</td>
						</tr>
					</s:else>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
	</body>
</html>
