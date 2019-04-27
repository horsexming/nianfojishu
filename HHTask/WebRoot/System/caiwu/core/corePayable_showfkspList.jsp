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
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%; margin-top: 10px;">
			<div align="center">
				<h3>
					<font color="red">${nonCoreReceivables.chengzufang}</font>
					<br />
					<s:if test="successMessage!=null">
						<font color="red">${successMessage}</font>
					</s:if>
				</h3>
				<%--<form action="CorePayableAction!findCorePaybleList.action"
					method="post">
					<input name="pageStatus" value="${pageStatus}" type="hidden">
					<table class="table">
						<tr>
							<td>
								供应商
							</td>
							<td>
								<input value="${corePayable.supplierName}"
									name="corePayable.supplierName">
							</td>
							<td>
								订单号
							</td>
							<td>
								<input value="${corePayable.orderNumber}"
									name="corePayable.orderNumber">
							</td>
							<td>
								入库时间
							</td>
							<td>
								<input value="${corePayable.saveTime}"
									name="corePayable.saveTime" class="Wdate" type="text"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">
							</td>
						</tr>
						<tr>
							<th colspan="6">
								<input type="submit" value="查询" class="input" />
							</th>
						</tr>
					</table>
				</form>
				--%>
				<form action="CorePayableAction!fukuanAudit.action" method="post"
					onsubmit="return validate()">
					<table class="table">
						<tr bgcolor="#c0dcf2" height="50px">
							<td align="center">
								<input type="hidden" name="pageStatus" value="${pageStatus}">
								<input type="hidden" name="qrTag" id="qrTag" value="yes">
								<input type="checkbox" id="checkId"
									onclick="chageAllCheck(this,'showCheckDetail')">
							</td>
							<td align="center">
								序号
							</td>
							<td align="center">
								类型
							</td>
							<td align="center">
								供应商名称
							</td>
							<td align="center">
								摘要
							</td>
							<td align="center">
								数量
							</td>
							<td align="center">
								单位
							</td>
							<td align="center">
								含税单价
							</td>
							<td align="center">
								应付金额
							</td>
							<td align="center">
								已付金额
							</td>
							<td align="center">
								未付金额
							</td>
							<td align="center">
								已批准总额
							</td>
							<td align="center">
								申请付款金额
							</td>
							<td align="center">
								调额
							</td>
							<td align="center">
								送货单号
							</td>
							<td align="center">
								状态
							</td>
							<td align="center">
								发票号
							</td>
						</tr>
						<s:iterator value="corePayableList" id="pagecorePayable"
							status="pageIndex">
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
								<input type="checkbox"
									onclick="chageNum(this,'showCheckDetail')"
									name="corePayableList[${pageIndex.index}].id"
									value="${pagecorePayable.id}"
									data="${pagecorePayable.yingfukuanJine-pagecorePayable.yipizhunJine}">
							</td>
							<td>
								<s:property value="#pageIndex.index+1" />
							</td>
							<td>
								${pagecorePayable.subjectItem}
							</td>
							<td>
								${pagecorePayable.supplierName}
							</td>
							<td>
								${pagecorePayable.zhaiyao}
							</td>
							<td align="right">
								<fmt:formatNumber type="number"
									value="${pagecorePayable.number}" pattern="0.0000"
									maxFractionDigits="4" />
							</td>
							<td>
								${pagecorePayable.unit}
							</td>
							<td align="right">
								<a target="_showPriceDetail"
									href="PriceAction!findPriceById.action?id=${pagecorePayable.priceId}&statue=find">
									<fmt:formatNumber type="number"
										value="${pagecorePayable.hsPrice}" pattern="0.0000"
										maxFractionDigits="4" /> </a>
							</td>
							<td align="right">
								<fmt:formatNumber type="number"
									value="${pagecorePayable.yingfukuanJine}" pattern="0.00"
									maxFractionDigits="2" />
							</td>
							<td align="right">
								<fmt:formatNumber type="number"
									value="${pagecorePayable.realfukuanJine}" pattern="0.00"
									maxFractionDigits="2" />
							</td>
							<td>
								<fmt:formatNumber type="number"
									value="${pagecorePayable.yingfukuanJine-pagecorePayable.realfukuanJine}"
									pattern="0.00" maxFractionDigits="2" />
							</td>
							<td align="right">
								<fmt:formatNumber type="number"
									value="${pagecorePayable.yipizhunJine}" pattern="0.00"
									maxFractionDigits="2" />
							</td>
							<td style="color: red;">
								<fmt:formatNumber type="number"
									value="${pagecorePayable.yingfukuanJine-pagecorePayable.yipizhunJine}"
									pattern="0.00" maxFractionDigits="2" />
							</td>
							<td>
								<input name="corePayableList[${pageIndex.index}].yipizhunJine"
									value="<fmt:formatNumber type="number" value="${pagecorePayable.yingfukuanJine-pagecorePayable.yipizhunJine}" maxFractionDigits="2"/>">
							</td>
							<td>
								<a target="_showWpd"
									href="WaigouwaiweiPlanAction!findWaigouPlanDNDetail.action?id=${pagecorePayable.orderId}">
									${pagecorePayable.deliveryNumber} </a>
							</td>
							<td>
								${pagecorePayable.status}
							</td>
							<td align="center">
								<a
									href="FileViewAction.action?FilePath=/upload/file/fapiao/${pagecorePayable.fujian}">
									${pagecorePayable.fapiaoNum}</a>

							</td>
						</s:iterator>
						<tr>
							<td colspan="22" align="left"
								style="color: red; font-size: 20px;">
								<span id="showCheckDetail"></span>
							</td>
						</tr>
						<tr>
							<th colspan="21">
								<input type="submit" value="同意付款" class="input">
							</th>
						</tr>
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
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
//财务确认 
function addFapiao(coreId) {
	$("#coreId").val(coreId);
	$("#submitProcessDiv").show();
	chageDiv("block");
	//单独设置弹出层的高度
	var thisTopHeight = $(obj).offset().top;
	$('#contentDiv').css( {
		'top' : thisTopHeight + 'px'
	});
}

function tosubmitNo(obj) {
	$("#qrTag").val("no");
	obj.submit();
}
</script>
	</body>
</html>
