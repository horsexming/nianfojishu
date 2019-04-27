<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<!-- include file="/util/inc.jsp" -->
		<base href="<%=basePath%>">
		<title>license信息管理</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<style type="text/css">
.tag_1 {
	padding-top: 4px;
	width: 140px;
	height: 30px;
	background: url('<%=path%>/admin/images/tags1_1.jpg');
}

.tag_2 {
	padding-top: 4px;
	width: 140px;
	height: 30px;
	background: url('<%=path%>/admin/images/tags1_2_2.jpg');
}
</style>
	<script type="text/javascript"
		src="javascript/DatePicker/WdatePicker.js" />

</script>

		<script type="text/javascript">

function updatesubmit(){
		var companyName= document.getElementById("companyName");
		var shortName= document.getElementById("shortName");
		var onLineConut= document.getElementById("onLineConut");
		var address= document.getElementById("address");
		var companyPeople= document.getElementById("companyPeople");
		var email= document.getElementById("email");
		var tel= document.getElementById("tel");
		var fax= document.getElementById("fax");
		var zip= document.getElementById("zip");
		var business= document.getElementById("business");
		var notAfter = document.getElementById("notAfter");
	    var startTime = document.getElementById("startTime");
		var form=document.getElementById("updateform");
   var filter = /[0-9][-]{0,}[0-9]+$/;
	if (companyName.value == "") {
		alert("公司名称不能为空!");
		return false;
	} else if (shortName.value == "") {
		alert("公司简称不能为空!");
		return false;
	}else if (onLineConut.value == "") {
		alert("同时在线点数不能为空!");
		return false;
	} else if (onLineConut.value != "" & isNaN(onLineConut.value)) {
		alert("同时在线点数为整数！");
		return false;
	} else if (address.value == "") {
		alert("公司地址不能为空!");
		return false;
	}else if (companyPeople.value == "") {
		alert("申请人不能为空!");
		return false;
	}else if (email.value == "") {
		alert("邮箱地址不能为空!");
		return false;
	}else if (tel.value != "" & !filter.test(tel.value)) {
		alert("请输入正确的号码格式");
		return false;
	} else if (fax.value != "" & !filter.test(fax.value)) {
		alert("请输入正确的传真格式");
		return false;
	} else if (zip.value != "" & isNaN(zip.value)) {
		alert("邮编栏请输入数字");
		return false;
	}  else if (business.value == ""|| business.value=="请选择") {
		alert("请选择行业");
		return false;
	} else if (startTime.value == "") {
		alert("使用时间不能为空!");
		return false;
	} else if (notAfter.value == "") {
		alert("截止时间不能为空!");
		return false;
	} else {
		form.action = "licenseMsgAction_update.action";
		form.submit();
	}
}

function backsubmit(){
window.location.href= "licenseMsgAction_showlicenseMsgs.action";
}

</SCRIPT>

		<link rel="stylesheet" href="css/css.css" type="text/css"></link>
	</head>
 
	<body bgcolor="#ffffff" onload="createDept('dept')">
		<center>
			<div style="width: 1000px;">
				<div id="xitong" align="center"
					style="width: 100%; height: 31px; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; background: url('admin/images/admin_title.jpg');">
					<font color="#ffffff">license信息编辑</font>
				</div>				
				</div>
					<div>
						<div align="center">
							<font color="red"><s:property value="#session.successMessage"/></font>
						</div>
						
						<div  align="center" padding-top: 20px;">
							<div align="center" id="addModule">
								<form id="updateform" action="" method="post"
									enctype="multipart/form-data">
									<table class="table" align="center">
								    
										<tr>
									<td align="right" width="45%">
										公司全称:
									</td>
									<td>
									<input type="hidden" name="licenseMsg.id" value="<s:property value="licenseMsg.id"/>">
										<input  id="companyName" type="text" value="<s:property value="licenseMsg.companyName"/>"
											name="licenseMsg.companyName" />
										<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<td align="right">
										公司简称:
									</td>
									<td>
										<input  id="shortName" type="text" value="<s:property value="licenseMsg.shortName"/>"
											name="licenseMsg.shortName" />
										<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<td align="right">
										同时在线点数:
									</td>
									<td>
										<input  id="onLineConut" type="text" value="<s:property value="licenseMsg.onLineConut"/>"
											name="licenseMsg.onLineConut" />
										<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<td align="right">
										一体机数:
									</td>
									<td>
										<input  id="oneMackConut" type="text" value="<s:property value="licenseMsg.oneMackConut"/>"
											name="licenseMsg.oneMackConut" />
										<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<td align="right">
										LED数:
									</td>
									<td>
										<input  id="onLEDConut" type="text" value="<s:property value="licenseMsg.onLEDConut"/>"
											name="licenseMsg.onLEDConut" />
										<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<td align="right">
										看板数:
									</td>
									<td>
										<input  id="oneScreenConut" type="text" value="<s:property value="licenseMsg.oneScreenConut"/>"
											name="licenseMsg.oneScreenConut" />
										<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<td align="right">
										公司地址:
									</td>
									<td>
										<textarea  rows="2" cols="35" id="address"
											name="licenseMsg.address"><s:property value="licenseMsg.address"/></textarea>
										<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<td align="right">
										公司网址:
									</td>
									<td>
										<textarea rows="2" cols="40" id="companyUrl"
											name="licenseMsg.companyUrl"><s:property value="licenseMsg.companyUrl"/></textarea>
									</td>
								</tr>
								<tr>
									<td align="right">
										申请人:
									</td>
									<td><input id="companyPeople" type="text" value="<s:property value="licenseMsg.companyPeople"/>"
											name="licenseMsg.companyPeople" />
										<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<td align="right">
										邮件地址:
									</td>
									<td>
										<input id="email" type="text" value="<s:property value="licenseMsg.email"/>"
											name="licenseMsg.email" />
										<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<td align="right">
										联系电话:
									</td>
									<td>
										<input id="tel" type="text" value="<s:property value="licenseMsg.tel"/>"
											name="licenseMsg.tel" />
										<font color="red">*</font>
									</td>
									
								</tr>
								<tr>
									<td align="right">
										公司传真:
									</td>
									<td>
										<input id="fax" type="text" value="<s:property value="licenseMsg.fax"/>"
											name="licenseMsg.fax" />
										<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<td align="right">
										邮编:
									</td>
									<td>
										<input id="zip" type="text" value="<s:property value="licenseMsg.zip"/>"
											name="licenseMsg.zip" />
										<font color="red">*</font>
									</td>
								</tr>
								
								
								<tr>
									<td align="right">
										行业:
									</td>
									<td>
									<SELECT name="licenseMsg.business" id="business">
									 <option value="<s:property value="licenseMsg.business"/>"><s:property value="licenseMsg.business"/></option>
									<option>信息技术业</option>
									<option>制造业</option>
									<option>采掘业</option>
									<option>电力、煤气及水的产业的生产和供应业</option>
									<option>建筑房地产业</option>
									<option>交通运输、仓储业</option>
									<option>批发和零售贸易</option>
									<option>金融、保险业</option>
									<option>社会服务业</option>
									<option>教育事业</option>
									<option>医疗卫生</option>
									<option>农、林、牧、渔业</option>
									<option>传播与文化产业</option>
									<option>政府机关</option>
									<option>其他产业</option>
									</SELECT>
										<font color="red">*</font>
									</td>
								</tr>
                                <tr>
									<td align="right">
										公司描述:
									</td>
									<td>
										<textarea rows="3" cols="40" id="description"
											name="licenseMsg.description"><s:property value="licenseMsg.description"/></textarea>
									</td>
								</tr>
								<tr>
									<td align="right">
										使用时间:
									</td>
									<td>
										<input type="text" class="Wdate" name="licenseMsg.startTime"
											value="<s:property value="licenseMsg.startTime"/>"
											id="startTime"
											onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
										<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<td align="right">
										截止时间:
									</td>
									<td>
										<input type="text" class="Wdate" name="licenseMsg.notAfter"
											value="<s:property value="licenseMsg.notAfter"/>"
											id="notAfter"
											onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
										<font color="red">*</font>
									</td>
								</tr>

								<tr>
									<td colspan="2" align="center">
										<br>
										<input type="button" class="input" onclick="updatesubmit()"
											value="提交">
										<input type="reset" class="input" value="重置">
									</td>
								</tr>
									</table>
								</form>
							</div>

						</div>

					<div align="center">
						<%
							request.getSession().removeAttribute("successMessage");
							request.getSession().removeAttribute("errorMessage");
						%>
						<br />
						<br />
						<br />
					</div>
				</div>
		</center>
		<script type="text/javascript">
</script>
	</body>
</html>
