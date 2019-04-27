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
<%-- 		<%@include file="/util/sonHead.jsp"%> --%>
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/css/bootstrap.min.css">
		<script src="${pageContext.request.contextPath}/javascript/jquery/jquery-3.2.1.js"> </script> 
<%-- 		<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/gantt/jquery.js"></script> --%>
		<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/gantt/jquery.ui.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/gantt/DateSlider.js"></script>
		<script type="text/javascript">
			//现在window.$和window.jQuery是3.2.1版本:
// 			console.log($().jquery); // => '3.2.1'
// 			var $jq = jQuery.noConflict(true);
			//现在window.$和window.jQuery被恢复成1.5版本:
// 			console.log($().jquery); // => '1.5.0'
		</script>
		<style type="text/css">
		</style>
	</head>
	<body bgcolor="#ffffff">
		<div class="container">
			<div class="row clearfix">
				<h2 align="center">
					班组列表
				</h2>
				<s:iterator value="showList" id="banzu" status="ps">
					<div class="form-group col-lg-6">
						<form action="${pageContext.request.contextPath}/productEBAction!showPebCheJian.action"
								 method="post" id="form${ps.index}" target="_blank">
							<input type="hidden" value="${banzu}" name="banzu">
	                    	<div class="input-group jumbotron" 
	 	                   			onclick="onclickSubmit(${ps.index})">
		                        <span class="input-group-addon">${banzu}</span>
		                    </div>
	                    </form>
	                </div>
				</s:iterator>
			</div>
		</div>
	</body>	
	<script type="text/javascript">
	function onclickSubmit(index){
		$("#form"+index).submit();
	}
	</script>
</html>