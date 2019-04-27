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
	<title></title>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
			<br/>
			<h2 style="font-size: x-large;">添加生产辅料追溯管理</h2>
			<font color="red" id="msg_font" style="font-size: large;"></font>
			<br/>
				<form action="FuLiaoZhuiSuAction_addflzs.action" method="post" onsubmit="return check()">
					<table class="table" id="mytable">
						<tr>
							<th align="right">
								订单编号(内部)
							</th>
							<td>
								<input type="text" value="" name="flzs.orderNum" id="orderNum" onchange="getywmarkIds(this)" /><font color="red">*</font>
							</td>
							<th align="right">
								业务件号
							</th>
							<td>
								<SELECT name="flzs.ywmarkId" id="ywmarkId" onchange="getprocardBy()" >
								</SELECT><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<th align="right">
								总成批次
							</th>
							<td>
								<input type="text" value="" name="flzs.rootselfCard" id="rootselfCard" readonly="readonly" />
							</td>
							<th align="right">
								生产数量
							</th>
							<td>
								<input type="text" value="" name="flzs.num"  id="num" readonly="readonly"/>
							</td>
							
						</tr>
						<tr>
							<th align="right">
								件号
							</th>
							<td>
								<input type="text" value="" name="flzs.markId" id="markId"  onchange="getproName(this)"/>
							</td>
							<th align="right">
								名称
							</th>
							<td>
								<input type="text" value="" name="flzs.proName" id="proName" readonly="readonly"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								工位
							</th>
							<td>
								<input type="text" value="" name="flzs.gongwei" id="gongwei" onchange="getshebeiNo(this)" />
							</td>
							<th align="right">
								设备编号
							</th>
							<td>
								<input type="text" value="" name="flzs.shebeiNo" id="shebeiNo" readonly="readonly"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								生产日期
							</th>
							<td>
								<input type="text" value="" name="flzs.scdate" id="scdate" class="Wdate" 
								onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<th align="right">
								负责人
							</th>
							<td>
								<input type="text" value="${Users.name}" name="flzs.person" readonly="readonly"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								组分A
							</th>
							<td>
								<textarea rows="20" cols="28" name="flzs.groupA" id="groupA" onchange="huanghang(this)"></textarea>
							</td>
							<th align="right">
								组分B
							</th>
							<td>
								<textarea rows="20" cols="28" name="flzs.groupB" id="groupB" onchange="huanghang(this)"></textarea>
							</td>
						</tr>
					</table>
					<input type="submit" value="提交" id="sub" class="input" onclick="todisabled(this)"/>
				</form>	
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">

$(function(){
	if('${errorMessage}' == "true"){
		alert('添加成功!')
		parent.window.location.reload();
	}
})
	function getywmarkIds(obj){
		if(obj!=null && obj.value!=''){
		$.ajax( {
		type : "POST",
		url : "FuLiaoZhuiSuAction_getywmarkIds.action",
		data : {
				orderNum:obj.value
			},
		dataType : "json",
		success : function(data) {
			if(data!=null){
				$("#ywmarkId").empty();
				$(data).each(function(){
					$("#ywmarkId").append('<option value="'+this+'">'+this+'</option>');
				})
				getprocardBy();
			}
		}
	})
}
	}

function getprocardBy(){
	var orderNum = $("#orderNum").val();
	var ywmarkId = $("#ywmarkId").val();
	if(orderNum!='' && ywmarkId!=''){
			$.ajax( {
		type : "POST",
		url : "FuLiaoZhuiSuAction_findProcardBy.action",
		data : {
				orderNum:orderNum,
				ywmarkId:ywmarkId
			},
		dataType : "json",
		success : function(data) {
			if(data!=null){
				$("#rootselfCard").val(data.selfCard);
				$("#num").val(data.filnalCount);
			}
		}
	})
	}
}

function getshebeiNo(obj){
		if(obj!=null && obj.value!=''){
		$.ajax( {
		type : "POST",
		url : "FuLiaoZhuiSuAction_getshebeiNo.action",
		data : {
				orderNum:obj.value
			},
		dataType : "json",
		success : function(data) {
			if(data!=null){
				$("#shebeiNo").val(data);
			}
		}
	})
}
}
function check(){
	var  groupA =$("#groupA").val();
	var  groupB =$("#groupB").val();
	var orderNum = $("#orderNum").val();
	var markId = $("#markId").val();
	if(orderNum == ''){
		$("#msg_font").html("订单编号(内部)");
		$("#orderNum").focus();
		$("#sub").removeAttr("disabled")
		return  false;
	}else if(markId == ''){
		$("#msg_font").html("请填写件号");
		$("#markId").focus();
		$("#sub").removeAttr("disabled")
		return  false;
	}else if(groupA == ''){
		$("#msg_font").html("请填写组分A");
		$("#groupA").focus();
		$("#sub").removeAttr("disabled")
		return  false;
	}else if(groupB == ''){
		$("#msg_font").html("请填写组分B");
		$("#groupA").focus();
		$("#sub").removeAttr("disabled")
		return  false;
	}
}
function getproName(obj){
		if(obj!=null && obj.value!=''){
		$.ajax( {
		type : "POST",
		url : "FuLiaoZhuiSuAction_getproName.action",
		data : {
				orderNum:obj.value
			},
		dataType : "json",
		success : function(data) {
			if(data!=null){
				$("#proName").val(data);
			}
		}
	})
}
}
var  index = 0;

function addline(){
	index++;
	var newline = '<tr><th align="right">组分A</th><td><input type="text" value="" name="groupA0"  id="groupA'+index+'"/><font color="red">*</font></td>' +
	'<th align="right">组分B</th><td><input type="text" value="" name="groupB0" id="groupB0" /><font color="red">*</font></td></tr>';
	$("#mytable").append(newline);
}
function delline(){
	if(index == 0){
		alert('只剩最后一行了，再删就真的没有了！~')
		return false;
	}
		var n = $('#mytable tr').length;
		$($('#mytable tr')[n-1]).remove();
		index --;
}


</SCRIPT>
	</body>
</html>
