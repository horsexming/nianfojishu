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
		<link rel="stylesheet" href="css/css.css" type="text/css"></link>
	</head>
	<body bgcolor="#ffffff" onload="createDept('dept')">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<div style="width: 1000px;">
					<div align="right"><a href="javascript:history.back(-1)">返回</a></div>
					<div id="xitong" align="center"
						style="width: 100%; height: 31px; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; background: url('admin/images/admin_title.jpg');">
						<font color="#ffffff" size="4">
							<strong>机密等级:
								<select id="jimi"  onmouseout="jimi()">
										<option></option>
										<s:iterator value="JiMiList" id="jimitest" status="statussdf" >
										<s:if test="#jimitest.id==jimileixing.id">
											<option value="${jimitest.id}" selected="selected">${jimitest.type}</option>
										</s:if>
										<s:else>
											<option value="${jimitest.id}">${jimitest.type}</option>
										</s:else>
									</s:iterator>
								</select>
							</strong>
						</font>
					</div>
					<s:if test="jimileixing==null">
						
					</s:if>
					
				<div style="border: solid 1px #0170b8; width: 1000px; font-family: 微软雅黑; margin-top: 10px;"
						align="left">
				<div align="center">
					<font color="red">${successMessage}</font>
				</div>
				<div id="bangding">
					<form action="jimileixing_findUsersByCondition.action"
						method="post" style="margin: 0px">
						<br>
						<table class="table">
							<tr>
								<td align="right">
									姓名:
								</td>
								<td>
									<input type="hidden" name="id"
										value="${jimileixing.id}" />
									<input type="text" name="user.name" />
								</td>
								<td align="right">
									部门:
								</td>
								<td>
									<select name="user.dept" id="dept" style="width: 155px;">
										<option value=""></option>
									</select>
								</td>
								<td rowspan="2">
									<input type="submit" value="查询"
										style="width: 100px; height: 50px;" />
								</td>
							</tr>
							<tr>
								<td align="right">
									卡号:
								</td>
								<td>
									<input type="text" name="user.cardId" />
								</td>
								<td align="right">
									工号:
								</td>
								<td>
									<input type="text" name="user.code" />
								</td>
							</tr>
						</table>
					</form>
					<hr>
					<form action="jimileixing_bangdingjimi.action"
						method="post" target="main" style="margin: 0px">
						<input type="hidden" name="id" value="${jimileixing.id}">
						<table style="width: 980px; border-collapse: collapse;" border="0"
							align="center">
							<tr>
								<td align="right" colspan="7">
									<font color="red">共选择 <label id="peopleLabel">
											${count}
										</label> <input type="hidden" id="propleText" name="peopleNum"
											style="width: 20px;" readonly="readonly"> 人</font>
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
										name="checkboxs" onclick="chageAllCheck(this)">
									全选
								</td>
							</tr>

							<s:iterator id="users" value="bangUserList"
								status="ststusfunction">
								<s:if test="#ststusfunction.first">
									<tr bgcolor="green">
										<td colspan="7" style="font-family: 微软雅黑; font-weight: bold;"
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
													name="uid" value="${users.id}" onclick="chageNum(this)"
													checked="checked">
								</td>
								</tr>
								<s:if test="#ststusfunction.last">
									<tr bgcolor="green">
										<td colspan="7" style="font-family: 微软雅黑; font-weight: bold;"
											align="center">
											<font color="#ffffff"> 未绑定用户</font>
										</td>
									</tr>
								</s:if>
							</s:iterator>

							<s:iterator id="users" value="userList" status="ststusfunction">
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
													name="uid" value="${users.id}" onclick="chageNum(this)"/>
								</td>
								</tr>
							</s:iterator>
							<tr>
								<td colspan="7" align="center">
									<font color="red" size="6">${errorMessage}</font>
								</td>
							</tr>
							<tr>
								<td colspan="7" align="right"
									style="font-weight: bold; padding-right: 40px">
									<input type="checkbox" id="checkAll2"
									 name="checkboxs"	onclick="chageAllCheck(this)">
									全选
								</td>
							</tr>
							<tr>
								<s:if test="errorMessage==null">
									<td colspan="11" align="right">
										第
										<font color="red"><s:property value="cpage" /> </font> /
										<s:property value="total" />
										页
										<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
											styleClass="page" theme="number" />
								</s:if>
								<s:else>
									<td colspan="11" align="center" style="color: red">
								</s:else>
								</td>
							</tr>
							<tr>
								<td align="right" colspan="7">
									<br>
									<font color="red">共选择 <label id="peopleLabel2">
											${count}
										</label>人</font>
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
					
			<%
				request.getSession().removeAttribute("successMessage");
				request.getSession().removeAttribute("errorMessage");
			%>
				</div>
			</div>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
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
					select.options.add(optionItem);
				}
			}
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
	if(selectName=='dept'){
		if (actionUrl == null) {
			actionUrl = "DeptNumberAction!finAllDeptNumberForSetlect.action";
		}
	}
	select = document.getElementById(selectName);
	sendRequest(actionUrl, findAllDept)
}

function chageAllCheck(obj) {
	var checkAll =obj;
	var checkboxs = document.getElementsByName("uid");
	var peopleLabel = document.getElementById("peopleLabel");
	var peopleLabel2 = document.getElementById("peopleLabel2");
	if (checkAll.checked == true) {
		for ( var i = 0; i < checkboxs.length; i++) {
			checkboxs[i].checked = true;
		}
		peopleLabel.innerHTML = checkboxs.length;
		peopleLabel2.innerHTML = checkboxs.length;
	} else {
		for ( var i = 0; i < checkboxs.length; i++) {
			checkboxs[i].checked = false;
		}
		peopleLabel.innerHTML = 0;
		peopleLabel2.innerHTML = 0;
	}

}
var count2=0;
var count1=peopleLabel.innerHTML;
function chageNum(obj) {
	var checkAll = document.getElementById("checkAll");
	var checkAll2 = document.getElementById("checkAll2");
	var checkboxs = document.getElementsByName("checkboxs");
	var peopleLabel = document.getElementById("peopleLabel");
	var peopleLabel2 = document.getElementById("peopleLabel2");
		if(obj.checked ==true){
			count2++;
		}else{
			count2--;
		}
		peopleLabel.innerHTML=parseInt(count1)+count2;
		peopleLabel2.innerHTML=parseInt(count1)+count2;
		if(count2==15){
			checkAll.checked=true;
			checkAll2.checked=true;
		}
}

$(document).ready(function(){
 	 $.ajax({
  		type:"POST", 
  		url:"jimileixing_showJiMiList.action",
  		data:{},
  		dataType:"json",
  		success:function(data){
  			  			var jimi = data;
  			document.getElementById("jimiDJ").value=jimi;
		
  		}
  	
 	 });
 	
 	});
function jimi(){
	var jimi=document.getElementById("jimi");
	if(jimi!=null){
		window.location.href="jimileixing_findMfById.action?id="+jimi.value;
	}
}
</script>
	</body>
</html>
