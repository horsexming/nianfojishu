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
		<STYLE type="text/css">
.pagetable {
	font-size: 14px;
	padding: 0px;
	margin: 0px;
	border-collapse: collapse;
	border: solid #000;
	border-width: 3px;
	width: 980px;
}

.pagetable th,.pagetable td {
	border: solid #000 3px;
	border-width: 2px;
	padding: 2px;
}
</STYLE>
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
				<h2>
					打印生产流水卡片
				</h2>
				<s:if test="%{'周转单'==runningWaterCard.rwStyle}">
					<div id="printDiv" style="width: 365px;; height: 272">
						<table border="1" class="pagetable"
							style="font-size: 13px; font-weight: bold; width: 360px; height: 270"
							align="center">

							<tr>
								<td style="padding-top: 0px; margin-left: 2px; height: 60px;"
									align="center">
									<img alt="honghu" widtn="64px;" height="55px;"
										src="<%=basePath%>${companyInfo.logoOKjpg}" />
								</td>
								<td colspan="3" align="center"
									style="font-size: 16px; font-weight: bold; center; vertical-align: middle;">

									${companyInfo.shortName}
									<span style="width: 6px;"></span>生产周转单
								</td>
							</tr>
							<tr>
								<td colspan="4" style="height: 46px;">
									<img
										src="<%=request.getContextPath()%>/barcode.action?msg=${runningWaterCard.cardNum}&type=code39"
										height="50px" width="360px" />
									</div>

								</td>
							</tr>
							<tr>
								<td style="width: 65px;" align="center">
									件号
								</td>
								<td style="width: 125px;">
									${runningWaterCard.markId }
								</td>
								<td style="width: 60px;" align="center">
									品名
								</td>
								<td style="width: 110px;">
									${runningWaterCard.partName }
								</td>
							</tr>
							<tr>
								<td align="center">
									客户
								</td>
								<td>
									${runningWaterCard.customer }
								</td>
								<td align="center">
									车型
								</td>
								<td>
									${runningWaterCard.carStyle }
								</td>
							</tr>

							<tr>
								<td align="center">
									类型
								</td>
								<td>
									${runningWaterCard.productStyle }
								</td>
								<td align="center">
									时间
								</td>
								<td>
									${runningWaterCard.createCardTime }
								</td>
							</tr>
							<tr>
								<td align="center">
									备注
								</td>
								<td colspan="3">
									此流水单请妥善保管，补打费用5元/张
								</td>
							</tr>
						</table>
					</div>
				</s:if>
				<s:else>
					<div id="printDiv">
						<table width="312px" height="165px">
							<tr>
								<th width="20%">
									<img src="<%=basePath%>${companyInfo.logoOKjpg}" width="45px" heighe="45px" />
								</th>
								<th width="80%"
									style="font-size: 25; color: red; font-weight: bolder">
									生产周转卡
								</th>
							</tr>
							<tr>
								<th style="font-size: 15; font-weight: bolder" heighe="20px">
									客 户
								</th>
								<th style="font-size: 14; font-weight: bolder">
									${runningWaterCard.customer }
								</th>
							</tr>
							<tr>
								<th style="font-size: 15; font-weight: bolder" heighe="20px">
									车 型
								</th>
								<th style="font-size: 14; font-weight: bolder">
									${runningWaterCard.carStyle }
								</th>
							</tr>
							<tr>
								<th style="font-size: 15; font-weight: bolder" heighe="20px">
									件 号
								</th>
								<th style="font-size: 14; font-weight: bolder">
									${runningWaterCard.markId }
								</th>
							</tr>
							<tr>
								<th colspan="4" height="55">
									<font style="font-size: 12px;; font-weight: bolder">领 料
										条 码 扫 描 区 如 下</font>
									<div
										style="border-color: #c0504d; border: thick; width: 100%; width: 100%;">
										<img
											src="<%=request.getContextPath()%>/barcode.action?msg=<s:property value="runningWaterCard.cardNum" />&type=code39"
											height="50px" width="310px" />
									</div>
								</th>
							</tr>
						</table>
					</div>
				</s:else>

				<input style="width: 80px; font-size: 18px;"
					onclick="pagePrint('printDiv')" type="button" value="打印">
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
