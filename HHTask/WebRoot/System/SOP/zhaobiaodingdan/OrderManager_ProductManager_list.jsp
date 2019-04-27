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
					<font color="#ffffff">功能使用</font>
				</div>
				<div style="float: left; width: 45%; padding-top:  5px;"
					align="right">
					<a href="zhaobiaoAction!listAll.action" style="color: #ffffff">刷新</a>
				</div>
			</div>
			<hr color="#BFEFFF">
			<div align="center" id="d1">
			<form action="DingdanAction!printStorage.action" method="post"
					onsubmit="return vali()">
			
				<table class="table" align="left">
			<s:iterator value="list" id="pageList" status="pageStatus">
						<tr>
						<td>
						 <td align="center">
							序号:
						 ${pageStatus.index+1}
						</td>
						<td align="center">
							订单编号:
							${pageList.orderNum }
						</td>
						<s:if test="#pageList.totalAmount == 0.0">
							<td align="center">
							总金额:
						
								0.00
							</td>
						</s:if>
						<s:else>
							<td align="center">
							总金额:
								${pageList.totalAmount }
							</td>
						</s:else>
						<td align="center">
							客户:
							${pageList.custome.clientcompanyname}
						</td>
						<td align="center">
							交付日期:
							${pageList.paymentDate}
						</td>
						<td align="center">
							跟单人:
							${pageList.documentaryPeople }
						</td>
						<td align="center">
							开单人:
							${pageList.billingPeople}
						</td>
						<td align="center">
							交付状态:
							${pageList.deliveryStatus }
						</td>
						<td align="center">
							回款状态:
							${pageList.backSection }
						</td>
					</tr>
					<s:iterator value="#oManager.products" id="product" status="pageIndex">
							<tr>	 	 	 	 
							<th>编号</th><th>件号</th><th>产品名称</th><th>数量</th><th>单价</th><th>总价</th>
							</tr>
							<tr>
							<td>${pageIndex.index+1}</td><td>${product.name}</td><td>${product.pieceNumber}</td>
							<td>${product.num}</td><td>${product.unit}</td><td>${product.unitPrice}</td>
							</tr>
							</s:iterator>
					
					</s:iterator>

				</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->

	</body>
	<SCRIPT type="text/javascript">
	</SCRIPT>

</html>
