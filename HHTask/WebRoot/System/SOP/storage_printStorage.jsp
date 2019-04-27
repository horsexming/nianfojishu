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
									${companyInfo.name}—————————入库详单
								</td>
							</tr>
							<tr>

								<td align="center">
									编号
								</td>
								<td align="center">
									入库时间
								</td>
								<td align="center">
									类型
								</td>
								<td align="center">
									物品名称
								</td>
								<td align="center">
									规格
								</td>
								<td align="center">
									物品编码
								</td>
								<td align="center">
									单位
								</td>
								<td align="center">
									数量
								</td>
								<td align="center">
									库别
								</td>
								<td align="center">
									库位
								</td>
								<td align="center">
									经办人
								</td>
								<td align="center">
									申请部门
								</td>
								<td align="center" width="160px;">
									用途
								</td>


							</tr>
							<s:iterator value="list" id="pageList">
								<tr align="center">
									<td>
										${pageList.id}
									</td>
									<td>
										${pageList.date}
									</td>
									<td>
										${pageList.parClass}
									</td>
									<td>
										${pageList.matetag}
									</td>
									<td>
										${pageList.format}
									</td>
									<td>
										${pageList.number}
									</td>
									<td>
										${pageList.unit}
									</td>
									<td>
										${pageList.num}
									</td>
									<td>
										${pageList.storehouse}
									</td>
									<td>
										${pageList.position}
									</td>
									<td>
										${pageList.jinbanren}
									</td>
									<td>
										${pageList.dept}
									</td>
									<td>
										${pageList.more}
									</td>
								</tr>
							</s:iterator>
						</table>
						<br>
					</div>
				</div>
				<input type="button" value="打印" onclick="pagePrintOld('printDiv')" />
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
	</body>
</html>
