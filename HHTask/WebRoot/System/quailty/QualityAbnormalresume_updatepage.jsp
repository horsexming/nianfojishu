<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/css/bootstrap.min.css">
		<script
			src="${pageContext.request.contextPath}/javascript/jquery/jquery-3.2.1.js">
</script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/js/bootstrap.js">
</script>
	</head>
	<body>
		<div class="container">
			<div class="row clearfix">
				<h2 align="center">
					修改品质异常履历
				</h2>
				<div class="col-md-12 column">
					<form action="QualityAbnormalresumeAction_update.action"
						method="post" enctype="multipart/form-data" id="myform">
						<input type="hidden" name="qualityAbnormalresume.id"
							value="${qualityAbnormalresume.id}" />
							<input type="hidden" name="cpage" value="${cpage}" />
						<div class="form-group">
							<label for="">
								异常来源
							</label>
							<%--<input type="text" class="form-control"--%>
								<%--name="qualityAbnormalresume.type"--%>
								<%--value="${qualityAbnormalresume.type}" />--%>
							<select class="form-control" id="typesel"
									name="qualityAbnormalresume.type"  >
								<option value="${qualityAbnormalresume.type}">
									${qualityAbnormalresume.type}
								</option>
								<option value="外购进料检验">
									外购进料检验
								</option>
								<option value="外协进料检验">
									外协进料检验
								</option>
								<option value="前制程检验">
									前制程检验
								</option>
								<option value="装配过程检验">
									装配过程检验
								</option>
								<option value="成品检验">
									成品检验
								</option>
								<option value="客户外检">
									客户外检
								</option>
								<option value="客户投诉">
									客户投诉
								</option>
								<option value="市场投诉">
									市场投诉
								</option>
								<option value="其他" >
									其他
								</option>
							</select>
						</div>
						<div class="form-group row">
							<div class="col-xs-4">
								<label for="">
									反馈单位/个人
								</label>
								<input type="text" class="form-control"
									name="qualityAbnormalresume.feedbackCo"
									value="${qualityAbnormalresume.feedbackCo}" />
							</div>
						</div>
						<div class="form-group row">
							<div class="col-xs-4">
								<label for="">
									问题类型(大)：
								</label>
									<select class="form-control" id="typeOfProblem"
									name="qualityAbnormalresume.typeOfProblem" onchange="change();">
									<option value="${qualityAbnormalresume.typeOfProblem}">
										${qualityAbnormalresume.typeOfProblem}
									</option>
								
								</select>
							</div>
						</div>
						<div class="form-group row">
							<div class="col-xs-4">
								<label for="">
									问题类型(小)：
								</label>
									<select class="form-control" id="inTypeOfProblem"
									name="qualityAbnormalresume.inTypeOfProblem">
									<option value="${qualityAbnormalresume.inTypeOfProblem}">
										${qualityAbnormalresume.inTypeOfProblem}
									</option>
						
								</select>
							</div>
						</div>
						<div class="form-group row">
							<div class="col-xs-4">
								<label for="">
									责任部门/单位
								</label>
								<input type="text" class="form-control"
									name="qualityAbnormalresume.responsibleCo"
									value="${qualityAbnormalresume.responsibleCo}" />
							</div>
						</div>
						<div class="form-group row">
							<div class="col-xs-4">
								<label for="">
									编码/图号
								</label>
								<input type="text" class="form-control"
									name="qualityAbnormalresume.code"
									value="${qualityAbnormalresume.code}" />
							</div>
						</div>
						<div class="form-group">
							<label for="">
								反馈日期
							</label>
							<input type="date" class="form-control id="
								complaintTime" style="width: 20%"
								name="qualityAbnormalresume.complaintTime"
								value="${qualityAbnormalresume.complaintTime}">
						</div>
						<div class="form-group row">
							<div class="col-xs-4">
								<label for="">
									跟踪人
								</label>
								<input type="text" class="form-control"
									name="qualityAbnormalresume.trackPeople"
									value="${qualityAbnormalresume.trackPeople}" />
							</div>
						</div>
						<div class="form-group">
							<label for="">
								异常描述
							</label>
							<font color="red"> *</font>
							<textarea class="form-control" rows="2"
								name="qualityAbnormalresume.description" id="">${qualityAbnormalresume.description}</textarea>
						</div>
						<div class="form-group">
							<label for="">
								原因分析
							</label>
							<textarea class="form-control" rows="2"
								name="qualityAbnormalresume.analyzes" id="">${qualityAbnormalresume.analyzes}</textarea>
						</div>
						<div class="form-group">
							<label for="">
								改善方案
							</label>
							<textarea class="form-control" rows="2"
								name="qualityAbnormalresume.scheme" id="">${qualityAbnormalresume.scheme}</textarea>
						</div>
						<div class="form-group col-xs-4" style="padding-left: 0px">
							<div class="">
								<label for="">
									是否闭环
								</label>
								<select class="form-control" id="process"
									name="qualityAbnormalresume.closedLoop"
									value="${qualityAbnormalresume.closedLoop}">
									<option value="未执行">
										未执行
									</option>
									<option value="已执行">
										已执行
									</option>
								</select>
							</div>
						</div>
						<div class="form-group col-xs-4">
							<div class="">
								<label for="">
									展开编码
								</label>
								<input type="text" class="form-control"
									name="qualityAbnormalresume.unfoldCode"
									value="${qualityAbnormalresume.unfoldCode}" />
							</div>
						</div>
						<div class="form-group col-xs-4">
							<label for="">
								责任人
							</label>
							<input type="text" class="form-control"
								name="qualityAbnormalresume.responsible"
								value="${qualityAbnormalresume.responsible}" />
						</div>
<%--						<div class="form-group">--%>
<%--							<label for="">--%>
<%--								不良图片--%>
<%--							</label>--%>
							<input type="hidden" name="fileUpload" multiple="multiple"
								id="files" />
<%--						</div>--%>
						<div class="form-group">
							<button type="submit" class="btn btn-default" id="taskadd">
								修改
							</button>
						</div>
					</form>
				</div>
			</div>
		</div>


		<script type="text/javascript"
			src="<%=request.getContextPath()%>/javascript/jquery-1.8.3.js">
</script>
		<script type="text/javascript">
		$(function() {
	//var tag = "leixing";
	$.ajax( {
		type : "post",
		url : "BuHeGePinAction_ajaxfindAllDefTypeList.action",
		dataType : "json",
		success : function(data) {
			$(data).each(
					function() {
						$(
								"<option value='"+this.defName+"'>"
										+ this.defName + "</option>")
								.appendTo("#typeOfProblem");
					});

		}
	});
});
	function change(){
		var statue = document.getElementById("typeOfProblem").value
		$.ajax( {
		type : "post",
		url : "BuHeGePinAction_findBhgByDefName.action",
		dataType : "json",
		data:{
					"statue":statue
				},
		success : function(data) {
			$(data).each(
					function() {
						$(
								"<option value='" +this.type+"'>"
										+ this.type + "</option>")
								.appendTo("#inTypeOfProblem");
					});

		}
	});
	}
</script>
	</body>
	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->

</html>
