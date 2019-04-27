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
		<div id="gongneng">

			<div align="center">
				<table class="table" style="width: 50%">
					<tr>
						<th>
							项目流程
						</th>
						<th>
							项目操作
						</th>
					</tr>
					<tr>
						<td>
							项目人员
						</td>
						<td>
							<a
								href="proUserAction!getProUserAddPage.action?proUser.proId=${pro.id}">添加</a>
							<a
								href="proUserAction!findAllProUserByproId.action?proUser.proId=${pro.id}">查看</a>
						</td>
					</tr>
					<tr>
						<td>
							项目产品
						</td>
						<td>
							<a
								href="proProductAction!getProProductAddPage.action?proProduct.proId=${pro.id}">添加</a>
							<a
								href="proProductAction!findAllProProductByproId.action?proProduct.proId=${pro.id}">查看</a>
						</td>
					</tr>
					<tr>
						<td>
							项目工装
						</td>
						<td>
							<a
								href="proToolingAction!getProToolingAddPage.action?proTooling.proId=${pro.id}">添加</a>
							<a
								href="proToolingAction!findAllProToolingByproId.action?proTooling.proId=${pro.id}">查看</a>
						</td>
					</tr>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
