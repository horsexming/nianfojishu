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
				<h2>库存详细信息</h2>
				<form action="wasteDisposeAction!addWasteDisponse.action" method="post" onsubmit="return checkfrom()">
					<table class="table" width="80%">
						<tr>
							<th align="right">件号</th>
							<td><SPAN>${goods.goodsMarkId }</SPAN></td>
							<th rowspan="7" align="center">处理数量</th>
							<td rowspan="7">
								<input type="text" id="disponseNum" name="wasteDisponsal.disposeNum" value="${goods.goodsCurQuantity}"/>
							</td>
						</tr>
						<tr>
							<th align="right">品名</th>
							<td><SPAN>${goods.goodsFullName}</SPAN></td>
						</tr>
						<tr>
							<th align="right">库别</th>
							<td><SPAN>${goods.goodsClass }</SPAN></td>
						</tr>
						<tr>
							<th align="right">原价格(单价)</th>
							<td><SPAN id="goodsPrice">${goods.goodsPrice }</SPAN></td>
						</tr>
						<tr>
							<th align="right">当前量</th>
							<td><SPAN id="goodsCurQuantity">${goods.goodsCurQuantity }</SPAN></td>
						</tr>
						<tr>
							<th align="right">批次号</th>
							<td><SPAN>${goods.goodsLotId }</SPAN></td>
						</tr>
						<tr>
							<th align="right">规格</th>
							<td><SPAN>${goods.goodsFormat }</SPAN></td>
						</tr>
						<tr>
							<th align="right">物料类别</th>
							<td><SPAN>${goods.wgType }</SPAN></td>
							<th rowspan="7" align="center">处理价格</th>
							<td rowspan="7">
								<input type="text" id="disponsePrice" name="wasteDisponsal.disposePrice" value="${goods.goodsPrice}"/>
							</td>
						</tr>
						<tr>
							<th align="right">物料属性</th>
							<td>
								<SPAN>
									<s:if test="goods.kgliao=='TK'">
										自购(TK)
									</s:if>
									<s:elseif test="TK AVL">
										指定供应商(TK AVL)
									</s:elseif>
									<s:elseif test="CS">
										客供(CS)
									</s:elseif>
									<s:elseif test="TK Price">
										完全指定(TK Price)
									</s:elseif>
								</SPAN>
							</td>
						</tr>
						<tr>
							<th align="right">供应商</th>
							<td><SPAN>${goods.goodsSupplier }</SPAN></td>
						</tr>
						<tr>
							<th align="right">仓区</th>
							<td><SPAN>${goods.goodHouseName }</SPAN></td>
						</tr>
						<tr>
							<th align="right">库位</th>
							<td><SPAN>${goods.goodsPosition }</SPAN></td>
						</tr>
						<tr>
							<th align="right">入库类型</th>
							<td><SPAN>${goods.goodsStyle }</SPAN>
							</td>
						</tr>
						<!--<tr>
							<th align="right">
								业务件号
							</th>
							<td>
								<SPAN>${goods.ywmarkId }</SPAN>
								<input type="hidden" name="goods.ywmarkId" value="${goods.ywmarkId }"/>
							</td>
						</tr>
						--><tr>
							<th align="right">入库日期</th>
							<td><SPAN>${goods.goodsChangeTime }</SPAN></td>
						</tr>
						<tr >
							<td	colspan="4" align="center">
								<input type="hidden" value="${goods.goodsId}" name="goodsId"/>
								<input type="submit" value="添加到待申请" id="sub"   style="width:95px;height: 35px;"/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="reset" value="重置" style="width:90px;height: 35px;"/>
							</td>
						</tr>
					</table>
				</form>
				<input type="hidden" id="rebeack" value='${errorMessage}'/>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<script type="text/javascript">
$(document).ready(function(){
		var rebeack=$("#rebeack").val();
		if(rebeack =="申请成功"){
			alert("申请成功!");
			parent.chageDiv('none');
			parent.window.location.reload();
			//parent.window.open("goodsAction!showbfgList.action");
		}else if(rebeack!='' || rebeack!=''){
		}
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
	var goodsCurQuantity = $("#goodsCurQuantity").text();
	var goodsPrice = $("#goodsPrice").text();
	var disponseNum =$("#disponseNum").val();
	var disponsePrice =$("#disponsePrice").val();
	if(disponseNum ==null || disponseNum==''){
		alert("申请数量不能为空！");
		$("#disponseNum").focus();
		return false;
	}
<%--	if(disponsePrice ==null || disponsePrice==''){--%>
<%--		alert("申请价格不能为空！");--%>
<%--		$("#disponsePrice").focus();--%>
<%--		return false;--%>
<%--	}--%>
	if(parseInt(disponseNum)<=0 || parseInt(disponseNum)>parseFloat(goodsCurQuantity)){
		alert("申请数量不正确");
		$("#disponseNum").focus();
		return false;
	}
	/*if(disponsePrice<=0 || disponsePrice>goodsPrice){
		alert("申请价格不正确");
		$("#disponsePrice").focus();
		return false;
	}*/
	return true;
}



</script>
	</body>
</html>
