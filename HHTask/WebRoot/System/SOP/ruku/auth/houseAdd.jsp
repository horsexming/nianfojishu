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
		<%@include file="/util/sonHead.jsp"%>
		<SCRIPT type="text/javascript">
			$(function(){
				$('#mybutton').bind('click',function(){
					$.ajax({
						type: "POST",
						url: "WareHouse_add.action",
						data: $('#myform').serialize(),
						dataType:"json",
						success: function(msg){
							alert(msg.message);
							location.href= "WareHouse_list.action";
						}
					});
				});
			})
		
		</SCRIPT>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">
					
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<form id="myform" ">
					<table class="table" style="width: 40%">
						<tr>
							<th colspan="2">添加仓库</th>
						</tr>
						<tr>
							<th>仓库名</th>
							<td><input name="house.name"/></td>
						</tr>
						<tr>
							<th>仓库编号</th>
							<td><input name="house.code"/></td>
						</tr>
						<tr>
							<td colspan="2" align="center"><input id="mybutton" value="提交" type="button"/></td>
						</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
