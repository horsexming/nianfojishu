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
<%--				<form action="TaskmanagerAction_daoru.action" method="post"--%>
<%--					enctype="multipart/form-data" onsubmit="return checktype()">--%>
<%--					<table class="table" width="75%">--%>
<%--						<tr>--%>
<%--							<th align="right">--%>
<%--								选择导入文件:--%>
<%--							</th>--%>
<%--							<td align="left">--%>
<%--								<input type="file" name="addfil" />--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--						<tr>--%>
<%--							<th>--%>
<%--							</th>--%>
<%--							<td align="left">--%>
<%--								<input type="submit"  id="sub" value="批量导入" class="input">--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--					</table>--%>
<%--				</form>--%>
				<h2 align="center">
					添加问题反馈
				</h2>
				<div class="col-md-12 column">
					<form action="TaskmanagerAction_add.action" method="post"
						enctype="multipart/form-data" id="myform">

						<div class="form-group">
							<label class="radio-inline">
								<input type="radio" name="taskmanager.taskType" id="TypeRadio1"
									value="问题点" checked="checked">
								问题点
							</label>
							<label class="radio-inline">
								<input type="radio" name="taskmanager.taskType" id="TypeRadio2"
									value="新需求">
								新需求
							</label>
						</div>
						<div class="form-group">
							<label class="radio-inline">
								<input type="radio" name="taskmanager.urgency" id="inlineRadio1"
									value="0" checked="checked">
								一般
							</label>
							<label class="radio-inline">
								<input type="radio" name="taskmanager.urgency" id="inlineRadio2"
									value="1">
								紧急
							</label>
							<label class="radio-inline">
								<input type="radio" name="taskmanager.urgency" id="inlineRadio2"
									value="2">
								非常紧急
							</label>
						</div>
						<%--						<div class="form-group">--%>
						<%--							<label for="exampleInputEmail1">--%>
						<%--								名称--%>
						<%--							</label>--%>
						<%--							<input type="text" class="form-control" name="taskmanager.name" />--%>
						<%--						</div>--%>
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
							<font color="red"> *</font>
							<textarea class="form-control" rows="3"
								name="taskmanager.description" id="taskdescription"></textarea>
						</div>
						<div class="form-group">
							<label for="">
								关联订单号或零件号
							</label>
							<input type="text" class="form-control" name="taskmanager.note" />
						</div>
						<%--						<div class="form-group">--%>
						<%--							<label for="">--%>
						<%--								问题类型--%>
						<%--							</label>--%>
						<%--							<select class="form-control" id="taskTypes">--%>
						<%--								<option value="问题点">--%>
						<%--									问题点--%>
						<%--								</option>--%>
						<%--								<option value="新需求">--%>
						<%--									新需求--%>
						<%--								</option>--%>
						<%--							</select>--%>
						<%--							<input type="hidden" class="form-control" id="taskType"--%>
						<%--								name="taskmanager.taskType" />--%>
						<%--						</div>--%>
						<div class="form-group">
							<label for="">
								问题截图 (附件)
							</label>
							<input type="file" name="fileUpload" multiple="multiple"
								id="files" />
						</div>
						<div class="form-inline">
							<div class="form-group">
								<label for="">
									所属流程  :
								</label>
								<select class="form-control" id="process" name="taskmanager.process">
									<option value="其他 ">
										其他
									</option>
									<option value="订单">
										订单
									</option>
									<option value="技术">
										技术
									</option>
									<option value="计划">
										计划
									</option>
									<option value="采购">
										采购
									</option>
									<option value="物流">
										物流
									</option>
									<option value="生产">
										生产
									</option>
									<option value="交付">
										交付
									</option>
								</select>
							</div>
							<div class="form-group">
								<label for="">
									 &nbsp &nbsp &nbsp功能所属类 :
								</label>
								<select class="form-control" id="functionType" name="taskmanager.functionType">
									<option value="其他 ">
										其他
									</option>
									<option value="查询">
										查询
									</option>
									<option value="录入">
										录入
									</option>
									<option value="计算">
										计算
									</option>
								</select>
							</div>
							<div class="form-group">
								<label for="">
									 &nbsp &nbsp &nbsp异常类别 :
								</label>
								<select class="form-control" id="exceptionType" name="taskmanager.exceptionType">
									<option value="其他">
										其他
									</option>
									<option value="异常">
										异常
									</option>
									<option value="错误">
										错误
									</option>
									<option value="系统延迟">
										系统延迟
									</option>
								</select>
							</div>
							<div class="form-group">
								<label for="">
									 &nbsp &nbsp &nbsp重复出现次数 :
								</label>
								<input type="text" class="form-control"
								name="taskmanager.repeatTime" value="1" />
							</div>
						</div>
						</br>


						<%--						<div class="form-group">--%>
						<%--							<label for="">--%>
						<%--								需求--%>
						<%--							</label>--%>
						<%--							<textarea class="form-control" rows="3" name="taskmanager.demand"></textarea>--%>
						<%--						</div>--%>
						<%--						<div class="form-group">--%>
						<%--							<label for="">--%>
						<%--								备注--%>
						<%--							</label>--%>
						<%--							<input type="text" class="form-control" name="taskmanager.note" />--%>
						<%--						</div>--%>
						<%--						<div class="form-inline">--%>
						<%--							<div class="form-group">--%>
						<%--								<label for="">--%>
						<%--									开始日期--%>
						<%--								</label>--%>
						<%--								<input type="date" class="form-control"--%>
						<%--									name="taskmanager.startTime">--%>
						<%--							</div>--%>
						<%--							</br>--%>
						<%--							</br>--%>
						<%--							<div class="form-group">--%>
						<%--								<label>--%>
						<%--									领取人--%>
						<%--								</label>--%>
						<%--								<input type="text" class="form-control"--%>
						<%--									name="taskmanager.receiver" />--%>
						<%--							</div>--%>
						<%--						</div>--%>
						<button type="submit" class="btn btn-default" id="taskadd">
							添加
						</button>
					</form>
				</div>
			</div>
		</div>
		</div>
		</center>
	</body>
	<script type="text/javascript">
$("#TypeRadio1").click(function() {
	$("#TypeRadio1").attr("checked", "checked");
	$("#TypeRadio2").removeAttr("checked", "checked");
});

$("#TypeRadio2").click(function() {
	$("#TypeRadio1").removeAttr("checked", "checked");
	$("#TypeRadio2").attr("checked", "checked");
});

$("#taskadd").click(function() {
	if ($("#taskdescription").val() == "") {
		alert("对不起，问题描述不能为空");
		$("#taskdescription").focus();
		return false;
	}
	if ($("#TypeRadio1").attr("checked")) {
		if ($("#files").val() == "") {
			alert("对不起，问题点请上传附件");
			$("#files").focus();
			return false;
		}
	}
	$("#myform").submit()
	$('#taskadd').attr("disabled", true);
});
</script>

</html>
