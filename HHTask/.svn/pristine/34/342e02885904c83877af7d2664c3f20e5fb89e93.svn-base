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
	<title></title>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body onload="createDept('dept')">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<table class="table">
					<tr>
						<th>序号</th>
						<th>卡号</th>
						<th>工号</th>
						<th>姓名</th>
						<th>日期</th>
						<th>开始时间</th>
						<th>结束时间</th>
						<th>总工作时长(分钟)</th>
					</tr>
					<s:iterator value="workTimeList" id="sl" status="ps">
						<s:if test="#ps.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
							<td>${ps.index+1 }</td>
							<td>${sl.cardId}</td>
							<td>${sl.userCode}</td>
							<td>${sl.userName }</td>
							<td>${sl.dateTime }</td>
							<td>${sl.startTime }</td>
							<td>${sl.endTime }</td>
							<td>${sl.workTime}</td>
						</tr>
					</s:iterator>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
			function submitBtn(pageStatus){
				$("#pageStatus").val(pageStatus);
				$("#submitForm").submit();
			}
			
			function toDetail(ids){
				location.href="${pageContext.request.contextPath}/"
			}
		</script>
	</body>
</html>
