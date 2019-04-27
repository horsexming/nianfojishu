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
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 600px; display: none;"
			align="center">
			<div id="closeDiv"
				style="background: url(<%=basePath%>/images/bq_bg2.gif); width: 100%;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">更新个人考勤状态</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="showProcess" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 450px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div align="center">
				<form action="AttendanceAction!findAttenList.action?tag=${tag}" method="post">
					<!-- onsubmit="return checkType();" -->
					<table class="table">
						<tr>
							<td colspan="4" align="center">
								考勤查询
							</td>
						</tr>
						<tr>
							<td>
								姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:
								<input type="text" id="users" style="width: 155px;"
									name="attendance.personName" value="${attendance.personName}" />
							</td>
							<td>
								当天状态:
								<select id="status" style="width: 155px;"
									name="attendance.attendanceStatus">
									<option value="${attendance.attendanceStatus}">
										${attendance.attendanceStatus}
									</option>
									<option value="">
										选择状态
									</option>
									<option value="正常">
										正常
									</option>
									<option value="未刷卡">
										未刷卡
									</option>
									<option value="迟到">
										迟到
									</option>
									<option value="早退">
										早退
									</option>
									<option value="请假">
										请假
									</option>
									<option value="加班">
										加班
									</option>
									<option value="缺勤">
										缺勤
									</option>
									<option value="缺勤无卡">
										缺勤无卡
									</option>
									<option value="缺勤有卡">
										缺勤有卡
									</option>
									<option value="异常">
										异常
									</option>
								</select>
							</td>
							<td>
								工&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号:
								<input type="text" style="width: 155px;"
									name="attendance.code" value="${attendance.code}" />
<%--								班&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;次:--%>
<%--								<select id="banci" style="width: 155px;"--%>
<%--									name="attendance.banci_name">--%>
<%--									<option value="${attendance.banci_Name}">--%>
<%--										${attendance.banci_Name}--%>
<%--									</option>--%>
<%--								</select>--%>
							</td>
						</tr>
						<tr>
							<td>
								开始日期:
								<input type="text" name="startDate" class="Wdate" id="startDate"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"
									value="${startDate}" />
							</td>
							<td>
								结束日期:
								<input type="text" name="endDate" class="Wdate" id="endDate"
									value="${endDate}"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<td>
								部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门:
								<select id="dept" style="width: 155px;"
									name="attendance.deptName">
									<option value="${attendance.deptName}">
										${attendance.deptName}
									</option>
								</select>

							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<input type="submit" class="input" value="查询" />
								<input type="reset" class="input" value="重置" />
								<input type="button" class="input" value="数据导出"
									onclick="exportExcel(this.form);todisabledone(this)" data="downData" />
<%--								<a href="AttendanceAction!sendKaoQin_1.action">推送考勤报表</a>--%>
<%--								<a href="AttendanceAction!jisuankaoqin.action">更新考勤信息</a>--%>
							</td> 
						</tr>
					</table>
				<div>
					<font style="color: red">${errorMessage}</font>
				</div>
				</form>
				<pre></pre>
				<form action="AttendanceAction!daoInkaoqin.action" method="post" 
					enctype="multipart/form-data" onsubmit="return checktype()">
					<input type="file" name="attendEx" id="file1" value="选择文件"/>
					<input type="submit" value="数据导入"
						/>
				</form>
				<pre></pre>
				<table style="border-collapse: collapse;"
					class="table">
					<tr bgcolor="#c0dcf2" height="30px" ondblclick="showmingxi('')">
						<th rowspan="2">
							序号
						</th>
						<th rowspan="2">
							姓名
						</th>
						<th rowspan="2">
							部门
						</th>
						<th rowspan="2">
							工号
						</th>
						<th rowspan="2">
							卡号
						</th>
						<th rowspan="2">
							考勤日期
						</th>
						<s:set name="maxTimeInterval" value="0"/>
						<s:iterator value="list" id="pageAttendance" status="pageStatus">
							<s:if test="#pageAttendance.fu.size()>#maxTimeInterval">
								<s:set name="maxTimeInterval" value="#pageAttendance.fu.size()"/>
							</s:if>
						</s:iterator>
						<s:if test="#maxTimeInterval>=1">
							<th colspan="2">
								第一段考勤时间
							</th>
						</s:if>
						<s:if test="#maxTimeInterval>=2">
							<th colspan="2">
								第二段考勤时间
							</th>
						</s:if>
						<s:if test="#maxTimeInterval>=3">
							<th colspan="2">
								第三段考勤时间
							</th>
						</s:if>
						<s:if test="#maxTimeInterval>=4">
							<th colspan="2">
								第四段考勤时间
							</th>
						</s:if>
						<s:if test="#maxTimeInterval>=5">
							<th colspan="2">
								第五段考勤时间
							</th>
						</s:if>
						<th rowspan="2">
							打卡记录
						</th>
						<th rowspan="2">
							当天打卡<br/>状态
						</th>
						<th rowspan="2">
							工作时长
						</th>
						<th rowspan="2">
							迟到时长
						</th>
						<th rowspan="2">
							早退时长
						</th>
						<th rowspan="2">
							请假时长
						</th>
						<th rowspan="2">
							缺勤时长
						</th>
						<th rowspan="2">
							加班时长
						</th>
						<th rowspan="2">
							操作
						</th>
					</tr>
					<tr bgcolor="#c0dcf2" height="30px">
						<s:if test="#maxTimeInterval>=1">
							<th>
								上班时间
							</th>
							<th>
								下班时间
							</th>
						</s:if>
						<s:if test="#maxTimeInterval>=2">
							<th>
								上班时间
							</th>
							<th>
								下班时间
							</th>
						</s:if>
						<s:if test="#maxTimeInterval>=3">
							<th>
								上班时间
							</th>
							<th>
								下班时间
							</th>
						</s:if>
						<s:if test="#maxTimeInterval>=4">
							<th>
								上班时间
							</th>
							<th>
								下班时间
							</th>
						</s:if>
						<s:if test="#maxTimeInterval>=5">
							<th>
								上班时间
							</th>
							<th>
								下班时间
							</th>
						</s:if>
					</tr>
					<s:iterator value="list" id="pageAttendance" status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								ondblclick="showmingxi(${pageAttendance.attendanceId})" class="zong"
								id="zong_${pageAttendance.attendanceId}"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								ondblclick="showmingxi(${pageAttendance.attendanceId})" class="zong"
								id="zong_${pageAttendance.attendanceId}"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#pageStatus.index+1" />
						</td>
						<td>
							${pageAttendance.personName}
						</td>
						<td>
							${pageAttendance.deptName}
						</td>
						<td>
							${pageAttendance.code}
						</td>
						<td>
							${pageAttendance.cardNo}
						</td>
						<td>
							${pageAttendance.dateTime}
						</td>
						<s:iterator value="#pageAttendance.fu" id="attendanceF" status="pageStatu">
							<td>
							${attendanceF.workDateTime} 
							</td>
							<td>
							${attendanceF.closingDateTime}
							</td>
						</s:iterator>
						<s:if test="null==#pageAttendance.fu">
							<s:if test="#maxTimeInterval>=1">
								<td>-</td>
								<td>-</td>
							</s:if>
							<s:if test="#maxTimeInterval>=2">
								<td>-</td>
								<td>-</td>
							</s:if>
							<s:if test="#maxTimeInterval>=3">
								<td>-</td>
								<td>-</td>
							</s:if>
							<s:if test="#maxTimeInterval>=4">
								<td>-</td>
								<td>-</td>
							</s:if>
							<s:if test="#maxTimeInterval>=5">
								<td>-</td>
								<td>-</td>
							</s:if>
							
						</s:if>
						<s:else>
							<s:if test="#maxTimeInterval-#pageAttendance.fu.size()>0">
								<s:if test="#maxTimeInterval-#pageAttendance.fu.size()>=1">
									<td>-</td>
									<td>-</td>
								</s:if>
								<s:if test="#maxTimeInterval-#pageAttendance.fu.size()>=2">
									<td>-</td>
									<td>-</td>
								</s:if>
								<s:if test="#maxTimeInterval-#pageAttendance.fu.size()>=3">
									<td>-</td>
									<td>-</td>
								</s:if>
								<s:if test="#maxTimeInterval-#pageAttendance.fu.size()>=4">
									<td>-</td>
									<td>-</td>
								</s:if>
								<s:if test="#maxTimeInterval-#pageAttendance.fu.size()>=5">
									<td>-</td>
									<td>-</td>
								</s:if>
							</s:if>
						</s:else>
						
<%--						<td>--%>
<%--							${pageAttendance.workDateTime}--%>
<%--						</td>--%>
<%--						<td>--%>
<%--							${pageAttendance.workDateTimeXia}--%>
<%--						</td>--%>
<%--						<td>--%>
<%--							${pageAttendance.closingDateTimeShang}--%>
<%--						</td>--%>
<%--						<td>--%>
<%--							${pageAttendance.closingDateTime}--%>
<%--						</td>--%>
						<td width="90px">
							${pageAttendance.timeAll}
						</td>
						<td>
							${pageAttendance.attendanceStatus}
						</td>
						<td>
							${pageAttendance.workTime}
						</td>
						<td>
							${pageAttendance.lateTime}
						</td>
						<td>
							${pageAttendance.earlyTime}
						</td>
						<td>
							${pageAttendance.qijiaTime}
						</td>
						<td>
							${pageAttendance.queqinTime}
						</td>
						<td>
							${pageAttendance.jiaBTime}
						</td>
						<td>
						<s:if test="tag=='admins'">
							<input type="button" value="更新"
								onclick="updateStatus(${pageAttendance.attendanceId})"
								style="width: 60px;" />
						</s:if>
						<s:else>
							<input type="button" value="打卡明细"
								onclick="updateStatus(${pageAttendance.attendanceId})"
								style="width: 80px;" />
						</s:else>
<%--						&&#pageAttendance.attendanceStatus=="缺卡"--%>
						<s:if test='tag=="code"&&(#pageAttendance.epId==null||#pageAttendance.status=="打回")'>
							<input type="button" value="补卡申请"
								onclick="shenqing(${pageAttendance.attendanceId})"
								style="width: 80px;" />
						</s:if>
						<s:if test="#pageAttendance.epId!=null">
							<a href="CircuitRunAction_findAduitPage.action?id=${pageAttendance.epId}">
							${pageAttendance.status}</a>
						</s:if>
						</td>
<%--						<s:if test="#pageAttendance.show!=null">--%>
<%--							<tr>--%>
<%--								<td colspan="15">--%>
<%--									${pageAttendance.show}--%>
<%--								</td>--%>
<%--							</tr>--%>
<%--						</s:if>--%>
							<s:if test="#pageAttendance.tow!=null && #pageAttendance.tow.size()>0">
								<tr class="mingxi" id="mingxi_${pageAttendance.attendanceId}" style="display: none;">
									<td colspan="19">
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
											<s:iterator value="#pageAttendance.tow" id="samples"
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
									</td>
								</tr>
							</s:if>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="22" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="22" align="center" style="color: red">
						</s:else>
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
		<script type="text/javascript">
function updateStatus(obj) {
	var id = obj.valueOf();
	$("#showProcess").attr("src",
			"AttendanceAction!getAttenctionById.action?tag=${tag}&id=" + id);
	chageDiv('block');
}
function shenqing(obj) {
	var id = obj.valueOf();
	$("#showProcess").attr("src",
			"AttendanceAction!quekaShenqing.action?tag=${tag}&id=" + id);
	chageDiv('block');
}
//导出EXCEL
function exportExcel(objForm) {
	objForm.action = "AttendanceAction!erporExcel.action";
	objForm.submit();
	objForm.action = "AttendanceAction!findAttenList.action?tag=${tag}";
}
//导出EXCEL
function exportExcel1(objForm) {
	objForm.action = "AttendanceAction!daoInkaoqin.action";
	objForm.submit();
	objForm.action = "AttendanceAction!findAttenList.action?tag=${tag}";
}

$(function() {
	//显示所有部门信息
	$.ajax( {
		url : 'DeptNumberAction!findAllDept.action',
		dataType : 'json',
		cache : false,//防止数据缓存
		success : function(allDdept) {
			$("#dept").empty();
			$("<option value=''>--请选择部门--</option>").appendTo("#dept");
			$(allDdept).each(
					function() {
						$(
								"<option value='" + this.dept + "'>"
										+ this.dept + "</option>").appendTo(
								"#dept");
			});
		}

	});
	//显示部门对应的员工信息
	$("#dept").bind(
			"change",
			function() {
				if ($("#dept").val() != "") {
					$.ajax( {
						url : "UsersAction!findUsersByDept.action",
						type : 'post',
						dataType : 'json',
						cache : false,//防止数据缓存
						data : {
							deptName : $("#dept").val()
						},
						success : function(useradsfa) {
							$("#users").empty();//清空
							$("<option></option>").appendTo("#users");
							$(useradsfa).each(
									function() {
										$(
												"<option value='"
												/*+ this.code+"|"*/
												+ this.name + "'>" + this.name
														+ "</option>")
												.appendTo("#users")
									});
						},
						error : function() {
							alert("服务器异常!");
						}
					});
				}

			});

})

function checktype(){
	var file1 = $("#file1").val();
	if(file1 == null || "" == file1){
		alert("上传文件不能为空！！！");
		return false;
	}
}

var count =0;
function showmingxi(id){
	if(count == 0){
		if(id==''){
			$(".mingxi").show();
			$(".zong").attr("style","font-weight: bold;")
		}else{
			$("#mingxi_"+id).show();
			$("#zong_"+id).attr("style","font-weight: bold;")
		}
		count =1;
	}else if(count == 1){
		if(id==''){
			$(".mingxi").hide();
			$(".zong").removeAttr("style");
		}else{
			$("#mingxi_"+id).hide();
			$("#zong_"+id).removeAttr("style");
		}
		count =0;
	}
}

</script>
	</body>
</html>
