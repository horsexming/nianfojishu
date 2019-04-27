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
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/css/freezeheader-style.css" />
		<%@include file="/util/sonHead.jsp"%>
	
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng">
			<div align="center">
				<h2>
					调仓待入库记录管理
				</h2>
				<font size="5" color="red" id="font"></font>
				<form id="ma" action="GoodsStoreAction!waigouWaiting.action"
					method="post">
					<input type="hidden" name="tag" value="${tag}">
					<input type="hidden" name="goodsStore.gysId"
						value="${goodsStore.gysId}">
					<table class="table">
						<tr>
							<th>
								件号:
							</th>
							<td>
								<input type="text" name="goodsStore.goodsStoreMarkId"
									value="${goodsStore.goodsStoreMarkId}" />
							</td>
							<th>
								名称:
							</th>
							<td>
								<input type="text" name="goodsStore.goodsStoreGoodsName"
									value="${goodsStore.goodsStoreGoodsName }" />
							</td>
							<th>
								规格:
							</th>
							<td>
								<input type="text" name="goodsStore.goodsStoreFormat"
									value="${goodsStore.goodsStoreFormat }" />
							</td>
						</tr>
						
						<tr>
							<th>
								版本:
							</th>
							<td>
								<input type="text" name="goodsStore.banBenNumber"
									value="${goodsStore.banBenNumber }" />
							</td>
					<th>
								业务件号：
							</th>
							<td>
								<input type="text" name="goodsStore.ywmarkId"
									value="${goodsStore.ywmarkId}" />
							</td>
							<th>
								内部订单号：
							</th>
							<td>
								<input type="text" name="goodsStore.neiorderId"
									value="${goodsStore.neiorderId}" />
							</td>
						</tr>
						<tr>
							<th>
								批次:
							</th>
							<td>
								<input type="text" name="goodsStore.goodsStoreLot"
									value="${goodsStore.goodsStoreLot }" />
							</td>
							<th>
								日期从
							</th>
							<td>
								<input class="Wdate" type="text" name="goodsStore.startDate"
									value="${goodsStore.startDate}" size="15"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<th>
								到
							</th>
							<td>
								<input class="Wdate" type="text" name="goodsStore.endDate"
									value="${goodsStore.endDate}" size="15"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
						</tr>
						<tr>
							<th colspan="8">
								<input type="submit" class="input" value="查找" />
							</th>
						</tr>
					</table>
				</form>
				<form action="GoodsStoreAction!printStorage.action" method="post"
					onsubmit="return vali()">
					<table class="gridView" align="left" id="table1"
						border="1px solid #999 ">
						<thead>
							<tr bgcolor="#c0dcf2" height="30px" id="topic"
								style="border-collapse: separate;">
								<th align="center">
									序号
								</th>
								<th align="center" style="width: 57px;">
									库别
								</th>
								<th align="center">
									仓区
								</th>
								<th align="center">
									库位
								</th>
								<th align="center">
									件号
								</th>
								<th align="center">
									业务件号
								</th>
								<th align="center">
									外部订单号
								</th>
								<th align="center">
									锁定单号
								</th>
								<th align="center">
									内部订单号
								</th>
								<th align="center">
									版本
								</th>
								<th align="center">
									供料属性
								</th>
								<th align="center">
									批次
								</th>
								<th align="center">
									物料类别
								</th>
								<th align="center">
									品名
								</th>
								<th align="center">
									规格
								</th>
								<th align="center">
									数量
								</th>
								<th align="center">
									单位
								</th>
								<th align="center">
									转换数量
								</th>
								<th align="center">
									转换单位
								</th>
								<th align="center">
									客户
								</th>
								<th align="center">
									供应商
								</th>
								<th align="center">
									入库类型
								</th>
								<th align="center">
									入库日期
								</th>
								<th align="center">
									状态
								</th>
								<th align="center">
									入库人
								</th>
								<th align="center">
									入库申请人
								</th>
								<th align="center">
									入库申请部门
								</th>
								<th align="center">
									打印单号
								</th>
								<th align="center">
									操作
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th colspan="40" bgcolor="#9BCD9B">
									外购件待入库记录
								</th>
							</tr>
							<s:iterator value="goodsStoreList" status="see" id="gs_history">
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
									${see.index+1}
								</td>
								<td align="left" style="color: gray;">
									${gs_history.goodsStoreWarehouse}
								</td>
								<td align="left" style="color: gray;">
									${gs_history.goodHouseName}
								</td>
								<td align="left" style="color: gray;">
									${gs_history.goodsStorePosition}
								</td>
								<td align="left">
									${gs_history.goodsStoreMarkId}
									<s:if test="#gs_history.processNo!=null">（<font
											color="red">${gs_history.processNo}</font>）</s:if>
								</td>
								<td align="left">
									${gs_history.ywmarkId}
								</td>
								<td align="left">
									${gs_history.waiorderId}
								</td>
								<td align="left">
									${gs_history.suodingdanhao}
								</td>
								<td align="left">
									${gs_history.neiorderId}
								</td>
								<s:if test='pagestatus == "price"'>
									<td
										style="max-width: 50px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
										<font size="1">${gs_history.goodsStoreSendId}</font>
										<ul class="qs_ul">
											<li>
												${gs_history.goodsStoreSendId}
											</li>
										</ul>
									</td>
									<td
										style="max-width: 50px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
										<font size="1">${gs_history.neiorderId}</font>
										<ul class="qs_ul">
											<li>
												${gs_history.neiorderId}
											</li>
										</ul>
									</td>
									<td align="left">
										${gs_history.goodsStorePrice}
									</td>
									<td align="left">
										${gs_history.taxprice}
									</td>
									<td align="left">
										${gs_history.hsPrice}
									</td>
									<td align="left">
										${gs_history.money}
									</td>
								</s:if>
								<td align="left">
									${gs_history.banBenNumber}
								</td>
								<td align="left">
									${gs_history.kgliao}
								</td>
								<td>
									${gs_history.goodsStoreLot}
								</td>
								<td align="left">
									${gs_history.wgType}
								</td>
								<td
									style="max-width: 50px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
									<font size="1">${gs_history.goodsStoreGoodsName}</font>
									<ul class="qs_ul">
										<li>
											${gs_history.goodsStoreGoodsName}
										</li>
									</ul>
								</td>
								<td align="left">
									${gs_history.goodsStoreFormat}
								</td>
								<td align="right">
									${gs_history.goodsStoreCount}
								</td>
								<td>
									${gs_history.goodsStoreUnit}
								</td>
								<td>
									${gs_history.goodsStoreZhishu}
								</td>
								<td>
									${gs_history.goodsStoreZHUnit}
								</td>
								<td>
									${gs_history.goodsStoreCompanyName}
								</td>
								<td
									style="max-width: 50px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
									<font size="1">${gs_history.goodsStoreSupplier}</font>
									<ul class="qs_ul">
										<li>
											${gs_history.goodsStoreSupplier}
										</li>
									</ul>
								</td>
								<td>
									${gs_history.style}
								</td>
								<td align="right">
									${gs_history.goodsStoreDate}
								</td>
								<td align="right">
									${gs_history.status}
								</td>
								<td>
									${gs_history.goodsStorePlanner}
								</td>
								<td>
									${gs_history.sqUsersName}
								</td>
								<td>
									${gs_history.sqUsersdept}
								</td>
								<td>
									${gs_history.printNumber}
								</td>
								<td>
										<a
											href="GoodsStoreAction!waigouQueren.action?goodsStore.goodsStoreId=${gs_history.goodsStoreId}">确认入库</a>
									
								</td>
							</s:iterator>
							<tr>
								<td colspan="35" align="right">
									共
									<s:property value="total" />
									页 第
									<s:property value="cpage" />
									页
									<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
										styleClass="page" theme="number" />
								</td>
							</tr>
							<tr>
								<td colspan="35">
								<input type="submit" style="width: 80px; height: 50px;"
									value="打印" />
								</td>
							</tr>
						</tbody>
					</table>
				</form>
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
