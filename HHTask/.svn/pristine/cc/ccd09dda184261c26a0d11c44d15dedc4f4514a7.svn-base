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
#yclwgj { <%--
	background-color: rgb(154, 205, 50);
	--%>
}

.cangqu {
	width: 280px;
}

#append { <%--
	background-color: rgb(65, 105, 225); --%> <%--
	background-color: #336699;
	--%>
}

#yclwgj td {
	width: 200px;
}

body {
	text-align: center;
}

.tt-menu,.gist {
	text-align: left;
}

.table-of-contents li {
	display: inline-block; *
	display: inline;
	zoom: 1;
}

.typeahead,.tt-query,.tt-hint {
	width: 550px;
	height: 30px;
	padding: 8px 12px;
	font-size: 14px;
	line-height: 30px;
	border: 2px solid #ccc;
	outline: none;
}

.typeahead {
	background-color: #fff;
}

.typeahead:focus {
	border: 2px solid #0097cf;
}

.tt-hint {
	color: #999
}

.tt-menu {
	width: 550px;
	margin: 12px 0;
	padding: 8px 0;
	background-color: #fff;
	border: 1px solid #ccc;
	border: 1px solid rgba(0, 0, 0, 0.2);
}

.tt-suggestion:hover {
	cursor: pointer;
	color: #fff;
	background-color: #0097cf;
}

.tt-suggestion.tt-cursor {
	color: #fff;
	background-color: #0097cf;
}

.tt-suggestion p {
	margin: 0;
}

.gist {
	font-size: 14px;
}
.table{
	word-wrap:break-word; 
	word-break:break-all; 
}
</style>
		<script type="text/javascript">
$(function() {
<%--	getUnit("danwei_0");--%>
<%--	getUnit("ZHdanwei_0");--%>
<%--	//获得所有单位信息--%>
<%--function getUnit(unitId) {--%>
<%--	$.ajax( {--%>
<%--		type : "POST",--%>
<%--		url : 'UnitManagerAction_findUninAll.action',--%>
<%--		dataType : 'json',--%>
<%--		cache : false,//防止数据缓存--%>
<%--		success : function(allUnit) {--%>
<%--			$(allUnit).each( function() {--%>
<%--				$("<option value='" + this.unitname + "'>"--%>
<%--					+ this.unitname + "</option>").appendTo("." + unitId);--%>
<%--			});--%>
<%--		}--%>
<%--	});--%>
<%--}--%>
	$('#submitBtn').bind('click',function() {
		document.getElementById("submitBtn").disabled = true;
		var warehouse = $("#warehouse").val();
		
		var goodsStoreLot = $("#goodsStoreLot").val();
		
		 if (goodsStoreLot == null || goodsStoreLot == "") {
			alert("批次号不能为空");
			document.getElementById("submitBtn").disabled = false;
			$(".goodsStoreLot_"+openIndex).focus();
			return false;
		}
		
		for(var openIndex=0;openIndex<=tab_option;openIndex++){
			var goodsStoreMarkId = $(".goodsStoreMarkId_"+openIndex).val();
			var goodsStoreCount = $(".goodsStoreCount_"+openIndex).val();
			var goodHouseName = $(".goodHouseName_"+openIndex).val();
			var style = $(".style"+openIndex).val();
			var goodsStoreZhishu = $(".goodsStoreZhishu_"+openIndex).val();
			var goodsStorePosition = $(".position_"+openIndex).val();
			var unit = $(".danwei_"+openIndex).val();
			
			if (typeof(goodsStoreMarkId) == "undefined") { 
				continue;
			}	
			if(goodHouseName==null || goodHouseName==""){
				alert("请选择仓区");
				document.getElementById("submitBtn").disabled = false;
				return;
			}else if (goodsStoreMarkId == null || goodsStoreMarkId == "") {
				alert("件号不能为空");
				document.getElementById("submitBtn").disabled = false;
				$(".goodsStoreMarkId_"+openIndex).focus();
				return false;
			} else if(goodsStoreCount== null || goodsStoreCount == ""){
				alert("数量不能为空");
				document.getElementById("submitBtn").disabled = false;
				$(".goodsStoreCount_"+openIndex).focus();
				return false;
			} else if (goodsStorePosition == null || goodsStorePosition == "") {
				alert("库位不能为空");
				document.getElementById("submitBtn").disabled = false;
				$(".goodsStorePosition_"+openIndex).focus();
				return false;
			}else if(unit==null || unit==""){
				alert("单位不能为空");
				$(".danwei_"+openIndex).focus();
				return false;
			}
<%--			} else if (warehouse == "成品库" && style != '返修入库' && style != '退货入库' && style != '外委入库') {--%>
<%--				if (neiorderId == "") {--%>
<%--					alert("请选择内部订单号");--%>
<%--					document.getElementById("submitBtn").disabled = false;--%>
<%--					return false;--%>
<%--				} else if (waiorderId == "") {--%>
<%--					alert("请选择外部订单号");--%>
<%--					document.getElementById("submitBtn").disabled = false;--%>
<%--					return false;--%>
<%--				}--%>
<%--				$("#showmess").show();--%>
<%--				$.ajax( {--%>
<%--					type : "POST",--%>
<%--					url : "GoodsStoreAction!addSdrk.action",--%>
<%--					data : $('#myform').serialize(),--%>
<%--					dataType : "json",--%>
<%--					success : function(msg) {--%>
<%--						alert(msg.message);--%>
<%--						if (msg.success) {--%>
<%--							location.href = "GoodsStoreAction!rukuList.action";--%>
<%--						} else {--%>
<%--							document.getElementById("submitBtn").disabled = false;--%>
<%--						}--%>
<%--					}--%>
<%--				});--%>
<%--			}--%>
		}
		var goodsStoreDate = $("#goodsStoreDate").val();
		var neiorderId = $("#neiorderId").val();
		var waiorderId = $("#waiorderId").val();
		if (goodsStoreDate == null || goodsStoreDate == "") {
			alert("入库日期不能为空");
			document.getElementById("submitBtn").disabled = false;
			return false;
		} else {
			$("#showmess").show();
			$.ajax( {
				type : "POST",
				url : "GoodsStoreAction!addSdrk.action",
				data : $('#myform').serialize(),
				dataType : "json",
				success : function(msg) {
					alert(msg.message);
					if (msg.success) {
						location.href = "GoodsStoreAction!rukuList.action";
					} else {
						document.getElementById("submitBtn").disabled = false;
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
				<h2>
					多条手动入库
					<s:if test="successMessage!=null">
						<br />
						<font color="red"><s:property value="successMessage" /> </font>
					</s:if>
				</h2>
				<form id="myform" method="post" action="">
					<table class="table" style="width: 98%;">
						<thead>
							<tr id="yclwgj">
								<th align="right" colspan="2">
									所属仓库
									<br />
									Owned warehouse
								</th>
								<td>
									<select name="goodsStore.goodsStoreWarehouse" id="warehouse"
										onchange="changeCss()">
										<s:iterator value="l" id="it" status="st">
											<s:if test="#it == '外购件库'">
												<option selected="selected">
													${it}
												</option>
											</s:if>
											<s:else>
												<option >
													${it}
												</option>
											</s:else>
											
										</s:iterator>
									</select>
									<font color="red">*</font>
								</td>
								<th align="right">
									供货属性
									<br>
									Properties of supply
								</th>
								<td colspan="2">
									<select name="goodsStore.kgliao" id="kgliao" onchange="addKgLiao()">
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
						</thead>
					</table>
					<table class="table" style="width: 98%;" id="table_2">
						<tbody id="tbody">
							<tr>
								<th>
									<font class="searchName_0"></font>
								</th>
								<th>
									件号
									<font color="red">*</font>
								</th>
								<th>
									数量
									<font color="red">*</font>
								</th>
								<th>
									仓区
									<font color="red">*</font>
								</th>
								<th>
									库位
									<font color="red">*</font>
								</th>
								<th>
									转换数量
								</th>
								<th>
									转换单位
								</th>
								<th>
									价格
								</th>
								<th>
									版本
								</th>
								<th>
									物料类别
								</th>
								<th>
									名称
								</th>
								<th>
									规格
								</th>
								<th>
									单位
								</th>
								<th>
									备注
								</th>
								<th>
								    预定单号
								</th>
								<th></th>
							</tr>
							<tr>
								<th class="mytd_0" id="search">
									<div class="showAll_0"
										style="background-color: #ffffff; position: absolute; visibility: hidden; z-index: 1; top: 40px">
									</div>
									<!--  -->
									<input type="text" class="shortname_0"
										 onkeyup="getAllNames('_0')"
										style="height: 20px; width: 115px;" onFocus="init('_0')"
										onBlur="hidediv('_0')" />
										
									<input type="hidden" name="goodsStoreList[0].kgliao"  class="kgliao_0"/>
									
								</th>
								<th >
									<input class="goodsStoreMarkId_0"
										name="goodsStoreList[0].goodsStoreMarkId" onblur="checkGoods(this,'_0')" 
										id="markIdKey_0" onkeydown="keyDown(event,this,'_0')" 
										style="width: 115px;"  /><!--  -->
										<!-- <span style="display:none;position:relative;color:red;font-size:12px;right:110px;" class="markId_Hint_0">当前件号不存在</span> -->
								</th>
								<th class="goodsStorePosition_td">
									<input class="goodsStoreCount_0"
									 onkeydown="keyDownAddList(event,this)" 
										name="goodsStoreList[0].goodsStoreCount"
										onchange=" numyanzhen(this);getbili('','_0')" style="width: 50px;" />
								</th>
								<th id="goodHouseName_td">
									<SELECT name="goodsStoreList[0].goodHouseName" class="goodHouseName_0"
										style="width: 90px;" onchange="getkuwei('_0')">
										<option value="">
											--请选择--
										</option>
									</SELECT>
								</th>
								<th>
									<select name="goodsStoreList[0].goodsStorePosition"
										style="width: 90px;" class="position_0">
										<option value="">
											--请选择--
										</option>
									</select>
								</th>
								<th>
									<input name="goodsStoreList[0].goodsStoreZhishu"
										class="goodsStoreZhishu_0" onchange=" numyanzhen(this)"
										style="width: 50px;" />
									<span class="sp_bili_0"></span>
								</th>
								<th>
								<input class="ZHdanwei_0" name="goodsStoreList[0].goodsStoreZHUnit" style="width: 60px;"/>
<%--									<select name="goodsStoreList[0].goodsStoreZHUnit"--%>
<%--										class="ZHdanwei_0">--%>
<%--									</select>--%>
								</th>
								<th>
									<input name="goodsStoreList[0].goodsStorePrice" value="0" style="width: 50px;"/>
								</th>
									<th>
										<input  name="goodsStoreList[0].banBenNumber"
											class="banbenhao_0" type='hidden'/>
										<label class="banbenhaoVal_0"> </label>
										<!-- <input name="goodsStoreList[0].kgliao" class="kgliao_0"
											style="width: 60px;" /> -->
									</th>
								<th>
									<input name="goodsStoreList[0].wgType" class="wgType_0"
										style="width: 60px;" />
									<label class="wgTypeVal_0"></label>
									</td>
									<th>
										<input name="goodsStoreList[0].goodsStoreGoodsName"
											class="goodsName_0" style="width: 60px;" />
										<label class="goodsNameVal_0">
										</label>
										<%--								<font color="red">*</font>--%>
									</th>
									<th>
										<input name="goodsStoreList[0].goodsStoreFormat"
											class="storeFormat_0" style="width: 60px;" />
										<label class="storeFormatVal_0">
										</label>
									</th>
									<th>
										<input class="danwei_0" name="goodsStoreList[0].goodsStoreUnit" style="width: 60px;"/> 
<%--										<select class="danwei_0"  --%>
<%--											name="goodsStoreList[0].goodsStoreUnit" style="width: 60px;">--%>
<%--										</select>--%>
										<label class="danweiVal_0">
										</label>
									</th>
									<th>
										<input type="text" name="goodsStoreList[0].goodsStoreGoodsMore"
											onkeyup="this.size=(this.value.length>4?this.value.length:4);"
											size="4"/>
									</th>
									<th>
										<input type="text" name="goodsStoreList[0].suodingdanhao"
											onkeyup="this.size=(this.value.length>4?this.value.length:4);"
											size="4"/>
									</th>
									<td style="text-align: center;">
										<input type="button" onclick="addAList()" value="追加" />
										<input type="button" onclick="delBtn(this)" value="删除" />
									</td>
							</tr>
						</tbody>
					</table>
					<table class="table" style="width: 98%;">
						<tr>
							<th align="right">
								入库日期
								<br />
								Date
							</th>
							<td>
								<input id="goodsStoreDate" name="goodsStore.goodsStoreDate" class="Wdate"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"  />
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
								入库类型
								<br />
								Storage type
							</th>
							<td>
								<select name="goodsStore.style" class="style_0">
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
						</tr>
						<tr>
							
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
							<th align="right">
								用途
								<br />
								Uses
							</th>
							<td>
								<input name="goodsStore.goodsStoreUseful" />
							</td>
							<th align="right">
								图号
							</th>
							<td>
								<input type="text" name="goodsStore.tuhao" readonly="readonly"
									id="tuhao" />
							</td>
						</tr>
						<tr>

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
							<th align="right">
								客户
								<br />
								Customers
							</th>
							<td>
								<input name="goodsStore.goodsStoreCompanyName" />
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
								工艺卡号
								<br />
								Process card
							</th>
							<td colspan="3">
								<input name="goodsStore.goodsStoreArtsCard" />
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
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/typeahead.js/typeahead.bundle.js">
</script>
		<script type="text/javascript">
var tab_option = 0;
$(document).ready(function() {
	changeCss();
	$(".kgliao_0").val($("#kgliao").val());
	
	//设置默认入库时间
	var d = new Date();
 	var day = d.getDate();        //获取当前日(1-31)
 	if((day-10)<0){
 		day="0"+day;
 	}
    function addzero(v) {if (v < 10) return '0' + v;return v.toString();}
    var s = d.getFullYear().toString() + '-'+addzero(d.getMonth() + 1)+'-'+day;
    $("#goodsStoreDate").val(s);
})
function checkAndSubmit() {
	document.getElementById("submitBtn").disabled = true;
	//var myForm = document.getElementById("myform");
	//myForm.submit();
}
function changeCss() {
	for ( var openIndex = 0; openIndex <= tab_option; openIndex++) {
		//	alert(openIndex);
		var warehouse = $("#warehouse").val();
		$(".orderId_span_" + openIndex).remove();
		$(".orderId_" + openIndex).remove();
		$(".neiorderId_span_" + openIndex).remove();
		$(".neiorderId_" + openIndex).remove();
		$(".waiorderId_span_" + openIndex).remove();
		$(".waiorderId_" + openIndex).remove();
		$(".order_Id_" + openIndex).remove();

		$(".wgType_" + openIndex).hide();
		$(".goodsName_" + openIndex).hide();
		$(".storeFormat_" + openIndex).hide();
		$(".danweiVal_" + openIndex).hide();
		$(".banbenhao_" + openIndex).hide();
		if (warehouse == "原材料库") {
			$(".mytd_" + openIndex).show();
			//$(".mytd_"+openIndex).empty();
			$(".searchName_" + openIndex).html("查询(牌号或规格)");
			$(".searchName_" + openIndex).show();
			$(".shortname_" + openIndex).show();
			$(".shortname_font_" + openIndex).show();
			//$(".goodsStoreMarkId").attr("readonly", "readonly");
			$(".goodsStoreMarkId_" + openIndex).removeAttr("readonly");
			$(".wgType" + openIndex).attr("readonly", "readonly");
			$(".storeFormat_" + openIndex).attr("readonly", "readonly");
			$(".bili_" + openIndex)
					.html(
							'<input type="button" onclick=getbili("goodsStoreCount","_' + openIndex + '") value="计算"/> ');
			$(".sp_bili_" + openIndex)
					.html(
							'<input type="button" onclick=getbili("goodsStoreZhishu","_' + openIndex + '") value="计算"/> ');
			$(".goodsStoreZHUnitfont_").html("*");
			$("#orderId" + openIndex).remove();
			$("#neiorderId" + openIndex).remove();
			$("#waiorderId" + openIndex).remove();
			$("#order_Id" + openIndex).remove();
			$("#neiorderId_span" + openIndex).remove();
			$("#waiorderId_span" + openIndex).remove();
		} else if (warehouse == "外购件库") {
			//$(".goodsStoreMarkId"+openIndex).attr("readonly", "readonly");
			$(".mytd_" + openIndex).show();
			//$(".mytd_"+openIndex).empty();
			$(".searchName_" + openIndex).html("查询(件号或名称)");
			$(".searchName_" + openIndex).show();
			$(".shortname_" + openIndex).show();
			$(".shortname_font_" + openIndex).show();
			$(".yclwgj_" + openIndex).show();
			$(".goodsStoreZHUnitfont_" + openIndex).html("");
			$(".bili_" + openIndex)
					.html(
							'<input type="button" onclick=getbili("goodsStoreCount","_' + openIndex + '") value="计算"/> ');
			$(".sp_bili_" + openIndex)
					.html(
							'<input type="button" onclick=getbili("goodsStoreZhishu","_' + openIndex + '") value="计算"/> ');
			$("#orderId" + openIndex).remove();
			$("#orderId_span" + openIndex).remove();
			$("#neiorderId" + openIndex).remove();
			$("#waiorderId" + openIndex).remove();
			$("#order_Id" + openIndex).remove();
			$("#neiorderId_span" + openIndex).remove();
			$("#waiorderId_span" + openIndex).remove();
		} else if (warehouse == "成品库") {
			$(".goodsStoreMarkId_" + openIndex).removeAttr("readonly");
			$(".storeFormat_" + openIndex).removeAttr("readonly");
			$("#goodsStoreround").removeAttr("readonly");
			$("#tuhao").removeAttr("readonly");
			$(".searchName_" + openIndex).hide();
			$(".shortname_" + openIndex).hide();
			$(".shortname_font_" + openIndex).hide();
			$(".bili_" + openIndex).html('<font color=red>*</font>');
			$(".sp_bili_" + openIndex).html('');
			$(".goodsStoreZHUnitfont_" + openIndex).html("");
			$("#tuhaospan").html('');
			$("#orderId_td2_" + openIndex).html("");
			$("#orderId_td1_" + openIndex).html("");

			$(".mytd_" + openIndex)
					.append(
							'<span class="orderId_span_'
									+ openIndex
									+ '">订单号:</span>'
									+ '<SELECT style="width:285px;" class="orderId_'
									+ openIndex
									+ '" onchange="changvalue(this,\'_'
									+ openIndex
									+ '\')" ><option value="" >--请选择--</option></SELECT><br>'
									+ '<span class="neiorderId_span_'
									+ openIndex
									+ '">内部订单号:</span> '
									+ '<input style="width:115px;" type="text" name="goodsStoreList['
									+ openIndex
									+ '].neiorderId" class="neiorderId_'
									+ openIndex
									+ '"  readonly= "readonly"> '
									+ '<span class="waiorderId_span_'
									+ openIndex
									+ '">外部订单号:</span> '
									+ '<input style="width:115px;" type="text"  readonly= "readonly" name="goodsStoreList['
									+ openIndex
									+ '].waiorderId" class="waiorderId_'
									+ openIndex
									+ '"> '
									+ ' <input type="hidden" name="goodsStoreList['
									+ openIndex
									+ '].order_Id" class="order_Id_'
									+ openIndex + '"> ');

		} else {
			$(".searchName_" + openIndex).hide();
			$(".shortname_" + openIndex).hide();
			$(".shortname_font_" + openIndex).hide();
			$("#tuhao").removeAttr("readonly");
			$("#goodsStoreround").removeAttr("readonly");
			$(".wgType_" + openIndex).show();
			$(".goodsName_" + openIndex).show();
			$(".storeFormat_" + openIndex).show();
			$(".danwei_" + openIndex).show();
			$(".banbenhao_" + openIndex).show();
			
		}
		getcangqu(openIndex);
	}
}

//得到仓区;
function getcangqu(openIndex) {
	var warehouse = $("#warehouse").val();
	//alert(warehouse);
	//alert($(obj).find("select").find("option:selected").text());
	if (warehouse != "") {
		$.ajax( {
			type : "POST",
			url : "WarehouseAreaAction_findwaListByNO.action",
			data : {
				wareHouseName : warehouse
			},
			dataType : "json",
			success : function(data) {
				if (data != null) {
					$(".goodHouseName_"+openIndex).empty();
					$(".goodHouseName_"+openIndex).append(
							'<option value="">--请选择--</option>')
					$(data).each(
							function() {
								$(".goodHouseName_"+openIndex).append(
										'<option value=' + this.goodHouseName
												+ '>' + this.goodHouseName
												+ '</option>');
							});
				}
			}
		});
	}
}
//得到库位
function getkuwei(className) {
	var warehouse = $("#warehouse").val();
	var obj = $(".goodHouseName"+className).val();
	if (warehouse != "" && obj != null && obj.value != "") {
		$.ajax( {
			type : "POST",
			url : "WarehouseAreaAction_findwnListByNO.action",
			data : {
				wareHouseName : warehouse,
				cangqu : obj
			},
			dataType : "json",
			cache:false, 
       		async:false, 
			success : function(data) {
				if (data != null) {
					$(".position" + className).empty();
					$(".position" + className).append( '<option value="">--请选择--</option>');
					$(data).each(function(){
						$(".position" +className).append('<option value='+this.number+'>'+this.number+'</option>');
					});
				}

			}
		});
	}
}

//初始化显示div位置
function init(className) {
	count_seach++;
	var shortname = document.getElementsByClassName("shortname" + className);
	var showAll = document.getElementsByClassName("showAll" + className);
	showAll[0].style.top = getTop(shortname[0]) + 20;
	showAll[0].style.left = getLeft(shortname[0]);
	showAll[0].style.visibility = "visible";
}
function hidediv(className) {
	count_seach--;
	if (count_seach == 0) {
		var showAll = document.getElementsByClassName("showAll" + className)[0];
		showAll.style.visibility = "hidden";
	}

}
function outdiv(obj, className) {
	obj.style.background = "#ffffff";
	// var showAll=document.getElementsByClassName(".showAll"+className);
	hidediv(className);

}

//ajax获取所有的类似的全称
function getAllNames(className) {
	var obj = document.getElementsByClassName("shortname" + className)[0];
	if ($(obj).val() == null || $(obj).val() == "") {
		//$("#showAll"+openIndex).empty();
		return;
	}
	var warehouse = $("#warehouse").val();
	var clClass;
	if (warehouse == "原材料库") {
		clClass = "原材料";
	} else if (warehouse == "外购件库") {
		clClass = "外购件";
	}
	var kgliao = $("#kgliao").val();
	$
			.ajax( {
				type : "POST",
				url : "yuanclAndWaigjAction!getAllNames.action",
				dataType : "json",
				cache:false,
				data : {
					'yuanclAndWaigj.kgliao' : kgliao,
					'yuanclAndWaigj.markId' : $(obj).val(),
					'yuanclAndWaigj.clClass' : clClass
				},
				success : function(data) {
					$(".showAll" + className).empty();
					$(data)
							.each(function() {
										var markId = $(this).attr('markId')
												.replace($(".shortname"+ className).val(),
														"<font color='red'>"
																+ $(".shortname"+ className).val()
																+ "</font>");
										$(".showAll" + className)
												.append(
														"<div onmouseover='ondiv(this)' onmouseout='outdiv(this,\""
																+ className+ "\")' "
																+ "onclick='selectdiv(this,\""
																+ className
																+ "\")' align='left'>"
																+ markId
																+ "__"
																+ $(this).attr('name')
																+ "__"
																+ $(this).attr('unit')
																+ "__"
																+ $(this).attr('specification')
																+ "__"
																+ $(this).attr('banbenhao')
																+ "__"
																+ $(this).attr('round')
																+ "__"
																+ $(this).attr('tuhao')
																+ "__"
																+ $(this).attr('wgType')
																+ "__"
																+ $(this).attr('ckUnit')
																+ "__"
																+ $(this).attr('cangqu')
																+ "<span style='visibility: hidden;'>"
																+ $(this).attr('markId')
																+ "__"
																+ $(this).attr('name')
																+ "__"
																+ $(this).attr('unit')
																+ "__"
																+ $(this).attr('specification')
																+ "__"
																+ $(this).attr('banbenhao')
																+ "__"
																+ $(this).attr('round')
																+ "__"
																+ $(this).attr('tuhao')
																+ "__"
																+ $(this).attr('wgType')
																+ "__"
																+ $(this).attr('ckUnit')
																+ "__"
																+ $(this).attr('cangqu')
																+ "</span>"
																+ "</div>");//wxf
									});
				}
			});
}
function selectdiv(obj, className) {
	var html = $(obj).find("span").html();
	var showAll = document.getElementsByClassName("showAll" + className)[0];
	showAll.style.visibility = "hidden";
	var htmls = html.split("__");
	$(".shortname" + className).val(html);
	var warehouse = $("#warehouse").val();
	if (warehouse == "原材料库") {
		$(".goodsStoreMarkId" + className).val(htmls[0]);
		if (htmls[1] != null && htmls[1].length > 0) {
			$(".storeFormat" + className).val(htmls[1]);
			$(".storeFormatVal" + className).html(
					"<font color='red'>" + htmls[1] + "</font>");
		} else {
			$(".storeFormat" + className).val("");
			$(".storeFormatVal" + className)
					.html("<font color='red'></font>");
		}
		if (htmls[2] == "null" || htmls[2] == "") {
			$(".goodsName" + className).val("");
			$(".goodsNameVal" + className).html("<font color='red'>无</font>");
		} else {
			$(".goodsName" + className).val(htmls[2]);
			$(".goodsNameVal" + className).html(
					"<font color='red'>" + htmls[2] + "</font>");
		}
		if (htmls[3] == null || htmls[3] == "") {
			$(".danwei" + className).val("");
			$(".danweiVal" + className).html("<font color='red'>无</font>");
		} else {
			$(".danwei" + className).val(htmls[3]);
			$(".danweiVal" + className).html(
					"<font color='red'>" + htmls[3] + "</font>");
		}
		//$(".danwei"+className).val(htmls[3]);
		if (htmls[4] == "null" || htmls[4] == "") {
			$(".banbenhao"+className).val("");
			$(".banbenhaoVal"+className).html(
					"<font color='red'>无</font>");
		} else {
			$(".banbenhao"+className).val(htmls[4]);
			$(".banbenhaoVal"+className).html(
					"<font color='red'>" + htmls[4] + "</font>");
		}
		if (htmls[5] == "null") {
			$("#tuhao").val("");
		} else {
			$("#tuhao").val(htmls[5]);
		}
		if (htmls[6] == "null" || htmls == "") {
			$(".wgType" + className).val("");
			$(".wgTypeVal" + className).html("<font color='red'>无</font>");
		} else {
			$(".wgType" + className).val(htmls[6]);
			$(".wgTypeVal" + className).html(
					"<font color='red'>" + htmls[6] + "</font>");
		}
	} else if (warehouse == "外购件库") {
		$(".goodsStoreMarkId" + className).val(htmls[0]);
		if (htmls[1] == "null" || htmls[1] == "") {
			$(".goodsName" + className).val("");
			$(".goodsNameVal" + className).html("<font color='red'>无</font>");
		} else {
			$(".goodsName" + className).val(htmls[1]);
			$(".goodsNameVal" + className).html(
					"<font color='red'>" + htmls[1] + "</font>");
		}
		if (htmls[2] == null || htmls[2] == "" || htmls[2]=="null") {
			$(".danwei" + className).val("");
			$(".danweiVal" + className).html("<font color='red'>无</font>");
		} else {
			$(".danwei" + className).val(htmls[2]);
			$(".danweiVal" + className).html(
					"<font color='red'>" + htmls[2] + "</font>");
		}

		//$(".banBenNumber"+className).val(htmls[3]);
		if ( htmls[3] != null && htmls[3].length > 0 && htmls[3] !="null") {
			$(".storeFormat" + className).val(htmls[3]);
			$(".storeFormatVal" + className).html(
					"<font color='red'>" + htmls[3] + "</font>");
		} else {
			$(".storeFormat" + className).val("");
			$(".storeFormatVal" + className)
					.html("<font color='red'></font>");
		}
		if (htmls[4] == "null" || htmls[4] == "") {
			$(".banbenhao"+className).val("");
			$(".banbenhaoVal"+className).html(
					"<font color='red'>无</font>");
		} else {
			$(".banbenhao"+className).val(htmls[4]);
			$(".banbenhaoVal"+className).html(
					"<font color='red'>" + htmls[4] + "</font>");
		}
		if (htmls[6] == "null") {
			$("#tuhao").val("");
		} else {
			$("#tuhao").val(htmls[6]);
		}
		if (htmls[7] == "null" || htmls == "") {
			$(".wgType" + className).val("");
			$(".wgTypeVal" + className).html("<font color='red'>无</font>");
		} else {
			$(".wgType" + className).val(htmls[7]);
			$(".wgTypeVal" + className).html(
					"<font color='red'>" + htmls[7] + "</font>");
		}
		if (htmls[8] == "null" || htmls == "") {
			$(".wgType" + className).val("");
			$(".wgTypeVal" + className).html("<font color='red'>无</font>");
		} else {
			$(".ZHdanwei" + className).val(htmls[8]);
		}
		if (htmls[9] == "null" || htmls == "") {
			$(".goodHouseName" + className).val('');
		} else {
			$(".goodHouseName" + className).val(htmls[9]);
		}
	}
	getcqAndkwBymarkId(htmls[0],className);
}
function numyanzhen(obj) {
	var ty1 = /^(-?\d+)(\.\d+)?$/;
	var bChk = ty1.test(obj.value);
	;
	if (!bChk) {
		obj.value = "";
		obj.focus();
		obj.select();
	}
}

function getbili(obj, className) {

	var value = $(".goodsStoreCount" + className).val();
if(value[0] !=null && value[0] != ""){
	  	$.ajax( {
			type : "POST",
			url : "yuanclAndWaigjAction!getYWbytrademark.action",
			data : {
				'yuanclAndWaigj.markId':$(".goodsStoreMarkId"+className).val(),
				'yuanclAndWaigj.specification':$(".storeFormat"+className).val(),
			},
			dataType : "json",
			success : function(data) {
				if(data!=null && data>0){
					//if(obj == "goodsStoreCount"){
						$(".goodsStoreZhishu"+className).val(Math.ceil(value/data))
					//}else if(obj == "goodsStoreZhishu"){
					//	$(".goodsStoreCount"+className).val((value*data).toFixed(3))
					//}
				}else{
					//alert("该材料没有相关的单张重量")
				}
			}
		})

  }else{
	  alert("请先输入数量")
  }
}

function getorder(obj,className){
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
				$(".orderId"+className).empty();
					$(".orderId"+className).append("<option value=''>--请选择--</option>");
					for(var i = 0; i<data.length;i++){
						$(".orderId"+className).append("<option value="+data[i].id+"_"+data[i].orderNum+"_"
							+data[i].outOrderNumber+">"+data[i].orderNum+"_"+data[i].outOrderNumber+"</option>");
					}
				}
				
			}
		})
	}
}
function changvalue(obj,className){
	if(obj!=null && obj.value!=""){
		var arrays =obj.value.split("_");
		if(arrays!=null && arrays.length == 3){
			$(".order_Id"+className).val(arrays[0]);
			$(".neiorderId"+className).val(arrays[1]);
			$(".waiorderId"+className).val(arrays[2]);
		}
	}else{
		$(".order_Id"+className).val("");
		$(".neiorderId"+className).val("");
		$(".waiorderId"+className).val("");
	}
}

function keyDownAddList(event,obj){
	 var ev= window.event||e;
	//13是键盘上面固定的回车键
    if (ev.keyCode == 13) {
    	if(null!=obj.value && ""!=obj.value){
			//你要执行的方法
		  	addAList();
    	}
    	
  	}
	
}

var addIndex = 1;
function addAList(){
	complateInputCount();
	var str = $("#tbody").children("tr:nth-child(2)").html();
	tab_option++;
	while (str.indexOf('_0') >= 0){
       str = str.replace('_0', '_'+tab_option);
       str = str.replace('[0]', '['+tab_option+']');
    }
	var firstNo=0;
	var endNo = 0;
	var middleStr="";
	while(str.indexOf('<font')>0){
		firstNo = str.indexOf('<font');
		endNo = str.indexOf("</font>")+7;
		middleStr = str.substring(firstNo,endNo);
		str=str.replace(middleStr,"");
	}
	str = "<tr>"+str+"</tr>"
	$("#tbody").append(str);
	//addKgLiao();
	
	//设置供料属性
	addKgLiao();
	$(".goodsStoreMarkId_"+tab_option).focus();
	
}

function delBtn(obj){
	 var html = $(obj).parent().parent().html();
	 if(html.indexOf('_0',0)>0){
		 alert("第一条不能删除呢");
	 }else{
		 $(obj).parent().parent().empty();
	 }
}

function keyDown(e,obj,className) {
    var ev= window.event||e;
	//13是键盘上面固定的回车键
    if (ev.keyCode == 13) {
		//你要执行的方法
	  	//checkGoods(obj,className)
  	}
}

function checkGoods(obj,className){
	 
	var warehouse = $("#warehouse").val();
	var kgliao = $("#kgliao").val();
	if(obj!=null && obj.value!=""){
		if(warehouse=="外购件库"){
			warehouse = "外购件";
		}else if(warehouse=="原材料库"){
			warehouse = "原材料";
		}
		$.ajax( {
			type : "POST",
			url : "yuanclAndWaigjAction!checkYuanclAndWaigjByinfo.action",
			data : {
				"yuanclAndWaigj.markId":obj.value,
				"yuanclAndWaigj.clClass":warehouse,
				"yuanclAndWaigj.kgliao":kgliao
			},
			dataType : "json",
			success : function(data) {
				if(data!=null){
					if (data.name == null || data.name == "") {
						$(".goodsName" + className).val("");
						$(".goodsNameVal" + className).html("<font color='red'>无</font>");
					} else {
						$(".goodsName" + className).val(data.name);
						$(".goodsNameVal" + className).html(
								"<font color='red'>" + data.name + "</font>");
					}
					if (data.unit == null || data.unit == "") {
						$(".danwei" + className).val("");
						$(".danweiVal" + className).html("<font color='red'>无</font>");
					} else {
						$(".danwei" + className).val(data.unit);
						$(".danweiVal" + className).html(
								"<font color='red'>" + data.unit + "</font>");
					}
			
					//$(".banBenNumber"+className).val(htmls[3]);
					if ( data.specification == null || data.specification =="") {
						$(".storeFormat" + className).val("");
						$(".storeFormatVal" + className)
								.html("<font color='red'>无</font>");
					} else {
						$(".storeFormat" + className).val(data.specification);
						$(".storeFormatVal" + className).html(
								"<font color='red'>" + data.specification + "</font>");
					}
					
					if (data.tuhao == null || data.tuhao=="") {
						$("#tuhao").val("");
					} else {
						$("#tuhao").val(data.tuhao);
					}
					if (data.wgType==null || data.wgType == "") {
						$(".wgType" + className).val("");
						$(".wgTypeVal" + className).html("<font color='red'>无</font>");
					} else {
						$(".wgType" + className).val(data.wgType);
						$(".wgTypeVal" + className).html(
								"<font color='red'>" + data.wgType + "</font>");
					}
					if (data.banbenhao== null || data.banbenhao == "") {
						$(".banbenhao" + className).val("");
						$(".banbenhaoVal" + className).html("<font color='red'>无</font>");
					} else {
						$(".banbenhao" + className).val(data.banbenhao);
						$(".banbenhaoVal" + className).html(
								"<font color='red'>" +  data.banbenhao + "</font>");
					}
					getcqAndkwBymarkId(obj.value,className);
					$(".goodsStoreCount"+className).focus(); //点击确认按钮光标放到数量上
				}else{
					$(".goodsName" + className).val("");
					$(".goodsNameVal" + className).html("");
					$(".danwei" + className).val("");
					$(".danweiVal" + className).html("");
					$(".storeFormat" + className).val("");
					$(".storeFormatVal" + className).html("");
					$("#tuhao").val("");
					$(".wgType" + className).val("");
					$(".wgTypeVal" + className).html("");
					$(".banbenhao" + className).val("");
					$(".banbenhaoVal" + className).html("");
					//$(".markId_Hint"+className).show("slow");
					//$(".goodsStoreMarkId" + className).val("");
					//$(".goodsStoreMarkId" + className).attr("placeholder","次件号不存在");
				}
				
			}
		});
	}
	
 }

function getcqAndkwBymarkId(markId,className){
	//加载最近一次入库历史记录中的仓区和库位
					$.ajax({
						type:"post",
						url:"GoodsStoreAction!findHouseNameByMarkId.action",
						dataType:"json",
						data:{"markId":markId},
						success:function(datas){
							if(null!=datas){
								//$(".goodHouseName"+className)[0].options[0].selected = true;
								 $(".goodHouseName"+className).find("option:contains('"+datas[0]+"')").attr("selected",true);
								 //$(".goodHouseName"+className).trigger("change");  //触发onchange
								 getkuwei(className);
								 $(".position"+className).find("option:contains('"+datas[1]+"')").attr("selected",true);
							}
							$(".goodsStoreCount"+className).focus(); //点击确认按钮光标放到数量上
						},function(){
							alert("出错了");
						}
					});
}
var oldOpenIndex;
function addKgLiao(){
	var kgliao = $("#kgliao").val();
	//for ( var openIndex = 0; openIndex <= tab_option; openIndex++) {
		$(".kgliao_"+tab_option).val(kgliao);
		$(".goodsStoreMarkId_"+tab_option).focus();
	//}
	//oldOpenIndex=tab_option;
}
/*
 * function checkGoods(className){
	$(".goodsStoreMarkId"+className).bind('keypress',function(event){ 
		//if(event.keyCode == 13) {  
             alert('你输入的内容为1：' + $(".goodsStoreMarkId"+className).val());  
      //   }  
		
	});
	
	$(function(){
	//输入件号查询物料
	 $('#markIdKey'+className).bind('keypress', function (event) {
	     if (event.keyCode == "13") {
	         alert($(this).val());
	     }
	 });
});
	
	
}
 * */


 
 
 
 
var tTD; //用来存储当前更改宽度的Table Cell,避免快速移动鼠标的问题   
var table = document.getElementById("table_2");   
for (j = 0; j < table.rows[0].cells.length; j++) {
	table.rows[0].cells[j].onmousedown = function () {   
	//记录单元格   
	tTD = this;
	if (event.offsetX > tTD.offsetWidth - 10) {   
		tTD.mouseDown = true;
		tTD.oldX = event.x;
		tTD.oldWidth = tTD.offsetWidth;   
	}   
	//记录Table宽度   
	//table = tTD; while (table.tagName != ‘TABLE') table = table.parentElement;   
	//tTD.tableWidth = table.offsetWidth;   
	};   
	table.rows[0].cells[j].onmouseup = function () {
		//结束宽度调整   
		if (tTD == undefined){
			tTD = this;
		}
		tTD.mouseDown = false;   
		tTD.style.cursor = 'default';   
	};
	table.rows[0].cells[j].onmousemove = function () {   
		//更改鼠标样式   
		if (event.offsetX > this.offsetWidth - 10)
			this.style.cursor = 'col-resize';   
		else
			this.style.cursor = 'default';   
		//取出暂存的Table Cell
		if (tTD == undefined) tTD = this;
		//调整宽度   
		if (tTD.mouseDown != null && tTD.mouseDown == true) {
			tTD.style.cursor = 'default';
			//if (tTD.oldWidth + (event.x - tTD.oldX)>0)
				tTD.width = tTD.oldWidth + (event.x - tTD.oldX);
			//调整列宽
			tTD.style.width = tTD.width;
			tTD.style.cursor = 'col-resize';
			//调整该列中的每个Cell
			table = tTD;
			while (table.tagName != 'TABLE'){
				table = table.parentElement;
			}
			for (j = 0; j < table.rows.length; j++) {
				table.rows[j].cells[tTD.cellIndex].width = tTD.width;
			}
			//调整整个表   
			//table.width = tTD.tableWidth + (tTD.offsetWidth – tTD.oldWidth);   
			//table.style.width = table.width;   
		}
	};
}  


function complateInputCount(){
	var numbers = 0;
	for ( var openIndex = 0; openIndex <= tab_option; openIndex++) {
		
		if($(".shortname_"+openIndex).val()!=null || $("#markIdKey_"+openIndex).val()!=null){
			numbers++;
			$(".shortname_"+openIndex).val(numbers);
		}
		
		
	}
}
</script>
	</body>
</html>
