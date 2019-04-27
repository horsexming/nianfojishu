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
		<div 
			style="width: 100%; ">
			<div align="center">
				<form action="QexamineAction!fixQexamine.action" method="post">
					<table class="table" style="width: 50%;">
						<tr>
							<td style="stress: 16px;" align="center">
								${qexamine.id}<input type="hidden" name="qexamine.id"
									value="${qexamine.id}"
									 />
							</td>
						</tr>
						<tr>
							<td>
								客户名称：
								<input type="text" name="qexamine.customername"
									value="${qexamine.customername}"
									 />
							</td>
						</tr>
						<tr>
							<td>
								产品名称：
								<input type="text" name="qexamine.productname"
									value="${qexamine.productname}"
									 />
							</td>
						</tr>
						<tr>
							<td>
								产品图号：
								<input type="text" name="qexamine.productdraw"
									value="${qexamine.productdraw}"
									 />
							</td>
						</tr>
						<tr>
							<td>
								引用标准：
								<input type="text" name="qexamine.referencestandard"
									value="${qexamine.referencestandard}"
									 />
							</td>
						</tr>
						<tr>
							<td>
								抽样批次：
								<input  class="Wdate"
									type="text" name="qexamine.batchsampling"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" 
									value="${qexamine.batchsampling}"/>
							</td>
						</tr>
						<tr>
							<td>
								备 &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp注：
								<input  type="text" name="qexamine.remarks" 
								value="${qexamine.remarks}"/>
							</td>
						</tr>
						
						<tr>
							<td align="center">
								<input type="submit" value="添加"
									style="width: 80px; height: 50px;" />
								<input type="reset" value="重置"
									style="width: 80px; height: 50px;" />
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
