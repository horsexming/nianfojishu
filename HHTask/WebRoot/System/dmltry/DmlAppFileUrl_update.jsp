<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'DmlAppFileUrl_showwid.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  
  
  <form action="DmlAppFileUrlAction!updateDmlAppFileUrl.action" method="post">
  <table>
  <tr>
  <td>id</td>
  <td><input type="text" value="${dmlAppFileUr1.id}" name="dmlAppFileUrl.id" /></td>
  </tr>
  
   <tr>
   <td>附件名称</td>
   <td><input type="text" value="${dmlAppFileUr1.appfileurlfj}" name="dmlAppFileUrl.appfileurlfj" /></td>
   </tr>
    <tr>
   <td>外键id</td>
   <td><input type="text" value="${dmlAppFileUr1.dmltryAppFiles.id}"  name="dmlAppFileUrl.dmltryAppFiles.id" /></td>
   </tr>
   <tr>
   <td><input type="submit" value="修改"/></td>
    <td><input type="reset" value="重置"/></td>
   </tr>
  </table>
	</form>  
  </body>
</html>
