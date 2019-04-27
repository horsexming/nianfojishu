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
	<style type="text/css">
		
		body{text-align:center;}		
		
		</style>
		
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<h3>修改开票明细信息</h3>
				
				<form action="huikuanAction!updateSta.action" method="post" >
					<input type="hidden" name="id" value="${tahkSellSta.taHkHuikuan.id}" />
					<input type="hidden" name="tahkSellSta.id" value="${tahkSellSta.id}" />
					<table width="80%" class="table">					
					<tr><td>客户:</td><td><input type="text" name="tahkSellSta.hkSellCumpanyName" value="${tahkSellSta.hkSellCumpanyName }" /></td></tr>
					<tr><td>零件号:</td><td><input type="text" name="tahkSellSta.hkSellMarkId" value="${tahkSellSta.hkSellMarkId }" /></td></tr>
					<tr><td>开票数量:</td><td><input type="text" name="tahkSellSta.hkSellCount" value="${tahkSellSta.hkSellCount }" /></td></tr>
					<tr><td>送货单号:</td><td><input type="text" name="tahkSellSta.hkSellSendId" value="${tahkSellSta.hkSellSendId }" /></td></tr>
					<tr><td>订单号:</td><td><input type="text" name="tahkSellSta.hkSellOutOrderId" value="${tahkSellSta.hkSellOutOrderId}" /></td></tr>
					<tr><td>备注:</td><td><input type="text" name="tahkSellSta.hkSellMore" value="${tahkSellSta.hkSellMore }" /></td></tr>
				
					
						<tr>						
							<td colspan="2" align="center">
								<input type="submit" value="提交"
									style="width: 60px; height: 40px;" align="top">
							</td>
						</tr>
						</table>
						</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>	
	</body>    

</html>
