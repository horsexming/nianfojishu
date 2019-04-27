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
		<div id="gongneng"
			style="width: 100%; border:  solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<h3>修改IP信息</h3>
				<form action="ipManager_update.action" method="post" onsubmit="return validate()">
				<table>
					<tr><td>IP地址：<input type="text" value="${ip.ip}" name="ip.ip" id="billingPeople"/></td></tr>
					<tr><td>用户名：<input type="text" name="ip.name" value="${ip.name}" id="paymentDate"/></td></tr>
					<tr><td>计算机名：<input type="text" name="ip.computerName" value="${ip.computerName}"/></td></tr>
					<tr><td>网关&nbsp;&nbsp;：<input type="text" name="ip.gateway" value="${ip.gateway}"/></td></tr>
					<tr><td>部门&nbsp;&nbsp;：<input type="text" name="ip.dept" value="${ip.dept}" id="documentaryPeople"/>
					<tr><td>状态&nbsp;&nbsp;：<input type="radio" name="ip.status" value="使用" checked="checked">使用</input><input type="radio" name="ip.status" value="闲置">闲置</input></td></tr>
					<tr><td>备注&nbsp;&nbsp;：<input type="text" name="ip.remark" value="${ip.remark}"/></td></tr>
					<tr><td><input type="hidden" name="id" value="${ip.id}"/><input type="submit" value="修改 " style="width: 80px; height: 50px;"/></td></tr>
				</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
			function validate(){
				var documentaryPeople = document.getElementById("documentaryPeople").value;
				var billingPeople = document.getElementById("billingPeople").value;
				var paymentDate = document.getElementById("paymentDate").value;
				if(documentaryPeople ==""){
					alert("请输入部门!");
					return false;
				}
				if(billingPeople ==""){
					alert("请输入IP地址!");
					return false;
				}
				if(paymentDate ==""){
					alert("请输入用户名!");
					return false;
				}
			}
		</script>
	</body>
</html>
