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
				<h3>
					转换订单(Conversion Order)
				</h3>
				<%--				<form--%>
				<%--					action="orderManager_queryOrderManagerByCondition.action?status=inner"--%>
				<%--					method="post">--%>
				<form action="internalOrder_initGenerateInnerOrder.action"
					method="post">
					<table class="table">
						<tr>
							<th align="right">
								订单编号：
								<br />
								(Order Number):
							</th>
							<td>
								<input type="text" name="om.orderNum" value="" />
							</td>
							<th align="right">
								交付状态
								<br />
								(Delivery Status)：
							</th>
							<td>
								<input type="text" name="om.deliveryStatus" value="" />
							</td>
							<th align="right">
								跟单人：
								<br />
								(With a single person)：
							</th>
							<td>
								<input type="text" name="om.documentaryPeople" value="" />
							</td>
							<th align="right">
								客户：
								<br />
								(Customers)：
							</th>
							<td>
								<select name="customeId">
									<option value="0" selected="selected">
										选择用户
									</option>
									<s:iterator id="c" value="clients">
										<s:if test="#c.id == customeId">
											<option value="${c.id}" selected="selected">
												${c.clientcompanyname}
											</option>
										</s:if>
										<s:else>
											<option value="${c.id}">
												${c.clientcompanyname}
											</option>
										</s:else>
									</s:iterator>
								</select>
							</td>
						</tr>
						<tr>
							<th align="right">
								开单人：
								<br />
								(Billing person)：
							</th>
							<td>
								<input type="text" name="om.billingPeople" value="" />
							</td>
							<th align="right">
								开始日期：
								<br />
								(Start date)：
							</th>
							<td>
								<input style="width: 155px" class="Wdate" type="text"
									name="beginTime" value="${beginTime}"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<th align="right">
								结束日期：
								<br />
								(End Date)：
							</th>
							<td>
								<input style="width: 155px" class="Wdate" type="text"
									name="endTime" value="${endTime}"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<td>
								<input type="hidden" name="errorMessage" value="all" />
								<input type="hidden" name="id" value="${id}" />
								<input type="hidden" value="${tag}" name="tag"/>
								<input type="submit" value="查询"
									style="width: 80px; height: 50px;" />
							</td>
						</tr>
					</table>
				</form>
				<form
					action="internalOrder_batchConversionOrder.action"
					method="post" onsubmit="return vali()">
					<table class="table">
						<tr bgcolor="#c0dcf2" height="50px">
							<td></td>
							<td align="center">
								序号
								<br />
								No.
							</td>
							<td align="center">
								订单编号
								<br />
								Order Number
							</td>
							<td align="center">
								总金额
								<br />
								Total amount
							</td>
							<td align="center">
								客户
								<br />
								Customers
							</td>
							<td align="center">
								支付日期
								<br />
								Payment Date
							</td>
							<td align="center">
								跟单人
								<br />
								With a single person
							</td>
							<td align="center">
								开单人
								<br />
								Billing person
							</td>
							<td align="center">
								交付状态
								<br />
								Delivery Status
							</td>
							<td align="center">
								回款状态
								<br />
								Reimbursement status
							</td>
							<td></td>
							<td></td>
						</tr>
						<s:iterator value="list" id="pageList" status="pageStatus">
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
								<input type="radio" value="${pageList.id }" name="selected">
							</td>
							<td>
								<s:property value="#pageStatus.index+1" />
							</td>
							<td>
								${pageList.orderNum }
							</td>
							<s:if test="#pageList.totalAmount == 0.0">
								<td>
									0.00
								</td>
							</s:if>
							<s:else>
								<td>
									${pageList.totalAmount }
								</td>
							</s:else>
							<td>
								${pageList.custome.clientcompanyname}
							</td>
							<td>
								${pageList.paymentDate}
							</td>
							<td>
								${pageList.documentaryPeople }
							</td>
							<td>
								${pageList.billingPeople}
							</td>
							<td>
								${pageList.deliveryStatus }
							</td>
							<td>
								${pageList.backSection }
							</td>
							<td>
								<input type="button" value="明细"
									style="width: 60px; height: 30px;"
									onclick="detail(${pageList.id})" />
							</td>
							</tr>
						</s:iterator>
						<tr>
							<s:if test="errorMessage==null">
								<td colspan="12" align="right">
									第
									<font color="red"><s:property value="cpage" /> </font> /
									<s:property value="total" />
									页
									<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
										styleClass="page" theme="number" />
							</s:if>
							<s:else>
								<td colspan="11" align="center" style="color: red">
									${errorMessage}
							</s:else>
							</td>
						</tr>
						<tr>
							<td colspan="15">
								<input type="hidden" name="pageStatus" value="${pageStatus}" />
								<input type="hidden" name="tag" value="${tag}" />
								<input type="submit" style="width: 80px; height: 50px;"
									value="转换订单" />
							</td>
						</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function generator() {
	window.location = "internalOrder_initGenerateInnerOrder.action";
}
function detail(id) {
	window.location = "orderManager_queryDetail.action?id=" + id
			+ "&status=inner";
}
function vali() {
	var selectList = document.getElementsByName("selected");
	for ( var i = 0; i < selectList.length; i++) {
		if (selectList[i].checked) {
			return true;
		}
	}
	alert("请选择需要转换的订单！谢谢");
	return false;
}
</script>
	</body>
</html>
