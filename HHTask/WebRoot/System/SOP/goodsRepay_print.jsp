<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
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
<%--					<a--%>
<%--						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"--%>
<%--						style="color: #ffffff">刷新</a>--%>
<%--				--%></div>
			</div>

			<div align="center">
				<div id="printDiv">
					<table  style="font-size: 14px; font-weight: bold;" >
						<tr>
							<td align="left" width="80px">   
								<img src="${companyInfo.logoOKjpg}" width="45px" height="45px" />
							</td>
							<td style="font-size: 15px; font-weight: bold">
								${companyInfo.shortName}归还清单
							</td>
						</tr>
						<%--<tr>
							<th>
								批次:
							</th>
							<td>
								${good.goodsStoreLot }
							</td>
						</tr>
						--%>
						<tr>
							<th>
								件号:
							</th>
							<td>
								${repay.goodsMarkId}
							</td>
						</tr>
						<tr>
							<th>
								规格:
							</th>
							<td>
								${repay.format}
							</td>
						</tr>
						<tr>
							<th>
								名称:
							</th>
							<td>
								${repay.goodsFullName}
							</td>
						</tr>
						<tr>
							<!-- 判断类型 -->
							<th>
								库别:
							</th>
							<td>
								${repay.storehouse}
							</td>
						</tr>
						<tr>
							<!-- 判断类型 -->
							<th>
								仓区:
							</th>
							<td>
								${repay.goodHouse}
							</td>
						</tr>
						
						<tr>
							<!-- 判断类型 -->
							<th>
								库位:
							</th>
							<td>
								${repay.wareHouse }
							</td>
						</tr>
						<tr>
							<th>
								数量:
							</th>
							<td>
								<b>${repay.num }</b>
							</td>
						</tr>
						<tr>
							<th>
								单位:
							</th>
							<td>
								${repay.unit}
							</td>
						</tr>
						
						<tr>
							<th>
								归还人:
							</th>
							<td>
								${repay.peopleName}
							</td>
						</tr>
						<tr>
							<th>
								负责人:
							</th>
							<td>
								${repay.admin}
							</td>
						</tr>
						<tr>
							<th>
								归还时间:
							</th>
						<td width="300px">
								${lend.date}
							
<%--								<fmt:formatDate value="${repay.rDate}" pattern="yyyy-MM-dd HH:mm:ss"/>--%>
							</td>
						</tr>
					</table>
				</div>
				<form action="LendNectAction!findIsLendBycardNum.action" method="post">
					<input type="hidden" name="lend.id" value="${repay.lendId}"/>
					<input type="hidden" name="lend.cardNum" value="${repay.cardNum}"/>

				<input style="width: 120px; font-size: 18px;"
					 type="submit" value="返回上级">
				<input style="width: 80px; font-size: 18px;"
					onclick="pagePrintOld('printDiv')" type="button" value="打印">
				</form>
			</div>
			
			
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)  onclick="returnShang()"-->
		<SCRIPT type="text/javascript">
			function returnShang(){
				window.location.href="LendNectAction!findIsLendBycardNum.action?lend.id="+${repay.lendId}+"&lend.cardNum="+${repay.cardNum};
			}
		</SCRIPT>
		
	</body>
</html>
