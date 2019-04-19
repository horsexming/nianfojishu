<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/static/UserZC/normalize.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/static/UserZC/style.css">
  <script  src="<%=request.getContextPath()%>/static/js/jquery-3.3.1.min.js"></script>
  <link href="<%=request.getContextPath()%>/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"rel="stylesheet" />
  <script src="<%=request.getContextPath()%>/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
  <title>用户注册</title>
  <link type="image/png" href="http://tm-image.tianyancha.com/tm/b928c552e7d861b16141d5d40e950749.jpg" rel="shortcut icon">
</head>
<style type="text/css">
body {
	background-image: url(../static/login/img/1.jpg);
	text-align: center;
	background-size: 100% 100%;
	height: 100%;
	overflow: hidden;
	background-repeat: no-repeat;
	background-position: center;
	background-attachment: fixed;
}
</style>
<script type="text/javascript">
	$(function() {
		$("#username3").blur(function() {
			var uname = $(this).val();
			var username= document.getElementById("username3");
			if(username.value.trim()==""){
				$("#tip").html("用户名不能为空!");		
			}else{			
				$.ajax({
					type : "POST",
					url : "<%=request.getContextPath()%>/User/SelectByUsername",
					data : "username=" + uname,
					success : function(data) {
						if(data=="0"){
							$("#tip").html("用户名已存在!");
							document.getElementById("tip2").value='用户名已存在!';
						}else{
							$("#tip").html("");
							document.getElementById("tip2").value='';
						}
					}
				});
			}
		});
		$("#password3").blur(function() {
			var password= document.getElementById("password3");
			if(password.value.trim()==""){
				$("#tip1").html("密码不能为空!");		
			}else{
				$("#tip1").html("");
			}
		});
		$("#phone3").blur(function() {
			var phone= document.getElementById("phone3").value;
			if(phone.trim()==""){
				document.getElementById("tip7").value='手机号不能为空!';
				$("#tip6").html("手机号不能为空");
			}else if(!(/^1\d{10}$/.test(phone))){
					document.getElementById("tip7").value='手机号格式不正确!';
					$("#tip6").html("手机号格式不正确");		
			}else{
				
				$.ajax({
					type : "POST",
					url : "<%=request.getContextPath()%>/User/SelUserByP",
					data : "phone=" + phone,
					success : function(data) {
						if(data=="1"){
							$("#tip6").html("手机号已被注册!");
							document.getElementById("tip7").value='手机号已被注册!';
						}else{
							document.getElementById("tip7").value='';
							$("#tip6").html("");
						}
					}
				});
			}
		});	
		
		$("#password1").blur(function() {
			var password1= document.getElementById("password1").value;
			var password= document.getElementById("password3").value;
			if(password1 != password){
				document.getElementById("tip4").value='两次密码不一样!';
				$("#tip5").html("两次密码不一样");		
			}else{
				document.getElementById("tip4").value='';
				$("#tip5").html("");
			}
		});	
	});
	
	function beforeSubmit(form){
		var username= document.getElementById("username3");
		var password= document.getElementById("password3");
		var tip= document.getElementById("tip2");
		var tip7= document.getElementById("tip7");
		var tip4= document.getElementById("tip4");
		if(password.value.trim()==""){
			alert("密码不能为空");
			return false;
		}else if(username.value.trim()==""){
			alert("用户名不能为空");
			return false;
		}else if(tip.value.trim()!=""){
			alert("用户名已存在");
			return false;
		}else if(tip7.value.trim()!=""){
			alert("手机号不符合要求");
			return false;
		}else if(tip4.value.trim()!=""){
			alert("两次密码不一样");
			return false;
		}			
		document.getElementById("userform").submit();
	}
	
	function returnlogin(){	
		window.location.href ="<%=request.getContextPath()%>/Common/login";
	}
</script>

<body>
<p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p>
<div class="reg_div">
	<br>
    <p>注册</p>
    <form action="<%=request.getContextPath()%>/User/Register" method="post" onSubmit="return beforeSubmit(this);" id="userform">
    	<ul class="reg_ul">
			<li>
				<span>手机号码：</span>
				<input type="text" name="phone" class="reg_mobile" id="phone3" onkeyup="value=value.replace(/[^\d]/g,'')" >		  
				<input type="hidden" value="" id="tip7">     
				<span class="tip mobile_hint"></span>
			</li>
			<li>
				<span></span>
				<span id="tip6" style="width:150px"></span>
			</li>
			<li>
				<span>密码：</span>         
				<input type="password" name="password" class="reg_password" id="password3" >      	 
				<span class="tip password_hint"></span>
			</li>
			<li>
				<span></span>
				<span id="tip1" style="width:150px"></span>
			</li>
			<li>
				<span>确认密码：</span>
				<input type="password" name="password1" class="reg_confirm" id="password1">
				<input type="hidden" value="" id="tip4">
				<span class="tip confirm_hint"></span>
			</li>
			<li>
				<span></span>
				<span id="tip5" style="width:150px"></span>
			</li>  
			<li>
				<span>用户名：</span>      
				<input type="text" name="username" class="reg_user" id="username3">
				<input type="hidden" name="login" value="${login}">		
				<input type="hidden" value="" class="form-control" id="tip2">
				<span class="tip user_hint"></span>
			</li>
			<li>
				<span></span>
				<span id="tip" style="width:150px"></span>
			</li>
		</ul>
	</form>
	<ul>
		<li>
			<input onclick="beforeSubmit(this)" type="button" value="&nbsp;&nbsp;&nbsp;注册&nbsp;&nbsp;&nbsp;" class="btn btn-default btn-sm">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input onclick="returnlogin()" type="button" value="&nbsp;&nbsp;&nbsp;返回&nbsp;&nbsp;&nbsp;" class="btn btn-default btn-sm">
		</li>
	</ul>
</div>
</body>
</html>

