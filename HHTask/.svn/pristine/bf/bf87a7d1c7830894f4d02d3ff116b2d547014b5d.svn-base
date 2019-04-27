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
    
    <title>My JSP 'Delivery.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@include file="/util/sonHead.jsp"%>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="System/YcB2B/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="System/YcB2B/songhuo/Deliveery.js"></script>

  </head>
  
  <body>
  <%@include file="/util/sonTop.jsp"%>
  <div style="width: 100%;height: 100%;background-color:#F1F1F1;">
  <!-- 头 -->
  <div align="center" style="background-color:#F1F1F1;width: 90%;height: 10%;margin-left: 70px;">
		  <div style="float: left; border:1px solid #000; margin-left: 40%; color: #FFFFFF; height: 40px;margin-top: 10px;"  class="choseone"><p style="font-size: 25px;font-weight: bold;margin-top: 11px;" align="center" >选择DN行</p></div>
		  <div style="float: left; border:1px solid #000; color: #FFFFFF;height: 40px;margin-top: 10px;" class="chosetwo" ><p style="font-size:   25px;font-weight: bold;margin-top: 11px;" align="center">填写ASN信息</p></div>
		  <div style="float: left; border:1px solid #000; color: #FFFFFF;height: 40px;margin-top: 10px;" class="chosethree"><p style="font-size: 25px;font-weight: bold;margin-top: 11px;" align="center">确认ASN信息</p></div>
  </div>
	<div style="margin-left:70px;">
  <iframe src=""    id="ifname"  style="height:80%; width: 94.5%;background-color:  #FFFFFF;"></iframe>
  </div>
  </div>
  
  <script type="text/javascript">
  function prevchosedn(){
   $('#ifname').attr("src","System/YcB2B/songhuo/tableDelive.jsp");
    $(".choseone").css({"background-color":"#1890FF"});
  	$(".chosetwo").css({"background-color":"#F1F1F1"});
  	$(".chosethree").css({"background-color":"#F1F1F1"});
  }
  
  
  </script>
  
  </body>
</html>
