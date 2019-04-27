<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
							数量(权值)
						</th>
						<th>
							1 : ${quotedPrice.corrCount==null?0:quotedPrice.corrCount}
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
						<td rowspan="3" style="width: 5px;" align="center">
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
						<td rowspan="3" align="center" width="10%">
							备
							<br />
							<br />
							<br />
							注
						</td>
						<td rowspan="3">
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
				</table>
				<s:if test="quotedPrice.procardStyle != '外购'">
				<form action="QuotedPrice_updateQuotedPriceForcl.action" method="post">
					<input name="quotedPrice.id" value="${quotedPrice.id}"
						type="hidden" />
					<input name="pageStatus" value="${pageStatus}" type="hidden" />
					<table class="table" style="width: 100%;">
						<tr>
							<th colspan="2">
								财务风水电气费 录入、核对
							</th>
						</tr>
						<tr>
							<th>
								水费(含税):
							</th>
							<td>
								<input name="quotedPrice.shuiFei" value="${quotedPrice.shuiFei}" /><font color="red">(批产)</font>&nbsp;&nbsp;
								<input name="quotedPrice.shuiFeisz" value="${quotedPrice.shuiFeisz}" /><font color="red">(试制)</font>&nbsp;&nbsp;
								<input name="quotedPrice.shuiFeisj" value="${quotedPrice.shuiFeisj}" /><font color="red">(首件)</font>&nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<th>
								电费(含税):
							</th>
							<td>
								<input name="quotedPrice.dianFei" value="${quotedPrice.dianFei}" /><font color="red">(批产)</font>&nbsp;&nbsp;
								<input name="quotedPrice.dianFeisz" value="${quotedPrice.dianFeisz}" /><font color="red">(试制)</font>&nbsp;&nbsp;
								<input name="quotedPrice.dianFeisj" value="${quotedPrice.dianFeisj}" /><font color="red">(首件)</font>&nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<th>
								风费(含税):
							</th>
							<td>
								<input name="quotedPrice.fengFei" value="${quotedPrice.fengFei}" /><font color="red">(批产)</font>&nbsp;&nbsp;
								<input name="quotedPrice.fengFeisz" value="${quotedPrice.fengFeisz}" /><font color="red">(试制)</font>&nbsp;&nbsp;
								<input name="quotedPrice.fengFeisj" value="${quotedPrice.fengFeisj}" /><font color="red">(首件)</font>&nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<th>
								气体费(含税):
							</th>
							<td>
								<input name="quotedPrice.qitiFei" value="${quotedPrice.qitiFei}" /><font color="red">(批产)</font>&nbsp;&nbsp;
								<input name="quotedPrice.qitiFeisz" value="${quotedPrice.qitiFeisz}" /><font color="red">(试制)</font>&nbsp;&nbsp;
								<input name="quotedPrice.qitiFeisj" value="${quotedPrice.qitiFeisj}" /><font color="red">(首件)</font>&nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<th colspan="2">
								<input type="submit" value="确定" class="input" />
							</th>
						</tr>
					</table>
				</form>
				</s:if>
			</div>
		</center>
	</body>
</html>
