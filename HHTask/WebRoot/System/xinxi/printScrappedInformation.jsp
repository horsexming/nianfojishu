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
		<style type="text/css">
table {
	font-size: 14px;
	padding: 0px;
	margin: 0px;
	border-collapse: collapse;
	/* 关键属性：合并表格内外边框(其实表格边框有2px，外面1px，里面还有1px哦) */
	border: solid #999; /* 设置边框属性；样式(solid=实线)、颜色(#999=灰) */
	border-width: 1px 0 0 1px;
	width: 980px;
}

table th,table td {
	border: solid #999;
	border-width: 1 1px 1px 1;
	padding: 2px;
}
</style>
	</head>
	<body bgcolor="#ffffff">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				
			</div>
			
			<div align="center">
				<div id="image">
					<table style="width: 650px; font-size: 18px;">
						<tr>
							<th colspan="6">
								<font size="5">设备报废移交验收单</font>
							</th>
						</tr>
						<tr>
							<th>
								设备名称
							</th>
							<td>
								<s:property value="aesset.taassetsname" />
							</td>
							<th>
								设备型号
							</th>
							<td>
								<s:property value="aesset.taequipmentModel" />
							</td>
							<th>
								规格
							</th>
							<td>
								<s:property value="aesset.taspecificationsmodel" />
							</td>
						</tr>

						<tR>
							<th>
								制造厂家
							</th>
							<td>
								<s:property value="aesset.tamanufacturer" />
							</td>
							<th>
								出厂编号
							</th>
							<tD>
								<s:property value="aesset.tafactorynumber" />
							</tD>
							<th>
								出厂日期
							</th>
							<td>
								<s:property value="aesset.tafactorydate" />
							</td>
						</tR>

						<tr>
							<th>
								安装地点
							</th>
							<td>
								<s:property value="aesset.tainstallationsite" />
							</td>
							<th>
								安装时间
							</th>
							<td>
								<s:property value="aesset.tainstallationdate" />
							</td>
							<th>
								统一编号
							</th>
							<td>
								<s:property value="aesset.taassetsnumber" />
							</td>
						</tr>

						<tr>
							<th>
								设备出厂原价
							</th>
							<td>
								<s:property value="aesset.taassetsNetworth" />
							</td>
							<th>
								设备(签章)
							</th>
							<td colspan="3">

							</td>
						</tr>
					</table>
					<table style="width: 650px;">
						<tr>
							<th align="left">
								<font size="4">报废原因及根据</font>
							</th>
						</tr>
						<tr>
							<td style="height: 120px;">
								${aesset.tascrappedwilling}
							</td>
						</tr>
						<tr>
							<td align="right" style="height: 50px; padding-right: 150px;">
								<font size="5">申请单位负责人</font>
							</td>
						</tr>

						<tr>
							<Td style="height: 120px;">

							</Td>
						</tr>

						<tr align="right">
							<td style="height: 50px; padding-right: 150px;">
								<font size="5">使用部门主管</font>
							</td>
						</tr>

						<tr>
							<th align="left">
								<font size="4">财 务 部</font>
							</th>
						</tr>

						<tr align="right">
							<td
								style="height: 100px; padding-right: 150px; padding-top: 60px;">
								<font size="5">主管</font>
							</td>
						</tr>
						<tr align="right">
							<td
								style="height: 100px; padding-right: 150px; padding-top: 60px;">
								<font size="5">副总经理</font>
							</td>
						</tr>
						<tr align="right">
							<tD
								style="height: 100px; padding-right: 150px; padding-top: 60px;">
								<font size="5">总经理</font>
							</tD>
						</tr>

						<tr>
							<td align="right">
								编号${shebeinumber}
							</td>
						</tr>
					</table>
				</div>
				<table>
					<tr>
						<th>
							<input type="submit" value="打  印" onclick="print1()" />
						</th>
					</tr>
				</table>
			</div>
			<br>
			<script language="javascript">
function print1() {
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
}
</script>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
	</body>
















</html>
