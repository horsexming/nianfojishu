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
					<table width="200px" style="font-size: 14px; font-weight: bold;">
						<tr>
							<td align="left">   
								<img src="${companyInfo.logoOKjpg}" width="45px" height="45px" />
							</td>
							<td style="font-size: 15px; font-weight: bold">
								${companyInfo.shortName}入库单
							</td>
						</tr>
						<tr>
							<th>
								批次:
							</th>
							<td>
								${goodsStore.goodsStoreLot }
							</td>
						</tr>
						<tr>
							<th>
								件号:
							</th>
							<td>
								${goodsStore.goodsStoreMarkId }
							</td>
						</tr>
						<tr>
							<th>
								规格:
							</th>
							<td>
								${goodsStore.goodsStoreFormat}
							</td>
						</tr>
						<tr>
							<th>
								名称:
							</th>
							<td>
								${goodsStore.goodsStoreGoodsName }
							</td>
						</tr>
						<tr>
							<!-- 判断类型 -->
							<th>
								库别:
							</th>
							<td>
								${goodsStore.goodsStoreWarehouse}
							</td>
						</tr>
						<tr>
							<!-- 判断类型 -->
							<th>
								仓区:
							</th>
							<td>
								${goodsStore.goodHouseName}
							</td>
						</tr>
						
						<tr>
							<!-- 判断类型 -->
							<th>
								库位:
							</th>
							<td>
								${goodsStore.goodsStorePosition }
							</td>
						</tr>
						<tr>
							<th>
								数量:
							</th>
							<td>
								<b>${goodsStore.goodsStoreCount }</b>
							</td>
						</tr>
						<tr>
							<th>
								单位:
							</th>
							<td>
								${goodsStore.goodsStoreUnit}
							</td>
						</tr>
						<tr>
							<th>
								供应商:
							</th>
							<td>
								${goodsStore.goodsStoreSupplier }
							</td>
						</tr>
						<tr>
							<th>
								客户:
							</th>
							<td>
								${goodsStore.goodsStoreCompanyName }
							</td>
						</tr>
						<tr>
							<th>
								负责人:
							</th>
							<td>
								${goodsStore.goodsStoreCharger}
							</td>
						</tr>
						<tr>
							<th>
								时间:
							</th>
							<td>
								${goodsStore.goodsStoreDate}
							</td>
						</tr>
					</table>
				</div>
				<input style="width: 80px; font-size: 18px;"
					onclick="pagePrintOld('printDiv')" type="button" value="打印">
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->

	</body>
</html>
