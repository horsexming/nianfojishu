<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
		<script
			src="${pageContext.request.contextPath}/javascript/jquery/jquery-3.2.1.js">
</script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/js/bootstrap.js">
</script>
		<style type="text/css">
</style>
	</head>
	<body bgcolor="#ffffff">
		<div class="container">
			<div class="row clearfix">
				<h2 align="center">
					修改
				</h2>
				<div class="col-md-12 column">
					<form action="TaskmanagerAction_update.action" method="post">
						<input type="hidden" name="taskmanager.id"
							value="${taskmanager.id}" />
						<%--						<div class="form-group">--%>
						<%--							<label for="exampleInputEmail1">--%>
						<%--								名称--%>
						<%--							</label>--%>
						<%--							<input type="text" class="form-control" name="taskmanager.name"--%>
						<%--								value="${taskmanager.name}" />--%>
						<%--						</div>--%>
						<div class="form-group">
							<label for="">
								问题类型
							</label>
							<select class="form-control" id="taskTypes">
								<option value="问题点">
									问题点
								</option>
								<option value="新需求">
									新需求
								</option>
							</select>
							<input type="hidden" class="form-control" id="taskType"
								name="taskmanager.taskType" />
						</div>
						<div class="form-group">
							<label for="">
								问题分类
							</label>
							<input type="text" class="form-control"
								name="taskmanager.taskCategory" />
						</div>
						<div class="form-group">
							<label for="">
								问题描述
							</label>
							<textarea class="form-control" rows="8"
								name="taskmanager.description">${taskmanager.description}</textarea>
						</div>
						<%--						<div class="form-group">--%>
						<%--							<label for="">--%>
						<%--								需求--%>
						<%--							</label>--%>
						<%--							<textarea class="form-control" rows="8"--%>
						<%--								name="taskmanager.demand">${taskmanager.demand}</textarea>--%>
						<%--						</div>--%>
						<%--						<div class="form-group">--%>
						<%--							<label for="">--%>
						<%--								备注--%>
						<%--							</label>--%>
						<%--							<input type="text" class="form-control" name="taskmanager.note"--%>
						<%--								value="${taskmanager.note}" />--%>
						<%--						</div>--%>
						<div class="form-inline">
							<div class="form-group">
								<label for="">
									开始日期
								</label>
								<input type="date" class="form-control" id="startTime"
									name="taskmanager.startTime" value="${taskmanager.startTime}">
							</div>
							<div class="form-group">
								<label>
									领取人
								</label>
								<input type="text" class="form-control" id="receiver"
									name="taskmanager.receiver" value="${taskmanager.receiver}" />
							</div>
						</div>
						<br />
						<div class="form-inline">
							<div class="form-group">
								<label for="">
									完成日期
								</label>
								<input type="date" class="form-control"
									name="taskmanager.finishTime" value="${taskmanager.finishTime}">
							</div>
							<div class="form-group">
								<label>
									确认人
								</label>
								<input type="text" class="form-control"
									name="taskmanager.verifier" value="${taskmanager.verifier}" />
							</div>
						</div>
						<br />
						<div class="form-group">
							<label for="">
								处理意见
							</label>
							<textarea class="form-control" rows="8" id="suggestion"
								name="taskmanager.suggestion">${taskmanager.suggestion}</textarea>
						</div>
						<div class="form-group" id="difficultydiv">
							<label for="">
								困难度
							</label>
							<textarea class="form-control" rows="2" id="difficulty"
								name="taskmanager.difficulty">${taskmanager.difficulty}</textarea>

						</div>

						<div class="form-group">
							<label for="">
								状态
							</label>
							<input type="text" class="form-control" id="tstatus"
								name="taskmanager.taskState" value="${taskmanager.taskState}" />
						</div>
						<button type="submit" class="btn btn-default">
							修改
						</button>
					</form>
				</div>
			</div>
		</div>
		</center>
	</body>
	<script type="text/javascript">
$(document).ready(function(){ 
	if(${level}==self){
		$('#startTime').attr("readonly","true");
		$('#receiver').attr("readonly","true");
		$('#suggestion').attr("readonly","true");
		$('#tstatus').attr("readonly","true");
		$('#difficulty').attr("readonly","true");
		
	}else{
	}
}); 
</script>


</html>
