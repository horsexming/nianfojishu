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
			<div align="center">
					<table class="table" style="width: 95%;">
						<tr>
							<th>
								批次:
							</th>
							<td>
								${goods.goodsLotId }
							</td>
							<th>
								件号:
							</th>
							<td>
								${goods.goodsMarkId }
							</td>
							<th>
								品名:
							</th>
							<td>
								${goods.goodsFullName }
							</td>
							<th>
								规格：
							</th>
							<td>
								${goods.goodsFormat }
							</td>
						</tr>
						<tr>
							<th>
								库存量：
							</th>
							<td>
								${goods.goodsCurQuantity}
							</td>
							<th>
								计量单位：
							</th>
							<td>
								${goods.goodsUnit }
							</td>
							<th>
								库别：
							</th>
							<td>
								${goods.goodsClass }
							</td>
							<th>
								库位:
							</th>
							<td>
								${goods.goodsPosition}
							</td>

						</tr>
						<tr>
							<th>
								换算数量:
							</th>
							<td>
								${goods.goodsZhishu}
							</td>
							<th>
								客户:
							</th>
							<td>
								${goods.goodsCustomer}
							</td>

							<th>
								入库类型
							</th>
							<td>
										${goods.goodsStyle}
							</td>
							<th>
								工艺卡号:
							</th>
							<td>${goods.goodsArtsCard}
							</td>
						</tr>
						<tr>
							<th>
								供应商:
							</th>
							<td>
									${goods.goodsSupplier}
							</td>
							<th>
								备注:
							</th>
							<td>${goods.goodsMore2}
							</td>

						</tr>
					</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
</script>
	</body>
</html>
