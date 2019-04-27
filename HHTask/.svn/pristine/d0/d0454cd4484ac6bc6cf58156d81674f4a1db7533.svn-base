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
		<script type="text/javascript">
function changetext(num) {
	var taxRate = document.getElementById("taxRate_" + num);
	if (taxRate != null && taxRate.value == "其他") {
		document.getElementById("taxRateTex_" + num).value = "";
		document.getElementById("taxRateTex_" + num).removeAttribute("style");
		document.getElementById("taxRateTex_" + num).style.width = "70px";
		//	document.getElementById("taxRateTex_"+num).style.display="block";
		} else {
		document.getElementById("taxRateTex_" + num).style.display = "none";
		var v = document.getElementById("taxRate_" + num).value;
			$("#taxRateTex_"+num).val(v)
		}
		document.getElementById("bhsPrice_" + num).value = "";
		document.getElementById("hsPrice_" + num).value = "";
}
function chagePrice(obj, few) {
	var price = obj.value;
	var tax = document.getElementById("taxRate_" + few).value;
	if (tax == "其他") {
		tax = document.getElementById("taxRateTex_" + few).value;
	}
	var taxvalue = 1 + (tax / 100);
	//alert(taxvalue);
	if (price != null) {
		if (obj.id == "hsPrice_" + few) {
			var otherPrice = (price / taxvalue).toFixed(3);
			document.getElementById("bhsPrice_" + few).value = parseFloat(otherPrice);
		} else if (obj.id == "bhsPrice_" + few) {
			document.getElementById("hsPrice_" + few).value = parseFloat((price * taxvalue)
					.toFixed(3));
		}
	}
	}
function isPrice(obj) {
	var ty1 = '^\\d+(\\.\\d+)?$';
	var re = new RegExp(ty1);
	if (obj != null && obj.value.match(re) == null) {
		obj.value = "";
		obj.focus();
		obj.select();
	}
}
		</script>
	</head>
	<body>
		<form action="NoPriceprocessAction_biaojiao.action" method="post" enctype="multipart/form-data">
			<table class="table">
				<tr align="center">
					<td colspan="4" style="font-size: 20px;">
						报价明细表 (${zhuserOffer.cmp})
					</td>
				</tr>
				<tr>
					<td align="right">
						件号：
					</td>
					<td>
						${zhuserOffer.markId}
						<input id="markId" type="hidden" readonly="readonly"
							name="zhuserOffer.markId" value="${zhuserOffer.markId}" />
					</td>
					<td align="right">
						工序号：
					</td>
					<td >
						${zhuserOffer.processNO}
						<input id="id" type="hidden" value="${zhuserOffer.id}"
							name="zhuserOffer.id" readonly="readonly" />
						<input id="processNO" type="hidden" readonly="readonly"
							name="zhuserOffer.processNO" value="${zhuserOffer.processNO}" />
					</td>
				</tr>
				<tr>
					<td align="right">
						工序名称：
					</td>
					<td>
						${zhuserOffer.processName}
						<input id="processName" type="hidden" value="${zhuserOffer.processName}"
							name="zhuserOffer.processName" readonly="readonly" />
					</td>
					<td align="right">
						报价：
					</td>
					<td>
						<select  id="taxRate_3" onchange="changetext('3')">
							<option value="${zhuserOffer.taxprice}">
								${zhuserOffer.taxprice}
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
							<option value="11">
								11%
							</option>
							<option value="13">
								13%
							</option>
							<option value="17" selected="selected">
								17%
							</option>
							<option value="其他">
								其他
							</option>
						</select>
						<s:if test="zhuserOffer.taxprice==null">
						<input id="taxRateTex_3" value="17" name="zhuserOffer.taxprice"
							type="hidden"/>
						</s:if>
						<s:else>
						<input id="taxRateTex_3" value="${zhuserOffer.taxprice}" name="zhuserOffer.taxprice"
							type="hidden"  />
						</s:else>
						(税率)
						<br />
						<input type="text" id="hsPrice_3" name="zhuserOffer.hsPrice"
							style="width: 70px;"
							onkeyup="mustBeNumber('hsPrice_3');chagePrice(this,'3')"
							onblur="mustBeNumber('hsPrice_3');chagePrice(this,'3')"
							onchange="isPrice(this);chagePrice(this,'3')"
							value="${zhuserOffer.hsPrice}">
						(含税价)
						<br/>
						<input type="text" id="bhsPrice_3" name="zhuserOffer.bhsPrice"
							style="width: 70px;"
							onkeyup="mustBeNumber('hsPrice_3');chagePrice(this,'3')"
							onblur="mustBeNumber('hsPrice_3');chagePrice(this,'3')"
							onchange="isPrice(this);chagePrice(this,'3')"
							value="${zhuserOffer.bhsPrice}">
						(不含税价)
					</td>
				</tr>
				<tr >
					<td align="right">
						报价文件：
					</td>
					<td colspan="11" align="center"  style="border-style:none">
						<input type="file" name="zhuserOffer.baojiaF" >
						</td>
					</tr>
				<tr >
					<td colspan="12" align="center"  style="border-style:none">
						<input type="submit" value="报价" style="height:30PX;width:60px">
						</td>
					</tr>
			</table>
		</form>
	</body>
</html>
