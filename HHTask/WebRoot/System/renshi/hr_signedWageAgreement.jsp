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
function checkContractForm() {
	var recruitmentSquare = document.getElementById("recruitmentSquare");//招聘方
	var contractNumber = document.getElementById("contractNumber");//合同编号
	var startDate = document.getElementById("startDate");//开始时间
	//var endDate = document.getElementById("endDate");//结束时间

	if (recruitmentSquare.value == "") {
		alert("请填写招聘方!");
		recruitmentSquare.focus();
		return false;
	} else if (contractNumber.value == "") {
		alert("请填写合同编号!");
		contractNumber.focus();
		return false;
	} else if (startDate.value == "") {
		alert("请填写生效时间!");
		startDate.focus();
		return false;
	} else {
		return true;
	}
}
//流程判断
function chagePrint() {
	var pageStatus = "${pageStatus}";
	var contract = "${contract}";
	var contractList = "${contractList}";
	if (pageStatus == "view") {
		chageDiv('block');
		document.getElementById("bodyDiv").style.height = 5000;//更改透明层的高度
		document.getElementById("addContract").style.display = "block";
	} else {
		if (contract != "") {
			document.getElementById("showContractDiv").style.display = "block";
		} else if (contractList != "") {
			document.getElementById("showAllContractDiv").style.display = "block";
		} else {
			document.getElementById("addContract").style.display = "block";
		}
	}
}
$(function() {
	var errorMessage = "${errorMessage}";
	if (errorMessage) {
		alert(errorMessage);
	}
});
</script>
	</head>
	<body bgcolor="#ffffff" onload="createDept('dept'),chagePrint()">
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="background: url(<%=basePath%>/images/bq_bg2.gif); width: 100%;">
				<table style="width: 100%; margin-top: ">
					<tr>
						<td>
							您正在预览薪资调整协议:
						</td>
						<td align="right">
							<img alt="" src="images/closeImage.png" width="30" height="32"
								onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%; height: 100px">
					<div style="width: 100%;" align="center">
						<div style="width: 100%; margin: 30 40 20 60px">
							<div align="center" style="padding-top: 10px;">
								<font style="font-weight: bolder;" size="5px">岗位及薪资确认协议书</font>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<s:if test="contractNumber!=null">
									<img align="middle" alt=""
										src="barcode.action?msg=${contractNumber}&type=code128"
										style="height: 40px; width: 120px;">
								</s:if>
							</div>
							<br />
							<br />
							<table align="left" border="0"
								style="width: 100%; border-collapse: collapse;">
								<tbody>
									<tr>
										<td colspan="4" align="left" style="font-weight: bold">
											一、协议双方:
										</td>
									</tr>
									<tr>
										<th align="right" style="width: 16%;">
											甲方:
										</th>
										<td colspan="3">
											${contract.recruitmentSquare}
										</td>
									</tr>
									<tr>
										<th align="right">
											地 址:
										</th>
										<td colspan="3">
											${contract.qiandingAddress}
										</td>
									</tr>
									<tr>
										<td colspan="4">
											<br />
										</td>
									</tr>
									<tr>
										<th align="right">
											乙方:
										</th>
										<td>
											${contract.employedSquare}
										</td>
										<th align="left">
											身份证号:
										</th>
										<td colspan="left">
											${contract.uid}
										</td>
									</tr>
									<tr>
										<th align="right">
											乙方户籍地址:
										</th>
										<td colspan="3">
											${contract.residence}
										</td>
									</tr>
									<tr>
										<th align="right">
											函件送达地址:
										</th>
										<td>
											${contract.presentAddress}
										</td>
										<th align="left">
											联系电话:
										</th>
										<td>
											${contract.phoneNumber}
										</td>
									</tr>
									<tr>
										<td colspan="4">
											<br />
										</td>
									</tr>
									<tr>
										<td colspan="4" style="font-weight: bold">
											二、协议内容:
										</td>
									</tr>
									<tr>
										<td colspan="4" align="right">
											&nbsp;
										</td>
									</tr>
									<s:iterator value="provisionList" id="pageProvision"
										status="pageStatus">
										<tr>
											<td colspan="4" align="left" style="padding-left: 20px;">
												<s:property value="#pageStatus.index+1" />
												、&nbsp; ${pageProvision.content}
											</td>
										</tr>
									</s:iterator>
									<tr>
										<td colspan="4" align="center">
											<input type="button" value="修改" onclick="chageDiv('none')"
												style="width: 80px; height: 50px;" />
											<input type="button" value="签订" onclick="submitViewForm()"
												style="width: 80px; height: 50px;" />
											<input type="button" value="关闭" onclick="chageDiv('none')"
												style="width: 80px; height: 50px;" />
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">
					
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a href="UsersAction!wageSignedAndPrint.action?id=${user.id}"
						style="color: #ffffff">添加薪资调整</a>
					<a
						href="ContractAction!findUserWageAgre.action?contract.contractStatus=wage&contract.userId=${user.id}"
						style="color: #ffffff">薪资调整历史</a>
				</div>
			</div>


			<div id="addContract" style="display: none">
				<form id="agreementForm"
					action="ContractAction!addWageAgreement.action" method="post"
					dir="ltr" onsubmit="return checkContractForm()">
					<input type="hidden" name="id" value="${user.id}">
					<input type="hidden" name="contract.userId" value="${user.id}">
					<input type="hidden" name="contract.code" value="${user.code}">
					<input type="hidden" name="contract.cardId" value="${user.cardId}">
					<input type="hidden" name="contract.contractStatus" value="wage">
					<input type="hidden" name="contract.isUse" value="待调整">
					<div style="width: 100%; padding: 20 0 20 0px">
						<div style="color: red; size: 16px;">
							${errorMessage}
						</div>
						<br />
						<font style="font-weight: bolder;" size="5px">岗位及薪资确认协议书</font>
						<br />
						<br />
						<table align="left" border="2" class="table" id="contract"
							onclick="showindex()">
							<tbody>
								<tr>
									<td colspan="7" style="width: 100px; font-weight: bold">
										&nbsp;&nbsp;一、协议双方:
									</td>
								</tr>
								<tr>
									<th align="right">
										甲方:
									</th>
									<td colspan="6">
										<input id="recruitmentSquare" class="horizontalLine"
											name="contract.recruitmentSquare" style="width: 640px"
											value="${companyInfo.name}">
									</td>
								</tr>
								<tr>
									<th align="right">
										地 址:
									</th>
									<td colspan="6">
										<input value="${companyInfo.address}" id="recruitmentSquare"
											class="horizontalLine" name="contract.qiandingAddress"
											style="width: 640px">
									</td>
								</tr>
								<tr>
									<td colspan="7">
										<br />
									</td>
								</tr>
								<tr>
									<th align="right">
										乙方:
									</th>
									<td colspan="1">
										<input class="horizontalLine" name="contract.employedSquare"
											style="" value="${user.name}" readonly="readonly">
									</td>
									<th align="right">
										身份证号:
									</th>
									<td colspan="4">
										<input class="horizontalLine" name="contract.uid"
											style="width: 400px;" value="${user.uid}" readonly="readonly">
									</td>
								</tr>
								<tr>
									<th align="right">
										乙方户籍地址:
									</th>
									<td colspan="6">
										<input id="recruitmentSquare" class="horizontalLine"
											name="contract.residence" style="width: 640px"
											value="${user.residence}">
									</td>
								</tr>
								<tr>
									<th align="right">
										函件送达地址:
									</th>
									<td>
										<input class="horizontalLine" name="contract.presentAddress"
											style="width: 300px;" value="${user.password.presentAddress}"
											readonly="readonly">
									</td>
									<th align="right">
										联系电话:
									</th>
									<td>
										<input class="horizontalLine" name="contract.phoneNumber"
											style="width: 400px;" value="${user.password.phoneNumber}"
											readonly="readonly">
									</td>
								</tr>
								<tr>
									<td colspan="7">
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="7" style="width: 100px; font-weight: bold">
										&nbsp;&nbsp;二、协议内容:
									</td>
								</tr>
								<tr>
									<td colspan="7" align="right">
										&nbsp;
									</td>
								</tr>
								<s:iterator value="provisionList" id="pageProvision"
									status="pageStatus">
									<s:if test="#pageStatus.index%2==1">
										<tr align="center" bgcolor="#e6f3fb"
											onmouseover="chageBgcolor(this)"
											onmouseout="outBgcolor(this,'#e6f3fb')">
									</s:if>
									<s:else>
										<tr align="center" onmouseover="chageBgcolor(this)"
											onmouseout="outBgcolor(this,'')">
									</s:else>
									<td>
										<s:if test="#pageStatus.index%2==1">
											<font>
										</s:if>
										<s:else>
											<font color="red">
										</s:else>
										<s:property value="#pageStatus.index+1" />
										</font>
									</td>
									<td colspan="5" align="left">
										<textarea name="proContent" rows="5" cols="100">${pageProvision.content}</textarea>
									</td>
									<td style="width: 1px" id="1">
										<input type="button" value="删" style="width: 20px"
											onclick="delTr(this.parentElement.parentElement);" />
										<input type="button" name="b1" value="↑" style="width: 20px"
											onClick="movetr(this.parentElement.parentElement)">
										<input type="button" name="b2" value="↓" style="width: 20px;"
											onClick="movetr(this.parentElement.parentElement)">
									</td>
									</tr>
								</s:iterator>
								<tr id="uploadtr">
									<td colspan="7" align="right">
										<input type="button" value="添加条款" onclick="addProvision()"
											style="width: 80; height: 30px;">
									</td>
								</tr>
								<tr>
									<td colspan="7" align="center">
										<s:if test="errorMessage!=null">
											<input type="submit" value="签订"
												style="width: 80; height: 50px;" disabled="disabled">
										</s:if>
										<s:else>
											<input type="submit" value="签订"
												style="width: 80; height: 50px;">
										</s:else>
										<input type="reset" value="重置"
											style="width: 80; height: 50px;">
										<input type="button" value="预览"
											style="width: 80; height: 50px;"
											onclick="submitFormForView(this.form)" />
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</form>
			</div>
			<div id="showAllContractDiv" style="display: none; margin-top: 10px;">
				<table style="width: 100%; border-collapse: collapse;">
					<tr bgcolor="#c0dcf2" height="50px">
						<th align="center">
							序号
						</th>
						<th align="center">
							姓名
						</th>
						<th align="center">
							工号
						</th>
						<th align="center">
							卡号
						</th>
						<th align="center">
							身份证号
						</th>
						<th align="center">
							联系方式
						</th>
						<th align="center">
							协议状态
						</th>
						<th align="center">
							调整类型
						</th>
						<th align="center" style="width: 100px;">
							添加时间
						</th>
						<th align="center">
							操作
						</th>
					</tr>
					<s:iterator value="contractList" id="pageContract"
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
							<s:property value="#ststusfunction.index+1" />
						</td>
						<td align="center">
							${pageContract.employedSquare}
						</td>
						<td align="center">
							${pageContract.code}
						</td>
						<td align="center">
							${pageContract.cardId}
						</td>
						<td align="center">
							${pageContract.uid}
						</td>
						<td align="center">
							${pageContract.phoneNumber}
						</td>
						<td align="center">
							${pageContract.isUse}
						</td>
						<td align="center">
							${pageContract.sqType}
						</td>
						<td align="center" style="width: 100px;">
							${pageContract.addDateTime}
						</td>
						<td>
							<s:if test="#pageContract.isUse=='待调整'">
								<a
									href="ContractAction!delContract.action?id=${pageContract.id}"
									onclick="return window.confirm('确定要删除吗?')">删除</a> / 
							</s:if>

							<a
								href="ContractAction!findProvisions.action?id=${pageContract.id}">
								详细</a>
						</td>
						</tr>
					</s:iterator>
				</table>
			</div>
			<div id="showContractDiv" style="display: none;">
				<div id="printDiv">
					<div style="width: 100%;" align="center">
						<div style="width: 100%; margin: 30 40 0 60px">
							<font style="font-weight: bolder;" size="5px">岗位及薪资确认协议书</font>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<s:if test="contractNumber!=null">
								<img align="middle" alt=""
									src="barcode.action?msg=${contractNumber}&type=code128"
									style="height: 40px; width: 120px;">
							</s:if>

							<br />
							<br />
							<table align="left" border="0"
								style="width: 100%; border-collapse: collapse;">
								<tbody>
									<tr>
										<td colspan="4" align="left" style="font-weight: bold">
											一、协议双方:
										</td>
									</tr>
									<tr>
										<th align="right" style="width: 16%;">
											甲方:
										</th>
										<td colspan="3">
											${contract.recruitmentSquare}
										</td>
									</tr>
									<tr>
										<th align="right">
											地 址:
										</th>
										<td colspan="3">
											${contract.qiandingAddress}
										</td>
									</tr>
									<tr>
										<td colspan="4">
											<br />
										</td>
									</tr>
									<tr>
										<th align="right">
											乙方:
										</th>
										<td>
											${contract.employedSquare}
										</td>
										<th align="left">
											身份证号:
										</th>
										<td colspan="left">
											${contract.uid}
										</td>
									</tr>
									<tr>
										<th align="right">
											乙方户籍地址:
										</th>
										<td colspan="3">
											${contract.residence}
										</td>
									</tr>
									<tr>
										<th align="right">
											函件送达地址:
										</th>
										<td>
											${contract.presentAddress}
										</td>
										<th align="left">
											联系电话:
										</th>
										<td>
											${contract.phoneNumber}
										</td>
									</tr>
									<tr>
										<td colspan="4">
											<br />
										</td>
									</tr>
									<tr>
										<td colspan="4" style="font-weight: bold">
											二、协议内容:
										</td>
									</tr>
									<tr>
										<td colspan="4" align="right">
											&nbsp;
										</td>
									</tr>
									<s:iterator value="provisionList" id="pageProvision"
										status="pageStatus">
										<tr>
											<td colspan="4" align="left" style="padding-left: 20px;">
												<s:property value="#pageStatus.index+1" />
												、&nbsp; ${pageProvision.content}
											</td>
										</tr>
									</s:iterator>
									<tr>
										<td colspan="4" align="left">
											&nbsp;
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			<div align="center">
				<div>
					<font color="red">${successMessage}</font>
					<font color="red">${errorMessage}</font>
				</div>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>

		<script type="text/javascript">
//添加自定义条款
var provisionSize = parseInt("<s:property value='provisionList.size' />", 0);
function addProvision() {
	var _tbody = document.getElementById("contract").tBodies[0];//获得第一个tbody
	var uploadtr = document.getElementById("uploadtr");//将要在该Tr之前添加元素

	var _tr = document.createElement("tr");
	_tr.onmouseover = function() {
		chageBgcolor(this);
	}
	if (provisionSize % 2 == 1) {
		_tr.style.background = "#e6f3fb";
		_tr.onmouseout = function() {
			outBgcolor(this, '#e6f3fb');
		}
	} else {
		_tr.onmouseout = function() {
			outBgcolor(this, '');
		}
	}

	var td1 = document.createElement("td");//序号
	td1.align = "center";
	var word = document.createTextNode(provisionSize + 1);

	var td2 = document.createElement("td");
	td2.colSpan = 5;//第2列列占4格位置

	var textarea1 = document.createElement("textarea");
	textarea1.name = "proContent";
	textarea1.rows = "5";
	textarea1.cols = "100";
	var td3 = document.createElement("td");
	//var checkbox1 = document.createElement("input");//复选框
	//checkbox1.type = "checkbox";
	//checkbox1.name = "provisionId";
	//checkbox1.value = provisionSize;
	//checkbox1.onclick = function() {
	//	chageCheckbox(this);
	//}
	var delInput = document.createElement("input");//删除
	delInput.type = "button";
	delInput.value = "删";
	delInput.style.width = "20px";
	delInput.onclick = function() {
		_tbody.deleteRow(_tr.rowIndex);
		//provisionSize--;
	}
	var br1 = document.createElement("br");
	var upInput = document.createElement("input");//上移
	upInput.type = "button";
	upInput.name = "b1";
	upInput.value = "↑";
	upInput.style.width = "20px";
	upInput.onclick = function() {
		movetr(_tr);
	}
	var br2 = document.createElement("br");
	var downInput = document.createElement("input");//上移
	downInput.type = "button";
	downInput.name = "b2";
	downInput.value = "↓";
	downInput.style.width = "20px";
	downInput.onclick = function() {
		movetr(_tr);
	}

	_tbody.insertBefore(_tr, uploadtr);
	_tr.insertBefore(td1, null);
	td1.insertBefore(word, null);

	_tr.insertBefore(td2, null);
	td2.insertBefore(textarea1, null);

	_tr.insertBefore(td3, null);
	//td3.insertBefore(checkbox1, null);
	//td3.insertBefore(br1, null);
	td3.insertBefore(delInput, null);
	td3.insertBefore(br1, null);
	td3.insertBefore(upInput, null);
	td3.insertBefore(br2, null);
	td3.insertBefore(downInput, null);
	provisionSize++;
}

//删除条款(已存在条款)
function delTr(_tr) {
	var _tbody = document.getElementById("contract").tBodies[0];//获得第一个tbody
	_tbody.deleteRow(_tr.rowIndex);
}

//移动行
function movetr(trObject) {
	var _table = document.getElementById("contract");
	var currindex = trObject.rowIndex;
	if (event.srcElement.name == "b1" && currindex > 0) {
		_table.moveRow(currindex, currindex - 1);
		currindex -= 1;
		event.srcElement.focus();
	}
	if (event.srcElement.name == "b2") {
		_table.moveRow(currindex, currindex + 1);
		currindex += 1;
	}
}
//预览
function submitFormForView(objForm) {
	objForm.action = "ContractAction!viewAgreement.action";
	objForm.submit();
	objForm.action = "ContractAction!addWageAgreement.action";
}
//预览时签订协议
function submitViewForm() {
	var viewForm = document.getElementById("agreementForm");
	viewForm.action = "ContractAction!addWageAgreement.action";
	viewForm.submit();
}
</script>
	</body>
</html>
