<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.task.entity.Users"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Users user = (Users) session.getAttribute("Users");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<%@include file="/util/sonHead.jsp"%>
		<script type="text/javascript">
$(function() {
	getUnit("danwei");
})

var successMessage = "${successMessage}";
if (successMessage != "") {
	alert(successMessage);
	window.location.replace("PriceAction!findAllPrice.action");
}

function dangangui() {
	var name = $("#accessWebcamId").val();
	var usersData = name.split("|");
	var banciid = usersData[1];
	var banciname = usersData[0];
	$("#danganId").val(banciid);
	$("#danganWeizhi").val(banciname);
}
function changetext() {
	var taxRate = document.getElementById("taxRate");
	if (taxRate != null && taxRate.value == "其他") {
		document.getElementById("taxRateTex").value = "";
		document.getElementById("taxRateTex").removeAttribute("style");
		document.getElementById("taxRateTex").style.width = "70px";

		//	document.getElementById("taxRateTex_"+num).style.display="block";
	} else {
		document.getElementById("taxRateTex").style.display = "none";
		var v = document.getElementById("taxRate").value;
		document.getElementById("taxRateTex").value = v;
	}
	$("#hsPrice").val("");
	$("#bhsPrice").val("");

}
function chagePrice(obj) {
	var price = obj.value;
	var tax = document.getElementById("taxRate").value;
	if (tax == "其他") {
		tax = document.getElementById("taxRateTex").value;
	}
	var taxvalue = 1 + (tax / 100);

	//alert(taxvalue);
	if (price != null) {
		if (obj.id == "hsPrice") {
			var otherPrice = (price / taxvalue).toFixed(3);
			document.getElementById("bhsPrice").value = parseFloat(otherPrice);
		} else if (obj.id == "bhsPrice") {
			document.getElementById("hsPrice").value = parseFloat((price * taxvalue)
					.toFixed(3));
		}
	}

}
var number = 0;
function chageCategory() {

	var productCategory = document.getElementById("productCategory").options.value;
	var parent = document.getElementById("parent");
	var parentName = document.getElementById("parentName");
	var productCategoryPrice = "${price.productCategory}";

	//如果是第一次点击就删除一个option
	if (number == 1) {
		document.getElementById("productCategory").options.remove(0);
	}

	if (productCategory == "总成") {
		parentName.innerHTML = "没有父类: ";
		parent.disabled = "true";
		parent.value = "root";
	} else if (productCategory == "组件") {
		parentName.innerHTML = "总成件号: ";
		parent.disabled = "";
		if (number > 0) {
			if (productCategory == productCategoryPrice) {
				parent.value = "${price.parent}";
			} else {
				parent.value = "";
			}
		} else {
			parent.value = "${price.parent}";
		}
	} else if (productCategory == "零件") {
		parentName.innerHTML = "组件件号: ";
		parent.disabled = "";
		if (number > 0) {
			if (productCategory == productCategoryPrice) {
				parent.value = "${price.parent}";
			} else {
				parent.value = "";
			}
		} else {
			parent.value = "${price.parent}";
		}
	}
	number++;
}
//修改转换
function showUpdate() {
	var showPrice = document.getElementById("showprice");
	var updatePrice = document.getElementById("updatePrice");
	var aUpdate = document.getElementById("aUpdate");
	if (showPrice.style.display == "table") {
		showPrice.style.display = "none";
		updatePrice.style.display = "block";
		//aUpdate.innerHTML = "取消修改";
		aUpdate.value = "取消修改";
	} else {
		showPrice.style.display = "table";
		updatePrice.style.display = "none";
		//aUpdate.innerHTML = "修改";
		aUpdate.value = "修改";
	}
}

//多文件上传
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
function validateText(id, textname) {
	var textValue = $.trim($("#" + id).val());
	if (textValue == null || textValue == "") {
		alert(textname + "不能为空");
		return false;
	}
	return true;
}
function writename() {
	var partNumber = $("#partNumber2").val();
	$.ajax( {
		type : "POST",
		url : "ProcardTemplateAction!getQuotedPricebymarkId.action",
		data : {
			markId : partNumber,
			pageStatus : 'writename'
		},
		dataType : "json",
		success : function(data) {
			if (data != null) {
				$("#name2").val(data);
			}
		}
	})
}

var count0 = 0;
function checkForm() {
	var hsPrice = $("#hsPrice").val();
	var bhsPrice = $("#bhsPrice").val();
	var wlTypes = document.getElementsByName("price.wlType");
	var bool = true
	if (wlTypes != null && wlTypes.length > 0 && count0 == 1) {
		for ( var i = 0; i < wlTypes.length; i++) {
			if (wlTypes[i].checked) {
				bool = false;
				break;
			}
		}
	} else {
		bool = false;
	}
	if (hsPrice == "") {
		$("#msg_font").html('请填写含税价');
		$("#aUpdate").removeAttr('disabled');
		return false;
	} else if (bhsPrice == "") {
		$("#msg_font").html('请填写不含税价');
		$("#aUpdate").removeAttr('disabled');
		return false;
	} else if (bool) {
		$("#msg_font").html('请选择物料类别');
		$("#aUpdate").removeAttr('disabled');
		return false;
	}

}
</script>
	</head>
	<body bgcolor="#ffffff" onload="chageCategory()">
		<center>
			<%@include file="/util/sonTop.jsp"%>
			<div id="jieshao"
				style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
				<div id="xitong"
					style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px;">
					<div align="left"
						style="width: 40%; margin-left: 0px; float: left;">
						&nbsp;&nbsp;
						<%--						<font color="#ffffff">${price.productCategory}:${Price.name}</font>--%>
					</div>
					<%--					<div style="color: #ffffff; padding-right: 20px" align="right">--%>
					<%--						<a href="javascript:showUpdate()" style="color: #ffffff"><span--%>
					<%--							id="aUpdate">修改1</span> </a> |--%>
					<%--						<a--%>
					<%--							href="javascript:if(confirm('确定要删除该数据?')){location.replace('PriceAction!deletePrice.action?id=${price.id}&statue=${statue}');};"--%>
					<%--							style="color: #ffffff"> 删除1</a> |--%>
					<%--						<a href="DownAction.action?fileName=${price.attachmentName}"--%>
					<%--							style="color: #ffffff">下载合同1</a>--%>
					<%--					</div>--%>
				</div>
					<canvas id="myCanvas" width="300" height="150"  style="border:1px solid #d3d3d3;"></canvas>
				<font id="msg_font" color="red" size="5mm"></font>
				<div align="left" style="font-family: 微软雅黑; padding-bottom: 10px;">
					<table class="table" id="showprice" style="display: table;">
						<tr>
							<th align="right">
								件号
							</th>
							<td>
								${price.partNumber}
							</td>
							<th align="right">
								名称
							</th>
							<td>
								${price.name}
							</td>
							<th align="right">
								物料类别
							</th>
							<td>
								${price.wlType}
							</td>
							<th align="right">
								供料属性
							</th>
							<td>
								${price.kgliao}
							</td>
						</tr>
						<tr>
							<th align="right">
								含税单价
							</th>
							<td>
								<s:iterator value="strList1" id="str">
									<s:if test="#str == (price.productCategory+'价格')">
										<fmt:formatNumber value="${price.hsPrice}" pattern="#.####"></fmt:formatNumber>

									</s:if>
									<s:elseif test="#str == (price.produceType+'价格')">
										<fmt:formatNumber value="${price.hsPrice}" pattern="#.####"></fmt:formatNumber>
									</s:elseif>
								</s:iterator>
							</td>
							<th align="right">
								不含税单价
							</th>
							<td>
								<s:iterator value="strList1" id="str">
									<s:if test="#str == (price.productCategory+'价格')">
										<fmt:formatNumber value="${price.bhsPrice}" pattern="#.####"></fmt:formatNumber>

									</s:if>
									<s:elseif test="#str == (price.produceType+'价格')">
										<fmt:formatNumber value="${price.bhsPrice}" pattern="#.####"></fmt:formatNumber>
									</s:elseif>
								</s:iterator>
							</td>
							<th align="right">
								税率
							</th>
							<td>
								<s:iterator value="strList1" id="str">
									<s:if test="#str == (price.productCategory+'价格')">
										<fmt:formatNumber value="${price.taxprice}" pattern="#.####"></fmt:formatNumber>

									</s:if>
									<s:elseif test="#str == (price.produceType+'价格')">
										<fmt:formatNumber value="${price.taxprice}" pattern="#.####"></fmt:formatNumber>
									</s:elseif>
								</s:iterator>
							</td>
							<th align="right">
								<s:if test="price.productCategory=='总成'">
											客户
										</s:if>
								<s:elseif test="price.productCategory=='零件' ">
											供应商
										</s:elseif>
							</th>
							<td>
								<s:if test="price.productCategory=='总成'">
									<s:iterator id="cu" value="cmList">
										<s:if test="#cu.id==price.kehuId">
											${cu.clientcompanyname}
										</s:if>
									</s:iterator>
								</s:if>
								<s:elseif test="price.productCategory=='零件'">
									<s:iterator id="gys" value="zhuserList">
										<s:if test="#gys.id==price.gysId">
													${gys.name} 
											</s:if>
									</s:iterator>
								</s:elseif>
							</td>
						</tr>
						<tr>
							<th align="right">
								版本
							</th>
							<td>
								${price.banbenhao}
							</td>
							<th align="right">
								规格
							</th>
							<td>
								${price.specification}
							</td>
							<th align="right">
								产品类别
							</th>
							<td>
								${price.productCategory}
							</td>
							<th align="right">
								生产类型
							</th>
							<td>
								${price.produceType}
								<s:if test="price.produceType=='外委'">
									<font color="red">(${price.wwType})</font>
								</s:if>
							</td>
						</tr>
						<tr>
							<th align="right">
								起始时间
							</th>
							<td>
								${price.pricePeriodStart}
							</td>
							<th align="right">
								结束时间
							</th>
							<td>
								${price.pricePeriodEnd}
							</td>
							<th align="right">
								合同编号
							</th>
							<td>
								${price.contractNumber}
							</td>
							<th align="right">
								档案号
							</th>
							<td>
								${price.fileNumber}
							</td>
						</tr>
						<tr>
							<th align="right">
								起始数量
							</th>
							<td>
								${price.firstnum}
							</td>
							<th align="right">
								截至数量
							</th>
							<td>
								${price.endnum}
							</td>
							<th align="right">
								档案柜
							</th>
							<td>
								${price.danganWeizhi}
							</td>
							<th align="right">
								机密等级
							</th>
							<td>
								${price.jimiDJ }
							</td>
						</tr>
						<tr>
							<th align="right">
								单位
							</th>
							<td>
								${price.danwei}
							</td>
							<th align="right">
								最低起订量
							</th>
							<td>
								${price.zdqdl}
							</td>
							<th align="right">
								最低装箱量
							</th>
							<td>
								${price.zdzxl}
							</td>
							<th align="right">
								最低起送量
							</th>
							<td>
								${price.zdqsl}
							</td>
						</tr>
						<tr>
							<th align="right">
								工序号
							</th>
							<td>
								${price.gongxunum}
							</td>
							<th align="right">
								工序名
							</th>
							<td>
								${price.processNames}
							</td>
							<th align="right">
								添加时间
							</th>
							<td>
								${price.writeDate}
							</td>
							<th align="right">
								备注
							</th>
							<td colspan="">
								${price.rmarks}
							</td>
						</tr>
					</table>

					<form action="PriceAction!updatePrice.action" method="post"
						onsubmit="return checkForm('2')" enctype="multipart/form-data"
						id="updatePrice" style="display: none;">
						<span id="msg_span" style="color: red; font-size: large;"></span>
						<table class="table">
							<tr>
								<td colspan="6" align="center"
									style="font-family: 微软雅黑; font-weight: bold;">
									修改${price.name}
									<input type="hidden" name="statue" value="${statue}">
									<input type="hidden" name="price.id" value="${price.id}">
									<input type="hidden" name="price.identityColumn"
										value="${price.identityColumn}">
									<input type="hidden" name="price.inputPeople"
										value="${price.inputPeople}">
									<input type="hidden" name="price.updatePeople"
										value="<%=user.getName()%>">
								</td>
							</tr>
							<tr>
								<td align="right">
									${price.productCategory}件号:
								</td>
								<td>
									<input type="text" id="partNumber2" name="price.partNumber"
										value="${price.partNumber}" onkeyup="writename()"
										onblur="writename()" readonly="readonly">
								</td>
								<td align="right">
									&nbsp;&nbsp;名称:
								</td>
								<td>
									<input type="text" id="name2" name="price.name"
										value="${price.name}">
								</td>
								<td align="right">
									型 别:
								</td>
								<td>
									<input type="text" id="type2" name="price.type"
										value="${price.type}">
								</td>
							</tr>
							<tr>
								<%--								<td align="right">--%>
								<%--									<span id="parentName">总成件号</span>--%>
								<%--								</td>--%>
								<%--								<td>--%>
								<%--									<input type="text" name="price.parent" value="${price.parent}"--%>
								<%--										id="parent">--%>
								<%--								</td>--%>
								<td align="right">
									&nbsp;&nbsp;价格:
								</td>
								<td>
									起始数量
									<input type="text" value="${price.firstnum}" id="firstnum"
										onkeyup="numyanzheng(this,'zhengshu');daxiao()"
										onblur="numyanzheng(this,'zhengshu');daxiao()"
										name="price.firstnum" style="width: 50px;" />
									截止数量
									<input type="text" value="${price.endnum}" id="endnum"
										onkeyup="numyanzheng(this,'zhengshu');daxiao()"
										onblur="numyanzheng(this,'zhengshu');daxiao()"
										name="price.endnum" style="width: 50px;" />

									<br />
									税 率:
									<select id="taxRate" onblur="changetext()">
										<option value="${price.taxprice}">
											${price.taxprice}%
										</option>
										<option value="0">
											0%
										</option>
										<option value="3">
											3%
										</option>
										<option value="5">
											5%
										</option>
										<option value="6">
											6%
										</option>
										<option value="7">
											7%
										</option>
										<option value="9">
											9%
										</option>
										<option value="10">
											10%
										</option>
										<option value="11">
											11%
										</option>
										<option value="13">
											13%
										</option>
										<option value="16">
											16%
										</option>
										<option value="17">
											17%
										</option>
										<option value="其他">
											其他
										</option>
									</select>
									<input id="taxRateTex" name="price.taxprice"
										value="${price.taxprice}" type="text" style="display: none;" />
									<br />
									含 税 价:
									<input type="text" id="hsPrice" name="price.hsPrice"
										style="width: 70px;" onkeyup="chagePrice(this)"
										onblur="chagePrice(this)" value="${price.hsPrice}">
									<br />
									不含税价:
									<input type="text" id="bhsPrice" name="price.bhsPrice"
										style="width: 70px;" onkeyup="chagePrice(this)"
										onblur="chagePrice(this)" value="${price.bhsPrice}">
								</td>
								<td align="right">
									负责人:
								</td>
								<td>
									<input type="text" name="price.chargePerson"
										value="${price.chargePerson}">
								</td>
								<s:if test='price.produceType == "外购"'>
									<td align="right">
										采购比例:
									</td>
									<td>
										<input type="text" name="price.cgbl" value="${price.cgbl}"
											onfocus="cgblpd('${price.id}')">
									</td>
								</s:if>
							</tr>
							<tr>
								<td align="right">
									合同编号:
								</td>
								<td>
									<input type="text" id="contractNumber2"
										name="price.contractNumber" value="${price.contractNumber}" />
								</td>
								<td align="right">
									规格:
								</td>
								<td>
									<input type="text" name="price.specification"
										value="${price.specification}">
								</td>
								<td align="right">
									档案号:
								</td>
								<td>
									<input type="text" name="price.fileNumber"
										value="${price.fileNumber}">
								</td>
							</tr>
							<tr>
								<td align="right">
									机密等级:
								</td>
								<td>
									<select name="price.jimiDJ" style="width: 155px;" id="jimiDJ">
										<s:iterator value="jimiList" id="jimitest">
											<s:if test="#jimitest.type==price.jimiDJ">
												<option value="${jimitest.type}" selected="selected">
													${price.danganWeizhi}
												</option>
											</s:if>
											<s:else>
												<option value="${jimitest.type}">
													${jimitest.type}
												</option>
											</s:else>
										</s:iterator>
									</select>
								</td>
								<td rowspan="10" align="right">
									&nbsp;&nbsp;&nbsp;&nbsp;备注:
								</td>
								<td rowspan="10" colspan="3">
									<textarea rows="10" cols="63" name="price.rmarks">${price.rmarks}</textarea>
								</td>
							</tr>
							<s:if test='price.produceType == "外购" || price.productCategory == "辅料" '>
								<tr>
									<td align="right">
										起送量:
									</td>
									<td colspan="1">
										<span>最低起订量:</span>
										<input type="text" name="price.zdqdl" value="${price.zdqdl}"
											style="width: 55px;" />
										<span>最低装箱量:</span>
										<input type="text" name="price.zdzxl" value="${price.zdzxl}"
											style="width: 55px;" />
										<span>最低起送量:</span>
										<input type="text" name="price.zdqsl" value="${price.zdqsl}"
											style="width: 55px;" />
									</td>
								</tr>
							</s:if>
							<tr>
								<td align="right">
									档案柜:
								</td>
								<td>
									<select onclick="dangangui()" style="width: 155px;"
										id="accessWebcamId">
										<option value="${price.danganWeizhi}|${price.danganId}">
											${price.danganWeizhi}
										</option>
										<s:iterator value="accessWebcamlist" id="accessWebcamtest">
											<s:if test="#accessWebcamtest.id!=price.danganId">
												<option
													value="${accessWebcamtest.cabinetNum}|${accessWebcamtest.id}">
													${accessWebcamtest.cabinetNum}
												</option>
											</s:if>
											<s:else>
											</s:else>
										</s:iterator>
									</select>
									<input type="hidden" name="price.danganWeizhi"
										id="danganWeizhi" value="${price.danganWeizhi}" />
									<input type="hidden" name="price.danganId" id="danganId"
										value="${price.danganId}" />
								</td>
							</tr>
							<tr>
								<td align="right">
									产品类别:
								</td>
								<td>
									<select name="price.productCategory" style="width: 155px;"
										id="productCategory" onchange="chageCategory()">
										<option value="${price.productCategory}">
											${price.productCategory}
										</option>
										<option value="总成">
											总成
										</option>
										<option value="组件">
											组件
										</option>
										<option value="零件">
											零件
										</option>
									</select>
								</td>
							</tr>
							<tr>
								<s:if test='price.produceType == "外购" || price.productCategory == "辅料"'>
									<td align="right">
										供料属性:
									</td>
									<td>
										<select name="price.kgliao" style="width: 155px;">
											<option value="<s:property value="price.kgliao" />">
												<s:if test="price.kgliao=='TK'">
										自购(TK)
										</s:if>
												<s:elseif test="price.kgliao=='TK AVL'">
										指定供应商(TK AVL)
										</s:elseif>
												<s:elseif test="price.kgliao=='CS'">
										客供(CS)
										</s:elseif>
												<s:elseif test="price.kgliao=='TK Price'">
										完全指定(TK Price)
										</s:elseif>
											</option>
											<option value="TK">
												自购(TK)
											</option>
											<option value="TK AVL">
												指定供应商(TK AVL)
											</option>
											<option value="CS">
												客供(CS)
											</option>
											<option value="TK Price">
												完全指定(TK Price)
											</option>
										</select>
									</td>
								</s:if>
								<s:elseif test="price.produceType == '外委'">
									<td align="right">
										工序号:
									</td>
									<td>
										<input type="text" value="${price.gongxunum}"
											name="price.gongxunum">
									</td>
								</s:elseif>
								<s:elseif test="price.productCategory == '总成'">
									<td align="right">
										单位:
									</td>
									<td>
										<SELECT name="price.danwei" id="danwei">
											<option value="${price.danwei}">
												${price.danwei}
											</option>
										</SELECT>
									</td>
								</s:elseif>
							</tr>
							<s:if test="price.produceType == '外委'">
								<td align="right">
									工序名称:
								</td>
								<td>
									<input type="text" value="${price.processNames}"
										name="price.processNames">
								</td>
							</s:if>
							<tr>
								<td align="right">
									生产类型:
								</td>
								<td>
									<select id="produceType" name="price.produceType"
										style="width: 155px;" onchange="changeproduceType()">
										<option value="${price.produceType}">
											${price.produceType}
										</option>
										<option value="销售">
											销售
										</option>
										<option value="外委">
											外委
										</option>
										<option value="外购">
											外购
										</option>
									</select>
									<SELECT name="price.wwType" style="width: 155px;" id="wwType">
										<option value="${price.wwType}">
											${price.wwType}
										</option>
										<option>
											工序外委
										</option>
										<option>
											包工包料
										</option>
									</SELECT>
								</td>
							</tr>
							<tr>
								<s:if test="price.productCategory == '总成'">
									<td align="right">
										客户名字
									</td>
									<td align="left">
										<select name="price.kehuId" id="custome" class="cxselect">
											<s:if test="price.kehuId==null">
												<option></option>
											</s:if>
											<s:iterator id="cu" value="cmList">
												<s:if test="#cu.id==price.kehuId">
													<option value="${cu.id}" selected="selected">
														${cu.clientcompanyname}
													</option>
												</s:if>
												<option value="${cu.id}">
													${cu.clientcompanyname}
												</option>
											</s:iterator>
										</select>
									</td>
								</s:if>
								<s:else>
									<td align="right">
										供应商
									</td>
									<td align="left">
										<select name="price.gysId" id="priceGysId"
											onchange="getwlType(this)">
											<s:if test="price.gysId==null">
												<option></option>
											</s:if>
											<s:else>
												<option value="${price.gysId}">
													${price.gys}
												</option>
											</s:else>
											<input type="button" id="gysBtn" value="切换"
												onclick="changeGys()">
											<!-- 
											<s:iterator id="gys" value="zhuserList">
												<s:if test="#gys.id==price.gysId">
													<option value="${gys.id}" selected="selected">
														${gys.name}
													</option>
												</s:if>
												<option value="${gys.id}">
													${gys.name}
												</option>
											</s:iterator>
											 -->
										</select>
									</td>
								</s:else>
							</tr>
							<s:if test="price.produceType == '外购'">
								<td align="right">
									物料类别
								</td>
								<td id="wltd">
									<input type="text" value="${price.wlType}" name="price.wlType"
										readonly="readonly" />
								</td>
							</s:if>
							<tr>
								<td align="right">
									版本号
								</td>
								<td>
									<input type="text" value="${price.banbenhao}"
										name="price.banbenhao" />
								</td>
							</tr>
							<tr>
								<td align="right">
									价格有效期时间从
								</td>
								<td>
									<input class="Wdate" type="text" name="price.pricePeriodStart"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"
										value="${price.pricePeriodStart}" readonly="readonly" />
								</td>
							</tr>
							<tr>
								<td align="right">
									到
								</td>
								<td>
									<input class="Wdate" type="text" name="price.pricePeriodEnd"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"
										value="${price.pricePeriodEnd}" readonly="readonly" />
								</td>
							</tr>
							<tr>
								<td align="right">
									<s:if test="price.attachmentName==null">
										上传附件:
									</s:if>
									<s:else>
										重新上传:
									</s:else>
								</td>
								<td colspan="5">
									<input type="button" id="fileButton_2"
										onclick="uploadFile(this,2)" value="上传附件">

									<div id="fileDiv_2" style="display: none;">

									</div>

								</td>

							</tr>
							<tr>
								<td colspan="6" align="center">
									<input type="submit" value="修改${price.productCategory}"
										id="sub" style="width: 100px; height: 50px;"
										onclick="todisabled(this)">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="reset" value="重置"
										style="width: 100px; height: 50px;">
								</td>
							</tr>
						</table>
					</form>

				</div>
				<table>
					<s:if test="statue!='mingxi'">
						<tr>
							<td align="center">
								<s:iterator value="strList" id="str">
									<s:if test="#str.indexOf(price.productCategory)!=-1">
										<input type="button" value="修改" onclick="showUpdate()"
											id="aUpdate">
									</s:if>
									<s:elseif
										test="#str.indexOf(price.produceType)!=-1 && price.produceType!=null && price.produceType!=''">
										<input type="button" value="修改" onclick="showUpdate()"
											id="aUpdate">
									</s:elseif>
								</s:iterator>
								<input type="button" value="删除" onclick="shanchu()">
							</td>
						</tr>
					</s:if>
					<%--					<input type="button" value=" 下载合同" onclick="xiazai()">--%>
				</table>
			</div>
			<%@include file="/util/foot.jsp"%>
		</center>
	</body>
	<SCRIPT type="text/javascript">
window.onscroll=function(){ 
	var oDiv=document.getElementById("test"); 
	oDiv.style.top=document.body.scrollTop + 10;  //控制上下位置
} 
$(function(){
	var c=document.getElementById("myCanvas");
	var ctx=c.getContext("2d");
 	ctx.strokeStyle = 'red';
	ctx.font="30px Arial";
	ctx.strokeText("${Users.code} ${Users.name}",10,50);
})

	//删除
	function shanchu(){
		if(confirm('确定要删除该数据?')){
			location.href="<%=request.getContextPath()%>/PriceAction!deletePrice.action?id=${price.id}&statue=${statue}";
		}
	}
	//下载合同
	function xiazai(){
			//对中文进行加密
			var fileName1 = encodeURI(encodeURI("${price.attachmentName}"));
			location.href="<%=request.getContextPath()%>/DownAction.action?fileName="+fileName1;
	}

$(function(){
	if("${errorMessage}"!="true"){
		//document.getElementById("aUpdate").disabled="disabled";
	}
})

function changeGys(){
	$("#gysBtn").hide();
	$.ajax( {
		type : "post",
		url : "PriceAction!findZhuerList.action",
		dataType : "json",
		success : function(data) {
			//填充部门信息
			var i=0;
			$(data).each(
					function() {
						$(
								"<option value='" + data[i][0] + "'>" + data[i][1]
										+ "</option>").appendTo("#priceGysId");
						//userlist($("#deptname").val());
						i++;
					});
				count0 =1;
			$("#priceGysId").tinyselect();
		}
	});
}
<%--$(FUNCTION(){--%>
<%--	VAR OBJ = DOCUMENT.GETELEMENTBYID("PRICEGYSID");--%>
<%--	 GETWLTYPE(OBJ);--%>
<%--})--%>
function getwlType(obj){
	if(obj!=null && obj.value!=''){
		$.ajax( {
		type : "POST",
		url : "PriceAction!finfZhuserByid.action",
		data : {
			id:obj.value
		},
		dataType : "json",
		success : function(data) {
			if (data != null) {
				var wltype = data.cclass;
				var wltypes = wltype.split(",");
				if(wltypes!=null && wltypes.length >0){
					$("#wltd").empty();
					
					for(var i=0; i<wltypes.length; i++){
						if(wltypes[i] == '${price.wlType}'){
							$("#wltd").append('<input type="radio" value="'+wltypes[i]+'" name=price.wlType  checked="checked">'+wltypes[i]);
						}else{
							$("#wltd").append('<input type="radio" value="'+wltypes[i]+'" name=price.wlType >'+wltypes[i]);
						}
						
					}
				}
				
			}
		}
	})
	
}
}


$(function(){
	if (/Android|webOS|iPhone|iPod|BlackBerry/i.test(navigator.userAgent)) {
		$.ajax( {
		type : "POST",
		url : "PriceAction!findPhoneqx.action",
		data : {},
		dataType : "json",
		success : function(data) {
		if(data!=null || data.length == 0){
			$("#aUpdate").remove();
		}

		}
	})
	}
})
function changeproduceType(){
	var produceType=$("#produceType").val();
	if(produceType=="外委"){
		$("#wwType").show();
	}else{
		$("#wwType").hide();
	}
}
function daxiao(){
	var firstnum = $("#firstnum").val();
	var endnum = $("#endnum").val();
	if(firstnum!='' && endnum!=''){
		firstnum = parseInt(firstnum);
		endnum = parseInt(endnum);
		if(firstnum>endnum){
		alert('起始数量大于截止数量，请重新输入!')
		 $("#firstnum").val(0);
		$("#endnum").val(0);
	}
	}
}

function cgblpd(id){
	if(id!=''){
		$.ajax( {
		type : "POST",
		url : "PriceAction!cgblpd.action",
		data : {id:id},
		dataType : "json",
		success : function(data) {
			$("#msg_span").html(data);

		}
	})
	}
}
	</SCRIPT>
</html>