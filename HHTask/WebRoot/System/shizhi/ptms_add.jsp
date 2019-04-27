<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>
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
		<center>
			<%@include file="/util/sonTop.jsp"%>
			<div id="gongneng"
				style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
				<div id="xitong"
					style="width: 100%; font-weight: bold; height: 50px;" align="left">
					<div
						style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
						align="left">

					</div>
					<div style="float: left; width: 45%; padding-top: 5px;"
						align="right">
						<a
							href="proTryMakeScoreAction_toadd.action?<s:property value="tryMake.id"/>"
							style="color: #ffffff">刷新<br />(reflesh)</a>
					</div>
				</div>

				<div align="center">
					<h3>
						添加项目试制评审
						<br />
						（add project try make）
						<br>
						<h3><font color="red">${errorMessage}</font></h3>
					</h3>
					<form action="proTryMakeScoreAction_add.action" method="post"
						onsubmit="return validate()">
						<table class="table">
							<tr>
							<th align="right">
								组别
								<br />
								（group）：
							</th>
							<td>
								<select name="proTryMakeScore.groupName" id="groupName">
									<option value="0">
										未选择
									</option>
									<s:iterator value="gNameList" id="gName">
										<option value="${gName}">
											${gName}
										</option>
									</s:iterator>
								</select>
							</td>
						</tr>
						
						<tr>
							<th align="right">
								项目
								<br />
								（customer name）：
							</th>
							<td>
								<select name="projectManage.id" id="proId">
									<option value="0">
										未选择
									</option>
									<s:iterator value="projectManageList" id="proManage">
										<option value="<s:property value="#proManage.id"/>">
											<s:property value="#proManage.projectName"/>
										</option>
									</s:iterator>
								</select>
							</td>
						</tr>
						<tr>
                         <th align="right">
								月份<br />
								（month）：
							</th>
							<td>
							<input type="text"  class="Wdate" name="proTryMakeScore.month" 
								id="month" onClick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})" />
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
	var groupName = document.getElementById("groupName").value;
	if (groupName == 0) {
		alert("请选择组别");
		return false;
	}
	var proId = document.getElementById("proId").value;
	if (proId == 0) {
		alert("请选择项目");
		return false;
	}
	
	var month = document.getElementById("month").value;
	if (month == "") {
		alert("请输入月份");
		return false;
	}
}
</script>
	</body>
</html>
