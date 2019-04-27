<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
function chagePrice(obj, few) {
	var price = obj.value;
	if (price != null) {
		if (obj.id == "hsPrice_" + few) {
			var otherPrice = price / 1.17;
			document.getElementById("bhsPrice_" + few).value = parseFloat(otherPrice);
		} else if (obj.id == "bhsPrice_" + few) {
			document.getElementById("hsPrice_" + few).value = parseFloat((price * 1.17));
		}
	}
}
var fileDivHTML = "";
var count = 0;
function uploadFile(obj, few) {
	var fileDiv = document.getElementById("fileDiv_" + few);
	if (obj.value == "上传附件") {
		fileDiv.style.display = "block";
		obj.value = "添加文件";
	}
	fileDivHTML = "<div id='file"
			+ count
			+ "'><input type='file' name='attachment'><a href='javascript:delFile("
			+ count + "," + few + ")'>删除</a></div>";
	fileDiv.insertAdjacentHTML("beforeEnd", fileDivHTML);
	count++;
}

function delFile(obj, few) {
	document.getElementById("file" + obj).parentNode.removeChild(document
			.getElementById("file" + obj));
	count--;
	if (count <= 0) {
		count = 0;
		document.getElementById("fileButton_" + few).value = "上传附件";
		document.getElementById("fileDiv_" + few).style.display = "none";
	}
}

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
function checkForm(num) {
	var partNumber = document.getElementById("partNumber" + num);
	var client = document.getElementById("client" + num);
	var contractNumber = document.getElementById("contractNumber" + num);
	var hair = document.getElementById("hair" + num);
	var price = document.getElementById("price" + num);
	var name = document.getElementById("name" + num);
	var fileNumber = document.getElementById("fileNumber" + num);
	if (partNumber != null && partNumber.value == "") {
		alert("件号不能为空!");
		partNumber.focus();
		return false;
	} else if (name != null && name.value == "") {
		alert("名称不能为空!");
		name.focus();
		return false;
	} else if (client != null && client.value == "") {
		alert("客户不能为空!");
		client.focus();
		return false;
	} else if (contractNumber != null && contractNumber.value == "") {
		alert("合同编号不能为空!");
		contractNumber.focus();
		return false;
	} else if (hair != null && hair.value == "") {
		alert("发住地不能为空!");
		hair.focus();
		return false;
	} else if (price != null && price.value == "") {
		alert("价格不能为空!");
		price.focus();
		return false;
	} else if (fileNumber != null && fileNumber.value == "") {
		alert("档案号不能为空!");
		fileNumber.focus();
		return false;
	} else {
		return true;
	}

}
</script>
	</head>
	<body bgcolor="#ffffff">
		<center>
			<%@include file="/util/sonTop.jsp"%>
			<div id="gongneng">
				<div align="left">
					<div align="center" style="width: 100%">
						<font color="red">${successMessage}</font>
					</div>
					<form action="SellPriceAction!addSellPrice.action" method="post"
						onsubmit="return checkForm('1')" enctype="multipart/form-data">
						<s:token />
						<table class="table">
							<tr>
								<td colspan="6" align="center"
									style="font-family: 微软雅黑; font-weight: bold;">
									添加销售价格
								</td>
							</tr>
							<tr>
								<td align="right">
									件号:
									<br />
									<td>
										<input type="text" id="partNumber1"
											name="sellPrice.partNumber">
									</td>
									<td align="right">
										&nbsp;&nbsp;名称 :
									</td>
									<td>
										<input type="text" id="name1" name="sellPrice.name">
									</td>
									<td align="right">
										客&nbsp;&nbsp;&nbsp;户:
										<br />
									</td>
									<td>
										<select id="type1" name="sellPrice.clientManagement">
											<option value="">
												请选择客户
											</option>
											<s:iterator value="clientNameList" id="clientName">
												<option>
													${clientName}
												</option>
											</s:iterator>
										</select>
									</td>
							</tr>
							<tr>
								<td align="right">
									合同编号:
									<br />
								</td>
								<td>
									<input type="text" id="contractNumber1"
										name="sellPrice.contractNumber" />
								</td>
								<td align="right">
									&nbsp;&nbsp;价格:
									<br />
								</td>
								<td>
									<input type="text" id="hsPrice_1" name="sellPrice.hsPrice"
										style="width: 70px;" onkeyup="chagePrice(this,'1')"
										onblur="chagePrice(this,'1')">
									(含税价)
									<input type="text" id="bhsPrice_1" name="sellPrice.bhsPrice"
										style="width: 70px;" onkeyup="chagePrice(this,'1')"
										onblur="chagePrice(this,'1')">
									(不含税价)
								</td>
								<td align="right">
									发住地:
									<br />
								</td>
								<td>
									<input type="text" name="sellPrice.hair">
								</td>
							</tr>
							<tr>
								<td align="right">
									档 案 号:
									<br />
								</td>
								<td>

									<input id="fileNumber1" type="text" name="sellPrice.fileNumber">

								</td>
								<td align="right">
									负责人:
									<br />
								</td>
								<td>
									<input type="text" name="sellPrice.chargePerson">
								</td>
								<td align="right">
									型&nbsp;&nbsp;&nbsp;别:
									<br />
								</td>
								<td>
									<input type="text" id="type1" name="sellPrice.type">
								</td>
							</tr>

							<tr>

								<td align="right">

									销售价格有效期时间从
									<br />
								</td>
								<td>
									<input class="Wdate" type="text" name="sellPrice.starttime"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
								</td>
								<td align="right">
									到
									<br />
								</td>
								<td>
									<input class="Wdate" type="text" name="sellPrice.endtime"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
								</td>
								<td rowspan="2">
									备注
								</td>
								<td rowspan="2">
									<textarea rows="5" cols="50" name="sellPrice.rmarks"></textarea>
								</td>

							</tr>
							<tr>
								<td align="right">
									产品类别:
									<br />
								</td>
								<td>
									<select name="sellPrice.productCategory">
										<option>
											总成
										</option>
										<option>
											组合
										</option>
										<option>
											外购
										</option>
										<option>
											自制
										</option>
									</select>
								</td>
								<td align="right">
									生产类型:
									<br />
								</td>
								<td>
									<select name="sellPrice.produceType">
										<option value="销售">
											销售
										</option>
										<option value="外委">
											外委
										</option>
										<option value="外购">
											外购
										</option>
										<option value="行政">
											行政
										</option>
									</select>
								</td>
							</tr>
							<tr>
								<td align="right">
									上传附件:
									<br />
								</td>
								<td colspan="10">
									<input type="file" id="fileButton_1" name="attachment"
										onclick="uploadFile(this,1)" value="上传附件">

									<div id="fileDiv_1" style="display: none;">

									</div>

								</td>
							</tr>



							<tr>
								<td colspan="9" align="center">
									<input type="submit" value="添加销售价格"
										style="width: 100px; height: 50px;">
									<input type="reset" value="重置"
										style="width: 100px; height: 50px;">
								</td>
							</tr>

						</table>
					</form>
				</div>
			</div>
			<br>

			<%@include file="/util/foot.jsp"%>
			<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
			<script type="text/javascript"
				src="<%=request.getContextPath()%>/javascript/jquery-1.8.3.js">
</script>
			<script type="text/javascript">
function find() {
	window.location.href = "SellPriceAction!tofind.action";
}
</script>
		</center>
	</body>
</html>
