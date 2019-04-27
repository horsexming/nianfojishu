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
		<script src="javascript/jquery.percentageloader-0.1.js">
</script>
		<script src="javascript/radialIndicator.js">
</script>
		<style type="text/css">
.divcss5 {
	float: left;
	width: 24%;
	border-right: 5px double #000000;
	overflow: hidden;
}
</style>
	</head>
	<body style="background: url('');">
		<div>
			<!-- 订单管理模块div -->
			<div class="divcss5" align="center">
				<div align="center">
					<div style="float: left; width: 80%;">
						<h1>
							订单管理
							<%--							<br />--%>
							<%--							(Order Management)--%>
						</h1>
					</div>
					<div style="float: left; width: 20%; padding-top: 10px;"
						align="right">
						<img alt="" src="<%=basePath%>images/jiantou.png" />
					</div>
					<div style="clear: both;">
					</div>
				</div>
				<hr />
				<h3>
					订单信息
					<%--					(Order Information)--%>
				</h3>
				<table class="table" style="width: 100%;">
					<tr>
						<th align="right" style="width: 150px;">
							订单编号:
							<%--							<br />--%>
							<%--							Order Number :--%>
						</th>
						<td>
							${om.orderNum}
						</td>
					</tr>
					<%--<tr>
						<th align="right">
							总金额:
							<br />
							Total amount :
						</th>
						<td>
							${om.totalAmount}
						</td>
					</tr>
					--%>
					<tr>
						<th align="right">
							客户编号:
							<%--							<br />--%>
							<%--							:--%>
						</th>
						<td>
							${om.custome.number}
						</td>
					</tr>
					<tr>
						<th align="right">
							跟单人:
							<%--							<br />--%>
							<%--							:--%>
						</th>
						<td>
							${om.documentaryPeople}
						</td>
					</tr>
					<tr>
						<th align="right">
							交付日期:
							<%--							<br />--%>
							<%--							:--%>
						</th>
						<td>
							${om.paymentDate}
						</td>
					</tr>
					<tr>
						<th align="right">
							协商交付日期:
							<%--							<br />--%>
							<%--							:--%>
						</th>
						<td>
							${om.paymentDate2}
						</td>
					</tr>
					<tr>
						<th align="right">
							交付状态:
							<%--							<br />--%>
							<%--							:--%>
						</th>
						<td>
							${om.deliveryStatus}
						</td>
					</tr>
				</table>
				<h3>
					订单对应产品
					<%--					<br />--%>
					<%--					(Order the corresponding product)--%>
				</h3>
				<table class="table" style="width: 100%;">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
							<%--							<br />--%>
							<%--							No.--%>
						</td>
						<td align="center">
							件号
							<%--							<br />--%>
							<%--							Part No.--%>
						</td>
						<td align="center">
							产品名称
							<%--							<br />--%>
							<%--							Product Name--%>
						</td>
						<td align="center">
							数量
							<%--							<br />--%>
							<%--							Quantity--%>
						</td>
						<%--<td align="center">
							单价
							<br />
							Unit price
						</td>
						<td align="center">
							总价
							<br />
							total price
						</td>
					--%>
					</tr>
					<s:iterator value="om.products" id="pageList" status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#pageStatus.index+1" />
						</td>
						<td>
							${pageList.pieceNumber }
							<s:if test="#pageList.ywMarkId!=null">
								<br />
								<font color="red">(${pageList.ywMarkId})</font>
							</s:if>
						</td>
						<td>
							${pageList.name}
						</td>
						<td>
							${pageList.num}
						</td>
						<%--<td>
							${pageList.unit}
						</td>
						<td>
							${pageList.unitPrice}
						</td>
						--%>
						<tr>
							<th align="left" colspan="20"
								style="background-color: #c33e41; border: 0px; margin: 0px; padding: 0px; font-size: 3px;">
								<div align="left"
									style="background-color: #33cc33; width:${pageList.sellCount/pageList.num*100}%; text-overflow: ellipsis; white-space: nowrap; height: 100%;">
									&nbsp;&nbsp;&nbsp;&nbsp;产品交付进度:
									${pageList.sellCount}/${pageList.num}
								</div>
							</th>
						</tr>
					</s:iterator>
					</tr>
				</table>
			</div>
			<!--内部计划管理模块div -->
			<div class="divcss5" align="center">
				<div align="center">
					<div style="float: left; width: 80%;">
						<h1>
							计划管理
							<%--							<br />--%>
							<%--							(Program Management)--%>
						</h1>
					</div>
					<div style="float: left; width: 20%; padding-top: 10px;"
						align="right">
						<img alt="" src="<%=basePath%>images/jiantou.png" />
					</div>
					<div style="clear: both;">
					</div>
				</div>
				<hr />
				<h3>
					内部计划
					<%--					(Internal planning)--%>
				</h3>
				<table class="table" style="width: 100%; margin-bottom: 32px;">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
							<%--							<br />--%>
							<%--							No.--%>
						</td>
						<td align="center">
							计划编号
							<%--							<br />--%>
							<%--							Project Number--%>
						</td>
						<td align="center">
							生产计划
							<%--							<br />--%>
							<%--							Production Plan--%>
						</td>
						<td align="center">
							状态
							<%--							<br />--%>
							<%--							State--%>
						</td>
					</tr>
					<s:iterator value="om.innerOrders" id="pageList"
						status="pageStatus">
						<s:if test="#pageStatus.first">
							<script type="text/javascript">
$(function() {
	showOrderProduct('${pageList.id}');
})
</script>
						</s:if>
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								style="cursor: pointer; line-height: 25px;"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')"
								onclick="showOrderProduct('${pageList.id}')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								style="cursor: pointer; line-height: 25px;"
								onmouseout="outBgcolor(this,'')"
								onclick="showOrderProduct('${pageList.id}')">
						</s:else>
						<td>
							<s:property value="#pageStatus.index+1" />
						</td>
						<td>
							${pageList.num }
						</td>
						<td>
							${pageList.genertorDate}
						</td>
						<td>
							${pageList.status}
						</td>
						</tr>
					</s:iterator>
				</table>
				<div id="showOrderProduct">
				</div>
			</div>
			<!--生产进度管理模块div -->
			<div class="divcss5" align="center">
				<div align="center">
					<div style="float: left; width: 80%;">
						<h1>
							生产进度管理
							<%--							<br />--%>
							<%--							(Production schedule management)--%>
						</h1>
					</div>
					<div style="float: left; width: 20%; padding-top: 10px;"
						align="right">
						<img alt="" src="<%=basePath%>images/jiantou.png" />
					</div>
					<div style="clear: both;">
					</div>
				</div>
				<hr />
				<h3>
					生产批次
					<%--					(Production batch)--%>
				</h3>
				<div id="showProcard" style="width: 100%;">
				</div>
			</div>
			<!--库存管理模块div -->
			<div style="float: left; width: 24%;" align="center">
				<div align="center">
					<div style="float: left; width: 80%;">
						<h1>
							库存管理
							<%--							(Inventory Management)--%>
						</h1>
					</div>
					<div style="clear: both;">
					</div>
				</div>
				<hr />
				<h3>
					入库
					<%--					(Warehousing)--%>
				</h3>
				<div id="showGoodsStore">
				</div>
				<h3>
					出库
					<%--					(The library)--%>
				</h3>
				<div id="showSell">
				</div>
			</div>
		</div>
		<script type="text/javascript">

//显示产品
function showOrderProduct(orderId) {
	$
			.ajax( {
				type : "POST",
				url : "internalOrder_showOrderProduct.action",
				data : {
					'id' : orderId
				},
				dataType : "json",
				success : function(data) {
					$("#showOrderProduct").html("");
					var html = "<h3>计划完成明细</h3><table class='table' style='width: 100%;'>"
							+ "<tr bgcolor='#c0dcf2' height='50px'><th>件号</th><th>已转</th><th>入库</th><th>出库</th></tr>";
					$
							.each(
									data,
									function(i, n) {
										var sellnumber = n.sellCount / n.num
												* 100;
										html += "<tr onmouseover=chageBgcolor(this,'#F0F8FF') "
												+ "onmouseout=outBgcolor(this,'')  style='height:50px;cursor: pointer;' onclick=clickBgcolor(this,'#D1EEEE');showProcard('"
												+ orderId
												+ "','"
												+ n.pieceNumber
												+ "')><td>"
												+ n.pieceNumber
												+ "</td><td>"
												+ n.turnCount
												+ "/"
												+ n.num
												+ "</td><td>"
												+ n.quantityCompletion
												+ "/"
												+ n.num
												+ "</td><td>"
												+ "<div class='planselljindu' style='cursor: pointer;' data='"
												+ sellnumber
												+ "'>"
												+ "</div></td></tr>";

									});
					html += "</table>";
					$("#showOrderProduct").html(html);
					sellnumbertiao();
				}
			});
	showProcard(orderId);
}

//显示流水单
function showProcard(planOrderId, markid) {
	$
			.ajax( {
				type : "POST",
				url : "ProcardAction!findProcardByPlanOrderId.action",
				data : {
					'id' : planOrderId,
					'markid' : markid
				},
				dataType : "json",
				success : function(data) {
					$("#showProcard").html("");
					var html_procard = "<table class='table' style='width: 100%;'>"
							+ "<tr bgcolor='#c0dcf2' height='50px' ><th>进度</th><th>件号</th><th style='width: 10px;'>批次</th><th>数量</th><th>状态</th></tr>";
					$
							.each(
									data,
									function(i, n) {
										var tjnumber = n.tjNumber;
										if (tjnumber == null) {
											tjnumber = 0;
										}
										var jindu = tjnumber / n.filnalCount
												* 100;
										html_procard += "<tr style='line-height: 25px;cursor: pointer;' onmouseover=chageBgcolor(this) "
												+ "onmouseout=outBgcolor(this,'') onclick=clickBgcolor(this,'#D1EEEE',2);showGoods('"
												+ n.markId
												+ "','"
												+ n.selfCard
												+ "')><td>"
												+ "<div class='procardjindu' style='cursor: pointer;' data='"
												+ jindu
												+ "' onclick='procardjinduD("
												+ n.id
												+ ")'>"
												+ "</div></td><td>"
												+ n.markId
												+ "</td><td>"
												+ n.selfCard
												+ "</td><td>"
												+ tjnumber
												+ "/"
												+ n.filnalCount
												+ "</td><td>"
												+ n.wlstatus + "</td></tr>";

										//html_procard += "<tr><th align='left' colspan='20' style='background-color: #c33e41; border: 0px; margin: 0px; padding: 0px;'>"
										//		+ "<div align='left' style='background-color: #00ff01;font-size:3px; width:"
										//		+ jindu
										//		+ "%; text-overflow: ellipsis; "
										//		+ "white-space: nowrap; height: 100%;'>&nbsp;&nbsp;&nbsp;&nbsp;完成进度:"
										//		+ ""
										//		+ jindu
										//		+ "%</div></th></tr>";
										if (i == 0) {
											showGoods(n.markId, n.selfCard);
										}
									});
					html_procard += "</table>";
					$("#showProcard").html(html_procard);
					procardjindutiao();//进度条显示
				}
			});
}
//显示入库、出库信息
function showGoods(markid, selfCard) {
	$
			.ajax( {
				type : "POST",
				url : "GoodsStoreAction!findGoodsStores.action",
				data : {
					'goodsStore.goodsStoreMarkId' : markid,
					'goodsStore.goodsStoreLot' : selfCard
				},
				dataType : "json",
				success : function(data) {
					$("#showGoodsStore").html("");
					var html_procard = "<table class='table' style='width: 100%;'>"
							+ "<tr bgcolor='#c0dcf2' height='50px' ><th>件号</th><th>批次</th><th>名称</th><th>数量</th><th>入库时间</th></tr>";
					$
							.each(
									data,
									function(i, n) {
										var showTime = "";
										if (n.status == "待入库") {
											showTime = "待入库";
										} else {
											showTime = n.goodsStoreTime;
										}
										html_procard += "<tr style='line-height: 25px;cursor: pointer;' onmouseover=chageBgcolor(this) "
												+ "onmouseout=outBgcolor(this,'#ffffff') onclick=showRukuList('"
												+ n.goodsStoreMarkId
												+ "','"
												+ n.goodsStoreLot
												+ "')><td>"
												+ n.goodsStoreMarkId
												+ "</td><td>"
												+ n.goodsStoreLot
												+ "</td><td>"
												+ n.goodsStoreGoodsName
												+ "</td><td>"
												+ n.goodsStoreCount
												+ "</td><td>"
												+ showTime
												+ "</td></tr>";
									});
					html_procard += "</table>";
					$("#showGoodsStore").html(html_procard);
				}
			});
	$
			.ajax( {
				type : "POST",
				url : "sellAction!findSell.action",
				data : {
					'sell.sellMarkId' : markid,
					'sell.sellLot' : selfCard
				},
				dataType : "json",
				success : function(data) {
					$("#showSell").html("");
					var html_procard = "<table class='table' style='width: 100%;'>"
							+ "<tr bgcolor='#c0dcf2' height='50px' ><th>件号</th><th>批次</th><th>名称</th><th>数量</th><th>出库时间 </th></tr>";
					$
							.each(
									data,
									function(i, n) {
										html_procard += "<tr style='line-height: 25px;cursor: pointer;' onmouseover=chageBgcolor(this) "
												+ "onmouseout=outBgcolor(this,'#ffffff') onclick=showChukuList('"
												+ n.sellMarkId
												+ "','"
												+ n.sellLot
												+ "')><td>"
												+ n.sellMarkId
												+ "</td><td>"
												+ n.sellLot
												+ "</td><td>"
												+ n.sellGoods
												+ "</td><td>"
												+ n.sellCount
												+ "</td><td>"
												+ n.sellDate + "</td></tr>";
									});
					html_procard += "</table>";
					$("#showSell").html(html_procard);
				}
			});
}

function showRukuList(markid, selfCard) {
	showModalDialog("GoodsStoreAction!rukuList.action?tag=showRuku&goodsStore.goodsStoreLot="
			+ selfCard + "&goodsStore.goodsStoreMarkId=" + markid);
}
function showChukuList(markid, selfCard) {
	showModalDialog("sellAction!findSellByCondition.action?tag=showChuku&sell.sellLot="
			+ selfCard + "&sell.sellMarkId=" + markid);
}

//生产流水单进度条
function procardjindutiao() {
	$(".procardjindu").each(function(i) {
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
}
//生产流水单进度条
function procardjinduD(id) {
	var tag = "${tag}";
	if (tag == "") {
		tag = "zjl";
	}
	var flag = "${flag}";
	if (flag == "dj") {
		flag = "history";
	} else {
		flag = "cb";
	}

	window.open("ProcardAction!findProcardView.action?pageStatus=" + flag
			+ "&viewStatus=zjl&id=" + id, '_showJinduD')
}

//计划入库量进度条
function sellnumbertiao() {
	$(".planselljindu").each(function(i) {
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
}
</script>
	</body>
</html>
