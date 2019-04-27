<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>提交工艺规程</title>
    
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
    <div align="center">
    <input type="button" value="进入SOP" class="input" onclick="createBom('试制')">
    <input type="button" value="进入LP" class="input" onclick="createBom('批产')">
    <script type="text/javascript" src="javascript/jquery-easyui-1.3.1/jquery-1.8.0.min.js"></script>
    </div>
  </body>
  <script type="text/javascript">
   function createBom(productStyle){
	   var rootId="${param.rootId}";
	  // alert(productStyle+"####"+rootId);
	   $.ajax( {
		type : "post",
		url : "gongyiGuichengAction!createSopOrLpBom.action",
		dataType : "json",
		data : {
		   productStyle : productStyle,
		   rootId : rootId
		},
		success : function(data) {
			alert(data.message);
		}
	});

   }
  </script>
</html>
