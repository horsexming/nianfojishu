<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Conditioning_addip.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<%@include file="/util/sonHead.jsp"%>
  </head>
  
  <body>
  <%@include file="/util/sonTop.jsp"%>
	<div id="gongneng" style="width: 100%; margin-top: 10px;">
		<div id="xitong" style="width: 100%; font-weight: bold; height: 50px;"
			align="left">
			<div
				style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
				align="left"></div>
			<div style="float: left; width: 45%; padding-top: 5px;" align="right">
				<a href="" style="color: rgb(79, 77, 77)"><br /> </a>
			</div>
		</div>
		
		
  <h2 align="center">添加空调ip</h2>
  <form action="ConditioningAction!addConditioning.action" method="post">
  <table class="table">
  <tr><td align="left">输入控制遥控器ip地址</td><td align="left"><input type="text"  name="conditioning.conditioningip" placeholder="列:D" /></td></tr>
  <tr><td align="left">所在区域</td><td align="left"><input type="text"  name="conditioning.region" placeholder="列:103`办公室`大厅" /></td></tr>
  <tr><td><input type="submit" value="提交"></td><td><input type="reset" value="重置"></td></tr>
 </table> 
  </form>
  <%@include file="/util/foot.jsp"%>
  </body>
</html>
