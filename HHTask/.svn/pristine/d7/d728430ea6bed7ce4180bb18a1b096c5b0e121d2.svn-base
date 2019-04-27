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
    
    <title>My JSP 'selall.jsp' starting page</title>
    
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
  
  
  	<form action="DmltryAppFilesAction!fenyeandtj.action" method="post">
		<p align="center">
			<input type="text"  placeholder="请输入名称" name="dmltryAppFiles.appFilename" /><input
				type="submit" value="查询" />
	</form>
	
  <table class="table">
		<tr bgcolor="#c0dcf2" height="50px">
				<th>序号</th>
				<th>名称</th>
				<th>描述</th>
				<th colspan="2">操作</th>
		</tr>
		
		<s:iterator id="liste" value="dmltryAppFileslist" status="stauts">
			<s:if test="#pageStatus.index%2==1">
				<tr align="center" bgcolor="#e6f3fb"
					onmouseover="chageBgcolor(this)"
					onmouseout="outBgcolor(this,'#e6f3fb')">
			</s:if>
			<s:else>
				<tr align="center" onmouseover="chageBgcolor(this)"
					style="height: 25px;" onmouseout="outBgcolor(this,'')">
			</s:else>
			

				<td><s:property value="#stauts.index+1" /></td>
				<td style="display: none;">${liste.id}</td>
				<td>${liste.appFilename}</td>
				<td>${liste.appFilesmshu}</td>
				<td>
				<a href="javascript:void(0)" class="update">修改</a>/ 
				<a href="javascript:void(0)" class="delete">删除</a>
				<a href="javascript:void(0)" class="selct">查看明细</a>
				<a href="javascript:void(0)" class="add">添加明细</a>
				</td>
				
		</s:iterator>
		<tr >
			<s:if test="errorMessage==null">
				<td colspan="12" align="right">第 <font color="red"><s:property
							value="cpage" /> </font> / <s:property value="total" /> 页 <fenye:pages
						cpage="%{cpage}" total="%{total}" url="%{url}" styleClass="page"
						theme="number" />
			</s:if>
			<s:else>
			
				<td colspan="12" align="center" style="color: red">
					${errorMessage}</td>
			</s:else>
		</tr>
	</table>
	

	<script type="text/javascript">
	//修改
	$(function(){
	$(".update").click(function(){
	var	id=$(this).parent().prev().prev().prev().text();
	window.location.href="DmltryAppFilesAction!selidDmltryAppFiles.action?dmltryAppFiles.id="+id;
	});
	
	//查看明细
	$(".selct").click(function(){
	var	id=$(this).parent().prev().prev().prev().text();
	window.location.href="DmlAppFileUrlAction!selct.action?dmltryAppFiles.id="+id;
	});
	
	//添加明细
	$(".add").click(function(){
	var	id=$(this).parent().prev().prev().prev().text();
	window.location.href="DmltryAppFilesAction!toAddDmlAppFileUrl.action?dmltryAppFiles.id="+id;
	});
	
	
	
	//删除
	$(".delete").click(function(){
	var	id=$(this).parent().prev().prev().prev().text();
	window.location.href="DmltryAppFilesAction!delteDmltryAppFiles.action?dmltryAppFiles.id="+id;
	});
	});
	</script>
  
  </body>
</html>
