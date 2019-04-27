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
				<h2>
					件号:${manualPlan.markId}共采购${manualPlan.outcgNumber}
					供应商:${waigouPlan.gysName}分配${waigouPlan.number}
				</h2>
				<table class="table">
					<tr align="center" bgcolor="#c0dcf2" height="50px">
						<th>件号</th>
						<th>批次</th>
						<th>采购数量</th>
						<th>入库数量</th>
						<th>库存量</th>
					</tr>
					<s:iterator value="list" id="pageList" status="statussdf">
						<s:if test="#statussdf.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'')">
								</s:else>
							<td>${pageList.markId}</td>
							<td>${pageList.procard.selfCard}</td>
							<td>${pageList.cgnumber}</td>
							<td>${pageList.rukuNum}</td>
							<td>${manualPlan.cgbl}</td>
								</tr>
					</s:iterator>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
