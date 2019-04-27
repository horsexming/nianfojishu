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
				<form action="JiaoXiaoKaoHeAction_addTargetAchievedMark.action" method="POST" onsubmit="return check()">
					<table class="table" style="width: 80%;">
						<tr>
							<th align="right">
								目标名称:
							</th>
							<td>
								<input type="text" value="" name="tam.targetName" id="targetName"
								/>
							</td>
						</tr>
						<tr>
							<th align="right">
								部门名称:
							</th>
							<td>
								<select name="tam.deptName" id="deptName">
									<option></option>
								</select>
							</td>
						</tr>
						<tr>
							<th align="right">
								加减分数:
							</th>
							<td>
								<input type="text" value="" name="tam.marks" id="marks"
								onchange="numyanzheng(this)"/>
							</td>
						</tr>
					</table>
					<input type="submit" value="添加" class="input" id="sub"/>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
$(function(){
	findDudList();
	if('${errorMessage}' == '添加成功!~'){
		alert('添加成功!~');
		parent.chageDiv('none');
		parent.window.location.reload();
	}
})
function check(){
	var coefficient =$("#coefficient").val();
	if(coefficient == ''){
		alert('请填写提取系数');
		$("#coefficient").focus();
		return false;
	}
	$("#sub").attr('disabled','disabled');
	return true;
}
function changvalue(obj){
	var value = obj.value;
	if(value!=''){
		var  strs = value.split("_");
		$("#rankNo").val(strs[0]);
		$("#rank").val(strs[1]);
	}
}

function findDudList(){
	$.ajax( {
		type : "POST",
		url : "JiaoXiaoKaoHeAction_findDudList.action",
		data:{'dud.isbmzmb':1},
		dataType : "json",
		success : function(data) {
			$("#deptName").empty();
			$("#deptName").append('<option value=""></option>');
			if(data!=null){
				$(data).each(function(){
					$("#deptName").append('<option value='+this.deptName+'>'+this.deptName+'</option>');
				})
			}
		}
	})
}
</SCRIPT>
	</body>
</html>
