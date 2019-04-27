<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/css/bootstrap.min.css">
		<script src="${pageContext.request.contextPath}/javascript/jquery/jquery-3.2.1.js"> </script> 
		<script type="text/javascript"
 			src="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/js/bootstrap.js"> </script> 
 		<script type="text/javascript"
			src="<%=basePath%>/javascript/DatePicker/WdatePicker.js">
		</script>
		<style type="text/css">
			#contentDiv{
				overflow: visible;
			}
			i{
				color: red;
			}
		</style>
	</head>
	<body bgcolor="#ffffff">
		<div class="container">
			<div class="row" style="height: 20px;">
            	
            </div>
			<div class="row clearfix">
				<h1 align="center">
					来访明细
				</h1>
            </div>
            <div class="row" style="height: 20px;">
            </div>
            <div class="row">
            	<table class="table table-bordered table-striped">
            		<tr>
						<th>来访人</th>
            			<th>来访人公司</th>
						<th>来访人手机号</th>
						<th>来访时间</th>
						<th>申请时间</th>
						<th>被访人</th>
						<th>来访缘由</th>
						<th>进入时间</th>
						<th>离开时间</th>
						<th>来访状态</th>
            			
            		</tr>
            		<s:iterator value="visitorList" id="visitor" status="ps">
						<tr>
							<td>${visitor.visitorName }</td>
	            			<td>${visitor.visitorComp }</td>
							<td>${visitor.visitorPhone }</td>
							<td>${visitor.dateTime}</td>
							<td>${visitor.addTime }</td>
							<td>${visitor.interviewee }</td>
							<td>${visitor.visitorCause}</td>
							<td>${visitor.inTime }</td>
							<td>${visitor.outTime }</td>
							<td>${visitor.visitorStatus }</td>
						</tr>            			
            		</s:iterator>
            	</table>
            	<div class="clearfix"></div>
            </div>
            <s:if test="pageStatus!=null && pageStatus=='longVisitor'">
	            	<form method="post" action="${pageContext.request.contextPath}/visitorAction!addLongVisitorApply.action" id="submitForm">
            <div class="row">
            	<table class="table table-bordered table-striped">
            		<tr>
            			<th class="text-right col-md-6">长访截至时间</th>
            			<td >
            				<input type=hidden value="${visitor.id }" name="visitor.id">
            				<input type="text" name="visitor.visitorLongDateTime" id="endTime" class="Wdate" 
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})">
            			</td>
            		</tr>
            		<tr>
            			<td colspan="2" class="text-center">
		          			<input type="button" class="btn btn-default" value="提交" onclick="check()">
            			</td>
            		</tr>
            	</table>
            
          		</form>
         	</s:if>
            </div>
		</div>
	</body>
	<script type="text/javascript">
		function check(){
			var endTime = $("#endTime").val();
			if(endTime==null || endTime==""){
				alert("请输入长访截至时间");
				return false;
			}
			$("#submitForm").submit();
			
		}
	</script>
</html>
