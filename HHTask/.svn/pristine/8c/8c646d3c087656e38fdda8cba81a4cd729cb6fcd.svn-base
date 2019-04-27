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
				<form action="ProcardAction!updateProcardForTime.action"
					method="post">
					<input name="procard.id" value="${procard.id}" type="hidden">
					<table class="table">
						<tr>
							<th>
								件号:
							</th>
							<td>
								${procard.markId}
							</td>
						</tr>
						<tr>
							<th>
								计划开始时间:
							</th>
							<td>
								<input name="procard.jihuoDate" value="${procard.jihuoDate}"
									class="Wdate"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})">
							</td>
						</tr>
						<tr>
							<th>
								计划完成时间:
							</th>
							<td>
								${procard.needFinalDate}
								<%--								<input name="procard.needFinalDate"--%>
								<%--									value="" class="Wdate"--%>
								<%--									onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})">--%>
							</td>
						</tr>
						<tr>
							<th colspan="2">
								<input type="submit" value="调整" class="input" onclick="todisabled(this)"/>
							</th>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
	</body>
</html>
