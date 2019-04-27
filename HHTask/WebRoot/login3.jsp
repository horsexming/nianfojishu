<%@ page language="java" import="java.util.*,com.task.entity.*"
	pageEncoding="utf-8" session="false"%>
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

		<title>生产力生态平衡系统</title>
		<link rel="shortcut icon"
			href="<%=basePath%>/upload/file/sysImages/favicon.ico" />
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="生产力生态平衡系统,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="css/css.css" />
		<script type="text/javascript">
//登录检查
function checkLogin() {
	var code = document.getElementById("code");
	var password = document.getElementById("password");
	var vcode = document.getElementById("vcode");
	if (code.value == "") {
		document.getElementById("codeMessage").innerHTML = "<font color='red'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;* 员工号不能为空</font>";
		code.focus();
		return false;
	} else {
		document.getElementById("codeMessage").innerHTML = "<font color='#04ee1a'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;* 员工号为三位数字组成</font>";
	}

	if (password.value == "") {
		document.getElementById("passwordMessage").innerHTML = "<font color='red'>&nbsp;&nbsp;&nbsp;&nbsp;* 密码不能为空</font>";
		password.focus();
		return false;
	} else {
		document.getElementById("passwordMessage").innerHTML = "<font color='red'>&nbsp;&nbsp;&nbsp;&nbsp;</font>";
	}
	return true;

}

//看不清 换一张
function chageVcode() {
	document.getElementById("v4").innerHTML = "<img src='util/vcode.jsp'>";

}

//文本框边框
function chageBorder(obj) {
	obj.style.border = "red 1px solid";
}
function blurBorder(obj) {
	obj.style.border = "#000000 1px solid";
}

//自动登录处理
function autoLogin() {
	document.getElementById("code").select();

	var code = "";// 员工号
	var deptNum = "";// 部门编号
	var password = "";// 密码
	var vcode = "";//验证码
	var strCookie = document.cookie;//获得所有cookie
	var arrCookie = strCookie.split(";");
	for ( var i = 0; i < arrCookie.length; i++) {
		var arr = arrCookie[i].split("=");
		var arr0 = arr[0].replace(/\ /g, "");
		if ("code" == arr0) {
			code = arr[1];
		} else if ("password" == arr0) {
			password = arr[1];
		} else if ("vcode" == arr0) {
			vcode = arr[1];
		}
	}

	//如果存在工号的cookie，为页面赋值
	if (code.length > 0) {
		var pageCode = document.getElementById("code");
		var pagePassword = document.getElementById("password");
		var pagePutoLogin = document.getElementById("autoLogin");
		pageCode.value = code;
		pagePassword.value = password;
		if (pageCode.value != "") {
			pagePutoLogin.checked = "checked";
		}
	}

	//验证码
	if (vcode == "passwordError") {
		var i = 1;
		for (i; i < 6; i++) {
			document.getElementById("v" + i).style.display = "block";
		}
		document.getElementById("v4").innerHTML = "<img src='util/vcode.jsp'>";
	}
}
</script>
		<style type="text/css">
.div1 {
	background: url(images/loginUser.png);
	background-repeat: no-repeat;
	background-position: 0px -5px;
	height: 54px;
	width: 600px;
	margin-top: 0px;
	padding-top: 10px;
}

.div2 {
	background: url(images/loginUser.png);
	background-repeat: no-repeat;
	background-position: 0px -70px;
	height: 58px;
	width: 600px;
	margin-top: 20px;
	padding-top: 12px;
}

.div3 {
	background: url(images/loginUser.png);
	background-repeat: no-repeat;
	background-position: 0px -140px;
	height: 58px;
	width: 600px;
	margin-top: 20px;
	padding-top: 10px;
}

.div4 {
	background: url(images/loginUser.png);
	background-repeat: no-repeat;
	background-position: 0px 0px;
	height: 58px;
	width: 600px;
	margin-top: 20px;
	padding-top: 10px;
}
</style>
	</head>
	<body
		style="background: url('images/background.jpg') no-repeat; background-size: cover; -moz-background-size: cover;"
		onload="autoLogin()">
		<center>
			<div style="width: 600px; height: 180px">
			</div>
			<div
				style="width: 980px; font-family: 微软雅黑; font-size: 10px; margin-top: 20px; color: #04ee1a;"
				align="center">
				<s:if test="errorMessage!=null">
					<div align="left" style="padding-left: 10px">
						<img alt="登录错误" src="images/error.png" align="bottom"
							style="height: 30px">
						<font color="red" size="2px" style="font-weight: bold;">${errorMessage}</font>
						<br />
						<br />
					</div>
				</s:if>
				<s:if test="successMessage!=null">
					<div align="left" style="padding-left: 10px">
						<font color="red" size="2px" style="font-weight: bold;">${successMessage}</font>
					</div>
				</s:if>
				<form action="UsersAction!login.action" method="post"
					onsubmit="return checkLogin()" target="_top">
					<div class="div1" align="right">
						<input type="text" style="border: #000000 1px solid;"
							onfocus="chageBorder(this)"
							onblur="blurBorder(this);checkLogin()" name="user.code" id="code"
							value="${user.code}" class="search_shuru1">
					</div>
					<div id="codeMessage" style="width: 320px;">
						&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="#04ee1a">*</font>&nbsp;员工号为三位数字组成
					</div>
					<div class="div3" align="right">
						<input type="password" onfocus="chageBorder(this)" id="password"
							onblur="blurBorder(this);checkLogin()"
							style="BORDER: #000000 1px solid;" name="password.password"
							id="passwordId" value="${password.password}"
							class="search_shuru1">
					</div>
					<div id="passwordMessage" style="width: 320px;">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
					<div id="v4" style="width: 310px; display: none;">
						&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="red">*</font>&nbsp;请输入前四位验证码
					</div>
					<div id="v4" style="display: none;">
					</div>
					<input type="checkbox" value="yes" id="autoLogin" name="autoLogin">
					一周内自动登录
					<div align="left" style="width: 600px;">
						<input type="image" src="images/loginInp.png" width="230px;"
							height="70px">
					</div>
				</form>
			</div>
		</center>
	</body>
</html>
