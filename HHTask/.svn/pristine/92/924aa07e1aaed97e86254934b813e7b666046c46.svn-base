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
		<SCRIPT language="javascript" type="text/javascript">
		onload=chageProcessDiv;
		
	function checkMyForm(){
		var injuryInsurance = document.getElementById("injuryInsurance");
		var maternityInsurance = document.getElementById("maternityInsurance");
		var oldageInsurance = document.getElementById("oldageInsurance");
		var medicalInsurance = document.getElementById("medicalInsurance");
		var unemploymentInsurance = document.getElementById("unemploymentInsurance");
		var housingFund = document.getElementById("housingFund");
		var minimumWage = document.getElementById("minimumWage");
		var validityStartDate = document.getElementById("validityStartDate");
		var validityEndDate = document.getElementById("validityEndDate");
		if (injuryInsurance.value == "") {
			alert("工伤保险不能为空!");
			injuryInsurance.focus();
			return false;
		} else if (maternityInsurance.value  == "") {
			alert("生育保险不能为空!");
			maternityInsurance.focus();
			return false;
		} else if (oldageInsurance.value  == "") {
			alert("养老保险不能为空!");
			oldageInsurance.focus();
			return false;
		} else if (medicalInsurance.value  == "") {
			alert("医疗保险不能为空!");
			medicalInsurance.focus();
			return false;
		} else if (unemploymentInsurance.value  == "") {
			alert("失业保险不能为空!");
			unemploymentInsurance.focus();
			return false;
		} else if (housingFund.value  == "") {
			alert("公积金不能为空!");
			housingFund.focus();
			return false;
		} else if (minimumWage.value  == "") {
			alert("最低工资不能为空!");
			minimumWage.focus();
			return false;
		} else if (validityStartDate.value  == "") {
			alert("开始时间不能为空!");
			validityStartDate.focus();
			return false;
		} else if (validityEndDate.value  == "") {
			alert("结束时间不能为空!");
			validityEndDate.focus();
			return false;
		} else {
			return true;
		}
	}
	
	function chageProcessDiv(){
		var insuranceGoldList="${insuranceGoldList}";
		var insuranceGold="${insuranceGold}";
		var addInsuranceGold=document.getElementById("addInsuranceGold");
		var showAllInsuranceGold=document.getElementById("showAllInsuranceGold");
		var updateInsuranceGold=document.getElementById("updateInsuranceGold");
		if(insuranceGoldList!=""){
			showAllInsuranceGold.style.display="block";
			return ;
		}else if(insuranceGold!=""){
			addInsuranceGold.innerHTML=updateInsuranceGold.innerHTML;
		}
		addInsuranceGold.style.display="block";
	}
</SCRIPT>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">添加缴纳比例</a>
<%--					<a href="InsuranceGoldAction!updateWSByIG.action"--%>
<%--						title="更新养老保险、医疗保险、失业保险、公积金信息" style="color: #ffffff">更新工资模版</a>--%>
					<a href="InsuranceGoldAction!findAllInsuranceGold.action"
						style="color: #ffffff">查询缴纳比例</a>
				</div>
			</div>

			<div align="center" id="addInsuranceGold" style="display: none">
				<form action="InsuranceGoldAction!addInsuranceGold.action"
					onsubmit="return checkMyForm()" method="post">
					<table width="100%" class="table">
						<tr>
							<th colspan="8" style="font-size: 17px;">
								添加个人保险缴纳比例(所填信息为百分比整数部分)
							</th>
						</tr>
						<tr>
							<th align="right">
								户籍
							</th>
							<td>
								<input type="radio" name="insuranceGold.localOrField" value="本地"
									checked="checked">
								本地
								<input type="radio" name="insuranceGold.localOrField" value="外地">
								外地
							</td>
							<th align="right">
								城镇
							</th>
							<td>
								<input type="radio" name="insuranceGold.cityOrCountryside"
									checked="checked" value="城市">
								城市
								<input type="radio" name="insuranceGold.cityOrCountryside"
									value="农村">
								农村
							</td>

							<th align="right">
								保险类型:
							</th>
							<td>
								<input type="radio" name="insuranceGold.personClass" value="城保" />
								城保
								<input type="radio" name="insuranceGold.personClass" value="社保" />
								社保
								<input type="radio" value="其他" />
								其他
								<input type="text" value="" name="insuranceGold.personClass" />
							</td>
						</tr>
						<tr>
							<th align="right">
								养老保险
							</th>
							<td>
								<input id="oldageInsurance" name="insuranceGold.oldageInsurance">
							</td>
							<th align="right">
								医疗保险
							</th>
							<td>
								<input id="medicalInsurance"
									name="insuranceGold.medicalInsurance">
							</td>
							<th align="right">
								公积金
							</th>
							<td>
								<input id="housingFund" name="insuranceGold.housingFund">
							</td>
						</tr>
						<tr>
							<th align="right">
								失业保险
							</th>
							<td>
								<input id="unemploymentInsurance"
									name="insuranceGold.unemploymentInsurance">
							</td>
							<th align="right">
								工伤保险
							</th>
							<td>
								<input id="injuryInsurance" name="insuranceGold.injuryInsurance"
									value="0">
							</td>
							<th align="right">
								生育保险
							</th>
							<td>
								<input id="maternityInsurance"
									name="insuranceGold.maternityInsurance" value="0">
							</td>
						</tr>
						<tr>
							<th colspan="8" style="font-size: 17px;">
								添加企业保险缴纳比例(所填信息为百分比整数部分)
							</th>
						</tr>
						<tr>
							<th align="right">
								养老保险
							</th>
							<td>
								<input id="oldageInsurance"
									name="insuranceGold.QYoldageInsurance">
							</td>
							<th align="right">
								医疗保险
							</th>
							<td>
								<input id="medicalInsurance"
									name="insuranceGold.QYmedicalInsurance">
							</td>
							<th align="right">
								公积金
							</th>
							<td>
								<input id="housingFund" name="insuranceGold.QYhousingFund">
							</td>
						</tr>
						<tr>
							<th align="right">
								失业保险
							</th>
							<td>
								<input id="unemploymentInsurance"
									name="insuranceGold.QYunemploymentInsurance">
							</td>
							<th align="right">
								工伤保险
							</th>
							<td>
								<input id="injuryInsurance"
									name="insuranceGold.QYinjuryInsurance" value="0">
							</td>
							<th align="right">
								生育保险
							</th>
							<td>
								<input id="maternityInsurance"
									name="insuranceGold.QYmaternityInsurance" value="0">
							</td>
						</tr>
						<tr>
							<th colspan="8" style="font-size: 17px;">
								添加其他相关项目
							</th>
						</tr>
						<tr>
							<th align="right">
								最低工资
							</th>
							<td>
								<input type="text" name="insuranceGold.minimumWage"
									id="minimumWage" />
							</td>
							<th align="right">
								开始时间
							</th>
							<td>
								<input class="Wdate" type="text" id="validityStartDate"
									name="insuranceGold.validityStartDate"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<th align="right">
								结束时间
							</th>
							<td>
								<input class="Wdate" type="text" id="validityEndDate"
									name="insuranceGold.validityEndDate"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
						</tr>
						<tr>
							<td colspan="8" align="center">
								<input type="submit" value="添加"
									style="width: 80px; height: 50px">
								&nbsp;&nbsp;
								<input type="reset" value="重置" style="width: 80px; height: 50px">
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div id="showAllInsuranceGold" style="display: none">
				<table  class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th>
							序号
						</th>
						<th>
							户籍
						</th>
						<th>
							城镇
						</th>
						<th>
							保险类型
						</th>
						<th>
							工伤保险
						</th>
						<th>
							生育保险
						</th>
						<th>
							养老保险
						</th>
						<th>
							医疗保险
						</th>
						<th>
							失业保险
						</th>
						<th>
							公积金
						</th>
						<th>
							最低工资
						</th>
						<th>
							开始时间
						</th>
						<th>
							结束时间
						</th>
						<th>
							操作
						</th>
					</tr>

					<s:iterator id="pageInsuranceGold" value="insuranceGoldList"
						status="iteratorStatus">
						<s:if test="#iteratorStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#iteratorStatus.index+1" />
						</td>
						<td align="center">
							${pageInsuranceGold.localOrField}
						</td>
						<td align="center">
							${pageInsuranceGold.cityOrCountryside}
						</td>
						<td align="center">
							${pageInsuranceGold.personClass}
						</td>
						<td align="center">
							${pageInsuranceGold.injuryInsurance}
						</td>
						<td align="center">
							${pageInsuranceGold.maternityInsurance}
						</td>
						<td align="center">
							${pageInsuranceGold.oldageInsurance}
						</td>
						<td align="center">
							${pageInsuranceGold.medicalInsurance}
						</td>
						<td align="center">
							${pageInsuranceGold.unemploymentInsurance}
						</td>
						<td align="center">
							${pageInsuranceGold.housingFund}
						</td>
						<td align="center">
							${pageInsuranceGold.minimumWage}
						</td>
						<td align="center">
							${pageInsuranceGold.validityStartDate}
						</td>
						<td align="center">
							${pageInsuranceGold.validityEndDate}
						</td>
						<td align="center">
							<a
								href="InsuranceGoldAction!delInsuranceGold.action?id=${pageInsuranceGold.id}">删除</a>/
							<a
								href="InsuranceGoldAction!findInsuranceGoldById.action?id=${pageInsuranceGold.id}">修改</a>
						</td>
					</s:iterator>
					</tr>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="14" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="14" align="center" style="color: red">
								${errorMessage}
						</s:else>
						</td>
					</tr>
				</table>
			</div>
			<div id="updateInsuranceGold" style="display: none">
				<form action="InsuranceGoldAction!addInsuranceGold.action"
					onsubmit="return checkMyForm()" method="post">
					<input type="hidden" name="id" value="${insuranceGold.id}">
					<table class="table">
						<tr>
							<th colspan="8">
								修改个人保险缴纳比例(所填信息为百分比整数部分)
							</th>
						</tr>
						<tr>
							<th align="right">
								户籍
							</th>
							<td>
								<s:if test="insuranceGold.localOrField=='本地'">
									<input type="radio" name="insuranceGold.localOrField"
										value="本地" checked="checked">
									本地
									<input type="radio" name="insuranceGold.localOrField"
										value="外地">
									外地
									</s:if>
								<s:else>
									<input type="radio" name="insuranceGold.localOrField"
										value="本地">
									本地
									<input type="radio" name="insuranceGold.localOrField"
										value="外地" checked="checked">
									外地
									</s:else>
							</td>
							<th align="right">
								城镇
							</th>
							<td>
								<s:if test="insuranceGold.cityOrCountryside=='城市'">
									<input type="radio" name="insuranceGold.cityOrCountryside"
										checked="checked" value="城市">
										城市
										<input type="radio" name="insuranceGold.cityOrCountryside"
										value="农村">
										农村
									</s:if>
								<s:else>
									<input type="radio" name="insuranceGold.cityOrCountryside"
										value="城市">
										城市
										<input type="radio" name="insuranceGold.cityOrCountryside"
										checked="checked" value="农村">
										农村
									</s:else>
							</td>
							<th align="right">
								保险类型:
							</th>
							<td>
								<s:if test="insuranceGold.personClass=='城保'">
									<input type="radio" name="insuranceGold.personClass" value="城保"
										checked="checked" />
								城保
								<input type="radio" name="insuranceGold.personClass" value="社保" />
								社保
									</s:if>
								<s:else>
									<input type="radio" name="insuranceGold.personClass" value="城保" />
								城保
								<input type="radio" name="insuranceGold.personClass" value="社保"
										checked="checked" />
								社保
									</s:else>
							</td>
						</tr>
						<tr>
							<th align="right">
								养老保险
							</th>
							<td>
								<input id="oldageInsurance" name="insuranceGold.oldageInsurance"
									value="${insuranceGold.oldageInsurance}">
							</td>
							<th align="right">
								医疗保险
							</th>
							<td>
								<input id="medicalInsurance"
									name="insuranceGold.medicalInsurance"
									value="${insuranceGold.medicalInsurance}">
							</td>
							<th align="right">
								公积金
							</th>
							<td>
								<input id="housingFund" name="insuranceGold.housingFund"
									value="${insuranceGold.housingFund}">
							</td>
						</tr>
						<tr>
							<th align="right">
								失业保险
							</th>
							<td>
								<input id="unemploymentInsurance"
									name="insuranceGold.unemploymentInsurance"
									value="${insuranceGold.unemploymentInsurance}">
							</td>
							<th align="right">
								工伤保险
							</th>
							<td>
								<input id="injuryInsurance" name="insuranceGold.injuryInsurance"
									value="${insuranceGold.injuryInsurance}">
							</td>
							<th align="right">
								生育保险
							</th>
							<td>
								<input id="maternityInsurance"
									name="insuranceGold.maternityInsurance"
									value="${insuranceGold.maternityInsurance}">
							</td>
						</tr>
						<tr>
							<th colspan="8">
								修改企业保险缴纳比例(所填信息为百分比整数部分)
							</th>
						</tr>
						<tr>
							<th align="right">
								养老保险
							</th>
							<td>
								<input id="oldageInsurance"
									name="insuranceGold.QYoldageInsurance"
									value="${insuranceGold.QYoldageInsurance}">
							</td>
							<th align="right">
								医疗保险
							</th>
							<td>
								<input id="medicalInsurance"
									name="insuranceGold.QYmedicalInsurance"
									value="${insuranceGold.QYmedicalInsurance}">
							</td>
							<th align="right">
								公积金
							</th>
							<td>
								<input id="housingFund" name="insuranceGold.QYhousingFund"
									value="${insuranceGold.QYhousingFund}">
							</td>
						</tr>
						<tr>
							<th align="right">
								失业保险
							</th>
							<td>
								<input id="unemploymentInsurance"
									name="insuranceGold.QYunemploymentInsurance"
									value="${insuranceGold.QYunemploymentInsurance}">
							</td>
							<th align="right">
								工伤保险
							</th>
							<td>
								<input id="injuryInsurance"
									name="insuranceGold.QYinjuryInsurance"
									value="${insuranceGold.QYinjuryInsurance}">
							</td>
							<th align="right">
								生育保险
							</th>
							<td>
								<input id="maternityInsurance"
									name="insuranceGold.QYmaternityInsurance"
									value="${insuranceGold.QYmaternityInsurance}">
							</td>
						</tr>
						<tr>
							<th colspan="8">
								修改其他相关项目
							</th>
						</tr>
						<tr>
							<th align="right">
								最低工资
							</th>
							<td>
								<input type="text" name="insuranceGold.minimumWage"
									id="minimumWage" value="${insuranceGold.minimumWage}" />
							</td>
							<th align="right">
								开始时间
							</th>
							<td>
								<input class="Wdate" type="text"
									name="insuranceGold.validityStartDate"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"
									value="${insuranceGold.validityStartDate}" />
							</td>
							<th align="right">
								结束时间
							</th>
							<td>
								<input class="Wdate" type="text"
									name="insuranceGold.validityEndDate"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"
									value="${insuranceGold.validityEndDate}" />
							</td>
						</tr>
						<tr>
							<td colspan="8" align="center">
								<input type="submit" value="修改"
									style="width: 80px; height: 50px">
								&nbsp;&nbsp;
								<input type="reset" value="重置" style="width: 80px; height: 50px">
							</td>
						</tr>
					</table>
				</form>
			</div>

			<div align="center">
				<font color="red">${successMessage}</font>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
	</body>
















</html>
