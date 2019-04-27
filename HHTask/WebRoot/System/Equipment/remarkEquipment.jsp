<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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
						style="color: #ffffff">刷新</a>
				</div>
			</div>

			<div align="center">
				<form action="EquipmentAction!upremarkMaintenance.action"
					method="post">
					<input type="hidden" value="${id}" name="id" />
					<input type="hidden" value="${pageStatus}" name="pageStatus" />
					<input type="hidden" value="${status}" name="status">
					<table class="table">
						<tbody>
							<tr>
								<td colspan="20" align="center"
									style="font-family: 微软雅黑; font-weight: bold;">
									申请回复操作
								</td>
							</tr>
							<tr>
								<th>
									工区
								</th>
								<td>
									<input type="hidden" value="${maintenance.id}"
										name="maintenance.id" />
									<input type="text" disabled="disabled" type="hidden"
										name="maintenance.workArea" value="${maintenance.workArea}" />
								</td>

								<th>
									工位
								</th>
								<td>

									<input type="text" disabled="disabled" type="hidden"
										name="maintenance.workPosition"
										value="${maintenance.workPosition}" />
								</td>
								<th>
									设备编号
								</th>
								<td>
									<input type="text" disabled="disabled" type="hidden"
										name="maintenance.no" value="${maintenance.no}" />
								</td>
							</tr>
							<tr>
								<th>
									设备类型
								</th>
								<td>
									<input type="text" disabled="disabled" type="hidden"
										name="maintenance.type" value="${maintenance.type}" />
								</td>

								<th>
									设备名称
								</th>

								<td>
									<input type="text" disabled="disabled" type="hidden"
										name="maintenance.name" value="${maintenance.name}">


								</td>

								<th>
									所在班组
								</th>
								<td>
									<input type="text" disabled="disabled" type="hidden"
										name="maintenance.classGroup"
										value="${maintenance.classGroup}" />
								</td>
							</tr>
							<tr>
								<th>
									报修时间
								</th>
								<td>
									<input type="text" disabled="disabled" type="hidden"
										name="maintenance.alarmTime"
										value="<fmt:formatDate value='${maintenance.alarmTime}'
										pattern='yyyy-MM-dd HH:mm:ss' />" />
								</td>
								<th>
									修理人
								</th>
								<td colspan="3">
									<s:if
										test='maintenance.status!="故障" && maintenance.status!="故障指派"'>
										<input type="text" disabled="disabled" type="hidden"
											name="maintenance.repairMan" value="${maintenance.repairMan}" />
									</s:if>
									<s:else>
										<select id="repairMan" name="maintenance.repairMan"
											style="width: 100px;">
											<option></option>
											<s:iterator value="list" id="pagePeople">
												<option value="${pagePeople.repairname}">
													${pagePeople.repairname}
												</option>
											</s:iterator>
										</select>
										<input type="button" id="addrepairMan" value="增加维修人员"
											onclick="addPeople()">
									</s:else>
								</td>
							</tr>
							<tr>

								<th>
									故障状况
								</th>
								<td>

									<input type="text" disabled="disabled" type="hidden"
										style="width: 250px; height: 80px;"
										name="maintenance.faultDetail"
										value="${maintenance.faultDetail}" />
								</td>
								<s:if
									test='maintenance.status!="故障" && maintenance.status!="故障指派"'>
									<th align="right">
										修复说明:
									</th>
									<td>
										<input type="text" style="width: 250px; height: 80px;"
											value="${maintenance.faultReason}"
											name="maintenance.faultReason" />
									</td>
								</s:if>
							</tr>
							<s:if
								test='maintenance.status!="故障" && maintenance.status!="故障指派"'>
								<tr>
									<th align="right">
										更换零件:
									</th>
									<td colspan="5">
										<input type="radio" name="partsradio"
											onclick="showOrClose('maintenaceTab','block');closeLing('block');"
											value="yes">
										是
										<input type="radio" name="partsradio" checked="checked"
											onclick="showOrClose('maintenaceTab','none');closeLing('none');"
											value="no">
										否
									</td>
								</tr>
							</s:if>
					</table>
					<table class="table" id="maintenaceTab" align="center"
						style="display: none;">
						<tbody>
							<tr>
								<th>
									零件名称
								</th>
								<th>
									零件规格
								</th>
								<th>
									零件数量
								</th>
								<th>
									单位
								</th>
								<th>
									单价
								</th>
							</tr>
							<tr id="parts">
								<td>
									<input type="text" value="${parts.partName}" name="partName" />
								</td>
								<td>
									<input type="text" value="${parts.pictureNo}" name="pictureNo" />
								</td>
								<td>
									<input type="text" value="${parts.num}" name="num"
										onkeyup="this.value=this.value.replace(/\D/g,'')"
										onafterpaste="this.value=this.value.replace(/\D/g,'')" />
								</td>
								<td>
									<input type="text" value="${parts.unit}" name="unit" />
								</td>
								<td>
									<input type="text" value="${parts.more}" name="more"
										onkeyup="this.value=this.value.replace(/\D/g,'')"
										onafterpaste="this.value=this.value.replace(/\D/g,'')" />
								</td>
							</tr>
							<tr id="uploadtr">
								<td align="right" colspan="5">
									<input type="button" value="添加零件" onclick="addProvision()"
										style="width: 80; height: 30px;">
								</td>
							</tr>
						</tbody>
					</table>
					<div align="center">
						<input type="submit" value="操作"
							style="width: 100px; height: 50px;">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="reset" value="重置" style="width: 100px; height: 50px;">
					</div>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>

		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
var provisionSize = 0;
function addProvision() {
	var _tbody = document.getElementById("maintenaceTab").tBodies[0];//获得第一个tbody
	var uploadtr = document.getElementById("uploadtr");//将要在该Tr之前添加元素

	var _tr = document.createElement("tr");
	_tr.id = "createTr" + provisionSize;
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

	var td2 = document.createElement("td");

	var input1 = document.createElement("input");
	input1.name = "partName";

	var td4 = document.createElement("td");
	var input2 = document.createElement("input");
	input2.name = "pictureNo";

	var td6 = document.createElement("td");
	var input3 = document.createElement("input");
	input3.name = "num";

	var td8 = document.createElement("td");
	var input4 = document.createElement("input");
	input4.name = "unit";

	var td10 = document.createElement("td");
	var input5 = document.createElement("input");
	input5.name = "more";

	var td11 = document.createElement("td");
	var delInput = document.createElement("input");//删除
	delInput.type = "button";
	delInput.value = "删";
	delInput.style.width = "20px";
	delInput.onclick = function() {
		_tbody.deleteRow(_tr.rowIndex);
		provisionSize--;
	}

	_tbody.insertBefore(_tr, uploadtr);
	//_tr.insertBefore(td1, null);
	//td1.insertBefore(word1, null);
	_tr.insertBefore(td2, null);
	td2.insertBefore(input1, null);

	//_tr.insertBefore(td3, null);
	//td3.insertBefore(word2, null);
	_tr.insertBefore(td4, null);
	td4.insertBefore(input2, null);

	//_tr.insertBefore(td5, null);
	//td5.insertBefore(word3, null);
	_tr.insertBefore(td6, null);
	td6.insertBefore(input3, null);

	//_tr.insertBefore(td7, null);
	//td7.insertBefore(word4, null);
	_tr.insertBefore(td8, null);
	td8.insertBefore(input4, null);

	//_tr.insertBefore(td9, null);
	//td9.insertBefore(word5, null);
	_tr.insertBefore(td10, null);
	td10.insertBefore(input5, null);

	_tr.insertBefore(td11, null);
	td11.insertBefore(delInput, null);

	provisionSize++;
}

function closeLing(showOrClose) {
	for ( var i = 0; i < provisionSize; i++) {
		var createTr = document.getElementById("createTr" + i);//获得第一个tbody
		createTr.style.display = showOrClose;
	}
}

//添加维修人员
function addPeople() {
	$("#repairMan").clone(true).insertAfter("#repairMan");
}
</script>
	</body>
</html>
