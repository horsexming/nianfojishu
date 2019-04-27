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
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body onload="createDept('ndept')">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<form action="CredentialsAction!add.action?ps=${ps}"
					onsubmit="return validate()" method="post"
					enctype="multipart/form-data">
					<table width="100%" border="0" class="table" bgcolor="#F0F0F0">
						<tr>
							<th colspan="8" align="center">
								<br />
								<font size="5px">驾驶证</font>
								<input name="credentials.cardtype" id="cardtype" readonly="readonly"
										type="hidden" value="驾驶证">
								<br />
							</th>
						</tr>
						<tr>
							<s:if test='ps=="admin"'>
								<th align="center">
									部门
								</th>
								<td align="left">
									<select id="ndept" style="width: 155px;">
										<option value="">
											--请选择部门--
										</option>
									</select>
									<font color="red">*</font>
								</td>
							</s:if>
							<th align="center">
								姓名:
							</th>
							<td>
								<s:if test='ps=="person"'>
									<input name="credentials.name" id="name" readonly="readonly"
										value="${user.name}">
								</s:if>
								<s:elseif test='ps=="admin"'>
									<select id="name" style="width: 155px;" onclick="deptNotNull()">
										<option></option>
									</select>
									<input id="name1" name="credentials.name" type="hidden">
								</s:elseif>
								<font color="red">*</font>
							</td>
							<th align="center">
								姓别:
							</th>
							<td>
								<s:if test='ps=="person"'>
									<input name="credentials.sex" id="sex" readonly="readonly"
										value="${user.sex}">
								</s:if>
								<s:elseif test='ps=="admin"'>
									<SELECT name="credentials.sex" id="sex" style="width: 156px;">
										<option value="">
											请选择性别
										</option>
										<option value="男">
											男
										</option>
										<option value="女">
											女
										</option>
									</SELECT>
								</s:elseif>
							</td>
							<th align="center">
								工号:
							</th>
							<td>
								<s:if test='ps=="person"'>
									<input name="credentials.code" id="code" readonly="readonly"
										value="${user.code}">
								</s:if>
								<s:elseif test='ps=="admin"'>
									<input name="credentials.code" id="code" readonly="readonly"
										value="${credentials.code}">
								</s:elseif>
								<font color="red">*</font>
							</td>
							<s:if test='ps=="person"'>
							<th align="center">
								类型:
							</th>
							<td>
								<input name="credentials.cardtype" readonly="readonly"
									value="驾驶证">
							</td>
							</s:if>
						</tr>
						<tr>
							<th align="center">
								出生日期:
							</th>
							<td>
								<input type="text" name="credentials.birthday" id="birthday"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<th align="center">
								住址:
							</th>
							<td>
								<input name="credentials.address" id="address"
									value="${credentials.address}">
							</td>
							<th align="center">
								驾驶证代号:
							</th>
							<td>
								<SELECT name="credentials.cartype" id="cartype" style="width: 173px;">
									<option value="">--请选择--</option>
									<option value="C1">C1</option>
									<option value="C2">C2</option>
									<option value="B1">B1</option>
									<option value="B2">B2</option>
									<option value="A1">A1</option>
									<option value="A2">A2</option>
									<option value="A3">A3</option>
								</SELECT>
								<font color="red">*</font>
							</td>
							<th align="center">
								初次领证日期:
							</th>
							<td>
								<input type="text" name="credentials.issuedate" id="issuedate"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
						</tr>
						<tr>
							<th align="center">
								有效起始日期:
							</th>
							<td>
								<input type="text" name="credentials.validfrom" id="validfrom"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<th align="center">
								有效截止日期:
							</th>
							<td>
								<input type="text" name="credentials.validfor" id="validfor"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
								<font color="red">*</font>
							</td>
							<th align="center">
								周期:
							</th>
							<td>
								<input name="credentials.cycle" id="cycle" 
									value="${credentials.cycle}">(年)
							</td>
							<th align="center">
								驾证文件:
							</th>
							<td>
								<input type="file" id="file" name="fujian">
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<th colspan="8" align="center">
								<input type="submit" style="width: 100px; height: 50px;"
									value="提交">
							</th>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
function validate() {
	if (!validateText("ndept", "部门")) {
		return false;
	}
	if (!validateText("name1", "姓名")) {
		return false;
	}
	if (!validateText("code", "工号")) {
		return false;
	}
	if (!validateText("cartype", "驾驶证代号")) {
		return false;
	}
	if (!validateText("cartype", "驾驶证代号")) {
		return false;
	}
	if (!validateText("validfor", "有效截止日期")) {
		return false;
	}
	if (!validateText("file", "上传文件")) {
		return false;
	}
	
}
//根据部门显示人员
$(function(){
	//显示部门对应的员工信息
	$("#ndept").bind(
			"change",
			function() {
				if ($("#ndept").val() != "") {
					$.ajax( {
						url : "UsersAction!findUsersByDept.action",
						type : 'post',
						dataType : 'json',
						cache : false,//防止数据缓存
						data : {
							deptName : $("#ndept").val()
						},
						success : function(useradsfa) {
							$("#name").empty();//清空
							$("#ncode").val("");
							$("<option></option>").appendTo("#name");
							$(useradsfa).each(
									function() {
										$(
												"<option value='"
															+ this.code + "|"
															+ this.name + "'>"
															+ this.name
															+ "</option>")
												.appendTo("#name")
									});
								$("#name").bind("change", function() {
								var name = $("#name").val();
								var usersData = name.split("|");
								var ncode = usersData[0];
								var nname = usersData[1];
								$("#code").val(ncode);
								$("#name1").val(nname);
							});
							
						},
						error : function() {
							alert("服务器异常!");
						}
					});
				} else {
					$("#name").empty();//清空
				$("#ncode").val("");
				}
		});
});
function validateText(id, textname) {
	var textValue = $.trim($("#" + id).val());
	if (textValue == null || textValue == "") {
		alert(textname + "不能为空");
		return false;
	}
	return true;
}
function deptNotNull() {
	if ($("#ndept").val() == "" || $("#ndept").val() == "") {
		alert("被访人部门不能为空！");
		return false;
	}
}
$("#cycle").keyup(function() {
	var tmptxt = $(this).val();
	$(this).val(tmptxt.replace(/\D|^0/g, ''));
})
</SCRIPT>
	</body>
</html>
