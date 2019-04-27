<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'tangtang.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style>
.box{
    width:50%; margin-top:10%; margin:auto; padding:28px;
    height:350px; border:1px #111 solid;
    display:none;            /* 默认对话框隐藏 */
}
 
.box .x{ font-size:18px; text-align:right; display:block;}
.box input{width:80%; font-size:18px; margin-top:18px;}
</style>
  </head>
 

  <body>

    <h2>测试</h2>
    <input type="button" onClick="msgbox(1)" value="点击弹出输入框">

    <script>  
        function msgbox(n){
            document.getElementById('inputbox').style.display=n?'block':'none';     /* 点击按钮打开/关闭 对话框 */
        }
     </script>  
     
     <div id='inputbox' class="box">
        <a class='x' href=''; onclick="msgbox(0); return false;">关闭</a>
        <input type="text">
        <input type="text">
        <input type="button" value="确定">
     </div>
  </body>
</html>
