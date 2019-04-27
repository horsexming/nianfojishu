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
				</div>
			</div>

			<div align="center">
				<h2>
					调库管理
					<s:if test="successMessage!=null">
						<br />
						<font color="red"><s:property value="successMessage" /> </font>
					</s:if>
				</h2>
				<form action="goodsAction!exChangeGoods.action"
					onsubmit="return checkFormm()" method="post">
					<input type="hidden" name="role" value="${role}">
					<input type="hidden" name="goods.goodsId" value="${goods.goodsId}">
					<table class="table" style="width: 95%;">
						<tr>
							<th colspan="10">原库存信息</th>
						</tr>
						
						
						<tr>
							<th>
								批次:
							</th>
							<td>${goods.goodsLotId }
							</td>
							<th>
								件号:
							</th>
							<td>${goods.goodsMarkId }
									
							</td>
							<th>
								品名:
							</th>
							<td>${goods.goodsFullName }
							</td>
							<th>
								规格：
							</th>
							<td>${goods.goodsFormat }
							</td>
						</tr>
						<tr>
							<th>
								供料属性
							</th>
							<td>
								<s:if test="goods.kgliao=='TK'">
								自购(TK)
								</s:if>
								<s:elseif test="goods.kgliao=='TK AVL'">
								指定供应商(TK AVL)
								</s:elseif>
								<s:elseif test="goods.kgliao=='CS'">
								客供(CS)
								</s:elseif>
								<s:elseif test="goods.kgliao=='TK Price'">
								完全指定(TK Price)
								</s:elseif>
							</td>
							<th>库别</th>
							<td>${goods.goodsClass}
							</td>
							
							
							<th>仓区</th>
							<td>${goods.goodHouseName}
							</td>
							
							<th>库位</th>
							<td>${goods.goodsPosition}
							</td>
						</tr>
						<tr>
							<th>
								计量单位：
							</th>
							<td >${goods.goodsUnit }
							</td>
							<th>
								原版本:
							</th>
							<td>${goods.banBenNumber}
							</td>
							<th>
								工艺卡号:
							</th>
							<td>${goods.goodsArtsCard }
							</td>
							<th>
								客户:
							</th>
							<td>${goods.goodsCustomer }
							</td>
						</tr>
						<tr>
							<th>
								原库存数量：
							</th>
							<td>
								<fmt:formatNumber value="${goods.goodsCurQuantity }" pattern="#0.0000" />
							</td>
							<th>
								原转换数量:
							</th>
							<td>
								${goods.goodsZhishu }
							</td>
							<th>
								原转换单位:
							</th>
							<td>
								${goods.goodsStoreZHUnit }
							</td>
							<th>
							</th>
							<td>
							</td>
						</tr>
						<tr>
							<th>
								预调库数量：
							</th>
							<td>
								<input type="text" name="sell.sellCount" id="sellCount"
									value="<fmt:formatNumber value="${goods.goodsCurQuantity }" pattern="#0.0000" />" onchange="numyanzhen(this)" />	
								<span id="span"></span>
							</td>
							<th>
								预转换数量:
							</th>
							<td>
								<input type="text" name="sell.sellZhishu" id="sellZhishu"
									value="${goods.goodsZhishu }" onchange=" numyanzhen(this)" />
								<span id="span1"></span>
							</td>
							<th>
								预转换单位:
							</th>
							<td>
								<input type="text" name="sell.goodsStoreZHUnit"
									value="${goods.goodsStoreZHUnit }" />
							</td>
							<th>
								预出库类型
							</th>
							<td>
								<select name="sell.style" id="sellStyle">
									<option value="转仓出库">
										转仓出库
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<th colspan="10">调库后库存信息</th>
						</tr>
						<tr>
							<th align="right">
								仓库
								<br />
								Owned warehouse
							</th>
							<td>
								<select name="gs.goodsStoreWarehouse" id="warehouse"
									onchange="getcangqu()">

									<s:iterator value="l" id="it" status="st">
										<option>
											${it}
										</option>
									</s:iterator>
								</select>
								<font color="red">*</font>
							</td>
							
							<th align="right">
								仓区
								<br />
								Owned area
							</th>
							<td id="goodHouseName_td">
								<SELECT name="gs.goodHouseName" id="goodHouseName"
									onchange="getkuwei(this)">
									<option value=""></option>
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
								<select name="gs.goodsStorePosition" 
									id="goodsStorePosition">
									<option value=""></option>
								</select>
								<font color="red">*</font>

							</td>
							<th align="right">
								入库类型
								<br>
								Storage type
							</th>
							<td>
								<select name="gs.style" id="style">
									<option>
										转仓入库
									</option>
								</select>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<s:if test="'成品库'!=goods.goodsClass">
								<th align="right">
									版本
									<br>
									Versions
								</th>
								<td>
									<input type="text" name="gs.banBenNumber" value="${goods.banBenNumber}"
										style="width: 200px;height: 25px;">
								</td>
								<th align="right">
									供料属性
								</th>
								<td>
									<input type="text" name="gs.kgliao" value="${goods.kgliao}">
								</td>
								<th align="right">
									备注
								</th>
								<td colspan="3">
									<input type="text" name="gs.goodsStoreGoodsMore" value="${goods.goodsMore2}">
								</td>
							</s:if>
							<s:else>
								<th align="right">
									版本
									<br>
									Versions
								</th>
								<td>
									<input type="text" name="gs.banBenNumber" value="${goods.banBenNumber}"
										style="width: 200px;height: 25px;">
								</td>
								<th align="right">
									内部订单号
								</th>
								<td>
									<input type="text" name="gs.neiorderId" value="${goods.neiorderId}">
								</td>
								<th align="right">
									外部订单号
								</th>
								<td>
									<input type="text" name="gs.waiorderId" value="${goods.waiorderId}">
								</td>
								<th align="right">
									备注
								</th>
								<td>
									<input type="text" name="gs.goodsStoreGoodsMore" value="${goods.goodsMore2}">
								</td>
							</s:else>
						</tr>
						<tr>
							<th colspan="8">
									<input type="submit" value="调库"  class="input" id="sub" />
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
$(document).ready(function() {
	getcangqu();
	$("#warehouse").tinyselect();
	$("#goodsStorePosition").tinyselect();
	$("#style").tinyselect();
});

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
var count;		
$(function(){
	count=$("#sellCount").val();	
});
//提交验证
function checkFormm() {
	var warehouse = document.getElementById("warehouse");
	var countVal=$("#sellCount").val().trim();
	var countnum = parseFloat(countVal);
	var cangqu = $("#goodHouseName").val();
	if(cangqu==null || cangqu==""){
		alert("请选择转入仓区");
		return false;
	}
	//库存数量
	if(countVal==""){
		alert("请输入调库数量");
		$("#sellCount").focus();
		return false;
	}else{
		if(countnum>count){
			alert("调库数量不能大于库存数量");
			$("#sellCount").focus();
			$("#sellCount").val(count);
			return false;
		}
	}
	$("#sub").attr('disabled','disabled');
	return true;
}

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
</script>
	</body>
</html>
