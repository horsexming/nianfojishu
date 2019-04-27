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
	<title></title>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<form action="yuanclAndWaigjAction_fenMoTzSq.action" method="POST" >
					<table class="table">
						<tr>
							<td align="right">
								件号
							</td>
							<td>
								<input type="text" value="${yuanclAndWaigj.markId}" readonly="readonly"
								 name="fmtzr.markId"/>
							</td>
							<td align="right">
								名称
							</td>
							<td>
								<input type="text" value="${yuanclAndWaigj.name}" 
								 readonly="readonly"	name="fmtzr.name"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								每公斤喷粉面积(调整前)
							</td>
							<td>
								<input type="text" value="${yuanclAndWaigj.areakg}" 
								readonly="readonly" name="fmtzr.areakg0"/>
							</td>
							<td align="right">
								每公斤喷粉面积(调整后)
							</td>
							<td>
								<input type="text" value="${yuanclAndWaigj.areakg}" name="fmtzr.areakg1"
								onchange="numyanzheng(this)"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								用量标准(调整前)
							</td>
							<td>
								<input type="text" value="" name="fmtzr.ylbz0" onchange="numyanzheng(this)"/>
							</td>
							<td align="right">
								用量标准(调整后)
							</td>
							<td>
								<input type="text" value="" name="fmtzr.ylbz1" onchange="numyanzheng(this)"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								调整原因
							</td>
							<td colspan="3">
								<textarea rows="2" cols="30" name="fmtzr.tzReason"></textarea>
							</td>
						</tr>
					</table>
					<input type="submit" value="申请调整" class="input"/>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
