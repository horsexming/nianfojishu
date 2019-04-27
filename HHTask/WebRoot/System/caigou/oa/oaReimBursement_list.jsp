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
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<s:if test="%{'manager'==powerTag}">
						<a
							href="oaReimBursementAction!findOAReimBursementList.action?powerTag=${powerTag}&tag=history"
							style="color: #ffffff">审核历史记录</a> &nbsp;&nbsp;&nbsp;
					</s:if>
					<s:elseif test="%{'shFD'==powerTag}">
						<a
							href="oaReimBursementAction!findOAReimBursementList.action?powerTag=${powerTag}&tag=history"
							style="color: #ffffff">审核历史记录</a> &nbsp;&nbsp;&nbsp;
					</s:elseif>
				</div>
			</div>

			<div align="center">

				<form
					action="oaReimBursementAction!findOAReimBursementList.action?powerTag=${powerTag}&tag=${tag}"
					method="post">
					<table class="table">
						<tr>
							<th>
								付款凭证编号
								<br>
								payment voucher number
							</th>
							<th>
								<select name="oaReimBursement.oaPaymentvouchernumber"
									style="width: 130px;" id="oaPaymentvouchernumber">
									<option value="">
										选择付款凭证编号
										<br>
										payment voucher number
									</option>
									<option value="${oaReimBursement.oaPaymentvouchernumber}">
										${oaReimBursement.oaPaymentvouchernumber}
									</option>
								</select>
							</th>
							<th>
								状态
								<br>
								status
							</th>
							<th>
								<select name="oaReimBursement.oastatus" style="width: 130px;"
									id="oastatus"
									onMouseOver="createDept('oastatus','oaReimBursementAction!selectItem.action?tag=oastatus&powerTag=${powerTag}')">
									<option value="">
										选择状态
									</option>
									<option value="${oaReimBursement.oastatus}">
										${oaReimBursement.oastatus}
									</option>
								</select>
							</th>
							<th>
								合同号
								<br>
								total amount

							</th>
							<th>
								<select name="oaReimBursement.oaContractnumber"
									style="width: 130px;" id="oaContractnumber"
									onMouseOver="createDept('oaContractnumber','oaReimBursementAction!selectItem.action?tag=oaContractnumber&powerTag=${powerTag}')">
									<option value="">
										选择合同号
									</option>
									<option value="${oaReimBursement.oaContractnumber}">
										${oaReimBursement.oaContractnumber}
									</option>
								</select>
							</th>
							<th rowspan="3">
								<input type="submit" style="width: 90px; height: 30px;"
									value="查询(Query)" />
							</th>
						</tr>
						<tr>
							<th>
								收款单位名称
								<br>
								associated customer name
							</th>
							<th>
								<select name="oaReimBursement.oaBeneficiary"
									style="width: 130px;" id="oaBeneficiary"
									onMouseOver="createDept('oaBeneficiary','oaReimBursementAction!selectItem.action?tag=oaBeneficiary&powerTag=${powerTag}')">
									<option value="">
										选择合同号
									</option>
									<option value="${oaReimBursement.oaBeneficiary}">
										${oaReimBursement.oaBeneficiary}
									</option>
								</select>
							</th>
							<th>
								关联客户名称
								<br>

								associated customer name
							</th>
							<th>
								<input type="text" style="width: 130px;"
									name="oaReimBursement.oaAssociatedusername"
									value="${oaReimBursement.oaAssociatedusername}" size="80px" />
							</th>
							<th>
								评审单号
								<br>
								payment voucher number
							</th>
							<th>
								<input type="text" style="width: 130px;"
									name="oaReimBursement.oaReviewnumber"
									value="${oaReimBursement.oaReviewnumber}" size="80px" />
							</th>
						</tr>
						<tr>
							<th>
								付款依据
							</th>
							<th>
								<select name="oaReimBursement.oaPaymentaccordancewith"
									style="width: 130px;" id="oaPaymentaccordancewith"
									onMouseOver="createDept('oaPaymentaccordancewith','oaReimBursementAction!selectItem.action?tag=oaPaymentaccordancewith&powerTag=${powerTag}')">
									<option value="">
										选择付款依据
									</option>
									<option value="${oaReimBursement.oaPaymentaccordancewith}">
										${oaReimBursement.oaPaymentaccordancewith}
									</option>
								</select>
							</th>
							<th>
								日期从
								<br>
								Date from
							</th>
							<th>
								<input class="Wdate" type="text" name="startDate" size="15"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</th>
							<th>
								到
								<br>
								To
							</th>
							<th>
								<input class="Wdate" type="text" name="endDate" size="15"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</th>
						</tr>
					</table>
				</form>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="30px"
						style="border-collapse: separate;">
						<th align="center">
							序号
							<br>
							No.
						</th>
						<th align="center">
							付款凭证编号
							<br>
							payment voucher number
						</th>
						<th align="center">
							收款单位名称
							<br>
							receivables Unit Name
						</th>
						<th align="center">
							关联客户名称
							<br>

							associated customer name

						</th>
						<th align="center">
							总金额
							<br>
							total amount
						</th>
						<th align="center">
							合同号
							<br>
							Contract Number
						</th>
						<th align="center">
							付款依据
							<br>
							payments basis
						</th>

						<th align="center">
							付款性质
							<br>
							Payment nature
						</th>
						<th align="center">
							付款方式
							<br>
							Payment
						</th>
						<th align="center">
							付款条件
							<br>
							payment terms
						</th>
						<th align="center">
							付款情况
							<br>
							payments
						</th>
						<th align="center">
							状态
							<br>
							state
						</th>
						<th align="center">
							类别
							<br>
							Class

						</th>
						<th align="center">
							日期
							<br>
							date
						</th>
						<th align="center" style="width: 60px;">
							操作operation
						</th>
					</tr>
					<s:if test="{auditList.size()>0}">
						<tr bgcolor="red">
							<th colspan="15" align="center">
								<font color="#ffffff"> 待审批的报账记录(超过90天)</font>
							</th>
						</tr>
						<s:iterator value="auditList" status="auditsee" id="orPrint">
							<s:if test="#auditsee.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this,'red')"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this,'red')"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:property value="#auditsee.index+1" />
							</td>
							<td>
								${orPrint.oaPaymentvouchernumber}
							</td>
							<td>
								${orPrint.oaBeneficiary}
							</td>
							<td>
								${orPrint.oaAssociatedusername}
							</td>
							<td>
								${orPrint.oathetotalamount}
							</td>
							<td>
								${orPrint.oaContractnumber}
							</td>
							<td>
								${orPrint.oaPaymentaccordancewith}
							</td>
							<td>
								${orPrint.oaPaymentnature}
							</td>
							<td>
								${orPrint.oaPayment}
							</td>
							<td>
								${orPrint.oaTermsPayment}
							</td>
							<td>
								${orPrint.oaPaymentsqk}
							</td>
							<td>
								${orPrint.oastatus}
							</td>
							<td>
								${orPrint.oaCategory}
							</td>
							<td>
								${orPrint.oadate}
							</td>
							<td>
								<a
									href="oaReimBursementAction!findViewOAReimBursement.action?id=${orPrint.id}&tag=print&powerTag=${powerTag}">打印</a>
								<br />
								<a
									href="oaReimBursementAction!findViewOAReimBursement.action?id=${orPrint.id}&tag=${tag}&powerTag=${powerTag}">预览</a>
								<br />
								<!-- 总经理审核价格筛选 -->
								<s:if test="%{'manager'==powerTag}">

									<a
										href="oaReimBursementAction!selectShaixuang.action?id=${orPrint.id}&tag=detail&&powerTag=${powerTag}">筛选</a>
									<br />
								</s:if>
								<a
									href="oaReimBursementAction!findBusinessList.action?business.oarbs.id=${orPrint.id}&tag=${tag}&&powerTag=${powerTag}">查看明细</a>
							</td>
							</tr>
						</s:iterator>
					</s:if>
					<s:if test="{listPrint.size()>0}">
						<tr>
							<td colspan="15"
								style="color: red; font-size: 14px; font-weight: bold; text-align: center;">
								可打印的报账记录
							</td>
						</tr>
						<s:iterator value="listPrint" status="see" id="orPrint">
							<td>
								<s:property value="#see.index+1" />
							</td>
							<td>
								${orPrint.oaPaymentvouchernumber}
							</td>
							<td>
								${orPrint.oaBeneficiary}
							</td>
							<td>
								${orPrint.oaAssociatedusername}
							</td>
							<td>
								${orPrint.oathetotalamount}
							</td>
							<td>
								${orPrint.oaContractnumber}
							</td>
							<td>
								${orPrint.oaPaymentaccordancewith}
							</td>
							<td>
								${orPrint.oaPaymentnature}
							</td>
							<td>
								${orPrint.oaPayment}
							</td>
							<td>
								${orPrint.oaTermsPayment}
							</td>
							<td>
								${orPrint.oaPaymentsqk}
							</td>
							<td>
								${orPrint.oastatus}
							</td>
							<td>
								${orPrint.oaCategory}
							</td>
							<td>
								${orPrint.oadate}
							</td>
							<td>

								<a
									href="oaReimBursementAction!findViewOAReimBursement.action?id=${orPrint.id}&tag=${tag}&powerTag=${powerTag}">打印</a>
								<br />
								<a
									href="oaReimBursementAction!findBusinessList.action?business.oarbs.id=${orPrint.id}&tag=${tag}&powerTag=${powerTag}">查看明细</a>

								<!--  判断状态显示
								<a onClick="return confirm('确定要删除该条记录吗？')" href="saleBudgetAction!deleteBaoXiaoDanById.action?id=${id}" >删除</a>
								-->
							</td>
							</tr>
						</s:iterator>
					</s:if>
					<s:if test="{list.size()>0}">
						<tr>
							<td colspan="15"
								style="color: Green; font-size: 14px; font-weight: bold; text-align: center;">
								<s:if test="%{'manager'==powerTag}">
									<s:if test="%{'exam'==tag}">
							审核信息
							</s:if>
									<s:else>
							审核历史信息
							</s:else>
								</s:if>
								<s:elseif test="%{'shFD'==powerTag}">
									<s:if test="%{'exam'==tag}">
							财务付款审核记录
							</s:if>
									<s:else>
							财务付款审核历史记录
							</s:else>
								</s:elseif>
								<s:elseif test="%{'shSD'==powerTag}">
							报账历史记录
							</s:elseif>

							</td>
						</tr>
						<s:iterator value="list" status="se" id="orbs">
							<s:if test="#se.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:property value="#se.index+1" />
							</td>
							<td>
								${orbs.oaPaymentvouchernumber}
							</td>
							<td>
								${orbs.oaBeneficiary}
							</td>
							<td>
								${orbs.oaAssociatedusername}
							</td>
							<td>
								${orbs.oathetotalamount}
							</td>
							<td>
								${orbs.oaContractnumber}
							</td>
							<td>
								${orbs.oaPaymentaccordancewith}
							</td>
							<td>
								${orbs.oaPaymentnature}
							</td>
							<td>
								${orbs.oaPayment}
							</td>
							<td>
								${orbs.oaTermsPayment}
							</td>
							<td>
								${orbs.oaPaymentsqk}
							</td>
							<td>
								${orbs.oastatus}
							</td>
							<td>
								${orbs.oaCategory}
							</td>
							<td>
								${orbs.oadate}
							</td>
							<td>
								<a
									href="oaReimBursementAction!findViewOAReimBursement.action?id=${orbs.id}&tag=${print}&powerTag=${powerTag}">打印</a>
								<br />
								<a
									href="oaReimBursementAction!findViewOAReimBursement.action?id=${orbs.id}&tag=${tag}&powerTag=${powerTag}">预览</a>
								<br />
								<!-- 总经理审核价格筛选 -->
								<s:if test="%{'manager'==powerTag}">

									<a
										href="oaReimBursementAction!selectShaixuang.action?id=${orbs.id}&tag=detail&&powerTag=${powerTag}">筛选</a>
									<br />
								</s:if>
								<a
									href="oaReimBursementAction!findBusinessList.action?business.oarbs.id=${orbs.id}&tag=${tag}&&powerTag=${powerTag}">查看明细</a>

								<!--  判断状态显示
								<a onClick="return confirm('确定要删除该条记录吗？')" href="saleBudgetAction!deleteBaoXiaoDanById.action?id=${id}" >删除</a>
								-->
							</td>
							</tr>
						</s:iterator>
						<tr>
							<td colspan="15" align="right">
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
							<td colspan="15" style="font-size: 15px; color: red;">
								对不起，没有查到相关的申报信息
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
	</body>
	<script type="text/javascript">
function alsoAdd() {
	window.location = "oaAppDetailAction!preSaveoaReimBursement.action";
}
$(function() {
	createDept(
			'oaPaymentvouchernumber',
			'oaReimBursementAction!selectItem.action?tag=oaPaymentvouchernumber&powerTag=${powerTag}');
	$("#oaPaymentvouchernumber").tinyselect();
})
</script>
</html>
