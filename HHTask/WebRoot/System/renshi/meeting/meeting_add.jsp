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
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<div class="container">
					<h1 class="text-center">添加会议信息</h1>
					<div class="row">
						<s:if test="meeting!=null&&meeting.id!=null">
							<form action="${pageContext.request.contextPath}/meetingAction!addMeeting.action" method="post" id="submitForm" >
							<input type="hidden" name="meeting.id" value="${meeting.id }" id="meetingId">
						</s:if>
						<s:else>
							<form action="${pageContext.request.contextPath}/meetingAction!addMeeting.action" method="post" id="submitForm">
						</s:else>
							<table class="table table-responsive">
								<tr>
									<th class="text-right">会议标题：</th>
									<td>
										<input type="text" name="meeting.title" value="${meeting.title }" id="screenName" class="form-control" autocomplete="off">
									</td>
								</tr>
								<tr>
									<th class="text-right">会议位置：</th>
									<td>
										<select name="meeting.position" id="position"  class="form-control">
											<s:if test="meeting!=null && meeting.position!=null">
												<option value="${meeting.position}">${meeting.position}</option>
											</s:if>
											<s:else>
												<option value="">请选择会议位置</option>
											</s:else>
											
										</select>
		<%-- 								<input type="text" name="meeting.position" value="${meeting.position}" id="position"> --%>
									</td>
								</tr>
								<tr>
									<th class="text-right">会议预计开始时间</th>
									<td>
										<input type="text" name="meeting.startDate"  class="Wdate form-control" 
											onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',skin:'whyGreen'})"
											 value="${meeting.startDate}" id="startDate" autocomplete="off">
									</td>
								</tr>
								<tr>
									<th class="text-right">会议预计结束时间</th>
									<td>
										<input type="text" name="meeting.endDate" class="Wdate form-control" 
											onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',skin:'whyGreen'})"
											value="${meeting.endDate}" id="endDate" autocomplete="off">
									</td>
								</tr>
								
								<tr>
									<th class="text-right">会议内容</th>
									<td>
										<textarea rows="" cols="" class="form-control" name="meeting.content" autocomplete="off">${meeting.content }</textarea>
									</td>
								</tr>
								<tr>
									<th colspan="2"  class="text-center">
										<input type="button" class="input" value="提交" id="submitBtn" onclick="submitFunction()">
									</th>
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
			$(function(){
				
				$.ajax({
					type:"post",
					url:"universalAction!findCategoryByType.action?code=meetingPosition",
					dataType:"json",
					success:function(data){
						if(data!=null){
							for(var i = 0;i<data.length;i++){
								$("#position").append("<option value='"+data[i].name+"'>"+data[i].name+"</option>");
							}
						}	
						
					}
				});
			});
			function submitFunction(){
				$("#submitBtn").attr("disabled",true);
				
				var screenName = $("#screenName").val();
				if(screenName==null || screenName==""){
					alert("请输入会议标题");
					return false;
				}
				
				var position = $("#position").val();
				if(position==null || position==""){
					alert("请选择会议位置");
					return false;
				}
				
				var startDate = $("#startDate").val();
				if(startDate==null || startDate==""){
					alert("请输入会议开始时间");
					return false;
				}
				$("#submitBtn").attr("disabled",false);
				$("#submitForm").submit();
			}
		</script>
	</body>
</html>