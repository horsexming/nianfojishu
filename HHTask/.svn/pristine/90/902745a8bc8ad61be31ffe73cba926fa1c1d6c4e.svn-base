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
	<body onload="createDept('dept')">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng">
			<div align="center">
				<h3>
					个人请假查询
				</h3>
				<form id="testFrom" action="SuspsomAction_qingjia.action" method="post">
					<input name="pageStatus" value="${pageStatus}" type="hidden" />
					<table class="table">
						<tr>
							<td align="center">
								请假类型:
								<SELECT name="askForLeave.leaveType" style="width: 153px;">
									<option value="">--请假类型--
									</option>
									<option value="个人请假">个人请假
									</option>
									<option value="代理请假">代理请假
									</option>
								</SELECT>
							</td>
							<td align="center">
								时间从:
								<input type="text" name="startDate" class="Wdate"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<td align="center">
								到:
								<input type="text" name="endDate" class="Wdate"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<td align="center">
								假事类型:
								<SELECT name="askForLeave.leaveTypeOf" style="width: 153px;">
									<option value="">--假事类型--
									</option>
									<option value="病假">
										病假
									</option>
									<option value="事假">
										事假
									</option>
									<option value="公出">
										公出
									</option>
									<option value="换休">
										换休
									</option>
									<option value="年休">
										年休
									</option>
									<option value="婚假">
										婚假
									</option>
									<option value="丧假">
										丧假
									</option>
									<option value="公假">
										公假
									</option>
									<option value="产假">
										产假
									</option>
									<option value="工伤假">
										工伤假
									</option>
								</SELECT>
							</td>
							<td align="center">
								<input type="submit" style="height: 38px;width: 56px;" value="查询" />
								<input type="button"  style="height: 38px;width: 66px;" onclick="qingjia()" value="请假申请" />
								<input class="input" onclick="window.history.back();" type="button" value="返回"/>
							</td>
						</tr>
					</table>
				</form>
				<h1>
					请假管理
				</h1>
				<table width="100%" border="0" style="border-collapse: collapse;"
					class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th>
							序号
						</th>
						<th>
							请假类型
						</th>
						<th>
							请假人
						</th>
						<th>
							请假人工号
						</th>
						<th>
							请假人所在部门
						</th>
						<th>
							请假开始时间
						</th>
						<th>
							请假结束时间
						</th>
						<th>
							请假天数
						</th>
						<th>
							请假小时数
						</th>
						<th>
							假事类型
						</th>
						<th>
							请假原因
						</th>
						<th>
							提交人
						</th>
						<th>
							出门时间
						</th>
						<th>
							返回时间
						</th>
						<th>
							审批状态
						</th>
						<th>
							审批动态
						</th>
					</tr>
					<tr>
						<td colspan="15" align="center">
							请假历史记录
						</td>
					</tr>
					<s:iterator value="#attr.askList" id="pageAskForLeave"
						status="pageStatus">
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
							<s:property value="#pageStatus.index+1" />
						</td>
						<td>
							${pageAskForLeave.leaveType}
						</td>
						<td>
							${pageAskForLeave.leavePerson}
						</td>
						<td>
							${pageAskForLeave.leavePersonCode}
						</td>
						<td>
							${pageAskForLeave.leavePersonDept}
						</td>
						<td>
							${pageAskForLeave.leaveStartDate}
						</td>
						<td>
							${pageAskForLeave.leaveEndDate}
						</td>
						<td>
							${pageAskForLeave.leaveDays}
						</td>
						<td>
							${pageAskForLeave.leaveHours}
						</td>
						<td>
							${pageAskForLeave.leaveTypeOf}
						</td>
						<td>
							${pageAskForLeave.leaveReason}
						</td>
						<td>
							${pageAskForLeave.submitPerson}
						</td>
						<td>
							${pageAskForLeave.exitTime}
						</td>
						<td>
							${pageAskForLeave.returnTime}
						</td>
						<td>
							${pageAskForLeave.approvalStatus}
						</td>
						<td>
							<a
								href="CircuitRunAction_findAduitPage.action?id=${pageAskForLeave.epId}">审批流程</a>
						</td>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="17" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="17" align="center" style="color: red">
						</s:else>
					</tr>
				</table>
			</div>
		</div>
		<div align="left">
			<%@include file="/util/foot.jsp"%>
			<center></center>
			<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		</div>
		<script type="text/javascript">
function back1() {
	window.location.href = "SuspsomAction_xuanzhe.action?ipAddress=${ipAddress}&tag=${tag}";
}
function qingjia() {
	window.location.href = "AskForLeaveAction!preAskForLeave.action?askForLeave.appayTag=A&pageStatus=one&ipAddress=${ipAddress}&tag=${tag}";
}
</script>
	</body>
</html>
