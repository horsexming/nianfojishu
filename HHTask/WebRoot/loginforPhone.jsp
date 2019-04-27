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
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<title>PEBS生产力平衡系统用户登录</title>
		<link rel="shortcut icon"
			href="<%=basePath%>/upload/file/sysImages/favicon.ico" />
		<link type="text/css" rel="styleSheet" href="css/login.css">
		<script type="text/javascript"
			src="<%=basePath%>/javascript/jquery-1.8.3.js">
</script>
		<style type="text/css">
.redBorder {
	border: solid 1px red;
}
</style>
		<script type="text/javascript">
//登录检查
function checkLogin() {
	var code = document.getElementById("code");
	var password = document.getElementById("password");
	if (code.value == "") {
		//document.getElementById("codeMessage").innerHTML = "<font color='red'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;* 员工号不能为空</font>";
		$("#errmessageDiv").html("请填写手机号!");
		code.focus();
		//return false;
	} else {
		$("#code").css(name)
	}

	if (password.value == "") {
		//document.getElementById("passwordMessage").innerHTML = "<font color='red'>&nbsp;&nbsp;&nbsp;&nbsp;* 密码不能为空</font>";
		//alert("请输入密码!");
		$("#errmessageDiv").html("请输入验证码!");
		password.focus();
		//return false;
	} else {
		//document.getElementById("passwordMessage").innerHTML = "<font color='red'>&nbsp;&nbsp;&nbsp;&nbsp;</font>";
	}
	return true;

}
//自动登录处理
function autoLogin() {
	//history.go(-1);
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
		} //else if ("deptNum" == arr0) {
		//deptNum = arr[1];
		//} 
		else if ("password" == arr0) {
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
	<body onload="autoLogin()">
		<div class="content">
			<div class="logo">
				<img src="img/loginlogo3.png" height="48" width="304">
			</div>
			<div class="loginbox">
				<form class="logininfo" action="UsersAction!loginByPhone.action"
					method="post" onsubmit="return checkLogin()" target="_top">
					<input name="id" value="${param.id}" type="hidden">
					<div class="form-item">
						<label>
							<img src='img/user.png' height="19" width="20">
						</label>
						<input type="text" placeholder="请输入手机号" name="cardNumber"
							id="code" value="${user.code}">
					</div>
					<div class="form-item">
						<label>
							<img src='img/lock.png' height="21" width="15">
						</label>
						<input type="text" placeholder="验证码" id="password"
							onkeyup="getNumber()" name="password.password"
							value="${password.password}">
					</div>
					<div class="form-item">
						<input id="send_id" type="button" onclick="onsend()" value="获取验证码"
							align="middle">
						<span id="isok"> </span>
						<%--<span style="color: red"> 序号为：${number}</span>
					--%>
					</div>
					<div id="errmessageDiv"
						style="color: red; font-size: 12px; font-weight: bolder; margin-top: 10px; height: 15px; padding-left: 45px;">
						${errorMessage}
					</div>
					<div class="form-item">
						<input type="submit" value="登录" id="ok" >
					</div>
				</form>
				<%--<tr>
										<th align="right">
											验证码:
										</th>
										<td>
											<input id="number1" type="text" style="width: 80px;"
												onkeyup="getNumber()" align="middle" maxlength="6">
											<input id="send_id" type="button" onclick="onsend()"
												value="获取验证码" align="middle">
											<span id="isok"> </span>
											<span style="color: red"> 序号为：${number}</span>
										</td>
									</tr>
			--%>
			</div>
		</div>
		<script type="text/javascript">
var pathname = window.location.pathname;
if (/Android|webOS|iPhone|iPod|BlackBerry/i.test(navigator.userAgent)) {
	//if (pathname.indexOf("login.jsp") < 0) {
	//	window.location.href = "login.jsp";
	$("#loginType").val("qx");
	$(".content").removeClass("content");
	$(".loginbox").removeClass("loginbox");
	$(".logo").hide();
	$(".logininfo").css("background-color", "#ffffff");
	//}
}
//else {
//	if (pathname.indexOf("otherLogin.jsp") < 0)
//		window.location.href = "otherLogin.jsp";
//}

var seconds = 100;
var speed = 1000;
var btn = document.getElementById("send_id");
var xhnum;
//发送验证码			
function onsend() {
	$("#send_id").attr("disabled", "disabled");
	var phone = $("#code").val();
	$.ajax( {
		type : "POST",
		url : "UsersAction!send.action?id=${param.id}&cardNumber=" + phone,
		data : {},
		dataType : "json",
		success : function(data) {
			var bol=data.success;
			if(bol){
				xhnum = data.data;
				countDown();
				seconds = 120;
			}else{
				alert(data.message);
				$("#send_id").removeAttr("disabled");
			}
		}
	});
}

//一分钟倒计时
function countDown(seconds, speed) {
	var txt = ((seconds < 10) ? "0" + seconds : seconds) + "秒";
	btn.value = txt;
	var timeId = setTimeout("countDown(seconds--,speed)", speed);
	if (seconds == 0) {
		clearTimeout(timeId);
		$("#send_id").removeAttr("disabled");
		btn.value = "重新获取验证码";
	}
}

function getNumber() {
	var number1 = $("#password").val();
	var a = number1.length;
	var number2 = "";
	if (a == 6) {
		var strCookie = document.cookie;//获得所有cookie
		var arrCookie = strCookie.split(";");
		for ( var i = 0; i < arrCookie.length; i++) {
			var arr = arrCookie[i].split("=");
			var arr0 = arr[0].replace(/\ /g, "");
			if (xhnum == arr0) {
				number2 = arr[1];
				if (number1 == number2) {
					$("#isok")
							.html(
									"<img	src='${pageContext.request.contextPath}/images/success.jpg'>");
					$("#ok").removeAttr("disabled");
				} else {
					$("#isok")
							.html(
									"<img	src='${pageContext.request.contextPath}/images/error.jpg'>");
				}
			}
		}
	}
}
</script>
	</body>
</html>
