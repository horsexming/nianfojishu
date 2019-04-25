<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="renderer" content="webkit">
<meta name="applicable-device" content="pc,mobile">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>我的阅读</title>
<link type="image/png" href="http://tm-image.tianyancha.com/tm/b928c552e7d861b16141d5d40e950749.jpg" rel="shortcut icon">
<script  src="<%=request.getContextPath()%>/static/js/jquery-3.3.1.min.js"></script>
<link href="<%=request.getContextPath()%>/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"rel="stylesheet" />
<script src="<%=request.getContextPath()%>/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/static/indexstyle/pintuer.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/static/indexstyle/style.css">
</head>
<script type="text/javascript">
function submituserform(){
	var password= document.getElementById("password3");	
	var tip7= document.getElementById("tip7");
	var tip= document.getElementById("tip2");
	if(password.value.trim()==""){
		alert("密码不能为空");
		return false;
	}else if(tip7.value.trim()!=""){	
		alert("手机号不符合要求");
		return false;
	}else if(tip.value.trim()!=""){
		alert("用户名已存在");
		return false;
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

$(function() {
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
				url : "<%=request.getContextPath()%>/User/SelUserByP4",
				data : "phone=" + phone,
				success : function(data) {
					if(data=="1"){
						$("#tip6").html("手机号已存在!");
						document.getElementById("tip7").value='手机号已存在!';
					}else{
						document.getElementById("tip7").value='';
						$("#tip6").html("");
					}
				}
			});
		}
	});
	
	$("#username3").blur(function() {
		var uname = $(this).val();
		var username= document.getElementById("username3");
		if(username.value.trim()==""){
			$("#tip").html("用户名不能为空!");		
		}else{			
			$.ajax({
				type : "POST",
				url : "<%=request.getContextPath()%>/User/SelectByUsername2",
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
	
});
</script>
<body>
	<p>&nbsp;</p><p>&nbsp;</p>
	<p align="right"><font size="3">欢迎<font size="3" color="green">${username}</font>来到系统
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</p>
	<p>&nbsp;</p>
	<div class="container-layout">
	    <div class="line head clearfix ">
	    	<div class="head-mu xb8 xm10 xs12 xl12">
	        	<div class="head-logo xb2 xm2 xs4 xl4">
	            	<a href="#">读书育人</a>
	        	</div>
	        	<ul class="head-list list-inline xb6 xm6 xs6 xl12">
	            	<li><a href="<%=request.getContextPath()%>/User/Myread">返回首页</a></li>	        
	       	
	            	<li><a href="<%=request.getContextPath()%>/Common/logOut">退出系统</a></li>       
	        	</ul>         
	    	</div>
		</div>
	    <div class="index-body xb8 xm12 xs12 xl12">
	        <div class="line index-body-nr-left clearfix">
	            <div class="main-tit xl12 xs12 xm12 xb12">
	                <span class='mt_tit'>信息完善及修改</span>
	                <span class='mt_tit'>&nbsp;</span>	
	                <button onclick="submituserform()" class="btn btn-default btn-xs"><font color="#4682B4">提交修改</font></button>	
	                <p><br>                	          
	              	<form action="<%=request.getContextPath()%>/User/Update" method="post" id="userform" class="form-horizontal">
						<input name="id" value="${updateUser.id}" type="hidden">
				  		<input name="role" value="${updateUser.role}" type="hidden">
						<div class="form-group">
						    <label for="inputEmail3" class="col-sm-1 control-label">用户名:&nbsp;&nbsp;</label>
						    <div class="col-sm-6">
								<input type="text" name="username" class="form-control" id="username3" value="${updateUser.username}">
					      		<font color="red"><span id="tip"></span></font>
					      		<input type="hidden" value="" class="form-control" id="tip2">
					      	</div>
						 </div>
						<div class="form-group">
						    <label for="inputEmail3" class="col-sm-1 control-label">密码:&nbsp;&nbsp;</label>
						    <div class="col-sm-6">
								<input type="text" name="password" class="form-control" id="password3" value="${updateUser.password}">
				      			<font color="red"><span id="tip1"></span></font>
					      	</div>
						 </div>
						 <div class="form-group">
						    <label for="inputEmail3" class="col-sm-1 control-label">手机号:&nbsp;&nbsp;</label>
						    <div class="col-sm-6">
								<input type="text" name="phone" class="form-control" id="phone3" value="${updateUser.phone}" onkeyup="value=value.replace(/[^\d]/g,'')">
					      		<input type="hidden" value="" id="tip7">
								<input type="hidden" value="" id="tip8">
								<font color="#FF0000">     
									<span class="tip mobile_hint" id="tip6" style="width:150px"></span>
								</font>
					      	</div>
						 </div>
						 <div class="form-group">
						    <label for="inputEmail3" class="col-sm-1 control-label">真实姓名:&nbsp;&nbsp;</label>
						    <div class="col-sm-6">
								<input type="text" name="realname" class="form-control" id="realname3" value="${updateUser.realname}">
					      	</div>
						 </div>
						  <div class="form-group">
						  	<label for="inputEmail3" class="col-sm-1 control-label">性别:&nbsp;&nbsp;</label>
						    <label for="inputEmail3" class="col-sm-1 control-label">&nbsp;</label>
						    <div class="col-sm-6">
						    	<div class="radio">
									<label class="radio-inline">
					  					<input type="radio" name="sex" id="sex1" value="m" <c:if test="${updateUser.sex=='m'}">checked</c:if>> 男
									</label>
									<label class="radio-inline">
									&nbsp;
									</label>
									<label class="radio-inline">
					  					<input type="radio" name="sex" id="sex2" value="n" <c:if test="${updateUser.sex =='n'}">checked</c:if>> 女
									</label>
								</div>					      	
							</div>
						 </div>
						 <div class="form-group">
						    <label for="inputEmail3" class="col-sm-1 control-label">年龄:&nbsp;&nbsp;</label>
						    <div class="col-sm-6">
								<input type="text" name="age" class="form-control" id="age3" value="${updateUser.age}" onkeyup="value=value.replace(/[^\d]/g,'')">
					      	</div>
						 </div>
					</form>	               
	            </div>
	        </div>
		</div> 
	</div>
</body>
</html>