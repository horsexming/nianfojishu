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
		<script type="text/javascript">
$(function() {
	getUnit("danwei");
	getUnit("ZHdanwei");
	$('#submitBtn')
			.bind(
					'click',
					function() {
						document.getElementById("submitBtn").disabled = true;
						var goodsStoreMarkId = $("#goodsStoreMarkId").val();
						var goodsStoreLot = $("#goodsStoreLot").val();
						var goodsStoreCount = $("#goodsStoreCount").val();
						var goodsStorePosition = $("#goodsStorePosition").val();
						var goodsStoreZhishu = $("#goodsStoreZhishu").val();
						var goodsStoreDate = $("#goodsStoreDate").val();
						var warehouse = $("#warehouse").val();
						var neiorderId = $("#neiorderId").val();
						var waiorderId = $("#waiorderId").val();
						var style = $("#style").val();
						var cangqu = $("#goodHouseName").val();
						if(cangqu==null || cangqu==""){
							alert("仓区不能为空");
							document.getElementById("submitBtn").disabled = false;
							return false;
						}else if (goodsStoreMarkId == null || goodsStoreMarkId == "") {
							alert("件号不能为空");
							document.getElementById("submitBtn").disabled = false;
							return false;
						} else if (goodsStoreLot == null || goodsStoreLot == "") {
							alert("批次号不能为空");
							document.getElementById("submitBtn").disabled = false;
							return false;
						} else if (goodsStorePosition == null
								&& goodsStorePosition == "") {
							alert("库位不能为空");
							document.getElementById("submitBtn").disabled = false;
							return false;
						} else if (goodsStoreDate == null
								|| goodsStoreDate == "") {
							alert("入库日期不能为空");
							document.getElementById("submitBtn").disabled = false;
							return false;
						} else if (warehouse == "原材料库") {
							if (goodsStoreCount == null
									|| goodsStoreCount == "") {
								alert("数量不能为空");
								document.getElementById("submitBtn").disabled = false;
								return false;
							} else if (goodsStoreZhishu == null
									|| goodsStoreZhishu == "") {
								alert("转换数量不能为空");
								document.getElementById("submitBtn").disabled = false;
								return false;
							} else {
								$("#showmess").show();
								$
										.ajax( {
											type : "POST",
											url : "GoodsStoreAction!addSdrk.action",
											data : $('#myform').serialize(),
											dataType : "json",
											success : function(msg) {
												alert(msg.message);
												if (msg.success) {
													location.href = "GoodsStoreAction!rukuList.action";
												} else {
													document
															.getElementById("submitBtn").disabled = false;
												}
											}
										});
							}
						} else if (warehouse == "成品库" && style != '返修入库'
								&& style != '退货入库' && style != '外委入库') {
							if (neiorderId == "") {
								alert("请选择内部订单号");
								document.getElementById("submitBtn").disabled = false;
								return false;
							} else if (waiorderId == "") {
								alert("请选择外部订单号");
								document.getElementById("submitBtn").disabled = false;
								return false;
							}
							$("#showmess").show();
							$
									.ajax( {
										type : "POST",
										url : "GoodsStoreAction!addSdrk.action",
										data : $('#myform').serialize(),
										dataType : "json",
										success : function(msg) {
											alert(msg.message);
											if (msg.success) {
												location.href = "GoodsStoreAction!rukuList.action";
											} else {
												document
														.getElementById("submitBtn").disabled = false;
											}
										}
									});
						} else {
							$("#showmess").show();
							$
									.ajax( {
										type : "POST",
										url : "GoodsStoreAction!addSdrk.action",
										data : $('#myform').serialize(),
										dataType : "json",
										success : function(msg) {
											alert(msg.message);
											if (msg.success) {
												location.href = "GoodsStoreAction!rukuList.action";
											} else {
												document
														.getElementById("submitBtn").disabled = false;
											}
										}
									});
						}
					});
});
</script>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng">
			<div align="center">
				<form id="myform" method="post" action="">
					<table class="table">
						<tr id="yclwgj">
							<th align="right">
								所属仓库
								<br />
								Owned warehouse
							</th>
							<td>
								<select name="goodsStore.goodsStoreWarehouse" id="warehouse"
									onchange="changeCss()">

									<s:iterator value="l" id="it" status="st">
										<option>
											${it}
										</option>
									</s:iterator>
								</select>
								<font color="red">*</font>
							</td>
							<th align="right">
								<font id="yclwgjfont"></font>
							</th>
							<td colspan="6" id="mytd">
								<div id="showAll"
									style="background-color: #ffffff; position: absolute; visibility: hidden; z-index: 1;">
								</div>
								<input type="text" id="shortname" onkeyup="getAllNames()"
									style="height: 20px" onFocus="init()" onBlur="hidediv()"
									name="markId" />
								<font color="red" id="shortname_font">*</font>
							</td>
						</tr>
						<tr>
							<th align="right">
								件号
								<br />
								Part No.
							</th>
							<td>
								<input id="goodsStoreMarkId" name="goodsStore.goodsStoreMarkId"
									onchange="getorder(this)" />
								<font color="red">*</font>

							</td>
							<th align="right">
								名称
								<br />
								Name
							</th>
							<td>
								<input name="goodsStore.goodsStoreGoodsName" id="goodsName"
									readonly="readonly" />
								<font color="red">*</font>

							</td>
							<th align="right">
								物料类别
							</th>
							<td>
								<input name="goodsStore.wgType" id="wgType"
									readonly="readonly" />
							</td>
							<th align="right">
								规格
								<br />
								Specifications
							</th>
							<td>
								<input name="goodsStore.goodsStoreFormat" id="storeFormat" />

							</td>
							
						</tr>
						<tr>
							<th align="right">
								数量
								<br />
								Quantity
							</th>
							<td>
								<input id="goodsStoreCount" name="goodsStore.goodsStoreCount"
									onchange=" numyanzhen(this)" style="width: 115px;" />
								<span id="bili"> </span>


							</td>
							<th align="right">
								单位
								<br />
								Unit
							</th>
							<td>
								<select name="goodsStore.goodsStoreUnit" id="danwei" onclick="notUpdate(this)">
								</select>
								<font color="red">*</font>

							</td>
							<th align="right">
								仓区
								<br />
								Owned area
							</th>
							<td id="goodHouseName_td">
							<SELECT name="goodsStore.goodHouseName" id="goodHouseName"
									onchange="getkuwei(this)">
<%--							<SELECT name="goodsStore.goodHouseName" id="goodHouseName">--%>
									<option value=""></option>
									<s:iterator value="goodHouseList" id="pageLists"
										status="pageStatus">
										<option value="${pageLists.goodHouseName}">
											${pageLists.goodHouseName}
										</option>
									</s:iterator>
								</SELECT>
								<font color="red">*</font>
							</td>
							<th align="right">
								库位
								<br />
								Location
							</th>
							<td id="goodsStorePosition_td">
<%--								<input type="text" name="goodsStore.goodsStorePosition" id="goodsStorePosition"/>--%>
<%--								<select name="goodsStore.goodsStorePosition" id="goodsStorePosition" >--%>
<%--									<option value=""></option>--%>
<%--								</select>--%>
								<select name="goodsStore.goodsStorePosition"
									style="width: 200px;" id="goodsStorePosition">
									<option value=""></option>
								</select>
								<font color="red">*</font>

							</td>
						</tr>
						<tr>
							<th align="right">
								转换数量
								<br />
								Count
							</th>
							<td>
								<input name="goodsStore.goodsStoreZhishu" id="goodsStoreZhishu"
									onchange=" numyanzhen(this)" style="width: 115px;" />
								<span id="sp_bili"> </span>
							</td>
							<th align="right">
								转换单位
								<br />
								Unit
							</th>
							<td>
								<select name="goodsStore.goodsStoreZHUnit" id="ZHdanwei">
								</select>
								<font color="red" id="goodsStoreZHUnitfont">*</font>

							</td>
							<th align="right">
								入库类型
								<br />
								Storage type
							</th>
							<td>
								<select name="goodsStore.style" id="style">
									<option>
										正常（成品）
									</option>
									<option>
										正常（原材料）
									</option>
									<option>
										返修入库
									</option>
									<option>
										退货入库
									</option>
									<option>
										试制
									</option>
									<option>
										中间件
									</option>
									<option>
										工序加工
									</option>
									<option>
										PEBS系统定制
									</option>
									<option>
										外委入库
									</option>
								</select>
								<font color="red">*</font>

							</td>
							<th align="right">
								批次
								<br />
								Batch
							</th>
							<td>
								<input name="goodsStore.goodsStoreLot" id="goodsStoreLot" />
								<font color="red">*</font>

							</td>

						</tr>
						<tr>
							<th align="right">
								入库日期
								<br />
								Date
							</th>
							<td>
								<input id="goodsStoreDate" name="goodsStore.goodsStoreDate"
									onClick="WdatePicker()" class="Wdate" />
								<font color="red">*</font>

							</td>
							<th align="right">
								上期质检时间
								<br />
								Lasttime
							</th>
							<td>
								<input type="text" id="goodsStorelasttime" class="Wdate"
									name="goodsStore.goodsStorelasttime"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">
							</td>
							<th align="right">
								质检周期
								<br />
								Round
							</th>
							<td>
								<input name="goodsStore.goodsStoreround" id="goodsStoreround"
									style="width: 125px;" readonly="readonly" />
								(天)
							</td>
							<th align="right">
								供应商
								<br />
								Supplier
							</th>
							<td>
								<input name="goodsStore.goodsStoreSupplier" />
							</td>
						</tr>
						<tr>
							<th align="right">
								总成件号
								<br />
								Assembly Number
							</th>
							<td>
								<input name="goodsStore.goodsStoreProMarkId" />
							</td>
							<th align="right">
								经办人
								<br />
								Attn.
							</th>
							<td>
								<input name="goodsStore.goodsStoreCharger" />
							</td>
							<th align="right">
								负责人
								<br />
								Leader
							</th>
							<td>
								<input name="goodsStore.goodsStorePerson" />
							</td>
							<th align="right" id="orderId_td1">
								订单号
								<br />
								Order number
							</th>
							<td id="orderId_td2">
								<input name="goodsStore.orderId" />
							</td>
						</tr>
						<tr>
							<th align="right">
								版本
								<br />
								version
							</th>
							<td>
								<input id="banBenNumber" name="goodsStore.banBenNumber" />
							</td>
							<th align="right">
								送货单号
								<br />
								No delivery note
							</th>
							<td>
								<input name="goodsStore.goodsStoreSendId" />
							</td>
							<th align="right">
								计划员
								<br />
								Planner
							</th>
							<td>
								<input name="goodsStore.goodsStorePlanner" />
							</td>
							<th align="right">
								申请单编号
								<br />
								Requisition Number
							</th>
							<td>
								<input name="goodsStore.goodsStoreNumber" />
							</td>
						</tr>
						<tr>
							<th align="right">
								炉批号
								<br />
								Furnace batch
							</th>
							<td>
								<input name="goodsStore.goodsStoreLuId" />
							</td>
							<th align="right">
								期初数量
								<br />
								Opening Number
							</th>
							<td>
								<input name="goodsStore.beginning_num" />
							</td>
							<th align="right">
								价格
								<br />
								Price
							</th>
							<td>
								<input name="goodsStore.goodsStorePrice" />
							</td>

							<th align="right">
								工艺卡号
								<br />
								Process card
							</th>
							<td>
								<input name="goodsStore.goodsStoreArtsCard" />
							</td>

						</tr>
						<tr>
							<th align="right">
								用途
								<br />
								Uses
							</th>
							<td>
								<input name="goodsStore.goodsStoreUseful" />
							</td>
							<th align="right">
								商品备注
								<br />
								Commodity Remarks
							</th>
							<td>
								<input name="goodsStore.goodsStoreGoodsMore" />
							</td>
							<th align="right">
								图号
							</th>
							<td>
								<input type="text" name="goodsStore.tuhao" readonly="readonly"
									id="tuhao" />
							</td>
							<th align="right">
								供货属性
							</th>
							<td>
										<select name="goodsStore.kgliao" id="kgliao">
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
						</tr>
						<tr>
							<th align="right">
								客户
								<br />
								Customers
							</th>
							<td>
								<input name="goodsStore.goodsStoreCompanyName" />
							</td>
							<th align="right">
								锁定单号
								
							</th>
							<td>
								<input name="goodsStore.suodingdanhao" />
							</td>
							<td colspan="10">
							</td>
						</tr>
						<tr>
							<th colspan="8">
								<input id="submitBtn" type="button" value="提交(submit)"
									class="input" />
								<div id="showmess" style="display: none;">
									正在入库激活中,请耐心等待中......
								</div>
							</th>
						</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
$(document).ready(function() {
	changeCss();
})
function checkAndSubmit() {
	document.getElementById("submitBtn").disabled = true;
	///var myForm = document.getElementById("myform");
	//myForm.submit();
}
function changeCss() {
	var warehouse = $("#warehouse").val();
if (warehouse == "原材料库") {
		$("#goodsStoreMarkId").attr("readonly", "readonly");
		$("#storeFormat").attr("readonly", "readonly");
		$("#danwei").attr("readonly", "readonly");
		$("#yclwgj").show();
		$("#yclwgjfont").html("原材料查询(牌号或规格)");
		$("#yclwgjfont").show();
		$("#shortname").show();
		$("#shortname_font").show();
<%--		$("#bili")--%>
<%--				.html(--%>
<%--						'<font color=red>*</font><input type="button" onclick=getbili("goodsStoreCount") value="计算"/> ');--%>
<%--		$("#sp_bili")--%>
<%--				.html(--%>
<%--						'<font color=red>*</font><input type="button" onclick=getbili("goodsStoreZhishu") value="计算"/> ');--%>
		$("#goodsStoreZHUnitfont").html("*");
		$("#orderId").remove();
		$("#orderId_span").remove();
		$("#neiorderId").remove();
		$("#waiorderId").remove();
		$("#order_Id").remove();
		$("#neiorderId_span").remove();
		$("#waiorderId_span").remove();
	} else if (warehouse == "外购件库") {
		$("#goodsStoreMarkId").attr("readonly", "readonly");
		$("#goodsName").attr("readonly", "readonly");
		$("#danwei").attr("readonly", "readonly");
		$("#yclwgj").show();
		$("#yclwgjfont").html("外购件查询(件号或名称)");
		$("#sp_bili").html('');
		$("#bili").html('<font color=red>*</font>');
		$("#yclwgjfont").show();
		$("#shortname").show();
		$("#shortname_font").show();
		$("#goodsStoreZHUnitfont").html("");
		$("#bili")
				.html(
						'<font color=red>*</font><input type="button" onclick=getbili("goodsStoreCount") value="计算"/> ');
		$("#sp_bili")
				.html(
						'<font color=red>*</font><input type="button" onclick=getbili("goodsStoreZhishu") value="计算"/> ');
		$("#orderId").remove();
		$("#orderId_span").remove();
		$("#neiorderId").remove();
		$("#waiorderId").remove();
		$("#order_Id").remove();
		$("#neiorderId_span").remove();
		$("#waiorderId_span").remove();
	} else if(warehouse == "成品库"){
		$("#goodsStoreMarkId").removeAttr("readonly");
		$("#storeFormat").removeAttr("readonly");
		$("#goodsName").removeAttr("readonly");
		$("#goodsStoreround").removeAttr("readonly");
		$("#tuhao").removeAttr("readonly");
		$("#danwei").removeAttr("disabled");
		$("#yclwgjfont").hide();
		$("#shortname").hide();
		$("#shortname_font").hide();
		$("#bili").html('<font color=red>*</font>');
		$("#sp_bili").html('');
		$("#tuhaospan").html('');
		$("#goodsStoreZHUnitfont").html("");
		$("#orderId_td2").html("");
		$("#orderId_td1").html("");
		
		$("#mytd").append('<span id="orderId_span">订单号:</span><SELECT  id="orderId" onchange="changvalue(this)"><option value="" >--请选择--</option></SELECT>' +
		'<span id="neiorderId_span">内部订单号:</span> <input type="text" name="goodsStore.neiorderId" id="neiorderId"  readonly= "readonly"> <span id="waiorderId_span">外部订单号:</span> <input type="text"  readonly= "readonly" name="goodsStore.waiorderId" id="waiorderId"> ' +
		' <input type="hidden" name="goodsStore.order_Id" id="order_Id"> ');
		
	}else {
		$("#goodsStoreMarkId").removeAttr("readonly");
		$("#storeFormat").removeAttr("readonly");
		$("#goodsName").removeAttr("readonly");
		$("#goodsStoreround").removeAttr("readonly");
		$("#tuhao").removeAttr("readonly");
		$("#danwei").removeAttr("disabled");
		$("#yclwgjfont").hide();
		$("#shortname").hide();
		$("#shortname_font").hide();
<%--		$("#yclwgj").hide();--%>
		$("#bili").html('<font color=red>*</font>');
		$("#sp_bili").html('');
		$("#tuhaospan").html('');
		$("#goodsStoreZHUnitfont").html("");
		$("#orderId").remove();
		$("#orderId_span").remove();
		$("#neiorderId").remove();
		$("#waiorderId").remove();
		$("#order_Id").remove();
		$("#neiorderId_span").remove();
		$("#waiorderId_span").remove();
	}
	getcangqu();

}

//得到仓区;
function getcangqu(){
	var warehouse = $("#warehouse").val();
	if(warehouse!=""){
			$.ajax( {
		type : "POST",
		url : "WarehouseAreaAction_findwaListByNO.action",
		data : {
			wareHouseName:warehouse
		},
		dataType : "json",
		success : function(data) {
			if (data != null) {
				$("#goodHouseName").empty();
				$("#goodHouseName").append('<option value="">--请选择--</option>')
				$(data).each(function(){
						$("#goodHouseName").append('<option value='+this.goodHouseName+'>'+this.goodHouseName+'</option>');
					});
			}
				var tinyselect = $("#goodHouseName_td").children(".tinyselect");
						if (tinyselect[0] != null) {
							document.getElementById("goodHouseName_td").removeChild(
									tinyselect[0]);
						}
						$("#goodHouseName").tinyselect();
		}
	});
	}
}
<%----%>
//得到库位
function getkuwei(obj){
	var warehouse = $("#warehouse").val();
	if(warehouse != "" && obj!=null && obj.value != ""){
			$.ajax( {
		type : "POST",
		url : "WarehouseAreaAction_findwnListByNO.action",
		data : {
			wareHouseName:warehouse,
			cangqu:obj.value
		},
		dataType : "json",
		success : function(data) {
			if (data != null) {
				$("#goodsStorePosition").empty();
				$("#goodsStorePosition").append('<option value="">--请选择--</option>')
				$(data).each(function(){
						$("#goodsStorePosition").append('<option value='+this.number+'>'+this.number+'</option>');
					});
			}
			var tinyselect =  $("#goodsStorePosition_td").children(".tinyselect");
						if (tinyselect[0] != null) {
							document.getElementById("goodsStorePosition_td").removeChild(
									tinyselect[0]);
						}
			$("#goodsStorePosition").tinyselect();
		}
	});
	}
}

//初始化显示div位置
function init() {
	count_seach++;
	var shortname = document.getElementById("shortname");
	var showAll = document.getElementById("showAll");
	showAll.style.top = getTop(shortname) + 20;
	showAll.style.left = getLeft(shortname);
	showAll.style.visibility = "visible";
}
//ajax获取所有的类似的全称
function getAllNames() {
	if ($("#shortname").val() == null || $("#shortname").val() == "") {
		$("#showAll").empty();
		return;
	}
	var warehouse = $("#warehouse").val();
	var clClass;
	if (warehouse == "原材料库") {
		clClass = "原材料";
	} else if (warehouse == "外购件库") {
		clClass = "外购件";
	}
	$
			.ajax( {
				type : "POST",
				url : "yuanclAndWaigjAction!getAllNames.action",
				dataType : "json",
				data : {
					'yuanclAndWaigj.trademark' : $("#shortname").val(),
					'yuanclAndWaigj.markId' : $("#shortname").val(),
					'yuanclAndWaigj.clClass' :clClass
				},
				success : function(data) {
					$("#showAll").empty();
					$(data)
							.each(
									function() {

										if (clClass == "原材料") {
											var trademark = $(this).attr(
													'trademark').replace(
													$("#shortname").val(),
													"<font color='red'>"
															+ $("#shortname")
																	.val()
															+ "</font>");
											$("#showAll")
													.append(
															"<div onmouseover='ondiv(this)' onmouseout='outdiv(this)' onclick='selectdiv(this)' align='left'>"
																	+ trademark
																	+ "__"
																	+ $(this)
																			.attr(
																					'specification')
																	+ "__"
																	+ $(this)
																			.attr(
																					'name')
																	+ "__"
																	+ $(this)
																			.attr(
																					'ckUnit')
																	+ "__"
																	+ $(this)
																			.attr(
																					'round')
																	+ "__"
																	+ $(this)
																			.attr(
																					'tuhao')
																	+ "__"
																	+ $(this)
																			.attr(
																					'wgType')
																	+ "<span style='visibility: hidden;'>"
																	+ $(this)
																			.attr(
																					'trademark')
																	+ "__"
																	+ $(this)
																			.attr(
																					'specification')
																	+ "__"
																	+ $(this)
																			.attr(
																					'name')
																	+ "__"
																	+ $(this)
																			.attr(
																					'ckUnit')
																	+ "__"
																	+ $(this)
																			.attr(
																					'round')
																	+ "__"
																	+ $(this)
																			.attr(
																					'tuhao')
																	+ "__"
																	+ $(this)
																			.attr(
																					'wgType')
																	+ "</span>"
																	+ "</div>");
										} else if (clClass == "外购件") {
											var banben = $(this).attr(
													'banbenhao');
											var specification = $(this).attr(
													'specification');

											if (banben == null
													|| banben.length == 0) {
												banben = "";
											}
											if (specification == null
													|| specification.length == 0) {
												specification = "";
											}
											var markId = $(this)
													.attr('markId')
													.replace(
															$("#shortname")
																	.val(),
															"<font color='red'>"
																	+ $(
																			"#shortname")
																			.val()
																	+ "</font>");
											$("#showAll")
													.append(
															"<div onmouseover='ondiv(this)' onmouseout='outdiv(this)' onclick='selectdiv(this)' align='left'>"
																	+ markId
																	+ "__"
																	+ $(this)
																			.attr(
																					'name')
																	+ "__"
																	+ $(this)
																			.attr(
																					'unit')
																	+ "__"
																	+ specification
																	+ "__"
																	+ banben
																	+ "__"
																	+ $(this)
																			.attr(
																					'round')
																	+ "__"
																	+ $(this)
																			.attr(
																					'tuhao')
																					+ "__"
																	+ $(this)
																			.attr(
																					'wgType')
																	+ "<span style='visibility: hidden;'>"
																	+ $(this)
																			.attr(
																					'markId')
																	+ "__"
																	+ $(this)
																			.attr(
																					'name')
																	+ "__"
																	+ $(this)
																			.attr(
																					'unit')
																	+ "__"
																	+ banben
																	+ "__"
																	+ specification
																	+ "__"
																	+ $(this)
																			.attr(
																					'round')
																	+ "__"
																	+ $(this)
																			.attr(
																					'tuhao')
																					+ "__"
																	+ $(this)
																			.attr(
																					'wgType')
																	+ "</span>"
																	+ "</div>");
										}

									});
				}
			});
	
}
function selectdiv(obj) {
	var html = $(obj).find("span").html();
	var showAll = document.getElementById("showAll");
	showAll.style.visibility = "hidden";
	var htmls = html.split("__");
	$("#shortname").val(html);
	var warehouse = $("#warehouse").val();
	if (warehouse == "原材料库") {
		$("#goodsStoreMarkId").val(htmls[0]);
		$("#storeFormat").val(htmls[1]);
		if(htmls[2] == "null" || htmls[2] == ""){
			$("#goodsName").val("");
			$("#goodsName").attr("readonly",false);
		}else{
			$("#goodsName").val(htmls[2]);
		}
		
		$("#danwei").val(htmls[3]);
		if(htmls[4] == "null" || htmls[4] == ""){
			$("#goodsStoreround").val("");
			$("#goodsStoreround").attr("readonly",false);
		}else{
			$("#goodsStoreround").val(htmls[4]);
		}
		if(htmls[5]=="null"){
			$("#tuhao").val("");
		}else{
			$("#tuhao").val(htmls[5]);
		}
		if(htmls[6]=="null"){
			$("#wgType").val("");
		}else{
			$("#wgType").val(htmls[6]);
		}
		
	} else if (warehouse == "外购件库") {
		$("#goodsStoreMarkId").val(htmls[0]);
		if(htmls[1] == "null" || htmls[1] == ""){
			$("#goodsName").val("");
			$("#goodsName").attr("readonly",false);
		}else{
			$("#goodsName").val(htmls[1]);
		}
		$("#danwei").val(htmls[2]);
		$("#banBenNumber").val(htmls[3]);
		if (htmls.length >= 4 && htmls[4] != null && htmls[4].length > 0) {
			$("#storeFormat").val(htmls[4]);
		}
		if(htmls[5]=="null" || htmls[5] == ""){
			$("#goodsStoreround").val("");
			$("#goodsStoreround").attr("readonly",false);
		}else{
			$("#goodsStoreround").val(htmls[5]);
		}
		if(htmls[6]=="null"){
			$("#tuhao").val("");
		}else{
			$("#tuhao").val(htmls[6]);
		}
		if(htmls[7]=="null"){
			$("#wgType").val("");
		}else{
			$("#wgType").val(htmls[7]);
		}
		
	}

}
function numyanzhen(obj) {
	var ty1 = /^(-?\d+)(\.\d+)?$/;
	var bChk=ty1.test(obj.value);;
	if (!bChk) {
		obj.value = "";
		obj.focus();
		obj.select();
	}
}

function getbili(obj) {
	var value = $("#" + obj).val();
if(value !=null && value != ""){
	  	$.ajax( {
		type : "POST",
		url : "yuanclAndWaigjAction!getYWbytrademark.action",
		data : {
			'yuanclAndWaigj.markId':$("#goodsStoreMarkId").val(),
			'yuanclAndWaigj.specification':$("#storeFormat").val(),
		},
		dataType : "json",
		success : function(data) {
			if(data!=null && data>0){
				if(obj == "goodsStoreCount"){
					$("#goodsStoreZhishu").val(Math.floor(value/data))
				}else if(obj == "goodsStoreZhishu"){
					$("#goodsStoreCount").val((value*data).toFixed(3))
				}
				
			}else{
			alert("该原材料没有相关的单张重量")
		}
		}
	})

  }else{
	  alert("请先输入数量或转换数量")
  }
	
}

function getorder(obj){
	var warehouse = $("#warehouse").val();
	if(obj!=null && obj.value!="" && warehouse == "成品库"){
		$.ajax( {
		type : "POST",
		url : "GoodsStoreAction!getorder.action",
		data : {
			markId:obj.value
		},
		dataType : "json",
		success : function(data) {
			if(data == "error"){
				alert("出错了呢!");
			}else if(data!=null){
			//$(".tinyselect").remove();
			$("#orderId").empty();
				$(data).each(function(){
						$("#orderId").append("<option value="+this.id+"_"+this.orderNum+"_"+this.outOrderNumber+">"+this.orderNum+"_"+this.outOrderNumber+"</option>");
					});
				$("#orderId").tinyselect();
			}
			
		}
	})
	}
}
function changvalue(obj){
	if(obj!=null && obj.value!=""){
		var arrays =obj.value.split("_");
		if(arrays!=null && arrays.length == 3){
			$("#order_Id").val(arrays[0]);
			$("#neiorderId").val(arrays[1]);
			$("#waiorderId").val(arrays[2]);
			
		}
	}else{
			$("#order_Id").val("");
			$("#neiorderId").val("");
			$("#waiorderId").val("");
		}
}

function notUpdate(obj){
	var shortname=$("#shortname").val();
	if(shortname!=null && shortname!=""){
		alert("不可修改");
	}
}
</script>
	</body>
</html>
