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
@media print{
	.noprint {
		display: none;
	}
}
</style>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h2>库存报废单</h2>
					<table class="table" width="80%">
						<tr>
							<th align="right">
								件号
							</th>
							<td>
								<SPAN>${baofeigoods.goodsMarkId }</SPAN>
								<input type="hidden" name="baofeigoods.goodsMarkId" value="${goods.goodsMarkId }"/>
							</td>
							<th rowspan="8" align="center">
								报废说明
							</th>
							<td rowspan="8">
								<textarea rows="10" cols="40" id="more" name="baofeigoods.more">${baofeigoods.more}</textarea>
							</td>
						</tr>
						<tr>
							<th align="right">
								品名
							</th>
							<td>
								<SPAN>${baofeigoods.goodsFullName}</SPAN>
								<input type="hidden" name="baofeigoods.goodsFullName" value="${goods.goodsFullName }"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								库别
							</th>
							<td>
								<SPAN>${baofeigoods.goodsClass }</SPAN>
								<input type="hidden" name="baofeigoods.goodsClass" value="${goods.goodsClass }"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								当前量
							</th>
							<td>
								<SPAN id="goodsCurQuantity">${baofeigoods.goodsCurQuantity }</SPAN>
								<input type="hidden" name="baofeigoods.goodsCurQuantity" value="${goods.goodsCurQuantity }"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								申请人
							</th>
							<td >
								<SPAN >${baofeigoods.username}</SPAN>
								<input type="hidden" name="baofeigoods.username" value="${username}"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								批次号
							</th>
							<td>
								<SPAN>${baofeigoods.goodsLotId }</SPAN>
								<input type="hidden" name="baofeigoods.goodsLotId" value="${goods.goodsLotId }"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								报废数量
							</th>
							<td>
								${baofeigoods.sq_num}
							</td>
						</tr>
						<tr>
							<th align="right">
								报废来源
							</th>
							<td>
								${baofeigoods.sq_num}
							</td>
						</tr>
<%--						<tr>--%>
<%--							<th>--%>
<%--								报废说明：--%>
<%--							</th>--%>
<%--							<td>--%>
<%--								<textarea rows="8" cols="35" id="more" name="baofeigoods.more"></textarea>--%>
<%--							</td>--%>
<%--							--%>
<%--						</tr>--%>
						<tr>
							<th colspan="4" align="center">新废品库位置</th>
						</tr>
						<tr>
							<th colspan="4" id="goodsPosition_td">
								&nbsp;&nbsp;&nbsp;&nbsp;库别:
								${baofeigoods.kubie}&nbsp;&nbsp;&nbsp;&nbsp;
								仓区:${baofeigoods.cangqu}&nbsp;&nbsp;&nbsp;&nbsp;
								库位:${baofeigoods.kuwei}
							</th>
						</tr>
					</table>
					<div style="width: 100%;">
						<div style="width:24%; float: left;" ><b>品质:</b></div>
						<div style="width:24%; float: left;"><b>工程:</b></div>
						<div style="width:24%; float: left;"><b>物控:</b></div>
						<div style="width:24%; float: left;"><b>仓库:</b></div>
					</div>
					<div class="noprint">
						<input type="button" value="打印" onclick="window.print()" class="input"/>
					</div>
					
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<script type="text/javascript">
$(document).ready(function(){
	
	$("#goodsClass").tinyselect();
	$("#goodsPosition").tinyselect();
	var rebeack=$("#rebeack").val();
	if(rebeack =="申请成功!"){
		alert(rebeack);
		parent.chageDiv('none');
		parent.window.location.reload();
		parent.window.open("goodsAction!showbfgList.action");
	}
	getcangqu();
})
function numyanzhen() {
	 var  baofei=document.getElementById("baofei");
	var sq_num = document.getElementById("sq_num");
	var ty = '^[0-9]*[1-9][0-9]*$';
	var re = new RegExp(ty);
	if (sq_num != null && sq_num.value.match(re) == null) {
		sq_num.value = "";
		sq_num.focus();
		sq_num.select();
	}
}
function checkfrom(){
	var  more=document.getElementById("more");
	var sq_num = document.getElementById("sq_num");
	var goodsCurQuantity = document.getElementById("goodsCurQuantity");
	var value=Number(goodsCurQuantity.innerHTML);
	var laiyuan = document.getElementById("laiyuan");
	var goodsPosition = document.getElementById("goodsPosition");
	if(sq_num!=null&&sq_num.value==""){
		alert("报废数量不能为空");
		sq_num.focus();
		return false;
	}else if(goodsCurQuantity!=null&&sq_num!=null&&sq_num.value>value){
		alert("报废数量不能大于当前量");
		sq_num.focus();
		return false;
	}else if(laiyuan!=null && laiyuan.value==""){
		alert("报废来源不能为空");
		laiyuan.focus();
		return false;
	}else if(more!=null&&more.value==""){
		alert("报废说明不能为空");
		more.focus();
		return false;
	}else if(goodsPosition!=null && goodsPosition.value==""){
		alert("报废将入到废品库中，仓区和库位不能为空！");
		return false;
	}
	document.getElementById("sub").disabled="disabled";
	return true;
}


//得到仓区;
function getcangqu(){
	var warehouse = $("#goodsClass").val();
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
	var warehouse = $("#goodsClass").val();
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
				$("#goodsPosition").empty();
				$("#goodsPosition").append('<option value="">--请选择--</option>')
				$(data).each(function(){
						$("#goodsPosition").append('<option value='+this.number+'>'+this.number+'</option>');
					});
			}
			var tinyselect =  $("#goodsPosition_td").children(".tinyselect");
						if (tinyselect[0] != null) {
							document.getElementById("goodsPosition_td").removeChild(
									tinyselect[2]);
						}
			$("#goodsPosition").tinyselect();
		}
	});
	}else{
		$("#goodsPosition").empty();
		var tinyselect =  $("#goodsPosition_td").children(".tinyselect");
						if (tinyselect[0] != null) {
							document.getElementById("goodsPosition_td").removeChild(
									tinyselect[2]);
						}
			$("#goodsPosition").tinyselect();
	}
}
</script>
	</body>
</html>