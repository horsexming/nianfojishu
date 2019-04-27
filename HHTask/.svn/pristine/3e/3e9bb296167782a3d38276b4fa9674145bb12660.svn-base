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
					入库信息确认和修改
				</h2>

				<s:if test="errorMessage!=null">
					<br>
					<h2>
						<font color="red">${errorMessage}</font>
					</h2>
				</s:if>
				<form action="GoodsStoreAction!updateGoodsStore.action" onsubmit="return checkFormm()"  method="post" id="submit">
					<input type="hidden" name="tag" value="${tag}" />
					<input type="hidden" name="goodsStore.goodsStoreId"
						value="${goodsStore.goodsStoreId}" />
					<table class="table">
						<tr>
							<th>
								批次:
							</th>
							<td>
								<input type="text" name="goodsStore.goodsStoreLot"
									value="${goodsStore.goodsStoreLot }" readonly="readonly" />
							</td>
							<th>
								件号:
							</th>
							<td>
								<input type="text" name="goodsStore.goodsStoreMarkId"
									value="${goodsStore.goodsStoreMarkId }" readonly="readonly" />
									（<font color="red">${goodsStore.processNo}</font>）
							</td>
							<th>
								名称:
							</th>
							<td>
								<input type="text" name="goodsStore.goodsStoreGoodsName"
									value="${goodsStore.goodsStoreGoodsName }" readonly="readonly" />
							</td>
						</tr>
						<tr>
							<th>
								数量:
							</th>
							<td>
								<input type="text" name="goodsStore.goodsStoreCount"
									value="${goodsStore.goodsStoreCount }"  onchange="changeCount(${goodsStore.goodsStoreCount })"  id="count"/>
							</td>


							<th>
								单位:
							</th>
							<td>
								<%--								<input type="text" name="goodsStore.goodsStoreUnit"--%>
								<%--									value="${goodsStore.goodsStoreUnit}" />--%>
								<select name="goodsStore.goodsStoreUnit" id="danwei">
									<option selected="selected">
										${goodsStore.goodsStoreUnit}
									</option>
								</select>

							</td>
							<th>
								入库时间:
							</th>
							<td>
								<input id="goodsStoreDate" class="Wdate" type="text"
									name="goodsStore.goodsStoreDate"
									value="${goodsStore.goodsStoreDate }" size="15"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" /> &nbsp;<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<th>
								库别:<!-- 属于哪个仓库 -->
							</th>
							<td>
								<input id="wareName" type="text"
									name="goodsStore.goodsStoreWarehouse"
									value="${goodsStore.goodsStoreWarehouse}" readonly="readonly"/>
							</td>
							
							
							<th>
								仓区:<!-- 属于哪个仓库下的哪个仓区 -->
							</th>
							
							<td id="warehouseArea_td">
								<%--<input id="position" type="text"
									name="goodsStore.goodHouseName"
									value="${goodsStore.goodHouseName}" />
							--%>
								<select id="warehouseArea" name="goodsStore.goodHouseName" onchange="getPosition()">
									<option value="">
										--请选择--
									</option>
								</select>
								<input type="hidden" id="houseName" value="${goodsStore.goodHouseName}"/>
								
								
							</td>
							
							<th>
								库位:
							</th>
							<td id="goodsStorePosition_td">
								<input id="position" type="hidden"
									value="${goodsStore.goodsStorePosition }" />
								<select id="goodsStorePosition" name="goodsStore.goodsStorePosition">
									
								</select>
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
								负责人:
							</th>
							<td>
								<input type="text" name="goodsStore.goodsStoreCharger"
									value="${goodsStore.goodsStoreCharger}" />
							</td>
							<th>
								申请人:
							</th>
							<td>
								<input type="text" value="${goodsStore.goodsStorePlanner}"
									name="goodsStore.goodsStorePlanner" />
							</td>
						</tr>
						<tr>
							<th>
								转换数量:
							</th>
							<td>
								<input type="text" value="${goodsStore.goodsStoreZhishu }"
									name="goodsStore.goodsStoreZhishu " />
							</td>
							<th>
								转换单位:
							</th>
							<td>
								<SELECT name="goodsStore.goodsStoreZHUnit" id="danwei1">
									<option selected="selected">
										${goodsStore.goodsStoreZHUnit}
									</option>
								</SELECT>
							</td>
							<th>
								业务件号:
							</th>
							<td>
							<input type="text" value="${goodsStore.ywmarkId}"
									name="goodsStore.ywmarkId " />
							</td>
							
						</tr>
						<tr>
							<th>
								内部订单号:
							</th>
							<td>
							<input type="text" value="${goodsStore.neiorderId}"
									name="goodsStore.neiorderId " />
							</td>
							<th>
								外部订单号:
							</th>
							<td>
								<input type="text" value="${goodsStore.waiorderId}"
									name="goodsStore.waiorderId "/>
							</td>
							<th>
								版本号
							</th>
							<td>${goodsStore.banBenNumber}
							<input type="hidden" value="${goodsStore.banBenNumber}"
									name="goodsStore.banBenNumber"/>
							</td>
							<th>
								
							</th>
							<td>
							</td>
						
						</tr>
						<tr>
							<th colspan="6">
								<input id="tosubmit" type="submit" value="提交" class="input" />
<%--								<input id="tosubmit" type="button" value="提交" class="input"  onclick="checkFormm()"/>--%>
								&nbsp;
								<input type="reset" value="放弃" class="input" />
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
$(function() {
	getUnit("danwei");
	getUnit("danwei1");
})
//拿到库别下所有仓区
$(function(){
    var wareHouseName=$("#wareName").val();
    if(wareHouseName!=""){
    	$.ajax({
			type : "POST",
			url : "WarehouseAreaAction_findwaListByNO.action",
			data : {
				wareHouseName:wareHouseName
			},
			dataType : "json",
			cache:false, 
       		async:false, 
			success : function(data) {
				$("#warehouseArea").empty();//清空
				$("<option value=''>--请选择--</option>").appendTo("#warehouseArea");
				if (data != null) {
					
					$(data).each(function(){
						$("#warehouseArea").append('<option value='+this.goodHouseName+'>'+this.goodHouseName+'</option>');						
					});
					$("#warehouseArea").tinyselect();
					getPosition();
				}
			},
			error : function() {
				alert("服务器异常!");
			}
		});
    }
    var houseName = $("#houseName").val();
<%--    $("#warehouseArea").append('<option value='+houseName+' selected="selected">'+houseName+'</option>');--%>
    var tinyselect =  $("#warehouseArea_td").children(".tinyselect");
	if (tinyselect[0] != null) {
		document.getElementById("warehouseArea_td").removeChild(
				tinyselect[0]);
	}
	$("#warehouseArea").tinyselect();
	
	var position = $("#position").val();
    $("#goodsStorePosition").append("<option value='"+position+"'>"+position+"</option>");
    var tinyselect =  $("#goodsStorePosition_td").children(".tinyselect");
    if (tinyselect[0] != null) {
		document.getElementById("goodsStorePosition_td").removeChild(
				tinyselect[0]);
	}
	$("#goodsStorePosition").tinyselect();
});
/**
 * 获得库位
 * @return {TypeName} 
 */
function getPosition(){
	 var warehouse = $("#wareName").val();
	var warehouseArea = $("#warehouseArea").val();
	if (warehouse != "" && obj != null && obj.value != "") {
		$.ajax( {
			type : "POST",
			url : "WarehouseAreaAction_findwnListByNO.action",
			data : {
				wareHouseName : warehouse,
				cangqu : warehouseArea
			},
			dataType : "json",
			cache:false, 
       		async:false, 
			success : function(data) {
				if (data != null) {
					$("#goodsStorePosition").empty();
					$("#goodsStorePosition").append( '<option value="">--请选择--</option>');
					$(data).each(function(){
						$("#goodsStorePosition").append('<option value='+this.number+'>'+this.number+'</option>');
					});
					var tinyselect =  $("#goodsStorePosition_td").children(".tinyselect");
				    if (tinyselect[0] != null) {
						document.getElementById("goodsStorePosition_td").removeChild(
								tinyselect[0]);
					}
					$("#goodsStorePosition").tinyselect();
				}

			}
		});
	}
}

//提交验证
function checkFormm(){
	var	submitFlag=false;
	var cangkuName=$("#wareName").val().trim();
	var cangquName=$("#warehouseArea").val().trim();
	var count=$("#count").val().trim();
	
	if(cangkuName!=""){
		// if(count==""){
			//alert("入库数量不能为空!");
			//$("#count").focus();
			//return false;
		// }else{
			 //if(!numyanzheng(countObj,"")){
				 //$("#count").focus();
				 //return false;
			// }
			 //return numyanzheng(countObj,"");
		// }
		 
		 if(cangquName==""){
			alert("仓区不能为空!");
			$("#warehouseArea").focus();
			return false;
		 }else{
				 //查找仓区面积
				$.ajax({
					type : "POST",
					url : "WarehouseAreaAction_ajaxSelectCangQuArea.action",
					data : {
						wareHouseName:cangkuName,
						cangqu:cangquName
					},
					dataType : "json",
					async : false,
					success : function(data) {
						if("full"==data){
							submitFlag=confirm(cangkuName+"下的"+cangquName+"已满，是否继续放入此仓区");
						}else{
							submitFlag=true;
						}
					},
					error : function() {
						alert("服务器异常!");
						
					}
				  });
				 
			}
		 if($("#goodsStoreDate").val() == "") {
			alert("入库时间不能为空!");
			$("#goodsStoreDate").focus();
			return false;
		 }
	}
	return submitFlag;
}

function changeCount(countVal){
	
	var count=$("#count").val();
	var countObj=document.getElementById("count");
	if(count=="" || count==0){
			alert("入库数量不能为空!");
			$("#count").focus();
			 $("#count").val(countVal);
			return false;
	}else{
			 if(!numyanzheng(countObj,"")){
				 $("#count").focus();
				 $("#count").val(countVal);
				 return ;
			 }else{
				 if(count>countVal || count<0){
					 alert("入库数量不能大于库存数量!");	
					$("#count").focus("");
					$("#count").val(countVal);
					return ;
				}
			 }
			
	}
	
}

$(document).ready(function() {
        document.getElementById("goodsStoreDate").value = today();
});
function today(){
    var today=new Date();
    var h=today.getFullYear();
    var m=today.getMonth()+1;
    var d=today.getDate();
    var hh=today.getHours();
    var mm=today.getMinutes();
    var ss=today.getSeconds();
    m= m<10?"0"+m:m;     
    d= d<10?"0"+d:d;
    hh = hh < 10 ? "0" + hh:hh;
    mm = mm < 10 ? "0" +  mm:mm;
    ss = ss < 10 ? "0" + ss:ss;
    return h+"-"+m+"-"+d+" "+hh+":"+mm+":"+ss;
}
</script>
	</body>
</html>
