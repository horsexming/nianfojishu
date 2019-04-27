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
			<form action="">
				<table class="table">
					<tr>
						<th>
							<input type="checkbox" onclick="chageAllCheck(this)">
						</th>
						<th>
							序号
						</th>
						<th>
							件号
						</th>
						<th>
							批次
						</th>
						<th>
							内部订单号
						</th>
						<th>
							业务件号
						</th>
						<th>
							仓区
						</th>
						<th>
							需求量
						</th>
						<th>
							需领量
						</th>
						<th>
							已领量
						</th>
						<th>
							库存量
						</th>
					</tr>
					<s:iterator value="procardList" id="pageprocard" status="">
					
					
					</s:iterator>
				</table>
			</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
