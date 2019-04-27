<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<script type="text/javascript"
		src="javascript/DatePicker/WdatePicker.js" />

</script>
	<head>
		<!-- include file="/util/inc.jsp" -->
		<base href="<%=basePath%>">
		<%@include file="/util/sonHead.jsp"%>
		<title>License信息管理</title>
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
		<script type="text/javascript">
function addsubmit() {
	var companyName = document.getElementById("companyName");
	var onLineConut = document.getElementById("onLineConut");
	var shortName = document.getElementById("shortName");
	var address = document.getElementById("address");
	var companyUrl = document.getElementById("companyUrl");
	var companyPeople = document.getElementById("companyPeople");
	var email = document.getElementById("email");
	var tel = document.getElementById("tel");
	var fax = document.getElementById("fax");
	var zip = document.getElementById("zip");
	var business = document.getElementById("business");
	var notAfter = document.getElementById("notAfter");
	var startTime = document.getElementById("startTime");
	var form = document.getElementById("addform");
	var filter = /[0-9][-]{0,}[0-9]+$/;
	if (companyName.value == "") {
		alert("公司名称不能为空!");
		return false;
	} else if (shortName.value == "") {
		alert("公司简称不能为空!");
		return false;
	} else if (onLineConut.value == "") {
		alert("同时在线点数不能为空!");
		return false;
	} else if (onLineConut.value != "" & isNaN(onLineConut.value)) {
		alert("同时在线点数为整数！");
		return false;
	} else if (address.value == "") {
		alert("公司地址不能为空!");
		return false;
	} else if (companyUrl.value == "") {
		alert("公司网址不能为空!");
		return false;
	} else if (companyPeople.value == "") {
		alert("申请人不能为空!");
		return false;
	} else if (email.value == "") {
		alert("邮箱地址不能为空!");
		return false;
	} else if (tel.value != "" & !filter.test(tel.value)) {
		alert("请输入正确的号码格式");
		return false;
	} else if (fax.value != "" & !filter.test(fax.value)) {
		alert("请输入正确的传真格式");
		return false;
	} else if (zip.value != "" & isNaN(zip.value)) {
		alert("邮编栏请输入数字");
		return false;
	} else if (business.value == "" || business.value == "请选择") {
		alert("请选择行业");
		return false;
	} else if (startTime.value == "") {
		alert("使用时间不能为空!");
		return false;
	} else if (notAfter.value == "") {
		alert("截止时间不能为空!");
		return false;
	} else {
		form.action = "licenseMsgAction_add.action";
		form.submit();
	}
}
var oldObj;
var oldObj2;
function chageModule(obj, obj2) {
	if (obj.id != "module1") {
		document.getElementById("module1").className = "tag_1";
		document.getElementById("module1_1").style.display = "none";
	}
	if (oldObj != null) {
		oldObj.className = "tag_1";
		document.getElementById("module1_" + oldObj2).style.display = "none";
	}

	obj.className = "tag_2";
	document.getElementById("module1_" + obj2).style.display = "block";

	oldObj = obj;
	oldObj2 = obj2;
}
function chageBgcolor(obj) {
	obj.style.background = "#c0dcf2";
}
function outBgcolor(obj, oldColor) {
	obj.style.background = oldColor;
}
</script>

		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/css/css.css" />
	</head>
	<body bgcolor="#ffffff">
		<center>
			<div style="width: 1000px;">
				<div id="xitong" align="center"
					style="width: 100%; height: 31px; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; background: url('admin/images/admin_title.jpg');">
					<font color="#ffffff">License信息管理</font>
				</div>
				<div
					style="border: solid 1px #0170b8; width: 1000px; font-family: 微软雅黑; margin-top: 10px;"
					align="left">
					<div
						style="padding-top: 10px; padding-left: 20px; border-bottom: solid #0170b8 1px;">
						<table align="center">
							<tr align="center">
								<td align="center">
									<div id="module1" class="tag_2" onclick="chageModule(this,'1')">
										License信息列表
									</div>
								</td>
								<td align="center">
									<div id="module2" class="tag_1" onclick="chageModule(this,'2')">
										添加License信息
									</div>
								</td>
								<!--
								<td align="center">
									<div id="module3" class="tag_1" onclick="chageModule(this,'3')">
										修改公司信息
									</div>
								</td>
								
								<td align="center">
									<div id="module4" class="tag_1" onclick="chageModule(this,'4')">
										删除公司信息
									</div>
								</td>
							</tr>
							-->
						</table>
					</div>
				</div>
			</div>
			<div>
				<div align="center">
					<font color="red"><s:property
							value="#request.successMessage" /> </font>
				</div>
				<div id="module1_1" align="center">
					<div id="bangding">
						<form action="licenseMsgAction_showLicenseMsgs.action" method="post"
							style="margin: 0px">
							<br>
							<table class="table">
								<tr>

									<td align="right">
										公司名称:
									</td>
									<td>
										<input type="text" name="licenseMsg.companyName"
											value="<s:property value="licenseMsg.companyName"/>" />
									</td>

									<td align="right">
										公司电话:
									</td>
									<td>
										<input type="text" name="licenseMsg.tel"
											value="<s:property value="licenseMsg.tel"/>" />
									</td>
									<td rowspan="2">
										<input type="submit" value="查询"
											style="width: 100px; height: 50px;" />
									</td>
								</tr>
								<tr>
									<td align="right">
										负责人:
									</td>
									<td>
										<input type="text" name="licenseMsg.companyPeople"
											value="<s:property value="licenseMsg.companyPeople"/>" />
									</td>
									<td align="right">
										公司地址:
									</td>
									<td>
										<input type="text" name="licenseMsg.address"
											value="<s:property value="licenseMsg.address"/>" />
									</td>

								</tr>
							</table>
						</form>
						<hr>
						<form action="licenseMsgAction_showlicenseMsgs.action"
							method="post" target="main" style="margin: 0px">
							<input type="hidden" name="id" value="${id}">
							<table class="table">
								<tr align="center" bgcolor="#c0dcf2"
									style="height: 40px; font-weight: bold;">
									<td>
										序号
									</td>

									<td>
										公司全称
									</td>
									<td>
										公司简称
									</td>
									<td>
										点数
									</td>
									<td>
										公司地址
									</td>
									<td>
										公司网址
									</td>

									<td>
										负责人
									</td>
									<td>
										邮件地址
									</td>
									<td>
										联系电话
									</td>
									<td>
										传真
									</td>
									<td>
										邮编
									</td>
									<td>
										行业
									</td>
									<td>
										描述
									</td>
									<td>
										使用时间
									</td>
									<td>
										截止时间
									</td>
									<td>
										操作
									</td>
								</tr>
								<tr bgcolor="red">
									<td colspan="16" align="center">
										<font color="#FFFFFF">证书到期的公司</font>
									</td>
								</tr>
								<s:if test="endList.size()==0">
									<tr>
										<td colspan="16" align="center">
											<font color="red">没有证书到期的公司</font>
										</td>
									</tr>
								</s:if>
								<s:iterator id="pagelicenseMsg0" value="endList"
									status="ststusfunction0">
									<tr align="center" bgcolor="red">
										<td>
											${ststusfunction0.index+1}
										</td>
										<td>
											<s:property value="#pagelicenseMsg0.companyName" />
										</td>
										<td>
											<s:property value="#pagelicenseMsg0.shortName" />
										</td>
										<td>
											<s:property value="#pagelicenseMsg0.onLineConut" />
										</td>
										<td>
											<s:property value="#pagelicenseMsg0.address" />
										</td>
										<td>
											<s:property value="#pagelicenseMsg0.companyUrl" />

										</td>
										<td>
											<s:property value="#pagelicenseMsg0.companyPeople" />
										</td>
										<td>
											<s:property value="#pagelicenseMsg0.email" />
										</td>
										<td>
											<s:property value="#pagelicenseMsg0.tel" />
										</td>
										<td>
											<s:property value="#pagelicenseMsg0.fax" />
										</td>
										<td>
											<s:property value="#pagelicenseMsg0.zip" />
										</td>
										<td>
											<s:property value="#pagelicenseMsg0.business" />
										</td>
										<td>
											<s:property value="#pagelicenseMsg0.description" />
										</td>
										<td>
											<s:property value="#pagelicenseMsg0.startTime" />
										</td>
										<td>
											<s:property value="#pagelicenseMsg0.notAfter" />
										</td>

										<td>
											<a
												onclick="if(window.confirm('您将删除数据是否继续？')){window.location.href = 'licenseMsgAction_delete.action?cpage=${cpage}&licenseMsg.id=<s:property value="#pagelicenseMsg0.id"/>'};"
												target="main">删除</a>
											<a
												href="licenseMsgAction_toupdate.action?licenseMsg.id=<s:property value="#pagelicenseMsg0.id"/>"
												target="main">编辑</a>
										</td>
								</s:iterator>
								<tr bgcolor="red">
									<td colspan="16" align="center">
										<font color="#FFFFFF">证书即将到期的公司</font>
									</td>
								</tr>
								<s:if test="enddingList.size()==0">
									<tr>
										<td colspan="16" align="center">
											<font color="red">没有证书即将到期的公司</font>
										</td>
									</tr>
								</s:if>
								<s:iterator id="pagelicenseMsg1" value="enddingList"
									status="ststusfunction">
									<tr align="center" bgcolor="red">
										<td>
											${ststusfunction.index+1}
										</td>
										<td>
											<s:property value="#pagelicenseMsg1.companyName" />
										</td>
										<td>
											<s:property value="#pagelicenseMsg1.shortName" />
										</td>
										<td>
											<s:property value="#pagelicenseMsg1.onLineConut" />
										</td>
										<td>
											<s:property value="#pagelicenseMsg1.address" />
										</td>
										<td>
											<s:property value="#pagelicenseMsg1.companyUrl" />

										</td>
										<td>
											<s:property value="#pagelicenseMsg1.companyPeople" />
										</td>
										<td>
											<s:property value="#pagelicenseMsg1.email" />
										</td>
										<td>
											<s:property value="#pagelicenseMsg1.tel" />
										</td>
										<td>
											<s:property value="#pagelicenseMsg1.fax" />
										</td>
										<td>
											<s:property value="#pagelicenseMsg1.zip" />
										</td>
										<td>
											<s:property value="#pagelicenseMsg1.business" />
										</td>
										<td>
											<s:property value="#pagelicenseMsg1.description" />
										</td>
										<td>
											<s:property value="#pagelicenseMsg1.startTime" />
										</td>
										<td>
											<s:property value="#pagelicenseMsg1.notAfter" />
										</td>

										<td>
											<a
												onclick="if(window.confirm('您将删除数据是否继续？')){window.location.href = 'licenseMsgAction_delete.action?cpage=${cpage}&licenseMsg.id=<s:property value="#pagelicenseMsg1.id"/>'};"
												target="main">删除</a>
											<a
												href="licenseMsgAction_toupdate.action?licenseMsg.id=<s:property value="#pagelicenseMsg1.id"/>"
												target="main">编辑</a>
										</td>
								</s:iterator>
								<tr bgcolor="green">
									<td colspan="16" align="center">
										<font color="#FFFFFF">公司信息</font>
									</td>
								</tr>

								<s:iterator id="pagelicenseMsg" value="comList"
									status="ststusfunction">
									<s:if test="#ststusfunction.index%2==1">
										<tr align="center" bgcolor="#e6f3fb"
											onmouseover="chageBgcolor(this)"
											onmouseout="outBgcolor(this,'#e6f3fb')">
									</s:if>
									<s:else>
										<tr align="center" onmouseover="chageBgcolor(this)"
											onmouseout="outBgcolor(this,'')">
									</s:else>
									<td>
										${ststusfunction.index+1}
									</td>
									<td>
										<s:property value="#pagelicenseMsg.companyName" />
									</td>
									<td>
										<s:property value="#pagelicenseMsg.shortName" />
									</td>
									<td>
										<s:property value="#pagelicenseMsg.onLineConut" />
									</td>
									<td>
										<s:property value="#pagelicenseMsg.address" />
									</td>
									<td>
										<s:property value="#pagelicenseMsg.companyUrl" />

									</td>
									<td>
										<s:property value="#pagelicenseMsg.companyPeople" />
									</td>
									<td>
										<s:property value="#pagelicenseMsg.email" />
									</td>
									<td>
										<s:property value="#pagelicenseMsg.tel" />
									</td>
									<td>
										<s:property value="#pagelicenseMsg.fax" />
									</td>
									<td>
										<s:property value="#pagelicenseMsg.zip" />
									</td>
									<td>
										<s:property value="#pagelicenseMsg.business" />
									</td>
									<td>
										<s:property value="#pagelicenseMsg.description" />
									</td>
									<td>
										<s:property value="#pagelicenseMsg.startTime" />
									</td>
									<td>
										<s:property value="#pagelicenseMsg.notAfter" />
									</td>

									<td>
										<a
											onclick="if(window.confirm('您将删除数据是否继续？')){window.location.href = 'licenseMsgAction_delete.action?licenseMsg.id=<s:property value="#pagelicenseMsg.id"/>&cpage=<s:property value="#request.cpage"/>&total=<s:property value="#request.total"/>'};"
											target="main">删除</a>
										<a
											href="licenseMsgAction_toupdate.action?licenseMsg.id=<s:property value="#pagelicenseMsg.id"/>"
											target="main">编辑</a>
										<a
											href="<%=path%>upload/file/liclib/<s:property value="#pagelicenseMsg.companyUrl"/>.lic">下载证书</a>
									</td>
									</tr>

								</s:iterator>
								<tr>

									<s:if test="errorMessage==null">
										<td colspan="16" align="right">
											第
											<font color="red"><s:property value="cpage" /> </font> /
											<s:property value="total" />
											页
											<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
												styleClass="page" theme="number" />
										</td>
									</s:if>
									<s:else>
										<td colspan="16" align="center">
											<font color="red"><s:property
													value="#request.errorMessage" /> </font>
										</td>
									</s:else>
								</tr>
							</table>
						</form>
					</div>
				</div>
				<!--添加版块 -->
				<div id="module1_2" align="center"
					style="display: none; padding-top: 20px;">
					<div align="center" id="addModule">
						<form id="addform" action="" method="post"
							enctype="multipart/form-data">
							<table class="table">
								<tr>
									<td align="right" width="45%">
										公司全称:
									</td>
									<td>
										<input id="companyName" type="text"
											name="licenseMsg.companyName" />
										<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<td align="right">
										公司简称:
									</td>
									<td>
										<input id="shortName" type="text" name="licenseMsg.shortName" />
										<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<td align="right">
										同时在线点数:
									</td>
									<td>
										<input id="onLineConut" type="text"
											name="licenseMsg.onLineConut" />
										<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<td align="right">
										一体机数:
									</td>
									<td>
										<input  id="oneMackConut" type="text"
											name="licenseMsg.oneMackConut" />
										<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<td align="right">
										LED数:
									</td>
									<td>
										<input  id="onLEDConut" type="text"
											name="licenseMsg.onLEDConut" />
										<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<td align="right">
										看板数:
									</td>
									<td>
										<input  id="oneScreenConut" type="text"
											name="licenseMsg.oneScreenConut" />
										<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<td align="right">
										公司地址:
									</td>
									<td>
										<textarea rows="2" cols="35" id="address"
											name="licenseMsg.address"></textarea>
										<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<td align="right">
										公司网址:
									</td>
									<td>
										<textarea rows="2" cols="40" id="companyUrl"
											name="licenseMsg.companyUrl"></textarea>
										<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<td align="right">
										申请人:
									</td>
									<td>
										<input id="companyPeople" type="text"
											name="licenseMsg.companyPeople" />
										<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<td align="right">
										邮件地址:
									</td>
									<td>
										<input id="email" type="text" name="licenseMsg.email" />
										<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<td align="right">
										联系电话:
									</td>
									<td>
										<input id="tel" type="text" name="licenseMsg.tel" />
										<font color="red">*</font>
									</td>

								</tr>
								<tr>
									<td align="right">
										公司传真:
									</td>
									<td>
										<input id="fax" type="text" name="licenseMsg.fax" />
										<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<td align="right">
										邮编:
									</td>
									<td>
										<input id="zip" type="text" name="licenseMsg.zip" />
										<font color="red">*</font>
									</td>
								</tr>


								<tr>
									<td align="right">
										行业:
									</td>
									<td>
										<SELECT name="licenseMsg.business" id="business">
											<option>
												请选择
											</option>
											<option>
												信息技术业
											</option>
											<option>
												制造业
											</option>
											<option>
												采掘业
											</option>
											<option>
												电力、煤气及水的产业的生产和供应业
											</option>
											<option>
												建筑房地产业
											</option>
											<option>
												交通运输、仓储业
											</option>
											<option>
												批发和零售贸易
											</option>
											<option>
												金融、保险业
											</option>
											<option>
												社会服务业
											</option>
											<option>
												教育事业
											</option>
											<option>
												医疗卫生
											</option>
											<option>
												农、林、牧、渔业
											</option>
											<option>
												传播与文化产业
											</option>
											<option>
												政府机关
											</option>
											<option>
												其他产业
											</option>
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
											name="licenseMsg.description"></textarea>
									</td>
								</tr>
								<tr>
									<td align="right">
										使用时间:
									</td>
									<td>
										<input type="text" class="Wdate" name="licenseMsg.startTime"
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
											id="notAfter"
											onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
										<font color="red">*</font>
									</td>
								</tr>

								<tr>
									<td colspan="2" align="center">
										<br>
										<input type="button" class="input" onclick="addsubmit()"
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
		</center><%@include file="/util/foot.jsp"%>
		<script type="text/javascript">
</script>
	</body>
</html>
