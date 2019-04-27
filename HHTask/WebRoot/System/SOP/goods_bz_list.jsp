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
		<div id="bodyDiv" align="center" class="transDiv">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 980px;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">产品明细与维护</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv" align="center"
					style="background-color: #ffffff; width: 100%;">
					<table class="table">
						<tr align="center">
							<th>
								数量
							</th>
							<td>
								<input type="hidden" id="goodsId" name="goods.goodsId">
								<input type="hidden" id="trindex">
								<input id="goodsCurQuantity" name="goods.goodsCurQuantity">
							</td>
						</tr>
						<tr>
							<td align="center" colspan="2">
								<input type="button" value="领取" onclick="out()"
									style="width: 60px; height: 40px;">
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h3>
					查询
				</h3>
				<form action="goodsAction!findGoodsForBz.action" method="post">
					<table class="table">
						<tr>
							<th align="right">
								件号
							</th>
							<td>
								<input type="text" value="${goods.goodsMarkId}"
									name="goods.goodsMarkId" />
							</td>
							<th align="right">
								批次
							</th>
							<td>
								<input type="text" value="${goods.goodsLotId}"
									name="goods.goodsLotId" />
							</td>
						</tr>
					</table>
					<input type="submit" value="查询" class="input" />
				</form>
				<table class="table">
					<tr align="center" bgcolor="#c0dcf2" height="50px">
						<th>
							序号
						</th>
						<th>
							内部订单号
						</th>
						<th>
							业务件号
						</th>
						<th>
							件号
						</th>
						<th>
							库存批次
						</th>
						<th>
							名称
						</th>
						<th>
							库别
						</th>
						<th>
							仓区
						</th>
						<th>
							库位
						</th>
						<th>
							数量
						</th>
						<th>
							操作
						</th>
					</tr>
					<s:iterator value="list" id="pageGoods" status="pageindex">
						<s:if test="#pageindex.index%2==1">
							<tr align="center" bgcolor="#e6f3fb" id="tr${pageindex.index}"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								id="tr${pageindex.index}" onmouseout="outBgcolor(this,'')">
						</s:else>
						<td align="left">
							${pageindex.index+1}
						</td>
						<td align="left">
							${pageGoods.neiorderId}
						</td>
						<td align="left">
							${pageGoods.ywmarkId}
						</td>
						<td align="left">
							${pageGoods.goodsMarkId}
						</td>
						<td align="left">
							${pageGoods.goodsLotId}
						</td>
						<td align="left">
							${pageGoods.goodsFullName}
						</td>
						<td align="right">
							${pageGoods.goodsClass}
						</td>
						<td align="right">
							${pageGoods.goodHouseName}
						</td>
						<td align="right">
							${pageGoods.goodsPosition}
						</td>
						<td align="right">
							<s:if test="#pageGoods.bzApplyCount==null">
							${pageGoods.goodsCurQuantity }
							</s:if>
							<s:else>
								${pageGoods.goodsCurQuantity- pageGoods.bzApplyCount}
							</s:else>
						</td>
						<td align="center">
							<a
								href="goodsAction!findGoodsDetailForBzSq.action?id=${pageGoods.goodsId}">打包申请</a>
						</td>
						</tr>
					</s:iterator>
					<tr>
						<td colspan="26" align="right">
							第
							<font color="red"><s:property value="cpage" /> </font> /
							<s:property value="total" />
							页
							<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
								styleClass="page" theme="number" />

						</td>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
