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
		<div id="bodyDiv" align="center" class="transDiv">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 980px;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">工序外委</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<div align="center">
						<table class="table">
							<tr>
								<th colspan="6">
									历史组合方式
								</th>
							</tr>
							<tr bgcolor="#c0dcf2" height="30px">
								<td align="center">
									序号
								</td>
								<td align="center">
									工序号
								</td>
								<td align="center">
									工序名称
								</td>
								<td align="center">
									供应商
								</td>
								<td align="center">
									外委类型
								</td>
								<td align="center">
									有效期
								</td>
							</tr>
							<s:iterator value="priceList" id="pageprice" status="pageStatus">
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
										<font color="#c0dcf2">
									</s:else>
									<s:property value="#pageStatus.index+1" />
									</font>
								</td>
								<td align="left">
									${pageprice.gongxunum}
								</td>
								<td align="left">
									${pageprice.processNames}
								</td>
								<td align="center">
									${pageprice.gys}
								</td>
								<td align="center">
									${pageprice.pricePeriodStart}-${pageprice.pricePeriodEnd}
								</td>
								<td align="center">
									<s:if test="#pageprice.wwType==null">
								工序外委
							</s:if>
									<s:else>
							${pageprice.wwType}
							</s:else>
								</td>
								</tr>
							</s:iterator>
						</table>
					</div>
					<br />
					<div id="show1">
						<table class="table">
							<tr>
								<td align="center">
									全选
									<input type="checkbox" id="checkAll" onchange="chageAllCheck()">
									序号
								</td>
								<td align="center">
									工序号
								</td>
								<td align="center">
									工序名称
								</td>
								<td align="center">
									生产类型
								</td>
								<td align="center">
									可领数量
								</td>
								<td align="center">
									已领数量
								</td>
								<td align="center">
									提交数量
								</td>
								<td align="center">
									不合格数量
								</td>
								<td align="center">
									申请中数量
								</td>
								<td align="center">
									同意数量
								</td>
								<td align="center">
									已选数量
								</td>
							</tr>
							<s:iterator value="processList" id="pageProcess"
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
									<s:if test="#pageProcess.productStyle=='自制'">
										<input type="checkbox" name="checkboxs"
											value="<s:property value="#pageStatus.index" />"
											onchange="chageNum()">
									</s:if>
									<s:if test="#pageStatus.index%2==1">
										<font>
									</s:if>
									<s:else>
										<font color="#c0dcf2">
									</s:else>
									<s:property value="#pageStatus.index+1" />
									</font>
								</td>
								<td align="center">
									<input id="hprocessNO<s:property value="#pageStatus.index" />"
										value="${pageProcess.processNO}" type="hidden">
									${pageProcess.processNO}
								</td>
								<td align="left">
									<input
										id="hprocessName<s:property value="#pageStatus.index" />"
										value="${pageProcess.processName}" type="hidden">
									${pageProcess.processName}
								</td>
								<td align="center">
									${pageProcess.productStyle}
								</td>
								<td>
									<input id="htotalCount<s:property value="#pageStatus.index" />"
										value="${pageProcess.totalCount}" type="hidden">
									${pageProcess.totalCount}
								</td>
								<td align="center">
									<input id="happlyCount<s:property value="#pageStatus.index" />"
										value="${pageProcess.applyCount}" type="hidden">
									${pageProcess.applyCount}
								</td>
								<td align="center">
									<input
										id="hsubmmitCount<s:property value="#pageStatus.index" />"
										value="${pageProcess.submmitCount}" type="hidden">
									${pageProcess.submmitCount}
								</td>
								<td align="center">
									<input id="breakCount<s:property value="#pageStatus.index" />"
										value="${pageProcess.breakCount}" type="hidden">
									${pageProcess.breakCount}
								</td>
								<td align="center">
									<input
										id="happlyWwCount<s:property value="#pageStatus.index" />"
										value="${pageProcess.applyWwCount}" type="hidden">
									${pageProcess.applyWwCount}
								</td>
								<td align="center">
									<input
										id="hagreeWwCount<s:property value="#pageStatus.index" />"
										value="${pageProcess.agreeWwCount}" type="hidden">
									${pageProcess.agreeWwCount}
								</td>
								<td align="center">
									<input
										id="hselectWwCount<s:property value="#pageStatus.index" />"
										value="${pageProcess.selectWwCount}" type="hidden">
									${pageProcess.selectWwCount}
								</td>
								</tr>
							</s:iterator>
							<tr>
								<td colspan="11" align="center">
									<input type="button" style="width: 80px; height: 40px;"
										onclick="addgx('工序外委');" value="工序外委">
									<input type="button" style="width: 80px; height: 40px;"
										onclick="addgx('包工包料');" value="包工包料">
								</td>
							</tr>
						</table>
					</div>
					<div id="show2" style="display: none;">
						<table id="sonCardTable" class="table">
						</table>
					</div>
				</div>
			</div>
		</div>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h3>
					工序外委申请
				</h3>
			</div>
			<div align="center">
				<table class="table">
					<tr>
						<th>
							件号
						</th>
						<td>
							${procard.markId}
						</td>
						<th>
							名称
						</th>
						<td>
							${procard.proName}
						</td>
						<th>
							批次
						</th>
						<td>
							${procard.selfCard}
						</td>
						<th>
							生产数量
						</th>
						<td>
							${procard.filnalCount}
						</td>
					</tr>
				</table>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							工序号
						</td>
						<td align="center">
							工序名称
						</td>
						<td align="center">
							生产类型
						</td>
						<td align="center">
							可领数量
						</td>
						<td align="center">
							已领数量
						</td>
						<td align="center">
							提交数量
						</td>
						<td align="center">
							不合格数量
						</td>
						<td align="center">
							申请中数量
						</td>
						<td align="center">
							同意数量
						</td>
						<td align="center">
							已选数量
						</td>
					</tr>
					<s:iterator value="processList" id="pageProcess"
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
								<font color="#c0dcf2">
							</s:else>
							<s:property value="#pageStatus.index+1" />
							</font>
						</td>
						<td align="center">
							<label id="processNO<s:property value="#pageStatus.index" />">
								${pageProcess.processNO}
							</label>
						</td>
						<td align="left">
							<label id="processName<s:property value="#pageStatus.index" />">
								${pageProcess.processName}
							</label>
						</td>
						<td align="center">
							${pageProcess.productStyle}
						</td>
						<td>
							<label id="totalCount<s:property value="#pageStatus.index" />">
								${pageProcess.totalCount}
							</label>
						</td>
						<td align="center">
							<label id="applyCount<s:property value="#pageStatus.index" />">
								${pageProcess.applyCount}
							</label>
						</td>
						<td align="center">
							<label id="submmitCount<s:property value="#pageStatus.index" />">
								${pageProcess.submmitCount}
							</label>
						</td>
						<td align="center">
							<label id="breakCount<s:property value="#pageStatus.index" />">
								${pageProcess.breakCount}
							</label>
						</td>
						<td align="center">
							<label id="applyWwCount<s:property value="#pageStatus.index" />">
								${pageProcess.applyWwCount}
							</label>
						</td>
						<td align="center">
							<label id="agreeWwCount<s:property value="#pageStatus.index" />">
								${pageProcess.agreeWwCount}
							</label>
						</td>
						<td align="center">
							<label id="selectWwCount<s:property value="#pageStatus.index" />">
								${pageProcess.selectWwCount}
							</label>
						</td>
						</tr>
					</s:iterator>
				</table>
			</div>
			<div align="center">
				<h3>
					历史组合方式
				</h3>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="30px">
						<td align="center">
							序号
						</td>
						<td align="center">
							工序号
						</td>
						<td align="center">
							工序名称
						</td>
						<td align="center">
							供应商
						</td>
						<td align="center">
							外委类型
						</td>
						<td align="center">
							有效期
						</td>
					</tr>
					<s:iterator value="priceList" id="pageprice" status="pageStatus">
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
								<font color="#c0dcf2">
							</s:else>
							<s:property value="#pageStatus.index+1" />
							</font>
						</td>
						<td align="left">
							${pageprice.gongxunum}
						</td>
						<td align="left">
							${pageprice.processNames}
						</td>
						<td align="center">
							${pageprice.gys}
						</td>
						<td align="center">
							<s:if test="#pageprice.wwType==null">
								工序外委
							</s:if>
							<s:else>
							${pageprice.wwType}
							</s:else>
						</td>
						<td align="center">
							${pageprice.pricePeriodStart}—${pageprice.pricePeriodEnd}
						</td>
						</tr>
					</s:iterator>
				</table>
			</div>
			<div>
<%--				<div align="center">--%>
<%--					<h3>--%>
<%--						工序外委申请预选--%>
<%--						<input type="button" value="添加外委" class="input"--%>
<%--							onclick="addgxyx();">--%>
<%--					</h3>--%>
<%--				</div>--%>
<%--				<div>--%>
<%--					<form id="yxForm" method="post">--%>
<%--						<input type="hidden" name='id' value="${id}">--%>
<%--						<table id="yxtable" class="table">--%>
<%--							<tr bgcolor="#c0dcf2" height="30px">--%>
<%--								<th>--%>
<%--									工序号--%>
<%--								</th>--%>
<%--								<th>--%>
<%--									件号--%>
<%--								</th>--%>
<%--								<th>--%>
<%--									工序名称--%>
<%--								</th>--%>
<%--								<th>--%>
<%--									工序外委数量--%>
<%--								</th>--%>
<%--								<th>--%>
<%--									合同状态--%>
<%--								</th>--%>
<%--								<th>--%>
<%--									外委方式--%>
<%--								</th>--%>
<%--								<th>--%>
<%--									外委下层组件--%>
<%--								</th>--%>
<%--								<th>--%>
<%--									关联件号--%>
<%--								</th>--%>
<%--								<th>--%>
<%--									操作--%>
<%--								</th>--%>
<%--							</tr>--%>
<%--							<tr id="btnTr" style="display: none;">--%>
<%--								<td align="center" colspan="9">--%>
<%--									<input id="submityxid" type="button" onclick="submityx(${id2});" value="确定"--%>
<%--										style="width: 80px; height: 50x;">--%>
<%--								</td>--%>
<%--							</tr>--%>
<%--						</table>--%>
<%--					</form>--%>
<%--				</div>--%>
			</div>
			<br />
			<div align="left">
				<h3>
					<font color="red">1.此处提交并不是提交申请只是挑选需要外委申请的工序<br />
						2.工序不要重复选择<br /> 步骤一、点击添加选择要外委的工序，和外委类型。<br />
						步骤二、如果是包工包料则点击关联件号，选择包料的件号。<br /> 步骤三、点击选择，提交本次所选的外委内容。<br /> </font>
				</h3>
				<br />
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
var htProcess = "";
$(document).ready(function() {
	htProcess = "${tag}";
})

function checkAppCount(index) {//结构改变此方法作废
	var thisCount = $("#thisCount" + index).val();
	if (isNaN(thisCount)) {
		alert("请输入数字");
		$("#thisCount" + index).val(0);
		return false;
	}
	var totalCount = "${procard.filnalCount}";
	var applyCount = $("#applyCount" + index).val();
	//var submmitCount=$("submmitCount"+index).val();
	//var breakCount=$("breakCount"+index).val();
	var applyWwCount = $("#applyWwCount" + index).val();
	var selectWwCount = $("#selectWwCount" + index).val();
	//var agreeWwCount=$("agreeWwCount"+index).val();
	var maxNumber = totalCount - applyCount - applyWwCount - selectWwCount;
	if (thisCount > maxNumber) {
		alert("对不起,您最多可申请" + maxNumber + "!");
		$("#thisCount" + index).val(maxNumber);
	}
}

function chageAllCheck() {//全选框
	var checkAll = document.getElementById("checkAll");
	var checkboxs = document.getElementsByName("checkboxs");
	if (checkAll.checked == true) {
		for ( var i = 0; i < checkboxs.length; i++) {
			checkboxs[i].checked = true;
		}
	} else {
		for ( var i = 0; i < checkboxs.length; i++) {
			checkboxs[i].checked = false;
		}
	}

}

function chageAllCheck2() {//全选框
	var checkAll = document.getElementById("checkAll2");
	var checkboxs = document.getElementsByName("checkboxs2");
	if (checkAll.checked == true) {
		for ( var i = 0; i < checkboxs.length; i++) {
			checkboxs[i].checked = true;
		}
	} else {
		for ( var i = 0; i < checkboxs.length; i++) {
			checkboxs[i].checked = false;
		}
	}

}
function chageNum() {
	var checkAll = document.getElementById("checkAll");
	var checkboxs = document.getElementsByName("checkboxs");
	var count = 0;
	for ( var i = 0; i < checkboxs.length; i++) {
		if (checkboxs[i].checked == false) {
			checkAll.checked = false;
		} else {
			count++;
		}
	}
	if (count == checkboxs.length) {
		checkAll.checked = true;
	}

}
function chageNum2() {
	var checkAll = document.getElementById("checkAll2");
	var checkboxs = document.getElementsByName("checkboxs2");
	var count = 0;
	for ( var i = 0; i < checkboxs.length; i++) {
		if (checkboxs[i].checked == false) {
			checkAll.checked = false;
		} else {
			count++;
		}
	}
	if (count == checkboxs.length) {
		checkAll.checked = true;
	}

}
function addgxyx() {//弹出添加页面
	$("#show1").show();
	$("#show2").hide();
	chageDiv("block");
	var checkAll = document.getElementById("checkAll");
	checkAll.checked = false;
	var checkboxs = document.getElementsByName("checkboxs");
	for ( var i = 0; i < checkboxs.length; i++) {
		checkboxs[i].checked = false;
	}
}
var trindex = 0;
var trcount = 0;
function addgx(wwType) {//选中工序并关闭添加页面
	var checkAll = document.getElementById("checkAll");
	var checkboxs = document.getElementsByName("checkboxs");
	var totalCount = "${procard.filnalCount}";
	var maxcount = totalCount;//最大可预选数量
	var minprocessNO = "${processNO}";//第一道工序的工序号
	var processNos = "";
	var processNames = "";
	var count = 0
	var flg = false;//是否含有第一道工序
	for ( var i = 0; i < checkboxs.length; i++) {
		if (checkboxs[i].checked == false) {
			//checkAll.checked = false;
		} else {
			//count++;
			var index = checkboxs[i].value;
			var processNO = $("#hprocessNO" + index).val();
			if (minprocessNO == processNO) {
				flg = true;
			}
			var processName = $("#hprocessName" + index).val();
			var applyCount = $("#happlyCount" + index).val();
			var applyWwCount = $("#happlyWwCount" + index).val();
			var selectWwCount = $("#hselectWwCount" + index).val();
			var maxNumber = totalCount - applyCount - applyWwCount
					- selectWwCount;
			if (maxNumber - maxcount < 0) {
				maxcount = maxcount;
			}
			if (count == 0) {
				processNos += processNO;
				processNames += processName;
			} else {
				processNos += ";" + processNO;
				processNames += ";" + processName;
			}
			count++;
		}
	}
	if (!flg && wwType == "包工包料") {
		alert("只有第一道工序外委才能包工包料!");
		return false;
	}
	var htStatus = "<font color='red'>无</font>";
	var processNozt = htProcess.split(",");
	for ( var i = 0; i < processNozt.length; i++) {
		var noAndType = processNozt[i].split("_")
		if (noAndType[0] == processNos && noAndType[1] == wwType) {
			htStatus = "<font color='gree'>可用</font>";
			break;
		}
	}
	var jlxc="是";
	if(wwType == "工序外委"){
		jlxc = "<input value='是' type='radio'  name='pwwApplyDetailList["+trindex+"].relatDown'> 是 &nbsp;<input checked='checked' value='否' type='radio' name='pwwApplyDetailList["+trindex+"].relatDown'> 否"
	}
	var html = "<tr id='tr"
			+ trindex
			+ "'>"
			+ "<td align='center'>${procard.markId}"
			+ "</td>"
			+ "<td align='center'>"
			+ processNos
			+ "<input type='hidden' class='pnos' name='pwwApplyDetailList["
			+ trindex
			+ "].processNOs' value='"
			+ processNos
			+ "'></td>"
			+ "<td align='center'>"
			+ processNames
			+ "<input type='hidden' class='pnames' name='pwwApplyDetailList["
			+ trindex
			+ "].processNames' value='"
			+ processNames
			+ "'></td>"
			+ "<td align='center'><input style='display:none;' id='count"
			+ trindex
			+ "' value='"
			+ maxcount
			+ "'>"
			+ "<input id='thisCount"
			+ trindex
			+ "' name='pwwApplyDetailList["
			+ trindex
			+ "].applyCount' class='sameCount' value='"
			+ maxcount
			+ "' onkeyup='checkyxCount("
			+ trindex
			+ ")'></td>"
			+ "<td align='center'>"
			+ htStatus
			+ "</td>"
			+ "<td align='center'><font size='4'>"
			+ wwType
			+ "</font><input type='hidden' class='ptypes' name='pwwApplyDetailList["
			+ trindex
			+ "].wwType' value='"
			+ wwType
			+ "'></td>"
			+ "<td align='center'>"
			+ jlxc
			+ "</td>"
			+ "<td align='center'><lable id='wwMarkId"
			+ trindex
			+ "'></lable>"
			+ "<input id='wwMarkIdInput"
			+ trindex
			+ "' type='hidden' name='pwwApplyDetailList["
			+ trindex
			+ "].wwMarkId' value=''>"
			+ "<input type='button' value='关联件号' style='width: 70px;height: 20px' onclick='glwwMarkId(\""
			+ trindex
			+ "\");'>"
			+ "</td>"
			+ "<td align='center'><input type='button' value='删除' style='width: 60px;height: 20px' onclick='deletegxyx(\""
			+ trindex + "\");'></td>" + "</tr>"
	$("#yxtable>tbody>tr").eq(0 + trcount).after(html);
	trindex++;
	trcount++;
	$("#btnTr").show();
	chageDiv('none');
}

function deletegxyx(index) {
	var deletetr = $("#tr" + index);
	deletetr.remove();
	trcount--;
	if (trcount == 0) {
		$("#btnTr").hide();
	}
}
function checkyxCount(index) {
	var thisCount = $("#thisCount" + index).val();
	if (isNaN(thisCount)) {
		alert("请输入数字");
		$("#thisCount" + index).val(0);
		return false;
	}
	var maxCount = $("#count" + index).val();
	if ((thisCount - maxCount) > 0) {
		alert("最多可预选" + maxCount + "道工序!");
		$(".samecount").val(maxCount);
		return false;
	}
	$(".samecount").val(thisCount);
}
function glwwMarkId(index) {
	$("#sonCardTable").empty();
	$("#show2").show();
	$("#show1").hide();
	chageDiv("block");
	$
			.ajax( {
				type : "post",
				url : "ProcardAction!findSonMarkId.action",
				dataType : "json",
				data : {
					id : "${id}"
				},
				success : function(data) {
					var i = 0;
					var htmlbefor = "";
					var html = "";
					var htmlend = "";
					$(data)
							.each(
									function() {
										if (i % 2 == 0) {
											html += "<tr><td><input type='checkbox' name='checkboxs2' value='"
													+ i
													+ "' onchange='chageNum2()'></td>"
													+ "<td align='center'><input type='hidden' id='markId"
													+ i
													+ "' value='"
													+ data[i][0]
													+ "'>"
													+ data[i][0]
													+ "</td>"
													+ "<td align='center'>"
													+ data[i][1]
													+ "</td>"
													+ "<td align='center'>"
													+ data[i][2]
													+ "</td>"
													+ "<td align='center'>"
													+ data[i][3] + "</td>";
										} else {
											html += "<td><input type='checkbox' name='checkboxs2' value='"
													+ i
													+ "' onchange='chageNum2()'></td>"
													+ "<td align='center'><input type='hidden' id='markId"
													+ i
													+ "' value='"
													+ data[i][0]
													+ "'>"
													+ data[i][0]
													+ "</td>"
													+ "<td align='center'>"
													+ data[i][1]
													+ "</td>"
													+ "<td align='center'>"
													+ data[i][2]
													+ "</td>"
													+ "<td align='center'>"
													+ data[i][3] + "</td></tr>";
										}
										i++;
									});
					if (i == 1) {
						htmlbefor = "<tr><th>全选 <input type='checkbox' id='checkAll2' onchange='chageAllCheck2()'>选择</th>" + "<th>件号</th>"
								+ "<th>名称</th>" + "<th>规格</th>"
								+ "<th>库存</th></tr>"
						html += "</tr>";
						htmlend = "<tr><td align='center' colspan='5'><input type='button' value='确定' style='width: 60px;height: 20px' onclick='setMarkId(\""
								+ index + "\");'></td></tr>"
					} else {
						htmlbefor = "<tr><th>全选 <input type='checkbox' id='checkAll2' onchange='chageAllCheck2()'>选择</th>" + "<th>件号</th>"
								+ "<th>名称</th>" + "<th>规格</th>" + "<th>库存</th>"
								+ "<th>选择</th>" + "<th>件号</th>" + "<th>名称</th>"
								+ "<th>规格</th>" + "<th>库存</th></tr>"
						if (i % 2 == 1) {
							html += "<td></td>" + "<td></td>" + "<td></td>"
									+ "<td></td>" + "<td></td></tr>";
						}
						htmlend = "<tr><td align='center' colspan='10'><input type='button' value='确定' style='width: 60px;height: 20px' onclick='setMarkId(\""
								+ index + "\");'></td></tr>"
					}
					$("#sonCardTable").append(htmlbefor + html + htmlend);
				}
			});
}
function setMarkId(index) {
	var checkboxs = document.getElementsByName("checkboxs2");
	var markIds = "";
	var index2 = -1;
	for ( var i = 0; i < checkboxs.length; i++) {
		if (checkboxs[i].checked == false) {
			//checkAll.checked = false;
		} else {
			//count++;
			index2 = checkboxs[i].value;
			var markId = $("#markId" + index2).val();
			if (markIds == "") {
				markIds = markId;
			} else {
				markIds += ";" + markId;
			}
		}
	}
	if(markIds==""){
		alert("请先选择零件!");
		return false;
	}
	$("#wwMarkId" + index).html(markIds + "</br>");
	$("#wwMarkIdInput" + index).val(markIds);
	chageDiv('none');
}
function submityx(tbIndex) {
	$("#submityxid").attr("disabled","disabled");
	$.ajax( {
		type : "post",
		url : "ProcardAction!wwyx.action",
		dataType : "json",
		data : $("#yxForm").serialize(),
		success : function(data) {
			if (data=="true") {
				window.parent.parent.location.href="ProcardAction!towwyx.action?id="+parent.$("#rootId").val()+"&tag="+tbIndex;
<%--				var html = "";--%>
<%--				$.each($(".pnos"), function(i, val) {--%>
<%--					html += "<tr bgcolor='yellow'>" + "<td width='22%'>"--%>
<%--							+ $(".pnos:eq(" + i + ")").val() + "</td>"--%>
<%--							+ "<td width='33%'>"--%>
<%--							+ $(".pnames:eq(" + i + ")").val() + "</td>"--%>
<%--							+ "<td width='22%'>"--%>
<%--							+ $(".sameCount:eq(" + i + ")").val() + "</td>"--%>
<%--							+ "<td width='22%'>"--%>
<%--							+ $(".ptypes:eq(" + i + ")").val() + "</td>"--%>
<%--							+ "</tr>";--%>
<%--				});--%>
<%----%>
<%--				if (html != "") {--%>
<%--					parent.$("#processTb" + tbIndex).append(html);--%>
<%--					parent.$("#procardTr" + tbIndex).removeClass("showall");--%>
<%--					parent.$("#procardTr" + tbIndex).addClass("showselected");--%>
<%--					parent.chageDiv("none");--%>
<%--				}--%>
			} else {
				alert(data);
			}
		}
	});
}

$(function() {
	var main = $(window.parent.document).find("#showProcess");//找到iframe对象
	if (main != null) {
		var thisheight = document.body.scrollHeight;
		var thisheight2 = parseFloat(thisheight);
		main.height(thisheight2 + 500);//为iframe高度赋值如果高度小于500，则等于500，反之不限高，自适应
	}
})
</script>
	</body>
</html>
