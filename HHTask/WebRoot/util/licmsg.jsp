<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>证书信息</title>
    
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
  <s:if test="timeFlag=='0'.toString()">
  <div align="center">您还没有注册！</div>
  </s:if>
  <s:elseif test="timeFlag=='1'.toString()">
    <div align="center">公司名称：${licenseMsg.companyName}</div>
    <div align="center">证书有效期:${licenseMsg.notAfter}</div>
     <div align="center">（您还没有上传证书请上传）</div>
  </s:elseif>
  <s:else>
  <div align="center">公司名称：${licenseMsg.companyName}</div>
    <div align="center">证书有效期:${licenseMsg.notAfter}</div>
     <div align="center">（您的证书已过期！请重新购买）</div>
  </s:else>
  </body>
</html>
