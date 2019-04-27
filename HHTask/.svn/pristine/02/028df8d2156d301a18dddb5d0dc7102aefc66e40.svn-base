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
		<link rel="stylesheet" href="javascript/keyboard/keyboard.css" />

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
function checkLogin(obj, tag) {
	var cardId = document.getElementById("cardId");
	var password = document.getElementById("password");
	var vcode = document.getElementById("vcode");
	if (cardId.value == "") {
		document.getElementById("cardIdMessage").innerHTML = "<font color='red'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;* 卡号不能为空</font>";
		cardId.focus();
		return false;
	} else {
		document.getElementById("cardIdMessage").innerHTML = "<font color='#000000'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;* 您的员工卡卡号</font>";
	}

	if (password.value == "") {
		document.getElementById("passwordMessage").innerHTML = "<font color='red'>&nbsp;&nbsp;&nbsp;&nbsp;* 密码不能为空</font>";
		password.focus();
		return false;
	} else {
		document.getElementById("passwordMessage").innerHTML = "<font color='#000000'>&nbsp;&nbsp;&nbsp;&nbsp;忘记密码了？</font>";
	}
	obj.action = "UsersAction!otherLogin.action?pageStatus=" + tag;
	obj.submit();
}

//看不清 换一张
function chageVcode() {
	document.getElementById("v4").innerHTML = "<img src='util/vcode.jsp'>";

}

//文本框边框
function chageBorder(obj) {
	obj.style.border = "#000000 1px solid";
}
function blurBorder(ids) {
	var obj = document.getElementById(ids);
	if (obj.value == "") {
		obj.style.border = "red 1px solid";
		document.getElementById(ids).focus();
		document.getElementById(ids).select();
	}

}

var vcode = "";//验证码
var strCookie = document.cookie;//获得所有cookie
var arrCookie = strCookie.split(";");
for ( var i = 0; i < arrCookie.length; i++) {
	var arr = arrCookie[i].split("=");
	var arr0 = arr[0].replace(/\ /g, "");
	if ("vcode" == arr0) {
		vcode = arr[1];
	}
}

//自动登录处理
function autoLogin() {
	//页面加载选中卡号
	document.getElementById("cardId").select();
	//验证码
	if (vcode == "passwordError") {
		var i = 1;
		for (i; i < 6; i++) {
			document.getElementById("v" + i).style.display = "block";
		}
		document.getElementById("v4").innerHTML = "<img src='util/vcode.jsp'>";
	}
}

function toPassword(event) {
	var e = event || window.event || arguments.callee.caller.arguments[0];
	if (e.keyCode == 13) {
		document.getElementById("password").select();
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
				<form action="" method="post">
					<table border="0">
						<tr>
							<td align="left">
								<label for="code">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									卡&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号:&nbsp;&nbsp;
								</label>
							</td>
							<td align="right">
								<input type="text" style="border: #000000 1px solid;"
									onfocus="chageBorder(this)" onblur="blurBorder('cardId');"
									onkeyup="toPassword()" name="user.cardId" id="cardId"
									value="${user.cardId}" class="search_shuru1">
							</td>
							<td>
								<div id="cardIdMessage" style="width: 320px;">
									&nbsp;&nbsp;&nbsp;&nbsp;
									<font color="red">*</font>&nbsp;您的员工卡卡号
								</div>
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
									onblur="blurBorder('passwordId');"
									style="BORDER: #000000 1px solid;" name="password.password"
									id="passwordId" value="${password.password}"
									class="search_shuru1">
							</td>
							<td>
								<div id="passwordMessage" style="width: 320px;">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 忘记密码了？
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
							<td colspan="3" align="left">
								<br>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="button" value="正常登录"
									onclick="checkLogin(this.form,'qx')"
									style="width: 100px; height: 50px">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="button" value="领工序登录"
									onclick="checkLogin(this.form,'gx')"
									style="width: 100px; height: 50px">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="reset" value="清空"
									style="width: 100px; height: 50px">
								&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
					</table>
				</form>
			</div>
		</center>
		<script src="javascript/keyboard/js/libs/jquery-1.6.4.min.js">
</script>
		<script
			src="javascript/keyboard/js/jquery.keyboard/jquery.keyboard.js">
</script>
		<script
			src="javascript/keyboard/js/jquery.keyboard/keyboards/qwerty.js">
</script>
		<script src="javascript/keyboard/js/jquery.keyboard/plugins/form.js">
</script>
		<script src="javascript/keyboard/js/jquery.selection.js">
</script>
		<script>
(function($) {
	$(document).ready(function() {
		$('body').keyboard( {
			keyboard : 'qwerty',
			plugin : 'form'
		});
		$('#keyboard').bind('change', function() {
			$('body').keyboard('keyboard', $(this).val());
		});
	})
})(jQuery);
</script>
	</body>
</html>
