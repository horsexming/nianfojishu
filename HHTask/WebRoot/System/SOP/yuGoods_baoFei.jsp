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
		              余料报废
		 </div>
			<div align="center">
			<form action="goodsAction!baoFeiYuliao.action" method="post" onsubmit="validate();">
			<input type="hidden" value="${goods.goodsId}" name="id"/>
			<table class="table">
			<tr ><td align="center">来源件号</td><td align="center">来源批次</td><td align="center">牌号</td><td align="center">规格</td><td align="center">数量</td><td align="center">可申请数量</td><td align="center">单位</td><td align="center">申请数量</td><td align="center">操作</td>
			</tr>
			<tr ><td >${goods.ylMarkId}</td><td align="center">${goods.ylSelfCard}</td><td align="center">${goods.goodsMarkId}</td>
			<td align="center">${goods.goodsFormat}</td><td align="center">${goods.goodsCurQuantity}</td>
			<td align="center"><s:if test="goods.ylApplyCount==null">${goods.goodsCurQuantity}<input type="hidden" id="ylApplyCount" value="${goods.goodsCurQuantity}"/>
			</s:if>
			<s:elseif test="#goods.ylApplyCount>#goods.goodsCurQuantity">
			<input type="hidden" id="ylApplyCount" value="0"/>0
			</s:elseif>
			<s:else><input id="ylApplyCount" value="${goods.goodsCurQuantity-goods.ylApplyCount}"/>
			</s:else>
			</td>
			<td align="center">${goods.goodsUnit}</td>
			<td align="center"><input name="yuLiaoApply.applyCount" id="applyCount" onkeyup="checkApplyCount()"/> </td><td align="center"><input type="submit" value="报废"> </td>
			</tr>
			</table>
			</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
		function checkApplyCount(){
			var applyCount=$("#applyCount").val();
	if(isNaN(applyCount)){
		alert("请输入数字");
		$("#applyCount").val(0);
	}else{
		var ylApplyCount=$("#ylApplyCount").val();
		if((applyCount-ylApplyCount)>0){
			alert("申请数量超额!");
			$("#applyCount").val(0);
		}
		
	}
		}
		function validate(){
			var applyCount=$("#applyCount").val();
			var ylApplyCount=$("#ylApplyCount").val();
		  if((applyCount-ylApplyCount)>0){
			alert("申请数量超额!");
			$("#applyCount").val(0);
			return false;
		  }
		}
		</SCRIPT>
	</body>
</html>
