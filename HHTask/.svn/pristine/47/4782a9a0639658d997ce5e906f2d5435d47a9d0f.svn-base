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
				<h2 style="font-size: 20px;">
					库存调拨单打印
				</h2>
				<form action="GoodsStoreAction!findChangeGoods.action" method="post">
					<table class="table">
						<tr>
							<th align="right">件号</th>
							<td><input type="text" name="goodsStore.goodsStoreMarkId" value="${goodsStore.goodsStoreMarkId}"/></td>
							<th align="right">名称</th>
							<td><input type="text" name="goodsStore.goodsStoreGoodsName" value="${goodsStore.goodsStoreGoodsName}"/></td>
							<th align="right">入库类型</th>
							<td><input type="text" name="goodsStore.style" value="${goodsStore.style}"/></td>.
							<th align="right">打印单号</th>
							<td><input type="text" name="goodsStore.changePrint" value="${goodsStore.changePrint}"/></td>
							<th align="right">输入已打印条数</th>
							<td><input type="text" name="pageSize" value="${pageSize}"/></td>
						</tr>
						<tr>
							<td colspan="10" align="center">
								<input type="submit" value="查询" class="input" />
							</td>
						</tr>
					</table>
				</form>
				<form action="GoodsStoreAction!toChangeGoodsPage.action" method="post">
					<table class="table">
						<tr>
							<th>选择</th>
							<th>序号</th>
							<th>打印单号</th>
							<th>件号</th>
							<th>名称</th>
							<th>规格</th>
							<th>单位</th>
							<th>数量</th>
							<th>调入仓库</th>
							<th>调入仓区</th>
							<th>调入库位</th>
							<th>调出仓库</th>
							<th>调出仓区</th>
							<th>调出库位</th>
							<th>日期</th>
							<th>入库类型</th>
						</tr>
						<s:if test="goodsStoreList!=null && goodsStoreList.size()>0">
							<tr bgcolor="red">
								<th>全选未打印<br/>
									<input type="checkbox" onchange="changeUnAll(this)"/></th>
								<th colspan="15">调拨未打印</th>
							</tr>
							<s:iterator value="goodsStoreList" id="unGS" status="pageStatus">
								<s:if test="#pageStatus.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'')">
								</s:else>
									<td align="center"><input type="checkbox" value="${unGS.goodsStoreId}" class="unSell" name="selected"/></td>
									<td>${pageStatus.index+1 }</td>
									<td>${unGs.changePrint }</td>
									<td>${unGS.goodsStoreMarkId}</td>
									<td>${unGS.goodsStoreGoodsName}</td>
									<td>${unGS.goodsStoreFormat}</td>
									<td>${unGS.goodsStoreUnit}</td>
									<td align="right">${unGS.goodsStoreCount}</td>
									<td>${unGS.goodsStoreWarehouse}</td>
									<td>${unGS.goodHouseName}</td>
									<td>${unGS.goodsStorePosition}</td>
									<td style="background-color: lime">${unGS.sellWarehouse}</td>
									<td style="background-color: lime">${unGS.sellHouseName}</td>
									<td style="background-color: lime">${unGS.sellKuwei}</td>
									<td>${unGS.goodsStoreDate}</td>
									<td>${unGS.style}</td>
								</tr>
							</s:iterator>
						</s:if>
						<tr bgcolor="green">
							<th>全选已打印<br/>
								<input type="checkbox" onchange="changeAll(this)"/></th>
							<th colspan="15">调拨已打印</th>
						</tr>
						<s:if test="list!=null && list.size()>0">
							<s:iterator value="list" id="gs" status="pageStatus">
								<s:if test="#pageStatus.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'')">
								</s:else>
									<td align="center"><input type="checkbox" value="${gs.goodsStoreId}" class="gstore" name="selected"/></td>
									<td>${pageStatus.index+1 }</td>
									<td>${gs.changePrint }</td>
									<td>${gs.goodsStoreMarkId}</td>
									<td>${gs.goodsStoreGoodsName}</td>
									<td>${gs.goodsStoreFormat}</td>
									<td>${gs.goodsStoreUnit}</td>
									<td align="right">${gs.goodsStoreCount}</td>
									<td>${gs.goodsStoreWarehouse}</td>
									<td>${gs.goodHouseName}</td>
									<td>${gs.goodsStorePosition}</td>
									<td style="background-color: lime">${gs.sellWarehouse}</td>
									<td style="background-color: lime">${gs.sellHouseName}</td>
									<td style="background-color: lime">${gs.sellKuwei}</td>
									<td>${gs.goodsStoreDate}</td>
									<td>${gs.style}</td>
								</tr>
							</s:iterator>
							<tr>
								<td colspan="20" align="right">
									第
									<font color="red"><s:property value="cpage" /> </font> /
									<s:property value="total" />
									页
									<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
										styleClass="page" theme="number" />
								</td>
							</tr>
						</s:if>
						<s:else>
							<tr>
								<td colspan="20" align="left">
									<font color="red">没有已打印的记录</font>
								</td>
							</tr>
						</s:else>
						<tr>
						<tr>
							<td colspan="20"><input type="submit" value="前往打印" class="input" ></td>
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

function changeUnAll(obj){
	var flag = obj.checked;
	var unSell = $(".unSell");
	if(flag){
		for(var i=0;i<unSell.length;i++){
			unSell[i].checked= true;
		}
	}else{
		for(var i=0;i<unSell.length;i++){
			unSell[i].checked= false;	
		}
	}
}

function changeAll(obj){
	var flag = obj.checked;
	var gstore = $(".gstore");
	if(flag){
		for(var i=0;i<gstore.length;i++){
			gstore[i].checked= true;
		}
	}else{
		for(var i=0;i<gstore.length;i++){
			gstore[i].checked= false;	
		}
	}
}
</script>
	</body>
</html>
