<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ycsel.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
	
	.biati{
	margin: 0px 60px;
	}


	/* 让导航条文字显示在一行*/
	.biati li{
			float: left; 
			list-style: none;
	     	 padding: 13px 20px;
	     	 margin：0px;
           	 display: inline-block;
            white-space:nowrap;             
	}

	/*文字后背景颜色更改*/
	.biati li:hover{
    		background:#4169E1;
    		cursor: pointer;
	}
	
	/*文字颜色点击事件*/
		.biati li a:hover{
    		background:#4169E1;
    		cursor: pointer;
	}
	#dht{
	border-width:0px;
	margin-left:0px;
	margin-top:0px;
	width:1385px;
	height:48p;
	border: 0px solid #dfdfdf;
    font-family: Tahoma, Arial, "微软雅黑", sans-serif;
    color: 	#D3D3D3;	
	}
	
	.beijing{
	 width: 100%;
    height: 48px;
    background: #3c75bb;
    font-family: "Microsoft Yahei", Arial;
    position: relative;
    position:relative;
    
	}
	
	</style>
  </head>
  
  <body>
  <!-- 导航条  -->
    <div>
    

    
    <div class="beijing">
    
    <a></a>
    
   <div id="dht">
   
   <ul class="biati">
   <li><a>首页</a></li>
   <li><a>商品维护</a></li>
   <li><a>预测</a></li>
   <li><a>供应能力</a></li>
   <li><a>仓库管理</a></li>
   <li><a>PO</a></li>
   <li><a>合同管理</a></li>
   
   </ul>
   </div>
    
    
    
   
    </div>
    
    </div>
  </body>
</html>
