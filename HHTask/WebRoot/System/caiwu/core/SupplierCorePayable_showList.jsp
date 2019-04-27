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
		<%@include file="/util/sonHead.jsp"%><script
			src="<%=basePath%>/javascript/jquery.percentageloader-0.1.js">
</script>
		<script src="<%=basePath%>/javascript/radialIndicator.js">
</script>
		<script type="text/javascript"
			src="<%=basePath%>/javascript/popwin.js">
</script>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%; margin-top: 10px;">
			<div align="center">
				<h3>
					付款管理系统
					<br />
					<s:if test="successMessage!=null">
						<font color="red">${successMessage}</font>
					</s:if>
				</h3>
				<form method="post"
					action="CorePayableAction!findSupplierCorePayableList.action">
					<input type="hidden" name="pageStatus" value="${pageStatus}" />
					<table class="table">
						<tr>
							<td>
								收款单位:
								<input name="supplierCorePayable.supplierName"
									value="${supplierCorePayable.supplierName}" />
								运营类别:
								<select name="supplierCorePayable.coreType">
									<option>
										${supplierCorePayable.coreType}
									</option>
									<option>
										主营
									</option>
									<option>
										非主营
									</option>
									<option value="">
									</option>
								</select>
								<input type="submit" value="查询" class="input" />
								<a href="CorePayableAction!findMonthPayableBillList.action?pageStatus=fukuan">查看月度应付账单</a>
							</td>
						</tr>
					</table>
				</form>
				<table class="table" style="font-size: 15px;">
					<tr bgcolor="#c0dcf2" height="50px">
						<th align="center" rowspan="2">
							序号
						</th>
						<th align="center" rowspan="2">
							类别
						</th>
						<th align="center" rowspan="2">
							收款单位
						</th>
						<th align="center" colspan="2">
							货物总计
						</th>
						<th align="center" colspan="2">
							开票总计(元、含税)
						</th>
						<th align="center" colspan="2">
							货款总计(元、含税)
						</th>
					</tr>
					<tr bgcolor="#c0dcf2">
						<th align="center">
							发货率(发/收)
						</th>
						<th align="center">
							汇总明细
						</th>
						<th align="center">
							开票率
						</th>
						<th align="center">
							汇总明细
						</th>
						<th align="center">
							付款率
						</th>
						<th align="center">
							汇总明细
						</th>
					</tr>

					<s:iterator value="supplierCorePayablerList"
						id="pageSupCorePayable" status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#c0dcf2"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'##c0dcf2')">
						</s:if>
						<s:else>
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)" style="height: 25px;"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:else>
						<td>
							<s:property value="#pageStatus.index+1" />
						</td>
						<td align="center">
							<s:if test='#pageSupCorePayable.coreType=="主营"'>
								<div
									style="font-size: 12px; border-radius: 50%; border: solid 1px #000000; width: 24px; height: 21px; padding-top: 3px;"
									align="left">
									${pageSupCorePayable.coreType}
								</div>
							</s:if>
							<s:else>
								<div
									style="font-size: 12px; border-radius: 50%; border: solid 1px #000000; width: 30px; height: 32px; padding-top: 0px;"
									align="center">
									非
									<br />
									主营
								</div>
							</s:else>
						</td>
						<td style="width: 200px;" align="left">
							<s:if test='#pageSupCorePayable.coreType=="主营"'>
								<a href="javascript:;"
									onclick="showKefu('${pageSupCorePayable.supplierId}')">
									${pageSupCorePayable.supplierName}</a>
							</s:if>
							<s:else>
							${pageSupCorePayable.supplierName}
						</s:else>
						</td>
						<td>
							<div class="hwjindu" style="cursor: pointer;"
								data="${pageSupCorePayable.sellNumber/pageSupCorePayable.cargoTotal*100}")">
							</div>
						</td>
						<td align="left" style="line-height: 25px;">
							<div style="border-bottom: 1px solid #000000;">
								<a
									href="GoodsStoreAction!rukuList.action?goodsStore.gysId=${pageSupCorePayable.supplierId}">
									收:${pageSupCorePayable.shouNumber}</a>
							</div>
							<div style="border-bottom: 1px solid #000000;">
								存:${pageSupCorePayable.goodsNumber}
							</div>
							<div style="border-bottom: 1px solid #000000;">
								用:${pageSupCorePayable.useNumber}
							</div>
							<div style="border-bottom: 0px solid #000000;">
								发:${pageSupCorePayable.sellNumber}
							</div>
						</td>
						<td>
							<div class="fpjindu" style="cursor: pointer;"
								data="${pageSupCorePayable.kaipiaoYgz/pageSupCorePayable.billingTotal*100}")">
							</div>
						</td>
						<td align="left" style="line-height: 25px;">
							<div style="border-bottom: 1px solid #000000;">
								已开:
								<a
									href="CorePayableAction!findCorePaybleList.action?pageStatus=yikaipiao&corePayable.supplierId=${pageSupCorePayable.supplierId }">
									${pageSupCorePayable.kaipiaoYgz}</a>(已挂账)
							</div>
							<div style="border-bottom: 1px solid #000000;">
								已开:
								<font color="red">${pageSupCorePayable.kaipiaoWgz}(未挂账)</font>
							</div>
							<div style="border-bottom: 1px solid #000000;">
								未开:
								<a
									href="CorePayableAction!findCorePaybleList.action?pageStatus=weiyikaipiao&corePayable.supplierId=${pageSupCorePayable.supplierId }">

									<font color="red">${pageSupCorePayable.weiKaipiao}</font> </a>
							</div>
							<div style="border-bottom: 0px solid #000000;">
								结清:${pageSupCorePayable.jieqing}
							</div>
						</td>
						<td>
							<div class="fkjindu" style="cursor: pointer;"
								data="${(pageSupCorePayable.realfukuanJine+pageSupCorePayable.fukuanzhongJine)/pageSupCorePayable.yingfukuanJine*100}"
								data1="${pageSupCorePayable.sellNumber/pageSupCorePayable.cargoTotal*100*(pageSupCorePayable.kaipiaoYgz/pageSupCorePayable.billingTotal)}">
							</div>

						</td>
						<td align="left" style="line-height: 25px;">
							<div style="border-bottom: 1px solid #000000;">
								应付:
								<fmt:formatNumber type="number"
									value="${pageSupCorePayable.yingfukuanJine}" pattern="0.00"
									maxFractionDigits="2" />
							</div>
							<div style="border-bottom: 1px solid #000000;">
								已付:
								<s:if test='#pageSupCorePayable.coreType=="主营"'>
									<a
										href="NoncoreReceAction!showListDetail.action?id=${pageSupCorePayable.supplierId }&tag=all">
								</s:if>
								<s:else>
									<a
										href="ReceiptAction!findReceiptList.action?receipt.payeeId=${pageSupCorePayable.payeeId }">
								</s:else>
								<fmt:formatNumber type="number"
									value="${pageSupCorePayable.realfukuanJine}" pattern="0.00"
									maxFractionDigits="2" />
								</a>
							</div>
							<div style="border-bottom: 1px solid #000000;">
								未付:
								<s:if test='#pageSupCorePayable.coreType=="主营"'>
									<a
										href="CorePayableAction!findCorePaybleList.action?pageStatus=wfkdetail&corePayable.supplierId=${pageSupCorePayable.supplierId }">
								</s:if>
								<s:else>
									<a
										href="ReceiptAction!findReceiptList.action?receipt.payeeId=${pageSupCorePayable.payeeId }">
								</s:else>
								<fmt:formatNumber type="number"
									value="${pageSupCorePayable.weifukuanJine}" pattern="0.00"
									maxFractionDigits="2" />
								</a>
							</div>
							<div style="border-bottom: 0px solid #000000;">
								待款中:
								<a
									href="ReceiptAction!findReceiptList.action?receipt.payeeId=${pageSupCorePayable.payeeId }">
									<fmt:formatNumber type="number"
										value="${pageSupCorePayable.fukuanzhongJine}" pattern="0.00"
										maxFractionDigits="2" /> </a>
							</div>
						</td>
						<%--<div class="fkjindu" style="cursor: pointer;"
								data="${pageSupCorePayable.realfukuanJine/pageSupCorePayable.yingfukuanJine}")">
							</div>
						--%>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="20" align="right">
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
		<script type="text/javascript">
function showKefu(id) {
	popWin.showWin("1024", "980", "您正在查看供应商信息",
			"zhaobiaoAction!listByIdZhUser.action?zhUser.id=" + id);
}

function showYfDetail(id) {
	popWin
			.showWin(
					"1024",
					"980",
					"您正在查看供应商应付列表",
					"CorePayableAction!findCorePaybleList.action?pageStatus=all&corePayable.supplierId="
							+ id);
}
$(function() {
	$(".hwjindu").each(function(i) {
		var hk_val = $(this).attr('data');
		$(this).radialIndicator( {
			barColor : ( {
				0 : '#FF0000',
				66 : '#FFFF00',
				100 : '#33CC33'
			}),
			barWidth : 3,
			radius : 22,
			initValue : hk_val,
			roundCorner : true,
			percentage : true
		});
	});
	$(".fpjindu").each(function(i) {
		var hk_val = $(this).attr('data');
		$(this).radialIndicator( {
			barColor : ( {
				0 : '#FF0000',
				66 : '#FFFF00',
				100 : '#33CC33'
			}),
			barWidth : 3,
			radius : 22,
			initValue : hk_val,
			roundCorner : true,
			percentage : true
		});
	});
	$(".fkjindu").each(function(i) {
		var hk_val = $(this).attr('data');
		var hk_val1 = parseInt($(this).attr('data1'));
		$(this).radialIndicator( {
			barColor : ( {
				0 : '#FF0000',
				10 : '#33CC33',
				100 : '#33CC33'
			}),
			barWidth : 3,
			radius : 22,
			initValue : hk_val,
			roundCorner : true,
			percentage : true
		});
	});
});
</script>
	</body>
</html>
