<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>绑定网站管理,${companyInfo.shortName}作业网</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="shortcut icon" href="favicon.ico" />
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="css/css.css">
		<script type="text/javascript">
function checkForm() {
	var oldPassword = document.getElementById("oldPassword");
	var newPassword1 = document.getElementById("newPassword1");
	var newPassword2 = document.getElementById("newPassword2");

	if (oldPassword.value == "") {
		alert("请输入原密码!");
		oldPassword.focus();
		return false;
	} else if (newPassword1.value == "") {
		alert("请输入新密码!");
		newPassword1.focus();
		return false;
	} else if (newPassword2.value == "") {
		alert("请再次输入新密码!");
		newPassword2.focus();
		return false;
	} else if (newPassword2.value != newPassword1.value) {
		alert("两次输入的密码不一致,请重新输入!");
		newPassword2.value = "";
		newPassword1.select();
		return false;
	} else if (newPassword2.value == oldPassword.value) {
		alert("新密码和原密码一致!无需修改!");
		oldPassword.focus();
		return false;
	} else {
		return true;
	}
}

function showOrUpdate(status, index) {
	var aClick = document.getElementById("aClick");
	if (status == "open") {
		document.getElementById('updateLoginField' + index).style.display = 'block';
		document.getElementById('showLoginField' + index).style.display = 'none';
	} else {
		document.getElementById('updateLoginField' + index).style.display = 'none';
		document.getElementById('showLoginField' + index).style.display = 'block';
	}
}
</script>
		<script type="text/javascript"
			src="<%=basePath%>javascript/jquery-1.8.3.js">
</script>
	</head>
	<body bgcolor="#ffffff">
		<center>
			<div id="gongneng"
				style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
				<div align="center" style="">
					<table  class="table">
						<tr>
							<th colspan="4">
								已绑定网站管理
							</th>
						</tr>
						<tr bgcolor="#c0dcf2" height="50px">
							<th align="center">
								序号
							</th>
							<th align="center">
								网站名称
							</th>
							<th align="left">
								帐号信息
							</th>
							<th align="center">
								操作
							</th>
						</tr>
						<s:iterator value="projectLoginList" id="pageProjectLogin"
							status="pageStatus">
							<s:if test="#pageStatus.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:if test="#pageStatus.index%2==1">
									<font>
								</s:if>
								<s:else>
									<font color="#c0dcf2">
								</s:else>
								<s:property value="#pageStatus.index+1" />
								</font>
							</td>
							<td>
								${pageProjectLogin.projectRecord.websiteName}
							</td>
							<td align="left">
								<div id="showLoginField<s:property value='#pageStatus.index'/>"
									style="display: block;">
									<s:iterator id="pageLoginFieldName"
										value='projectRecord.loginFieldName.split(",")'
										status="fieldStatus">
											${pageLoginFieldName}:
												<s:iterator id="pageLoginField"
											value='loginFieldValue.split(",")[#fieldStatus.index]'>
													${pageLoginField}
												</s:iterator>
									</s:iterator>
								</div>
								<div
									id="updateLoginField<s:property value='#pageStatus.index'/>"
									style="display: none;">
									<form action="ProjectRecordAction!updateProjectLogin.action"
										method="post" style="margin: 0px; padding: 0px;">
										<input type="hidden" name="projectLogin.projectRecord.id"
											value="${projectRecord.id}">
										<input type="hidden" name="projectLogin.id" value="${id}">
										<s:iterator id="pageLoginFieldName"
											value='projectRecord.loginFieldName.split(",")'
											status="fieldStatus">
											${pageLoginFieldName}:
												<s:iterator id="pageLoginField"
												value='loginFieldValue.split(",")[#fieldStatus.index]'>
												<input name="projectLogin.loginFieldValue"
													value="${pageLoginField}" style="width: 80px;" />
											</s:iterator>
										</s:iterator>
										<input value="修改" type="submit" />
										<input value="取消" type="button"
											onclick="showOrUpdate('close','<s:property value='#pageStatus.index'/>')" />
									</form>
								</div>
							</td>
							<td>
								<a
									href="ProjectRecordAction!delProjectLogin.action?id=${pageProjectLogin.id}">删除</a>
								/
								<a href="javascript:;" id="aClick"
									onclick="showOrUpdate('open','<s:property value='#pageStatus.index'/>')">修改</a>
							</td>
							</tr>
						</s:iterator>
					</table>

				</div>
			</div>
			<%@include file="/util/foot.jsp"%>
		</center>
	</body>
</html>
<%
	request.getSession().removeAttribute("mfNames");
	request.getSession().removeAttribute("moduleFunction");
%>