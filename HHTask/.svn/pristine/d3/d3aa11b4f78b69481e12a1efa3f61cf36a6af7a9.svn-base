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
					添加库位
				</h2>
				<font id="zi_font" color="red" size="5"></font>
				<form action="WarehouseAreaAction_addwarehouseNumber.action"
					method="post" onsubmit="return check()">
					<table class="table" id="mytable">
						<tr>
							<th align="right">
								所属仓库:
							</th>
							<td align="left" colspan="2">
								<select id="wareHouseName" name="wareHouseName"
									onchange="changvalue(this)">
									<option value="">
										--请选择--
									</option>
								</select>
							</td>
							<th align="right">
								所属仓区:
							</th>
							<td align="left" id="cangqu_td" colspan="2">
								<select id="warehouseArea" name="cangqu" class="cxselect">
									<option value="">
										--请选择--
									</option>
								</select>
							</td>
							<th colspan="4">
								<input type="button" value="追加" onclick="addline()" class="input"/>
								<input type="button" value="删除" onclick="deline()" class="input">
							</th>
						</tr>
						<tr>
							<th align="right">
								库位号:
							</th>
							<th align="left">
								<input type="text" name="wnList[0].number" id="warehouseNumber0" />
							</th>
							<th align="right">
								编号(1~6):
							</th>
							<th align="left">
								<input type="text" name="wnList[0].numid" id="warehouseNumid0" />
							</th>
							<th align="right">
								库位IP:
							</th>
							<th align="left">
								<select name="wnList[0].ip" id="warehouseIp0">
									<option></option>
								</select>
							</th>
							<th align="right">
								库位层数:
							</th>
							<th align="left">
								<SELECT name="wnList[0].floorNumbe" id="warehouseFloorNumbe0" >
									<option value="">请选择层位</option>
									<option value="上">上</option>
									<option value="中">中</option>
									<option value="下">下</option>
								</SELECT>
							</th>
							<th align="right">
								库位方向:
							</th>
							<th align="left">
								<select name="wnList[0].direction" id="direction0">
									<option value="left" selected="selected">从左至右</option>
									<option value="right">从右至左</option>
								</select>
							</th>
						</tr>
						<tr id="lastTr">
							<td colspan="12" align="center">
								<input type="submit" id="sub" value="提交" class="input" />
							</td>
						</tr>
					</table>
				</form>
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
	getIpList(0);
	
})

function getIpList(num){
	$.ajax( {
		type : "POST",
		url : "WarehouseAreaAction_findIpList.action",
		data : {
		},
		dataType : "json",
		success : function(data) {
			if (data != null && data.length == 2) {
				var IpList = data[0];
				//var fourlightIpList = data[1];
				$(IpList).each(function(){
						$("#warehouseIp"+num).append('<option value='+this.equipmentIP+'>'+this.equipmentIP+'</option>');
					});
				//$(fourlightIpList).each(function(){
				//		$("#fourlightIp"+num).append('<option value='+this.equipmentIP+'>'+this.equipmentIP+'</option>');
				//	});
			}
		}
	})
	
}
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
			$("#warehouseArea").empty();//清空
			$("<option value=''>--请选择--</option>").appendTo("#warehouseArea");
			if (data != null) {
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
		},
		error : function() {
			alert("服务器异常!");
		}
	});
	}
}

var index = 1;
function addline(){
	var newline = '<tr>' +
	'<th align="right">库位号:</th>' +
	'<th align="left"><input type="text" name=wnList['+index+'].number  id="warehouseNumber'+index+'" /></th> ' +
	'<th align="right">编号(1~6):</th>' +
	'<th align="left"><input type="text" name=wnList['+index+'].numid  id="warehouseNumid'+index+'" /></th> ' +
	'<th align="right">库位IP:</th>' +
	'<th align="left"><select name="wnList['+index+'].ip" id="warehouseIp'+index+'"><option></option></select></th>' +
	'<th align="right">库位层位:</th>' +
	'<th align="left">' +
	'<select name=wnList['+index+'].floorNumbe id="warehouseFloorNumbe'+index+'">' +
	'<option value="">请选择层位</option>' +
	'<option value="上">上</option>' +
	'<option value="中">中</option>' +
	'<option value="下">下</option>' +
	'</select></th>' +
	'<th align="right">库位方向:</th>' +
	'<th align="left"><select name="wnList['+index+'].direction" id="direction'+index+'">' +
	'<option value="left" selected="selected">从左至右</option>' +
	'<option value="right">从右至左</option>' +
	'</select></th>' +
	'</tr>';
	$("#lastTr").before(newline);
	getIpList(index);
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
	var wareHouse =	$("#wareHouseName").val();
	var bool = false;
	var i;
	for(i = 0;i<=index; i++){
		var name = $("#warehouseNumber"+i).val();
		//var uid = $("#warehouseNumid"+i).val(); //|| uid == ""
		//var ip = $("#warehouseIp"+i).val();
		//var floorNumbe = $("#warehouseFloorNumbe"+i).val();
		if(name == ""){
			bool = true;
			if(bool){
				var i2 = i+1;
				alert("请仔细填写第"+i2+"条库位信息");
				return false;
			}
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
		$("#zi_font").html("请仔细填写第"+index+"条库位信息");
		$("#warehouseNumber"+i).focus();
		return false;
	}
	document.getElementById("sub").disabled="disabled"
	return true;
	
}
</SCRIPT>
	</body>
</html>
