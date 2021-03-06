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
		<link type="text/css" rel="stylesheet"
			href="${pageContext.request.contextPath}/css/duoXuanSelct.css">
			
<STYLE type="text/css">
.Tdhidden {display: none};
</STYLE>
	</head>
	<body onload="createDept('selectDept')">
		<div id="bodyDiv" align="center" class="transDiv">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="background: url(<%=basePath%>/images/bq_bg2.gif); width: 100%;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title"></span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%; display: none;">
					<iframe id="xiugaiIframe" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 500px; margin: 0px; padding: 0px;"></iframe>
				</div>
				<div id="operatingDiv1"
					style="background-color: #ffffff; width: 100%; display: none;">
					<iframe id="xiugaiIframe1" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 500px; margin: 0px; padding: 0px;"></iframe>
				</div>
				<div id="addProductPrice"
					style="background-color: #ffffff; width: 100%; display: none;">
					<form action="ProdEquipmentAction!add.action" method="post"
						enctype="multipart/form-data" onsubmit="return check() ">
						<table border="1" width="100%" class="table">
							<tr>
								<td colspan="20" align="center"
									style="font-family: 微软雅黑; font-weight: bold;">
									添加生产设备
								</td>
							</tr>
							<tr>
								<td align="right">
									是否手工:
								</td>
								<td align="left">
									<input type="radio" name="machine.isManual" value="是"
										onclick="setMachine()" />
									是&nbsp;
									<input type="radio" name="machine.isManual" value="否"
										checked="checked" />
									否&nbsp;
								</td>
								<td align="right">
									工区:
								</td>
								<td align="left">

									<input type="text" name="machine.workArea" />
								</td>
								<td align="right">
									工位:
								</td>
								<td align="left">
									<input type="text" name="machine.workPosition" />
								</td>
							</tr>
							<tr>
								<td align="right">
									设备编码:
								</td>
								<td align="left">
									<input type="text" name="machine.no" id="no" />
								</td>
								<td align="right">
									设备类型:
								</td>
								<td>
									<input type="text" name="machine.type" />
								</td>
								<td align="right">
									设备名称:
								</td>
								<td>
									<input type="text" name="machine.name" id="name" />
								</td>

							</tr>
							<tr>
								<td align="right">
									设备型号
								</td>
								<td>
									<input type="text" name="machine.unitType"
										value="${machine.unitType}">
								</td>
								<td align="right">
									设备品牌
								</td>
								<td>
									<input type="text" name="machine.brand"
										value="${machine.brand}">
								</td>
								<td align="right">
									使用状态
								</td>
								<td>
									<input type="radio" value="在用" name="machine.useStatus"
										checked="checked">
									在用
									<input type="radio" value="停用" name="machine.useStatus">
									停用
								</td>
							</tr>
							<tr>
								<td align="right">
									所在班组:
								</td>
								<td>

									<input name="machine.classGroup" value="${Users.dept}"
										readonly="readonly" />
								</td>
								<td align="right">
									折旧年限:
								</td>
								<td align="left">
									<input type="text" id="depreciationYear"
										name="machine.depreciationYear" />
								</td>
								<td align="right">
									购买时间:
								</td>
								<td align="left">
									<input class="Wdate" type="text" name="machine.buytime"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
									<!--									<input type="text" id="buytime" name="machine.buytime" />-->
								</td>
							</tr>
							<tr>
								<td align="right">
									购买金额:
								</td>
								<td align="left">
									<input type="text" id="buyamount" name="machine.buyamount" />
								</td>
								<td align="right">
									关键设备
								</td>
								<td>
									<select style="width: 150px;" name="machine.isKey">
										<option></option>
										<option value="no">
											否
										</option>
										<option value="yes">
											是
										</option>
									</select>
								</td>
								<td align="right">
									备注:
								</td>
								<td>
									<input type="text" name="machine.more" />
								</td>
							</tr>
							<tr>
								<td align="right">
									是否为流水线工位:
								</td>
								<td align="left">
									<input type="radio" value="是" name="machine.islsxgw" />
									是
									<input type="radio" value="否" name="machine.islsxgw"
										checked="checked" />
									否
								</td>

								<td align="right">
									设备图片
								</td>
								<td>
									<input type="file" id="pic" name="machine.picF"
										onchange="validateFile();" />
								</td>
								<td align="right">
									设备分类:
								</td>
								<td>
									<select style="width: 150px;" name="machine.baoyangType">
										<option value="${machine.baoyangType}">
											${machine.baoyangType}
										</option>
										<option value="A类">
											A类（需要日常保养、二级保养、年度保养）
										</option>
										<option value="B类">
											B类（需要年度保养）
										</option>
										<option value="C类">
											C类（需要日常保养）
										</option>
									</select>
								</td>
							</tr>
						</table>
						<br />
						<h2 style="font-size: 20px;">
							保养标准
						</h2>
						<table class="table" id="bybz_table">
							<tr>
								<th>
									序号
								</th>
								<th>
									保养条件
								</th>
								<th>
									保养方法
								</th>
								<th>
									保养周期（天）
								</th>
								<th>

									<input type="button" value="添加" onclick="addline()">
									<input type="button" value="删除" onclick="delline()">
								</th>
							</tr>
						</table>
						<br />
						<input type="hidden" value="否" name="machine.isdj">
						<input type="submit" onclick="add()" value="添加"
							style="width: 100px; height: 50px;">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="reset" value="重置" style="width: 100px; height: 50px;">
					</form>
				</div>
			</div>
		</div>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng">
			<div align="center">
				<div align="center">
					<form
						action="ProdEquipmentAction!findAll.action?operation=${operation}"
						method="post" enctype="multipart/form-data" style="margin: 0px;">

						<table class="table">
							<tr>
								<th colspan="8">
									生产设备管理
								</th>
							</tr>
							<tr>
								<td align="right">
									工区：
								</td>
								<td>
									<%--								<input type="hidden"--%>
									<%--										name="operation"--%>
									<%--										value="<s:property value="operation"/>">--%>
									<input type="text" style="width: 150px;"
										name="machine.workArea"
										value="<s:property value="machine.workArea"/>">
								</td>
								<td align="right">
									设备编码：
								</td>
								<td>
									<input type="text" style="width: 150px;" name="machine.no"
										value="<s:property value="machine.no"/>">
								</td>
								<td align="right">
									设备名称:
								</td>
								<td>
									<input type="text" style="width: 150px;" name="machine.name"
										value="<s:property value="machine.name"/>">
								</td>
							</tr>
							<tr>

								<td align="right">
									所在班组:
								</td>
								<td>
									<select id="selectDept" style="width: 155px;"
										name="machine.classGroup">
										<s:if test="machine.classGroup!=null">
											<option value="<s:property value="machine.classGroup"/>">
												<s:property value="machine.classGroup" />
											</option>
										</s:if>
										<option></option>
									</select>

								</td>
								<td align="right">
									状态:
								</td>
								<td>
									<select style="width: 150px;" name="machine.status">
										<s:if test="machine.status!=null">
											<option value="<s:property value="machine.status"/>">
												<s:property value="machine.status" />
											</option>
										</s:if>
										<option></option>
										<option value="故障">
											故障
										</option>
										<option value="故障指派">
											故障指派
										</option>
										<option value="维修中">
											维修中
										</option>
										<option value="修复待验证">
											修复待验证
										</option>
										<option value="正常">
											正常
										</option>

									</select>
								</td>
								<td align="right">
									工位:
								</td>
								<td>
									<input type="text" style="width: 150px;"
										name="machine.workPosition"
										value="<s:property value="machine.workPosition"/>">
								</td>
							</tr>
							<tr>
								<td align="right">
									关键设备
								</td>
								<td>
									<select style="width: 150px;" name="machine.isKey">
										<s:if test="machine.isKey!=null&&machine.isKey!=''">
											<option value="<s:property value="machine.isKey"/>">
												<s:if test="machine.isKey=='yes'">是</s:if>
												<s:else>否</s:else>
											</option>
										</s:if>
										<option></option>
										<option value="yes">
											是
										</option>
										<option value="no">
											否
										</option>
									</select>
								</td>
								<td align="right">
									使用状态
								</td>
								<td>
									<input type="radio" value="在用" name="machine.useStatus">
									在用
									<input type="radio" value="停用" name="machine.useStatus">
									停用
								</td>
								<td align="right">
									设备分类:
								</td>
								<td>
									<select style="width: 150px;" name="machine.baoyangType">
										<option value="${machine.baoyangType}">
											${machine.baoyangType}
										</option>
										<option value="A类">
											A类（需要日常保养、二级保养、年度保养）
										</option>
										<option value="B类">
											B类（需要年度保养）
										</option>
										<option value="C类">
											C类（需要日常保养）
										</option>
									</select>
								</td>
							</tr>
							<tr>
								<td align="right">
									显示列
								</td>
								<td>
									<SELECT id="select_column" name="showCloumn"></SELECT>
								</td>
								<td colspan="4"></td>
							</tr>
							<tr align="center">
								<td colspan="16" rowspan="5">
									<input type="submit" value="查询"
										style="width: 100px; height: 60px">

									<input type="button" onclick="showadd()" value="添加设备"
										style="width: 100px; height: 60px">
									<%--										<input type="file" name="addmachine">--%>
									<%--						                <input type="submit" value="批量导入">--%>
							</tr>
						</table>

					</form>
					<form action="ProdEquipmentAction!addMachine.action" method="post"
						enctype="multipart/form-data">
						<table>
							<tr>
								<th>
									设备批量导入:
								</th>
								<th>
									<input type="file" name="addmachine">
								</th>
								<td>
									<input type="submit" value="批量导入">
									<a href="/upload/file/download/shebeiTemplate.xls">导入模版下载</a>
									<a
										href="FileViewAction.action?FilePath=/upload/file/download/shebeiTemplate.xls&Refresh=true">/预览</a>
								</td>
							</tr>
						</table>

					</form>
					<div>
						
					</div>
					<form action="ProdEquipmentAction!printStorage.action"
						method="post" onsubmit="return vali()">
						<input type="hidden" name="machine.id" value="${machine.id}" />

						<table width="100%" border="0" style="border-collapse: collapse;"
							class="table" >
							<tr>
								<td align="left" colspan="7">
									<font color="red">共选择 <label id="peopleLabel">
											${count}
										</label> <input type="hidden" id="propleText" name="peopleNum"
											style="width: 20px;" readonly="readonly"> 台</font>
								</td>
								<td align="right">
									<a href="ProdEquipmentAction!toupdate.action"
										style="color: red">最新设备净值</a>
								</td>
							</tr>
						</table>
						<table width="100%" border="0" style="border-collapse: collapse;"
							class="table" id="selectTable">
							<thead>
							</thead>
							<tbody>
							</tbody>
							<!--  
							<tr bgcolor="#c0dcf2" height="50px" id="tableHeader">
								<th align="center">
									<input type="checkbox" id="checkAll"
										onclick="chageAllCheck(this)">
									全选
								</th>
								<th align="center" >
									编号
								</th>
								<th align="center">
									工区
								</th>
								<th align="center">
									工位
								</th>
								<th align="center">
									设备图片
								</th>
								<th align="center">
									设备编码
								</th>
								<th align="center">
									设备类型
								</th>
								<th align="center">
									设备名称
								</th>
								<th align="center">
									设备型号
								</th>
								<th align="center">
									设备品牌
								</th>
								<th align="center">
									所在班组
								</th>
								<th align="center">
									状态
								</th>
								<th align="center">
									保养类型
								</th>
								<th align="center">
									报修条码
								</th>
								<th align="center">
									折旧年限
								</th>
								<th align="center">
									购买金额
								</th>
								<th align="center">
									设备净值
								</th>
								<th align="center">
									关键设备
								</th>
								<th align="center">
									是否手工
								</th>
								<th align="center">
									稼动率
								</th>
								<th align="center">
									流水线
								</th>
								<th align="center">
									使用状态
								</th>
								<th align="center">
									操作
								</th>
							</tr>
							<s:iterator value="machineList" id="pagemachine"
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

									<input type="checkbox" name="vsto.selected"
										value="${pagemachine.id}"
										id="machine<s:property value="#pageStatus.index"/>"
										onclick="chageNum(this)" name="id" />

								</td>
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
								<td>
									${pagemachine.workArea}
								</td>
								<td>
									${pagemachine.workPosition}
								</td>
								<td>
									<a
										href="<%=basePath%>/FileViewAction.action?FilePath=/upload/file/machine/${pagemachine.pic}"><img
											style="width: 80px; height: 80px; max-width: 100%; max-height: 100%;"
											alt="暂无图片"
											src="${pageContext.request.contextPath}/upload/file/machine/${pagemachine.pic}">
									</a>
								</td>
								<td>
									${pagemachine.no}
								</td>
								<td>
									${pagemachine.type}
								</td>
								<td>
									${pagemachine.name}
								</td>
								<td>
									${pagemachine.unitType}
								</td>
								<td>
									${pagemachine.brand}
								</td>
								<td>
									${pagemachine.classGroup}
								</td>
								<td>
									${pagemachine.baoyangType}
								</td>
								<td>
									${pagemachine.status}
								</td>
								<td>
									${pagemachine.barcode}
								</td>
								<td>
									${pagemachine.depreciationYear}
								</td>
								<td>
									${pagemachine.buyamount}
								</td>
								<td>
									${pagemachine.equipmentworth}
								</td>
								<td>
									<s:if test="#pagemachine.isKey=='yes'">是</s:if>
									<s:else>否</s:else>
								</td>
								<td>
									<s:if
										test="#pagemachine.isManual==null||#pagemachine.isManual==''">否</s:if>
									<s:else>${pagemachine.isManual}</s:else>
								</td>
								<td>
									${pagemachine.jiadonglv}
								</td>
								<td>
									${pagemachine.islsxgw}
								</td>
								<td>
									${pagemachine.useStatus}
								</td>
								<td align="center">
									<s:if test="operation!='noOperation'">
										<a href="javascript:;"
											onclick="tanchu(0,'<%=basePath%>/ProdEquipmentAction!checkMachiner.action?id=${pagemachine.id}','${pagemachine.workPosition}')">工位码/</a>
										<a
											href="ProdEquipmentAction!initupmachiner.action?id=${pagemachine.id}&cpage=${cpage}">修改</a>
										/
									<a
											href="ProdEquipmentAction!delequipment.action?id=${pagemachine.id}"
											onClick="return window.confirm('确认要删除吗？')">删除</a>
										<br />
										<s:if
											test="#pagemachine.isKey!=null&&#pagemachine.isKey=='yes'">
											<%--										 <a href="#">关键备件</a>--%>
										</s:if>

										<a href="javascript:;" onclick="showbd(${pagemachine.id})">绑定点检内容</a>/
									</s:if>
									<a href="javascript:;" onclick="showdjhz(${pagemachine.id})">点检汇总</a>/

									<br />
									<a
										href="ProdEquipmentAction!baoYangRecordList.action?id=${pagemachine.id}&cpage=${cpage}">查看保养记录</a>
								</td>
							</s:iterator>
							<tr>
								<td colspan="8" align="left"
									style="font-weight: bold; padding-right: 40px">
									<input type="checkbox" id="checkAll2"
										onclick="chageAllCheck(this)">
									全选
									<font color="red">共选择 <label id="peopleLabel2">
											${count}
										</label>台</font>

								</td>
									
								<s:if test="errorMessage==null">
									
								</s:if>
								<s:else>
									<td colspan="20" align="center" style="color: red">
										${errorMessage}
									</td>
								</s:else>
							</tr>
					-->
					<tfoot>
					<tr>
							<td colspan="30" align="right">
										第
										<font color="red"><s:property value="cpage" /> </font> /
										<s:property value="total" />
										页
										<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
											styleClass="page" theme="number" />
					</tr>
							<tr>
								<td colspan="30">
									<input type="submit" style="width: 100px; height: 50px;"
										value="打印报修条码" />
								</td>
							</tr>
					</tfoot>
						</table>
					</form>
				</div>
			</div>
			<%@include file="/util/foot.jsp"%>

			<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
			<script type="text/javascript">
function add() {
	var depreciationYear = document.getElementById("depreciationYear").value;
	var reg = /^[0-9]+$/;
	//校验折旧年限
	if (depreciationYear != "" && !reg.test(depreciationYear)) {
		alert("折旧年限只能为整数！");
		return false;
	} else {
		return true;
	}
	//校验时间		
	//			var   reg1   =  [0-9]{4}-[0-9]{2}-[0-9]{2};  
	//			var buytime =document.getElementById("buytime").value;
	//			var   arr   =   reg1.exec(buytime);  
	//			if   (buytime=="")   return   true;  
	//			 if   (!reg1.test(buytime)&&RegExp.$2<=12&&RegExp.$3<=31){  
	//      			  alert("请输入的日期格式为yyyy-mm-dd或正确的日期!");  
	//      			  return   false;  
	//   		     }  
	//		 		 return   true;  	 
}

function vali() {
	var selectList = document.getElementsByName("vsto.selected");
	for ( var i = 0; i < selectList.length; i++) {
		if (selectList[i].checked) {
			return true;
		}
	}
	alert("请选择需要打印的设备报修条码！谢谢");
	return false;
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
	var checkAll2 = document.getElementById("checkAll2");
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
		num++;
	} else if (num == 0 && check.checked == false) {
		num = 0;
	} else {
		if (checkAll.checked == true || checkAll2.checked == true) {
			checkAll.checked = false;
			checkAll2.checked = false;
		}
		num--;
	}
	document.getElementById("peopleLabel").innerHTML = num;
	document.getElementById("peopleLabel2").innerHTML = num;
	document.getElementById("propleText").value = num;
}
function showbd(id) {
	$("#operatingDiv").show();
	$("#addProductPrice").hide();
	$("#xiugaiIframe").attr("src",
			"DJNRAction_findAlldjnr.action?id=" + id + "&pageStatus=bd");
	chageDiv('block');

}
function showdjhz(id) {
	window.open("MachineMonthDjAction_findallbyid.action?id=" + id);
}

function showadd() {
	$("#operatingDiv").hide();
	$("#addProductPrice").show();
	chageDiv('block');
}
function setMachine() {
	$("#no").val("manual");
	$("#name").val("手工");
}
function tanchu(num, number, obj) {
	if (num == 0) {
		document.getElementById("xiugaiIframe1").src = "<%=basePath%>System/wuliu/WarehouseNumber_ewm.jsp?number="
				+ number + "&ku=" + obj;
		$("#operatingDiv1").show();
	} else {
		document.getElementById("xiugaiIframe1").src = "WarehouseAreaAction_getwarehouseNumberById.action?id="
				+ num;
	}
	chageDiv('block')
}

var index = 0;
function addline() {
	index = parseInt(index);
	var newline = '<tr align="center"><td>'
			+ (index + 1)
			+ '</td>'
			+ '<td><input type="text" name="machine.bybzList['
			+ index
			+ '].baoyangCondition" ></td>'
			+ '<td><input type="text" name="machine.bybzList['
			+ index
			+ '].baoyangMeans" ></td>'
			+ '<td><input type="text"  name="machine.bybzList['
			+ index
			+ '].baoyangCycle" onkeyup="numyanzheng(this,&apos;zhengshu&apos;)" onblur="numyanzheng(this,&apos;zhengshu&apos;)" ></td><td></td></tr>';
	$("#bybz_table").append(newline);
	index++;
}
function delline() {
	var num = $("#list_size").val();
	var n = $('#bybz_table tr').length;
	if (index < 1) {
		alert("已经没有了保养项了，不能再删了。");
		return;
	}
	$($('#bybz_table tr')[n - 1]).remove();
	index--;

}
/**
 * 限制文件格式
 */
function validateFile() {
	var fileObject = $("#pic");
	var filepath = fileObject.val();
	var fileArr = filepath.split("//");
	var fileTArr = fileArr[fileArr.length - 1].toLowerCase().split(".");
	var filetype = fileTArr[fileTArr.length - 1];
	if (filetype != "jpeg" && filetype != "jpg" && filetype != "bmp"
			&& filetype != "png" && filetype != "gif") {
		var file = document.getElementById('pic');
		file.outerHTML = file.outerHTML; //重新初始化了file的html
		fileObject.focus();
		alert("上传文件必须为图片(.jpeg/.jpg/.bmp/.png/.gif)文件！");
	}
}
var ths = $("#tableHeader").find("th");
window.onload = function() {
	var msg = ${successMessage};
	appendOptions(msg);
	var str = '';
	appendTableHeader(msg,str);
	var jsonList = ${jsonList};
	appendTableBody(jsonList);
	//getTableHeader();
}
function getTableHeader(){
	$.each(ths, function(i, n) {
		$("#select_column").append(
				'<option value="' + n.innerText + '">' + n.innerText
						+ '</option>');
	})
	duoxuaSelect("select_column",'${showCloumn}');
	var obj = document.getElementById("textselectselect_column");
	changvalue_(obj);
}

function appendOptions(msg){
	$("#select_column").empty();
	$("#select_column").append('<option value=""></option>');
	$.each(msg,function(key,value){
		$("#select_column").append('<option value="'+key+'">'+value+'</option>');
	})
	duoxuaSelect("select_column",'${showCloumn}','hidden','${showText}');
}

function appendTableHeader(headerText,str){
	var tr ='<tr bgcolor="#c0dcf2" height="50px" align="center"><th>序号</th>'
	$.each(headerText,function(key,value){
		if(key!='id'){
			tr+='<th>'+value+'</th>';
		}
	})
	tr+=str+"</tr>"
	$("#selectTable thead").append(tr);
}

function appendTableBody(jsonList,tdstr){
	$.each(jsonList,function(i,list){
		var tr = "";
		if(i%2==1){
			tr ='<tr align="center" bgcolor="#e6f3fb" ' +
			' onmouseover="chageBgcolor(this)" onmouseout="outBgcolor(this,&apos;#e6f3fb&apos;)">' +
			'<td>'+(i+1)+'</td>';
		}else{
			tr ='<tr align="center" onmouseover="chageBgcolor(this)" ' +
			'onmouseout="outBgcolor(this,&apos;&apos;)" >' +
			'<td>'+(i+1)+'</td>';
		}
		var str ='';
		$.each(list,function(i,data){
			if(!data.id){
				if(data.url.length>0){ 
					str+='<td><a href=<%=basePath%>FileViewAction.action?FilePath='+data.url+'/'+data.value+'>'
					if(data.img){
						str+='<img style="width: 80px; height: 80px; max-width: 100%; max-height: 100%;"' 
						+' alt="暂无图片" src="${pageContext.request.contextPath}/'+data.url+'/'+data.value+'">'
					}else{
						str+=data.value;
					}   
					str+='</a></td>'
				}else{
					str+='<td>'+data.value+'</td>'
				}
			}
		})
			tr+=str+tdstr+'</tr>'
		$("#selectTable tbody").append(tr);
	})
}


function changvalue_(obj) {
	var text = obj.value;
	$.each(ths, function(i, n) {
		var innerText =	n.innerText.trim();
		if (text.indexOf(innerText) < 0) {
			$(n).addClass("Tdhidden");
			$("#selectTable tr td").each(function() {
				if (this.cellIndex === n.cellIndex) {
					$(this).addClass("Tdhidden");
				}
			})
		}else{
			$(n).removeClass();
			$("#selectTable tr td").each(function() {
				if (this.cellIndex === n.cellIndex) {
					$(this).removeClass();
				}
			})
		}
	})
}



</script>