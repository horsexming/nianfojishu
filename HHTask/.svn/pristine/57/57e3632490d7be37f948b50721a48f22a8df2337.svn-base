<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Conditioning_show.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="System/dmltry/jquery-1.11.3.min.js"></script>
<%@include file="/util/sonHead.jsp"%>
  </head>
  
  <body>
  <table class="table">
  <tr>
  <td>id</td>
  <td>控制器IP</td>
   <td>所在区域</td>
   <td>绑定人员</td>
   <td>操作</td>
  </tr>
  

	<s:if test="listConditioning==null">
	<tr><td colspan="4">没有可查询的数据</td></tr>
	</s:if>
	<s:else>
  <s:iterator id="list" value="listConditioning" status="stauts">

  			<s:if test="#pageStatus.index%2==1">
				<tr align="center" bgcolor="#e6f3fb"
					onmouseover="chageBgcolor(this)"
					onmouseout="outBgcolor(this,'#e6f3fb')">
			</s:if>
			<s:else>
				<tr align="center" onmouseover="chageBgcolor(this)"
					style="height: 25px;" onmouseout="outBgcolor(this,'')">
			</s:else>
			

			

  <td>${list.id}</td>
  <td>${list.conditioningip}</td>
  <td>${list.region}</td>
  <td>${list.userid}</td>

   <td><input type="button" class="addmin" value="添加人员"/></td>
  </s:iterator>
  	</s:else>
  </table>
   
 <input type="button"  value="添加区域" class="addip"/>
  <script type="text/javascript">
  $(function(){
  
  $(".addmin").click(function(){
  var id= $(this).parent().prev().prev().prev().prev().text();
  //跳转条件查询
  window.location.href="ConditioningAction!conditioningDetail.action?id="+id;
  });
  $(".addip").click(function(){
    window.location.href="System/dmltry/Conditioning/Conditioning_addip.jsp";
  });

		  
  
  });
  </script>
  </body>
</html>
