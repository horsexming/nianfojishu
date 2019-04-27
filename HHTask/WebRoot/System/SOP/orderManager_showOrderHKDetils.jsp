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
		<style type="text/css">
.divcss5 {
	float: left;
	width: 32%;
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
							<br />
							(Order Management)
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
					订单信息(Order Information)
				</h3>
				<table class="table" style="width: 100%;">
					<tr>
						<th align="right" style="width: 150px;">
							订单编号(内部):
							<br />
							Internal Order Number :
						</th>
						<td>
							${om.orderNum}
						</td>
					</tr>
					<tr>
						<th align="right" style="width: 150px;">
							订单编号(外部):
							<br />
							External Order Number :
						</th>
						<td>
							${om.outOrderNumber}
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
							<br />
							:
						</th>
						<td>
							${om.custome.number}
						</td>
					</tr>
					<tr>
						<th align="right">
							跟单人:
							<br />
							:
						</th>
						<td>
							${om.documentaryPeople}
						</td>
					</tr>
					<tr>
						<th align="right">
							开票及时率:
							<br />
							:
						</th>
						<td>
							${om.kprate}
						</td>
					</tr>
					<tr>
						<th align="right">
							回款率:
							<br />
							:
						</th>
						<td>
							${om.hkrate}
						</td>
					</tr>
				</table>
				<h3>
					订单对应产品
					<br />
					(Order the corresponding product)
				</h3>
				<table class="table" style="width: 100%;">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
							<br />
							No.
						</td>
						<td align="center">
							件号
							<br />
							Part No.
						</td>
						<td align="center">
							产品名称
							<br />
							Product Name
						</td>
						<td align="center">
							数量
							<br />
							Quantity
						</td>
						<td align="center">
							单价
							<br />
							Unit price
						</td>
						<td align="center">
							申请数量
							<br />
							apply count
						</td>
						<td align="center">
							回款数量
							<br />
							back count
						</td>
						<td align="center">
							废单数量
							<br />
							cut count
						</td>
					</tr>
					<s:iterator value="om.products" id="pageList" status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')" onclick="showFaPiao('${om.outOrderNumber}','${pageList.pieceNumber}')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')" onclick="showFaPiao('${om.outOrderNumber}','${pageList.pieceNumber}')">
						</s:else>
						<td>
							<s:property value="#pageStatus.index+1" />
						</td>
						<td>
							${pageList.pieceNumber }
						</td>
						<td>
							${pageList.name}
						</td>
						<td>
							${pageList.num}
						</td>
						<td>
							${pageList.unit}
						</td>
						<td>
							${pageList.applyNumber}
						</td>
						<td>
							${pageList.hkNumber}
						</td>
						<td>
							${pageList.cutNum}
						</td>
						<tr>
							<th align="left" colspan="20"
								style="background-color: #c33e41; border: 0px; margin: 0px; padding: 0px; font-size: 3px;">
								<div align="left"
									style="background-color: #00ff01; width:${pageList.hkNumber/(pageList.num-cutNum)*100}%; text-overflow: ellipsis; white-space: nowrap; height: 100%;">
									<a target="_blank" href="huikuanAction!showFaPiao.action?id=${pageList.id}&tag=p">&nbsp;&nbsp;&nbsp;&nbsp;产品回款进度(Product delivery schedule):
									${pageList.hkNumber}/${pageList.num-cutNum}</a>
								</div>
							</th>
						</tr>
					</s:iterator>
					</tr>
				</table>
			</div>
			<!--发票管理模块div -->
			<div class="divcss5" align="center">
				<div align="center">
					<div style="float: left; width: 80%;">
						<h1>
							发票管理
							<br />
							(bill Management)
						</h1>
					</div>
					<div style="float: left; width: 20%; padding-top: 10px;"
						align="right">
						<img alt="" src="<%=basePath%>images/jiantou.png" />
					</div>
					<div style="clear: both;">
					</div>
				</div>
				<div id="showFaPiao">
				<hr />
				<h3>
					发票(Invoice)
				</h3>
				<table class="table" style="width: 100%; margin-bottom: 32px;">
					<tr bgcolor="#c0dcf2" height="50px">
						
						<td align="center">
							发票单号
							<br />
							bill number
						</td>
						<td align="center">
							客户名称
							<br />
							customer name
						</td>
						<td align="center">
							负责人
							<br />
							Principal
						</td>
						
						<td align="center">
							开票金额
							<br />
							money
						</td>
						<td align="center">
							折扣
							<br />
							Discount
						</td>
						<td align="center">
							状态
							<br />
							status
						</td>
					</tr>
					<s:iterator value="taHkHuikuanList" id="pageList"
						status="pageStatus">
						<s:if test="#pageStatus.first">
							<script type="text/javascript">
$(function() {
	showKaiPiaoDetail('${pageList.id}','${om.orderNum}');
})
</script>
						</s:if>
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								style="cursor: pointer; line-height: 25px;"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')"
								onclick="showKaiPiaoDetail('${pageList.id}','${om.orderNum}')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								style="cursor: pointer; line-height: 25px;"
								onmouseout="outBgcolor(this,'')"
								onclick="showKaiPiaoDetail('${pageList.id}','${om.orderNum}')">
						</s:else>
						<td>
							${pageList.hkNum}
						</td>
						<td>
							${pageList.hkClientComp}
						</td>
						<td>
							${pageList.hkClientName}
						</td>
						<td>
							${pageList.hkBillMoney}
						</td>
						<td>
							${pageList.zhekou}
						</td>
						<td>
							<a target="_blank" href="huikuanAction!showFaPiao.action?id=${pageList.id}&tag=f">${pageList.hkStatus}</a>
						</td>
						</tr>
					</s:iterator>
				</table>
			</div>
				<div id="showKaiPiaoDetail">
				</div>
			</div>
			<!--回款单div -->
			<div class="divcss5" align="center">
				<div align="center">
					<div style="float: left; width: 80%;">
						<h1>
							回款单
							<br />
							(back money bill)
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
				<div id="showBackMoney" style="width: 100%;">
				</div>
				<div id="showBackMoneyDetail">
				</div>
			</div>
		</div>
		<script type="text/javascript">
//显示发票
function showFaPiao(orderNum,markId) {
	$
			.ajax( {
				type : "POST",
				url : "huikuanAction!showKaiPiaoDan.action",
				data : {
					'outOrderNumber' : orderNum,
					'markId' : markId,
				},
				dataType : "json",
				success : function(data) {
					$("#showFaPiao").html("");
					var html = "<h3>发票管理(Bill manage)</h3><table class='table' style='width: 100%;'>"
							+ "<tr bgcolor='#c0dcf2' height='50px'><th>发票单号<br />bill number</th><th>客户名称<br />customer name</th><th>负责人 <br />Principa</th><th>开票金额<br />mone</th><th>折扣<br />Discount</th><th>状态 <br />status</th></tr>";
					$
							.each(
									data,
									function(i, n) {
										html += "<tr onmouseover=chageBgcolor(this) "
												+ "onmouseout=outBgcolor(this,'#ffffff') style='height:50px;cursor: pointer;' onclick=showKaiPiaoDetail('" +
												+n.id
												+"','"
												+orderNum
												+ "')><td>"
												+ n.hkNum
												+ "</td><td>"
												+ n.hkClientComp
												+ "</td><td>"
												+ n.hkClientName
												+ "</td><td>"
												+ n.hkBillMoney
												+ "</td><td>"
												+ n.zhekou
												+ "</td><td><a target='_blank' href='huikuanAction!showFaPiao.action?id="
												+n.id
												+"&tag=f'>"
												+ n.hkStatus
												 "</a></td></tr>";
												if(i==0){
													showKaiPiaoDetail(n.id,orderNum);
												}

									});
					html += "</table>";
					$("#showFaPiao").html(html);
				}
			});
}
//显示发票明细
function showKaiPiaoDetail(kpId,orderNum) {
	$
			.ajax( {
				type : "POST",
				url : "huikuanAction!showKaiPiaoDetail.action",
				data : {
					'id' : kpId,
					'outOrderNumber' : orderNum,
				},
				dataType : "json",
				success : function(data) {
					$("#showKaiPiaoDetail").html("");
					var html = "<h3>发票明细(Plans to complete details)</h3><table class='table' style='width: 100%;'>"
							+ "<tr bgcolor='#c0dcf2' height='50px'><th>件号<br/>Part No.</th><th>名称<br/>Name</th><th>送货单号<br/>send number</th><th>开票数量<br/>bill count</th><th>开票金额<br/>bill money</th></tr>";
					$
							.each(
									data,
									function(i, n) {
										var hkSellCount;
										var jindu =0 ;
										if(n.hkSellCount==null){
											hkSellCount="申请数"+n.applyCount;
										}else{
											hkSellCount=n.hkSellCount;
											jindu =n.hkcount / n.hkSellCount
												* 100;
										}
										var hkSellMoney;
										if(n.hkSellMoney==null){
											hkSellMoney="未确定";
										}else{
											hkSellMoney=n.hkSellMoney;
										}
										
										html += "<tr onmouseover=chageBgcolor(this) "
												+ "onmouseout=outBgcolor(this,'#ffffff') style='height:50px;cursor: pointer;' onclick=showBackMoney('"
												+ kpId
												+"','"
												+orderNum
												+"','"
												+n.hkSellMarkId
												+ "')><td>"
												+ n.hkSellMarkId
												+ "</td><td>"
												+ n.hkSellGoods
												+ "</td><td>"
												+ n.hkSellSendId
												+ "</td><td>"
												+ n.hkcount
												+ "/"
												+ hkSellCount
												+ "</td><td>"
												+ n.hkmoney
												+ "/"
												+ hkSellMoney + "</td></tr>";
												
										html += "<tr><th align='left' colspan='20' style='background-color: #c33e41; border: 0px; margin: 0px; padding: 0px;'>"
												+ "<div align='left' style='background-color: #00ff01;font-size:3px; width:"
												+ jindu
												+ "%; text-overflow: ellipsis; "
												+ "white-space: nowrap; height: 100%;'>&nbsp;&nbsp;&nbsp;&nbsp;完成进度:"
												+ ""
												+ jindu.toFixed(2)
												+ "%</div></th></tr>";

									});
					html += "</table>";
					$("#showKaiPiaoDetail").html(html);
				}
			});
	showBackMoney(kpId,orderNum);
}

//显示回款单
function showBackMoney(id,orderNum,markId) {
	$
			.ajax( {
				type : "POST",
				url : "huikuanAction!showBackMoney.action",
				data : {
					'id' : id,
					'outOrderNumber' : orderNum
				},
				dataType : "json",
				success : function(data) {
					$("#showBackMoney").html("");
					$("#showBackMoneyDetail").html("");
					var html_backMoney = "<h3>回款单(back money bill)</h3><table class='table' style='width: 100%;'><table class='table' style='width: 100%;'>"
							+ "<tr bgcolor='#c0dcf2' height='50px' ><th>发票单号<br/>bill number</th><th>客户名称<br/>customer name</th><th style='width: 10px;'>回款金额<br/>back money</th><th>回款数量<br/>back count</th><th>回款日期<br/>back date</th></tr>";
							$
							.each(
									data,
									function(i, n) {
										html_backMoney += "<tr style='line-height: 25px;cursor: pointer;' onmouseover=chageBgcolor(this) "
												+ "onmouseout=outBgcolor(this,'#ffffff') onclick=showBackMoneyDatail('"
												+ n.id
												+ "','"
												+orderNum
												+ "')><td>"
												+ n.hkbmNum
												+ "</td><td>"
												+ n.hkbmClientCom
												+ "</td><td>"
												+ n.hkbmMoney
												+ "</td><td>"
												+ n.hkbmCount
												+ "</td><td>"
												+ n.hkbmDate
												+"</td></tr>";
										
										if (i == 0) {
											if(markId==null){
											showBackMoneyDatail(n.id,orderNum);
											}
											
											
										}
									});
					html_backMoney += "</table>";
					$("#showBackMoney").html(html_backMoney);
				}
			});
	if(markId!=null){
		showBackMoneyDatail(id,orderNum,markId);
	}
}
//显示回款单明细
function showBackMoneyDatail(id,orderNum,markId) {//有markId说明id是发票明细的id没有markId说明id是回款单的id
	$
			.ajax( {
				type : "POST",
				url : "huikuanAction!showBackMoneyDatail.action",
				data : {
					'id' : id,
					'outOrderNumber' : orderNum,
					'markId' : markId
				},
				dataType : "json",
				success : function(data) {
					$("#showBackMoneyDetail").html("");
					var html_procard = "<h3>回款单明细(back money bill detail)</h3><table class='table' style='width: 100%;'><table class='table' style='width: 100%;'>"
							+ "<tr bgcolor='#c0dcf2' height='50px' ><th>发票单号<br/>bill number</th><th>件号<br/>part number</th><th>回款数量<br/>back count</th><th>回款金额<br/>back money</th><th>回款时间<br/>back time</th></tr>";
					$
							.each(
									data,
									function(i, n) {
										html_procard += "<tr style='line-height: 25px;cursor: pointer;' onmouseover=chageBgcolor(this) "
												+ "onmouseout=outBgcolor(this,'#ffffff')><td>"
												+ n.hkbmNum
												+ "</td><td>"
												+ n.hkmarkId
												+ "</td><td>"
												+ n.hkbmCount
												+ "</td><td>"
												+ n.hkbmMoney
												+ "</td><td>"
												+ n.hkTime
												+ "</td></tr>";
									});
					html_procard += "</table>";
					$("#showBackMoneyDetail").html(html_procard);
				}
			});
	
}

<%--function showRukuList(markid, selfCard) {--%>
<%--	showModalDialog("GoodsStoreAction!rukuList.action?tag=showRuku&goodsStore.goodsStoreLot="--%>
<%--			+ selfCard + "&goodsStore.goodsStoreMarkId=" + markid);--%>
<%--}--%>
<%--function showChukuList(markid, selfCard) {--%>
<%--	showModalDialog("sellAction!findSellByCondition.action?tag=showChuku&sell.sellLot="--%>
<%--			+ selfCard + "&sell.sellMarkId=" + markid);--%>
<%--}--%>
</script>
	</body>
</html>
