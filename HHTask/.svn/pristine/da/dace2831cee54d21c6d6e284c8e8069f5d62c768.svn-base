<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
		<style type="text/css">
.s {
	padding: 0px;
	margin: 0px;
	border-collapse: collapse;
	/* 关键属性：合并表格内外边框(其实表格边框有2px，外面1px，里面还有1px哦) */
	border: solid #999; /* 设置边框属性；样式(solid=实线)、颜色(#999=灰) */
	border-width: 1px 0 0 1px;
	width: 95%;
}

.s th,.s td {
	border: solid #999;
	border-width: 1 1px 1px 1;
	padding: 2px;
}
</style>
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
	</head>
	<body bgcolor="#ffffff">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border:  solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
			</div>
			
			<div id="image" align="center">

				<div style="width: 95%; padding-top: 30px" align="right">
					编号: ${biannumber}
				</div>
				<table align="center" width="95%"
					style="border:  solid #000000 1px" border="0">
					<tr>
						<td align="center">
							<img alt="logo" src="${companyInfo.logoOKjpg}">
						</td>
						<td align="center">
							<font style="font-family: 华文行楷; font-size: 40px">
								${companyInfo.name}</font>
						</td>
						<td align="right">
							<img alt="" src="barcode.action?msg=${tiaonumber}&type=code128"
								style="height: 80px;">
						</td>
					</tr>
				</table>
				<div style="font-family: 微软雅黑; font-size: 20px">
					<table align="center" class="s">
						<tr>
							<th>
								班组
							</th>
							<th>
								成员姓名
							</th>
							<th>
								成员工号
							</th>
							<th>
								成员卡号
							</th>
							<th>
								成员奖金
							</th>
							<th>
								成员加班费及餐贴
							</th>
							<th>
								月份
							</th>
						</tr>
						<s:iterator id="p" value="printlist">
							<tr>
								<td align="center">
									${p.bonusteam}
								</td>
								<td align="center">
									${p.bonusteamname}
								</td>
								<td align="center">
									${p.bonusmembernumber}
								</td>
								<td align="center">
									${p.bonuscardnumber}
								</td>
								<td align="center">
									${p.bonusmembermoney}
								</td>
								<td align="center">
									${p.bonusovertimemealmoney}
								</td>
								<td align="center">
									${p.bonusdata}
								</td>
							</tr>
						</s:iterator>
						<tr>
							<td colspan="7" align="right"
								style="font-size: 22px; font-weight: bold; padding-top: 40px">
								批准__________ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;定制__________&nbsp;&nbsp;&nbsp;&nbsp;

							</td>
						</tr>
					</table>
				</div>
			</div>
			<table>
				<tr>
					<td>
						<input type="submit" onclick="print1()" value="打   印"
							style="width: 80px; height: 50px;" />
					</td>
				</tr>
			</table>

			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
	</body>
















</html>
