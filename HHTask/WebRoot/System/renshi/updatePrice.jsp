<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
		<style type="text/css">
table {
	font-size: 14px;
	padding: 0px;
	margin: 0px;
	border-collapse: collapse;
	/* 关键属性：合并表格内外边框(其实表格边框有2px，外面1px，里面还有1px哦) */
	border: solid #999; /* 设置边框属性；样式(solid=实线)、颜色(#999=灰) */
	border-width: 1px 0 0 1px;
	width: 980px;
}

table th,table td {
	border: solid #999;
	border-width: 1 1px 1px 1;
	padding: 2px;
}
</style>
	</head>
	<body bgcolor="#ffffff">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border:  solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a href="" style="color: #ffffff">添加功能</a>
				</div>
			</div>
			
			<div align="center">
				<form action="TijingpriceAction!update.action" method="post">
					<table align="center">
						<tr>
							<th colspan="6">
								<font size="5">修改提奖价格表信息</font>
							</th>
						</tr>
						<tr>
							<th>
								件号
							</th>
							<td>
								<input type="hidden" value="${tijiangprice.priceid}"
									name="tijiangprice.priceid" />
								<input type="text" name="tijiangprice.pricemarkId"
									value="${tijiangprice.pricemarkId}" />
							</td>
							<th>
								型别
							</th>
							<td>
								<input type="text" name="tijiangprice.pricestyle"
									value="${tijiangprice.pricestyle}" />
							</td>
							<th>
								规格
							</th>
							<td>
								<input type="text" name="tijiangprice.priceformt"
									value="${tijiangprice.priceformt }" />
							</td>
						</tr>

						<tr>
							<th>
								批次
							</th>
							<td>
								<input type="text" name="tijiangprice.pricelotId"
									value="${tijiangprice.pricelotId }" />
							</td>
							<th>
								计件单价
							</th>
							<td>
								<input type="text" name="tijiangprice.pricefactPrice"
									value="${tijiangprice.pricefactPrice }" />
							</td>
							<th>
								含税价
							</th>
							<td>
								<input type="text" name="tijiangprice.pricetoxprice"
									value="${tijiangprice.pricetoxprice }" />
							</td>
						</tr>

						<tr>
							<th>
								定额（工时）
							</th>
							<td>
								<input type="text" name="tijiangprice.pricedinge"
									value="${tijiangprice.pricedinge}" />
							</td>
							<th>
								开始数量
							</th>
							<td>
								<input type="text" name="tijiangprice.pricesenacount"
									value="${tijiangprice.pricesenacount}" />
							</td>
							<th>
								结束数量
							</th>
							<td>
								<input type="text" name="tijiangprice.priceendcount"
									value="${tijiangprice.priceendcount}" />
							</td>
						</tr>

						<tr>
							<th>
								状态
							</th>
							<td colspan="5">
								<select name="tijiangprice.pricedefault">
									<option value="${tijiangprice.pricedefault}">
										${tijiangprice.pricedefault}
									</option>
									<option value="正常使用">
										正常使用
									</option>
									<option value="备用">
										备用
									</option>
									<option value="外委">
										外委
									</option>
								</select>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								提奖类别<select name="tijiangprice.priceTjStyle">
									<option value="单价计价">单件计价</option>
									<option value="累计计价">累计计价</option>
									<option value="配套计价">配套计价</option>
									
									</select>
							</td>
						</tr>
						<tr>
							<th>
								备注
							</th>
							<td colspan="5">
								<input style="width: 250px; height: 80px" type="text"
									name="tijiangprice.pricemore"
									value="${tijiangprice.pricemore }" />
							</td>
						</tr>
						<tr>
							<td colspan="6" align="center">
								<input type="submit" value="确   定" />
								&nbsp;&nbsp;
								<input type="reset" value="取   消" />
							</td>
						</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
	</body>
















</html>
