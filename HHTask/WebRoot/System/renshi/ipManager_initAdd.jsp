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
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<h3>
					添加IP信息
				</h3>
				<form action="ipManager_add.action" method="post"
					onsubmit="return validate()">
					<table class="table">
						<tr>
							<th align="right">
								IP地址：
							</th>
							<td>
								<input type="text" name="ip.ip" id="billingPeople" />
							</td>
						</tr>
						<tr>
							<th align="right">
								用户名：
							</th>
							<td>
								<input type="text" name="ip.name" id="paymentDate" />
							</td>
						</tr>
						<tr>
							<th align="right">
								计算机名：
							</th>
							<td>
								<input type="text" name="ip.computerName" />
							</td>
						</tr>
						<tr>
							<th align="right">
								网关&nbsp;&nbsp;：
							</th>
							<td>
								<input type="text" name="ip.gateway" />
							</td>
						</tr>
						<tr>
							<th align="right">
								部门&nbsp;&nbsp;：
							</th>
							<td>
								<input type="text" name="ip.dept" id="documentaryPeople" />
							</td>
						</tr>
						<tr>
							<th align="right">
								状态&nbsp;&nbsp;：
							</th>
							<td>
								<input type="radio" name="ip.status" value="使用"
									checked="checked">
								使用
								</input>
								<input type="radio" name="ip.status" value="闲置">
								闲置
								</input>
							</td>
						</tr>
						<tr>
							<th align="right">
								备注&nbsp;&nbsp;：
							</th>
							<td>
								<input type="text" name="ip.remark" />
							</td>
						</tr>
						<tr>
							<td colspan="2" align="right">
								<input type="submit" value="提交 "
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
	var documentaryPeople = document.getElementById("documentaryPeople").value;
	var billingPeople = document.getElementById("billingPeople").value;
	var paymentDate = document.getElementById("paymentDate").value;
	if (documentaryPeople == "") {
		alert("请输入部门!");
		return false;
	}
	if (billingPeople == "") {
		alert("请输入IP地址!");
		return false;
	}
	if (paymentDate == "") {
		alert("请输入用户名!");
		return false;
	}
}
</script>
	</body>
</html>
