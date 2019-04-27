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
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h2>
					修改库位
				</h2>
				<font id="zi_font" color="red" size="5"></font>
				<form action="WarehouseAreaAction_updatewarehouseNumber.action" method="post" onsubmit="return check()">
					<table id="mytable">
						<tr>
							<th>
								所属仓库:
							</th>
							<td>
								<select id="wareHouseName"  name="warehouseNumber.wareHouseName" onchange="changvalue(this)">
									<option value="${warehouseNumber.wareHouseName}">
										${warehouseNumber.wareHouseName}
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>
								所属仓区:
							</th>
							<td id="cangqu_td">
								<select id="warehouseArea" name="warehouseNumber.warehouseArea" class="cxselect">
									<option value="${warehouseNumber.warehouseArea}">
										${warehouseNumber.warehouseArea}
									</option>
								</select>
							</td>
						</tr>
						
						<tr id="lastTr">
							<td colspan="2" align="center">
								<input type="hidden" value="${warehouseNumber.id}" name="warehouseNumber.id"/>
								<input type="submit" id="sub" value="修改" class="input" />
							</td>
						</tr>
					</table>
				</form>
				<input type="hidden" id="rebeack" value='${errorMessage}'/>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
$(function(){
	$.ajax( {
		type : "POST",
		url : "WarehouseAreaAction_getAllwarehouse.action",
		data : {
		},
		dataType : "json",
		success : function(data) {
			if (data != null) {
				$(data).each(function(){
						$("#wareHouseName").append('<option value='+this.name+'>'+this.name+'</option>');
					});
				$("#wareHouseName").tinyselect();
			}
		}
	})
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
					$("#warehouseArea").empty();
				$(data).each(function(){
						$("#warehouseArea").append('<option value='+this.goodHouseName+'>'+this.goodHouseName+'</option>');
					});
				$("#warehouseArea").tinyselect();
				var tinyselect = $(".tinyselect");
				if (tinyselect[2] != null) {
							document.getElementById("cangqu_td").removeChild(
									tinyselect[2]);
						}
			}
		}
	});
	}
}

var index = 1;
function addline(){
	var newline = '<tr><th>库位号:</th><td><input type="text" name=wnList['+index+'].number  id="warehouseNumber'+index+'" /></td></tr>';
	$("#lastTr").before(newline);
	index++;
}
function deline(){
	var n = $('#mytable tr').length;
	if (index == 1) {
		alert("只剩最后一项了,再删真没了");
		return;
	}
	$($('#mytable tr')[n - 2]).remove();
		index--;
}
function check(){
	var wareHouse =	$("#wareHouse").val();
	var bool = false;
	var i;
	for(i = 0;i<=index; i++){
		var name = $("#warehouseNumber"+i).val();
		if(name == ""){
			bool = true;
			break;
		}
	}
	var warehouseArea = $("#warehouseArea").val();
	if(wareHouse == ""){
		$("#zi_font").html("请选择所属仓库");
		$("#wareHouse").focus();
		$("#wareHouse").select();
		return false;
	}else if(warehouseArea == ""){
		$("#zi_font").html("请选择所属仓区");
		$("#warehouseArea").focus();
		$("#warehouseArea").select();
		return false;
	}else if(bool){
		$("#zi_font").html("请填写库位号");
		$("#warehouseNumber"+i).focus();
		return false;
	}
	document.getElementById("sub").disabled="disabled"
	return true;
	
}
$(document).ready(function(){
		var rebeack=$("#rebeack").val();
		if(rebeack =="修改成功"){
			alert(rebeack);
			parent.chageDiv('none');
			parent.window.location.reload();
		}
	})
</SCRIPT>
	</body>
</html>
