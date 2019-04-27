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
		<div>
			<div align="center">
				<div id="rootTemplateDiv" style="height: 600px;">
					<form action="PurchasedPartsInputAction_update.action"
						method="post" onsubmit="check">
						<input type="hidden" name="procardStruts" value="总成" />
						<table class="table" style="width: 60%;">
							<tr>
								<th align="center" colspan="2">
									修改外购件入库申请(update)
								</th>
							</tr>
							<tr>
								<th align="right" style="width: 25%;">
									件号(Part No.):
								</th>
								<td>
									<input type="hidden" name="purchasedPartsInput.id" value="<s:property value="purchasedPartsInput.id"/>">
									<input  value="<s:property value="purchasedPartsInput.id"/>" readonly="readonly">
								</td>
							</tr>
							<tr>
								<th align="right">
									名称(Name):
								</th>
								<td>
									<input  value="<s:property value="purchasedPartsInput.id"/>" readonly="readonly">
								</td>
							</tr>
							<tr>
								<th align="right">
									批次(Name):
								</th>
								<td>
									<input  value="<s:property value="purchasedPartsInput.inCount"/>" readonly="readonly">
								</td>
							</tr>
							<tr>
								<th align="right">
									申请数量(Models):
								</th>
								<td>
									<input name="purchasedPartsInput.inCount" value="<s:property value="purchasedPartsInput.inCount"/>">
								</td>
							</tr>
							<tr>
								<td align="center" colspan="2">
									<input type="submit" value="修改(update)"
										style="width: 80px; height: 50px;" />
								</td>
							</tr>
						</table>
						<!-- ***************************************************************************************** -->
						
					</form>
				</div>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
		</SCRIPT>
	</body>
</html>
