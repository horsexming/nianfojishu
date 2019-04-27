<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.task.util.MD5"%>
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
		<title>修改密码,生产力生态平衡系统</title>
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
		newPassword1.value = "";
		newPassword1.focus();
		return false;
	} else if (newPassword2.value == oldPassword.value) {
		alert("新密码和原密码一致!无需修改!");
		oldPassword.focus();
		return false;
	} else {
		return true;
	}
}
window.onload=function(){
 var errorMessage="${errorMessage}";
 if(errorMessage!=null&&errorMessage!=""){
  alert(errorMessage);
 }
 var successMessage="${successMessage}";
 if(successMessage!=null&&successMessage!=""){
  alert(successMessage);
 }
};
</script>
	</head>
	<body bgcolor="#ffffff">
		<center>
			<div id="gongneng" align="left" style="width: 1000px;">
				<div id="xitong" align="center"
					style="width: 100%; height: 31px; font-weight: bold; margin-top: 15px; padding-top: 5px; padding-bottom: 5px; background: url('admin/images/admin_title.jpg');">
					<font color="#ffffff">修改密码</font>
				</div>


				<div align="center"
					style="margin-top: 10px; border: solid 1px #0170b8;">
					<form action="AdminAction!updatePassword.action" method="post"
						onsubmit="return checkForm()">
						<input type="hidden" name="admin.id" value="${admin.id}">
						<table width="100%">
							<tr>
								<td align="right">
									原密码:
								</td>
								<td>
									<input id="oldPassword" type="password" name="oldPassword">
								</td>
							</tr>
							<tr>
								<td align="right">
									新密码:
								</td>
								<td>
									<input id="newPassword1" type="password"
										name="admin.adminPassword">
								</td>
							</tr>
							<tr>
								<td align="right">
									确认新密码:
								</td>
								<td>
									<input id="newPassword2" type="password">
								</td>
							</tr>
							<tr>
								<td></td>
								<td colspan="1" align="left">
									<input type="submit" value="修改"
										style="width: 80px; height: 50px;">
									<input type="reset" value="重置"
										style="width: 80px; height: 50px;">
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</center>
	</body>
</html>
