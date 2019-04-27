<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
		<div id="bodyDiv" align="center" class="transDiv" style="z-index: 2"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none; top: 20px;"
			align="center">
			<div id="closeDiv"
				style="background: url(<%=basePath%>/images/bq_bg2.gif); width: 100%;">
				<div id="submitProcessDiv" style="display: none;">
					<table style="width: 100%; margin-top: ">
						<tr>
							<td>
								您正在上传发票:
							</td>
							<td align="right">
								<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
									height="32" onclick="chageDiv('none');reload();">
							</td>
						</tr>
					</table>
					<div id="operatingDiv"
						style="background-color: #ffffff; width: 100%;">
						<form action="CorePayableAction!uploadFapiao.action"
							enctype="multipart/form-data" method="post"
							onsubmit="return validate()">
							<input type="hidden" id="coreId" name="corePayable.id" />
							<table class="table" style="width: 40%" align="center">
								<tr>
									<th align="right">
										发票号:
									</th>
									<th align="left">
										<input name="corePayable.fapiaoNum" />
									</th>
								</tr>
								<tr>
									<th align="right">
										发票附件:
									</th>
									<th align="left">
										<input name="attachment" type="file" />
									</th>
								</tr>
								<tr>
									<td colspan="2" align="center">
										<input type="submit" value="提交"
											style="width: 65px; height: 40px;" />
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%; margin-top: 10px;">
			<div align="center">
				<h3>
					月度应收应付账单
				</h3>
				<form action="CorePayableAction!findMonthPayableBillList.action"
					method="post">
					<input name="pageStatus" value="${pageStatus}" type="hidden">
					<table class="table">
						<tr>
							<td>
								账单月份
							</td>
							<td>
								<input value="${monthPayableBill.jzMonth}"
									name="monthPayableBill.jzMonth" class="Wdate" type="text"
									onClick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})">
							</td>

							<th colspan="6">
								<input type="submit" value="查询" class="input" />
							</th>
						</tr>
					</table>
				</form>
				<table class="table">
					<s:if test="pageStatus=='dfk'">
						<tr>
							<th colspan="25">
								<input type="submit" value="申请付款" class="input">
							</th>
						</tr>
					</s:if>
					<%--<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
						</td>
						<td align="center">
						</td>
						<s:if test="pageStatus=='shoukuan'">
							<td align="center">
							</td>
							<td align="center">
							</td>
							<td align="right">
								总计:
								<fmt:formatNumber type="number"
									value="${monthPayableBill.weishoukuanJine}" pattern="#,##0.00"
									maxFractionDigits="2" />
							</td>
						</s:if>
						<s:elseif test="pageStatus=='fukuan'">
							<td align="center">
							</td>
							<td align="center">
							</td>
							<td align="right">
								总计:
								<fmt:formatNumber type="number"
									value="${monthPayableBill.weifukuanJine}" pattern="#,##0.00"
									maxFractionDigits="2" />
							</td>
						</s:elseif>
						<s:else>
							<td align="center">
							</td>
							<td align="center">
							</td>
							<td align="right">
								总计:
								<fmt:formatNumber type="number"
									value="${monthPayableBill.weishoukuanJine}" pattern="#,##0.00"
									maxFractionDigits="2" />
							</td>
							<td align="center">
							</td>
							<td align="center">
							</td>
							<td align="right">
								总计:
								<fmt:formatNumber type="number"
									value="${monthPayableBill.weifukuanJine}" pattern="#,##0.00"
									maxFractionDigits="2" />
							</td>
						</s:else>
					</tr>
					--%>
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							月份
						</td>
						<s:if test="pageStatus=='shoukuan'">
							<td align="right" style="background-color: green;">
								应收
								<font style="font-size: 12px;">总计: <fmt:formatNumber
										type="number" value="${monthPayableBill.yingshoukuanJine}"
										pattern="#,##0.00" maxFractionDigits="2" /> </font>
							</td>
							<td align="right" style="background-color: green;">
								已收
								<font style="font-size: 12px;">总计: <fmt:formatNumber
										type="number" value="${monthPayableBill.realshoukuanJine}"
										pattern="#,##0.00" maxFractionDigits="2" /> </font>
							</td>
							<td align="right" style="background-color: green;">
								未收
								<font style="font-size: 12px;">总计: <fmt:formatNumber
										type="number" value="${monthPayableBill.weishoukuanJine}"
										pattern="#,##0.00" maxFractionDigits="2" /> </font>
							</td>
						</s:if>
						<s:elseif test="pageStatus=='fukuan'">
							<td align="right" style="background-color: yellow;">
								应付
								<font style="font-size: 12px;">总计: <fmt:formatNumber
										type="number" value="${monthPayableBill.yingfukuanJine}"
										pattern="#,##0.00" maxFractionDigits="2" /> </font>
							</td>
							<td align="right" style="background-color: yellow;">
								已付
								<font style="font-size: 12px;">总计: <fmt:formatNumber
										type="number" value="${monthPayableBill.realfukuanJine}"
										pattern="#,##0.00" maxFractionDigits="2" /> </font>
							</td>
							<td align="right" style="background-color: yellow;">
								未付
								<font style="font-size: 12px;">总计: <fmt:formatNumber
										type="number" value="${monthPayableBill.weifukuanJine}"
										pattern="#,##0.00" maxFractionDigits="2" /> </font>
							</td>
						</s:elseif>
						<s:else>
							<td align="center" style="background-color: green;">
								应收
							</td>
							<td align="center" style="background-color: green;">
								已收
							</td>
							<td align="center" style="background-color: green;">
								未收
							</td>
							<td align="center" style="background-color: yellow;">
								应付
							</td>
							<td align="center" style="background-color: yellow;">
								已付
							</td>
							<td align="center" style="background-color: yellow;">
								未付
							</td>
						</s:else>
					</tr>
					<s:iterator value="monthPayableBillList" id="pagemonthPayableBill"
						status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								style="height: 25px;" onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#pageStatus.index+1" />
						</td>
						<td>
							${pagemonthPayableBill.jzMonth}
						</td>
						<s:if test="pageStatus=='shoukuan'">
							<td align="right">
								<fmt:formatNumber type="number"
									value="${pagemonthPayableBill.yingshoukuanJine}"
									pattern="#,##0.00" maxFractionDigits="2" />
							</td>
							<td align="right">
								<fmt:formatNumber type="number"
									value="${pagemonthPayableBill.realshoukuanJine}"
									pattern="#,##0.00" maxFractionDigits="2" />
							</td>
							<td align="right">
								<a href="huikuanAction!hkExam.action"> <fmt:formatNumber
										type="number" value="${pagemonthPayableBill.weishoukuanJine}"
										pattern="#,##0.00" maxFractionDigits="2" /> </a>
							</td>
						</s:if>
						<s:elseif test="pageStatus=='fukuan'">
							<td align="right">
								<fmt:formatNumber type="number"
									value="${pagemonthPayableBill.yingfukuanJine}"
									pattern="#,##0.00" maxFractionDigits="2" />
							</td>
							<td align="right">
								<fmt:formatNumber type="number"
									value="${pagemonthPayableBill.realfukuanJine}"
									pattern="#,##0.00" maxFractionDigits="2" />
							</td>
							<td align="right">
								<a
									href="ReceiptAction!findReceiptList.action?pageStatus=dfk&receipt.fukuanMonth=${pagemonthPayableBill.jzMonth}">
									<fmt:formatNumber type="number"
										value="${pagemonthPayableBill.weifukuanJine}"
										pattern="#,##0.00" maxFractionDigits="2" /> </a>
							</td>
						</s:elseif>
						<s:else>
							<td align="right">
								<fmt:formatNumber type="number"
									value="${pagemonthPayableBill.yingshoukuanJine}"
									pattern="#,##0.00" maxFractionDigits="2" />
							</td>
							<td align="right">
								<fmt:formatNumber type="number"
									value="${pagemonthPayableBill.realshoukuanJine}"
									pattern="#,##0.00" maxFractionDigits="2" />
							</td>
							<td align="right">
								<a href="huikuanAction!hkExam.action"> <fmt:formatNumber
										type="number" value="${pagemonthPayableBill.weishoukuanJine}"
										pattern="#,##0.00" maxFractionDigits="2" /> </a>
							</td>
							<td align="right">
								<fmt:formatNumber type="number"
									value="${pagemonthPayableBill.yingfukuanJine}"
									pattern="#,##0.00" maxFractionDigits="2" />
							</td>
							<td align="right">
								<fmt:formatNumber type="number"
									value="${pagemonthPayableBill.realfukuanJine}"
									pattern="#,##0.00" maxFractionDigits="2" />
							</td>
							<td align="right">
								<a
									href="ReceiptAction!findReceiptList.action?pageStatus=dfk&receipt.fukuanMonth=${pagemonthPayableBill.jzMonth}">
									<fmt:formatNumber type="number"
										value="${pagemonthPayableBill.weifukuanJine}"
										pattern="#,##0.00" maxFractionDigits="2" /> </a>
							</td>
						</s:else>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="25" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="20" align="center" style="color: red">
								${errorMessage}
							</td>
						</s:else>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
