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
		<center>
			<div id="showZong" style="border: solid #000 1px;">
				<div align="center"
					style="border-bottom: solid #000 1px; font-weight: bolder;">
					工 艺 规范
				</div>
				<div style="font-weight: bolder;">
					名称:${quotedPrice.proName} &nbsp;&nbsp;件号:${quotedPrice.markId}
					&nbsp;&nbsp; 卡片类型:${quotedPrice.procardStyle} &nbsp;&nbsp;
					产品类型:${quotedPrice.productStyle} &nbsp;&nbsp;
					车型:${quotedPrice.carStyle}&nbsp;&nbsp;
				</div>
				<table class="table" style="width: 100%;">
					<tr>
						<th>
							数量
						</th>
						<th>
							${quotedPrice.filnalCount}
						</th>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
					</tr>
					<tr align="center">
						<td style="width: 150px">
							零组件
						</td>
						<td>
							名称
						</td>
						<td>
							数量
						</td>
						<td>
							卡片类型
						</td>
						<th>
							&nbsp;
						</th>

					</tr>
					<s:iterator value="quotedPriceList" id="pageQuotedPrice">
						<tr align="center">
							<th>
								${pageQuotedPrice.markId}
							</th>
							<th>
								${pageQuotedPrice.proName}
							</th>
							<th>
								${pageQuotedPrice.filnalCount}
							</th>
							<th>
								${pageQuotedPrice.procardStyle}
							</th>
							<th>
								&nbsp;
							</th>
						</tr>
					</s:iterator>
					<tr>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
					</tr>
					<tr>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
					</tr>
					<tr>
						<td rowspan="4" style="width: 5px;" align="center">
							原
							<br />
							<br />
							材
							<br />
							<br />
							料
						</td>
						<td width="15%">
							牌号
						</td>
						<td width="15%">
							${quotedPrice.trademark}
						</td>
						<td rowspan="4" align="center" width="10%">
							备
							<br />
							<br />
							<br />
							注
						</td>
						<td rowspan="4">
							${quotedPrice.remark}
						</td>
					</tr>
					<tr>
						<td>
							规格
						</td>
						<td>
							${quotedPrice.specification}
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
					</tr>
				</table>
				<s:if test="quotedPrice.trademark!=null">
					<form action="QuotedPrice_updateQuotedPriceForcl.action" method="post">
						<input name="quotedPrice.id" value="${quotedPrice.id}"
							type="hidden" />
						<input name="pageStatus" value="${pageStatus}" type="hidden" />
						<table class="table" style="width: 100%">
							<tr>
								<th colspan="2">
									材料信息录入
									<s:if test="quotedPrice.yucailiaostatus=='是'.toString()">
									<font color="red">(外购)</font>
									</s:if>
								</th>
							</tr>
							<tr>
								<th>
									材料牌号:
								</th>
								<td>
									${quotedPrice.trademark}
									<%--<input name="quotedPrice.trademark"
									value="${quotedPrice.trademark}" />
							--%>
								</td>
							</tr>
							<tr>
								<th>
									材料消耗:
								</th>
								<td>
									<%--<input name="quotedPrice.materialXh"
									value="${quotedPrice.materialXh}" />
								/${quotedPrice.yuanUnit}
							--%>
									${quotedPrice.materialXh}(${quotedPrice.yuanUnit})/件<font color="red">(批产)</font>
									${quotedPrice.materialXhsz}(${quotedPrice.yuanUnit})/件<font color="red">(试制)</font>
									${quotedPrice.materialXhsj}(${quotedPrice.yuanUnit})/件<font color="red">(首件)</font>
								</td>
							</tr>
							<tr>
								<th>
									上月开标价格(含税):
								</th>
								<td>
									<input name="quotedPrice.lastMonthPrice"
										value="${quotedPrice.lastMonthPrice}" /><font color="red">(批产)</font>
								</td>
							</tr>
							<tr>
								<th>
								</th>
								<td>
									<input name="quotedPrice.lastMonthPricesz"
										value="${quotedPrice.lastMonthPricesz}" /><font color="red">(试制)</font>
								</td>
							</tr>
							<tr>
								<th>
								</th>
								<td>
									<input name="quotedPrice.lastMonthPricesj"
										value="${quotedPrice.lastMonthPricesj}" /><font color="red">(首件)</font>
								</td>
							</tr>
							<tr>
								<th>
									材料费:
								</th>
								<td>
									<%--<input name="quotedPrice.materialPrice"
									value="${quotedPrice.materialPrice}" />
							--%>
									${quotedPrice.materialPrice}<font color="red">(批产)</font>
									${quotedPrice.materialPricesz}<font color="red">(试制)</font>
									${quotedPrice.materialPricesj}<font color="red">(首件)</font>
								</td>
							</tr>
							<tr>
								<td colspan="2" align="center">
									<input type="submit" value="录入" class="input" />
								</td>
							</tr>
						</table>
					</form>
				</s:if>
			</div>
		</center>
	</body>
</html>
