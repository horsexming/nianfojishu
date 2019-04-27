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
				</div>
			</div>
			
			<div align="center">
				<h3>
					填写入库详细
				</h3>
				<form action="storage_storageProducts.action"
					onsubmit="return validate()" method="post">
					<input type="hidden" name="vsto.number" value="${vsto.number}" />
					<input type="hidden" name="vsto.oaDetailId" value="${vsto.oaDetailId}" />
<%--					<input type="hidden" name="vsto.ids" value="${oa.ids}" />--%>
					<input type="hidden" name="vsto.jump" value="${vsto.jump}" />
					<table class="table">
						<tr>
							<td align="right">
								编号：
							</td>
							<td>
								<input type="text" name="sf.number" value="${oa.detailSeqNum}"
									readonly="readonly" title="禁止修改" />
							</td>
							<td align="right">
								名称：
							</td>
							<td>
								<input type="text" name="sf.matetag" value="${oa.detailAppName}"
									 title="禁止修改" />
							</td>
							<td align="right">
								规格：
							</td>
							<td>
								<input type="text" name="sf.format" value="${oa.detailFormat}"
									readonly="readonly" title="禁止修改" />
							</td>
						</tr>
						<tr>
							<td align="right">
								仓库：
							</td>
							<td>
								<select name="sf.storehouse">
									<s:iterator id="house" value='{"工具库 ","工装库","备件库","综合库","油料库"}'>
										<s:if test='#house == store.storehouse'>
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
								<%-- <input type="text" name="sf.storehouse"/>--%>
							</td>
							<td align="right">
								类别：
							</td>
							<td>
								<input type="text" name="sf.parClass"
									value="${oa.detailChildClass}" />
							</td>
							<td align="right">
								维修周期：
							</td>
							<td>
								<input type="text" name="sf.carePeriod" value="6" id="month" />
								/月
							</td>

						</tr>
						<tr>
							<td align="right">
								是否换算：
							</td>
							<td id="isTranslated">
								<input type="radio" name="sf.isTranslation" value="是"
									onclick="translatedFun()" />
								是
								<input type="radio" name="sf.isTranslation" value="否"
									checked="checked" onclick="translatedFun()" />
								否
							</td>
							<td align="right">
								发票数量：
							</td>
							<td>
								<input type="text" name="sf.num" value="${oa.detailCount}"
									id="num" />

							</td>
							<td align="right">
								发票单位：
							</td>
							<td>
<%--								<input id="unit" type="text" name="sf.unit"--%>
<%--									value="${oa.detailUnit}" />--%>
								<select name="sf.unit"  id="danwei" 	>
								<option selected="selected" value="${oa.detailUnit}">${oa.detailUnit}</option>
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
								<input type="text" name="sf.categoryNum" id="categoryNum"
									value="${oa.detailCount}" />
							</td>
							<td align="right">
								库存单位：
							</td>
							<td>
<%--								<input type="text" name="sf.category" id="category"--%>
<%--									value="${oa.detailUnit}" />--%>
									<select name="sf.category"  id="category" >
								<option selected="selected" value="${oa.detailUnit}">${oa.detailUnit}</option>
								</select>
									
							</td>
						</tr>
						<tr>
							<td align="right">
								发票号：
							</td>
							<td>
								<input type="text" name="sf.storageInvoice" />
							</td>
							<td align="right">
								供应商：
							</td>
							<td>
								<input type="text" name="sf.storageCompany" />
							</td>
							<td align="right">
								时间：
							</td>
							<td>
								<input class="Wdate" type="text" name="sf.date" id="date"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
						</tr>
						<tr>
							<td align="right">
								是否含税：
							</td>
							<td id="isTax">
								<input type="radio" name="sf.storageIsTax" value="是"
									checked="checked" onclick="plusDuty()">
								是
								</input>
								<input type="radio" name="sf.storageIsTax" value="否"
									onclick="plusDuty()">
								否
								</input>
							</td>
							<td align="right" id="priceTd">
								单价(含税)：
							</td>
							<td>
								<input id="storageTaxPrice" type="text"
									name="sf.storageTaxPrice"  value="0"/>
								<input id="unitPrice" name="sf.unitPrice"  value="0" style="display: none;" />
							</td>
							<td align="right" id="taxRateTxTd">
								税率：
							</td>
							<td id="taxRateTd">
								<select id="taxRate">
									<s:iterator id="a"
										value="{'0%','3%','5%','6%','7%','11%','13%','17%','其他'}"
										status="status">
										<s:if test="#status.first == true">
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
								<input id="taxRateTex" name="sf.storageTaxRate" type="text"
									style="display: none;" />
								<input id="taxTex" name="sf.taxRate" type="text"
									style="display: none;" />
							</td>
						</tr>
						<tr>
							<td align="right">
								位置：
							</td>
							<td>
								<input type="text" name="sf.place" value="${oa.wareWei}"/>
							</td>
							<td align="right">
								对账：
							</td>
							<td>
								<select name="sf.duizhang">
									<s:iterator id="c" value="{'对账','未对账'}" status="status">
										<s:if test="#status.first == true">
											<option value="${c}" selected="selected">
												${c}
											</option>
										</s:if>
										<s:else>
											<option value="${c}">
												${c}
											</option>
										</s:else>
									</s:iterator>
								</select>
							</td>
							<td align="right">
								经办人：
							</td>
							<td>
								<input type="text" name="sf.jinbanren" id="people" />
							</td>
						</tr>
						<tr>
							<td align="right">
								申报部门：
							</td>
							<td>
								<input type="text" name="sf.appDept" value="${oa.detailAppDept}" />
							</td>
							<td align="right">
								入库类别：
							</td>
							<td>
								<select name="sf.classify">
									<s:iterator id="b" value="{'领用','可借用'}" status="status">
										<s:if test="#status.first == true">
											<option value="${b}" selected="selected">
												${b}
											</option>
										</s:if>
										<s:else>
											<option value="${b}">
												${b}
											</option>
										</s:else>
									</s:iterator>
								</select>
							</td>
							<td align="right">送货价格(含税):</td>
									<td>
									<input type="text" name="sf.budgetPrice">
									</td>
						</tr>
						<tr>
							<td align="right">
								计划依据：
							</td>
							<td colspan="3">
								<input type="text" name="sf.more" value="${oa.detailPlanAcco}"
									style="width: 400px;" />
							</td>
							<td align="right">
								期初数量:
							</td>
							<td>
								<input type="text" name="sf.beginning_num"  />
							</td>
							
						</tr>
						<tr>
							<td colspan="6" align="center">
								<s:if test="oa.detailCount>0.0">
									<input type="submit" value="添加" id="ruku"
										style="width: 80px; height: 50px;" />
									<input type="reset" value="重置" onclick="chongzhi()"
										style="width: 80px; height: 50px;" />
								</s:if>
							</td>
						</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
var isFloat = /^\d+(\.\d+)?$/;
$(function() {
			getUnit("danwei");
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
		var taxRateTex = $('#taxRateTex');
		var v = taxRateTex.val();
		var b = isFloat.test(v);
		if (b) {
			v = (v * 100) + "%";
			$('#taxTex').val(v);
		}
	});
});
function plusDuty() {
	//var val = $('input:radio:checked').val();
	var val = $('#isTax :checked').val(); //获取
	var taxRateTd = $('#taxRateTd'); //税率表格
	var taxRate = $('#taxRate'); //下拉框
	var taxRateTex = $('#taxRateTex'); //其他税率文本

	var priceTd = $("#priceTd");//单价/含税单价 td
	var unitPrice = $('#unitPrice'); //不含税单价
	var storageTaxPrice = $("#storageTaxPrice");//含税单价text
	var taxRateTxTd = $('#taxRateTxTd');//税率文字
	if (val == '是') {
		priceTd.text("含税单价:");
		storageTaxPrice.show();
		unitPrice.hide();
		taxRateTxTd.show();
		taxRate.show();
		if (taxRate.val() == '其他') {
			taxRateTex.show();
		}
	} else {
		priceTd.text("单价:");
		unitPrice.show();
		storageTaxPrice.hide();
		taxRateTxTd.hide();
		taxRate.hide();
	}
}
var translatedTR = $('#translatedTR');//行
var category = $('#category');
var categoryNum = categoryNum = $('#categoryNum');
var conversionNum = $('#conversionNum');
function translatedFun() {
	var val = $('#isTranslated :checked').val();//是否换算
	if (val == '是') {
		translatedTR.show();
	} else {
		category.val("");
		categoryNum.val("");
		conversionNum.val("");
		translatedTR.hide();
	}
}
var exp = /^\d+$/;
function validate() {
	var num = document.getElementById("num").value;
	var month = document.getElementById("month").value;
	var time = document.getElementById("date").value;
	var peo = document.getElementById("people").value;
	var isTranslated = $('#isTranslated :checked').val();//是否换算
	if (isTranslated == "否") {
		if (num == "") {
			alert("请输入数量!");
			return false;
		}
	}
	if (month == "") {
		alert("请输入维修周期!");
		return false;
	}
	if (!exp.test(month)) {
		alert("维修周期请输入整数!");
		return false;
	}
	if (time == "") {
		alert("请选择日期！谢谢");
		return false;
	}
	if (peo == "") {
		alert("请输入经办人!");
		return false;
	}
	if (isTranslated == "是") {
		if (category == "") {
			alert("请输入换算类型!");
			return false;
		}
		if (categoryNum == "") {
			alert("请输入类别数量!");
			return false;
		}
		if (conversionNum == "") {
			alert("请输入对应数量!");
			return false;
		}
	}
	document.getElementById("ruku").disabled=true;
	return true;
}
function chongzhi(){
	document.getElementById("ruku").disabled=false;
}
</script>
	</body>
</html>
