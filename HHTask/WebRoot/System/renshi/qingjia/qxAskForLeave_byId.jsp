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
				<table >
					<tr>
						<th>
							申请销假人姓名:
						</th>
						<td>
							${qxAskForLeave.userName}
						</td>
					</tr>
					<tr>
						<th>
							申请销假人工号:
						</th>
						<td>
							${qxAskForLeave.userCode}
						</td>
					</tr>
					<tr>
						<th>
							实际开始时间:
						</th>
						<td>
							${qxAskForLeave.startDate}
						</td>
					</tr>
					<tr>
						<th>
							实际结束时间:
						</th>
						<td>
							${qxAskForLeave.endDate}
						</td>
					</tr>
					<tr>
						<th>
							实际请假天数:
						</th>
						<td>
							${qxAskForLeave.days}
						</td>
					</tr>
					<tr>
						<th>
							实际请假小时:
						</th>
						<td>
							${qxAskForLeave.hours}
						</td>
					</tr>
				</table>				
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
		
		</SCRIPT>
	</body>
</html>
