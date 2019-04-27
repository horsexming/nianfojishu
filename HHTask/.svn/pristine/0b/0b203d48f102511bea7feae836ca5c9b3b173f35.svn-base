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
		<form action="pmiManagementAction_addPmi.action" method="post"
			onsubmit="return checkOK()">
			<table align="center" class="table">
				<tr>
					<th colspan="4">
						<font size="5">添加PMI</font>
					</th>
				</tr>
				<tr>
					<th align="right">
						名称:
					</th>
					<td>
						<input id="pmi_name" name="pmiManagement.pmi_name" value="" />
					</td>
					<th align="right">
						客户端IP地址:
					</th>
					<td>
						<input id="pmi_ip" name="pmiManagement.pmi_ip" value="" />
					</td>
				</tr>
				<tr>
					<th align="right">
						序号:
					</th>
					<td>
						<input id="min_num" name="pmiManagement.min_num" value="" />
					</td>
					<th align="right">
						服务端IP地址:
					</th>
					<td>
						<input id="pmi_serverIp" name="pmiManagement.pmi_serverIp" value="" />
					</td>
				</tr>
				<tr>
					<th align="right">
						端口:
					</th>
					<td>
						<input id="pmi_port" name="pmiManagement.pmi_port" value="" />
					</td>
					<th align="right">
						类型:
					</th>
					<td>
						<select id="pmi_type" name="pmiManagement.pmi_type">
							<option value="强控">
								强控
							</option>
							<option value="弱控">
								弱控
							</option>
						</select>
					</td>
				</tr>
				<tr>
					<th align="right">
						额定电流
					</th>
					<td>
						<input id="rated_Current" name="pmiManagement.rated_Current" />
					</td>
					<th align="right">
						报警百分比
					</th>
					<td>
						<input id="alert_Percentage" name="pmiManagement.alert_Percentage" />
					</td>
				</tr>
				<tr>
					<td align="center" colspan="4">
						<input type="submit" value=" 添加" class="input" />
						<input type="reset" value=" 重置" class="input" />
					</td>
				</tr>
			</table>
		</form>
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
function checkOK() {
	var pmi_name = document.getElementById("pmi_name");
	var pmi_ip = document.getElementById("pmi_ip");
	if (pmi_name.value == "") {
		alert("请填写名称!");
		pmi_name.focus();
		return false;
	}
	if (pmi_ip.value == "") {
		alert("请填写IP!");
		pmi_ip.focus();
		return false;
	}

}
</script>
</html>
