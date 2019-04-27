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
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<div id="printDiv">
					<table border="1" style="border-collapse: collapse;">
						<tr>
							<td align="center">
								<table>
									<tr>
										<td align="left">
											<b>${companyInfo.shortName}</b>
										</td>
										<td width="10px"></td>
										<td align="right">
											<b>以旧换新单</b>
										</td>
									</tr>
									<tr>
										<td colspan="3" height="2">
											<hr>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								旧申报编号：${renew.exAppNumber}
							</td>
							<td rowspan="12">
								白
								<br>
								联
								<br>
								仓
								<br>
								库
								<br>
								，
								<br>
								红
								<br>
								联
								<br>
								财
								<br>
								务
								<br>
								，
								<br>
								黄
								<br>
								联
								<br>
								存
								<br>
								档
							</td>
						</tr>
						<tr>
							<td>
								备件更换时间：${renew.exDate}
							</td>
						</tr>
						<tr>
							<td>
								换用人姓名：${renew.exUser }
							</td>
						</tr>
						<tr>
							<td>
								换用人工号：${renew.exUserJobNum}
							</td>
						</tr>
						<tr>
							<td>
								旧备件名称：${renew.exMetatag }
							</td>
						</tr>
						<tr>
							<td>
								更换设备名称：${renew.exObj}
							</td>
						</tr>
						<tr>
							<td>
								旧备件规格：${renew.exFormat }
							</td>
						</tr>
						<tr>
							<td>
								旧备件单位：${renew.exUnit}
							</td>
						</tr>
						<tr>
							<td>
								旧备件库别：${renew.exStore }
							</td>
						</tr>
						<tr>
							<td>
								旧备件类别：${renew.exClass}
							</td>
						</tr>
						<tr>
							<td>
								旧件位置：${renew.exPosition}
							</td>
						</tr>
						<tr>
							<td>
								旧备件数量：${renew.exCount }
							</td>
						</tr>
						<tr>
							<td>
								更换原因：${renew.exResult}
							</td>
						</tr>
					</table>
				</div>
				<div>
					<input type="button" value="打印"
						onclick="pagePrint('printDiv','yes')" />
					<input type="button" value="返回" onclick="goBack()" />
				</div>
				<script type="text/javascript">
function goBack() {
	window.location = "store_initQueryLoan.action?vos.formUrl=consuming";
}
</script>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
	</body>
</html>
