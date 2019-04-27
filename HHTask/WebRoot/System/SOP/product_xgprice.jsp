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
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h2>修改产品价格</h2>
				<form action="PriceAction!updatePrice1.action" method="get" onsubmit="return check()">
					<table class="table">
						<tr>
							<th align="right">
								税率
							</th>
							<td>
							<select id="taxprice" onblur="changetext()">
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
											<option value="11">
												11%
											</option>
											<option value="13">
												13%
											</option>
											<option value="17">
												17%
											</option>
											<option value="其他">
												其他
											</option>
										</select>
										<input id="taxRateTex_" name="price.taxprice" type="text"
											style="display: none;" value="17" />
								</td>
						</tr>
						<tr>
							<th align="right">
								含税价格
							</th>
							<td>
								<input type="text" value="${price.hsPrice }" 
								name="price.hsPrice" id="hsPrice" 
								onkeyup="chagePrice(this)" onblur="chagePrice(this)"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								不含税价格
							</th>
							<td>
								<input type="text" value="${price.bhsPrice }" 
								name="price.bhsPrice" id="bhsPrice" 
								onkeyup="chagePrice(this)" onblur="chagePrice(this)"/>
							</td>
						</tr>
						<tr>
							<td>
							</td>
							<td>
								<input type="hidden" value="${errorMessage}" id="rebeack"/>
								<input type="hidden" value="${price.id }" name="price.id"/>
								<input type="submit" value="修改价格" id="sub" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
function check(){
	var hsPrice =  document.getElementById("hsPrice");
	var bhsPrice =  document.getElementById("bhsPrice");
	if(hsPrice!=null && hsPrice.value == ""){
		alert("含税价格不能为空");
		return false;
	} else if(bhsPrice!=null && bhsPrice.value == ""){
		alert("不含税价格不能为空");
		return false;
	}
	document.getElementById("sub").disabled="disabled";
	return true;
	
}

function chagePrice(obj) {
	var price = obj.value;
	var tax = document.getElementById("taxprice").value;
	var taxvalue = 1 + (tax / 100);
	if(tax == "其他"){
		tax = document.getElementById("taxRateTex_"+few).value;
	}
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
$(document).ready(function(){
		var rebeack=$("#rebeack").val();
		if(rebeack =="修改价格成功"){
			alert(rebeack);
			parent.chageDiv('none');
			parent.window.location.reload();
		}
	})
	
function changetext() {
	
	var taxRate = document.getElementById("taxprice");
	if (taxRate != null && taxRate.value == "其他") {
		document.getElementById("taxRateTex_" ).value = "";
		document.getElementById("taxRateTex_" ).removeAttribute("style");
		document.getElementById("taxRateTex_").style.width = "70px";

		//	document.getElementById("taxRateTex_"+num).style.display="block";
	} else {
		document.getElementById("taxRateTex_").style.display = "none";
		var v = document.getElementById("taxprice" ).value;
		document.getElementById("taxRateTex_").value = v;
	}
	document.getElementById("bhsPrice").value = "";
	document.getElementById("hsPrice").value = "";

}

</SCRIPT>
	</body>
</html>
