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
				<form
					action="CredentialsAction!add.action?ps=${ps}"
					onsubmit="return validate()" method="post"
					enctype="multipart/form-data">
					<table width="100%" border="0" class="table" bgcolor="#F0F0F0">
						<tr>
							<th colspan="8" align="center">
								<br />
								<font size="5px">行驶证</font>
								<br />
							</th>
						</tr>
						<tr>
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
							<th align="center">
								姓名:
							</th>
							<td>
								<s:if test='ps=="person"'>
									<input name="credentials.name" id="name" readonly="readonly"
										value="${user.name}">
								</s:if>
								<s:elseif test='ps=="admin"'>
									<select id="name" style="width: 155px;" 
										onclick="deptNotNull()">
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
									<input name="credentials.code" id="code"readonly="readonly"
										value="${credentials.code}">
								</s:elseif>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<th align="center">
								类型:
							</th>
							<td>
								<input name="credentials.cardtype" readonly="readonly"
									value="行驶证">
							</td>
							<th align="center">
								车牌号码:
							</th>
							<td>
								<input name="credentials.platenumber" id="platenumber"
									value="${credentials.platenumber}">
								<font color="red">*</font>
							</td>
							<th align="center">
								车辆类型:
							</th>
							<td>
								<input name="credentials.vehicletype" id="vehicletype"
									value="${credentials.vehicletype}">
							</td>
							<th align="center">
								所有人:
							</th>
							<td>
								<input name="credentials.owner" id="owner"
									value="${credentials.owner}">
							</td>
						</tr>
						<tr>
							<th align="center">
								品牌型号:
							</th>
							<td>
								<input name="credentials.model" id="model"
									value="${credentials.model}">
							</td>
							<th align="center">
								使用性质:
							</th>
							<td>
								<input name="credentials.usecharacter" id="usecharacter"
									value="${credentials.usecharacter}">
							</td>
							<th align="center">
								车辆识别代号:
							</th>
							<td>
								<input name="credentials.vin" id="vin"
									value="${credentials.vin}">
							</td>
							<th align="center">
								发动机号码:
							</th>
							<td>
								<input name="credentials.enginenumber" id="enginenumber"
									value="${credentials.enginenumber}">
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
								行驶证文件:
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
	if (!validateText("platenumber", "车牌号码")) {
		return false;
	}
	if (!validateText("validfor", "有效截止日期")) {
		return false;
	}
	if (!validateText("file", "上传文件")) {
		return false;
	}
}

function validateText(id, textname) {
	var textValue = $.trim($("#" + id).val());
	if (textValue == null || textValue == "") {
		alert(textname + "不能为空");
		return false;
	}
	return true;
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
