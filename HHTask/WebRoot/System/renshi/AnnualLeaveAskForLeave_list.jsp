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
					<a href="annualLeave!gerennianxiumingxi.action" style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center" id="d1">
				<table class="table" style="width: 50%;">
					<tr>
						<th colspan="2">
							年休管理
						</th>
					</tr>
					<tr>
						<th align="right" width="50%">
							工号：
						</th>
						<td>
							${al.jobNum}
						</td>
					</tr>
					<tr>
						<th align="right">
							姓名：
						</th>
						<td>
							${al.name}
						</td>
					</tr>
					<tr>
						<th align="right">
							部门：
						</th>
						<td>
							${al.dept}
						</td>
					</tr>
					<tr>
						<th align="right">
							年休调整：
						</th>
						<td>
							${al.standardAnnualLeave}
						</td>
					</tr>
					<tr>
						<th align="right">
							累计可用年休：
						</th>
						<td>
							${al.surplus}
						</td>
					</tr>
					<tr>
						<th align="right">
							工龄：
						</th>
						<td>
							${al.lengthOfService}
						</td>
					</tr>
					<tr>
						<th align="right">
							备注：
						</th>
						<td rowspan="2">
							${al.remark}
						</td>
					</tr>
				</table>
				<br />
				<br />
				<br />

				<table class="table">
					<tr>
						<th align="center" colspan="18" style="size: 15px;">
							<font style="size: 12px;">年休记录</font>
						</th>
					</tr>
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
							提交人工号
						</th>
						<th>
							提交人所在部门
						</th>
						<th>
							审批状态
						</th>
					</tr>
					<s:iterator value="list" id="pageAskForLeave" status="pageStatus">
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
							${pageAskForLeave.submitPersonCode}
						</td>
						<td>
							${pageAskForLeave.submitPersonDept}
						</td>
						<td>
							${pageAskForLeave.approvalStatus}
						</td>

						</tr>
					</s:iterator>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->

	</body>
	<SCRIPT type="text/javascript">
 function f1(){
	    window.location.href="<%=basePath%>/System/renshi/caozuonengli/shebeiAndEmp_add.jsp";
	}
	</SCRIPT>

</html>
