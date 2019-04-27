<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>

			<div align="center">
				<h3>
					修改入库信息
				</h3>
				<form action="storage_update.action" method="post"
					onsubmit="return validateStatus()">
					<table class="table">
						<tr>
							<th>
								编号：
							</th>
							<td>
								<input type="text" name="sto.number" value="${sto.number }"
									readonly="readonly" title="禁止修改" />
							</td>
							<th>
								品名：
							</th>
							<td>
								<input type="text" name="sto.matetag" value="${sto.matetag }"
									readonly="readonly" title="禁止修改" />
							</td>
							<th>
								规格：
							</th>
							<td>
								<input type="text" name="sto.format" value="${sto.format }"
									readonly="readonly" title="禁止修改" />
							</td>
						</tr>
						<tr>
							<th>
								仓库：
							</th>
							<td>
								<select name="sto.storehouse">
									<s:iterator id="house" value='{"工具库 ","工装库","备件库","综合库","油料库"}'>
										<s:if test='#house == sto.storehouse'>
											<option value="${house }" selected="selected">
												${house }
											</option>
										</s:if>
										<s:else>
											<option value="${house }">
												${house }
											</option>
										</s:else>
									</s:iterator>
								</select>
							</td>
							<th>
								类别：
							</th>
							<td>
								<input type="text" name="sto.parClass" value="${sto.parClass }" />
							</td>
							<th>
								位置：
							</th>
							<td>
								<input type="text" name="sto.position" value="${sto.position}" />
							</td>
						</tr>
						<tr>
							<th>
								是否换算：
							</th>
							<td id="isTranslated">
								<c:choose>
									<c:when test="${sto.isTranslation=='是'}">
										<input type="radio" name="sto.isTranslation" value="是"
											onclick="translatedFun()" checked="checked" />是
								<input type="radio" name="sto.isTranslation" value="否"
											onclick="translatedFun()" />否
							</c:when>
									<c:when test="${sto.isTranslation=='否'}">
										<input type="radio" name="sto.isTranslation" value="是"
											onclick="translatedFun()" />是
								<input type="radio" name="sto.isTranslation" value="否"
											onclick="translatedFun()" checked="checked" />否
							</c:when>
								</c:choose>
							</td>
							<th>
								发票数量：
							</th>
							<td>
								<input id="num" type="text" name="sto.num" value="${sto.num }"
									onkeyup="getSumPrice()" />
							</td>
							<th>
								发票单位：
							</th>
							<td>
								<%--								<input type="text" name="sto.unit" value="${sto.unit }" />--%>
								<select name="sto.unit" id="aaa">
									<option selected="selected" value="${sto.unit }">
										${sto.unit }
									</option>
								</select>
							</td>

						</tr>
						<%-- 是否有换算 --%>
						<tr id="translatedTR" style="display: none;">
							<td align="center" colspan="2">
								<STRONG>库存信息(转换后):</STRONG>
							</td>
							<td align="right">
								库存数量：
							</td>
							<td>
								<input type="text" name="sto.categoryNum" id="categoryNum"
									value="${sto.categoryNum}" />
							</td>
							<td align="right">
								库存单位：
							</td>
							<td>
								<%--								<input type="text" name="sto.category" id="category"--%>
								<%--									value="${sto.category}" />--%>
								<select name="sto.category" id="category">
									<option selected="selected" value="${sto.category}">
										${sto.category}
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>
								发票号：
							</th>
							<td>
								<input type="text" name="sto.storageInvoice"
									value="${sto.storageInvoice }" />
							</td>
							<th>
								供应商：
							</th>
							<td>
								<input type="text" name="sto.storageCompany"
									value="${sto.storageCompany }" />
							</td>
							<th>
								是否含税：
							</th>
							<td id="isTax">
								<c:choose>
									<c:when test="${sto.storageIsTax=='是'}">
										<input type="radio" name="sto.storageIsTax" value="是"
											onclick="plusDuty()" checked="checked">是
								<input type="radio" name="sto.storageIsTax" value="否"
											onclick="plusDuty()">否
						 	</c:when>
									<c:when test="${sto.storageIsTax=='否'}">
										<input type="radio" name="sto.storageIsTax" value="是"
											onclick="plusDuty()">是
								<input type="radio" name="sto.storageIsTax" value="否"
											onclick="plusDuty()" checked="checked">否
						 	</c:when>
								</c:choose>
							</td>
						</tr>
						<tr>
							<th id="isPriceTd">
								价格(含税)：
							</th>
							<td>
								<div id="priceTd">
									<input id="storageTaxPrice" type="text"
										name="sto.storageTaxPrice" value="${sto.storageTaxPrice }"
										onkeyup="getSumPrice()" style="width: 125px;" />
								</div>
								<input id="unitPrice" name="sto.price" style="display: none;"
									value="${sto.price }" onkeyup="SumUnitPrice()" />
							</td>
							<th>
								<span id="tax">税率：</span>
							</th>
							<td id="taxRateTd">
								<select id="taxRate">
									<s:iterator id="a"
										value="{'选择税率','3%','5%','6%','7%','11%','13%','17%','其他'}"
										status="status">
										<s:if test="sto.taxRate==#a">
											<option value="${a}" selected="selected">
												${a}
											</option>
										</s:if>
										<s:else>
											<option value="${a}">
												${a}
											</option>
										</s:else>
									</s:iterator>
								</select>
								<s:if test="#sto.storageTaxRate=='其他'">
									<input id="taxRateTex" name="sto.storageTaxRate" type="text"
										value="${sto.storageTaxRate}" />
								</s:if>
								<s:else>
									<input id="taxRateTex" name="sto.storageTaxRate" type="text"
										style="display: none;" value="${sto.storageTaxRate}" />
								</s:else>
								<input id="taxTex" name="sto.taxRate" type="text"
									value="${sto.taxRate}" style="display: none;" />
							</td>
							<th>
								<span id="sumPriceName">含税总额:</span>
							</th>
							<td>
								<input id="storageTaxMoney" type="text"
									name="sto.storageTaxMoney" value="${sto.storageTaxMoney}" />
							</td>

						</tr>
						<tr>
							<th>
								日期：
							</th>
							<td>
								<input style="width: 155px;" type="text" name="sto.date"
									value="${sto.date}" class="Wdate"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<th>
								车型：
							</th>
							<td colspan="1">
								<input type="text" name="sto.carModel" value="${sto.carModel}" />
							</td>
							<th>
								申报部门：
							</th>
							<td colspan="2">
								<input type="text" name="sto.dept" value="${sto.dept}" />
							</td>
						</tr>
						<tr>
							<th>
								入库状态：
							</th>
							<td>
								<select name="sto.classify">
									<s:iterator id="b" value="{'可借用','领用'}" status="status">
										<s:if test="#b == sto.classify">
											<option value="${b}" selected="selected">
												${b}
											</option>+
									    </s:if>
										<s:else>
											<option value="${b}">
												${b}
											</option>
										</s:else>
									</s:iterator>
								</select>
							</td>
							<th>
								备注:
							</th>
							<td colspan="3" align="left">
								<textArea rows="5" cols="40" name="sto.more">${sto.more}</textArea>
							</td>
						</tr>
						<tr>
							<td align="right">
								送货价格(含税):
							</td>
							<td>
								<input type="text" name="sto.budgetPrice"
									value="${sto.budgetPrice}">
							</td>
						</tr>
						<TR>
							<td colspan="6" align="center">
								<input type="hidden" name="sto.id" value="${sto.id }" />
								<input type="submit" value="更改"
									style="height: 50px; width: 80px;" />
								<input type="reset" value="取消"
									style="height: 50px; width: 80px;" />
							</td>
						</TR>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
var isFloat = /^\d+(\.\d+)?$/;
var tax = $('#tax'); //税率文字
var taxRate = $('#taxRate'); //下拉框
var taxRateTex = $('#taxRateTex'); //其他税率
var unitPriceName = $('#unitPriceName'); //单价
var unitPrice = $('#unitPrice'); //单价框
var taxTex = $('#taxTex'); //
$(function() {
	getUnit("aaa");
	getUnit("category");

	$('#taxRate').change(function() {
		if ($('#taxRate').val() == '其他') {
			$('#taxRateTex').val("");
			$('#taxRateTex').show();
		} else {
			$('#taxRateTex').hide();
			var v = $('#taxRate').val();
			var num = v.toString().substring(0, v.toString().indexOf("%"));
			num = num / 100;
			$('#taxRateTex').val(num);
			$('#taxTex').val(v);
		}
	});

	$('#taxRateTex').change(function() {
		var v = taxRateTex.val();
		var b = isFloat.test(v);
		if (b) {
			v = (v * 100) + "%";
			$('#taxTex').val(v);
		}
	});

	//是否含税
	var isTax = "${sto.storageIsTax}";
	var taxRateTd = $('#taxRateTd');
	if (isTax == '是') {
		tax.show();
		taxRate.show();
		unitPriceName.hide();
		unitPrice.hide();
	} else {
		tax.hide();
		taxRate.hide();
		$("#priceTd").hide();
		unitPriceName.show();
		unitPrice.show();
	}
	//是否换算
	var isTranslated = "${sto.isTranslation}";
	if (isTranslated == '是') {
		$('#translatedTR').show();
	} else {
		$('#translatedTR').hide();
	}
});
function plusDuty() {
	//var val = $('input:radio:checked').val();
	var val = $('#isTax :checked').val(); //获取
	var taxRateTd = $('#taxRateTd');//税率表格
	var taxRateTxTd = $('#taxRateTxTd');
	if (val == '是') {
		tax.show();
		$("#priceTd").show();
		taxRate.show();
		if (taxRate.val() == '其他') {
			taxRateTex.show();
		}
		unitPriceName.hide();
		unitPrice.hide();
		$("#isPriceTd").text("价格(含税)： ");
		$("#sumPriceName").html("含税总额");
	} else {
		taxRateTex.hide();
		tax.hide();
		taxRate.hide();
		$("#priceTd").hide();
		unitPriceName.show();
		unitPrice.show();
		$("#isPriceTd").text("价格： ");
		$("#sumPriceName").html("不含税总额");
	}
}
var translatedTR = $('#translatedTR');//行
var category = $('#category');
var categoryNum = categoryNum = $('#categoryNum');
var conversionNum = $('#conversionNum');
function translatedFun() {
	var val = $('#isTranslated :checked').val();//是否换算
	if (val == '是') {
		category.val("");
		categoryNum.val("");
		conversionNum.val("");
		translatedTR.show();
	} else {
		category.val("");
		categoryNum.val("");
		conversionNum.val("");
		translatedTR.hide();
	}
}
//计算总额
function getSumPrice() {
	$("#storageTaxMoney").val($("#storageTaxPrice").val() * $("#num").val());
}
function SumUnitPrice() {
	$("#storageTaxMoney").val($("#unitPrice").val() * $("#num").val());
}

//表单验证
function validateStatus() {

	return window.confirm('请再次核对入库数量,如确认无误请确定!')
}
</script>
	</body>
</html>
