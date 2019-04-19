<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
<link type="image/png" href="http://tm-image.tianyancha.com/tm/b928c552e7d861b16141d5d40e950749.jpg" rel="shortcut icon">
<link rel="stylesheet" href="<%=request.getContextPath() %>/static/login/css/login.css" />
<script  src="<%=request.getContextPath()%>/static/js/jquery-3.3.1.min.js"></script>
<link href="<%=request.getContextPath()%>/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"rel="stylesheet" />
<script src="<%=request.getContextPath()%>/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

</head>

<style type="text/css">
body {
	background-image: url(../static/login/img/bg2.jpg);
	text-align: center;
	background-size: 100% 100%;
	height: 100%;
	overflow: hidden;
	background-repeat: no-repeat;
	background-position: center;
	background-attachment: fixed;
}
</style>

<body>
	<div class="top">读万卷书&nbsp;·&nbsp;用知识改变未来</div>
		<div class="content">
			<div class="login">
				<div class="title">读书系统登录</div>
				<p align="left"><font size="4" color="red">${LoginError}</font></p>
				<form action="<%=request.getContextPath() %>/Common/Login" method="post">
				<div class="line">
					<img class="smallImg" src="<%=request.getContextPath() %>/static/login/img/icon1.png" />
					<input placeholder="请输入账号" type="text" name="username" />
				</div>
				<div class="line">
					<img class="smallImg" src="<%=request.getContextPath() %>/static/login/img/icon2.png" />
					<input placeholder="请输入密码" type="password" name="password"/>
				</div>
				<font>账号类型：</font>				
				<label class="checkbox-inline">
  					<input type="radio" id="inlineCheckbox2" value="User" name="role" checked="checked"> 普通用户
				</label>
				<label class="checkbox-inline">
  					<input type="radio" id="inlineCheckbox1" value="Admin" name="role"> 管理员
				</label>
				<input class="logBut" type="submit" value="登&nbsp;&nbsp;录"><br>
				</form>
				<p align="left"><font size="2">没有账号？</font></p>
				<p align="right"><font size="2"><a href="<%=request.getContextPath()%>/Common/SelectRegister">注册</a></font></p>	
			</div>
		</div>
</body>
</html>