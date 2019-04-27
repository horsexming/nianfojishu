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
			<form action="BecomingAction_updatebecoming.action" method="post">
				<font color="red" size="5">${successMessage}</font>
				<table  style="width: 50%;">
					<tr>
						<th align="right">
							姓名:
						</th>
						<td>
							<input type="text" value="${becoming.name }"  name="becoming.name"/>
						</td>
					</tr>
					<tr>
						<th align="right">
							工号:
						</th>
						<td>
							<input type="text" value="${becoming.code }"name="becoming.code"/>
						</td>
					</tr>
					<tr>
						<th align="right">当前状态:</th>
						<td>
							<input type="text" value="${becoming.befroestatus}"  name="becoming.befroestatus"/>
						</td>
					</tr>
					<tr>
						<th align="right">
							转正状态:		
						</th>
						<td>
							<input type="text" value="在职(正式)" readonly="readonly"/>
							<input type="hidden" value="在职"  name="becoming.status"/>
						</td>
					</tr>
					<tr>
						<th align="right">
							入职时间:
						</th>
						<td>
							<input type="text" value="${user.joined }" readonly="readonly">
						</td>
					</tr>
					<tr>
						<th align="right">
							试用期结束时间:
						</th>
						<td>
							<input type="text" value="${user.tryDays }" readonly="readonly">
						</td>
					</tr>
					<s:if test="status == 'update'">
					<tr>
						<td colspan="2" align="center">
							<input type="hidden" value="${becoming.befroestatus}" name="becoming.userId" />
							<input type="submit" value="提交" style="width: 75px;height: 35px;"/>							
							<input type="reset" value="重置" style="width: 75px;height: 35px;"/>							
						</td>
					</tr>
				</s:if>
				</table>
			</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
