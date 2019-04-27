<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	</head>
	<form id="proForm" action="ProjectManage_addProjectManage.action" onsubmit="return checkForm()"
		enctype="multipart/form-data" method="post">
		<table class="table">
			<tr>
				<th colspan="4">
					<h2>
						项目立项
					</h2>
				</th>
			</tr>
			<tr>
				<th align="right">项目类别:</th>
				<th align="left" >
					<div>
						<select id="proType" name="projectManage.proType" onchange="changeProType()">
							<option></option>
							<option value="预研类">预研类</option>
							<option value="基础类">基础类</option>
							<option value="客户定制类">客户定制类</option>
							<option value="改善类">改善类</option>
						</select>
					</div>
				</th>
				<th align="right" >
					<font id="costomerStr">客户:</font>
				</th>
				<th align="left" id="costomerTd">
					<select name="projectManage.client" id="costomer">
						<option></option>
					</select>
				</th>
			</tr>
			<tr>
				<th align="right">
					项目名称:
				</th>
				<th align="left">
					<input id="projectName" name="projectManage.projectName" />
					<font color="red">*</font>
				</th>
				<th align="right">
					项目编号:
				</th>
				<th align="left">
					<input type="text" name="projectManage.projectNum" />
					(开头)
					<br />
					后面数字系统自动生成
				</th>
			</tr>
			<tr>
				<th align="right">
					预估金额:
				</th>
				<th align="left">
					<input name="projectManage.yuMoney" />
					元
				</th>
				<th align="right">
					是否保密:
				</th>
				<th align="left">
					<c:choose>
						<c:when test="${param.pageStatus == 'baomi'}">
							<input type="radio" value="是" name="projectManage.isbaomi"
								checked="checked" />是
						<input type="radio" value="否" name="projectManage.isbaomi" />否
					</c:when>
						<c:otherwise>
							<input type="text" value="否" name="projectManage.isbaomi" readonly="readonly" />
					</c:otherwise>
					</c:choose>
				</th>
			</tr>
			<tr>
				<th align="right">
					附件上传:
				</th>
				<th align="left" colspan="3">
					<%--					<input type="file" name="attachment">--%>
					<input type="button" id="fileButton_1" onclick="uploadFile(this,1)"
						value="上传附件">

					<div id="fileDiv_1" style="display: none;">

					</div>
				</th>
				
			</tr>
			<tr>
				<th align="right">
					项目内容/可行性分析:
				</th>
				<th align="left" colspan="3">
					<textarea rows="10" cols="80" name="projectManage.content"></textarea>
				</th>
			</tr>
			<tr>
				<th align="right">
					选择审批人:
				</th>
				<th align="left" id="freeDeptDiv" colspan="3">
					<font color="red">开发立项审批人:</font>
					<input type="button" value="增加" onclick="addFreeDept()"
						style="width: 60px; height: 30px">
					<ul id="freeDeptUl0">
						<li id="freeDeptli0">
							<SELECT id="zrdept0" name="deptIds" onchange="changefreeDept(0)"></SELECT>
							<SELECT id="zrpeople0" name="userIds"></SELECT>
							<input type="button" value="删除" onclick="deleteFreeDept(0)"
								style="width: 60px; height: 30px">
						</li>
					</ul>
				</th>
			</tr>
			<tr>
				<th align="center" colspan="4">
					<input type="submit" value="添加" class="input" />
					<input type="reset" value="重置" class="input" />
				</th>
			</tr>
		</table>
	</form>
	<script type="text/javascript">
$(function() {
	setDept(0);
	$("#proType").tinyselect();
	
	$.ajax( {
		type : "post",
		url : "${pageContext.request.contextPath}/orderManager_getCustomer.action",
		dataType : "json",
		async : false, 
		success : function(data) {
			$("#costomer").append("<option></option>");
			if (data != null) {
				for ( var i = 0; i < data.length; i++) {
					$("#costomer").append(
							"<option value='" + data[i] + "'>"
									+ data[i] + "</option>");
				}
			}
			$("#costomer").tinyselect();
		}

	});
	//设置客户项隐藏
	$("#costomerStr").hide();
	$("#costomerTd").children().filter("div").hide();
	
});

var fileDivHTML = "";
var count = 0;
function uploadFile(obj, few) {
	var fileDiv = document.getElementById("fileDiv_" + few);
	if (obj.value == "上传附件") {
		fileDiv.style.display = "block";
		obj.value = "添加文件";
	}
	fileDivHTML = "<div id='file"
			+ count
			+ "'><input type='file' name='attachment'><input type='text' name='otherName'>（文件名） <a href='javascript:delFile("
			+ count + "," + few + ")'>删除</a></div>";
	fileDiv.insertAdjacentHTML("beforeEnd", fileDivHTML);
	count++;
}

function delFile(obj, few) {
	document.getElementById("file" + obj).parentNode.removeChild(document
			.getElementById("file" + obj));
	count--;
	if (count <= 0) {
		count = 0;
		document.getElementById("fileButton_" + few).value = "上传附件";
		document.getElementById("fileDiv_" + few).style.display = "none";
	}
}

function checkForm(){
	if ($("#projectName").val() == "") {
		alert("请填写项目名称!");
		$("#projectName").focus();
		return false;
	} else if ($("#projectNum").val() == "") {
		alert("请填写项目编号!");
		$("#projectNum").focus();
		return false;
	} else {
		return true;
	}
}
var deptIndex = 0;
function setDept(i) {
	$.ajax( {
		type : "post",
		url : "GzstoreAction_getdept.action",
		dataType : "json",
		success : function(data) {
			//填充部门信息
			$(data).each(
					function() {
						var html = "";
						if (this.dept == "${Users.dept}") {
							html = "<option selected='selected' value='"
									+ this.id + "'>" + this.dept + "</option>";
						} else {
							html = "<option value='" + this.id + "'>"
									+ this.dept + "</option>";
						}
						$(html).appendTo("#zrdept" + i);
					});
			changefreeDept(i);
			$("#zrdept" + i).tinyselect();
		}
	});
}
function changefreeDept(i) {
	var deptId = $("#zrdept" + i).val();
	if (deptId > 0) {
		$.ajax( {
			type : "post",
			url : "GzstoreAction_getusers.action",
			dataType : "json",
			data : {
				id : deptId
			},
			success : function(data) {
				//填充部门信息
			var selectbox = $("#freeDeptUl" + i + " .tinyselect");
			if (selectbox.length > 1) {
				var len = selectbox.length - 1;
				for ( var n = len; n >= 1; n--) {
					$(selectbox[n]).remove();
				}
			}
			$("#zrpeople" + i).empty();
			$(data).each(
					function() {
						var html = "<option value='" + this.id + "'>"
								+ this.name + "</option>";
						$(html).appendTo("#zrpeople" + i);
					});
			$("#zrpeople" + i).tinyselect();

		}
		});
	}
}
function addFreeDept() {
	deptIndex++;
	var html = "<ul id='freeDeptUl" + deptIndex + "'>" + "<li id='freeDeptli"
			+ deptIndex + "'>" + "<SELECT id='zrdept" + deptIndex
			+ "' name='deptIds' onchange='changefreeDept(" + deptIndex
			+ ")'></SELECT>" + "<SELECT id='zrpeople" + deptIndex
			+ "' name='userIds'></SELECT>"
			+ "<input type='button' value='删除' onclick='deleteFreeDept("
			+ deptIndex + ")' style='width: 60px;height: 30px'>" + "</li></ul>"
	$(html).appendTo("#freeDeptDiv");
	setDept(deptIndex);
}
function deleteFreeDept(index) {
	$("#freeDeptUl" + index).remove();
}

function changeProType(){
	var proType = $("#proType").val();
	if(proType=="客户定制类"){
		$("#costomerStr").show();
		$("#costomerTd").children().filter("div").show();
	}else{
		$("#costomerStr").hide();
		$("#costomerTd").children().filter("div").hide();
	}
}
</script>