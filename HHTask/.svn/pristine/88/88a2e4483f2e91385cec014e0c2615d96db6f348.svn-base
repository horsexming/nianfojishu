<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'DmlAppFileUrl_show.jsp' starting page</title>

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
	<form action="DmlAppFileUrlAction!fenyeandtj.action" method="post">
		<p align="center">
			<input type="text"  placeholder="请输入附件名称" name="dmlAppFileUrl.appfileurlfj" /><input
				type="submit" value="查询" />
	</form>
	<table class="table">
		<tr bgcolor="#c0dcf2" height="50px">
			<th >序号</td>
			<th >版本号</td>
			<th >附件名称</td>
			<th >外键id</td>
			<th colspan="2">操作</td>
		</tr>
		<s:iterator id="listurl" value="dmlAppFileUrllist" status="stauts">
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

			
			<td>${listurl.coide}</td>
			<td style="display: none;">${listurl.id}</td>
			<td>${listurl.appfileurlfj}</td>
				<td>${listurl.dmltryAppFiles.id}</td>
				<td>
				<a href="javascript:void(0)" class="update">修改</a>/ 
				<a href="javascript:void(0)" class="delete">删除</a>
				<a href="javascript:void(0)" class="addmx">添加</a>
				<a href="<%=path%>/System/dmltry/file/${listurl.appfileurlfj}">查看附件</a>
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




	<p>
		<input type="button" value="增加" class="add" />
	</p>



	</a>


	<script type="text/javascript">
	
	
		//修改
		$(function() {
			$(".update").click(function() {
				var id = $(this).parent().prev().prev().prev().text();
				window.location.href = "DmlAppFileUrlAction!selidDmlAppFileUrl.action?dmlAppFileUrl.id=" + id;
			});
	
	
			$(".add").click(function() {
			var id = $(this).parent().prev().prev().prev().text();
			window.location.href = "System/dmltry/DmlAppFileUrl_add.jsp";
			});
			
			
			$(".addmx").click(function() {
			var id = $(this).parent().prev().prev().prev().text();
			alert(id);
			window.location.href = "DmlAppFileUrlAction!addmx.action?dmlAppFileUrl.id="+id;
			});
			
			
			
			
			//删除
			$(".delete").click(function() {
				var id = $(this).parent().prev().prev().prev().text();
				window.location.href = "DmlAppFileUrlAction!delteDmlAppFileUrl.action?dmlAppFileUrl.id=" + id;
			});
	
	
		});
	</script>


</body>
</html>
