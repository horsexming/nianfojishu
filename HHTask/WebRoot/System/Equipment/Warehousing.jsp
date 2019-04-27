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
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a href="javascript:location.reload();" style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center" id="shebei">
				<form action="EquipmentAction!pcocardfindAll.action" method="post">
					<table class="table">
						<tr>
							<th colspan="2" style="font-size: 21px; color: red;"
								align="center">
								入库条码管理
							</th>
						</tr>
						<tr>
							<td style="font-size: 18px; color: black;" align="right">
								请扫描入库条码：
							</td>
							<td>
								<input id="barcodeID" type="text" name="procard.barcode"
									style="font-size: 20PX" onblur="javascript:this.select();"
									value="${procard.barcode}" />

							</td>
						</tr>
					</table>
				</form>
			</div>
			<div id="showSb" style="display: none;">
				<form action="EquipmentAction!pcocardupdte.action" method="post">
					<input type="hidden" name="procard.id" value="${procard.id}" />
					<input type="hidden" name="pageStatus" value="barcode">
					<table class="table">
						<tr>
							<td style="font-size: 18px; color: black;">
								件号:
							</td>
							<td>
								<input type="text" name="procard.markId"
									value="${procard.markId}" readonly="readonly" />
							</td>

							<td align="center" style="font-size: 18px; color: black;">
								批号：
							</td>
							<td>
								<input type="text" name="procard.selfCard"
									value="${procard.selfCard}" readonly="readonly" />
							</td>

							<td style="font-size: 18px; color: black;">
								名称：
							</td>
							<td>
								<input type="text" name="procard.name" value="${procard.name}"
									readonly="readonly" />
							</td>
						</tr>
						<tr>
							<td colspan="6" align="center"
								style="font-size: 18px; color: black;">
								<input type="submit" value="打印入库条码"
									style="width: 90px; height: 60px;">
								<input type="reset" value="取消"
									style="width: 90px; height: 60px;">
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div id="number" style="display: none;">
				<div id="printDiv" align="center"
					style="width: 230px; height: 100px;">
					<table
						style="font-size: 11px; border-collapse: collapse; width: 230px; height: 99x; border: solid 1px #000;">
						<tr>
							<td rowspan="5"
								style="font-weight: bolder; border: solid 1px #000; width: 10px;">
								<br />
								入 库
								<br />
								<br />
								<br />
								条 码
								<br />
							</td>
							<td style="padding-left: 5px;">
								件号: ${procard.markId}
							</td>
						</tr>
						<tr>
							<td style="padding-left: 5px;">
								规格: ${procard.model}
							</td>
						</tr>
						<tr>
							<td style="padding-left: 5px;">
								批号: ${procard.selfCard}
							</td>
						</tr>
						<tr>
							<td style="padding-left: 5px;">
								名称: ${procard.name}
							</td>
						</tr>
						<tr>
							<td align="left">
								<br />
								<s:if test="procard.testMan!=null">
									<img src="barcode.action?msg=${procard.barcode}&type=code39"
										style="height: 50px; width: 150px;">
								</s:if>
							</td>
						</tr>
					</table>
				</div>

			</div>
		</div>

		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
onload = function() {
	document.getElementById("barcodeID").select();
	var testMan = "${procard.testMan}";//报修信息
	var procard = "${procard}";//报修信息
	if (testMan != "") {
		document.getElementById("number").style.display = "block";
		pagePrint('printDiv');
		document.getElementById("barcodeID").select();
	} else {
		if (procard != "") {
			document.getElementById("showSb").style.display = "block";
		}
	}
}
</script>
	</body>
</html>
