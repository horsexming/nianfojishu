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
		<center>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">
					
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<div id="printDiv">
				<table border="1" style="font-size:14px;" >
				<tr>
				<th width="40%"><img src="${companyInfo.logoOKjpg}" width="35px" height="35px" /></th>
				<th width="59%" style="font-size: 16;color: red;font-weight: bolder;text-align:left">出库单</th>
				</tr>
					<tr>
					<th>批&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;次:</th><td>${sell.sellLot } </td></tr>
					<tr>
					<th>件&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号:</th><td>${sell.sellMarkId } </td></tr>
					<tr>
					<th>总成件号:</th><td>${sell.sellProMarkId }</td></tr>
					<tr>
					<th>品&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:</th><td>${sell.sellGoods } </td></tr>
					<tr>
					<th>规&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;格:</th><td>${sell.sellFormat } </td>					
					
					</tr>
					<tr>
					<th>数&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;量:</th><td><b>${sell.sellCount }</b>  </td></tr>
					<tr>
					<th>计量单位:</th><td>${sell.sellUnit } </td></tr>
					
					<tr>
					<th>仓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;库:</th><td>${sell.sellWarehouse } </td>	</tr>
					
					<tr>
					<th>仓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;区:</th><td>${sell.goodHouseName} </td>	</tr>
					
					
					<tr>		
					<th>库&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位:</th><td>${goods.goodsPosition} </td>					
					</tr>
					<tr>
					<th>换算数量:</th><td><b>${sell.sellZhishu }</b></td></tr>
					<tr>
					<th>客&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;户:</th><td>${sell.sellCompanyName } </td></tr>
					<tr>					
					<th>出库类型:</th><td>${sell.style}		
					 </td></tr>
					<tr><th>工艺卡号:</th><td>${sell.sellArtsCard } </td></tr>
					<s:if test="%{null!=sell.sellCharger}">
					<tr><th>领料人:</th><td>${sell.sellCharger } </td></tr>
					</s:if>
					<tr>
					<th>出库日期:</th><td>${sell.sellDate } </td>
					</tr>
						
					</table>
					</div>
					
					<input style="width: 80px; font-size: 18px;" onclick="printStick()"
					type="button" value="打印" />
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
		function printStick(){
			pagePrintOld('printDiv');
			var id=${sell.sellId }
			$.ajax({
					type : "POST",
					url : "sellAction!print.action",
					data : {
						id : id
					},
					dataType : "json",
					success : function(msg) {
					}
				});		
		}
		</script>
	</body>
</html>
