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
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<h3>
					修改明细
				</h3>
				<form action="orderManager_updateDetail.action" method="post"
					onsubmit="return validate()">
					<table class="table" style="width: 45%">
						<tr>
							<th align="right">
								产品名称：
							</th>
							<td>
								${pm.name}
							</td>
						</tr>
						<tr>

							<th align="right">
								产品件号：
							</th>
							<td>
								${pm.pieceNumber}
							</td>
						</tr>
						<tr>

							<th align="right">
								数量：
							</th>
							<td>
								<input type="hidden" value="${pm.unit}" id="unit"/>
								<input type="text" name="orderNum" value="${pm.num}" id="num" onchange="changeunitprice()"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								总价：
							</th>
							<td>
								<SPAN id="unitprice1">${pm.unitPrice}</SPAN>
								<input type="hidden" name="unitPrice" value="${pm.unitPrice}" id="unitprice2" />
							</td>
						</tr>
						<tr>
							<th align="right">
								交付日期:
							</th>
							<td>
								<input type="text" name="paymentDate" value="${pm.paymentDate }"  class="Wdate"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">
							</td>
						</tr>
						<tr>
							<th align="right">
								备注：
							</th>
							<td>
								<textarea cols="30" rows="5" name="deliveryStatus">${pm.remark}</textarea>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="hidden" name="id" value="${pm.id}" />
								<input type="submit" value="修改"
									style="width: 80px; height: 50px;" />
							</td>
						</tr>
					</table>
				</form>
				<input type="hidden" id="rebeack" value='${errorMessage}'/>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function validate() {
	var num = document.getElementById("num");
	if (num.value > 0 && num.length > 0) {
		alert("请填入数量!谢谢");
		return false;
	}
}
function changeunitprice(){
	var num=document.getElementById("num");
	var unit=document.getElementById("unit");
	var unitprice1=document.getElementById("unitprice1");
	var unitprice2=document.getElementById("unitprice2");
	if(num!=null&&num.value>0){
		unitprice1.innerHTML=(num.value)*(unit.value)
		unitprice2.value=(num.value)*(unit.value)
	}
}
$(document).ready(function(){
		var rebeack=$("#rebeack").val();
		if(rebeack =="修改成功!"||rebeack=="修改失败!"){
			alert(rebeack);
			parent.chageDiv('none');
			parent.window.location.reload();
		}
	})
</script>
	</body>
</html>
