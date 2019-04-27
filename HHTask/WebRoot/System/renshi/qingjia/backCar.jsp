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
				<form action="AskForLeaveAction!backCar.action" method="post" onsubmit="return validate();">
				<input type="hidden" name="singleCar.id" value="${singleCar.id}">
				<br/>
				<table class="table">
				<tr>
				 <th>用车里程</th>
				 <td align="center"><input name="singleCar.kilometers" id="kilometers" value="${singleCar.kilometers}" onkeyup="mustBeNumber('kilometers')"/></td>
				</tr>
				<tr>
					<td colspan="4" align="center"><input type="submit" value="提交" style="width: 50px;height: 25px;"> </td>
				</tr>
				</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<script type="text/javascript">
function validate(){
	var kilometers =$("#kilometers").val();
	if(kilometers==null||kilometers.length==0){
		alert("请填写kilometers!");
		return false;
	}
}
</script>
	</body>
</html>
