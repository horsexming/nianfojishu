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
								批次:
							</th>
							<td>
								<input type="text" name="goodsStore.goodsStoreLot"
									value="${goodsStore.goodsStoreLot }" />
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
						</tr>
						<tr>
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
						</tr>
						<tr>
							<th colspan="8">
								<input type="hidden" name="pagestatus" value="${pagestatus}" />
								<input type="submit" class="input" value="查找" />
								<input id="exportBtn" type="button" class="input" value="导出" data="downData" onclick="todisabledone(this)" />
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
									件号
								</th>
								<th align="center">
									批次
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
								<th align="center">
									版本
								</th>
								<th align="center">
									供料属性
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
									供应商
								</th>
								<th align="center">
									入库类型
								</th>
								<th align="center">
									入库时间
								</th>
								<th align="center">
									入库人
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
									入库历史记录
								</th>
							</tr>
							<s:iterator value="list" status="see" id="gs_history">
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
									<input type="checkbox" name="selected" onclick="chageNum(this)"
										value="${gs_history.goodsStoreId}" />
								</td>
								<td>
									${see.index+1}
								</td>
								<td align="left" style="color: gray;">
									${gs_history.goodsStoreWarehouse}
								</td>
								<td>
									${gs_history.goodHouseName}
								</td>
								<td align="left">
									${gs_history.goodsStoreMarkId}
									<s:if test="#gs_history.processNo!=null">（<font
											color="red">${gs_history.processNo}</font>）</s:if>
								</td>
								<td>
									${gs_history.goodsStoreLot}
								</td>
								<td align="left"
									id="goodsStorePriceTd_${gs_history.goodsStoreId}">
									${gs_history.goodsStorePrice}
									<input type="hidden" value="${gs_history.goodsStorePrice}"
										id="goodsStorePrice_${gs_history.goodsStoreId}" />
								</td>
								<td align="left" id="taxTd_${gs_history.goodsStoreId}">
									${gs_history.taxprice}
									<input type="hidden" value="${gs_history.taxprice}"
										id="taxprice_${gs_history.goodsStoreId}" />
								</td>
								<td align="left" id="hsPriceTd_${gs_history.goodsStoreId}">
									${gs_history.hsPrice}
									<input type="hidden" style="width: 75px; display: none;"
										value="${gs_history.hsPrice}"
										id="hsPrice_${gs_history.goodsStoreId}" />
								</td>
								<td align="left">
									${gs_history.money}
								</td>
								<td align="left">
									${gs_history.banBenNumber}
								</td>
								<td align="left">
									${gs_history.kgliao}
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
									style="max-width: 50px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
									<font size="1">${gs_history.goodsStoreFormat}</font>
									<ul class="qs_ul">
										<li>
											${gs_history.goodsStoreFormat}
										</li>
									</ul>
								</td>
								<td>
									${gs_history.goodsStoreCount}
								</td>
								<td>
									${gs_history.goodsStoreUnit}
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
								<td>
									${gs_history.goodsStorePlanner}
								</td>
								<td>
									${gs_history.printNumber}
								</td>
								<td>
									<input type="button" value="修改单价"
										onclick="toupdatePrice('${gs_history.goodsStoreId}')"
										id="but_${gs_history.goodsStoreId}" />
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
function toupdatePrice(num) {
	var goodsStorePrice = $("#goodsStorePrice_" + num).val();
	var hsPrice = $("#hsPrice_" + num).val();
	var taxPrice = $("#taxPrice_" + num).val();
	if (taxPrice == null || taxPrice == '') {
		taxPrice = 16;
	}
	$("#goodsStorePriceTd_" + num).html(
			'<input type="text" value="' + goodsStorePrice
					+ '" id="goodsStorePrice_' + num + '"'
					+ '  style="width: 75px;" onchange="changPrice(this)" >');
	$("#hsPriceTd_" + num).html(
			'<input type="text" value="' + goodsStorePrice
					+ '" id="hsPrice_' + num + '"'
					+ '  style="width: 75px;" onchange="changPrice(this)" >');
	$("#taxTd_" + num).html(
			'<input type="text" value="' + taxPrice + '" id="taxPrice_' + num
					+ '"  style="width: 75px;" onchange="numyanzheng(this,&apos;zhengshu&apos;)">');
	$("#but_" + num).attr('value', '提交');
	$("#but_" + num).attr('onclick', 'updatePrice(' + num + ')');
}
function changPrice(obj) {
	var str = obj.id;
	var value = obj.value;
	if (value != null && value != '') {
		value = value*1;
		var strs = str.split("_");
		var name = strs[0];
		var num = strs[1];
		var tax =  $("#taxPrice_"+num).val();
		if (name == "hsPrice") {
			var bhsPrice = value/(1+(tax/100));
			bhsPrice =	bhsPrice.toFixed(2);
			$("#goodsStorePrice_" + num).val(bhsPrice);
		}else if(name == "goodsStorePrice"){
			var hsPrice = value*(1+(tax/100));
			hsPrice =hsPrice.toFixed(2);
			$("#hsPrice_" + num).val(hsPrice);
		}
	}

}
function updatePrice(num) {
	var goodsStorePrice = $("#goodsStorePrice_" + num).val();
	var hsPrice = $("#hsPrice_" + num).val();
	var taxPrice = $("#taxPrice_"+num).val();
	if(goodsStorePrice==null || goodsStorePrice == ''){
		$("#goodsStorePrice_" + num).focus();
		alert('请填写不含税单价');
		return false;
	}else if(hsPrice==null || hsPrice == ''){
		$("#hsPrice_" + num).focus();
		alert('请填写含税单价');
		return false;
	}else if(taxPrice==null || taxPrice == ''){
		$("#taxPrice_"+num).focus();
		alert('请填写税率');
		return false;
	}
	$.ajax( {
		type : "POST",
		url : "GoodsStoreAction!updatePrice.action",
		dataType : "json",
		data:{
			id:num*1,
			hsPrice:hsPrice,
			bhsPrice:goodsStorePrice,
			taxPrice:taxPrice
		},
		success : function(data) {
			if(data){
				$("#goodsStorePriceTd_" + num).html(goodsStorePrice+
					'<input type="hidden" value="'+goodsStorePrice+'" id="goodsStorePrice_'+num+'">');
				$("#hsPriceTd_" + num).html(hsPrice+
					'<input type="hidden" value="'+hsPrice+'" id="hsPrice_'+num+'">');
				$("#taxTd_" + num).html(taxPrice+
					'<input type="hidden" value="'+taxPrice+'" id="taxPrice_'+num+'">');
				$("#but_" + num).attr('value', '修改单价');
				$("#but_" + num).attr('onclick', 'toupdatePrice(' + num + ')');
				alert('修改成功!~')
			}else{
				alert('修改失败!~')
			}
		}
	})
}
</script>
	</body>
</html>
