<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="checkAdmin.jsp"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<%@include file="/util/inc.jsp"%>
		<base href="<%=basePath%>">
		<title>功能管理</title>
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

var XMLHttpReq;
//创建XMLHttpRequest对象 
function createXMLHttpRequest() {
	if (window.XMLHttpRequest) {//Mozilla 浏览器
		XMLHttpReq = new XMLHttpRequest();
	} else {
		if (window.ActiveXObject) { // IE浏览器
			try {
				XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {
				}
			}
		}
	}
}
//发送请求函数 (url=请求地址 ,obj=指定响应函数)
function sendRequest(url, obj) {
	createXMLHttpRequest();
	var url = url;
	url = encodeURI(encodeURI(url));
	XMLHttpReq.open("post", url, true);
	XMLHttpReq.onreadystatechange = obj;//指定响应函数
	XMLHttpReq.send(null);// 发送请求
}

var select;
var select2
//为select赋值(返回信息为包含|的字符串)
function findAllDept() {
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
			var message = XMLHttpReq.responseText;
			var dept = message.split("|");
			for ( var i = 0; i < dept.length - 1; i++) {
				var isPresence = jsSelectIsExitItem(select, dept[i]);
				if (isPresence == false) {
					var optionItem = new Option(dept[i], dept[i]);
                    var optionItem2 = new Option(dept[i], dept[i]);
					select.options.add(optionItem);
                    select2.options.add(optionItem2);

				}
			}
            var tinyselect = $(".tinyselect");
            $("#dept").tinyselect();
            $("#dept2").tinyselect();
		} else { //页面不正常
			window.alert("页面异常,请重试!");
		}
	}
}

// 判断select选项中 是否存在Value和查出的相同的Item   
function jsSelectIsExitItem(objSelect, objItemValue) {
	var isPresence = false;
	for ( var i = 0; i < objSelect.options.length; i++) {
		if (objSelect.options[i].value == objItemValue) {
			isPresence = true;
			break;
		}
	}
	return isPresence;
}

//生成下拉列表(默认是查询部门)
function createDept(selectName, actionUrl) {
	if (actionUrl == null) {
		actionUrl = "DeptNumberAction!finAllDeptNumberForSetlect.action";
	}
	select = document.getElementById(selectName);
    select2 = document.getElementById("dept2");
	sendRequest(actionUrl, findAllDept);


}
</script>



		<script type="text/javascript">
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

function chageAllCheck(obj) {
	var inputs = document.getElementsByTagName("input");
	for ( var i = 0; i < inputs.length; i++) {
		if (inputs[i].type == "checkbox") {
			var checkBox = inputs[i];
			if (checkBox.checked != obj.checked) {
				checkBox.checked = obj.checked;
				if (checkBox.id != "checkAll2" && checkBox.id != "checkAll") {
					chageNum(checkBox);
				}
			}
		}
	}
}
var num = "${count}";
if (num == "") {
	num = 0;
}
function chageNum(obj) {
	var check = obj;
	var checkAll = document.getElementById("checkAll");
	if (check.checked == true) {
		var inputs = document.getElementsByTagName("input");
		var status = true;
		for ( var i = 0; i < inputs.length; i++) {
			if (inputs[i].type == "checkbox") {
				var checkBox = inputs[i];
				if (checkBox.id != "checkAll") {
					if (checkBox.checked == false) {
						status = false;
						break;
					}
				}
			}
		}
		if (status == true) {
			checkAll.checked = true;
		}
		num++;
	} else if (num == 0 && check.checked == false) {
		num = 0;
	} else {
		if (checkAll.checked == true ) {
			checkAll.checked = false;
		}
		num--;
	}
	// document.getElementById("peopleLabel").innerHTML = num;
	// document.getElementById("peopleLabel2").innerHTML = num;
	// document.getElementById("propleText").value = num;
}

function chageForm1(form, status) {
	var functionName = document.getElementById("functionName");
	if (functionName.value == "") {
		alert("功能名称不能为空!");
		return false;
	} else {
		form.action = "ModuleFunctionAction!addMf.action?pageStatus=" + status;
		form.submit();
	}
}

function updateForm() {
	$.ajax( {
		type : "POST",
		url : "ModuleFunctionAction!updateMf.action",
		dataType : "json",
		data : $("#updateMf").serialize(),
		success : function(msg) {
			if (msg.success) {
				alert(msg.message);
				//加载树形
		parent.parent.test();
	} else {
		alert(msg.message);
	}
}
	});
}

$(function() {
	var pageStatus = "${pageStatus}";
    if (pageStatus == "cancel"){
		var module6=document.getElementById("module6_2");
        chageModule(module6,'6');
    }
	if (pageStatus == "add") {
		//添加节点
		parent.id = "${sessionScope.moduleFunction.id}";
		parent.pId = "${sessionScope.moduleFunction.fatherId}";
		parent.name = "${sessionScope.moduleFunction.functionName}";
		parent.addzTree();
	} else if (pageStatus == "update") {
		//更新节点
		parent.name = "${sessionScope.moduleFunction.functionName}";
		parent.updatezTree();
	} else if (pageStatus == "delete") {
		//删除节点
		parent.delzTree();
	}

});
</SCRIPT>
		<script type="text/javascript">
function isreset(obj) {
	window.location.href = "ModuleFunctionAction!findMfById1.action?id=" + obj;
}
</script>

		<link rel="stylesheet" href="css/css.css" type="text/css"></link>
	</head>

	<body bgcolor="#ffffff" onload="createDept('dept')">
		<center>
			<div style="width: 1000px;">
				<div id="xitong" align="center"
					style="width: 100%; height: 31px; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; background: url('admin/images/admin_title.jpg');">
					<font color="#ffffff">${moduleFunction.functionName}</font>
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
										绑定用户
									</div>
								</td>
								<td align="center">
									<div id="module6_2" class="tag_1" onclick="chageModule(this,'6')">
										取消绑定
									</div>
								</td>
								<s:if
									test="#session.adminusers==null&&#session.admin.type=='system'">
									<td align="center">
										<div id="module2" class="tag_1"
											onclick="chageModule(this,'2')">
											添加功能
										</div>
									</td>
									<td align="center">
										<div id="module3" class="tag_1"
											onclick="chageModule(this,'3')">
											修改功能详细
										</div>
									</td>
									<td align="center">
										<div id="module4" class="tag_1"
											onclick="chageModule(this,'4')">
											删除功能
										</div>
									</td>
									<td align="center">
										<div id="module5" class="tag_1"
											onclick="isreset(${moduleFunction.id})">
											自拟定位置
										</div>
									</td>
									<s:iterator value="moduleFunction" status="st" id="mf">
										<s:if test="#mf.functionLink!=''">
											<s:if test="#mf.isSubModule!='true'">
												<td align="center">
													<div id="module6" class="tag_1"
														onclick="chageModule(this,'5')">
														子模块
													</div>
												</td>
											</s:if>
										</s:if>
									</s:iterator>
								</s:if>
								<s:elseif
									test="#session.adminusers==null&&#session.admin.type=='super'">
									<td align="center">
										<div id="module5" class="tag_1"
											onclick="isreset(${moduleFunction.id})">
											自拟定位置
										</div>
									</td>
								</s:elseif>
								<s:elseif test="admin.type==system">
									<td align="center">
										<div id="module5" class="tag_1"
											onclick="isreset(${moduleFunction.id})">
											自拟定位置
										</div>
									</td>
								</s:elseif>
							</tr>
						</table>
					</div>
					<div>
						<div align="center">
							<font color="red">${successMessage}</font>
						</div>
						<div id="module1_1" align="center">
							<div id="bangding">
								<form action="ModuleFunctionAction!findUsersByCondition.action"
									method="post" style="margin: 0px" id="formDeletebinDing">
									<input type="hidden" value="${moduleFunction.id}" name="id">
									<br>
									<table class="table">
										<tr>
											<td align="right">
												姓名:
											</td>
											<td>
												<input type="text" name="user.name" value="${user.name}"/>
											</td>
											<td align="right">
												部门:
											</td>
											<td>
												<select name="user.dept" id="dept" style="width: 155px;">
                                                    <option value="${user.dept}">${user.dept}</option>
													<option value=""></option>
												</select>
											</td>
											<td>
												<font style="margin-left: 10px">在职状态：</font>
												<select name="user.onWork" style="width: 80px;" >
													<option value="${user.onWork}">${user.onWork}</option>
                                                    <option value=""></option>
													<option value="实习">
														实习
													</option>
													<option value="试用">
														试用
													</option>
													<option value="在职">
														在职
													</option>
													<option value="离职中">
														离职中
													</option>
													<option value="离职">
														离职
													</option>
													<option value="退休">
														退休
													</option>
													<option value="内退">
														内退
													</option>
												</select>
											</td>
										</tr>
										<tr>
											<td align="right">
												卡号:
											</td>
											<td>
												<input type="text" name="user.cardId" value="${user.cardId}" />
											</td>
											<td align="right">
												工号:
											</td>
											<td>
												<input type="text" name="user.code"  value="${user.code}"/>
											</td>
											<td align="center">
												<input type="submit" value="查询"
													style="width: 100px; height: 30px;" />
											</td>
										</tr>
									</table>
								</form>
								<hr>
								<form action="ModuleFunctionAction!AddbinDingUsers.action"
									method="post" target="main" style="margin: 0px">
									<input type="hidden" name="id" value="${id}">
									<table style="width: 980px; border-collapse: collapse;"
										border="0" align="center">
										<tr>
											<td align="right" colspan="7">
												<input type="submit" value="绑定"
													   style="width: 60px; height: 40px;" align="top">
												<br>
												<br>
											</td>
										</tr>
										<tr align="center" bgcolor="#c0dcf2"
											style="height: 40px; font-weight: bold;">
											<td>
												序号
											</td>
											<td>
												工号
											</td>
											<td>
												卡号
											</td>
											<td>
												姓名
											</td>
											<td>
												部门
											</td>
											<td>
												职位
											</td>
											<td>
												<input type="checkbox" id="checkAll"
													onclick="chageAllCheck(this)">
												全选
											</td>
										</tr>

										<s:iterator id="users" value="bangUserList"
											status="ststusfunction">
											<s:if test="#ststusfunction.last">
												<tr bgcolor="green">
													<td colspan="7"
														style="font-family: 微软雅黑; font-weight: bold;"
														align="center">
														<font color="#ffffff"> 未绑定用户</font>
													</td>
												</tr>
											</s:if>
										</s:iterator>

										<s:iterator id="users" value="userList"
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
												<s:if test="#ststusfunction.index%2==1">
													<font>
												</s:if>
												<s:else>
													<font color="#c0dcf2">
												</s:else>
												<s:property value="#ststusfunction.index+1" />
												</font>
											</td>
											<td>
												${users.code}
											</td>
											<td>
												${users.cardId}
											</td>
											<td>
												${users.name}
											</td>
											<td>
												${users.dept}
											</td>
											<td>
												${users.duty}
											</td>
											<td>
												<input type="checkbox"
													id="user<s:property value="#ststusfunction.index"/>"
													name="usersId" value="${users.id}" onclick="chageNum(this)" />
											</td>
											</tr>
										</s:iterator>
										<tr>
											<td colspan="7" align="center">
												<font color="red">${errorMessage}</font>
											</td>
										</tr>
										<tr>
											<s:if test="errorMessage==null">
												<td colspan="11" align="right">
													第
													<font color="red"><s:property value="cpage" /> </font> /
													<s:property value="total" />
													页
													<span class="page">
                                                    <s:if test="total!=1">
														<input type="button" value="首页" style="width: 40px;height: 20px"  class="input" onclick="firstpage(this)" />
														<input type="button" value="下一页" style="width: 60px;height: 20px"  class="input" onclick="nextpage(this)" />
														<input type="button" value="末页" style="width: 40px;height: 20px"  class="input" onclick="endpage(this)" />
                                                        <%--<a href="ModuleFunctionAction!findMfById.action?id=${id}&amp;cpage2=1&amp;total2=${total2}&amp;pageStatus=cancel">[首页]</a>--%>
                                                        <%--<a href="ModuleFunctionAction!findMfById.action?id=${id}&amp;cpage2=${cpage2+1}&amp;total2=${total2}&amp;pageStatus=cancel">[下一页]</a>--%>
                                                        <%--<a href="ModuleFunctionAction!findMfById.action?id=${id}&amp;cpage2=${total2}&amp;total2=${total2}&amp;pageStatus=cancel">[末页]</a>--%>
                                                     </s:if>
													<%--<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"--%>
														<%--styleClass="page" theme="number" />--%>
											</s:if>
											<s:else>
												<td colspan="11" align="center" style="color: red">
											</s:else>
											</td>
										</tr>
										<tr>
											<td align="right" colspan="7">
												<br>
												<%--<font color="red">共选择 <label id="peopleLabel">--%>
														<%--${count}--%>
													<%--</label>人</font>--%>
												<input type="submit" value="绑定"
													style="width: 60px; height: 40px;" align="top">
												<br>
												<br>
												<br>
												<br>
											</td>
										</tr>
									</table>
								</form>
							</div>
						</div>
						<div id="module1_2" align="center"
							style="display: none; padding-top: 20px;">
							<div align="center" id="addModule">
								<form id="addMf" action="" method="post"
									enctype="multipart/form-data">
									<input type="hidden" name="id" value="${moduleFunction.id}">
									<table class="table">
										<tr>
											<td align="right">
												功能名称:
											</td>
											<td>
												<input id="functionName" type="text"
													name="moduleFunction.functionName" />
												<font color="red">*</font>
											</td>
										</tr>
										<tr>
											<td align="right">
												功能名称(英文):
											</td>
											<td>
												<input type="text" name="moduleFunction.englishName" />
												<font color="red">*</font>
											</td>
										</tr>
										<tr>
											<td align="right">
												功能介绍:
											</td>
											<td>
												<textarea rows="8" cols="35"
													name="moduleFunction.functionIntro"></textarea>
												<font color="red">*</font>
											</td>
										</tr>
										<tr>
											<td align="right">
												清新图标:
											</td>
											<td>
												<input type="file" name="qxImage" />
											</td>
										</tr>
										<tr>
											<td align="right">
												炫酷图标:
											</td>
											<td>
												<input type="file" name="xkImage" />
											</td>
										</tr>
										<tr>
											<td align="right">
												小图标:
											</td>
											<td>
												<input type="file" name="smallImage" />
											</td>
										</tr>

										<tr>
											<td align="right">
												导航默认图标:
											</td>
											<td>
												<input type="file" name="mrImage" />
											</td>
										</tr>
										<tr>
											<td align="right">
												导航变色图标:
											</td>
											<td>
												<input type="file" name="bsImage" />
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
												功能链接:
											</td>
											<td>
												<textarea rows="5" cols="40"
													name="moduleFunction.functionLink"></textarea>
												<font color="red">*</font>
											</td>
										</tr>
										<tr>
											<td align="right">
												新开页面:
											</td>
											<td>
												<input type="radio" name="moduleFunction.targetNewPage"
													value="yes">
												是
												<input type="radio" name="moduleFunction.targetNewPage"
													value="no" checked="checked">
												否
												<font color="red">*</font>
											</td>
										</tr>
										<tr>
											<td align="right">
												只显示于手机端:
											</td>
											<td>
												<input type="radio" name="moduleFunction.phoneShow"
													value="yes">
												是
												<input type="radio" name="moduleFunction.phoneShow"
													value="no" checked="checked">
												否
												<font color="red">*</font>
											</td>
										</tr>
										<!-- 
										<tr>
											<td align="right">
												时间控制:
											</td>
											<td>
												<input type="radio" name="moduleFunction.timeControl"
													value="yes"
													onclick="javascript:document.getElementById('dateTimeTr').style.display='block';">
												是
												<input type="radio" name="moduleFunction.timeControl"
													value="no" checked="checked"
													onclick="javascript:document.getElementById('dateTimeTr').style.display='none';">
												否
												<font color="red">*</font>
											</td>
										</tr> -->
										<tr id="dateTimeTr" style="display: none;">
											<td align="right">
												时间:
											</td>
											<td>
												从
												<input class="Wdate" type="text"
													name="moduleFunction.stratDateTime"
													onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" />
												到
												<input class="Wdate" type="text"
													name="moduleFunction.endDateTime"
													onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" />
												<br />
												本月
											</td>
										</tr>
										<tr>
											<td colspan="2" align="center">
												<br>
												<input type="button" style="width: 100px; height: 50"
													onclick="chageForm1(this.form,'lower')" value="添加下层">
												<input type="button" style="width: 100px; height: 50"
													onclick="chageForm1(this.form,'same')" value="添加同层">
												<input type="reset" style="width: 100px; height: 50px;"
													value="重置">
											</td>
										</tr>
									</table>
								</form>
							</div>

						</div>
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
													功能名称(英文):
												</td>
												<td>
													<input type="text" name="moduleFunction.englishName"
														value="${moduleFunction.englishName}" />
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
													清新图标:
												</td>
												<td>
													<input type="file" name="qxImage" />
													<s:if test="moduleFunction.qximageName!=null">
														<img alt="${moduleFunction.functionName}"
															style="width:180px; height:140px; background-color:${moduleFunction.bgColor}"
															src="<%=basePath%>upload/file/sysImages/${moduleFunction.qximageName}">
													</s:if>
												</td>
											</tr>
											<tr>
												<td align="right">
													炫酷图标:
												</td>
												<td>
													<input type="file" name="xkImage" />
													<s:if test="moduleFunction.imageName!=null">
														<img alt="${moduleFunction.functionName}"
															style="width: 350px; height: 100px;"
															src="<%=basePath%>upload/file/sysImages/${moduleFunction.imageName}">
													</s:if>
												</td>
											</tr>
											<tr>
												<td align="right">
													小图标:
												</td>
												<td>
													<input type="file" name="smallImage" />
													<s:if test="moduleFunction.smallImageName!=null">
														<img alt="${moduleFunction.functionName}"
															style="width: 150px; height: 120px;"
															src="<%=basePath%>upload/file/sysImages/${moduleFunction.smallImageName}">
													</s:if>
												</td>
											</tr>
											<tr>
												<td align="right">
													导航默认图标:
												</td>
												<td>
													<input type="file" name="mrImage" />
													<s:if test="moduleFunction.dhNoColor!=null">
														<img alt="${moduleFunction.functionName}"
															style="width: 150px; height: 120px;"
															src="<%=basePath%>upload/file/sysImages/${moduleFunction.dhNoColor}">
													</s:if>
												</td>
											</tr>
											<tr>
												<td align="right">
													导航变色图标:
												</td>
												<td>
													<input type="file" name="bsImage" />
													<s:if test="moduleFunction.dhHasColor!=null">
														<img alt="${moduleFunction.functionName}"
															style="width: 150px; height: 120px;"
															src="<%=basePath%>upload/file/sysImages/${moduleFunction.dhHasColor}">
													</s:if>
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
													<input type="radio" name="moduleFunction.targetNewPage"
															value="no">
													否
													</s:else>
													<font color="red">*</font>
												</td>
											</tr>
											<tr>
												<td align="right">
													只显示于手机端:
												</td>
												<td>
													<s:if test="moduleFunction.phoneShow=='yes'">
														<input type="radio" name="moduleFunction.phoneShow"
															value="yes" checked="checked">
													是
													<input type="radio" name="moduleFunction.phoneShow"
															value="no">
													否
													</s:if>
													<s:else>
														<input type="radio" name="moduleFunction.phoneShow"
															value="yes">
													是
													<input type="radio" name="moduleFunction.phoneShow"
															value="no" checked="checked">
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
						<div id="module1_5" align="center"
							style="display: none; padding-top: 20px;">
							<br />
							<%--<form id="updateSubMf" action="SubModuleAction_addSubModule.action?id=${moduleFunction.id}"--%>
							<%--method="post" style="margin: 0px; padding: 0px;">--%>
							<form id="updateSubMf"
								action="ModuleFunctionAction!addMf.action?pageStatus=sub&id=${moduleFunction.id}"
								method="post" style="margin: 0px; padding: 0px;">
								<table class="table">
									<tr>
										<th colspan="2">
											添加子模块
										</th>

									</tr>
									<tr>
										<td align="right">
											子功能名称:
										</td>
										<td>
											<input type="text" name="moduleFunction.functionName" />
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
													name="moduleFunction.functionIntro"></textarea>
												<font color="red">*</font>
											</div>
										</td>
									</tr>
									<tr>
										<td align="right">
											功能链接:
										</td>
										<td>
											<textarea rows="5" cols="40"
												name="moduleFunction.functionLink"></textarea>
											<font color="red">*</font>
										</td>
									</tr>
									<tr>
										<td colspan="2" align="center">
											<br>
											<input type="submit" style="width: 100px; height: 50"
												value="添加">
											<input type="reset" style="width: 100px; height: 50px;"
												value="重置">
										</td>
									</tr>
								</table>
							</form>
							<br/>
							<form id="chang2SubMf"
								action="ModuleFunctionAction!chang2SubModuleFunction.action?id=${moduleFunction.id}"
								method="post" style="margin: 0px; padding: 0px;">
								<table class="table">
									<tr>
										<th colspan="2">
											转换为子模块
										</th>

									</tr>
									<tr>
										<td align="right">
											父模块名称:
										</td>
										<td>
											<input type="text" name="fatherMfname" />
											<font color="red">*</font>
										</td>
									</tr>
									<tr>
										<td colspan="2" align="center">
											<br>
											<input type="submit" style="width: 150px; height: 50"
												value="收缩至指定模块">
<%--											<input type="submit" style="width: 100px; height: 50"--%>
<%--												value="收缩至上层">--%>
											<input type="reset" style="width: 100px; height: 50px;"
												value="重置">
										</td>
									</tr>
								</table>
							</form>

						</div>
						<div id="module1_6" align="center"style="display: none; padding-top: 20px;">
							<div id="bangding2">
								<form action="ModuleFunctionAction!findhadBindingUsersByCondition.action"
									  method="post" style="margin: 0px" id="formfindhadBinding"/>
									<input type="hidden" value="${moduleFunction.id}" name="id">
									<br>
									<table class="table">
										<tr>
											<td align="right">
												姓名:
											</td>
											<td>
												<input type="text" name="user.name" value="${user.name}" />
											</td>
											<td align="right">
												部门:
											</td>
											<td>
												<select name="user.dept" id="dept2" style="width: 155px;">
													<option value="${user.dept}">${user.dept}</option>
													<option value=""></option>
												</select>
											</td>
											<td>
												<font style="margin-left: 10px">在职状态：</font>
												<select name="user.onWork" style="width: 80px;">
                                                    <option value="${user.onWork}">${user.onWork}</option>
													<option value=""></option>
													<option value="实习">
														实习
													</option>
													<option value="试用">
														试用
													</option>
													<option value="在职">
														在职
													</option>
													<option value="离职中">
														离职中
													</option>
													<option value="离职">
														离职
													</option>
													<option value="退休">
														退休
													</option>
													<option value="内退">
														内退
													</option>
												</select>
											</td>
										</tr>
										<tr>
											<td align="right">
												卡号:
											</td>
											<td>
												<input type="text" name="user.cardId" value="${user.cardId}"/>
											</td>
											<td align="right">
												工号:
											</td>
											<td>
												<input type="text" name="user.code" value="${user.code}" />
											</td>
											<td align="center">
												<input type="submit" value="查询"
													   style="width: 100px; height: 30px;" />
											</td>
										</tr>
									</table>
								</form>
								<hr>
								<form action="ModuleFunctionAction!DeletebinDingUsers.action"
									  method="post" target="main" style="margin: 0px" >
									<input type="hidden" name="id" value="${id}">
									<table style="width: 980px; border-collapse: collapse;"
										   border="0" align="center">
										<tr>
											<td align="right" colspan="7">
												<input type="submit" value="取消绑定"
													   style="width: 80px; height: 40px;" align="top">
												<br>
												<br>
											</td>
										</tr>
										<tr align="center" bgcolor="#c0dcf2"
											style="height: 40px; font-weight: bold;">
											<td>
												序号
											</td>
											<td>
												工号
											</td>
											<td>
												卡号
											</td>
											<td>
												姓名
											</td>
											<td>
												部门
											</td>
											<td>
												职位
											</td>
											<td>
												<input type="checkbox" id="checkAll_2"
													   onclick="chageAllCheck(this)">
												全选
											</td>
										</tr>

										<s:iterator id="users" value="bangUserList"
													status="ststusfunction">
											<s:if test="#ststusfunction.first">
												<tr bgcolor="green">
													<td colspan="7"
														style="font-family: 微软雅黑; font-weight: bold;"
														align="center">
														<font color="#ffffff"> 已绑定用户</font>
													</td>
												</tr>
											</s:if>
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
												<s:if test="#ststusfunction.index%2==1">
												<font>
													</s:if>
													<s:else>
													<font color="#c0dcf2">
														</s:else>
														<s:property value="#ststusfunction.index+1" />
													</font>
											</td>
											<td>
													${users.code}
											</td>
											<td>
													${users.cardId}
											</td>
											<td>
													${users.name}
											</td>
											<td>
													${users.dept}
											</td>
											<td>
													${users.duty}
											</td>
											<td>
												<input type="checkbox"
													   id="user<s:property value="#ststusfunction.index"/>"
													   name="usersId" value="${users.id}" onclick="chageNum(this)"
													   <%--checked="checked"--%>
												>
											</td>
											</tr>
										</s:iterator>
										<tr>
											<td colspan="7" align="center">
												<font color="red">${errorMessage}</font>
											</td>
										</tr>
										<tr>
										</tr>
										<tr>
											<s:if test="errorMessage==null">
											<td colspan="11" align="right">
												第
												<font color="red"><s:property value="cpage2" /> </font> /
													<s:property value="total2" />
												页
												<span class="page">
                                                    <s:if test="total2!=1">
														<input type="button" value="首页" style="width: 40px;height: 20px"  class="input" onclick="firstpage2(this)" />
														<input type="button" value="下一页" style="width: 60px;height: 20px"  class="input" onclick="nextpage2(this)" />
														<input type="button" value="末页" style="width: 40px;height: 20px"  class="input" onclick="endpage2(this)" />
                                                        <%--<a href="ModuleFunctionAction!findMfById.action?id=${id}&amp;cpage2=1&amp;total2=${total2}&amp;pageStatus=cancel">[首页]</a>--%>
                                                        <%--<a href="ModuleFunctionAction!findMfById.action?id=${id}&amp;cpage2=${cpage2+1}&amp;total2=${total2}&amp;pageStatus=cancel">[下一页]</a>--%>
                                                        <%--<a href="ModuleFunctionAction!findMfById.action?id=${id}&amp;cpage2=${total2}&amp;total2=${total2}&amp;pageStatus=cancel">[末页]</a>--%>
                                                     </s:if>

												</s:if>
												<s:else>
											<td colspan="11" align="center" style="color: red">
												</s:else>
											</td>
										</tr>
										<tr>
											<td align="right" colspan="7">
												<br>
												<%--<font color="red">共选择 <label id="peopleLabel2">--%>
													<%--${count}--%>
												<%--</label>人</font>--%>
												<input type="submit" value="取消绑定"
													   style="width: 80px; height: 40px;" align="top">
												<br>
												<br>
												<br>
												<br>
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
		<script type="text/javascript">

function  nextpage2(obj) {
    var tmpInput=$("<input type='hidden' name='cpage2'/>");
    tmpInput.attr("value",${cpage2+1});
    $('#formfindhadBinding').append(tmpInput);
    $('#formfindhadBinding').submit();
}

function  firstpage2(obj) {
    var tmpInput=$("<input type='hidden' name='cpage2'/>");
    tmpInput.attr("value",1);
    $('#formfindhadBinding').append(tmpInput);
    $('#formfindhadBinding').submit();
}
function  endpage2(obj) {
    var tmpInput=$("<input type='hidden' name='cpage2'/>");
    tmpInput.attr("value",${total2});
    $('#formfindhadBinding').append(tmpInput);
    $('#formfindhadBinding').submit();
}

function  nextpage(obj) {
    var tmpInput=$("<input type='hidden' name='cpage'/>");
    tmpInput.attr("value",${cpage+1});
    $('#formDeletebinDing').append(tmpInput);
    $('#formDeletebinDing').submit();
}

function  firstpage(obj) {
    var tmpInput=$("<input type='hidden' name='cpage'/>");
    tmpInput.attr("value",1);
    $('#formDeletebinDing').append(tmpInput);
    $('#formDeletebinDing').submit();
}
function  endpage(obj) {
    var tmpInput=$("<input type='hidden' name='cpage'/>");
    tmpInput.attr("value",${total});
    $('#formDeletebinDing').append(tmpInput);
    $('#formDeletebinDing').submit();
}

</script>
	</body>
</html>
