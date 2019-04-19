<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息修改</title>
<link type="image/png" href="http://tm-image.tianyancha.com/tm/b928c552e7d861b16141d5d40e950749.jpg" rel="shortcut icon">
<script src="<%=request.getContextPath()%>/static/js/jquery-3.3.1.min.js"></script>
<link href="<%=request.getContextPath()%>/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"rel="stylesheet" />
<script src="<%=request.getContextPath()%>/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/Course/css/style.css">

</head>
<script type="text/javascript">
	function submituserform(){
		var password= document.getElementById("password3");		
		if(password.value.trim()==""){
			alert("密码不能为空");
		}else{
			document.getElementById("userform").submit();
		}
			
	}
	
	$(function() {		
		$("#password3").blur(function() {
			var password= document.getElementById("password3");
			if(password.value.trim()==""){
				$("#tip1").html("密码不能为空!");		
			}else{
				$("#tip1").html("");
			}
		});	
	});
	
</script>
<body>
<br>
<br>
<div class="user_mesg">
<p align="left"><font size="6" color="#3CB371" face="华文隶书">&nbsp;&nbsp;&nbsp;&nbsp;信息修改及完善</font>
	&nbsp;&nbsp;&nbsp;<button onclick="submituserform()" class="btn btn-default btn-xs"><font color="#4682B4">内容提交</font></button>
	&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/User/Myread" class="btn btn-default btn-xs"><font color="#4682B4">返回首页</font></a>
</p>
<br>
	<form action="<%=request.getContextPath()%>/User/Update" method="post" id="userform" class="fl">
		<input name="id" value="${updateUser.id}" type="hidden">
  		<input name="role" value="${updateUser.role}" type="hidden">
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
					<input type="text" name="username" class="form-control" id="username3" value="${updateUser.username}" readonly="readonly">
	      			<font color="red"><span id="tip"></span></font>
	      			<input type="hidden" value="" class="form-control" id="tip2">
				</div>
				<div style="clear: both"></div>
			</div>
			
			<div class="item">
				<span>密码</span>
				<div class="item-right">
					<input type="text" name="password" class="form-control" id="password3" value="${updateUser.password}">
      				<font color="red"><span id="tip1"></span></font>
				</div>
				<div style="clear: both"></div>
			</div>
			
			<div class="item">
				<span>手机号</span>
				<div class="item-right">
					<input type="text" name="phone" class="form-control" id="phone3" value="${updateUser.phone}" onkeyup="value=value.replace(/[^\d]/g,'')">
				</div>
				<div style="clear: both"></div>
			</div>
			<div class="item">
				<span>真实姓名</span>
				<div class="item-right">
					<input type="text" name="realname" class="form-control" id="realname3" value="${updateUser.realname}">
				</div>
				<div style="clear: both"></div>
			</div>
			<div class="item">
				<span>性别</span>
				<div class="item-right">
					<label class="radio-inline">
  						<input type="radio" name="sex" id="sex1" value="m" <c:if test="${updateUser.sex=='m'}">checked</c:if>> 男
					</label>
					<label class="radio-inline">
  						<input type="radio" name="sex" id="sex2" value="n" <c:if test="${updateUser.sex =='n'}">checked</c:if>> 女
					</label>
				</div>
				<div style="clear: both"></div>
			</div>
			<div class="item">
				<span>年龄</span>
				<div class="item-right">
					<input type="text" name="age" class="form-control" id="age3" value="${updateUser.age}" onkeyup="value=value.replace(/[^\d]/g,'')">
				</div>
				<div style="clear: both"></div>
			</div>
		</div>
	</for>
</div>
</body>
</html>
