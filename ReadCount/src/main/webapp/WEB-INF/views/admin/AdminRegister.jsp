<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员注册</title>
<script src="<%=request.getContextPath()%>/static/js/jquery-3.3.1.min.js"></script>
<link href="<%=request.getContextPath()%>/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"rel="stylesheet" />
<script src="<%=request.getContextPath()%>/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/Course/css/style.css">

</head>
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
					url : "<%=request.getContextPath()%>/Admin/SelectByUsername",
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
	});
	
	function beforeSubmit(form){
		var username= document.getElementById("username3");
		var password= document.getElementById("password3");
		var tip= document.getElementById("tip2");
		if(password.value.trim()==""){
			alert("密码不能为空");
			return false;
		}else if(username.value.trim()==""){
			alert("用户名不能为空");
			return false;
		}else if(tip.value.trim()!=""){
			alert("用户名已存在");
			return false;
		}
		
		document.getElementById("userform").submit();
		
	}
</script>
<body>
<br>
<br>
<div class="user_mesg">
<p align="left"><font size="6" color="#3CB371" face="华文隶书">&nbsp;&nbsp;&nbsp;&nbsp;管理员注册</font>
	&nbsp;&nbsp;&nbsp;<button onclick="beforeSubmit(form)" class="btn btn-default btn-xs"><font color="#4682B4">内容提交</font></button>
	&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/Common/login" class="btn btn-default btn-xs"><font color="#4682B4">返回</font></a>
</p>
<br>
	<form action="<%=request.getContextPath()%>/Admin/Register" method="post" onSubmit="return beforeSubmit(this);" id="userform" class="fl">	
		<div class="quick_liuyan_con">
		<div class="item">
				<span></span>
				<div class="item-right">
					<span></span>
				</div>		
				<div style="clear: both"></div>
			</div>
			<div class="item">
				<span>用户名</span>
				<div class="item-right">
					<input type="text" name="username" class="form-control" id="username3">
	      			<font color="red"><span id="tip"></span></font>
	      			<input type="hidden" value="" class="form-control" id="tip2">
				</div>
				<div style="clear: both"></div>
			</div>
			
			<div class="item">
				<span>密码</span>
				<div class="item-right">
					<input type="text" name="password" class="form-control" id="password3">
      				<font color="red"><span id="tip1"></span></font>
				</div>
				<div style="clear: both"></div>
			</div>
			
			<div class="item">
				<span>手机号</span>
				<div class="item-right">
					<input type="text" name="phone" class="form-control" id="phone3" onkeyup="value=value.replace(/[^\d]/g,'')">
				</div>
				<div style="clear: both"></div>
			</div>
			<div class="item">
				<span>真实姓名</span>
				<div class="item-right">
					<input type="text" name="realname" class="form-control" id="realname3">
				</div>
				<div style="clear: both"></div>
			</div>
			<div class="item">
				<span>性别</span>
				<div class="item-right">
					<label class="radio-inline">
	  					<input type="radio" name="sex" id="sex1" value="m"> 男
					</label>
					<label class="radio-inline">
	  					<input type="radio" name="sex" id="sex2" value="n"> 女
					</label>
				</div>
				<div style="clear: both"></div>
			</div>
			<div class="item">
				<span>年龄</span>
				<div class="item-right">
					<input type="text" name="age" class="form-control" id="age3" onkeyup="value=value.replace(/[^\d]/g,'')">					
				</div>
				<div style="clear: both"></div>
			</div>		
		</div>
	</for>
</div>
</body>
</html>
