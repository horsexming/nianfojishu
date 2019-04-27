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
function print1() {
	if("${mealticket.fuck}"=="未通过"){
		alert("你还未通过审批，再等等!");
		return false;
	}else{
	var printBody = document.getElementById("image");
	var printBodyHTML = "";
	var bodyHTML = "";
	if (printBodyHTML == "") {
		printBodyHTML = printBody.innerHTML;
	}
	if (bodyHTML == "") {
		bodyHTML = document.body.innerHTML;
	}
	document.body.innerHTML = printBodyHTML;
	window.print();
	document.body.innerHTML = bodyHTML;
	window.location.href="mealAction!copy1.action?mealticket.id=${mealticket.id}";
	}
}
</script>
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
					<a href="mealAction!allFind.action" style="color: #ffffff">查询全部客饭票/</a>
				<a href="mealAction!reKan.action" style="color: #ffffff">全部未通过审核查询页面</a>
				<a href="mealAction!reKan3.action" style="color: #ffffff">全部通过审核查询页面/</a>
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<div align="center" id="image">
					<table border="1" align="center" width="600px" height="380px"
						cellpadding="0" cellspacing="0">
						<tr>
							<td colspan="4" align="center">
								${companyInfo.name}
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<img src="barcode.action?msg=${mealticket.barcode}&type=code39"
									style="height: 45px; width: 300px;">
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								客饭票
							</td>
						</tr>
						<tr>
							<td align="right" width="90px">
								来客姓名：
							</td>
							<td align="left" width="250px">
								${mealticket.name}
							</td>
							<td align="right" width="90px">
								职务：
							</td>
							<td align="left" idth="150px">
								${mealticket.job}
							</td>
						</tr>
						<tr>
							<td align="right">
								单位：
							</td>
							<td align="left">
								${mealticket.company}
							</td>
							<td align="right">
								人数：
							</td>
							<td align="left">
								${mealticket.number}
							</td>
						</tr>
						<tr>
							<td align="right">
								事由：
							</td>
							<td align="left" colspan="4">
								${mealticket.reason}
							</td>
						</tr>
						<tr>
							<td align="right">
								申请人：
							</td>
							<td align="left">
								${mealticket.manage}
							</td>
							<td align="right">
								审核人：
							</td>
							<td align="left">
								${mealticket.checkname}
							</td>
						</tr>
						<tr>
							<td align="right">
								有效日：
							</td>
							<td>
								${mealticket.intime}
							</td>
							<td align="right">
								申请日期：
							</td>
							<td>
								${mealticket.addDate}
							</td>
						</tr>
					</table>
					<div align="center">
				-------------------------------------------------------------------------------------------------------------------------------------
					</div>
					<table border="1" align="center" width="600px" height="380px"
						cellpadding="0" cellspacing="0">
						<tr>
							<td colspan="4" align="center">
								${companyInfo.name}
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<img src="barcode.action?msg=${mealticket.barcode}&type=code39"
									style="height: 45px; width: 300px;">
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								客饭票（存根）
							</td>
						</tr>
						<tr>
							<td align="right" width="90px">
								来客姓名：
							</td>
							<td align="left" width="250px">
								${mealticket.name}
							</td>
							<td align="right" width="90px">
								职务：
							</td>
							<td align="left" idth="150px">
								${mealticket.job}
							</td>
						</tr>
						<tr>
							<td align="right">
								单位：
							</td>
							<td align="left">
								${mealticket.company}
							</td>
							<td align="right">
								人数：
							</td>
							<td align="left">
								${mealticket.number}
							</td>
						</tr>
						<tr>
							<td align="right">
								事由：
							</td>
							<td align="left" colspan="4">
								${mealticket.reason}
							</td>
						</tr>
						<tr>
							<td align="right">
								申请人：
							</td>
							<td align="left">
								${mealticket.manage}
							</td>
							<td align="right">
								审核人：
							</td>
							<td align="left">
								${mealticket.checkname}
							</td>
						</tr>
						<tr>
							<td align="right">
								有效日：
							</td>
							<td>
								${mealticket.intime}
							</td>
							<td align="right">
								申请日期：
							</td>
							<td>
								${mealticket.addDate}
							</td>
						</tr>
					</table>
				</div>
				<table width="165px" height="50px">
					<tr>
						<td>
								<s:if test="mealticket.copy==1">
								<input type="submit" onclick="print1()" value="打 印"
									style="width: 80px; height: 50px;" />
								</s:if>
								<s:else>
									<div color="red">你已经打印过了
									</div>
								</s:else>
							
						</td>
					</tr>
				</table>

			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->

	</body>
</html>
