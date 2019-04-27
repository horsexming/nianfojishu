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
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<br />
				<br />
				<table class="table" style="width: 100%">
					<tr>
						<th colspan="7">
							辅助明细
						</th>
					</tr>
					<tr>
						<th align="center">
							序号
						</th>
						<th align="center">
							收款单位
						</th>
						<th align="center">
							摘要
						</th>
						<th align="center">
							支付流水号
						</th>
						<th align="center">
							关联单号
						</th>
						<th align="center">
							付款类型
						</th>
						<th align="center">
							金额
						</th>
					</tr>
					<s:iterator value="cwUseDetailList" id="pagevCwUsedetail"
						status="pageIndex">
						<tr align="center" height="30px"
							ondblclick="showFzclDetail(${pagevdetail.id})">
							<td align="center">
								${pageIndex.index+1}
							</td>
							<td align="center">
								${pagevCwUsedetail.payee}
							</td>
							<td align="center">
								${pagevCwUsedetail.useFor}
							</td>
							<td align="center">
								<a target="_shownumDetail"
									href="ReceiptAction!findReceiptLogList.action?receiptLog.pkNumber=${pagevCwUsedetail.payNum}">${pagevCwUsedetail.payNum}</a>
							</td>
							<td align="center">
								<s:if test="#pagevCwUsedetail.fk_fundApplyId>0">
									<a
										href="FundApplyAction_findfundDetailedList.action?id=${pagevCwUsedetail.fk_fundApplyId}">${pagevCwUsedetail.aboutNum}</a>
								</s:if>
								<s:else>
									<a target="_shownumDetail"
										href="CorePayableAction!findCorePaybleList.action?corePayable.id=${pagevCwUsedetail.fk_monthlyBillsId}&pageStatus=all">${pagevCwUsedetail.aboutNum}</a>

								</s:else>
							</td>
							<td align="center">
								${pagevCwUsedetail.payType}
							</td>
							<td align="center">
								<fmt:formatNumber type="number"
									value="${pagevCwUsedetail.usemoney}" pattern="0.00"
									maxFractionDigits="2" />
							</td>
						</tr>
					</s:iterator>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
$(function() {
	$("#showFzclDetailIf", window.parent.document).load(function() {//绑定事件
				var main = $("#showFzclDetailIf", window.parent.document);//找到iframe对象
				//获取窗口高度 
				var thisheight;
				thisheight = document.body.scrollHeight;
				thisheight = parseFloat(thisheight);
				var conHeight = parseFloat($("body").css("height"));//contentDiv div的宽度
				//				alert("thisheight--"+thisheight);
				//				alert("conHeight--"+conHeight);
				if (conHeight > thisheight) {
					thisheight = conHeight;
				}
				if (thisheight < 500) {
					thisheight = 500;
				}
				main.height(thisheight);//为iframe高度赋值如果高度小于500，则等于500，反之不限高，自适应
			});
});
function showFzclDetail(cvdId) {
	$("#showFzclDetailIf").attr("src",
			"CwVouchersAction!findCwUseDetailByCvdId.action?id=" + cvdId);

}
</script>
	</body>
</html>
