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
							href="proTryMakeScoreAction_toupdatePro.action?proTryMakeScore.id=<s:property value="proTryMakeScore.id"/>"
							style="color: #ffffff">刷新<br />(reflesh)</a>
					</div>
				</div>

				<div align="center">
					<h3>
						修改项目试制评审
						<br />
						（update project try make）
					</h3>
					<form action="proTryMakeScoreAction_updatePro.action" method="post"
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
									<option value="<s:property value="proTryMakeScore.groupName"/>">
										<s:property value="proTryMakeScore.groupName"/>
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
										项目名称
										<br />
										（part number）
									</th>
									<td>
										<input type="hidden" name="proTryMakeScore.id"
											value="<s:property value="proTryMakeScore.id"/>" />
										<input type="text" name="proTryMakeScore.proName"
											value="<s:property value="proTryMakeScore.proName"/>"  />
									</td>
								</tr>
								<tr>
									<th align="right">
										项目编号
										<br />
										（project number）
									</th>
									<td>
										<input type="text" name="proTryMakeScore.proNum"
											value="<s:property value="proTryMakeScore.proNum"/>"/>
									</td>
								</tr>
								<tr>
							<th align="right">
								客户名称
								<br />
								（customer name）：
							</th>
							<td>
								<select name="proTryMakeScore.cusName">
									<option value="<s:property value="proTryMakeScore.cusName"/>">
										<s:property value="proTryMakeScore.cusName"/>
									</option>
									<s:iterator value="cusNameList" id="cusName">
										<option value="${cusName}">
											${cusName}
										</option>
									</s:iterator>
								</select>
							</td>
						</tr>
                        <tr>
                         <th align="right">
								月份（project name）：
								
							</th>
							<td>
							<input type="text"  class="Wdate" name="proTryMakeScore.month"  value="<s:property value="proTryMakeScore.month"/>"
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
	var proName = document.getElementById("proName").value;
	if (groupName == "") {
		alert("请输入项目名称");
		return false;
	}
	var groupNum = document.getElementById("groupNum").value;
	if (groupName == "") {
		alert("请输入项目编号");
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
