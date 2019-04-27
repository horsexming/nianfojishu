<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
		<script type="text/javascript">
// 查看工资明细
function wageDetails() {
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
			var ulMessage = XMLHttpReq.responseText;
			var operatingDiv = document.getElementById("operatingDiv");
			var exportWageform = document.getElementById("exportWageform");
			var uploadChageWageDiv = document
					.getElementById("uploadChageWageDiv");
			operatingDiv.innerHTML = ulMessage;
			exportWageform.style.display = "none";
			uploadChageWageDiv.style.display = "none";
			operatingDiv.style.display = "block";
			chageDiv('block');
		} else { //页面不正常
			window.alert("页面异常,请重试!");
		}
	}
}

//更改标题
function chageTitle(userName, id) {
	var titleDiv = document.getElementById("title");
	titleDiv.innerHTML = "您正在查看<font color='red'> " + userName
			+ "</font> 的工资明细 ";
	sendRequest("WageAction!showWageDetails.action?id=" + id, wageDetails)

}

//添加变动工资
function addChageWage(userName, wageId) {
	var showAllChageWageDiv = document.getElementById("showAllChageWage");
	var addChageWageDiv = document.getElementById("addChageWage");
	var userNameSpan = document.getElementById("userName");
	var wageIdInput = document.getElementById("wageId");
	wageIdInput.value = wageId;
	userNameSpan.innerHTML = userName;
	showAllChageWageDiv.style.display = "none";
	addChageWageDiv.style.display = "block";

}

//表单检查
function checkForm() {
	var jiabanfei = document.getElementById("jiabanfei");//加班费
	var wucanfei = document.getElementById("wucanfei");//午餐费
	var jiangjin = document.getElementById("jiangjin");//奖金
	var fangzufei = document.getElementById("fangzufei");//房租费
	var shuidianfei = document.getElementById("shuidianfei");//水电费
	var bingshikangdeng = document.getElementById("bingshikangdeng");//病事旷
	var bufagongzi = document.getElementById("bufagongzi");//补发(补扣)工资
	var other = document.getElementById("other");//其他

	if (jiabanfei.value == "") {
		alert("加班费不能为空!");
		jiabanfei.focus();
		return false;
	} else if (wucanfei.value == "") {
		alert("午餐费不能为空!");
		wucanfei.focus();
		return false;
	} else if (jiangjin.value == "") {
		alert("奖金不能为空!");
		jiangjin.focus();
		return false;
	} else if (fangzufei.value == "") {
		alert("房租费不能为空!");
		fangzufei.focus();
		return false;
	} else if (shuidianfei.value == "") {
		alert("水电费不能为空!");
		shuidianfei.focus();
		return false;
	} else if (bingshikangdeng.value == "") {
		alert("病事旷等不能为空!");
		bingshikangdeng.focus();
		return false;
	} else if (bufagongzi.value == "") {
		alert("补发工资不能为空!");
		bufagongzi.focus();
		return false;
	} else if (other.value == "") {
		alert("其他不能为空!");
		other.focus();
		return false;
	} else {
		return true;
	}

}

//上传变动工资
function uploadWage(status) {
	var uploadChageWageDiv = document.getElementById("uploadChageWageDiv");
	var operatingDiv = document.getElementById("operatingDiv");
	var exportWageform = document.getElementById("exportWageform");
	var downStatus = document.getElementById("downStatus");
	operatingDiv.style.display = "none";
	if (status == "upload") {
		uploadChageWageDiv.style.display = "block";
		exportWageform.style.display = "none";
		chageDiv('block', '您将上传变动工资信息');
	} else if (status == "down") {
		uploadChageWageDiv.style.display = "none";
		exportWageform.style.display = "block";
		downStatus.value = "jixiao";
		chageDiv('block', '您将下载变动工资信息');
	} else if (status == "check") {
		uploadChageWageDiv.style.display = "none";
		exportWageform.style.display = "block";
		downStatus.value = "check";
		chageDiv('block', '您将下载自查工资信息');
	}
}

function chageErrorMessage() {
	var errorMessage = "${errorMessage}";
	if (errorMessage != "" && errorMessage != "没有找到你要查询的内容,请检查后重试!") {
		var uploadChageWageDiv = document.getElementById("uploadChageWageDiv");
		var operatingDiv = document.getElementById("operatingDiv");
		uploadChageWageDiv.style.display = "none";
		operatingDiv.innerHTML = errorMessage;
		chageDiv('block', '您正在查看上传变动工资后的处理信息:');
	}
}
</script>
	</head>
	<body bgcolor="#ffffff" onload="chageErrorMessage(),createDept('dept')">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px;"
				align="left">
				<div style="float: left; width: 50%" align="left">

				</div>
				<div style="float: left; width: 48%" align="right">
					<a
						href="DownAction.action?fileName=shebaoWage.xls&directory=/upload/public/"
						style="color: #ffffff">社保工资模版下载</a>
					<a
						href="FileViewAction.action?FilePath=&directory=/upload/public/shebaoWage.xls&Refresh=true"
						style="color: #ffffff">/预览</a>
				</div>
			</div>

			<div align="center">
				<div align="left" style="padding-left: 50px;">
					<h3>
						上传说明:
					</h3>
					<p>
						1、下载社保工资模版
						<a
							href="DownAction.action?fileName=shebaoWage.xls&directory=/upload/public/">社保工资模版下载</a>
						<a
							href="FileViewAction.action?FilePath=/upload/public/shebaoWage.xls&Refresh=true">/预览</a>，按照模版内的格式填写好数据
						<br />
						<br />
						2、关于数据格式相关注意:
						<br />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(1)、工号列数据必须存在
						<br />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(2)、如果某项不填(留空)则该工号对应人员该项不变
						<br />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(3)、如果某项为0,则该工号对应人员不缴纳该项
					</p>
				</div>
				<form action="WageAction!uploadSbWage.action" method="post"
					enctype="multipart/form-data">
					请选择社保工资文件(excel文件):&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="file" name="chageWage">
					<input type="submit" value="上传" style="width: 80px; height: 30px;">
				</form>
				<div>
					<font color="red">${errorMessage}</font>
				</div>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
	</body>
</html>
