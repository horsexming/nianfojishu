<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<html>  
    <head>  
        <title>验证码</title>  
        <style type="text/css">  
            #code  
            {  
                font-family:Arial;  
                font-style:italic;  
                font-weight:bold;  
                border:0;  
                letter-spacing:2px;  
                color:blue;  
            }  
        </style>  
        <script type = "text/javascript" src = "checkCode.js">  
        </script>  
    </head>  
    <body>  
        <div>  
            <input type = "text" id = "input"/>  
            <div id="code" onclick="createCode()">
            </div>
            <table>
            	<tr>
            		<td colspan="1"></td>
            	</tr>
            </table>
<%--            <input type = "button" id="code" onclick="createCode()"/>  --%>
            <input type = "button" value = "验证" onclick = "validate()"/>  
        </div>  
    </body>  
</html>  
<SCRIPT type="text/javascript">
var code ; //在全局定义验证码   
//产生验证码  
window.onload = function createCode(){  
     code = "";   
     var codeLength = 4;//验证码的长度  
     var checkCode = document.getElementById("code");   
     var random = new Array(0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R',  
     'S','T','U','V','W','X','Y','Z');//随机数  
     for(var i = 0; i < codeLength; i++) {//循环操作  
        var index = Math.floor(Math.random()*36);//取得随机数的索引（0~35）  
        code += random[index];//根据索引取得随机数加到code上  
    }  
    checkCode.innerHTML = code;//把code值赋给验证码  
}  
//校验验证码  
function validate(){  
    var inputCode = document.getElementById("input").value.toUpperCase(); //取得输入的验证码并转化为大写        
    if(inputCode.length <= 0) { //若输入的验证码长度为0  
        alert("请输入验证码！"); //则弹出请输入验证码  
    }         
    else if(inputCode != code ) { //若输入的验证码与产生的验证码不一致时  
        alert("验证码输入错误！@_@"); //则弹出验证码输入错误  
        createCode();//刷新验证码  
        document.getElementById("input").value = "";//清空文本框  
    }         
    else { //输入正确时  
        alert("^-^"); //弹出^-^  
    }             
}  
</SCRIPT>
