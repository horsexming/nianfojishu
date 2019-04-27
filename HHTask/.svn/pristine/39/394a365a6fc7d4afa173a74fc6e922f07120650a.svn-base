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
		<div id="gongneng">
			
			<div align="center">
				<h2>
					出库管理
					<s:if test="successMessage!=null">
					<br/><font color="red"><s:property value="successMessage"/> </font>
					</s:if>
				</h2>
				<form action="GoodsStoreAction!changeWareHouse.action"
					onsubmit="return checkFormm()" method="post">
					<input type="hidden" name="goods.goodsId" value="${goods.goodsId}">
					<table class="table" style="width: 95%;">
						<tr>
							<th>
								批次:
							</th>
							<td>
								<input type="text" name="sell.sellLot"
									value="${goods.goodsLotId }" readonly="readonly" />
							</td>
							<th>
								件号:
							</th>
							<td>
								<input type="text" name="sell.sellMarkId" id ="sellMarkId"
									value="${goods.goodsMarkId }"  /><s:if test="goods.processNo!=null"><font color="red">(工序:${goods.processNo})</font></s:if>
							</td>
							<th>
								品名:
							</th>
							<td>
								<input type="text" name="sell.sellGoods" 
									value="${goods.goodsFullName}" />
							</td>
							<th>
								规格：
							</th>
							<td>
								<input type="text" name="sell.sellFormat" id="sellFormat"
									value="${goods.goodsFormat }"/>
							</td>

							</th>
						</tr>
						<tr>
							<th>
								数量：
							</th>
							<td>
								<input type="text" name="sell.sellCount" id="sellCount"
									value="${goods.goodsCurQuantity}" onchange="numyanzhen(this)"/>
									<span id="span"></span>
							</td>
							<th>
								计量单位：
							</th>
							<td>
								<input type="text" name="sell.sellUnit"
									value="${goods.goodsUnit }" readonly="readonly" />
							</td>
							<th>
								库别：
							</th>
							<td>${goods.goodsClass}->
							<input type="text" name="sell.sellWarehouse" id="warehouse" value="外购件库" readonly="readonly"/>
							</td>
							<th>
								仓区：
							</th>
							<td id="cangqu_td">${goods.goodHouseName}->
									<SELECT name="sell.goodHouseName" id="goodHouseName" onchange="changvalue1(this)">
										<option></option>
									</SELECT>
							</td>
							

						</tr>
						<tr>
							<th>
								转换数量:
							</th>
							<td>
								<input type="text" name="sell.sellZhishu" id="sellZhishu"
									value="${goods.goodsZhishu}" onchange=" numyanzhen(this)"/>
									<span id="span1"></span>
							</td>
							<th>
								转换单位:
							</th>
							<td>
								<input type="text" name="sell.goodsStoreZHUnit"
									value="${goods.goodsStoreZHUnit }" />
							</td>

							<th>
								库位:
							</th>
							<td>${goods.goodsPosition}->
<%--								<input type="text" name="goods.goodsPosition"/>--%>
								<select name="sell.kuwei" id="goodsPosition">
										<option></option>
								</select>
							</td>	
							<th>
								出库类型
							</th>
							<td>
							<input type="text" name="sell.style" id="sellStyle" value="调仓出库" readonly="readonly"/>
							</td>
							
						</tr>
						<tr>
							<th>
							  负责人
							</th>
							<td>
								<input id="sellCompanyPeople" type="text" name="sell.sellCompanyPeople" value="${Users.name}"/>
							</td>
							<th>
								领物品人
							</th>
							<td>
								<input type="text" name="sell.sellCharger" />
							</td>
                            <th>供料属性</th>
							<td>
								<select name="sell.kgliao">
								<option></option>
									<option value="<s:property value="goodsStore.kgliao" />">
										<s:if test="goodsStore.kgliao=='TK'">
										自购(TK)
										</s:if>
										<s:elseif test="goodsStore.kgliao=='TK AVL'">
										指定供应商(TK AVL)
										</s:elseif>
										<s:elseif test="goodsStore.kgliao=='CS'">
										客供(CS)
										</s:elseif>
										<s:elseif test="goodsStore.kgliao=='TK Price'">
										完全指定(TK Price)
										</s:elseif>
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
								出库日期
							</th>
							<td>
								<input class="Wdate" type="text" id="goodsChangeTime"
									name="sell.sellDate" size="15"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
								<span style="color: red">*</span>
							</td>
						</tr>
						<tr>
							<th>
							版本
							</th>
							<td>
								<input id="banben" type="text" name="sell.banBenNumber" value="${goods.banBenNumber}"/>
							</td>
						</tr>
						<tr>
							<th colspan="8">
								<input type="submit" value="调仓" class="input" onclick="todisabled(this)"/>
								&nbsp;
								<input type="reset" value="取消" class="input" />
							</th>
						</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
//提交验证
function checkFormm() {
	var warehouse = document.getElementById("warehouse");
	var sellStyle = document.getElementById("sellStyle");
	var orderNum = document.getElementById("orderNum");
	if ($("#goodsChangeTime").val() == "") {
		alert("请填写出库时间!");
		$("#goodsChangeTime").focus();
		return false;
	}
	if($("#sellSendnum").val()!=""&&$("#sellCompanyName").val()==""){
		alert("请填写客户!");
		return false;
	}
	if($("#sellSendnum").val()!=""&&$("#sellCompanyPeople").val()==""){
		alert("请填写对方负责人!");
		return false;
	}
	if($("#goodHouseName").val()==""){
		alert("请填写仓区!");
		return false;
	}
	if (warehouse.value == "成品库" && sellStyle.value == "销售出库") {
		if ($("#sellSendnum").val() == "") {
			alert("成品销售出库时送货单号不能为空!");
			$("#sellSendnum").focus();
			return false;
		}
<%--		if (orderNum.value == "") {--%>
<%--			alert("订单号不能为空!");--%>
<%--			orderNum.focus();--%>
<%--			return false;--%>
<%--		}--%>
	}

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

$(function(){
	if('${goods.dtcFlag}'!='外协调委外'){
		$.ajax( {
		type : "POST",
		url : "WarehouseAreaAction_getAllwarehouse.action",
		data : {
		},
		dataType : "json",
		success : function(data) {
			if (data != null) {
				$(data).each(function(){
						$("#warehouse").append('<option value='+this.name+'>'+this.name+'</option>');
					});
			}
		}
	})
	}
	
	
})

$(function(){
	var obj = document.getElementById("warehouse");
	changvalue(obj);
})

function changvalue(obj){
	if(obj!=null && obj.value != ""){
		$.ajax( {
		type : "POST",
		url : "WarehouseAreaAction_findwaListByNO.action",
		data : {
			wareHouseName:obj.value
		},
		dataType : "json",
		success : function(data) {
			if (data != null) {
				$(data).each(function(){
						$("#goodHouseName").append('<option value='+this.goodHouseName+'>'+this.goodHouseName+'</option>');
					});
			}
		}
	});
	}
}
function changvalue1(obj){
	if(obj!=null && obj.value != ""){
		$.ajax( {
		type : "POST",
		url : "WarehouseAreaAction_findwnListByNO.action",
		data : {
			wareHouseName:$("#warehouse").val(),
			cangqu:obj.value
		},
		dataType : "json",
		success : function(data) {
			if (data != null) {
				$("#goodsPosition").empty();
				$(data).each(function(){
						$("#goodsPosition").append('<option value='+this.number+'>'+this.number+'</option>');
					});
			}
		}
	});
	}
}
</script>
	</body>
</html>
