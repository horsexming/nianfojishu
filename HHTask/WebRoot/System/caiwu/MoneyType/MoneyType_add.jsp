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
			<br/>
			<font id="zi_font" color="red">${errorMessage}</font>
			<h2 style="font-size: x-large;">添加货币类别</h2>
				<form action="MoneyTypeAction_addMonyType.action" method="post" onsubmit="return check()">
					<table>
						<tr>
							<th>货币&nbsp;名称:</th>
							<th>
								<input type="text" id="name" name="mt.name"/>
							</th>
						</tr>
						<tr>
							<th>货币&nbsp;符号:</th>
							<th>
								<input type="text" id="sign" name="mt.sign"/>
							</th>
						</tr>
						<tr>
							<th>使用/禁用:</th>
							<th align="left">
								<input type="radio" value="使用" name="mt.status" checked="checked"/>使用
								<input type="radio" value="禁用" name="mt.status" />禁用
							</th>
						</tr>
						<tr>
							<th colspan="2">
								<input type="submit" value="添加" id="sub" class="input"/>
							</th>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
function check(){
	var name = $("#name").val();
	var sign = $("#sign").val();
	if(name == ""){
		$("#zi_font").html("请填写货币名称");
	}else if(sign == ""){
		$("#zi_font").html("请填写货币符号");
	}
	document.getElementById("sub").disabled = "disabled";
}

</SCRIPT>
	</body>
</html>
