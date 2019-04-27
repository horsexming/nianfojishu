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
	<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="background: url(<%=basePath%>/images/bq_bg2.gif); width: 100%;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">您正在查看同一物品的历价格</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="showProcess" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 400px; margin: 0px; padding: 0px;"></iframe>
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
					<a href="saleBudgetAction!prepareSave.action"
						style="color: #ffffff">采购申请</a> &nbsp;&nbsp;&nbsp;
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
			
				<form
					action="oaReimBursementAction!findBaozhangList.action"
					method="post" >
					<table class="table">
						<tr>
							<th>
								发票号<br>Invoice number
							</th>
							<th>
								<select name="storage.storageInvoice" style="width: 130px;"
									id="storageInvoice"
									onMouseOver="createDept('storageInvoice','oaReimBursementAction!findSelectItemStorage.action?tag=storageInvoice')">
									<option value="">
										选择发票号
									</option>
									<option value="${storage.storageInvoice}">
										${storage.storageInvoice}
									</option>
								</select>
							</th>
							<th>
								申报部门<br>Declare department
							</th>
							<th>
								<select name="storage.dept" style="width: 130px;"
									id="dept"
									onMouseOver="createDept('dept','oaReimBursementAction!findSelectItemStorage.action?tag=dept')">
									<option value="">
										选择部门
									</option>
									<option value="${storage.dept}">
										${storage.dept}
									</option>
							</th>
						
							<th>
								仓库<br>Warehouse
							</th>
							<th>
								<select name="storage.storehouse" style="width: 130px;"
									id="storehouse"
									onMouseOver="createDept('storehouse','oaReimBursementAction!findSelectItemStorage.action?tag=storehouse')">
									<option value="">
										选择库别
									</option>
									<option value="${storage.storehouse}">
										${storage.storehouse}
									</option>
								</select>
							</th>
							<th rowspan="3">
								<input type="submit" style="width: 90px; height: 30px;"
									value="查询(Query)" />
							</th>
						</tr>
						<tr>
							<th>
								物品名称<br>Item Name
							</th>
							<th>
								<input type="text" style="width: 130px;"
									name="storage.matetag"
									value="${storage.matetag }" size="80px" />
							</th>
							<th>
								规格</br>Specifications
							</th>
							<th>
								<input type="text" style="width: 130px;"
									name="storage.format" value="${storage.format}"
									size="80px" />
							</th>
							<th>
								编号<br/>Number
							</th>
							<th>
								<input type="text" style="width: 130px;"
									name="storage.number" value="${storage.number}"
									size="80px" />
							</th>
						</tr>
						<tr>
							<th>
								物品类别<br/>Items category
							</th>
							<th>
								<select name="storage.parClass" style="width: 130px;"
									id="parClass"
									onMouseOver="createDept('parClass','oaReimBursementAction!findSelectItemStorage.action?tag=parClass')">
									<option value="">
										选择类别
									</option>
									<option value="${storage.parClass}">
										${storage.parClass}
									</option>
								</select>
							</th>
							<th>
								日期从<br/>Date from 
							</th>
							<th>
								<input class="Wdate" type="text" name="startDate" size="15"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</th>
							<th>
								到<br/>To
							</th>
							<th>
								<input class="Wdate" type="text" name="endDate" size="15"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</th>
						</tr>
						</table>
						</form>
						</div>
			<form action="oaReimBursementAction!findStorageBZList.action" method="post" id="myForm">
			<center>
				<table class="table" >
					<tr>
						<th>
							选择<br>Select 
						
						</th>
						<th>
							商品编号<br>
							commodity number
						</th>
						<th>
							商品名称<br>
							Commodity name 
						</th>
						<th>
							商品规格<br>
							Product Specifications 
						</th>
						<th>
							商品单位<br>
							Commodity units
						</th>
						<th>
							商品单价<br>
							 Commodity price
						</th>
						<th>
							商品总额<br/>
							total Money 
							
						</th>
						<th>
							商品数量<br>
							Count
						</th>
						<th>
							商品类别<br>
							 category 
						</th>
						<th>
							部门<br>
							Department 
						</th>
						<th>
							发票号<br>
							Invoice number 
						
						</th>
							<th>
							历史价<br>
							Historical prices
						</th>
					</tr>

					<s:iterator id="procut" value="list" status="asd">
						<tr>
							<tD align="center">
								<input type="checkbox" name="storageSelect"
									value="${procut.id}"
									id="quxian<s:property value="#asd.index" />">
							</tD>
							<td align="center">
								${procut.number}
							</td>
							<td align="center">
								${procut.matetag }
							</td>
							<td align="center">
								${procut.format }
							</td>
							<td align="center">
								${procut.unit }
							</td>
							<td align="center">
								${procut.storageTaxPrice}
							</td>
							<td align="center">
								${procut.storageTaxMoney }
							</td>
							<td align="center">
								${procut.num}
							</td>
							<td align="center">
								${procut.parClass }
							</td>
							<td align="center">
								${procut.dept}
							</td>
							<td align="center">
								${procut.storageInvoice}
							</td>
							<td>	<input type="button" id="" value="比价" onclick="bijia(${procut.id})" /></td>
						</tr>
					</s:iterator>
					<tr>
						<td colspan="12" align="center">
							<input type="button" value="全  选(Select)" onclick=" return check()">
							&nbsp;&nbsp;&nbsp;
							<input type="button"  id="item" value="确  定(OK)" onclick="checkAndSubmit()">
							 共 <s:property value="total"/> 页 第 <s:property value="cpage"/> 页
        					<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}" styleClass="page" theme="number"/>
						</td>
					</tr>
				</table>
			</center>
		</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
	<script type="text/javascript">
	 function checkAndSubmit(){
    document.getElementById("item").disabled=true;
    var myForm = document.getElementById("myForm");
    myForm.submit();
  }
	function bijia(obj) {
	var id = obj.valueOf();
	$("#showProcess").attr("src",
			"oaReimBursementAction!findSameProductPrice.action?id=" + id+"&tag=store");
	chageDiv('block');
	}
	
	function check() {
	var quxuanName = document.getElementsByName("storageSelect");
	var i = 0
	for (i = 0; i < quxuanName.length; i++) {
		document.getElementById("quxian" + i).checked = true;
	}
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
}
</script>
</html>
