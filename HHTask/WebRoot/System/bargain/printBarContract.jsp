<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.task.entity.Users"%>
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
.table2 {
	font-size: 16px;
	padding: 0px;
	margin: 0px;
	border-collapse: collapse;
	border-width: 0px 0 0 0px;
	width: 100%;
}

.table2 th,.table2 td {
	border-width: 0 0px 0px 0;
}
</style>
	</head>
	<body>
		<center>
			<%@include file="/util/sonTop.jsp"%>
			<div>
				<form action="bargainAction_updateBarContract.action" method="post"
					theme="simple">
					<div align="right">
						<s:if test='barContract.contract_source=="OA"'>
							<s:if test="projectManage.id!=null">
								<a
									href="QuotedPrice_findQPByCondition.action?quotedPrice.belongLayer=0&pageStatus=all&quotedPrice.proId=${projectManage.id}">
									查看项目</a>&nbsp;&nbsp;&nbsp;&nbsp;
						</s:if>
					</div>
					</s:if>
					<input type="hidden" name="barContract.contract_num1"
						value="${barContract.contract_num1}">
					<input type="hidden" name="barContract.contract_source"
						value="${barContract.contract_source}">
					<input type="hidden" name="barContract.id"
						value="${barContract.id}">
					<div align="center" id="d1">
						<div id="printDiv" class="my_show" align="center">
							<table width="100%" border="0" style="border-collapse: collapse;"
								align="center">
								<tr>
									<td align="center" colspan="10">
										<img width="45px" height="45px;"
											src="<%=basePath%>${companyInfo.logoOKjpg}" align="bottom"></img>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<font size="6px"><B>${companyInfo.name}</B> </font>
									</td>
								</tr>
								<tr>
									<td align="right" colspan="7">
										<font size="5px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											${barContract.contract_name}合同</font>
									</td>
									<td colspan="2" align="right">
										<font>合同编号:NO.${barContract.contract_num} </font>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
								</tr>
								<tr>
									<td colspan="6">
										甲方： ${barContract.demandside}
									</td>
									<td colspan="5" align="right">
										签定地点：${barContract.signedPlace} &nbsp;&nbsp;&nbsp;
									</td>
								</tr>
								<tr>
									<td colspan="6">
										乙方： ${barContract.supplier}
									</td>
									<td colspan="5" align="right">
										签定时间：${barContract.signedDate}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
								</tr>
								<tr>
									<td align="center" colspan="7">
										&nbsp;&nbsp;
									</td>
								</tr>
								<tr>
									<td colspan="8">
										<table style="size: 18px; line-height: 22px;" border="1px"
											class="table">
											<tr>
												<td colspan="8">
													经双方协商，乙方根据甲方的需求，向甲方提供相关产品
												</td>
											</tr>
											<tr>
												<s:if test='barContract.contract_num1=="工装采购"'>
													<td colspan="8">
														一、项目名称：${barContract.projectname}
													</td>
												</s:if>
												<s:else>
													<td colspan="8">
														一、零件及价格：
													</td>
												</s:else>

											</tr>
											<tr>
												<s:if test='barContract.contract_source=="OA"'>
													<td align="center">
														序号
													</td>
													<td align="center">
														物品名称
													</td>
													<td align="center">
														类型
													</td>
													<td align="center">
														规格
													</td>
													<td align="center">
														数量
													</td>
													<td align="center">
														单位
													</td>
													<td align="center">
														预算单价(元)
													</td>
													<td align="center">
														金额(RMB/元)
													</td>
												</s:if>
												<s:if test='barContract.contract_source=="SB"'>
													<td align="center">
														序号
													</td>
													<td align="center">
														货物名称
													</td>
													<td align="center">
														规格
													</td>
													<td align="center">
														单位
													</td>
													<td align="center">
														数量
													</td>
													<td align="center">
														单价
													</td>
													<td align="center" colspan="3">
														备注
													</td>
												</s:if>
												<s:if test='barContract.contract_source=="XBYJ"'>
													<td align="center">
														序号
													</td>
													<td align="center">
														品名
													</td>
													<td align="center">
														规格
													</td>
													<td align="center">
														采购数量
													</td>
													<td align="center">
														单位
													</td>
													<td align="center">
														采购交期
													</td>
													<td align="center">
														单价
													</td>
													<td align="center">
														总金额
													</td>
												</s:if>
												<s:if test='barContract.contract_source=="紧急采购"'>
													<td align="center">
														序号
													</td>
													<td align="center">
														品名
													</td>
													<td align="center">
														单位
													</td>
													<td align="center">
														数量
													</td>
													<td align="center">
														单价
													</td>
													<td align="center" colspan="2">
														金额
													</td>
												</s:if>
												<s:if test='barContract.contract_source=="零部件及工序外委采购"'>
													<td align="center">
														序号
													</td>
													<td align="center">
														名称
													</td>
													<td align="center">
														件号
													</td>
													<td align="center">
														每件单价(元/${barContract.istax})
													</td>
													<td align="center">
														型别
													</td>
													<td align="center">
														生产类型
													</td>
													<td align="center">
														工序号
													</td>
													<td align="center">
														产品类型
													</td>
												</s:if>
												<s:if
													test='barContract.contract_source=="原材料采购"||barContract.contract_source=="包装物"'>
													<td align="center">
														序号
													</td>
													<td align="center">
														牌号
													</td>
													<td align="center">
														规格
													</td>
													<td align="center">
														单位
													</td>
													<td align="center" colspan="2">
														含税单价(元/KG)
													</td>
												</s:if>
												<s:if test='barContract.contract_source=="工装采购"'>
													<tr>
														<td align="center">
															申请单号
														</td>
														<td align="center">
															招标单号
														</td>
														<td align="center">
															名称
														</td>
														<td align="center">
															零件号
														</td>
														<td align="center">
															工装号
														</td>
														<td align="center">
															数量
														</td>
														<td align="center">
															含税金额(17%)
														</td>
													</tr>
												</s:if>
												<s:if test='barContract.contract_source=="设备"'>
													<tr>
														<td align="center">
															序号
														</td>
														<td align="center">
															设备编号
														</td>
														<td align="center">
															设备名称
														</td>
														<td align="center">
															设备类型
														</td>
														<td align="center">
															部门
														</td>
														<td align="center">
															购买金额
														</td>
													</tr>
												</s:if>

												<s:if test='barContract.contract_source=="KVP"'>
													<tr>
														<td align="center">
															序号
														</td>
														<td align="center">
															项目执行单编号
														</td>
														<td align="center">
															改进员工
														</td>
														<td align="center">
															责任员工
														</td>
														<td align="center" colspan="3">
															成本结余
														</td>
													</tr>
												</s:if>
											</tr>
											<s:if test='barContract.contract_source=="OA"'>
												<s:if test="list.size <7">
													<s:iterator value="list" status="pageIndex">
														<tr>
															<td align="center">
																${pageIndex.index+1}
															</td>
															<td align="center">
																${detailAppName}
															</td>
															<td align="center">
																${detailChildClass}
															</td>
															<td align="center">
																${detailFormat}
															</td>
															<td align="center">
																${detailCount}
															</td>
															<td align="center">
																${detailUnit}
															</td>
															<td align="center">
																${detailBudgetMoney}
															</td>
															<td align="center">
																${zongMoney}
															</td>
														</tr>
													</s:iterator>
												</s:if>
												<s:else>
													<tr>
														<td colspan="7" align="center">
															具体明细见明细附表
														</td>
													</tr>
												</s:else>
											</s:if>
											<s:if test='barContract.contract_source=="KVP"'>
												<s:if test="list.size <7">
													<s:iterator value="list" status="pageIndex">
														<tr>
															<td align="center">
																${pageIndex.index+1}
															</td>
															<td align="center">
																${executeNumber}
															</td>
															<td align="center">
																${improve_username}
															</td>
															<td align="center">
																${res_username}
															</td>
															<td align="center" colspan="3">
																${costsavings}
															</td>
														</tr>
													</s:iterator>
												</s:if>
												<s:else>
													<tr>
														<td colspan="7" align="center">
															具体明细见明细附表
														</td>
													</tr>
												</s:else>
											</s:if>


											<s:if test='barContract.contract_source=="设备"'>
												<s:if test="list.size <7">
													<s:iterator value="list" status="pageIndex">
														<tr>
															<td align="center">
																${pageIndex.index+1}
															</td>
															<td align="center">
																${machine_no}
															</td>
															<td align="center">
																${machine_name}
															</td>
															<td align="center">
																${machine_type}
															</td>
															<td align="center">
																${machine_classGroup}
															</td>
															<td align="center">
																${machine_buyamount}
															</td>
														</tr>
													</s:iterator>
												</s:if>
												<s:else>
													<tr>
														<td colspan="7" align="center">
															具体明细见明细附表
														</td>
													</tr>
												</s:else>
											</s:if>


											<s:if test='barContract.contract_source=="SB"'>
												<s:if test="list.size <7">
													<s:iterator value="list" id="hh" status="pageStatus">
														<tr>
															<td align="center">
																<s:property value="#hh.macrepair_classGroup" />
																${pageStatus.index+1}
															</td>
															<td align="center">
																${hh.macrepair_name}
															</td>
															<td align="center">
																${hh.macrepair_format}
															</td>
															<td align="center">
																${hh.macrepair_unit}
															</td>
															<td align="center">
																${hh.macrepair_amount}
															</td>
															<td align="center">
																${hh.macrepair_money}
															</td>
															<td align="center" colspan="3">
																${hh.macrepair_remark}
															</td>
														</tr>
													</s:iterator>
												</s:if>
												<s:else>
													<tr>
														<td colspan="7" align="center">
															具体明细见明细附表
														</td>
													</tr>
												</s:else>

											</s:if>
											<s:if test='barContract.contract_source=="XBYJ"'>
												<s:if test="list.size <7">
													<s:iterator value="list" id="hh" status="pageStatus">
														<tr>
															<td align="center">
																${pageStatus.index+1}
															</td>
															<td align="center">
																${hh.goods_name}
															</td>
															<td align="center">
																${hh.goods_format}
															</td>
															<td align="center">
																${hh.goods_amount}
															</td>
															<td align="center">
																${hh.goods_unit}
															</td>
															<td align="center">
																${hh.purchase_delivery}
															</td>
															<td align="center">
																${hh.money}
															</td>
															<td align="center">
																${hh.zongMoney}
															</td>
														</tr>
													</s:iterator>
												</s:if>
												<s:else>
													<tr>
														<td colspan="7" align="center">
															具体明细见明细附表
														</td>
													</tr>
												</s:else>
											</s:if>
											<s:if test='barContract.contract_source=="紧急采购"'>
												<s:if test="list.size <7">
													<s:iterator value="list" id="hh" status="pageStatus">
														<tr>
															<td align="center">
																${pageStatus.index+1}
															</td>
															<td align="center">
																${hh.qtName}
															</td>
															<td align="center">
																${hh.qtUnit}
															</td>
															<td align="center">
																${hh.qtNum}
															</td>
															<td align="center">
																${hh.qtMoney}
															</td>
															<td align="center" colspan="3">
																${hh.zongMoney}
															</td>
														</tr>
													</s:iterator>
												</s:if>
												<s:else>
													<tr>
														<td colspan="7" align="center">
															具体明细见明细附表
														</td>
													</tr>
												</s:else>
											</s:if>
											<s:if test='barContract.contract_source=="零部件及工序外委采购"'>
												<s:if test="list.size <7">
													<s:iterator value="list" id="hh" status="pageStatus">
														<tr>
															<td align="center">
																${pageStatus.index+1}
															</td>
															<td align="center">
																${hh.gx_name}
															</td>
															<td align="center">
																${hh.gx_number}
															</td>
															<td align="center">
																${hh.gx_price}
															</td>
															<td align="center">
																${hh.gx_type}
															</td>
															<td align="center">
																${hh.gx_status}
															</td>
															<td align="center">
																${hh.gx_producetype}
															</td>
															<td align="center">
																${hh.gx_goodstype}
															</td>
														</tr>
													</s:iterator>
												</s:if>
												<s:else>
													<tr>
														<td colspan="7" align="center">
															具体明细见明细附表
														</td>
													</tr>
												</s:else>
											</s:if>
											<s:if test='barContract.contract_source=="工装采购"'>
												<s:if test="list.size <7">
													<s:iterator value="list" id="hh" status="pageStatus">
														<tr>
															<td align="center">
																${pageStatus.index+1}
															</td>
															<td align="center">
																${hh.frock_biddingnum}
															</td>
															<td align="center">
																${hh.frock_name}
															</td>
															<td align="center">
																${hh.frock_partnum}
															</td>
															<td align="center">
																${hh.frock_num}
															</td>
															<td align="center">
																${hh.frock_amount}
															</td>
															<td align="center">
																${hh.frock_money}
															</td>
														</tr>
													</s:iterator>
												</s:if>
												<s:else>
													<tr>
														<td colspan="7" align="center">
															具体明细见明细附表
														</td>
													</tr>
												</s:else>
											</s:if>
											<s:if
												test='barContract.contract_source=="原材料采购"||barContract.contract_source=="包装物"'>
												<s:if test="list.size <7">
													<s:iterator value="list" id="hh" status="pageStatus">
														<tr>
															<td align="center">
																${pageStatus.index+1}
															</td>
															<td align="center">
																${hh.materials_name}
															</td>
															<td align="center">
																${hh.materials_format}
															</td>
															<td align="center">
																${hh.materials_unit}
															</td>
															<td align="center">
																${hh.materials_price}
															</td>
														</tr>
													</s:iterator>
												</s:if>
												<s:else>
													<tr>
														<td colspan="7" align="center">
															具体明细见明细附表
														</td>
													</tr>
												</s:else>
											</s:if>
											<tr>
												<s:if test='barContract.contract_source=="OA"'>
													<th colspan="7" align="center">
														合计(含税价)
													</th>
													<th align="center">
														${barContract.heji}元
													</th>
												</s:if>
												<s:if test='barContract.contract_source=="SB"'>
													<th colspan="7" align="center">
														合计(含税价)
													</th>
													<th align="center">
														${barContract.heji}元
													</th>
												</s:if>
												<s:if test='barContract.contract_source=="XBYJ"'>
													<th colspan="7" align="center">
														合计(含税价)
													</th>
													<th align="center">
														${barContract.heji}元
													</th>
												</s:if>
												<s:if test='barContract.contract_source=="紧急采购"'>
													<th colspan="5" align="center">
														合计(含税价)
													</th>
													<th align="center">
														${barContract.heji}元
													</th>
												</s:if>
												<s:if test='barContract.contract_source=="工装采购"'>
													<th colspan="6" align="center">
														合计(含税价)
													</th>
													<th align="center">
														${barContract.heji}元
													</th>
												</s:if>
												<s:if test='barContract.contract_source=="KVP"'>
													<th colspan="6" align="center">
														合计(含税价)
													</th>
													<th align="center">
														${barContract.heji}元
													</th>
												</s:if>
											</tr>
											<s:if test='barContract.contract_source=="OA"'>
												<tr>
													<td rowspan="2" align="center">
														合同期限
													</td>
													<td align="center">
														起
													</td>
													<td align="center">
														止
													</td>
													<td rowspan="2" colspan="5" align="left">
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;合同依据:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${barContract.contract_according}
													</td>
												</tr>
												<tr>
													<td align="center">
														${barContract.startDate}
													</td>
													<td align="center">
														${barContract.endDate}
													</td>
												</tr>
											</s:if>
											<s:if test='barContract.contract_source=="设备"'>
												<tr>
													<td rowspan="2" align="center">
														合同期限
													</td>
													<td align="center">
														起
													</td>
													<td align="center">
														止
													</td>
													<td rowspan="2" colspan="5" align="left">
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;合同依据:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${barContract.contract_according}
													</td>
												</tr>
												<tr>
													<td align="center">
														${barContract.startDate}
													</td>
													<td align="center">
														${barContract.endDate}
													</td>
												</tr>
											</s:if>
											<s:if test='barContract.contract_source=="KVP"'>
												<tr>
													<td rowspan="2" align="center">
														合同期限
													</td>
													<td align="center">
														起
													</td>
													<td align="center">
														止
													</td>
													<td rowspan="2" colspan="5" align="left">
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;合同依据:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${barContract.contract_according}
													</td>
												</tr>
												<tr>
													<td align="center">
														${barContract.startDate}
													</td>
													<td align="center">
														${barContract.endDate}
													</td>
												</tr>
											</s:if>
											<s:if test='barContract.contract_source=="XBYJ"'>
												<tr>
													<td rowspan="2" align="center">
														合同期限
													</td>
													<td align="center">
														起
													</td>
													<td align="center">
														止
													</td>
													<td rowspan="2" colspan="5" align="left">
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;合同依据:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${barContract.contract_according}
													</td>
												</tr>
												<tr>
													<td align="center">
														${barContract.startDate}
													</td>
													<td align="center">
														${barContract.endDate}
													</td>
												</tr>
											</s:if>
											<s:if test='barContract.contract_source=="SB"'>
												<tr>
													<td rowspan="2" align="center">
														合同期限
													</td>
													<td align="center">
														起
													</td>
													<td align="center">
														止
													</td>
													<td rowspan="2" colspan="5" align="left">
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;合同依据:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${barContract.contract_according}
													</td>
												</tr>
												<tr>
													<td align="center">
														${barContract.startDate}
													</td>
													<td align="center">
														${barContract.endDate}
													</td>
												</tr>
											</s:if>
											<s:if
												test='barContract.contract_source=="原材料采购"||barContract.contract_source=="零部件及工序外委采购"||barContract.contract_source=="包装物"'>
												<tr>
													<td rowspan="2" align="center">
														合同期限
													</td>
													<td align="center">
														起
													</td>
													<td align="center">
														止
													</td>
													<td rowspan="2" colspan="5" align="left">
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;合同依据:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${barContract.contract_according}
													</td>
												</tr>
												<tr>
													<td align="center">
														${barContract.startDate}
													</td>
													<td align="center">
														${barContract.endDate}
													</td>
												</tr>
											</s:if>
											<s:if test='barContract.contract_source=="紧急采购"'>
												<tr>
													<td colspan="7">
														备注：
													</td>
												</tr>
												<tr>
													<td colspan="7">
														1.款到后安排发配件。
													</td>
												</tr>
												<tr>
													<td colspan="7">
														2.本合同含17%税，次月开具发票。
													</td>
												</tr>
												<tr>
													<td colspan="7">
														3.本合同一式两份，甲乙双方各执一份，签字、盖章有效。
													</td>
												</tr>
												<tr>
													<td align="center" colspan="7">
														&nbsp;&nbsp;
													</td>
												</tr>
											</s:if>
											<s:if test='barContract.contract_source=="零部件及工序外委采购"'>
												<tr>
													<td colspan="8">
														二、付款：
														<s:if test='barContract.paymentDate != "0"'>
														甲方在货物验收合格，收到乙方正确发票后${barContract.paymentDate}天内电汇。</s:if>
														<s:else>乙方收到甲方货款后发货</s:else>
													</td>
												</tr>
												<tr>
													<td colspan="8">
														三、质量要求及标准：
													</td>
												</tr>
												<tr>
													<td colspan="8">
														1、按技术协议、质量协议、供货承诺要求执行。
													</td>
												</tr>
												<tr>
													<td colspan="8">
														2、甲方在生产过程中，发现因乙方原因造成零件的报废或返修，由此发生的各项费用由乙方承担。
													</td>
												</tr>
												<tr>
													<td colspan="8">
														四、包装、标识，运输及交货：
													</td>
												</tr>
												<tr>
													<td colspan="8">
														1、交货地点：${barContract.deliveryPlace}
													</td>
												</tr>
												<tr>
													<td colspan="8">
														2、运输方式及费用负担：${barContract.transpor_tway}
													</td>
												</tr>
												<tr>
													<td colspan="8">
														3、包装要求：${barContract.pack_ask}
													</td>
												</tr>
												<tr>
													<td colspan="8">
														五、解决合同纠纷：友好协商、违约责任：按《经济合同法》。
													</td>
												</tr>
												<tr>
													<td colspan="8">
														六、本合同一式两份，甲乙双方各执一份，签字、盖章有效。
													</td>
												</tr>
												<tr>
													<td align="center" colspan="8">
														&nbsp;&nbsp;
													</td>
												</tr>
											</s:if>
											<s:if
												test='barContract.contract_source=="原材料采购"||barContract.contract_source=="包装物"'>
												<tr>
													<td colspan="7">
														二、付款：甲方在货物验收合格，收到乙方正确发票后90天内电汇或银行承兑票形式交付。
													</td>
												</tr>
												<tr>
													<td colspan="7">
														三、质量要求及标准：
													</td>
												</tr>
												<tr>
													<td colspan="7">
														1、按技术协议、质量协议、供货承诺要求执行。
													</td>
												</tr>
												<tr>
													<td colspan="7">
														2、甲方在生产过程中，发现因乙方原因造成零件的报废或返修，由此发生的各项费用由乙方承担。
													</td>
												</tr>
												<tr>
													<td colspan="7">
														四、包装、标识，运输及交货：
													</td>
												</tr>
												<tr>
													<td colspan="7">
														1、交货地点：${barContract.deliveryPlace}（${companyInfo.name}）
													</td>
												</tr>
												<tr>
													<td colspan="7">
														2、运输方式及费用负担：${barContract.transpor_tway}
													</td>
												</tr>
												<tr>
													<td colspan="7">
														3、包装要求：${barContract.pack_ask}
													</td>
												</tr>
												<tr>
													<td colspan="7">
														五、解决合同纠纷：友好协商、违约责任：按《经济合同法》。
													</td>
												</tr>
												<tr>
													<td colspan="7">
														六、本合同一式两份，甲乙双方各执一份，签字、盖章有效。
													</td>
												</tr>
												<tr>
													<td align="center" colspan="7">
														&nbsp;&nbsp;
													</td>
												</tr>
											</s:if>
											<s:if
												test='barContract.contract_source=="工装采购"||barContract.contract_source=="XBYJ"||barContract.contract_source=="OA"'>
												<tr>
													<td colspan="8">
														二.工装技术制造要求及标准：
													</td>
												</tr>
												<tr>
													<td colspan="8">
														1.乙方按照甲方提供的产品图纸及技术要求，经甲、乙双方确定合理的设计方案而制造。
													</td>
												</tr>
												<tr>
													<td colspan="8">
														2.所使用的材料由乙方负责采购，工装材料必须达到该工装的寿命要求。使用寿命在20万次以上。
													</td>
												</tr>
												<tr>
													<td colspan="8">
														3.外观要求模具统一涂蓝色油漆，在模具的上模座前方用钢字码打印模具型号、制造日期、冲床吨位。夹
														具要求部件经过发黑处理，统一在夹具台面右下角用钢字码打印夹具型号、制造日期。
													</td>
												</tr>
												<tr>
													<td colspan="8">
														三.工装验收：交货日期（${barContract.deliverydate}）
													</td>
												</tr>
												<tr>
													<td colspan="8">
														1.按照甲方提供的产品数模图纸及技术协议要求或样件进行验收。
													</td>
												</tr>
												<tr>
													<td colspan="8">
														2.调试工装所使用的材料由乙方负责，甲方只提供正品材料每道工序10件，乙方必须提供合格件5件以上。
													</td>
												</tr>
												<tr>
													<td colspan="8">
														3.在甲方现场验收时，工装调试均由乙方负责，对工装是否合格按产品数模及图纸技术要求进行验收，同
														时在批量生产过程中报废率不能超过3% 。超出3% 报废零件由乙方承担废品损失。
													</td>
												</tr>
												<tr>
													<td colspan="8">
														四.包装及配送：
													</td>
												</tr>
												<tr>
													<td colspan="8">
														${barContract.pack_ask}
													</td>
												</tr>
												<tr>
													<td colspan="8">
														五.售后服务：
													</td>
												</tr>
												<tr>
													<td colspan="8">
														1.乙方交付工装使用之日起六个月为质量保证期，如出现损坏乙方质量原因则乙方负责免费更换
													</td>
												</tr>
												<tr>
													<td colspan="8">
														2.保修期内乙方接到甲方维修及质量问题通知后，在一个小时内给予答复，如不能用传真及电话解决的，
														乙方在24小时内派人到甲方现场解决。
													</td>
												</tr>
												<tr>
													<td colspan="8">
														六.付款方式分期付款：
													</td>
												</tr>
												<tr>
													<td colspan="8">
														一.${barContract.paymentway1}
													</td>
												</tr>
												<tr>
													<td colspan="8">
														二.${barContract.paymentway2}
													</td>
												</tr>
												<tr>
													<td colspan="8">
														三.${barContract.paymentway3}
													</td>
												</tr>
												<tr>
													<td colspan="8">
														七.违约责任：
													</td>
												</tr>
												<tr>
													<td colspan="8">
														1.甲方提供给乙方的技术图纸及样件、工艺结构等技术资料均属甲方产权，乙方对此负有保密责任，
														如因乙方对技术资料疏忽或故意外泄造成甲方产权损失,
													</td>
												</tr>
												<tr>
													<td colspan="8">
														&nbsp;&nbsp;&nbsp;经查实甲方有权进行追究，乙方必须承担因此造成的损失及赔偿责任。
													</td>
												</tr>
												<tr>
													<td colspan="8">
														2.经甲方同意适当顺延交期确认外，或除了国家发令天灾不可抗拒因素，乙方未按预订日期完成情况时，
														应以每日赔偿本合同总金额 2 %工装费给甲方作为逾期违约损失赔偿。
													</td>
												</tr>
												<tr>
													<td colspan="8">
														&nbsp;&nbsp;&nbsp;超过10日未能完成的，甲方有权解除合同和追究乙方的责任并要求乙方赔偿相应的损失。
													</td>
												</tr>
												<tr>
													<td colspan="8">
														3.若乙方制造的工装结构用料、品质及寿命不合理，功能未达到甲方规定的原技术设计要求时，
														乙方负责工装修改或重开取代原有工装，
													</td>
												</tr>
												<tr>
													<td colspan="8">
														&nbsp;&nbsp;&nbsp;乙方应自行承担重开工装费用及该工装拖延时间的赔偿损失责任。
													</td>
												</tr>
												<tr>
													<td colspan="8">
														八.解决合同纠纷方式：
													</td>
												</tr>
												<tr>
													<td colspan="8">
														遇合同中未协签事项，双方友好协商解决，协商不成，任何一方均可向合同签订地所在人民法院提出诉讼
													</td>
												</tr>
											</s:if>
											<%--<s:if test='barContract.contract_source!="OA"'>
												<tr>
													<td colspan="8">
														1、运输方式及费用负担：${barContract.transpor_tway}
													</td>
												</tr>
												<tr>
													<td colspan="8">
														2、包装要求：${barContract.pack_ask}
													</td>
												</tr>
												<tr>
													<td colspan="8">
														3、合理损耗标准及计算方法：以实际来料净重计算确定（磅重）。
													</td>
												</tr>
												<tr>
													<td colspan="8">
														4、甲方在生产过程中，发现因乙方原因（如材质或焊接问题等）造成零件的报废或返修，由此发生的各项费用由乙方承担。
													</td>
												</tr>
												<tr>
													<td colspan="8">
														5、违约责任：按《经济合同法》。
													</td>
												</tr>
												<tr>
													<td colspan="8">
														6、本合同一式两份，甲乙双方各执一份。
													</td>
												</tr>
												<tr>
													<td align="center" colspan="8">
														&nbsp;&nbsp;
													</td>
												</tr>
											</s:if>
											--%>
											<s:if test='barContract.contract_source=="XBYJ2"'>
												<tr>
													<td colspan="8">
														1、运输方式及费用负担：${barContract.transpor_tway}
													</td>
												</tr>
												<tr>
													<td colspan="8">
														2、包装要求：${barContract.pack_ask}
													</td>
												</tr>
												<tr>
													<td colspan="8">
														3、合理损耗标准及计算方法：以实际来料净重计算确定（磅重）。
													</td>
												</tr>
												<tr>
													<td colspan="8">
														4、甲方在生产过程中，发现因乙方原因（如材质或焊接问题等）造成零件的报废或返修，由此发生的各项费用由乙方承担。
													</td>
												</tr>
												<tr>
													<td colspan="8">
														5、违约责任：按《经济合同法》。
													</td>
												</tr>
												<tr>
													<td colspan="8">
														6、本合同一式两份，甲乙双方各执一份。
													</td>
												</tr>
												<tr>
													<td align="center" colspan="8">
														&nbsp;&nbsp;
													</td>
												</tr>
											</s:if>
											<s:if
												test='barContract.contract_source=="SB"||barContract.contract_source=="设备"||barContract.contract_source=="KVP"'>
												<tr>
													<td colspan="8">
														1、运输方式及费用负担：：${barContract.transpor_tway}
													</td>
												</tr>
												<tr>
													<td colspan="8">
														2、包装要求：${barContract.pack_ask}
													</td>
												</tr>
												<tr>
													<td colspan="8">
														3、合理损耗标准及计算方法：以实际来料净重计算确定（磅重）。
													</td>
												</tr>
												<tr>
													<td colspan="8">
														4、甲方在生产过程中，发现因乙方原因（如材质或焊接问题等）造成零件的报废或返修，由此发生的各项费用由乙方承担。
													</td>
												</tr>
												<tr>
													<td colspan="8">
														5、违约责任：按《经济合同法》。
													</td>
												</tr>
												<tr>
													<td colspan="8">
														6、本合同一式两份，甲乙双方各执一份。
													</td>
												</tr>
												<tr>
													<td align="center" colspan="8">
														&nbsp;&nbsp;
													</td>
												</tr>
											</s:if>
											<tr>
												<s:if test='barContract.contract_source=="OA"'>
													<td colspan="8">
														<table class="table2" style="width: 50%; float: left;">
															<tr height="30px">
																<td colspan="3" align="center">
																	甲方
																</td>
															</tr>
															<tr height="30px">
																<td>
																	单位名称：
																</td>
																<td>
																	${companyInfo.name}
																</td>
															</tr>
															<tr height="30px">
																<td>
																	单位地址：
																</td>
																<td>
																	${companyInfo.address}
																</td>
															</tr>
															<tr height="30px">
																<td>
																	法定代表人：
																</td>
																<td>
																	<div style="height: 45px;">
																		<span id="aaa"> </span>
																		<br />
																	</div>
																	______________________
																</td>
															</tr>
															<tr height="30px">
																<td>
																	委托代理人：
																</td>
																<td>

																	______________________
																</td>
															</tr>
															<tr height="30px">
																<td>
																	电 话：
																</td>
																<td>
																	<input readonly="readonly" class="horizontalLine"
																		value="${companyInfo.tel}" style="width: 175px">
																</td>
															</tr>
															<tr height="30px">
																<td>
																	开户银行：
																</td>
																<td>
																	<input readonly="readonly" align="bottom"
																		class="horizontalLine"
																		value="${companyInfo.accountBank}"
																		style="width: 175px">
																</td>
															</tr>
															<tr height="30px">
																<td>
																	帐 号：
																</td>
																<td>
																	<input readonly="readonly" class="horizontalLine"
																		value="${companyInfo.accountnum}" style="width: 175px">
																</td>
															</tr>
															<tr height="30px">
																<td>
																	邮政编码：
																</td>
																<td>
																	<input readonly="readonly" class="horizontalLine"
																		value="${companyInfo.zip}" style="width: 175px">
																</td>
														</table>
														<table class="table2" style="width: 50%; float: left;">
															<tr height="30px">
																<td colspan="4" align="center">
																	乙 方
																</td>
															</tr>
															<tr height="30px">
																<td>
																	单位名称：
																</td>
																<td>
																	<input readonly="readonly" class="horizontalLine"
																		name="barContract.unit_name"
																		value="${barContract.unit_name}" style="width: 175px">
																</td>
															</tr>
															<tr height="30px">
																<td>
																	单位地址：
																</td>
																<td>
																	<input readonly="readonly" class="horizontalLine"
																		name="barContract.address"
																		value="${barContract.address}" style="width: 175px">
																</td>
															</tr>
															<tr height="30px">
																<td>
																	法定代表人：
																</td>
																<td>
																	<div>
																		<div style="height: 34px;"></div>
																		<br />
																		______________________
																	</div>
																</td>
															</tr>
															<tr height="30px">
																<td>
																	委托代理人：
																</td>
																<td>
																	______________________
																</td>
															</tr>
															<tr height="30px">
																<td>
																	电 话：
																</td>
																<td>
																	<input readonly="readonly" class="horizontalLine"
																		name="barContract.tel" value="${barContract.tel}"
																		style="width: 175px">
																</td>
															</tr>
															<tr height="30px">
																<td>
																	开户银行：
																</td>
																<td>
																	<input readonly="readonly" class="horizontalLine"
																		name="barContract.accountBank"
																		value="${barContract.accountBank}"
																		style="width: 175px">
																</td>
															</tr>
															<tr height="30px">
																<td>
																	帐 号：
																</td>
																<td>
																	<input readonly="readonly" class="horizontalLine"
																		name="barContract.accountnum"
																		value="${barContract.accountnum}" style="width: 175px">
																</td>
															</tr>
															<tr height="30px">
																<td>
																	邮政编码：
																</td>
																<td>
																	<input readonly="readonly" class="horizontalLine"
																		name="barContract.zip" value="${barContract.zip}"
																		style="width: 175px">
																</td>
															</tr>
														</table>
													</td>
												</s:if>
												<s:if test='barContract.contract_source=="XBYJ"'>
													<td colspan="4">
														<table class="table2">
															<tr height="30px">
																<td colspan="3" align="center">
																	甲方
																</td>
															</tr>
															<tr height="30px">
																<td>
																	单位名称：
																</td>
																<td>
																	${companyInfo.name}
																</td>
															</tr>
															<tr height="30px">
																<td>
																	单位地址：
																</td>
																<td>
																	${companyInfo.address}
																</td>
															</tr>
															<tr height="30px">
																<td>
																	法定代表人：
																</td>
																<td>
																	<div>
																		<span id="aaa"></span>
																		<br />
																		______________________
																	</div>
																</td>
															</tr>
															<tr height="30px">
																<td>
																	委托代理人：
																</td>
																<td>

																	______________________
																</td>
															</tr>
															<tr height="30px">
																<td>
																	电 话：
																</td>
																<td>
																	<input readonly="readonly" class="horizontalLine"
																		value="${companyInfo.tel}" style="width: 175px">
																</td>
															</tr>
															<tr height="30px">
																<td>
																	开户银行：
																</td>
																<td>
																	<input readonly="readonly" align="bottom"
																		class="horizontalLine"
																		value="${companyInfo.accountBank}"
																		style="width: 175px">
																</td>
															</tr>
															<tr height="30px">
																<td>
																	帐 号：
																</td>
																<td>
																	<input readonly="readonly" class="horizontalLine"
																		value="${companyInfo.accountnum}" style="width: 175px">
																</td>
															</tr>
															<tr height="30px">
																<td>
																	邮政编码：
																</td>
																<td>
																	<input readonly="readonly" class="horizontalLine"
																		value="${companyInfo.zip}" style="width: 175px">
																</td>
														</table>
													</td>
													<td colspan="4">
														<table class="table2">
															<tr height="30px">
																<td colspan="4" align="center">
																	乙 方
																</td>
															</tr>
															<tr height="30px">
																<td>
																	单位名称：
																</td>
																<td>
																	<input readonly="readonly" class="horizontalLine"
																		name="barContract.unit_name"
																		value="${barContract.unit_name}" style="width: 175px">
																</td>
															</tr>
															<tr height="30px">
																<td>
																	单位地址：
																</td>
																<td>
																	<input readonly="readonly" class="horizontalLine"
																		name="barContract.address"
																		value="${barContract.address}" style="width: 175px">
																</td>
															</tr>
															<tr height="30px">
																<td>
																	法定代表人：
																</td>
																<td>
																	<div>
																		<div style="height: 34px;"></div>
																		<br />
																		______________________
																	</div>
																</td>
															</tr>
															<tr height="30px">
																<td>
																	委托代理人：
																</td>
																<td>
																	______________________
																</td>
															</tr>
															<tr height="30px">
																<td>
																	电 话：
																</td>
																<td>
																	<input readonly="readonly" class="horizontalLine"
																		name="barContract.tel" value="${barContract.tel}"
																		style="width: 175px">
																</td>
															</tr>
															<tr height="30px">
																<td>
																	开户银行：
																</td>
																<td>
																	<input readonly="readonly" class="horizontalLine"
																		name="barContract.accountBank"
																		value="${barContract.accountBank}"
																		style="width: 175px">
																</td>
															</tr>
															<tr height="30px">
																<td>
																	帐 号：
																</td>
																<td>
																	<input readonly="readonly" class="horizontalLine"
																		name="barContract.accountnum"
																		value="${barContract.accountnum}" style="width: 175px">
																</td>
															</tr>
															<tr height="30px">
																<td>
																	邮政编码：
																</td>
																<td>
																	<input readonly="readonly" class="horizontalLine"
																		name="barContract.zip" value="${barContract.zip}"
																		style="width: 175px">
																</td>
															</tr>
														</table>
													</td>
												</s:if>
												<s:if
													test='barContract.contract_source=="SB"||barContract.contract_source=="设备"||barContract.contract_source=="KVP"'>
													<td colspan="4">
														<table class="table2">
															<tr height="30px">
																<td colspan="3" align="center">
																	甲方
																</td>
															</tr>
															<tr height="30px">
																<td>
																	单位名称：
																</td>
																<td>
																	${companyInfo.name}
																</td>
															</tr>
															<tr height="30px">
																<td>
																	单位地址：
																</td>
																<td>
																	${companyInfo.address}
																</td>
															</tr>
															<tr height="30px">
																<td>
																	法定代表人：
																</td>
																<td>
																	<div>
																		<span id="aaa"></span>
																		<br />
																		______________________
																	</div>
																</td>
															</tr>
															<tr height="30px">
																<td>
																	委托代理人：
																</td>
																<td>

																	______________________
																</td>
															</tr>
															<tr height="30px">
																<td>
																	电 话：
																</td>
																<td>
																	<input readonly="readonly" class="horizontalLine"
																		value="${companyInfo.tel}" style="width: 175px">
																</td>
															</tr>
															<tr height="30px">
																<td>
																	开户银行：
																</td>
																<td>
																	<input readonly="readonly" align="bottom"
																		class="horizontalLine"
																		value="${companyInfo.accountBank}"
																		style="width: 175px">
																</td>
															</tr>
															<tr height="30px">
																<td>
																	帐 号：
																</td>
																<td>
																	<input readonly="readonly" class="horizontalLine"
																		value="${companyInfo.accountnum}" style="width: 175px">
																</td>
															</tr>
															<tr height="30px">
																<td>
																	邮政编码：
																</td>
																<td>
																	<input readonly="readonly" class="horizontalLine"
																		value="${companyInfo.zip}" style="width: 175px">
																</td>
														</table>
													</td>
													<td colspan="4">
														<table class="table2">
															<tr height="30px">
																<td colspan="4" align="center">
																	乙 方
																</td>
															</tr>
															<tr height="30px">
																<td>
																	单位名称：
																</td>
																<td>
																	<input readonly="readonly" class="horizontalLine"
																		name="barContract.unit_name"
																		value="${barContract.unit_name}" style="width: 175px">
																</td>
															</tr>
															<tr height="30px">
																<td>
																	单位地址：
																</td>
																<td>
																	<input readonly="readonly" class="horizontalLine"
																		name="barContract.address"
																		value="${barContract.address}" style="width: 175px">
																</td>
															</tr>
															<tr height="30px">
																<td>
																	法定代表人：
																</td>
																<td>
																	<div>
																		<div style="height: 34px;"></div>
																		<br />
																		______________________
																	</div>
																</td>
															</tr>
															<tr height="30px">
																<td>
																	委托代理人：
																</td>
																<td>
																	______________________
																</td>
															</tr>
															<tr height="30px">
																<td>
																	电 话：
																</td>
																<td>
																	<input readonly="readonly" class="horizontalLine"
																		name="barContract.tel" value="${barContract.tel}"
																		style="width: 175px">
																</td>
															</tr>
															<tr height="30px">
																<td>
																	开户银行：
																</td>
																<td>
																	<input readonly="readonly" class="horizontalLine"
																		name="barContract.accountBank"
																		value="${barContract.accountBank}"
																		style="width: 175px">
																</td>
															</tr>
															<tr height="30px">
																<td>
																	帐 号：
																</td>
																<td>
																	<input readonly="readonly" class="horizontalLine"
																		name="barContract.accountnum"
																		value="${barContract.accountnum}" style="width: 175px">
																</td>
															</tr>
															<tr height="30px">
																<td>
																	邮政编码：
																</td>
																<td>
																	<input readonly="readonly" class="horizontalLine"
																		name="barContract.zip" value="${barContract.zip}"
																		style="width: 175px">
																</td>
															</tr>
														</table>
													</td>
												</s:if>
												<s:if test='barContract.contract_source=="零部件及工序外委采购"'>
													<td colspan="3" height="270px">
														<div>
															<table class="table2"
																style="height: 100%; margin-top: 0px; padding-top: 0px">
																<tr height="30px">
																	<td colspan="3" align="center">
																		甲方
																	</td>
																</tr>
																<tr height="30px">
																	<td>
																		单位名称：
																	</td>
																	<td>
																		${companyInfo.name}
																	</td>
																</tr>
																<tr height="30px">
																	<td>
																		单位地址：
																	</td>
																	<td>
																		${companyInfo.address}
																	</td>
																</tr>
																<tr height="30%">
																	<td>
																		法定代表人：
																	</td>
																	<td height="100%">
																		<div style="height: 45px;">
																			<span id="aaa"></span>
																			<br />
																		</div>
																		______________________
																	</td>
																</tr>
																<tr style="max-height: 30px">
																	<td>
																		委托代理人：
																	</td>
																	<td>

																		______________________
																	</td>
																</tr>
																<tr height="30px">
																	<td>
																		电 话：
																	</td>
																	<td>
																		<input readonly="readonly" class="horizontalLine"
																			value="${companyInfo.tel}" style="width: 175px">
																	</td>
																</tr>
																<tr height="30px">
																	<td>
																		开户银行：
																	</td>
																	<td>
																		<input readonly="readonly" align="bottom"
																			class="horizontalLine"
																			value="${companyInfo.accountBank}"
																			style="width: 175px">
																	</td>
																</tr>
																<tr height="30px">
																	<td>
																		帐 号：
																	</td>
																	<td>
																		<input readonly="readonly" class="horizontalLine"
																			value="${companyInfo.accountnum}"
																			style="width: 175px">
																	</td>
																</tr>
																<tr height="30px">
																	<td>
																		邮政编码：
																	</td>
																	<td>
																		<input readonly="readonly" class="horizontalLine"
																			value="${companyInfo.zip}" style="width: 175px">
																	</td>
															</table>
														</div>
													</td>
													<td colspan="5" height="270px">
														<table class="table2"
															style="height: 100%; margin-top: 0px; padding-top: 0px">
															<tr height="30px">
																<td colspan="5" align="center">
																	乙 方
																</td>
															</tr>
															<tr height="30px">
																<td>
																	单位名称：
																</td>
																<td>
																	<input readonly="readonly" class="horizontalLine"
																		name="barContract.unit_name"
																		value="${barContract.unit_name}" style="width: 190px">
																</td>
															</tr>
															<tr height="30px">
																<td>
																	单位地址：
																</td>
																<td>
																	<input readonly="readonly" class="horizontalLine"
																		name="barContract.address"
																		value="${barContract.address}" style="width: 190px">
																</td>
															</tr>
															<tr height="30%">
																<td>
																	法定代表人：
																</td>
																<td height="100%">
																	<div style="height: 45px">
																		<span></span>
																		<br />
																	</div>
																	________________________
																</td>
															</tr>
															<tr style="max-height: 30px;">
																<td>
																	委托代理人：
																</td>
																<td>
																	________________________
																</td>
															</tr>
															<tr height="30px">
																<td>
																	电 话：
																</td>
																<td>
																	<input readonly="readonly" class="horizontalLine"
																		name="barContract.tel" value="${barContract.tel}"
																		style="width: 190px">
																</td>
															</tr>
															<tr height="30px">
																<td>
																	开户银行：
																</td>
																<td>
																	<input readonly="readonly" class="horizontalLine"
																		name="barContract.accountBank"
																		value="${barContract.accountBank}"
																		style="width: 190px">
																</td>
															</tr>
															<tr height="30px">
																<td>
																	帐 号：
																</td>
																<td>
																	<input readonly="readonly" class="horizontalLine"
																		name="barContract.accountnum"
																		value="${barContract.accountnum}" style="width: 190px">
																</td>
															</tr>
															<tr height="30px">
																<td>
																	邮政编码：
																</td>
																<td>
																	<input readonly="readonly" class="horizontalLine"
																		name="barContract.zip" value="${barContract.zip}"
																		style="width: 190px">
																</td>
															</tr>
														</table>
													</td>
												</s:if>
												<s:elseif
													test='barContract.contract_source=="紧急采购"||barContract.contract_source=="原材料采购"||barContract.contract_source=="包装物"||barContract.contract_source=="零部件及工序外委采购"'>
													<td colspan="3" height="270px">
														<div>
															<table class="table2"
																style="height: 100%; margin-top: 0px; padding-top: 0px">
																<tr height="30px">
																	<td colspan="3" align="center">
																		甲方
																	</td>
																</tr>
																<tr height="30px">
																	<td>
																		单位名称：
																	</td>
																	<td>
																		${companyInfo.name}
																	</td>
																</tr>
																<tr height="30px">
																	<td>
																		单位地址：
																	</td>
																	<td>
																		${companyInfo.address}
																	</td>
																</tr>
																<tr height="30%">
																	<td>
																		法定代表人：
																	</td>
																	<td height="100%">
																		<div style="height: 45px;">
																			<span id="aaa"> </span>
																			<br />
																		</div>
																		______________________
																	</td>
																</tr>
																<tr style="max-height: 30px">
																	<td>
																		委托代理人：
																	</td>
																	<td>

																		______________________
																	</td>
																</tr>
																<tr height="30px">
																	<td>
																		电 话：
																	</td>
																	<td>
																		<input readonly="readonly" class="horizontalLine"
																			value="${companyInfo.tel}" style="width: 175px">
																	</td>
																</tr>
																<tr height="30px">
																	<td>
																		开户银行：
																	</td>
																	<td>
																		<input readonly="readonly" align="bottom"
																			class="horizontalLine"
																			value="${companyInfo.accountBank}"
																			style="width: 175px">
																	</td>
																</tr>
																<tr height="30px">
																	<td>
																		帐 号：
																	</td>
																	<td>
																		<input readonly="readonly" class="horizontalLine"
																			value="${companyInfo.accountnum}"
																			style="width: 175px">
																	</td>
																</tr>
																<tr height="30px">
																	<td>
																		邮政编码：
																	</td>
																	<td>
																		<input readonly="readonly" class="horizontalLine"
																			value="${companyInfo.zip}" style="width: 175px">
																	</td>
															</table>
														</div>
													</td>
													<td colspan="4" height="270px">
														<table class="table2"
															style="height: 100%; margin-top: 0px; padding-top: 0px">
															<tr height="30px">
																<td colspan="4" align="center">
																	乙 方
																</td>
															</tr>
															<tr height="30px">
																<td>
																	单位名称：
																</td>
																<td>
																	<input readonly="readonly" class="horizontalLine"
																		name="barContract.unit_name"
																		value="${barContract.unit_name}" style="width: 190px">
																</td>
															</tr>
															<tr height="30px">
																<td>
																	单位地址：
																</td>
																<td>
																	<input readonly="readonly" class="horizontalLine"
																		name="barContract.address"
																		value="${barContract.address}" style="width: 190px">
																</td>
															</tr>
															<tr height="30%">
																<td>
																	法定代表人：
																</td>
																<td height="100%">
																	<div style="height: 45px">
																		<span></span>
																		<br />
																	</div>
																	________________________
																</td>
															</tr>
															<tr style="max-height: 30px;">
																<td>
																	委托代理人：
																</td>
																<td>
																	________________________
																</td>
															</tr>
															<tr height="30px">
																<td>
																	电 话：
																</td>
																<td>
																	<input readonly="readonly" class="horizontalLine"
																		name="barContract.tel" value="${barContract.tel}"
																		style="width: 190px">
																</td>
															</tr>
															<tr height="30px">
																<td>
																	开户银行：
																</td>
																<td>
																	<input readonly="readonly" class="horizontalLine"
																		name="barContract.accountBank"
																		value="${barContract.accountBank}"
																		style="width: 190px">
																</td>
															</tr>
															<tr height="30px">
																<td>
																	帐 号：
																</td>
																<td>
																	<input readonly="readonly" class="horizontalLine"
																		name="barContract.accountnum"
																		value="${barContract.accountnum}" style="width: 190px">
																</td>
															</tr>
															<tr height="30px">
																<td>
																	邮政编码：
																</td>
																<td>
																	<input readonly="readonly" class="horizontalLine"
																		name="barContract.zip" value="${barContract.zip}"
																		style="width: 190px">
																</td>
															</tr>
														</table>
													</td>
												</s:elseif>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</div>
				</form>
				<input type="button" id="print" value="打印" class="input"
					onclick="print1()" />
				<s:if test="list.size>6">
					<div id="printDiv1" class="my_show" align="center">
						<table width="100%" border="1px"
							style="size: 18px; line-height: 22px;" align="center"
							class="table">
							<tr>

								<s:if test='barContract.contract_source=="工装采购"'>
									<td colspan="8" align="center">
										<font size="5px">明细附表</font>
									</td>
								</s:if>
								<s:else>
									<td colspan="8" align="center">
										<font size="5px">明细附表</font>
									</td>
								</s:else>
							</tr>
							<s:if test='barContract.contract_source=="紧急采购"'>
								<tr>
									<td align="center">
										序号
									</td>
									<td align="center">
										品名
									</td>
									<td align="center">
										单位
									</td>
									<td align="center">
										数量
									</td>
									<td align="center">
										单价
									</td>
									<td align="center" colspan="2">
										金额
									</td>
								</tr>
								<s:iterator value="list" id="hh" status="pageStatus">
									<tr>
										<td align="center">
											${pageStatus.index+1}
										</td>
										<td align="center">
											${hh.qtName}
										</td>
										<td align="center">
											${hh.qtUnit}
										</td>
										<td align="center">
											${hh.qtNum}
										</td>
										<td align="center">
											${hh.qtMoney}
										</td>
										<td align="center" colspan="3">
											${hh.zongMoney}
										</td>
									</tr>
								</s:iterator>
							</s:if>
							<s:if test='barContract.contract_source=="设备"'>
								<tr>
									<td align="center">
										序号
									</td>
									<td align="center">
										设备编号
									</td>
									<td align="center">
										设备名称
									</td>
									<td align="center">
										设备类型
									</td>
									<td align="center">
										部门
									</td>
									<td align="center">
										购买金额
									</td>
								</tr>
								<s:iterator value="list" status="pageIndex">
									<tr>
										<td align="center">
											${pageIndex.index+1}
										</td>
										<td align="center">
											${machine_no}
										</td>
										<td align="center">
											${machine_name}
										</td>
										<td align="center">
											${machine_type}
										</td>
										<td align="center">
											${machine_classGroup}
										</td>
										<td align="center">
											${machine_buyamount}
										</td>
									</tr>
								</s:iterator>
							</s:if>

							<s:if test='barContract.contract_source=="零部件及工序外委采购"'>
								<tr>
									<td align="center">
										序号
									</td>
									<td align="center">
										名称
									</td>
									<td align="center">
										件号
									</td>
									<td align="center">
										每件单价(元/${barContract.istax})
									</td>
									<td align="center">
										型别
									</td>
									<td align="center">
										生产类型
									</td>
									<td align="center">
										工序号
									</td>
									<td align="center">
										产品类型
									</td>
								</tr>
								<s:iterator value="list" id="hh" status="pageStatus">
									<tr>
										<td align="center">
											${pageStatus.index+1}
										</td>
										<td align="center">
											${hh.gx_name}
										</td>
										<td align="center">
											${hh.gx_number}
										</td>
										<td align="center">
											${hh.gx_price}
										</td>
										<td align="center">
											${hh.gx_type}
										</td>
										<td align="center">
											${hh.gx_status}
										</td>
										<td align="center">
											${hh.gx_producetype}
										</td>
										<td align="center">
											${hh.gx_goodstype}
										</td>
									</tr>
								</s:iterator>
							</s:if>
							<s:if
								test='barContract.contract_source=="原材料采购"||barContract.contract_source=="包装物"'>
								<tr>
									<td align="center">
										序号
									</td>
									<td align="center">
										牌号
									</td>
									<td align="center">
										规格
									</td>
									<td align="center">
										单位
									</td>
									<td align="center" colspan="2">
										含税单价(元/KG)
									</td>
								</tr>
								<s:iterator value="list" id="hh" status="pageStatus">
									<tr>
										<td align="center">
											${pageStatus.index+1}
										</td>
										<td align="center">
											${hh.materials_name}
										</td>
										<td align="center">
											${hh.materials_format}
										</td>
										<td align="center">
											${hh.materials_unit}
										</td>
										<td align="center">
											${hh.materials_price}
										</td>
									</tr>
								</s:iterator>
							</s:if>
							<s:if test='barContract.contract_source=="工装采购"'>
								<tr>
									<td align="center">
										序号
									</td>
									<td align="center">
										申请单号
									</td>
									<td align="center">
										招标单号
									</td>
									<td align="center">
										名称
									</td>
									<td align="center">
										零件号
									</td>
									<td align="center">
										工装号
									</td>
									<td align="center">
										数量
									</td>
									<td align="center">
										含税金额(17%)
									</td>
								</tr>
								<s:iterator value="list" id="hh" status="pageStatus">
									<tr>
										<td align="center">
											${pageStatus.index+1}
										</td>
										<td align="center">
											${hh.frock_applynum}
										</td>
										<td align="center">
											${hh.frock_biddingnum}
										</td>
										<td align="center">
											${hh.frock_name}
										</td>
										<td align="center">
											${hh.frock_partnum}
										</td>
										<td align="center">
											${hh.frock_num}
										</td>
										<td align="center">
											${hh.frock_amount}
										</td>
										<td align="center">
											${hh.frock_money}
										</td>
									</tr>
								</s:iterator>
							</s:if>
							<s:if test='barContract.contract_source=="OA"'>
								<tr>
									<td align="center">
										序号
									</td>
									<td align="center">
										物品名称
									</td>
									<td align="center">
										类型
									</td>
									<td align="center">
										规格
									</td>
									<td align="center">
										数量
									</td>
									<td align="center">
										单位
									</td>
									<td align="center">
										预算单价(元)
									</td>
									<td align="center">
										金额(RMB/元)
									</td>
								</tr>
								<s:iterator value="list" status="pageIndex">
									<tr>
										<td align="center">
											${pageIndex.index+1}
										</td>
										<td align="center">
											${detailAppName}
										</td>
										<td align="center">
											${detailChildClass}
										</td>
										<td align="center">
											${detailFormat}
										</td>
										<td align="center">
											${detailCount}
										</td>
										<td align="center">
											${detailUnit}
										</td>
										<td align="center">
											${detailBudgetMoney}
										</td>
										<td align="center">
											${zongMoney}
										</td>
									</tr>
								</s:iterator>
							</s:if>
							<s:if test='barContract.contract_source=="SB"'>
								<tr>
									<td align="center">
										序号
									</td>
									<td align="center">
										保修单号
									</td>
									<td align="center">
										设备名称
									</td>
									<td align="center">
										设备类型
									</td>
									<td align="center">
										单位
									</td>
									<td align="center">
										数量
									</td>
									<td align="center">
										单价
									</td>
									<td align="center">
										总金额
									</td>
								</tr>
								<s:iterator value="list" id="hh" status="pageStatus">
									<tr>
										<td align="center">
											${pageStatus.index+1}
										</td>
										<td align="center">
											${hh.barcode}
										</td>
										<td align="center">
											${hh.name}
										</td>
										<td align="center">
											${hh.type}
										</td>
										<td align="center">
											${hh.macrepair_unit}
										</td>
										<td align="center">
											${hh.macrepair_amount}
										</td>
										<td align="center">
											${hh.macrepair_money}
										</td>
										<td align="center">
											${hh.zongMoney}
										</td>
									</tr>
								</s:iterator>
							</s:if>
							<s:if test='barContract.contract_source=="XBYJ"'>
								<tr>
									<td align="center">
										序号
									</td>
									<td align="center">
										品名
									</td>
									<td align="center">
										规格
									</td>
									<td align="center">
										采购数量
									</td>
									<td align="center">
										单位
									</td>
									<td align="center">
										采购交期
									</td>
									<td align="center">
										单价
									</td>
									<td align="center">
										总金额
									</td>
								</tr>
								<s:iterator value="list" id="hh" status="pageStatus">
									<tr>
										<td align="center">
											${pageStatus.index+1}
										</td>
										<td align="center">
											${hh.goods_name}
										</td>
										<td align="center">
											${hh.goods_format}
										</td>
										<td align="center">
											${hh.goods_amount}
										</td>
										<td align="center">
											${hh.goods_unit}
										</td>
										<td align="center">
											${hh.purchase_delivery}
										</td>
										<td align="center">
											${hh.money}
										</td>
										<td align="center">
											${hh.zongMoney}
										</td>
									</tr>
								</s:iterator>
							</s:if>
						</table>
					</div>
					<input type="button" id="print" value="打印" class="input"
						onclick="pagePrint('printDiv1','sy')" />
				</s:if>
				<%@include file="/util/foot.jsp"%>
		</center>
		<SCRIPT type="text/javascript">
		$(function(){
			//所有都签名
			$("#aaa").append("<img style='max-height:45px;' width='120px' align= 'middle' height='45px' src='<%=request.getContextPath()%>/upload/signature/20150313102656.png'></img>");
			
			//var contract_id = "${performsingle.id}";
			//$.ajax( {
			//	type : "POST",
			//	url : "bargainAction_findContractExecutionNode.action",
			//	data : {
			//		contract_id : contract_id
			//	},
			//	dataType : "json",
			//	success : function(data) {
			//		//$("#aaa").append("<img style='max-height:45px;' width='120px' align= 'middle' height='45px' src='<%=request.getContextPath()%>"+data.data2[0].signature_address+"'></img>");
			//		$("#aaa").append("<img style='max-height:45px;' width='120px' align= 'middle' height='45px' src='<%=request.getContextPath()%>/upload/signature/20150313102656.png'></img>");
			//}
			//});
		})
		function print1(){
			var size = "<s:property value="list.size"/>";
			pagePrint('printDiv','sy');
			if(size>=7){
				pagePrint('printDiv1','sy');
			}
		}
		
		
		</SCRIPT>
	</body>
</html>
