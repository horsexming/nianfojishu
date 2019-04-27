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
					个人财务中心
					<br />
					<s:if test="successMessage!=null">
						<font color="red">${successMessage}</font>
					</s:if>
				</h3>
				<table class="table" style="font-size: 15px; width: 60%;">
					<tr>
						<th>
							${Users.name} | ${Users.dept}
						</th>
						<th style="font-weight: bolder;" align="center">
							我的资产
							<div style="border: solid 1px #000000; border-radius: 50%;width: 18px;height: 18px;"><a href="FundApplyAction_toAddfundApply.action" style="color: red;">借</a></div>
						</th>
						<th style="font-weight: bolder;" align="center">
							我的负债
							<div style="border: solid 1px #000000; border-radius: 50%;width: 18px;height: 18px;"><a href="FundApplyAction_toAddfundApply.action" style="color: green;">还</a></div>
						</th>
					</tr>
					<tr>
						<td style="width: 150px;" align="center" rowspan="5">
							<s:if test='#session.Users.sex =="男"'>
								<img alt="${Users.name}"
									src="upload/user/${Users.password.picture}" width="120px;"
									style="border: solid 1px #000000" height="130px;"
									onerror="this.src='images/man.jpg'" align="middle">
							</s:if>
							<s:else>
								<img alt="${Users.name}"
									src="upload/user/${Users.password.picture}" width="120px;"
									style="border: solid 1px #000000" height="130px;"
									onerror="this.src='images/woman.jpg'" align="middle">
							</s:else>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center" style="font-size: 12px;">
							费用归集(元)
						</td>
					</tr>
					<tr>
						<td>
							<div>
								应收:
								<fmt:formatNumber type="number"
									value="${supplierCorePayable.yingfukuanJine}" pattern="0.00"
									maxFractionDigits="2" />
							</div>
						</td>
						<td>
							<div>
								应付:
								<fmt:formatNumber type="number"
									value="${supplierCorePayable2.yingfukuanJine}" pattern="0.00"
									maxFractionDigits="2" />
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div>
								已收:
								<s:if test='#supplierCorePayable.coreType=="主营"'>
									<a
										href="NoncoreReceAction!showListDetail.action?id=${supplierCorePayable.supplierId }&tag=all">
								</s:if>
								<s:else>
									<a
										href="ReceiptAction!findReceiptList.action?receipt.payeeId=${supplierCorePayable.payeeId }">
								</s:else>
								<fmt:formatNumber type="number"
									value="${supplierCorePayable.realfukuanJine}" pattern="0.00"
									maxFractionDigits="2" />
								</a>
							</div>
						</td>
						<td>
							<div>
								已付:
								<s:if test='#supplierCorePayable.coreType=="主营"'>
									<a
										href="FundApplyAction_findfundList.action?pagestatus=personyf">
								</s:if>
								<s:else>
									<a
										href="FundApplyAction_findfundList.action?pagestatus=personyf">
								</s:else>
								<fmt:formatNumber type="number"
									value="${supplierCorePayable2.realfukuanJine}" pattern="0.00"
									maxFractionDigits="2" />
								</a>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div>
								未收:
								<s:if test='#supplierCorePayable.coreType=="主营"'>
									<a
										href="CorePayableAction!findCorePaybleList.action?pageStatus=wfkdetail&corePayable.supplierId=${supplierCorePayable2.supplierId }">
								</s:if>
								<s:else>
									<a
										href="ReceiptAction!findReceiptList.action?receipt.payeeId=${supplierCorePayable.payeeId }">
								</s:else>
								<fmt:formatNumber type="number"
									value="${supplierCorePayable.weifukuanJine}" pattern="0.00"
									maxFractionDigits="2" />
								</a>
							</div>
						</td>
						<td>
							<div>
								未付:
								<s:if test='#supplierCorePayable.coreType=="主营"'>
									<a
										href="FundApplyAction_findfundList.action?pagestatus=personyf">
								</s:if>
								<s:else>
									<a
										href="FundApplyAction_findfundList.action?pagestatus=personyf">
								</s:else>
								<fmt:formatNumber type="number"
									value="${supplierCorePayable2.weifukuanJine}" pattern="0.00"
									maxFractionDigits="2" />
								</a>
							</div>
						</td>
					</tr>

					<tr>
						<th rowspan="10">
						</th>
						<td colspan="2" align="center" style="font-size: 12px;">
							借领还(PCS)
						</td>
					</tr>
					<tr>
						<td>
							已领:
							<a
								href="consuming_queryConsumingByCondition.action?voc.cardId=${Users.cardId}">${objs[1]}</a>
						</td>
						<td>
						</td>
					</tr>
					<tr>
						<td>
							已借:
							<a
								href="borrow_queryBorrowByCondition.action?vobo.cardId=${Users.cardId}">${objs[0]}</a>
						</td>
						<td>
							已还:
							<a
								href="also_queryAlsoByCondition.action?voal.cardId=${Users.cardId}">${objs[2]}</a>
						</td>
					</tr>
					<tr>
						<td>

						</td>
						<td>
							应还:${objs[0]-objs[2]}
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center" style="font-size: 12px;">
							工资归集(元)
						</td>
					</tr>
					<tr>
						<td>
							实发:<a href="WageAction!findWageByCondition.action?wage.code=${Users.code}&pageStatus=print">${objs[3]}</a>
						</td>
						<td>

						</td>
					</tr>
					<tr>
						<td colspan="2" align="center" style="font-size: 12px;">
							借阅资料
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center" style="font-size: 12px;">

						</td>
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
