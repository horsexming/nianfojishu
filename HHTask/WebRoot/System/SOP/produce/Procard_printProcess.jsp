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
		<script src="<%=basePath%>/javascript/jquery.corner.js"
			type="text/javascript">
</script>
		<style type="text/css">
body {
	background: #ffffff;
}
</style>
	</head>
	<body style="background-color: #ffffff;">
		<center>
			<div id="printDiv">
				<table class="table">
					<tr>
						<th colspan="2">
							提交工序成功
						</th>
					</tr>
					<tr>
						<th>
							件号:
						</th>
						<td>
							1KD253059AR
						</td>
					</tr>
					<tr>
						<th>
							批次:
						</th>
						<td>
							20140600005
						</td>
					</tr>
					<tr>
						<th>
							工序号:
						</th>
						<td>
							5
						</td>
					</tr>
					<tr>
						<th>
							工序名称:
						</th>
						<td>
							配齐零件
						</td>
					</tr>
					<tr>
						<th>
							提交数量:
						</th>
						<td>
							100
						</td>
					</tr>
				</table>
			</div>
			<input type="button" onclick="pagePrint('printDiv')" value="打印"
				class="input" />
		</center>
	</body>
</html>
