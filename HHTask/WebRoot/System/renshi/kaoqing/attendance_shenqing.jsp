<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
		<SCRIPT type="text/javascript">
	$(function() {
	var errorMessage = '${errorMessage}';
	if (errorMessage != "") {
		alert(errorMessage);
		parent.location.reload(true);//刷新父页面
	}
})
		</SCRIPT>
	</head>
	<body bgcolor="#ffffff">
		<center>
			<div id="gongneng"
				style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
				<s:if test="tag=='code'">
					<div align="center" id="showScoreDiv" style="index: 0">
						<form action="AttendanceAction!updateShenqing.action?tag=${tag}"
							method="post" onsubmit="return adddata()">
							<input type="hidden" name="attendance.attendanceId"
								value="${attendance.attendanceId}">
							<table class="table" width="800px;">
								<tr>
									<td>
										姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：
									</td>
									<td>
										${attendance.personName}
									</td>
									<td>
										部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门：
									</td>
									<td>
										${attendance.deptName}
									</td>
								</tr>
	
								<tr>
									<td>
										考勤日期：
									</td>
									<td>
										${attendance.dateTime}
									</td>
									<td>
										卡&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：
									</td>
									<td>
										${attendance.cardNo}
									</td>
								</tr>
								<tr>
									<td>
										班次信息：
									</td>
									<td colspan="3">
										${attendance.show}
									</td>
								</tr>
								<s:iterator value="attendanceFuList" id="attenFu" status="pageStatus">
									<tr align="center" >
										<td align="left">
											第<s:property value="#pageStatus.index+1"/>段上班时间：
										</td>
										<td align="left">
											${attenFu.workDateTime}
										</td>
										<td align="left">
											第<s:property value="#pageStatus.index+1"/>段下班时间：
										</td>
										<td align="left">
											${attenFu.closingDateTime}
										</td>
								</s:iterator>
								<tr>
									<td>
										上班时间：
									</td>
									<td>
										<input type="text" id="workDateTime" name="attendance.workDateTime"
											value="${attendance.workDateTime}" 
											class="Wdate" onclick="WdatePicker({dateFmt:'HH:mm',skin:'whyGreen'})" />
											<font color="red">*</font>
									</td>
									<td>
										下班时间：
									</td>
									<td>
										<input type="text" id="closingDateTime" name="attendance.closingDateTime"
											value="${attendance.closingDateTime}" 
											class="Wdate" onclick="WdatePicker({dateFmt:'HH:mm',skin:'whyGreen'})" />
											<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<td>
										说明：
									</td>
									<td colspan="3">
										<textarea cols="88" name="attendance.more"></textarea>
									</td>
								</tr>
								<tr>
									<td colspan="4" align="center">
										<input id="tijiao" type="submit" value="申请" class="input" />
										&nbsp;&nbsp;
										<input type="reset" value="重置" class="input" />
									</td>
								</tr>
							</table>
						</form>
					</div>
				</s:if>
				<br>
				<s:if test="overtimeList!=null&&overtimeList.size!=0">
				<div>
				<table width="100%" border="0" style="border-collapse: collapse;"
					class="table">
					<tr><td colspan="10" align="center">加班申请记录</td></tr>
					<tr bgcolor="#c0dcf2" height="20px">
						<th>
							序号
						</th>
						<th>
							姓名
						</th>
						<th>
							开始加班时间
						</th>
						<th>
							结束时间
						</th>
						<th>
							实际开始加班时间
						</th>
						<th>
							实际结束时间
						</th>
						<th>
							中途休息（分钟）
						</th>
						<th>
							状态
						</th>
						<th>
							加班时长（小时）
						</th>
						<th>
							加班说明
						</th>
					</tr>
					<s:iterator value="overtimeList" id="overTime" status="pageStatus">
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
							${overTime.overtimeName}
						</td>
						<td>
							${overTime.startDate}
						</td>
						<td>
							${overTime.endDate}
						</td>
						<td>
							${overTime.filnalStartDate}
						</td>
						<td>
							${overTime.filnalEndDate}
						</td>
						<td>
							${overTime.xiuxi}
						</td>
						<td>
							${overTime.status}
						</td>
						<td>
							${overTime.overTimeLong}
						</td>
						<td>
							${overTime.shuoming}
						</td>
					</s:iterator>
					</table>
				</div>
				</s:if>
				<s:if test="askForLeaveList!=null&&askForLeaveList.size!=0">
					<div>
				<table width="100%" border="0" style="border-collapse: collapse;"
					class="table">
					<tr><td colspan="10" align="center">请假记录</td></tr>
					<tr bgcolor="#c0dcf2" height="20px">
						<th>
							序号
						</th>
						<th>
							姓名
						</th>
						<th>
							请假类型
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
							请假共计小时数
						</th>
						<th>
							假事类型
						</th>
						<th>
							请假缘由
						</th>
						<th>
							审批状态
						</th>
						
					</tr>
					
					<s:iterator value="askForLeaveList" id="aForLeave" status="pageStatus">
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
							${aForLeave.leavePerson}
						</td>
						<td>
							${aForLeave.leaveType}
						</td>
						<td>
							${aForLeave.leaveStartDate}
						</td>
						<td>
							${aForLeave.leaveEndDate}
						</td>
						<td>
							${aForLeave.leaveDays}
						</td>
						<td>
							${aForLeave.leaveHours}
						</td>
						<td>
							${aForLeave.leaveTypeOf}
						</td>
						<td>
							${aForLeave.leaveReason}
						</td>
						<td>
							${aForLeave.approvalStatus}
						</td>
					</s:iterator>
					</table>
				</div>
				</s:if>
<%--				考勤记录--%>
				<s:if test="attendanceTowList!=null&&attendanceTowList.size!=0">
					<div>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="30px">
						<td align="center">
							序号
						</td>
						<td align="center">
							姓名
						</td>
						<td align="center">
							部门
						</td>
						<td align="center">
							卡号
						</td>
						<td align="center">
							时间
						</td>
						<td align="center">
							状态
						</td>
						<td align="center">
							打卡类型
						</td>
						<td align="center">
							位置
						</td>
					</tr>
					<s:iterator value="attendanceTowList" id="samples"
						status="pageStatus">
						<tr align="center"
							style="height: 25px;">
						<td>
							<s:property value="#pageStatus.index+1" />
						</td>
						<td align="center">
							${samples.name}
						</td>
						<td align="center">
							${samples.dept}
						</td>
						<td align="center">
							${samples.cardId}
						</td>
						<td align="center">
							${samples.dateTime} ${samples.time}
						</td>
						<td align="center">
							正常
						</td>
						<td align="center">
							${samples.outInDoor}
						</td>
						<td align="center">
							${samples.downAddress}
						</td>
						</tr>
					</s:iterator>
				</table>
				</div>
				</s:if>
<%--				进出记录--%>
				<s:if test="accessRecordstList!=null&&accessRecordstList.size!=0">
				<div>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="30px">
						<td align="center">
							序号
						</td>
						<td align="center">
							工号
						</td>
						<td align="center">
							姓名
						</td>
						<td align="center">
							卡号/车牌号
						</td>
						<td align="center">
							内部/来访
						</td>
						<td align="center">
							进出类型
						</td>
						<td align="center">
							进出状况
						</td>
						<td align="center">
							进出行道
						</td>
						<td align="center">
							出入时间
						</td>
					</tr>
					<s:iterator value="accessRecordstList" id="samples"
						status="pageStatus">
						<tr align="center"
							>
						<td>
							<s:property value="#pageStatus.index+1" />
						</td>
						<td align="center">
							${samples.inCode}
						</td>
						<td align="center">
							${samples.inName}
						</td>
						<td align="center">
							${samples.recordContents}
						</td>
						<td align="center">
							${samples.recordisIn}
						</td>
						<td align="center">
							${samples.openType}
						</td>
						<td align="center">
							${samples.recordStatus}
						</td>
						<td align="center">
							${samples.equipmentDaoType}
						</td>
						<td align="center">
							${samples.addTime}
						</td>
					</s:iterator>
				</table>
				</div>
				</s:if>
				<br/>
			</div>
		</center>
		<script type="text/javascript">
function changvalue2(){
}
function adddata() {
	if (!validateText_1("workDateTime", "上班时间")) {
		return false;
	}
	if (!validateText_1("closingDateTime", "下班时间")) {
		return false;
	}
	$("#tijiao").attr("disabled", "disabled");
}


function validateText_1(id, textname) {
	var textValue = $.trim($("#" + id).val());
	if (textValue == null || textValue == "") {
		$("#" + id).focus();
		alert(textname + "不能为空");
		return false;
	}
	return true;
}

</script>
	</body>
</html>
