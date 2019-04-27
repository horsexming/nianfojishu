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
					<a href="mealAction!allFind.action" style="color: #ffffff">全部客饭票查询/</a>
				<a href="mealAction!reKan.action" style="color: #ffffff">全部未通过审核总览/</a>
				<a href="mealAction!reKan3.action" style="color: #ffffff">全部通过审核总览</a>
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<div align="center" id="image">
					<table border="1" align="center" width="600px" height="380px"
						cellpadding="0" cellspacing="0">
						<tr>
							<td colspan="4" align="center">
								${companyInfo.name}
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<img src="barcode.action?msg=${mealticket.barcode}&type=code39"
									style="height: 45px; width: 300px;">
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								客饭票
							</td>
						</tr>
						<tr>
							<td align="right" width="90px">
								来客姓名：
							</td>
							<td align="left" width="250px">
								${mealticket.name}
							</td>
							<td align="right" width="90px">
								职务：
							</td>
							<td align="left" idth="150px">
								${mealticket.job}
							</td>
						</tr>
						<tr>
							<td align="right">
								单位：
							</td>
							<td align="left">
								${mealticket.company}
							</td>
							<td align="right">
								人数：
							</td>
							<td align="left">
								${mealticket.number}
							</td>
						</tr>
						<tr>
							<td align="right">
								事由：
							</td>
							<td align="left" colspan="4">
								${mealticket.reason}
							</td>
						</tr>
						<tr>
							<td align="right">
								申请人：
							</td>
							<td align="left">
								${mealticket.manage}
							</td>
							<td align="right">
								审核人：
							</td>
							<td align="left">
								${mealticket.checkname}
							</td>
						</tr>
						<tr>
							<td align="right">
								有效日期：
							</td>
							<td>
								${mealticket.intime}
							</td>
							<td align="right">
								申请日期：
							</td>
							<td>
								${mealticket.addDate}
							</td>
						</tr>
						<tr><td align="right">状态：</td>
							<td>${mealticket.fuck}</td>
							<td align="right">可打印次数：</td>
							<td>${mealticket.copy}</td>
						</tr>
					</table>
					<s:if test='%{"未通过".equals(mealticket.fuck)}'>
					<table width="165px" height="50px">
					<tr>
						<td>
							<form action="mealAction!updatee.action?mealticket.id=${mealticket.id}" method="post">
								<input type="submit"  value="审核同意"
								 onclick="return window.confirm('确定通过审核！！！')"
									style="width: 120px; height: 50px;" />
							</form>	
						</td>
						<td>
							<form action="mealAction!redown.action?mealticket.id=${mealticket.id}" method="post">
								<input type="submit"  value="审核打回"
									style="width: 120px; height: 50px;" />
							</form>	
						</td>
					</tr>
				</table>
				</s:if><s:else>
				</s:else>
			</div>
		</div>
	</div>
	<%@include file="/util/foot.jsp"%>
	</body>
</html>