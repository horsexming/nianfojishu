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
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<form action="CredentialsAction!addOne.action?ps=${ps}"
					onsubmit="return validate()" method="post"
					enctype="multipart/form-data">
					<table width="100%" border="0" class="table" bgcolor="#F0F0F0">
						<tr>
							<th colspan="8" align="center">
								<br />
								<font size="5px">证件添加</font>
								<br />
							</th>
						</tr>
						<tr>
							<th align="center">
								证件类型:
							</th>
							<td>
								<select name="credentials.cardtype" id="cardtype" style="width: 173px;">
									<option value="">空</option>
									<option value="驾驶证" selected="selected">驾驶证</option>
								</select>
								<font color="red">*</font>
							</td>
							<td colspan="6">
								<font color="red"><b>备注：</b>不添加驾驶证/行驶证，请选为空。  带*文本框为必填项！</font>
							</td>
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
									value="">
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
								驾驶证文件:
							</th>
							<td>
								<input type="file" id="file" name="fujian">
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<th align="center">
								证件类型:
							</th>
							<td>
								<select name="credentials1.cardtype" id="cardtype1" style="width: 173px;">
									<option value="">空</option>
									<option value="行驶证" selected="selected">行驶证</option>
								</select>
								<font color="red">*</font>
							</td>
							<td colspan="6">
							</td>
						</tr>
						<tr>
							<th align="center">
								车牌号码:
							</th>
							<td>
								<input name="credentials1.platenumber" id="platenumber1"
									value="">
								<font color="red">*</font>
							</td>
							<th align="center">
								车辆所有人:
							</th>
							<td>
								<input name="credentials1.owner" id="owner1"
									value="">
							</td>
							<th align="center">
								品牌型号:
							</th>
							<td>
								<input name="credentials1.model" id="model1"
									value="">
							</td>
							<th align="center">
								车辆识别代号:
							</th>
							<td>
								<input name="credentials1.vin" id="vin1"
									value="">
							</td>
						</tr>
						<tr>
							<th align="center">
								有效起始日期:
							</th>
							<td>
								<input type="text" name="credentials1.validfrom" id="validfrom1"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<th align="center">
								有效截止日期:
							</th>
							<td>
								<input type="text" name="credentials1.validfor" id="validfor1"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
								<font color="red">*</font>
							</td>
							<th align="center">
								周期:
							</th>
							<td>
								<input name="credentials1.cycle" id="cycle1" 
									value="">(年)
							</td>
							<th align="center">
								行驶证文件:
							</th>
							<td>
								<input type="file" id="file1" name="fujian1">
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
	var jiashi = $("#cardtype").val();
	var xingshi = $("#cardtype1").val();
	if (jiashi=='驾驶证') {
		if (!validateText("cartype", "驾驶证代号")) {
			return false;
		}
		if (!validateText("validfor", "有效截止日期")) {
			return false;
		}
		if (!validateText("file", "驾驶证附件")) {
			return false;
		}
	}
	if (xingshi=='行驶证') {
		if(!validateText("platenumber1", "车牌号码")){
			return false;
		}
		//匹配车牌正则
		var car = /^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$/
	    if ($("#platenumber1").val().search(car)==-1) {
	        alert("请输入正确的车牌号");
	        return false;
	    }
		if (!validateText("validfor1", "行驶证有效截止日期")) {
			return false;
		}
		if (!validateText("file1", "行驶证附件")) {
			return false;
		}
	}
	if((jiashi==''||jiashi==null) && (xingshi==''||xingshi==null)){
		alert("添加证件必须选择一种！");
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
$("#cycle").keyup(function() {
	var tmptxt = $(this).val();
	$(this).val(tmptxt.replace(/\D|^0/g, ''));
})
$("#cycle1").keyup(function() {
	var tmptxt = $(this).val();
	$(this).val(tmptxt.replace(/\D|^0/g, ''));
})
</SCRIPT>
	</body>
</html>
