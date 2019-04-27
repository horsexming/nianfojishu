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
		<div id="gongneng">
			<div align="center">
				<h2>
					入库管理入库(Storage management storage)
				</h2>
				<form action="GoodsStoreAction!sureZaizhiinputApply.action"
					method="post">
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
								名称:
								<br />
								Name:
							</th>
							<td>
								<input type="text" name="goodsStore.goodsStoreGoodsName"
									value="${goodsStore.goodsStoreGoodsName }" />
							</td>
							<th rowspan="4">
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
								负责人:
								<br />
								Leader :
							</th>
							<td>
								<input type="text" name="goodsStore.goodsStorePerson"
									value="${goodsStore.goodsStorePerson}" />
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
									value="${goodsStore.neiorderId}" />
							</td>
							<th align="right">
								日期从
								<br />
								Date from :
							</th>
							<td>
								<input class="Wdate" type="text" name="startDate"
									value="${ startDate}" size="15"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<th align="right">
								到
								<br />
								To :
							</th>
							<td>
								<input class="Wdate" type="text" name="endDate"
									value="${ endDate}" size="15"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
						</tr>
						<tr>
							<th align="right">
								业务件号 
								<br />
								Business Part No.：
							</th>
							<td>
								<input type="text" name="goodsStore.ywmarkId"
									value="${goodsStore.ywmarkId}" />
							</td>
							<td colspan="2">
							</td>
							<td colspan="2">
							</td>
						</tr>
					</table>
				</form>
				<table class="table" style="width: 95%;">
					<tr bgcolor="#c0dcf2" height="30px"
						style="border-collapse: separate;">
						<th align="center">
							序号
							<br />
							No.
						</th>
						<th align="center">
							批次
							<br />
							Batch
						</th>
						<th align="center">
							业务件号
							<br />
							Part No.
						</th>
						<th align="center">
							件号
							<br />
							Part No.
						</th>
						<th align="center">
							版本
							<br />
							version
						</th>
						<th align="center">
							工序名
						</th>
						<th align="center">
							品名
							<br />
							Name
						</th>
						<th align="center">
							需求数量
							<br />
							Quantity
						</th>
						<th align="center">
							数量
							<br />
							Quantity
						</th>
						<th align="center">
							单位
							<br />
							Unit
						</th>
						<th>
							库别
							<br />
							Warehouse
						</th>
						<th>
							仓区
							<br />
							Location
						</th>
						<th>
							库位
							<br />
							Location
						</th>
						<th align="center">
							订单号
							<br />
							Order number
						</th>
						<th align="center">
							申请时间
							<br />
							Application Period
						</th>
						<th align="center">
							入库时间
							<br />
							in time
						</th>
						<th align="center">
							状态
							<br />
							State
						</th>
						<th align="center">
							操作
							<br />
							Operation
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
									${goodsIn.ywmarkId}
								</td>
								<td>
									${goodsIn.goodsStoreMarkId}（<font color="red">${goodsIn.processNo}</font>）
								</td>
								<td>
									${goodsIn.banBenNumber}
								</td>
								<td>
									${goodsIn.processName}
								</td>
								<td>
									${goodsIn.goodsStoreGoodsName}
								</td>
								<td>
									${goodsIn.beginning_num}
								</td>
								<td>
									${goodsIn.goodsStoreCount}
								</td>

								<td>
									${goodsIn.goodsStoreUnit}
								</td>
								<td>
									${goodsIn.goodsStoreWarehouse}
								</td>
								<td>
									${goodsIn.goodHouseName}
								</td>
								<td>
									${goodsIn.goodsStorePosition}
								</td>
								<td>
									${goodsIn.neiorderId}
								</td>
								<td>
									${goodsIn.applyTime}
								</td>
								<td>
									${goodsIn.goodsStoreTime}
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
					<tr>
						<th colspan="18" bgcolor="#9BCD9B">
							入库历史记录(Warehousing History)
						</th>
					</tr>
					<s:if test="{list.size()>0}">
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
								${gs.ywmarkId}
							</td>
							<td>
								${gs.goodsStoreMarkId}
								<s:if test="#gs.processNo!=null">（<font color="red">${gs.processNo}</font>）</s:if>
							</td>
							<td>
									${gs.banBenNumber}
							</td>
							<td>
									${goodsIn.processName}
							</td>
							<td>
								${gs.goodsStoreGoodsName}
							</td>
							<td>
									${gs.beginning_num}
							</td>
							<td>
								${gs.goodsStoreCount}
							</td>
							<td>
								${gs.goodsStoreUnit}
							</td>
							<td>
								${gs.goodsStoreWarehouse}
							</td>
							<td>
								${gs.goodHouseName}
							</td>
							<td>
								${gs.goodsStorePosition}
							</td>
							<td>
								${gs.neiorderId}
							</td>
							<td>
								${gs.applyTime}
							</td>
							<td>
								${gs.goodsStoreTime}
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
						<tr>
							<td style="font-size: 15px; color: red;">
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

</script>
	</body>
</html>
