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
			<h2 style="font-size: x-large;">修改货币类别</h2>
				<form action="MoneyTypeAction_updateMoneyType.action" method="post" onsubmit="return check()">
					<table>
						<tr>
							<th>货币&nbsp;名称:</th>
							<th>
								<input type="text" id="name" value="${mt.name}" name="mt.name"/>
							</th>
						</tr>
						<tr>
							<th>货币&nbsp;符号:</th>
							<th>
								<input type="text" id="sign" value="${mt.sign}" name="mt.sign"/>
							</th>
						</tr>
						<tr>
							<th colspan="2">
								<input type="hidden" value="${mt.status}" name="mt.status"/>
								<input type="hidden" value="${mt.id}" name="mt.id"/>
								<input type="submit" value="修改" id="sub" class="input"/>
							</th>
						</tr>
					</table>
				</form>
				<input type="hidden" value="${errorMessage}" id="rebeack"/>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
$(function(){
	var rebeack = $("#rebeack").val();
	if (rebeack == "修改成功") {
		alert(rebeack);
		parent.chageDiv('none');
		parent.window.location.reload();
	}
})
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
