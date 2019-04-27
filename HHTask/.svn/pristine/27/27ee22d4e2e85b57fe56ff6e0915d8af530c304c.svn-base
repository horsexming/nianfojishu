<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
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
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/css/bootstrap.min.css">
			<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/css/mobiscroll.core-2.5.2.css">
		<script src="${pageContext.request.contextPath}/javascript/jquery/jquery-3.2.1.js"> </script> 
 		<script type="text/javascript"
 			src="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/js/bootstrap.js"> </script> 
<!--  		<script type="text/javascript" -->
<%--  			src="${pageContext.request.contextPath}/js/mobiscroll.core-2.5.2.js"> </script>  --%>
		<script type="text/javascript">
			// 现在window.$和window.jQuery是3.2.1版本:
			console.log($().jquery); // => '3.2.1'
			var $jq = jQuery.noConflict(true);
			// 现在window.$和window.jQuery被恢复成1.5版本:
			console.log($().jquery); // => '1.5.0'
		
		</script>
		<SCRIPT type="text/javascript">
	$(function() {
	var errorMessage = '${errorMessage}';
	if (errorMessage != "") {
		alert(errorMessage);
		parent.location.reload(true);//刷新父页面
	}
})
		</SCRIPT>
		<style type="text/css">
			#tooltip{
			    position:absolute;
			    border:1px solid #333;
			    background:#f7f5d1;
			    padding:1px;
			    color:#333;
			    display:none;
			}
		</style>
	</head>
	<body bgcolor="#ffffff" onload="zhuzhuang2()">
		<center>
			<div id="gongneng"
				style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
				<s:if test="tag=='admins'">
					<div align="center" id="showScoreDiv" style="index: 0">
						<form action="AttendanceAction!updateAttenceById.action?tag=${tag}"
							method="post" onsubmit="return adddata()">
							<input type="hidden" name="attendance.attendanceId"
								value="${attendance.attendanceId}">
							<table class="table" width="800px;">
								<!-- 上班时间段 -->
								<input type="hidden" id="bancilistSize" value="${fn:length(banciTimeList)}" >
								<s:iterator value="attendanceFuList" id="attenFu" status="pageStatus">
								<s:iterator value="banciTimeList" id="banci" status="banciStatus">
									<s:if test="#pageStatus.index==#banciStatus.index">
										<tr align="center" >
											<td align="left" class="col-xs-2">
												第<s:property value="#pageStatus.index+1"/>段上班时间：
												<input class="Wdate" type="hidden" name="attendance.fu[<s:property value="#pageStatus.index"/>].id"
													value="${attenFu.id}"/>
											</td>
											<td align="left" class="col-xs-4">
												<input class="Wdate" type="text" name="attendance.fu[<s:property value="#pageStatus.index"/>].workDateTime"
													value="${attenFu.workDateTime}" id="attendanceStart_${pageStatus.index}"
													onclick="WdatePicker({dateFmt:'HH:mm',skin:'whyGreen'})" onchange="checkAttenanceStatus()"/>
													&nbsp;&nbsp;
													<span class="tooltip-test btn btn-default" data-toggle="tooltip"  title="使用该班次时间${banci.startTime}"
														 onclick="qieHuanDate(this)" 
														data-placement="right" style="cursor:pointer">${banci.startTime }</span>
													<input type="hidden" value="${banci.startTime}" id="banciStart_${pageStatus.index}">
											</td>
											<td align="left" class="col-xs-2">
												第<s:property value="#pageStatus.index+1"/>段下班时间：
											</td>
											<td align="left"  class="col-xs-4">
												<input class="Wdate" type="text" name="attendance.fu[<s:property value="#pageStatus.index"/>].closingDateTime"
													value="${attenFu.closingDateTime}" id="attendanceEnd_${pageStatus.index}"
													onclick="WdatePicker({dateFmt:'HH:mm',skin:'whyGreen'})"  onchange="checkAttenanceStatus()"/>
													&nbsp;&nbsp;
													<span class="tooltip-test btn btn-default" data-toggle="tooltip" title="使用该班次时间${banci.endTime}"
													 onclick="qieHuanDate(this)"
													data-placement="right" style="cursor:pointer">${banci.endTime}</span>
													<input type="hidden" value="${banci.endTime}" id="banciEnd_${pageStatus.index}">
											</td>
											</tr>
									</s:if>
								</s:iterator>
									
								</s:iterator>
								<!-- 上班时间段结束 -->
								<tr>
									<td>
										姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：
									</td>
									<td>
										<input type="text" name="attendance."
											value="${attendance.personName}" readonly="readonly" />
									</td>
									<td>
										部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门：
									</td>
									<td>
										<input type="text" name="attendance."
											value="${attendance.deptName}" readonly="readonly" />
									</td>
								</tr>
	
								<tr>
									<td>
										考勤日期：
									</td>
									<td>
										<input type="text" name="attendance."
											value="${attendance.dateTime}" readonly="readonly" />
									</td>
									<td>
										卡&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：
									</td>
									<td>
										<input type="text" name="attendance."
											value="${attendance.cardNo}" readonly="readonly" />
									</td>
								</tr>
<%--								<tr>--%>
<%--									<td>--%>
<%--										上班时间：--%>
<%--									</td>--%>
<%--									<td>--%>
<%--										<input class="Wdate" type="text" name="attendance.workDateTime"--%>
<%--											value="${attendance.workDateTime}"--%>
<%--											onclick="WdatePicker({dateFmt:'HH:mm',skin:'whyGreen'})" />--%>
<%--									</td>--%>
<%--									<td>--%>
<%--										下班时间：--%>
<%--									</td>--%>
<%--									<td>--%>
<%--										<input class="Wdate" type="text"--%>
<%--											name="attendance.closingDateTime"--%>
<%--											value="${attendance.closingDateTime}"--%>
<%--											onclick="WdatePicker({dateFmt:'HH:mm',skin:'whyGreen'})" />--%>
<%--									</td>--%>
<%--								</tr>--%>
								<tr>
									<td>
										当天状态：
									</td>
									<td>
										<select name="attendance.attendanceStatus" id="attendanceStatus">
											<option value="${attendance.attendanceStatus}">
												${attendance.attendanceStatus}
											</option>
											<option value="正常">
												正常
											</option>
											<option value="迟到">
												迟到
											</option>
											<option value="早退">
												早退
											</option>
											<option value="缺勤">
												缺勤
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
											<option value="离职">
												离职
											</option>
										</select>
									</td>
									<td>
										请假时间(分钟)：
									</td>
									<td>
										<input type="text" style="width: 280px;"
											name="attendance.mingXiJi" value="${attendance.mingXiJi}" />
									</td>
								</tr>
								<tr>
									<td>
										迟到时长：
									</td>
									<td>
										<input type="text" name="attendance.lateTime"
											value="${attendance.lateTime}" id="lateTime"
											onkeyup="mustBeNumber('lateTime');changvalue2()" 
											/>
										分
									</td>
									<td>
										早退时长：
									</td>
									<td>
										<input type="text" name="attendance.earlyTime"
											value="${attendance.earlyTime}" id="earlyTime"
											onkeyup="mustBeNumber('earlyTime');changvalue2()" 
											/>
										分
									</td>
								</tr>
								<tr>
									<td>
										请假时长：
									</td>
									<td>
										<input type="text" name="attendance.qijiaTime"
											value="${attendance.qijiaTime}" id="qijiaTime"
											onkeyup="mustBeNumber('qijiaTime');changvalue2()" 
											 />
										分
									</td>
									<td>
										加班时长：
									</td>
									<td>
										<input type="text" name="attendance.jiaBTime"
											value="${attendance.jiaBTime}" id="jiaBTime"
											onblur="mustBeNumber_1('jiaBTime')" />
										分
									</td>
								</tr>
								<tr>
									<td>
										缺勤时长：
									</td>
									<td>
										<input type="text" name="attendance.queqinTime"
											value="${attendance.queqinTime}" id="queqinTime"
											onkeyup="mustBeNumber('queqinTime');changvalue2()" 
											 />
										分
									</td>
									<td>
										工作时长：
									</td>
									<td>
										<input type="text" name="attendance.workTime"
											value="${attendance.workTime}" id="workTime"
											onblur="mustBeNumber_1('workTime')" readonly="readonly"/>
										分
									</td>
								</tr>
								<tr>
									<td>
										说明：
									</td>
									<td colspan="3">
										<select name="attendance.more" class="selectpicker form-control" style="width: 635px;">
											<s:iterator id="utl" value="universalTypeList" >
												<option >${utl.name}</option>						
											</s:iterator>
											
										</select>
<!-- 										<textarea cols="88" name="attendance.more"></textarea> -->
									</td>
								</tr>
								<tr>
									<td>
										请假说明：
									</td>
									<td colspan="3">
										<textarea cols="88" name="attendance.mingXiLiu">${attendance.mingXiLiu}</textarea>
									</td>
								</tr>
								<tr>
									<td>
										请假单号：
									</td>
									<td colspan="3">
										<input type="text" name="attendance.qingjiaNumber" style="width: 300px;"/>
									</td>
								</tr>
								<tr>
									<td colspan="4" align="center">
										<input type="submit" value="确定" class="input" />
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
				<s:else>
					<tr>
						<td colspan="8">
							<font color="red" size="8">没有打卡记录</font>
						</td>
					</tr>
				</s:else>
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
function mustBeNumber_1(id) {
	var number = $("#" + id).val();
	if (isNaN(number)) {
		alert("请输入数字");
		$("#" + id).val(0);
		return false;
	}
	if (number < 0) {
		alert("时间不能为负数");
		$("#" + id).val(0);
		return false;
	}
	return true;
}
function changvalue2(){
	var lateTime = $("#lateTime").val();
	var earlyTime = $("#earlyTime").val();
	var qijiaTime = $("#qijiaTime").val();
	var queqinTime = $("#queqinTime").val();
	var workTime = $("#workTime").val();//工作时间
	var workBiaoTime = '${attendance.workBiaoTime}';//标准时间
	//var yfbl = $("#yfbl").val();
	//var coun = Number('${coun}');
	if(workBiaoTime!=null){
		var workTime = workBiaoTime-lateTime-earlyTime-qijiaTime-queqinTime;
		$("#workTime").val(workTime);
	}
}
function adddata() {
	var min = 0;
	if (!validateText_1("lateTime", "迟到时长")) {
		$("#lateTime").val(min);
		return false;
	}
	if (!validateText_1("earlyTime", "早退时长")) {
		$("#earlyTime").val(min);
		return false;
	}
	if (!validateText_1("qijiaTime", "请假时长")) {
		$("#qijiaTime").val(min);
		return false;
	}
	if (!validateText_1("queqinTime", "缺勤时长")) {
		$("#queqinTime").val(min);
		return false;
	}
	if (!validateText_1("workTime", "工作时长")) {
		return false;
	}
	if (!validateText("jiaBTime", "加班时长")) {
		$("#jiaBTime").val(min);
		return false;
	}
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


var x = 10;
var y = 20;
var newtitle = '';
$('span.tooltip-test').mouseover(function(e) {
    newtitle = this.title;
    this.title = '';
    $('body').append('<div id="tooltip">' + newtitle + '</div>');
    $('#tooltip').css({
        'left': (e.pageX + x + 'px'),
        'top': (e.pageY + y + 'px')
    }).show();
}).mouseout(function() {
    this.title = newtitle;
    $('#tooltip').remove();
}).mousemove(function(e) {
    $('#tooltip').css({
        'left': (e.pageX + x + 'px'),
        'top': (e.pageY + y + 'px')
    }).show();
})

//切换时间
function qieHuanDate(obj){
	var btnValue = $(obj).text();
	var prevValue = $(obj).prev().val();
	
	$(obj).text(prevValue);
	$(obj).prev().val(btnValue);
	
	checkAttenanceStatus();
}

function checkAttenanceStatus(){
	var bancilistSize = $("#bancilistSize").val();
	var flag = false;//默认不正常考勤
	for(var i=0;i<bancilistSize;i++){
		var banciStart = $("#banciStart_"+i).val();//班次开始时间
		var attendanceStart = $("#attendanceStart_"+i).val();//考勤调整后开始时间
		
		flag = compareDateScale(attendanceStart,attendanceStart)
		
		if(flag){
			var banciEnd = $("#banciEnd_"+i).val();//班次结束时间
			var attendanceEnd = $("#attendanceEnd_"+i).val();//考勤调整后结束时间
			
			flag = compareDateScale(banciEnd,attendanceEnd)
		}
		
		if(!flag){
			break;
		}
	}
	if(flag){
		$("#attendanceStatus").val("正常");
	}else{
		$("#attendanceStatus").val("缺勤");
	}
	
}

//计算时间大小(第一个参数<=第二个参数 true)
function compareDateScale(start,end){
	if(start==null || start=="" || end==null || end==""){
		return false;
	}
	var time1 =Date.parse("2018-9-27 "+start);
	var time2 = Date.parse("2018-9-27 "+end);
	if(time1<=time2){
		return true;
	}else{
		return false;
	}
}
</script>
	</body>
</html>
