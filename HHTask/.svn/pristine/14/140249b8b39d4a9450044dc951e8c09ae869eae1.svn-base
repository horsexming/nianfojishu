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
			<h2>添加手动下单信息</h2>
			<font id="msg_font" color="red"></font>
			<s:if test="status!='mingxi'">
				<form action="ManualOrderPlanAction_updatemanualPlan.action" method="POST" onsubmit="return check()">
			</s:if>
					<table class="table" >
						<tr>
							<th align="right">
								件&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号
							</th>
							<td>
								<input type="text" value="${mod1.markId}" name="mod1.markId" id="markId" onchange="getwgj()" readonly="readonly" /><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<th align="right">
								名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称
							</th>
							<td>
								<input type="text" value="${mod1.proName}" name="mod1.proName" id="proName" readonly="readonly">
							</td>
						</tr>
						<tr>
							<th align="right">
								规&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;格
							</th>
							<td>
								<input type="text" value="${mod1.specification}" name="mod1.specification" id="specification" readonly="readonly">
							</td>
						</tr>
						<tr>
							<th align="right">
								物料类别
							</th>
							<td>
								<input type="text" value="${mod1.wgType}" name="mod1.wgType" id="wgType" readonly="readonly">
							</td>
						</tr>
						<tr>
							<th align="right">
								供料属性
							</th>
							<td>
								<input type="text" value="${mod1.kgliao}" name="mod1.kgliao" id="kgliao" readonly="readonly">
							</td>
						</tr>
						<tr>
							<th align="right">
								版&nbsp;&nbsp;本&nbsp;&nbsp;号
							</th>
							<td>
								<input type="text" value="${mod1.banben}" name="mod1.banben" id="banben" readonly="readonly">
							</td>
						</tr>
						<tr>
							<th align="right">
								单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位
							</th>
							<td>
								<input type="text" value="${mod1.unit}" name="mod1.unit" id="unit" readonly="readonly">
							</td>
						</tr>
						<tr>
							<th align="right">
								采购数量
							</th>
							<td>
								<input type="text" value="${mod1.cgnumber}" name="mod1.cgnumber" onchange="numyanzheng(this)" 
								id="number" ><font color="red">*</font>
								
							</td>
						</tr>
						<tr>
							<th align="right">
								是否紧急
							</th>
							<td>
								<s:if test='mod1.isurgent=="YES"'>
									<input type="radio" value="YES" name="mod1.isurgent" checked="checked"/>是
									<input type="radio" value="NO" name="mod1.isurgent" />否
								</s:if>
								<s:else>
									<input type="radio" value="YES" name="mod1.isurgent" />是
									<input type="radio" value="NO" name="mod1.isurgent" checked="checked"/>否
								</s:else>
							</td>
						</tr>
						<tr>
							<th align="right">
								备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注
							</th>
							<td>
								<textarea rows="2" cols="40" name="mod1.remarks">${mod1.remarks}</textarea>
							</td>
						</tr>
					</table>
						<input type="hidden" value="${mod1.id}" name="mod1.id" />
						<input type="hidden" value="${mod1.type}" name="mod1.type" id="type"/>
						<s:if test="status!='mingxi'">
							<input type="submit" value="修改" class="input" id="sub" onclick="todisabled(this)" >
						</s:if>
				<s:if test="status!='mingxi'">		
				</form>
			</s:if>
			</div> 
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
$(function(){
	if ('${errorMessage}' == 'true') {
		alert('修改成功');
		parent.chageDiv('none');
		parent.window.location.reload();
	}
})

function getwgj(){
	var markId = $("#markId").val();
	if(markId!=""){
		$.ajax( {
		type : "POST",
		url : "PriceAction!getAllNames.action",
		data : {
			'yuanclAndWaigj.markId' : markId
		},
		dataType : "json",
		success : function(data) {
			if (data != null) {
				$("#proName").val(data.name);	
				$("#specification").val(data.specification);
				$("#wgType").val(data.wgType);
				$("#banben").val(data.banbenhao);
				$("#kgliao").val(data.kgliao);
				$("#type").val("外购");
				$("#unit").val(data.unit);
			}
		}
	})
	}
}
function check(){
	var markId = $("#markId").val();
	var number = $("#number").val();
	if(markId==""){
		$("#msg_font").html("请输入件号");	
		$("#sub").removeAttr("disabled","disabled")
		return false;
	}else if(number==""){
		$("#number").html("请输入采购数量");	
		$("#sub").removeAttr("disabled","disabled")
		return false;
	}
}

</SCRIPT>
	</body>
</html>
