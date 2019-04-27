<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.task.entity.Users"%>
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
			href="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/css/mobiscroll.core-2.5.2.css">
		<script src="${pageContext.request.contextPath}/javascript/jquery/jquery-3.2.1.js"> </script> 
 		<script type="text/javascript"
 			src="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/js/bootstrap.js"> </script> 
<!--  		<script type="text/javascript" -->
<%--  			src="${pageContext.request.contextPath}/js/mobiscroll.core-2.5.2.js"> </script>  --%>
		<script type="text/javascript">
			// 现在window.$和window.jQuery是3.2.1版本:
			console.log($().jquery); // => '3.2.1'
			var $jq = jQuery.noConflict(true);
			// 现在window.$和window.jQuery被恢复成1.5版本:
			console.log($().jquery); // => '1.5.0'
		
		</script>
	</head>
	<body bgcolor="#ffffff">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<s:if test="pageStatus=='importProduction'">
						<h2>导入装配产品下线</h2>
						<p>模板<a href="${pageContext.request.contextPath}/upload/peb/production.xls">下载</a></p>
					</s:if>
					<s:elseif test="pageStatus=='importBanjinProduction'">
						<h2>导入钣金产品下线</h2>
						<p>模板<a href="${pageContext.request.contextPath}/upload/peb/bjProduction.xls">下载</a></p>
					</s:elseif>
					<s:elseif test="pageStatus=='importpebUsers'">
						<h2>导入人事档案信息</h2>
						<p>模板<a href="${pageContext.request.contextPath}/upload/peb/pebusers.xls">下载</a></p>
					</s:elseif>
				</div>
			</div>
			<form method="post" id="fromFile">
				<div class="row">
					<div class="col-lg-12 text-center" style="text-align:center">
						<br>
						<input type="file" name="attachment" id="file" style="text-align:center;display:inline">
						<input type="hidden" name="pageStatus" value="${pageStatus}">
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12 text-center" align="center">
						<br>
						<input type="button" value="提交" class="input" onclick="submitFile()">
					</div>
				</div>
			</form>
		</div>
	</body>	
	<script type="text/javascript">
	
	
	function submitFile(){
		var form = new FormData(document.getElementById("fromFile"));
	    var file = $jq("#file").val();
	    if(file!=null){
	    	var pageStatus = $("input[name='pageStatus']").val();
	    	var url = null;
	    	
	    	if(pageStatus!=null && (pageStatus=="importBanZuJieGou" || pageStatus=="importBanZuprincipals") ){
	    		url="${pageContext.request.contextPath}/productEBAction!importPdata.action";
	    	}else if(pageStatus!=null){
	    		url="${pageContext.request.contextPath}/productEBAction!importOtherData.action";
	    	}else{
	    		url="${pageContext.request.contextPath}/productEBAction!importPdata.action";
	    	}
	    	$jq.ajax({
	              url:url,
	              type:"post",
	              data:form,
	              processData:false,
	              contentType:false,
	              async : false,
	              dataType:"json",
	              success:function(data){
	            	  if(data!=""){
			              	alert(data);
	            	  }else{
	            		  alert("导入成功");
	            	  }
	              },
	              error:function(e){
	                  alert("错误！！");
	              }
	          });
	    }
	}
	
	
	</script>
</html>
