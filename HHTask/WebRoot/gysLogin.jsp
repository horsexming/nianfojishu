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
		<link rel="shortcut icon" href="favicon.ico" />
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
	var deptNumber = document.getElementById("deptNumber");
	var password = document.getElementById("password");
	var vcode = document.getElementById("vcode");
	if (code.value == "") {
		document.getElementById("codeMessage").innerHTML = "<font color='red'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;* 用户名不能为空</font>";
		code.focus();
		return false;
	} else {
		document.getElementById("codeMessage").innerHTML = "<font color='#000000'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </font>";
	}

	if (password.value == "") {
		document.getElementById("passwordMessage").innerHTML = "<font color='red'>&nbsp;&nbsp;&nbsp;&nbsp; * 密码不能为空</font>";
		password.focus();
		return false;
	} else {
		document.getElementById("passwordMessage").innerHTML = "<font color='#000000'>&nbsp;&nbsp;&nbsp;&nbsp;</font>";
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
		} else if ("deptNum" == arr0) {
			deptNum = arr[1];
		} else if ("password" == arr0) {
			password = arr[1];
		} else if ("vcode" == arr0) {
			vcode = arr[1];
		}
	}

	//如果存在工号的cookie，为页面赋值
	if (code.length > 0) {
		var pageCode = document.getElementById("code");
		var pageDeptNumber = document.getElementById("deptNumber");
		var pagePassword = document.getElementById("password");
		var pagePutoLogin = document.getElementById("autoLogin");
		pageCode.value = code;
		pageDeptNumber.value = deptNum;
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
	</head>
	<body bgcolor="#ffffff" onload="autoLogin()">
		<center>
			<div
				style="width: 981px; height: 150px; border: 1px solid #000; background: url('${pageContext.request.contextPath}/upload/file/sysImages/tasklogo.jpg');">
			</div>
			<div
				style="border: solid 1px #000000; width: 980px; font-family: 微软雅黑; font-size: 10px; padding-top: 20px;"
				align="center">
				<s:if test="errorMessage!=null">
					<div align="left" style="padding-left: 10px">
						<img alt="登录错误" src="images/error.png" align="bottom"
							style="height: 30px">
						<font color="red" size="2px" style="font-weight: bold;">${errorMessage}</font>
					</div>
				</s:if>
				<form action="UsersAction!login.action" method="post"
					onsubmit="return checkLogin()" target="_top">
					<input name="pageStatus" type="hidden" value="gys" />
					<table border="0">
						<tr>
							<th colspan="2" style="height: 50px;">
								招标系统——供应商登录
							</th>
						</tr>
						<tr>
							<td align="left">
								<label for="code ">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用&nbsp;户&nbsp;
									名:&nbsp;&nbsp;&nbsp;&nbsp;
								</label>
							</td>
							<td align="right">
								<input type="text" style="border: #000000 1px solid;"
									onfocus="chageBorder(this)"
									onblur="blurBorder(this);checkLogin()" name="user.code"
									id="code" value="${user.code}" class="search_shuru1">
							</td>
							<td>
								<div id="codeMessage" style="width: 320px;">
									&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr style="display: none;">
							<td align="left">
								<label for="deptNumber">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;部门编号:
								</label>
							</td>
							<td align="right">
								<input type="text" onfocus="chageBorder(this)"
									onblur="blurBorder(this);checkLogin()"
									style="BORDER: #000000 1px solid" name="password.deptNumber"
									id="deptNumber" value="gys" class="search_shuru1">
							</td>
							<td>
								<!-- 
								<div id="deptNumberMessage" style="width: 320px;">
									&nbsp;&nbsp;&nbsp;&nbsp;
									<font color="red">*</font>&nbsp;部门编号为字母组成
								</div>
								 -->
							</td>
						</tr>
						<tr>
							<td align="left">
								<div style="">
									<label for="password">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:
									</label>
								</div>
							</td>
							<td align="right">
								<input type="password" onfocus="chageBorder(this)" id="password"
									onblur="blurBorder(this);checkLogin()"
									style="BORDER: #000000 1px solid;" name="password.password"
									id="passwordId" value="${password.password}"
									class="search_shuru1">
							</td>
							<td>
								<div id="passwordMessage" style="width: 320px;">
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div id="v1" style="display: none;">
									<label for="vcode">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;验&nbsp;证&nbsp;
										码:
									</label>
								</div>
							</td>
							<td>
								<div id="v2" style="display: none;">
									<input type="text" onfocus="chageBorder(this)"
										onKeyUp="this.value=this.value.replace(/\D/g,'')"
										onblur="blurBorder(this)" style="BORDER: #000000 1px solid;"
										maxlength="4" name="vcode" id="vcode" class="search_shuru1">
								</div>
							</td>
							<td>
								<div id="v3" style="width: 310px; display: none;">
									&nbsp;&nbsp;&nbsp;&nbsp;
									<font color="red">*</font>&nbsp;请输入前四位验证码
								</div>
							</td>
						</tr>
						<tr>
							<td></td>
							<td>
								<div id="v4" style="display: none;">
								</div>
							</td>
							<td>
								<div id="v5" style="display: none;">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<a href="javascript:chageVcode()">看不清,换一张 </a>
								</div>
							</td>
						</tr>
						<tr>
							<td></td>
							<td align="left">
								<input type="checkbox" value="yes" id="autoLogin"
									name="autoLogin">
								一周内自动登录
							</td>
						</tr>
						<tr>
							<td colspan="3" align="left">
								<br>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="submit" value="登录"
									style="width: 100px; height: 50px">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="reset" value="重置"
									style="width: 100px; height: 50px">
								&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
					</table>
				</form>
			</div>
		</center>
	</body>
</html>