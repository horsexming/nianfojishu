<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>扫码支付界面</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<%@include file="/util/sonHead.jsp"%>
        <script type="text/javascript" src="<%=basePath%>/javascript/jquery.qrcode.min.js"></script>
	</head>
	 <style type="text/css">
	 .wepayCode{
	 margin-left: 40px;
	 margin-top: 60px;
	 }
	 .ztys{
	 font-size: 26px;
	 }
	 </style>
  <body>
     <input type="hidden" id="urlCode" value="${urlCode}"/>
     <h1 class="ztys">扫码支付：</h1>
     <div class="wepayCode">
										
	 </div>
  </body>
  <script type="text/javascript">
  $(function(){
      //加载支付二维码
      var leaveRQCode = $("#urlCode").val();
      alert(leaveRQCode);
		$(".wepayCode").qrcode({
            width: 180,
            height: 180,
            render: "canvas", //设置渲染方式 table canvas
            text: leaveRQCode,
            background: "#ffffff", //背景颜色 
            foreground: "#000000"  //前景颜色 
        });
  });
  </script>
</html>
