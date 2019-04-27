<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="background: url(<%=basePath%>/images/bq_bg2.gif); width: 100%;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">您正在查看${attendanceCount.cardDateTime
								}日的状态比例图</span>
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
						style="width: 98%; height: 650px; margin: 0px; padding: 0px;"></iframe>
				</div>

			</div>
		</div>
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
				<form action="AttendanceCountAction!findAllAttC.action"
					method="post">
					<table class="table">
						<tr>
							<td colspan="4" align="center">
								考勤查询
							</td>
						</tr>
						<tr>
							<td>
								开始日期:
								<input type="text" name="startDate" class="Wdate" id="startDate"
									value="${startDate}"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<td>
								结束日期:
								<input type="text" name="endDate" class="Wdate" id="endDate"
									value="${endDate}"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<td>
								班次名称:
								<input type="text" name="banciName" id="banci_name" value="${banciName}"/>
							</td>
							<td colspan="2" align="center">
								<input type="submit" class="input" value="查询" />
								<input type="reset" class="input" value="重置" />
								<input type="button" class="input" value="走势图"
									onclick="lineChart()" />
								<!-- 
								<a href="System/renshi/kaoqing/lineChartCount.jsp">走势图</a>
								 -->
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
							打卡日期
						</th>
						<th>
							班次名称
						</th>
						<th>
							正常(人次)
						</th>
						<th>
							迟到(人次)
						</th>
						<th>
							早退(人次)
						</th>
						<th>
							请假(人次)
						</th>
						<th>
							缺勤(人次)
						</th>
						<th>
							当日持卡总人数
						</th>
						<th>
							正常出勤率
						</th>
						<th>
							分析
						</th>

					</tr>
					<s:iterator value="list" id="pageAtCount" status="pageStatus">
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
							${pageAtCount.cardDateTime}
						</td>
						<td>
							${pageAtCount.banci_name}
						</td>
						<td>
							${pageAtCount.normalDate}
						</td>
						<td>
						<a href="AttendanceAction!findAttenList.action?attendance.dateTime=${pageAtCount.cardDateTime}&attendance.attendanceStatus=cd">
							${pageAtCount.lateCount}
						</a>
						</td>
						<td>
						<a href="AttendanceAction!findAttenList.action?attendance.dateTime=${pageAtCount.cardDateTime}&attendance.attendanceStatus=zt">
							${pageAtCount.leaveEarlyCount}
						</a>
						</td>
						<td>
						<a href="AttendanceAction!findAttenList.action?attendance.dateTime=${pageAtCount.cardDateTime}&attendance.attendanceStatus=qj">
							${pageAtCount.askForLeaveCount}
						</a>
						</td>
						<td>
						<a href="AttendanceAction!findAttenList.action?attendance.dateTime=${pageAtCount.cardDateTime}&attendance.attendanceStatus=kg">
							${pageAtCount.kuangGongCount}
						</a>
						</td>
						<td>
							${pageAtCount.totalAttendance}
						</td>
						<td>
							<fmt:formatNumber type="number"
								value="${pageAtCount.attendance}" maxFractionDigits="2" />
							%

						</td>
						<td>
							<!-- 
						<a href="System/renshi/kaoqing/pieChartCount.jsp?id=${pageAtCount.id}" target="_black" >分析</a>
						 -->
							<input type="button" id="" value="分析"
								onclick="bijia(${pageAtCount.id})" />
						</td>
						</tr>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="11" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="11" align="center" style="color: red">
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
			<script type="text/javascript">
function bijia(obj) {
	var id = obj.valueOf();
	$("#showProcess").attr("src",
			"System/renshi/kaoqing/pieChartCount.jsp?id=" + id);
	chageDiv('block');

}
function lineChart() {
	var startDate = $("#startDate").val();
	var endDate = $("#endDate").val();
	$("#showProcess").attr(
			"src",
			"System/renshi/kaoqing/lineChartCount.jsp?startDate=" + startDate
					+ "&endDate=" + endDate);
	chageDiv('block');
}
</script>
		</div>
	</body>
</html>
