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
			<form action="BecomingAction_add.action" method="post" id="submitForm">
				<font color="red" size="5">${successMessage}</font>
				<a href="BecomingAction_findBecoming.action?status=person">转正申请记录</a>
				<table  style="width: 50%;">
					<tr>
						<th align="right">
							姓名:
						</th>
						<td>
							<input type="text" value="${user.name }" readonly="readonly" name="becoming.name"/>
						</td>
					</tr>
					<tr>
						<th align="right">
							工号:
						</th>
						<td>
							<input type="text" value="${user.code }" readonly="readonly" name="becoming.code"/>
						</td>
					</tr>
					<tr>
						<th align="right">当前状态:</th>
						<td>
							<input type="text" value="${user.onWork}" readonly="readonly" name="becoming.befroestatus"/>
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
					<tr>
						<td colspan="2" align="center">
							<input type="hidden" value="${user.dept}" name="becoming.dept" />
							<input type="hidden" value="${user.id}" name="becoming.userId" />
							<input type="button" value="提交" style="width: 75px;height: 35px;" 
							onclick="todisabled(this)"/>							
						</td>
					</tr>
				</table>
			</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
			function todisabled(obj){
				$(obj).attr('disabled',true);
				$("#submitForm").submit();
			}
		</script>
	</body>
</html>
