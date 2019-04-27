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
				<form action="OrderForB2BAction!findAllPo.action" method="post">
					<table class="table" style="width: 60%">
						<tr>
							<td align="right">
								订单状态类别
							</td>
							<td>
								<SELECT style="width: 155px" name="orderForB2B.poStatus">
									<option value="${orderForB2B.poStatus}">
										<s:if test="orderForB2B.poStatus=='before_signe_back'">待接受
										</s:if>
										<s:elseif test="orderForB2B.poStatus=='all'">全部</s:elseif>
										<s:elseif test="orderForB2B.poStatus=='signed_back'">已接受</s:elseif>
										<s:elseif test="orderForB2B.poStatus=='warn'">预警</s:elseif>
										<s:else>${orderForB2B.poStatus}</s:else>
									</option>
									<option value="all">
										全部
									</option>
									<option value="before_signe_back">
										待接受
									</option>
									<option value="signed_back">
										已接受
									</option>
									<option value="warn">
										预警
									</option>
								</SELECT>
							</td>
							<td align="right">
								业务领域
							</td>
							<td>
								<SELECT style="width: 155px" name="orderForB2B.poSubType">
									<option value="${orderForB2B.poSubType}">
										<s:if test='orderForB2B.poSubType=="E"'>工程领域
										</s:if>
										<s:elseif test='orderForB2B.poSubType=="P"'>生产领域</s:elseif>
										<s:elseif test='orderForB2B.poSubType=="G"'>综合领域</s:elseif>
										<s:else>${orderForB2B.poSubType}</s:else>
									</option>
									<option value="E">
										工程领域
									</option>
									<option value="P">
										生产领域
									</option>
									<option value="G">
										综合领域
									</option>
								</SELECT>
							</td>
						</tr>
						<tr>
							<td align="right">
								待办类型:
							</td>
							<td>
								<SELECT style="width: 155px"
									name="orderForB2B.colTaskOrPoStatus">
									<option value="${orderForB2B.colTaskOrPoStatus}">
										<s:if
											test="orderForB2B.colTaskOrPoStatus=='huaweiPublishOrder'">新下订单
										</s:if>
										<s:elseif test="orderForB2B.colTaskOrPoStatus=='all'">全部</s:elseif>
										<s:elseif
											test="orderForB2B.colTaskOrPoStatus=='huaweiNotifyCancelOrder'">订单取消通知</s:elseif>
										<s:elseif
											test="orderForB2B.colTaskOrPoStatus=='huaweiApplyCancelOrder'">订单取消确认</s:elseif>
										<s:elseif
											test="orderForB2B.colTaskOrPoStatus=='huaweiNotifyOrderChange'">内容变更通知</s:elseif>
										<s:elseif
											test="orderForB2B.colTaskOrPoStatus=='huaweiApplyRequredDateChange'">订单交期修改</s:elseif>
										<s:elseif
											test="orderForB2B.colTaskOrPoStatus=='vendorApplyPromiseDateChange'">待华为确认交期更改</s:elseif>
										<s:elseif
											test="orderForB2B.colTaskOrPoStatus=='vendorApplyQuantityChange'">待华为确认数量变更</s:elseif>
										<s:elseif
											test="orderForB2B.colTaskOrPoStatus=='huaweiNotifyOrderChange'">变更待确认订单</s:elseif>
										<s:elseif
											test="orderForB2B.colTaskOrPoStatus=='po_warning_overdue'">预警订单</s:elseif>
										<s:else>${orderForB2B.colTaskOrPoStatus}</s:else>
									</option>
									<option value="all">
										全部
									</option>
									<option value="huaweiPublishOrder">
										新下订单
									</option>
									<option value="huaweiApplyRequredDateChange">
										订单交期修改
									</option>
									<option value="huaweiNotifyOrderChange">
										内容变更通知
									</option>
									<option value="huaweiApplyCancelOrder">
										订单取消确认
									</option>
									<option value="huaweiNotifyCancelOrder">
										订单取消通知
									</option>
									<option value="vendorApplyPromiseDateChange">
										待华为确认交期更改
									</option>
									<option value="vendorApplyQuantityChange">
										待华为确认数量变更
									</option>
									<option value="vendorApplyCancelOrder">
										待华为确认取消
									</option>
								</SELECT>
							</td>
							<td align="right">
								订单状态:
							</td>
							<td>
								<SELECT style="width: 155px" name="orderForB2B.shipmentStatus">
									<s:if test="orderForB2B.shipmentStatus!=null">
										<option value="${orderForB2B.shipmentStatus}">
											<s:if test="orderForB2B.shipmentStatus=='all'">全部
											</s:if>
											<s:elseif test="orderForB2B.shipmentStatus=='NEW'">新下订单</s:elseif>
											<s:elseif test="orderForB2B.shipmentStatus=='OPEN'">在途订单</s:elseif>
											<s:elseif
												test="orderForB2B.shipmentStatus=='CLOSED FOR RECEVING'">已交货未关闭</s:elseif>
											<s:elseif test="orderForB2B.shipmentStatus=='CANCELLED'">取消订单</s:elseif>
											<s:elseif test="orderForB2B.shipmentStatus=='CLOSED'">关闭订单</s:elseif>
										</option>
									</s:if>
									<option value="all">
										全部
									</option>
									<option value="NEW">
										新下订单
									</option>
									<option value="OPEN">
										在途订单
									</option>
									<option value="CLOSED FOR RECEVING">
										已交货未关闭
									</option>
									<option value="CANCELLED">
										取消订单
									</option>
									<option value="CLOSED">
										关闭订单
									</option>
								</SELECT>
							</td>
						</tr>
						<tr>
							<td align="right">
								PO号:
							</td>
							<td>
								<input name="orderForB2B.poNumber"
									value="${orderForB2B.poNumber}">
							</td>
							<td align="right">
								Item编码:
							</td>
							<td>
								<input name="orderForB2B.itemCode"
									value="${orderForB2B.itemCode}">
							</td>
						</tr>
						<tr>
							<td align="right">
								发布开始时间:
							</td>
							<td>
								<input name="fbStartDate" value="${fbStartDate}" class="Wdate"
									type="text"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">
							</td>
							<td align="right">
								发布结束时间:
							</td>
							<td>
								<input name="fbEndDate" value="${fbEndDate}" class="Wdate"
									type="text"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">
							</td>
						</tr>
						<tr>
							<td align="right">
								承诺开始时间:
							</td>
							<td>
								<input name="cnStartDate" value="${cnStartDate}" class="Wdate"
									type="text"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">
							</td>
							<td align="right">
								承诺结束时间:
							</td>
							<td>
								<input name="cnEndDate" value="${cnEndDate}" class="Wdate"
									type="text"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<input type="submit" value="查询" class="input">
							</td>
						</tr>
					</table>
				</form>

				<table class="table">
					<tr>
						<td colspan="17" align="right">
							第
							<font color="red"><s:property value="cpage" /> </font> /
							<s:property value="total" />
							页
							<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
								styleClass="page" theme="number" />
						</td>
					</tr>
					<tr bgcolor="#c0dcf2" height="50px">
						<th align="center">
							序号
						</th>
						<th align="center" style="width: 25px;">
							订单
							<br />
							状态
						</th>
						<th align="center">
							PO号
						</th>
						<th align="center">
							Item编码
						</th>
						<th align="center">
							版本
						</th>
						<th align="center">
							Item描述
						</th>
						<th align="center">
							需求数量
						</th>
						<th align="center">
							未交付数量
						</th>
						<th align="center">
							已交付数量
						</th>
						<th align="center">
							单价
						</th>
						<th align="center">
							税率
						</th>
						<th align="center">
							发布日期
						</th>
						<%--<th align="center">
							订单有效日期
						</th>
						--%>
						<th align="center">
							承诺日期
						</th>
						<th align="center">
							收货地点
						</th>
						<th align="center">
							采购员
						</th>
						<th align="center">
							备注
						</th>
						<th align="center" colspan="2">
							操作
						</th>
					</tr>
					<s:iterator value="list" id="pageOrderForB2B" status="pageIndex">
						<s:if test="#pageIndex.index%2==1">
							<tr align="center" bgcolor="#e6f3fb" style="height: 50px;"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								style="height: 50px;" onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#pageIndex.index+1" />
						</td>
						<td align="left" style="width: 30px;">
							<s:if test="#pageOrderForB2B.shipmentStatus=='NEW'">
								新下订单
							</s:if>
							<s:elseif test="#pageOrderForB2B.shipmentStatus=='OPEN'">
								在途订单
							</s:elseif>
							<s:elseif test="#pageOrderForB2B.shipmentStatus=='CANCELLED'">
								取消订单
							</s:elseif>
							<s:elseif test="#pageOrderForB2B.shipmentStatus=='CLOSED'">
								关闭订单
							</s:elseif>
							<s:elseif
								test="#pageOrderForB2B.shipmentStatus=='CLOSED FOR RECEVING'">
								已交货未关闭
							</s:elseif>
							<s:else>${pageOrderForB2B.shipmentStatus}</s:else>
						</td>
						<td align="left">
							${pageOrderForB2B.poNumber}
						</td>
						<td align="left">
							${pageOrderForB2B.itemCode}
						</td>
						<td align="left">
							${pageOrderForB2B.itemRevision}
						</td>
						<td align="left" style="font-size: 6px; width: 150px;">
							${pageOrderForB2B.itemDescription}
						</td>
						<td align="right">
							${pageOrderForB2B.quantity}
						</td>
						<td align="right">
							${pageOrderForB2B.dueQty}
						</td>
						<td align="right">
							${pageOrderForB2B.quantityReceived}
							<br />
							<font size="1px">(${pageOrderForB2B.unitOfMeasure})</font>
						</td>
						<td align="right">
							${pageOrderForB2B.priceOverride}
							<br />
							<font size="1px">(${pageOrderForB2B.currencyCode})</font>
						</td>
						<td align="left">
							${pageOrderForB2B.taxRate}
						</td>
						<td align="right" width="80px;">
							${pageOrderForB2B.publishDate}
						</td>
						<%--<td align="left">
							${pageOrderForB2B.expireDate}
						</td>
						--%>
						<td align="right" width="80px;">
							${pageOrderForB2B.promiseDate}
						</td>
						<td align="left" style="font-size: 6px; width: 150px;">
							${pageOrderForB2B.shipToLocation}
						</td>
						<td align="left" style="width: 50px;">
							${pageOrderForB2B.agentName}
						</td>
						<td align="left" style="width: 50px;">
							${pageOrderForB2B.remark}
						</td>
						<td align="left">
							<a
								href="OrderForB2BAction!findPOById.action?orderForB2B.poHeaderId=${pageOrderForB2B.poHeaderId}&orderForB2B.poReleaseId=${pageOrderForB2B.poReleaseId}&orderForB2B.poLineId=${pageOrderForB2B.poLineId}">订单明细</a>
							<%--							<a--%>
							<%--								href="OrderForB2BAction!signBackPOList.action?orderForB2B.poHeaderId=${pageOrderForB2B.poHeaderId}&orderForB2B.poReleaseId=${pageOrderForB2B.poReleaseId}&operateType=accept">接受</a>/--%>
							<%--							<a--%>
							<%--								href="OrderForB2BAction!signBackPOList.action?orderForB2B.poHeaderId=${pageOrderForB2B.poHeaderId}&orderForB2B.poReleaseId=${pageOrderForB2B.poReleaseId}&operateType=reject">驳回</a>--%>
						</td>
						</tr>
					</s:iterator>
					<tr>
						<td colspan="17" align="right">
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
