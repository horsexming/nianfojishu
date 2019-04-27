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
	<title></title>
		<%@include file="/util/sonHead.jsp"%>
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<!-- 		<meta http-equiv="Access-Control-Allow-Origin" content="*"> -->
<%-- 		<script src="${pageContext.request.contextPath}/javascript/jquery/jquery-3.2.1.js"> </script>  --%>
<%-- 		<script type="text/javascript" --%>
<%--  			src="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/js/bootstrap.js"> </script>  --%>
<%-- 		<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/DatePicker/WdatePicker.js"> --%>
<%-- 		</script> --%>
		<style type="text/css">
			.form-control{
				width: 180px;
			}
		</style>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<div class="container">
					<h1 class="text-center">添加屏幕信息</h1>
					<div class="row">
						<s:if test="screen!=null&&screen.id!=null">
							<form action="${pageContext.request.contextPath}/inDoorScreenAction!addScreen.action" method="post" id="submitForm">
							<input type="hidden" name="screen.id" value="${screen.id }" id="screenId">
						</s:if>
						<s:else>
							<form action="${pageContext.request.contextPath}/inDoorScreenAction!addScreen.action" method="post" id="submitForm">
						</s:else>
							<table class="table table-responsive">
								<tr>
									<th class="text-right">屏幕名称：</th>
									<td>
										<input type="text" name="screen.name" value="${screen.name }" id="screenName">
									</td>
								</tr>
								<tr>
									<th class="text-right">屏幕位置：</th>
									<td>
										<input type="text" name="screen.position" value="${screen.position}" id="position">
									</td>
								</tr>
								<tr>
									<td colspan="2"  class="text-center">
										<input type="button" class="btn btn-default" value="提交" id="submitBtn" onclick="submitFunction()">
									</td>
								</tr>
							</table>
						</form>
					</div>
				
				</div>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript" >
	
		function submitFunction(){
			$("#submitBtn").attr("disabled",true);
			
			var screenName = $("#screenName").val();
			if(screenName==null || screenName==""){
				alert("请输入屏幕名称");
				return false;
			}
			
			var position = $("#position").val();
			if(position==null || position==""){
				alert("请输入屏幕位置");
				return false;
			}
			
			$("#submitBtn").attr("disabled",false);
			$("#submitForm").submit();
		}
	</script>
	</body>
</html>