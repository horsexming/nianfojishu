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
<STYLE type="text/css">
td:hover .qs_ul {
	display: block;
}

.qs_ul {
	display: none;
	border: 1px solid #999;
	list-style: none;
	margin: 0;
	padding: 0;
	position: absolute;
	width: auto;
	background: #CCC;
	color: green;
}
</STYLE>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		
		<div id="gongneng">

			<div align="center">
				<h2>
					入库管理入库(Storage management storage)
				</h2>
				<form action="GoodsStoreAction!findRukuGoodsStore.action"
					method="post">
					<input type="hidden" name="runningWaterCard.id"
						value="${runningWaterCard.id}" />
					<input type="hidden" name="procard.id" value="${procard.id}" />
					<table class="table" style="width: 95%;">
						<tr>
							<th align="right">
								批次:
								<br />
								Batch:
							</th>
							<td>
								<input type="text" name="goodsStore.goodsStoreLot"
									value="${goodsStore.goodsStoreLot }" />
							</td>
							<th align="right">
								件号:
								<br />
								Part No.:
							</th>
							<td>
								<input type="text" name="goodsStore.goodsStoreMarkId"
									value="${goodsStore.goodsStoreMarkId }" />
							</td>
							<th align="right">
								业务件号:
								<br />
								YWPart No:
							</th>
							<td>
								<input type="text" name="goodsStore.ywmarkId"
									value="${goodsStore.ywmarkId }" />
							</td>
							<th rowspan="3">
								<input type="submit" value="查找"
									style="width: 80px; height: 100px; margin-top: 5px;" />
								&nbsp;

							</th>
						</tr>
						<tr>
							<th align="right">
								客户:
								<br />
								Customers:
							</th>
							<td>
								<input type="text" name="goodsStore.goodsStoreCompanyName"
									value="${goodsStore.goodsStoreCompanyName }" />
							</td>
							<th align="right">
								名称:
								<br />
								Name:
							</th>
							<td>
								<input type="text" name="goodsStore.goodsStoreGoodsName"
									value="${goodsStore.goodsStoreGoodsName }" />
							</td>
							<th align="right">
								工艺卡号:
								<br />
								Process card :
							</th>
							<td>
								<input type="text" name="goodsStore.goodsStoreArtsCard"
									value="${goodsStore.goodsStoreArtsCard }" />
							</td>
						</tr>
						<tr>
							<th align="right">
								订单：
								<br />
								Orders :
							</th>
							<td>
								<input type="text" name="goodsStore.neiorderId"
									value="${goodsStore.neiorderId }" />
							</td>
							<th align="right">
								日期
								<br />
								Date:
							</th>
							<td>
								<input class="Wdate" type="text" name="startDate"
									value="${ startDate}" size="15"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
									<br/>
									<b>到</b>
									<br />
									<b>To:</b>
									<br />
									<input class="Wdate" type="text" name="endDate"
									value="${ endDate}" size="15"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<th align="right">
									申请人:
								<br />
								Leader :
							</th>
							<td>
								<input type="text" name="goodsStore.sqUsersName"
									value="${goodsStore.sqUsersName}" />
							</td>
						</tr>
						<tr>
							<th align="right">
									入库人:
								<br />
								Leader :
							</th>
							<td>
								<input type="text" name="goodsStore.goodsStoreCharger"
									value="${goodsStore.goodsStoreCharger}" />
							</td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>

					</table>
				</form>
				<table class="table" style="width: 95%;">
					<tr bgcolor="#c0dcf2" height="30px"
						style="border-collapse: separate;">
						<th align="center">
							序号
						</th>
						<th align="center">
							批次
						</th>
						<th align="center">
							件号
						</th>
						<th align="center">
							版本
						</th>
						<th align="center">
							业务件号
						</th>
						<th align="center">
							品名
						</th>
						<th align="center">
							数量
						</th>
						<th align="center">
							单位
						</th>
						<th align="center">
							客户
						</th>
						<th>
							库位
						</th>
						<th align="center">
							内部订单号
						</th>
						<th align="center">
							外部订单号
						</th>
						<th align="center">
							申请时间
						</th>
						<th align="center">
							申请人
						</th>
						<th align="center">
							确认人
						</th>
						<th align="center">
							入库类型
						</th>
						<th align="center">
							状态
						</th>
						<th align="center">
							操作
						</th>
					</tr>
					<tr>
						<th colspan="18" bgcolor="#FFB6C1"
							style="color: red；font-weight :      bold;">
							入库待确认记录(Record storage to be confirmed)
						</th>
					</tr>
					<s:if test="{listIn.size()>0}">
						<s:iterator value="listIn" status="se" id="goodsIn">

							<tr align="center" bgcolor="#FFB6C1"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#FFB6C1')">

								<td>
									<s:property value="#se.index+1" />
								</td>
								<td>
									${goodsIn.goodsStoreLot}
								</td>
								<td>
									${goodsIn.goodsStoreMarkId}
								</td>
								<td>
									${goodsIn.banBenNumber}
								</td>
								<td>
									${goodsIn.ywmarkId}
								</td>
								<td
											style="max-width: 50px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
											<font size="1">${goodsIn.goodsStoreGoodsName}</font>
											<ul class="qs_ul">
												<li>
													${goodsIn.goodsStoreGoodsName}
												</li>
											</ul>
								</td>
								<td>
									${goodsIn.goodsStoreCount}
								</td>

								<td>
									${goodsIn.goodsStoreUnit}
								</td>
								<td>
									${goodsIn.goodsStoreCompanyName}
								</td>
								<td>
									${goodsIn.goodsStorePosition}
								</td>
								<td>
									${goodsIn.neiorderId}
								</td>
								<td>
									${goodsIn.waiorderId}
								</td>
								<td>
									${goodsIn.applyTime}
								</td>
								<td>
									${goodsIn.sqUsersName}
								</td>
								<td>
									${goodsIn.goodsStoreCharger}
								</td>
								<td>
									${goodsIn.style}
								</td>
								<td>
									${goodsIn.status}
								</td>
								<td>
									<a
										href="GoodsStoreAction!getOneGoodsStore.action?id=${goodsStoreId}&tag=ruku">确认</a>&nbsp;
								</td>

							</tr>
						</s:iterator>
					</s:if>
					<s:else>
						<tr>
							<td style="font-size: 15px; color: red;">
								对不起，没有查到相关的入库信息(Sorry, no storage-related information found)
							</td>
						</tr>
					</s:else>
					<s:if test="list.size()>0">
					<tr>
						<th colspan="18" bgcolor="#9BCD9B">
							入库历史记录(Warehousing History) 
						</th>
					</tr>
						<s:iterator value="list" status="see" id="gs">
							<s:if test="#see.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:property value="#see.index+1" />
							</td>
							<td>
								${gs.goodsStoreLot}
							</td>
							<td>
								${gs.goodsStoreMarkId}
							</td>
							<td>
								${goodsIn.banBenNumber}
							</td>
							<td>
								${gs.ywmarkId}
							</td>
							<td
											style="max-width: 50px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
											<font size="1">${gs.goodsStoreGoodsName}</font>
											<ul class="qs_ul">
												<li>
													${gs.goodsStoreGoodsName}
												</li>
											</ul>
							</td>
							<td>
								${gs.goodsStoreCount}
							</td>
							<td>
								${gs.goodsStoreUnit}
							</td>
							<td>
								${gs.goodsStoreCompanyName}
							</td>
							<td>
								${gs.goodsStorePosition}
							</td>
							<td>
								${gs.neiorderId}
							</td>
							<td>
								${gs.waiorderId}
							</td>
							<td>
								${gs.applyTime}
							</td>
							<td>
								${gs.sqUsersName}
							</td>
							<td>
								${gs.goodsStoreCharger}
							</td>
							<td>
								${gs.style}
							</td>
							<td>
								${gs.status}
							</td>
							<td>
								<a
									href="GoodsStoreAction!getOneGoodsStore.action?id=${goodsStoreId}&tag=update">修改</a>
							</td>

							</tr>
						</s:iterator>
						<tr>
							<td colspan="18" align="right">
								共
								<s:property value="total" />
								页 第
								<s:property value="cpage" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />

							</td>
						</tr>
					</s:if>
					<s:else>
						<tr id="tr_msg">
							<td  colspan="18" style="font-size: 15px; color: red;">
								对不起，没有查到相关的入库信息
							</td>
						</tr>
					</s:else>

				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
$(function() {
	if (/Android|webOS|iPhone|iPod|BlackBerry/i.test(navigator.userAgent)) {
		$("#tr_msg").hide();
	}
})
</script>
	</body>
</html>
