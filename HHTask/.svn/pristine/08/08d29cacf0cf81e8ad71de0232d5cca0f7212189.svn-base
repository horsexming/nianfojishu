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
	getUnit("danwei1");
	$('#submitBtn').bind('click', function() {
		$.ajax( {
			type : "POST",
			url : "GoodsStoreAction!editSdrk.action",
			data : $('#myform').serialize(),
			dataType : "json",
			success : function(msg) {
				alert(msg.message);
				if (msg.success) {
					location.href = "GoodsStoreAction!rukuList.action";
				}
			}
		});
	});
	if ($("#goodsStoreWarehouse").val() == "原材料库") {
		$("#span")
				.html(
						'<input type="button" onclick=getbili("goodsStoreCount") value="计算"/>');
		$("#span1")
				.html(
						'<input type="button" onclick=getbili("goodsStoreZhishu") value="计算"/>');
	}

});
function numyanzhen(obj) {
	var ty1 = '^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$';
	var re = new RegExp(ty1);
	if (obj != null && obj.value.match(re) == null) {
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
			'yuanclAndWaigj.trademark':$("#goodsStoreMarkId").val(),
			'yuanclAndWaigj.specification':$("#goodsStoreFormat").val(),
		},
		dataType : "json",
		success : function(data) {
			if(data!=null && data>0){
				if(obj == "goodsStoreCount"){
					$("#goodsStoreZhishu").val((value*data).toFixed(2))
				}else if(obj == "goodsStoreZhishu"){
				 	
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
	var obj = document.getElementById("goodsStoreMarkId");
	 getorder(obj);
	 getcangqu();
	 
})

function getorder(obj){
	var warehouse ='${goodsStore.goodsStoreWarehouse}';
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
//得到仓区;
function getcangqu() {
	var warehouse = $("#goodsStoreWarehouse").val();//得到库别
	var goodHouseName = $("#goodHouseName").val();//得到仓区
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
					$("#goodHouseName").empty();
					$("#goodHouseName").append('<option value='+goodHouseName+'>'+goodHouseName+'</option>')
					$("#goodHouseName").append('<option value="">请选择仓区</option>')
					$(data).each(function(){
							$("#goodHouseName").append('<option value='+this.goodHouseName+'>'+this.goodHouseName+'</option>');
						});
				}
				var tinyselect =  $("#goodHouseName_td").children(".tinyselect");
							if (tinyselect[0] != null) {
								document.getElementById("goodHouseName_td").removeChild(
										tinyselect[0]);
							}
				$("#goodHouseName").tinyselect();
				getkuwei();
			}
		});
	}
}
//得到库位
function getkuwei(){
	var warehouse = $("#goodsStoreWarehouse").val();//得到库别
	var goodHouseName = $("#goodHouseName").val();//得到仓区
	var kuposition = $("#kuposition").val();//得到库位
	if(warehouse != "" && obj!=null && obj.value != ""){
			$.ajax( {
		type : "POST",
		url : "WarehouseAreaAction_findwnListByNO.action",
		data : {
			wareHouseName:warehouse,
			cangqu:goodHouseName
		},
		dataType : "json",
		success : function(data) {
			if (data != null) {
				$("#goodsStorePosition").empty();
				$("#goodsStorePosition").append('<option value='+kuposition+'>'+kuposition+'</option>')
				$("#goodsStorePosition").append('<option value="">请选择库位</option>')
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
</script>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng">

			<div align="center">
				<form id="myform" method="post" action="">
					<input type="hidden" name="goodsStore.goodsStoreId"
						value="${goodsStore.goodsStoreId}" onchange="getorder(this)" />
					<table class="table">
						<tr>
							<th>
								件号
							</th>
							<td>
								<input name="goodsStore.goodsStoreMarkId" id="goodsStoreMarkId"
									value="${goodsStore.goodsStoreMarkId}" readonly="readonly" />
							</td>
							<th>
								版本号
							</th>
							<td>
								<input name="goodsStore.banBenNumber" readonly="readonly"
									value="${goodsStore.banBenNumber}" />
							</td>
							<th>
								供料属性
							</th>
							<td>
								<input name="goodsStore.kgliao" value="${goodsStore.kgliao}" />
							</td>
							<th>
								批次
							</th>
							<td>
								<input readonly="readonly" name="goodsStore.goodsStoreLot"
									value="${goodsStore.goodsStoreLot}" readonly="readonly"/>
							</td>

						</tr>
						<tr>
							<th>
								库别
							</th>
							<td>
								<select name="goodsStore.goodsStoreWarehouse"
									id="goodsStoreWarehouse">
									<option>
										${goodsStore.goodsStoreWarehouse}
									</option>
								</select><font color="red">*</font>
							</td>
							<th>
								仓区
							</th>
							<td id="goodHouseName_td">
								<input name="goodsStore.goodHouseName" id="goodHouseName"
									value="${goodsStore.goodHouseName}" onchange="getkuwei()"/>
							</td>
							<th>
								库位
							</th>
							<td id="goodsStorePosition_td">
								<input id="kuposition" type="hidden"
									value="${goodsStore.goodsStorePosition}" />
								<select name="goodsStore.goodsStorePosition" id="goodsStorePosition" >
								<option value="${goodsStore.goodsStorePosition}">${goodsStore.goodsStorePosition}</option>
								</select><font color="red">*</font>
							<th>
								品名
							</th>
							<td>
								<input name="goodsStore.goodsStoreGoodsName" readonly="readonly"
									value="${goodsStore.goodsStoreGoodsName}" />
							</td>
						</tr>
						<tr>

							<th>
								数量
							</th>
							<td>
								<input name="goodsStore.goodsStoreCount" id="goodsStoreCount"
									value="${goodsStore.goodsStoreCount}"
									onchange=" numyanzhen(this)"  readonly="readonly"/>

								<span id="span"></span>
							</td>
							<th>
								单位
							</th>
							<td>
								<select name="goodsStore.goodsStoreUnit" id="danwei">
									<option selected="selected">
										${goodsStore.goodsStoreUnit}
									</option>
								</select>
							</td>
							<th>
								转换数量
							</th>
							<td>
								<input name="goodsStore.goodsStoreZhishu" id="goodsStoreZhishu"
									value="${goodsStore.goodsStoreZhishu}"
									onchange=" numyanzhen(this)"  readonly="readonly"/>
								<span id="span1"></span>
							</td>
							<th>
								转换单位
							</th>
							<td>
								<select name="goodsStore.goodsStoreZHUnit" id="danwei1">
									<option selected="selected">
										${goodsStore.goodsStoreZHUnit}
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>
								价格
							</th>
							<td>
								<input name="goodsStore.goodsStorePrice" readonly="readonly"
									value="${goodsStore.goodsStorePrice}" />
							</td>
							<th>
								用途
							</th>
							<td>
								<input name="goodsStore.goodsStoreUseful"
									value="${goodsStore.goodsStoreUseful}" />
							</td>
							<th>
								供应商
							</th>
							<td>
								<input name="goodsStore.goodsStoreSupplier"
									value="${goodsStore.goodsStoreSupplier}" />
							</td>
							<th>
								商品备注
							</th>
							<td>
								<input name="goodsStore.goodsStoreGoodsMore"
									value="${goodsStore.goodsStoreGoodsMore}" />
							</td>
						</tr>
						<tr>
							<th>
								经办人
							</th>
							<td>
								<input name="goodsStore.goodsStoreCharger"
									value="${goodsStore.goodsStoreCharger}" />
							</td>
							<th>
								订单号
							</th>
							<td>
								<s:if test="goodsStore.goodsStoreWarehouse == '成品库'">
									<select id="orderId" style="width: 200px;"
										onchange="changvalue(this)">
										<option>
											${goodsStore.neiorderId}_${goodsStore.waiorderId}
										</option>
									</select>
									<input type="hidden" value="${goodsStore.order_Id}"
										name="goodsStore.order_Id" id="order_Id" />
									<input type="hidden" value="${goodsStore.neiorderId}"
										name="goodsStore.neiorderId" id="neiorderId" />
									<input type="hidden" value="${goodsStore.waiorderId}"
										name="goodsStore.waiorderId" id="waiorderId" />
								</s:if>
								<s:else>
									<input name="goodsStore.orderId" value="${goodsStore.orderId}" />
								</s:else>
							</td>
							<th>
								送货单号
							</th>
							<td>
								<input name="goodsStore.goodsStoreSendId"
									value="${goodsStore.goodsStoreSendId}" readonly="readonly" />
							</td>
							<th>
								计划员
							</th>
							<td>
								<input name="goodsStore.goodsStorePlanner"
									value="${goodsStore.goodsStorePlanner}" />
							</td>
						</tr>
						<tr>
							<th>
								日期
							</th>
							<td>
								<input name="goodsStore.goodsStoreDate" onClick="WdatePicker()"
									class="Wdate" value="${goodsStore.goodsStoreDate}" /><font color="red">*</font>
							</td>
							<th>
								入库类型
							</th>
							<td>
								<select name="goodsStore.style">
									<option selected="selected">
										${goodsStore.style}
									</option>
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
								</select>
							</td>
							<th>
								客户
							</th>
							<td>
								<input name="goodsStore.goodsStoreCompanyName"
									value="${goodsStore.goodsStoreCompanyName}" />
							</td>

							<th>
								申请单编号
							</th>
							<td>
								<input name="goodsStore.goodsStoreNumber"
									value="${goodsStore.goodsStoreNumber}" />
							</td>

						</tr>
						<tr>
							<th>
								炉批号
							</th>
							<td>
								<input name="goodsStore.goodsStoreLuId"
									value="${goodsStore.goodsStoreLuId}" />
							</td>
							<th>
								规格
							</th>
							<td>
								<input name="goodsStore.goodsStoreFormat" id="goodsStoreFormat"
									value="${goodsStore.goodsStoreFormat}" readonly="readonly" />
							</td>

							<th>
								打印单号
							</th>
							<td>
								<input name="goodsStore.printNumber" id="printNumber"
									value="${goodsStore.printNumber}"  readonly="readonly"/>
							</td>
							<th>
								锁定单号
							</th>
							<td>
								<input name="goodsStore.suodingdanhao" id="suodingdanhao"
									value="${goodsStore.suodingdanhao}" />
							</td>
						</tr>
						<tr>
							<th colspan="8">
								<input id="submitBtn" type="button" value="修改" class="input" />
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
	</body>
</html>
