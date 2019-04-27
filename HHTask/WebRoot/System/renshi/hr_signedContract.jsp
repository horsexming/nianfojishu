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
	var startDate = document.getElementById("startDate");//开始时间
	//var endDate = document.getElementById("endDate");//结束时间

	if (recruitmentSquare.value == "") {
		alert("请填写招聘方!");
		recruitmentSquare.focus();
		return false;
	} else if (startDate.value == "") {
		alert("请填写生效时间!");
		startDate.focus();
		return false;
	} else {
		return true;
	}
}
function chagePrint() {
	var contract = "${pageStatus}";
	if (contract == "print") {
		document.getElementById("allDiv").style.display = "block";
	} else if (contract == "view") {
		chageDiv('block');
		document.getElementById("bodyDiv").style.height = 5000;//更改透明层的高度
		document.getElementById("addContract").style.display = "block";
	} else {
		document.getElementById("addContract").style.display = "block";
	}
}

//全选
function chageAllCheckbox(obj) {
	var inputs = document.getElementsByTagName("input");
	for ( var i = 0; i < inputs.length; i++) {
		if (inputs[i].type == "checkbox") {
			var checkBox = inputs[i];
			if (checkBox.checked != obj.checked) {
				checkBox.checked = obj.checked;
			}
		}
	}
}

function chageCheckbox(obj) {
	var check = obj;
	var checkAll = document.getElementById("checkAll");
	if (check.checked == true) {
		var inputs = document.getElementsByTagName("input");
		var status = true;
		for ( var i = 0; i < inputs.length; i++) {
			if (inputs[i].type == "checkbox") {
				var checkBox = inputs[i];
				if (checkBox.id != "checkAll2" && checkBox.id != "checkAll") {
					if (checkBox.checked == false) {
						status = false;
						break;
					}
				}
			}
		}
		if (status == true) {
			checkAll.checked = true;
			checkAll2.checked = true;
		}
	} else {
		checkAll.checked = false;
		checkAll2.checked = false;
	}
}
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
							您正在预览合同:
						</td>
						<td align="right">
							<img alt="" src="images/closeImage.png" width="30" height="32"
								onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%; height: 100px">
					<div>
						<div>
							<div style="width: 98%; padding: 40 20 20 40px" align="center">
								<div align="left"
									style="padding: 80 0 30 80px; float: left; width: 50%">
									编号:
									<input class="horizontalLine" style="width: 100px"
										value="${contract.contractNumber}">
								</div>
								<div style="padding: 80 0 30 0px; width: 48%">
									<s:if test="contract.contractNumber!=''">
										<img
											src="barcode.action?msg=${contract.contractNumber}&type=code39"
											style="height: 30px; width: 180px;">
									</s:if>
								</div>
								<div style="clear: both;">

								</div>
								<h1>
									劳 动 合 同 书
									<hr width="500px" />
									<hr width="500px" />
								</h1>
								<br />
								<table align="left" border="0">
									<tr>
										<td colspan="2" align="left"
											style="height: 30px; padding-left: 100px;">
											甲方:
										</td>
									</tr>
									<tr>
										<td colspan="2" align="left"
											style="height: 30px; padding-left: 100px;">
											法定代表人:
										</td>
									</tr>
									<tr>
										<td colspan="2" align="left"
											style="height: 30px; padding-left: 100px;">
											地址:
										</td>
									</tr>
									<tr>
										<td colspan="2" align="left" style="height: 20px;">
											&nbsp;
										</td>
									</tr>
									<tr>
										<td colspan="2" align="left"
											style="height: 30px; padding-left: 100px;">
											乙方:
											<input class="horizontalLine" name="contract.employedSquare"
												style="width: 80px" value=" " readonly="readonly">
										</td>
									</tr>
									<tr>
										<td colspan="2" align="left"
											style="height: 50px; padding-left: 100px;">
											性别:
											<input align="left" class="horizontalLine"
												style="width: 80px" value="" name="contract.sex"
												readonly="readonly">
											出生日期:
											<input class="horizontalLine" name="contract.contractNumber"
												value="" style="width: 200px">

										</td>
									</tr>
									<tr>
										<td colspan="2" align="left"
											style="height: 50px; padding-left: 100px;">
											学历:
											<input align="left" class="horizontalLine"
												style="width: 80px" value="" name="contract.sex"
												readonly="readonly">
											入职时间:
											<input class="horizontalLine" value=""
												name="contract.joinDate" readonly="readonly"
												style="width: 200px">
										</td>
									</tr>
									<tr>
										<td colspan="2" align="left"
											style="height: 50px; padding-left: 100px;">
											户籍性质:
											<input align="left" class="horizontalLine"
												style="width: 80px" value="" name="" readonly="readonly">
											联系电话:
											<input class="horizontalLine" value="" style="width: 170px"
												name="contract.dety" readonly="readonly">
										</td>
									</tr>
									<tr>
										<td align="left" colspan="2"
											style="height: 50px; padding-left: 100px;">
											身份证号码:
											<input class="horizontalLine" value="" style="width: 310px;"
												readonly="readonly">
										</td>
									</tr>
									<tr>
										<td align="left" colspan="2"
											style="height: 50px; padding-left: 100px;">
											户籍地地址:
											<input class="horizontalLine" value="" style="width: 310px;"
												readonly="readonly">
										</td>
									</tr>
									<tr>
										<td align="left" colspan="2"
											style="height: 50px; padding-left: 100px;">
											联系地址(现居住地):
											<input class="horizontalLine" value="" style="width: 250px;"
												readonly="readonly">
										</td>
									</tr>
									<tr>
										<td colspan="2" align="left"
											style="height: 50px; padding-left: 100px;">
											生效时间:
											<input class="horizontalLine" type="text"
												style="width: 329px" name="contract.startDate" value="" />
										</td>

									</tr>
									<tr>
										<td colspan="2" align="left"
											style="height: 50px; padding-left: 100px;">
											到期时间:
											<input class="horizontalLine" type="text"
												style="width: 329px" name="contract.endDate" value="" />
										</td>
									</tr>
									<tr>
										<td colspan="2">

										</td>
									</tr>
								</table>
							</div>
						</div>
						<div>
							<div style="width: 98%; padding: 40 20 20 40px" align="center">
								<table>
									<tr>
										<td colspan="2" align="left">
											&nbsp;&nbsp;${contract.content}
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<br />
										</td>
									</tr>
									<s:iterator value="provisionList" id="pageProvision"
										status="pageStatus">
										<tr>
											<td colspan="2" align="left">
												&nbsp;&nbsp;${pageProvision.content}
											</td>
										</tr>
									</s:iterator>
									<tr>
										<td colspan="2">
											<br />
											<br />
										</td>
									</tr>
									<tr>
										<td align="left">
											甲 方(盖章):
										</td>
										<td align="left">
											乙方(签字):
										</td>
									</tr>
									<tr>
										<td align="left">
											&nbsp;
										</td>
									</tr>
									<tr>
										<td align="left">
											法人代表(或委托代理人):________________
										</td>
									</tr>
									<tr>
										<td align="left">
											&nbsp;
										</td>
									</tr>

									<td align="left">
										签约时间:_______________________________
									</td>
									<td align="left">
										签约时间:__________________________
									</td>
									</tr>
									<tr>
										<td colspan="4" align="left">
											&nbsp;
										</td>
									</tr>
								</table>
							</div>
						</div>
						<input type="button" value="修改" onclick="chageDiv('none')"
							style="width: 80px; height: 50px;" />
						<input type="button" value="签订" onclick="submitViewForm()"
							style="width: 80px; height: 50px;" />
						<input type="button" value="关闭" onclick="chageDiv('none')"
							style="width: 80px; height: 50px;" />
					</div>
				</div>
			</div>
		</div>


		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; height: 30px; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">

				</div>
				<div style="float: left; width: 45%" align="right">
					<a href="System/renshi/hr_addUser.jsp" style="color: #ffffff">添加员工</a>
				</div>
			</div>
			<!-- 入职流程导航栏 -->
			<div align="center" style="height: 30px;">
				<div class="style2"
					style="float: left; border: blue solid 1px; width: 25%; height: 30px; margin: 0px; padding: 0px;">
					<font size="4"> 添加员工 </font>
				</div>
				<div class="style2"
					style="float: left; border: blue solid 1px; width: 25%; height: 30px; margin: 0px; padding: 0px;">
					<font size="4"> 上传简历 </font>
				</div>
				<div class="style1"
					style="float: left; border: blue solid 1px; width: 24%; height: 30px; margin: 0px; padding: 0px;">
					<font size="4"> 签订合同 </font>
				</div>
				<div class="style2"
					style="float: left; border: blue solid 1px; width: 25%; height: 30px; margin: 0px; padding: 0px;">
					<font size="4"> 薪资信息 </font>
				</div>
			</div>
			<div id="addContract" style="display: none">
				<form action="UsersAction!uploadResume.action" method="post"
					enctype="multipart/form-data">
					上传员工
					<font color="red">${user.name}</font> 的合同:
					<input type="hidden" name="id" value="${user.id}">
					<input type="hidden" name="pageStatus" value="contract">
					<input type="file" name="picture">
					<input type="submit" value="上传" style="width: 60px; height: 30px">
					<br />
					<br />
				</form>



				<form id="viewForm" action="ContractAction!addContract.action"
					method="post" onsubmit="return checkContractForm()">
					<input type="hidden" name="id" value="${user.id}">
					<input type="hidden" name="contract.userId" value="${user.id}">
					<input type="hidden" name="contract.code" value="${user.code}">
					<input type="hidden" name="contract.cardId" value="${user.cardId}">
					<input type="hidden" name="contract.probationaryPeriod"
						value="${user.tryDays}">
					<input type="hidden" name="contract.contractStatus"
						value="contract">
					<input type="hidden" name="contract.contractNumber"
						value="${contractNumber}${contract.contractNumber}">
					<div style="width: 100%; padding: 20 0 20 0px">
						<font style="font-weight: bolder;" size="5px">人事劳动合同(编号：${contractNumber}${contract.contractNumber})</font>
						<br />
						<br />
						<table align="left" border="2" class="table" id="contract"
							onclick="showindex()">
							<tbody>
								<tr>
									<th align="right">
										招聘方(简称甲方):
									</th>
									<td colspan="6">
										<input id="recruitmentSquare" class="horizontalLine"
											name="contract.recruitmentSquare" style="width: 640px"
											value="${companyInfo.name}">
									</td>
								</tr>
								<tr>
									<th align="right">
										法 定 代 表 人:
									</th>
									<td colspan="6">
										<input value="" id="recruitmentSquare" class="horizontalLine"
											name="contract.daibiao" style="width: 640px">
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
									<th align="right">
										受聘方(简称乙方):
									</th>
									<td colspan="6">
										<input class="horizontalLine" name="contract.employedSquare"
											style="width: 640px" value="${user.name}" readonly="readonly">
									</td>
								</tr>
								<tr>
									<td colspan="7">
										<br />
									</td>
								</tr>
								<tr>
									<th align="right">
										性别:
									</th>
									<td>
										<input class="horizontalLine" value="${user.sex}"
											name="contract.sex" readonly="readonly">
									</td>
									<th align="right">
										职务:
									</th>
									<td align="left">
										<input class="horizontalLine" value="${user.duty}"
											name="contract.dety" readonly="readonly">
									</td>
									<th align="right">
										入职时间:
									</th>
									<td>
										<input class="horizontalLine" value="${user.joined}"
											name="contract.joinDate" readonly="readonly">
									</td>
									<td></td>
								</tr>
								<tr>
									<th align="right">
										生效时间:
									</th>
									<td>
										<input id="startDate" class="Wdate" type="text"
											name="contract.startDate"
											onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"
											value="${contract.startDate}" />
									</td>
									<th align="right">
										到期时间:
									</th>
									<td>
										<input id="endDate" class="Wdate" type="text"
											name="contract.endDate"
											onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"
											value="${contract.endDate}" />
									</td>
									<td></td>
								</tr>
								<tr>
									<td colspan="7">
										<br />
									</td>
								</tr>
								<tr>
									<th align="right">
										内容:
									</th>
									<td colspan="6">
										<textarea rows="5" cols="80" name="contract.content">   甲方根据公司经营业务需要聘用${user.name}先生/女士（以下简称乙方）为甲方合同制员工。根据《中华人民共和国劳动法》、《中华人民共和国劳动合同法》等有关法律法规以及本单位依法制定的规章制度，甲乙双方本着平等自愿、协商一致、合法公平、诚实信用的原则，签订劳动合同，并承诺共同遵守以下条款</textarea>
									</td>
								</tr>
								<tr>
									<td colspan="7" style="width: 100px;">
										&nbsp;&nbsp;条款:
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
										<input type="submit" value="签订"
											style="width: 80; height: 50px;">
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
			<div id="allDiv" style="display: none">
				<a href="UsersAction!findUserById.action?id=${id}&pageStatus=2">本合同到期了?重新签订!</a>
				<div id="printDiv">
					<div style="width: 100%;" align="center">
						<div align="left"
							style="padding: 80 0 30 80px; float: left; width: 50%">
							编号:
							<input class="horizontalLine" style="width: 100px"
								value="${contract.contractNumber}">
						</div>
						<div style="padding: 80 0 30 0px; width: 48%">
							<s:if test="contract.contractNumber!=''">
								<img
									src="barcode.action?msg=${contract.contractNumber}&type=code39"
									style="height: 30px; width: 180px;">
							</s:if>
						</div>
						<div style="clear: both;">

						</div>
						<h1>
							劳 动 合 同 书
							<hr width="500px" />
							<hr width="500px" />
						</h1>
						<br />
						<div align="left">
							<table>
								<tr>
									<td colspan="2" align="left"
										style="height: 30px; padding-left: 100px;">
										甲方: ${contract.recruitmentSquare}
									</td>
								</tr>
								<tr>
									<td colspan="2" align="left"
										style="height: 30px; padding-left: 100px;">
										法定代表人: ${contract.daibiao}
									</td>
								</tr>
								<tr>
									<td colspan="2" align="left"
										style="height: 30px; padding-left: 100px;">
										地址: ${contract.qiandingAddress}
									</td>
								</tr>
								<tr>
									<td colspan="2" align="left" style="height: 20px;">
										&nbsp;
									</td>
								</tr>
								<tr>
									<td colspan="2" align="left"
										style="height: 30px; padding-left: 100px;">
										乙方:
										<input class="horizontalLine" name="contract.employedSquare"
											style="width: 80px; border-bottom: 1px solid #000000; border-left: 1px solid #ffffff; border-right: 1px solid #ffffff; border-top: 1px solid #ffffff;"
											value=" ${contract.employedSquare}" readonly="readonly">
									</td>
								</tr>
								<tr>
									<td colspan="2" align="left"
										style="height: 50px; padding-left: 100px;">
										性别:
										<input align="left" class="horizontalLine"
											style="width: 80px; border-bottom: 1px solid #000000; border-left: 1px solid #ffffff; border-right: 1px solid #ffffff; border-top: 1px solid #ffffff;"
											value="${user.sex}" name="contract.sex" readonly="readonly">
										出生日期:
										<input class="horizontalLine" name="contract.contractNumber"
											value="${user.bothday}"
											style="width: 270px; border-bottom: 1px solid #000000; border-left: 1px solid #ffffff; border-right: 1px solid #ffffff; border-top: 1px solid #ffffff;">

									</td>
								</tr>
								<tr>
									<td colspan="2" align="left"
										style="height: 50px; padding-left: 100px;">
										学历:
										<input align="left" class="horizontalLine"
											style="width: 80px; border-bottom: 1px solid #000000; border-left: 1px solid #ffffff; border-right: 1px solid #ffffff; border-top: 1px solid #ffffff;"
											value="${user.education}" name="contract.sex"
											readonly="readonly">
										入职时间:
										<input class="horizontalLine" value="${contract.joinDate}"
											name="contract.joinDate" readonly="readonly"
											style="width: 270px; border-bottom: 1px solid #000000; border-left: 1px solid #ffffff; border-right: 1px solid #ffffff; border-top: 1px solid #ffffff;">
									</td>
								</tr>
								<tr>
									<td colspan="2" align="left"
										style="height: 50px; padding-left: 100px;">
										户籍性质:
										<input align="left" class="horizontalLine"
											style="width: 80px; border-bottom: 1px solid #000000; border-left: 1px solid #ffffff; border-right: 1px solid #ffffff; border-top: 1px solid #ffffff;"
											value="${user.password.censusNature}" name=""
											readonly="readonly">
										联系电话:
										<input class="horizontalLine"
											value="${user.password.phoneNumber}"
											style="width: 240px; border-bottom: 1px solid #000000; border-left: 1px solid #ffffff; border-right: 1px solid #ffffff; border-top: 1px solid #ffffff;"
											name="contract.dety" readonly="readonly">
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2"
										style="height: 50px; padding-left: 100px;">
										身份证号码:
										<input class="horizontalLine" value="${user.uid}"
											style="width: 380px; border-bottom: 1px solid #000000; border-left: 1px solid #ffffff; border-right: 1px solid #ffffff; border-top: 1px solid #ffffff;"
											readonly="readonly">
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2"
										style="height: 50px; padding-left: 100px;">
										户籍地地址:
										<input class="horizontalLine" value="${user.residence}"
											style="width: 380px; border-bottom: 1px solid #000000; border-left: 1px solid #ffffff; border-right: 1px solid #ffffff; border-top: 1px solid #ffffff;"
											readonly="readonly">
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2"
										style="height: 50px; padding-left: 100px;">
										联系地址(现居住地):
										<input class="horizontalLine"
											value="${user.password.presentAddress}"
											style="width: 320px; border-bottom: 1px solid #000000; border-left: 1px solid #ffffff; border-right: 1px solid #ffffff; border-top: 1px solid #ffffff;"
											readonly="readonly">
									</td>
								</tr>
								<tr>
									<td colspan="2" align="left"
										style="height: 50px; padding-left: 100px;">
										生效时间:
										<input class="horizontalLine" type="text"
											style="width: 160px; border-bottom: 1px solid #000000; border-left: 1px solid #ffffff; border-right: 1px solid #ffffff; border-top: 1px solid #ffffff;"
											name="contract.startDate" value="${contract.startDate}" />
										到期时间:
										<input class="horizontalLine" type="text"
											style="width: 160px; border-bottom: 1px solid #000000; border-left: 1px solid #ffffff; border-right: 1px solid #ffffff; border-top: 1px solid #ffffff;"
											name="contract.endDate" value="${contract.endDate}" />
									</td>

								</tr>
								<tr>
									<td colspan="2" align="left"
										style="height: 50px; padding-left: 100px;">
										试用期:
										<input class="horizontalLine" type="text"
											style="width: 409px; border-bottom: 1px solid #000000; border-left: 1px solid #ffffff; border-right: 1px solid #ffffff; border-top: 1px solid #ffffff;"
											name="contract.endDate"
											value="${contract.probationaryPeriod}" />
									</td>
								</tr>
								<tr>
									<td colspan="2">

									</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
				<s:if test="pageStatus!='view'">
					<div align="center">
						<input type="button" value="打印"
							onclick="pagePrint('printDiv','sy')"
							style="width: 80px; height: 50px;">
					</div>
				</s:if>
				<div id="printDiv2">
					<div style="width: 100%;" align="center">
						<table>
							<tr>
								<td colspan="2" align="left">
									&nbsp;&nbsp;${contract.content}
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<br />
								</td>
							</tr>
							<s:iterator value="provisionList" id="pageProvision"
								status="pageStatus">
								<tr>
									<td colspan="2" align="left">
										&nbsp;&nbsp;${pageProvision.content}
									</td>
								</tr>
							</s:iterator>
							<tr>
								<td colspan="2">
									<br />
									<br />
								</td>
							</tr>
							<tr>
								<td align="left">
									甲 方(盖章):
								</td>
								<td align="left">
									乙方(签字):
								</td>
							</tr>
							<tr>
								<td align="left">
									&nbsp;
								</td>
							</tr>
							<tr>
								<td align="left">
									法 人 代 表
									<br />
									(或委托代理人):_____________
								</td>
							</tr>
							<tr>
								<td align="left">
									&nbsp;
								</td>
							</tr>

							<td align="left">
								签约时间:__________________
							</td>
							<td align="left">
								签约时间:________________
							</td>
							</tr>
							<tr>
								<td colspan="4" align="left">
									&nbsp;
								</td>
							</tr>
						</table>
					</div>
				</div>
				<s:if test="pageStatus!='view'">
					<input type="button" value="打印" onclick="pagePrint('printDiv2')"
						style="width: 80px; height: 50px;">
					<a href="UsersAction!findUserById.action?id=${contract.userId}">打印完成?去处理薪资</a>
				</s:if>
				<s:else>
					<input type="button" value="关闭预览"
						onclick="javascripr:window.close()" />
				</s:else>
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

function submitFormForView(objForm) {
	if (checkContractForm()) {
		objForm.action = "ContractAction!viewContract.action";
		objForm.submit();
		objForm.action = "ContractAction!addContract.action";
	}
}

function submitViewForm() {
	var viewForm = document.getElementById("viewForm");
	viewForm.action = "ContractAction!addContract.action";
	viewForm.submit();
}
</script>


	</body>
</html>
