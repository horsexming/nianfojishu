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
				<h3>设变退库</h3>
			</div>
			<br/>
			<form method="post" action="GoodsStoreAction!sbtk.action">
			<input type="hidden" name="cpage" value="${cpage}">
			<input type="hidden" name="id" value="${procardSbWasterxc.id}">
			<table class="table">
				<tr>
					<th>件号</th><td align="center">${procardSbWasterxc.markId}</td>
					<th>名称</th><td align="center">${procardSbWasterxc.proName}</td>
					<th>版本</th><td align="center">${procardSbWasterxc.banbenNumber}</td>
					<th>供料属性</th><td align="center">${procardSbWasterxc.kgliao}</td>
				</tr>
				<tr>
					<th>物料类别</th><td align="center">${procardSbWasterxc.wgType}</td>
					<th>工序号</th><td align="center">${procardSbWasterxc.processNo}</td>
					<th>工序名称</th><td align="center">${procardSbWasterxc.processName}</td>
					<th>库别</th>
					<td align="center"> 
					<s:if test="procardSbWasterxc.procardStyle=='自制'||procardSbWasterxc.processNo!=null">
					<SELECT name="goodsStore.goodsStoreWarehouse">
					<option>在制品</option>
					</SELECT> 
					</s:if>
					<s:else>
					<SELECT name="goodsStore.goodsStoreWarehouse">
					<option>外购件库</option>
					</SELECT> 
					</s:else>
					</td>
				</tr>
				<tr>
					<th>仓区</th><td align="center"><input name="goodsStore.goodHouseName"></td>
					<th>库位</th><td align="center"><input name="goodsStore.goodsStorePosition"></td>
					<th>数量</th><td align="center"><input name="goodsStore.goodsStoreCount" value="${procardSbWasterxc.tkclcount}"></td>
					<th>单位</th><td align="center">${procardSbWasterxc.util}</td>
				</tr>
				<tr>
					<td align="center" colspan="4"><input type="button" style="width: 80px;height: 60px;" value="提交"> </td>
				</tr>
			</table>
			</form>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
