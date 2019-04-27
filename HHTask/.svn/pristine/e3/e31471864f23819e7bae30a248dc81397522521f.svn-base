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
	<body style="background: url('');">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng">

			<div align="center">
				<div id="printDiv">
					<div align="center" style="font-weight: bolder;">
						<table width="100%" border="1" style="border-collapse: collapse; font-size: 8px; font-family: 黑体;">
							<tr>
								<td>
									<img src="${companyInfo.logoOKjpg}" height="40px" width="80px">
								</td>

								<td colspan="19"
									style="width: 100%; text-align: center; font-size: 25; font-weight: bold;">
									${companyInfo.name}———— 出库记录详单
								</td>
							</tr>
							<tr>

								<th align="center">
									序号
								</th>
								<th align="center">
									工艺卡号
								</th>
								<th align="center">
									批次
								</th>
								<th align="center">
									件号
								</th>
								<th align="center">
									业务件号
								</th>
								<th align="center">
									品名
								</th>
								<th>
									规格
								</th>
								<th align="center">
									数量
								</th>
								<th align="center">
									单位
								</th>
								<th align="center">
									库别
								</th>
								<th align="center">
									出库类型
								</th>
								<th align="center">
									客户
								</th>
								<th align="center">
									供应商
								</th>
								<th align="center">
									负责人
								</th>
								<th align="center">
									计划单号
								</th>
								<th align="center">
									出库时间
								</th>
								<th align="center">
									返回状态
								</th>
								<th align="center">
									支数
								</th>
								<th align="center">
									领料人
								</th>
								<th align="center">
									备注
								</th>
							</tr>
							<s:iterator value="list1" id="pageList" status="pageSat">
								<tr align="center">
									<td>
										<input type="hidden" id="sellId_${pageSat.index}"
											value="${pageList.sellId}">
										${pageSat.index+1}
									</td>
									<td>
										${pageList.sellArtsCard}
									</td>
									<td>
										${pageList.sellLot}
									</td>

									<td>
										${pageList.sellMarkId}
									</td>
									<td>
										${pageList.ywmarkId}
									</td>
									<td>
										${pageList.sellGoods}
									</td>
									<td>
										${pageList.sellFormat}
									</td>

									<td>
										${pageList.sellCount}
									</td>
									<td>
										${pageList.sellUnit}
									</td>
									<td>
										${pageList.sellWarehouse}
									</td>
									<td>
										${pageList.style}
									</td>
									<td>
										${pageList.sellCompanyName}
									</td>
									<td>
										${pageList.sellSupplier}
									</td>
									<td>
										${pageList.sellGetGoodsMan}
									</td>
									<td>
										${pageList.planID}
									</td>
									<td>
										${pageList.sellDate}
									</td>
									<td>
										${pageList.sellPeople}
									</td>
									<td>
										${pageList.sellZhishu}
									</td>
									<td>
										${pageList.sellCharger}
									</td>
									<td>
									</td>
								</tr>
							</s:iterator>
						</table>
						<br>
						<div style="font-family: 黑体; font-size: 14px;">
							审核:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							业务员:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							仓管:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							品管:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							制单:${Users.name}
						</div>
					</div>
				</div>
				<input style="width: 80px; font-size: 18px;" onclick="aaa()"
					type="button" value="打印" />
				<%--				<input type="button" value="打印"  onclick="printStick()" />--%>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<script type="text/javascript">
function aaa() {
	pagePrint('printDiv');
	var listLength = "<s:property value='list1.size()' />";
	for ( var i = 0; i < listLength; i++) {
		var sellId = $("#sellId_" + i).val();
		$.ajax( {
			type : "POST",
			url : "sellAction!print.action",
			data : {
				id : sellId
			},
			dataType : "json",
			success : function(msg) {
			}
		});

	}
}
</script>
	</body>
</html>
