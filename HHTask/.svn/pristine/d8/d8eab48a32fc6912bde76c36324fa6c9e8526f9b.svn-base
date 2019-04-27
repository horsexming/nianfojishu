<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	<div>
		<form action="OneMachineAction_add.action" method="post"
			onsubmit="return validate()">
			<table class="table">
				<tr>
					<th colspan="4">
						<font size="5">添加一体机</font>
					</th>
				</tr>
				<tr>
					<th align="right">
						一体机名称:
					</th>
					<td>
						<input id="omNum" name="machine.omNum" value="" />
					</td>
					<th align="right">
						一体机IP:
					</th>
					<td>
						<input id="ipAddress" name="machine.ipAddress" value="" />
					</td>
				</tr>
				<tr>
					<th align="right">
						对应位置:
					</th>
					<td>
						<input id="omAddress" name="machine.omAddress" value="" />
					</td>
					<td align="center" colspan="2">
						<input type="submit" value="添加" class="input" />
						<input type="reset" value="重置" class="input" />
					</td>
				</tr>
			</table>
		</form>
		</div>
	</body>
	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	<script type="text/javascript">
$(function() {
	var errorMessage = '${errorMessage}';
	if (errorMessage != "") {
		alert(errorMessage);
		parent.location.reload(true);//刷新父页面
	}
})
function validate() {
	if (!validateText("omNum", "一体机编号")) {
		return false;
	}
	if (!validateText("ipAddress", "一体机ip地址")) {
		return false;
	}
}
function validateText(id, textname) {
	var textValue = $.trim($("#" + id).val());
	if (textValue == null || textValue == "") {
		alert(textname + "不能为空");
		return false;
	}
	return true;
}
</script>
</html>
