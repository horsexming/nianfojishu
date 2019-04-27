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
	<body style="background: url('');">
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
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<div id="printDiv">
					<div align="center" style="font-weight: bolder;">
						<table width="100%" border="1" style="border-collapse: collapse;">
							<tr>
								<td>
									<img src="${companyInfo.logoOKjpg}" height="40px" width="80px">
								</td>

								<td colspan="12"
									style="width: 100%; text-align: center; font-size: 25; font-weight: bold;">
									${companyInfo.name}—————————领用记录详单
								</td>
							</tr>
							<tr>

							<td align="center">
									序号
								</td>
								<td align="center">
									卡号
								</td>
								<td align="center">
									领用人
								</td>
								<td align="center">
									部门
								</td>
								<td align="center">
									编号
								</td>
								<td align="center">
									物品名称
								</td>
								<td align="center">
									规格
								</td>
								<td align="center">
									车型
								</td>
								<td align="center">
									数量
								</td>
								<td align="center">
									单位
								</td>
								<td align="center">
									仓库名
								</td>
								<td align="center" width="160px;">
									领用时间
								</td>
							</tr>
							<s:iterator value="list" id="pageList">
					<tr align="center">
						<td>
								${pageList.id}
						</td>
						<td>
								${pageList.cardNum}
						</td>
						<td>
							${pageList.peopleName}
						</td>
						<td>
							${pageList.dept}
						</td>
						<td>
							${pageList.number}
						</td>
						<td>
							${pageList.matetag}
						</td>
						<td>
							${pageList.format}
						</td>
						<td>
							${pageList.carType}
						</td>

						<td>
							${pageList.num}
						</td>
						<td>
							${pageList.unit}
						</td>
						<td>
							${pageList.storehouse}
						</td>

						<td>
							${pageList.date}
						</td>
								</tr>
							</s:iterator>
						</table>
						<br>
					</div>
				</div>
				<input type="button" value="打印" onclick="pagePrint('printDiv')" />
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
	</body>
</html>
