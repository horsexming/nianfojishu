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
				<form action="AskForLeaveAction!selectAllByLeavePage.action"
					method="post">
					<input name="pageStatus" value="${pageStatus}" type="hidden" />
					<input name="finAll&askForLeave.submitPersonDept" value="${askForLeave.submitPersonDept}" type="hidden" />
					<table class="table">
					    <tr>
					        <th colspan="4" align="center">查询请假</th>
					    </tr>
						<tr>
							<td>
								姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:
								<input type="text" name="askForLeave.leavePerson" />
							</td>
							<td>
								工&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号:
								<input type="text" name="askForLeave.leavePersonCode" />
							</td>
							<td>
								部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门:
								<input type="text" name="askForLeave.leavePersonDept" />
							</td>
							<td>
								天&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;数:
								<input type="text" name="askForLeave.leaveDays" />
							</td>
						</tr>
						<tr>
							<td>
								请假类型:
								<input type="text" name="askForLeave.leaveType" />
							</td>
							<td>
								开始时间:
								<input type="text" name="askForLeave.leaveStartDate" class="Wdate" 
								onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"/>
							</td>
							<td>
								结束时间:
								<input type="text" name="askForLeave.leaveEndDate" class="Wdate" 
								onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"/>
							</td>
							<td>
								假事类型:
								<input type="text" name="askForLeave.leaveTypeOf" />
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<input type="submit" class="input" value="查询" />
								<input type="reset" class="input" value="重置" />
							</td>
						</tr>
					</table>
				</form>
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
							<a href="CircuitRunAction_findAduitPage.action?id=${pageAskForLeave.epId}">审批流程</a>
						</td>
						</tr>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="16" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="16" align="center" style="color: red">
						</s:else>
						</td>
					</tr>
				</table>
				<div align="left">
				</div>
			</div>
			<div align="left">
				<br>
			</div>
		</div>
		<div align="left">
			<%@include file="/util/foot.jsp"%>
			<center></center>
			<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		</div>
	</body>
</html>
