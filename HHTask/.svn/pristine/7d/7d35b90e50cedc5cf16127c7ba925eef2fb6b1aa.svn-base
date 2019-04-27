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
			<h2 style="font-size: x-large;">修改生产辅料追溯管理</h2>
			<br/>
				<form action="FuLiaoZhuiSuAction_updateflzs.action" method="post" onsubmit="return check()">
					<table class="table">
						<tr>
							<th align="right">
								订单编号(内部)
							</th>
							<td>
								<input type="text" value="${flzs.orderNum}" name="flzs.orderNum" id="orderNum" onchange="getywmarkIds(this)" /><font color="red">*</font>
							</td>
							<th align="right">
								业务件号
							</th>
							<td>
								<SELECT name="flzs.ywmarkId" id="ywmarkId"  onchange="getprocardBy()" >
									<option value="${flzs.ywmarkId}">${flzs.ywmarkId}</option>
								</SELECT><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<th align="right">
								总成批次
							</th>
							<td>
								<input type="text" value="${flzs.rootselfCard}" name="flzs.rootselfCard" id="rootselfCard" readonly="readonly" />
							</td>
							<th align="right">
								件号
							</th>
							<td>
								<input type="text" value="${flzs.markId}" name="flzs.markId" id="markId"/>
							</td>
							
						</tr>
						<tr>
							<th align="right">
								名称
							</th>
							<td>
								<input type="text" value="${flzs.proName}" name="flzs.proName" id="proName" readonly="readonly"/>
							</td>
							<th align="right">
								生产数量
							</th>
							<td>
								<input type="text" value="${flzs.num}" name="flzs.num"  id="num" readonly="readonly"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								生产日期
							</th>
							<td>
								<input type="text" value="${flzs.scdate}" name="flzs.scdate" id="scdate"
								class="Wdate" 
								onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<th align="right">
								工位
							</th>
							<td>
								<input type="text" value="${flzs.gongwei}" name="flzs.gongwei" id="gongwei" onchange="getshebeiNo(this)" />
							</td>
						</tr>
						<tr>
							<th align="right">
								设备编号
							</th>
							<td>
								<input type="text" value="${flzs.shebeiNo}" name="flzs.shebeiNo" id="shebeiNo" readonly="readonly"/>
							</td>
							<th align="right">
								负责人
							</th>
							<td>
								<input type="text" value="${flzs.person}" name="flzs.person" readonly="readonly"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								组分A
							</th>
							<td>
								<textarea rows="20" cols="28" name="flzs.groupA" id="groupA" onchange="huanghang(this)">${flzs.groupA}</textarea>
							</td>
							<th align="right">
								组分B
							</th>
							<td>
								<textarea rows="20" cols="28" name="flzs.groupB" id="groupB" onchange="huanghang(this)">${flzs.groupB}</textarea>
							</td>
						</tr>
					</table>
					<input type="hidden" value="${flzs.addtime}" id="" name="flzs.addtime"/>
					<input type="hidden" value="${flzs.id}" id="" name="flzs.id"/>
					<input type="submit" value="提交" id="sub" class="input" onclick="todisabled(this)"/>
				</form>	
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
	function getywmarkIds(obj){
		alert(obj.value)
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
				$("#ywmarkId").append("<option></option>");
				$(data).each(function(){
					$("#ywmarkId").append('<option value="'+this+'">'+this+'</option>');
				})
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
		url : "FuLiaoZhuiSuAction_getywmarkIds.action",
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
</SCRIPT>
	</body>
</html>
