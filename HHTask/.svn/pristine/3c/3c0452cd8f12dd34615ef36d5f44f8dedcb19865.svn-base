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
		<meta name="viewport" content="width=device-width, initial-scale=1" /> 
		<title>修改密码,${companyInfo.shortName}作业网</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="shortcut icon" href="favicon.ico" />
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="css/css.css">
		<script type="text/javascript"
			src="<%=basePath%>javascript/jquery-1.8.3.js">
</script>
<script type="text/javascript">
function checkForm() {
	var oldPassword = document.getElementById("oldPassword");
	var newPassword1 = document.getElementById("newPassword1");
	var newPassword2 = document.getElementById("newPassword2");
	var oldP=true,new1P=true,new2P=true;
	if (oldPassword.value == "") {
		$("#oldPwdIcon").html("✘");
		$("#oldPwdFont").html("请输入原密码!");
		oldPassword.focus();
		oldP=false;
	} else if (newPassword1.value == "") {
		$("#oldPwdIcon").css("color","blue");
		$("#oldPwdIcon").html("✔");
		$("#oldPwdFont").html("");

		$("#newPwd1Icon").html("✘");
		$("#newPwd1Font").html("请输入新密码!");
		newPassword2.value = "";
		newPassword1.focus();
		new1P= false;
	}else if (newPassword2.value == "") {
		$("#newPwd2Icon").html("✘");
		$("#newPwd2Font").html("请再次输入新密码!");
		newPassword2.focus();
		new2P= false;
	} 
	else if (newPassword2.value != newPassword1.value) {
		$("#newPwd2Icon").html("✘");
		$("#newPwd2Font").html("两次输入的密码不一致,请重新输入!");
		newPassword2.value = "";
		newPassword1.select();
		new2P= false;
	} else if (newPassword2.value == oldPassword.value) {
		$("#newPwd2Font").html("新密码和原密码一致!无需修改!");
		oldPassword.focus();
		new2P= false;
	}
<%--	else {--%>
<%--		alert("tr");--%>
<%--		return true;--%>
<%--	}--%>
	
	return oldP&&new1P&&new2P;
}



</script>
	</head>
	<body bgcolor="#ffffff">
		<center>
			<div id="gongneng">
				<div align="center" style="">
					<h2>
						<font color="red" size="4px" id="error">${successMessage}
							${errorMessage}</font>
					</h2>
					<form action="UsersAction!updatePassword.action" method="post"
						onsubmit="return checkForm()">
						<table class="table" border="0" width="850px">
							<tr>
								<td align="right" colspan="4">
									<font style="color:red;">输入密码只能包含数字字母的组合，不能有特殊字符</font>
								</td>
								
							</tr>
							
							<tr>
								<td align="right" width="200px">
									原密码:
								</td>
								<td  width="400px">
									<input id="oldPassword" type="password"
										name="password.password">
								</td>
								<td id="oldPwdIcon" width="50px"></td>
								<td id="oldPwdFont" width="200px"></td>
							</tr>
							<tr>
								<td align="right">
									新密码:
								</td>
								<td>
									<input id="newPassword1" type="password" name="password1" onkeyup="chckPwd(this)">
								</td>
<%--							新密码新密码2	--%>
								<td id="newPwd1Icon" style="color:red;"></td>
								<td id="newPwd1Font" style="color:red;"></td>
								
							</tr> 
							<tr>
								<td align="right">
									确认新密码:
								</td>
								<td>
									<input id="newPassword2" type="password" name="password2" onkeyup="chckPwd(this)">
								</td>
								<td id="newPwd2Icon" style="color:red;"></td>
								<td id="newPwd2Font" style="color:red;"></td>
							</tr>
							<tr>
								
								<td colspan="4" align="left">
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
			<%@include file="/util/foot.jsp"%>
		</center>
	</body>
	<SCRIPT type="text/javascript">
	$("#newPassword1").keyup(function(){
		var newPassword1 = document.getElementById("newPassword1");
		var rule=/^[0-9a-zA-Z]*$/;
		if(newPassword1.value!=""){
			if(!rule.test(newPassword1.value)){ 	
				$("#newPwd1Font").html("输入包含非法字符,请重输");
				$("#newPwd1Icon").html("✘");
				$("#newPassword1").val("");
				newPassword1.focus();
				new1= false;
			}else{
				$("#newPwd1Font").html("");
				$("#newPwd1Icon").css("color","blue");
				$("#newPwd1Icon").html("✔");	
				
			}
		}
	});
	$("#newPassword2").keyup(function(){
		var newPassword1 = document.getElementById("newPassword2");
		var rule=/^[0-9a-zA-Z]*$/;
		if(newPassword1.value!=""){
			if(!rule.test(newPassword2.value)){ 	
				$("#newPwd2Font").html("输入包含非法字符，请重输");
				$("#newPwd2Icon").html("✘");
				$("#newPassword2").val("");
				newPassword1.focus();
				new2= false;
			}else{
				$("#newPwd2Font").html("");
				$("#newPwd2Icon").html("✔");	
				$("#newPwd2Icon").css("color","blue");
			}
		}
	})

	
	
	
<%--	function chckPwd(obj){--%>
<%--		--%>
<%--		var rule=/^[0-9a-zA-Z]*$/;--%>
<%--		if(obj.value!=""){--%>
<%--			--%>
<%--			if(!rule.test(obj.value)){--%>
<%--				obj.value="";--%>
<%--				$(this).focus();--%>
<%--				alert($(this).parent("td").next().html);--%>
<%--				alert($(this).parent("td").next().find("td").get(1));--%>
<%--				--%>
<%--			}--%>
<%--		}--%>
<%--	}--%>
	
	
	</SCRIPT>
	
	
</html>