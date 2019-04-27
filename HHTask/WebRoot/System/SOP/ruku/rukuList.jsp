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
		<script type="text/javascript">
function deleteUser(id) {
	if (!confirm('确定将此条记录删除?')) {
		return;
	}
	$.ajax( {
		type : "POST",
		url : "GoodsStoreAction!delete.action",
		data : "goodsStore.goodsStoreId=" + id,
		dataType : "json",
		success : function(msg) {
			alert(msg.message);
			if (msg.success) {
				location.reload();
			}
		}
	});
}

$(function() {
	createDept('sqUsersdept');
<%--	$("#table1").freezeHeader();--%>
	function addSelect() {
		$.ajax( {
			type : "POST",
			url : "GoodsStoreAction!getViewAuth.action",
			data : {},
			dataType : "json",
			success : function(msg) {
				if (msg.success) {
					for (k in msg.data) {
						$('#whView').append(
								"<option>" + msg.data[k] + "</option>");
					}
				} else {
					alert(msg.message);
				}
				duoxuaSelect("whView",'${goodsStore.goodsStoreWarehouse}');
			}
		});

	}

	addSelect();
	$('#exportBtn').bind('click', function() {
		var act = $('#ma')[0].action;
		$('#ma')[0].action = 'GoodsStoreAction!export.action';
		//$('#ma')[0].action = 'GoodsStoreAction!exportByPoi.action';
		$('#ma')[0].submit();
		$('#ma')[0].action = act;
	});
})
</script>
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

/* 带复选框的下拉框 */
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
				<h2>
					入库历史记录管理
				</h2>
				<font size="5" color="red" id="font"></font>
				<form id="ma" action="GoodsStoreAction!rukuList.action"
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
								<input type="text" id="goodsStoreMarkId"
									name="goodsStore.goodsStoreMarkId"
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
								物料类别:
							</th>
							<td>
								<div class="zTreeDemoBackground left">
									<ul class="list">
										<li class="title">
											<input id="wgType" type="text" value="${goodsStore.wgType}"
												style="width: 120px;" name="goodsStore.wgType" />
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
								<select name="goodsStore.kgliao" style="width: 155px;">
									<option value="${goodsStore.kgliao}">
										${goodsStore.kgliao}
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
								<input type="text" name="goodsStore.banBenNumber"
									value="${goodsStore.banBenNumber }" />
							</td>
						</tr>
						<tr>
							<th>
								库别:
							</th>
							<td>
								<select id="whView" name="goodsStore.goodsStoreWarehouse"
									style="width: 155px;" onchange="changvalue(this)">
									<option>
										${goodsStore.goodsStoreWarehouse}
									</option>
									<option></option>
								</select>
							</td>
							<th>
								仓区:
							</th>
							<td>
								<select id="goodHouseName" name="goodsStore.goodHouseName"
									style="width: 155px;">
									<option>
										${goodsStore.goodHouseName}
									</option>
									<option></option>
								</select>
								<%--								<select id="goodHouseName" name="goodsStore.goodHouseName"--%>
								<%--									style="width: 155px;">--%>
								<%--									<option>--%>
								<%--										${goodsStore.goodHouseName}--%>
								<%--									</option>--%>
								<%--									<option></option>--%>
								<%--									<s:iterator value="goodHouseList" id="pageLists"--%>
								<%--										status="pageStatus">--%>
								<%--										<option value="${pageLists.goodHouseName}">--%>
								<%--											${pageLists.goodHouseName}--%>
								<%--										</option>--%>
								<%--									</s:iterator>--%>
								<%--								</select>--%>
							</td>
							<th>
								库位:
							</th>
							<td>
								<input type="text" name="goodsStore.goodsStorePosition"
									value="${goodsStore.goodsStorePosition }" />
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
							<th>
								客户:
							</th>
							<td>
								<input type="text" name="goodsStore.goodsStoreCompanyName"
									value="${goodsStore.goodsStoreCompanyName }" />
							</td>
							<th>
								供应商:
							</th>
							<td>
								<input name="goodsStore.goodsStoreSupplier"
									value="${goodsStore.goodsStoreSupplier}" />
							</td>
							<th>
								入库申请部门:
							</th>
							<td>
								<select name="goodsStore.sqUsersdept" id="sqUsersdept">
									<option value="${goodsStore.sqUsersdept}">
										${goodsStore.sqUsersdept}
									</option>
									<option></option>
								</select>
							</td>
						</tr>
						<tr>
							<th>
								打印单号:
							</th>
							<td>
								<input type="text" name="goodsStore.printNumber"
									value="${goodsStore.printNumber}" />
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
								锁定单号：
							</th>
							<td>
								<input type="text" name="goodsStore.suodingdanhao"
									value="${goodsStore.suodingdanhao}" />

							</td>
							<th>
								送货单号：
							</th>
							<td>
								<input type="text" name="goodsStore.goodsStoreSendId"
									value="${goodsStore.goodsStoreSendId}" />

							</td>
							<th>
								入库类型
							</th>
							<td>
								<SELECT name="goodsStore.style">
									<option value="${goodsStore.style}">
										${goodsStore.style}
									</option>
									<option value=""></option>
									<option value="采购入库">
										采购入库
									</option>
									<option value="调仓入库">
										调仓入库
									</option>
									<option value="设变退库">
										设变退库
									</option>
									<option value="生产退料入库">
										生产退料入库
									</option>
									<option value="在制品入库">
										在制品入库
									</option>
									<option value="在制品入库(委外接收)">
										在制品入库(委外接收)
									</option>
									<option value="外协退料入库">
										外协退料入库
									</option>
									<option value="刷卡入库">
										刷卡入库
									</option>
									<option value="冲销转库">
										冲销转库
									</option>
									<option value="正常（成品）">
										正常（成品）
									</option>
									<option value="半成品转库">
										半成品转库
									</option>
									<option value="合格品入库">
										合格品入库
									</option>
									<option value="不合格品入库">
										不合格品入库
									</option>
								</SELECT>
							</td>
						</tr>
						<tr>
							<th >
								状态
							</th>
							<td>
								<select name="goodsStore.status">
									<option value=""></option>
									<option value="${goodsStore.status}">goodsStore.status</option>
									<option value="待入库">待入库</option>
									<option value="入库">入库</option>
								</select>
							</td>
							<td colspan="20"></td>
						</tr>
						<tr>
							<th colspan="8">
								<input type="hidden" name="pagestatus" value="${pagestatus}" />
								<input type="submit" class="input" value="查找" />
								<input id="exportBtn" type="button" class="input" value="导出" data="downData" onclick="todisabledone(this)"/>
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
								<th align="left">
									<input type="checkbox" onclick="chageAllCheck(this)">
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
								<s:if test='pagestatus == "price"'>
									<th align="center">
										送货单号
									</th>
									<th align="center">
										采购订单号
									</th>
									<th align="center">
										不含税单价
									</th>
									<th align="center">
										税率
									</th>
									<th align="center">
										含税价格
									</th>
									<th align="center">
										总额
									</th>
								</s:if>
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
									<br />
									总数量${zongCount}
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
									入库时间
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
								<%--
							<th align="center">
								申请单号
							</th>
							<th align="center">
								炉批号
							</th>
							--%>
								<th align="center">
									操作
								</th>
							</tr>
						</thead>
						<tbody>
							<s:if test="tag!='showRuku'">
								<%--							<tr>--%>
								<%--								<th colspan="30" bgcolor="#FFB6C1"--%>
								<%--									style="color: red；font-weight :                                                                                               bold;">--%>
								<%--									入库未打印记录--%>
								<%--								</th>--%>
								<%--							</tr>--%>
									<s:iterator value="goodsStoreList" status="see" id="gs">
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
												onclick="chageNum(this)" value="${gs.goodsStoreId}" />
										</td>
										<td>
											${see.index+1}
										</td>
										<td align="left" style="color: gray;">
											${gs.goodsStoreWarehouse}
										</td>
										<td align="left" style="color: gray;">
											${gs.goodHouseName}
										</td>
										<td align="left" style="color: gray;">
											${gs.goodsStorePosition}
										</td>
										<td align="left">
											${gs.goodsStoreMarkId}
											<s:if test="#gs.processNo!=null">（<font color="red">${gs.processNo}</font>）</s:if>
										</td>
										<td align="left">
											${gs.ywmarkId}
										</td>
										<td align="left">
											${gs.waiorderId}
										</td>
										<td align="left">
											${gs.neiorderId}
										</td>
										<s:if test='pagestatus == "price"'>
											<td
												style="max-width: 50px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
												<font size="1">${gs.goodsStoreSendId}</font>
												<ul class="qs_ul">
													<li>
														${gs.goodsStoreSendId}
													</li>
												</ul>
											</td>
											<td
												style="max-width: 50px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
												<font size="1">${gs.neiorderId}</font>
												<ul class="qs_ul">
													<li>
														${gs.n}
													</li>
												</ul>
											</td>
											<td align="left">
												${gs.goodsStorePrice}
											</td>
											<td align="left">
												${gs.taxprice}
											</td>
											<td align="left">
												${gs.hsPrice}
											</td>
											<td align="left">
												${gs.money}
											</td>
										</s:if>
										<td align="left">
											${gs.banBenNumber}
										</td>
										<td align="left">
											${gs.kgliao}
										</td>
										<td>
											${gs.goodsStoreLot}
										</td>
										<td align="left">
											${gs.wgType}
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
										<td
											style="max-width: 50px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
											<font size="1">${gs.goodsStoreFormat}</font>
											<ul class="qs_ul">
												<li>
													${gs.goodsStoreFormat}
												</li>
											</ul>
										</td>
										<td align="right">
											${gs.goodsStoreCount}
										</td>
										<td>
											${gs.goodsStoreUnit}
										</td>
										<td align="right">
											${gs.goodsStoreZhishu}
										</td>
										<td>
											${gs.goodsStoreZHUnit}
										</td>
										<td
											style="max-width: 50px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
											<font size="1">${gs.goodsStoreCompanyName}</font>
											<ul class="qs_ul">
												<li>
													${gs.goodsStoreCompanyName}
												</li>
											</ul>
										</td>
										<td
											style="max-width: 50px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
											<font size="1">${gs.goodsStoreSupplier}</font>
											<ul class="qs_ul">
												<li>
													${gs.goodsStoreSupplier}
												</li>
											</ul>
										</td>
										<td>
											${gs.style}
										</td>
										<td align="right">
											<s:if
												test="#gs_history.goodsStoreTime!=null&&#gs_history.goodsStoreTime!=''">
												${gs.goodsStoreTime}
											</s:if>
											<s:else>
												${gs.goodsStoreDate}
											</s:else>
										</td>
										<td>
											${gs.status}
										</td>
										<td>
											${gs.sqUsersName}
										</td>
										<td>
											${gs.sqUsersdept}
										</td>
										<td>
											${gs.printNumber}
										</td>
										<td></td>
									</s:iterator>
							</s:if>
							<tr>
								<th colspan="40" bgcolor="#9BCD9B">
									入库历史记录
								</th>
							</tr>
							<s:iterator value="list" status="see" id="gs_history">
								<s:if test="#see.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')"
										ondblclick="showdj(${gs_history.goodsStoreId})">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'')"
										ondblclick="showdj(${gs_history.goodsStoreId})">
								</s:else>
								<td>
									<input type="checkbox" name="selected" onclick="chageNum(this)"
										value="${gs_history.goodsStoreId}" />
								</td>
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
								<td
									style="max-width: 150px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
									<font size="1">${gs_history.goodsStoreGoodsName}</font>
									<ul class="qs_ul">
										<li>
											${gs_history.goodsStoreGoodsName}
										</li>
									</ul>
								</td>

								<td align="right">
									<s:if test="#gs_history.goodsStoreWarehouse == '不合格品库'">
										<font color="red">${gs_history.goodsStoreCount}</font>
									</s:if>
									<s:else>
										${gs_history.goodsStoreCount}
									</s:else>
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
									<s:if
										test="#gs_history.goodsStoreTime!=null&&#gs_history.goodsStoreTime!=''">
												${gs_history.goodsStoreTime}
											</s:if>
									<s:else>
												${gs_history.goodsStoreDate}
											</s:else>
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
									<s:if test="#gs_history.bedit == true">
										<a
											href="GoodsStoreAction!rukuEdit.action?goodsStore.goodsStoreId=${goodsStoreId}">修改</a>
									</s:if>
									<s:if test="#gs_history.isdel == true">
										<a href="javascript:void(0)"
											onclick="deleteUser(${goodsStoreId})">删除</a>
									</s:if>
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
function vali() {
	var selectList = document.getElementsByName("selected");
	for ( var i = 0; i < selectList.length; i++) {
		if (selectList[i].checked) {
			return true;
		}
	}
	alert("请选择需要打印的记录！谢谢");
	return false;
}
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
function changvalue(obj) {
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
						if (data != null && data.length > 0) {
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
							if (duoxuanselect[1] != null) {
								$(duoxuanselect[1]).remove();
							}
							duoxuaSelect("goodHouseName",
									'${goodsStore.goodHouseName}');
						}
					}
				});
	}
}

function showdj(id) {
	window.location.href = "PrintedOutAction_findPoorandPoByEntiyId.action?id="
			+ id + "&entiyName=GoodsStore";
}
$(function() {

})
</script>
	</body>
</html>
