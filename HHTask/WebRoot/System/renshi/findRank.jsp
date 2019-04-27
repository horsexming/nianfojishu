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
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/css/bootstrap.css">
		<style type="text/css">
			caption{
				text-align: center;
			}
			body{
				margin:5px;
/* 				padding: 5px; */
			}
			.row{
				margin:0;
			}
		</style>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div class="contaniner">
			<div class="row col-xs-12">
				<h2 align="center text-center">职级列表</h2>
			</div>
			<table class="table">
				<tr>
					<th colspan="5" class="text-center ">
						<input type="button" value="前往添加" class="input text-center" 
							onclick="location.href='${pageContext.request.contextPath}/System/renshi/addRank.jsp'"> 
					</th>
				</tr>
				<tr>
					<th>序号</th>
					<th>编码</th>
					<th>名称</th>
					<th>操作</th>
				</tr>
				<s:iterator value="rankList" id="rl" status="ps">
					<tr>
						<td>${ps.index }</td>
						<td>${rl.code }</td>
						<td>${rl.name }</td>
						<td>
							<input type="button" value="删除" onclick="toDelete(${rl.id})">
							
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
		
			function toDelete(id){
				$.ajax({
					type:"post",
					url:"${pageContext.request.contextPath}/UsersAction!deleteRank.action?id="+id,
					dataType:"json",
					success:function(data){
						alert(data);
						window.location.reload();
						
					},error:function(){
						alert("系统异常");
					}
				})
			}
		</script>
	</body>
</html>
