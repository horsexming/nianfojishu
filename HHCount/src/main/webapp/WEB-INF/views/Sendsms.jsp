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
<script type="text/javascript">
	/* var countdown=60; 
	function settime(obj) { 
	    if (countdown == 0) { 
	        obj.removeAttribute("disabled");
	        obj.value="免费获取验证码"; 
	        countdown = 60; 
	        return;
	    } else { 
	        obj.setAttribute("disabled", true); 
	        obj.value="重新发送(" + countdown + ")"; 
	        countdown--; 
	    } 
		setTimeout(function() {settime(obj)},1000)
	} */	
	var AddInterValObj; //timer变量，控制时间
	var adcount = 60; //间隔函数，1秒执行
	var addCount;//当前剩余秒数
	function sendAddmes() {
		var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 				
		if(!myreg.test($("#phone3").val())){ 
			alert('请输入有效的手机号码')
		    return false;
		}else{
			var phone =  document.getElementById("phone3").value;
			//向后台发送处理数据
			 $.ajax({
				type: "POST", //用POST方式传输
				url: "<%=request.getContextPath()%>/User/SelUserByP3",
				data: "phone=" + phone,		
				success : function(data) {
					if(data >= 0){
						document.getElementById("tip8").value='';
						alert("短信发送成功!");
						var otxt=document.getElementById("password3");		
						otxt.readOnly=false;
						$("#tip9").html("");
						addCount = adcount;
						//设置button效果，开始计时
						$("#addSendCode").attr("disabled", "true");
						$("#addSendCode").val("" + addCount + "秒后重新获取").css({"background-color":"#D1D4D3"});
						AddInterValObj = window.setInterval(SetAddnTime, 1000); //启动计时器，1秒执行一次
					} else if (data == -1) {
						document.getElementById("tip8").value='发送失败!';
						$("#tip9").html("发送失败!");
						alert("短信帐号余额不足，请通知管理员！");
					} else if (data==-2) {
						document.getElementById("tip8").value='发送失败!';
						$("#tip9").html("发送失败!");
						alert("短信帐号ID错误，请通知管理员!");
					} else if (data==-3) {
						document.getElementById("tip8").value='发送失败!';
						$("#tip9").html("发送失败!");
						alert("短信帐号密码错误，请通知管理员!");
					}else if (data==-4) {
						document.getElementById("tip8").value='发送失败!';
						$("#tip9").html("发送失败!");
						alert("参数不够或参数内容的类型错误，请通知管理员!");
					}else if (data==-12) {
						document.getElementById("tip8").value='发送失败!';
						$("#tip9").html("发送失败!");
						alert("发送短信失败，请重新尝试，如果多次失败，请联系管理员!");
					}else if (data==-13) {
						document.getElementById("tip8").value='发送失败!';
						$("#tip9").html("发送失败!");
						alert("网络连接失败!");
					}else{	
						document.getElementById("tip8").value='发送失败!';
						$("#tip9").html("发送失败!");
						alert("验证码发送太频繁，请稍后再试!");
					}
				}
				 
			});
			
			
		}
		
		/* else if(document.getElementById("tip8").value.trim()!=""){
			var tip8= document.getElementById("tip8").value;
			alert(tip8);
			return false;  	
		}else{ */
			/* addCount = adcount;
			//设置button效果，开始计时
			$("#addSendCode").attr("disabled", "true");
			$("#addSendCode").val("" + addCount + "秒后重新获取").css({"background-color":"#D1D4D3"});
			AddInterValObj = window.setInterval(SetAddnTime, 1000); //启动计时器，1秒执行一次 */
		/* }	 */
			
	}
	
	//timer处理函数
	function SetAddnTime() {
		if (addCount == 0) {                
			window.clearInterval(AddInterValObj);//停止计时器
			$("#addSendCode").removeAttr("disabled");//启用按钮
			$("#addSendCode").val("重新获取验证码").css({"background-color":"#0097a8"});
		}
		else {
			addCount--;
			$("#addSendCode").val("" + addCount + "秒后重新获取").css({"background-color":"#D1D4D3"});
		}
	}
	
	
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
					url : "<%=request.getContextPath()%>/User/SelUserByP2",
					data : "phone=" + phone,
					success : function(data) {
						if(data=="0"){
							$("#tip6").html("手机号不存在!");
							document.getElementById("tip7").value='手机号不存在!';
						}else{
							document.getElementById("tip7").value='';
							$("#tip6").html("");
						}
					}
				});
			}
		});
		$("#password3").blur(function() {
			var password= document.getElementById("password3");
			if(password.value.trim()==""){
				$("#tip1").html("验证码不能为空!");		
			}else{
				$("#tip1").html("");
			}
		});	
	});
	function beforeSubmit(form){
		var tip7= document.getElementById("tip7");
		var tip8= document.getElementById("tip8");
		var password= document.getElementById("password3");
		if(tip7.value.trim()!=""){
			alert("手机号不符合要求");
			return false;
		}else if(password.value.trim()==""){
			alert("验证码不能为空");
			return false;
		}else if(tip8.value.trim()!=""){
			alert("验证码无效");
			return false;
		}
		document.getElementById("userform").submit();
	}
	
	function load(){
		$('#password3').attr("readonly", "readonly");
	}
	
	window.onload=function(){		  
		var otxt=document.getElementById("password3");		
		otxt.readOnly=true;
	}
	
</script>
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

<body >
	<div class="top">读万卷书&nbsp;·&nbsp;用知识改变未来</div>
		<div class="content">
			<div class="login">
				<div class="title">读书系统登录</div>
				<p align="left"><font size="4" color="red">${LoginError}</font></p>
				<font color="#FF0000">     
					<span class="tip mobile_hint" id="tip9" style="width:150px"></span>
				</font>
				<form action="<%=request.getContextPath() %>/Common/LoginMsM" method="post" onSubmit="return beforeSubmit(this);" id="userform">
				<div class="line">
					<img class="smallImg" src="<%=request.getContextPath() %>/static/login/img/q.png" />
					<input placeholder="请输入手机号" type="text" name="username" id="phone3" onkeyup="value=value.replace(/[^\d]/g,'')"/>
					<input type="hidden" value="" id="tip7">
					<input type="hidden" value="" id="tip8">
					<font color="#FF0000">     
						<span class="tip mobile_hint" id="tip6" style="width:150px"></span>
					</font>
				</div>
				<div class="line">
					<img class="smallImg" src="<%=request.getContextPath() %>/static/login/img/x.png" />
					<input align="left" placeholder="验证码" type="text" name="password" style="width:80px" id="password3"/>
<!-- 					<input class="btn btn-default btn-sm" type="button" id="btn" value="免费获取验证码" onclick="settime(this)" style="width: 120px"/>
 -->					<input type="button" class="btn btn-default btn-sm" id="addSendCode" value="获取验证码" onClick="sendAddmes()" style="width: 120px"/>
					<font color="#FF0000">
						<span class="tip mobile_hint" id="tip1" style="width:150px"></span>
					</font>
				</div>
				<font>账号类型：</font>				
				<label class="checkbox-inline">
  					<input type="radio" id="inlineCheckbox2" value="User" name="role" checked="checked"> 普通用户
				</label>
				<label class="checkbox-inline">
  					<input type="radio" id="inlineCheckbox1" value="Admin" name="role"> 管理员
				</label>
				<input class="logBut" type="button" value="登&nbsp;&nbsp;录" onclick="beforeSubmit(this)"><br>
				</form>
				<p align="left"><font size="2">没有账号？</font></p>
				<p align="left">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<font size="2"><a  href="<%=request.getContextPath()%>/Common/SelectRegister">注册</a></font>				
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<font size="2"><a href="<%=request.getContextPath()%>/Common/login">用户名密码登录</a></font>	
				</p>	
			</div>
		</div>
</body>
</html>
</html>