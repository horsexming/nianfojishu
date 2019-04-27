<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.task.util.Util"%>
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

	<body style="background: url('');">
		<center>
			<%@include file="/util/sonTop.jsp"%>

			<div>
				<!-- bargainAction_addBarContract.action?test=self -->
				<form action="bargainAction_addBarContract.action?test=self"
					method="post" theme="simple" onsubmit="return check()">
					<input type="hidden" id="contract_num1"
						name="barContract.contract_num1" value="${contract_num1}">
					<input type="hidden" name="barContract.contract_source"
						value="${contract_source}">
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
									<td align="center" colspan="9">
										<font size="5px"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
												type="text" class="horizontalLine"
												name="barContract.contract_name" style="width: 200px"
												value="${performsingle.purchase_name}" align="right">
											合同</font>
									</td>
								</tr>
								<tr>
									<td colspan="9" align="right">
										<font>合同编号NO: </font>
										<br />
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="6">
										甲方：
										<input readonly="readonly" name="barContract.demandside"
											value="${companyInfo.name}">
									</td>
									<td colspan="5" align="right">
										签定地点：
										<input readonly="readonly" name="barContract.signedPlace"
											value="${companyInfo.name}">
									</td>
								</tr>
								<tr>
									<td colspan="6">
										乙方：
										<input type="text" class="horizontalLine"
											name="barContract.supplier" style="width: 200px" value="">
									</td>
									<td colspan="5" align="right">
										签定时间：
										<input class="horizontalLine" name="barContract.signedDate"
											onfocus="chageClass(this,'')"
											onblur="chageClass(this,'horizontalLine')"
											onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">
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
											class="table" id="complexselectedlist">
											<tr>
												<td colspan="7">
													经双方协商，乙方根据甲方的需求，向甲方提供相关产品
												</td>
											</tr>
											<tr>
												<s:if test='contract_num1=="工装采购"'>
													<td colspan="7">
														一、项目名称:
														<input type="text" class="horizontalLine"
															name="barContract.projectname" value="">
													</td>
												</s:if>
												<s:else>
													<td colspan="7">
														一、零件及价格：
													</td>
												</s:else>

											</tr>
											<s:if test='contract_source=="OA"'>
												<tr>
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
											</s:if>
											<s:if test='contract_source=="XBYJ"'>
												<tr>
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
											</s:if>
											<s:if test='contract_source=="SB"'>
												<tr>
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
													<td align="center">
														备注
													</td>
												</tr>
											</s:if>

											<s:if test='contract_source=="KVP"'>
												<tr>
													<td align="center">
														项目执行单编号
													</td>
													<td align="center">
														改进员工
													</td>
													<td align="center">
														责任员工
													</td>
													<td align="center" colspan="2">
														成本结余
													</td>
												</tr>
											</s:if>
											<s:if test='contract_source=="紧急采购"'>
												<tr>
													<td align="center">
														名称
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
														金额
													</td>
												</tr>
											</s:if>
											<s:if test='contract_source=="零部件及工序外委采购"'>
												<tr>
													<td align="center">
														件号
													</td>
													<td align="center">
														名称
													</td>
													<td align="center">
														每件单价(元/
														<select name="barContract.istax">
															<option value="含税">
																含税
															</option>
															<option value="不含税">
																不含税
															</option>
														</select>
														)
													</td>
													<td align="center">
														型别
													</td>
													<td align="center">
														生产类型
													</td>
													<td align="center">
														产品类型
													</td>
												</tr>
											</s:if>
											<s:if test='contract_source=="原材料采购"||contract_source=="包装物"'>
												<tr>
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
											</s:if>
											<s:if test='contract_num1=="工装采购"'>
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
											<s:if test='contract_source=="设备"'>
												<tr>
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
											<s:if test='contract_source=="设备"'>
												<s:iterator value="list" id="hh" status="pageStatus">
													<tr>
														<td align="center">
															<input
																name="barContractDetailsList[${pageStatus.index}].machine_no"
																value="${hh.machine_no}">
														</td>
														<td align="center">
															<input
																name="barContractDetailsList[${pageStatus.index}].machine_name"
																value="${hh.machine_name}">
														</td>
														<td align="center">
															<input
																name="barContractDetailsList[${pageStatus.index}].machine_type"
																value="${hh.machine_type}">
														</td>
														<td align="center">
															<input
																name="barContractDetailsList[${pageStatus.index}].machine_classGroup"
																value="${hh.machine_classGroup}">
														</td>
														<td align="center">
															<input
																name="barContractDetailsList[${pageStatus.index}].machine_buyamount"
																value="${hh.machine_buyamount}">
														</td>
													</tr>
												</s:iterator>
											</s:if>

											<s:if test='contract_source=="KVP"'>
												<s:iterator value="list" id="hh" status="pageStatus">
													<tr>
														<td align="center">
															<input
																name="barContractDetailsList[${pageStatus.index}].executeNumber"
																value="${hh.executeNumber}">
														</td>
														<td align="center">
															<input
																name="barContractDetailsList[${pageStatus.index}].improve_username"
																value="${hh.improve_username}">
														</td>
														<td align="center">
															<input
																name="barContractDetailsList[${pageStatus.index}].res_username"
																value="${hh.res_username}">
														</td>
														<td align="center" colspan="2">
															<input
																name="barContractDetailsList[${pageStatus.index}].costsavings"
																value="${hh.costsavings}">
														</td>
													</tr>
												</s:iterator>
											</s:if>

											<s:if test='contract_source=="OA"'>
												<s:iterator value="list" status="pageStatus">
													<tr>
														<td align="center">
															<input readonly="readonly"
																name="barContractDetailsList[${pageStatus.index}].detailAppName"
																value="${detailAppName}">
															<input type="hidden"
																name="barContractDetailsList[${pageStatus.index}].detailItemId"
																value="${detailItemId}">
														</td>
														<td align="center">
															<input readonly="readonly"
																name="barContractDetailsList[${pageStatus.index}].detailChildClass"
																value="${detailChildClass}">
														</td>
														<td align="center">
															<input readonly="readonly"
																name="barContractDetailsList[${pageStatus.index}].detailFormat"
																value="${detailFormat}">
														</td>
														<td align="center">
															<input readonly="readonly"
																name="barContractDetailsList[${pageStatus.index}].detailCount"
																value="${detailCount}">
														</td>
														<td align="center">
															<input readonly="readonly"
																name="barContractDetailsList[${pageStatus.index}].detailUnit"
																value="${detailUnit}">
														</td>
														<td align="center">
															<input readonly="readonly"
																name="barContractDetailsList[${pageStatus.index}].detailBudgetMoney"
																value="${detailBudgetMoney}">
														</td>
														<td align="center">
															<input type="text" id="zongMoney${pageStatus.index}"
																name="barContractDetailsList[${pageStatus.index}].zongMoney"
																value="${detailCount*detailBudgetMoney}"
																readonly="readonly">
														</td>
													</tr>
												</s:iterator>
											</s:if>
											<s:if test='contract_source=="XBYJ"'>
												<s:iterator value="list" status="pageStatus">
													<tr>
														<td align="center">
															<input readonly="readonly"
																name="barContractDetailsList[${pageStatus.index}].goods_name"
																value="${goods_name}">
														</td>
														<td align="center">
															<input readonly="readonly"
																name="barContractDetailsList[${pageStatus.index}].goods_format"
																value="${goods_format}">
														</td>
														<td align="center">
															<input readonly="readonly"
																name="barContractDetailsList[${pageStatus.index}].goods_amount"
																value="${goods_amount}">
														</td>
														<td align="center">
															<input readonly="readonly"
																name="barContractDetailsList[${pageStatus.index}].goods_unit"
																value="${goods_unit}">
														</td>
														<td align="center">
															<input readonly="readonly"
																name="barContractDetailsList[${pageStatus.index}].purchase_delivery"
																value="${purchase_delivery}">
														</td>
														<td align="center">
															<input
																name="barContractDetailsList[${pageStatus.index}].money"
																value="${price}">
														</td>
														<td align="center">
															<input id="zongMoney${pageStatus.index}"
																name="barContractDetailsList[${pageStatus.index}].zongMoney"
																value="0">
														</td>
													</tr>
												</s:iterator>
											</s:if>
											<s:if test='contract_source=="SB"'>
												<s:iterator value="list" id="hh" status="pageStatus">
													<tr>
														<td align="center">
															<input
																name="barContractDetailsList[${pageStatus.index}].macrepair_name"
																value="${hh.macrepair_name}">
														</td>
														<td align="center">
															<input
																name="barContractDetailsList[${pageStatus.index}].macrepair_format"
																value="${hh.macrepair_format}">
														</td>
														<td align="center">
															<input
																name="barContractDetailsList[${pageStatus.index}].macrepair_unit"
																value="${hh.macrepair_unit}">
														</td>
														<td align="center">
															<input
																name="barContractDetailsList[${pageStatus.index}].macrepair_amount"
																value="${hh.macrepair_amount}">
														</td>
														<td align="center">
															<input
																name="barContractDetailsList[${pageStatus.index}].macrepair_money"
																value="${hh.macrepair_money}">
														</td>
														<td align="center">
															<input
																name="barContractDetailsList[${pageStatus.index}].macrepair_remark"
																value="${hh.macrepair_remark}">
														</td>
													</tr>
												</s:iterator>
											</s:if>
											<s:if test='contract_source=="紧急采购"'>
												<tr>
													<td align="center">
														<input name="barContractDetailsList[0].qtName" value="">
													</td>
													<td align="center">
														<input name="barContractDetailsList[0].qtUnit" value="">
													</td>
													<td align="center">
														<input name="barContractDetailsList[0].qtNum" value="">
													</td>
													<td align="center">
														<input name="barContractDetailsList[0].qtMoney" value="">
													</td>
													<td align="center" colspan="3">
														<input id="zongMoney${pageStatus.index}"
															name="barContractDetailsList[0].zongMoney" value="">
													</td>
												</tr>
												<tr id="uploadtr">
													<td align="left">
														<input type="button" id="inforButton_1"
															onclick="saveHKInfor(this,1)" value="添加物品" />
													</td>
													<td align="center" colspan="5">
														<input id="deleteItem" style="display: none;"
															type="button" id="inforButton_2" onclick="delInfor()"
															value="删除物品" />
													</td>
												</tr>
											</s:if>
											<s:if test='contract_source=="零部件及工序外委采购"'>
												<s:iterator value="list" id="hh" status="pageStatus">
													<tr>
														<td align="center">
															<%--														<input  name="barContractDetailsList[0].gx_number" value="" >--%>
															<%--														<select id="gx_number1" name="barContractDetailsList[${pageStatus.index}].gx_number" onchange="getType(1)" onmouseover="getNumber(1)">--%>
															<%--															<option value="${hh.gx_number}">${hh.gx_number}</option>--%>
															<%--															</select>--%>
															<%--															<input type="hidden" id="gx_quotedNumber1" name="barContractDetailsList[${pageStatus.index}].gx_quotedNumber" value="">--%>
															<%--															<input type="hidden" id="gx_projectnum1" name="barContractDetailsList[${pageStatus.index}].gx_projectnum" value="">--%>
															<input type="text"
																name="barContractDetailsList[${pageStatus.index}].gx_number"
																value="${hh.gx_number}">
														</td>
														<td align="center">
															<%--															<input  name="barContractDetailsList[0].gx_name" value="" >--%>
															<%--															<select id="gx_name1" name="barContractDetailsList[${pageStatus.index}].gx_name">--%>
															<%--															<option value="${hh.gx_name}">${hh.gx_name}</option>--%>
															<%--															</select>--%>
															<input type="text"
																name="barContractDetailsList[${pageStatus.index}].gx_name"
																value="${hh.gx_name}">
														</td>
														<td align="center">
															<input
																name="barContractDetailsList[${pageStatus.index}].gx_price"
																value="${hh.gx_price}">
														</td>
														<td align="center">
															<%--														<input  name="barContractDetailsList[0].gx_type" value="" >--%>
															<!-- onmouseover="getType(1)" -->
															<%--														<select id="gx_type1" name="barContractDetailsList[${pageStatus.index}].gx_type" >--%>
															<%--															<option value="${hh.gx_type}">${hh.gx_type}</option>--%>
															<%--															</select>--%>
															<input type="text"
																name="barContractDetailsList[${pageStatus.index}].gx_type"
																value="${hh.gx_type}">
														</td>
														<td align="center">
															<%--															<input  name="barContractDetailsList[0].gx_producetype" value="" >--%>
															<!-- onmouseover="getProducetype(1)" -->
															<%--															<select id="gx_producetype1" name="barContractDetailsList[${pageStatus.index}].gx_producetype" >--%>
															<%--															<option value="${hh.gx_producetype}">${hh.gx_producetype}</option>--%>
															<%--															</select>--%>
															<%--															<input type="hidden"   id="gx_status1" name="barContractDetailsList[${pageStatus.index}].gx_status" value="${hh.gx_status}" >--%>
															<input type="text"
																name="barContractDetailsList[${pageStatus.index}].gx_producetype"
																value="${hh.gx_producetype}">
														</td>
														<td align="center">
															<input
																name="barContractDetailsList[${pageStatus.index}].gx_goodstype"
																value="${hh.gx_goodstype}">
														</td>
													</tr>
												</s:iterator>

												<%--													<tr>--%>
												<%--														<td colspan="7" align="center">具体外委零部件品种见附表<input type="file" name="contract_affix" ></td>--%>
												<%--													</tr>--%>

												<%--													<tr id="uploadtr">--%>
												<%--														<td align="left">--%>
												<%--															<input type="button" id="inforButton_1"--%>
												<%--																onclick="saveHKInfor(this,1)" value="添加物品" />--%>
												<%--														</td>--%>
												<%--															<s:if test='contract_num1=="工装采购"'>--%>
												<%--															<td align="left"  colspan="6">--%>
												<%--															<input id="deleteItem" style="display: none;" type="button"--%>
												<%--																id="inforButton_2" onclick="delInfor()" value="删除物品" />--%>
												<%--														</td>--%>
												<%--															</s:if>--%>
												<%--															<s:else>--%>
												<%--															<td align="left"  colspan="5">--%>
												<%--															<input id="deleteItem" style="display: none;" type="button"--%>
												<%--																id="inforButton_2" onclick="delInfor()" value="删除物品" />--%>
												<%--														</td>--%>
												<%--															</s:else>--%>
												<%--														--%>
												<%--													</tr>--%>
											</s:if>

											<s:if test='contract_source=="原材料采购"||contract_source=="包装物"'>
												<s:iterator value="list" id="hh" status="pageStatus">
													<tr>
														<td align="center">
															<input
																name="barContractDetailsList[${pageStatus.index}].materials_name"
																value="${hh.materials_name}">
														</td>
														<td align="center">
															<input
																name="barContractDetailsList[${pageStatus.index}].materials_format"
																value="${hh.materials_format}">
														</td>
														<td align="center">
															<input
																name="barContractDetailsList[${pageStatus.index}].materials_unit"
																value="${hh.materials_unit}">
														</td>
														<td align="center" colspan="2">
															<input
																name="barContractDetailsList[${pageStatus.index}].materials_price"
																value="${hh.materials_price}">
														</td>
													</tr>
												</s:iterator>
												<%--													<tr id="uploadtr">--%>
												<%--														<td align="left">--%>
												<%--															<input type="button" id="inforButton_1"--%>
												<%--																onclick="saveHKInfor(this,1)" value="添加物品" />--%>
												<%--														</td>--%>
												<%--														<td align="left"  colspan="5">--%>
												<%--															<input id="deleteItem" style="display: none;" type="button"--%>
												<%--																id="inforButton_2" onclick="delInfor()" value="删除物品" />--%>
												<%--														</td>--%>
												<%--													</tr>--%>
											</s:if>
											<s:if test='contract_source=="工装采购"'>
												<tr>
													<td align="center">
														<input name="barContractDetailsList[0].frock_applynum"
															value="">
													</td>
													<td align="center">
														<input name="barContractDetailsList[0].frock_biddingnum"
															value="">
													</td>
													<td align="center">
														<input name="barContractDetailsList[0].frock_name"
															value="">
													</td>
													<td align="center">
														<input name="barContractDetailsList[0].frock_partnum"
															value="">
													</td>
													<td align="center">
														<input name="barContractDetailsList[0].frock_num" value="">
													</td>
													<td align="center">
														<input name="barContractDetailsList[0].frock_amount"
															value="">
													</td>
													<td align="center">
														<input name="barContractDetailsList[0].frock_money"
															value="">
													</td>
												</tr>
												<tr id="uploadtr">
													<td align="left">
														<input type="button" id="inforButton_1"
															onclick="saveHKInfor(this,1)" value="添加物品" />
													</td>
													<td align="left" colspan="6">
														<input id="deleteItem" style="display: none;"
															type="button" id="inforButton_2" onclick="delInfor()"
															value="删除物品" />
													</td>
												</tr>
											</s:if>
											<s:elseif test='contract_source=="SB"'>
												<tr>
													<th colspan="3">
														合计
													</th>
													<th colspan="4">
														<input class="horizontalLine" style="width: 150px"
															onfocus="chageClass(this,'')" name="barContract.heji"
															id='heji' value="${heji}"
															onblur="chageClass(this,'horizontalLine')"
															readonly="readonly">
														元
														<s:else>
															<input class="horizontalLine" style="width: 150px"
																onfocus="chageClass(this,'')" name="barContract.heji"
																id='heji' value=""
																onblur="chageClass(this,'horizontalLine')">元
													</s:else>

													</th>
												</tr>
											</s:elseif>
											<s:elseif
												test='contract_source!="紧急采购"&&contract_source!="零部件及工序外委采购"&&contract_source!="原材料采购"&&contract_source!="包装物"'>
												<tr>
													<th colspan="3">
														合计
													</th>
													<th colspan="4">
														<s:if
															test='contract_source=="OA"||contract_source=="XBYJ"'>
															<input class="horizontalLine" style="width: 150px"
																onfocus="chageClass(this,'')" name="barContract.heji"
																id='heji' value="${heji}"
																onblur="chageClass(this,'horizontalLine')"
																readonly="readonly">元
													</s:if>
														<s:else>
															<input class="horizontalLine" style="width: 150px"
																onfocus="chageClass(this,'')" name="barContract.heji"
																id='heji' value=""
																onblur="chageClass(this,'horizontalLine')">元
													</s:else>

													</th>
												</tr>
											</s:elseif>
											<s:else>
												<s:if test='contract_num1=="紧急采购"||contract_num1=="工装采购"'>
													<tr>
														<th colspan="3">
															合计
														</th>
														<th colspan="4">
															<input class="horizontalLine" style="width: 150px"
																onfocus="chageClass(this,'')" name="barContract.heji"
																id='heji' value=""
																onblur="chageClass(this,'horizontalLine')">
															元
														</th>
													</tr>
												</s:if>
											</s:else>

											<s:if
												test='contract_source!="紧急采购"&&contract_source!="零部件及工序外委采购"&&contract_source!="原材料采购"'>
												<tr>
													<td rowspan="2">
														合同期限
													</td>
													<td align="center">
														起
													</td>
													<td align="center">
														止
													</td>
													<td rowspan="3">
														合同依据:
													</td>
												</tr>
												<tr>
													<td>

														<input class="horizontalLine" style=""
															onfocus="chageClass(this,'')"
															name="barContract.startDate" value=""
															onblur="chageClass(this,'horizontalLine')"
															onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">

													</td>
													<td>
														<input class="horizontalLine" style=""
															onfocus="chageClass(this,'')" name="barContract.endDate"
															value="" onblur="chageClass(this,'horizontalLine')"
															onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">
													</td>
													<td>
														<input type="text" style="width: 500px"
															onfocus="chageClass(this,'')"
															onblur="chageClass(this,'horizontalLine')"
															class="horizontalLine"
															name="barContract.contract_according" value="">
													</td>
												</tr>
											</s:if>
											<s:else>
												<s:if
													test='contract_source!="紧急采购"&&contract_source!="工装采购"||contract_source!="OA"'>
													<tr>
														<td rowspan="2">
															合同期限
														</td>
														<td align="center">
															起
														</td>
														<td align="center">
															止
														</td>
														<td rowspan="3">
															合同依据:
														</td>
													</tr>
													<tr>
														<td>

															<input class="horizontalLine" style=""
																onfocus="chageClass(this,'')"
																name="barContract.startDate" value=""
																onblur="chageClass(this,'horizontalLine')"
																onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">

														</td>
														<td>
															<input class="horizontalLine" style=""
																onfocus="chageClass(this,'')" name="barContract.endDate"
																value="" onblur="chageClass(this,'horizontalLine')"
																onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">
														</td>
														<td>
															<input type="text" style="width: 500px"
																onfocus="chageClass(this,'')"
																onblur="chageClass(this,'horizontalLine')"
																class="horizontalLine"
																name="barContract.contract_according" value="">
														</td>
													</tr>
												</s:if>
											</s:else>


											<s:if
												test='contract_source!="紧急采购"&&contract_source!="零部件及工序外委采购"&&contract_source!="原材料采购"&&contract_source!="XBYJ"&&contract_source!="OA"'>
												<tr>
													<td colspan="7">
														1、运输方式及费用负担：
														<input name="barContract.transpor_tway" size="35px"
															value="由乙方负责运输，费用乙方承担。">
													</td>
												</tr>
												<tr>
													<td colspan="7">
														2、包装要求：
														<input name="barContract.pack_ask" size="70px"
															value="纸箱每箱附标识卡，每批附产品合格证、质保书及原材料材质报告。">
													</td>
												</tr>
												<tr>
													<td colspan="7">
														3、合理损耗标准及计算方法：以实际来料净重计算确定（磅重）。
													</td>
												</tr>
												<tr>
													<td colspan="7">
														4、甲方在生产过程中，发现因乙方原因（如材质或焊接问题等）造成零件的报废或返修，由此发生的各项费用由乙方承担。
													</td>
												</tr>
												<tr>
													<td colspan="7">
														5、违约责任：按《经济合同法》。
													</td>
												</tr>
												<tr>
													<td colspan="7">
														6、本合同一式两份，甲乙双方各执一份。
													</td>
												</tr>
												<tr>
													<td align="center" colspan="7">
														&nbsp;&nbsp;
													</td>
												</tr>
											</s:if>

											<s:if test='contract_source=="紧急采购"'>
												<tr>
													<td align="left" colspan="7">
														备注:
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
											<s:if test='contract_source=="零部件及工序外委采购"'>
												<tr>
													<td colspan="7">
														二、付款：甲方在货物验收合格，收到乙方正确发票后
														<input name="barContract.paymentDate" value="30"
															size="4px">
														天内电汇或银行承兑票形式支付。
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
														1、交货地点：
														<input name="barContract.deliveryPlace"
															value="${companyInfo.address}" size="26px">
														（${companyInfo.name}）
													</td>
												</tr>
												<tr>
													<td colspan="7">
														2、运输方式及费用负担：
														<input name="barContract.transpor_tway" size="35px"
															value="由乙方负责运输，费用乙方承担。">
													</td>
												</tr>
												<tr>
													<td colspan="7">
														3、包装要求：
														<input name="barContract.pack_ask" size="70px"
															value="纸箱每箱附标识卡，每批附产品合格证、质保书及原材料材质报告。">
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
											<s:if test='contract_source=="原材料采购"'>
												<tr>
													<td colspan="7">
														二、付款：甲方在货物验收合格，收到乙方正确发票后
														<input name="barContract.paymentDate" value="90"
															size="4px">
														天内电汇或银行承兑票形式支付。
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
														1、交货地点：
														<input name="barContract.deliveryPlace"
															value="${companyInfo.address}" size="26px">
														（${companyInfo.name}）
													</td>
												</tr>
												<tr>
													<td colspan="7">
														1、运输方式及费用负担：
														<input name="barContract.transpor_tway" size="35px"
															value="由乙方负责运输，费用乙方承担。">
													</td>
												</tr>
												<tr>
													<td colspan="7">
														2、包装要求：
														<input name="barContract.pack_ask" size="70px"
															value="纸箱每箱附标识卡，每批附产品合格证、质保书及原材料材质报告。">
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
												test='contract_source=="工装采购"||contract_source=="XBYJ"||contract_source=="OA"'>
												<tr>
													<td colspan="7">
														二.工装技术制造要求及标准：
													</td>
												</tr>
												<tr>
													<td colspan="7">
														1.乙方按照甲方提供的产品图纸及技术要求，经甲、乙双方确定合理的设计方案而制造。
													</td>
												</tr>
												<tr>
													<td colspan="7">
														2.所使用的材料由乙方负责采购，工装材料必须达到该工装的寿命要求。使用寿命在20万次以上。
													</td>
												</tr>
												<tr>
													<td colspan="7">
														3.外观要求模具统一涂蓝色油漆，在模具的上模座前方用钢字码打印模具型号、制造日期、冲床吨位。夹
														具要求部件经过发黑处理，统一在夹具台面右下角用钢字码打印夹具型号、制造日期。
													</td>
												</tr>
												<tr>
													<td colspan="7">
														三.工装验收：交货日期（
														<input class="horizontalLine"
															name="barContract.deliverydate"
															onfocus="chageClass(this,'')"
															onblur="chageClass(this,'horizontalLine')"
															onClick="WdatePicker({dateFmt:'yyyy年-MM月-dd日',skin:'whyGreen'})">
														）
													</td>
												</tr>
												<tr>
													<td colspan="7">
														1.按照甲方提供的产品数模图纸及技术协议要求或样件进行验收。
													</td>
												</tr>
												<tr>
													<td colspan="7">
														2.调试工装所使用的材料由乙方负责，甲方只提供正品材料每道工序10件，乙方必须提供合格件5件以上。
													</td>
												</tr>
												<tr>
													<td colspan="7">
														3.在甲方现场验收时，工装调试均由乙方负责，对工装是否合格按产品数模及图纸技术要求进行验收，同
														时在批量生产过程中报废率不能超过3% 。超出3% 报废零件由乙方承担废品损失。
													</td>
												</tr>
												<tr>
													<td colspan="7">
														四.包装及配送：
													</td>
												</tr>
												<tr>
													<td colspan="7">
														<input type="text" size="70px;" class="horizontalLine"
															name="barContract.pack_ask"
															value="包装及运输费用由乙方承担，乙方负责将工装运送到甲方公司。">
													</td>
												</tr>
												<tr>
													<td colspan="7">
														五.售后服务：
													</td>
												</tr>
												<tr>
													<td colspan="7">
														1.乙方交付工装使用之日起六个月为质量保证期，如出现损坏乙方质量原因则乙方负责免费更换
													</td>
												</tr>
												<tr>
													<td colspan="7">
														2.保修期内乙方接到甲方维修及质量问题通知后，在一个小时内给予答复，如不能用传真及电话解决的，
														乙方在24小时内派人到甲方现场解决。
													</td>
												</tr>
												<tr>
													<td colspan="7">
														六.付款方式分期付款：
													</td>
												</tr>
												<tr>
													<td colspan="7">
														一.
														<input class="horizontalLine" size="70px;"
															name="barContract.paymentway1"
															value="期于合同签定后一周内甲方付合同款的30%">
													</td>
												</tr>
												<tr>
													<td colspan="7">
														二.
														<input class="horizontalLine" size="70px;"
															name="barContract.paymentway2"
															value="期于工装验收合格后一个月内付合同款的60%">
													</td>
												</tr>
												<tr>
													<td colspan="7">
														三.
														<input class="horizontalLine" size="70px;"
															name="barContract.paymentway3"
															value="期于工装使用半年后一周内付合同款的10%">
													</td>
												</tr>
												<tr>
													<td colspan="7">
														七.违约责任：
													</td>
												</tr>
												<tr>
													<td colspan="7">
														1.甲方提供给乙方的技术图纸及样件、工艺结构等技术资料均属甲方产权，乙方对此负有保密责任，
														如因乙方对技术资料疏忽或故意外泄造成甲方产权损失,
													</td>
												</tr>
												<tr>
													<td colspan="7">
														&nbsp;&nbsp;&nbsp;经查实甲方有权进行追究，乙方必须承担因此造成的损失及赔偿责任。
													</td>
												</tr>
												<tr>
													<td colspan="7">
														2.经甲方同意适当顺延交期确认外，或除了国家发令天灾不可抗拒因素，乙方未按预订日期完成情况时，
														应以每日赔偿本合同总金额 2 %工装费给甲方作为逾期违约损失赔偿。
													</td>
												</tr>
												<tr>
													<td colspan="7">
														&nbsp;&nbsp;&nbsp;超过10日未能完成的，甲方有权解除合同和追究乙方的责任并要求乙方赔偿相应的损失。
													</td>
												</tr>
												<tr>
													<td colspan="7">
														3.若乙方制造的工装结构用料、品质及寿命不合理，功能未达到甲方规定的原技术设计要求时，
														乙方负责工装修改或重开取代原有工装，
													</td>
												</tr>
												<tr>
													<td colspan="7">
														&nbsp;&nbsp;&nbsp;乙方应自行承担重开工装费用及该工装拖延时间的赔偿损失责任。
													</td>
												</tr>
												<tr>
													<td colspan="7">
														八.解决合同纠纷方式：
													</td>
												</tr>
												<tr>
													<td colspan="7">
														遇合同中未协签事项，双方友好协商解决，协商不成，任何一方均可向合同签订地所在人民法院提出诉讼
													</td>
												</tr>
											</s:if>
											<tr>
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
															<td height="100%">
																<div style="height: 45px;">
																	<span id="aaa"></span>
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
																${companyInfo.tel}
															</td>
														</tr>
														<tr height="30px">
															<td>
																开户银行：
															</td>
															<td>
																${companyInfo.accountBank}
															</td>
														</tr>
														<tr height="30px">
															<td>
																帐 号：
															</td>
															<td>
																${companyInfo.accountnum}
															</td>
														</tr>
														<tr height="30px">
															<td>
																邮政编码：
															</td>
															<td>
																${companyInfo.zip}
															</td>
													</table>
												</td>
												<td colspan="3">
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
																<input class="horizontalLine"
																	name="barContract.unit_name" style="width: 175px">
															</td>
														</tr>
														<tr height="30px">
															<td>
																单位地址：
															</td>
															<td>
																<input class="horizontalLine" name="barContract.address"
																	style="width: 175px">
															</td>
														</tr>
														<tr height="30px">
															<td>
																法定代表人：
															</td>
															<td>
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
																<input class="horizontalLine" name="barContract.tel"
																	style="width: 175px">
															</td>
														</tr>
														<tr height="30px">
															<td>
																开户银行：
															</td>
															<td>
																<input class="horizontalLine"
																	name="barContract.accountBank" style="width: 175px">
															</td>
														</tr>
														<tr height="30px">
															<td>
																帐 号：
															</td>
															<td>
																<input class="horizontalLine"
																	name="barContract.accountnum" style="width: 175px">
															</td>
														</tr>
														<tr height="30px">
															<td>
																邮政编码：
															</td>
															<td>
																<input class="horizontalLine" name="barContract.zip"
																	style="width: 175px">
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</div>
						<input type="hidden" value="${contract_id}" name="contract_id"/>
						<input type="submit" value="保存" class="input" />
				</form>
				<s:if test='#session.Users.dept!="供应商"'>
					<s:if test='zhtoubiao.tkong7=="审核中"'>
						<a href="CircuitRunAction_findAduitPage.action ">审核</a>
					</s:if>
				</s:if>
				<s:if test='zhtoubiao.tkong7=="同意"'>
					<input type="button" id="print" value="打印" class="input"
						onclick="pagePrint('printDiv')" />
				</s:if>

				<%@include file="/util/foot.jsp"%>
		</center>
	</body>
</html>
<script type="text/javascript">
//添加物品
var begAddLineNum = 3;//物品
var lineCount = 1;
//添加明细
function saveHKInfor(obj, few) {
	var _tbody = document.getElementById("complexselectedlist").tBodies[0];//获得第一个tbody

	var uploadtr = document.getElementById("uploadtr");//将要在该Tr之前添加元素

	var _tr = document.createElement("tr");

	_tr.setAttribute('align', 'center');

	_tbody.insertBefore(_tr, uploadtr);

	begAddLineNum++;
	var x = _tr.insertCell(0);
	x.innerHTML = "<input name='barContractDetailsList[" + lineCount
			+ "].qtName' value=''>";

	var x1 = _tr.insertCell(1);
	x1.innerHTML = "<input name='barContractDetailsList[" + lineCount
			+ "].qtUnit' value=''>";

	var x2 = _tr.insertCell(2);
	x2.innerHTML = "<input name='barContractDetailsList[" + lineCount
			+ "].qtNum' value=''>";

	var x3 = _tr.insertCell(3);
	x3.innerHTML = "<input name='barContractDetailsList[" + lineCount
			+ "].qtMone' value=''>";

	var x4 = _tr.insertCell(4);
	x4.setAttribute('colspan', '3');
	x4.setAttribute('align', 'center')
	x4.innerHTML = "<input id='zongMoney${pageStatus.index}' name='barContractDetailsList["
			+ lineCount + "].zongMoney' value=''>";

	lineCount++;
	document.getElementById("deleteItem").style.display = "block";
}

//删除明细
function delInfor() {
	//alert(begAddLineNum);
	complexselectedlist.deleteRow(begAddLineNum);
	begAddLineNum--;
	lineCount--;
	if (begAddLineNum < 4) {
		document.getElementById("deleteItem").style.display = "none";
	}

}
</script>
