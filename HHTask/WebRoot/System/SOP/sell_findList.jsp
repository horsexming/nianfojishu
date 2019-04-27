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
.ztree li a {
	color: #fff;
}
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

ul li {
	list-style: none;
	padding: 0px;
	margin: 0px;
}

.select_checkBox {
	border: 0px solid red;
	position: relative;
	display: inline-block;
}

.chartQuota {
	height: 23px;
	float: left;
	display: inline-block;
	border: 0px solid black;
	position: relative;
}

.chartOptionsFlowTrend {
	z-index: 300;
	background-color: white;
	border: 1px solid gray;
	display: none;
	position: absolute;
	left: 0px;
	top: 23px;
	width: 150px;
}

.chartOptionsFlowTrend ul {
	float: left;
	padding: 0px;
	margin: 5px;
}

.chartOptionsFlowTrend li { /* float:left; */
	display: block;
	position: relative;
	left: 0px;
	margin: 0px;
	clear: both;
}

.chartOptionsFlowTrend li * {
	float: left;
}

a:-webkit-any-link {
	color: -webkit-link;
	text-decoration: underline;
	cursor: auto;
}

.chartQuota p a {
	float: left;
	height: 21px;
	outline: 0 none;
	border: 1px solid #ccc;
	line-height: 22px;
	padding: 0 5px;
	overflow: hidden;
	background: #eaeaea;
	color: #313131;
	text-decoration: none;
}

.chartQuota p {
	margin: 0px;
	folat: left;
	overflow: hidden;
	height: 23px;
	line-height: 24px;
	display: inline-block;
}

.chartOptionsFlowTrend p {
	height: 23px;
	line-height: 23px;
	overflow: hidden;
	position: relative;
	z-index: 2;
	background: #fefbf7;
	padding-top: 0px;
	display: inline-block;
}

.chartOptionsFlowTrend p a {
	border: 1px solid #fff;
	margin-left: 15px;
	color: #2e91da;
}
</style>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng">

			<div align="center">
				<div style="">
					<form action="sellAction!PladdSell.action" method="post"
						enctype="multipart/form-data">
						<strong>出库记录导入：选择导入文件:</strong>
						<input type="file" name="addsell">
						<a href="<%=basePath%>/upload/file/download/SellChuKu.xls">导入模版下载</a>
						<a
							href="FileViewAction.action?FilePath=/upload/file/download/SellChuKu.xls&Refresh=true">/预览</a>
						<input type="submit" value="批量导入" id="sub"
							onclick="todisabled(this)">
					</form>
					<br />
				</div>
				<form action="sellAction!findSellByCondition.action?status=selllog" method="post">
					<input type="hidden" name="tag" value="${tag}">
					<table class="table" style="width: 95%;">
						<tr>
							<th>
								件号:
							</th>
							<td>
								<input type="text" name="sell.sellMarkId"
									value="${sell.sellMarkId }" />
							</td>
							<th>
								品名:
							</th>
							<td>
								<input type="text" name="sell.sellGoods"
									value="${sell.sellGoods }" />
							</td>
							<th>
								规格：
							</th>
							<td>
								<input type="text" name="sell.sellFormat"
									value="${sell.sellFormat }" />
							</td>
						</tr>
						<tr>
							<th>
								物料类别:
							</th>
							<td>
								<div class="zTreeDemoBackground left">
									<ul class="list">
										<li class="title">
											<input id="wgType" type="text" value="${sell.wgType}"
												style="width: 120px;" name="sell.wgType" />
											<a id="menuBtn" href="#" onclick="showMenu(); return false;">选择</a>
											<%--
											(按住Ctrl建不松点击,可清空)
										--%>
										</li>
									</ul>
								</div>
								<div id="menuContent" class="menuContent"
									style="display: none; position: absolute;">
									<ul id="treeDemo" class="ztree"
										style="margin-top: 0; width: 160px;"></ul>
								</div>
							</td>
							<th>
								供料属性:
							</th>
							<td>
								<select name="sell.kgliao" style="width: 155px;">
									<option value="${sell.kgliao}">
										${sell.kgliao}
									</option>
									<option>
									</option>
									<option value="TK">
										自购(TK)
									</option>
									<option value="TK AVL">
										指定供应商(TK AVL)
									</option>
									<option value="CS">
										客供(CS)
									</option>
									<option value="TK Price">
										完全指定(TK Price)
									</option>
								</select>
							</td>
							<th>
								版本:
							</th>
							<td>
								<input type="text" name="sell.banBenNumber"
									value="${sell.banBenNumber }" />
							</td>
						</tr>
						<tr>
							<th>
								库别:
							</th>
							<td>
								<select id="whView" name="sell.sellWarehouse"
									style="width: 155px;" onchange="changvalue0(this)">
									<option>
										${sell.sellWarehouse}
									</option>
									<option></option>
								</select>
							</td>
							<th>
								仓区:
							</th>
							<td>
								<!--								<select id="goodHouseName" name="sell.goodHouseName"-->
								<!--									style="width: 155px;">-->
								<!--									<option>-->
								<!--										${sell.goodHouseName}-->
								<!--									</option>-->
								<!--									<option></option>-->
								<!--									<s:iterator value="list1" id="pageLists" status="pageStatus">-->
								<!--										<option value="${pageLists.goodHouseName}">-->
								<!--											${pageLists.goodHouseName}-->
								<!--										</option>-->
								<!--									</s:iterator>-->
								<!--								</select>-->
								<select id="goodHouseName" name="sell.goodHouseName"
									style="width: 155px;">
									<option value="${sell.goodHouseName}">
										${sell.goodHouseName}
									</option>
									<option></option>
								</select>
							</td>
							<th>
								库位:
							</th>
							<td>
								<input type="text" name="sell.kuwei" value="${sell.kuwei }" />
							</td>
						</tr>
						<tr>
							<th>
								内部订单号:
							</th>
							<td>
								<input type="text" name="sell.orderNum" value="${sell.orderNum}" />
							</td>
							<th>
								日期从
							</th>
							<td>
								<input class="Wdate" type="text" name="startDate"
									value="${startDate}" size="15"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<th>
								到
							</th>
							<td>
								<input class="Wdate" type="text" name="endDate"
									value="${endDate}" size="15"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
						</tr>
						<tr>
							<th>
								出库类型
							</th>
							<td>
								<select name="sell.style">
									<option>
										${sell.style}
									</option>
									<option value="">
									</option>
									<option value="生产刷卡(多)">
										生产刷卡(多)
									</option>
									<option value="销售出库">
										销售出库
									</option>
									<option value="领料出库">
										领料出库
									</option>
									<option value="返修出库">
										返修出库
									</option>
									<option value="退料出库">
										退料出库
									</option>
									<option value="报废出库">
										报废出库
									</option>
									<option value="转仓出库">
										转仓出库
									</option>
									<option value="售后出库">
										售后出库
									</option>
									<option value="供应商领料">供应商领料</option>
									<option value="不合格品出库">不合格品出库</option>
									<option value="外协刷卡出库(BOM)">外协刷卡出库(BOM)</option>
									<option value="外协刷卡出库(全)">外协刷卡出库(全)</option>
									<option value="生产刷卡(多)">生产刷卡(多)</option>
									<option value="生产刷卡">生产刷卡</option>
									<option value="调仓出库">调仓出库</option>
								</select>
							</td>
							<th>
								客户:
							</th>
							<td>
								<input type="text" name="sell.sellCompanyName"
									value="${sell.sellCompanyName }" />
							</td>
							<th>
								供应商:
							</th>
							<td>
								<input name="sell.sellSupplier" />
							</td>
						</tr>
						<tr>
							<th>
								业务件号:
							</th>
							<td>
								<input type="text" name="sell.ywmarkId"
									value="${sell.ywmarkId }" />
							</td>
							<th>
								领料人:
							</th>
							<td>
								<input type="text" name="sell.sellCharger"
									value="${sell.sellCharger}" />
							</td>
							<th>
								领料部门:
							</th>
							<td>
								<select id="llrdept" name="sell.sellchardept"
									style="width: 155px;">
									<option>
										${sell.sellchardept}
									</option>
									<option></option>
									<s:iterator value="list2" id="pagedept">
										<option>
											${pagedept}
										</option>
									</s:iterator>
								</select>
							</td>
						</tr>
						<tr>
							<th>
								打印单号:
							</th>
							<td>
								<input type="text" name="sell.printNumber"
									value="${sell.printNumber}" />
							</td>
							<th>
								批次:
							</th>
							<td>
								<input type="text" name="sell.sellLot" value="${sell.sellLot}" />
							</td>
							<th>
								需求单号:
							</th>
							<td>
								<input type="text" name="sell.planTotalNum"
									value="${sell.planTotalNum}">
							</td>
						</tr>
						<tr>
							<th>审批状态：</th>
							<td>
								<select name="sell.handwordSellStatus" >
									<s:iterator value="{'','未审批','同意','打回'}" id="epStatus">
										<s:if test="#epStatus==sell.handwordSellStatus">
											<option selected="selected" >${epStatus }</option>
										</s:if>
										<s:else>
											<option>${epStatus }</option>
										</s:else>
									</s:iterator>
								
									
								</select>
							</td>
							<th>
								外部订单：
							</th>
							<td>
								<input type="text" name="sell.outOrderNumer"
									   value="${sell.outOrderNumer}">
							</td>
							<th>
								锁定订单：
							</th>
							<td>
								<input type="text" name="sell.suodingdanhao"
									   value="${sell.suodingdanhao}">
							</td>
						</tr>
						<tr>
							<th colspan="8">
								<input type="submit" value="查找" class="input" />
								<input type="button" value="导出" onclick="exportExcel(this.form);todisabledone(this)" data="downData"
									class="input" />
							</th>
						</tr>
					</table>
				</form>
				<form action="sellAction!printStorage.action" method="post"
					onsubmit="return vali()">
					<table class="table" style="width: 95%;">
						<tr bgcolor="#c0dcf2" height="30px"
							style="border-collapse: separate;">
							<th>
								<input type="checkbox" id="checkAll" onchange="checkAllBoxs()">
								全选未打印
							</th>
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
								业务件号
							</th>
							<th align="center">
								内部订单号（锁定单号）
							</th>
							<th align="center">
								外部订单号
							</th>
							<th align="center">
								送货单号
							</th>
							<th align="center">
								件号
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
								单位
							</th>
							<th align="center">
								数量
								<br />
								(总数量
								<br />
								${zongCount})
							</th>
							<th align="center">
								转换单位
							</th>
							<th align="center">
								转换数量
							</th>
							<th align="center">
								退货(库)数量
							</th>
							<th align="center">
								客户
							</th>
							<th align="center">
								供应商
							</th>
							<th align="center">
								出库类型
							</th>
							<th align="center">
								出库日期
							</th>
							<th align="center">
								领料人
							</th>
							<s:if test="tag=='price'">
								<th align="center">
									含税单价
								</th>
								<th align="center">
									总额(含税)
								</th>
								<th align="center">
									税率(%)
								</th>
								<th align="center">
									不含税单价
								</th>
							</s:if>
							<th align="center">
								打印单号
							</th>
							<th align="center">
								审批状态
							</th>
							<th align="center">
								操作
							</th>
						</tr>
						<s:if test="tag!='showChuku'">
							<%--							<tr>--%>
							<%--								<th colspan="26" bgcolor="#FFB6C1"--%>
							<%--									style="color: red；font-weight :                                             bold;">--%>
							<%--									出库未打印记录--%>
							<%--								</th>--%>
							<%--							</tr>--%>
							<s:if test="{listSell.size()>0}">
								<s:iterator value="listSell" status="se" id="sellPrint">

									<tr align="center" bgcolor="#FFB6C1"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#FFB6C1')">
										<td>
											<input type="checkbox" name="selected"
												value="${sellPrint.sellId}" class="toCheckSingle"
												onchange="checkSingle()" />
										</td>
										<td>
											<s:property value="#see.index+1" />
										</td>
										<td>
											${sellPrint.sellWarehouse}
										</td>
										<td>
											${sellPrint.goodHouseName}
										</td>
										<td>
											${sellPrint.kuwei}
										</td>
										<td>
											${sellPrint.ywmarkId}
										</td>
										<td>
											${sellPrint.orderNum}
											<br/>
											(${sellPrint.suodingdanhao})
										</td>
										<td>
											${sellPrint.outOrderNumer}
										</td>
										<td>
											${sellPrint.sellSendnum}
										</td>
										<td>
											${sellPrint.sellMarkId}
										</td>
										<td>
											${sellPrint.banBenNumber}
										</td>

										<td>
											${sellPrint.kgliao}
										</td>
										<td>
											${sellPrint.sellLot}
										</td>
										<td>
											${sellPrint.wgType}
										</td>
										<td
											style="max-width: 50px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
											<font size="1">${sellPrint.sellGoods}</font>
											<ul class="qs_ul">
												<li>
													${sellPrint.sellGoods}
												</li>
											</ul>
										</td>
										<td
											style="max-width: 50px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
											<font size="1">${sellPrint.sellFormat}</font>
											<ul class="qs_ul">
												<li>
													${sellPrint.sellFormat}
												</li>
											</ul>
										</td>
										<td>
											${sellPrint.sellUnit}
										</td>
										<td>
											${sellPrint.sellCount}
										</td>
										<td>
											${sellPrint.goodsStoreZHUnit}
										</td>
										<td>
											${sellPrint.sellZhishu}
										</td>
										<td>
											${sellPrint.tksellCount}
										</td>
										<td>
											${sellPrint.sellCompanyName}
										</td>
										<td
											style="max-width: 50px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
											<font size="1">${sellPrint.sellSupplier}</font>
											<ul class="qs_ul">
												<li>
													${sellPrint.sellSupplier}
												</li>
											</ul>
										</td>
										<td>
											${sellPrint.style}
										</td>
										<td>
											${sellPrint.sellDate}
										</td>
										<td>
											${sellPrint.sellCharger}
											<s:if
												test="#sellPrint.sellchardept!=null&&#sellPrint.sellchardept!=''">
											(<s:property value="#sellPrint.sellchardept" />)
											</s:if>
										</td>
										<s:if test="tag='price'">
											<td>
												${sellPrint.sellPrice}
											</td>
											<td>
												${sellPrint.sellPrice*sellPrint.sellCount}
											</td>
											<td>
												${sellPrint.taxprice}
											</td>
											<td>
												${sellPrint.sellbhsPrice}
											</td>
										</s:if>
										<td>
											${sellPrint.printNumber}
										</td>
										<td>
											<a href="CircuitRunAction_findAduitPage.action?id=${sellPrint.handwordSellEpId}">${sellPrint.handwordSellStatus}</a>
										</td>
										<td>
											<a
												href="sellAction!getOneSell.action?id=${sellPrint.sellId}&tag=updateThisSell&status=mingxi">明细</a>
											<%--										<s:if test="true==#sellPrint.bedit">--%>
											<%--											<a target="print"--%>
											<%--												href="sellAction!getOneSell.action?id=${sellPrint.sellId}&tag=sellPrint">打印</a>&nbsp;--%>
											<%--							</s:if>--%>
										</td>

									</tr>
								</s:iterator>
							</s:if>
						</s:if>
						<tr>
							<th colspan="35" bgcolor="#9BCD9B">
								出库历史记录
							</th>
						</tr>
						<s:if test="{list.size()>0}">
							<s:iterator value="list" status="see" id="sellStory">
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
									<input type="checkbox" name="selected"
										value="${sellStory.sellId}" />
								</td>
								<td>
									<s:property value="#see.index+1" />
								</td>
								<td>
									${sellStory.sellWarehouse}
								</td>
								<td>
									${sellStory.goodHouseName}
								</td>
								<td>
									${sellStory.kuwei}
								</td>
								<td>
									${sellStory.ywmarkId}
								</td>
								<td>
									${sellStory.orderNum}
									<br/>
											(${sellStory.suodingdanhao})
								</td>
								<td>
									${sellStory.outOrderNumer}
								</td>
								<td>
									${sellStory.sellSendnum}
								</td>
								<td>
									${sellStory.sellMarkId}
								</td>
								<td>
									${sellStory.banBenNumber}
								</td>

								<td>
									${sellStory.kgliao}
								</td>
								<td>
									${sellStory.sellLot}
								</td>
								<td>
									${sellStory.wgType}
								</td>
								<td
									style="max-width: 50px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
									<font size="1">${sellStory.sellGoods}</font>
									<ul class="qs_ul">
										<li>
											${sellStory.sellGoods}
										</li>
									</ul>
								</td>
								<td
									style="max-width: 50px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
									<font size="1">${sellStory.sellFormat}</font>
									<ul class="qs_ul">
										<li>
											${sellStory.sellFormat}
										</li>
									</ul>
								</td>
								<td>
									${sellStory.sellUnit}
								</td>
								<td>
									<fmt:formatNumber value="${sellStory.sellCount}"
										pattern="#.####"></fmt:formatNumber>
								</td>
								<td>
									${sellStory.goodsStoreZHUnit}
								</td>
								<td>
									${sellStory.sellZhishu}
								</td>
								<td>
									${sellStory.tksellCount}
								</td>
								<td>
									${sellStory.sellCompanyName}
								</td>
								<td
									style="max-width: 50px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
									<font size="1">${sellStory.sellSupplier}</font>
									<ul class="qs_ul">
										<li>
											${sellStory.sellSupplier}
										</li>
									</ul>
								</td>
								<td>
									${sellStory.style}
								</td>
								<td>
									${sellStory.sellTime}
								</td>
								<td>
									${sellStory.sellCharger}
									<s:if
										test="#sellStory.sellchardept!=null&&#sellStory.sellchardept!=''">
											(<s:property value="#sellStory.sellchardept" />)
											</s:if>
								</td>
								<s:if test="tag=='price'">
									<td>
										<fmt:formatNumber value="${sellStory.sellPrice}"
										pattern="#.####"></fmt:formatNumber>
									</td>
									<td>
										<fmt:formatNumber value="${sellStory.sellPrice*sellStory.sellCount}"
										pattern="#.####"></fmt:formatNumber>
									</td>
									<td>
										<fmt:formatNumber value="${sellStory.taxprice}"
										pattern="#.####"></fmt:formatNumber>
									</td>
									<td>
										<fmt:formatNumber value="${sellStory.sellbhsPrice}"
										pattern="#.####"></fmt:formatNumber>
									</td>
								</s:if>
								<td>
									${sellStory.printNumber}
								</td>
								<td>
									<a href="CircuitRunAction_findAduitPage.action?id=${sellStory.handwordSellEpId}">${sellStory.handwordSellStatus}</a>
								</td>
								<td>
<!-- 									/<a onClick="return confirm('确定要删除该条记录吗？')" -->
<%-- 												href="sellAction!getOneSell.action?id=${sellStory.sellId}&tag=deleteThisSell&cpage=${cpage}">删除</a>&nbsp; --%>
									<s:if test="true==#sellStory.bedit">
										<a
											href="sellAction!getOneSell.action?id=${sellStory.sellId}&tag=updateThisSell&status=mingxi">明细</a>&nbsp;/
										<a
											href="sellAction!getOneSell.action?id=${sellStory.sellId}&tag=updateThisSell">修改</a>&nbsp;
										<s:if
											test="#sellStory.sellWarehouse!='成品库'&&#sellStory.sellWarehouse!='待检库'&&#sellStory.sellWarehouse!='在制品'&&(#sellStory.style=='领料出库'||#sellStory.style=='研发耗用'||#sellStory.style=='售后出库')">
											<s:if test="#sellStory.sellCount-#sellStory.tksellCount>0">
												<a
													href="sellAction!toTuikuSell.action?id=${sellStory.sellId}">/退料</a>&nbsp;
											</s:if>
										</s:if>
										<s:if test="#sellStory.sellWarehouse=='成品库' 
										&&(#sellStory.tksellCount == null || #sellStory.tksellCount<#sellStory.sellCount)">
											/<a href="sellAction!getOneSell.action?id=${sellStory.sellId}&tag=updateThisSell&status=tuihuo">退货申请</a>
										</s:if>
									</s:if>
									<s:if test="#sellStory.isdel == true">
										/<a onClick="return confirm('确定要删除该条记录吗？')"
												href="sellAction!getOneSell.action?id=${sellStory.sellId}&tag=deleteThisSell&cpage=${cpage}">删除</a>&nbsp;
									</s:if>
								</td>

								</tr>
							</s:iterator>
							<tr>
								<td colspan="32" align="right">
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
									对不起，没有查到相关的出库信息
								</td>
							</tr>
						</s:else>
						<tr>
							<td colspan="32">
								<input type="submit" style="width: 80px; height: 50px;"
									value="打印" />
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
function vali() {
	var nums = document.getElementsByName("selected");
	for ( var i = 0; i < nums.length; i++) {
		if (nums[i].checked) {
			return true;
		}
	}
	alert("请选择需要打印的记录！谢谢");
	return false;
}
function exportExcel(objForm,tag) {
	objForm.action = "sellAction!exportEXCEL.action?message=sellDetail";
	objForm.submit();
	objForm.action = "sellAction!findSellByCondition.action";

}

function checkAllBoxs() {
	var checkAll = document.getElementById("checkAll");
	var checkboxs = $(".toCheckSingle");
	if (checkAll.checked == true) {
		for ( var i = 0; i < checkboxs.length; i++) {
			checkboxs[i].checked = true;
		}
	} else {
		for ( var i = 0; i < checkboxs.length; i++) {
			checkboxs[i].checked = false;
		}
	}

}

function checkSingle() {
	var checkAll = document.getElementById("checkAll");
	var checkboxs = $(".toCheckSingle");
	var count = 0;
	for ( var i = 0; i < checkboxs.length; i++) {
		if (checkboxs[i].checked == false) {
			checkAll.checked = false;
			return;
		} else {
			count++;
		}
	}
	if (count == checkboxs.length) {
		checkAll.checked = true;
	}
}
function addSelect() {
	$
			.ajax( {
				type : "POST",
				url : "GoodsStoreAction!getViewAuth.action",
				data : {},
				dataType : "json",
				success : function(msg) {
					$('#whView').empty();
					$('#whView').append(
									"<option value='${sell.sellWarehouse}'>${sell.sellWarehouse}</option>");
					$('#whView').append(
									"<option value=''></option>");
					if (msg.success) {
						for (k in msg.data) {
							$('#whView').append(
									"<option>" + msg.data[k] + "</option>");
						}
					} else {
						alert(msg.message);
					}
				}
			});

}
function changvalue0(obj) {
	if (obj != null && obj.value != "") {
		$
				.ajax( {
					type : "POST",
					url : "WarehouseAreaAction_findwaListByNO.action",
					data : {
						wareHouseName : obj.value
					},
					dataType : "json",
					success : function(data) {
						if (data != null) {
							$("#goodHouseName").empty();
							$(data).each(
									function() {
										$("#goodHouseName").append(
												'<option value='
														+ this.goodHouseName
														+ '>'
														+ this.goodHouseName
														+ '</option>');
									});
							var duoxuanselect = $(".duoxuaselect_div");
							if (duoxuanselect[0] != null) {
								$(duoxuanselect[0]).remove();
							}
							duoxuaSelect("goodHouseName",
									'${goodsStore.goodHouseName}');
						}
					}
				});
	}
}

addSelect();


var mfzTree;
var addzTree;
var delzTree;
var updatezTree;

var id;
var pId;
var name;
var setting = {
	view : {
		dblClickExpand : false
	},
	data : {
		simpleData : {
			enable : true
		}
	},
	callback : {
		beforeClick : beforeClick,
		onClick : onClick
	}
};
//读取树形数据
$(document).ready(function() {
	parent.mfzTree();
	addSelect();

});
var zNodes = [];
parent.mfzTree = function() {
	$.ajax( {
		url : 'CategoryAction_findcyListByrootId.action',
		type : 'post',
		data : {
			status : '物料类别'
		},
		dataType : 'json',
		cache : true,
		success : function(doc) {
			$(doc).each(function() {
				zNodes.push( {
					id : $(this).attr('id'),
					pId : $(this).attr('fatherId'),
					name : $(this).attr('name'),
					target : "main",
					click : false
				});

			});
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.expandAll(true);
		},
		error : function() {
			alert("服务器异常!");
		}
	});
};
function beforeClick(treeId, treeNode) {
	var check = (treeNode && !treeNode.isParent);

	return true;
}

function onClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo"), nodes = zTree
			.getSelectedNodes(), v = "";
	nodes.sort(function compare(a, b) {
		return a.id - b.id;
	});
	for ( var i = 0, l = nodes.length; i < l; i++) {
		v = nodes[i].name;
	}
	//if (v.length > 0 ) v = v.substring(0, v.length-1); 
	cityObj = $("#wgType").val(v);

}

function showMenu() {
	var cityObj = $("#wgType");
	var cityOffset = $("#wgType").offset();
	$("#menuContent").css( {
		left : cityOffset.left + "px",
		top : cityOffset.top + cityObj.outerHeight() + "px"
	}).slideDown("fast");

	$("body").bind("mousedown", onBodyDown);
}
function hideMenu() {
	$("#menuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
	if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(
			event.target).parents("#menuContent").length > 0)) {
		hideMenu();
	}
}
</script>
	</body>
</html>