<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>读书系统</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/read/vendor/simple-line-icons/css/simple-line-icons.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/read/vendor/font-awesome/css/fontawesome-all.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/read/css/styles.css">
    <script  src="<%=request.getContextPath()%>/static/js/jquery-3.3.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/read/vendor/popper.js/popper.min.js"></script>
	<script src="<%=request.getContextPath()%>/read/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/read/vendor/chart.js/chart.min.js"></script>
	<script src="<%=request.getContextPath()%>/read/js/carbon.js"></script>
	<script src="<%=request.getContextPath()%>/read/js/demo.js"></script>
</head>
<script type="text/javascript"> 	
	function showTime() {
		var datetime = new Date();
		var h = datetime.getHours();
		var m = datetime.getMinutes();
		var s = datetime.getSeconds();
		if (h < 10) {
			h = "0" + h;
		}
		if (m < 10) {
			m = "0" + m;
		}
		if (s < 10) {
			s = "0" + s;
		}
		var hour = document.getElementById("hour");
		var minute = document.getElementById("minute");
		var seconds = document.getElementById("second");

		hour.innerHTML = h;
		minute.innerHTML = m;
		seconds.innerHTML = s;
		setTimeout(showTime, 1000);
	}
</script>
<script type="text/javascript">
function submituserform(){
	var password= document.getElementById("password3");	
	var username= document.getElementById("username3");
	var tip7= document.getElementById("tip7");
	var tip= document.getElementById("tip2");
	if(password.value.trim()==""){
		alert("密码不能为空");
		return false;
	}else if(username.value.trim()==""){
		alert("用户名不能为空");
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
				url : "<%=request.getContextPath()%>/Admin/SelUserByP5",
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
				url : "<%=request.getContextPath()%>/Admin/SelectByUsername2",
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
<body class="sidebar-fixed header-fixed" onload="showTime()">
<div class="page-wrapper">
    <nav class="navbar page-header">
        <a href="#" class="btn btn-link sidebar-mobile-toggle d-md-none mr-auto">
            <i class="fa fa-bars"></i>
        </a>

        <a class="navbar-brand" href="#">
            <font size="5">个人信息</font>
        </a>

        <a href="#" class="btn btn-link sidebar-toggle d-md-down-none">
            <i class="fa fa-bars"></i>
        </a>

        <ul class="navbar-nav ml-auto">
            <li class="nav-item d-md-down-none">
                             北京时间：
				<span id="hour"></span>时
				<span id="minute"></span>分
				<span id="second"></span>秒	
            </li>
			<li class="nav-item dropdown">
                <a href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                </a>
            </li>
            <li class="nav-item d-md-down-none">
                <a>
                   <span class="small ml-1 d-md-down-none">欢迎用户：${username}</span>
                </a>
            </li>
            <li class="nav-item dropdown">
                <a href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                </a>
            </li>
        </ul>
    </nav>

    <div class="main-container">
        <div class="sidebar">
            <nav class="sidebar-nav">
                <ul class="nav">
                    <li class="nav-title">读书系统</li>

                    <li class="nav-item">
                        <a href="<%=request.getContextPath()%>/Admin/toAdminIndex" class="nav-link active">
                            <i></i>首页
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="<%=request.getContextPath()%>/Admin/updateAdmin" class="nav-link active">
                            <i></i>个人信息
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="<%=request.getContextPath()%>/Course/toRelease" class="nav-link active">
                            <i></i>课程发布
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="<%=request.getContextPath()%>/Course/AdminCount" class="nav-link active">
                            <i></i>课程统计
                        </a>
                    </li>
                     <li class="nav-item">
                        <a href="<%=request.getContextPath()%>/Course/PrivilegeBinding" class="nav-link active">
                            <i></i>权限绑定
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="<%=request.getContextPath()%>/Common/logOut" class="nav-link active">
                            <i></i>退出系统
                        </a>
                    </li>                     
                </ul>
            </nav>
        </div>
        <div class="content">
            <div class="container-fluid">
                <div class="row ">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-header">
                                <font size="4">信息完善</font>
                                <span class="btn btn-default" onclick="submituserform()">
				                	<font color="#6CA6CD"> &nbsp;提交修改&nbsp;</font>
				               	</span>
				               	<a href="<%=request.getContextPath()%>/Admin/toAdminIndex">
                            		<font color="#6CA6CD"> &nbsp;返回&nbsp;</font>
                        		</a>
                            </div>
                            <div class="card-body p-0">      
                                <div class="justify-content-around mt-4 p-4 bg-light d-flex border-top d-md-down-none">
                                	<form action="<%=request.getContextPath()%>/Admin/Update" method="post" id="userform">
										<table align="center" class="table table-hover">
											<input name="id" value="${updateAdmin.id}" type="hidden">
  											<input name="role" value="${updateAdmin.role}" type="hidden">
											<tr>
												<td>用户名:</td>
												<td><input type="text" name="username" class="form-control" id="username3" value="${updateAdmin.username}"></td>
												<td><font color="red"><span id="tip"></span></font></td>
												<td><input type="hidden" value="" class="form-control" id="tip2"></td>
											</tr>
											<tr>
												<td>密码:</td>
												<td><input type="text" name="password" class="form-control" id="password3" value="${updateAdmin.password}"></td>
												<td><font color="red"><span id="tip1"></span></font></td>
											</tr>
											<tr>
												<td>手机号:</td>
												<td><input type="text" name="phone" class="form-control" id="phone3" value="${updateAdmin.phone}" onkeyup="value=value.replace(/[^\d]/g,'')"></td>
												<td><input type="hidden" value="" id="tip7"></td>
												<td><input type="hidden" value="" id="tip8"></td>
												<td><font color="#FF0000">     
														<span class="tip mobile_hint" id="tip6" style="width:150px"></span>
													</font>
												</td>
											</tr>
											<tr>
												<td>真实姓名:</td>
												<td><input type="text" name="realname" class="form-control" id="realname3" value="${updateAdmin.realname}"></td>
											</tr>
											<tr>
												<td>性别:</td>
												<td><input type="radio" name="sex" id="sex1" value="m" <c:if test="${updateAdmin.sex=='m'}">checked</c:if>> 男
												<input type="radio" name="sex" id="sex2" value="n" <c:if test="${updateAdmin.sex =='n'}">checked</c:if>> 女</td>
											</tr>
											<tr>
												<td>年龄:</td>
												<td><input type="text" name="age" class="form-control" id="age3" value="${updateAdmin.age}" onkeyup="value=value.replace(/[^\d]/g,'')"></td>
											</tr>
										</table>
									</form>
                                </div>
                            </div>
                        </div>
                    </div>                   
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
