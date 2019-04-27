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
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 980px;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">信息展示</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								id="closeTcDiv" height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="showYcl" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 500px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		<div id="gongneng" style="width: 100%;">
		 <div align="center">
		             余料转原材料
		 </div>
			<div align="center">
			<form action="goodsAction!ylchageToYcl.action" method="post" onsubmit="validate();">
			<input type="hidden" value="${goods.goodsId}" name="id"/>
			<table class="table">
			<tr ><td align="center">来源件号</td>
			<td align="center">来源批次</td>
			<td align="center">牌号</td>
			<td align="center">规格</td>
			<td align="center">数量</td>
			<td align="center">可申请数量</td>
			<td align="center">单位</td>
			<td align="center">申请数量</td>
			<td align="center">新材料牌号</td>
			<td align="center">新材料规格</td>
			<td align="center">新材料数量</td>
			<td align="center">新材料单位</td>
			<td align="center">操作</td>
			</tr>
			<tr ><td >${goods.ylMarkId}</td>
			<td align="center">${goods.ylSelfCard}</td>
			<td align="center">${goods.goodsMarkId}</td>
			<td align="center">${goods.goodsFormat}</td>
			<td align="center">${goods.goodsCurQuantity}</td>
			<td align="center"><s:if test="goods.ylApplyCount==null">
			 ${goods.goodsCurQuantity}<input type="hidden" id="ylApplyCount" value="${goods.goodsCurQuantity}"/>
			</s:if>
			<s:elseif test="#goods.ylApplyCount>#goods.goodsCurQuantity">
			0<input type="hidden"  id="ylApplyCount" value="0"/>
			</s:elseif>
			<s:else>
			${goods.goodsCurQuantity-goods.ylApplyCount}<input type="hidden"  id="ylApplyCount" value="${goods.goodsCurQuantity-goods.ylApplyCount}"/>
			</s:else>
			</td>
			<td align="center">${goods.goodsUnit}</td>
			<td align="center"><input style="width: 50px" name="yuLiaoApply.applyCount" id="applyCount" onkeyup="checkApplyCount()"/> </td>
			<td align="center"><input name="yuLiaoApply.newGoodsMarkId" id="newGoodsMarkId" readonly="readonly"/><input type="button" value="请选择" onclick="selectYcl()"> </td>
			<td align="center"><input name="yuLiaoApply.newGoodsFormat" id="newGoodsFormat" readonly="readonly"/></td>
			<td align="center"><input style="width: 50px"  name="yuLiaoApply.changeCount" id="changeCount" onkeyup="mustBeNumber('changeCount')"/></td>
			<td align="center"><input style="width: 50px" name="yuLiaoApply.newGoodsUnit" id="newGoodsUnit" readonly="readonly"></select></td>
			<td align="center"><input type="submit" value="转原材料"> </td>
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
	function selectYcl(){
		$("#showYcl").attr("src",
			"procardTemplateGyAction_showYclAndWgj.action?type=yl");
	chageDiv('block');
	}
		</SCRIPT>
	</body>
</html>
