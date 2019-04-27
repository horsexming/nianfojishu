<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			<textarea rows="30" cols="300" style="display: none;" id="information">
				<tr>
					<td class="mytd_0" id="search" align="center">
						<div id="showAll_0"
							style="background-color: #ffffff; position: absolute; visibility: hidden; z-index: 1; top: 40px">
						</div>
						<input type="text" id="shortname_0"
							onkeyup="getAllNames('_0')" style="height: 20px; width: 115px;"
							onFocus="init('_0')" onBlur="hidediv('_0')" />
						<!-- 供料属性 -->
						<input type="hidden" name="sellList[0].kgliao" id="kgliao_0" />
						<!-- Goods.goodsID -->
						<input type="hidden" id="goodsId_0" name="list[0].goodsId"/>
					</td>
					<td align="center"><!-- onblur="checkGoods(this,'_0')"  -->
						<!-- 件号 -->
						<input type="text" name="sellList[0].sellMarkId" id="sellMarkId_0" class="markIdClass"
							  onkeydown="keyDown(event,this,'_0')" 
							  name="sellList[0].sellMarkId" style="width: 115px;"  />
						
						<s:if test='goods.goodsClass =="成品库" ||goods.goodsClass =="备货库"  '>
							<span>(<font color="66ff00">${goods.ywmarkId}</font>)</span>
							<input type="hidden" value="${goods.ywmarkId}"
								name="sell.ywmarkId" />
						</s:if>
					</td>
					<td align="center">
						<input type="text" name="sellList[0].sellCount" id="sellCount_0"
							style="width: 80px" onchange="numyanzhen(this);bijiao(this,0)"
							 onkeydown="keyDownNextList(event,this,'_0')"/>
						<span id="span"></span>
						
						<!-- 保存最大数量 -->
						<input type="hidden" id="maxCount_0"/>
					</td>
					<td align="center">
						<input type="text" name="sellList[0].sellZhishu" id="sellZhishu_0"
							 style="width: 80px" onchange=" numyanzhen(this)" />
						<span id="span1"></span>
					</td>
					<td align="center">
						<input type="text" name="sellList[0].goodsStoreZHUnit"
							id="goodsStoreZHUnit_0" style="width: 80px" />
					</td>
					<td align="center">
						<input id="goodHouseName_0" type="hidden" name="sellList[0].goodHouseName"/>
						<span id="goodHouseNameVal_0"></span>
					</td>
					<td align="center">
						<input id="kuwei_0" type="hidden" name="sellList[0].kuwei"/>
						<span id="kuweiVal_0"></span>
					</td>
					<td align="center">
						<input type="hidden" name="sellList[0].sellGoods" id="sellGoodsName_0"/>
						<span id="sellGoodsNameVal_0"></span>
					</td>
					<td align="center">
						<input type="hidden" name="sellList[0].sellUnit" id="sellUnit_0" />
						<span id="sellUnitVal_0"></span>
					</td>
					<td align="center">
						<input type="hidden" name="sellList[0].sellFormat" id="sellFormat_0" />
						<span id="sellFormatVal_0"></span>
					</td>
					<td align="center">
						<input type="hidden" name="sellList[0].wgType" id="wgType_0" />
						<span id="wgTypeVal_0"></span>
					</td>
					<td align="center">
						<input type="hidden" name="sellList[0].sellLot" id="sellLot_0" />
						<span id="sellLotVal_0"></span>
					</td>
					<td align="center">
						<input type="hidden" name="sellList[0].banBenNumber" id="banBenNumber_0" />
						<span id="banBenNumberVal_0"></span>
					</td>
					
					<td>
						<input type="button" value="增加" onclick="addAList()" />
						<input type="button" value="删除" onclick="delBtn(this)" />
					</td>
				</tr>
			</textarea>
			<div align="center">
				<h2>
					多条手动出库
					<s:if test="successMessage!=null">
						<br />
						<font color="red"><s:property value="successMessage" /> </font>
					</s:if>
				</h2>
				<br />
				<form action="goodsAction!sellGoodsMultiterm.action?tag=saveSell"  method="post" id="submitForm">
					<table class="table">
						<tr>
							<th style="width: 25%;" align="right">
								所属仓库:
								<br />
								Owned warehouse
							</th>
							<td align="left" style="width: 25%;">
								<select name="sell.sellWarehouse" id="warehouse">
									<s:iterator value="l" id="it" status="st">
										<option>
											${it}
										</option>
									</s:iterator>
								</select>
								<font color="red">*</font>
							</td>
							<th align="right" style="width: 25%;">
								供料属性:
								<br>
								Properties of supply
							</th>
							<td style="width: 25%;" align="left">
								<select name="sell.kgliao" style="width: 80px" id="kgliao">
									<option value="">
										--请选择--
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
						</tr>
					</table>
					<table class="table" >
					<input type="hidden" id="ids"/><!-- 记录id -->
					<tbody id="tbody">
						<tr>
							<th>
								查询（件号或名称）
							</th>
							<th>
								件号
							</th>
							<th>
								数量
							</th>
							<th>
								转换数量
							</th>
							<th>
								转换单位
							</th>
							<th>
								仓区
							</th>
							<th>
								库位
							</th>
							<th>
								品名
							</th>
							<th>
								单位
							</th>
							<th>
								规格
							</th>
							<th>
								物料类别
							</th>
							<th>
								批次
							</th>
							<th>
								版本
							</th>
							<th>
								操作
							</th>
						</tr>
						
					</tbody>
					</table>
					<table class="table" style="width: 100%;">
						<tr>
							<th>
								出库日期
							</th>
							<td>
								<input class="Wdate" type="text" id="goodsChangeTime"
									name="sell.sellDate" size="15"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
								<span style="color: red">*</span>
							</td>
							<th>
								供应商:
							</th>
							<td>
								<input type="text" name="sell.sellSupplier"
									value="${goods.goodsSupplier}" />
							</td>
							<th>
								出库类型
							</th>
							<td>
								<select name="sell.style" id="sellStyle">
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
									<option value="损耗出库">
										损耗出库
									</option>
									<option value="研发耗用">
										研发耗用
									</option>
									<option value="售后出库">
										售后出库
									</option>
								</select>
							</td>
							<th>
								返回状态
							</th>
							<td>
								<select name="sell.sellPeople">
									<option>
										已确认
									</option>
									<option>
										在途
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>
								客户:
							</th>
							<td>
								<input type="text" name="sell.sellCompanyName"
									id="sellCompanyName" value="${goods.goodsCustomer }" />
							</td>
							<th>
								负责人
							</th>
							<td>
								<input id="sellCompanyPeople" type="text"
									name="sell.sellCompanyPeople" />
							</td>
							<th>
								领物品人
							</th>
							<td>
								<input type="text" name="sell.sellCharger" />
							</td>
							<th>
								送货单号
							</th>
							<td>
								<input id="sellSendnum" type="text" name="sell.sellSendnum" />
							</td>
						</tr>
						<tr>
							<th>
								总成件号
							</th>
							<td>
								<input type="text" name="sell.sellProMarkId" />
							</td>
							<th>
								订单关联
							</th>
							<td>
								<input id="isNeed1" name="isNeed" type="radio" value="全"
									onclick="showOrder('全')" checked="checked">
								都关联
								<input id="isNeed2" name="isNeed" type="radio" value="外"
									onclick="showOrder('外')">
								仅外部订单号
								<input id="isNeed3" name="isNeed" type="radio" value="否"
									onclick="showOrder('否')">
								否
							</td>
							<th>
								订单号（内部）
							</th>
							<td>
								<%--								<input id="orderNum" type="text" name="sell.orderNum" />--%>
								<SELECT id="orderNum" name="sell.orderNum"
									onchange="changeNumber()">
									<option>
										未确定
									</option>
									<s:iterator value="orderNumberList" id="pageNumber">
										<option>
											<s:property value="#pageNumber" />
										</option>
									</s:iterator>
								</SELECT>
							</td>

							<th>
								订单号（外部）
							</th>
							<td>
								<input name="sell.outOrderNumer" id="outOrderNumer">
							</td>

						</tr>
						<tr>
							<th>
								工艺卡号:
							</th>
							<td>
								<input type="text" name="sell.sellArtsCard" />
							</td>
							

							<th>
								送货单运费(总)
							</th>
							<td>
								<input id="sellSendCost" type="text" name="sell.sellSendCost" />
							</td>
							<th>
								备注：
							</th>
							<td colspan="3">
								<input type="text" name="sell.sellGoodsMore" style="width: 500px;"/>
							</td>
						</tr>
						<tr>
							<th colspan="8">
								<input type="button" value="出库" class="input" id="submitBtn" onclick="checkFormm()"/>
								&nbsp;
								<input type="reset" value="取消" class="input" />
							</th>
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

var count;
$(function() {
	count = $("#sellCount").val();
	addAList();
	//设置默认出库时间
	var d = new Date();
 	var day = d.getDate();        //获取当前日(1-31)
    function addzero(v) {if (v < 10) return '0' + v;return v.toString();}
    var s = d.getFullYear().toString() + '-'+addzero(d.getMonth() + 1)+'-'+day;
    $("#goodsChangeTime").val(s);
});
//提交验证
function checkFormm() {
	var warehouse = document.getElementById("warehouse");
	var sellStyle = document.getElementById("sellStyle");
	var orderNum = document.getElementById("orderNum");
	//var countObj = document.getElementById("sellCount");
	//var countVal = $("#sellCount").val().trim();
	var goodsMarkId = $("#sellMarkId_1").val();
	
	if(null==goodsMarkId || ""==goodsMarkId){
		alert("最少出库一条数据。");
		$("#sellMarkId_1").focus();
		return false;
	}
	
	//校验数量
	for ( var openIndex = 1; openIndex <= tab_option; openIndex++) {
		var sellCount = $("#sellCount_"+openIndex).val();
		var maxCount = $("#maxCount_"+openIndex).val();
		if(sellCount<=0){
			alert("第" +openIndex+ "行，出库数量不正确");
			$("#sellCount_"+openIndex).focus();
			return false;
		}else if(parseInt(sellCount)>parseInt(maxCount)){
			alert("第" + openIndex + "行，出库数量大于最大数量");
			$("#sellCount_"+openIndex).focus();
			return false;
		}
		
		/**
		 * var goodsId = $("#goodsId_"+openIndex).val();
		if(null!= goodsId && ""!=goodsId){
			ids+= goodsId+",";
		}*/
	}
	
	if ($("#goodsChangeTime").val() == "") {
		alert("请填写出库时间!");
		$("#goodsChangeTime").focus();
		return false;
	}
	if ($("#sellSendnum").val() != "" && $("#sellCompanyName").val() == "") {
		alert("请填写客户!");
		return false;
	}
	if ($("#sellSendnum").val() != "" && $("#sellCompanyPeople").val() == "") {
		alert("请填写对方负责人!");
		return false;
	}

	if (warehouse.value == "成品库" && sellStyle.value == "销售出库") {
		if ($("#sellSendnum").val() == "") {
			alert("成品销售出库时送货单号不能为空!");
			$("#sellSendnum").focus();
			return false;
		}
	}
	$("#submitForm").submit();
	return true;
}
function changeNumber() {
	if($("#orderNum").val()=='未确定'){
		
		return;
	}
	
	//加载所有分组
	$.ajax( {
		type : "post",
		url : "orderManager_getOutNumerByNumber.action",
		dataType : "json",
		data : {
		    'orderNum' : $("#orderNum").val()
		},
		success : function(data) {
			//填充部门信息
				if(data!=null&&data!=""){
				 $("#outOrderNumer").val(data);	
				}
		}
	});
}
function showOrder(obj){
	if(obj=="全"){
		$("#orderNum").show();
		$("#outOrderNumer").show();
	}else if(obj=="外"){
		$("#orderNum").hide();
		$("#outOrderNumer").show();
	}else{
		$("#orderNum").hide();
		$("#outOrderNumer").hide();
	}
}
$(function(){
	if($("#warehouse").val() == "原材料库"){
		$("#span").html('<input type="button" onclick=getbili("sellCount") value="计算"/>');
		$("#span1").html('<input type="button" onclick=getbili("sellZhishu") value="计算"/>');
	}
	
})

function numyanzhen(obj) {
	var ty1 = '^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$';
	var re = new RegExp(ty1);
	if (obj != null && obj.value.match(re) == null) {
		obj.value = "";
		obj.focus();
		obj.select();
	} 
}

function getbili(obj){
  var value = $("#"+obj).val();
  if(value !=null && value != ""){
	  	$.ajax( {
		type : "POST",
		url : "yuanclAndWaigjAction!getYWbytrademark.action",
		data : {
			'yuanclAndWaigj.trademark':$("#sellMarkId").val(),
			'yuanclAndWaigj.specification':$("#sellFormat").val(),
		},
		dataType : "json",
		success : function(data) {
			if(data!=null && data>0){
				if(obj == "sellCount"){
					$("#sellZhishu").val((value*data).toFixed(2))
				}else if(obj == "sellZhishu"){
				 	
					$("#sellCount").val((value/data).toFixed(2))
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


var tab_option =0;

//初始化显示div位置
function init(className) {
	count_seach++;
	var shortname = document.getElementById("shortname" + className);
	var showAll = document.getElementById("showAll" + className);
	showAll.style.top = getTop(shortname) + 20;
	showAll.style.left = getLeft(shortname);
	showAll.style.visibility = "visible";
}
function hidediv(className) {
	count_seach--;
	if (count_seach == 0) {
		var showAll = document.getElementById("showAll" + className);
		showAll.style.visibility = "hidden";
	}

}
function outdiv(obj, className) {
	obj.style.background = "#ffffff";
	// var showAll=document.getElementById(".showAll"+className);
	hidediv(className);
}
function getAllNames(className){
	var obj = document.getElementById("shortname" + className);
	if ($(obj).val() == null || $(obj).val() == "") {
		//$("#showAll"+openIndex).empty();
		return;
	}
	var warehouse = $("#warehouse").val();
	var kgliao = $("#kgliao").val();
	var ids = "";
	for ( var openIndex = 0; openIndex <= tab_option; openIndex++) {
		var goodsId = $("#goodsId_"+openIndex).val();
		if(null!= goodsId && ""!=goodsId){
			ids+= goodsId+",";
		}
	}
	if(ids.length>1){
		//$("#ids").val(ids.substring(0,ids.length-1));
		ids=ids.substring(0,ids.length-1);
	}
	$.ajax( {
		type : "POST",
		url : "goodsAction!getAllNames.action",
		dataType : "json",
		cache:false,
		data : {
			'goods.goodsClass':warehouse,
			'goods.goodsMarkId' : obj.value,
			'goods.kgliao' : kgliao,
			'goods.ids' : ids
		},
		success : function(data) {
			$("#showAll" + className).empty();
			$(data).each(function() {
				var markId = $(this).attr('goodsMarkId')
					.replace($("#shortname"+ className).val(),
						"<font color='red'>"
								+ $("#shortname"+ className).val()
								+ "</font>");
				$("#showAll" + className).append(
					"<div onmouseover='ondiv(this)' onmouseout='outdiv(this,\""
						+ className+ "\")' "
						+ "onclick='selectdiv(this,\"" + className + "\")' align='left'>"
						+ markId
						+ "__"
						+ $(this).attr('goodHouseName')
						+ "__"
						+ $(this).attr('goodsPosition')
						+ "__"
						+ $(this).attr('goodsFullName')
						+ "__"
						+ $(this).attr('goodsUnit')
						+ "__"
						+ $(this).attr('goodsFormat')
						+ "__"
						+ $(this).attr('wgType')
						+ "__"
						+ $(this).attr('goodsCurQuantity')
						+ "<span style='visibility: hidden;'>"
						+ $(this).attr('goodsMarkId')
						+ "__"
						+ $(this).attr('goodHouseName')
						+ "__"
						+ $(this).attr('goodsPosition')
						+ "__"
						+ $(this).attr('goodsFullName')
						+ "__"
						+ $(this).attr('goodsUnit')
						+ "__"
						+ $(this).attr('goodsFormat')
						+ "__"
						+ $(this).attr('wgType')
						+ "__"
						+ $(this).attr('goodsCurQuantity')
						+ "__"
						+ $(this).attr('goodsId')
						+ "__"
						+ $(this).attr('kgliao')
						+ "__"
						+ $(this).attr('goodsLotId')
						+ "__"
						+ $(this).attr('banBenNumber')
						+ "</span>"
						+ "</div>");
			});
		}
	});
}

function selectdiv(obj, className) {
	var html = $(obj).find("span").html();
	var showAll = document.getElementById("showAll" + className);
	showAll.style.visibility = "hidden";
	var htmls = html.split("__");
	$(".shortname" + className).val(html);
	var warehouse = $("#warehouse").val();
	//if (warehouse == "原材料库") {
		$("#sellMarkId" + className).val(htmls[0]);
		if (htmls[1] != null && htmls[1].length > 0) {
			$("#goodHouseName" + className).val(htmls[1]);
			$("#goodHouseNameVal" + className).html(
					"<font color='red'>" + htmls[1] + "</font>");
		} else {
			$("#goodHouseName" + className).val("");
			$("#goodHouseNameVal" + className)
					.html("<font color='red'></font>");
		}
		if (htmls[2] == null || htmls[2] == "") {
			$("#kuwei" + className).val("");
			$("#kuweiVal" + className).html("<font color='red'>无</font>");
		} else {
			$("#kuwei" + className).val(htmls[2]);
			$("#kuweiVal" + className).html(
					"<font color='red'>" + htmls[2] + "</font>");
		}
		
		if (htmls[3] == "null" || htmls[3] == "") {
			$("#sellGoodsName" + className).val("");
			$("#sellGoodsNameVal" + className).html("<font color='red'>无</font>");
		} else {
			$("#sellGoodsName" + className).val(htmls[3]);
			$("#sellGoodsNameVal" + className).html(
					"<font color='red'>" + htmls[3] + "</font>");
		}
		//$(".danwei"+className).val(htmls[3]);
		if (htmls[4] == "null" || htmls[4] == "") {
			$("#sellUnit"+className).val("");
			$("#sellUnitVal" + className).html("<font color='red'>无</font>");
		} else {
			$("#sellUnit" + className).val(htmls[4]);
			$("#sellUnitVal" + className).html(
					"<font color='red'>" + htmls[4] + "</font>");
		}
		if (htmls[5] == "null" || htmls[5] == "") {
			$("#sellFormat"+className).val("");
			$("#sellFormatVal" + className).html("<font color='red'>无</font>");
		} else {
			$("#sellFormat" + className).val(htmls[5]);
			$("#sellFormatVal" + className).html(
					"<font color='red'>" + htmls[5] + "</font>");
		}
		
		if (htmls[6] == "null" || htmls == "") {
			$("#wgType" + className).val("");
			$("#wgTypeVal" + className).html("<font color='red'>无</font>");
		} else {
			$("#wgType" + className).val(htmls[6]);
			$("#wgTypeVal" + className).html(
					"<font color='red'>" + htmls[6] + "</font>");
		}
		if (htmls[7] == "null" || htmls[7] == "") {
			$("#sellCount" + className).val("");
			$("#sellCountVal" + className).html("<font color='red'>无</font>");
			$("#maxCount"+className).val("0");
		} else {
			$("#sellCount" + className).val(htmls[7]);
			$("#sellCountVal" + className).html(
					"<font color='red'>" + htmls[7] + "</font>");
			$("#maxCount"+className).val(htmls[7]);
		}
		if (htmls[8] == "null" || htmls[8] == "") {
			$("#goodsId" + className).val("");
		} else {
			$("#goodsId" + className).val(htmls[8]);
		}
		if(htmls[9] =="null" || htmls[9] ==""){
			$("#kgliao" + className).val("");
		}else{
			$("#kgliao" + className).val(htmls[9]);			
		}
		if(htmls[10] =="null" || htmls[10] ==""){
			$("#sellLot" + className).val("");
			$("#sellLotVal" + className).html("<font color='red'>无</font>");
		}else{
			$("#sellLotVal" + className).val(htmls[10]);
			$("#sellLotVal" + className).html(
					"<font color='red'>" + htmls[10] + "</font>");
		}
		
		if(htmls[11] =="null" || htmls[11] ==""){
			$("#banBenNumber" + className).val("");
			$("#banBenNumberVal" + className).html("<font color='red'>无</font>");
		}else{
			$("#banBenNumber" + className).val(htmls[11]);
			$("#banBenNumberVal" + className).html(
					"<font color='red'>" + htmls[11] + "</font>");
		}
		/*if(htmls[10] =="null" || htmls[10] ==""){
			$("#goodsId" + className).val("");
		}else{
			$("#goodsId" + className).val(htmls[10]);			
		}*/
		
		$("#sellCount"+className).focus();//数量聚焦	
		$("#sellCount"+className).select();
		
		//仓库和供料属性设为已读
		$("#warehouse").click(function(){
			//console.log("不可编辑");
			alert("已确定，不可编辑");
	        return false;
	    });   
		$("#kgliao").val(htmls[9]);
		$("#kgliao").click(function(){
			//console.log("不可编辑");
			alert("已确定，不可编辑");
	        return false;
	    });   
}

function keyDown(e,obj,className) {
    var ev= window.event||e;
	//13是键盘上面固定的回车键
    if (ev.keyCode == 13) {
		//你要执行的方法
	  	checkGoods(obj,className)
  	}
}

function checkGoods(obj,className){
	
	var ids = "";
	for ( var openIndex = 0; openIndex <= tab_option; openIndex++) {
		if(className=="_"+openIndex){
			continue;
		}
		var goodsId = $("#goodsId_"+openIndex).val();
		if(null!= goodsId && ""!=goodsId){
			ids+= goodsId+",";
		}
	}
	if(ids.length>1){
		//$("#ids").val(ids.substring(0,ids.length-1));
		ids=ids.substring(0,ids.length-1);
	}
	if(obj!=null && obj.value!=""){
		var warehouse = $("#warehouse").val();
		var kgliao = $("#kgliao").val();
		$.ajax( {
			type : "POST",
			url : "goodsAction!checkGoodsByMarkId.action",
			data : {
				"goods.goodsMarkId":obj.value,
				"goods.goodsClass":warehouse,
				"goods.kgliao":kgliao,
				"goods.ids":ids
			},
			dataType : "json",
			cache:false,
			async:false, 
			success : function(data) {
				if(data!=null){
					/*
					var markIdClass =$(".markIdClass");
					if(markIdClass.length>1){
						var markIdClassLength=0;
						var markIdValue = data[0].goodsMarkId;
						var markIdLength = data.length;
						for(var i =0;i<markIdClass.length;i++){
							if(markIdClass[i].value==markIdValue){
								markIdClassLength++;
	   						}
						}
						
						if(markIdLength == markIdClassLength){
							return ;
						}
					} 
					*/
					var ids="";
					for(var sell=0; sell<data.length;sell++){
						//校验不重复追加
						if(sell>0){
							addAList();
							className = "_"+tab_option;
							var markId = data[sell].goodsMarkId;
							if (markId != null && markId.length > 0) {
								$("#sellMarkId" + className).val(markId);
							} else {
								$("#sellMarkId" + className).val("");
							}
							
						}
						var goodHouseName =data[sell].goodHouseName;// $(this).attr("goodHouseName");
						var kuwei =data[sell].goodsPosition;
						var goodsFullName =data[sell].goodsFullName;
						var sellUnit =data[sell].goodsUnit;
						var sellFormat =data[sell].goodsFormat;
						var sellCount =data[sell].goodsCurQuantity;
						var goodsId = data[sell].goodsId;
						var wgType = data[sell].wgType;
						var kgliao = data[sell].kgliao;
						var goodsLotId = data[sell].goodsLotId;
						var banBenNumber = data[sell].banBenNumber;
						//$("#sellMarkId" + className).val(markId);
						if (goodHouseName != null && goodHouseName.length > 0) {
							$("#goodHouseName" + className).val(goodHouseName);
							$("#goodHouseNameVal" + className).html(
									"<font color='red'>" + goodHouseName + "</font>");
						} else {
							$("#goodHouseName" + className).val("");
							$("#goodHouseNameVal" + className)
									.html("<font color='red'>无</font>");
						}
						if (kuwei == null || kuwei == "") {
							$("#kuwei" + className).val("");
							$("#kuweiVal" + className).html("<font color='red'>无</font>");
						} else {
							$("#kuwei" + className).val(kuwei);
							$("#kuweiVal" + className).html(
									"<font color='red'>" + kuwei + "</font>");
						}
						
						if (goodsFullName == null || goodsFullName == "") {
							$("#sellGoodsName" + className).val("");
							$("#sellGoodsNameVal" + className).html("<font color='red'>无</font>");
						} else {
							$("#sellGoodsName" + className).val(goodsFullName);
							$("#sellGoodsNameVal" + className).html(
									"<font color='red'>" + goodsFullName + "</font>");
						}
						if (sellUnit == null || sellUnit == "") {
							$("#sellUnit"+className).val("");
							$("#sellUnitVal" + className).html("<font color='red'>无</font>");
						} else {
							$("#sellUnit" + className).val(sellUnit);
							$("#sellUnitVal" + className).html(
									"<font color='red'>" + sellUnit + "</font>");
						}
						if (sellFormat == "null" || sellFormat == "") {
							$("#sellFormat"+className).val("");
							$("#sellFormatVal" + className).html("<font color='red'>无</font>");
						} else {
							$("#sellFormat" + className).val(sellFormat);
							$("#sellFormatVal" + className).html(
									"<font color='red'>" + sellFormat + "</font>");
						}
						if (sellCount == null || sellCount == "") {
							$("#sellCount" + className).val("0");
							$("#maxCount"+className).val("0");
						} else {
							$("#sellCount" + className).val(sellCount);
							$("#maxCount"+className).val(sellCount);
						}
						if (goodsId == null || goodsId == "") {
							$("#goodsId" + className).val("");
						} else {
							$("#goodsId" + className).val(goodsId);
						}
						$("#sellCount"+className).focus();//数量聚焦	
						$("#sellCount"+className).select();
						//$(".danwei"+className).val(htmls[3]);
						
						if (wgType == null || wgType == "") {
							$("#wgType" + className).val("");
							$("#wgTypeVal" + className).html("<font color='red'>无</font>");
						} else {
							$("#wgType" + className).val(wgType);
							$("#wgTypeVal" + className).html(
									"<font color='red'>" + wgType + "</font>");
						}
						if(kgliao==null || kgliao==""){
							$("#kgliao" + className).val("");
						}else{
							$("#kgliao" + className).val(kgliao);
						}
						
						if(goodsLotId==null || goodsLotId==""){
							$("#sellLot" + className).val("");
							$("#sellLotVal" + className).html("<font color='red'>无</font>");
						}else{
							$("#sellLot" + className).val(goodsLotId);
							$("#sellLotVal" + className).html(
									"<font color='red'>" + goodsLotId + "</font>");
						}
						if(banBenNumber==null || banBenNumber==""){
							$("#banBenNumber" + className).val("");
							$("#banBenNumberVal" + className).html("<font color='red'>无</font>");
						}else{
							$("#banBenNumber" + className).val(banBenNumber);
							$("#banBenNumberVal" + className).html(
									"<font color='red'>" + banBenNumber + "</font>");
						}
						
						
						/*if(htmls[10] =="null" || htmls[10] ==""){
							$("#goodsId" + className).val("");
						}else{
							$("#goodsId" + className).val(htmls[10]);			
						}*/
						//仓库和供料属性设为已读
						$("#warehouse").click(function(){
							//console.log("不可编辑");
							alert("已确定，不可编辑");
					        return false;
					    });   
						$("#kgliao").val(kgliao);
						$("#kgliao").click(function(){
							//console.log("不可编辑");
							alert("已确定，不可编辑");
					        return false;
					    }); 
						
						ids+=goodsId+",";
					}
					$("#ids").val(ids.substring(0,ids.length-1));
					
					
				}else{
					$("#goodHouseName").val("");
					$("#kuwei").val("");
					$("#sellGoodsName").val("");
					$("#sellUnit").val("");
					$("#sellFormat").val("");
					$("#sellCount").val("");
					$("#wgType").val("");
					$("#kgliao").val("");
				}
				
			}
		});
	}
	
 }

var addIndex = 1;
function addAList(){
	
	//var str = $("#tbody").children("tr:nth-child(2)").html();
	var str = $("#information").val();
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
	//addKgLiao();
	//$(".goodsStoreMarkId_"+tab_option).focus();
}

function delBtn(obj){
	 var html = $(obj).parent().parent().html();
	 if(html.indexOf('_0',0)>0){
		 alert("第一条不能删除呢");
	 }else{
		 $(obj).parent().parent().empty();
	 }
}

function keyDownNextList(event,obj,className){
	var ev= window.event||e;
	//13是键盘上面固定的回车键
    if (ev.keyCode == 13) {
    	if(null!=obj.value && ""!=obj.value){
			//你要执行的方法
			index = parseInt(className.substring(1,2))+1;
			if(tab_option>className){
				$("#sellCount_"+index).focus();//数量聚焦	
				$("#sellCount_"+index).select();
			}else{
			  	addAList();
				$("#sellMarkId_"+index).focus();//数量聚焦	
				$("#sellMarkId_"+index).select();
			}
    	}
    	
  	}
}

function  bijiao(obj,num){
	if(obj!=null && obj.value!=''){
		var sellcount = obj.value;
		var maxcount =	$("#maxCount_"+num).val();
		if(sellcount>maxcount){
			obj.value = maxcount;
			alert("出库数量"+sellcount+"不能大于库存量"+maxcount);
			$(obj).focus();
		}
		
	}
}

</script>
	</body>
</html>
