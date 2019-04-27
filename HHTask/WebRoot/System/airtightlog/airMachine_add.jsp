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
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">
					
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a
						href="airtightLogAction_toAddMachine.action"
						style="color: #ffffff">刷新<br/>(reflesh)</a>
				</div>
			</div>
			
			<div align="center">
				<h3>
					添加气密机器
				</h3>
				<form action="airtightLogAction_addMachine.action" method="post"
					onsubmit="return validate()">
					<table class="table">
						<tr>
							<th align="right">
								机器名称
							</th>
							<td>
								<input type="text" name="airMachine.name" id="name" />
							</td>
						</tr>
						<tr>
							<th align="right">
								开关Ip
							</th>
							<td>
								<input type="text" name="airMachine.switchIp" id="switchIp" />
							</td>
						</tr>
						<tr>
							<th align="right">
								开关端口
							</th>
							<td>
								<input type="text" name="airMachine.switchPort" id="switchPort" />
							</td>
						</tr>
						<tr>
							<th align="right">
								传值Ip
							</th>
							<td>
								<input type="text" name="airMachine.valueIp" id="valueIp" />
							</td>
						</tr>
						<tr>
							<th align="right">
								传值端口
							</th>
							<td>
								<input type="text" name="airMachine.valuePort" id="valuePort"/>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="right">
								<input type="submit" value="提交(submit) "
									style="width: 80px; height: 50px;" />
							</td>
						</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function validate() {
	var name = document.getElementById("name").value;
	var switchIp = document.getElementById("switchIp").value;
	var switchPort = document.getElementById("switchPort").value;
	var valueIp = document.getElementById("valueIp").value;
	var valuePort = document.getElementById("valuePort").value;
	if (name== "") {
		alert("请输入机器名称!");
		return false;
	}
	if (switchIp=="") {
		alert("请输入开关IP!");
		return false;
	}
	if (switchPort=="") {
		alert("请输入开关端口!");
		return false;
	}
	if (valueIp=="") {
		alert("请输入传值IP!");
		return false;
	}
	if (valuePort=="") {
		alert("请输入传值端口!");
		return false;
	}
	return true;
}
</script>
	</body>
</html>
