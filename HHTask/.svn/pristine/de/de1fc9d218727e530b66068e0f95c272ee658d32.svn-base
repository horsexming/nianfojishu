<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
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
	<head><script type="text/javascript"
	src="<%=basePath%>/javascript/DatePicker/WdatePicker.js">
</script>
		<!-- include file="/util/inc.jsp" -->
		<base href="<%=basePath%>">
		<title>公司信息管理</title>
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
	var form = document.getElementById("addform");
	var taskfile = document.getElementById("taskfile");
	var footfile = document.getElementById("footfile");
	var logoOKfile = document.getElementById("logoOKfile");
	var shhhfile = document.getElementById("shhhfile");
	var shuiyinfile = document.getElementById("shuiyinfile");
	var faviconfile = document.getElementById("faviconfile");
	var name = document.getElementById("name");
	var tel = document.getElementById("tel");
	var fax = document.getElementById("fax");
	var zip = document.getElementById("zip");
	var email = document.getElementById("email");
	var address = document.getElementById("address");
	var urlname = document.getElementById("urlname");
	var url = document.getElementById("url");
	var sbtime = document.getElementById("sbtime");
	var xbtime = document.getElementById("xbtime");
	var wxstart = document.getElementById("wxstart");
	var wxend = document.getElementById("wxend");
	var ext, idx;
	if (taskfile.value == '') {
		alert("请选择需要上传的登陆图标!");
		return false;
	} else {
		idx = taskfile.value.lastIndexOf(".");
		if (idx != -1) {
			ext = taskfile.value.substr(idx + 1).toUpperCase();
			if (ext != 'GIF' && ext != 'JPG' && ext != 'JPEG'  && ext != 'PNG'&& ext != 'ICON'&& ext != 'ICO'&& ext != 'ICON'&& ext != 'ICO') {
				alert("只能上传.gif, .jpg, .jpeg,.png,.icon,.ico,.icon,.ico类型的文件!");
				return false;
			}
		} else {
			alert("只能上传.gif, .jpg, .jpeg,.png,.icon,.ico类型的文件!");
			return false;
		}
	}
	if (footfile.value == '') {
		alert("请选择需要上传的底部图标!");
		return false;
	} else {
		idx = footfile.value.lastIndexOf(".");
		if (idx != -1) {
			ext = footfile.value.substr(idx + 1).toUpperCase();
			if (ext != 'GIF' && ext != 'JPG' && ext != 'JPEG' && ext != 'PNG'&& ext != 'ICON'&& ext != 'ICO') {
				alert("只能上传.gif, .jpg, .jpeg,.png,.icon,.ico类型的文件!");
				return false;
			}
		} else {
			alert("只能上传.gif, .jpg, .jpeg,.png,.icon,.ico类型的文件!");
			return false;
		}
	}
	
	if (shuiyinfile.value == '') {
		alert("请选择需要上传的水印图标!");
		return false;
	} else {
		idx = shuiyinfile.value.lastIndexOf(".");
		if (idx != -1) {
			ext = shuiyinfile.value.substr(idx + 1).toUpperCase();
			if (ext != 'GIF' && ext != 'JPG' && ext != 'JPEG'  && ext != 'PNG'&& ext != 'ICON'&& ext != 'ICO') {
				alert("只能上传.gif, .jpg, .jpeg,.png,.icon,.ico类型的文件!");
				return false;
			}
		} else {
			alert("只能上传.gif, .jpg, .jpeg,.png,.icon,.ico类型的文件!");
			return false;
		}
	}
	if (faviconfile.value == '') {
		alert("请选择需要上传的ico图标!");
		return false;
	} else {
		idx = faviconfile.value.lastIndexOf(".");
		if (idx != -1) {
			ext = faviconfile.value.substr(idx + 1).toUpperCase();
			if (ext != 'GIF' && ext != 'JPG' && ext != 'JPEG' && ext != 'PNG'&& ext != 'ICON'&& ext != 'ICO') {
				alert("只能上传.gif, .jpg, .jpeg,.png,.icon,.ico类型的文件!");
				return false;
			}
		} else {
			alert("只能上传.gif, .jpg, .jpeg,.png,.icon,.ico类型的文件!");
			return false;
		}
	}
	
	if (shhhfile.value == '') {
		alert("请选择需要上传的水印图标!");
		return false;
	} else {
		idx = shhhfile.value.lastIndexOf(".");
		if (idx != -1) {
			ext = shhhfile.value.substr(idx + 1).toUpperCase();
			if (ext != 'GIF' && ext != 'JPG' && ext != 'JPEG'  && ext != 'PNG'&& ext != 'ICON'&& ext != 'ICO') {
				alert("只能上传.gif, .jpg, .jpeg,.png,.icon,.ico类型的文件!");
				return false;
			}
		} else {
			alert("只能上传.gif, .jpg, .jpeg,.png,.icon,.ico类型的文件!");
			return false;
		}
	}
	if (logoOKfile.value == '') {
		alert("请选择需要上传的打印图标!");
		return false;
	} else {
		idx = logoOKfile.value.lastIndexOf(".");
		if (idx != -1) {
			ext = logoOKfile.value.substr(idx + 1).toUpperCase();
			if (ext != 'GIF' && ext != 'JPG' && ext != 'JPEG' && ext != 'PNG'&& ext != 'ICON'&& ext != 'ICO') {
				alert("只能上传.gif, .jpg, .jpeg,.png,.icon,.ico类型的文件!");
				return false;
			}
		} else {
			alert("只能上传.gif, .jpg, .jpeg,.png,.icon,.ico类型的文件!");
			return false;
		}
	}
	
	
	
	
	
   var filter = /[0-9][-]{0,}[0-9]+$/;
	if (name.value == "") {
		alert("公司名称不能为空!");
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
	} else if (sbtime.value == "") {
		alert("请输入上班时间");
		return false;
	} else if (xbtime.value == "") {
		alert("请输入下班时间");
		return false;
	} else if (wxstart.value == "") {
		alert("请输入午休开始时间");
		return false;
	} else if (wxend.value == "") {
		alert("请输入午休结束时间");
		return false;
	} else {
		form.action = "companyInfoAction_add.action";
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
					<font color="#ffffff">公司信息管理</font>
				</div>
				<div
					style="border: solid 1px #0170b8; width: 1000px; font-family: 微软雅黑; margin-top: 10px;"
					align="left">
					<div
						style="padding-top: 10px; padding-left: 20px; border-bottom: solid #0170b8 1px;">
						<table>
							<tr>
								<td align="center">
									<div id="module1" class="tag_2" onclick="chageModule(this,'1')">
										公司信息列表
									</div>
								</td>
								<td align="center">
									<div id="module2" class="tag_1" onclick="chageModule(this,'2')">
										添加公司信息
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
					<font color="red"><s:property value="#request.successMessage" /> </font>
				</div>
				<div id="module1_1" align="center">
					<div id="bangding">
						<form action="companyInfoAction_showCompanyInfos.action" method="post"
							style="margin: 0px">
							<br>
							<table class="table">
								<tr>
									
									<td align="right">
										公司名称:
									</td>
									<td>
										<input type="text" name="companyInfo.name" />
									</td>
									
									<td align="right">
										公司电话:
									</td>
									<td>
										<input type="text" name="companyInfo.tel" />
									</td>
									<td rowspan="2">
										<input type="submit" value="查询"
											style="width: 100px; height: 50px;" />
									</td>
								</tr>
								<tr>
									<td align="right">
										公司邮编:
									</td>
									<td>
										<input type="text" name="companyInfo.zip" />
									</td>
									<td align="right">
										公司地址:
									</td>
									<td>
										<input type="text" name="companyInfo.address" />
									</td>

								</tr>
							</table>
						</form>
						<hr>
						<form action="companyInfoAction_showCompanyInfos.action"
							method="post" target="main" style="margin: 0px">
							<input type="hidden" name="id" value="${id}">
							<table class="table">
								<tr align="center" bgcolor="#c0dcf2"
									style="height: 40px; font-weight: bold;">
									<td>
										序号
									</td>

									<td>
										名称
									</td>
									<td>
										英文名称
									</td>
									<td>
										简称
									</td>
									<td>
										电话
									</td>
									<td>
										地址
									</td>

									<td>
										邮箱
									</td>
									<td>
										传真
									</td>
									<td>
										邮编
									</td>
									<td>
										网址名称
									</td>
									<td>
										网址
									</td>

									<td>
										操作
									</td>
								</tr>

								<s:iterator id="pageCompanyinfo" value="comList"
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
										<s:property value="#pageCompanyinfo.name" />
									</td>
									<td>
										<s:property value="#pageCompanyinfo.englishName" />
									</td>
									<td>
										<s:property value="#pageCompanyinfo.shortName" />
									</td>
									<td>
										<s:property value="#pageCompanyinfo.tel" />
									</td>
									<td>
										<s:property value="#pageCompanyinfo.address" />

									</td>
									<td>
										<s:property value="#pageCompanyinfo.email" />
									</td>
									<td>
										<s:property value="#pageCompanyinfo.fax" />
									</td>
									<td>
										<s:property value="#pageCompanyinfo.zip" />
									</td>
									<td>
										<s:property value="#pageCompanyinfo.urlname" />
									</td>
									<td>
										<s:property value="#pageCompanyinfo.url" />
									</td>

									<td>
										  <a 
										onclick="if(window.confirm('您将删除数据是否继续？')){window.location.href = 'companyInfoAction_delete.action?companyInfo.id=<s:property value="#pageCompanyinfo.id"/>&cpage=<s:property value="#request.cpage"/>&total=<s:property value="#request.total"/>'};"
											target="main">删除</a>
										<a
											href="companyInfoAction_toupdate.action?companyInfo.id=<s:property value="#pageCompanyinfo.id"/>
												&cpage=<s:property value="#request.cpage"/>&total=<s:property value="#request.total"/>"
											target="main">编辑</a>
											<a
											href="companyInfoAction_showImg.action?companyInfo.id=<s:property value="#pageCompanyinfo.id"/>
												&cpage=<s:property value="#request.cpage"/>&total=<s:property value="#request.total"/>"
											target="main">图标显示</a>
									</td>
									</tr>

								</s:iterator>
								<tr>
								
									<s:if test="errorMessage==null">
										<td colspan="12" align="right">
											第
											<font color="red"><s:property value="cpage" /> </font> /
											<s:property value="total" />
											页
											<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
												styleClass="page" theme="number" />
									</td>
									</s:if>
									<s:else>
									<td colspan="12" align="center">
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
									<td align="right">
										上传登陆图标:
									</td>
									<td>
										<input  type="file" name="taskfile" id="taskfile">
										<font color="red">*(980*150)</font>
									</td>
								</tr>
								<tr>
									<td align="right">
										上传底部图标
									</td>
									<td>
										<input  type="file" name="footfile" id="footfile">
										<font color="red">*(257*35)</font>
									</td>
								</tr>
								<tr>
									<td align="right">
										上传打印图标:
									</td>
									<td>
										<input  type="file" name="logoOKfile" id="logoOKfile">
										<font color="red">*(382*382)</font>
									</td>
								</tr>
								<tr>
									<td align="right">
										上传图表图标
									</td>
									<td>
										<input  type="file" name="shhhfile" id="shhhfile">
										<font color="red">*748*787</font>
									</td>
								</tr>
								<tr>
									<td align="right">
										上传水印图标:
									</td>
									<td>
										<input  type="file" name="shuiyinfile" id="shuiyinfile">
										<font color="red">*(295*90)</font>
									</td>
								</tr>
								<tr>
									<td align="right">
										上传ico图标
									</td>
									<td>
										<input  type="file" name="faviconfile" id="faviconfile">
										<font color="red">*(16*16)</font>
									</td>
								</tr>
								<tr>
									<td align="right">
										logo图标:
									</td>
									<td>
										<input  type="file" name="logofile" id="logofile">
										<font color="red">*(295*90)</font>
									</td>
								</tr>
								<tr>
									<td align="right">
										公司名称:
									</td>
									<td>
										<input  id="name" type="text"
											name="companyInfo.name" />
										<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<td align="right">
										英文名称:
									</td>
									<td>
										<input  id="englishName" type="text"
											name="companyInfo.englishName" />
										<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<td align="right">
										公司简称:
									</td>
									<td>
										<input  id="name" type="text"
											name="companyInfo.shortName" />
										<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<td align="right">
										公司电话:
									</td>
									<td>
										<input id="tel" type="text"
											name="companyInfo.tel" />
										<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<td align="right">
										公司开户银行:
									</td>
									<td>
										<input id="accountBank" type="text"
											name="companyInfo.accountBank" />
										<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<td align="right">
										公司账户:
									</td>
									<td>
										<input id="accountnum" type="text"
											name="companyInfo.accountnum" />
										<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<td align="right">
										公司传真:
									</td>
									<td>
										<input id="fax" type="text"
											name="companyInfo.fax" />
										<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<td align="right">
										公司邮编:
									</td>
									<td>
										<input id="zip" type="text"
											name="companyInfo.zip" />
										<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<td align="right">
										公司邮箱:
									</td>
									<td>
										<input id="email" type="text"
											name="companyInfo.email" />
										<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<td align="right">
										上班时间:
									</td>
									<td>
										<input id="sbtime" type="text"
										class="Wdate" onClick="WdatePicker({dateFmt:'HH:mm',skin:'whyGreen'})"
											name="companyInfo.sbtime" />
										<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<td align="right">
										下班时间:
									</td>
									<td>
										<input id="xbtime" type="text"
										class="Wdate" onClick="WdatePicker({dateFmt:'HH:mm',skin:'whyGreen'})"
											name="companyInfo.xbtime" />
										<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<td align="right">
										午休开始时间:
									</td>
									<td>
										<input id="wxstart" type="text"
										class="Wdate" onClick="WdatePicker({dateFmt:'HH:mm',skin:'whyGreen'})"
											name="companyInfo.wxstart" />
										<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<td align="right">
										午休结束时间:
									</td>
									<td>
										<input id="wxend" type="text"
										class="Wdate" onClick="WdatePicker({dateFmt:'HH:mm',skin:'whyGreen'})"
											name="companyInfo.wxend" />
										<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<td align="right">
										公司坐标经度:
									</td>
									<td>
										<input id="email" type="text"
											name="companyInfo.jingdu" />
										<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<td align="right">
										公司坐标纬度:
									</td>
									<td>
										<input id="email" type="text"
											name="companyInfo.weidu" />
										<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<td align="right">
										有效签到范围:
									</td>
									<td>
										<input id="email" type="text"
											name="companyInfo.fanwei" />
										<font color="red">*(米)</font>
									</td>
								</tr>
								<tr>
									<td align="right">
										有效签到范围:
									</td>
									<td>
										<input id="email" type="text"
											name="companyInfo.fanwei" />
										<font color="red">*(米)</font>
									</td>
								</tr>
								<tr>
									<td align="right">
										公司地址:
									</td>
									<td>
										<textarea  rows="8" cols="35" id="address"
											name="companyInfo.address"></textarea>
										<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<td align="right">
										公司外部IP:
									</td>
									<td>
										<textarea  rows="8" cols="35" id="outIp"
											name="companyInfo.outIp"></textarea>
										<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<td align="right">
										网址名称:
									</td>
									<td>
										<textarea rows="8" cols="35" id="urlname"
											name="companyInfo.urlname"></textarea>
										<font color="red">*</font>
									</td>
								</tr>


								<tr>
									<td align="right">
										公司网址:
									</td>
									<td>
										<textarea rows="5" cols="40" id="url"
											name="companyInfo.url"></textarea>
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
				<%-- 
						<div id="module1_3"
							style="font-weight: bold; font-family: 微软雅黑; display: none">
							<div align="center" id="addModule">
								修改${moduleFunction.functionName}
								<div align="center">
									<form id="updateMf"
										action="ModuleFunctionAction!updateMf.action" method="post"
										style="margin: 0px; padding: 0px;"
										enctype="multipart/form-data">
										<input type="hidden" name="id" value="${moduleFunction.id}">
										<input type="hidden" name="moduleFunction.id"
											value="${moduleFunction.id}">
										<input type="hidden" name="moduleFunction.fatherId"
											value="${moduleFunction.fatherId}">
										<input type="hidden" name="moduleFunction.belongLayer"
											value="${moduleFunction.belongLayer}">
										<table class="table">
											<tr>
												<td align="right">
													功能名称:
												</td>
												<td>
													<input type="text" name="moduleFunction.functionName"
														value="${moduleFunction.functionName}" />
													<font color="red">*</font>
												</td>
											</tr>
											<tr>
												<td align="right">
													功能介绍:
												</td>
												<td>
													<div style="float: left;">
														<textarea rows="8" cols="35"
															name="moduleFunction.functionIntro">${moduleFunction.functionIntro}</textarea>
														<font color="red">*</font>
													</div>
													<s:if test="imageName!=''">
														<div style="border: solid #000 1px; float: left;">
															<s:if test="moduleFunction.bgColor!=''">
																<img alt="${moduleFunction.functionName}"
																	style="background-color:${moduleFunction.bgColor}"
																	src="<%=basePath%>upload/file/sysImages/${moduleFunction.imageName}">
															</s:if>
															<s:else>
																<img alt="${moduleFunction.functionName}"
																	src="<%=basePath%>upload/file/sysImages/${moduleFunction.imageName}">
															</s:else>
														</div>
													</s:if>
												</td>
											</tr>
											<tr>
												<td align="right">
													功能链接:
												</td>
												<td>
													<textarea rows="5" cols="40"
														name="moduleFunction.functionLink">${moduleFunction.functionLink}</textarea>
													<font color="red">*</font>
												</td>
											</tr>
											<tr>
												<td align="right">
													图标:
												</td>
												<td>
													<input type="file" name="attachment" />
												</td>
											</tr>
											<tr>
												<td align="right">
													背景色:
												</td>
												<td>
													<input type="text" name="moduleFunction.bgColor"
														value="${moduleFunction.bgColor}" />
												</td>
											</tr>
											<tr>
												<td align="right">
													新开页面:
												</td>
												<td>
													<s:if test="moduleFunction.targetNewPage=='no'">
														<input type="radio" name="moduleFunction.targetNewPage"
															value="yes">
													是
													<input type="radio" name="moduleFunction.targetNewPage"
															value="no" checked="checked">
													否
													</s:if>
													<s:else>
														<input type="radio" name="moduleFunction.targetNewPage"
															value="yes" checked="checked">
													是
													<input type="radio" name="moduleFunction.targetNewPage" value="no">
													否
													</s:else>
													<font color="red">*</font>
												</td>
											</tr>
											<tr>
												<td colspan="2" align="center">
													<br>
													<input type="submit" style="width: 100px; height: 50"
														value="修改">
													<input type="reset" style="width: 100px; height: 50px;"
														value="重置">
												</td>
											</tr>
										</table>
									</form>
								</div>
							</div>

						</div>
						<div id="module1_4" align="center"
							style="display: none; padding-top: 20px;">
							<form
								action="ModuleFunctionAction!delMf.action?id=${moduleFunction.id}"
								method="post" target="main">
								<p>
									&nbsp;&nbsp;&nbsp;&nbsp;该功能将删除${moduleFunction.functionName}模块！
									<br />
									<br />
								</p>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="submit" value="删除"
									style="width: 70px; height: 50px"
									onClick="return window.confirm('确定要删除${moduleFunction.functionName}模块吗?')">
							</form>
						</div>

					</div>
					 --%>
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
